package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES( #{notetitle},#{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Update("UPDATE NOTES  SET (notetitle,notedescription) = ( #{notetitle}, #{notedescription}) WHERE noteid = #{noteid}")
    void update(Note note);
    @Delete("DELETE  FROM NOTES WHERE noteid = #{noteid}")
    void delete(Integer noteid);
}

