package web.mvc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Conversation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private RestTemplate restTemplateHCCHRF;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public void addConversation(String name, String password) throws JSONException, URISyntaxException {
        String userId = userAuthenticationService.getUsername();
        System.out.println("IDZIE DODANIE KONWERSACJI O NAZWIE "+name+" Z HASŁEM "+password+" ZALOZONEJ PRZEZ "+userId);

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

    public List<Conversation> getConversations() throws URISyntaxException, IOException, HttpClientErrorException {
        String username = userAuthenticationService.getUsername();
        URI uri = new URI("http://localhost:8210/conversation/" + username);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + userAuthenticationService.getRawToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> data = restTemplateHCCHRF.exchange(uri, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(data.getBody(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
    }
}
