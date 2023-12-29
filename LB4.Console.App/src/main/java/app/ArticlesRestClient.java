package app;

import app.additions.ReturnsAtomic;
import app.entity.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import article.service.Constants;
import article.service.rest.JAXBContextProvider;
import article.service.rest.JSONMessageBodyReader;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ArticlesRestClient extends Application {

    private static final URI BASE_URI = URI.create(Constants.SERVICE_BASE_URL);

    private static void printMenu() {
        System.out.println("1. Get books\n" +
            "2. Add book\n" +
            "3. Remove book\n" +
            "4. Edit book\n" +
            "5. TakeBook\n" +
            "6. Exit\n");
    }

    private static void getBooksInfo(Client client) throws Exception {
        WebTarget target = client.target(BASE_URI);
        Builder request = target.request()
                .accept(MediaType.APPLICATION_JSON);
        Response resp = request.get();
        if (Response.Status.OK.getStatusCode() == resp.getStatus()) {
            var books = resp.readEntity(Book[].class);
            for (Book book : books) {
                System.out.println(book.toString());
            }
        } else {
            throw new Exception("Error with code " + resp.getStatus());
        }
    }

    private static void addBook(Client client) throws Exception {
        Scanner scanner = new Scanner(System.in);
        var book = new Book();
        System.out.println("====Book====");
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book body: ");
        String body = scanner.nextLine();

        var author = createAuthorForBook();
        var genre = createGenreForBook();

        book.setAuthor(author);
        book.setGenre(genre);
        book.setTitle(title);
        book.setBody(body);

        WebTarget target = client.target(BASE_URI + "/addBook");
        Builder request = target.request()
                .accept(MediaType.APPLICATION_JSON);
        jakarta.ws.rs.client.Entity<Book> payload = jakarta.ws.rs.client.Entity.json(book);
        Response resp = request.post(payload);
        if (Response.Status.OK.getStatusCode() == resp.getStatus()) {
            var body1 = resp.readEntity(ReturnsAtomic.class);
            System.out.println("id = " + body1.getItem());
        } else {
            throw new Exception("Error with code " + resp.getStatus());
        }
    }

    private static Author createAuthorForBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====Author====");

        var author = new Author();
        System.out.println("Enter author's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter author's place of birth: ");
        String placeOfBirth = scanner.nextLine();
        System.out.println("Enter author's biography: ");
        String biography = scanner.nextLine();
        System.out.println("Enter author's major works: ");
        String majorWorks = scanner.nextLine();

        author.setName(name);
        author.setPlaceOfBirth(placeOfBirth);
        author.setBiography(biography);
        author.setMajorWorks(majorWorks);
        author.setId(1);

        return author;
    }

    private static Genre createGenreForBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====Genre====");

        var genre = new Genre();
        System.out.println("Enter genre name: ");
        String genreName = scanner.nextLine();

        System.out.println("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        genre.setBookId(id);
        genre.setGenreName(genreName);

        return genre;
    }

    private static Book takeBook(Client client) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("====Take a Book====");
        System.out.println("Enter book id: ");

        int id = Integer.parseInt(scanner.nextLine());
        WebTarget target = client.target(BASE_URI).path("search").queryParam("pattern", id);
        Builder request = target.request()
                .accept(MediaType.APPLICATION_JSON);
        Response resp = request.get();
        if (Response.Status.OK.getStatusCode() == resp.getStatus()) {
            var body = resp.readEntity(Book.class);
            System.out.println(body.toString());
            return body;
        } else {
            throw new Exception("Error with code " + resp.getStatus());
        }
    }

    private static void removeBook(Client client) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====Book Removal====");
        System.out.println("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        WebTarget target = client.target(BASE_URI + "/removeBook");
        Builder request = target.request()
                .accept(MediaType.APPLICATION_JSON);
        jakarta.ws.rs.client.Entity<Integer> payload = jakarta.ws.rs.client.Entity.json(id);
        Response resp = request.post(payload);
        if (Response.Status.NO_CONTENT.getStatusCode() == resp.getStatus()) {
            System.out.println("Sicces");
        } else {
            throw new Exception("Error with code " + resp.getStatus());
        }
    }
    private static void editBook(Client client) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====Book edit====");
        System.out.println("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        WebTarget getTarget = client.target(BASE_URI+ "/takeBook");
        Builder getRequest = getTarget.request().accept(MediaType.APPLICATION_JSON);
        jakarta.ws.rs.client.Entity<Integer> getPayload = jakarta.ws.rs.client.Entity.json(id);
        Response getResp = getRequest.post(getPayload);
        if (Response.Status.OK.getStatusCode() == getResp.getStatus()) {
            // Получаем существующую книгу
            var existingBook = getResp.readEntity(Book.class);
            System.out.println("Current Book Details:\n" + existingBook.toString());

            // Запрос на внесение изменений
            System.out.println("Enter new title: ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                existingBook.setTitle(newTitle);
            }
            System.out.println("Enter new body: ");
            String newBody = scanner.nextLine();
            if (!newBody.isEmpty()) {
                existingBook.setBody(newBody);
            }

            System.out.println("Enter new author: ");
            Author newAuthor = new Author();
            newAuthor = createAuthorForBook();
            existingBook.setAuthor(newAuthor);
            System.out.println("Enter new genre: ");
            Genre newGenre = new Genre();
            newGenre = createGenreForBook();

            WebTarget editTarget = client.target(BASE_URI +"/editBook");
            Builder editRequest = editTarget.request().accept(MediaType.APPLICATION_JSON);
            jakarta.ws.rs.client.Entity<Book> editPayload = jakarta.ws.rs.client.Entity.json(existingBook);
            Response editResp = editRequest.post(editPayload);

            if (Response.Status.NO_CONTENT.getStatusCode() == editResp.getStatus()) {
                System.out.println("Book with id " + id + " has been successfully updated.");
            } else {
                throw new Exception("Error with code " + editResp.getStatus());
            }
        } else {
            throw new Exception("Error with code " + getResp.getStatus());
        }
    }

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        client
            .register(JSONMessageBodyReader.class)
            .register(JAXBContextProvider.class);

        Scanner sc = new Scanner(System.in);
        printMenu();
        while (true) {
            int choise = Integer.valueOf(sc.nextLine());
            try {
                switch (choise) {
                    case 1 -> {
                        getBooksInfo(client);
                        break;
                    }
                    case 2 -> {
                        addBook(client);
                        break;
                    }
                    case 3 -> {
                        removeBook(client);
                        break;
                    }
                    case 4 -> {
                        editBook(client);
                        break;
                    }
                    case 5 -> {
                        takeBook(client);
                        break;
                    }
                    case 6 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid option");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("[Error] " + e.getMessage());
                e.printStackTrace();
            }
            printMenu();
        }
    }

    private static GregorianCalendar convertToGregorianCalendar(String dateString) throws ParseException, DatatypeConfigurationException {
        SimpleDateFormat dateFormat;

        if (dateString.length() <= 10) { // If input contains only date (without time)
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        Date date = dateFormat.parse(dateString);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        return cal;
    }

    private static XMLGregorianCalendar convertToXMLGregorianCalendar(GregorianCalendar calendar) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }
}
