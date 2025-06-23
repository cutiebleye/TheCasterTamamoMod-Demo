package thecastertamamomod.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thecastertamamomod.cards.ManaCard;

public class ManaGainVariable extends DynamicVariable {
    @Override
    public String key() {
        return "manaGain";
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof ManaCard) {
            ManaCard manaCard = (ManaCard) card;
            int manaGain = manaCard.manaGain;
            int baseManaGain = manaCard.baseManaGain;
            return manaGain != baseManaGain;
        } else {
            return false;
        }
        // Set to true if the value is modified from the base value.
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof ManaCard) {
            ManaCard manaCard = (ManaCard) card;
            manaCard.baseManaGain = manaCard.manaGain;
        }
        // Do something such that isModified will return the value v.
        // This method is only necessary if you want smith upgrade previews to function correctly.
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof ManaCard) {
            return ((ManaCard) card).manaGain;// What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
        } else {
            return 0;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof ManaCard) {
            return ((ManaCard) card).manaGain;
        }else {
            return 0;
        }
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof ManaCard) {
            return ((ManaCard) card).upgraded;
        } else {
            return false;
        }
        // Should return true if the card was upgraded and the value was changed
    }
}