package wkyyzl.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wkyyzl.cn.bean.db.User;
import wkyyzl.cn.mapper.UserMapper;
import wkyyzl.cn.service.IUserService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wukongyong
 * @since 2023-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", username);
        List<User> users = new User().selectList(userQueryWrapper);
        if (users == null || users.size() == 0) {
            //无此用户
            throw new UsernameNotFoundException("用户名错误!!!");
        } else if (users.size() > 1) {
            //此用户名大于1个
            throw new RuntimeException("用户数量大于1!!!");
        } else {
            //正常情况
            User user = users.get(0);
            return user;
        }
    }

}
