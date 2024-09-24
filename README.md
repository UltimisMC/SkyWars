# UltimisMC SkyWars
**This project is incomplete and non-functional and is only available for inspiration.**

The SkyWars project was discontinued in April 2023 during its mid-phase of development. This decision came after we noticed a significant drop in player activity in SkyWars over the past few years. Given the reduced interest from players, we decided that continuing the project would not be a good use of our time and resources.

On June 23, 2024, we decided to open-source the SkyWars project. This way, the hard work already put into the project can still benefit the community.

## Project Layout
- **API** - Allows other plugins to access SkyWars data using the API. (Not implemented)
- **Core** - Used by modules such as [game](game) and [lobby](lobby) to reduce duplicated code throughout the project.
- **Game** - Handles the gameplay mechanics, including combat, events (such as chest refill), chests, etc...
- **Lib** - Helps with the development of this project. (Outdated)
- **Lobby** - Handles SkyWars Shop such as Cosmetics, Kits, etc...
- **Server Sync** - Allows the synchronization of data and states between multiple servers. Also used for load balancing.

## Credit
This project was inspired by [Hypixel's SkyWars](https://hypixel.net/skywars/).

# License
This repository is licensed under the permissive MIT license.
