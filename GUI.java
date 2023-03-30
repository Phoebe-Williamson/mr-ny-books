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
        //UI.addButton("Find", this::findBook);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Add book to collection
     * 
     */
    public void addBook() {
        final int MAX_QUANTITY = 999;
        
        // Ask the user for details
        String name = UI.askString("Title: ");
        String author = UI.askString("Author: ");
        
        int quantity = UI.askInt("Quantity: ");     // this requires validation
        
        // add a book image for display in GUI
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        
        books.addBook(name, author, quantity, imgFileName);
    }
    
    /**
     * find book based on name
     * prints out the author, qty and book cover if found
     */
    public void findBook() {
        String bookName = UI.askString("Name of book: ");
        if (books.findBook(bookName)) {
        }
    }