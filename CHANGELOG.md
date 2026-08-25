**Release Highlights:**
- 1.20.1 is an active development version again
- Add support for 26.2
- Updated all mods to most recent versions
- Backported "Use Effective's Cascade for Effectual's Mouth Steam particle" to 1.20.1
  - This feature now defaults to off
- Configs from pre 0.4.0-alpha.1 are not compatible
- Fix adding droplets to wakes completely removing the Wakes splashes regardless of if its enabled
  - They now run in tandem
- Fix Effective glow droplets for Effectual's player drip not working
  - This effect is now disabled by default
    - There was simply no way to get the particle movement correct without messing with the glow droplet's tick method in a way that negatively impact other effects
      - That being said I have an idea for a way better way to do this so It'll likely look better next release
- Significantly improve and decrease size of many mixins

_I'm still working out what mods are going to be added for compatibility, just trying to find things that have similar or overlapping features that can be made to work together better; feel free to suggest things on GitHub. This update is mostly intended to fix any issues that may have come from the mod not being updated for several months as well as make a new project structure that allows me to manage the multiple versions better (and support 1.20.1 again/update to 26.2). My plan for version support is to support the main modding versions as well as the latest release (once there are a couple of mods for it of course), though I'm open to adding more options if enough people want them. I've seen requests for this mod being ported to NeoForge. I am open to doing so (as well as Forge on 1.20.1), but I need to have a look at what mods are available for those versions. They will likley be added to the next release._
