## Gamelogic Team

## Leaderboard Team

## Network Team

## GUI Team
Rematch Function

We added a prompt that would be shown to the player asking whether they would like a rematch after the game ends. If both players select ok, then the rematch begins. The prompt is incorporated into the function for checking endgame condition so there are no changes to the diagram.

User friend system

Instead of implementing the proposed User Friend System, we opted for a Player Search and Rematch feature, which was simpler and required fewer changes to the codebase. The Player Search extended our existing profile search functionality, allowing users to look up other players easily. The Rematch feature enabled users to quickly start a new game with the same opponent after a match ended, requiring minimal updates to the code/GUI. This approach reduced development complexity while still improving user interaction and engagement without the need to manage persistent friendships.

Lobby Chat (Global)

We decided to forgo implementing a global chat system and instead opted for a chat limited to players within a current game due to the lack of actual networking in our application. A global chat would require real-time communication and complex networking infrastructure, which was beyond the scope of our project. By restricting the chat to active game sessions, we simplified the implementation, as messages could be managed locally within the game instance without needing a server. 


## Authentication Team

## Integration Team
Integration team documented changes requested, creating ease for other teams to look through information. Also documented 
the effect on the system, including the amount of work that had to be done and what had to be changed. Team decided to focus 
on rematch system, as both teams recommend it. Chat functionality was deemed unimportant and would likely overstress the system, 
resulting in failure of primary functionality.
