import ecs100.*;
/**
 * Support class of Book.
 * A book contains an name, author, qty, image
 *
 * @author (Phoebe WIlliamosn)
 * @version (3/4/23)
 */

public class Book   {
  // instance variables
  private String name;
  private String author;
  private int quantity;
  private String image;
  private int pages;
  private int year;
  private String genre;
  private int locX = 50;
  private int locY = 50;
  private final double WIDTH = 220;
  private final double HEIGHT = 300;
  private static final String DEFAULT_IMAGE = "book.jpg";
    
  /**
  * Constructor for objects of class Book.
  */
  public Book(String nm, String auth, int qty, int pgs, 
              int year, String genre, String img) {
        // initialise instance variables
        this.name = nm;
        this.author = auth;
        this.quantity = qty;
        this.pages = pgs;
        this.year = year;
        this.genre = genre;
        
        // If the user selects cancel instead of giving image
    if (img == null) {
      this.image = DEFAULT_IMAGE;
    } else {
            this.image = img;
    }
  }
    
  /**
  * Constructor overloading for objects of class Book.
  */
  public Book(String nm, String auth, int qty, int pgs, int year, String genre) {
    // initialise instance variables
    this(nm, auth, qty, pgs, year, genre, null);
   }

  /**
  * Getter for name.
  * @return String the name of book
  */
  public String getName() {
    return this.name;
  }
    
  /**
  * Display image on GUI.
  */
  public void displayBook() {
    UI.drawImage(this.image, this.locX, this.locY, this.WIDTH, this.HEIGHT);
  }
    
  public String getAuthor() {
    return this.author;
  }
    
  public int getQuantity() {
    return this.quantity;
  } 
    
  public int getPages() {
    return this.pages;
  }
  
    /**
     * getter for the year.
     */
  public int getYear(){
    return this.year;
  }
  
    /**
     * getter for the genre.
     */
  public String getGenre(){
    return this.genre;
  }
  
    /**
     * getter for the image.
     */
  public String getImage() {
    return this.image;
  }
    
  public boolean isOnBook(double x, double y) {
    if ((x >= locX) && (x <= locX + WIDTH) && ( y >= locY) && ( y <= locY + HEIGHT)){
        return true;
    } else {
        return false;
    }
  }
}