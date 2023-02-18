package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    Date date;
    String sender, message;

    public Pair(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }
    Queue<Pair> inbox = new LinkedList<>();
    Queue<Pair> Trash = new LinkedList<>();
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(inbox.size()>= inboxCapacity){
            Trash.add(inbox.poll());
        }
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        inbox.add(new Pair(date,sender,message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Queue<Pair> dummy = new LinkedList<>();

        int n = inbox.size();
        for(int i=0;i<n;i++){
            Pair q = inbox.peek();
            if(q.message.equals(message)){
                Trash.add(inbox.poll());
            }
            else{
                dummy.add(inbox.poll());
            }
        }

        while(dummy.size()>0){
            inbox.add(dummy.poll());
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        if(inbox.size()==0){
            return null;
        }
        // Else, return the message of the latest mail present in the inbox
        Queue<Pair> dummy = new LinkedList<>();

        String latest="";
        int n = inbox.size();
        for(int i=0;i<n;i++){
            Pair q = inbox.peek();
            if(i==n-1){
                latest = q.message;
            }
            dummy.add(inbox.poll());
        }
        while(dummy.size()>0){
            inbox.add(dummy.poll());
        }
        return latest;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if (inbox.size()==0) return null;
        // Else, return the message of the oldest mail present in the inbox
        Pair q = inbox.peek();
        return q.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count =0;
        if(inbox.size()==0){
            return 0;
        }

        Queue<Pair> dummy = new LinkedList<>();

        String latest="";
        int n = inbox.size();
        for(int i=0;i<n;i++){
            Pair q = inbox.peek();
            if (q.date.equals(start) || q.date.equals(end)){
                count++;
            }
            else if(q.date.after(start) && q.date.before(end)){
                count++;
            }
            dummy.add(inbox.poll());
        }
        while(dummy.size()>0){
            inbox.add(dummy.poll());
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        while (Trash.size()>0){
            Trash.remove();
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
