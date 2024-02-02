package practice.itemService.usingJsp.posts.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleReq;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleVO;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    /**매개변수가 객체가 아닌 경우에는 @Param 어노테이션이 붙어야 마이바티스에서 인식을 한다.**/
    // 당해년도 일정목록을 가져온다.
    List<ScheduleRes> selectSchedule(@Param("years") int[] years);

    // 일정 등록
    int insertSchedule(ScheduleVO scheduleVO);

    // 일정 수정
    int updateSchedule(ScheduleVO scheduleVO);

    // 일정 삭제
    int deleteSchedule(ScheduleReq scheduleReq);

}
