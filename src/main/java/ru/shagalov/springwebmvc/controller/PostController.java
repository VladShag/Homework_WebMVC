package ru.shagalov.springwebmvc.controller;

import com.google.gson.Gson;
import ru.shagalov.springwebmvc.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.shagalov.springwebmvc.service.PostService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;


@Controller
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    @Autowired
    private PostService service;
    private final Gson gson = new Gson();
    @GetMapping
    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(gson.toJson(data));
    }
    @GetMapping("/{id}")
    public void getById(@PathVariable long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        Post post = service.getById(id);
        response.getWriter().print(gson.toJson(post));
    }
    @PostMapping
    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.getWriter().print("Success!");

    }
}
