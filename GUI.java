import ecs100.*;
/**
 * Class to handle the GUI functionality
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables
    private Books books;
    private Book book;  // Store the current book instance

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        books = new Books();
        UI.initialise();
        //UI.addButton("All", books::printAll);
        UI.addButton("Add", this::addBook);
        UI.addButton("Find", this::findBook);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Add book to collection
     * 
     */
    public void addBook() {
        final int MAX_QUANTITY = 999;
        final int MIN_QUANTITY = 1;
        final int MIN_YEAR = 1700;
        final int MAX_YEAR = 2023;
        
        // Ask the user for details
        String name = UI.askString("Enter the book Title: ");
        String author = UI.askString("enter the Author: ");
        
        boolean getQty = true;
        int quantity = UI.askInt("Enter Quantity of books in the library: ");
        while (getQty){
            if (quantity > MAX_QUANTITY || quantity < MIN_QUANTITY) {
                quantity = UI.askInt("Enter Quantity of books in the library (between 1 - 999): ");
            } else{
                getQty = false;
            }
        }
        
        boolean getPgs = true;
        int pages = UI.askInt("Enter number of pages in the book: ");
        while (getPgs){
            if (pages > MAX_QUANTITY || pages < MIN_QUANTITY) {
                pages = UI.askInt("Enter the number of pages in the book (between 1 - 999): ");
            } else{
                getPgs = false;
            }
        }
        
        Boolean getYear = true;
        int years = UI.askInt("Enter the year thee book was published: ");
        while (getYear){
            if (years > MAX_YEAR || years < MIN_YEAR) {
                years = UI.askInt("Enter the year the book was published (between 1700 - 2023): ");
            } else{
                getYear = false;
            }
        }
        
        String genre = UI.askString("What is the genre of the book: ");
        
        // add a book image for display in GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        
        books.addBook(name, author, quantity, pages, years, genre, imgFileName);
    }
    
    /**
     * delet book from collection
     */
    
    /**
     * Finds book based on name
     * Prints out the author, qty and book cover if found
     */
    public void findBook() {
        String bookName = UI.askString("Name of book: ");
        if (books.findBook(bookName)) {
            UI.println("Found book!");
            book = books.getBook();
            UI.println("Author: " + book.getAuthor());
            UI.println("Quantity: " + book.getQuantity());
            book.displayBook(); // Show book cover
        } else {
            UI.println("Book not found!");
        }
        
    }
    
    /**
     * mouse listener
     */
}