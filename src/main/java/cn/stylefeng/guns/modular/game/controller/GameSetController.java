package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.api.ApiController;
import cn.stylefeng.guns.modular.game.model.Game;
import cn.stylefeng.guns.modular.game.service.IGameService;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ccanrom7
 * CreateDate on 2019/4/15  16:20.
 * Email canrom7@outlook.com
 * 游戏设置
 **/
@Controller
@RequestMapping("/gameset")
public class GameSetController extends BaseController {

    private String PREFIX = "/game/canada28/";
    @Autowired
    private IGameService gameService;

    /**
     * 加拿大二八设置
     */
    @RequestMapping("/canada28Set/{id}")
    public String canada28Set(@PathVariable Integer id, Model model) {
        Game game = gameService.selectById(id);
        model.addAttribute("item", game);
        LogObjectHolder.me().set(game);
        return PREFIX + "canada_view.html";
    }

    /**
     * 设置游戏开盘时间
     * @param idstr
     * @param avatar
     * @param title
     * @param descr
     * @param sort
     * @param interval
     * @param beforTime
     * @param afterTime
     * @param url
     * @return
     */
    @RequestMapping("/setGame")
    @ResponseBody
    public Object setGame(@RequestParam(value = "idstr", required = true) Integer idstr,
                          @RequestParam(value = "avatar", required = false) String avatar,
                          @RequestParam(value = "title", required = true) String title,
                          @RequestParam(value = "descr", required = true) String descr,
                          @RequestParam(value = "sort", required = true) Integer sort,
                          @RequestParam(value = "interval", required = true) Integer interval,
                          @RequestParam(value = "beforTime", required = true) Integer beforTime,
                          @RequestParam(value = "afterTime", required = true) Integer afterTime,
                          @RequestParam(value = "url", required = true) String url) {
        Game game = gameService.selectById(idstr);
        if (game != null) {
            game.setAfterTime(afterTime);
            game.setBeforTime(beforTime);
            game.setInterval(interval);
            game.setTitle(title);
            game.setDescr(descr);
            game.setSort(sort);
            System.out.println("头像：" + avatar);
            game.setIcon(avatar);
            game.setUrl(url);
            gameService.updateById(game);
            if (game.getId() == 1) {
                ApiController.CanadaGame = game;
            } else {
                //北京28

            }
        } else {
            throw new RuntimeException("设置错误");
        }
        return SUCCESS_TIP;
    }

}
