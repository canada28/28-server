package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.game.model.YzUser;
import cn.stylefeng.guns.modular.game.service.IYzUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 11:39:21
 */
@Controller
@RequestMapping("/yzUser")
public class YzUserController extends BaseController {

    private String PREFIX = "/game/yzUser/";

    @Autowired
    private IYzUserService yzUserService;

    /**
     * 跳转到用户管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yzUser.html";
    }

    /**
     * 跳转到添加用户管理
     */
    @RequestMapping("/yzUser_add")
    public String yzUserAdd() {
        return PREFIX + "yzUser_add.html";
    }

    /**
     * 跳转到修改用户管理
     */
    @RequestMapping("/yzUser_update/{yzUserId}")
    public String yzUserUpdate(@PathVariable Integer yzUserId, Model model) {
        YzUser yzUser = yzUserService.selectById(yzUserId);
        model.addAttribute("item", yzUser);
        LogObjectHolder.me().set(yzUser);
        return PREFIX + "yzUser_edit.html";
    }

    /**
     * 获取用户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Page<YzUser> page = new PageFactory<YzUser>().defaultPage();
       List<YzUser> users= yzUserService.selectListByQuery(page,name,beginTime,endTime);
        page.setRecords(users);
        return new PageInfoBT<>(page);
    }

    /**
     * 新增用户管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YzUser yzUser) {
        yzUserService.insert(yzUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer yzUserId) {
        yzUserService.deleteById(yzUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YzUser yzUser) {
        yzUserService.updateById(yzUser);
        return SUCCESS_TIP;
    }

    /**
     * 用户管理详情
     */
    @RequestMapping(value = "/detail/{yzUserId}")
    @ResponseBody
    public Object detail(@PathVariable("yzUserId") Integer yzUserId) {
        return yzUserService.selectById(yzUserId);
    }

    /**
     * 更新用户状态
     */
    @RequestMapping(value = "/changeUserStatus")
    @ResponseBody
    public Object changeUserStatus(@RequestParam("userid") Integer userid,
                                   @RequestParam("status") Integer status) {
        YzUser user = yzUserService.selectById(userid);
        user.setStatus(status == 1 ? 2 : 1);
        yzUserService.updateById(user);
         return SUCCESS_TIP;
    }


}
