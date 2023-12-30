package mymod.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.character.TheNimbus;
import mymod.modifiers.TrueDamage;
import mymod.spireModifications.TrueDamageEnum;
import mymod.util.CardStats;

public class SongwovenShot extends BaseCard {

    public static final String ID = makeID(SongwovenShot.class.getSimpleName()); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;
    private static final CardStats info = new CardStats(
            TheNimbus.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public SongwovenShot() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        DamageModifierManager.addModifier(this, new TrueDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}