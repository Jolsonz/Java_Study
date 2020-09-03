package study2;

import java.util.List;

/**
 * DAO:data(base) access object
 * 把一些通用的数据库操作写在里面
 * 每一张表都可能增删改查，但每一张表的类型不一样。
 * @author Jolson
 * @Create 2020-08-29 17:51
 */
public class DAO<T> {//表的共性操作的DAO
    //添加一条记录
    public void add(T t){

    }

    //删除一条记录
    public boolean remove(int index){

        return false;
    }

    //修改一条记录
    public void update(int index,T t){

    }

    //查询一条记录
    public T getIndex(int index){

        return null;
    }

    //查询多条记录
    public List<T> getForList(int index){

        return null;
    }

    //泛型方法
    //举例：获取表中一共有多少条记录？获取最大的员工入职时间？
    public <E> E getValue(){

        return null;
    }


}
