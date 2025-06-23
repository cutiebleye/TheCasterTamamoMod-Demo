package thecastertamamomod.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.LosePercentHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static thecastertamamomod.BasicMod.makeID;

public class MythMysticCodePower extends BasePower {
    public static final String POWER_ID = makeID("MythMysticCodePower");
    public static final int PERCENT_PER_TURN = 20;

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    private int turns = 0;

    public MythMysticCodePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.owner = owner;
        this.amount = 1;

        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        int damageAmount = (int) (this.amount * (p.maxHealth * (.01 * PERCENT_PER_TURN)));
        this.addToTop(new LoseHPAction(p, p, damageAmount));
        turns++;
        System.out.println("The Power thinks Tamamo has this much max HP: " + p.maxHealth);
        System.out.println("Dealing this much damage to Tamamo: " + damageAmount);
        System.out.println("This many turns have passed: " + turns);
    }

    public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("RELIC_DROP_MAGICAL", 0.05F);
}


    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        int recoverAmount = (int) (this.amount * (p.maxHealth * (.01 * PERCENT_PER_TURN))) * turns;
        if (p.currentHealth > 0) {
            p.heal(recoverAmount);
        }
        System.out.println("tamamo is recovering this much hp: " + recoverAmount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + PERCENT_PER_TURN + DESCRIPTIONS[1];
    }
}