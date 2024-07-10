package com.erp.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.erp.entity.FileEntity;
import com.erp.repository.FileEntityRepo;



@Service
public class FileEntityService {

    @Autowired
    private FileEntityRepo fileEntityRepo;

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(fileName);
            fileEntity.setContentType(file.getContentType());
            fileEntity.setData(file.getBytes());
            fileEntityRepo.save(fileEntity);
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + fileName, ex);
        }
    }

    public FileEntity getFileById(Long id) {
        return fileEntityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }
}

