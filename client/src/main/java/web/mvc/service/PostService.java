package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Conversation;
import web.mvc.domain.Post;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplateHCCHRF;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public List<Post> getPosts(String conversationName) throws URISyntaxException, IOException, HttpClientErrorException {
        URI uri = new URI("http://localhost:8210/post/" + conversationName);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
    }

/*    public void addPost(String conversationId, String message) throws JSONException, URISyntaxException {
        URI uri = new URI("http://localhost:8210/post/newPost");
        System.out.println("3");
        String userId = userAuthenticationService.getUsername();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + userAuthenticationService.getRawToken());
        String requestJson = new JSONObject()
                .put("conversationId", conversationId)
                .put("userId", userId)
                .put("body", message)
                .put("date", new Date())
                .toString();
        System.out.println("4");
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println("5");
    }*/

    public void addPost(String conversationId, String message) throws JSONException, URISyntaxException {
        URI uri = new URI("http://localhost:8210/post/newPost");
        System.out.println("3");
        String userId = userAuthenticationService.getUsername();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + userAuthenticationService.getRawToken());
        String requestJson = new JSONObject()
                .put("conversationId", conversationId)
                .put("userId", userId)
                .put("body", message)
                .put("date", new Date())
                .toString();
        System.out.println("4");
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        System.out.println("5");
        ResponseEntity<String> response = restTemplateHCCHRF.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println("6");
    }




}