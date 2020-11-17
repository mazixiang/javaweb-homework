package me.mazixiang.dao;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
    public void insert(String fileId, String originalFileName, String filePath) throws Exception {
        String sql = "insert into file(id, originalFileName, filePath) values (?, ?, ?)";
        Connection connection = openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, fileId);
        preparedStatement.setString(2, originalFileName);
        preparedStatement.setString(3, filePath);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(String filePath) throws Exception {

    }
}
