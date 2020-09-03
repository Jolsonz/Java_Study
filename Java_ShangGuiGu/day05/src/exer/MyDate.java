package exer;


/**
 * MyDate类包含:
 private成员变量year,month,day；并为每一个属性定义 getter, setter 方法；

 * @author Jolson
 * @Create 2020-08-26 22:37
 */
public class MyDate implements Comparable {
    private int year,month,day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof MyDate) {
            MyDate m = (MyDate) o;
            int minusYear = this.getYear() - m.getYear();
            if (minusYear != 0) {
                return minusYear;
            }
            //比较月
            int minusMonth = this.getMonth() - m.getMonth();
            if (minusMonth != 0) {
                return minusMonth;
            }
            //比较日
            return this.getDay() - m.getDay();
        }
        throw new RuntimeException("传入类型错误");
    }
}
