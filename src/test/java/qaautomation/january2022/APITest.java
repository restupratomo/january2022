package qaautomation.january2022;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import qaautomation.january2022.utils.DataUtility;

public class APITest extends BaseAPITest {

	@Test
	public void dashboardAPI() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get("/build_cards");
		int statusCode = response.getStatusCode();
		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));
		assertEquals(200, statusCode);
	}

	@Test
	public void configurations() {
		Response response = given().spec(commonJsonSpec).when().get("/configurations");
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
	}

}
