package practice.itemService.usingJsp.posts.schedule.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleReq;
import practice.itemService.usingJsp.posts.schedule.dto.ScheduleRes;
import practice.itemService.usingJsp.posts.schedule.service.ScheduleServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleApi {

    private final ScheduleServiceImpl scheduleService;

    @ResponseBody
    @PostMapping(value = "/selectSchedule")
    public ResponseEntity getSchedule() {
        List<ScheduleRes> scheduleRes = scheduleService.selectSchedule();
        return new ResponseEntity(scheduleRes, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/insertSchedule")
    public boolean insertSchedule(@RequestBody ScheduleReq scheduleReq) {
        return scheduleService.insertSchedule(scheduleReq);
    }

    @ResponseBody
    @PostMapping(value = "/updateSchedule")
    public boolean updateSchedule(@RequestBody ScheduleReq scheduleReq) {
        return scheduleService.updateSchedule(scheduleReq);
    }

    @ResponseBody
    @PostMapping(value = "/deleteSchedule")
    public boolean deleteSchedule(@RequestBody ScheduleReq scheduleReq) {
        return scheduleService.deleteSchedule(scheduleReq);
    }

}
