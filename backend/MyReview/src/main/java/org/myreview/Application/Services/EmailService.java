package org.myreview.Application.Services;

import lombok.AllArgsConstructor;
import org.myreview.Infra.Mail.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    // private final JavaMailSender mailSender;

//    @Override
//    @Async
//    public void send(String to, String email) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("*********@example.com"); //sender email
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            LOGGER.error("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//        }
//    }

    @Override
    public void send(String to, String email) {
        System.out.println("Sending email to " + to);
        System.out.println("With message " + email);
    }

}
