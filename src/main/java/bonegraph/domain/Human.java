package bonegraph.domain;

public class Human {
	
	private String name;
	private int age;
	private Boolean money;
	
	public Human() {
	}
	
	public Human(String name, int age, Boolean money) {
		this.name = name;
		this.age = age;
		this.money = money;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Boolean getMoney() {
		return money;
	}
	
	public void setMoney(Boolean money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "Human {" +
				"name='" + name + '\'' +
				", age=" + age +
				", money=" + money +
				'}';
	}
}
