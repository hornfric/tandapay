package africa.tanda.tandaPay.service;

import org.json.JSONObject;

import africa.tanda.tandaPay.model.PaymentRequestDTO;

public interface PaymentRequest {
	public JSONObject addPaymentRequest(JSONObject paymentRequestPayload);
	

}
