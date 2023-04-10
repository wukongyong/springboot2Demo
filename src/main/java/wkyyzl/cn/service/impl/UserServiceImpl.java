package wkyyzl.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wkyyzl.cn.bean.db.Role;
import wkyyzl.cn.bean.db.User;
import wkyyzl.cn.bean.db.UserRole;
import wkyyzl.cn.mapper.UserMapper;
import wkyyzl.cn.service.IUserService;

import java.util.ArrayList;
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
        List<User> users = getUserIncludeRolesByUserName(username);
        if (users == null || users.size() == 0) {
            //无此用户
            //抛出的UsernameNotFoundException会被security隐藏成BadCredentialsException
            //若想不隐藏，可以自定义异常类
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

    @Override
    public List<User> getUserIncludeRolesByUserName(String userName) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userName);
        List<User> users = new User().selectList(userQueryWrapper);
        for (User user : users) {
            //获取该用户的所有角色
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", user.getId());
            List<UserRole> userRoles = new UserRole().selectList(userRoleQueryWrapper);

            List<Role> roleList = new ArrayList<>();
            for (UserRole userRole : userRoles) {
                Role role = new Role().selectById(userRole.getRoleId());
                if (role != null) {
                    roleList.add(role);
                }
            }

            user.setRoleList(roleList);
        }

        return users;
    }
}
