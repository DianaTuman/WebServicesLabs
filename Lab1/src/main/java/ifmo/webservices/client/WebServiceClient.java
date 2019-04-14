package ifmo.webservices.client;

import ifmo.webservices.Character;
import ifmo.webservices.CharacterFieldValue;
import ifmo.webservices.CharacterService;
import ifmo.webservices.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

enum MenuOption {Add, Print, Clear, Find, ShowAll, Exit}

public class WebServiceClient {

    private static final String standaloneUrl =
            "http://localhost:8081/Character943981846163911436/CharacterService?wsdl";
    private static final String j2eeUrl =
            "http://localhost:8081/Character943981846163911436/CharacterService?wsdl";

    private String url;
    private CharacterService characterService;

    private List<CharacterFieldValue> conditions = new ArrayList<CharacterFieldValue>();

    public WebServiceClient(String serviceUrl) {
        this.url = serviceUrl;
    }

    public static void main(String[] args) throws MalformedURLException {
        WebServiceClient client = new WebServiceClient(j2eeUrl);
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

        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
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

    private int readOption(BufferedReader in) throws IOException {
        int option = -1;

        String input = in.readLine();
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Wrong option");
        }

        return option;
    }

    private void processOption(BufferedReader in) throws IOException {
        int option = readOption(in);

        if (option < 1 || option > MenuOption.values().length) {
            System.err.println("Wrong option");
            return;
        }

        MenuOption menuOption = MenuOption.values()[option - 1];

        switch (menuOption) {
            case Add:
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
            case Exit:
                exit();
                break;
        }
    }

    private String getOptionText(MenuOption menuOption) {
        switch (menuOption) {
            case Add:
                return "Add search condition";
            case Find:
                return "Find results";
            case Print:
                return "Print saved conditions";
            case Clear:
                return "Clear saved conditions";
            case ShowAll:
                return "Show all characters";
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

        int field = readOption(in);

        if (field < 1 || field > fields.length) {
            System.err.println("Wrong option");
        }

        System.out.println("Print expected field value:");
        String value = in.readLine();

        CharacterFieldValue condition = new CharacterFieldValue(fields[field - 1], value);
        this.conditions.add(condition);

        System.out.println("Condition saved: " + condition);
    }

    private void findResults() {
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

    private void showAll() {
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
}