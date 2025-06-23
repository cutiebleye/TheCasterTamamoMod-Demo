package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thecastertamamomod.actions.ManaChangeAction;

import static thecastertamamomod.BasicMod.makeID;

public class CurseMasterPower extends BasePower {
    public static final String POWER_ID = makeID("CurseMasterPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public CurseMasterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = 1;

        this.updateDescription();
    }

    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.CURSE) {
            this.flash();
            this.addToBot(new DrawCardAction(this.owner, 1));
            this.addToBot(new ManaChangeAction(this.owner, this.amount));
            //todo change this to make it scale better than 1 MP per curse drawn probably
        }
    }

    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("RELIC_DROP_MAGICAL", 0.05F);
}

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}