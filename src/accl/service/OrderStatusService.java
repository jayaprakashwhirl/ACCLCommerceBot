package accl.service;

import org.json.simple.JSONObject;

import accl.util.ACCLConstant;
import accl.util.EndpointConnectUtils;
import accl.util.EndpointLookupManager;

/*
 * THIS CLASS WILL HANDLE ALL THE REQUESTS RELATED TO ORDERS 
 * @author WHIRLDATA
 *
 */

public class OrderStatusService {

	public JSONObject run(JSONObject request) {
		EndpointLookupManager endpointLookupManager = new EndpointLookupManager();
		JSONObject responseObject = null;
		String clientId = (String) request.get(ACCLConstant.CLIENT_ID_TEXT);
		String serviceRequest = (String) request.get(ACCLConstant.SERVICE_REQUEST_TEXT);
		String serviceEndpoint = endpointLookupManager.getEndPoint(clientId, serviceRequest);
		System.out.println("Service endpoint url: " + serviceEndpoint);

		if (request.get(ACCLConstant.ORDER_ID_TEXT) != null) {
			System.out.println("Order id is exist");
			EndpointConnectUtils endpointUtil = new EndpointConnectUtils();
			responseObject = endpointUtil
					.getRequest(serviceEndpoint + "?order_id=" + request.get(ACCLConstant.ORDER_ID_TEXT));
			return responseBuilder(responseObject);
		}
		return responseObject;
	}

	@SuppressWarnings({ "unchecked" })
	public JSONObject responseBuilder(JSONObject responseObject) {
		JSONObject botResponseObject = new JSONObject();
		String speechString = "";
		if ((String) responseObject.get(ACCLConstant.ORDER_STATUS_TEXT) != null)
			speechString = "your order id is " + (String) responseObject.get("OrderId") + ". The order status is "
					+ (String) responseObject.get(ACCLConstant.ORDER_STATUS_TEXT) + ". Order total is "
					+ (String) responseObject.get("orderTotal") + " " + (String) responseObject.get("orderCurrency");
		else
			speechString = "Invalid order id";
		System.out.println("Reponse: " + speechString);
		botResponseObject.put("speech", speechString);
		botResponseObject.put("text", speechString);
		return botResponseObject;
	}

	public JSONObject get(String orderId) {

		return null; // RETURN THE STATUS OF SINGLE ORDER
	}

	public JSONObject getLatestOrderStatus(JSONObject request) {

		return null; // FIND THE LATEST ORDER ID AND GET ITS STATUS FROM GET()
	}

}
