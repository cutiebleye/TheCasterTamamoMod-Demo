package thecastertamamomod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ApplyManaPowerAction;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.powers.ManaPower;

import static thecastertamamomod.BasicMod.makeID;

public class TamamosFanClub extends BaseRelic {
    private static final String NAME = "TamamosFanClub"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.
    //todo change the landing sound to tamamo's mikon

    public TamamosFanClub() {
        super(ID, NAME, CasterTamamo.Meta.CARD_COLOR, RARITY, SOUND);
    }


    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtsBoostPower(AbstractDungeon.player, 1)));

    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"
}

