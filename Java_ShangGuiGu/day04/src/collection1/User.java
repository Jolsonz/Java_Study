package collection1;

import java.util.Objects;

/**
 * @author Jolson
 * @Create 2020-08-26 10:15
 */
public class User implements Comparable {
    private String name ;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals_____");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    //object的hashcode是完全随机的。objects里的不是随机的，是有一定算法的。
    // 所以如果注释掉这里的hashcode会输出两个User{name='Tom', age=12}，因为这两个对象的hashcode随机，不一样了。
    // 写成return name.hashcode()+age也行。反正你知道这个意思。
    public int hashCode() {
        System.out.println("hash_____");
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {//按照姓名从小到大排列,按年龄从小到大排列。
        if(o instanceof User){
            User u = (User) o;
            int c = -this.name.compareTo(u.name);//负号就是从大到小
            if(c != 0) return c;
            else {
                return Integer.compare(this.age,u.age);
            }
        }else{
            throw new RuntimeException("输入的类型不匹配");
        }
    }
}
