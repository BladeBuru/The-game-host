package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.Player;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public getDrawCardsTest(){
        Player p1 = new Player(6);
        String s = p1.getDrawCards(Player.CARD_IN_HAND);
        System.out.println( s);
    //ToDO

    }

}
