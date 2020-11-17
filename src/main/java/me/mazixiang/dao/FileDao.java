package me.mazixiang.dao;

public interface FileDao {
    void insert(String fileId, String originalFileName, String filePath) throws Exception;

    void delete(String filePath) throws Exception;
}
