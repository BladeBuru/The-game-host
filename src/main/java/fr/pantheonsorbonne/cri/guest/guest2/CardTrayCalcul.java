package fr.pantheonsorbonne.cri.guest.guest2;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;

import java.util.ArrayList;

public class CardTrayCalcul extends CardTray {
    protected int score ;

    public CardTrayCalcul(int ascendingStackAlly, int downAllyStack, int ascendingEnemyStack, int downEnemyStack) {
        super(ascendingStackAlly, downAllyStack, ascendingEnemyStack, downEnemyStack);
        this.score = 0;
    }

    public CardTrayCalcul(Player allyPlayer, Player enemyPlayer) {
        this(allyPlayer.getAscendingStack(), allyPlayer.getDownStack(), enemyPlayer.getAscendingStack(), enemyPlayer.getDownStack());
    }

    public CardTrayCalcul(PlayerEngine player){
        this(player.getAscendingStack(),player.getDownStack(), player.getAscendingStackEnemy(), player.getDownStackEnemy());
    }

    @Override
    protected boolean poseAscendingStackAlly(int card) {
        if(card > this.ascendingAllyStack || card == this.ascendingAllyStack - 10){
            score += card - this.ascendingAllyStack;
            this.ascendingAllyStack = card;
           return true;
        }
        return false;
    }



    @Override
    protected boolean poseDownStackAlly(int card) {
        if(card < this.downAllyStack || card == this.downAllyStack + 10){
            score +=  this.downAllyStack -  card;
            this.downAllyStack = card;
           return true;
        }
        return false;
    }
    @Override
    protected boolean poseAscendingStackEnemy(int card) {
        if(card < this.ascendingEnemyStack && this.playEnemyStack == false){
            score += this.ascendingEnemyStack - card +1;
            this.ascendingEnemyStack = card;
            this.playEnemyStack = true;
           return true;
        }
        return false;
    }
    @Override
    protected boolean poseDownStackEnemy(int card) {
        if(card > this.downEnemyStack && this.playEnemyStack == false){
            score += card  - this.downEnemyStack + 1;
            this.downEnemyStack = card;
            this.playEnemyStack = true;
           return true;
        }
        return false;
    }

    protected boolean poseAllCard(ArrayList<String> cards){
        boolean valid = true;
        for (String card: cards) {
            if (!poseCard(card)) valid = false ;
        }

        score -= cards.size() * 3;
        return valid;
    }

    public int getScore() {
       return this.score ;
    }
}
