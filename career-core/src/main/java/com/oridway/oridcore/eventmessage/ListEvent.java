package com.oridway.oridcore.eventmessage;

/**
 * Created by lihao on 2017/8/15.
 */

public class ListEvent {

    public static final int HOME_JOB_LIST_LOADMORE = 1000;


    public int eventType;
    public String eventMessage;
    public Object eventBody;

    public static ListEvent newEvent(int eventType){
        return new ListEvent(eventType);
    }

    private ListEvent(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public Object getEventBody() {
        return eventBody;
    }

    public void setEventBody(Object eventBody) {
        this.eventBody = eventBody;
    }
}
