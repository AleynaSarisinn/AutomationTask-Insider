package petstore.tests;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;
import petstore.api.requests.CreatePetIdRequest;
import petstore.api.requests.CreatePetRequest;
import petstore.api.requests.DeletePetRequest;
import petstore.api.requests.GetPetFindByStatusRequest;
import petstore.api.requests.GetPetRequest;
import petstore.api.requests.UpdatePetRequest;
import petstore.clients.ApiClient;
import petstore.tests.utils.PetBaseTest;
import petstore.utils.JsonUtils;

public class PetApiTests extends ApiClient {
    private final PetBaseTest petBaseTest = new PetBaseTest();
    private final String createPetJsonFilePath = "src/test-data/request-data/createPet.json";
    private final String createPetIdJsonFilePath = "src/test-data/request-data/createPetId.json";
    private final String updatePetJsonFilePath = "src/test-data/request-data/updatePet.json";
    private final String readPetJsonFilePath = "src/test-data/request-data/readPet.json";
    private final String readPetByStatusJsonFilePath = "src/test-data/request-data/readPetFindByStatus.json";
    private final String deletePetJsonFilePath = "src/test-data/request-data/deletePet.json";

    public PetApiTests() {
    }


    // Positive tests for CRUD operations

    /**
     * Test for creating a pet with valid data.
     * Expected: Status code 200, successful creation.
     */


    @Test
    public void testCreatePet() throws IOException {
        CreatePetRequest createPetRequest = JsonUtils.readJsonFile(createPetJsonFilePath, CreatePetRequest.class);
        Response response = ApiClient.sendRequest(createPetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 200);
    }

    // Failed test ! -> This test returns 404 with valid petId to check in test output: https://prnt.sc/ktLEl90ob4cd for web: https://prnt.sc/wHdnYIEthKBs
    @Test
    public void testCreatePetId() throws IOException {
        CreatePetIdRequest createPetIdRequest = JsonUtils.readJsonFile(createPetIdJsonFilePath, CreatePetIdRequest.class);
        Response response = ApiClient.sendRequestForCreatePathId(createPetIdRequest);
        //System.out.println(response);

        this.petBaseTest.verifyHttpStatusCode(response, 200);
    }

    //Failed test ! This test takes fail : if petId is not exists: please follow here for web and test responses -> https://prnt.sc/Ijqc6huWBU-0
    @Test
    public void testReadPet() throws IOException {
        GetPetRequest getPetRequest = JsonUtils.readJsonFile(readPetJsonFilePath, GetPetRequest.class);
        Response response = ApiClient.sendRequest(getPetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 200);
    }

    // //In this test status MUST be sent as a required parameter in body. Acceptable list : ["available","pending", "sold"]
    @Test
    public void testReadPetByStatus() throws IOException {
        GetPetFindByStatusRequest getPetFindByStatusRequest = JsonUtils.readJsonFile(readPetByStatusJsonFilePath, GetPetFindByStatusRequest.class);
        GetPetFindByStatusRequest.QueryParams queryParams = new GetPetFindByStatusRequest.QueryParams();
        queryParams.setStatus(List.of("available"));
        getPetFindByStatusRequest.setQueryParams(queryParams);

        Response response = ApiClient.sendRequest(getPetFindByStatusRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 200);
    }

    @Test
    public void testUpdatePet() throws IOException {
        UpdatePetRequest updatedPetRequest = JsonUtils.readJsonFile(updatePetJsonFilePath, UpdatePetRequest.class);
        Response response = ApiClient.sendRequest(updatedPetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 200);
    }

    //This endpoint returns 404 as a behaviour : https://prnt.sc/3EDrPkhoAlus
    @Test
    public void testDeletePet() throws IOException {
        DeletePetRequest deletePetRequest = JsonUtils.readJsonFile(deletePetJsonFilePath, DeletePetRequest.class);
        Response response = ApiClient.sendRequest(deletePetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 404);
    }

// Negative test scenarios for CRUD operations

    /**
     * Negative test for creating a pet with invalid data.
     * Expected: Status code 400 or 500, error response.
     */


    @Test
    public void testCreatePetInvalidData() throws IOException {
        CreatePetRequest createPetRequest = JsonUtils.readJsonFile(createPetJsonFilePath, CreatePetRequest.class);
        Response response = ApiClient.sendWrongRequest(createPetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 400, 404);
    }

    @Test
    public void testReadPetNotFound() throws IOException {
        GetPetRequest getPetRequest = JsonUtils.readJsonFile(readPetJsonFilePath, GetPetRequest.class);
        Response response = ApiClient.sendWrongRequest(getPetRequest);

        this.petBaseTest.verifyHttpStatusCode(response, 404);
    }

    // Failed test ! -> This test takes 200 with Invalid Request Body. To check: https://prnt.sc/E9yQxmKbDCxx
    @Test
    public void testReadPetByInvalidData() throws IOException {
        GetPetFindByStatusRequest getPetFindByStatusRequest = JsonUtils.readJsonFile(readPetByStatusJsonFilePath, GetPetFindByStatusRequest.class);
        Response response = ApiClient.sendWrongRequest(getPetFindByStatusRequest);
        this.petBaseTest.verifyHttpStatusCode(response, 404);
    }

    @Test
    public void testUpdatePetInvalidData() throws IOException {
        UpdatePetRequest updatePetRequest = JsonUtils.readJsonFile(updatePetJsonFilePath, UpdatePetRequest.class);
        Response response = ApiClient.sendWrongRequest(updatePetRequest);
        this.petBaseTest.verifyHttpStatusCode(response, 400, 404, 500);
    }

    @Test
    public void testDeletePetNotFound() throws IOException {
        DeletePetRequest deletePetRequest = JsonUtils.readJsonFile(deletePetJsonFilePath, DeletePetRequest.class);
        Response response = ApiClient.sendWrongRequest(deletePetRequest);
        this.petBaseTest.verifyHttpStatusCode(response, 404);
    }
}
