package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;



public class MockBook {


    public Book mockEntity() {
    	return mockEntity(0);
    }
    
    public BookVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    private Book mockEntity(Integer number) {
    	Book book = new Book();
    	book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setId(number.longValue());
        book.setLaunch_date(new Date());
        book.setPrice(new Random().nextDouble());
        return book;
    }

    private BookVO mockVO(Integer number) {
    	BookVO book = new BookVO();
    	book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setKey(number.longValue());
        book.setLaunch_date(new Date());
        book.setPrice(new Random().nextDouble());
        
        return book;
    }

}
