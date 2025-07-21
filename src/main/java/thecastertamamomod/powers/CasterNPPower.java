package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thecastertamamomod.cards.ManaCard;

import static thecastertamamomod.BasicMod.makeID;

public class CasterNPPower extends BasePower {
    public static final String POWER_ID = makeID("CasterNPPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private boolean upGraded;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public CasterNPPower(AbstractCreature owner, int amount, boolean isUpgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = -1;
        upGraded = isUpgraded;

        this.updateDescription();
    }

    public void onCardDraw(AbstractCard card) {
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                card.setCostForTurn(-9);
                ((ManaCard) card).manaCost = 0;
                //todo mana cost COLOR is red make it green
            }
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target.hasPower(CasterNPExhaustedPower.POWER_ID)){
            power.amount = 0;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }

        if (!upGraded) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CasterNPExhaustedPower(this.owner, 1)));
            System.out.println("you are getting debuffed oooooooooh");
            //todo change this to the debuff that you're going to make
        }
    }


    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("RELIC_DROP_MAGICAL", 0.05F);
}

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}