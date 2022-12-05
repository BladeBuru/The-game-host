package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.guest.guest2.PlayerEngine;
import fr.pantheonsorbonne.cri.guest.guest2.PlayerHard;


import java.util.*;

public class LocalTheGame extends TheGameEngine {
   static   HashMap<String, Integer> scores = new HashMap<>();
    private final Map<String, PlayerEngine> players;

    public LocalTheGame() {
        players = new HashMap<>();

        players.put("joueur 1", new PlayerHard("joueur 1" ,-1,-1));
        players.put("joueur 2" , new PlayerHard("joueur 2",-1,0));


    }

    public static void main(String[] args) {

        int i = 0;
        while (i < 100) {
            LocalTheGame localTheGame = new LocalTheGame();
            localTheGame.play();
            i++;
        }

    }

    @Override
    protected List<String> getInitialPlayers() {
        ArrayList<String> initialPlayers = new ArrayList<String>();
        for (Map.Entry player : this.players.entrySet()) {
            initialPlayers.add((String) player.getKey());
        }

        return initialPlayers;
    }

    @Override
    protected void declareWinner(String winner) {
        if (this.scores.containsKey(winner)){
            this.scores.put(winner, scores.get(winner).intValue() + 1);
            //this.scores.put(winner, scores.getOrDefault((winner), 1).intValue() + 1);
        }else {
            this.scores.put(winner, 1);
        }
        System.out.println(winner + " has won!");
        System.out.println("\n\n");
        for (Map.Entry score : this.scores.entrySet()) {
            System.out.println("Joueur " + score.getKey() + " win " + score.getValue());
        }

    }

    @Override
    protected ArrayList<String> getCardsPlayed(String player) {
        return this.splitString(this.players.get(player).bestMoov());
    }

    @Override
    protected void giveCardsPlayer(String player, String cards) {
        if (cards.length() != 0 )this.players.get(player).getCardsFrom(this.splitInteger(cards));

    }

    @Override
    protected void updateStacksPlayer(String player, int ascendingStackAlly, int downStackAlly, int ascendingStackEnemy, int downStackEnemy) {
        this.players.get(player).updateStack(ascendingStackAlly, downStackAlly, ascendingStackEnemy, downStackEnemy);
    }
}
