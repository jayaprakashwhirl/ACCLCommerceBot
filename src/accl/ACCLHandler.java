package accl;

import org.json.simple.JSONObject;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import accl.service.OrderStatusService;
import accl.util.ACCLConstant;

public class ACCLHandler implements RequestHandler<JSONObject, JSONObject> {
	BasicAWSCredentials awsCredential = new BasicAWSCredentials(ACCLConstant.AWS_ACCESSKEY_ID,
			ACCLConstant.AWS_SECRET_ACCESS_KEY);
	AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCredential)
			.withRegion(Region.getRegion(Regions.US_WEST_2));

	public DynamoDB dynamoDB = new DynamoDB(client);

	@Override
	public JSONObject handleRequest(JSONObject request, Context context) {
		JSONObject response = new JSONObject();
		String orderId = "100000031";
		// request.put(ACCLConstant.SERVICE_REQUEST_TEXT,
		// ACCLConstant.ORDER_STATUS_TEXT);
		// request.put(ACCLConstant.ORDER_ID_TEXT, orderId);

		switch ((String) request.get(ACCLConstant.SERVICE_REQUEST_TEXT)) {
		case ACCLConstant.ORDER_STATUS_TEXT:
			OrderStatusService orderStatus = new OrderStatusService();
			response = orderStatus.run(request);
			break;

		default:
			break;
		}
		System.out.println(response.get("orderStatus"));
		return response;
	}

}
