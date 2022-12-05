package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.Player;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    Player test = new Player();

    @Test
    public void getDrawCardsTest(){
        Player p1 = new Player(14);
        String s = p1.getDrawCards(Player.CARD_IN_HAND );
    }

    @Test
    public void cardIsInHandTrue(){
        ArrayList<Integer> testt = new ArrayList<>();
        testt.add(15);
        test.setCardInHand(testt);
        assertTrue(test.cardIsInHand(15));
    }

}
