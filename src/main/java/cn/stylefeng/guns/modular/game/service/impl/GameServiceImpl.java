package cn.stylefeng.guns.modular.game.service.impl;

import cn.stylefeng.guns.modular.game.model.Game;
import cn.stylefeng.guns.modular.game.dao.GameMapper;
import cn.stylefeng.guns.modular.game.service.IGameService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏表 服务实现类
 * </p>
 *
 * @author canrom7
 * @since 2019-04-09
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {

}
