package contacts;


import contacts.userInterface.UserInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename;
        if (args == null || args.length == 0) {
            filename = null;
        } else {
            filename = args[0];
        }
        UserInterface app = new UserInterface();
        app.initSerialization(filename);
        app.initContacts();
        app.loop();
    }
}

