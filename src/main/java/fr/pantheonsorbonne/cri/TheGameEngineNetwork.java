package fr.pantheonsorbonne.cri;

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

    //...
}
