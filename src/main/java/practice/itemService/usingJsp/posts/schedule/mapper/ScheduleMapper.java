package practice.itemService.usingJsp.posts.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedNotification;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    // 당해년도 일정목록을 가져온다.
    List<ScheduleRes> selectThisMonthSchedule(int month);

}
