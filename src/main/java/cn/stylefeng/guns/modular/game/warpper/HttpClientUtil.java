package cn.stylefeng.guns.modular.game.warpper;

import cn.stylefeng.guns.job.ConventionJob;
import cn.stylefeng.guns.modular.api.ApiController;
import cn.stylefeng.guns.modular.game.model.Canada28;
import cn.stylefeng.guns.modular.game.model.Canada28Model;
import cn.stylefeng.guns.modular.game.service.ICanada28Service;
import cn.stylefeng.guns.modular.game.service.IGameService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ccanrom7
 * CreateDate on 2019/4/9  19:27.
 * Email canrom7@outlook.com
 **/
@Component
public class HttpClientUtil {
    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;
    @Autowired
    private IGameService gameService;
    @Autowired
    private ICanada28Service canada28Service;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd- yyyy-hh:mm:ss a", Locale.CANADA);

    private Gson gson = new Gson();
   public static DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public void toGetCanada28Data() {
        try {
            //判断当前时间是否等于下次开奖时间
            if (!ConventionJob.isSeal) {
                if (ApiController.CanadaGame == null) {
                    ApiController.CanadaGame = gameService.selectById(1);
                }
                HttpPost httpPost = new HttpPost(ApiController.CanadaGame.getUrl());
                httpPost.setConfig(config);
                CloseableHttpResponse response = null;
                response = httpClient.execute(httpPost);
                // 判断状态码是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 返回响应体的内容
                    String string = EntityUtils.toString(response.getEntity(), "UTF-8");
                    Canada28Model model = gson.fromJson(string, Canada28Model.class);
                    Canada28 canada28 = canada28Service.selectOne(new EntityWrapper<Canada28>().orderBy("id",false).last("limit 1"));
                    //是否有新的开奖结果
                    if ( model.getDrawNbr()> canada28.getPeriod()&& !ConventionJob.isSeal) {
                        ApiController.CANADA28_CURRENT_PERIOD = model.getDrawNbr();
                        ApiController.CANADA28_NEXT_PERIOD = model.getDrawNbr() + 1;
                        canada28 = new Canada28();
                        canada28.setId(model.getDrawNbr());
                        canada28.setPeriod(model.getDrawNbr());
                        List<Integer> drawNbrs = model.getDrawNbrs();
                        canada28.setNumbers(drawNbrs.toString());
                        canada28.setDateStr(model.getDrawDate());
                        canada28.setTimeStr(model.getDrawTime());
                        String dateReplace = model.getDrawDate().replace(",", "-");
                        String dateStr = dateReplace + "-" + model.getDrawTime();
                        Date date = simpleDateFormat.parse(dateStr);
                        canada28.setChTime(date);
                        System.out.println();
                        System.out.println(" " + ApiController.CANADA28_CURRENT_PERIOD + " 期开奖 时间：" + dateFormat.format(new Date()));
                        // todo 计算下期开奖时间   三分钟=180000  三分半种=210000
                        Date nextDate = new Date(date.getTime() + ApiController.CanadaGame.getInterval());
                        ApiController.CANADA28_STOP_TIME = nextDate.getTime();
//                        System.out.println(" " + ApiController.CANADA28_NEXT_PERIOD + " 期开奖 时间：" + dateFormat.format(nextDate));
                        ConventionJob.isSeal = true;
                        ConventionJob.isOpen = true;
                        int oneSum = drawNbrs.get(1) + drawNbrs.get(4) + drawNbrs.get(7) + drawNbrs.get(10) + drawNbrs.get(13) + drawNbrs.get(16);
                        int twoSum = drawNbrs.get(2) + drawNbrs.get(5) + drawNbrs.get(8) + drawNbrs.get(11) + drawNbrs.get(14) + drawNbrs.get(17);
                        int threeSum = drawNbrs.get(3) + drawNbrs.get(6) + drawNbrs.get(9) + drawNbrs.get(12) + drawNbrs.get(15) + drawNbrs.get(18);
                        String oneSumStr = String.valueOf(oneSum);
                        String twoSumStr = String.valueOf(twoSum);
                        String threeSumStr = String.valueOf(threeSum);
//                        System.out.println("第一个：" + oneSumStr);
//                        System.out.println("第二个：" + twoSumStr);
//                        System.out.println("第三个：" + threeSumStr);
                        int oneNum = Integer.parseInt(oneSumStr.substring(oneSumStr.length() - 1));
                        int twoNum = Integer.parseInt(twoSumStr.substring(twoSumStr.length() - 1));
                        int threeNum = Integer.parseInt(threeSumStr.substring(threeSumStr.length() - 1));
                        canada28.setOne(oneNum);
                        canada28.setTwo(twoNum);
                        canada28.setThree(threeNum);
                        int ruslt = oneNum + twoNum + threeNum;
                        int jo = ruslt % 2 == 1 ? 1 : 2;
                        canada28.setResult(ruslt);
                        canada28.setOddEven(jo);
                        canada28.setCreateTime(new Date());
                        try {
                            canada28Service.insert(canada28);
                        }catch (Exception e){
                            System.out.println("重复结果插入失败");
                        }
                        ApiController.inint = false;
                        System.out.println(" " + ApiController.CANADA28_CURRENT_PERIOD + " 期开奖结果：" + ruslt);
                        System.out.println();
                    }

                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
