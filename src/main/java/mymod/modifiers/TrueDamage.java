package mymod.modifiers;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import mymod.MyMod;

public class TrueDamage extends AbstractDamageModifier {
    public static final String ID = MyMod.makeID("TrueDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    TooltipInfo trueDamageTooltip = null;

    @Override
    public boolean ignoresThorns() {
        return true;
    }

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