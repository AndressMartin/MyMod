package mymod.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import mymod.character.TheNimbus;

import static mymod.MyMod.makeID;

public class InsidersMap extends BaseRelic {
    private static final String NAME = InsidersMap.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private static final int STRENGTH = 1; //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.

    public InsidersMap() {
        super(ID, NAME, TheNimbus.Enums.CARD_COLOR, RARITY, SOUND);
    }


    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        super.onUseCard(targetCard, useCardAction);
    }
}
