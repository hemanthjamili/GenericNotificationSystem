package com.genericnotificationsystem.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genericnotificationsystem.model.Notification;
import com.genericnotificationsystem.repositiory.NotificationRepository;


@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepo;
	
	public NotificationService(NotificationRepository notificationRepo) {
		this.notificationRepo = notificationRepo;
	}

	public Notification send(Notification notification) {
		if(notification.getId()!=null) {
			throw new EntityExistsException("Notification already Exists with ID: "+notification.getId());			
		}
		return notificationRepo.save(notification);
	}
	
	public Notification update(Notification notification) {
		if(notification.getId()==null) {
			throw new EntityNotFoundException("Notification Not Found");			
		}
		return notificationRepo.save(notification);
	}
	
	public List<Notification> findAll(){
		return notificationRepo.findAll();
	}
	
	public Notification findOne(Integer id) {
		
		Notification result = notificationRepo.findById(id).orElse(null);
		if(result == null)
			throw new EntityNotFoundException("Notification Not Found");
		return result;
		
	}
	
	public void delete(Integer id) {
		notificationRepo.deleteById(id);		 
	}
	
	
}
