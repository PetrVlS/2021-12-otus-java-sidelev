package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {


    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> customerService = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getCopyOfMapEntry(customerService.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getCopyOfMapEntry(customerService.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customerService.put(customer,data);
    }

    private Map.Entry<Customer, String> getCopyOfMapEntry(Map.Entry<Customer, String> mapEntry){
        if (mapEntry == null){
            return null;
        }
        return Map.entry(Customer.getCopyOfCustomer(mapEntry.getKey()), mapEntry.getValue());
    }
}
