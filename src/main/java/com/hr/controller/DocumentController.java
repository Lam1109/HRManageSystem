package com.hr.controller;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Document;
import com.hr.bean.ResultObject;
import com.hr.service.IDocumentService;
import com.hr.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @RequestMapping("/getAll")
    public ResultObject<List<Document>> getAllDocument(@RequestParam("limit") int limit, @RequestParam("page") int page, Document document) {
        PageInfo<Document> pageInfo = documentService.getAllDocument(limit, page, document);
        List<Document> list = pageInfo.getList();
        ResultObject<List<Document>> result = new ResultObject<List<Document>>();
        result.setCode(Constant.SUCCESS_RETUEN_CODE);
        result.setMsg("查询成功");
        result.setData(list);
        result.setCount(pageInfo.getTotal());
        return result;
    }


    // 文件上传
    @RequestMapping("/upload")
    public ResultObject<Integer> upload(@RequestParam("file") MultipartFile file) {
        ResultObject<Integer> rs = null;
        String fileName = file.getOriginalFilename();
        Document document = new Document();
        document.setDocumentName(fileName);
        String filePath = "D:/";
        File dest = new File(filePath + fileName);
        //判断文件是否存在
        rs = documentService.ifExists(document);
        // 若文件不存在,进行上传
        if (rs.getCode().equals("0")) {
            try {
                file.transferTo(dest);
            } catch (IOException e) {
            }
            // 记录添加到数据库
            return documentService.addDocument(document);
        } else {
            // 否则直接返回已存在错误
            return rs;
        }
    }


    // 文件下载
    @RequestMapping("/download")
    public ResultObject<Integer> download(Document document, HttpServletResponse response) {
        ResultObject<Integer> rs = new ResultObject<Integer>();

        String filename = document.getDocumentName();
        File file = new File("d:/" + filename);
        if (file.exists()) {
            try {
//                response.setContentType("application/octet-stream");
                response.setContentType("application/force-download");
                filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");
                response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                rs.setCode(Constant.FAILURE_RETUEN_CODE);
                rs.setMsg("下载出错");
            }
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("下载成功");
            return rs;
        } else {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("文件不存在");
            return rs;
        }
    }

    // 文件删除
    @RequestMapping("/delete")
    public ResultObject<Integer> delete(Document document, HttpServletResponse response) {
        ResultObject<Integer> rs = new ResultObject<Integer>();

        String filename = document.getDocumentName();
        File file = new File("d:/" + filename);
        if (file.exists()) {
            // 删除文件
            file.delete();
            // 删除文件记录
            return documentService.delDocument(document);
        } else {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("文件不存在");
            return rs;
        }
    }
}
