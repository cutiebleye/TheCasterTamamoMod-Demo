package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thecastertamamomod.actions.ManaChangeAction;
import thecastertamamomod.cards.ExtraTurn;

import static thecastertamamomod.BasicMod.makeID;

public class SpiritTheftPower extends BasePower {
    public static final String POWER_ID = makeID("SpiritTheftPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public SpiritTheftPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            System.out.println("Spirit Theft inflict damage");
            this.addToBot(new ManaChangeAction(this.owner, amount));
            this.updateDescription();

        }
    }

    public void onVictory() {
        this.amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}