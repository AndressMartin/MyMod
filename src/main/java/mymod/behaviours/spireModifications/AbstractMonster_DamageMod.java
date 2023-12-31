package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.modifiers.TrueDamage;

import static mymod.behaviours.baseCardExtended.BaseCardExtendedListener.lastCardsPlayed;
import static mymod.behaviours.baseCardExtended.BaseCardExtendedListener.trueDamage;

@SpirePatch(
        clz= AbstractMonster.class,
        method="damage"
)
public class AbstractMonster_DamageMod
{
    @SpirePrefixPatch
    public static void OnDamage_CheckTrueDamage(AbstractMonster __instance, DamageInfo info)
    {
        System.out.println("On Damage Function Modified \nDmg: " + info.output);
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