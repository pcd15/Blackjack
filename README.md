# Blackjack

This program simulates a Blackjack game between one player and a dealer. Blackjack is a casino card game, in which the player(s) attempt to achieve a hand value as close to 21 as possible without exceeding it. Each numbered card's value is equal to its rank, face cards are valued at 10, and Aces can take the value of either 1 or 11, depending on what "suits" the player. The game begins with each player being dealt two cards; each player may then choose to "hit"-- draw another card--while their hand's value is less than 21. Busting occurs when a hand's value exceeds 21, resulting in an automatic loss for the player.

### Assumptions/Rules
1) The dealer will only hit if their hand's value is less than 17 and the player didn't bust.
2) Aces will always be valued at 11, unless doing so would make the player/dealer bust, in which case the Ace will be valued at 1.
3) If the dealer and player have hands of the same value, the dealer wins. Similarly, if both the dealer and player bust, the dealer wins.
