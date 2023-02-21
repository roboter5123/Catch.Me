package com.roboter5123.catchme.engine.messages;
import com.roboter5123.catchme.engine.Player;

public class IncomingMessage {

    private Player player;

    private Command command;

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {

        this.player = player;
    }

    public Command getCommand() {

        return command;
    }

    public void setCommand(Command command) {

        this.command = command;
    }
}
