/**
 * Represents a book with various attributes such as ISBN, authors, publication year, original title, title, and average rating.
 * Provides methods to access and modify these attributes as needed.
 */
public class Book {

    private String isbn;
    private String authors;
    private int publicationYear;
    private String originalTitle;
    private String title;
    private double averageRating;

    /**
     * Constructor to create a new Book instance with all its details.
     *
     * @param isbn            The International Standard Book Number of the book.
     * @param authors         The author(s) of the book.
     * @param publicationYear The year the book was published.
     * @param originalTitle   The original title of the book.
     * @param title           The title of the book.
     * @param averageRating   The average rating of the book on a scale from 0 to 5.
     */
    public Book(String isbn, String authors, int publicationYear, String originalTitle, String title, double averageRating) {
        this.isbn = isbn;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.averageRating = averageRating;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The new ISBN of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the authors of the book.
     *
     * @return The authors of the book.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Sets the authors of the book.
     *
     * @param authors The new authors of the book.
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Gets the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param publicationYear The new publication year of the book.
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets the original title of the book.
     *
     * @return The original title of the book.
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * Sets the original title of the book.
     *
     * @param originalTitle The new original title of the book.
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the average rating of the book.
     *
     * @return The average rating of the book.
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the average rating of the book.
     *
     * @param averageRating The new average rating of the book.
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Returns a string representation of the book with all its details.
     *
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", publicationYear=" + publicationYear +
                ", originalTitle='" + originalTitle + '\'' +
                ", title='" + title + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }
}
