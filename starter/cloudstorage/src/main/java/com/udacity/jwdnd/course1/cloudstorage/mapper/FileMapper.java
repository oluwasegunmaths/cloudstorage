package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getAllFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename,filesize,contenttype, userid,filedata) VALUES( #{filename},#{filesize}, #{contenttype},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    File getFile(Integer id);

    @Delete("DELETE  FROM FILES WHERE fileId = #{fileId}")
    void delete(Integer fileId);
    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByName(String filename);
}
