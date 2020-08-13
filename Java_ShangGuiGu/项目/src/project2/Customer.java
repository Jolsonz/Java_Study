package project2;

/**
 * @author Jolson
 * @Create 2020-08-08 21:12
 * 实体对象，用来封装客户信息
 */
public class Customer {

    private String name;
    private char gender;
    private int age;
    private String phone;
    private String email;
    public Customer(){
    }

    /**
     * @param name
     * @param gender
     * @param age
     * 构造器，只记录前三个参数的
     */
    public Customer(String name, char gender, int age){
        this(name,gender,age,"","");//要用this调用本类的构造器
    }
    public Customer(String name, char gender, int age,String phone,String email){
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetails(){
        return name + "\t" + gender + "\t" + age + "\t\t" + phone + "\t" + email;
    }

    public static void main(String[] args) {
        Customer t = new Customer("你爹",'男',31,"1331245","helkjoi@jo.com");
        t.setEmail("hello@qq.com");
        System.out.println(t.getDetails());
    }
}
