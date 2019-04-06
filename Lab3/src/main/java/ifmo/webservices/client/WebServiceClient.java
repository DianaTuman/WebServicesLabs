package ifmo.webservices.client;

import ifmo.webservices.Character;
import ifmo.webservices.CharacterFieldValue;
import ifmo.webservices.CharacterService;
import ifmo.webservices.Field;
import ifmo.webservices.errors.DatabaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

enum MenuOption {AddCondition, Print, Clear, Find, ShowAll, Add, Delete, Modify, Exit}

public class WebServiceClient {

    private static final String standaloneUrl =
            "http://namiwave-GP60-2PE:8081/Character7334838576781084093/CharacterService?wsdl";

    private String url;
    private CharacterService characterService;

    private List<CharacterFieldValue> conditions = new ArrayList<CharacterFieldValue>();

    public WebServiceClient(String serviceUrl) {
        this.url = serviceUrl;
    }

    public static void main(String[] args){
        WebServiceClient client = new WebServiceClient(standaloneUrl);
        client.startListening();
    }

    private void startListening() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            URL url = new URL(this.url);
            this.characterService = new CharacterService(url);

            while (true) {

                    printMenu();
                    processOption(in);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("------------Menu------------");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, getOptionText(options[i]));
        }
    }

    private int readIntValue(BufferedReader in) throws IOException {
        int option = -1;

        String input = in.readLine();
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return option;
        }

        return option;
    }

    private void processOption(BufferedReader in) throws IOException, DatabaseException {
        int option = readIntValue(in);

        if (option < 1 || option > MenuOption.values().length) {
            System.err.println("Wrong option");
            return;
        }

        MenuOption menuOption = MenuOption.values()[option - 1];

        switch (menuOption) {
            case AddCondition:
                addCondition(in);
                break;
            case Find:
                findResults();
                break;
            case Print:
                printConditions();
                break;
            case Clear:
                clearConditions();
                break;
            case ShowAll:
                showAll();
                break;
            case Add:
                add(in);
                break;
            case Delete:
                delete(in);
                break;
            case Modify:
                modify(in);
                break;
            case Exit:
                exit();
                break;
        }
    }

    private String getOptionText(MenuOption menuOption) {
        switch (menuOption) {
            case AddCondition:
                return "AddCondition search condition";
            case Find:
                return "Find results";
            case Print:
                return "Print saved conditions";
            case Clear:
                return "Clear saved conditions";
            case ShowAll:
                return "Show all characters";
            case Add:
                return "Add new character";
            case Delete:
                return "Delete character";
            case Modify:
                return "Modify character";
            case Exit:
                return "Exit";
            default:
                return "Option not supported";
        }
    }

    private void addCondition(BufferedReader in) throws IOException {
        System.out.println("Choose field:");

        Field[] fields = Field.values();
        for (int i = 0; i < fields.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, fields[i]);
        }

        int field = readIntValue(in);

        if (field < 1 || field > fields.length) {
            System.err.println("Wrong option");
            return;
        }

        System.out.println("Print expected field value:");
        String value = in.readLine();

        CharacterFieldValue condition = new CharacterFieldValue(fields[field - 1], value);
        this.conditions.add(condition);

        System.out.println("Condition saved: " + condition);
    }

    private void findResults() throws DatabaseException {
        List<Character> characters = this.characterService.getCharacterWebServicePort().
                getCharacters(this.conditions);
        printCharacters(characters);
    }

    private void printConditions() {
        if (this.conditions.size() == 0) {
            System.out.println("No conditions saved");
            return;
        }

        System.out.println("Saved conditions:");

        for (CharacterFieldValue condition : this.conditions) {
            System.out.println(condition);
        }
    }

    private void clearConditions() {
        this.conditions.clear();
        System.out.println("Saved conditions cleared");
    }

    private void showAll() throws DatabaseException{
        List<Character> characters = this.characterService.getCharacterWebServicePort().
                getAllCharacters();
        printCharacters(characters);
    }

    private void exit() {
        System.exit(0);
    }

    private void printCharacters(List<Character> characters) {
        for (Character character : characters) {
            System.out.println(character);
        }
        System.out.println("Total characters: " + characters.size());
    }

    private void add(BufferedReader in) {
        Character character = new Character();
        try {
            Field[] fields = Field.values();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != Field.ID) {
                    System.out.printf("Print '%s' value:\n", fields[i]);
                    character.setField(fields[i], in.readLine());
                }
            }

            int id = this.characterService.getCharacterWebServicePort().addCharacter(character);
            System.out.println("Character added: id = " + id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void delete(BufferedReader in) {
        try {
            System.out.println("Print id:");
            int id = readIntValue(in);
            if (id < 0) {
                return;
            }

            boolean success = this.characterService.getCharacterWebServicePort().deleteCharacter(id);
            if (success) {
                System.out.println("Character deleted");
            } else {
                System.out.println("Character deletion failed");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void modify(BufferedReader in) {
        try {
            System.out.println("Print id:");
            int id = readIntValue(in);
            if (id < 0) {
                return;
            }

            List<CharacterFieldValue> newValues = new ArrayList<CharacterFieldValue>();

            Field[] fields = Field.values();

            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != Field.ID) {
                    System.out.printf("Print '%s' value (to skip press enter):\n", fields[i]);
                    String value = in.readLine();

                    if (!value.isEmpty()) {
                        newValues.add(new CharacterFieldValue(fields[i], value));
                    }
                }
            }

            boolean success = this.characterService.getCharacterWebServicePort().modifyCharacter(id, newValues);
            if (success) {
                System.out.println("Character modified");
            } else {
                System.out.println("Character modification failed");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}