package cn.stylefeng.guns.modular.game.service.impl;

import cn.stylefeng.guns.modular.game.model.Canada28;
import cn.stylefeng.guns.modular.game.dao.Canada28Mapper;
import cn.stylefeng.guns.modular.game.service.ICanada28Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 加拿大28数据 服务实现类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-10
 */
@Service
public class Canada28ServiceImpl extends ServiceImpl<Canada28Mapper, Canada28> implements ICanada28Service {

    @Override
    public List<Canada28> selectListByQuery(Page<Canada28> page, String condition) {
        return baseMapper.selectCanadaByQuery(page,condition);
    }
}
