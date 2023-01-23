# Blackjack

This program simulates a Blackjack game between one player and a dealer. Blackjack is a casino card game, in which the player(s) attempt to acheive a hand value as close to 21 as possible (but no higher), where each card's value is equal to its rank. Face cards--Jack, Queen, and King--are valued at 10, and Aces can either take on the value of 1 or 11. The game begins with each player being dealt two cards; each player may then choose to "hit," or draw another card, while their hand's value is less than 21. "Busting" occurs when a hand's value exceeds 21; this results in an automatic loss for the player.

# Assumptions
1) The dealer will only hit while their hand's value < 17.
2) Aces will always be valued at 11, unless doing so would make the player/dealer bust, in which case the Ace will be valued at 1.
3) If both the dealer and player bust, the dealer wins.
