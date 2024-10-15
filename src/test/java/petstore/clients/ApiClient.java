package petstore.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import petstore.api.requests.Request;
import petstore.utils.ConfigReader;
import petstore.utils.JsonUtils;

public class ApiClient {
    private static final String baseUrl = ConfigReader.getProperty("baseUrl");

    public ApiClient() {
    }

    public static <T extends Request> Response sendRequest(T request) throws JsonProcessingException {
        String requestBody = request != null ? JsonUtils.convertToJson(request) : null;

        System.out.println(request.getPath());
        switch (request.getMethod().toUpperCase()) {
            case "POST" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(requestBody)
                        .log().all()
                        .post(baseUrl + request.getPath());
            }
            case "PUT" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(requestBody)
                        .log().all()
                        .put(baseUrl + request.getPath());
            }
            case "DELETE" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(requestBody)
                        .log().all()
                        .delete(baseUrl + request.getPath());
            }
            case "GET" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .log().all()
                        .get(baseUrl + request.getPath());
            }

            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + request.getMethod());
        }
    }

    //this method created for only testCreatePetId,  .contentType("application/json") test take HTTP Status code: 415
    public static <T extends Request> Response sendRequestForCreatePathId(T request) throws JsonProcessingException {
        String requestBody = request != null ? JsonUtils.convertToJson(request) : null;
        if (request.getMethod().toUpperCase().equals("POST")) {
            return RestAssured
                    .given()
                    .contentType("application/x-www-form-urlencoded")
                    .body(requestBody).log().all()
                    .post(baseUrl + request.getPath());
        } else {
            throw new IllegalArgumentException("Unsupported HTTP method: " + request.getMethod());
        }
    }

    public static <T extends Request> Response sendWrongRequest(T request) throws IOException {
        String requestBody = JsonUtils.convertToJson(request);
        String invalidJson = createInvalidJson(requestBody);

        System.out.println(request.getPath());
        System.out.println("InvalidJson created for negative tests:" + invalidJson);

        switch (request.getMethod().toUpperCase()) {
            case "POST" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(invalidJson)
                        .post(baseUrl + request.getPath());
            }
            case "PUT" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(invalidJson)
                        .put(baseUrl + request.getPath());
            }
            case "DELETE" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(invalidJson)
                        .delete(baseUrl + request.getPath());
            }
            case "PATCH" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(invalidJson)
                        .patch(baseUrl + request.getPath());
            }
            case "GET" -> {
                return RestAssured
                        .given()
                        .contentType("application/json")
                        .body(invalidJson)
                        .get(baseUrl + request.getPath());
            }
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + request.getMethod());
        }
    }

    private static String createInvalidJson(String requestBody) {
        return requestBody.substring(0, requestBody.length() - 20);
    }
}