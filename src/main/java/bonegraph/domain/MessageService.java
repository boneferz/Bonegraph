package bonegraph.domain;

import java.util.List;

public interface MessageService {
	
	List<Message> getAll();
	Message save(Message message);
	void deleteById(Long id);
}
