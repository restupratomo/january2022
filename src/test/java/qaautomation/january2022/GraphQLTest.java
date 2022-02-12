package qaautomation.january2022;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import qaautomation.january2022.utils.GraphQLRequestBuilder;

public class GraphQLTest {
	@Test
	public void checkCEO() {
		GraphQLRequestBuilder myGqlBuilder = new GraphQLRequestBuilder();
		String query = "{  company {    ceo  }}";
		myGqlBuilder.setQuery(query);
		String jsonStringQuery = myGqlBuilder.getJSONString();

		Response response = given().contentType(ContentType.JSON).body(jsonStringQuery).when()
				.post("https://api.spacex.land/graphql/");
		String ceoName = response.jsonPath().get("data.company.ceo");
		assertEquals(ceoName, "Elon Musk");
	}

	@Test
	public void launchRocketWithVariable() {
		JSONObject variables = new JSONObject();
		variables.put("limit", 5);

		GraphQLRequestBuilder myGqlBuilder = new GraphQLRequestBuilder();
		String query = "query getLaunches($limit : Int!){launches(limit: $limit) {    mission_name  }}";
		myGqlBuilder.setQuery(query);
		myGqlBuilder.setVariables(variables.toString());
		String jsonStringQuery = myGqlBuilder.getJSONString();

		Response response = given().contentType(ContentType.JSON).body(jsonStringQuery).when()
				.post("https://api.spacex.land/graphql/");
		String missionName = response.jsonPath().get("data.launches[0].mission_name");
		assertEquals(missionName, "Thaicom 6");
	}

	@Test
	public void fakerTest() {
		Faker faker = new Faker();
		System.out.println(faker.phoneNumber().cellPhone());
		System.out.println(faker.name().fullName());
	}
}
