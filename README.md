 Paper implementation to prevent a villager monopoly by locking what a villager trades after its UI is opened.

 ### How Does this Work?
 This works by checking if a villager has less than 1 xp, if so it will give tha villager 1 xp to lock its trades when a player opens its menu. To allow the server to use its main thread, this runs on a different thread rather than the one the server uses, this can be toggled in the config which is shown below.

```
# Configuration for VillagerTradeLockPlugin

# If true, the plugin will run experience-checking logic asynchronously on a separate thread.
# If false, the plugin will run this logic on the server's main thread.
use-async-check: true
```
