package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.core.database.jpa.JpaBookRepository;
import lv.javaguru.java2.library.core.database.jpa.JpaReaderBookRepository;
import lv.javaguru.java2.library.core.database.jpa.JpaReaderRepository;
import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.domain.Reader;
import lv.javaguru.java2.library.core.domain.ReaderBook;
import lv.javaguru.java2.library.core.requests.TakeBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.TakeBookResponse;
import lv.javaguru.java2.library.core.services.validators.TakeBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class TakeBookService {

    @Autowired private TakeBookRequestValidator validator;
    @Autowired private JpaReaderRepository readerRepository;
    @Autowired private JpaBookRepository bookRepository;
    @Autowired private JpaReaderBookRepository readerBookRepository;


    public TakeBookResponse execute(TakeBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new TakeBookResponse(errors);
        }

        Reader reader = readerRepository.getReferenceById(request.getReaderId());
        Book book = bookRepository.getReferenceById(request.getBookId());

        ReaderBook readerBook = new ReaderBook();
        readerBook.setReader(reader);
        readerBook.setBook(book);
        readerBook.setBookOutDate(new Date());

        readerBookRepository.save(readerBook);

        return new TakeBookResponse(null);
    }

}
