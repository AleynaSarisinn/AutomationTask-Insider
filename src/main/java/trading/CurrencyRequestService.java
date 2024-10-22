package trading;

import trading.responses.PairExchangeRateResponse;
import trading.responses.PairWithAmountResponse;
import trading.responses.StandardExchangeRateResponse;
import trading.utils.Request;

import java.io.IOException;


public class CurrencyRequestService extends Request { // Request sınıfını extend ediyor
    public CurrencyRequestService() {
        super(); // Request'in constructor'ını çağırır
    }

    // Standart döviz kurunu alıp, verilen miktarı çeviren ana metod USD
    public double convert(StandardExchangeRateResponse standardResponse, String targetCurrency,double amount) throws IOException {

        double exchangeRate = standardResponse.getConversionRates().get(targetCurrency);

        double convertedAmount = amount * exchangeRate;

        return convertedAmount;
    }

    public double convertPair(PairExchangeRateResponse response , double amount) throws IOException {
        double exchangeRate = response.getConversionRate();
        double convertedAmount = amount * exchangeRate;

        return convertedAmount;
        // response.getTargetCode();
        // response.getBaseCode(); can be controlled in test class
    }

    public double convertWithAmountPair(PairWithAmountResponse  response) throws IOException {
        double conversionResult = response.getConversion_result();
        return conversionResult;
    }


}

