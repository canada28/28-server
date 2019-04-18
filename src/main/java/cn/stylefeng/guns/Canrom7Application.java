/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * SpringBoot方式启动类
 * http://superzhifu.xyz
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
//@EnableScheduling
@EnableAsync
@SpringBootApplication(exclude = WebAutoConfiguration.class)
public class Canrom7Application {

    private final static Logger logger = LoggerFactory.getLogger(Canrom7Application.class);
    static DateFormat  dateFormat = DateFormat.getDateTimeInstance();

    public static void main(String[] args) {
        SpringApplication.run(Canrom7Application.class, args);
//        testA();
        System.out.println("Application is success!");
    }

    private static void testA(){
        try {
            // todo 接口时间转换为大陆时间
            String dateStr="Apr 2- 2019-"+"02:04:30 AM";
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MMMM dd- yyyy-hh:mm:ss a",Locale.CANADA);
            simpleDateFormat.setTimeZone(new SimpleTimeZone(-25200000, "GMT"));//偏移7个小时
            Date date=simpleDateFormat.parse(dateStr);
            System.out.println("上次开奖 中国时间："+dateFormat.format(date));
            System.out.println("下次开奖 中国时间："+dateFormat.format(new Date(date.getTime()+210000)));
            simpleDateFormat.setTimeZone(new SimpleTimeZone(28800000, "GMT"));
            date=simpleDateFormat.parse(dateStr);
            System.out.println("上次开奖 加拿大时间："+dateFormat.format(date));
            System.out.println("下次开奖 加拿大时间："+dateFormat.format(new Date(date.getTime()+210000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


//    var operation = function () {
//
//
//
//        }Feng.confirm("确定删除该订单?", operation);

