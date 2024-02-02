package practice.itemService.usingJsp.posts.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleReq;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;
import practice.itemService.usingJsp.posts.schedule.mapper.ScheduleMapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleRes> selectSchedule() {
        try {

            LocalDate now = LocalDate.now();
            int year = now.getYear();

            int range = 2;
            int[] years = new int[range * 2 + 1];
            AtomicInteger atomicInteger = new AtomicInteger(0);

            IntStream.rangeClosed(year - range, year + range).forEach(y -> {
                years[atomicInteger.getAndIncrement()] = y;
            });

            List<ScheduleRes> scheduleRes = scheduleMapper.selectSchedule(years);
            return scheduleRes;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    @Override
    public boolean insertSchedule(ScheduleReq scheduleReq) {
        return scheduleMapper.insertSchedule(scheduleReq) > 0 ? true : false;
    }

    @Override
    public boolean updateSchedule(ScheduleReq scheduleReq) {
        return scheduleMapper.updateSchedule(scheduleReq) > 0 ? true : false;
    }

    @Override
    public boolean deleteSchedule(ScheduleReq scheduleReq) {
        return scheduleMapper.deleteSchedule(scheduleReq) > 0 ? true : false;
    }

}
