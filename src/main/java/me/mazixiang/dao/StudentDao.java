package me.mazixiang.dao;

import me.mazixiang.vo.Student;

import java.util.List;

public interface StudentDao {
    public void insert(Student student) throws Exception;

    public void delete(Student student) throws Exception;

    public void update(Student student) throws Exception;

    public Student queryById(String id) throws Exception;

    public List<Student> queryAll() throws Exception;
}
