package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.FileEntity;

public interface FileEntityRepo extends JpaRepository<FileEntity, Long> {
    // Additional query methods can be defined here if needed
}
