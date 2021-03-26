package es.codeurjc.testunitario;



import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;



import org.junit.jupiter.api.Test;



import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookRepository;
import es.urjc.code.daw.library.book.BookService;
import es.urjc.code.daw.library.notification.NotificationService;

import org.springframework.stereotype.Component;


@Component
public class BookServiceTest extends BookTest {


	@Test

	public void saveBook() {
		
		//Given
     BookRepository bookRepository = mock(BookRepository.class);		
	 NotificationService notificationService = mock(NotificationService.class);
	 BookService bookService = new BookService(bookRepository ,notificationService);
     //When

	Book book = createBook("Ironman","historia.....");
	bookService.save(book);

		//Then
			
	verify(bookRepository).save(book);

	verify(notificationService).notify("Book Event: book with title="+book.getTitle()+" was created");


	}


	@Test
	public void deleteBook() {
		
		//Given
		 BookRepository bookRepository = mock(BookRepository.class);
         NotificationService notificationService = mock(NotificationService.class);
		 BookService bookService = new BookService(bookRepository ,notificationService);
        //When
		Book booke =createBook("Avatar","Aventura....");
		bookService.save(booke);
        bookService.delete(booke.getId());

		//Then
		verify(bookRepository).deleteById(booke.getId());
		
		verify(notificationService).notify("Book Event: book with id="+booke.getId()+" was deleted");
	}
}
