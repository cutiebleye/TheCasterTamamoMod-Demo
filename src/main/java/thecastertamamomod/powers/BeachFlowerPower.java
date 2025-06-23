package thecastertamamomod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thecastertamamomod.actions.ManaChangeAction;

import static thecastertamamomod.BasicMod.makeID;

public class BeachFlowerPower extends BasePower {
    public static final String POWER_ID = makeID("BeachFlowerPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public BeachFlowerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        System.out.println("test");
        this.priority = 6;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
            return type == DamageInfo.DamageType.NORMAL ? damage * amount : damage;
        }
        else {
            return damage;
        }
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            return blockAmount * amount;
        } else {
            return blockAmount;
        }
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}