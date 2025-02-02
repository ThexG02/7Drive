package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Slf4j
@Service@RequiredArgsConstructor
public class EmailSenderServiceimplementation implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        try{
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
        log.info("Email Sent Successfully");
        }
        catch( Exception exception){
            log.info("Cannot send email ,"+ exception.getMessage());


        }
    }

    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo("harshbhardwaj6421@gmail.com");
            simpleMailMessage.setBcc(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            log.info("Email sent succesfully");
        } catch(Exception exception){
            log.info("cannot send email ,"+exception.getMessage());
        }

    }
}
