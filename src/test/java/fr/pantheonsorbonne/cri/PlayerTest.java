package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.Player;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    public static void main(String[] args) {
        new PlayerTest().getDrawCardsTest();
    }
    //@Test
    public void getDrawCardsTest(){
        Player p1 = new Player(14);
        String s = p1.getDrawCards(Player.CARD_IN_HAND );
        System.out.println("Test"+ s);
    //ToDO

    }

}
