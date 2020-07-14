# HrManage

#### 介绍
本项目是一个人力资源管理系统
实训项目，基于springboot和layui的人事系统<br />项目基本来源于开源的HrMange（组长找到的，我也没找到原始地址），**改动了数据库，配色，增加了文件上传下载功能**
#### 软件架构
软件架构说明
SpringBoot+Mybatis+layui

#### 数据库设计
![image.png](https://cdn.nlark.com/yuque/0/2020/png/670787/1592362727540-94cde5d5-9779-4823-b7bf-efaac541515f.png#align=left&display=inline&height=517&margin=%5Bobject%20Object%5D&name=image.png&originHeight=786&originWidth=927&size=92081&status=done&style=shadow&width=610)

#### 主要功能
左侧导航栏所示<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/670787/1592362815279-880e5326-eeb4-43f8-88a9-ffd24554e395.png#align=left&display=inline&height=291&margin=%5Bobject%20Object%5D&name=image.png&originHeight=500&originWidth=1909&size=83168&status=done&style=shadow&width=1112)

### 实现
没有额外可说的，毕竟项目已经完整，这里主要说下文件上传下载相关的前后端代码

#### 文件上传
layui官方提供了文件上传的前端代码[layui.upload](https://www.layui.com/doc/modules/upload.html)，相关<scripts>主要在项目document.html中；后端实现在DocumentController中，我设定文件上传至D盘根目录，除了上传文件还需要在数据库中保存文件记录<document_id, document_name, createTime>
```javascript
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
```
效果<br />![upload.gif](https://cdn.nlark.com/yuque/0/2020/gif/670787/1592360752024-c068f47f-615b-47e8-a7f7-6e8a722a69b9.gif#align=left&display=inline&height=564&margin=%5Bobject%20Object%5D&name=upload.gif&originHeight=933&originWidth=1906&size=931675&status=done&style=shadow&width=1152)
<a name="azEcY"></a>
#### 文件下载
前端效果同上面gif所示，复用了一些table，同样在document.html<br />需要注意的是开始我采取传统的ajax发送request后端response的方式下载，但是浏览器可以接收到文件数据却不能下载（见document.html被注释部分），**ajax回调会把response的数据解析成字符串（参考**[ajax下载文件](https://www.cnblogs.com/qlqwjy/p/8971207.html)**）**<br />因此采用“提交表单--移除表单”的方式请求下载，将请求参数append
```javascript
function download(data){
    console.log(data)

    var documentId = data.documentId
    var createTime = data.createTime
    var documentName = data.documentName


    var downloadForm = $("<form action='/document/download' method='post'></form>")
    downloadForm.append("<input type='text' name='documentId' value='"+documentId+"'/>")
    downloadForm.append("<input type='text' name='createTime' value='"+createTime+"'/>")
    downloadForm.append("<input type='text' name='documentName' value='"+documentName+"'/>")
    $(document.body).append(downloadForm);

    downloadForm.submit()
    downloadForm.remove()

}
```

后端方面，主要是设置response格式

- 'Content-Type'：实测octet-stream和force-download都可以下载
- 'Content-Disposition'：传递filename

```java
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
```

演示<br />![download.gif](https://cdn.nlark.com/yuque/0/2020/gif/670787/1592362271650-f0ab41e1-caef-4a5d-8143-feb2e4ae069a.gif#align=left&display=inline&height=554&margin=%5Bobject%20Object%5D&name=download.gif&originHeight=938&originWidth=1906&size=190007&status=done&style=shadow&width=1126)
<a name="9UHWx"></a>
#### 文件删除
删除较为简单，前端使用了确认弹窗；后端使用file.delete()就可直接删除文件，再删除数据库记录即可
```java
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
```
演示<br />![delete.gif](https://cdn.nlark.com/yuque/0/2020/gif/670787/1592362487084-7eab3123-9f90-4302-ac42-7f3ee0300ec8.gif#align=left&display=inline&height=574&margin=%5Bobject%20Object%5D&name=delete.gif&originHeight=938&originWidth=1906&size=446994&status=done&style=shadow&width=1167)