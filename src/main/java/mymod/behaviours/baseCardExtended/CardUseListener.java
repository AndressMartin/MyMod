package mymod.behaviours.baseCardExtended;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

// Listener interface
public interface CardUseListener {
    void onCardUse(AbstractCard card, AbstractMonster monster);
}
