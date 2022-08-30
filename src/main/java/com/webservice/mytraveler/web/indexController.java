package com.webservice.mytraveler.web;

import com.webservice.mytraveler.config.auth.LoginUser;
import com.webservice.mytraveler.config.auth.dto.SessionUser;
import com.webservice.mytraveler.posts.PostsService;
import com.webservice.mytraveler.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null){
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null){
            model.addAttribute("loginUserName", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/retrieve/{id}")
    public String postsRetrieve(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        if (user != null){
            model.addAttribute("loginUserName", user.getName());
        }

        return "posts-retrieve";
    }
}

