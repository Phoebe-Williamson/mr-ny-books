import ecs100.*;
/**
 * Class to handle the GUI functionality.
 *
 * @author (Phoebe Williamsomn)
 * @version (3/4/23)
 */

public class GUI {
  // instance variables
  private Books books;
  private Book book;  // Store the current book instance

  /**
  * Constructor for objects of class GUI.
  */
  public GUI() {
    // initialise instance variables
    books = new Books();
    UI.initialise();
    UI.setMouseListener(this::doMouse);
    
    //UI.addButton("All", books::printAll);
    UI.addButton("Add", this::addBook);
    UI.addButton("Find by title", this::findBook);
    UI.addButton("Print all books", this::printBooks);
    UI.addButton("Delete book", this::deleteBook);
    UI.addButton("Change quantity of books", this::changeQty);
    UI.addButton("Clear text", this::clearAll);
    UI.addButton("Quit", UI::quit);

    UI.println("Welcome to the book manager. \nClick a button to start your advernture!");
    }
    
  /**
  * clears the screen.
  */
  public void clearAll() {
    UI.clearText();
    UI.clearGraphics();  
    book = null;
   }

  /**
  * Add book to collection.
  * 
  */
  public void addBook() {
    final int MAX_QUANTITY = 999;
    final int MIN_QUANTITY = 1;
    final int MIN_YEAR = 1;
    final int MAX_YEAR = 2023;
    String[] genres = {"ROMANCE", "HORROR", "COMEDY", "SCIFI", "HISTORICAL FICTION", "NON FICTION", 
                       "MYSTERY", "CHILDRENS", "ACTION", "NATURE", "POETRY"};
        
    // Ask the user for detail
    boolean getName = true;
    boolean addMore = true;
    // asks the book title and puts the input to upper case to check in the hashmap
    String bookName = UI.askString("Enter the book Title: ").toUpperCase();
    while (getName) {
        // checks to see if the book is in the library
      if (books.findBook(bookName))  { 
         // the book is in the hashmap so asks if the author is correct for the book
         book = books.getBook();
         String question = UI.askString("Is the author of the book "
                                        + book.getAuthor() + " (Y/N): ").toUpperCase();
         if (question.equals("Y")) {
             // if it is the same author as shown above, asks if they want to change the quantity
             String qty = UI.askString("The book is already in the library, Would you like ot change the quantity instead (Y/N): ").toUpperCase();
             if (qty.equals("Y")) {
                 // if the want to change the quantity, the new quantity is asked for
                 boolean getQty = true;
                 int quantity = UI.askInt("Enter Quantity of books in the library: ");
                 while (getQty) {
                        if (quantity > MAX_QUANTITY || quantity < MIN_QUANTITY) {
                            quantity = UI.askInt("Enter Quantity of books in numbers in the library (between 1 - 999): ");
                         }else {
                            getQty = false;
                         }
                     }
                
                     UI.println(book.getQuantity());
                     book.setQuantity(quantity);
                     UI.println(book.getQuantity());
                     
                     
                 String name =  book.getName();
                 String author = book.getAuthor();
                 int pages = book.getPages();
                 int years = book.getYear();
                 String genre = book.getGenre();
                 String imgFileName = book.getImage();
                 books.removeBook(bookName);
            
                 books.addBook(name, author, quantity, pages, years, genre, imgFileName); 
             } else if (qty.equals("N")) {
                 
                 
                 break;
             } else {
                UI.askString("That is not a valid option, enter 'Y' for yes or 'N' for no: ").toUpperCase(); 
             }
         }else if (question.equals("N")) {
             // if they dont want ot chnage the quantity of the book, the loop breaks
             addMore = false;
             break;
         }else{
             UI.askString("That is not a valid option, enter 'Y' for yes or 'N' for no: ").toUpperCase();
         }
      } else if(bookName.equals("")){
          bookName = UI.askString("Enter the book title: ").toUpperCase();
      }else {
          getName = false;
       }
     }
        
    // boolean getAuthor = true;
    // String author = UI.askString("Enter the Author: ").toUpperCase();
    // while(getAuthor) {
        // if(author.equals("")) {
            // author = UI.askString("Enter the Author: ").toUpperCase();
         // } else {
            // getAuthor = false;
         // }
    // }
    if (addMore == true) {
    String author = addAuthor();
        
    boolean getQty = true;
    int quantity = UI.askInt("Enter Quantity of books in the library: ");
    while (getQty) {
        if (quantity > MAX_QUANTITY || quantity < MIN_QUANTITY) {
            quantity = UI.askInt("Enter Quantity of books in numbers in the library (between 1 - 999): ");
         }else {
            getQty = false;
         }
     }
        
    boolean getPgs = true;
    int pages = UI.askInt("Enter number of pages in the book: ");
    while (getPgs){ 
        if (pages > MAX_QUANTITY || pages < MIN_QUANTITY) {
            pages = UI.askInt("Enter the number of pages in the book (between 1 - 999): ");
         } else {
            getPgs = false;
         }
     }
        
    Boolean getYear = true;
    int years = UI.askInt("Enter the year the book was published: ");
    while (getYear) {
        if (years > MAX_YEAR || years < MIN_YEAR) {
            years = UI.askInt("Enter the year the book was published (between 1 - 2023): ");
        } else {
            getYear = false;
         }
     }
        
    boolean getGenre = true;
    UI.println("Pick from one of these genres: ROMANCE, HORROR, COMEDY, SCIFI,");
    UI.println("HISTORICAL FICTION, NON FICTION, MYSTERY, CHILDRENS, ACTION, NATURE, POETRY");
    String genre = UI.askString("What is the genre of the book: ").toUpperCase();
    while (getGenre) {
        for (String g : genres) {
            if (genre.equals(g)) {
                getGenre = false;
             }
         }
        if (getGenre == true) {
                genre = UI.askString("Please enter the genre of the book from the options above").toUpperCase();
         }
     }

    // add a book image for display in GUI
    String imgFileName = UIFileChooser.open("Choose Image File: ");
    books.addBook(bookName, author, quantity, pages, years, genre, imgFileName);
    }
   }
    
   /**
    * Check the input is valid and return it
    */
   public String addAuthor() {
           boolean getAuthor = true;
    String author = UI.askString("Enter the Author: ").toUpperCase();
    while(getAuthor) {
        if(author.equals("")) {
            author = UI.askString("Enter the Author: ").toUpperCase();
         } else {
            getAuthor = false;
         }
    }
    return author;
   }
  /**
  * delete book from collection.
  */ 
  public void deleteBook() {
    final int MAX_QUANTITY = 999;
    final int MIN_QUANTITY = 1;

    // asks the name of the book that the user wants to change the quantity of
    String bookName = UI.askString("Name of book: ").toUpperCase();
    if (books.findBook(bookName)) {
        UI.println("-------------------");
        UI.println("Found book!");
        book = books.getBook();
        books.removeBook(bookName);
        UI.println("Books has been removed.");
     } else {
        UI.println("book not found");
     }
    }
    
  /**
  * change the quantity of the number of books in library.
  */
  public void changeQty() {
    final int MAX_QUANTITY = 999;
    final int MIN_QUANTITY = 1;
        
    // asks the name of the book that the user wants to change the quantity of
    String bookName = UI.askString("Name of book you want to change quantity: ").toUpperCase();
    if (books.findBook(bookName)) {
        UI.println("-------------------");
        UI.println("Found book!");
        book = books.getBook();

        // asks for the new quantity
        boolean getQty = true;
        int quantity = UI.askInt("Enter Quantity of books in the library: ");
        while (getQty) {
            if (quantity > MAX_QUANTITY || quantity < MIN_QUANTITY) {
                quantity = UI.askInt("Enter Quantity of books in numbers in the library (between 1 - 999): ");
             } else if (quantity == book.getQuantity()) {
                UI.println("That is the old quantity");
                quantity = UI.askInt("Enter a new quantity: ");
             } else {
                getQty = false;
             }
         }
            
        String name=  book.getName();
        String author = book.getAuthor();
        int pages = book.getPages();
        int years = book.getYear();
        String genre = book.getGenre();
        String imgFileName = book.getImage();
        books.removeBook(bookName);
            
        books.addBook(name, author, quantity, pages, years, genre, imgFileName);
     }else {
        UI.println("book not found");
     }
    }
    
    
  /**
  * Finds book based on name.
  * Prints out the author, qty and book cover if found.
  */
  public void findBook() {
    String bookName = UI.askString("Name of book: ").toUpperCase();
    if (books.findBook(bookName)) {
        UI.println("-------------------");
        UI.println("Found book!");
        book = books.getBook();
        UI.println("Author: " + book.getAuthor());
        UI.println("Quantity: " + book.getQuantity());
        UI.println("Pages: " + book.getPages());
        UI.println("Year Published: " + book.getYear());
        UI.println("Genre: " + book.getGenre());
        book.displayBook(); // Show book cover
    } else {
        UI.println("Book not found!");
     }
    }
    
  /**
  * Finds book based on name.
  * Prints out the author, qty and book cover if found.
  */
  public void findBookAuth() {
    String bookAuth = UI.askString("Name of book: ").toUpperCase();
    if (books.findBook(bookAuth)) {
        UI.println("-------------------");
        UI.println("Found book!");
        book = books.getBook();
        UI.println("Author: " + book.getAuthor());
        UI.println("Quantity: " + book.getQuantity());
        UI.println("Pages: " + book.getPages());
        UI.println("Year Published: " + book.getYear());
        UI.println("Genre: " + book.getGenre());
        book.displayBook(); // Show book cover
    } else {
        UI.println("Book not found!");
     }
    }
    
  /**
  * mouse listener.
  */
  private void doMouse(String action, double x, double y) {
    if (action.equals("clicked")) {
        if (book != null){
            if (book.isOnBook(x,y)) {
                UI.println("-------------------");
                UI.println("Title: " + book.getName());
                UI.println("Author: " + book.getAuthor());
                UI.println("Quantity: " + book.getQuantity());
                UI.println("Pages: " + book.getPages());
                UI.println("Year Published: " + book.getYear());
                UI.println("Genre: " + book.getGenre());
             }
         }
     }
    }
    
  /**
  * calls method in books to print out all books.
  */
  public void printBooks() {  
    books.printAll();
   }   
}