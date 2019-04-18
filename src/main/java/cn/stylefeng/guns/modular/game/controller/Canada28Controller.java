package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.game.model.YzUser;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.game.model.Canada28;
import cn.stylefeng.guns.modular.game.service.ICanada28Service;

import java.util.List;

/**
 * Canada28数据控制器
 *
 * @author fengshuonan
 * @Date 2019-04-10 14:59:17
 */
@Controller
@RequestMapping("/canada28")
public class Canada28Controller extends BaseController {

    private String PREFIX = "/game/canada28/";

    @Autowired
    private ICanada28Service canada28Service;

    /**
     * 跳转到Canada28数据首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "canada28.html";
    }

    /**
     * 跳转到添加Canada28数据
     */
    @RequestMapping("/canada28_add")
    public String canada28Add() {
        return PREFIX + "canada28_add.html";
    }

    /**
     * 跳转到修改Canada28数据
     */
    @RequestMapping("/canada28_update/{canada28Id}")
    public String canada28Update(@PathVariable Integer canada28Id, Model model) {
        Canada28 canada28 = canada28Service.selectById(canada28Id);
        model.addAttribute("item",canada28);
        LogObjectHolder.me().set(canada28);
        return PREFIX + "canada28_edit.html";
    }

    /**
     * 获取Canada28数据列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        Page<Canada28> page = new PageFactory<Canada28>().defaultPage();
        List<Canada28> canada28s= canada28Service.selectListByQuery(page,condition);
        page.setRecords(canada28s);
        return new PageInfoBT<>(page);
    }

    /**
     * 新增Canada28数据
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Canada28 canada28) {
        canada28Service.insert(canada28);
        return SUCCESS_TIP;
    }

    /**
     * 删除Canada28数据
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer canada28Id) {
        canada28Service.deleteById(canada28Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改Canada28数据
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Canada28 canada28) {
        canada28Service.updateById(canada28);
        return SUCCESS_TIP;
    }

    /**
     * Canada28数据详情
     */
    @RequestMapping(value = "/detail/{canada28Id}")
    @ResponseBody
    public Object detail(@PathVariable("canada28Id") Integer canada28Id) {
        return canada28Service.selectById(canada28Id);
    }
}
