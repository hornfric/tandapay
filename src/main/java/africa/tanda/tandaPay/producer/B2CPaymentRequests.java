package africa.tanda.tandaPay.producer;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class B2CPaymentRequests {
	
	
	/*Method to take the payment Requests from the Core CPS and push them to Kafka Topic with name "payment-requests"
	 * *
	 * 
	 */
	public JSONObject produceB2CPaymentRequests() {
		return null;
		
	}

}
