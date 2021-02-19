package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


// importing the Utils class methods by extending then no need to create object to import utils method
public class stepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response res1;
	TestDataBuild data = new TestDataBuild();
	static String place_id; // declaring the variable as static so that it will use the place id for all the test case other wise after running the first test case it will set the value of placeid as null. So our delete place api will not run properly
	 
	 
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException 
		{
						
			res = given().spec(requestSpecification()).body(data.addPlaceLoad(name,language,address));
		}


		@When("user calls {string} with {string} http request")
		public void user_calls_with_http_request(String resource, String method) {
		    // Declare the object to call the apis from the API resources 
			
			//constructor will be called with valueOf(resource) which you pass
			APIResources resourcesAPI=APIResources.valueOf(resource); // valueOf method will accepts all the resources name and it invokes the constructor
			
			System.out.println(resourcesAPI.getResource());
			
			resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 
			if(method.equalsIgnoreCase("POST")) 
			res1 = res.when().post(resourcesAPI.getResource());
			else if(method.equalsIgnoreCase("GET")) 
			res1 = res.when().get(resourcesAPI.getResource());	
			
			
					
		}
		@Then("the API call got success with status code {int}")
		public void the_api_call_got_success_with_status_code(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
		    assertEquals(res1.getStatusCode(),200);
		}
		@Then("{string} in response body is {string}")
		public void in_response_body_is(String keyvalue, String Expectedvalue) {
		    // Write code here that turns the phrase above into concrete actions
		  		  
		  assertEquals(getJsonPath(res1,keyvalue),Expectedvalue); // junit assertion to check keyvlaue is matching with Exepcted value
		  //keyvalue is converted into to string using tostring
		}
		@Then("Verify place_Id created maps to {string} using {string}")
		public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		    
			//Prepare Request spec  
			
			 place_id = getJsonPath(res1,"place_id");
			
			res = given().spec(requestSpecification()).queryParam("place_id",place_id);
			
			user_calls_with_http_request(resource,"GET");
			
			String actualName = getJsonPath(res1,"name");
			assertEquals(actualName,expectedName);
		    
		}
		
		@Given("DeletePlace Payload")
		public void delete_place_payload() throws IOException {
		    // Write code here that turns the phrase above into concrete actions
		    
			res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		}


		
	




}
