package mymod.spireModifications;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;

@SpirePatch(
        clz= IntangiblePower.class,
        method="atDamageFinalReceive"
)
public class IntangiblePowerModifications
{
    static float trueDamage = 0;
    @SpireInsertPatch(
            rloc=0
    )
    public static void InsertTrueDamageVar(IntangiblePower __instance, float damage, DamageInfo.DamageType type)
    {
        trueDamage = damage;
        System.out.println("IntangiblePowerModifications InsertTrueDamageVar trueDamage " + trueDamage
                + " output " + damage
                + "type " + type);
    }

    @SpireInsertPatch(
            rloc=3
    )
    public static void UseTrueDamage(IntangiblePower __instance, float damage, DamageInfo.DamageType type)
    {
        System.out.println("IntangiblePowerModifications UseTrueDamage trueDamage " + trueDamage
                + " output " + damage
                + "type " + type);
        if(type == TrueDamageEnum.TRUE_DAMAGE){
            damage = trueDamage;
            System.out.println("IntangiblePowerModifications UseTrueDamage IF trueDamage " + trueDamage
                    + " output " + damage
                    + "type " + type);
        }
    }
}