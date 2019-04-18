package cn.stylefeng.guns.modular.game.service;

import cn.stylefeng.guns.modular.game.model.YzUser;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-04
 */
public interface IYzUserService extends IService<YzUser> {
    /**
     * 根据条件查询用户
     * @param page       分页
     * @param name       UID/姓名/手机号/昵称
     * @param beginTime  开始时间
     * @param endTime    结束时间
     */
    List<YzUser> selectListByQuery(Page<YzUser> page, String name, String beginTime, String endTime);
}
