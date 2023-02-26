# Constable

Constable is a Proof of Concept for ping compensation idea. 

## Installation

Drop build jar into `plugins/` folder, then restart your server.

## How does this work

Constable is only a plugin, that is built into Combat and uses 3 technologies, to normalize the ping of a player with a bad internet connection

### Merging

Connects game events of required types that occurred in a short period of time (default - 125ms) This allows you to process all these events at once.

### Throttling

NPC will handle merged events only after merging is done.

### Shuffling

While handling merged events, NPC will randomly shuffle event order. This eliminates the dependence of the event processing order on the player's ping.

## License

[MIT](https://github.com/DrupalDoesNotExists/Constable/blob/develop/LICENSE)