public class Main {
    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Harry Potter", "J.K.Rowling"),
                new Book(2, "Sesher Kabita", "R.N Tagore"),
                new Book(3, "1984", "George Orwell"),
                new Book(4, "Pride and Prejudice", "Jane Austen")
        };

        // Linear Search
        Book foundBook = Linear_Search.SearchBooks(books, "1984");
        System.out.println(foundBook);

        // Binary Search
        Binary_Search.sortBooksByTitle(books); // Ensure the list is sorted before binary search
        foundBook = Binary_Search.SearchBooks(books, "1984");
        System.out.println(foundBook);
    }
}
