package accl;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ACCLHandler implements RequestHandler<JSONObject, JSONObject> {

	@Override
	public JSONObject handleRequest(JSONObject request, Context context) {
		JSONObject response = new JSONObject();

		return response; // RETURN THE JSON RESPONSE
	}

}
