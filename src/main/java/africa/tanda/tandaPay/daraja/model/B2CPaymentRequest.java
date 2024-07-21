package africa.tanda.tandaPay.daraja.model;

public class B2CPaymentRequest {
	private static String endpoint;
	private String  OriginatorConversationID;
	private String  InitiatorName;
	private String  SecurityCredential;
	private String  CommandID;
	private String  Amount;
	private String  PartyA;
	private String  PartyB;
	private String  Remarks;
	private String  QueueTimeOutURL;
	private String  ResultURL;
	private String  Occassion;
	/*{    
		   "OriginatorConversationID": "feb5e3f2-fbbc-4745-844c-ee37b546f627",
		   "InitiatorName": "testapi",
		   "SecurityCredential":"EsJocK7+NjqZPC3I3EO+TbvS+xVb9TymWwaKABoaZr/Z/n0UysSs..",
		   "CommandID":"BusinessPayment",
		   "Amount":"10"
		   "PartyA":"600996",
		   "PartyB":"254728762287"
		   "Remarks":"here are my remarks",
		   "QueueTimeOutURL":"https://mydomain.com/b2c/queue",
		   "ResultURL":"https://mydomain.com/b2c/result",
		   "Occassion":"Christmas"
		}*/
	public B2CPaymentRequest(String originatorConversationID, String initiatorName, String securityCredential,
			String commandID, String amount, String partyA, String partyB, String remarks, String queueTimeOutURL,
			String resultURL, String occassion) {
		super();
		OriginatorConversationID = originatorConversationID;
		InitiatorName = initiatorName;
		SecurityCredential = securityCredential;
		CommandID = commandID;
		Amount = amount;
		PartyA = partyA;
		PartyB = partyB;
		Remarks = remarks;
		QueueTimeOutURL = queueTimeOutURL;
		ResultURL = resultURL;
		Occassion = occassion;
	}
	

}
