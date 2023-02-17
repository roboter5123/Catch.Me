package com.roboter5123.catchme.games;
import com.roboter5123.catchme.engine.Game;
import com.roboter5123.catchme.engine.Message;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Game {

    List<String> messages;

    public Chat() {

        messages = new ArrayList<>();
    }

    public Message getStatus(){

        Message message = new Message();
        StringBuilder messageBody = new StringBuilder();

        for (String s : messages) {

            messageBody.append(s).append("\n");
        }

        message.setBody(messageBody.toString());
        return message;
    }

    @Override
    public void changeStatus(Message message) {

        messages.add(message.getBody());
    }

}
