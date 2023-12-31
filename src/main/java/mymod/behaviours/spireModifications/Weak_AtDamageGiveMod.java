package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.WeakPower;
import mymod.modifiers.TrueDamage;

import static mymod.behaviours.baseCardExtended.BaseCardExtendedListener.lastCardsPlayed;

@SpirePatch(
        clz= WeakPower.class,
        method="atDamageGive"
)
public class Weak_AtDamageGiveMod {
    static float incomingDamage;
    @SpirePrefixPatch
    public static void atDamage_SaveIncoming(WeakPower __instance, float damage, DamageInfo.DamageType type)
    {
        incomingDamage = damage;
    }

    @SpirePostfixPatch
    public static float atDamage_CheckTrueDamage(float __result, WeakPower __instance, float damage, DamageInfo.DamageType type)
    {
        if (__result < incomingDamage){
            if(lastCardsPlayed[0] == null)
                return __result;
            if(DamageModifierManager.modifiers(lastCardsPlayed[0]).isEmpty())
                return __result;

            if (DamageModifierManager.modifiers(lastCardsPlayed[0]).stream()
                    .anyMatch(modifier -> modifier.getClass() == TrueDamage.class)) {
                System.out.println("Blocking effect of Weak due to True Damage.");
                return incomingDamage;
            }
        }
        return __result;
    }
}
