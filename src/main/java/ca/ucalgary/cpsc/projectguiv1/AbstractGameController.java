package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.boardGames.Game;

public abstract class AbstractGameController implements BoardGameObserver {

    AbstractBoardGame game;

    abstract public void setUpBoard(String board);
    protected String currentPlayer;

    protected User user1= new User("1", "firsstUsr", "email@google.com");

    protected User user2= new User("2", "scndUsr", "otheremail@google.com");

    @Override
    public void update(String element) {
        String[] list=element.split("\n");
        switch (list[0]) {
            case "Setup":
                StringBuilder builder = new StringBuilder();
                for (int i = 2; i < list.length; i++) {
                    builder.append(list[i]).append("\n");
                }
                setUpBoard(builder.toString());
                break;

            case "TurnEnd":
                testUpdate = list[2];
                break;
            case "GameEnd":
                testUpdate = list[2];
                break;
    }
}

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
