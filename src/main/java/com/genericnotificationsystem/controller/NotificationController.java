package com.genericnotificationsystem.controller;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genericnotificationsystem.service.NotificationService;
import com.genericnotificationsystem.model.Notification;

@RestController
@RequestMapping("/api")
public class NotificationController {
	
	public static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@RequestMapping(value = "/notification", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Notification>> findAll(){
		List<Notification> notificationList = notificationService.findAll();
		if(notificationList.isEmpty()) {
			return new ResponseEntity<List<Notification>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Notification>>(notificationList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notification", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> sendNotification(@Valid @RequestBody Notification notification) throws URISyntaxException{
		logger.info("Creating an Notification : {}", notification);
		try {
			Notification result = notificationService.send(notification);
			System.out.println("Notification sent with ID:"+result.getId());
			return ResponseEntity.created(new URI("/api/Notification/"+result.getId())).body(result);
		}catch(EntityExistsException ex){
			return new ResponseEntity<Notification>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/notification", 
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> updateNotification(@Valid @RequestBody Notification notification) throws URISyntaxException{
		logger.info("Updating an Notification with id {}", notification.getId());
		if(notification.getId() == null) {
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}		
		try {
			Notification result = notificationService.update(notification);
			return ResponseEntity.created(new URI("/api/Notification/"+result.getId())).body(result);
		}catch(Exception ex){
			return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/notification/{id}", 
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteNotification(@PathVariable Integer id){
		logger.info("Deleting an Notification with id: {}", id);
		notificationService.delete(id);
		return ResponseEntity.ok().build();
	}
}

