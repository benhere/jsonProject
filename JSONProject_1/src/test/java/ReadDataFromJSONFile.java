import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSONFile {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		JSONParser jsp = new JSONParser();
		
		//load JSON file
		FileReader fr = new FileReader(".\\JSONFiles\\RawData.json");
		
		//parse JSON data using JSONParser object
		Object obj = jsp.parse(fr);
		
		//(typecasting)convert Java Object obj to JSON Object
		 JSONObject jsonObj = (JSONObject)obj;
		 
		 //extract data from JSON's object
		 String fname = (String) jsonObj.get("firstName");
		 String lname = (String) jsonObj.get("lastName");
		 
		 System.out.println("First name is:"+fname);
		 System.out.println("Last name is:"+lname);
		 
		 //Read JSON array's address using JSONObject
		 JSONArray jsonArr = (JSONArray) jsonObj.get("address");
		 
		 //iterate over JSON Array
		 for(int i=0; i<jsonArr.size(); i++)
		 {
			 JSONObject address = (JSONObject) jsonArr.get(i);
			 String streetVal = (String) address.get("street");
			 String city = (String) address.get("city");
			 String state = (String) address.get("state");
			 
			 System.out.println("Address of "+i+"is....");
			 System.out.println("Street is:"+streetVal);
			 System.out.println("City is:"+city);
			 System.out.println("State is:"+state);
		 }
	}
}
