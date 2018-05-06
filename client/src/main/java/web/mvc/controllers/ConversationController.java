package web.mvc.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import web.mvc.domain.Conversation;
import web.mvc.service.ConversationService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ConversationController {

    UserAuthenticationService userAuthenticationService;
    ConversationService conversationService;

    public ConversationController(UserAuthenticationService userAuthenticationService, ConversationService conversationService) {
        this.userAuthenticationService = userAuthenticationService;
        this.conversationService = conversationService;
    }

    @RequestMapping(value = "/conversations")
    public String conversationsList(HttpServletRequest request, ModelMap modelMap) throws JSONException, IOException, URISyntaxException {
        try {
            List<Conversation> conversations = conversationService.getConversations();
            System.out.println(conversations);
//            Collections.sort(conversations, Comparator.comparingInt(Conversation::getId));
            modelMap.addAttribute("conversations", conversationService.getConversations());

        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/homeLogged?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/conversations";
    }
}
