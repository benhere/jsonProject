
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class DDTestUsingJsonData {
	
	WebDriver wd;
	
	@BeforeClass
	void setUpDriver()
	{
//		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Webdriver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		wd = new ChromeDriver();
	    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    wd.manage().window().maximize();
	    wd.manage().deleteAllCookies(); // delete all Browser's cookies
	}
	
	@AfterClass
	void tearDown()
	{
		wd.close();
	}
	
	@Test(dataProvider = "testData")
	void loginTest(String data)
	{
		String[] users = data.split(",");
		
		wd.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		wd.findElement(By.id("Email")).sendKeys(users[0]);
		wd.findElement(By.id("Password")).sendKeys(users[1]);
		wd.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
		
		 String act_title = wd.getTitle();
	     String exp_title = "nopCommerce demo store. Login";
	     Assert.assertEquals(act_title, exp_title);
	}
	
	@DataProvider(name="testData")
	String[] readJsonData() throws IOException, ParseException
	{
		//Read JSON data using JSON Parser object
		JSONParser jsp = new JSONParser();
		
		//load the file
		FileReader fr = new FileReader(".\\JSONFiles\\JsonTestData.json");
		
		//parse the JSON file
		Object obj = jsp.parse(fr);
		
		//typecasting - as each data is of type JSONObject
		JSONObject loginDeatils = (JSONObject) obj;
		
		//extract JSON data from JSONObject
		//and typecast to JSONArray
		JSONArray users = (JSONArray) loginDeatils.get("userlogins");
		
		//retrieve the data from JSONArray
		// and save it to Java's Array object
		
		//for above operation, create String array object
		String arr[] = new String[loginDeatils.size()];
		
		for(int i=0; i<loginDeatils.size(); i++)
		{
			JSONObject usersDetails = (JSONObject) users.get(i);
			String username = (String) usersDetails.get("username");
			String Password = (String) usersDetails.get("password");
			
			//now to String's array object,arr
			arr[i] = username+","+ Password;
		}
		return arr;
	}
}
