package org.ccguyka.slacker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SlackerCli {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackerCli.class);

    private SlackerCli() {
        // prevent instantiation
    }

    public static void main(String[] args) throws Exception {
        MessageCreator messageCreator = new MessageCreator();
        Message message = messageCreator.from(args);
        if (message == null) {
            messageCreator.printHelp();
            return;
        }

        LOGGER.info("Try to send message {} to destination {}", message.getMessageText(),
            message.getDestination());

        Slacker slacker = new Slacker(message);
        slacker.send();

        LOGGER.info("Message successfully sent");
    }
}
