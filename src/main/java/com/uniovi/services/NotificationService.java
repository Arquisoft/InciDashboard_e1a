package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Notification;
import com.uniovi.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	public void addNotification(Notification notification) {
		notificationRepository.save(notification);
	}

}
