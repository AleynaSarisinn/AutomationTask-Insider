package petstore.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPetRequest extends Request {
    private int petId;

    public GetPetRequest() {
    }

    public GetPetRequest(String method, String path, int petId) {
        super(method, path.replace("{petId}", String.valueOf(petId)));
        this.petId = petId;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
        this.setPath(this.getPath().replace("{petId}", String.valueOf(petId)));
    }
}
