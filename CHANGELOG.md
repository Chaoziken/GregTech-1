## Changelog

## 2.1.3
BETA RELEASE (hotfix)

* Fix crash on servers
* Fix converters not respecting rotation changes
* Fix potential power dupe exploit with converters when conversion rates differ
* Fix Wrench rotating Vanilla and some mod Blocks in a confusing way
* Breaking Glass or Glass Panes (or Stained versions) with a GT Hammer drops Glass Dust

### 2.1.2
BETA RELEASE

Featured Changes:

* Energy Converters
    * Added Energy Converters (like the CEU mod for GTCE)
    * By default, they are disabled
    * The RF <-> EU ratio is configurable, and separated between the 2 conversion modes. By default, it is 4 FE -> 1 EU for both ways
    * Differences from the CEU mod:
        * Converters no longer have Battery slots
        * Converters now have 5 sides for FE input or output
        * No more CEU/CEF, use a Soft Hammer to switch between modes (like Transformers)

* Optional Voiding Modes on Multiblocks
    * Multiblocks now have modes to selectively enable voiding outputs
    * Can choose between the following modes:
        * Void Nothing (default, original behavior before this change)
        * Void Items (void any excess items output by recipes)
        * Void Fluids (void any excess fluids output by recipes)
        * Void Items and Fluids (void any excess outputs by recipes)

* Default UI Coloration Config
    * A new config value has been added to colorize GT UIs like GT5 had
    * Default to the classic GT5 blue color, but any color can be chosen (including 0xFFFFFF, for the CE coloration)

* Fix and optimize many issues with Covers (especially when placed onto Pipes)
* Redesigned the Clipboard UI (in-inventory and placed in-world)
* New overlays for Input/Output Hatches and Buses
* Fix GT GUIs taking longer and longer to open as the world stays open
* Fix Fluid Prospector and Fluid Rig server-sided crash
* Added Fertilizer
* Allow Robot Arm Supply Exact mode to supply over 64 items
* Fixed Ore Dictionary Filter server-sided crash
* Reworked internals of the Crafting Station, should perform better and have less issues
* Fix stack overflow issue with Compact Machines
* Fix Steam Venting sound not stopping when the machine is muffled
* Fix Assembly Line and Distillation Tower having no maximum count on Energy Hatches
* Removed the Sodium Loop
* Fix Distillation Tower requiring too large of a hatch on some layers
* Added XP to smelting GT ores
* Rework Polycaprolactam processing
* Fix GT Shovels not being able to make Path Blocks
* Fixed many, many recipe conflicts in the chemical reactor
* Buffed GT Coal Ore to crush to 2x the normal amount (instead of default amount)
* New textures for Jetpack Thruster items
* Fix crash in the TextField widget
* Fix LuV+ Field Generators using the wrong wires
* Fix some buttons in UIs having a delay before the tooltip appears
* Fix potential crash related to Stone Types
* Add ability for stone types to be added for other mods
* Added more zh_cn localization
* Fix maintenance problems not occurring
* Fix muffler particles having little positional variance
* Changed prices of Fluid Prospector Terminal App
* Fixed Heat, Electric, and Frost damage sources ignoring armor
* Fix issues with user-entered number in Creative Chest/Tank
* Fix Flint having no decomposition recipe
* Fix Rubber Planks and Treated Planks not having a burn time
* Fixed wires not being paintable with a Spray Can
* Fixed Steam Machines not using the correct duration for all recipes
* Fixed Miner/Fluid Rig front overlay getting desynced
* Fixed issue with sounds getting cut off when too many are playing
* Fixed `/gt shader_reload` command
* Fixed a few Material tooltips (Polyethylene, Nickel-Zinc Ferrite, Sugar, Liquid Air)
* Fixed finding elements from CraftTweaker
* Fixed issues with Terminal text encoding
* Fix enchanted items in the Crafting Station getting the "glint" effect over the entire slot
* Allow Dust and Gem Materials to make Wires
* Added UU-Matter, to be used in the future
* Removed Graphene Ingot and Fluid
* Nerfed the amount of Sticky Resin acquired from Rubber Trees
* Fixed Catalyst order with Steam Machines in JEI
* Fixed issues with Stone Types
* Fixed GT Fluid slots sometimes drawing a Fluid incorrectly
* Internal: Added flag to force generate a block (useful for Dust-only Materials)
* Internal: Added `PostMaterialEvent` which runs after all Materials are added (GT, Addons, and CT)
* Internal: Fixed the `NO_SMELTING` flag not always working
* Internal: Reworked how Not-Consumed inputs are stored internally. Allows for recipes to have any amount of input for not-consumed items/fluids
* Internal: Deprecated `GTValues#isModLoaded()`, use the similarly named method from Forge's `Loader` class instead
* Internal: Reworked how Fluids are registered (#542)
* Internal: JEI Preview world no longer gets ticked


### 2.1.1
BETA RELEASE

* Overhaul Sound System
    * Muffling machines now no longer requires a world reload, and works immediately
    * Sounds will no longer stop working entirely after some amount of time
    * Machines running sound are now more performant
* Fix crash with Prospector Tool on servers
* Fix covers on pipes not being able to open UIs
* Fix issues with Large Turbines on servers
* Fix some GT blocks having their textures broken after changing Resource Packs / using F3+T
* Fix log spam in some situations with Fluid Pipes
* Fix Rock Breaker NPE on load, causing the machine to occasionally disappear
* Fix crash using Macerators in PA/APA
* Fix Prospector Tool and Item Magnets not saving their state between world loads
* Fix ULV Casing getting colored by the default machine color config
* Fix lag when playing a GT record
* Fix some Furnace recipe removal errors
* Adjust some circuit tier tooltips to better match the circuit theme's color
* Slightly tweak the Wool maceration/assembling recipes
* Internal: Pipes no longer have textures bound to IconSets. Removes approximately 288 textures from the Atlas
* Internal: Rename `HermeticCasings.class` -> `BlockHermeticCasing.class`

### 2.1.0
BETA RELEASE

Featured Changes:

* Fluid Rigs
    * New Multiblocks capable of draining Fluids from in-world data
    * Each 8x8 chunk area of a Dimension can have a Fluid Vein in it (not in-world)
    * Rigs at MV, EV, and LuV for harvesting these fluids
    * "Veins" can be specified similarly to Ore Veins in the config JSON files

* CraftTweaker Changes
    * In Creative Mode, there is now a button on a RecipeMap JEI Page to copy the CT script to remove that recipe to the clipboard
    * Fix IconSet changes not working in CraftTweaker
    * Fix rare issues with adding properties to materials in CraftTweaker

* Fix Fluid Pipes having half the intended transfer rate
* Fix issues with pipe/cable connections with GT6StylePipesCables OFF
* Fix power transfer in cables randomly failing
* Fix Z-Fighting with covers on pipes and cables
* Fix issues with painting Pipes and Cables
* Fix rendering and performance issues with the Clipboard
* Fix Overclock Button not working in some cases
* Fix machine default color not applying to Machine Casings, Hermetic Casings
* Fix issues with changing machine default color in-game
* Fix Item Magnets working in Spectator Mode
* Fix Coke Oven Hatch being sharable between Coke Ovens
* Fix crash with Tricorders when scanning dimensions with no fluid veins
* Fix Creative Energy Emitter tier button not updating from a manually entered voltage
* Fix issues with UI's staying open after TileEntities are removed
* Fix Compressor and Forge Hammer recipes sometimes using the wrong amount of items for Blocks (Glowstone, Quartz, etc)
* Fix Large Boilers burning for too long with fluid fuels
* Fix circuit overlay in Assembling Machine
* Fix Block Breaker, Coke Oven Hatch tooltips
* Fix Large Fluid Cells not showing their fluid capacity in the tooltip
* Move PGS processing to Centrifuge
* Fix various issues with Solar Panel covers
* Remove `dustRegular` OreDict, as its need is no more with the new Ore Dictionary Filter
* Fix a few recipe problems (Fish Oil, Ender Pearl dust Mixer using O over N, Propene + Cl conflict, PBI fluid output amount)
* Add Huge Item and Huge Restrictive Item Pipes
* Add Fluid Temperature and State tooltips to more places
* Add Config to automatically remove Furnace smelting recipes for EBF Metals added by other mods (excluding CraftTweaker)
* Add Advancement for Primitive Pump
* Rebalance Steel Processing (PBF is slightly slower, as is EBF, drastically reduce Oxygen amount for EBF)
* Buff Oilsands processing time
* Fix Beacon recipe with harder recipe configs enabled
* Add many, many new harder recipes to the harder recipe configs
* Add many new Vanilla item recycling recipes
* Change behavior of machine explosions with the config off
* Internal: Converted MetaPrefix item to use `MetaValueItem` instead of its own handwoven Map

### 2.0.6
BETA RELEASE

Featured Changes:

* Fluid Pipe Rework
    * Fixes all instances of voiding and other random issues
    * Fluid pipes can now have a side be "blocked" by shift-right-clicking with a Wrench on the Machine Grid
    * Fluids in this new implementation currently slosh like GT5U
    * Expect more changes to come to pipes in the coming updates

* Unlockable Capes
    * Capes can now be unlocked via Advancements in-game, and selected using a new Terminal app
    * 4 capes are available now, unlocked from various advancements
    * Capes can be added by addons or by CraftTweaker (documentation coming soon!)

* Remove item pipe logging spam
* Fix Large Boilers increasing item burn time over time
* Fix Large Turbines not showing the fluid in their Hatches on login
* Cables now act as "fuses" when overvolted, to protect your machines
* Fix amperage averaging for cable burning
* Add a new command `/gt recipecheck` which will look for and inform you of any potential recipe conflicts
* Add new CT method to hide a multiblock preview from JEI
* Fix all recipe conflicts in GT recipes
* Fix treated wood pulp and plank localization
* Misc changes (remove Sodium Ingot, change Platinum Sludge Residue from Electrolyzer to Centrifuge)

### 2.0.5
BETA RELEASE

Featured Changes:

* Item Magnets
    * Similar to other mods, Item Magnets can bring dropped items towards you for a little bit of power
    * Available at LV, and an upgraded form at HV, which has farther range and faster collection speed

* Ore Dictionary Filter Overhaul
    * Can now use expanded regex to filter items passed through
    * New "Info" tooltip provided in the cover UI
    * Added a "Test Slot," which can be provided an item to see if it matches your pattern (can use JEI "drag" ingredients)

* Machine UI and Item Charging Improvements
    * Machines now have a "Configurator Slot" which can be used to change the selected number of a Programmed Circuit
    * Machine Charger Slot can now charge Electric Tools, as well as RF Tools
    * Battery Buffer and Charger can now also charge RF tools
    * New tooltips to explain what the Configurator and Charger slots do, as well as the GT logo and other misc UI improvements

* Improvements to the `/gt util hand` command, now just `/gt hand`
* Fix various Cable and Wire rendering issues
* Fix Item Pipe Enhanced Round Robin voiding items occasionally
* Fix Pipes and Cables not working properly with `gamerule doDaylightCycle` disabled
* Fix Round Robin button not appearing on covers when the cover is placed on the block adjacent to a pipe
* Added a config to disable Log -> Charcoal smelting recipes
* Fix EBF UI not being openable
* Fix dust -> foil extrusion recipes
* Fix some missing recipes, like Alloy Smelter alloys and a few others
* Fix issues relating to Dyed Lens OreDictionary entries
* Fix Fueled Jetpack not always accepting fuel
* Fix Machine top and bottom faces not rotating with the front face
* Fix various instances of log spam with other mods, relating to crafting with tools
* Fix the Sense voiding some sugar cane when used
* Fix Diamond and Gold Swords having no recipes
* Fix some multiblock parts not displaying if they can be shared between multiblocks
* Fix MetaItem tooltip not appearing in some places when Advanced Tooltips is enabled
* Fix HV pump recipe
* Fix a few small chemical recipes, like Salt Water centrifuging, Aqua Regia composition, and Salt creation
* Rework "Small Lava Boiler" into "Small Liquid Boiler," which can now also burn Creosote
* Rebalance some fuels, move oils to combustion
* Add a few extra slots to Autoclave, EBF, Chemical Bath, Fermenter
* Internal: Add `SimpleCubeRenderer`, which renders a single texture across a full block
* Internal: Redo `IMultipleRecipeMaps` API
* Internal: Redo `MetaOreDictItem`
* Internal: Fix Materials with names that end in numbers causing strange errors

### 2.0.4
BETA RELEASE

* Add support for Galacticraft dimensions and GTCEu veins
* Add extruder recipes using dust for polymers (Polyethylene, PTFE, etc)
* Change the gem texture for Quartzite and Certus Quartz
* Add the ability to name GT machines, and display the name as a "name tag" rendered in-world
* Fix Hammer and Hammering Enchant not respecting Nether/End ores, not using proper fortune calculations, and dropping ore blocks for non-stone ores
* Fix crash with LittleTiles and Ore Blocks
* Fix Drills mining 2 blocks at a time when in single block mode
* Fix issue with multiblocks not being able to run when missing some output hatch types in rare situations
* Fix EnergyNet issues, where power could seemingly get "stuck" and allowed machines to be powered "wirelessly"
* Adjust GT loot values
* Fix recipe conflicts in the Ore Washer
* Add Lathe recipes for Buzzsaw Blades
* Fix recipe conflicts with Fluid Regulators
* Fix Multiblock Autobuild not replacing blocks like Grass, Leaves, Snow, etc
* Fix Nichrome Mixer recipe conflict
* Fix Radon chemical reactor recipe
* Fix Toluene fuel burn time
* Fix some Arc Furnace recipes being too high of an EU/t
* Fix buffers voiding items when broken
* Swap the ore vein weights of Cassiterite and Copper-Tin veins, to make Cassiterite more common
* Many small autogen recipe tweaks (fix arcing blocks just giving blocks still, redo lens lathe recipes, more gem ore byproducts)
* Slightly rebalance the Pump (machine)
* Cut Rock Breaker EU/t costs (tiers are still the same)
* Fix logging warnings about Hassium missing flags
* Fix crash with some multiblock structures (none available in CEu, but possible from addons or MBT)
* Recipe cleanups of some duplicate recipes (glowstone, redstone, etc)
* Add UHV+ components, for addons to utilize

### 2.0.3
BETA RELEASE

Featured Changes:

* Portable Scanner (Tricorder):
    * A new tool available at early MV, useful for scanning many things, like:
        * Machines: Can be scanned to see their current progress, energy stored, fluids stored internally, etc.
        * Generators: Can be scanned to see all the above, as well as their Power Production
        * Cables: Can be scanned to see EU/t at that point (including losses), amperage, and more
    * Can also be used to see performance data of Machines, showing the nanoseconds of CPU time they are taking on average
    * Additionally, along comes a Debug Scanner, which shows more advanced details, useful for developers and pack authors

* Surface Rock reworks:
    * Surface rocks properly spawn in the Nether
    * Model has been redone once again
    * Surface rocks can now always be broken by a bare hand
    * Surface rocks can be right-clicked to be "picked up" (drop their Tiny Dusts)
    * A few fixes for Surface Blocks, which can be specified in JSON ore veins
    
* Fix Various Mod Compatibility Problems:
    * Fix crash with the Terminal Hardware Manager and some mods (like Building Gadgets)
    * Fix crash with NuclearCraft in rare situations
    * Fix NuclearCraft GTCE compat config being required off (config can now safely be enabled if you desire)

* Change pipe/cable hitbox to "full" when holding a Pipe or Cable in your hand, and pressing SHIFT
* Fix many generator bugs (like Singleblock Generators occasionally outputting multiple amps)
* Fix Small Coal Boilers consuming Buckets when using Bucket Fuels
* Fix crash when using `addOre()` on a material in CraftTweaker
* Fix crash when running the PA with only 1 machine
* Fix a few default vein spawn heights to make them more accurate to their weight (copper-tin vein, mineral sands vein)
* Nerf Small Boiler cooldown rates slightly
* Fix Stainless Steel Gearbox recipe
* Add a few Glass-related recipes (Fluid Extract Glass Block, Fluid Solidify Glass Tubes)
* Fix some wrong Advancement triggers
* Fix double plate bender recipes
* Change Wooden Barrel to use Sticky Resin instead of Slimeball
* Fix Coke Oven allowing any number of Coke Oven Hatches
* Add ability for custom Chance Functions per RecipeMap
* Fix a few inconsistencies in the Ore Byproduct Page
* Fix Hammering Enchant being allowed on Axes and some other tools
* Fix Crafting Station dupe exploit
* Fix some issues with the LCE and ECE
* Add TOP info for Maintenance Status for Multiblocks
* Fix Activity Detector Covers missing recipes
* Significantly improve the performance of the Rock Breaker (about 16x more performant after changes)
* Fix `harderBrickRecipes` config making Brick Blocks require a Compressor
* Fix recipe conflict with Methanol
* Fix Electric and Steam Furnaces not properly working when clicking on the Progress Bar

### 2.0.2
BETA RELEASE
* Fix crash on null Fluid with some mods (and add better logging)
* Fix Rubber Trees being too tall/too short in some cases
* Buff Garnet Sand Centrifuge recipe
* Fix Crafting Recipe for the Terminal
* Fix ULPIC's being needed in MV but requiring HV to craft
* Fix Rubber Trees not giving Sticky Resin
* Fix some Stone Types having higher harvest levels than the other ores
* Clean up Cable covering recipes, add cable Unpacking recipes
* Fix Surface Rock placement being poor
    * Rocks now cannot spawn as far from the vein center
    * A rock is guaranteed to spawn in the center of a chunk with a vein in it
* Fix drum overlay being improperly tinted
* Fix a few veins (Sulfur, Cassiterite) being too limited on their height range, making them very rare
* Fix Crafting Station recipe not allowing non-Vanilla Wood Planks
* Remove Argon Plasma Arc Furnace recipes
* Add a few new configs:
    * `harderBrickRecipes`: Harder recipes for Bricks, Coke Bricks, and Firebricks (default false)
    * `plateWrenches`: Use Plates to make Wrenches instead of Ingots (default false)
    * `harderEBFControllerRecipe`: Use Electric Furnaces instead of Furnaces in the EBF recipe (default false)
* Fix a few minor problems in the Terminal
* Fix some invalid Crafting recipes, add logging

### 2.0.1
BETA RELEASE
* Add Cosmetic Blocks for Marble, Basalt, Red Granite, etc (all stone types)
* Add missing Gearbox casings, fix textures
* Fix missing resource warnings
* Remove Abandoned Bases (to return in a future release, reworked)
* Compress Audio files, shrinking .jar size by ~2.5MB
* Remove some unused configs
* Redo World Accelerator Recipe
* Fix some crashes with AOE mining
* Fix NPE in Fluid Pipes
* Add missing UV Battery Buffer, and UHV Charger and Battery Buffer
* Redo Central Monitor component recipes
* Fix server crash with Pipe Hitboxes
* Fix Vanadiumsteel requiring an HV mixer (now MV)
* Fix Fusion Ring effect playing when Fusion Reactor is deformed
* Cleanup Sulfuric/Nitric/Phosphoric Acid recipes
* Fix surface rocks using any Ore from the vein instead of the designated one
* Remove config for World Accelerator to accelerate GT machines
* Fix rare Stack Overflow in some GT inventories
* Adjust bloom config defaults
* Fix PipeNet desync on world unload
* Fix Divide By Zero issue in Creative Tank/Chest
* Fix Singleblock Steam generators outputting Distilled Water
* Fix Crafting Station being improperly colored
* Redo Rotor Holder recipes
* Fix minor lang issues
* Rework Circuit Board recipes slightly
* Rework Material Fluid registration
* Allow Dusts to Fluid Extract, and be Arc Furnaced to Ingots in some situations
* Add tooltip to Boiler about when it explodes
* Rework Energy Hatch recipes
* Fix desyncs with "Allow Input from Output Side" setting on Machines and similar chat messages
* Move ASMD Assembler recipes to IV
* Separate Fusion Ring config from normal bloom settings
* Rework Rubber Tree leaf biome coloring, sapling item model,
log top texture, leaf shape, generating in wrong biomes, generating too frequently
* Add config to increase/reduce Rubber Tree spawnrate in their allowed biomes (Forest, Jungle, Swamp)

### 2.0.0
ALPHA RELEASE
* For a full changelog, see https://github.com/GregTechCEu/GregTech/blob/master/CHANGELOG-GTCEU.md

### 1.17.0
* Fix many small GUI issues (#1574) - dan
* Add clarification tooltips to blocks in the multiblock preview (#1584) - ALongStringOfNumbers
* Allow Quantum Chests/Tanks to Keep Inventory on Break (#1555) - dan
* Apply Rotor Efficiency to all EU output from Turbine (#1694) - ALongStringOfNumbers
* Fix integer overflow for total fuel burn time (#1683) - Adrian Brock
* Fix time on Yttrium Barium Cuprate recipe (#1692) - ALongStringOfNumbers
* Fix consumable Integrated circuit being added to EBF recipe (#1690) - ALongStringOfNumbers
* Fixed Magic Energy Absorber's tooltip (#1685) - SuperCraftAlex
* Internal make the Fusion Property protected (#1689) - ALongStringOfNumbers
* Internal change Fusion Property to Long (#1693) - ALongStringOfNumbers

### 1.16.1
* Note: This is hotfix
* Fixed Surface block spawning (#1681) - ALongStringOfNumbers
* Reverted "Fixed name overlaps in GUIs (#1665)" (#1686) - LAGIdiot

### 1.16.0
* Added Fluid Regulator (#1570) - hjae78
* Added Info tab for multiblock description (#1583) - ALongStringOfNumbers
* Update input/output overlays on buses/hatches to look different (#1607) - galyfray
* Updated name of Phosphate to Tricalcium Phosphate (#1659) - MounderFod
* Updated systems to new Recipe Properties (#1672) - ALongStringOfNumbers
* Fixed wire and cable lang (#1645) - TechLord22
* Fixed typo in Monazite vein preventing surface rock spawning (#1649) - ALongStringOfNumbers
* Fixed Electric Pump Assembler recipe using wrong cable (#1650) - ALongStringOfNumbers
* Fixed damage is not applied when a saw is used as shears (#1655) - Adrian Brock
* Fixed name overlaps in GUIs (#1665) - TechLord22
* Fixed Surface Block generation issue, add logging (#1666) - ALongStringOfNumbers
* Fixed Ore generation Page glitches (#1668) - ALongStringOfNumbers
* Internal migrated Json Recipes to Java Code (#1634) - Dane Strandboge
* Internal added isHidden to RecipeMaps to hide the default RecipeMapCategory (#1652) - bruberu

### 1.15.1
* Added recipe using liquid ice (#1620) - ALongStringOfNumbers
* Added Active Overlay to single block Steam and Gas Turbines (#1624) - dan
* Added dustRegular oredict to vanilla dusts (#1625) - Adrian Brock
* Added unique OrientedOverlayRenderers for multiblocks (#1630) - ALongStringOfNumbers
* Added Assembler recipes for components (#1633) - ALongStringOfNumbers
* Updated Surface Rocks definitions and spawning (#1619) - ALongStringOfNumbers
* Updated fuel names in TOP to be translated (#1612) - Adrian Brock
* Fixed Item Collector duplicating items (#1621) - ALongStringOfNumbers
* Fixed rare bug with output hatches not working (#1626) - Adrian Brock
* Fixed material type for plain granite ore blocks (#1627) - Adrian Brock
* Fixed items not clickable in the crafting station (#1629) - Adrian Brock
* Internal updated project state in README.md (#1609) - pyure
* Internal made MatFlags require unique IDs (#1622) - dan

### 1.15.0
* Highlight: Fixed Granite ID Shift (#1549) - dan
  * Full info: https://github.com/GregTechCE/GregTech/pull/1549
  * There may be conversion of old worlds
* Added minimal Coil tier for Electric Blast furnace recipes to JEI (#1580) - LAGIdiot
* Added notconsumable for fluids to CT (#1603) - dan
* Updated GTCE chests to allow covers on bottom side (#1579) - htmlcsjs  
* Updated Hay Bale recipe to use Packager instead of Compressor (#1592) - LAGIdiot
* Updated Assembler Slot background to be more darker (#1611) - galyfray
* Removed Forge Multipart legacy support (#1594) - LAGIdiot
* Fixed MouseTweaks wheel action not working (#1485) - Nikolay Korolev
* Fixed Crafting Station voiding items on right click (#1485) - Nikolay Korolev
* Fixed Global Renderer in MetaTileEntityTESR (#1563) - KilaBash
* Fixed Rubber trees spawning in water (#1590) - Bohdan Schepansky
* Fixed eating food not returning container (#1595) - bruberu
* Fixed Issues with Dust Uncrafting (#1596) - dan
* Fixed TOP showing wrong burn time for small steam boilers (#1600) - galyfray
* Internal removed Bintray uploading (#1593) - LAGIdiot
* Internal fixed git using wrong folder, upgrade git and gradle (#1565) - Adrian Brock

### 1.14.1
* Added mechanism to distribute expensive ticks more evenly (#1513) - dan
* Added ore weight tooltips to JEI Ore page (#1562) - ALongStringOfNumbers
* Added ZenProperty annotation to blastFurnaceTemperature and toolAttackDamage (#1568) - dan
* Updated MCP Mappings to stable_39 (#1506, #1564) - dan
* Fixed glitches for SlotWidgets inside ScrollableListWidgets (#1558) - Adrian Brock
* Fixed Distillation Tower not accepting 12th layer (#1572) - dan
* Fixed NotConsumed and Output Chance Tooltips in JEI Recipes (#1576) - dan
* Fixed EV charger not having a recipe (#1586) - ALongStringOfNumbers
* Fixed Steel Steam furnace not using steel pipes (#1587) - ALongStringOfNumbers
* Refactored some metaTileEntities to provide getBaseRenderer as protected (#1573) - dan
* Internal build is running against forked version of ForgeGradle (#1582) - dan

### 1.14.0
* Added Fluid Tooltips (#1519) - dan
* Added partial overclocking (#1522) - Adrian Brock
* Added config option for lossless cables to have lossy wires  (#1537) - dan
* Added Zoom Functionality to Multiblock Previews (#1550) - dan
* Fixed chests rendering issues (#1531) - ALongStringOfNumbers
* Fixed NPE when something proxies fluid pipe capabilities (#1552) - Adrian Brock
* Fixed TileEntities bottom side texture being rotated (#1556) - detav

### 1.13.0
* Highlight: Chemistry update, Take Two (#1492) - dan
  * Full info: https://github.com/GregTechCE/GregTech/pull/1492
* Added ability to set Allowed Input from output side via the machine grid (#1483) - ALongStringOfNumbers
* Added notConsumable Fluids as possible recipe input (#1514) - dan
* Updated Plunger to work as always empty fluid container (#1461) - Adrian Brock
* Updated CycleButtonWidget to cycle in reverse on right click (#1523) - ALongStringOfNumbers
* Removed NBT from JEI recipe registration (#1516) - dan
* Removed some duplicated recipes in Macerator (#1542) - dan
* Fixed Quantum Tank behavior (#1535) - dan
* Refactored RotorHolder to not be dependent on MetaTileEntityLargeTurbine (#1527) - galyfray

### 1.12.1
* Updated number formatting (#1467) - Adrian Brock
* Updated progress bars for some machines (#1504) - dan
* Updated EU/t price of Rotor recipe in Fluid Solidifier (#1517) - ALongStringOfNumbers
* Fixed progress bars being 1px off (#1504) - dan
* Fixed Fuel TOP integration not working for server hosted games (#1511) - Adrian Brock
* Fixed rare bug with Ore vein generation page biome tooltip (#1518) - ALongStringOfNumbers
* Fixed Transformer TOP integration not working for server hosted games (#1520) - dan

### 1.12.0
* Highlight: TOP now displays fuel information (#1484) - Adrian Brock
  * Blocks consuming fuel now has new information provided in TOP
  * Specifically what fuel is used and how long will last
* Highlight: Ore vein generation page in JEI (#1386) - ALongStringOfNumbers
  * Pressing U on GTCE Ore will show in which veins it can be found
  * Additional information like dimensions, spawn range and vein weight are available
  * Vein files now can contain name and description optional values
* Added descriptors to the machine controller (#1459) - ALongStringOfNumbers
* Added unlocalized name of supported items is copied to clipboard when using /gt util hand (#1465) - Adrian Brock
* Added support for multiblocks having own front side overlay (#1494) - dan
* Added circuit method for adding circuits to CT recipes (#1496) - ALongStringOfNumbers
* Updated JEI Multiblocks to show block counts and allow bookmarking (#1452) - Adrian Brock
* Updated surface rocks to allow walking over them (#1462) - Adrian Brock
* Updated sort to sort items by amount in descending order (#1477) - Nikolay Korolev
* Updated particle texture to use material color (#1481) - Nikolay Korolev
* Updated bounding box for Chest and Safe (#1482) - Nikolay Korolev
* Removed generation of orebyproduct recipes when there are no byproducts (#1463) - Adrian Brock
* Removed duplicate Dense Plate recipe (#1495) - dan
* Fixed JEI multiblock rendering issue for Cracking Unit (#1464) - Adrian Brock
* Fixed setting held item for non player listeners (#1466) - Adrian Brock
* Fixed findPlayersUsing usage of AxisAlignedBB (#1470) - Nikolay Korolev
* Fixed chest placement render problem (#1475) - Nikolay Korolev
* Fixed some Russian translation (#1478) - Nikolay Korolev
* Fixed empty containers working as full ones in Crafting Station (#1479) - Adrian Brock
* Refactored MetaTileEntityChest (#1471) - Nikolay Korolev

### 1.11.3 (Hotfix)
* Removed mechanism to distribute expensive ticks more evenly as it broke JEI multiblock preview - LAGIdiot

### 1.11.2
* Added mechanism to distribute expensive ticks more evenly (#1424) - dan
* Fixed Turbine rotor retaining/resetting speed incorrectly (#1422) - dan
* Fixed CokeOven/PBF controller coloring support (#1432) - dan
* Fixed NPE when placing covers (#1435) - dan
* Fixed two tanks being shown in TOP for import hatch (#1447) - dan

### 1.11.1 (Hotfix)
* Fixed crash when CraftTweaker is not present - LAGIdiot

### 1.11.0
* Highlight: Chemistry update (#1414) - dan
  * Unifies conventions of how much of a material is considered 1 mol
  * Corrects non-stoichiometric reactions, removing the possibility of positive crafting loops
  * High number of recipes got added/changed/removed
  * Full info: https://github.com/GregTechCE/GregTech/pull/1414
* Added better TOP Transformer info (#1404) - dan
* Added Cable separation recipes (#1405) - dan
* Added Wire & Cable 4x recipes (#1405) - dan
* Added Rotor Mold (#1408) - dan
  * Old Assembler recipe was removed
* Added "Regular" to dust oredict entries (#1416) - dan
* Added ability to take Fluids out of Input Hatch (#1427) - dan
* Updated CT bracket handler to allows getting MetaItems (#1352) - idcppl
* Updated InputIngredient to implement IIngredient (#1387) - Eutro
* Removed Tehnut's maven as it is not needed (#1400) - LAGIdiot
* Removed Duplicate Seed Oil recipes from Fluid Extractor (#1413) - thecodingchicken
* Removed duplicated extruder recipes for metal rods (#1419) - LAGIdiot
* Fixed ItemHandlerList::extractItem using slot instead of amount (#1394) - solidDoWant
* Fixed Tooltip Bounding Box (#1415) - dan
* Fixed oregen skipping high altitude ores in rare cases (#1418) - ALongStringOfNumbers
* Fixed Surface Rocks being place underwater (#1420) - Ametsuchi
* Fixed placed potions missing texture (#1421) - Ametsuchi
* Fixed crash on registering Fluid with GENERATE_FLUID_BLOCK & GENERATE_PLASMA flags (#1428) - dan

### 1.10.9
* Updated Fuel recipe map to be similar to recipe map (#1353) - idcppl
* Updated Furnace recipe map to check all inputs (#1362) - ALongStringOfNumbers
* Removed Extra Fuel recipe additions (#1388) - ALongStringOfNumbers
* Fixed incorrect Lapotron Crystal crafting recipe (#1348) - ALongStringOfNumbers
* Fixed the Primitive Blast Furnace Voiding outputs (#1351) - ALongStringOfNumbers
* Fixed rare crash on placing cover (#1354) - Eutro
* Fixed large boiler heatEfficiencyMultiplier not working (#1355) - Artem Melentyev
* Fixed Forestry like wood not having GTCE recipes (#1369) - detav
* Fixed Forge data and Capabilities not being synced to client (#1372) - Eutro
* Fixed surface rocks rendering issue with shaders (#1373) - Hero9909
* Fixed Multiblocks output voiding (#1384) - Exaxxion

### 1.10.8
* Added Sifter to Smart Item FIlter options (#1345) - LAGIdiot
* Fixed shouldCheckWeakPower not working correctly because of Vanilla MC bug (#1331) - solidDoWant
* Fixed wrong localization key for empty hand (#1327) - Pierre Zhang
* Fixed wrong translation of Rubber nuget in ZH (#1327) - Pierre Zhang
* Fixed Multiblocks voiding partial recipe outputs when output bus is mostly full (#1337) - ALongStringOfNumbers

### 1.10.7 (Hotfix)
* Fixed Brewery and Forming Press recipe finder not working properly - LAGIdiot
* Fixed Fluid Canner and Electric Furnace recipe finder not working properly (#1326) - ALongStringOfNumbers

### 1.10.6
* Added Oredicts to granite variants, marble, basalt (#1306) - ALongStringOfNumbers
* Added Matching Mode to API for possible recipe filtering (#1308) - galyfray
* Added Smart Matching Mode to Smart Item Filter allowing it ignoring fluids in recipes (#1308) - galyfray
* Updated Crafting Station to show only extractable items from connected inventories (#1290) - Frederic Marceau
* Updated zh_cn localization (#1307) - Pierre Zhang
* Updated Ore Byproduct JEI page to include data for Chemical Bath byproducts (#1310) - ALongStringOfNumbers
* Fixed a missing command warning for hand (#1307) - Pierre Zhang
* Fixed Smart Item Filter tooltip on Filter Mode (#1308) - galyfray
* Fixed Ore Byproduct JEI page showing some unobtainable resources (#1310) - ALongStringOfNumbers
* Fixed multiblock fluid tank forming on load not respecting blocked sides (#1313) - PrototypeTrousers
* Fixed multiblock fluid tank incorrectly providing capabilities on load (#1313) - PrototypeTrousers
* Fixed Ice broken by Saw still creating water block (#1317) - Eutro
* Fixed drained Fluid Cells not stacking due to NBT (#1319) - ALongStringOfNumbers

### 1.10.5
* Added LargeStackSizeItemStackHandler to API (#1284) - LAGIdiot
* Added a JEI Info Tab for the Fluid Canner Machines for Buckets and Fluid Cells (#1288) - Frederic Marceau
* Added recipe for turning Block of Quartz (Vanilla MC) to 4 Nether Quartz (Vanilla MC) (#1293) - LAGIdiot
* Updated Granite to be its own material (#1287) - LAGIdiot
    * Macerating recipe changed
* Fixed Item Filter not persisting items with size > 127 (#1284) - LAGIdiot
* Fixed recipe validation logging data from builder chance instead of recipe chance (#1297) - galyfray
* Fixed Machine Controller GUI allowing to set too high redstone signal (#1299) - galyfray
* Fixed Machine Controller incorrectly setting working enabled on cover removal (#1299) - galyfray
* Fixed recipes taking 1 tick less to finish then they should (#1301) - LAGIdiot

### 1.10.4
* Added placing Pump/Conveyor/Robotic Arm on Output side of machine will turn allow input from output side on (#1266) - PrototypeTrousers
* Fixed issue preventing battery from fully charged. (#1267) - Derek.CHAN
* Fixed Crafting Station crash and item dupe (#1274) - PrototypeTrousers
* Fixed some RU lang entries (#1279) - Bombm

### 1.10.3
* Updated (extended) manual I/O mode on covers to be able to bypass filter (#1248) - PrototypeTrousers
* Fixed JEI R/U hotkeys not working on GTCE Fluid Slots (#1260) - PrototypeTrousers

### 1.10.2
* Added way to cast materials in CraftTweaker (#1232) - LAGIdiot
* Added zenClass annotation to GemMaterial and RoughSolidMaterial (#1253) - LAGIdiot
* Added comment to config regarding need of useCustomModPriorities to be set to true for modPriorities to work (#1254) - LAGIdiot
* Added NanoSaber configuration options (#1258) - ALongStringOfNumbers
* Updated multiblock info recipe to be exposed (#1245) - decal
* Fixed Rotor Holder spinning status after world reload (#1240) - ALongStringOfNumbers
* Fixed crash when attempting to move items through Shutter Covers (#1247) - ALongStringOfNumbers

### 1.10.1
* Added english fruit juice localization (#1204) - Saereth
* Updated Scanner tooltip to clarify usage (#1214) - DoctorWeirdGuy
* Fixed position of ghosting area for JEI items/fluids - (#1208) - detav
* Fixed zh localization for Fluid Extractor (#1213) - LovelyCatHyt
* Fixed Abandon Base generation being disabled on disabling Rubber Tree generation (#1225) - LAGIdiot
* Fixed zh localization for Autoclave (#1227) - LAGIdiot

### 1.10.0
* Added CTM as optional dependency (#1197) - LAGIdiot
* Updated output amount of Facade recipes to be based on used plate (#1194) - LAGIdiot
* Updated Integrated Circuit recipe to return circuit with configuration set to zero (#1198) - LAGIdiot
* Fixed crash on getHarvestLevel when IBlockState is not provided (#1193) - LAGIdiot
    * Mostly know as crash on putting GTCE tools to EnderIO machines
    * GTCE tools can't be put in EnderIO machines as GTCE is not provided with information how they will be used
* Fixed API shading (#1197) - LAGIdiot
    * Problems related to EnderIO facades glitching when made from Chisel blocks

### 1.9.4
* Added Bone Meal recipe to Mortar (#1178) - LAGIdiot
* Added Crafting Station to JEI list of crafting tables (#1186) - LAGIdiot
* Added Material Flag EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES (#1188) - LAGIdiot
* Updated Bone Meal recipes to work with Vanilla MC Bone Meal (#1178) - LAGIdiot
* Updated Config to have unified style and default values (#1185) - ALongStringOfNumbers
* Updated Crafting Station GUI (#1186) - LAGIdiot
    * Recipes from JEI can be clicked to crafting grid
    * Fixed scrolling via mouse wheel
    * Fixed scroll bar rendering outside of GUI
    * Fixed too many empty rows being rendered on Item List tab
    * Fixed Item List not correctly responding to removal/destruction of adjacent container
* Updated Energy Crystal and Lapotron Crystal capacity (#1189) - LAGIdiot
* Updated Nano Saber recipe to use Energy Crystal as battery (#1189) - LAGIdiot
* Fixed possible concurrent modification exception on unification (#1176) - LAGIdiot
* Fixed Blaze Powder and Bone Meal amount from Macerating recipes (#1178) - LAGIdiot
* Fixed some Vanilla MC blocks having duplicate recipes (#1188) - LAGIdiot
* Removed Bone Meal Dust (#1178) - LAGIdiot

### 1.9.3
* Added replication of mold and extruder shapes in Forming Press (#1138) - LAGIdiot
* Added Metal Bender recipe for Empty Shape Plate (#1138) - LAGIdiot
* Added JEI information for integrated circuits (#1142) - Eutro
* Added support custom chance functions (#1145) - Eutro
  * This allows for reverting to old byproduct chances
* Added Implosion Compressor CraftTweaker integration for the ability to specify explosives types (#1148) - ALongStringOfNumbers
* Added config option for lossless wires ot damage player on contact (#1151) - ALongStringOfNumbers
* Updated JEI recipe info to render from the bottom instead of at a constant height (#1118) - Eutro
* Updated zh-cn translation for Americium (#1124) - Rika
* Updated pump cover with QOL improvements (#1125) - Decal
* Updated circuit configurations to be distinct subtypes in JEI (#1142) - Eutro
* Updated overclocking tooltip in GUI (#1143) - ALongStringOfNumbers
* Updated thickness of wires for more realistic look regarding insulating them (#1159) - LAGIdiot
* Fixed drained tank not being stackable (#1127) - Derek.CHAN
* Fixed blocks not dropping when destroyed by AOE effect of Thermal Foundation hammers (#1130) - Creysys
* Fixed the zh_cn translations for fluid_extractor (#1133) - Rika
* Fixed running into powered 16x wires does not damage player (#1159) - LAGIdiot
* Fixed missing localization for plasma generator recipe map (#1160) - LAGIdiot
* Removed redundant DISABLE_DECOMPOSITION flag (#1150) - ALongStringOfNumbers
* Removed localization for old way of handling fuel recipe maps (#1160) - LAGIdiot

### 1.9.2
* Updated dependencies versions (#1070) - LAGIdiot 
    * Forge updated to 14.23.5.2847
    * CraftTweaker updated to 4.1.9.6
    * CodeChickenLib updated to 3.2.3.358
    * Forestry updated to 5.8.2.387
    * ForgeMultipart updated to 2.6.2.83
    * JEI updated to 4.15.0.291
    * The One Probe updated to 1.4.28
* Added pan and zoom for Multiblock Pattern category in JEI (#1090) - Eutro
* Added minimum tier energy input required to machine recipes in JEI (#1108) - clienthax
* Added config options to hide containers (#1110) - LAGIdiot
* Added Air Collector QOL improvements - tooltip, dimension blacklist (#1113) - LAGIdiot
* Added config option for generating veins in center of chunk (#1116) - LAGIdiot
* Fixed Chinese translation for Kanthal (#1034) - Balthild Ires
* Fixed Light Gray Dye (Silver) using bad oreDict name (#1098) - LAGIdiot
* Fixed all recipes using Gray Dye not being correctly oreDict (#1098) - LAGIdiot
* Fixed Crafting Station missing lock texture (#1099) - LAGIdiot
* Fixed Slider Widget not saving it's state when clicked (#1111) - LAGIdiot
* Improved English localization (#1094) - LAGIdiot

### 1.9.1
* Fixed mess regarding WrapScreen initialization (#1066) (#1080) - LAGIdiot
* Add flexibility into API (#1076) - Decal
* Add possibility to disable direct smelting - Decal
* Add possibility to change crushed material - Decal
* SimpleGeneratorMetaTileEntity has now a createWorkableHandle - Decal
* INSULATION_MATERIALS is now public to add more insulation tier - Decal
* Total EU is now long - Decal
* Added Method for fixed ZenGetter (addresses typo) - Ludi87
* Fixed Client side only field being initialized on server (#1065) - LAGIdiot
* Fix surface rock drops (#1062) - pyure
* Fix Lossy tank break (#1060) - pyure
* Bugfix/some ore veins not being generated because of surface rocks (#1049) - LAGIdiot
* Fixed some veins using silicon  as surface rock - which is invalid - replace it with surface rock from same file from diferent dimension, or just by most appropriet one - LAGIdiot
* Bugfix/spray can tooltip (#1056) - LAGIdiot
* Updated some color names in tooltip for RU - LAGIdiot
* Fixed light gray tooltip for ColorSprayBehaviour for ZH - LAGIdiot
* Fixed Light Gray Dye localization (#1057) - LAGIdiot
* Fixed missing material synchronization (#1059) - LAGIdiot
* Make sure to not initialize client-side only fields - Archengius
* Fixed unlocalized name shown for vanilla fluids (#1053) - LAGIdiot
* Added Extractor recipe: 1 beetroot -> 2 red dyes (#1048) - LAGIdiot
* Fixed energy network ignoring sideness on connecting to machine (#1047) - LAGIdiot
* Bugfix/item fluid slots out of alignment (#1045) - LAGIdiot
* Fixed JEI fluid being too big for fluid slots - LAGIdiot
* Scanner: - Archengius
* Surface rocks: - Archengius
* Crafting station: - Archengius
* Fixed sort button not working properly - Archengius
* Multiblock JEI preview now includes all blocks used in template in recipe lookup - Archengius
* Fix null pointer in AdvancedTextWidget - Archengius
* Fluid Tank changes: - Archengius
* Large Boiler Multiblock changes: - Archengius
* Fixed workbench ui not working on server - Archengius
* Implemented text copying for /gt util hand - Archengius
* Added boiler throttling (initial version) (#1010) - pyure
* Replaced hammer throttling with gui and removed max-temp throttling prevention - Archengius
* Pump: stop and re-calculate when touching solid-top block (#1006) - oxalica
* Do not allow ghost ingredient placing and picking if widget group is not visible - Archengius
* Bump version - Archengius

### 1.8.9
* Fluid pipes visuals rework - Archengius
* Added Rebreather - Archengius
* Increased Nano Saber energy usage to 64 EU/t when on - Archengius
* New system for armor metaitems - Archengius
* Add OreByproduct page to JEI (#913) - Blood-Asp
* Implemented manual IO switch for conveyors/pumps - Archengius
* Fixed weird max stack size behavior on robot arm - Archengius
* Allow using different rubbers in wire insulation - Archengius
* Multiblock tanks finish - Archengius
* More multiblock fluid tanks work - Dragon2488
* Decrease cobalt mining level - Archengius

### 1.8.8
* Fixed locked safe not having textures.
* Added config value for rarity of abandoned base generation.
* Bump version.

### 1.8.7
- ##### Added WorldGen - abandoned structures.
  * Can spawn anywhere in the world on the surface
  * Contain useful materials for progression, if you can get them!
  * More is coming soon!
* Disallowed fake players to use most of GTCE tool abilities.
* Fixed battery buffers charging batteries wrongly.
* Buff multi smelter smelting speed & item amount.
* Fixed pipe syncing sometimes working improperly.
* Bump version.

### 1.8.6
* Fixed inability to make dark concrete bricks.
* Fixed Air Collector consuming EU when tank is full.
* Disabled decomposition of more organic compounds.
* Removed max voltage transformer.
* Prevent invalid models on facades from crashing game.
* Fixed JEI multiblock preview not showing machines.
* Fixed robotic arm modes not working.
* Allow controlling battery buffers output via machine controller.

### 1.8.4
* Added Energy Field Projector.
* Fix server side crash with covers on pipes.
* Tree capitation now only cuts logs connected via at most 1 leaves block.
* Play destroy sound and spawn particles when tree capitating wood blocks too.

### 1.8.3-hotfix
* Removed debug log
* Fixed exception while badly written mods attempt to retrieve info from invalid stacks.

### 1.8.3
* Added Nano Saber.
* Fixed severe e-net bug where battery buffers voided accepted EU.
* Fixed cables burning on too many amps even if they're not consumed.
* Allow using GTCE gems and ingots as beacon payments.
* Changed some high-tier circuit textures.

### 1.8.2
* Show full inventory in most covers UI instead of only hotbar.
* Added recipe for electric screwdriver. Closes #530.
* Added alternative recipes for tools using power units.
* Electric tools now leave power units when their tips are broken. Closes #605.
* Remove Quartz Block cutting recipe. Closes #848.
* Temporary revert pipe rendering to previous version. Closes #846.
* Fix fisher-related compile issues.
* Added discharge mode for batteries in player inventory.
* Don't connect wires on sides of mach ines that can't neither input nor output energy.
* Increased overclock efficiency a bit for high voltage recipes.
* Reduce damage taken by tools in certain actions a bit. 
* Added a Fisher.
* Show only stone variants of ore in ores tab, show all variants in search tab.
* Use voltage names instead of tier numbers in electric items tooltip.
* Fixed incorrect pipes color after using spray on them.
* Particle textures of machines, cables and pipes are now properly colored.
* Added two new chest variants: wooden chest (27 slots) and small wooden chest (1 slot).
* Fixed incorrect lighting of pipes in world.
* Disabled cover placement grid on pipes - they now use sub areas for cover placement.
* Fixed covers not blocking flow between pipes.
* Allow blocking connections between pipes via wrench clicking on sides.
* Fixed battery buffer not updating comparator value.
* Disabled shift clicking for phantom item slots.
* Added blacklist option for filters and conveyors.
* Show actual meta tile entity creator mod ID in JEI.
* Enhanced placement grid for covers on machines and pipes, show it only when it's used now.
* Robotic Arm now isn't limited in the transfer rate in transfer exact & keep exact modes.
* Fix few tools-related issues. 

### 1.8.1
Added a config option to specify bonus EU output per turbine type.

### 1.8.0
* Bug fixes and tool improvements
* Nerfed large turbine to the ground so you will never use it again.
* Some other improvements.

### 1.7.1
* Added block breakers with several tiers.
* Refactor pump. Increase range and pump speed, made visuals better.
* Refactored byproduct boost mechanic to fit better into game.
* Adjust overclocking efficiency from 50% to 70%.
* Remove output tank index filter mechanic (replaced by fluid filters for more precise control)
* Add configuration circuit for NaOH production chain.
* Allow using sodalite and lazurite for lapotron crystals.
* Allow using sodalite and lazurite as blue dyes.
* Remove amperage requirements for thermal centrifuge.
* Fix wrong solar panel behavior on battery buffer.
* Remove duplicate TiCl4 recipe.
* Remove Radon requirements for some processing chains.
* Allow recycling pipes.
* Fix iron bars iron recycling loop.
* Fix charge overrides not working.
* Fix some redstone-related issues.
* Disable electrolyzing of some materials.
* Disallow input from output side. Can be configured with screwdriver.
* Minor recipe searching and buffering refactor, fixes some issues related to fluid canner.
* Allow toggling overclocking via GUI button for electrical machines.
* Increase duration of some ore recycling chain processes because of increased overclock efficiency.
* Made Lathe work as fast as extruder for rod production.
* Add forge hammer recipe for small gears.
* Made JackHammer use HV tier for charging.
* Fix certain ore deposits not generating.
* Fixed generators not showing in JEI as fuel recipe catalysts.
* Added bismuth as cassiterite byproduct.
* Fix JEI ingredient hovering not working on machine's fluid tanks.
* Fixed fluid filter upgrade in pump not working properly.
* Fixed rubber leaves and wood not burning.
* Fixed certain issues with charger.
* Change model in hand of rubber wood sapling to look like vanilla saplings.

### 1.7.0
* JEI now recognizes fluids in tanks as ingredients and can show their recipes.
* You can now drag ghost items and fluids from JEI to item filters and fluid filters.
* Allow inserting more items to "Keep Exact" mode than transfer rate of cover.
* Rework "Transfer Exact" mode of robotic arm to make more sense - now it only transfers items of same type in one operation, allowing to use it for example to combine dusts in packager.
* Added machine controller cover to control covers and machines with redstone.
* Added recipes for gravel and sand in forge hammer.
* Added validate/invalidate hooks for meta tile entities.
* Added turbine blade Recipes in the Assembling Machine.
* Fixed weird item movement when affected by >1 item collectors.
* Fixed pipes not being synced to other players on placement.
* Fixed crash with wrench overlay.
* Fixed rubber trees not producing rubber after server restart.
* Fixed dark cover lighting.
* Fixed wrong macerator byproduct outputs.
* Fixed error for slab recipe generation.
* Fixed inability to use plunger when there is little fluid left in machine.
* Fixed brewery consuming all items.
* Added wooden pipe recipes.
* Fix batteries not recharging in machines.
* Fix covers not dropping their inventory contents when removed (e.g filter upgrades)
* Fix weird pump behavior on deep fluid sources.

### 1.6.9
* Fix large boiler not using solid fuel.
* Fix collectors collecting items without energy.

### 1.6.8
* Fix silicon consuming integrated circuits + some other recipes.
* Allow taking fluids from inputs via containers.
* Changed lathe again to give 2 rods instead of long one.
* Re-textured pipes to look better.
* Re-design side hit calculation so covers are accessible in the similar to wrench manner.
* Fixed wooden tanks being able to hold hot fluids as items.
* Added wooden soft hammer.
* Increased stats of flint & added Fire Aspect II to flint tools.
* Fixed some cable duplication issues.
* Fix duplicate decomposition recipe for liquid air.

### 1.6.5
* Buff large boilers (a bit).
* Fix boilers burning container-dependent fuels like firestone from Railcraft.
* Fix ray tracing null exception.
* Fixed indium gallium phosphate crafting.
* Removed monazite -> helium recipe.
* Made autoclave a bit faster in crystallization.
* Reworked pipe texture locations a bit.
* Fixed fluid references in oregen.
* Fixed pipe burning crash.
* InventoryTweaks compatibility.

### 1.6.3
* Added item collectors. They collect items in configurable range.
* Fixed boule recipes consuming integrated circuits.
* Fixed NanoCPU wafer recipe wrong voltage.
* Fixed power wafer duplication.
* Fixed sodium sulfide recipes.
* Added smoke to overvoltage.
* Added rubber recipes for low-voltage cables.
* Added nugget/dust recipes to packer/unpacker.
* Fixed lumber axe not working.
* Made Primitive Blast Furnace properly handle all types of fuel and use remainder.
* Bump version & update changelog.

### 1.6.2
* Reworked chests. Re-implemented visuals, increased capacities.
* Added machine recipes for metal frames.
* Added surface block populator for custom indicators support for ores.
* Buff rotor efficiencies a bit more. Reworked rotor items.
* Implemented fortune support for hard hammers.
* Implemented anvil tool repairing.
* Fix many issues related to previous major beta version release.

### 1.6.0
* Merge GA chemistry into master.
* New circuits inspired by GA.
* Fix machine's auto outputting ignoring filtering covers (and covers installed in general)
* Tweak electrolyzing voltages a bit.
* Make Ilmenite a bit more valuable source of rutile.
* Change amount of noble gases compounds a bit and add radon.
* Increased rotor durability and efficiency for all rotors. Increased rotor cost a bit.
* Increased base pump speed a bit.
* Increased chests capacities.
* Made Tesla Coil drop player-kill requiring loot.
* Fix some machines not stacking after being broken down.
* Fix turbine blades dupe.
* Remove granites spheres from nether.
* Fix noble gases not using centrifuge for decomposition.
* Fixed disjunction crash.
* Fixed wrong overclocking on multi-amperage input machines.
* Fixed wrong light matrix usage on machine covers.

### 1.5.21
* Fix paper crafting recipes not showing in JEI.
* Fix wrong container item return with wooden form crafting.
* Fixed wiremill fine wire recipe missing.
* Fix tools harvesting not respecting block's harvest tool type.
* Made filter recipes simpler and more accessible in early game.
* Probably fix pipe crash without FMP installed.
* Fallback to StoneTypes.STONE for ore generation replaceable blocks.
* Rework StoneTypes to better fit above change. Cleanup OrePrefix.
* Fix OrePrefix maxStackSize not applying to material items.
* Do not apply incompatible material enchantments to tools.
* Allow to use sodalite and lapis in enchantment table.
* Refactor tool properties of most materials.
* Added vinteum as a dungeon loot to some places.
* Added unbreaking/mending enchantments support for tools.
* Fixed enchantment glint not being rendered on tools.

### 1.5.18
* Fix certain crashes with invalid pipe items and pipe placement in MP. Closes #576. Closes #573.
* Make biome_dictionary raise error on invalid tags and upper case them automatically. Closes #575.
* Fix frame recipes. Closes #571.
* Fixed wrong output in UI of turbines. Closes #574.
* Fixed large turbines not loosing rotor speed while working disabled.
* Disabled electrolyzing of uranium compound ores.
* Fixed high pressure machines not overclocking recipes.

### 1.5.15
* Change amount of crushed ore obtained from different ores
* Nerfed smelting of compound dust to nuggets (yet by smelting all dust from 1 ore block you will get 1 or 2 ingots for forge hammer/macerator)
* Removed smelting of crushed ore
* Jackhammer no longer behaves like hammer on blocks
* Buffed hammer durability for block mining and dig speed

### 1.5.14
* Hotfix for crash with coal boilers

### 1.5.13
* Added foam blocks and reinforced stone (colorable)
* Rework frames so they act like scaffolds and are paintable now
* Added foam sprayer for placing them
* Fixed some minor bugs and issues

### 1.5.12
* Fixed server side crash. Closes #510.
* Refactored meta tile entity sync to use less data and be faster.
* Changed size of input * output fluid tanks to 64/32 buckets. Closes #510.
* Fixed wrong sound type of gravel ores.
* Disable steel decomposition.

### 1.5.11
* Updated pipes throughput values
* Fixed damage values back. Closes #411
* fix cobalt missing screws (#407)
* Fixed server-side crash with redefined storage. Closes #397.
* Fixed some graphical glitches. Closes #399. Closes #408.
* Fixed crash when painting pipes. Closes #402.
* Fixed wrong sphere generator behavior. Closes #422.
* Fixed wrong min. amount of outputs for ore washer. Closes #425.
* Removed blaze blocks. Closes #417.
* Disallow automation of primitive blast furnace. Closes #423.
* Fixed diesel engine not using oxygen. Closes #401.
* Added wooden frames. Made wood plates usable in place of planks. Closes #418.
* Fixed wrong speed on high pressure steam machines. Disallow overclocking for bronze steam machines. Closes #400.
* Fixed wrong information about items for chargers and battery buffers. Closes #409.
* Fixed wrong generation of invalid frames.
* Changed wood color to feel more vanilla-alike.
* Fixed crashes when loading multiblock controllers. Closes #410.
* Fix steam output update on steam machines not causing an chunk update
* Add quantum tank fluid handlers. Closes #436
* Fix quantum chest/tank tooltips. Closes #435
* Fix door recipes
* Updated Butchery knife. Changed the enchantment from sharpness II to Looting III
* fix #454 use isAir instead of Blocks.AIR comparison (#458)
* fix nulls passed to isAir in MetaTileEntityDieselEngine
* Do not accept energy with zero voltage or amperage. Closes #465
* do not trigger forge events when using setBlockState in dummy world
* Fixed quantum chest exploit. Closes #463.
* Fixed lag when attempting to fill/empty full/empty fluid tanks. Closes #460.
* Made dynamite usable in implosion compressor. Closes #457.
* Made cutter recipes using lubricant 4 times faster than water. Closes #450.
* Implemented comparator support for chests & tanks. Closes #447.
* Fixed wrong comparator output on battery buffer. Closes #467.
* Rebalanced turbine rotor holder speed multipliers and logic in general. Closes #452.
* Fixed turbine rotor not getting damage. Closes #451.
* Fixed PBF not updating texture. Closes #446.
* Fixed arc smelting recipes not generating for some ore prefixes. Closes #437.
* Increased capacity of transformers to 2x to allow constant transform rate.
* Fixed cyclic reference to OrePrefix when loading Materials class to early.
* Implemented a proper place to register materials from the code to implement.
* Added option to disable crashing behavior of invalid or error recipes.
* Removed useless and random information from tools. Should fix issues with other mods supplying tools into recipes. Closes #453.
* fixed jei multiblock info error
* Sort out multiblock parts for persistent order. Closes #474.
* Fixed harvest level for DustMaterial ores (#471)
* Some energy API improvements, battery fixes. Closes #483.
* Fixed battery buffer implementation bug. Closes #488.
* Fixed color not applying during initial machine placement. Closes #502.
* Fixed some machines have no painting color applied to their casings.
* Made fluid tanks usable as fluid containers in item-form.
* Made tungstensteel tank capacity 64 buckets.
* Fixed large cells. Increased their capacity to 64/256 buckets (for steel/tungstensteel).- Fixed wrong tools attack speed.
* Fixed creature spawning on machines. Closes #477.
* Implemented big rubber trees.
* Made electrolyzer and centrifuge output slots tabbed.
* Fixed some recipes.
* Fire tree grow event. Closes #509.
* #### Implemented covers: 
- Robot Arm (most advanced cover- filtering, putting in specific slots, buffering and many more) 
- Conveyor Belt (transporting items, not filtered) 
- Pump (Fluid transportation, filtered)
- Filter (item/fluid/ore dictionary) (allows you to filter items/fluids passing through machines)

All covers have GUIs that help you set them up. They can be placed on machines and on pipes. They're tiered

### 0.5.10
* Fixed not colored wrenches and wrong crowbar overlay color
* Rebalanced damage and durability values for tools
* Fixed too long burn time for large boiler
* Fixed worldgen replacing bedrock
* Fixed crafted tools having 0 durability
* Added emerald and diamond GT forms of gems
* Removed rubber tools except soft hammer
* Hoes now till coarse dirt too
* Removed ambiguous gem + hammer -> gem recipes
* Added 50% hammer tooltip

### 0.5.9
* Added Stones and Oils to Generation in Overworld/Nether (Rongm[a]rio)
* Added Chinese Translation (Timozer)
* Rename worldgen extract lock file to be twitch-import friendly (pyure)
* Fixed pipe networks not updating connections on world load
* Fixed potion effect from food being applied on both sides
* Fixed pumps not accepting power from cables
* Removed unnecessary concrete recipe
* Fixed hard hammer not dropping 1 extra crushed ore
* Fixed crafting recipes for gems that do not have plate variants
* Other bugfixes

### 0.5.8
* **Optimized cables and reworked pipes due to performance impact they had. Unfortunately these are not backwards compatible with previous ones, and it is better to remove all existing pipes and cables from the world to avoid possible world corruption**
* Fixed multiblock info tooltip rendering
* Fixes an issue where BuildCraft oil cannot be refined in a Distillery, among others *(codewarrior0)* 
* Removed glow effect from metatools
* Added turbine rotor efficiency description
* Added option to reset recipe progress when energy supply is insufficient (default is slowly going down like in vanilla)
* Added byproduct multiplier for higher tiers of the machines
* Fixed hoes taking too much damage on use
* Fixed memory leaks in packet system
* Fixed various issues with lighting
* Fixed a lot of other bugs

### 0.5.7
* Added Item Pipes
* Added Fluid Pipes
* Added Dynamite
* Fix duplicated MetaItem IDs
* Fix duplicated Distillery recipes
* Fix lens colors
* Remove placeholder pipes
* Added method for pbf recipe removal
* Rewritten cable code
