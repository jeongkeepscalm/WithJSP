package practice.itemService.usingJsp.posts.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ScheduleRes {

    private int id;
    private String title;
    private Date start;
    private Date end;

    public ScheduleRes scheduleVO() {
        return ScheduleRes.builder()
                .id(id)
                .title(title)
                .start(start)
                .end(end)
                .build();
    }
}
