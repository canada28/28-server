package cn.stylefeng.guns.job;


import cn.stylefeng.guns.modular.api.ApiController;
import cn.stylefeng.guns.modular.game.warpper.HttpClientUtil;
import cn.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @Author: ccanrom7
 * @CreateDate: 2018/11/16  21:50.
 * @Email: canrom7@outlook.com
 * @Description: 常规数据定时处理调度任务
 */
@Component
public class ConventionJob {

    private volatile static long openTime = 0;
    public volatile static boolean isSeal = false;
    public volatile static boolean isOpen = false;

    /**
     * 每天一次
     *
     * @throws Exception
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    @Transactional
    public void devicesSettlement() {
        if (ApiController.inint) {
            return;
        }
        ApiController.inint = true;

        while (true) {
            long sysTime = System.currentTimeMillis();
            //封盘时间
            long bf = ApiController.CANADA28_STOP_TIME - ApiController.CanadaGame.getBeforTime();
            if (sysTime == bf && isSeal) {
                isSeal = false;
                //开盘时间
                openTime = ApiController.CANADA28_STOP_TIME + ApiController.CanadaGame.getAfterTime();
                //todo 封盘通知
                System.out.println(ApiController.CANADA28_NEXT_PERIOD+" -------封盘了:" + ApiController.dateFormat.format(new Date()));
                // 推送下注列表


            }
            if (sysTime == openTime && isOpen) {
                isOpen = false;
                //todo 开盘通知
                System.out.println(ApiController.CANADA28_NEXT_PERIOD+" -------开盘了:" + ApiController.dateFormat.format(new Date()));
                System.out.println();
                System.out.println();
            }

        }
    }

}
