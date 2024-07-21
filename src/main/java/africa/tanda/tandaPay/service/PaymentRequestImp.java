package africa.tanda.tandaPay.service;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import africa.tanda.tandaPay.kafka.producer.PaymentRequestKafkaProducer;
import africa.tanda.tandaPay.model.PaymentRequestDTO;
import africa.tanda.tandaPay.repository.PaymentRequestRepository;
@Service
public class PaymentRequestImp implements PaymentRequest{
	@Autowired
	PaymentRequestRepository paymentRequestRepository;
	@Autowired
	PayloadValidator payloadValidator;
	@Autowired
	PaymentRequestKafkaProducer paymentRequestKafkaProducer;
	

	@Override
	public JSONObject addPaymentRequest(JSONObject paymentRequestPayload) {
		JSONObject response=new JSONObject();
	
		HashMap<String, String> validatePayloadJSON=payloadValidator.validatePaymentRequestPayload(paymentRequestPayload);
		
		//First Validate the Values
		if (validatePayloadJSON.get("code") != "00") {
			response=new JSONObject(validatePayloadJSON);
		}
		//payload has been validated
		else {
			String mobileNumber=paymentRequestPayload.getString("mobileNumber");
		if (payloadValidator.validateMobileNumberFormat(mobileNumber)) {
			
			//Check if the Number is a safaricom Number
			if (payloadValidator.isSafaricomNumber(mobileNumber)) {
				//Push the Request to Kafka Topic
				paymentRequestKafkaProducer.sendPaymentRequest("tandapay_payementRequests", paymentRequestPayload.toString());
				 //Save the Request to Cloud Database.
				 PaymentRequestDTO requestDTO=new PaymentRequestDTO(paymentRequestPayload.getString("requestID"),paymentRequestPayload.getFloat("amount"),paymentRequestPayload.getString("mobileNumber"),"Pending");				
				 paymentRequestRepository.save(requestDTO);
				 
				 
				 
		
				 response.put("requestID",requestDTO.getRequestID());
				 response.put("status", requestDTO.getStatus());
				 response.put("description", "Your tansaction has been successfully Logged");
			}
			else {
				response.put("code", "001");
				response.put("info", "Error");
				response.put("description", "Invalid Safaricom Number");
			}
			
		}
		else {
			response.put("code", "001");
			response.put("info", "Error");
			response.put("description", "Invalid Phone Number Format");
		}
		}
		//Check Validations First
		return response;
				
	}

}
