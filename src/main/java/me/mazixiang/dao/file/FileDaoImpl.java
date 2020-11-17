package me.mazixiang.dao.file;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.mazixiang.vo.FileModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements FileDao {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    public FileDaoImpl(String jsonConfigString) {
        JSONObject config = JSONUtil.parseObj(jsonConfigString);
        JSONObject dbConfig = (JSONObject) config.get("db");
        String dbHost = (String) dbConfig.get("db_host");
        String dbPort = (String) dbConfig.get("db_port");
        String dbName = (String) dbConfig.get("db_name");
        dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
        dbUsername = (String) dbConfig.get("db_username");
        dbPassword = (String) dbConfig.get("db_password");
    }

    private Connection openConnection() throws Exception {
        Class.forName(driverName);
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @Override
    public void insert(FileModel file) throws Exception {
        String sql = "insert into file(id, originalFileName, filePath) values (?, ?, ?)";
        Connection connection = openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, file.getId());
        preparedStatement.setString(2, file.getOriginalFileName());
        preparedStatement.setString(3, file.getFilePath());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(String filePath) throws Exception {

    }

    @Override
    public FileModel queryById(String id) throws Exception {
        String sql = "select id, originalFileName, filePath from file where id = ?";
        Connection connection = openConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, id);

        ResultSet resultSet = pst.executeQuery();
        FileModel fileModel = null;

        while (resultSet.next()) {
            fileModel = new FileModel();

            fileModel.setId(resultSet.getString("id"));
            fileModel.setOriginalFileName(resultSet.getString("originalFileName"));
            fileModel.setFilePath(resultSet.getString("filePath"));
        }

        resultSet.close();
        pst.close();
        connection.close();

        return fileModel;
    }

    @Override
    public List<FileModel> queryAllFiles() throws Exception {
        List<FileModel> fileModelList = new ArrayList<>();
        String sql = "select id, originalFileName, filePath from file";
        Connection connection = openConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            FileModel tempFileModel = new FileModel();

            tempFileModel.setId(resultSet.getString("id"));
            tempFileModel.setOriginalFileName(resultSet.getString("originalFileName"));
            tempFileModel.setFilePath(resultSet.getString("filePath"));

            fileModelList.add(tempFileModel);
        }

        resultSet.close();
        pst.close();
        connection.close();
        return fileModelList;
    }
}
