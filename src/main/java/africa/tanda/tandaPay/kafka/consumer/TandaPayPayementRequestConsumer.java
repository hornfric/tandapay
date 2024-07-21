package africa.tanda.tandaPay.kafka.consumer;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TandaPayPayementRequestConsumer {
	@KafkaListener(topics = "tandapay_payementRequests", groupId = "tandapayments-group-id")
    public void listen(String message) {
		JSONObject payloadObject=new JSONObject(message);
		
		
		//Call the Third Party Service Now.
        
		System.out.println("Received message: " + message);
    }

}
