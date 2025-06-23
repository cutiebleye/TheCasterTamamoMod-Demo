package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.BreathOfTheSoulPower;
import thecastertamamomod.powers.SpiritTheftPower;
import thecastertamamomod.util.CardStats;


public class BreathOfTheSoulCard extends BaseCard {
    public static final String ID = makeID("BreathOfTheSoul"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int MANA_AMT = 2;
    private static final int UPG_MANA_AMT = 1;

    public BreathOfTheSoulCard() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MANA_AMT, UPG_MANA_AMT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BreathOfTheSoulPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BreathOfTheSoulCard();
    }
}
