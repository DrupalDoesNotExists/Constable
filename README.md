# Constable

Connection Stable is the PoC of the ping normalization algorithm for Paper.

## Installation

Drop build jar into `plugins/` folder, then restart your server.
| :exclamation: This will probably break 101% of PvP mechanics! |
|---------------------------------------------------------------|

## How does this work

Constable reimplements combat event handling and embeds 3 technologies to normalize the ping of a players with a bad internet connection.

### Merging

Connects game events of required types that occurred in a short period of time (default - 125ms). This allows you to process all these events at once.

### Throttling

Constable will handle merged events only after merging is done.

### Shuffling

While handling merged events, Constable will randomly shuffle event order. This eliminates the dependence of the event processing order on the player's ping.

## License

[MIT](https://github.com/DrupalDoesNotExists/Constable/blob/develop/LICENSE)