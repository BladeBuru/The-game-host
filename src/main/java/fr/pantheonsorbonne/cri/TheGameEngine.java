package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.Player;

import java.util.List;

public abstract class TheGameEngine {

        protected void play(){

            Player player1 = new Player();
            Player player2 = new Player();

        }



    protected abstract boolean setPiles(/*...*/); 
    
    protected abstract void giveCardsToPlayer(/*...*/);

    protected  boolean playRound(Player firstPlayer, Player secondPlayer,String cardsPlay ){
        int tabcardsPlay[];
        // creéer une méthode pour sépare les différenet coupp
        //regarder la taille
        // poser au bon androit 
        // regarder si les cartes son préseznte dans le jeux
        // les poser et voir si ell erespecte les regle de pose
        //

    return true;
    }
    
    protected boolean playerWin(Player p1){

        //To Do
        //Verif de si il peut jouer ou si il a finit ses cartes à l'interieur de la fonction????
        return true;//to reform
    }

    protected static String getWinner(Player p1, Player p2){

        return;
    }

    protected abstract void declareWinner(String winner);

    protected abstract List<Integer> getCardsPlayed(Player player);

}
