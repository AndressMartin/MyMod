//package mymod.spireModifications;

//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;

//@SpirePatch(
//        clz= AbstractMonster.class,
//        method="damage"
//)
//public class AbstractMonsterModifications
//{
//    static int trueDamage = 0;
//    @SpireInsertPatch(
//            rloc=0
//    )
//    public static void InsertTrueDamageVar(AbstractMonster __instance, DamageInfo info)
//    {
//        trueDamage = info.output;
//        System.out.println("InsertTrueDamageVar trueDamage " + trueDamage + " output " + info.output);
//    }
//
//    @SpireInsertPatch(
//            rloc=3
//    )
//    public static void UseTrueDamage(AbstractMonster __instance, DamageInfo info)
//    {
//        System.out.println("UseTrueDamage trueDamage " + trueDamage + " output " + info.output);
//        if(info.type == TrueDamageEnum.TRUE_DAMAGE){
//            info.output = trueDamage;
//            System.out.println("UseTrueDamage IF trueDamage " + trueDamage + " output " + info.output);
//        }
//    }
//}