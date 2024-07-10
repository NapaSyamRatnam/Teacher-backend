package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Notification;
import com.erp.entity.SignUpEntity;
import com.erp.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(SignUpEntity user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);;
        notification.setMessage(message);
        notificationRepository.save(notification);
        // You may implement notification delivery mechanisms here, such as email or push notification
    }
    
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUser_UserId(userId);
    }
}

