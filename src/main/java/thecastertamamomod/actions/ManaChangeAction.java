package thecastertamamomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import thecastertamamomod.powers.ManaPower;

public class ManaChangeAction extends AbstractGameAction {
    public ManaChangeAction(AbstractCreature player, int mana) {
        this.target = player;
        this.amount = mana;
    }

    @Override
    public void update() {
        if (!this.target.isDying && !this.target.isDead && this.duration == this.startDuration) {
            AbstractDungeon.effectsQueue.add(new HealEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY, this.amount));
            //todo make new mana gain effect
            //todo fix text that says mana down when gaining 0 mp
            if ( amount >= 0 ) {
                ManaPower.addMana(this.target, this.amount);
            } else {
                ManaPower.spendMana(this.target, -this.amount);
            }

            for(AbstractCard c : AbstractDungeon.player.hand.group) {
                c.applyPowers();
            }
        }
        this.tickDuration();
    }
}
