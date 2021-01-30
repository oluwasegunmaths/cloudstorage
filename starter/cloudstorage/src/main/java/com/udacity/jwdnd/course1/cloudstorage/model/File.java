package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.InputStream;
import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String filesize;
    private String contenttype;
    private Integer userid;
    private byte[] filedata;

    public File(String filename, String filesize, String contenttype, Integer userid, byte[] filedata) {
        this.filename = filename;
        this.filesize = filesize;
        this.contenttype = contenttype;
        this.userid = userid;
        this.filedata = filedata;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer id) {
        this.fileId = id;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setFilename(String username) {
        this.filename = username;
    }

//    public Blob getFiledata() {
//        return filedata;
//    }
//
//    public void setFiledata(Blob filedata) {
//        this.filedata = filedata;
//    }
}
