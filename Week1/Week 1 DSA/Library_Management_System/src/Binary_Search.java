import java.util.Arrays;
import java.util.Comparator;

public class Binary_Search {

    public static Book SearchBooks(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            String midTitle = books[mid].getTitle().toLowerCase();

            if (midTitle.equals(title.toLowerCase())) {
                return books[mid];
            } else if (midTitle.compareTo(title.toLowerCase()) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void sortBooksByTitle(Book[] books) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
    }
}
