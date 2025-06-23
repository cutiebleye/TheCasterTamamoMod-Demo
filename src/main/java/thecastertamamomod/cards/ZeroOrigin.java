package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.powers.MythMysticCodePower;
import thecastertamamomod.util.CardStats;


public class ZeroOrigin extends BaseCard {
    public static final String ID = makeID("ZeroOrigin"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int artsAmount = 10;
    private static final int UPG_artsAmount = 5;

    public ZeroOrigin() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(artsAmount, UPG_artsAmount);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MythMysticCodePower(p, 1)));
        this.addToBot(new ApplyPowerAction(p, p, new ArtsBoostPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ZeroOrigin();
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
    }
}
