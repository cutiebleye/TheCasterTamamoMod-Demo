package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thecastertamamomod.cards.ExtraTurn;

import static thecastertamamomod.BasicMod.makeID;

public class CasterComboPower extends BasePower {
    public static final String POWER_ID = makeID("CasterComboPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    //todo make better icon
    //todo make it scale per CARD played and not per hit

    public CasterComboPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
    }

/*    @Override
    public void atStartOfTurn() {
        this.amount = 0;
    }
*/

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            System.out.println("reset!!!");
            this.flash();
            this.amount = 0;
        }
        this.updateDescription();

    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            System.out.println("Caster Combo inflict damage");
            ++this.amount;
            if (this.amount % 3 == 0) {
                this.flash();
                this.amount = 0;
                this.addToBot(new MakeTempCardInHandAction(new ExtraTurn(), 1, false));
            }
            this.updateDescription();

        }
    }

    public void onVictory() {
        this.amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (3-amount) + DESCRIPTIONS[1];
    }
}