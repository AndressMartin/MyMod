package mymod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static mymod.MyMod.makeID;

public class Dodge extends BasePower {
    public static final String POWER_ID = makeID("Dodge");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Dodge(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damage) {
        if (info.type != DamageInfo.DamageType.THORNS &&
                info.type != DamageInfo.DamageType.HP_LOSS &&
                info.owner != null &&
                info.owner != owner) {
            if (amount == 0) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
            } else {
                addToBot(new ReducePowerAction(owner, owner, ID, 1));
            }
            this.flash();
            return 0;
        }
        return damage;
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}