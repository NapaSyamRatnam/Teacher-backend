package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	 List<Notification> findByUser_UserId(Long userId);
}

