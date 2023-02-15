package wkyyzl.cn.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wukongyong
 * @since 2023-02-15
 */
@Data
@TableName("tb_Device")
public class Device extends Model<Device> {

    private String sn;

    private Integer type;

    private Integer status;

    private String ipAddress;

    private Long projectId;

    private String deviceName;

    private String swv;

    private String hwv;

    private Long debugTaskId;

    private Long userId;

    @Override
    public Serializable pkVal() {
        return this.sn;
    }
}
