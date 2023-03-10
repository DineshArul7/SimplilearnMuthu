package org.example;



import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static Logger logger= LogManager.getLogger();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void reqresSimpliLearnGet()
    {
        baseURI="https://reqres.in/";
        basePath="/api/users/{UserName}";
        Response response=given().pathParam("UserName",2).log().all()
                .when().get().then().statusCode(200).log().all().extract().response();
        JsonPath jsonPath=new JsonPath(response.asString());
        Assert.assertEquals(2,jsonPath.getInt("data.id"));
        logger.info("Successfully Validated the response");
        logger.info(response.asString());

    }

    @Test
    public void reqresSimpliLearnPost()
    {
        baseURI="https://reqres.in/";
        basePath="/api/users";
        String body="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response=given().body(body)
                .contentType(ContentType.JSON)
                .log().all()
                .when().post().then().statusCode(201).log().all().extract().response();
        JsonPath jsonPath=new JsonPath(response.asString());
        Assert.assertEquals("morpheus",jsonPath.get("name"));
        Assert.assertEquals("leader",jsonPath.get("job"));
        logger.info("Successfully Validated the response");
        logger.info(response.asString());

    }
}
