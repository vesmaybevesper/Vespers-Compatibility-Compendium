**Release Highlights:**

- Switch config to Forge Config API Port
  - The way I eventually want to set up the visual config wouldn't have been possible using YACL, the Forge API port was the best option I could find for an existing config API
- Enabled applicable fixes on NeoForge >=26.2 [WIP]
  - Fixes for JEI, Iceberg & Entity Texture Features are now implemented
- Glowing Wakes is now considered a tweak of Wakes on all versions
- Use MixinConstraints to fully prevent running non-applicable mixins
