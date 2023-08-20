package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class Utils {

    private static final Gson gson = new Gson();
    public static JsonObject extractResponse(ExtractableResponse<Response> extractableResponse) {
        String response = extractableResponse.response().asString();
        return gson.fromJson(response, JsonObject.class);
    }

    public static void evaluateTest(ExtractableResponse<Response> extractableResponse, String expectedMessage, int expectedCode) {
        SoftAssert softAssert = new SoftAssert();
        int statusCode = extractableResponse.statusCode();
        JsonObject obj = Utils.extractResponse(extractableResponse);
        String message = obj.get("message").isJsonNull() ? "" : obj.get("message").getAsString();
        softAssert.assertEquals(message, expectedMessage);
        softAssert.assertEquals(statusCode, expectedCode);
        softAssert.assertAll();
    }
}
