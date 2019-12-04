package com.example.fillinggood.Boundary;

import androidx.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MarkingDots{

    public static List<CalendarDay> markingDots(@NonNull Void... voids){

        //GUI 구성을 보이기 위한 array로, DB 구축 후 적절한 코드로 대체해주세요
        String[] datesHavingEvents = new String[3];
        datesHavingEvents[0] = new String("2019.11.20");
        datesHavingEvents[1] = new String("2019.11.22");
        datesHavingEvents[2] = new String("2019.11.27");

        ArrayList<CalendarDay> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        //일정을 가진 날짜에 점 표시해주는 파트
        for (int i=0; i<datesHavingEvents.length; i++){
            String s = datesHavingEvents[i];
            int year = Integer.parseInt(s.substring(0,4));
            int month = Integer.parseInt(s.substring(5,7));
            int day = Integer.parseInt(s.substring(8,10));
            calendar.set(year,month-1,day);
            CalendarDay date = CalendarDay.from(calendar);
            dates.add(date);
        }

        return dates;
    }
}