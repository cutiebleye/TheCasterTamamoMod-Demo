package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thecastertamamomod.cards.ManaCard;

import java.util.Objects;

import static thecastertamamomod.BasicMod.makeID;

public class CasterNPExhaustedPower extends BasePower {
    public static final String POWER_ID = makeID("CasterNPExhaustedPower");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public CasterNPExhaustedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = -1;

        this.updateDescription();
    }

    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("RELIC_DROP_MAGICAL", 0.05F);
    //todo change sound effect
    }

    @Override
    public void atStartOfTurn() {
        if (this.owner.hasPower(NoblePhantasmChargePower.POWER_ID)){
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, NoblePhantasmChargePower.POWER_ID));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.owner.hasPower(NoblePhantasmChargePower.POWER_ID)){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NoblePhantasmChargePower.POWER_ID));
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}