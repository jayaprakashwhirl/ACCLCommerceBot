package accl.service;

import org.json.simple.JSONObject;

/*
 * THIS CLASS WILL HANDLE ALL THE REQUESTS RELATED TO ORDERS 
 * @author WHIRLDATA
 *
 */

public class OrderStatusService {

	public JSONObject get(String orderId) {

		return null; // RETURN THE STATUS OF SINGLE ORDER
	}

	public JSONObject getLatestOrderStatus() {

		return null; // FIND THE LATEST ORDER ID AND GET ITS STATUS FROM GET()
	}
}
