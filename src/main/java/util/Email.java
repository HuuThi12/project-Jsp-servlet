package util;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	// email có 2 dạng TLS, SSL (cần tìm hiểu )
	// bài này chúng ta sẽ sử dụng TLS
	// cách tạo password -> truy cập vào gmail tìm kiếm mật khẩu ứng dụng
	// password : rqrkilsycijuerkb
	// mail: huuthi12072003@gmail.com
	
	static final String from = "huuthi12072003@gmail.com";
	static final String password = "ppjiusgeqmdhpsqq";
	
	public static boolean senEmail(String to,String tieuDe, String noiDung) {
//		final String to = "lehuuthi1211@gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
		props.put("mail.smtp.port", "587"); // TLS 587, SSL 465
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from,password);
			}
		};
		
		// phiên làm việc
		Session session = Session.getInstance(props, auth);
		
		// Tạo một tin nhắn
		MimeMessage msg = new MimeMessage(session);
		
		
		try {
			// kiểu nội dung
			msg.addHeader("context-type","text/html; charset=UTF-8");
			
			// người gửi
			msg.setFrom(from);
			
			// người nhận
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			// tiêu đề mail
			msg.setSubject(tieuDe);
			
			// Quy định ngày gửi
			msg.setSentDate(new Date());
			
			// Quy định email nhận phản hồi
			msg.setReplyTo(InternetAddress.parse(from,false));
			
			// Nội dung kiểu cũ
			/*
			 msg.setContent("<!DOCTYPE html>"
			+"<html>"
			+"<body>"
			+ "<h1>Hi Hi his his</h1>"
			+ "<p>Ha ha ha</p>"
			+ "<img src=\"https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg\" >"
			+ "</body>"
			+ "</html>", "text/html");
			  */
			
			// Nội dung kiểu mới
			msg.setContent(noiDung,"text/HTML; charset=UTF-8");
			
			
			// Gửi email
			Transport.send(msg);
			System.out.println("Gửi email thành công");
			return true;
		} catch (MessagingException e) {
			System.out.println("Gặp lỗi trong quá trình gửi email");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			Email.senEmail("dangvanthong29042003@gmail.com",System.currentTimeMillis() + "","Hello Thống, mới nhởi được cái code gửi email cũng dui dui " );
		}
		// Email.senEmail("lehuuthi1211@gmail.com",System.currentTimeMillis() + "","hí hí" );
	}
}
