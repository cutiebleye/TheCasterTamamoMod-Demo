package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thecastertamamomod.cards.ManaCard;

import static thecastertamamomod.BasicMod.makeID;

public class ArtsBoostPower extends BasePower {
    public static final String POWER_ID = makeID("ArtsBoostPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public ArtsBoostPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = amount;

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

        this.updateDescription();
        this.canGoNegative = true;
    }

/*    @Override
    public void atStartOfTurn() {
        this.amount = 0;
    }
*/
    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
}

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            System.out.println("REMOVE ARTS WHEN GOING FROM -1 to 0");
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            System.out.println("REMOVE ARTS WHEN GOING FROM 1 to 0");
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.amount : damage;
            }
        }
        return damage;
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        float var2 = blockAmount;
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                return (var2 = blockAmount + (float) this.amount) < 0.0F ? 0.0F : var2;
            }
        }
        return var2;
    }

    public void updateDescription() {
        if (this.amount > 0) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
            this.type = PowerType.BUFF;
        } else {
            int tmp = -this.amount;
            this.description = DESCRIPTIONS[2] + tmp + DESCRIPTIONS[3];
            this.type = PowerType.DEBUFF;
        }
    }
}