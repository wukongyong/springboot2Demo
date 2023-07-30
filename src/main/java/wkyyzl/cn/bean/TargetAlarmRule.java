package wkyyzl.cn.bean;

import lombok.Data;

@Data
public class TargetAlarmRule {

    //指标生成告警事件的唯一标识(由前端生成的uuid)
    private String ruleId;

    //指标名称
    private String name;

    //触发类型(每次/触发/连续触发)
    private String triggerType;

    //规则操作符(用于判断是否符合规则)
    private String operate;

    //阈值
    private String operateNum;

}
