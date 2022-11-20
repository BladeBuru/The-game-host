package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheGameEngineTest
{

    @Test
    public void isCardDuplicatesTrue()
    {
        ArrayList<Integer> cards = new ArrayList<>(Arrays.asList(1, 1, 32, 41, 56, 60));
        assertEquals(true, TheGameEngine.cardsIsDuplicates(cards));
    }

    @Test
    public void isCardDuplicatesFalse()
    {
        ArrayList<Integer> cards = new ArrayList<>(Arrays.asList(1, 15, 32, 41, 56, 60));
        assertEquals(false, TheGameEngine.cardsIsDuplicates(cards));
    }

    public void isSplitStringTrue()
    {
        ArrayList<String> cardsSplit = new ArrayList<>(Arrays.asList("05^A","57vE"));
        assertTrue( cardsSplit.equals(TheGameEngine.splitString("05^A,57vE")));
    }

}
