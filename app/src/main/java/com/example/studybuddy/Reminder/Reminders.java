package com.example.studybuddy.Reminder;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "reminder")
public class Reminders {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    private String message;

    @ColumnInfo(name = "remind_date")
    public Date remindDate;

    @Entity(tableName = "assignment_reminders")
    public static class AssignmentReminder extends Reminders {
        @ColumnInfo(name = "remindDate")
        private Date remindDate;

        // Additional properties and methods specific to AssignmentReminder
    }



    @Entity(tableName = "session_reminders")
    public static class SessionReminder extends Reminders {
        // Additional properties and methods specific to SessionReminder
    }




    public String getMessage() {
        return message;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public void setId(int id) {
        this.id = id;
    }
}