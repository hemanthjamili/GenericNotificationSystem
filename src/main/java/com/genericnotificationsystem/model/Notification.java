package com.genericnotificationsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Notification")
@EntityListeners(AuditingEntityListener.class)
public class Notification{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotEmpty
	@Column(name = "from_address", nullable = false)
	private String fromAddress;
	
	@NotEmpty
	@Column(name = "to_address", nullable = false)
	private String toAddress;
	
	@NotEmpty
	@Column(name = "channel_type", nullable = false)
	private String channelType;
	
	@NotEmpty
	@Column(name = "message", nullable = false)
	private String message;
	
		
	public Notification() {
		
	}
	
	public Notification(Integer id, @NotEmpty String fromAddress, @NotEmpty String toAddress, @NotEmpty String channelType,	@NotEmpty String message) {
		super();
		this.id = id;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.channelType = channelType;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", fromAddress=" + fromAddress + ", toAddress=" + toAddress + ", channelType="
				+ channelType + ", message=" + message + "]";
	}	
}