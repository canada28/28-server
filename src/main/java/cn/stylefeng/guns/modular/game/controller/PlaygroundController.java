package cn.stylefeng.guns.modular.game.controller;

import cn.stylefeng.guns.modular.game.model.YzUser;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.game.model.Playground;
import cn.stylefeng.guns.modular.game.service.IPlaygroundService;

/**
 * 房间管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-09 16:14:06
 */
@Controller
@RequestMapping("/playground")
public class PlaygroundController extends BaseController {

    private String PREFIX = "/game/playground/";

    @Autowired
    private IPlaygroundService playgroundService;

    /**
     * 跳转到房间管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "playground.html";
    }

    /**
     * 跳转到添加房间管理
     */
    @RequestMapping("/playground_add")
    public String playgroundAdd() {
        return PREFIX + "playground_add.html";
    }

    /**
     * 跳转到修改房间管理
     */
    @RequestMapping("/playground_update/{playgroundId}")
    public String playgroundUpdate(@PathVariable Integer playgroundId, Model model) {
        Playground playground = playgroundService.selectById(playgroundId);
        model.addAttribute("item",playground);
        LogObjectHolder.me().set(playground);
        return PREFIX + "playground_edit.html";
    }

    /**
     * 获取房间管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return playgroundService.selectList(null);
    }

    /**
     * 新增房间管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Playground playground) {
        playgroundService.insert(playground);
        return SUCCESS_TIP;
    }

    /**
     * 删除房间管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer playgroundId) {
        playgroundService.deleteById(playgroundId);
        return SUCCESS_TIP;
    }

    /**
     * 修改房间管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Playground playground) {
        playgroundService.updateById(playground);
        return SUCCESS_TIP;
    }

    /**
     * 房间管理详情
     */
    @RequestMapping(value = "/detail/{playgroundId}")
    @ResponseBody
    public Object detail(@PathVariable("playgroundId") Integer playgroundId) {
        return playgroundService.selectById(playgroundId);
    }

    /**
     * 房间状态更新
     */
    @RequestMapping(value = "/changePlaygroundStatus")
    @ResponseBody
    public Object changePlaygroundStatus(@RequestParam("pid") Integer userid,
                                   @RequestParam("status") Integer status) {
        Playground user = playgroundService.selectById(userid);
        user.setStatus(status == 1 ? 2 : 1);
        playgroundService.updateById(user);
        return SUCCESS_TIP;
    }

}
