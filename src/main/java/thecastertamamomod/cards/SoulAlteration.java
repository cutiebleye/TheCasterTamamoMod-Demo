package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UpdateCardDescriptionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.util.CardStats;


public class SoulAlteration extends BaseCard {
    public static final String ID = makeID("SoulAlteration"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    boolean is_upgraded = false;

    public SoulAlteration() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int stacks = 0;

        if (p.hasRelic("Chemical X")) {
            stacks += 2;                                //adds +2 to effect if you have chem x
            p.getRelic("Chemical X").flash();
        }

        if (!is_upgraded) {
            this.addToBot(new ApplyPowerAction(p, p, new ArtsBoostPower(p, energyOnUse + stacks)));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new ArtsBoostPower(p, energyOnUse + stacks + 1)));
        }

        if (!this.freeToPlayOnce) {
            this.addToBot(new LoseEnergyAction(energyOnUse));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            is_upgraded = true;
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
        super.upgrade();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SoulAlteration();
    }
}
