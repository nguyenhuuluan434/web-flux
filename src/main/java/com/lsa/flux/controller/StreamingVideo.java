package com.lsa.flux.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;

@RestController
public class StreamingVideo {

    @GetMapping("/videos/full/fa21525e-8482-4e95-a5aa-41d0749f7afa")
    public ResponseEntity<UrlResource> getVideo() throws MalformedURLException {
        UrlResource video = new UrlResource(new File("/home/luannh/Desktop/1.mp4").toURI());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory
                        .getMediaType(video)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }
}
