package me.mazixiang.dao.file;

import me.mazixiang.vo.FileModel;

import java.util.List;

public interface FileDao {
    void insert(FileModel file) throws Exception;

    void delete(String filePath) throws Exception;

    FileModel queryById(String id) throws Exception;

    List<FileModel> queryAllFiles() throws Exception;
}
