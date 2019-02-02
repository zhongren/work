package com.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import com.admin.common.base.BaseController;
import com.admin.common.bean.PageInfoBean;
import com.admin.common.bean.ParamBean;
import com.admin.common.bean.ResultBean;
import com.admin.common.util.BeanUtil;
import com.admin.common.util.JsonUtil;
import com.admin.common.util.PropertyUtil;
import com.admin.model.param.WorkRequestParam;
import com.admin.model.param.UserRequestParam;
import com.admin.model.param.WxLoginRequestParam;
import com.admin.model.param.WxLoginResponseParam;
import com.admin.model.user.UserDto;
import com.admin.model.work.AllWorkPageVo;
import com.admin.model.work.WorkDto;
import com.admin.model.work.WorkPageVo;
import com.admin.service.UserService;
import com.admin.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.*;


/**
 * @author zhongren
 * @date 2017/11/8
 */
@Controller
@RequestMapping(value = "work")
public class WxLoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorkService workService;


    /**
     * 登陆
     *
     * @param wxLoginParam
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean login(@RequestBody WxLoginRequestParam wxLoginParam) {
        String code = wxLoginParam.getCode();
        String url = PropertyUtil.getProperty("jscode2session", "");
        String appid = PropertyUtil.getProperty("appid", "");
        String secret = PropertyUtil.getProperty("secret", "");
        String wxUrl = url.replace("JSCODE", code).replace("APPID", appid).replace("SECRET", secret);
        String response = HttpUtil.get(wxUrl);
        WxLoginResponseParam wxLoginResponseParam = JsonUtil.jsonToBean(response, WxLoginResponseParam.class);
        System.out.println(wxLoginResponseParam.toString());
        //微信登陆成功
        if (wxLoginResponseParam.loginSuccess()) {
            String openid = wxLoginResponseParam.getOpenid();
            UserDto userDto = userService.findByOpenId(openid);
            if (null == userDto) {
                //openid对应的用户不存在  创建新用户
                userDto = userService.create(openid);
            }

            return success(userDto, "成功");
        } else {
            return fail(wxLoginResponseParam, "失败");
        }
    }


    /**
     * 登陆
     *
     * @param wxLoginParam
     * @return
     */
    @RequestMapping(value = "login2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean login2(@RequestBody WxLoginRequestParam wxLoginParam) {
        String code = wxLoginParam.getCode();
        String url = PropertyUtil.getProperty("jscode2session", "");
        String appid = PropertyUtil.getProperty("appid", "");
        String secret = PropertyUtil.getProperty("secret", "");
        String wxUrl = url.replace("JSCODE", code).replace("APPID", appid).replace("SECRET", secret);
        String response = HttpUtil.get(wxUrl);
        WxLoginResponseParam wxLoginResponseParam = JsonUtil.jsonToBean(response, WxLoginResponseParam.class);
        System.out.println(wxLoginResponseParam.toString());
        //微信登陆成功
        if (wxLoginResponseParam.loginSuccess()) {
            String openid = wxLoginResponseParam.getOpenid();
            UserDto userDto = userService.findByOpenId(openid);
            return success(userDto, "成功");
        } else {
            return fail(wxLoginResponseParam, "失败");
        }
    }

    /**
     * 更新用户信息
     *
     * @param userRequestParam
     * @return
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean updateUser(@RequestBody UserRequestParam userRequestParam) {
        String id = userRequestParam.getId();
        UserDto userDto = userService.findById(id);

        if (null != userDto) {
            userDto.setName(userRequestParam.getName());
            userDto.setPhone(userRequestParam.getPhone());
            userDto.setPic(userRequestParam.getPic());
        }
        userService.update(userDto);
        return success();
    }

    /**
     * 添加加班记录
     *
     * @param workRequestParam
     * @return
     */
    @RequestMapping(value = "createWork", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean createWork(@RequestBody WorkRequestParam workRequestParam) {
        String uid = workRequestParam.getUid();
        UserDto userDto = userService.findById(uid);
        WorkDto workDto = new WorkDto();
        workDto.setName(userDto.getName());
        workDto.setStart(workRequestParam.getStart());
        workDto.setEnd(workRequestParam.getEnd());
        workDto.setRemark(workRequestParam.getRemark());
        workDto.setUid(uid);
        workDto.setTotal(workService.total(workRequestParam.getStart(), workRequestParam.getEnd()));
        workService.create(workDto);
        return success("提交成功");
    }

    /**
     * 添加加班记录
     *
     * @param workRequestParam
     * @return
     */
    @RequestMapping(value = "deleteWork", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean deleteWork(@RequestBody WorkRequestParam workRequestParam) {
        List<String> list = Arrays.asList(workRequestParam.getIds());
        if (CollectionUtil.isNotEmpty(list)) {
            workService.delete("id", list);
        }
        return success("提交成功");
    }

    /**
     * 查询加班记录
     *
     * @param workRequestParam
     * @return
     */
    @RequestMapping(value = "findWork", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean findWork(@RequestBody WorkRequestParam workRequestParam) {
        String id = workRequestParam.getId();
        WorkDto workDto = workService.findById(id, WorkDto.class);
        return success(workDto);
    }


    /**
     * 个人加班记录列表
     *
     * @return
     */
    @RequestMapping(value = "userWork", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean userWork() {

        ParamBean paramBean = getParamBean();
        PageInfoBean page = workService.page(paramBean);

        String uid = (String) paramBean.get("uid");
        String year = (String) paramBean.get("year");
        String month = (String) paramBean.get("month");

        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("year", year);
        param.put("month", month);
        List<WorkDto> monthList = workService.findListBy(param, WorkDto.class);
        int monthTotal = 0;
        if (CollectionUtil.isNotEmpty(monthList)) {
            for (WorkDto workDto : monthList) {
                monthTotal += new Integer(workDto.getTotal());
            }
        }

        Map<String, Object> param2 = new HashMap<>();
        param2.put("uid", uid);
        param2.put("year", year);
        List<WorkDto> yearList = workService.findListBy(param2, WorkDto.class);
        int yearTotal = 0;
        if (CollectionUtil.isNotEmpty(yearList)) {
            for (WorkDto workDto : yearList) {
                yearTotal += new Integer(workDto.getTotal());
            }
        }

        WorkPageVo workPageVo = new WorkPageVo();
        workPageVo.setData(page.getData());
        workPageVo.setTotal(page.getTotal());
        workPageVo.setPageNum(page.getPageNum());
        workPageVo.setPageSize(page.getPageSize());
        workPageVo.setPages(page.getPages());
        workPageVo.setYearTotal(yearTotal + "");
        workPageVo.setMonthTotal(monthTotal + "");
        return success(workPageVo);
    }


    /**
     * 统计中心 加班记录列表
     *
     * @return
     */
    @RequestMapping(value = "allWork", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean allWork() {
        AllWorkPageVo allWorkPageVo = new AllWorkPageVo();
        int yearTotal = 0;
        float yearAverage = 0;
        String yearAverageStr = "0";
        int myTotal = 0;
        ParamBean paramBean = getParamBean();
        String year = (String) paramBean.get("year");
        String uid = (String) paramBean.get("uid");
        List<WorkDto> yearList = workService.findListBy("year", year, WorkDto.class, "total");
        if (CollectionUtil.isNotEmpty(yearList)) {
            for (WorkDto workDto : yearList) {
                yearTotal += new Integer(workDto.getTotal());
            }
        }
        Map<String, Object> userParam = new HashMap<>();
        List<UserDto> userDtoList = userService.findListBy(userParam, UserDto.class);
        if (CollectionUtil.isNotEmpty(userDtoList)) {
            yearAverage =new Float(yearTotal)/new Float(userDtoList.size());
            //yearAverage = yearTotal / userDtoList.size();
        }

        Map<String, Object> workParam = new HashMap<>();
        workParam.put("uid",uid);
        workParam.put("year",year);
        List<WorkDto> myWorkDtoList = workService.findListBy(workParam, WorkDto.class);
        if (CollectionUtil.isNotEmpty(myWorkDtoList)) {
            for (WorkDto workDto : myWorkDtoList) {
                myTotal += new Integer(workDto.getTotal());
            }
        }
        allWorkPageVo.setMyTotal(myTotal + "");
        allWorkPageVo.setYearTotal(yearTotal + "");


        DecimalFormat df2 = new DecimalFormat("###.00");
        yearAverageStr = df2.format(yearAverage);
        if(yearAverageStr.equals(".00")){
            yearAverageStr="0.00";
        }
        allWorkPageVo.setYearAverage(yearAverageStr);

        Map<String, Object> rankParam = new HashMap<>();
        rankParam.put("year", year);
        List<Map<String, Object>> workDtoList = workService.workRankList(rankParam);
        allWorkPageVo.setData(workDtoList);
        return success(allWorkPageVo);
    }


    @RequestMapping(value = "testInsert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean testInsert() {
        // userService.create("ooo");
        //UserDto userDto = userService.findBy("id", "ed5d4e99-1830-4553-bd49-5da87f52db5f", UserDto.class);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        int count = workService.delete("id", list);
        return success(count);
    }

    public static void main(String[] args) {
        float op = 0;
        DecimalFormat df2 = new DecimalFormat("###.00");
       String yearAverageStr = df2.format(op);
        System.out.println(yearAverageStr);
    }


}
