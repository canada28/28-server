package cn.stylefeng.guns.modular.game.service;

import cn.stylefeng.guns.modular.game.model.Canada28;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 加拿大28数据 服务类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-10
 */
public interface ICanada28Service extends IService<Canada28> {
    /**
     * 加拿大28数据列表
     * @param page       分页
     * @param condition  期数
     * @return
     */
    List<Canada28> selectListByQuery(Page<Canada28> page, String condition);

}
