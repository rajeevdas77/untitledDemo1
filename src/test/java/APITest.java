//
//import io.restassured.RestAssured;
//import org.testng.annotations.Test;
//import io.restassured.response.Response;
//
//
//public class APITest {
//
//    @Test
//    void test1(){
//
//        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
//
//        System.out.println("Response : "+response.asString());
//        System.out.println("Status Code :"+response.getStatusCode());
//
//    }
//
//}

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.User;
import static io.restassured.RestAssured.given;
import io.qameta.allure.*;

public class APITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Test creating a new user")
    @AllureId("001")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Create User")
    @Story("POST - Create a user")
    @Description("Verify that a new user can be created")
    public void testCreateUser() {
        User user = new User("rajeev", "Software Engineer");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "Expected status code 201");

        System.out.println(response.getBody().asString());
    }

    @Test(description = "Test retrieving user details")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Get User")
    @Story("GET - Retrieve a user")
    @Description("Verify that we can fetch user details")
    public void testGetUser() {
        Response response = given()
                .when()
                .get("/users/2")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200");

        System.out.println(response.getBody().asString());
    }
}
