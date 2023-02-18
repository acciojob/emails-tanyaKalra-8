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
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar = new ArrayList<>();
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
        int meetingCounts=0;
        if (calendar.size()==0) return 0;
        if (calendar.size()==1) return 1;

        LocalTime start1 = calendar.get(0).getStartTime();
        LocalTime end1 = calendar.get(0).getEndTime();
        meetingCounts++;

        for (int i=1;i<calendar.size();i++){
            LocalTime start = calendar.get(i).getStartTime();
            LocalTime end = calendar.get(i).getEndTime();

            if(start.equals(start1)){
                continue;
            }
            else if (start.isAfter(start1) && start.isBefore(end1)){
                continue;
            }
            else if(start.isBefore(start1) && end.isAfter(start1)){
                continue;
            }
            start1 = start;
            end1 = end;
            meetingCounts++;
        }
        return meetingCounts;
    }
}
