package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ApplyManaPowerAction;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.powers.ManaPower;
import thecastertamamomod.powers.MythMysticCodePower;
import thecastertamamomod.util.CardStats;


public class SecretGarden extends ManaCard {
    public static final String ID = makeID("SecretGarden"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int vulnAmount = 2;
    private static final int UPG_vulnAmount = -1;

    public SecretGarden() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(vulnAmount, UPG_vulnAmount);
    }

    public void useManaCard(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyManaPowerAction(p, p, new ManaPower(p,0,999)));
        this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, this.magicNumber, false)));
        //todo when MP becomes a Meter, change this
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SecretGarden();
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
    }
}
