package web.mvc.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ConversationService {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void addConversation(String name, String password) throws JSONException, URISyntaxException {
        String userId = userAuthenticationService.getUsername();

        URI uri = new URI("http://localhost:8210/conversation");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+userAuthenticationService.getRawToken());

        String requestJson = new JSONObject()
                .put("name", name)
                .put("userId", userId)
                .put("password", password)
                .toString();

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
    }
}
