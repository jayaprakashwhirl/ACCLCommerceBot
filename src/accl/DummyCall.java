package accl;

import org.json.simple.JSONObject;

import accl.service.OrderStatusService;
import accl.util.ACCLConstant;

public class DummyCall {

	public static void main(String[] args) {
		JSONObject request = new JSONObject();
		JSONObject response = new JSONObject();
		switch ((String) request.get(ACCLConstant.SERVICE_REQUEST_TEXT)) {
		case ACCLConstant.ORDER_STATUS_TEXT:
			OrderStatusService orderStatus = new OrderStatusService();
			response = orderStatus.run(request);
			break;

		default:
			break;
		}
	}

}
