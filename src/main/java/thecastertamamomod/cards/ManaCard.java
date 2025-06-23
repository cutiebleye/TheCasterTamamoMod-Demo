package thecastertamamomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thecastertamamomod.actions.ManaChangeAction;
import thecastertamamomod.powers.ManaPower;
import thecastertamamomod.util.CardStats;

public abstract class ManaCard extends BaseCard {
    public int manaCost;
    public int manaGain;
    public boolean upgradeManaCost;
    public boolean upgradeManaGain;
    protected int manaCostUpgrade;
    protected int manaGainUpgrade;
    public int baseManaCost;
    public int baseManaGain;
    public boolean isCurseArt;

    public ManaCard(String ID, CardStats info) {
        super(ID, info);
    }

    public ManaCard(String ID, CardStats info, String cardImage) {
        super(ID, info, cardImage);
    }

    public ManaCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color) {
        super(ID, cost, cardType, target, rarity, color);
    }

    public ManaCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, String cardImage) {
        super(ID, cost, cardType, target, rarity, color, cardImage);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m) && (ManaPower.getCurrentMana(p) >= manaCost);
        if (!canUse){
            this.cantUseMessage = "I don't have the MP for this!";
        }
        return canUse;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ManaPower.spendMana(p, manaCost);
        if (manaGain > 0) {
            addToTop(new ManaChangeAction(p, manaGain));
        }
        useManaCard(p, m);
        System.out.println("you spent mana!!!!!!!!!!!!!!!");
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            if (upgradeManaCost) {
                manaCost -= manaCostUpgrade;
                baseManaCost = manaCost;
            }
            if (upgradeManaGain) {
                manaGain += manaGainUpgrade;
                baseManaGain = manaGain;
            }
        }
        super.upgrade();
    }

    //Methods meant for constructor use
    protected final void setManaCost(int manaCost)
    {
        this.setManaCost(manaCost, 0);
    }
    protected final void setManaCost(int manaCost, int manaCostUpgrade)
    {
        this.baseManaCost = this.manaCost = manaCost;
        if (manaCostUpgrade != 0)
        {
            this.upgradeManaCost = true;
            this.manaCostUpgrade = manaCostUpgrade;
        }
    }

    protected final void setManaGain(int manaGain)
    {
        this.setManaGain(manaGain, 0);
    }
    protected final void setManaGain(int manaGain, int manaGainUpgrade)
    {
       this.baseManaGain = this.manaGain = manaGain;
        if (manaGainUpgrade != 0)
        {
            this.upgradeManaGain = true;
            this.manaGainUpgrade = manaGainUpgrade;
        }
    }

    protected abstract void useManaCard(AbstractPlayer p, AbstractMonster m);

    @Override
    public void initializeDescription() {
        super.initializeDescription();
    }
}
