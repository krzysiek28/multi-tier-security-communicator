package web.mvc;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import web.mvc.domain.Conversation;
import web.mvc.service.AppUserService;
import web.mvc.service.ConversationService;
import web.mvc.service.MessageService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    AppUserService appUserService;
    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    ConversationService conversationService;
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/")
    public String homePage(ModelMap model) {
        model.addAttribute("authservice", userAuthenticationService);
        return "homePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loggedPage(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             SessionStatus status,
                             Model model,
                             HttpServletRequest request) throws URISyntaxException, JSONException {
        try {
            appUserService.login(username, password);
            status.setComplete();
        } catch (HttpStatusCodeException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/loginPage?error=badcredentials&message=" + errorMessage;

        }
        return "redirect:/homeLogged";
    }

    @RequestMapping("/logout")
    public String logout() {
        userAuthenticationService.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage(ModelMap model, SessionStatus status) {
        model.addAttribute("authservice", userAuthenticationService);
        status.setComplete();
        return "loginPage";
    }

    @RequestMapping(value = "/homeLogged")
    public String homeLogged(ModelMap model) throws URISyntaxException, JSONException, IOException {
        model.addAttribute("authservice", userAuthenticationService);
        return "homeLogged";
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public String postMessage(@RequestParam String message) throws URISyntaxException, JSONException, IOException {
        messageService.postMessage("abc", message);
        return "conversation";
    }

    @RequestMapping(value = "/registrationPage")
    public String registrationPage() {
        return "registrationPage";
    }

    @ExceptionHandler({ HttpServerErrorException.class})
    public String handleException(HttpServerErrorException ex) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(ex.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/homeLogged?error="+errorMessage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return"redirect:/homeLogged?error=Nieoczekiwany błąd!";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String register(@RequestParam("email") String email,
                           @RequestParam("username") String username,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("personal") String personalData,
                           SessionStatus status) throws URISyntaxException, JSONException {
        try {
            appUserService.register(username, email, login, password, personalData);
            status.setComplete();
        } catch (HttpStatusCodeException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            String errorType;
            switch (errorMessage) {
                case "Username already exists!":
                    errorType = "username";
                    break;
                case "User with provided email already exists!":
                    errorType = "email";
                    break;
                case "Login already exists!":
                    errorType = "login";
                    break;
                default:
                    errorType = errorMessage;
                    break;
            }
            return "redirect:/registrationPage?error=yes&message=" + errorType;
        }

        return "redirect:/homeLogged";
    }

    @RequestMapping(value = "/delete/{userName}")
    public String deleteUser(@PathVariable("userName") String userName) throws URISyntaxException, JSONException {
        try {
            appUserService.deleteUser();
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/homePage?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}