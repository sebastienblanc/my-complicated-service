package org.sebi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/lowercase")
public class LowercaseResource {

    @GET
    @Path("/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertToLowercaseByPath(@PathParam("text") String text) {
        if (text == null || text.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new LowercaseResponse(null, "Input text cannot be null or empty"))
                    .build();
        }
        
        String lowercaseText = text.toLowerCase();
        return Response.ok(new LowercaseResponse(lowercaseText, "Success")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertToLowercaseByQuery(@QueryParam("text") String text) {
        if (text == null || text.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new LowercaseResponse(null, "Input text cannot be null or empty"))
                    .build();
        }
        
        String lowercaseText = text.toLowerCase();
        return Response.ok(new LowercaseResponse(lowercaseText, "Success")).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertToLowercaseByBody(LowercaseRequest request) {
        if (request == null || request.getText() == null || request.getText().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new LowercaseResponse(null, "Input text cannot be null or empty"))
                    .build();
        }
        
        String lowercaseText = request.getText().toLowerCase();
        return Response.ok(new LowercaseResponse(lowercaseText, "Success")).build();
    }

    public static class LowercaseRequest {
        private String text;

        public LowercaseRequest() {
        }

        public LowercaseRequest(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class LowercaseResponse {
        private String result;
        private String message;

        public LowercaseResponse() {
        }

        public LowercaseResponse(String result, String message) {
            this.result = result;
            this.message = message;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}