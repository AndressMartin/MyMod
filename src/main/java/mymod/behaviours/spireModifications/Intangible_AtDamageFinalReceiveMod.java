package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import mymod.modifiers.TrueDamage;

import static mymod.behaviours.baseCardExtended.BaseCardExtendedListener.lastCardsPlayed;

@SpirePatch(
        clz= IntangiblePower.class,
        method="atDamageFinalReceive"
)
public class Intangible_AtDamageFinalReceiveMod {
    static float incomingDamage;
    @SpirePrefixPatch
    public static void atDamage_SaveIncoming(IntangiblePower __instance, float damage, DamageInfo.DamageType type)
    {
        incomingDamage = damage;
    }

    @SpirePostfixPatch
    public static float atDamage_CheckTrueDamage(float __result, IntangiblePower __instance, float damage, DamageInfo.DamageType type)
    {
        if (__result < incomingDamage){
            if(lastCardsPlayed[0] == null)
                return __result;
            if(DamageModifierManager.modifiers(lastCardsPlayed[0]).isEmpty())
                return __result;

            if (DamageModifierManager.modifiers(lastCardsPlayed[0]).stream()
                    .anyMatch(modifier -> modifier.getClass() == TrueDamage.class)) {
                System.out.println("Blocking effect of Intangible due to True Damage.");
                return incomingDamage;
            }
        }
        return __result;
    }
}