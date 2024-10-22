package trading.responses;

public class Response {

    private String result;
    private String documentation;
    private String terms_of_use;
    private long time_last_update_unix;
    private String time_last_update_utc;
    private long time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;


    public Response(String result, String documentation, String terms_of_use,
                    long time_last_update_unix, String time_last_update_utc,
                    long time_next_update_unix, String time_next_update_utc,
                    String base_code) {
        this.result = result;
        this.documentation = documentation;
        this.terms_of_use = terms_of_use;
        this.time_last_update_unix = time_last_update_unix;
        this.time_last_update_utc = time_last_update_utc;
        this.time_next_update_unix = time_next_update_unix;
        this.time_next_update_utc = time_next_update_utc;
        this.base_code = base_code;
    }

    // Getter ve Setter metodları

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return terms_of_use;
    }

    public void setTermsOfUse(String terms_of_use) {
        this.terms_of_use = terms_of_use;
    }

    public long getTimeLastUpdateUnix() {
        return time_last_update_unix;
    }

    public void setTimeLastUpdateUnix(long time_last_update_unix) {
        this.time_last_update_unix = time_last_update_unix;
    }

    public String getTimeLastUpdateUtc() {
        return time_last_update_utc;
    }

    public void setTimeLastUpdateUtc(String time_last_update_utc) {
        this.time_last_update_utc = time_last_update_utc;
    }

    public long getTimeNextUpdateUnix() {
        return time_next_update_unix;
    }

    public void setTimeNextUpdateUnix(long time_next_update_unix) {
        this.time_next_update_unix = time_next_update_unix;
    }

    public String getTimeNextUpdateUtc() {
        return time_next_update_utc;
    }

    public void setTimeNextUpdateUtc(String time_next_update_utc) {
        this.time_next_update_utc = time_next_update_utc;
    }

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

}


