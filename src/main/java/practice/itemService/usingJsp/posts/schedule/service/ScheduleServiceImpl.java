package practice.itemService.usingJsp.posts.schedule.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import practice.itemService.usingJsp.SessionConst;
import practice.itemService.usingJsp.login.dto.User;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleReq;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleVO;
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

        HttpSession session = getCurrentRequest().getSession();
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);

        ScheduleVO scheduleVO = ScheduleVO.from(scheduleReq);
        scheduleVO.setRegId(user.getId());

        return scheduleMapper.insertSchedule(scheduleVO) > 0 ? true : false;
    }

    @Override
    public boolean updateSchedule(ScheduleReq scheduleReq) {

        User user = (User) getCurrentRequest().getSession().getAttribute(SessionConst.LOGIN_USER);
        ScheduleVO scheduleVO = ScheduleVO.from(scheduleReq);
        scheduleVO.setUpdateId(user.getId());

        return scheduleMapper.updateSchedule(scheduleVO) > 0 ? true : false;
    }

    @Override
    public boolean deleteSchedule(ScheduleReq scheduleReq) {
        return scheduleMapper.deleteSchedule(scheduleReq) > 0 ? true : false;
    }


    /**
     * 각 service 파라미터에 HttpServletRequest 를 추가할 필요 없이,
     * RequestContextHolder 를 활용에 현제 request 객체에 접근할 수 있다.
     * **/
    public static HttpServletRequest getCurrentRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
