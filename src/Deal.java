import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@author Paul Dilly

public class Deal {

    private static Map<Integer, String> suits; //key: arbitrary integer, value: corresponding suit; used when generating cards for O(1) efficiency
    private static Map<Integer, String> names; //key: integer, value: corresponding card name (for face cards only); used when generating cards for O(1) efficiency
    private Set<String> cardsDealt; //stores all cards dealt so far in order to prevent duplicates (and thus simulate drawing from a real deck)
    private StringBuilder myCards; //stores a StringBuilder representation of all cards in player's hand
    private int score; //equals total of all cards in hand
    private int wins;
    private int money; //start with $50
   
    //@param number: number of cards in starting hand (2 for Blackjack)
    public Deal(int number) {
        score = 0;
        wins = 0;
        money = 50;
        initialize();
        cardsDealt = new HashSet<>();
        myCards = new StringBuilder();
        createCards(number);
    }

    //initializes suits and names maps and then adds their key-value pairs to be accessed when generating random cards
    private static void initialize() {
        suits = new HashMap<>();
        suits.put(0, "Spades");
        suits.put(1, "Clubs");
        suits.put(2, "Diamonds");
        suits.put(3, "Hearts");
        names = new HashMap<>();
        names.put(1, "Ace");
        names.put(11, "Jack");
        names.put(12, "Queen");
        names.put(13, "King");
    }
    
    /*simulates drawing from a shuffled deck by using random integers to generate a card; adds created cards to cardsDealt and myCards
    * @param numCards: number of cards to generate
    */
    private void createCards(int numCards) {
        int number;
        int suit;
        String name;
        String cardName;
        for (int i = 0; i < numCards; i++) {
            number = (int) (13 * Math.random() + 1);
            suit = (int) (4 * Math.random());
            name = Integer.toString(number);
            if (names.containsKey(number)) name = names.get(number);
            cardName = name + " of " + suits.get(suit);
            while (cardsDealt.contains(cardName)) {
                number = (int) (13 * Math.random() + 1);
                suit = (int) (4 * Math.random());
                name = Integer.toString(number);
                if (names.containsKey(number)) name = names.get(number);
                cardName = name + " of " + suits.get(suit);
            }
            cardsDealt.add(cardName);
            score += number;
            if (numCards == 1) myCards.append(", ");
            myCards.append(cardName);
            if (i < numCards - 1) myCards.append(", ");
        } 
    }
   
    //calls createCards again to generate and add another random card to hand
    public void hit() { createCards(1); }
         
    //shuffles deck and resets hand
    public void redeal(int numCards) {
        cardsDealt = new HashSet<>();
        myCards = new StringBuilder();
        score = 0;
        createCards(numCards);
    }

    public void setMoney(int dollars) { money += dollars; }
    public int getMoney() { return money; }
    public void setWins(int gamesWon) { wins += gamesWon; }
    public int getWins() { return wins; }
    public void setScore(int myScore) { score += myScore; }
    public int getScore() { return score; }
    public String getCards() { return myCards.toString(); }
} 
