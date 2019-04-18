package cn.stylefeng.guns.job;

import cn.stylefeng.guns.modular.game.warpper.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by ccanrom7
 * CreateDate on 2019/4/12  17:30.
 * Email canrom7@outlook.com
 **/
@Component
public class GetDataJob {
    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 1秒一次
     *
     * @throws Exception
     */
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updataDevicesStatus() {
        httpClientUtil.toGetCanada28Data();
    }
}
