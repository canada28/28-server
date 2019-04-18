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
package cn.stylefeng.guns.modular.api;


import cn.stylefeng.guns.core.util.IpUtil;
import cn.stylefeng.guns.core.util.Md5_UUidUtil;
import cn.stylefeng.guns.modular.game.model.*;
import cn.stylefeng.guns.modular.game.service.*;
import cn.stylefeng.guns.modular.game.warpper.GameRuleWarp;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.*;

/**
 * 接口控制器提供
 *
 * @author stylefeng   Root..2018
 * @Date 2018/7/20 23:39  18627313837
 */
@Api(description = "接口文档( 点击展开 )")
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
    public static final int CODER_UID = 1;
    public static volatile boolean inint = true;
    public volatile static Game CanadaGame = null;

    public volatile static Game BeijingGame = null;
    /**
     * 加拿大28当前期数
     */
    public volatile static long CANADA28_CURRENT_PERIOD = 2412292;
    /**
     * 加拿大28下一期数
     */
    public volatile static long CANADA28_NEXT_PERIOD = 2412292;
    /**
     * 加拿大28停止下注时间
     */
    public volatile static long CANADA28_STOP_TIME = 64654564;

    /**
     * 北京28当前期数
     */
    public volatile static long BEIJING28_PERIOD = 945689;

    /**
     * 北京28停止下注时间
     */
    public volatile static long BEIJING28_STOP_TIME = 64654564;


    /**
     * 创建订单时候，选用设备的策略  DESC降序（大到小）,ASC升序(小到大)
     */
    public static final String DEVICE_STRATEGY = "DEVICE_STRATEGY";

    public static DateFormat dateFormat = DateFormat.getDateTimeInstance();

    @Autowired
    private IYzUserService userService;
    @Autowired
    private IYzNoticeService noticeService;
    @Autowired
    private ICardService cardService;
    @Autowired
    private IFlowingService flowingService;
    @Autowired
    private IRemitService remitService;
    @Autowired
    private IGameService gameService;
    @Autowired
    private IPlaygroundService playgroundService;
    @Autowired
    private ICanada28Service canada28Service;
    @Autowired
    private IStakesService stakesService;
    @Autowired
    private IGameruleService gameruleService;
    @Autowired
    private IOddsService oddsService;

    /**
     * 测试接口
     */
//    @ApiOperation(value = "测试接口", notes = "测试接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "测试ID", required = true, paramType = "query", dataType = "String")
//    })
    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public Object test(@RequestParam(value = "id", required = false) String id, HttpServletRequest requestq) {
        Map<String, String[]> parameterMap = requestq.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.println("请求  KEY:" + entry.getKey() + " VALU:" + Arrays.toString(entry.getValue()));
        }
        System.out.println("测试接口  " + dateFormat.format(new Date()));
        return SUCCESS_TIP;
    }

    /**
     * 获取短信验证码
     *
     * @param phone 手机号
     * @param type  验证码类型（1=注册 2=登陆&找回密码）
     */
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "验证码类型（1=注册 2=登陆验证 3=找回密码）", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/verificCode", method = {RequestMethod.POST, RequestMethod.GET})
    public Object verificCode(@RequestParam(value = "phone", required = true) String phone,
                              @RequestParam(value = "type", required = true) int type) {
        YzUser user = userService.selectOne(new EntityWrapper<YzUser>().eq("phone", phone));
        switch (type) {
            case 1: {
                if (user == null) {//注册
                    //TODO 发送短信
                    String code = "123456";
                    String token = Md5_UUidUtil.getUUID();
                    user = new YzUser();
                    user.setToken(token);
                    user.setPhone(phone);
                    user.setMsgCode(code);
                    user.setCreateTime(new Date());
                    userService.insert(user);
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "发送验证成功", "");
                } else {
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账号已注册", "");
                }
            }
            case 2: {//登陆验证
                if (user == null) {
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账号错误", "");
                }
                //TODO 发送短信
                String code = "123456";
                user.setMsgCode(code);
                userService.updateById(user);
                return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "发送验证成功", "");
            }
            case 3: {//找回密码
                if (user == null) {
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账号错误", "");
                }
                //TODO 发送短信
                String code = "123456";
                user.setMsgCode(code);
                userService.updateById(user);
                return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "发送验证成功", "");
            }

        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "发送验证码失败", "");
    }

    /**
     * 用户注册
     *
     * @param phone    手机号
     * @param code     验证码
     * @param password 登陆密码
     * @param nickname 用户名
     * @param parent   推荐人
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登陆密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parent", value = "推荐人（选填）", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/userRegister", method = {RequestMethod.POST, RequestMethod.GET})
    public Object userRegister(@RequestParam(value = "phone", required = true) String phone,
                               @RequestParam(value = "code", required = true) String code,
                               @RequestParam(value = "password", required = true) String password,
                               @RequestParam(value = "nickname", required = true) String nickname,
                               @RequestParam(value = "parent", required = false) Integer parent) {
        YzUser user = userService.selectOne(new EntityWrapper<YzUser>().eq("phone", phone));
        if (user == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "验证码错误", "");
        }
        if (code.equals(user.getMsgCode())) {
            String md5Pasd = Md5_UUidUtil.getMd5(password);
            user.setNickName(nickname);
            user.setPassword(md5Pasd);
            user.setParent(parent);
            userService.updateById(user);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "注册成功", "");
        } else {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "验证码错误", "");
        }
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登陆密码", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/userLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public Object userLogin(@RequestParam(value = "phone", required = true) String phone,
                            @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        YzUser user = userService.selectOne(new EntityWrapper<YzUser>().eq("phone", phone));
        if (user == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账号未注册", "");
        }
        int count = user.getReserved();
        if (count >= 5) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "多次错误，冻结登陆2小时", "");
        }
        String md5Pasd = Md5_UUidUtil.getMd5(password);
        if (!md5Pasd.equals(user.getPassword())) {
            user.setReserved(count + 1);
            userService.updateById(user);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "密码错误", "");
        }
        if (user.getStatus() == 2) {
            user.setToken(null);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "用户被冻结", user);
        }
        String ip = IpUtil.getIpAddr(request);
        user.setLoginTime(new Date());
        user.setLoginIp(ip);
        if (user.getLock() == 2) {
            String token = Md5_UUidUtil.getUUID();
            user.setToken(token);
            userService.updateById(user);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "登陆成功", user);
        } else {
            user.setToken(null);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "设备锁验证", user);
        }
    }

    @ApiOperation(value = "用户短信验证登陆", notes = "用户短信验证登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/loginVerific", method = {RequestMethod.POST, RequestMethod.GET})
    public Object loginVerific(@RequestParam(value = "phone", required = true) String phone,
                               @RequestParam(value = "code", required = true) String code) {
        YzUser user = userService.selectOne(new EntityWrapper<YzUser>().eq("phone", phone));
        if (user == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "验证码错误", "");
        }
        if (code.equals(user.getMsgCode())) {
            String token = Md5_UUidUtil.getUUID();
            user.setToken(token);
            userService.updateById(user);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "登陆成功", user);
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "验证码错误", "");
    }

    /**
     * 重置登录密码
     *
     * @param phone    手机号
     * @param password 新密码
     * @param code     验证码
     */
    @ApiOperation(value = "重置登录密码", notes = "重置登录密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "新密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/reSetPassword", method = {RequestMethod.POST, RequestMethod.GET})
    public Object reSetPassword(@RequestParam(value = "phone", required = true) String phone,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "code", required = true) String code) {
        YzUser user = userService.selectOne(new EntityWrapper<YzUser>().eq("phone", phone));
        if (user == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "重置密码失败", "");
        }
        if (code.equals(user.getMsgCode())) {
            String newPasd = Md5_UUidUtil.getMd5(password);
            user.setPassword(newPasd);
            userService.updateById(user);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "重置密码成功", user);
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "重置密码失败", "");
    }

    /**
     * 获取各类公告
     */
    @ApiOperation(value = "获取各类公告", notes = "获取各类公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（1=系统公告&横滚文 2=最新公告 3=会员必读 7=banner图通知）", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getNotices", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getNotices(@RequestParam(value = "type", required = true) int type) {
        List<YzNotice> notices = noticeService.selectList(new EntityWrapper<YzNotice>().eq("type", type).and().eq("status", 1));
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取公告成功", notices);
    }

    /**
     * 获取用户的提现卡包
     */
    @ApiOperation(value = "获取用户的提现卡包", notes = "获取用户的提现卡包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getCards", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getCards(@RequestParam(value = "uid", required = true) String uid,
                           @RequestParam(value = "token", required = true) String token) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        List<Card> cardList = cardService.selectList(new EntityWrapper<Card>().eq("uid", uid).and().eq("status", 1));
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取卡包成功", cardList);
    }

    /**
     * 获取用户账单记录
     */
    @ApiOperation(value = "获取用户账单记录", notes = "获取用户账单记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "业务类型（0=全部  1=充值 2=提现 3=业务 4=提成 5=分红 6=介绍 7=返水 8=活动）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "superzhifu.xyz（每页30条）", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getFlowings", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getFlowings(@RequestParam(value = "uid", required = true) String uid,
                              @RequestParam(value = "token", required = true) String token,
                              @RequestParam(value = "page", required = true) int page,
                              @RequestParam(value = "type", required = true) int type) {
        YzUser user = userService.selectById(uid);
        StringBuffer sb = new StringBuffer();
        sb.append("limit ");
        sb.append(page * 30).append(",").append(30);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        List<Flowing> flowings;
        if (type == 0) {
            flowings = flowingService.selectList(new EntityWrapper<Flowing>().eq("uid", uid).orderBy("id", false).last(sb.toString()));
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取账单记录成功", flowings);
        }
        flowings = flowingService.selectList(new EntityWrapper<Flowing>().eq("uid", uid).and().eq("type", type).orderBy("id", false).last(sb.toString()));
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取账单记录成功", flowings);
    }

    /**
     * 添加提现下分卡账户
     */
    @ApiOperation(value = "添加提现下分卡账户", notes = "添加提现下分卡账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账号类型（1=支付宝 2=银行卡）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "收款账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bank_brand", value = "银行名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bank_name", value = "支行名称", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/addCard", method = {RequestMethod.POST, RequestMethod.GET})
    public Object addCard(@RequestParam(value = "uid", required = true) String uid,
                          @RequestParam(value = "token", required = true) String token,
                          @RequestParam(value = "type", required = true) int type,
                          @RequestParam(value = "account", required = true) String account,
                          @RequestParam(value = "bank_brand", required = true) String bank_brand,
                          @RequestParam(value = "bank_name", required = true) String bank_name) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        Card card = cardService.selectOne(new EntityWrapper<Card>().eq("account", account).and().eq("type", type).and().eq("status", 1));
        if (card == null) {
            card = new Card();
            card.setUid(user.getId());
            card.setType(type);
            card.setAccount(account);
            card.setBank(bank_brand);
            card.setBankName(bank_name);
            card.setCreateTime(new Date());
            cardService.insert(card);
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "添加卡成功", "");
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账号已经存在", "");
    }

    /**
     * 删除下卡包卡
     */
    @ApiOperation(value = "删除下卡包卡", notes = "删除下卡包卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "card_id", value = "收款卡的ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/deleteCard", method = {RequestMethod.POST, RequestMethod.GET})
    public Object deleteCard(@RequestParam(value = "uid", required = true) String uid,
                             @RequestParam(value = "token", required = true) String token,
                             @RequestParam(value = "card_id", required = true) int card_id) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        Card card = cardService.selectOne(new EntityWrapper<Card>().eq("uid", uid).and().eq("id", card_id));
        if (card == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "删除失败", "");
        }
        card.setStatus(2);
        cardService.updateById(card);
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "删除卡成功", card_id);
    }

    /**
     * 申请下分提现
     */
    @ApiOperation(value = "申请下分提现", notes = "申请下分提现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "money", value = "提现金额", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "card_id", value = "收款卡的ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/applyRemit", method = {RequestMethod.POST, RequestMethod.GET})
    public Object applyRemit(@RequestParam(value = "uid", required = true) String uid,
                             @RequestParam(value = "token", required = true) String token,
                             @RequestParam(value = "money", required = true) float money,
                             @RequestParam(value = "card_id", required = true) int card_id) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        if (money > user.getAssets()) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "账户余额不足", "");
        }
        Remit remit = new Remit();
        remit.setUid(user.getId());
        remit.setCid(card_id);
        remit.setMoney(money);
        remit.setCreateTime(new Date());
        float ye = user.getAssets() - money;
        user.setAssets(ye);
        userService.updateById(user);
        remitService.insert(remit);
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "下分申请成功", ye);
    }

    /**
     * 获取热门游戏
     */
    @ApiOperation(value = "获取热门游戏", notes = "获取热门游戏")
    @RequestMapping(value = "/getHotGames", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getHotGames() {
        List<Game> games = gameService.selectList(new EntityWrapper<>());
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取热门游戏成功", games);
    }

    /**
     * 获取游戏场房间
     */
    @ApiOperation(value = "获取游戏场房间", notes = "获取游戏场房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "game_id", value = "游戏ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getPlayground", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getPlayground(@RequestParam(value = "game_id", required = true) String game_id) {
        Game game = gameService.selectOne(new EntityWrapper<Game>().eq("id", game_id).and().eq("status", 1));
        if (game == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏维护中", "");
        }
        List<Playground> playgrounds = playgroundService.selectList(new EntityWrapper<Playground>().eq("gid", game_id));
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取房间成功", playgrounds);
    }

    /**
     * 进入游戏场
     */
    @ApiOperation(value = "进入游戏场", notes = "进入游戏场")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "playground_id", value = "游戏场房间ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/toPlayground", method = {RequestMethod.POST, RequestMethod.GET})
    public Object toPlayground(@RequestParam(value = "uid", required = true) String uid,
                               @RequestParam(value = "token", required = true) String token,
                               @RequestParam(value = "playground_id", required = true) int playground_id) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        Playground playground = playgroundService.selectOne(new EntityWrapper<Playground>().eq("id", playground_id).and().eq("status", 1));
        if (playground == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏场维护中", "");
        }
        Map<String, Object> datas = new HashMap<>();
        long sysTime = System.currentTimeMillis();
        switch (playground.getGid()) {
            case 1: {//加拿大28场
                //当前下注期号
                datas.put("current_period", CANADA28_NEXT_PERIOD);
                //当前下注开奖剩余时间(秒)
                long mtime = CANADA28_STOP_TIME - CanadaGame.getBeforTime() - sysTime;
                datas.put("count_down", mtime);
                //上期开奖结果
                Canada28 canada28 = canada28Service.selectById(CANADA28_CURRENT_PERIOD);
                datas.put("previou_period", canada28);
                //账户余额
                datas.put("balance", user.getAssets());
                //是否封盘开奖中
                long bf = CANADA28_STOP_TIME - CanadaGame.getBeforTime();
                long af = CANADA28_STOP_TIME + CanadaGame.getAfterTime();
                if (sysTime >= bf || sysTime <= af) {
                    datas.put("blocking", 1);
                } else {
                    datas.put("blocking", 2);
                }
                //获取用户下注待开订单
                List<Stakes> stakesList = stakesService.selectList(new EntityWrapper<Stakes>().eq("uid", user.getId())
                        .and().eq("rid", playground.getId()).and().eq("status", 3));
                datas.put("stakes_list", stakesList);
                user.setRoomid(playground_id);
                userService.updateById(user);
                //todo 用户加入房间
                return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "进入房间成功", datas);
            }
            case 2: {//北京28场


            }
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏场维护中", "");
    }


    /**
     * 获取当前房间下注规则与赔率约束
     */
    @ApiOperation(value = "获取当前房间下注赔率约束", notes = "获取当前房间下注赔率约束")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "playground_id", value = "游戏场房间ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getGameRule", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getPlayRule(@RequestParam(value = "playground_id", required = true) int playground_id) {
        Playground playground = playgroundService.selectOne(new EntityWrapper<Playground>().eq("id", playground_id).and().eq("status", 1));
        if (playground == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏场维护中", "");
        }
        List<Gamerule> gameruleList = gameruleService.selectList(new EntityWrapper<Gamerule>().eq("gid", playground.getGid()));
        List<Odds> oddsList = oddsService.selectList(new EntityWrapper<Odds>().eq("pid", playground_id));
        GameRuleWarp ruleWarp = new GameRuleWarp(gameruleList, oddsList);
        return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "获取赔率规则成功", ruleWarp);
    }

    /**
     * 玩家下注
     */
    @ApiOperation(value = "玩家下注", notes = "玩家下注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "playground_id", value = "游戏场房间ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "下注输入类型（1=投注 2=块投 3=手动）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rule", value = "下注输入的内容", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public Object createOrder(@RequestParam(value = "uid", required = true) String uid,
                              @RequestParam(value = "token", required = true) String token,
                              @RequestParam(value = "playground_id", required = true) int playground_id,
                              @RequestParam(value = "type", required = true) int type,
                              @RequestParam(value = "rule", required = true) String rule) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        Playground playground = playgroundService.selectOne(new EntityWrapper<Playground>().eq("id", playground_id).and().eq("status", 1));
        if (playground == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏场维护中", "");
        }
        //todo 玩家下注
        long sysTime = System.currentTimeMillis();
        switch (playground.getGid()) {
            case 1: {//加拿大28场
                //是否封盘开奖中
                long bf = CANADA28_STOP_TIME - CanadaGame.getBeforTime();
                long af = CANADA28_STOP_TIME + CanadaGame.getAfterTime();
                if (sysTime >= bf || sysTime <= af) {
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "封盘中,下注失败", "");
                }
                Map<Integer, Integer> map = parseCanada28Stakes(type, rule);
                if (map == null || map.size() == 0) {
                    return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "数据错误,下注失败", "");
                }
                Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
                for (Map.Entry<Integer, Integer> entry : entries) {
                    int ruid = entry.getKey();
                    int money = entry.getValue();
                    //循环下注
                    Stakes stake = new Stakes(playground.getGid(), CANADA28_NEXT_PERIOD, playground.getId(), user.getId(), ruid, money, new Date());
                    if (user.getAssets() - money < 0) {
                        // todo  通知余额不足
                    } else {
                        //减去余额
                        user.setAssets(user.getAssets() - money);
                        //加冻结
                        user.setFrozenAssets(user.getFrozenAssets() + money);
                        //添加流水
                        StringBuffer title = new StringBuffer();
                        title.append("下注").append(playground.getTitle());
                        StringBuffer desc = new StringBuffer();
                        desc.append(desc.toString()).append("-第").append(CANADA28_NEXT_PERIOD).append("期，花费：").append(money).append("元");
                        Flowing flowing = new Flowing(user.getId(), Float.parseFloat(String.valueOf(money)), title.toString(), user.getAssets(), 1, 3, String.valueOf(CANADA28_NEXT_PERIOD), desc.toString(), new Date());
                        boolean flowin = flowingService.insert(flowing);
                        boolean insert = stakesService.insert(stake);
                        boolean update = userService.updateById(user);
                        if (insert && update && flowin) {
                            //todo 通知下注成功
                        }
                    }
                }
            }
            case 2: {//北京28场


            }
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "下注失败,此游戏场维护中", "");
    }

    /**
     * 玩家撤单
     */
    @ApiOperation(value = "玩家撤单", notes = "玩家撤单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户登陆后的ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户登陆后的Token", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "playground_id", value = "游戏场房间ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "order_id", value = "下注成功的订单ID", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/cancelOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public Object cancelOrder(@RequestParam(value = "uid", required = true) String uid,
                              @RequestParam(value = "token", required = true) String token,
                              @RequestParam(value = "playground_id", required = true) int playground_id,
                              @RequestParam(value = "order_id", required = true) long order_id) {
        YzUser user = userService.selectById(uid);
        if (user == null || !token.equals(user.getToken())) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "权限异常", "");
        }
        Playground playground = playgroundService.selectOne(new EntityWrapper<Playground>().eq("id", playground_id).and().eq("status", 1));
        if (playground == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "此游戏场维护中", "");
        }
        long sysTime = System.currentTimeMillis();
        long bf = CANADA28_STOP_TIME - CanadaGame.getBeforTime();
        long af = CANADA28_STOP_TIME + CanadaGame.getAfterTime();
        if (sysTime >= bf || sysTime <= af) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "封盘中,撤单失败", "");
        }
        Stakes stakes = stakesService.selectOne(new EntityWrapper<Stakes>().eq("id", order_id).and().eq("status", 3));
        if (stakes == null) {
            return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "参数错误,撤单失败", "");
        }
        // 减去冻结
        user.setFrozenAssets(user.getFrozenAssets() - stakes.getMoney());
        // 加上余额
        user.setAssets(user.getAssets() + stakes.getMoney());
        stakes.setStatus(4);
        //添加流水
        StringBuffer title = new StringBuffer();
        title.append("撤单").append(playground.getTitle());
        StringBuffer desc = new StringBuffer();
        desc.append(desc.toString()).append("-第").append(CANADA28_NEXT_PERIOD).append("期，返还：").append(stakes.getMoney()).append("元");
        Flowing flowing = new Flowing(user.getId(), Float.parseFloat(String.valueOf(stakes.getMoney())), title.toString(), user.getAssets(), 2, 3, String.valueOf(CANADA28_NEXT_PERIOD), desc.toString(), new Date());
        boolean flowin = flowingService.insert(flowing);
        boolean updateUser = userService.updateById(user);
        boolean updateStakes = stakesService.updateById(stakes);
        if (updateUser && updateStakes && flowin) {
            //todo 撤单成功通知
            return new SuccessResponseData(SuccessResponseData.DEFAULT_SUCCESS_CODE, "撤单成功", order_id);
        }
        return new SuccessResponseData(SuccessResponseData.DEFAULT_ERROR_CODE, "撤单失败", "");
    }


    /**
     * 解析加拿大28下注数据
     */
    private Map<Integer, Integer> parseCanada28Stakes(int type, String rule) {
        Map<Integer, Integer> integerMap = new HashMap<>();


        return null;
    }

    /**
     * 解析北京28下注数据
     */
    private Map<Integer, Integer> parseBeijing28Stakes(int type, String rule) {
        return null;
    }


}

