import java.util.HashMap;
import ecs100.*;
/**
 * Holds collection of books in a hashmap
 * Allows a user to add, find, print all
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Books
{
    // instance variables
    private HashMap<Integer, Book> booksMap; // declare the hashmap
    private int currBookId;         // store the current id of book being added

    /**
     * Constructor for objects of class Books
     */
    public Books()
    {
        // initialise instance variables
        booksMap = new HashMap<Integer, Book>();    // initialise hashmap
        
        // Create some books
        Book b1 = new Book("Cellphone", "Stephen King", 86);
        Book b2 = new Book("Pet Semetary", "Stephen King", 68);
        Book b3 = new Book("1984", "George Orwell", 63);
        
        // Add books to collection
        booksMap.put(1, b1);
        booksMap.put(2, b2);
        booksMap.put(3, b3);
        
        this.currBookId = 3;        
    }

    /**
     * Adds book to hashmap
     */
    public void addBook(String nm, String auth, int qty, String img){
        currBookId++;   // increment book id
        booksMap.put(currBookId, new Book(nm, auth, qty, img));
    }
    
    /**
     * looks for book in hashMap
     * return boolean if found
     */
    public boolean findBook(String name) {
        for (int bookId : booksMap.keySet()) {
            if (booksMap.get(bookId).getName().equals(name)) // hashmap, key of hashmap, get the name of book
            {
                return true;
            }
        }
        return false;
    }
}