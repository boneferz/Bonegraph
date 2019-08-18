package bonegraph.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
	
	List<Message> findAllByOrderById();
}
