package fr.pantheonsorbonne.cri.guest.guest2;

import fr.pantheonsorbonne.cri.TheGameEngine;
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayerHardNetwork  {

    static final String playerId = "Player-les-indestructibles" ;
    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game theGame;

    static PlayerHard player ;

    public static void main(String[] args) {
        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        theGame = playerFacade.autoJoinGame("TheGame");
         player = new PlayerHard(playerId,-1,0);
        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(theGame);
            switch (command.name()) {
                case "cardsForYou":
                    handleCardsForYou(command);
                    break;
                case "playACard":
                    handlePlayACard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;
                case "updateStack":
                    handleUpdupdateStack(command);
                    break;

            }
        }

    }

    private static void handleCardsForYou(GameCommand command) {
       if (command.body().length()> 0)player.getCardsFrom(TheGameEngine.splitInteger(command.body()));
    }

    private static void handleUpdupdateStack(GameCommand command) {
        ArrayList<Integer> updateStack = TheGameEngine.splitInteger(command.body());
        player.updateStack(updateStack.get(0),updateStack.get(1),updateStack.get(2),updateStack.get(3));
    }

    private static void handlePlayACard(GameCommand command) {
        String bestMoov = player.bestMoov();
        playerFacade.sendGameCommandToAll(theGame, new GameCommand("card", bestMoov));
    }

    private static void handleGameOverCommand(GameCommand command) {
        if (command.body().equals("win")) {
            System.out.println("I've won!");
        } else {
            System.out.println("I've lost");
        }
       // System.exit(0);
    }




}
