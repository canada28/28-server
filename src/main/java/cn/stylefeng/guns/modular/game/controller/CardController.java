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
import cn.stylefeng.guns.modular.game.model.Card;
import cn.stylefeng.guns.modular.game.service.ICardService;

/**
 * 用户卡包控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 10:54:52
 */
@Controller
@RequestMapping("/card")
public class CardController extends BaseController {

    private String PREFIX = "/game/card/";

    @Autowired
    private ICardService cardService;

    /**
     * 跳转到用户卡包首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "card.html";
    }

    /**
     * 跳转到添加用户卡包
     */
    @RequestMapping("/card_add")
    public String cardAdd() {
        return PREFIX + "card_add.html";
    }

    /**
     * 跳转到修改用户卡包
     */
    @RequestMapping("/card_update/{cardId}")
    public String cardUpdate(@PathVariable Integer cardId, Model model) {
        Card card = cardService.selectById(cardId);
        model.addAttribute("item",card);
        LogObjectHolder.me().set(card);
        return PREFIX + "card_edit.html";
    }

    /**
     * 获取用户卡包列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return cardService.selectList(null);
    }

    /**
     * 新增用户卡包
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Card card) {
        cardService.insert(card);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户卡包
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardId) {
        cardService.deleteById(cardId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户卡包
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Card card) {
        cardService.updateById(card);
        return SUCCESS_TIP;
    }

    /**
     * 用户卡包详情
     */
    @RequestMapping(value = "/detail/{cardId}")
    @ResponseBody
    public Object detail(@PathVariable("cardId") Integer cardId) {
        return cardService.selectById(cardId);
    }
}
