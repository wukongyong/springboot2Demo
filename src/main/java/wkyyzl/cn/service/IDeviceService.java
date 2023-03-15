package wkyyzl.cn.service;

import wkyyzl.cn.bean.Device;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wukongyong
 * @since 2023-02-15
 */
public interface IDeviceService extends IService<Device> {

    void truncateTable();

}
