package africa.tanda.tandaPay.kafka.producer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestKafkaProducer {
@Autowired
KafkaTemplate<String, String> kafkaTemplate;
//Create the Topic that Consumers will Listen to.
public void sendPaymentRequest(String topicName,String messageToTopic) {
	 kafkaTemplate.send(topicName, messageToTopic);
}
}
