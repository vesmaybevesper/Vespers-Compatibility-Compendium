- Add support for 26.3
- Add a tweak to Wakes' Splash Clouds effect to make them "glow" just like the wakes
  - The "glow" is really just a color shift to better match the wakes, improvements coming (I hope)
- Better Effectual glowing player droplet effect
  - This is now considered a tweak, is on all versions, and is on by default again
- Switch config to Forge Config API Port
  - The way I eventually want to set up the config wouldn't have been possible using YACL, the Forge API port was the best option I could find for an existing config API
- Re-add support for 26.1.2
- Enabled applicable fixes on NeoForge >=26.1.2
- Properly version some fixes and remove unneeded ones
  - List of fixes and versions will be updated at the end of the update cycle
- Glowing Wakes is now considered a tweak of Wakes on all versions
- Use MixinConstraints to fully prevent the running of non-applicable mixins
- Update for Effectual 1.4.2
  - Remove info about needing to enable Effectual's breath effect as it is now on by default

Alpha Version Changes:
- Note that Wakes Reforged settings are in the same menu as Fabric Wakes
- Improve config 
  - Including tooltip descriptions ([#8](https://github.com/vesmaybevesper/Vespers-Compatibility-Compendium/issues/8))
  - Feel free to open pull requests with translations
- Add a screen for 1.20.1 Forge config using [Configured](https://www.curseforge.com/minecraft/mc-mods/configured) ([#9](https://github.com/vesmaybevesper/Vespers-Compatibility-Compendium/issues/9))
  - This isn't a perfect solution, but it sure is the easiest. Given that I expect 1.20.1 to be the first or second dropped version I don't feel its worth the effort to write a custom screen system for this.
  - I'm not sure why the Fabric implementation of this isn't working how I expect, but given that most of the 1.20.1 downloads are on Forge (& I really want to put out an update for this mod) I'm okay with leaving it as broken on Fabric for the time being. Use the config file if you need to edit the config on it, and as soon as I fix it I will release an update.

_We return to feature additions in the next update_
