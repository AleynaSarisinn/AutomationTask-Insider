package trading.responses;

public class PairWithAmountResponse extends Response {

    public PairWithAmountResponse(String result, String documentation, String terms_of_use, long time_last_update_unix, String time_last_update_utc, long time_next_update_unix, String time_next_update_utc, String base_code) {
        super(result, documentation, terms_of_use, time_last_update_unix, time_last_update_utc, time_next_update_unix, time_next_update_utc, base_code);
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }

    private double conversion_rate;
    private double conversion_result;
}
