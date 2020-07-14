package com.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.bean.Document;
import com.hr.bean.ResultObject;
import com.hr.dao.DocumentMapper;
import com.hr.service.IDocumentService;
import com.hr.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public ResultObject<Integer> ifExists(Document document) {
        ResultObject<Integer> rs = new ResultObject<Integer>();
        List<Document> list = documentMapper.selectByBean(document);
        if (list.isEmpty()) {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("文档不存在");
        } else {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("文档已存在");
        }
        return rs;
    }

    @Override
    public PageInfo<Document> getAllDocument(int limit, int page, Document document) {
        PageHelper.startPage(page, limit);
        List<Document> list = documentMapper.selectAll(document);
        PageInfo<Document> pageInfo = new PageInfo<Document>(list);
        return pageInfo;
    }

    @Override
    public ResultObject<Integer> delDocument(Document document) {
        ResultObject<Integer> rs = new ResultObject<Integer>();
        if (document == null){
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("删除成功");
        }
        Document check = new Document();
        check.setDocumentName(document.getDocumentName());
        documentMapper.deleteByPrimaryKey(document.getDocumentId());

        List<Document> list = documentMapper.selectByBean(document);
        if (list.isEmpty()) {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("删除成功");
        } else {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("删除失败");
        }
        return rs;
    }


    @Override
    public ResultObject<Integer> addDocument(Document document) {
        Document check = new Document();
        check.setDocumentName(document.getDocumentName());
        List<Document> list = documentMapper.selectByBean(check);
        ResultObject<Integer> rs = new ResultObject<Integer>();
        if (list.isEmpty()) {
            Integer total = documentMapper.insert(document);
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("上传成功");
            rs.setData(total);
        } else {
            rs.setCode(Constant.HASE_RETUEN_CODE);
            rs.setMsg("文档已上传");
        }
        return rs;
    }
}
