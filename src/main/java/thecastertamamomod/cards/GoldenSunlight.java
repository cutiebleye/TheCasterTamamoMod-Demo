package thecastertamamomod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.NoblePhantasmChargePower;
import thecastertamamomod.util.CardStats;


public class GoldenSunlight extends ManaCard {
    public static final String ID = makeID("GoldenSunlight"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int NP_AMOUNT = 3;
    private static final int UPG_NP_AMOUNT = 2;

    public GoldenSunlight() {
        super(ID, info);
        setMagic(NP_AMOUNT, UPG_NP_AMOUNT);
        this.exhaust = true;
        //Pass the required information to the BaseCard constructor.
    }

    public void useManaCard(AbstractPlayer p, AbstractMonster m) {
        NoblePhantasmChargePower.stackIfApplicable(p, magicNumber);
    }

    public void upgrade() {
      /*  if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        } */

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GoldenSunlight();
    }
}
