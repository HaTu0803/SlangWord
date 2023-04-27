import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;

import java.util.Map.Entry;


// Tham khảo code trong link https://www.youtube.com/watch?v=m5m-Fuc77fg&ab_channel=FoveITOfficial ==> Phần trăm thảo khảo là 20%
import java.util.Random;

public class SlangWord {
    private HashMap<String, HashSet<String>> dictionary, modifiedList;
    private LinkedList<String> historyList, deleteList;

    public SlangWord() {
        dictionary = new HashMap<String, HashSet<String>>();
        modifiedList = new HashMap<String, HashSet<String>>();
        historyList = new LinkedList<String>();
        deleteList = new LinkedList<String>();

        readFileToHashMap(dictionary, "slangword.txt");
        readFileToHashMap(modifiedList, "modified.txt");
        for (Entry<String, HashSet<String>> entry : modifiedList.entrySet()) {
            dictionary.put(entry.getKey(), entry.getValue());
        }

        readFileToLinkedList(historyList, "history.txt");
        readFileToLinkedList(deleteList, "delete.txt");
        for (int i = 0; i < deleteList.size(); i++) {
            dictionary.remove(deleteList.get(i));
        }
    }

    void readFileToHashMap(HashMap<String, HashSet<String>> hashmap, String fileName) {
        Path pathToFile = Paths.get(fileName);
        String line;
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("`");
                if (attributes.length != 2) {

                }
                else {
                    String slang = attributes[0].trim();
                    String[] definitions = attributes[1].split("\\|");
                    for (int i = 0; i < definitions.length; i++) {
                        definitions[i] = definitions[i].trim();
                    }
                    List<String> definitionList = Arrays.asList(definitions);
                    HashSet<String> definitionHashSet = new HashSet<String>(definitionList);
                    hashmap.put(slang, definitionHashSet);
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readFileToLinkedList(LinkedList<String> arraylist, String fileName) {
        Path pathToFile = Paths.get(fileName);
        String line;
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            line = br.readLine();
            while (line != null) {
                arraylist.add(line);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void saveDelete() {
        String fileName = "delete.txt";
        FileWriter fileWriter = null;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
            for (String line : deleteList)
                pw.println(line);
            pw.close();
        } catch (FileNotFoundException e) {

        }
    }

    void saveHistory() {
        String fileName = "history.txt";
        FileWriter fileWriter = null;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
            for (String line : historyList)
                pw.println(line);
            pw.close();
        } catch (FileNotFoundException e) {

        }
    }

    String toString(Entry<String, HashSet<String>> input) {
        String slang = input.getKey();
        HashSet<String> definitionHashSet = input.getValue();
        String[] definitions = definitionHashSet.toArray(new String[definitionHashSet.size()]);
        String definition = definitions[0];
        for (int i = 1; i < definitions.length; i++) {
            definition += "|" + definitions[i];
        }
        String output = slang + "`" + definition;
        return output;
    }

    void saveModified() {
        String fileName = "modified.txt";
        FileWriter fileWriter = null;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
            for (Entry<String, HashSet<String>> entry : modifiedList.entrySet())
                pw.println(toString(entry));
            pw.close();
        } catch (FileNotFoundException e) {

        }
    }

    public void Save() {
        saveDelete();
        saveHistory();
        saveModified();
    }

    public void addHistory(String his) {
        historyList.add(his);
    }

    public LinkedList<String> getHistory(){
        return historyList;
    }

    public HashSet<String> searchSlang(String slang){
        return dictionary.get(slang);
    }

    public void addDefinition(String slang, String definition) {
        dictionary.get(slang).add(definition);
        modifiedList.put(slang, dictionary.get(slang));
    }

    public void addNew(String slang, String definition) {
        HashSet<String> definitions = new HashSet<String>();
        definitions.add(definition);
        dictionary.put(slang, definitions);
        modifiedList.put(slang, dictionary.get(slang));
    }

    public boolean hasSlang(String slang) {
        return dictionary.containsKey(slang);
    }

    public boolean EditSlang(String slang, String old_value, String new_value) {
        HashSet<String> definitionHashSet = dictionary.get(slang);
        String[] definitions = definitionHashSet.toArray(new String[definitionHashSet.size()]);
        boolean result = false;
        for (int i = 0; i < definitions.length; i++) {
            if (definitions[i].strip().equals(old_value)) {
                definitions[i] = new_value;
                result = true;
                break;
            }
        }
        if (result == true) {
            List<String> definitionList = Arrays.asList(definitions);
            HashSet<String> definitionHashSetAferEdit = new HashSet<String>(definitionList);
            modifiedList.put(slang, definitionHashSetAferEdit);
        }
        return result;
    }

    public void deleteSlang(String slang) {
        deleteList.add(slang);
        dictionary.remove(slang);
    }

    public ArrayList<String> searchDefinition(String definition) {
        ArrayList<String> slangs = new ArrayList<String>();
        for (Entry<String, HashSet<String>> entry : dictionary.entrySet()) {
            String slang = entry.getKey();
            HashSet<String> definitionHashSet = entry.getValue();
            String[] definitions = definitionHashSet.toArray(new String[definitionHashSet.size()]);
            for (int i = 0; i < definitions.length; i++) {
                if (definitions[i].contains(definition)) {
                    slangs.add(slang);
                    break;
                }
            }

        }
        return slangs;
    }

    public String randomSlang() {
        Random random = new Random();
        ArrayList<String> slangs = new ArrayList<String>(dictionary.keySet());
        return slangs.get(random.nextInt(slangs.size()));
    }

    public HashMap<String, HashSet<String>> slangGame() {
        HashMap<String, HashSet<String>> slangGame = new HashMap<String, HashSet<String>>();
        for (int i = 0; i < 4; i++) {
            String slang = randomSlang();
            slangGame.put(slang, dictionary.get(slang));
        }
        return slangGame;
    }

    public HashMap<String, ArrayList<String>> definitionGame() {
        HashMap<String, ArrayList<String>> definitionGame = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < 4; i++) {
            String definition = dictionary.get(randomSlang()).iterator().next();
            definitionGame.put(definition, searchDefinition(definition));
        }
        return definitionGame;
    }

    public void reset() {
        readFileToHashMap(dictionary, "slangword.txt");
        for (Entry<String, HashSet<String>>entry : modifiedList.entrySet()) {
            String slang = entry.getKey();
            if (dictionary.containsKey(slang) == false) {
                dictionary.remove(slang);
            }
        }
        historyList.clear();
        deleteList.clear();
        modifiedList.clear();
    }

    void clearHistory() {
        historyList.clear();
    }
}
