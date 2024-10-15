package petstore.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePetRequest extends Request {
    private String _description;
    private String method;
    private String path;
    private Payload payload;

    public UpdatePetRequest(String method, String path, Payload payload) {
        super(method, path);
        this.payload = payload;
    }

    public UpdatePetRequest() {
    }

    public Payload getPayload() {
        return this.payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Payload {
        private int id;
        private String name;
        private Category category;
        private String[] photoUrls;
        private CreatePetRequest.Tag[] tags;
        private String status;

        public Payload() {
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Category getCategory() {
            return this.category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String[] getPhotoUrls() {
            return this.photoUrls;
        }

        public void setPhotoUrls(String[] photoUrls) {
            this.photoUrls = photoUrls;
        }

        public CreatePetRequest.Tag[] getTags() {
            return this.tags;
        }

        public void setTags(CreatePetRequest.Tag[] tags) {
            this.tags = tags;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class Category {
            private int id;
            private String name;

            public Category() {
            }

            public int getId() {
                return this.id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
