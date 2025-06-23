package thecastertamamomod.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thecastertamamomod.cards.ManaCard;

public class ManaCostVariable extends DynamicVariable {
    @Override
    public String key() {
        return "manaCost";
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }


    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof ManaCard) {
            ManaCard manaCard = (ManaCard) card;
            int manaCost = manaCard.manaCost;
            int baseManaCost = manaCard.baseManaCost;
            return manaCost != baseManaCost;
        } else {
            return false;
        }
        // Set to true if the value is modified from the base value.
    }

    @Override
    public int modifiedBaseValue(AbstractCard card) {
        return super.modifiedBaseValue(card);
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof ManaCard) {
            ManaCard manaCard = (ManaCard) card;
                manaCard.baseManaCost = manaCard.manaCost;
            }
        // Do something such that isModified will return the value v.
        // This method is only necessary if you want smith upgrade previews to function correctly.
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof ManaCard) {
            return ((ManaCard) card).manaCost;// What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
        } else {
            return 0;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof ManaCard) {
            return ((ManaCard) card).baseManaCost;
        }else {
            return 0;
        }
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof ManaCard) {
            return card.upgraded && ((ManaCard)card).upgradeManaCost;
        } else {
            return false;
        }
        // Should return true if the card was upgraded and the value was changed
    }
}