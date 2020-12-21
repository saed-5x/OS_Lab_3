import java.util.ArrayList;

public class SubstitutionAlgorithm {

    ArrayList<Record> Table;
    PhysicalMemory physicalMemory;

    public SubstitutionAlgorithm(ArrayList<Record> Table, PhysicalMemory physicalMemory) {
        this.Table = Table;
        this.physicalMemory = physicalMemory;
    }

    public void start() {
        for(int i = 0; i< Table.size(); i++) {
            int number = Table.get(i).getNumberOfPhysicalPage();

            if(Table.get(i).flagger() == false && Table.get(i).isInPhysicalMemory()) {

                if(Table.get(i).isModified() == false) {
                    System.out.println("Page № ->[" + Table.get(i).getPageNumber() +
                            "] deleted from memory, so the access flag was cleared and the page was not modified");
                    physicalMemory.removePage(number);
                }
                else {

                    System.out.println("Page № ->[" + Table.get(i).getPageNumber() + "]swapped out to disk,"+ " since the call flag has been reset");
                    physicalMemory.sendPageToDisk(number);
                }

                Table.get(i).removeFromPhysicalMemory();
                Table.get(i).setNumberOfPhysicalPage(-1);
                break;
            }
            else {
                Table.get(i).flagger(false);
                System.out.println("Page № ->[" + Table.get(i).getPageNumber() + "]call indication cleared");
            }

        }
    }
}
