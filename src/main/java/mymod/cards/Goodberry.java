package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.character.TheNimbus;
import mymod.util.CardStats;

public class Goodberry extends BaseCard {

    public static final String ID = makeID(Goodberry.class.getSimpleName()); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DRAW = 3;
    private static final int UPG_DRAW = 1;
    private static final CardStats info = new CardStats(
            TheNimbus.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Goodberry() {
        super(ID, info);
        setMagic(DRAW, UPG_DRAW);
        setExhaust(true);
        cardsToPreview = new mymod.cards.Berry();
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.addToTop(new MakeTempCardInHandAction(new mymod.cards.Berry(), magicNumber));
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }
}
