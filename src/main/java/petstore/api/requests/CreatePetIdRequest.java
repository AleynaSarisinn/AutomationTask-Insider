package petstore.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePetIdRequest extends Request {
    private String name;
    private String status;
    private int petId;

    public CreatePetIdRequest() {
    }

    public CreatePetIdRequest(String method, String path, int petId) {
        super(method, path.replace("{petId}", String.valueOf(petId)));
        this.petId = petId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
        this.setPath(this.getPath().replace("{petId}", String.valueOf(petId)));
    }

    public static class PetId {
        private int id;

        public PetId() {
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}