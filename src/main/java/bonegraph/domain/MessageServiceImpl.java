package bonegraph.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired private MessageRepository messageRepository;
	
	@Override
	public List<Message> getAll() {
		return (List<Message>) messageRepository.findAll();
	}
	
	@Override
	public Message getById(Long id) {
		return messageRepository.findById(id).orElse(null);
	}
	
	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}
	
	@Override
	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}
	
}
