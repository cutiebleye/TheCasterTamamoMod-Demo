package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.util.CardStats;

public class CurseVoidCleft extends ManaCard {

    public static final String ID = makeID("VoidCleft"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"

    //todo change effect to be something cooler. also change noise probably



    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int MANA_COST = 5;
    private static final int MANA_GAIN = 0;
    private static final int UPG_MANA_COST = 0;
    private static final int UPG_MANA_GAIN = 0;
    private static final int SCALING = 5;
    private static final int UPG_SCALING = 2;


    public CurseVoidCleft() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setManaCost(MANA_COST);

        setMagic(SCALING, UPG_SCALING);
        isCurseArt = true;
    }

    @Override
    protected void useManaCard(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void applyPowers() {
        AbstractPower arts = AbstractDungeon.player.getPower(ArtsBoostPower.POWER_ID);
        if (arts != null) {
            arts.amount *= this.magicNumber;
        }

        super.applyPowers();
        if (arts != null) {
            arts.amount /= this.magicNumber;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower arts = AbstractDungeon.player.getPower(ArtsBoostPower.POWER_ID);
        if (arts != null) {
            arts.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);
        if (arts != null) {
            arts.amount /= this.magicNumber;
        }

    }
}
