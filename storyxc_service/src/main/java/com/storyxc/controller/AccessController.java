package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.service.AccessService;
import com.storyxc.util.AccessStatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xc
 * @description 网站访问控制器
 * @createdTime 2020/1/19 11:12
 */
@RequestMapping("/story/access")
@RestController
@CrossOrigin
public class AccessController {

    @Autowired
    private AccessService accessService;

    @GetMapping("/visit")
    public Result visitWebSite(HttpServletRequest request){
        accessService.updateVisitCount();
        Map<String,String> map = new HashMap<String,String>();
        String remoteAddr = request.getRemoteAddr();
        String visitorLocation = AccessStatisticsUtils.getVistorLocation(remoteAddr);
        accessService.addVisitorInfo(remoteAddr,visitorLocation);
        accessService.addVisitorStatistics(remoteAddr,visitorLocation);
        map.put("ip",remoteAddr);
        map.put("location",visitorLocation);
        return new Result(true, StatusCode.OK,"访问统计成功",map);
    }
}
