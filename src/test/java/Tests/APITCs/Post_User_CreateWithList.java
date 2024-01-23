package Tests.APITCs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class Post_User_CreateWithList {
    public void createWithList() {
        RestAssured.baseURI = "http://localhost:8080/";

        // Request payload for adding an employee
        String requestBody = "{\n" +
                "    \"id\": 10,\n" +
                "    \"username\": \"theUser\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"James\",\n" +
                "    \"email\": \"john@email.com\",\n" +
                "    \"password\": \"12345\",\n" +
                "    \"phone\": \"12345\",\n" +
                "    \"userStatus\": 1\n" +
                "  }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/user/createUsersWithListInput");
        Assert.assertEquals(response.getStatusCode(),200,"Unexpected status code");
        Assert.assertEquals(response.getBody().asString(), requestBody, "Response body does not match the request body");

    }
}
