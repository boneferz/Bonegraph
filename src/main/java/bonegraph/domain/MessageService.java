package bonegraph.domain;

import java.util.List;

public interface MessageService {
	
	List<Message> getAll();
	Message getById(Long id);
	Message save(Message message);
	void deleteById(Long id);
	void delete(Message message);
}
