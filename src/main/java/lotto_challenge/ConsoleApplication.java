package lotto_challenge;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.config.AppConfig;
import lotto_challenge.core.controller.MainController;

public class ConsoleApplication {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();
        final InputHandler inputHandler = appConfig.inputHandler();
        final MainController mainController = appConfig.mainController();
        final ConsoleClient consoleClient = appConfig.consoleClient(inputHandler, mainController);

        consoleClient.start();
    }
}
