package wkyyzl.cn.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user") //在yml中全局配置了表名前缀
public class User extends Model<User> {//继承Model类实现ActiveRecord

    //@TableId(type = IdType.AUTO) //在yml中全局配置了主键的自增
    private Long id;
    private String userName;

    @TableField(select = false) //查询时不返回该字段的值
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email") //指定数据表中字段名
    private String mail;

    @TableField(exist = false)
    private String address; //在数据库表中是不存在的

}
