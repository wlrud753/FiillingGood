package com.example.fillinggood.Entity;

public class PersonalSchedule extends Schedule{
    private String priority;

    // 생성자
    public PersonalSchedule() {};
    public PersonalSchedule(String name, String location, String description, String priority,
                    String date, String startTime, String endTime) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    // DB에 Schedule 저장 > DBmanager 호출해서 저장.
    public static int save(long mScheduleID) {

        String ENDDATE;

        return 0;
    }

    public static int modify(long mScheduleID) {

        String ENDDATE;

        return 0;
    }

    public static long delete() {

        String ENDDATE;

        return 0;
    }

    // GETTERs & SETTERs
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}