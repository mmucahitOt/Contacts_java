package contacts.userInterface.enums;

import java.util.InputMismatchException;

public enum Command {
    ADD("add"),
    LIST("list"),
    SEARCH("search"),
    DELETE("delete"),
    EDIT("edit"),
    COUNT("count"),
    INFO("info"),
    EXIT("exit"),
    BACK("back"),
    AGAIN("again"),
    MENU("menu");
    final String value;

    Command(String value) {
        this.value = value;
    }



    static public Command toCommand(String value) {
        switch (value) {
            case "add":
                return ADD;
            case "list":
                return LIST;
            case "search":
                return SEARCH;
            case "delete":
                return DELETE;
            case "edit":
                return EDIT;
            case "count":
                return COUNT;
            case "info":
                return INFO;
            case "back":
                return BACK;
            case "again":
                return AGAIN;
            case "menu":
                return MENU;
            case "exit":
                return EXIT;
            default:
                throw new InputMismatchException();
        }
    }
}
