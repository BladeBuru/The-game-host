package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.CardTray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTrayTest
{
    @Test
    public void isPoseCardAllyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        String card;
        //Ascending True
        card = "05^A";
        assertEquals(true, cardTray.poseCard(card));
        card = "20^A";
        assertEquals(true, cardTray.poseCard(card));
        //Down True
        card = "55vA";
        assertEquals(true, cardTray.poseCard(card));
        card = "40vA";
        assertEquals(true, cardTray.poseCard(card));
    }

    @Test
    public void isPoseCardAllyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        String card;
        //Ascending False
        card = "01^A";
        assertEquals(false, cardTray.poseCard(card));
        //Down False
        card = "59vA";
        assertEquals(false, cardTray.poseCard(card));
    }

    @Test
    public void isPoseCardEnemyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        String card;
        //Ascending True
        card = "15^E";
        assertEquals(true, cardTray.poseCard(card));
        card = "45vE";
        assertEquals(false, cardTray.poseCard(card));
        //Down True
        CardTray cardTrayy = new CardTray(15,45,20,40);
        card = "45vE";
        assertEquals(true, cardTrayy.poseCard(card));
    }

    @Test
    public void isPoseCardEnemyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        String card;
        //Ascending False
        card = "25^E";
        assertEquals(false, cardTray.poseCard(card));
        //Down False
        card = "35vE";
        assertEquals(false, cardTray.poseCard(card));
    }

    @Test
    public void isPoseAscendingStackAllyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(true, cardTray.poseAscendingStackAlly(5));
        assertEquals(true, cardTray.poseAscendingStackAlly(20));
    }

    @Test
    public void isPoseAscendingStackAllyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(false, cardTray.poseAscendingStackAlly(10));
    }

    @Test
    public void isPoseAscendingStackEnemyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(true, cardTray.poseAscendingStackEnemy(15));
    }

    @Test
    public void isPoseAscendingStackEnemyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(false, cardTray.poseAscendingStackEnemy(25));
    }

    @Test
    public void isPoseDownStackAllyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(true, cardTray.poseCard("55vA"));
        assertEquals(true, cardTray.poseCard("40vA"));
    }

    @Test
    public void isPoseDownStackAllyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(false, cardTray.poseDownStackAlly(50));
    }

    @Test
    public void isPoseDownStackEnemyTrue()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(true, cardTray.poseDownStackEnemy(45));
    }

    @Test
    public void isPoseDownStackEnemyFalse()
    {
        CardTray cardTray = new CardTray(15,45,20,40);
        assertEquals(false, cardTray.poseDownStackEnemy(25));
    }

}
