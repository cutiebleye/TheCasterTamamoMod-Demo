package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thecastertamamomod.cards.CasterNoblePhantasm;
import thecastertamamomod.cards.ExtraTurn;
import thecastertamamomod.cards.ManaCard;

import java.util.Objects;

import static thecastertamamomod.BasicMod.makeID;

public class NoblePhantasmChargePower extends BasePower {
    public static final String POWER_ID = makeID("NoblePhantasmChargePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public NoblePhantasmChargePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = amount;

        if (this.amount >= 9) {
            this.amount = 9;
        }

        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }

        if (owner.hasPower(CasterNPExhaustedPower.POWER_ID)){
            this.amount = 0;
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner,POWER_ID));
            System.out.println("noSTACKS");
        }

        this.updateDescription();
    }

    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
}
    //todo update sound

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (this.amount >= 9) {
            this.addToBot(new MakeTempCardInHandAction(new CasterNoblePhantasm(), 1, false));
            this.amount -= 9;

        }
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
    //todo fix and make stack properly

    /* @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target.hasPower(CasterNPExhaustedPower.POWER_ID)){
            this.amount = -1;
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner,POWER_ID));
            System.out.println("onApplyPowerMoment");
        }
    } */

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if (owner.hasPower(CasterNPExhaustedPower.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        System.out.println("removing np power");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (9 - this.amount) + DESCRIPTIONS[1];
    }

    public static void stackIfApplicable(AbstractCreature player, int amount) {
        if (!player.hasPower(CasterNPExhaustedPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new NoblePhantasmChargePower(player, amount)));
        }
    }
}