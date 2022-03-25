import java.io.*;
import java.util.*;

public class SlangWord {
    public static void main(String[] args) {
        System.out.println("Slang Word");
    }

    public final static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseScreen() {
        System.out.println("Press Any Key To Continue...");
        new Scanner(System.in).nextLine();
    }

    public static HashMap<String, List<String>> dictionarySlang = new HashMap<String, List<String>>();
    public static List<String> historySlangWord = new ArrayList();
    public static Scanner word = new Scanner(System.in);

    // get data into file slang.txt
    public static void getData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(".slang.txt")));
            String line = br.readLine();
            while (line != null) {
                String[] name = line.split("`");
                String[] define = name[1].split("\\|");
                List<String> temp = Arrays.asList(define);
                dictionarySlang.put(name[0], temp);
            }
            br.close();

        } catch (Exception ex) {
            System.out.println("ERROR" + ex);
        }
    }

    public static void getHistory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(".history.txt")));
            String line = br.readLine();
            while (line != null) {
                historySlangWord.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("ERROR" + ex);
        }
    }

    // mode 1: finding defination by slang word
    public static void findBySlangWord() {
        clearScreen();

        System.out.println("-----Finding defination by slang word-----");
        System.out.println("Please! Enter defination which you want to search: ");
        String info = word.nextLine();
        info = info.toUpperCase();
        List<String> resultInfo = dictionarySlang.get(info);

        historySlangWord.add(info);
        System.out.println(info + " meaning " + resultInfo);

        pauseScreen();
        // showMenu();
    }

    // mode 2: finding slang word by defination
    public static void findByDefination() {
        clearScreen();

        System.out.println("-----Finding slang word by defination-----");
        System.out.println("Please! Enter slang word which you want to search: ");
        String defination = word.nextLine();
        historySlangWord.add(defination);

        List<String> result = new ArrayList<>();
        for (String i : dictionarySlang.keySet()) {
            if (dictionarySlang.get(i).contains(defination)) {
                result.add(i);
            }
        }
        System.out.println("Slang words: ");
        System.out.println(result);
        pauseScreen();
        // showMenu();
    }

    // mode 3: Show history of slang words which are searched
    public static void showHistory() {
        clearScreen();

        System.out.println("Your search history is: ");
        for (String i : historySlangWord) {
            System.out.println(i);
        }

        pauseScreen();
        // showMenu();
    }

    // mode 4: Adding a new slang word into slang.txt
    public static void addNewSW() {
        clearScreen();

        System.out.println("Adding a new slang word into file slang.txt");

        System.out.println("Please! Enter a new slang word which you want to add: ");
        String sw = word.nextLine();
        sw = sw.toUpperCase();

        System.out.println("Please! Enter its defination which you want to add: ");
        String defi = word.nextLine();

        List<String> newdf = new ArrayList<>();

        newdf.add(defi);
        if (dictionarySlang.containsKey(sw)) {

            String choose;
            do {
                System.out.println("Do you want to override this slang word (because this slang word has existed)");
                System.out.println("(Y/N): ");
                choose = word.nextLine();
            } while ((choose.equals("Y") == false) && (choose.equals("y") == false) && (choose.equals("N") == false) && (choose.equals("n") == false));
            if (choose.equals("Y") || choose.equals("y")){
                dictionarySlang.put(sw, newdf);
            }
            else {
                List<String> oldDefi = dictionarySlang.get(sw);
                for(String i : oldDefi){
                    newdf.add(i);
                }
                dictionarySlang.put(sw, newdf);
            }
        }
        else {
            dictionarySlang.put(sw, newdf);
            System.out.println("Adding a new slang word successfully!");
        }
        
        pauseScreen();
        //showMenu();
    }
}
