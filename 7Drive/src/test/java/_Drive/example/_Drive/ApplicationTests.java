package _Drive.example._Drive;

import _Drive.example._Drive.Service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {


	@Autowired
	private EmailSenderService emailSenderService;
	@Test
	void contextLoads() {
		emailSenderService.sendEmail(
				"abcd@gmail.com",
				"testing email",
				"testing email for 7 drive");
	}

}
