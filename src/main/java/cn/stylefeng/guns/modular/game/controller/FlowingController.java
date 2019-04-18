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
import cn.stylefeng.guns.modular.game.model.Flowing;
import cn.stylefeng.guns.modular.game.service.IFlowingService;

/**
 * 账单记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 11:43:56
 */
@Controller
@RequestMapping("/flowing")
public class FlowingController extends BaseController {

    private String PREFIX = "/game/flowing/";

    @Autowired
    private IFlowingService flowingService;

    /**
     * 跳转到账单记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "flowing.html";
    }

    /**
     * 跳转到添加账单记录
     */
    @RequestMapping("/flowing_add")
    public String flowingAdd() {
        return PREFIX + "flowing_add.html";
    }

    /**
     * 跳转到修改账单记录
     */
    @RequestMapping("/flowing_update/{flowingId}")
    public String flowingUpdate(@PathVariable Integer flowingId, Model model) {
        Flowing flowing = flowingService.selectById(flowingId);
        model.addAttribute("item",flowing);
        LogObjectHolder.me().set(flowing);
        return PREFIX + "flowing_edit.html";
    }

    /**
     * 获取账单记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return flowingService.selectList(null);
    }

    /**
     * 新增账单记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Flowing flowing) {
        flowingService.insert(flowing);
        return SUCCESS_TIP;
    }

    /**
     * 删除账单记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer flowingId) {
        flowingService.deleteById(flowingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改账单记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Flowing flowing) {
        flowingService.updateById(flowing);
        return SUCCESS_TIP;
    }

    /**
     * 账单记录详情
     */
    @RequestMapping(value = "/detail/{flowingId}")
    @ResponseBody
    public Object detail(@PathVariable("flowingId") Integer flowingId) {
        return flowingService.selectById(flowingId);
    }
}
