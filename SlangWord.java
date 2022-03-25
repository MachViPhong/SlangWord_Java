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

    //mode 1: finding defination by slang word
    public static void findBySlangWord(){
        clearScreen();

        System.out.println("-----Finding defination by slang word-----");
        System.out.println("Please! Enter defination which you want to search: ");
        String info = word.nextLine();
        info = info.toUpperCase();
        List<String> resultInfo = dictionarySlang.get(info);

        historySlangWord.add(info);
        System.out.println(info + " meaning " + resultInfo);

        pauseScreen();
        //showMenu();
    }

    
}
