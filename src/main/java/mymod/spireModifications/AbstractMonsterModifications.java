package mymod.spireModifications;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.cards.BaseCardExtended;
import mymod.cards.extended.CardUseListener;
import mymod.modifiers.TrueDamage;

@SpirePatch(
        clz= AbstractMonster.class,
        method="damage"
)
public class AbstractMonsterModifications
{
    static int trueDamage = 0;

    static final AbstractCard[] lastCardsPlayed = new BaseCardExtended[1];

    static {
        // Listen to when a BaseCardExtended is played
        BaseCardExtended.addListener(new CardUseListener() {
            @Override
            public void onCardUse(AbstractCard card, AbstractMonster monster) {
                System.out.println(card.name + " assigned.");
                lastCardsPlayed[0] = card;
                trueDamage = card.damage;
            }
        });
    }

    public static void Initialize(){   }

    @SpirePrefixPatch
    public static void DealTrueDamage(AbstractMonster __instance, DamageInfo info)
    {
        if(lastCardsPlayed[0] == null)
            return;
        if(DamageModifierManager.modifiers(lastCardsPlayed[0]).isEmpty())
            return;
        if (DamageModifierManager.modifiers(lastCardsPlayed[0]).stream()
                .anyMatch(modifier -> modifier.getClass() == TrueDamage.class)) {
            info.output = Math.max(info.output, trueDamage);
        }
        lastCardsPlayed[0] = null;
    }
}