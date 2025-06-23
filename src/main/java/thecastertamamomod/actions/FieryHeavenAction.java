package thecastertamamomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

public class FieryHeavenAction extends AbstractGameAction {
    private final AbstractMonster m;
    private final AbstractPlayer p;

    public FieryHeavenAction(int pwrAmt, AbstractMonster m, AbstractPlayer p) {
        this.actionType = ActionType.WAIT;
        this.amount = pwrAmt;
        this.p = p;
        this.m = m;
    }

    public void update() {
        if (this.m != null) {
            if (m.intent == AbstractMonster.Intent.DEBUFF || m.intent == AbstractMonster.Intent.DEFEND_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.STRONG_DEBUFF){
                this.addToTop(new ApplyPowerAction(this.p, this.p, new ArtifactPower(this.p, this.amount), this.amount));
            }
        }

        this.isDone = true;
    }
}
