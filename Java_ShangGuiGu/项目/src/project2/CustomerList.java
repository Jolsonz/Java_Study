package project2;

/**
 * @author Jolson
 * @Create 2020-08-08 21:12
 * Customer对象的管理模块，内部用数组管理一组Customer对象
 * 并提供相应的添加、修改、删除和遍历方法，供CustomerView调用
 */
public class CustomerList {
    Customer[] customers;//用来保存客户对象的数组
    int total = 0 ;//记录已保存客户对象的数量

    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];
    }
    public boolean addCustomer(Customer customer){
        if(total >= customers.length) return false;
        customers[total++] = customer;
        return true;
    }
    public boolean replaceCustomer(int index, Customer cust){//奇怪的形参名
        if(index >= total || index<0) return false;
        customers[index] = cust;
        return false;
    }

    public boolean deleteCustomer(int index){
        if(index >= total || index<0) return false;
        //把index后面的都往前移一位
        for(int i= index+1 ;i<total ;i++){
            customers[i-1]=customers[i];
        }
        customers[--total]=null;//最后一个元素置空，并同时把总数减一
        return true;
    }

    /**
     * 关键是不能把原数组直接给它操作，复制一个之后给他，注意不能写Customer[] c = customers;毫无意义。还是同一个对象。
     * 这其实是为了安全性，如果只是打印的话可以不这么一个个赋值
     * @return 一个新Customer的数组
     */
    public Customer[] getAllCustomers(){
        Customer[] c = new Customer[total];//
        for (int i = 0; i < total; i++) {
            c[i] = customers[i];
        }
        return c;

    }
    public Customer getCustomer(int index){
        if (index < 0 || index >= total) return null;
        return customers[index];
/*      Customer t = new Customer();
        t = customers[index];
        return t;
        为啥不这么写？更安全吧。
        */
    }
    public int getTotal(){
        return total;
    }
}
