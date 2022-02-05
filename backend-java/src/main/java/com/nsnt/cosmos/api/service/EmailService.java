package com.nsnt.cosmos.api.service;

import javax.mail.MessagingException;

/**
 * 회원가입시 이메일 인증을 위한 서비스 인터페이스 정의.
 */
public interface EmailService {
	public String sendMail(String user_email) throws MessagingException;
}
