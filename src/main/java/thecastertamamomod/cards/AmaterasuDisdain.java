package thecastertamamomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.actions.ManaChangeAction;
import thecastertamamomod.util.CardStats;

public class AmaterasuDisdain extends ManaCard {

    public static final String ID = makeID("AmaterasuDisdain"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"



    private static final CardStats info = new CardStats(
            CardColor.CURSE, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.CURSE, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.SPECIAL, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 4;
    private static final int MANA_COST = 3;
    private static final int MANA_GAIN = 0;
    private static final int UPG_MANA_COST = 0;
    private static final int UPG_MANA_GAIN = 0;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
//todo should not send a message when you try to play it

    public AmaterasuDisdain() {
        super(ID, info);
    }

    @Override
    protected void useManaCard(AbstractPlayer p, AbstractMonster m) {

    }

    public void triggerOnEndOfPlayerTurn() {
        this.addToTop(new ManaChangeAction(AbstractDungeon.player, -3));
    }

}
