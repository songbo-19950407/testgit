package com.exportcsv.controller;

import com.exportcsv.util.CsvExport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExportCSVController {
    @RequestMapping("/getCSVsong")
    @ResponseBody
    public void csv(HttpServletResponse response,  @RequestParam(value = "Token", required = true) String authorization) throws IOException {
        // 构造导出数据结构
        String titles = "id,任务名,优惠券类型,优惠券价值,生效日期,截止日期,优惠码";  // 设置表头
        String keys = "id,task,type,price,startTime,endTime,code";  // 设置每列字段
        List<Map<String, Object>> datas = new ArrayList<>();

        for (int i=0;i<10;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",111);
            map.put("task", "task");
            map.put("type", "一次性");
            map.put("price", 1000);
            map.put("startTime","2020-06-18 15:11:00");
            map.put("endTime", "2020-06-18 15:11:00");
            map.put("code",1);
            datas.add(map);
        }



        // 设置导出文件前缀
        String fName = "优惠券详情_";

        // 文件导出
        OutputStream os = response.getOutputStream();
        CsvExport.responseSetProperties(fName, response);
        CsvExport.doExport(datas, titles, keys, os);
        os.close();
    }
}
