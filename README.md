 Paper implementation to prevent a villager monopoly by locking what a villager trades after its UI is opened.

 ### How Does this Work?
 This works by checking if a villager has less than 1 xp, if so it will give tha villager 1 xp to lock its trades when a player opens its menu. To allow the server to use its main thread, this runs on a different thread rather than the one the server uses, this can be toggled in the config which is shown below.

```
# Configuration for VillagerTradeLockPlugin

# If true, the plugin will run experience-checking logic asynchronously on a separate thread.
# If false, the plugin will run this logic on the server's main thread.
use-async-check: true
```

<p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://github.com/Disc-Realms-Network/villagertradelock">Villager Trade Lock</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://discrealms.net">Disc Realms Network</a> is licensed under <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1" alt=""><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1" alt=""><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/nc.svg?ref=chooser-v1" alt=""><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/sa.svg?ref=chooser-v1" alt=""></a></p>
