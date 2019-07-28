package statics;

import java.util.Date;
import java.util.Calendar;

public class InternalCalendar{
    public static int simTime;
    public static Calendar startDate = Calendar.getInstance();

    public InternalCalendar(){
        InternalCalendar.startDate.set(2019, 6, 1);
    }
    

    public static void tick(){
        InternalCalendar.startDate.add(Calendar.DATE, 1);
        simTime++;
    }

    public static String getDate(){
        return InternalCalendar.startDate.getTime().toString();
    }

    public static boolean inTransferWindow(){
        if(InternalCalendar.startDate.get(Calendar.MONTH) == Calendar.JANUARY || InternalCalendar.startDate.get(Calendar.MONTH) == Calendar.FEBRUARY){
            return true;
        }
        if(InternalCalendar.startDate.get(Calendar.MONTH) == Calendar.JUNE || InternalCalendar.startDate.get(Calendar.MONTH) == Calendar.JULY){
            return true;
        }

        return false;
    }

    public static Date beginningOfSeason(){
        Calendar calendar = InternalCalendar.startDate;
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 1);    
        return calendar.getTime();
    }

}