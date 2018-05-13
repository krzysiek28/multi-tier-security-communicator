package web.mvc.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import web.mvc.domain.Post;
import web.mvc.service.PostService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class PostController {

    UserAuthenticationService userAuthenticationService;
    PostService postService;

    public PostController(UserAuthenticationService userAuthenticationService, PostService postService) {
        this.userAuthenticationService = userAuthenticationService;
        this.postService = postService;
    }

    @RequestMapping(value = "/conversation/messages/{conversationId}/posts")
    public String postsList(@PathVariable("conversationId") String conversationId,
                            HttpServletRequest request,
                            ModelMap modelMap) throws JSONException, IOException, URISyntaxException {
        try {
            modelMap.addAttribute("posts", postService.getPosts(conversationId));
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/homeLogged?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/conversation";
    }

    @RequestMapping(value = "/conversation/messages/{conversationId}/posts", method = RequestMethod.POST)
    public String addPost(@PathVariable String conversationId,
                          @RequestParam String message,
                          ModelMap model) throws URISyntaxException, JSONException, IOException {
        model.addAttribute("authservice", userAuthenticationService);
        postService.addPost(conversationId, message);
        return "/conversation";
    }
}
