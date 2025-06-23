# Bleye's Tamamo Mod

Hi if you're looking at this that means you're one of the lucky few that get to help alice on her project. yippee!

---

### stuff CURRENTLY implemented!!


- Brand new MP mechanic (currently a power should be a new resource meter)
- Unique Starting Relic to interact with the MP system
- ~40 new cards exclusively to Tamamo
- every card has art (or screenshots) from official Fate works. mostly the Extra and Extella games.
- Also there's new tamamo-specific common and boss relics so look out for those.
- Three-ish "archetypes" unique-ish to the character. 
  - new "Curse Art" cards that scale with a new Arts Boost system and use the new MP mechanic.
  - a series of cards that interact with Slay the Spire's base game Curse cards in exciting ways
  - or you can just forget all that and try to dial up your strength and dexterity lmao

---
### stuff that alice WILL implement

- ~35 more cards to take Tamamo up to every other character's card total of 75
- also like 8 more relics to also line up with every other character too
- another "archetype" of cards based around tamamo's *Noble Phantasm* ooh so cool
- making new animations and sounds is like a long term goal it's gonna be a pain in the butt im sure

---
### what alice needs from YOU my dear viewer
so like obviously this mod is gonna be unbalanced like crazy. what i need from YOU is to think about the flaws of this mod and tell me

- Is [card] too strong? too weak?
- Is [card] too Energy (or MP) expensive?
- Is [mechanic] balanced? is it too easy to scale? or too hard?
- What new cards does Tamamo need? Does she need more block cards? draw cards? energy gain cards? damage cards?
- Do cards need to change rarity? Should a Common really be Uncommon? Should an Uncommon be a Rare?
- or honestly if something just feels off that's good info too and i can take a look at it

visuals and sounds are long-term goals so if something SUCKS. please tell me because i might make it a priority.

---

## anyway how to install (sorry its not simpler):
1. Subscribe to ModTheSpire, BaseMod, and StSLib on the Steam Workshop
2. Open the project in IntelliJ as a Maven Project (can elaborate more if needed)
3. Open the pom.xml file
4. Change the <steam.windows> parameter to match your actual Steam installation location (e.g. for me, E:\Program Files\Steam\steamapps)
5. Edit the Run Configuration:

    1. set Path to JAR to the path to the ModTheSpire.jar file (i.e. ..\Steam\steamapps\workshop\content\646570\1605060445\ModTheSpire.jar)
    
    2. set Working directory to the path to the SlayTheSpire game directory (i.e. Steam\steamapps\common\SlayTheSpire)

    3. set JRE to the Slay the Spire packaged JRE (i.e. ..\Steam\steamapps\common\SlayTheSpire\jre)

 Then it should run