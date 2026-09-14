**Release Highlights:**

- New icon thanks to Flubs! [WIP]
- Add a tweak to Wakes' Splash Clouds effect to make them glow just like the wakes [WIP]
- Add "accurate" splash method for Particular Reforged [WIP]
  - This is based on a similar method from an Effected Wakes version that aims to make the splashes spawn positions better match Wakes
- Better Effectual glowing droplet effect
  - This is now considered a tweak, is on all versions, and is on by default again
- Switch config to Forge Config API Port [NEEDS MORE SET UP ADN TESTING]
  - The way I eventually want to set up the visual config wouldn't have been possible using YACL, the Forge API port was the best option I could find for an existing config API
- Re-add support for 26.1.2
  - I'm still feeling out what the most popular versions are (across all my mods), so this may go away in the future if it doesn't get utilized
- Enabled applicable fixes on NeoForge >=26.1.2
  - Fixes for JEI, Iceberg & Entity Texture Features
- Glowing Wakes is now considered a tweak of Wakes on all versions
- Use MixinConstraints to fully prevent the running of non-applicable mixins
