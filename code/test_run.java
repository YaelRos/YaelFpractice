import java.util.ArrayList;
import java.util.HashMap;

public class test_run {
    public static void main (String [] args) {
        ArrayList<Integer> a_a = new ArrayList <Integer>(2);
        ArrayList<Integer> a_b = new ArrayList <Integer>(2);
        ArrayList<Integer> a_c = new ArrayList <Integer>(2);

        ArrayList<Integer> b_a = new ArrayList <Integer>(2);
        ArrayList<Integer> b_b = new ArrayList <Integer>(2);
        ArrayList<Integer> b_c = new ArrayList <Integer>(2);

        a_a.add(0,9);
        a_a.add(1,10);
        a_b.add(0,12);
        a_b.add(1,15);
        a_c.add(0,16);
        a_c.add(1,18);

        b_a.add(0,8);
        b_a.add(1,11);
        b_b.add(0,18);
        b_b.add(1,19);
        b_c.add(0,20);
        b_c.add(1,22);

        HashMap<Integer, ArrayList> s_1= new HashMap<Integer, ArrayList>();
        s_1.put(1, a_a);
        s_1.put(2, a_b);
        s_1.put(3, a_c);

        HashMap<Character, ArrayList> s_2= new HashMap<Character, ArrayList>();
        s_2.put('a', b_a);
        s_2.put('b', b_b);
        s_2.put('c', b_c);

        MeetingsOverlap meet = new MeetingsOverlap (s_1,s_2);
        meet.firstMeeting();
        meet.printFinalSchedule();
    }
}
