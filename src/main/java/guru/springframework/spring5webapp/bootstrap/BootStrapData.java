package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pb = new Publisher("Publisher1", "11822 Harbour Light", "North Royalton", "Ohio", "44133");
        publisherRepository.save(pb);

        Author eric = new Author("Eric", "Evans");
        Book dd = new Book("Domain Drivn Design", "123456");
        dd.setPublisher(pb);
        pb.getBooks().add(dd);
        eric.getBooks().add(dd);
        dd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(dd);
        publisherRepository.save(pb);
        Author rod = new Author("Rod", "Jhonson");
        Book noEJB = new Book("J2EE Development", "5678");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pb);
        pb.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pb);



        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Books: " + pb.getBooks().size());
    }
}
