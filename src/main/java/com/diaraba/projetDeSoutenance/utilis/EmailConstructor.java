package com.diaraba.projetDeSoutenance.utilis;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.diaraba.projetDeSoutenance.models.Demande;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.User;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


@Component
public class EmailConstructor {

	@Autowired
	private Environment env;

	@Autowired
	private TemplateEngine templateEngine;
		public MimeMessagePreparator sendmailfordemande(Demande demande) {
		Context context = new Context();
		context.setVariable("demande", demande);
		String text = templateEngine.process("sendemailfordemande", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(demande.getEmaildestinateur());
				email.setSubject("Demande depuis cogoya so");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructNewStructureEmail(Structure structure) {
		Context context = new Context();
		context.setVariable("utilisateur", structure);
		String text = templateEngine.process("newStructureEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(structure.getEmail());
				email.setSubject("Bienvenu sur Cogoya So");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
	public MimeMessagePreparator constructNewUserEmail(Utilisateurs user) {
		Context context = new Context();
		context.setVariable("utilisateur", user);
		String text = templateEngine.process("newUserEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Bienvenu sur Cogoya So");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructUpdateUserProfileEmail(Utilisateurs user, String password) {
		Context context = new Context();
		context.setVariable("utilisateur", user);
		context.setVariable("password", password);
		String text = templateEngine.process("resetPasswordEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Nouveau mots de passe - Malitourist");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructResetPasswordEmail(User user) {
		Context context = new Context();
		context.setVariable("utilisateur", user);
		String text = templateEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Profile Modifié - CogoYaSo");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}



	public MimeMessagePreparator constructStructureResetPasswordEmail(Structure user) {
		Context context = new Context();
		context.setVariable("utilisateur", user);
		String text = templateEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Profile Modifié - CogoYaSo");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructStructureUpdateUserProfileEmail(Structure user, String password) {
		Context context = new Context();
		context.setVariable("utilisateur", user);
		String text = templateEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Profile Modifié - CogoYaSo");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
}
