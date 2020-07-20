package com.barclays.userservice.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserMailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String toMailId,String subject,String mailContent) {
		System.out.println("Sending Email...");
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("ashav21011996@gmail.com");
        mail.setTo(toMailId);
        mail.setSubject(subject);
        mail.setText(mailContent);
        javaMailSender.send(mail);
        System.out.println("Done");
	}
}
