package me.mazixiang.dao;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.mazixiang.vo.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    public StudentDaoImpl(String jsonConfigString) {
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
    public void insert(Student student) throws Exception {
        student.setStuId(IdUtil.simpleUUID());
        String sql = "insert into student(id, name, password, age, gender, hobbies, school) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = openConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, student.getStuId());
        pst.setString(2, student.getStuName());
        pst.setString(3, student.getStuPass());
        pst.setInt(4, student.getStuAge());
        pst.setString(5, student.getStuGender());
        pst.setString(6, student.getStuHobbies());
        pst.setString(7, student.getStuSchool());

        pst.executeUpdate();
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public Student queryById(String id) {
        return null;
    }

    @Override
    public List<Student> queryAll() {
        return null;
    }
}
