package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.pantheonsorbonne.cri.game.PlayerEngine;
import fr.pantheonsorbonne.cri.guest.guest2.PlayerEasy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class PlayerEngineTest
{

    PlayerEngine player = new PlayerEasy("Test");

    @Test
    public void updateStackTrue()
    {
        player.updateStack(15,15,15,15);
        assertTrue((player.getAscendingStack() == 15 && player.getDownStack() == 15 && player.getAscendingStackEnemy() == 15 && player.getDownStackEnemy() == 15));
    }

    @Test
    public void putCardsInHandTrue() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(15);
        player.getCardsFrom(test);
        assertFalse((player.getCardInHand().get(0) == 15 && player.getCardInHand().size() == 0));
    }
}
