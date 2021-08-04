package mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSend {
	
	private static class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			//지메일 아이디 , 비밀번호
			return new PasswordAuthentication("여기에본인 지메일주소", "여기에 비밀번호");
		}
	}
	
	public static void send(String title, String content, String toEmail) {
		Properties p = new Properties();
		p.put("mail.smtp.user", "본인지메일주소"); //본인 지메일
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(p, auth);
			session.setDebug(true); 
			MimeMessage msg = new MimeMessage(session);
			String message = content;
			msg.setSubject(title);
			//InternetAddress(메일주소, 보내는사람 이름, 문자셋)
			Address fromAddr = new InternetAddress("보내는사람 메일주소"); 
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(toEmail); 
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(message, "text/html;charset=KSC5601");
			Transport.send(msg);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String title = "구글 메일 테스트";
		String content = "<h3>구글 메일 테스트</3>";
		String toEmail = "여기에 보낼 이메일주소";
		send(title, content, toEmail);
		System.out.print("성공");
	}

}
