package africa.tanda.tandaPay.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import africa.tanda.tandaPay.daraja.service.SafaricomDarajaHelper;
import africa.tanda.tandaPay.kafka.producer.PaymentRequestKafkaProducer;
import africa.tanda.tandaPay.service.PaymentRequestImp;

@RestController
@RequestMapping("/tandaPay/")
public class MainController {
	@Autowired
	PaymentRequestImp paymentRequestImp;
	@Autowired
	PaymentRequestKafkaProducer paymentRequestKafkaProducer;
	@PostMapping("requestPayment")
	public String  requestPayment(@RequestBody String paymentRequest) {
		return paymentRequestImp.addPaymentRequest(new JSONObject(paymentRequest)).toString();
		 
		
	}
	
	
	@PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
		paymentRequestKafkaProducer.sendPaymentRequest("tandapay_payementRequests", message);
        return "Message sent: " + message;
    }
	
	
	@PostMapping("/b2cSecurity")
    public void testSecurityCredential(@RequestParam("password") String password) {
		new  SafaricomDarajaHelper().generateSecurityCredential(password);
        //return "Message sent: " + message;
    }

}
