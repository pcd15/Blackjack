import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Deal {

    private static Map<Integer, String> suits;
    private static Map<Integer, String> names;
    private Set<String> cardsDealt;
    private StringBuilder myCards;
    private int score;
    private int wins;
    private int money;
   
    public Deal(int number) {
        score = 0;
        wins = 0;
        money = 50;
        initialize();
        cardsDealt = new HashSet<>();
        myCards = new StringBuilder();
        createCards(number);
    }

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
   
    public void hit() { createCards(1); }
            
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
