package mymod.behaviours.baseCardExtended;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.modifiers.TrueDamage;

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

    public static boolean CheckForTrueDamage(){
        if(lastCardsPlayed[0] == null)
            return false;
        if(DamageModifierManager.modifiers(lastCardsPlayed[0]).isEmpty())
            return false;
        if (DamageModifierManager.modifiers(lastCardsPlayed[0]).stream()
                .anyMatch(modifier -> modifier.getClass() == TrueDamage.class)) {
//            System.out.println("Last card played deals True Damage.");
            return true;
        }
        return false;
    }

    public static boolean CheckForTrueDamage(AbstractCard card){
        if(DamageModifierManager.modifiers(card).isEmpty())
            return false;
        if (DamageModifierManager.modifiers(card).stream()
                .anyMatch(modifier -> modifier.getClass() == TrueDamage.class)) {
//            System.out.println("Last card played deals True Damage.");
            return true;
        }
        return false;
    }

    public static void Initialize(){   }
}
