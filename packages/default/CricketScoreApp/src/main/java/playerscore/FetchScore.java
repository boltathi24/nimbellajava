package playerscore;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import playerscore.DBUtil;
/**
 * Hello world!
 *
 */
public class FetchScore 
{
	public static JsonObject main(JsonObject args) throws Exception {
		Gson gson = new Gson(); 
		JsonObject response = new JsonObject();
	    JsonObject headers = new JsonObject();
	    headers.addProperty("content-type", "text/html; charset=UTF-8");
	    HashMap<String, String> dbResult=DBUtil.executeQueryForRead("select * from Runs where PlayerName='Sachin Tendulkar'");
	    response.addProperty("dbResult",gson.toJson(dbResult));
	    return response;
	}
	
	
}
