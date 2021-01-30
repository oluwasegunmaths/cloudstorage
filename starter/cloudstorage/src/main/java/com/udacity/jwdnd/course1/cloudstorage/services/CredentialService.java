package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper,EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService=encryptionService;
    }


    public void addCred(Credential credential) {
        setEncodedPasswordAndKey(credential);
        credentialMapper.addCred(credential);
    }

    private void setEncodedPasswordAndKey(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setEncodedPassword(encryptionService.encryptValue(credential.getRealPassword(),encodedKey));
        credential.setPasswordkey(encodedKey);
    }

    public void update(Credential credential) {
        setEncodedPasswordAndKey(credential);
        credentialMapper.update(credential);
    }
    public void delete(Integer credentialid) {

        credentialMapper.delete(credentialid);
    }


    public List<Credential> getCredentials(Integer userId) {
        List<Credential> credentials=credentialMapper.getAllCredentials(userId);
        for (Credential credential: credentials) {
              if (credential.getEncodedPassword()==null) {
                  System.out.println("passwordnullnullnull");
              }else if (credential.getPasswordkey()==null){
                  System.out.println("passwordnotnotnotnullnullnull");

              }
            credential.setRealPassword(encryptionService.decryptValue(credential.getEncodedPassword(),credential.getPasswordkey()));
        }

        return credentials;
    }
}
