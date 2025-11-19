package lotto_challenge;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.config.AppConfig;

public class ConsoleApplication {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();
        final InputHandler inputHandler = appConfig.inputHandler();
        final ConsoleClient consoleClient = appConfig.consoleClient(inputHandler);

        consoleClient.start();
    }
}
