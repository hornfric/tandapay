package africa.tanda.tandaPay.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;



/*This is the Class that will House All the validations*/
@Service
public class PayloadValidator {
	public static List<String> safaricomNumbers=Arrays.asList("11","01","02","03","04","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","40","41","42","43","45","46","48","57","58","59","90","91","92","93","94","95","96","97","98","99");

	
	
	
	//Validate Phone Number Format and Country Code
	public boolean validateMobileNumberFormat(String mobileNumber) {
		//Valid Length and Starts with Country Code
		System.out.println("Country Code:"+mobileNumber.substring(0,3));
		if (mobileNumber.length()==12 && mobileNumber.substring(0,3).equals("254")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
			

  
	 /*Determine if the Phone Number Belongs to Safaricom */

	public boolean isSafaricomNumber(String phoneNumber) {
		String safaricomFormat=phoneNumber.substring(4, 6);
		System.out.println("The Format Extract:"+safaricomFormat);
		boolean isValid=false;
		//Valid Safaricom Number
		if (safaricomNumbers.contains(safaricomFormat)) {
			isValid=true;
		}
		else{
			isValid=false;
		}
		return isValid;
	}
	
	
	public HashMap<String, String> validatePaymentRequestPayload(JSONObject paymentRequest) {
		String mobileNumber="";
		float  amount=0;		
		String requestID="";
		HashMap<String, String> paymentRequestResponse=new HashMap<>();
		//Confirm Valid Payload Keys are Supplied.
		if (paymentRequest.has("mobileNumber") && paymentRequest.has("amount") && paymentRequest.has("requestID")) {
			//Confirm the dataypes
			
			
		
			
			try {
				
				mobileNumber=paymentRequest.getString("mobileNumber");			
				requestID=paymentRequest.getString("requestID");
				//Confirm all Keys have Values
				if(mobileNumber.equals("")) {
					paymentRequestResponse.put("code", "01");
					paymentRequestResponse.put("info", "Error");
					paymentRequestResponse.put("description", "Mobile Number must have a value");
				
				}
				else {
				if(requestID.equals("")) {
					paymentRequestResponse.put("code", "01");
					paymentRequestResponse.put("info", "Error");
					paymentRequestResponse.put("description", "Request ID must have a value");
								
				}
				else {
					try {
						amount=paymentRequest.getFloat("amount");				
						//Amount should be between 10 and 150000
						if (amount>=10 && amount <=150000) {
							
							//Log the Payment Request to the Database.
							//PaymentRequest paymentRequestInstance= paymentRequestService.logPaymentRequest(amount, paybillNumber, reference);
							paymentRequestResponse.put("code", "00");
							paymentRequestResponse.put("info", "Success");
							paymentRequestResponse.put("description", "The Payload contains all Madatory Fields");
						}
						else {
							paymentRequestResponse.put("code", "01");
							paymentRequestResponse.put("info", "Error");
							paymentRequestResponse.put("description", "Amount must be between 10 and 150000");
						}
					}
					catch (Exception e) {
						paymentRequestResponse.put("code", "01");
						paymentRequestResponse.put("info", "Error");
						paymentRequestResponse.put("description", "Invalid amount Format");
					}
				}
				}
				
				//Validate Amount
				
				
				
				
				
			}
			catch (JSONException e) {
				
				paymentRequestResponse.put("code", "01");
				paymentRequestResponse.put("info", "Error");
				paymentRequestResponse.put("description", "Formats of Request Data");
			}
		}
		else {
			paymentRequestResponse.put("code", "02");
			paymentRequestResponse.put("info", "Error");
			paymentRequestResponse.put("description", "Invalid Payment Request");
		}
		return paymentRequestResponse;
	}

	

}
