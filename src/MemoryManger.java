import java.util.ArrayList;

public class MemoryManger {
    PhysicalMemory physicalMemory;
    SubstitutionAlgorithm clock;

    public MemoryManger() {
        physicalMemory = new PhysicalMemory();
    }

    public void implementPage(int address, ArrayList<Record> Table) {
        if(Table.get(address).isInPhysicalMemory() == true) {
            System.out.println("Page № ->[" + address + "] mapped to physical page ->[" + Table.get(address).getNumberOfPhysicalPage()+"]");
        }
        else {

            int physicalPage = physicalMemory.getFreePage();

            if(physicalPage!= -1) {
                physicalMemory.takePhysicalPage(physicalPage);
                Table.get(address).sendToPhysicalMemory();
                Table.get(address).setNumberOfPhysicalPage(physicalPage);
                System.out.println("Virtual page № ->[" + address + "] mapped to physical page № ->[" + physicalPage+"]");
            }
            else{
                System.out.println("All physical pages are busy, we start the page replacement algorithm");
                clock = new SubstitutionAlgorithm(Table, physicalMemory);
                clock.start();
                implementPage(address,Table);
            }
        }
        Table.get(address).flagger(true);
    }

}

