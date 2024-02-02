package practice.itemService.usingJsp.posts.schedule.service;

import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleReq;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;

import java.util.List;

@Service
public interface ScheduleService {

    // 당해년도 일정목록을 가져온다.
    List<ScheduleRes> selectSchedule();

    // 일정 등록
    boolean insertSchedule(ScheduleReq scheduleReq);

    // 일정 수정
    boolean updateSchedule(ScheduleReq scheduleReq);

    // 일정 삭제
    boolean deleteSchedule(ScheduleReq scheduleReq);

}
