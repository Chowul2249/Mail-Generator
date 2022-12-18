package com.home.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailGenerator {

	protected static final String FROM = "*******@gmail.com";
	protected static final String PASSWORD = "************";	
	private static final Session SESSION;
	

	static {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		SESSION = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(FROM, PASSWORD);// change
																				// accordingly
			}
		});
		
	}

	public static String generateMail( String email) {
		String subject = "Your Generating OTP" ;
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
                String symbols = "!@#$%^&*_=+-/.?<>)";

                String values = Capital_chars + Small_chars +
                        numbers + symbols;
 
                int len=6;
                Random rndm_method = new Random();
                
                char[] password = new char[len];
         
             
                for (int i = 0; i < len; i++)
                { 
                    password[i] =
                    values.charAt(rndm_method.nextInt(values.length()));
                    
                }
                String otp=String.valueOf(password);
                System.out.println(otp);
		try {
			MimeMessage message = new MimeMessage(SESSION);
			message.setFrom(); // change accordingly
			// change accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.trim()));
			message.setSubject(subject);
			message.setText("Dear Sir/Mam,\t \n\n" + 
					"Your Generating OTP Details:  " +
					
					""+otp+"\n\n\n" +
					
					"Regards,\n"
					+ "********");
			message.setSentDate(new Date());
			Transport.send(message);
			
			System.out.println("Mail Sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return otp;
	}

}
