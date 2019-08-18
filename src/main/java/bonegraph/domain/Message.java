package bonegraph.domain;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	
	public Message(String text) {
		this.text = text;
	}
	
	public Message() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "Message {" +
				"id=" + id +
				", text='" + text + '\'' +
				'}';
	}
}
