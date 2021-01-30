package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getAllCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url,username,encodedPassword, userid, passwordkey) VALUES( #{url},#{username}, #{encodedPassword},#{userid}, #{passwordkey})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCred(Credential credential);

    @Delete("DELETE  FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void delete(Integer credentialid);

    @Update("UPDATE CREDENTIALS  SET (url,username,encodedPassword,passwordkey) = ( #{url},#{username}, #{encodedPassword},#{passwordkey}) WHERE credentialid = #{credentialid}")
    int update(Credential credential);

}
