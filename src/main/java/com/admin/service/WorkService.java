package com.admin.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.DateParser;
import cn.hutool.core.util.IdUtil;
import com.admin.SysConstants;
import com.admin.common.base.BaseService;
import com.admin.common.util.StringUtil;
import com.admin.model.param.WorkRequestParam;
import com.admin.model.user.UserDto;
import com.admin.model.work.WorkDto;
import com.admin.repo.repo.UserRepo;
import com.admin.repo.repo.WorkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Service
public class WorkService extends BaseService{
    @Autowired
    private WorkRepo workRepo;
    @Override
    public void init() {
        setBaseRepo(workRepo);
    }




    public WorkDto create(WorkDto workDto) {
        Date today=new Date();
        workDto.setId(IdUtil.fastUUID());
        workDto.setStatus(SysConstants.WORK_STATUS_UNACTIVE);
        workDto.setCreateTime(today);
        int year=DateUtil.year(today);
        workDto.setYear(year+"");
        //月  从0开始
        int month=DateUtil.month(today)+1;
        workDto.setMonth(month+"");
        workDto.setDay(DateUtil.dayOfMonth(today)+"");
        workRepo.create(workDto);
        return workDto;
    }
    public  List<Map<String,Object>> workRankList(Map<String,Object> map){
        return  workRepo.workRankList(map);
    }



    public String total(Date start,Date end){
        Long time=DateUtil.between(start,end, DateUnit.HOUR);
        return time.toString();
    }

    public static void main(String[] args) {
        String t11="2019-1-31 10:30:00";
        String t12="2019-1-31 12:01:01";
        WorkService workService=new WorkService();
        Date t1=DateUtil.parse(t11,"yyyy-MM-dd HH:mm:ss");
        Date t2=DateUtil.parse(t12,"yyyy-MM-dd HH:mm:ss");
        System.out.println(workService.total(t2,t1));


        System.out.println( DateUtil.date(Long.valueOf("1548905091615")));
        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date3);
        System.out.println( System.currentTimeMillis());


    }
}
