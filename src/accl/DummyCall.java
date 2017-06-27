package accl;

import org.json.simple.JSONObject;

import accl.service.OrderStatusService;
import accl.util.ACCLConstant;
import accl.util.ClientLookupManager;
import accl.util.EndpointLookupManager;

public class DummyCall {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject request = new JSONObject();
		JSONObject response = new JSONObject();
		String orderId = "100000031";

		request.put(ACCLConstant.SERVICE_REQUEST_TEXT, ACCLConstant.ORDER_STATUS_TEXT);
		request.put(ACCLConstant.ORDER_ID_TEXT, orderId);
		request.put("applicationId", "amzn1.ask.skill.b0c0c103-0b72-4672-bf66-3dcff49d7d38");
		request.put("applicationAgent", "alexa");
		
		

		ClientLookupManager clientLookup = new ClientLookupManager();
		String clientId = clientLookup.getClientId((String) request.get("applicationId"),
				(String) request.get("applicationAgent"));
		request.put(ACCLConstant.CLIENT_ID_TEXT, clientId);

		switch ((String) request.get(ACCLConstant.SERVICE_REQUEST_TEXT)) {
		case ACCLConstant.ORDER_STATUS_TEXT:
			OrderStatusService orderStatus = new OrderStatusService();
			response = orderStatus.run(request);
			break;

		default:
			break;
		}
		System.out.println(response.get("orderStatus"));
	}

}
