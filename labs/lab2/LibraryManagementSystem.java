import java.util.*;

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() { isAvailable = false; }
    public void returnBook() { isAvailable = true; }
}

// Member/Student class
class Member {
    private String id;
    private String name;
    private String contact;
    private List<Book> borrowedBooks;

    public Member(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }
}

// Library class
class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Book operations
    public void addBook(Book book) { books.add(book); }
    public void removeBook(String isbn) { books.removeIf(b -> b.getIsbn().equals(isbn)); }
    public Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }
    public List<Book> searchBooks(String keyword) {
        if (keyword.isEmpty()) return new ArrayList<>(books);
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getIsbn().contains(keyword)) {
                results.add(b);
            }
        }
        return results;
    }
    public List<Book> getBooks() { return books; }

    // Member operations
    public void registerMember(Member member) { members.add(member); }
    public void removeMember(String id) { members.removeIf(m -> m.getId().equals(id)); }
    public Member findMemberById(String id) {
        for (Member m : members) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }
    public List<Member> getMembers() { return members; }

    // Borrow/return operations
    public void borrowBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);

        if (member == null) {
            System.out.println("\n⚠️  Member not found.");
        } else if (book == null) {
            System.out.println("\n⚠️  Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("\n⚠️  Book is already borrowed.");
        } else {
            member.borrowBook(book);
            System.out.println("\n✅ " + member.getName() + " borrowed \"" + book.getTitle() + "\"");
        }
    }

    public void returnBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);

        if (member == null || book == null) {
            System.out.println("\n⚠️  Member or book not found.");
        } else if (!member.getBorrowedBooks().contains(book)) {
            System.out.println("\n⚠️  This member did not borrow this book.");
        } else {
            member.returnBook(book);
            System.out.println("\n✅ " + member.getName() + " returned \"" + book.getTitle() + "\"");
        }
    }
}

// Main Program with Console UI
public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> registerMember();
                case 4 -> removeMember();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> searchBooks();
                case 8 -> displayBooks(library.getBooks());
                case 9 -> displayMembers(library.getMembers());
                case 0 -> { running = false; System.out.println("\n👋 Goodbye!"); }
                default -> System.out.println("\n⚠️  Invalid choice.");
            }
        }
    }

    // UI Printing
    private static void printMenu() {
        System.out.println("\n=====================================");
        System.out.println("       📚 LIBRARY MANAGEMENT MENU    ");
        System.out.println("=====================================");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Register Member");
        System.out.println("4. Remove Member");
        System.out.println("5. Borrow Book");
        System.out.println("6. Return Book");
        System.out.println("7. Search Books");
        System.out.println("8. Display All Books");
        System.out.println("9. Display All Members");
        System.out.println("0. Exit");
        System.out.println("=====================================");
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("\n⚠️  No books available.\n");
            return;
        }
        System.out.println("\n================= BOOKS =================");
        System.out.printf("%-20s %-15s %-10s %-6s %-12s%n",
                "Title", "Author", "ISBN", "Year", "Status");
        System.out.println("---------------------------------------------------------------");
        for (Book b : books) {
            System.out.printf("%-20s %-15s %-10s %-6d %-12s%n",
                    b.getTitle(), b.getAuthor(), b.getIsbn(),
                    b.getPublicationYear(), b.isAvailable() ? "Available" : "Borrowed");
        }
        System.out.println("===========================================\n");
    }

    private static void displayMembers(List<Member> members) {
        if (members.isEmpty()) {
            System.out.println("\n⚠️  No members registered.\n");
            return;
        }
        System.out.println("\n=============== MEMBERS =================");
        System.out.printf("%-8s %-20s %-15s %-10s%n",
                "ID", "Name", "Contact", "Borrowed");
        System.out.println("---------------------------------------------------");
        for (Member m : members) {
            System.out.printf("%-8s %-20s %-15s %-10d%n",
                    m.getId(), m.getName(), m.getContact(),
                    m.getBorrowedBooks().size());
        }
        System.out.println("==========================================\n");
    }

    // Menu Actions
    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        int year = getIntInput("Enter publication year: ");

        library.addBook(new Book(title, author, isbn, year));
        System.out.println("\n✅ Book added successfully.");
    }

    private static void removeBook() {
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("\n✅ Book removed successfully.");
    }

    private static void registerMember() {
        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();

        library.registerMember(new Member(id, name, contact));
        System.out.println("\n✅ Member registered successfully.");
    }

    private static void removeMember() {
        System.out.print("Enter member ID to remove: ");
        String id = scanner.nextLine();
        library.removeMember(id);
        System.out.println("\n✅ Member removed successfully.");
    }

    private static void borrowBook() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter ISBN of book: ");
        String isbn = scanner.nextLine();
        library.borrowBook(memberId, isbn);
    }

    private static void returnBook() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter ISBN of book: ");
        String isbn = scanner.nextLine();
        library.returnBook(memberId, isbn);
    }

    private static void searchBooks() {
        System.out.print("Enter keyword (title, author, or ISBN): ");
        String keyword = scanner.nextLine();
        List<Book> results = library.searchBooks(keyword);
        if (results.isEmpty()) {
            System.out.println("\n⚠️  No books found.");
        } else {
            displayBooks(results);
        }
    }

    // Utility
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Invalid number, try again.");
            }
        }
    }
}