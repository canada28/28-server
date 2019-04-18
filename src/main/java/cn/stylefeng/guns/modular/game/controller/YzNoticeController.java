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
import cn.stylefeng.guns.modular.game.model.YzNotice;
import cn.stylefeng.guns.modular.game.service.IYzNoticeService;

/**
 * 公告管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-07 19:13:07
 */
@Controller
@RequestMapping("/yzNotice")
public class YzNoticeController extends BaseController {

    private String PREFIX = "/game/yzNotice/";

    @Autowired
    private IYzNoticeService yzNoticeService;

    /**
     * 跳转到公告管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yzNotice.html";
    }

    /**
     * 跳转到添加公告管理
     */
    @RequestMapping("/yzNotice_add")
    public String yzNoticeAdd() {
        return PREFIX + "yzNotice_add.html";
    }

    /**
     * 跳转到修改公告管理
     */
    @RequestMapping("/yzNotice_update/{yzNoticeId}")
    public String yzNoticeUpdate(@PathVariable Integer yzNoticeId, Model model) {
        YzNotice yzNotice = yzNoticeService.selectById(yzNoticeId);
        model.addAttribute("item",yzNotice);
        LogObjectHolder.me().set(yzNotice);
        return PREFIX + "yzNotice_edit.html";
    }

    /**
     * 获取公告管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return yzNoticeService.selectList(null);
    }

    /**
     * 新增公告管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YzNotice yzNotice) {
        yzNoticeService.insert(yzNotice);
        return SUCCESS_TIP;
    }

    /**
     * 删除公告管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer yzNoticeId) {
        yzNoticeService.deleteById(yzNoticeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公告管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YzNotice yzNotice) {
        yzNoticeService.updateById(yzNotice);
        return SUCCESS_TIP;
    }

    /**
     * 公告管理详情
     */
    @RequestMapping(value = "/detail/{yzNoticeId}")
    @ResponseBody
    public Object detail(@PathVariable("yzNoticeId") Integer yzNoticeId) {
        return yzNoticeService.selectById(yzNoticeId);
    }
}
