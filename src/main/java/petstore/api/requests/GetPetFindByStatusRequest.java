package petstore.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPetFindByStatusRequest extends Request {
    private QueryParams queryParams;

    public GetPetFindByStatusRequest() {
    }

    public GetPetFindByStatusRequest(String method, String path, QueryParams queryParams) {
        super(method, path);
        this.queryParams = queryParams;
    }

    public QueryParams getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
        if (queryParams != null && queryParams.getStatus() != null) {
            String status = String.join(",", queryParams.getStatus());
            this.setPath("v2/pet/findByStatus?/status=" + status);
        }

    }

    public static class QueryParams {
        private List<String> status = new ArrayList();

        public QueryParams() {
        }

        public List<String> getStatus() {
            return this.status;
        }

        public void setStatus(List<String> status) {
            this.status.clear();
            if (status != null && !status.isEmpty()) {
                this.status.addAll(status);
            }

        }

        public String toQueryString() {
            return this.status.isEmpty() ? "" : "status=" + String.join(",", this.status);
        }
    }
}
