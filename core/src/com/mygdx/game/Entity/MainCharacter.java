package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Music;
import java.lang.Math;
public class MainCharacter extends Entity{
    public float moveSpeed = 375;
    public boolean gotHit = false;
    public int hitSpeed = 0;
    protected boolean isFalling = false;
    protected boolean walking = false;
    boolean flip = false;
    protected int HitDir;
    private double deltaImpact = 400;
    private double impactForce = 8;
    private float timer = 0;
    private float impactCooldown = 1f;
    public boolean onImpact = false;
    protected float timeAttack;
    protected boolean attacking;
    public float w;
    public float h;
    TextureRegion frame;
    public MainCharacter()
    {

        w = Gdx.graphics.getWidth() * 4.25f/100;
        h = w * 1.8f;
        frame = new TextureRegion();
        animator = new Animator();
        animator.AddAnimation("walk.png", 9, 0.6f, "walk");
        animator.AddAnimation("jump.png", 3, 0.3f, "jump");
        animator.AddAnimation("stand.png", 3, 2f, "stand");
        animator.AddAnimation("slash.png", 6, 0.3f, "slash");
        animator.StartAnimation("stand");
        setXY(0-(Gdx.graphics.getWidth()/2)+300, 0-(Gdx.graphics.getHeight()/2));
        this.hitBox = new Rectangle(this.x, this.y, this.w, this.h); //-10 pra corrigir a imagem
        mass = 40;
    }
    public void setHitDir(int dir)
    {
        this.HitDir = dir;
    }
    //agora apenas aumenta e diminui a velocidade do impacto
    public void impacto()
    {
        if(gotHit && onImpact)
        {
            gotHit = false;
        }

        if(gotHit){
            gotHit = false;
            onImpact = true;
            hitSpeed = (int)(deltaImpact);
            if(fallSpeed < 0)
                fallSpeed += 2 * deltaImpact;
            return;
        }
        
        if(hitSpeed > 0){
            hitSpeed -= impactForce;
            return;
        }
        hitSpeed = 0;
        HitDir = 0;
    }

    public void translate(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public Rectangle attack(){
        timeAttack = 0;
        this.attacking = true;
        setSound("Sounds/slash.wav");
        return new Rectangle(this.x, this.y, w*2, h/2);
    }
    public void move()
    {
        float futureX = 0;
        float futureY = 0;
    
        if(Gdx.input.isKeyPressed(Keys.A) && hitSpeed <= 0) 
        {
            futureX -= moveSpeed * Gdx.graphics.getDeltaTime();
            flip = true;
            walking = true;
        }
        else if(Gdx.input.isKeyPressed(Keys.D) && hitSpeed <= 0) 
        {
            futureX += moveSpeed * Gdx.graphics.getDeltaTime();
            walking = true;
            flip = false;
        }
        else{
            walking = false;
        }

        if(Gdx.input.isKeyJustPressed(Keys.W) && !isFalling && hitSpeed <= 0)
            fallSpeed = 1250;

        futureY += fallSpeed*Gdx.graphics.getDeltaTime();
        //x movido com base no impacto
        futureX += hitSpeed*HitDir*Gdx.graphics.getDeltaTime();
        this.hitBox.x += futureX;
        Rectangle collided = this.GetCollision();

        if(collided != null)
        {
            this.hitBox.x -= futureX;
        }

        this.hitBox.y += futureY;
        collided = this.GetCollision();
        
        if(collided != null)
        {
            this.hitBox.y -= futureY;
            isFalling = false;

            if(fallSpeed < 0)
            {
                hitBox.y = collided.y + collided.height;
            }

            fallSpeed = 0;
        }
        else
        {
            isFalling = true;
        }


        y = hitBox.y;
        x = hitBox.x;
    }
    public String getAnimation(){
        if(attacking){
            return "slash";
        }
        if(isFalling || fallSpeed > 1)
        {
            return "jump";
        }
        else if(walking)
        {
            return "walk";
        }

        else{
            return "stand";
        }
    }

    public void VerifyAttack(){
        float slashTime = 0.3f;
        if(attacking && timeAttack < slashTime)
            timeAttack += Gdx.graphics.getDeltaTime();
        else if(attacking){
            attacking = false;
        }
    }
    public void update(SpriteBatch batch){
        impacto();
        move();
        VerifyAttack();
        animator.StartAnimation(getAnimation());
        frame = animator.UpdateFrame();
        if(fallSpeed > -2000)
        {
            fallSpeed -= 2*mass;
        }

        if(onImpact)
        {
            timer += Gdx.graphics.getDeltaTime();
        }
        if(timer >= impactCooldown)
        {
            timer = 0;
            onImpact = false;
        }
        
        if(flip ^ frame.isFlipX())
        {
            frame.flip(true, false);
        }

        batch.draw(frame, this.x, this.y, this.w, this.h);
    }
}
