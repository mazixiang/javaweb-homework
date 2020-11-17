package me.mazixiang.servlet.file;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.IdUtil;
import me.mazixiang.dao.FileDao;
import me.mazixiang.dao.FileDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileServlet extends HttpServlet {

    private String dbConfigString;
    private String uploadRoot;

    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        String configFilePath = this.getServletContext().getRealPath(application.getInitParameter("ConfigFile"));
        FileReader fileReader = new FileReader(configFilePath);
        dbConfigString = fileReader.readString();

        uploadRoot = this.getServletContext().getRealPath("/") + this.getInitParameter("uploadRoot");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("file");
        String fileType = file.getContentType();
        System.out.println(fileType);

        String name = file.getName();
        System.out.println(name);

        String fileName = file.getSubmittedFileName();
        System.out.println(fileName);

        long fileSize = file.getSize();
        System.out.println(fileSize);

        InputStream in = file.getInputStream();

        String affix = fileName.substring(fileName.lastIndexOf("."));
        String fileId = IdUtil.fastSimpleUUID();
        String newFileName = fileId + affix;
        String filePath = uploadRoot + "/" + newFileName;

        FileDao fileDao = new FileDaoImpl(dbConfigString);

        try {
            fileDao.insert(fileId, fileName, filePath);
            System.out.println("insert record complete");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert record failed");
        }

        FileOutputStream out = new FileOutputStream(filePath);

        byte[] bytes = new byte[1024];
        int count = 0;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        in.close();
        out.close();
    }
}
