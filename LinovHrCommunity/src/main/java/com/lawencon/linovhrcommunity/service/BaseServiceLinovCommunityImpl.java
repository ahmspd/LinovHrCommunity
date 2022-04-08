package com.lawencon.linovhrcommunity.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.linovhrcommunity.dto.email.EmailTemplate;
import com.lawencon.linovhrcommunity.security.AuthPrincipal;

public class BaseServiceLinovCommunityImpl extends BaseServiceImpl {
	private AuthPrincipal authPrincipal;

	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}

	@Autowired
	private freemarker.template.Configuration freeMarkerConfiguration;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail(String image,String template,EmailTemplate mail) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setFrom(mail.getFrom());
			mimeMessageHelper.setTo(mail.getTo());
			mail.setContent(getContentFromTemplate(template,mail.getModel()));
			mimeMessageHelper.setText(mail.getContent(), true);
			mimeMessageHelper.addInline("myLogo", new ClassPathResource(image));

			emailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public String getContentFromTemplate(String template,Map<String, Object> model) {
		StringBuffer content = new StringBuffer();

		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freeMarkerConfiguration.getTemplate(template), model));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	protected String getIdFromPrincipal() {
		String auth = authPrincipal.getAuthentication().getPrincipal().toString();
		return auth;
	}

	public String generateCode(int input) {
		String codeString = "AEL0987654321";
		StringBuilder sb = new StringBuilder(input);

		for (int i = 0; i < input; i++) {
			int index = (int) (codeString.length() * Math.random());
			sb.append(codeString.charAt(index));
		}
		return sb.toString();
	}

	public String getExtension(MultipartFile fileName) {
		String extension = fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf(".") + 1,
				fileName.getOriginalFilename().length());
		return extension;
	}

	public String stringBuilder(String... args) {
		StringBuilder str = new StringBuilder();

		for (String word : args) {
			str.append(word);
		}

		return str.toString();
	}

}
