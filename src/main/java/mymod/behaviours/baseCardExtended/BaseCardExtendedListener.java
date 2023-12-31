package mymod.behaviours.baseCardExtended;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BaseCardExtendedListener {

    public static int trueDamage = 0;
    public static final AbstractCard[] lastCardsPlayed = new BaseCardExtended[1];

    static {
        // Listen to when a BaseCardExtended is played
        BaseCardExtended.addListener(new CardUseListener() {
            @Override
            public void onCardUse(AbstractCard card, AbstractMonster monster) {
                System.out.println(card.name + " assigned.");
                lastCardsPlayed[0] = card;
                System.out.println("dmg: " + card.damage + " base: " + card.baseDamage);
                trueDamage = Math.max(card.damage, card.baseDamage);
            }
        });
    }

    public static void Initialize(){   }
}
