package com.lsa.flux.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class PageController {
    @GetMapping("/")
    public String  index(Model model) {
        return "index";
    }

    @GetMapping("/video/play")
        public String  video(Model model) {
        return "video";
    }
}
