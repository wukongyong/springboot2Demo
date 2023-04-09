package wkyyzl.cn.bean.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wukongyong
 * @since 2023-04-09
 */
@Data
@TableName("tbl_role")
public class Role extends Model<Role> implements GrantedAuthority {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String roleName;

    private String roleDesc;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
