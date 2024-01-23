package Tests.APITCs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Get_user_username {
    @Test
    public void getUserByUsername(){
        RestAssured.baseURI="http://localhost:8080/";
        String username = "username";
        // Send the GET request with parameters
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .param("username", username)
                .when()
                .get("/user/loginUser");

        // Validate the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");
    }
}
