package cc.boneferz.bonegraph.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
