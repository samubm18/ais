package es.codeurjc.testunitario;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import es.urjc.code.daw.library.book.Book;


public class BookTest {
	
	protected Book createBook(String name, String description) {
		
		Book book = mock(Book.class);
		when(book.getTitle()).thenReturn(name);
		when(book.getDescription()).thenReturn(description);
		
		return book;
	}
	
}
