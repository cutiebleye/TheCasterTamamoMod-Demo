package thecastertamamomod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ApplyManaPowerAction;
import thecastertamamomod.powers.ManaPower;

import static thecastertamamomod.BasicMod.makeID;

public class TamamoStartingRelic extends BaseRelic {
    private static final String NAME = "TamamoStartingRelic"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.


    public TamamoStartingRelic() {
        super(ID, NAME, CasterTamamo.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }


    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        super.onUseCard(targetCard, useCardAction);
    }

    @Override
    public void atBattleStart() {
        boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
                break;
            }
        }
        if (isEliteOrBoss) {
            this.beginLongPulse();
        }
        this.addToTop(new ApplyManaPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ManaPower(AbstractDungeon.player, this.counter, this.counter)));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onVictory() {
        boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
                break;
            }
        }

        if (isEliteOrBoss) {
            this.stopPulse();
            this.flash();
            this.counter++;
            this.getUpdatedDescription();
        }
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    // Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"
}

