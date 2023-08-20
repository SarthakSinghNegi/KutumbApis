package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.utils.Utils;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class VerifyOtpTest {

    private static final String BASE_URL = "https://crafto.app/crafto/v1.0";

    private static final String VERIFY_OTP_ENDPOINT = "/verify/otp/android";

    @Test
    public void testVerifyOtpWithIncorrectOtp() {
        String phoneNumber = "91111111999";
        String incorrectOtp = "5678";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + phoneNumber + "\", \"otp\": \"" + incorrectOtp + "\"}")
                .when()
                .post(BASE_URL + VERIFY_OTP_ENDPOINT)
                .then()
                .extract();
        Utils.evaluateTest(extractableResponse, "", 200);
    }

    @Test
    public void testVerifyOtpWithInvalidPhoneNumber() {
        String invalidPhoneNumber = "123";
        String otp = "1234";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + invalidPhoneNumber + "\", \"otp\": \"" + otp + "\"}")
                .when()
                .post(BASE_URL + VERIFY_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "", 200);
    }

    @Test
    public void testVerifyOtpWithMissingPhoneNumber() {
        String otp = "1234";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"otp\": \"" + otp + "\"}")
                .when()
                .post(BASE_URL + VERIFY_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "", 200);
    }

    @Test
    public void testVerifyOtpWithMissingOtp() {
        String phoneNumber = "91111111999";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + phoneNumber + "\"}")
                .when()
                .post(BASE_URL + VERIFY_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "", 200);
    }

    @Test
    public void testVerifyOtpWithValidPhoneNumberAndOtp() {
        String phoneNumber = "91111111999";
        String otp = "1234";

        ExtractableResponse<Response> extractableResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + phoneNumber + "\", \"otp\": \"" + otp + "\"}")
                .when()
                .post(BASE_URL + VERIFY_OTP_ENDPOINT)
                .then()
                .extract();

        Utils.evaluateTest(extractableResponse, "", 200);
    }
}
