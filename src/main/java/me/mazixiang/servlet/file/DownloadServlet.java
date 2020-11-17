package me.mazixiang.servlet.file;

import cn.hutool.core.io.file.FileReader;
import me.mazixiang.dao.file.FileDao;
import me.mazixiang.dao.file.FileDaoImpl;
import me.mazixiang.vo.FileModel;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {

    private String dbConfigString;

    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        String configFilePath = this.getServletContext().getRealPath(application.getInitParameter("ConfigFile"));
        FileReader fileReader = new FileReader(configFilePath);
        dbConfigString = fileReader.readString();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("fileId");

        FileDao fileDao = new FileDaoImpl(dbConfigString);

        try {
            FileModel fileModel = fileDao.queryById(fileId);
            String filePath = fileModel.getFilePath();
            String originalFileName = fileModel.getOriginalFileName();

            resp.setContentType(getServletContext().getMimeType(originalFileName));
            resp.setHeader("Content-Disposition", "attachment;filename=" + originalFileName);

            FileInputStream in = new FileInputStream(filePath);
            OutputStream out = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            int size = 0;
            while ((size = in.read(bytes)) > 0) {
                out.write(bytes, 0, size);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
