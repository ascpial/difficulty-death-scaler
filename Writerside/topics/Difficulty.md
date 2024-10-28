# Difficulty

The default difficulty of the game is entirely modified and does not work anymore. 

The first major change is the creation of two separate difficulties:
- the [global difficulty](Global-Difficulty.md) affecting every player;
- the [player difficulty](Player-Difficulty.md) linked with one player.

When a player dies, the global difficulty is increased for everyone and his/her difficulty is increased.
These difficulties work with steps: a step is enabled after a number of player died.
Each step is unique.

After a certain time without death (12 hours), the difficulty is decreased step by step.

BUT if no one died during a long time (3 days), the difficulty will be increased each 12 hours.
