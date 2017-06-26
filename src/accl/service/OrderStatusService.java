package accl.service;

import org.json.simple.JSONObject;

import accl.util.ACCLConstant;
import accl.util.EndpointConnectUtils;

/*
 * THIS CLASS WILL HANDLE ALL THE REQUESTS RELATED TO ORDERS 
 * @author WHIRLDATA
 *
 */

public class OrderStatusService {

	public JSONObject run(JSONObject request) {
		JSONObject responseObject = null;
		if (request.get(ACCLConstant.ORDER_ID_TEXT) != null) {
			System.out.println("Order id is exist");
			EndpointConnectUtils endpointUtil = new EndpointConnectUtils();
			responseObject = endpointUtil.getRequest("http://c7bd04ba.ngrok.io/dilsebol2/acclerator?order_id="
					+ request.get(ACCLConstant.ORDER_ID_TEXT));
			return responseBuilder(responseObject);
		}
		return responseObject;
	}

	@SuppressWarnings({ "null", "unchecked" })
	public JSONObject responseBuilder(JSONObject responseObject) {
		JSONObject botResponseObject = new JSONObject();
		String speechString = "The order status is: " + (String) responseObject.get(ACCLConstant.ORDER_STATUS_TEXT);
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
