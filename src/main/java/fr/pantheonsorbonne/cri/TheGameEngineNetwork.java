package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TheGameEngineNetwork extends TheGameEngine {

    private static final int PLAYER_COUNT = 3;

    private final HostFacade hostFacade;
    private final Set<String> players;
    private final Game theGame;

    public TheGameEngineNetwork(HostFacade hostFacade, Set<String> players, fr.pantheonsorbonne.miage.model.Game theGame) {
        this.hostFacade = hostFacade;
        this.players = players;
        this.theGame = theGame;
    }

    public static void main(String[] args) {
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        hostFacade.createNewPlayer("Host");
        fr.pantheonsorbonne.miage.model.Game ourGame = hostFacade.createNewGame("TheGame");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);


        TheGameEngine host = new TheGameEngineNetwork(hostFacade, ourGame.getPlayers(), ourGame);
        host.play();
    }


    @Override
    protected List<String> getInitialPlayers() {
        ArrayList<String> initialPLayers = new ArrayList<>();
        for (String player: players) {
            initialPLayers.add(player);
        }
        return initialPLayers;
    }

    @Override
    protected void declareWinner(String winner) {
        hostFacade.sendGameCommandToPlayer(theGame, winner, new GameCommand("gameOver", "win"));
        String loser = "";
        for (String player : players) {
            if (!player.equals(winner)) loser = player;
        }
        hostFacade.sendGameCommandToPlayer(theGame, loser , new GameCommand("gameOver", "lost"));
        System.out.println(winner + " has won !!!");
    }

    @Override
    protected ArrayList<String> getCardsPlayed(String player) {
        hostFacade.sendGameCommandToPlayer(theGame, player, new GameCommand("playACard"));
        GameCommand CardsPlayed = hostFacade.receiveGameCommand(theGame);

        return  splitString(CardsPlayed.body());
    }

    @Override
    protected void giveCardsPlayer(String player, String cards) {
        hostFacade.sendGameCommandToPlayer(theGame, player, new GameCommand("cardsForYou", cards));
    }

    @Override
    protected void updateStacksPlayer(String player, int ascendingStackAlly, int downStackAlly, int ascendingStackEnemy, int downStackEnemy) {
       String cards = "" + ascendingStackAlly + "," + downStackAlly + "," + ascendingStackEnemy + "," + downStackEnemy;
        hostFacade.sendGameCommandToPlayer(theGame, player, new GameCommand("updateStack", cards));
    }
}