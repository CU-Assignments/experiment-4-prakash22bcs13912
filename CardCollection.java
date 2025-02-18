import java.util.*;

class Card {
    String symbol;  // Suit of the card (e.g., Hearts, Spades, etc.)
    String rank;    // Rank of the card (e.g., Ace, 2, 3, ... 10, Jack, Queen, King)

    // Constructor to initialize the card with a symbol and rank
    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    // Method to display card details
    public void display() {
        System.out.println(rank + " of " + symbol);
    }
}

public class CardCollection {
    // Using List (which is a Collection) to store cards
    private List<Card> deck;

    // Constructor to initialize the deck
    public CardCollection() {
        deck = new ArrayList<>();
    }

    // Method to add a card to the collection
    public void addCard(Card card) {
        deck.add(card);
    }

    // Method to find all cards of a given symbol
    public void findCardsBySymbol(String symbol) {
        boolean found = false;
        for (Card card : deck) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                card.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found with symbol: " + symbol);
        }
    }

    // Method to display all the cards in the collection
    public void displayAllCards() {
        if (deck.isEmpty()) {
            System.out.println("The deck is empty.");
        } else {
            for (Card card : deck) {
                card.display();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection cardCollection = new CardCollection();
        int choice;

        // Menu for user interaction
        do {
            System.out.println("\n1. Add Card\n2. Find Cards by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline character

            switch (choice) {
                case 1:
                    // Add a new card
                    System.out.print("Enter the symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter the rank (e.g., Ace, 2, 3, Jack, Queen, King): ");
                    String rank = scanner.nextLine();
                    Card card = new Card(symbol, rank);
                    cardCollection.addCard(card);
                    System.out.println("Card added successfully.");
                    break;

                case 2:
                    // Find cards by symbol
                    System.out.print("Enter the symbol to find cards (e.g., Hearts, Spades): ");
                    symbol = scanner.nextLine();
                    cardCollection.findCardsBySymbol(symbol);
                    break;

                case 3:
                    // Display all cards in the collection
                    System.out.println("All cards in the collection:");
                    cardCollection.displayAllCards();
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
