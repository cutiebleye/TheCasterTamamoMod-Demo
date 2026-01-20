package thecastertamamomod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.util.CardStats;


public class MoonCruxForm extends BaseCard {
    public static final String ID = makeID("MoonCruxForm"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.

    );

    private static final int powersAmount = 2;
    private static final int UPG_powersAmount = 1;
    private static int curseAmount = 0;

    public MoonCruxForm() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(powersAmount, UPG_powersAmount);
        tags.add(BaseModCardTags.FORM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == CardType.CURSE) {
                ++curseAmount;
            }
        }

        if (curseAmount > 0 ) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, (curseAmount * this.magicNumber)), (curseAmount * this.magicNumber)));
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, (curseAmount * this.magicNumber)), (curseAmount * this.magicNumber)));
        }

        curseAmount = 0;

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MoonCruxForm();
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
    }
}
