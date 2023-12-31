package mymod.cards.extended;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.cards.BaseCardExtended;

// Listener interface
public interface CardUseListener {
    void onCardUse(AbstractCard card, AbstractMonster monster);
}
