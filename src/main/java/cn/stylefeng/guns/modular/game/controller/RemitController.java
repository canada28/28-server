package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.guns.modular.game.model.Card;
import cn.stylefeng.guns.modular.game.service.ICardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.game.model.Remit;
import cn.stylefeng.guns.modular.game.service.IRemitService;

/**
 * 下分管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 11:43:27
 */
@Controller
@RequestMapping("/remit")
public class RemitController extends BaseController {

    private String PREFIX = "/game/remit/";

    @Autowired
    private IRemitService remitService;
    @Autowired
    private ICardService cardService;
    /**
     * 跳转到下分管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "remit.html";
    }

    /**
     * 跳转到添加下分管理
     */
    @RequestMapping("/remit_add")
    public String remitAdd() {
        return PREFIX + "remit_add.html";
    }

    /**
     * 跳转到修改下分管理
     */
    @RequestMapping("/remit_update/{remitId}")
    public String remitUpdate(@PathVariable Integer remitId, Model model) {
        Remit remit = remitService.selectById(remitId);
        model.addAttribute("item",remit);
        LogObjectHolder.me().set(remit);
        return PREFIX + "remit_edit.html";
    }
    private String PREFIXA = "/game/card/";

    /**
     * 下分管理详情
     */
    @RequestMapping(value = "/remit_account/{account}")
    public Object accountDetail(@PathVariable("account") Integer account, Model model) {
        Card card = cardService.selectById(account);
        model.addAttribute("item",card);
        return PREFIXA + "card_edit.html";
    }

    /**
     * 获取下分管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return remitService.selectList(null);
    }

    /**
     * 新增下分管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Remit remit) {
        remitService.insert(remit);
        return SUCCESS_TIP;
    }

    /**
     * 删除下分管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer remitId) {
        remitService.deleteById(remitId);
        return SUCCESS_TIP;
    }

    /**
     * 修改下分管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Remit remit) {
        remitService.updateById(remit);
        return SUCCESS_TIP;
    }

    /**
     * 下分管理详情
     */
    @RequestMapping(value = "/detail/{remitId}")
    @ResponseBody
    public Object detail(@PathVariable("remitId") Integer remitId) {
        return remitService.selectById(remitId);
    }


}
