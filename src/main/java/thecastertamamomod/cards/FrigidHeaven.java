package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ForTheEyesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thecastertamamomod.actions.FrigidHeavenAction;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.util.CardStats;

public class FrigidHeaven extends ManaCard {

    public static final String ID = makeID("FrigidHeaven"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"



    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 0;
    private static final int MANA_COST = 5;
    private static final int MANA_GAIN = 0;
    private static final int UPG_MANA_COST = 0;
    private static final int UPG_MANA_GAIN = 0;
    private static final int POWER_AMOUNT = 2;
    private static final int UPG_POWER_AMOUNT = 1;
    //todo better animation

    public FrigidHeaven() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setManaCost(MANA_COST, UPG_MANA_COST);
        setMagic(POWER_AMOUNT, UPG_POWER_AMOUNT);

        isCurseArt = true;
    }

    @Override
    protected void useManaCard(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(this.magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(this.magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new FrigidHeavenAction(this.magicNumber, m));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped()) {
                if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    break;
                }
            }
        }

    }
}
