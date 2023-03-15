package wkyyzl.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import wkyyzl.cn.bean.Device;
import wkyyzl.cn.mapper.DeviceMapper;
import wkyyzl.cn.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wukongyong
 * @since 2023-02-15
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void truncateTable() {
        deviceMapper.truncateTable();
    }

}
