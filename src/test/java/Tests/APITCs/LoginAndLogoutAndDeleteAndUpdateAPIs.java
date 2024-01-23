package Tests.APITCs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAndLogoutAndDeleteAndUpdateAPIs {
    String authToken;
    @Test
    public void testLoginUserApi() {
        RestAssured.baseURI = "http://localhost:8080/";

        // Parameters for the login API (adjust these based on the actual requirements of the API)
        String username = "yourUsername";
        String password = "yourPassword";

        // Send the GET request with parameters
        Response loginresponse = RestAssured.given()
                .header("X-Rate-Limit",100)
                .header("X-Expires-After","2024-12-31T23:59:59Z")
                .header("Content-Type", "application/json")
                .param("username", username)
                .param("password", password)
                .when()
                .get("/user/loginUser");
        System.out.println("Response status code: " + loginresponse.getStatusCode());
        System.out.println("Response body: " + loginresponse.getBody().asString());
        // Validate the status code
        Assert.assertEquals(loginresponse.getStatusCode(), 200, "Unexpected status code");
          authToken = loginresponse.jsonPath().getString("token");

    }
    @Test(dependsOnMethods = "testLoginUserApi")
    public void deleteUser(){
        RestAssured.baseURI="http://localhost:8080/";
        // Replace "userIdToDelete" with the actual user ID you want to delete
        String userIdToDelete = "123";

        // Send the DELETE request with the authentication token in the headers
        Response deleteResponse = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .pathParam("userId", userIdToDelete)
                .when()
                .delete("/user/deleteUser/{userId}");

        // Validate the status code
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Delete API - Unexpected status code");

    }
    @Test(dependsOnMethods = "testLoginUserApi")
    public void updateUser(){
        RestAssured.baseURI = "http://localhost:8080/";
        // Request payload for adding an employee
        String requestBody = "{\n" +
                "  \"id\": 10,\n" +
                "  \"username\": \"theUser\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"James\",\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"12345\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .body(requestBody)
                .put("/user");
        Assert.assertEquals(response.getStatusCode(),200,"Unexpected status code");
    }
    @Test(dependsOnMethods = "testLoginUserApi")
    public void testLogoutUserApi() {
        RestAssured.baseURI = "http://localhost:8080/";


        // Send the GET request with parameters
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/user/loginUser");

        // Validate the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");
    }
}
