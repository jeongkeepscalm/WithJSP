package practice.itemService.usingJsp.posts.schedule.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleRes {

    private int id;
    private String title;
    private Date start;
    private Date end;
    private String usedAt;

}
