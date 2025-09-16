import java.util.*;
public class DesignPhoneDirectory {
    ArrayList<Integer> list;
    public DesignPhoneDirectory(int maxNumbers) {
        this.list = new ArrayList<>();
        for(int i = 0; i < maxNumbers; i++) {
            list.add(i);
        }
    }
    public int get() {
        if(list.size() <= 0) return -1;
        return list.remove(0);
    }
    public boolean check(int number) {
        return list.contains(number);
    }
    public void release(int number) {
        if(!list.contains(number))
            list.add(number);
    }

    public static void main(String[] args) {
        //["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
        //[[3],                 [], [],    [2],    [],     [2],       [2],     [2]]

        DesignPhoneDirectory phn = new DesignPhoneDirectory(3);
        System.out.println(phn.get());
        System.out.println(phn.get());
        System.out.println(phn.check(2));
        System.out.println(phn.get());
        System.out.println(phn.check(2));
        phn.release(2);
        System.out.println(phn.check(2));
    }
}
