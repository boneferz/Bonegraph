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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
	
	@Autowired private MessageService messageService;
	
	@GetMapping({"/chat", "/"})
	public String chat(Model model) {
		List<Message> messages = messageService.getAll();
		Collections.reverse(messages);
		
		model.addAttribute("messages", messages);
		return "/pages/chat";
	}
	
	@PostMapping(value = "/api/create", produces = "text/plain")
	public String create(Model model,
						 HttpServletResponse servletResponse,
						 @RequestBody(required = false) String rBody,
						 @RequestParam(required = false) String rParam) throws UnsupportedEncodingException {
		
		System.out.println("rBody = " + rBody);
		String decoded = URLDecoder.decode(rBody, "UTF-8");
		System.out.println("decoded = " + decoded);
		
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST");
		servletResponse.setHeader("Access-Control-Allow-Headers", "*");
		
		messageService.save(new Message(decoded));
		
		List<Message> messages = messageService.getAll();
		Collections.reverse(messages);
		List<Message> messagesOne = Collections.singletonList(messages.get(0));
		model.addAttribute("messages", messagesOne);
//		return decoded;
		return "/fragments/fragment-1 :: resultsList";
	}
}
