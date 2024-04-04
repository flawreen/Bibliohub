package org.bibliohub;

import org.bibliohub.service.MenuService;

public class Main {
    public static void main(String[] args) {
        final MenuService menuService = MenuService.getInstance();
        menuService.chooseUser();
        int option = -1;
        while (option != 0) {
            menuService.printMenu();
            option = menuService.waitForOption();
            menuService.chooseOption(option);
        }
    }
}