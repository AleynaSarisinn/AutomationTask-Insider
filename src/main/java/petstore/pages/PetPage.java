package petstore.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import petstore.utils.ConfigReader;

public class PetPage { // deprecated class rather than using it created generic methods like sendRequest and sentWrongRequests
    private final String baseUrl = ConfigReader.getProperty("baseUrl");

    public PetPage() {
    }

    public Response createPet(String petJson) {
        return RestAssured.given().contentType("application/json").body(petJson).post(this.baseUrl);
    }

    public Response readPet(int petId) {
        return RestAssured.given().get(this.baseUrl + "/" + petId);
    }

    public Response updatePet(String petJson) {
        return RestAssured.given().contentType("application/json").body(petJson).put(this.baseUrl);
    }

    public Response deletePet(int petId) {
        return RestAssured.given().delete(this.baseUrl + "/" + petId);
    }
}
