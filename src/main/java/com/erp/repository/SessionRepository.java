package com.erp.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.SessionEntity;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByUserIdOrderByLoginTimeDesc(Long userId);
}

