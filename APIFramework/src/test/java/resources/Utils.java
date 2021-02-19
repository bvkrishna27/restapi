package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req; // If we declare any variable as static then that variable is shared accross all the test execution 
	
	// create the one method to store the common utilis like base uri
	public RequestSpecification requestSpecification() throws IOException
	{
		
		//Create the object for print stream to store log information on run time
		
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt")); // New Fileoutput stream will write log information on run time
		//RestAssured.baseURI= "https://rahulshettyacademy.com";
		// we can call any method not creating object for any class using extends(inherited) or static
		// we can call any method if that is static method (declared getGlobalValue as static)
		
		req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
				
				 .addFilter(RequestLoggingFilter.logRequestTo(log)) // .add filter mechanisim print the log information using the addFilter for the request
				 
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))//.add filter for response printing in the log file
				 
				 .setContentType(ContentType.JSON).build();
		 
		 return req;
		}
		return req;
	}
	
	// created below method to get the global properties
	
	public static String getGlobalValue(String key) throws IOException 
	{
		
		Properties prop = new Properties(); // properties class object will scan the all the files with .properties exetension to get the global properties values
		// create the file input class to tell where exactly the file is present 
		
		FileInputStream fis = new FileInputStream("C:\\Users\\User\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties"); // FileipuStream will read the file information
		
		// creating the connecting between prop and fis //
		prop.load(fis); // .load will give the  infomration from fis to prop
		
		// now the .get the global values using the prop
		
		
		
		return prop.getProperty(key);
	} 
	
	public String getJsonPath(Response res1 ,String key) 
	{
		String response2=res1.asString();
		
		JsonPath js =  new JsonPath(response2);
		
		return js.get(key).toString();
	}
	

}
