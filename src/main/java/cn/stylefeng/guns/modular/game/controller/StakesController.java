package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.game.model.Stakes;
import cn.stylefeng.guns.modular.game.service.IStakesService;

/**
 * 下注管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-11 11:07:01
 */
@Controller
@RequestMapping("/stakes")
public class StakesController extends BaseController {

    private String PREFIX = "/game/stakes/";

    @Autowired
    private IStakesService stakesService;

    /**
     * 跳转到下注管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "stakes.html";
    }

    /**
     * 跳转到添加下注管理
     */
    @RequestMapping("/stakes_add")
    public String stakesAdd() {
        return PREFIX + "stakes_add.html";
    }

    /**
     * 跳转到修改下注管理
     */
    @RequestMapping("/stakes_update/{stakesId}")
    public String stakesUpdate(@PathVariable Integer stakesId, Model model) {
        Stakes stakes = stakesService.selectById(stakesId);
        model.addAttribute("item",stakes);
        LogObjectHolder.me().set(stakes);
        return PREFIX + "stakes_edit.html";
    }

    /**
     * 获取下注管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return stakesService.selectList(null);
    }

    /**
     * 新增下注管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Stakes stakes) {
        stakesService.insert(stakes);
        return SUCCESS_TIP;
    }

    /**
     * 删除下注管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer stakesId) {
        stakesService.deleteById(stakesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改下注管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Stakes stakes) {
        stakesService.updateById(stakes);
        return SUCCESS_TIP;
    }

    /**
     * 下注管理详情
     */
    @RequestMapping(value = "/detail/{stakesId}")
    @ResponseBody
    public Object detail(@PathVariable("stakesId") Integer stakesId) {
        return stakesService.selectById(stakesId);
    }
}
