package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.Player;
import fr.pantheonsorbonne.cri.guest.guest2.PlayerEngine;
import fr.pantheonsorbonne.cri.guest.guest2.PlayerMedium;

import java.util.*;

public class LocalTheGame extends TheGameEngine{

    private final Map<String, PlayerEngine> players;
    public  LocalTheGame(){
        players = new HashMap<>();
        for (int i = 1; i < 3; i++) {
            players.put("joueur "+i,new PlayerMedium("joueur "+i));
        }

    }

    public static void main(String[] args) {


        LocalTheGame localTheGame = new LocalTheGame();
        localTheGame.play();
    }

    @Override
    protected List<String> getInitialPlayers() {
        ArrayList<String> initialPlayers = new ArrayList<String>();
        for (Map.Entry player:   this.players.entrySet()) {
            initialPlayers.add((String) player.getKey());
        }

        return initialPlayers;
    }

    @Override
    protected void declareWinner(String winner) {

        System.out.println(winner + " has won!");

    }

    @Override
    protected ArrayList<String> getCardsPlayed(String player) {
        return this.splitString( this.players.get(player).bestMoov());
    }

    @Override
    protected void giveCardsPlayer(String player, String cards) {
        this.players.get(player).getCardsFrom(this.splitInteger(cards));

    }

    @Override
    protected void updateStacksPlayer(String player, int ascendingStackAlly, int downStackAlly, int ascendingStackEnemy, int downStackEnemy) {
        this.players.get(player).updateStack(ascendingStackAlly,downStackAlly,ascendingStackEnemy,downStackEnemy);
    }
}
