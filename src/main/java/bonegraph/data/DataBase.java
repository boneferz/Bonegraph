package bonegraph.data;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
	
	public static List<String> posts;
	
	static {
		posts = new ArrayList<>();
		posts.add("Hey");
		posts.add("To learn how to integrate Thymeleaf with Spring, " +
				"you can check out our main Spring article here – where " +
				"you can also learn how to display fields, accept input, " +
				"display validation errors, or convert data for display.");
		posts.add("You can also learn how to display fields.");
		posts.add(" ^ ^ ");
	}
}
