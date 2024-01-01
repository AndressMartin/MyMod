package mymod.behaviours.spireModifications;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static mymod.behaviours.baseCardExtended.BaseCardExtendedListener.CheckForTrueDamage;

@SpirePatch2(
        clz= AbstractPower.class,
        method="atDamageGive",
        paramtypez={
                float.class,
                DamageInfo.DamageType.class,
                AbstractCard.class,
        }
)
public class Power_DamageGivePatch {
    @SpirePostfixPatch
    public static float Postfix(float __result, AbstractPower __instance, float damage, AbstractCard card)
    {
        if(__result < damage){
            if (CheckForTrueDamage(card)){
//                System.out.println("Negating Weak due to TrueDamage. " +
//                        "\nDamage: " + damage +
//                        "\nResult: " + __result);
                return damage;
            }
        }
        return __result;
    }
}
