package thecastertamamomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class FrigidHeavenAction extends AbstractGameAction {
    private final AbstractMonster m;

    public FrigidHeavenAction(int pwrAmt, AbstractMonster m) {
        this.actionType = ActionType.WAIT;
        this.amount = pwrAmt;
        this.m = m;
    }

    public void update() {
        if (this.m != null) {
            if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND){
                this.addToTop(new ApplyPowerAction(this.m, AbstractDungeon.player, new StrengthPower(this.m, -this.amount), -this.amount));
            }
        }

        this.isDone = true;
    }
}
