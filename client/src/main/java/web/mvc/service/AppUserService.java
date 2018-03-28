package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppUserService {
    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    RestTemplate restTemplateHCCHRF;



}
