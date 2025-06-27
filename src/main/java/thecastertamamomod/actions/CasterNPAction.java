package thecastertamamomod.actions;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thecastertamamomod.cards.ManaCard;

public class CasterNPAction extends AbstractGameAction {
    public CasterNPAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ManaCard) {
                if (((ManaCard) c).isCurseArt) {
                    c.setCostForTurn(-9);
                    ((ManaCard) c).manaCost = 0;
                    //todo mana cost isnt going down fix that
                }
            }
        }

        this.isDone = true;
    }
}
