package com.amazoneaws;

import com.amazoneaws.utils.BaseTest;
import com.amazoneaws.utils.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIEndPointTest extends BaseTest {

    @Test(priority = 1)
    public void verifyGetTheFirstofPostEndPoint(){

        String endPoint = "https://my-json-server.typicode.com/typicode/demo/posts/1";

        System.out.println("1. Get response baseline from JSon file");
        JsonHelper jsonHelper = new JsonHelper();
        String responseBaseline = jsonHelper.getResponseBaseLine("GetTheFirstPostResponse.json");

        System.out.println("2. Send Get request to the server");
        Response response = RestAssured.get(endPoint);

        System.out.println("3. Get the response status code");
        int responseStatusCode = response.getStatusCode();

        System.out.println("VP. Verify Response Status Code is 200");
        Assert.assertEquals(200, responseStatusCode);

        System.out.println("4. Get The response body in string");
        String responseBody = response.getBody().asString().replace("\n", "").replace("  ", "").replace(": ", ":");

        System.out.println("VP. Verify Response body");
        Assert.assertEquals(responseBaseline, responseBody);
    }
}
