package mymod.behaviours.baseCardExtended;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.cards.extended.BaseCard;
import mymod.util.CardStats;

import java.util.ArrayList;
import java.util.List;

public class BaseCardExtended extends BaseCard {

    private static final List<CardUseListener> listeners = new ArrayList<>();

    public BaseCardExtended(String ID, CardStats info) {
        super(ID, info);
    }

    // Static method to add a listener
    public static void addListener(CardUseListener listener) {
        listeners.add(listener);
    }

    // Static method to remove a listener
    public static void removeListener(CardUseListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        System.out.println("OnPlayCard " + c.cardID);
        if (c != this)
            return;
        System.out.println("Listeners " + listeners.size());
        // Notify all listeners
        for (CardUseListener listener : listeners) {
            System.out.println(listener.getClass().getName());
            listener.onCardUse(c, m);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        System.out.println("OnUse");
    }
}