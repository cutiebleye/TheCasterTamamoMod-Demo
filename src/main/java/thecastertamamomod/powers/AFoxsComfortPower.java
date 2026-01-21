package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.cards.ManaCard;

import static thecastertamamomod.BasicMod.makeID;

public class AFoxsComfortPower extends BasePower {
    public static final String POWER_ID = makeID("AFoxsComfortPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public AFoxsComfortPower(AbstractCreature owner, int amount) {
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
    CardCrawlGame.sound.play("Magical", 0.05F);
}

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card instanceof ManaCard) {
            if (((ManaCard) card).isCurseArt) {
                NoblePhantasmChargePower.stackIfApplicable(AbstractDungeon.player, this.amount);
            }
        }
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
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}