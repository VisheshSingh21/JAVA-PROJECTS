package com.example.securingweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileController {

    // Temporarily stores bio per username (in memory)
    private Map<String, String> bioStore = new HashMap<>();

    @GetMapping("/hello")
    public String showProfile(Model model,
                              org.springframework.security.core.Authentication auth) {
        String username = auth.getName();
        String bio = bioStore.getOrDefault(username, "");
        model.addAttribute("bio", bio);
        model.addAttribute("saved", false);
        return "hello";
    }

    @PostMapping("/savebio")
    public String saveBio(@RequestParam String bio,
                          org.springframework.security.core.Authentication auth,
                          Model model) {
        String username = auth.getName();
        bioStore.put(username, bio);
        model.addAttribute("bio", bio);
        model.addAttribute("saved", true);
        return "hello";
    }
}
