import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewReadFile {

    private static Map<Integer, Integer> storageMap;
    private static List<Integer> keyList;
    private static List<Integer> valueList;

    public NewReadFile() {
        this.storageMap = new HashMap<>();
        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
    }

    public static Map<Integer, Integer> getStorageMap() {
        return storageMap;
    }

    public static List<Integer> getKeyList() {
        return keyList;
    }

    public static List<Integer> getValueList() {
        return valueList;
    }


    public List<Integer> integerListSorter(List<Integer> inputList) {
        return inputList.stream().sorted().collect(Collectors.toList());
    }


    public void loadFile(String path) throws IOException {
        String thisLine; // variable to read each line
        BufferedReader myInput = null; // object to read the file
        try {
            FileInputStream fin = new FileInputStream(new File(path));
            myInput = new BufferedReader(new InputStreamReader(fin));
            // for each line
            while ((thisLine = myInput.readLine()) != null) {
                // if the line is not a comment, is not empty or is not other
                // kind of metadata
                if (!thisLine.isEmpty() &&
                        thisLine.charAt(0) != '#' && thisLine.charAt(0) != '%'
                        && thisLine.charAt(0) != '@') {
                    // split the line according to spaces and then
                    // call "addTransaction" to process this line.
                    Initializer(thisLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myInput != null) {
                myInput.close();
            }
        }
    }

    public void Initializer(String str) {
        String[] inputString = str.split(" ");
        List<Integer> newList = new ArrayList<>();
        for (String s : inputString) {
            if (s.equals(":") || s.equals(" ") || s.equals("") || s.equals(" : "))
                continue;
            int i = Integer.parseInt(s);
            newList.add(i);
        }
        initialStage(newList);
    }

    public void initialStage(List<Integer> inputList) {
        int keyListEndIndex = inputList.size() / 2;
        int valueListStartIndex = keyListEndIndex + 1;

        for (int i = 0; i < keyListEndIndex; i++) {
            keyList.add(inputList.get(i));
        }

        for (int i = valueListStartIndex; i < inputList.size(); i++) {
            valueList.add(inputList.get(i));
        }

        valueListUpdater();

    }

    private void valueListUpdater() {
        List<Integer> updatedKeyList = new ArrayList<>();
        List<Integer> updatedValueList = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            Integer currentKey = keyList.get(i);
            Integer currentValue = valueList.get(i);
            if (!updatedKeyList.contains(currentKey)) {
                updatedKeyList.add(currentKey);
                updatedValueList.add(currentValue);
                storageMap.put(currentKey, currentValue);
            } else {
                int indexOfCurrentKey = updatedKeyList.indexOf(currentKey);
                Integer foundedValue = updatedValueList.get(indexOfCurrentKey);
                foundedValue += valueList.get(i);
                updatedValueList.add(indexOfCurrentKey, foundedValue);
                storageMap.put(currentKey, foundedValue);
            }
        }
//        System.out.println("=====================================");
//        TreeMap<Integer, Integer> sorted = new TreeMap<>(storageMap);
//        Set<Map.Entry<Integer, Integer>> mapping = sorted.entrySet();
//        for (Map.Entry<Integer, Integer> integerIntegerEntry : mapping) {
//            System.out.println(integerIntegerEntry.getKey() + " ==> " + integerIntegerEntry.getValue());
//        }
        System.out.println("=====================================");
         storageMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);



    }

    public static void main(String[] args) throws IOException {
        NewReadFile newReadFile = new NewReadFile();
        String path = "C:\\Users\\m_malakouti\\IdeaProjects\\Transaction-Matrix\\src\\main\\java\\foodmart2.txt";
        newReadFile.loadFile(path);

    }


}
