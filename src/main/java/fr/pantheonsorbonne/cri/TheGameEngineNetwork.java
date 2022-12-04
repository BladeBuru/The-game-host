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
    private final Game ourGame;

    public TheGameEngineNetwork(HostFacade hostFacade, Set<String> players, fr.pantheonsorbonne.miage.model.Game ourGame) {
        this.hostFacade = hostFacade;
        this.players = players;
        this.ourGame = ourGame;
    }

    public static void main(String[] args) {
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady;

        hostFacade.createNewPlayer("Host");
        fr.pantheonsorbonne.miage.model.Game ourGame = hostFacade.createNewGame("The Game");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        TheGameEngine host = new TheGameEngineNetwork(hostFacade, ourGame.getPlayers(), ourGame);
        host.play();
    }


    @Override
    protected List<String> getInitialPlayers() {
        return null;
    }

    @Override
    protected void declareWinner(String winner) {

    }

    @Override
    protected ArrayList<String> getCardsPlayed(String player) {
        return null;
    }

    @Override
    protected void giveCardsPlayer(String player, String cards) {

    }

    @Override
    protected void updateStacksPlayer(String player, int ascendingStackAlly, int downStackAlly, int ascendingStackEnemy, int downStackEnemy) {

    }
}