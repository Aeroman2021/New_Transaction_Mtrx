import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewMethod {

    private int currentRow;
    private int rowLimit;
    private int kCounter;
    private int min;

    public NewMethod() {
        currentRow = 3;
        kCounter = 3;
        min = 0;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public static List<Integer> Sorter(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    public static Integer MinFinder(List<Integer> list) {
        return Sorter(list).get(0);
    }

    public List<Integer> TopK2Maker(List<Integer> mainRow, List<Integer> newMatrix, int k) {
        for (Integer matrix : newMatrix) {
            if (mainRow.size() < k) {
                mainRow.add(matrix);
            }
        }
        return Sorter(mainRow);
    }

    public void CreateTopK1AndK2() {
        int kParameter = Input.getInputValue("Enter the K parameter: ");
        int rowLimit = Input.getInputValue("Enter row limit: ");
        setRowLimit(rowLimit);

        List<Integer> mainRow = Sorter(InputListMaker());
        System.out.println("K1 : " + mainRow);
//        if(IsLessThanKParameter(mainRow,kParameter)
        List<Integer> newMatrix1 = Sorter(InputListMaker());
        List<Integer> topK2 = TopK2Maker(mainRow, newMatrix1, kParameter);
        System.out.println("K2 : " + topK2);
        TopKnMaker(topK2);
    }


    private boolean isLastRow() {
        return currentRow == rowLimit;
    }


    public void TopKnMaker(List<Integer> inputKn) {

        if (isLastRow()) {
            return;
        } else {
            List<Integer> inputRow = InputListMaker();
            for (Integer currentNumber : inputRow) {
                int min = MinFinder(inputKn);
                int minIndex = inputKn.indexOf(min);
                if (currentNumber > min) {
                    inputKn.remove(minIndex);
                    inputKn.add(currentNumber);
                    Sorter(inputKn);
                }
            }
            List<Integer> sortedKn = Sorter(inputKn);
            System.out.println("K" + kCounter + " = " + sortedKn);
            kCounter++;
            currentRow++;
            TopKnMaker(sortedKn);
        }
    }


    private static List<Integer> InputListMaker() {
        List<Integer> newList = new ArrayList<>();
        int listLength = Input.getInputValue("Enter list length");
        for (int i = 0; i < listLength; i++) {
            int number = Input.getInputValue("Enter number");
            newList.add(number);
        }
        return newList;
    }


}
