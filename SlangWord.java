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
            BufferedReader br = new BufferedReader(new FileReader(new File("./slang.txt")));
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
            BufferedReader br = new BufferedReader(new FileReader(new File("./history.txt")));
            String line = br.readLine();
            while (line != null) {
                historySlangWord.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("ERROR" + ex);
        }
    }

    // mode 1: finding definition by slang word
    public static void findBySlangWord() {
        clearScreen();

        System.out.println("-----Finding definition by slang word-----");
        System.out.println("Please! Enter slangword which you want to search: ");
        String info = word.nextLine();
        info = info.toUpperCase();
        List<String> resultInfo = dictionarySlang.get(info);

        historySlangWord.add(info);
        System.out.println(info + " meaning " + resultInfo);

        pauseScreen();
        // showMenu();
    }

    // mode 2: finding slang word by definition
    public static void findByDefinition() {
        clearScreen();

        System.out.println("-----Finding slang word by definition-----");
        System.out.println("Please! Enter definition which you want to search: ");
        String definition = word.nextLine();
        historySlangWord.add(definition);

        List<String> result = new ArrayList<>();
        for (String i : dictionarySlang.keySet()) {
            if (dictionarySlang.get(i).contains(definition)) {
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

        System.out.println("Please! Enter its definition which you want to add: ");
        String defi = word.nextLine();

        List<String> newdf = new ArrayList<>();

        newdf.add(defi);
        if (dictionarySlang.containsKey(sw)) {

            String choose;
            do {
                System.out.println("Do you want to override this slang word (because this slang word has existed)");
                System.out.println("(Y/N): ");
                choose = word.nextLine();
            } while ((choose.equals("Y") == false) && (choose.equals("y") == false) && (choose.equals("N") == false)
                    && (choose.equals("n") == false));
            if (choose.equals("Y") || choose.equals("y")) {
                dictionarySlang.put(sw, newdf);
            } else {
                List<String> oldDefi = dictionarySlang.get(sw);
                for (String i : oldDefi) {
                    newdf.add(i);
                }
                dictionarySlang.put(sw, newdf);
            }
        } else {
            dictionarySlang.put(sw, newdf);
            System.out.println("Adding a new slang word successfully!");
        }

        pauseScreen();
        // showMenu();
    }

    // mode 5: edit slangword
    public static void editSlangword() {
        clearScreen();

        System.out.println("Please! Enter slangword you want to edit: ");
        String sw = word.nextLine();
        sw = sw.toUpperCase();

        if (!dictionarySlang.containsKey(sw)) {
            System.out.println("This slangword don't exist");
            pauseScreen();
            // showMenu();
        }

        System.out.println("Definition: ");
        List<String> definition = dictionarySlang.get(sw);
        List<String> definitions = new ArrayList<>();
        int count = 1;
        for (String i : definition) {
            definitions.add(i);
            System.out.println(count + ". " + i);
            count++;
        }

        System.out.println("Which definition do you want to change? \noption: ");
        int index = word.nextInt();

        definitions.remove(index - 1);
        System.out.println("Enter new edfinition of this slangword: ");
        String newdf = word.nextLine();
        List<String> newdefi = new ArrayList<>();
        newdefi.add(newdf);
        dictionarySlang.put(sw, newdefi);

        pauseScreen();
        // showMenu();
    }

    // mode 6. Remode Slangword
    public static void removeSlangWord() {
        clearScreen();

        System.out.println("Please! Enter slang word you want to remove: ");
        String sw = word.nextLine();
        System.out.println(sw + ": " + dictionarySlang.get(sw));

        if (dictionarySlang.containsKey(sw)) {
            System.out.println("Are you sure to remove this slangword");
            System.out.println("Y/N: ");
            String choose = word.nextLine();
            if (choose.equals("Y") || choose.equals("y")) {
                dictionarySlang.remove(sw);
                System.out.println("Remove successfully!");
            }
        }
        pauseScreen();
        // showMenu();
    }

    // mode 7: Reset Slangword dictionary
    public static void resetSlangwordDictionary() {
        clearScreen();
        dictionarySlang.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./default.txt")));
            String line = br.readLine();
            while (line != null) {
                String[] name = line.split("`");
                String[] define = name[1].split("\\|");
                List<String> temp = Arrays.asList(define);
                dictionarySlang.put(name[0], temp);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("ERROR " + ex);
        }
        System.out.println("Reset Slangword list to default successfully!");
        pauseScreen();
        // showMenu();
    }

    // mode 8: Random one slangword
    public static String randomOneSlangWord() {
        clearScreen();

        Random rd = new Random();
        String answer = "";
        int count = 0;
        int randomnumber = rd.nextInt(dictionarySlang.size());
        for (String i : dictionarySlang.keySet()) {
            if (count == randomnumber) {
                answer = i;
                break;
            }
            count++;
        }
        return answer;
    }

    // mode 9: minigaem random 1 slangword and choose the correct definition
    public static void minigameSlangWord() {
        clearScreen();

        Random rd = new Random();
        List<String> answerList = new ArrayList<>();

        // create question and answer correct, answer A
        String ans1 = randomOneSlangWord();
        String question = ans1;
        List<String> defi1 = dictionarySlang.get(ans1);
        ans1 = defi1.get(rd.nextInt(defi1.size()));
        answerList.add(ans1);
        String correctAns = ans1;

        // create answer B
        String ans2 = randomOneSlangWord();
        List<String> defi2 = dictionarySlang.get(ans2);
        ans2 = defi2.get(rd.nextInt(defi2.size()));
        answerList.add(ans2);

        // create answer C
        String ans3 = randomOneSlangWord();
        List<String> defi3 = dictionarySlang.get(ans3);
        ans3 = defi3.get(rd.nextInt(defi3.size()));
        answerList.add(ans3);

        // create answer D
        String ans4 = randomOneSlangWord();
        List<String> defi4 = dictionarySlang.get(ans4);
        ans4 = defi4.get(rd.nextInt(defi4.size()));
        answerList.add(ans4);

        System.out.println("Question: What is the Definition for " + question);

        // random answer A
        ans1 = answerList.get(rd.nextInt(answerList.size()));
        answerList.remove(ans1);
        System.out.print("A. " + ans1 + "\t");

        // random answer B
        ans2 = answerList.get(rd.nextInt(answerList.size()));
        answerList.remove(ans2);
        System.out.print("B. " + ans2 + "\t");

        // random answer C
        ans3 = answerList.get(rd.nextInt(answerList.size()));
        answerList.remove(ans3);
        System.out.print("C. " + ans3 + "\t");

        // random answer D
        ans4 = answerList.get(rd.nextInt(answerList.size()));
        answerList.remove(ans4);
        System.out.println("D. " + ans4);

        System.out.print("Your choice is: ");
        String choice = word.nextLine();
        if ((choice.equals("A") || choice.equals("a")) && ans1 == correctAns)
            System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("B") || choice.equals("b")) && ans2 == correctAns)
            System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("C") || choice.equals("c")) && ans3 == correctAns)
            System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("D") || choice.equals("d")) && ans4 == correctAns)
            System.out.println("Congratulations , Your Answer is correct");
        else
            System.out.println("Sorry , Your Answer is incorrect . The Answer is " + correctAns);

        pauseScreen();
        //showMenu();
    }
}
