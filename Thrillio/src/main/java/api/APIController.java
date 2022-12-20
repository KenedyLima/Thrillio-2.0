package api;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Book;

@RestController
@RequestMapping("/api")
public class APIController {

	@GetMapping("/books/list")
	public Collection<Book> getBooks() {
		return null;
	}
	
}
