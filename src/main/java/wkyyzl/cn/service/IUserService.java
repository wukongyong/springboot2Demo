package wkyyzl.cn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wkyyzl.cn.bean.db.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wukongyong
 * @since 2023-04-09
 */
public interface IUserService extends IService<User> {

    List<User> getUserIncludeRolesByUserName(String userName);

}
