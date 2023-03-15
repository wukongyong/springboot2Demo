package wkyyzl.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wkyyzl.cn.bean.Device;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wukongyong
 * @since 2023-02-15
 */
public interface DeviceMapper extends BaseMapper<Device> {

    //@Update("truncate table tb_Device")
    void truncateTable();

}
