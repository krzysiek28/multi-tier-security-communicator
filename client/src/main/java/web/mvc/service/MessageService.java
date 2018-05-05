package web.mvc.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@Service
public class MessageService {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void postMessage(String conversationId, String message) throws JSONException, URISyntaxException {

        System.out.println("IDZIE NEW MESSAGE");

        URI uri = new URI("http://localhost:8210/post/newMessage");

        String userId = userAuthenticationService.getUsername();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + userAuthenticationService.getRawToken());

        System.out.println("TOKEN TO "+userAuthenticationService.getRawToken());

        String requestJson = new JSONObject()
                .put("conversationId", conversationId)
                .put("userId", userId)
                .put("body", message)
                .put("date", new Date())
                .toString();

        System.out.println("SLE");

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);


    }

}
