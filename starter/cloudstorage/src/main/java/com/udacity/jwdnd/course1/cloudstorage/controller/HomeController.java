package com.udacity.jwdnd.course1.cloudstorage.controller;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/home")
@Controller
public class HomeController implements HandlerExceptionResolver {
    private FileService fileService;
    private CredentialService credentialService;
    private NoteService noteService;
    private UserMapper userMapper;

    public HomeController(FileService fileService, CredentialService credentialService, NoteService noteService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.userMapper=userMapper;
        this.fileService = fileService;
    }
    @GetMapping
    public String getPage(
            Authentication authentication,
            Note note, Credential credential, MultipartFile file, Model model
    ) {
        String userName=authentication.getName();
        model.addAttribute("files", this.fileService.getFiles(getUserId(userName)));
        model.addAttribute("notes", this.noteService.getNotes(getUserId(userName)));
        model.addAttribute("credentials", this.credentialService.getCredentials(getUserId(userName)));
        return "home";
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        File fileDB = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFilename() + "\"")
                .body(fileDB.getFiledata());
    }

    @PostMapping
    public String post(
            Authentication authentication,
            Note note, Credential credential, @RequestParam  ("fileUpload") @Nullable MultipartFile file, Model model
    ) {
        String userName=authentication.getName();

        if (note.getNotetitle()!=null){
            note.setUserid(getUserId(userName));
            try {
                if (note.getNoteid()==null) {
                    noteService.addNote(note);
                } else {
                    noteService.update(note);
                }
            } catch (Exception e) {
                model.addAttribute("errormessage", "Action could not be completed due to "+e.getMessage());
                model.addAttribute("error", true);            }

        }else if (credential.getUrl()!=null){
            credential.setUserid(getUserId(userName));
            try {
                if (credential.getCredentialid()==null) {
                    credentialService.addCred(credential);
                } else {
                    credentialService.update(credential);

                }
            } catch (Exception e) {
                model.addAttribute("errormessage", "Action could not be completed due to "+e.getMessage());
                model.addAttribute("error", true);             }
        }else if (!file.isEmpty()){
            if (file.getSize()<128000l) {

                if (fileService.isFilenameAvailable(file.getOriginalFilename())) {
                    byte[] bytes= new byte[0];
                    try {
                        InputStream inputStream= file.getInputStream();
                        bytes = inputStream.readAllBytes();
                        File dbFile= new File(file.getOriginalFilename(),String.valueOf(file.getSize()),file.getContentType(),getUserId(userName),bytes);
                        fileService.addFile(dbFile);
                    } catch (IOException e) {
                        model.addAttribute("errormessage", "File could not be uploaded due to "+e.getMessage());
                        model.addAttribute("error", true);
                    }
                } else {
                    model.addAttribute("errormessage", "File with similar name exists");
                    model.addAttribute("error", true);

                }
            } else {
                model.addAttribute("errormessage", "File shouldn't be larger than 128 kilobytes");
                model.addAttribute("error", true);
            }
        }else {

            model.addAttribute("files", this.fileService.getFiles(getUserId(userName)));
            model.addAttribute("notes", this.noteService.getNotes(getUserId(userName)));
            model.addAttribute("credentials", this.credentialService.getCredentials(getUserId(userName)));
            return "home";
        }
        return "result";
    }
    @GetMapping("delete/{entityType}/{id}")
    public String delete(
            @PathVariable("id") int id, @PathVariable("entityType") String entityType,
            Authentication authentication,
            Note note, Credential credential,File file, Model model
    ) {
        if (entityType.equals("note")){
            noteService.delete(id);
        }else if (entityType.equals("credential")){
           credentialService.delete(id);
        }else if (entityType.equals("file")){
            fileService.delete(id);
        }

        return "result";
    }

    private Integer getUserId(String username) {
        return userMapper.getUser(username).getUserid();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> model = new HashMap<String, Object>();
        System.out.println(e.getClass().getName());
        if(e instanceof MaxUploadSizeExceededException){
            model.put("errormessage", "File size must be lesser than 128kb");

        }else {
            model.put("errormessage", "Unexpected error: " + e.getLocalizedMessage() );
        }
        model.put("error", true);
        return new ModelAndView("result", model);
    }
}


