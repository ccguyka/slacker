package org.ccguyka.slacker;

class SlackerCli {

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
        Slacker slacker = new Slacker(message);
        slacker.send();
    }
}
