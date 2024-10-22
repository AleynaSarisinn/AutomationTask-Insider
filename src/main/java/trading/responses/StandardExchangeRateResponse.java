package trading.responses;

import java.util.Map;

public class StandardExchangeRateResponse extends Response{
    private Map<String, Double> conversion_rates;

    public StandardExchangeRateResponse(String result, String documentation, String terms_of_use, long time_last_update_unix, String time_last_update_utc, long time_next_update_unix, String time_next_update_utc, String base_code) {
        super(result, documentation, terms_of_use, time_last_update_unix, time_last_update_utc, time_next_update_unix, time_next_update_utc, base_code);
    }

    // Getter ve setter metodları

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}