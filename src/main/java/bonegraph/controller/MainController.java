package bonegraph.controller;

import bonegraph.data.DataBase;
import bonegraph.domain.Human;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
	
	public static String CHAT = "chat";
	public static String INDEX = "index";
	public static String APP = "app";
	
	private String name ="ElonMask";
	private int counter = 0;
	
	private Human robert;
	
	@Value("${props.data}")
	private String message;
	
	@GetMapping(path = {"/", "/index"})
	public String index(Model model) {
		return INDEX;
	}
	
	@GetMapping("/app")
	public String app(Model model) {
		return APP;
	}
	
	@GetMapping("/chat")
	public String chat(Model model) {
		model.addAttribute("messages", DataBase.posts);
		model.addAttribute("name", name);
		model.addAttribute("counter", counter);
		model.addAttribute("messagesSize", DataBase.posts.size());
		
		robert = new Human("Roberto", 28, true);
		
		model.addAttribute("human", robert);
		return CHAT;
	}
	
	@GetMapping("/clear")
	public String clear(Model model, @RequestParam int index) {
		DataBase.posts.set(index, "-empty-");
		return "redirect:" + CHAT;
	}
	
	@GetMapping("/delete")
	public String delete(Model model, @RequestParam int index) {
		DataBase.posts.remove(index);
		return "redirect:" + CHAT;
	}
	
	@PostMapping("/chat")
	public String addText(Model model, @RequestParam(value="foo", required = false) String text) {
		
		if (text.trim() != null && text.length() > 0) {
			if(text.length() > 450) {
				text = text.substring(0, 450);
			}
			
			DataBase.posts.add(0, text);
		}
		
		model.addAttribute("messages", DataBase.posts);
		return "redirect:" + CHAT;
	}
}
