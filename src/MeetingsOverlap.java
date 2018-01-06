import java.util.ArrayList;
import java.util.HashMap;

public class MeetingsOverlap {
    private Integer _start1, _end1, _start2, _end2, _start3, _end3, _nextStart1, _nextEnd1, _nextStart2, _nextEnd2;
    private Integer countNext1 = 1;
    private Integer countMeetings= 1;
    private Character countNext2 = 'a';
    private Character CurrentList = 'A';
    private Boolean finishB = false;
    private Boolean finishA = false;
    private HashMap<Integer, ArrayList> scheduleA = new HashMap<Integer, ArrayList>();
    private HashMap<Integer, ArrayList> scheduleB = new HashMap<Integer, ArrayList>();
    private HashMap<Integer, ArrayList> final_schedule = new HashMap<Integer, ArrayList>();

    public MeetingsOverlap (HashMap m_1, HashMap m_2) {
        scheduleA.putAll(m_1);
        scheduleB.putAll(m_2);
        _start1 = (Integer)scheduleA.get(countNext1).get(0);
        _end1 = (Integer) scheduleA.get(countNext1).get(1);
        _start2 = (Integer)scheduleB.get((Character) countNext2).get(0);
        _end2 = (Integer)scheduleB.get((Character) countNext2).get(1);
        SetNext('A');
        SetNext('B');
    }

    public void firstMeeting (){
        if (finishA == true && finishB == true) {
            test();
            return;}
        if (finishA == false && finishA == false && _start1 > _start2) {
             Switch();
        }
        test();
    }

    private void test() {
        ArrayList<Integer> meeting = new ArrayList <Integer>(2);
        if (!final_schedule.isEmpty()){ //check if the final list already contain at least 1 meeting
            if (_start1 > _end3) { //if the next meeting is not overlapping with the exist one than creating new meeting in the final_schedule
                meeting.add(_start1);
                meeting.add(_end1);
                countMeetings++;
            } else { //update the exist meeting in the final_schedule with the right time
                meeting.add(_start3);
                meeting.add(_end1 > _end3 ? _end1 : _end3);
            }
        } else { //the final list empty
            meeting.add(0,_start1); //add the first meeting to the final_schedule
            if (_end1 >= _start2) {//if the meeting in scheduleA is overlap with the meeting in scheduleB
                meeting.add(_end2 > _end1 ? _end2: _end1);
                _start2 = _nextStart2;
                _end2 = _nextEnd2;
            } else {meeting.add(_end1);};
        }
        final_schedule.put(countMeetings,meeting);
        _start3 = meeting.get(0);
        _end3 = meeting.get(1);
        if (finishA == true && finishB == true) {return;}
        _start1= (CurrentList == 'A' ? _nextStart1: _nextStart2);
        _end1 = (CurrentList == 'A' ? _nextEnd1: _nextEnd2);;
        SetNext(CurrentList);
        firstMeeting();
    }



    private void SetNext(Character x){
        if (x == (Character) 'A') {
            if (scheduleA.containsKey(countNext1+1)) {
                countNext1++;
                _nextStart1 = (Integer) scheduleA.get(countNext1).get(0);
                _nextEnd1 = (Integer) scheduleA.get(countNext1).get(1);
            } else {
                CurrentList = 'B';
                _nextStart1 = _start2;
                _nextEnd1= _end2;
                finishA=true;
            }
        } else {
            if (scheduleB.containsKey((char)((int)(countNext2 + 1)))) {
                countNext2++;
                _nextStart2 = (Integer) scheduleB.get((Character) countNext2).get(0);
                _nextEnd2 = (Integer) scheduleB.get((Character) countNext2).get(1);
            } else {
                CurrentList = 'A';
                _nextStart1 = _start2;
                _nextEnd1= _end2;
                finishB = true;
            }
        }
    }

    private void Switch () {
        CurrentList = (CurrentList == 'A' ? 'B': 'A');
        Integer temp_1 = _start1;
        Integer temp_2 = _end1;
        _start1 = _start2;
        _end1 = _end2;
        _start2 = temp_1;
        _end2 = temp_2;

        temp_1 = _nextStart1;
        temp_2 = _nextEnd1;
        _nextStart1 = _nextStart2;
        _nextEnd1 = _nextEnd2;
        _nextStart2 = temp_1;
        _nextEnd2 = temp_2;
    }

    public void printFinalSchedule (){
        for (int i=1; i <= final_schedule.size(); i++) {
            System.out.println(final_schedule.get(i));
        }
    }
}

