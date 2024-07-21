package africa.tanda.tandaPay.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor

@Document(collection="payment_request")
@Data
public class PaymentRequestDTO {

	@Id
	private String requestID;
	private float amount;
	private String mobileNumber;
	private String status;
	public PaymentRequestDTO(String requestID, float amount, String mobileNumber, String status) {
		super();
		this.requestID = requestID;
		this.amount = amount;
		this.mobileNumber = mobileNumber;
		this.status = status;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
