package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.util.concurrent.Flow;

public class Requests {
    private static final String defaultUrl = "http://localhost:8080/api/contacts";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ContactList getContacts(String query) throws IOException, InterruptedException{
        String targetUrl = query == null ? defaultUrl : defaultUrl + query;
        HttpRequest request = HttpRequest.newBuilder(URI.create(targetUrl)).header("accept", "application/json").build();
        return client.send(request, new JsonBodyHandler<>(ContactList.class)).body().get();
    }

    public static Contact addContact(Contact contact) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder(URI.create(defaultUrl)).
                POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(contact))).header("Content-Type", "application/json").build();
        return client.send(request, new JsonBodyHandler<>(Contact.class)).body().get();
    }
}
