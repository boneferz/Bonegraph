package bonegraph.controller;

import bonegraph.domain.Message;
import bonegraph.domain.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

import static bonegraph.data.Common.CHAT;

@Controller
public class DataBaseController {
	
	private final String CONTEXT = "/db/";
	
	@Autowired MessageService messageService;
	
	@GetMapping("/db/chat")
	public String chat(Model model) {
		List<Message> messages = messageService.getAll();
		Collections.reverse(messages);
		model.addAttribute("messages", messages);
		
		return CHAT;
	}
	
	@GetMapping("/db/delete")
	public String delete(Model model, @RequestParam Long index) {
		messageService.deleteById(index);
		return "redirect:" + CONTEXT + CHAT;
	}
	
	@PostMapping("/db/chat")
	public String addText(Model model, @RequestParam(value = "foo", required = false) String text) {
		
		if (text != null && text.trim().length() > 0) {
			if (text.length() > 450) {
				text = text.substring(0, 450);
			}
			// create
			Message newMessage = new Message();
			newMessage.setText(text);
			// save
			messageService.save(newMessage);
		}
		
		return "redirect:" + CONTEXT + CHAT;
	}
}
