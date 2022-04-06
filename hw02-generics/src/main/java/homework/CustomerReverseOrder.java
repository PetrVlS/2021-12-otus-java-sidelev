package homework;


import java.util.LinkedList;

public class CustomerReverseOrder {

    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedList<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
    customers.push(customer);
    }

    public Customer take() {
        return customers.pop();
    }
}
