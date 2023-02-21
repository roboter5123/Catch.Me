package com.roboter5123.catchme.games;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;
import com.roboter5123.catchme.engine.sessions.OutGoingGame;

public interface Game {

    OutGoingMessage changeStatus(IncomingMessage incomingMessage);
    OutGoingMessage joinGame();
    OutGoingMessage leaveGame();
    OutGoingGame out();
    String getSessionCode();
}
