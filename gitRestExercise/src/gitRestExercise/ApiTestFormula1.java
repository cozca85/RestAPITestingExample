package gitRestExercise;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import io.restassured.http.ContentType;

public class ApiTestFormula1 {

	String baseUri = "http://ergast.com/api/f1/";
	
	
	
	@Test
	public void test_Number_Of_Circuits_2017()
	{
		
		String season = "2017";
		int numberOfRaces = 20;
		
		given()
			.pathParam("raceSeason", season).
		when()
			.get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
		then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

	}
	
	@Test
	public void test_ResponseHeaderData_ShouldBeCorrect() {
	        
	    given().
	    when().
	        get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("4551"));
	}
}
