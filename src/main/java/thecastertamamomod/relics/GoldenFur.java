package thecastertamamomod.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import thecastertamamomod.cards.AmaterasuDisdain;
import thecastertamamomod.character.CasterTamamo;

import static thecastertamamomod.BasicMod.makeID;

public class GoldenFur extends BaseRelic {
    private static final String NAME = "GoldenFur"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.
    //todo change sound for both pickup and click
    private boolean cardsReceived = true;

    public GoldenFur() {
        super(ID, NAME, CasterTamamo.Meta.CARD_COLOR, RARITY, SOUND);

    }

    public void onEquip() {
        this.cardsReceived = false;
        ++AbstractDungeon.player.energy.energyMaster;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractCard ammyDisdain = new AmaterasuDisdain();
        UnlockTracker.markCardAsSeen(ammyDisdain.cardID);
        group.addToBottom(ammyDisdain.makeCopy());
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
    }

    public void update() {
        super.update();
        if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
            this.cardsReceived = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }

        if (this.hb.hovered && InputHelper.justClickedLeft) {
            CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
            this.flash();
        }

    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
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

