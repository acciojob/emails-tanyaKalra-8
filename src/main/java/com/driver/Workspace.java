package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar=new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

//    public int findMaxMeetings(){
//        // find the maximum number of meetings you can attend
//        // 1. At a particular time, you can be present in at most one meeting
//        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
//        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
//        int meetingCounts=0;
//        if (calendar.size()==0) return 0;
//        if (calendar.size()==1) return 1;
//        for (int i=0;i<calendar.size()-1;i++){
//            LocalTime start1 = calendar.get(i).getStartTime();
//            LocalTime end1 = calendar.get(i).getEndTime();
//
//            LocalTime start2 = calendar.get(i+1).getStartTime();
//            LocalTime end2 = calendar.get(i+1).getEndTime();
//
//            if(!start1.equals(start2)){
//                meetingCounts++;
//            }
//        }
//        return meetingCounts;
//    }
//}

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count=1;
        LocalTime max=calendar.get(0).getEndTime();
        for (Meeting m :calendar){
            if(m.getStartTime().isAfter(max)){
                count++;
                if(m.getEndTime().isAfter(max)) max=m.getEndTime();
            }
        }
        return count;
    }
}
