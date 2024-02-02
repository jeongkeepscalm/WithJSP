package practice.itemService.usingJsp.posts.schedule.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleReq {

    private Integer id;
    private String title;
    private String start;
    private String end;

}
