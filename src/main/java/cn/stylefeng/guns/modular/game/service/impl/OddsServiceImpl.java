package cn.stylefeng.guns.modular.game.service.impl;

import cn.stylefeng.guns.modular.game.model.Odds;
import cn.stylefeng.guns.modular.game.dao.OddsMapper;
import cn.stylefeng.guns.modular.game.service.IOddsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 赔率配置表 服务实现类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-11
 */
@Service
public class OddsServiceImpl extends ServiceImpl<OddsMapper, Odds> implements IOddsService {

}
