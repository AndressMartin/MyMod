package mymod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import mymod.character.TheNimbus;
import mymod.powers.Dodge;
import mymod.util.CardStats;

import java.util.Iterator;

public class Vanish extends BaseCard {

    public static final String ID = makeID(Vanish.class.getSimpleName()); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DODGE = 1;
    private static final int UPG_DODGE = 1;
    private static final CardStats info = new CardStats(
            TheNimbus.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Vanish() {
        super(ID, info);
        setMagic(DODGE, UPG_DODGE);
    }

    public boolean IsNormalEncounter(){
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        AbstractMonster m;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            m = (AbstractMonster)var1.next();
            if (m.hasPower("BackAttack")) {
                return false;
            }
        } while(m.type != AbstractMonster.EnemyType.BOSS);

        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (IsNormalEncounter()) {
            AbstractDungeon.getCurrRoom().smoked = true;
            addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));
            p.hideHealthBar();
            p.isEscaping = true;
            p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            AbstractDungeon.overlayMenu.endTurnButton.disable();
            p.escapeTimer = 2.5F;
        }
        else{
            addToBot(new ApplyPowerAction(p, p, new Dodge(p, magicNumber)));
        }
    }
}
