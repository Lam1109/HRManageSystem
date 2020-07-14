package com.hr.dao;

import com.hr.bean.Document;

import java.util.List;

public interface DocumentMapper {
    int deleteByPrimaryKey(Integer DocumentId);

    int insert(Document record);

    List<Document> selectAll(Document document);

    List<Document> selectByBean(Document document);

}
