package _Drive.example._Drive.Service;

public interface EmailSenderService {
     void sendEmail(String toEmail , String subject , String body);
     void sendEmail(String toEmail[] , String subject, String body);
}
