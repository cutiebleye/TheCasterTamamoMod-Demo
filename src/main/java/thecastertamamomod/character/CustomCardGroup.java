package thecastertamamomod.character;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import thecastertamamomod.cards.AmaterasuDisdain;

import java.util.Objects;

public class CustomCardGroup extends CardGroup {
    public CustomCardGroup(CardGroupType type) {
        super(type);
    }

    @Override
    public CardGroup getPurgeableCards() {
        CardGroup parentPurgeable = super.getPurgeableCards();
        CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
        for (AbstractCard card : parentPurgeable.group) {
            if (!card.cardID.equals(AmaterasuDisdain.ID )) {
                retVal.group.add(card);
            }
        }
        return retVal;
    }
}
