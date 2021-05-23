package com.genericnotificationsystem.test.controller;


import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.genericnotificationsystem.controller.NotificationController;
import com.genericnotificationsystem.model.Notification;
import com.genericnotificationsystem.service.NotificationService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = NotificationController.class, secure = false)
public class NotificationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NotificationService notificationService;

	List<Notification> mockNotificationsList;
	
	Notification mockNotification = new Notification(1,"hemanthjamili@gmail.com", "sai.hemanth@gmail.com", "Gmail", "Hi Hemanth! This is test notification.");

	String exampleNotificationJson = "{\"id\":\"1\",\"fromAddress\":\"hemanthjamili@gmail.com\",\"toAddress\":\"sai.hemanth@gmail.com\",\"channelType\":\"Gmail\",\"message\":\"Hi Hemanth! This is test notification.\"}";

	@Test
	public void fetchNotificationDetails() throws Exception {

		Mockito.when(notificationService.findAll()).thenReturn(mockNotificationsList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/notification").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		
		JSONAssert.assertEquals(mockNotificationsList.toString(), result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void sendNotification() throws Exception {
		
		Notification mockNotification = new Notification(1,"hemanthjamili@gmail.com", "sai.hemanth@gmail.com", "Gmail", "Hi Hemanth! This is test notification.");

		Mockito.when(notificationService.send(Mockito.any(Notification.class))).thenReturn(mockNotification);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/notification")
				.accept(MediaType.APPLICATION_JSON).content(exampleNotificationJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}