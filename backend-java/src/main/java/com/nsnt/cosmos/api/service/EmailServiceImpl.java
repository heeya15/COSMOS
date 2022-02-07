package com.nsnt.cosmos.api.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {
	
	private JavaMailSender javaMailSender;
	
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	// 랜덤 비밀번호 생성
	public String getRamdomCode(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7'
									  , '8', '9'}; 
		
		int idx = 0; StringBuffer sb = new StringBuffer(); 
		System.out.println("charSet.length :::: "+charSet.length); 
		
		for (int i = 0; i < len; i++) { 
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
			sb.append(charSet[idx]);
		} 
		return sb.toString(); 
	}

	@Override
	public String sendMail(String user_email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
		String code = getRamdomCode(6);
		
		String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 COSMOS 회원가입 페이지의 인증코드 입력란에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";
        msg += "<a href=\"https://slack.com\" style=\"text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">COSMOS Clone Technologies, Inc</a>";

		// 수신자 설정
        message.addRecipients(RecipientType.TO, user_email);
		
		// 메일 제목
        message.setSubject("[COSMOS] 회원가입 이메일 인증 메일");
		
		// 메일 내용
        message.setText(msg, "utf-8", "html"); //내용
		
		javaMailSender.send(message);
		
		return code;
	}

}
