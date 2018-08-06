package com.example.mytter.app;

import com.example.mytter.model.domain.GithubRepo;
import com.example.mytter.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    private OAuth2RestTemplate auth2RestTemplate;

    @GetMapping(path = {"/"})
    public String getHello() {
        return "hello";
    }

    @GetMapping(path="/user")
    public String getUser(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user";
    }

    @GetMapping(path = "/repos")
    public String repositories(Model model){
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/user/repos").build().toUri();
        model.addAttribute("repos", auth2RestTemplate.getForEntity(uri, GithubRepo[].class).getBody());
        return "repos";
    }

    @GetMapping(path = "/repos_bs")
    public String repositories_bs(Model model){
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/user/repos").build().toUri();
        model.addAttribute("repos", auth2RestTemplate.getForEntity(uri, GithubRepo[].class).getBody());
        return "repos_bs";
    }
}
