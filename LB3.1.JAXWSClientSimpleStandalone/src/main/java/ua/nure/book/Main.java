package ua.nure.book;

import ua.nure.book.entity.*;
import ua.nure.book.server.service.*;
import java.util.Scanner;

public class Main {
    private static final String CLIENT_TOKEN_VALID = "clientToken";
    private static String CLIENT_TOKEN = "clientToken";
    private static void printMenu() {
        System.out.println("1. Get Books\n" +
                "2. Add book\n" +
                "3. Remove book\n" +
                "4. Edit book\n" +
                "5. Take book\n" +
                "6. Exit\n");
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        BookService client = new Books().getBookPort();
        Scanner sc = new Scanner(System.in);
        printMenu();
        while (true) {
            int choise = Integer.valueOf(sc.nextLine());
            try {
                switch (choise) {
                    case 1 -> {
                        getBooks(client);
                        break;
                    }
                    case 2 -> {
                        addBook(client, sc);
                        break;
                    }
                    case 3 -> {
                        removeBook(client, sc);
                        break;
                    }
                    case 4 -> {
                        editBook(client, sc);
                        break;
                    }
                    case 5 -> {
                        takeBook(client, sc);
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

    private static void getBooks(BookService service) {
        var res = service.listBooks();
        for (var book : res) {
                System.out.println(book.toString());
            }
    }

    private static void addBook(BookService service, Scanner scanner) throws DAOException_Exception {
        var book = new Book();
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book body: ");
        String body = scanner.nextLine();

        book.setTitle(title);
        book.setBody(body);

        var author = createAuthor(scanner);
        book.setAuthor(author);
        var genre = createGenre(scanner);
        book.setGenre(genre);
        var request = service.addBook(book);
        System.out.println("Created book with id " + request);
    }
    private static Book takeBook(BookService service,Scanner scanner) throws DAOException_Exception {

        System.out.println("====Take a Book====");
        System.out.println("Enter book id: ");

        int id = Integer.parseInt(scanner.nextLine());
        var book = service.takeBook(id);
        if(book == null) {
            System.out.println("Book not exists");
        }
        System.out.println("Book with ID " + id + " has been successfully taken.");
        System.out.println(book.toString());
        return book;
    }

    private static void removeBook(BookService service,Scanner scanner) throws DAOException_Exception {
        System.out.println("====Book Removal====");
        System.out.println("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            service.removeBook(id);
            System.out.println("Book with ID " + id + " has been successfully removed.");
        } catch (DAOException_Exception e) {
            System.out.println("Error while removing the book: " + e.getMessage());
        }

    }
    private static void editBook(BookService service,Scanner scanner) throws DAOException_Exception {
        System.out.println("====Book edit====");
        System.out.println("Enter book id: ");

        int id = Integer.parseInt(scanner.nextLine());

        var book = service.takeBook(id);
        if(book == null) {
            System.out.println("Book not exists");
            return;
        }
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book body: ");
        String body = scanner.nextLine();

        if(!title.equals("")) {
            book.setTitle(title);
        }

        if(!body.equals("")) {
            book.setBody(body);
        }

        System.out.println("Update author?");
        if(scanner.nextLine().equals("y")) {
            var author = createAuthor(scanner);
            book.setAuthor(author);
        }

        System.out.println("Update genre?");
        if(scanner.nextLine().equals("y")) {
            var genre = createGenre(scanner);
            book.setGenre(genre);
        }
        var request = service.addBook(book);
        System.out.println("Created book with id " + request);
        try {
            service.editBook(book);
            System.out.println("Book with ID " + id + " has been successfully updated.");
        } catch (DAOException_Exception e) {
            System.out.println("Error while updating the book: " + e.getMessage());
        }

    }
    private static Genre createGenre(Scanner scanner) throws DAOException_Exception {
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

    private static Author createAuthor(Scanner scanner) {
        System.out.println("====Author====");

        var author = new Author();
        System.out.println("Enter author's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter author's place of birth: ");
        String placeOfBirth = scanner.nextLine();
        System.out.println("Enter author's biography: ");
        String biography = scanner.nextLine();
        System.out.println("Enter author's major work: ");
        String majorWork = scanner.nextLine();
        author.setName(name);
        author.setMajorWorks(majorWork);
        author.setBiography(biography);
        author.setPlaceOfBirth(placeOfBirth);

        return author;
    }

}
