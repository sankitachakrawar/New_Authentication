package com.example.demo.services;

public interface EmailServiceIntf {

	public void sendMessageWithAttachment();

	void sendSimpleMessage(String emailTo, String subject, String text);

}
