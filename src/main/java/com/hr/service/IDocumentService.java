package com.hr.service;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Document;
import com.hr.bean.ResultObject;

public interface IDocumentService {
    /**
     * 查询所有文档
     * @return 数据集合
     */
    public PageInfo<Document> getAllDocument(int limit, int page, Document document);

    /**
     * 增加文档
     * @return 数据集合
     */
    public ResultObject<Integer> addDocument(Document document);

    /**
     * 删除文档
     * @return 数据集合
     */
    public ResultObject<Integer> delDocument(Document document);


    /**
     * 同名文档是否存在
     * @return 数据集合
     */
    public ResultObject<Integer> ifExists(Document document);
}
