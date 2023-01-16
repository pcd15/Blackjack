import java.util.Scanner;

//@author Paul Dilly

public class Blackjack {
    public static void main(String[] args) {
        Deal player = new Deal(2); //you
        Deal dealer = new Deal(2); //the program
        Scanner play = new Scanner(System.in);
        int game = 0;
        int playAgain = 0;
        do {
            game++;
            System.out.println();
            if (game == 1) System.out.println("Welcome. Let's play some Blackjack!");
            int bet = betting(player, play);
            playerTurn(player, play);
            dealerTurn(player, dealer);
            announceWinner(player, dealer, bet);
            if (player.getMoney() > 0) playAgain = reset(player, dealer, play);
        }
        while (playAgain == 1 && player.getMoney() > 0);
        end(player, play, game);
    }

    //asks the player what they'd like to bet
    private static int betting(Deal player, Scanner play) {
        System.out.println("You have $" + player.getMoney() + "--how much would you like to wager? \n(Ignore the dollar sign when typing your response.)");
        System.out.println();
        int bet = play.nextInt();
        System.out.println();
        while (bet > player.getMoney() || bet < 0) {
            System.out.println("Please enter a dollar amount less than or equal to $" + player.getMoney() + " and greater than or equal to $0.");
            System.out.println();
            bet = play.nextInt();
            System.out.println();
        }
        return bet;
    }

    //conducts player's turn; continues running until either the player stops hitting or the player's cards total more than 21
    private static void playerTurn(Deal player, Scanner play) {
        System.out.println("You have these cards in your hand: " + player.getCards());
        System.out.println("Their total value is " + player.getScore() + ".");
        if (player.getScore() > 21) {
            System.out.println("Oh, no! You busted.");
            System.out.println();
        }   
        else {
            System.out.println("Enter 1 if you'd like to hit or any other number if not.");
            System.out.println();
            int hitMe = play.nextInt();
            if (hitMe == 1) {
                do {
                    player.hit();
                    System.out.println();
                    System.out.println("You now have these cards in your hand: " + player.getCards());
                    System.out.println("Their new total value is " + player.getScore() + ".");
                    if (player.getScore() > 21) {
                        System.out.println("Oh, no! You busted.");
                    }   
                    else if (player.getScore() == 21) {
                        System.out.println("You can no longer hit.");
                    }
                    else {
                        System.out.println("Enter 1 if you'd like to hit again or any other number if not.");
                        System.out.println();
                        hitMe = play.nextInt();
                    }
                }
                while (hitMe == 1 && player.getScore() < 21);
            } 
        }
    }
    
    //conducts dealer's turn, operating under the assumption that the dealer will only hit if their cards total less than 17
    private static void dealerTurn(Deal player, Deal dealer) {
        System.out.println();
        System.out.println("The dealer has these cards in their hand: " + dealer.getCards());
        System.out.println("Their total value is " + dealer.getScore() + ".");
        System.out.println();
        if (dealer.getScore() < 17 && player.getScore() <= 21) {
            do {
                dealer.hit();
                System.out.println();
                System.out.println("The dealer hit and now has these cards in their hand: " + dealer.getCards());
                System.out.println("Their new total value is " + dealer.getScore() + ".");
                System.out.println();
            }
            while (dealer.getScore() < 17 && player.getScore() <= 21);
        }
    }

    //announces winner of round and updates player's money
    private static void announceWinner(Deal player, Deal dealer, int bet) {
        if (dealer.getScore() > player.getScore() && dealer.getScore() <= 21) { 
            System.out.println("You lost.");
            player.setMoney(-1 * bet);
        }
        else if (dealer.getScore() == player.getScore()) {
            System.out.println("You lost.");
            player.setMoney(-1 * bet);
        }
        else if ( player.getScore() > 21) {
            System.out.println("You lost.");
            player.setMoney(-1 * bet);
        }
        else {
            System.out.println("You win!");
            player.setWins(1);
            player.setMoney(bet);
        }
    }

    //asks the player if they'd like to play again and, if so, redeals
    private static int reset(Deal player, Deal dealer, Scanner play) {
        System.out.println("Enter 1 if you'd like to play again, or enter any other number if not.");
        System.out.println();
        int playAgain = play.nextInt();
        player.redeal(2);
        dealer.redeal(2);
        return playAgain;
    }

    //calculates win rate and states closing remarks
    private static void end(Deal player, Scanner play, int game) {
        play.close();
        calculateWinnings(player.getMoney());
        double winRate = ((int) (10000 * ((double) player.getWins() / game))) / 100.0;
        System.out.println("You won " + player.getWins() + " out of " + game + " games or about " + winRate + "% of the time.");  
        System.out.println("Thank you for playing!");
        System.out.println();
    }

    //calculates winnings and current balance
    private static void calculateWinnings(int playerMoney) {
        int winnings = playerMoney - 50;
        System.out.println();
        if (playerMoney > 50) System.out.println("You started with $50 and won $" + winnings + ", so you now have $" + playerMoney + ".");
        else if (playerMoney > 0) System.out.println("You started with $50 and lost $" + Math.abs(winnings) + ", so now you have $" + playerMoney + ".");
        else System.out.println("You lost all of your money.");
    }
}
