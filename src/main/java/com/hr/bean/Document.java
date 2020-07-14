package com.hr.bean;

import java.io.Serializable;
public class Document implements Serializable {
    private Integer documentId;
    private String userName;
    private String createTime;

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return userName;
    }

    public void setDocumentName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
