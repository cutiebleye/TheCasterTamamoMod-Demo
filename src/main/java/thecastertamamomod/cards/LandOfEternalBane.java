package thecastertamamomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thecastertamamomod.character.CasterTamamo;
import thecastertamamomod.character.CustomTags;
import thecastertamamomod.powers.ArtsBoostPower;
import thecastertamamomod.util.CardStats;

public class LandOfEternalBane extends ManaCard {

    public static final String ID = makeID("LandOfEternalBane"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"

    //todo change effect to be something cooler. also change noise probably



    private static final CardStats info = new CardStats(
            CasterTamamo.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 0;
    private static final int MANA_COST = 10;
    private static final int MANA_GAIN = 0;
    private static final int UPG_MANA_COST = 0;
    private static final int UPG_MANA_GAIN = 0;
    private static final int SCALING = 2;
    private static final int UPG_SCALING = 1;


    public LandOfEternalBane() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setManaCost(MANA_COST, UPG_MANA_COST);
        setManaGain(MANA_GAIN, UPG_MANA_GAIN);
        setMagic(SCALING, UPG_SCALING);
        isCurseArt = true;
        tags.add(CustomTags.CURSE_ART);
    }

    @Override
    protected void useManaCard(AbstractPlayer p, AbstractMonster m) {
        int artsBoostAmount = 0;
        AbstractPower pwr = p.getPower(ArtsBoostPower.POWER_ID);
        if (pwr != null){
            artsBoostAmount = pwr.amount;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, 9 + this.magicNumber * artsBoostAmount), 9 + this.magicNumber * artsBoostAmount));
    }
}
