package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thecastertamamomod.cards.ManaCard;

import static thecastertamamomod.BasicMod.makeID;

public class DoubleCurseArtsPower extends BasePower {
    public static final String POWER_ID = makeID("DoubleCurseArtsPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public DoubleCurseArtsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = amount;

        this.updateDescription();
    }

/*    @Override
    public void atStartOfTurn() {
        this.amount = 0;
    }
*/
    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
}

    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }


    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
            }
        }
        return damage;
    }

 /*
    public float modifyBlock(float blockAmount, AbstractCard card) {
        float var2 = blockAmount;
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                return (var2 = blockAmount + (float) this.amount) < 0.0F ? 0.0F : var2;
            }
        }
        return var2;
    }
*/

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }
}