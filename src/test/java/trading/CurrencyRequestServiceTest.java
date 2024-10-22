package trading;
import org.junit.Before;
import org.junit.Test;
import trading.responses.PairExchangeRateResponse;
import trading.responses.PairWithAmountResponse;
import trading.responses.StandardExchangeRateResponse;
import trading.utils.Request;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CurrencyRequestServiceTest {
    private CurrencyRequestService currencyRequestService;
    private Request request;

    @Before
    public void setUp() {
        request = new Request();
        currencyRequestService = new CurrencyRequestService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertZeroAmount() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        currencyRequestService.convert(response, "EUR", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertNegativeAmount() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        currencyRequestService.convert(response, "EUR", -100);
    }

    @Test(timeout = 5000)
    public void testApiResponseTime() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        currencyRequestService.convert(response, "EUR", 100);
    }

    @Test
    public void testStandardResponseStructure() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        assertNotNull(response);
        assertEquals("success", response.getResult());
        assertNotNull(response.getConversionRates());
    }

    @Test
    public void testConvertUsdToEur() throws IOException {
        PairExchangeRateResponse pairExchangeRateResponse = request.getPairConversion("USD", "EUR");
        Double convertedAmount = currencyRequestService.convertPair(pairExchangeRateResponse, 100);
        assertNotNull(convertedAmount);

        double expectedRate = 0.85;
        double expectedAmount = 100 * expectedRate;
        assertEquals(expectedAmount, convertedAmount, 0.01);

        System.out.println("Converted USD to EUR: " + convertedAmount);
    }

    @Test
    public void testTargetCode() throws IOException {
        PairExchangeRateResponse pairExchangeRateResponse = request.getPairConversion("USD", "EUR");
        assertEquals(pairExchangeRateResponse.getTargetCode(), "EUR");
    }

    @Test
    public void testSourceCode() throws IOException {
        PairExchangeRateResponse pairExchangeRateResponse = request.getPairConversion("USD", "EUR");
        assertEquals(pairExchangeRateResponse.getBaseCode(), "USD");
    }



    @Test
    public void testConvertEurToUsd() throws IOException {
        PairExchangeRateResponse pairExchangeRateResponse = request.getPairConversion("EUR", "USD");
        Double convertedAmount = currencyRequestService.convertPair(pairExchangeRateResponse, 100);
        assertNotNull(convertedAmount);

        double expectedRate = 1.18;
        double expectedAmount = 100 * expectedRate;
        assertEquals(expectedAmount, convertedAmount, 0.01);

        System.out.println("Converted EUR to USD: " + convertedAmount);
    }

    @Test
    public void testSmallAmountConversion() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        double convertedAmount = currencyRequestService.convert(response, "EUR", 0.01);
        assertNotNull(convertedAmount);

        double expectedRate = 0.85;
        double expectedAmount = 0.01 * expectedRate;
        assertEquals(expectedAmount, convertedAmount, 0.01);

        System.out.println("Converted small amount USD to EUR: " + convertedAmount);
    }

    @Test
    public void testLargeAmountConversion() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("USD");
        double convertedAmount = currencyRequestService.convert(response, "EUR", 1000000);
        assertNotNull(convertedAmount);

        double expectedRate = 0.85;
        double expectedAmount = 1000000 * expectedRate;
        assertEquals(expectedAmount, convertedAmount, 0.01);

        System.out.println("Converted large amount USD to EUR: " + convertedAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCurrency() throws IOException {
        StandardExchangeRateResponse response = request.getStandardConversion("XYZ");
        currencyRequestService.convert(response, "EUR", 100);
    }

    @Test
    public void testConvertPair() throws IOException {
        PairExchangeRateResponse pairExchangeRateResponse = request.getPairConversion("EUR", "GBP");
        double convertedAmount = currencyRequestService.convertPair(pairExchangeRateResponse, 100);
        assertNotNull(convertedAmount);

        double expectedRate = 0.90;
        double expectedAmount = 100 * expectedRate;
        assertEquals(expectedAmount, convertedAmount, 0.01);

        System.out.println("Converted EUR to GBP: " + convertedAmount);
    }

    @Test
    public void testConvertAmountPair() throws IOException {
        Integer initialAmount =100;
        PairWithAmountResponse pairWithAmountResponse = request.getPairAmountConversion("EUR", "GBP", initialAmount);
        double convertedAmount = currencyRequestService.convertWithAmountPair(pairWithAmountResponse);
        assertNotNull(convertedAmount);


        double conversionRate =  pairWithAmountResponse.getConversion_rate();
        double expectedAmount = conversionRate*initialAmount;


        double expectedRate = 0.90;
        assertEquals(expectedRate, convertedAmount, 0.01);
        assertEquals(convertedAmount,expectedAmount );

        System.out.println("Converted EUR to GBP with amount: " + convertedAmount);
    }
}

