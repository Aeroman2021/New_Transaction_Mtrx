import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NewReadFile {

    private static Map<Integer, Integer> storageMap;
    private List<Integer> keyList;
    private List<Integer> valueList;

    public NewReadFile() {
        this.storageMap = new HashMap<>();
        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
    }

    public void loadFile(String path) throws IOException {
        String thisLine;
        BufferedReader myInput = null;
        try {
            FileInputStream fin = new FileInputStream(new File(path));
            myInput = new BufferedReader(new InputStreamReader(fin));

            while ((thisLine = myInput.readLine()) != null) {
                if (!thisLine.isEmpty() &&
                        thisLine.charAt(0) != '#' && thisLine.charAt(0) != '%'
                        && thisLine.charAt(0) != '@') {
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

    public void Initializer(String InputStr) {
        List<Integer> newList = new ArrayList<>();
        String replace = InputStr.replace(":", " ");
        String str[] = replace.split(" : ");
        for (String currentStr : str) {
            newList.add(Integer.parseInt(currentStr));
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
    }

    private Map<Integer, Integer> convertor(Map<Integer, Integer> inputMap) {
        return inputMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

    private Set<Integer> keyFinder(Map<Integer, Integer> inputMap) {
        return inputMap.keySet();
    }


    public static void main(String[] args) throws IOException {
        NewReadFile newReadFile = new NewReadFile();
        String path = "C:\\Users\\m_malakouti\\IdeaProjects\\Transaction-Matrix\\src\\main\\java\\foodmart2.txt";
        newReadFile.loadFile(path);
        System.out.println(newReadFile.convertor(storageMap));
        System.out.println(newReadFile.keyFinder(newReadFile.convertor(storageMap)));

    }


}
