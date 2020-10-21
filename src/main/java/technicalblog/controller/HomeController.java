package technicalblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import technicalblog.model.Post;
import technicalblog.service.PostService;



@Controller
public class HomeController {
	
	public HomeController() {

		System.out.println("*** HomeController ***");
    }

	@Autowired
	private PostService postservice;

	@RequestMapping("/")
	public String getAllPosts(Model model) {
		// TODO Auto-generated method stub
		List<Post> posts = postservice.getAllPosts();
		model.addAttribute("posts", posts);
		return "index";
	}

}



