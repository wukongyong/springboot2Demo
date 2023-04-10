package wkyyzl.cn.bean.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author wukongyong
 * @since 2023-04-10
 */
@Data
@TableName("tbl_user_role")
public class UserRole extends Model<UserRole> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
