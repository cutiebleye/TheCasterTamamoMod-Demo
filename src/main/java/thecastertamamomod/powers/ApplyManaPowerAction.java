package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApplyManaPowerAction extends ApplyPowerAction {
    private final int maxMana;

    private boolean isFirstUpdate = true;

    public ApplyManaPowerAction(AbstractCreature target, AbstractCreature source, ManaPower powerToApply) {
        super(target, source, powerToApply);
        maxMana = powerToApply.amount2;
    }

    @Override
    public void update() {
        super.update();
        if (this.target != null && !this.target.isDeadOrEscaped() && isFirstUpdate) {
            if (this.target instanceof AbstractPlayer) {
                ManaPower.addMaxMana((AbstractPlayer) this.target, maxMana);
            }
            isFirstUpdate = false;
        }
    }
}
