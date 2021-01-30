package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    public void addFile(File file) {
         fileMapper.addFile(file);

    }

    public List<File> getFiles(Integer userId) {
        return fileMapper.getAllFiles(userId);
    }

    public File getFile(Integer id) {

       return fileMapper.getFile(id);
    }

    public void delete(Integer fileId) {

        fileMapper.delete(fileId);
    }

    public boolean isFilenameAvailable(String fileName) {
       return fileMapper.getFileByName(fileName)==null;

    }
}
