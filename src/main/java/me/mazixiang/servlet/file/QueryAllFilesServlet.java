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
import java.io.IOException;
import java.util.List;

public class QueryAllFilesServlet extends HttpServlet {
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
        FileDao fileDao = new FileDaoImpl(dbConfigString);
        try {
            List<FileModel> fileModelList = fileDao.queryAllFiles();
            req.setAttribute("fileModelList", fileModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
