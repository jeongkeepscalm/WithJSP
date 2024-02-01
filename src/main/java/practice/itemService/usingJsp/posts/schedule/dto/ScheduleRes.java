package practice.itemService.usingJsp.posts.schedule.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleRes {

    private String title;
    private Date startDate;
    private Date endDate;

}
