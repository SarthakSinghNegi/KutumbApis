package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.utils.Utils;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class SendOtpTest {

    private static final String BASE_URL = "https://crafto.app/crafto/v1.0";
    private static final String VERIFY_OTP_ENDPOINT = "/verify/otp/android";
    private static final String SEND_OTP_ENDPOINT = "/send/otp/android";

    @Test
    public void testSendOtpWithValidPhoneNumber() {

        String phoneNumber = "917087165058";
        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + phoneNumber + "\"}")
                .when()
                .post(BASE_URL + SEND_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "OK", 200);
    }

    @Test
    public void testSendOtpWithInvalidPhoneNumberFormat() {
        String invalidPhoneNumber = "123";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + invalidPhoneNumber + "\"}")
                .when()
                .post(BASE_URL + SEND_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "OK", 200);
    }

    @Test
    public void testSendOtpWithMissingPhoneNumber() {

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(BASE_URL + SEND_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "", 200);
    }
}