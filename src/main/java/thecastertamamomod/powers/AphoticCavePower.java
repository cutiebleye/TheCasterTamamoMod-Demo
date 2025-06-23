package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thecastertamamomod.actions.ManaChangeAction;

import static thecastertamamomod.BasicMod.makeID;

public class AphoticCavePower extends BasePower {
    public static final String POWER_ID = makeID("AphoticCavePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public AphoticCavePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID));
    }

    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("RELIC_DROP_MAGICAL", 0.05F);
}


    public int onAttacked(DamageInfo info, int damageTaken) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            this.flash();
            int damageAmount = info.output;
            if (damageAmount > 1 && this.owner.hasPower("IntangiblePlayer")) {
                damageAmount = 1;
            }
            this.addToTop(new ManaChangeAction(this.owner, damageAmount - damageTaken));

        }

        return damageTaken;
    }

    public void onVictory() {
        this.amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}