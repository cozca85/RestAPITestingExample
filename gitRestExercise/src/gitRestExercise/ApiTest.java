package gitRestExercise;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWeatherStatusCode200() {
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Nice");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		System.out.println("Response Code is: " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());	
	}
	
	@Test
	public void testGetWeatherWrongCityNameStatusCode400() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Snodfgfddfdf");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		System.out.println("Response Code is: " + response.getStatusCode());
		assertEquals(400, response.getStatusCode());	
	}
	
	@Test 
	public void testPostBadrequest()
	{
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
		
		String apiBody = "{\"FirstName\":\"Coni\",\"LastName\":\"Coni\","
				+ "\"UserName\":\"simpleuser001\",\"Password\":\"password1\","
				+ "\"Email\":\"someuser@gmail.com\"}";
		
		 RequestSpecBuilder builder = new RequestSpecBuilder();
		 
		 //Set API's Body
		 builder.setBody(apiBody);
		 
		 //Setting content type as application/json
		 builder.setContentType("application/json; charset=UTF-8");
		 
		 RequestSpecification requestSpec = builder.build();
		 
		 //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
		 //String[] response = given().authentication().preemptive().basic({""}, {""})
		 //.spec(requestSpec).when().post(restAPIUrl);
		 	
		 
		 //request.body(builder);
		
		 Response response = request.post("/register");
		 
		 int statusCode = response.getStatusCode();
		 
		 String statusMessage = response.getStatusLine();
		 
		 System.out.println("STATUS CODE is : " + statusCode + "  " + statusMessage);
		 assertEquals(400, statusCode);
	}
	
	@Test
	public void testPostSuccess()
	{
		
		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		
		String apiBody = "{\"FirstName\":\"Coni\",\"LastName\":\"Coni\","
				+ "\"UserName\":\"simpleuser001\",\"Password\":\"password1\","
				+ "\"Email\":\"someuser@gmail.com\"}";
		

		String apiBody1 = "{\"author\":\"\","
				+ "\"title\":\"\"}";
		
		/*
		 * JsonObject requestParam = new JsonObject();
		 *  requestParam.addProperty("name",
		 * "Master"); 
		 * requestParam.addProperty("age", "34");
		 */
		JsonObject requestParam = new JsonObject();
		
        given().urlEncodingEnabled(true)
        	.body(apiBody1)
            .header("Accept", ContentType.JSON.getAcceptHeader())
            .put("/register")
            .then().statusCode(200);
	}
	
	

	
	
}
