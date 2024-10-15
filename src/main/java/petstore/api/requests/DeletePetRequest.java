package petstore.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeletePetRequest extends Request {
    private int petId;

    public DeletePetRequest() {
    }

    public DeletePetRequest(String method, String path, int petId) {
        super(method, path.replace("{petId}", String.valueOf(petId)));
        this.petId = petId;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.setPath(this.getPath().replace("{petId}", String.valueOf(petId)));
    }
}
