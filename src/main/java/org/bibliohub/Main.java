package org.bibliohub;

import org.bibliohub.config.SeedData;
import org.bibliohub.service.MenuService;

public class Main {
    public static void main(String[] args) {
        SeedData seedData = new SeedData();
        seedData.seedData();

        final MenuService menuService = MenuService.getInstance();
        int option = -1;
        while (option != 0) {
            menuService.printMenu();
            option = menuService.waitForOption();
            menuService.chooseOption(option);
        }
    }
}