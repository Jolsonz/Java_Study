package study2;

import org.junit.Test;

import java.util.List;

/**
 * @author Jolson
 * @Create 2020-08-29 17:56
 */
public class DAOTest {
    @Test
    public void test1(){
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        List<Customer> list = dao1.getForList(10);

    }
}
