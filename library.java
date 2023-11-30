import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Map <String, Map<String, Integer>> books = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String user_question = "Please enter (1, 2, 3, 4) for the desired action:";
        String user_options = "1-AddBooks 2-BorrowBooks 3-ReturnBooks 4-Exit";

        while (true) {
            int user_selection = get_user_selection(scanner, user_question, user_options);
            switch(user_selection) {
                case 1:
                    addBooks(books, scanner);
                    break;
                case 2:
                    BorrowBooks(books, scanner);
                    break;
                case 3:
                    ReturnBooks(books, scanner);
                    break;
                case 4:
                    System.exit(0);
                    System.out.println(books);
                default:
                    System.out.println("Invalid input: Please enter a valid option (1, 2, 3, 4).");
                    break;
            }
        }
    }
    public static int get_user_selection(Scanner scanner, String user_question, String user_options) {
        int user_selection;
        while (true) {
            System.out.println(user_question);
            System.out.println(user_options);
            if (scanner.hasNextInt()) {
                user_selection = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input: Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return user_selection;
    }
    public static void addBooks(Map<String, Map<String, Integer>> books, Scanner scanner) {
        String title_question = "Please enter the book name: ";
        System.out.println(title_question);
        String book_title = scanner.nextLine().toUpperCase();
        Map<String, Integer> author_quantity = new HashMap<>();
        String book_author_question = "Please enter the author name: ";
        System.out.println(book_author_question);
        String book_author = scanner.nextLine().toUpperCase();
        String book_quantity_question = "Please enter the book quantity: ";
        System.out.println(book_quantity_question);
        int quantity;
        while (true) {
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input: Please enter a number for the quantity.");
                scanner.nextLine();
            }
        }
        if (!books.containsKey(book_title)) {
            author_quantity.put(book_author, quantity);
            books.put(book_title, author_quantity);
        }
        else {
            Map<String, Integer> existing_book = books.get(book_title);
            if (existing_book != null) {
                int current_quantity = existing_book.getOrDefault(book_author, 0);
                int new_quantity = current_quantity + quantity;
                existing_book.put(book_author, new_quantity);
            }
        }
        System.out.println(books);
    }
    public static void BorrowBooks(Map<String, Map<String, Integer>> books, Scanner scanner) {
        String title_question2 = "Please enter the book name: ";
        System.out.println(title_question2);
        String book_title2 = scanner.nextLine().toUpperCase();
        String checkout_qty_question = "Please enter the quantity to checkout of the entered title: ";
        System.out.println(checkout_qty_question);
        int checkout_qty;
        while (true) {
            if (scanner.hasNextInt()) {
                checkout_qty = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input: Please enter a number for the checkout quantity.");
                scanner.nextLine();
            }
        }
        boolean found = false;
        for (Map.Entry<String, Map<String, Integer>> entry : books.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(book_title2)) {
                found = true;
                Map<String, Integer> existing_book = entry.getValue();
                if (existing_book != null) {
                    for (Map.Entry<String, Integer> bookEntry : existing_book.entrySet()) {
                        int current_quantity = bookEntry.getValue();
                        if (current_quantity > checkout_qty) {
                            int new_quantity = current_quantity - checkout_qty;
                            existing_book.put(bookEntry.getKey(), new_quantity);
                            System.out.println(books);
                            System.out.println("you have successfully borrowed a book! ");
                        }
                        else {
                            System.out.println("unsuccessful! Checkout quantity is greater than available quantity, please try again!");
                        }
                        break;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Book not found in the library.");
            }
        }
    public static void ReturnBooks(Map<String, Map<String, Integer>> books, Scanner scanner) {
        String title_question3 = "Please enter the book name: ";
        System.out.println(title_question3);
        String book_title3 = scanner.nextLine().toUpperCase();
        String return_qty_question = "Please enter the quantity to return of the entered title: ";
        System.out.println(return_qty_question);
        int return_qty;
        while (true) {
            if (scanner.hasNextInt()) {
                return_qty = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input: Please enter a number for the quantity.");
                scanner.nextLine();
            }
        }
        boolean found = false;
        for (Map.Entry<String, Map<String, Integer>> entry : books.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(book_title3)) {
                found = true;
                Map<String, Integer> existing_book = entry.getValue();
                if (existing_book != null) {
                    for (Map.Entry<String, Integer> book_entry : existing_book.entrySet()) {
                        int current_quantity = book_entry.getValue();
                        int new_quantity = current_quantity + return_qty;
                        existing_book.put(book_entry.getKey(), new_quantity);
                        System.out.println(books);
                        System.out.println("you have successfully returned a book! ");
                        break;

                    }
                }
            }
        }
        if (!found) {
            System.out.println("Book does not belong to the the library.");
    }
    }
}
