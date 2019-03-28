package slate.seleniumappium.automation.slate.seleniumappium.automation;

import slate.seleniumappium.automation.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.*;

public class email {

	static String strLine = null;
	static String log;
	static String dateresponse;
	static String campaign;
	static String chatbottestresult;
	static String screencapture;
	static String conversation;
	static String workingdir = System.getProperty("user.dir");

	private static MimeBodyPart messageBodyPart;

	public static void main(String [] args) { 

		String logcreateddate;
		logcreateddate = AppTest.requiredDate;
		log = workingdir + "/test-log/" + logcreateddate + "_slateTest.log";
		screencapture = workingdir + "/screencapture/" + logcreateddate +".png";

		//Update gmail username and password
		//Provide email to to send email out
		final String username = "";
		final String password = "";
		String emailto = "";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailto));
			message.setSubject(AppTest.requiredDate + ": AUTOMATION ALERT - SLATE TEST");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("This is an automated email");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			//Attachment 1
			messageBodyPart = new MimeBodyPart();
			String filename = AppTest.requiredDate + "_slateTest.log";
			DataSource source = new FileDataSource(log);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			//Attachment 2
			messageBodyPart = new MimeBodyPart();
			String filename1 = AppTest.requiredDate + ".png";
			DataSource source1 = new FileDataSource(screencapture);
			messageBodyPart.setDataHandler(new DataHandler(source1));
			messageBodyPart.setFileName(filename1);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("eMail Sent....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}