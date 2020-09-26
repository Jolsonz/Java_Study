package study2;

import java.io.Serializable;

/**
 * @anthor: Jolson
 * @date: 2020/9/26-15:51
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}

