package practice.itemService.usingJsp.posts.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;
import practice.itemService.usingJsp.posts.schedule.mapper.ScheduleMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    @Override
    public List<ScheduleRes> selectThisMonthSchedule(int month) {
        try {
            List<ScheduleRes> scheduleRes = scheduleMapper.selectThisMonthSchedule(month);
            return scheduleRes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
