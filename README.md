# UltimisMC SkyWars developed by DirectPlan
**This project is incomplete and might not be in working condition. Any contribution is welcomed.**

The SkyWars project was discontinued in April 2023 during its final phase of development. This decision came after we noticed a significant drop in player activity in SkyWars over the past few years. Given the reduced interest from players, we decided that continuing the project would not be a good use of our time and resources.

On June 23, 2024, we decided to open source the SkyWars project to at least give the community the chance to access, modify, and possibly continue its development. This way, the hard work already put into the project can still benefit the community, and new ideas and improvements can come from collaborative efforts.

## Project Layout
- **API** - Allows other plugins to access or retrieve SkyWars data using the API. (Not implemented)
- **Core** - Contains code that is used by other modules (game, lobby) to reduce duplicated code throughout the project.
- **Game** - Handles the main gameplay mechanics, including combat, events (such as chest refill), chests, etc...
- **Lib** - Helps with the development of this project. (Outdated)
- **Lobby** - Handles SkyWars Shop (such as Cosmetics, Kits), leaderboards, NPCs, Server Queues, etc...
- **Server Sync** - Allows the synchronization of data and states between multiple servers. Used for load balancing purposes.

# License
This repository is licensed under the permissive MIT license.
