import java.util.HashMap;
import ecs100.*;
/**
 * Holds collection of books in a hashmap.
 * Allows a user to add, find, print all
 *
 * @author (Phoebe Williamosn)
 * @version (3/4/23)
 */
public class Books {
    // instance variables
    private HashMap<Integer, Book> booksMap; // declare the hashmap
    private int currBookId;         // store the current id of book being added
    private Book currBook;          // store the instance of the found book

    /**
     * Constructor for objects of class Books.
     */
    public Books()
    {
        // initialise instance variables
        booksMap = new HashMap<Integer, Book>();    // initialise hashmap
        
        // Create some books
        Book b1 = new Book("CELLPHONE", "STEPHEN KING",3, 86, 2016, "HORROR");
        Book b2 = new Book("PET CEMETARY", "STEPHEN KING", 3, 68, 1903, "HISTORY");
        Book b3 = new Book("1984", "GEORGE ORWELL", 3, 63, 1949, "HISTORY");
        
        // Add books to collection
        booksMap.put(1, b1);
        booksMap.put(2, b2);
        booksMap.put(3, b3);
        
        this.currBookId = 3;        
    }

    /**
     * Adds book to hashmap.
     */
    public void addBook(String nm, String auth, int qty, int pgs, int year, 
                        String genre,  String img) {
        currBookId++;   // increment book id
        booksMap.put(currBookId, new Book(nm, auth, qty, pgs, year, genre, img));
    }
    
    /**
     * Looks for book in hashmap.
     * @return boolean if found
     */
    public boolean findBook(String name) {
        for (int bookId : booksMap.keySet()) {
            if (booksMap.get(bookId).getName().equals(name)) {
                currBook = booksMap.get(bookId);
                return true;
            }
        }
        return false; // if not found
    }
    
    /**
     * prints all books in hashmap.
     */
    public void printAll() {
        UI.println("Here are all of the books in the library");
        for (int bookId : booksMap.keySet()) {
            currBook = booksMap.get(bookId);
            UI.println("-------------------");
            UI.println("Title: " + currBook.getName());
            UI.println("Author: " + currBook.getAuthor());
            UI.println("Quantity: " + currBook.getQuantity());
            UI.println("Pages: " + currBook.getPages());
            UI.println("Year Published: " + currBook.getYear());
            UI.println("Genre: " + currBook.getGenre());
        }
    }
    
    /**
     * Getter for current Book.
     * @return Book the found book instance
     */
    public Book getBook() {
        return this.currBook;
        
    }
    
    /**
     * rmoves book from hasmap.
     */
    public void removeBook(String name) {
        for (int BookID : booksMap.keySet()) {
            if (booksMap.get(BookID).getName().toUpperCase().equals(name)) {
                booksMap.remove(BookID);
            }
        }
    }
}