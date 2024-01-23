package Tests.APITCs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Post_User {
    @Test
    public void createUser() {
        RestAssured.baseURI = "http://localhost:8080/";

        // Request payload for adding an employee
        String requestBody = "{ \"id\": 10,\n" +
                "  \"username\": \"theUser\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"James\",\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"12345\",\n" +
                "  \"userStatus\": 1}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/user");
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200,"Unexpected status code");
        Assert.assertEquals(response.getBody().asString(), requestBody, "Response body does not match the request body");

    }

}
