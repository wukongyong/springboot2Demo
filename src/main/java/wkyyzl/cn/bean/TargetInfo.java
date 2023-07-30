package wkyyzl.cn.bean;

import lombok.Data;

import java.util.List;

@Data
public class TargetInfo {

    private String id;

    private String eventTime;

    private String windowSize;

    private String value;

    private String operate;

    private String operateNum;

    private List<TargetAlarmRule> targetAlarmRules;

}
