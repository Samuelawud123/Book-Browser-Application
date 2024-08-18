import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
/**
 * The `BookBrowser` class represents a simple Swing-based book browser application.
 * It allows users to browse through a collection of books, displaying book details based on different ordering criteria.
 */
public class BookBrowser extends JFrame {

    private JTextField bnField, authors, year, originalTitle, title, avgRating;
    private JComboBox<String> comboBox;
    private JButton button1, button2, button3, button4;
    private final HashMap<String, TreeMap<String, Book>> fieldMaps;
    private TreeMap<String, Book> currentTreeMap;
    private int currentIndex;
    private List<String> currentKeys;
    
    /**
     * Constructs a new instance of the `BookBrowser` class.
     * Initializes components, loads books from a data file, and sets up the user interface.
     */
    public BookBrowser() {
        fieldMaps = new HashMap<>();
        initializeComponents();
        layoutComponents();
        loadBooks();
        updateDisplay("ISBN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Book Browser");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Initializes Swing components such as buttons, text fields, and combo boxes.
     */
    private void initializeComponents() {
        String[] orders = {"ISBN", "Authors", "Publication Year", "Original Title", "Title", "Average Rating"};
        comboBox = new JComboBox<>(orders);
        comboBox.setFont(new Font("SansSerif", Font.BOLD, 12));
        comboBox.addActionListener(this::onFieldChanged);

        bnField = new JTextField(10);
        authors = new JTextField(10);
        year = new JTextField(10);
        originalTitle = new JTextField(10);
        title = new JTextField(10);
        avgRating = new JTextField(10);

        button1 = new JButton("|<-");
        button2 = new JButton("<Prev");
        button3 = new JButton("Next>");
        button4 = new JButton("->|");

        button1.addActionListener(e -> navigate("first"));
        button2.addActionListener(e -> navigate("prev"));
        button3.addActionListener(e -> navigate("next"));
        button4.addActionListener(e -> navigate("last"));
    }
    /**
     * Defines the layout of Swing components within the JFrame.
     */
    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(10, 5, 10, 5);
        g.gridx = 0;
        g.gridy = 1;
        add(new JLabel("Order:"), g);
        g.gridx = 1;
        add(comboBox, g);

        addLabelAndTextField("ISBN:", bnField, ++g.gridy);
        addLabelAndTextField("Authors:", authors, ++g.gridy);
        addLabelAndTextField("Publication Year:", year, ++g.gridy);
        addLabelAndTextField("Original Title:", originalTitle, ++g.gridy);
        addLabelAndTextField("Title:", title, ++g.gridy);
        addLabelAndTextField("Average Rating:", avgRating, ++g.gridy);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        g.gridy++;
        g.gridwidth =1;
        add(buttonPanel, g);
    }

    /**
     * Adds a JLabel and a JTextField to the JFrame with specified layout constraints.
     *
     * @param labelText The label text.
     * @param textField The JTextField component.
     * @param gridy     The grid y-coordinate for layout.
     */
    private void addLabelAndTextField(String labelText, JTextField textField, int gridy) {
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(10, 5, 10, 5);
        g.gridx = 0;
        g.gridy = gridy;
        add(new JLabel(labelText), g);
        g.gridx = 1;
        add(textField, g);
    }
    /**
     * Loads books from a data file and populates the `fieldMaps` data structure.
     * Displays an error message dialog in case of failure.
     */
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("BooksDataFile.txt"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip the header line
                    isFirstLine = false;
                    continue;
                }
                Book book = parseBook(line);
                addBookToTreeMaps(book);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load books from file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Parses a book record from a string line and creates a `Book` object.
     *
     * @param line The string representing a book record.
     * @return A `Book` object.
     * @throws IllegalArgumentException If the book data format is invalid.
     */
    private Book parseBook(String line) {
        String[] parts = line.split("~");
        // Ensure the array has the expected number of parts
        if (parts.length < 8) {
            throw new IllegalArgumentException("Invalid book data format: " + line);
        }
        return new Book(parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6], Double.parseDouble(parts[7]));
    }
    /**
     * Adds a book to the `fieldMaps` data structure for various fields (ISBN, Authors, Year, etc.).
     *
     * @param book The `Book` object to be added.
     */
    private void addBookToTreeMaps(Book book) {
        // Assuming Book class has appropriate getters
        fieldMaps.computeIfAbsent("ISBN", k -> new TreeMap<>()).put(book.getIsbn(), book);
        fieldMaps.computeIfAbsent("Authors", k -> new TreeMap<>()).put(book.getAuthors(), book);
        fieldMaps.computeIfAbsent("Publication Year", k -> new TreeMap<>())
                .put(String.valueOf(book.getPublicationYear()), book);
        fieldMaps.computeIfAbsent("Original Title", k -> new TreeMap<>()).put(book.getOriginalTitle(), book);
        fieldMaps.computeIfAbsent("Title", k -> new TreeMap<>()).put(book.getTitle(), book);
        fieldMaps.computeIfAbsent("Average Rating", k -> new TreeMap<>())
                .put(String.format("%.2f", book.getAverageRating()), book);
    }
    /**
     * Handles the action event when the order combo box selection changes.
     *
     * @param e The action event.
     */
    private void onFieldChanged(ActionEvent e) {
        updateDisplay((String) comboBox.getSelectedItem());
    }
    /**
     * Updates the display based on the selected field (e.g., ISBN, Authors).
     *
     * @param field The selected field for ordering.
     */
    private void updateDisplay(String field) {
        currentTreeMap = fieldMaps.get(field);
        currentKeys = new ArrayList<>(currentTreeMap.keySet()); // Get all keys
        Collections.sort(currentKeys); // Sort keys based on the selected field
        currentIndex = 0; // Reset index
        displayBook(currentKeys.get(currentIndex));
    }
    /**
     * Navigates through the book collection (e.g., first, previous, next, last).
     *
     * @param direction The navigation direction (first, prev, next, last).
     */
    private void navigate(String direction) {
        if (currentKeys.isEmpty()) {
            return;
        }

        if (direction.equals("first")) {
            currentIndex = 0;
        } else if (direction.equals("prev") && currentIndex > 0) {
            currentIndex--;
        } else if (direction.equals("next") && currentIndex < currentKeys.size() - 1) {
            currentIndex++;
        } else if (direction.equals("last")) {
            currentIndex = currentKeys.size() - 1;
        }
        displayBook(currentKeys.get(currentIndex));
    }
    /**
     * Displays book details in the UI based on the selected key.
     *
     * @param key The selected key for displaying book details.
     */
    private void displayBook(String key) {
        Book book = currentTreeMap.get(key);
        bnField.setText(book.getIsbn());
        authors.setText(book.getAuthors());
        year.setText(Integer.toString(book.getPublicationYear()));
        originalTitle.setText(book.getOriginalTitle());
        title.setText(book.getTitle());
        avgRating.setText(Double.toString(book.getAverageRating()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookBrowser::new);
    }
}
