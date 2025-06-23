package thecastertamamomod.vfx;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.CollectorStakeEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

//todo ALOT. right now its just a copy of the collector's curse effect. the todo is to make the screen tint "permanent" until the end of battle
//todo make the darkened screen last until end of fight. maybe even make it darker. most importantly add tamamo's glowing torii around the background
//temporarily the effect is going to have to probably just be a sound effect and gaining the NP Power or something.

public class CasterNPEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int count;
    private float stakeTimer = 0.0F;

    public CasterNPEffect(float x, float y) {
        this.x = x;
        this.y = y;
        this.count = 13;
    }

    public void update() {
        this.stakeTimer -= Gdx.graphics.getDeltaTime();
        if (this.stakeTimer < 0.0F) {
            if (this.count == 13) {
                CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.5F);
                AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F));
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.0F, 1.0F, 0.7F)));
            }

            AbstractDungeon.effectsQueue.add(new CollectorStakeEffect(this.x + MathUtils.random(-50.0F, 50.0F) * Settings.scale, this.y + MathUtils.random(-60.0F, 60.0F) * Settings.scale));
            this.stakeTimer = 0.04F;
            --this.count;
            if (this.count == 0) {
                this.isDone = true;
            }
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }

    ;

}