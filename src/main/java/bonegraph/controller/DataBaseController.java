package bonegraph.controller;

import bonegraph.domain.Message;
import bonegraph.domain.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static bonegraph.data.Common.*;

@Controller
public class DataBaseController {
	
	private final String CONTEXT = "/db/";
	private int foo = 123;
	@Autowired private MessageService messageService;
	
	@ResponseBody
	@GetMapping(value = "/greeting", produces = "application/json")
	public String greeting() {
		return "{\"id\":1,\"content\":\"Hello, World!\"}";
	}
	
	public String delete(Model model, @RequestParam Long index) {
		messageService.deleteById(index);
		
		return "redirect:" + CONTEXT + CHAT;
	}
	
	public String edit(Model model, @RequestParam Long index) {
		List<Message> messages = messageService.getAll();
		Collections.reverse(messages);
		Message currentMessage = messageService.getById(index);
		String currentText = currentMessage.getText();
		
		model.addAttribute("messages", messages);
		model.addAttribute("currentText", currentText);
		model.addAttribute("currentTextIndex", index);
		return CHAT_EDIT;
	}
	
	public String addText(Model model,
						  @RequestParam(value = "foo", required = false) String text) {
		text = validateText(text);
		
		if (text != null) {
			messageService.save(new Message(text));
		}
		
		System.out.println(foo);
		
		return "redirect:" + CONTEXT + CHAT;
	}
	
	public String editSave(Model model,
						   @RequestParam(value = "foo", required = false) String text,
						   @RequestParam Long index) {
		text = validateText(text);
		
		if (text != null) {
			Message editedMessage = messageService.getById(index);
			editedMessage.setText(text);
			messageService.save(editedMessage);
		}
		
		return "redirect:" + CONTEXT + CHAT;
	}
	
	private String validateText(String str) {
		if (str != null && str.trim().length() > 0) {
			if (str.length() > 450) {
				str = str.substring(0, 450);
			}
			return str;
		}
		return null;
	}
	
	public String showGuestList(Model model) {
		List<Message> messages = messageService.getAll();
		Collections.reverse(messages);
		
		model.addAttribute("messages", messages);
		
		return "fragment-1 :: resultsList";
	}
}
