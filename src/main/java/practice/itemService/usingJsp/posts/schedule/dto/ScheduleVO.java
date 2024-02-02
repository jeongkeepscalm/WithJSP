package practice.itemService.usingJsp.posts.schedule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleVO {

    private Integer id;
    private String title;
    private String start;
    private String end;
    private String regId;
    private String updateId;

    public static ScheduleVO from (ScheduleReq scheduleReq) {
        return ScheduleVO.builder()
                .id(scheduleReq.getId())
                .title(scheduleReq.getTitle())
                .start(scheduleReq.getStart())
                .end(scheduleReq.getEnd())
                .build();
    }

}
