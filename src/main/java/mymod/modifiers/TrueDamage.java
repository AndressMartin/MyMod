package mymod.modifiers;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import mymod.MyMod;
import mymod.spireModifications.TrueDamageEnum;

public class TrueDamage extends AbstractDamageModifier {
    public static final String ID = MyMod.makeID("TrueDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    TooltipInfo leechTooltip = null;
//    DamageInfo.DamageType damageType = DamageInfo.DamageType.HP_LOSS;

    public float incomingDamage = 0;

    @Override
    public boolean affectsDamageType(DamageInfo.DamageType type) {
        type = TrueDamageEnum.TRUE_DAMAGE;
        return true;
    }

    public TrueDamage() {

    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        System.out.println("atDamageGive " + damage + " damageType " + type);
        type = TrueDamageEnum.TRUE_DAMAGE;
        return super.atDamageGive(damage, type, target, card);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        System.out.println("atDamageFinalGive " + damage + " damageType " + type);
        type = TrueDamageEnum.TRUE_DAMAGE;
        return super.atDamageFinalGive(damage, type, target, card);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        System.out.println("onAttack " + damageAmount + " damageType " + info.type);
        info.type = TrueDamageEnum.TRUE_DAMAGE;
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        System.out.println("onAttackToChangeDamage " + damageAmount + " damageType " + info.type);
        info.type = TrueDamageEnum.TRUE_DAMAGE;
        return super.onAttackToChangeDamage(info, damageAmount, target);
    }

    //    @Override
//    public boolean ignoresThorns() {
//        return true;
//    }
//
//    //This hook grabs the lastDamageTaken once it is updated upon attacking the monster.
//    //This lets us heal the attacker equal to the damage that was actually dealt to the target
//    @Override
//    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature targetHit) {
//        if (lastDamageTaken > 0) {
//            this.addToBot(new HealAction(info.owner, info.owner, lastDamageTaken));
//        }
//    }
//
//    @Override
//    public boolean affectsDamageType(DamageInfo.DamageType type) {
//        return true;
//    }
//
//    @Override
//    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
//        System.out.println("atDamageGive " + damage + " damageType " + type);
//        incomingDamage = damage;
//        return super.atDamageGive(damage, type, target, card);
//    }
//
//    @Override
//    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
//        float dmg = damage;
//        if (damage < incomingDamage){
//            dmg = incomingDamage;
//        }
//        System.out.println("atDamageFinalGive " + damage + " newDmg " + dmg + "damageType " + type);
//        return super.atDamageFinalGive(dmg, type, target, card);
//    }
//
//    @Override
//    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature target) {
//        int block = target.currentBlock;
//        int remainingDmg = (int) incomingDamage - block;
//        if (remainingDmg > 0 ){
//            System.out.println("unblockedAmount " + remainingDmg + " blockedAmount " + block);
//            super.onDamageModifiedByBlock(info, remainingDmg, block, target);
//        }
//        System.out.println("unblockedAmount " + unblockedAmount + " blockedAmount " + incomingDamage);
//        super.onDamageModifiedByBlock(info, unblockedAmount, (int)incomingDamage, target);
//    }
//
//    @Override
//    public int onAttackToChangeDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
//        int dmg = damageAmount;
//        if (damageAmount < incomingDamage){
//            dmg = (int)incomingDamage;
//        }
//        System.out.println("onAttackToChangeDamage " + damageAmount + " newDmg " + dmg);
//        return super.onAttackToChangeDamage(info, dmg, target);
//    }
//
//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        int dmg = damageAmount;
//        if (damageAmount < incomingDamage){
//            dmg = (int)incomingDamage;
//        }
//        System.out.println("onAttack " + damageAmount + " newDmg " + dmg);
//        super.onAttack(info, dmg, target);
//    }

    //    //This hook allows up to add a custom tooltip to any cards it is added to.
//    //In this case, we are using cardstrings to get the localized data
//    @Override
//    public ArrayList<TooltipInfo> getCustomTooltips() {
//        ArrayList<TooltipInfo> array = new ArrayList();
//        if (leechTooltip == null) {
//            leechTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
//            array.add(leechTooltip);
//        }
//        return array;
//    }

    //This allows us to add an stslib descriptor to the card
    // If it was originally an Attack, it will now read "Attack | Leech"
    @Override
    public String getCardDescriptor() {
        return cardStrings.NAME;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return null;
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

}