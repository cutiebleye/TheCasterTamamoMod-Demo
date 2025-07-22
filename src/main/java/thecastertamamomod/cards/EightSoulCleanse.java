package thecastertamamomod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantFireEffect;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.util.CardStats;

public class EightSoulCleanse extends ManaCard {

    public static final String ID = makeID("EightSoulCleanse"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"



    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            5 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;
    private static final int MANA_COST = 99;
    private static final int MANA_GAIN = 0;
    private static final int UPG_MANA_COST = 2;
    private static final int UPG_MANA_GAIN = 0;
    private static final int HIT_AMOUNT = 9;
    private static final int UPG_HIT_AMOUNT = 1;

    public EightSoulCleanse() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setManaCost(MANA_COST);
        setMagic(HIT_AMOUNT);

        this.isMultiDamage = true;
        isCurseArt = true;
    }

    @Override
    protected void useManaCard(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new VFXAction(p, new BorderLongFlashEffect(Color.BLACK), 0.0F, true)); maybe add in a border effect later to make the card more powerful looking
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ScreenOnFireEffect(), 0.1F));
        for(int i = 0; i < this.magicNumber; ++i) {
            if (i%2 == 1){
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new GiantFireEffect(), 0.25F));
                this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
            } else {
                this.addToBot(new VFXAction(new BlizzardEffect(99, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
                this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }
}
