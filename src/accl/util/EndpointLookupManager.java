package accl.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import accl.ACCLHandler;

public class EndpointLookupManager extends ACCLHandler {

	public String getEndPoint(String clientId, String serviceRequest) {
		Table table = dynamoDB.getTable(ACCLConstant.CLIENT_SERVICE_LOOKUP_TABLE);
		Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		expressionAttributeValues.put(":client_id", clientId);
		expressionAttributeValues.put(":service_id", serviceRequest);
		System.out.println("Client id: " + clientId);
		System.out.println("Service id: " + serviceRequest);

		ItemCollection<ScanOutcome> items = table.scan("client_id = :client_id and service_id = :service_id", // FilterExpression
				"end_point", // ProjectionExpression
				null, // ExpressionAttributeNames - not used in this example
				expressionAttributeValues);
		System.out.println("Collected items");
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			System.out.println("in Loop");
			return ((String) iterator.next().asMap().get("end_point"));
		}

		return null;
	}

}
