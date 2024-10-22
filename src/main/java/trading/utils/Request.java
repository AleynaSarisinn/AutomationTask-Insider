package trading.utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import trading.responses.PairExchangeRateResponse;
import trading.responses.PairWithAmountResponse;
import trading.responses.StandardExchangeRateResponse;


import java.io.IOException;

public class Request {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY";


    public StandardExchangeRateResponse getStandardConversion(String sourceCurrency) throws IOException {
        String url = BASE_URL + "/latest/" + sourceCurrency;

        Response response = RestAssured.get(url);

        if (response.getStatusCode() != 200) {
            throw new IOException("Unexpected code " + response);
        }

        return response.getBody().as(StandardExchangeRateResponse.class);
    }

    public PairExchangeRateResponse getPairConversion(String sourceCurrency, String targetCurrency) throws IOException {
        String url = BASE_URL + "/pair/" + sourceCurrency + "/" + targetCurrency;

        Response response = RestAssured.get(url);

        if (response.getStatusCode() != 200) {
            throw new IOException("Unexpected code " + response);
        }
        return response.getBody().as(PairExchangeRateResponse.class);
    }

    public PairWithAmountResponse getPairAmountConversion(String sourceCurrency, String targetCurrency, double amount) throws IOException {
        String url = BASE_URL + "/pair/" + sourceCurrency + "/" + targetCurrency + "/"  + amount;

        Response response = RestAssured.get(url);

        if (response.getStatusCode() != 200) {
            throw new IOException("Unexpected code " + response);
        }
        return response.getBody().as(PairWithAmountResponse.class);
    }

}
