package project2;

import java.text.BreakIterator;

/**
 * @author Jolson
 * @Create 2020-08-08 21:12
 * 主模块，负责菜单的显示和处理用户操作
 */
public class CustomerView {
    private CustomerList customers = new CustomerList(10);
    public CustomerView(){
        Customer cust = new Customer("张三", '男', 30, "123456789","abc@email.com");
        customers.addCustomer(cust);
    }
    public void enterMainMenu(){
        boolean loopFlag = true;
        do{
            System.out.println("\n-----------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出\n");
            System.out.print("                   请选择(1-5)：");
            char key = CMUtility.readMenuSelection();
            System.out.println();
            switch (key){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.println("确认是否退出(Y/N)");
                    char c = CMUtility.readConfirmSelection();
                    if(c == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }

        }while (loopFlag);
    }

    private void addNewCustomer(){
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(4);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(15);
        System.out.print("邮件：");
        String email = CMUtility.readString(15);
        Customer cust = new Customer(name,gender,age,phone,email);
        boolean flag = customers.addCustomer(cust);
        if(flag) System.out.println("---------------------添加完成---------------------");
        else System.out.println("---------------------添加失败---------------------");

    }

    /**
     * @author JoJo
     * @Description 用于修改客户
     * 为什么这个又不能正常显示？
     */
    private void modifyCustomer(){
        System.out.println("---------------------修改客户---------------------");
        int index = 0;
        Customer cust = null;
        while (true){
            System.out.println("请输入待修改客户编号(-1退出)");
            index = CMUtility.readInt();
            if(index == -1) return;
            cust = customers.getCustomer(index-1);
            if(cust == null) System.out.println("无法找到指定用户");
            else break;
        }
        //现在拿到了cust这个要修改的用户
        System.out.print("姓名(" + cust.getName() + ")：");
        String name = CMUtility.readString(4,cust.getName());
        System.out.print("性别(" + cust.getGender() + ")：");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.print("年龄(" + cust.getAge() + ")：");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("电话(" + cust.getGender() + ")：");
        String phone = CMUtility.readString(15,cust.getPhone());
        System.out.print("邮件(" + cust.getGender() + ")：");
        String email = CMUtility.readString(15,cust.getEmail());
        /*
        * 我觉得这里的逻辑有点麻烦。
        * cust显然就是和customers[index-1]是同一个指针，这里可以直接用set方法来改，这样不用new新对象
        * 直接拿到name之后调用set改了
        * 比如cust.setName(CMUtility.readString(4,cust.getName()));
        * 这样不用写replaceCustomer
        * */
        cust = new Customer(name,gender,age,phone,email);
        boolean flag = customers.replaceCustomer(index-1 ,cust);
        if(flag)
            System.out.println("修改成功");
        else
            System.out.println("无法找到指定客户,修改失败");
    }

    private void deleteCustomer(){
        if(customers.customers[0] == null){
            System.out.println("无客户可删");
            return;
        }
        System.out.println("---------------------删除客户---------------------");
        int index = 0;
        Customer cust = null;
        while (true){
            System.out.println("请输入待删除客户编号(-1退出)");
            index = CMUtility.readInt();
            if(index == -1) return;
            cust = customers.getCustomer(index-1);
            if(cust == null) System.out.println("无法找到指定用户");
            else break;
        }

        System.out.println("请确认是否删除" + index + "号用户");
        char yn = CMUtility.readConfirmSelection();
        if(yn == 'N') return;

        boolean flag = customers.deleteCustomer(index-1);
        if (flag) {
            System.out.println("---------------------删除完成---------------------");
        } else {
            System.out.println("----------无法找到指定客户,删除失败--------------");
        }

    }
    private void listAllCustomers(){
        /*这其实是为了安全性，如果只是打印的话可以不这么一个个赋值*/
        Customer[] allCust = customers.getAllCustomers();
        if(allCust.length == 0){
            System.out.println("没有客户记录");
        }else {
            System.out.println("---------------------------客户列表---------------------------");
            System.out.println("编号\t姓名\t性别\t年龄\t\t电话\t\t邮箱");
            for (int i=0 ;i<allCust.length;i++){
                System.out.println((i + 1) + "\t" + allCust[i].getDetails());
            }
        }
        System.out.println("-------------------------客户列表完成-------------------------");
    }
    public static void  main(String[] args){
        CustomerView cView = new CustomerView();//先new个主类对象再说
        cView.enterMainMenu();
    }

}
