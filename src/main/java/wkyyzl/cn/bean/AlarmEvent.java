package wkyyzl.cn.bean;

import lombok.Data;

@Data
public class AlarmEvent {
    //日期
    private String date;
    //时间
    private String time;
    //级别
    private String level;
    //内容
    private String content;
    //对象类型
    private String type;
    //对象ip
    private String ip;
    //
    private String topicName;

    private String targetKey;

}
