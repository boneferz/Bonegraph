package bonegraph.controller;

import bonegraph.data.DataBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
	
	@Value("${props.data}")
	private String message;
	
	private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
	
	@GetMapping(path = {"/", "/index"})
	public String index(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("tasks", tasks);
		
		return "index";
	}
	
	@GetMapping("/app")
	public String app(Model model) {
		return "app";
	}
	
	@GetMapping("/chat")
	public String chat(Model model) {
		model.addAttribute("messages", DataBase.posts);
		return "chat";
	}
	
	@PostMapping("/chat")
	public String addText(Model model,
						  @RequestParam(value="foo", required = false) String text) {
		System.out.println("text = " + text);
		
		if (text != null) {
			DataBase.posts.add(0, text);
		}
		
		model.addAttribute("messages", DataBase.posts);
		return "chat";
	}
}
