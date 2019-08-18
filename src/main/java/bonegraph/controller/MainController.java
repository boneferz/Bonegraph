package bonegraph.controller;

import bonegraph.domain.Message;
import bonegraph.domain.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {
	
	List<Message> messages;
	@Autowired private MessageService messageService;
	
	@GetMapping({"/chat", "/"})
	public String chat(Model model) {
		messages = messageService.getAll();
		Collections.reverse(messages);
		
		model.addAttribute("messages", messages);
		return "/pages/chat";
	}
	
	@PostMapping(value = "/api/create", produces = "text/plain")
	public String create(Model model,
						 @RequestBody(required = false) String rBody) throws Exception {
		// client data
		String decoded = URLDecoder.decode(rBody, "UTF-8").trim();
		System.out.println("decoded = " + decoded);
		
		if (decoded.length() == 0) {
			throw new Exception("data empty");
		}
		// db
		Message newMessage = messageService.save(new Message(decoded));
		messages.add(0, newMessage);
		
		model.addAttribute("messages", Collections.singletonList(newMessage));
		return "/fragments/fragment-1 :: resultsList";
	}
	
	@ResponseBody
	@PostMapping(value = "/api/delete", produces = "text/plain")
	public String delete(@RequestBody(required = false) String rBody) {
		System.out.println("rBody: " + rBody);
		if (rBody != null && rBody.trim().length() != 0) {
			System.out.println(rBody);
			int deleteIdex = Integer.parseInt(rBody);
			messageService.delete(messages.remove(deleteIdex));
		}
		return String.valueOf(messages.size());
	}
}
