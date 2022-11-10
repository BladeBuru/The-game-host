package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public abstract class TheGameEngine {


    protected void play() {
        Stack<Player> players = new Stack<>();
        players.push(new Player());
        players.push(new Player());

        //TODO
        while (){
            Player firstPlayer = players.pop();
            Player secondPlayer = players.pop();
            players.push(firstPlayer);
            players.push(secondPlayer);
            //TODO
            // recuperer les cartes joué par le premier joueur

            int failureCounter = 0 ;

            while (failureCounter < 3 ){

                if( playRound(firstPlayer,secondPlayer,splitString(cardsPlay))){
                    break;
                }
                failureCounter++;
            }



        }


        //Todo
        // Jouer des round en inversqnt a chaque fois les joeuur


        //Todo
        // si le round n'a pas été joué renvoyer les cartes au joueur et si plus de 3x arreter le jeu


    }


    protected abstract boolean setPiles(/*...*/);

    protected abstract void giveCardsToPlayer(/*...*/);

    protected boolean playRound(Player firstPlayer, Player secondPlayer, ArrayList<String> cardsPlay) {
        //Minimum two cards played and maximum number in hand
        if (cardsPlay.size() < 2 || cardsPlay.size() > firstPlayer.getCardInHand().size()) return false;
        //All the cards played are present in his hand
        for (String card : cardsPlay) {
            if (!firstPlayer.cardIsInHand(Integer.parseInt(card.substring(0, 2))))
                return false; // TODO Cganger la façon de récuperre le int
        }
        CardTray cardTray = new CardTray(firstPlayer.getAscendingStack(), firstPlayer.getDownStack(), secondPlayer.getAscendingStack(), secondPlayer.getDownStack());

        for (String card : cardsPlay) {
            if (!cardTray.poseCard(card)) return false;
        }

        //ToDo
        // Si tous c'est bien passser mettre a jour le plateau et retirer les cartes en main du joueur
        // calculer le nombre de carte à piocher piocher et renvoyer les cartes à piocher
        // revoyer les cartes joueur au joueur enemie
        // ...

        return true;
    }

    private ArrayList<String> splitString(String cardsPlay) {
        ArrayList<String> cardsPlaySplit = new ArrayList<>(Arrays.asList(cardsPlay.split(",")));
        return cardsPlaySplit;
    }
    
    protected boolean playerWin(Player p1) {
        return (p1.getSizeCardInHand() == 0 && p1.getSizePick() == 0);// to reform
    }

    protected static String getWinner(Player p1, Player p2) {


    }

    protected abstract void declareWinner(String winner);

    protected abstract List<Integer> getCardsPlayed(Player player);

}
