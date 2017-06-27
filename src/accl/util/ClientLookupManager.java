package accl.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import accl.ACCLHandler;

public class ClientLookupManager extends ACCLHandler {

	public String getClientId(String applicationId, String applicationAgent) {
		Table table = dynamoDB.getTable(ACCLConstant.CLIENT_LOOKUP_TABLE);
		Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		expressionAttributeValues.put(":application_id", applicationId);
		expressionAttributeValues.put(":application_agent", applicationAgent);
		System.out.println("Application id: " + applicationId);
		System.out.println("applicationAgent id: " + applicationAgent);

		ItemCollection<ScanOutcome> items = table.scan(
				"application_id = :application_id and application_agent = :application_agent", // FilterExpression
				"client_id", // ProjectionExpression
				null, // ExpressionAttributeNames - not used in this example
				expressionAttributeValues);
		System.out.println("Collected client items");
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			System.out.println("in Loop");
			return ((String) iterator.next().asMap().get("client_id"));
		}

		return null;
	}
	
	public String getClientIdDepricated(String applicationId, String applicationAgent) {
		System.out.println("Started");
		return "dilsebol";
	}

}
