package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Hour;

import java.util.ArrayList;

/**
 * Created by DaNN on 30/10/2017.
 */

public class Hours {

    public String status="";
    public boolean isOpen =false;
    public boolean isLocalHoliday = false;
    public ArrayList<Timeframes> listtimeframes =  new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isLocalHoliday() {
        return isLocalHoliday;
    }

    public void setLocalHoliday(boolean localHoliday) {
        isLocalHoliday = localHoliday;
    }

    public ArrayList<Timeframes> getTimeframes() {
        return listtimeframes;
    }

    public void setTimeframes(ArrayList<Timeframes> timeframes) {
        this.listtimeframes = timeframes;
    }

    public void setTime(Timeframes tm){
        listtimeframes.add(tm);
    }

    public Hours(){}

    public static class Timeframes{

        public String days;
        public ArrayList<OpenTime> listrenderedtime = new ArrayList<>();

        public Timeframes(){}
        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public ArrayList<OpenTime> getListrenderedtime() {
            return listrenderedtime;
        }

        public void setListrenderedtime(ArrayList<OpenTime> listrenderedtime) {
            this.listrenderedtime = listrenderedtime;
        }
        public void adOP(OpenTime op){
            listrenderedtime.add(op);
        }



        public static class OpenTime{
            String renderedtime;
            public OpenTime(){}

            public String getRenderedtime() {
                return renderedtime;
            }

            public void setRenderedtime(String renderedtime) {
                this.renderedtime = renderedtime;
            }
        }
    }

}
