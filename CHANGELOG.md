- Add a tweak to Wakes' Splash Clouds effect to make them "glow" just like the wakes
  - The "glow" is really just a color shift to better match the wakes, improvements coming (I hope)
- Better Effectual glowing player droplet effect
  - This is now considered a tweak, is on all versions, and is on by default again
- Switch config to Forge Config API Port
  - The way I eventually want to set up the config wouldn't have been possible using YACL, the Forge API port was the best option I could find for an existing config API
- Re-add support for 26.1.2
  - I'm still feeling out what the most popular versions are (across all my mods), so this may go away in the future if it doesn't get utilized
- Enabled applicable fixes on NeoForge >=26.1.2
- Properly version some fixes and remove unneeded ones
  - List of fixes and versions will be updated at the end of the update cycle
- Glowing Wakes is now considered a tweak of Wakes on all versions
- Use MixinConstraints to fully prevent the running of non-applicable mixins
- Update for Effectual 1.4.2

Alpha Version Changes:
- Note that Wakes Reforged settings are in the same menu as Fabric Wakes
- Improve config 
  - Including tooltip descriptions ([#8](https://github.com/vesmaybevesper/Vespers-Compatibility-Compendium/issues/8))
  - Feel free to open pull requests with translations

_1.20.1 fabric still cant open the config in game, long story short I forgot I needed to make a screen as Forge Config Port API doesn't ship one on that version. I'm working on one, and it will hopefully be included in alpha.3_

_I will add 26.3 during this update cycle if enough features have the required mods updated_
