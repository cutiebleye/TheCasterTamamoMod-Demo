package thecastertamamomod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static thecastertamamomod.BasicMod.makeID;

public class ManaPower extends BasePower {
    public static final String POWER_ID = makeID("ManaPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    private static ManaPower getManaPower(AbstractCreature player) {
        AbstractPower power = player.getPower(POWER_ID);
        if (power instanceof ManaPower) {
            return (ManaPower) power;
        } else {
            return null; // TODO fix this (kristen you idiot)
        }
    }

    public static void addMaxMana(AbstractCreature player, int mana) {
        ManaPower power = getManaPower(player);
        if (power == null) {
            System.err.println("Kristen fucked it up");
            return;
        }
        power.amount2 += mana;
        if (power.amount2 > 999){
            power.amount2 = 999;
        }
        power.updateDescription();
    }

    public static int getCurrentMana(AbstractCreature player) {
        return player.getPower(POWER_ID).amount;
    }

    public static void addMana(AbstractCreature player, int mana) {
        ManaPower power = getManaPower(player);
        if (power == null) {
            System.err.println("Kristen fucked it up");
            return;
        }
        power.amount += mana;
        if (power.amount > power.amount2) {
            power.amount = power.amount2;
        }
        power.updateDescription();
    }

    public static void spendMana(AbstractCreature player, int mana) {
        ManaPower power = getManaPower(player);
        if (power == null) {
            System.err.println("Kristen fucked it up");
            return;
        }
        power.amount -= mana;
        if (power.amount < 0) {
            power.amount = 0;
        }
        power.updateDescription();
    }

    public ManaPower(AbstractCreature owner, int current, int max) {
        super(POWER_ID, TYPE, TURN_BASED, owner, current);
        System.out.println("test");
        amount2 = max;
        this.updateDescription();
    }


    public void onVictory() {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }
}