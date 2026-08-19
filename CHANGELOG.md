**Release Highlights:**
- 1.20.1 is an active development version again
- Add support for 26.2
- Updated all mods to most recent versions
- Backported "Use Effective's Cascade for Effectual's Mouth Steam particle" to 1.20.1
  - This feature now defaults to off
  - This feature is currently broken
- Improved many mixins
- Configs from pre 0.4.0-alpha.1 are not compatible
- Fix adding droplets to wakes completely removing the Wakes splashes regardless of if its enabled
  - They now run in tandem
- Fix Effective glow droplets for Effectual's player drip not working
  - The particle doesn't look quite right and the velocities are a tiny bit messed up right now
- Significantly improve and decrease size of many mixins
  - Still only partly through this process as of this beta, this sub-bullet will vanish when complete

Beta Fixes:


_I'm still working out what mods are going to be added for compatibility, just trying to find things that have similar or overlapping features that can be made to work together better; feel free to suggest things on GitHub. This update is mostly intended to fix any issues that may have come from the mod not being updated for several months as well as make a new project structure that allows me to manage the multiple versions better (and support 1.20.1 again/update to 26.2). My plan for version support is to support the main modding versions as well as the latest release (once there are a couple of mods for it of course), though I'm open to adding more options if enough people want them. I've seen requests for this mod being ported to NeoForge. I am open to doing so (as well as Forge on 1.20.1), but I need to have a look at what mods are available for those versions, so it will not happen until at least after the 0.4.0 full release._
