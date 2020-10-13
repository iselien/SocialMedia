package io.yovelas.common.model;

import java.util.Date;

/**
 * 操作记录
 * 参数：id,操作时间,操作类型，操作者
 */
public class OperatingRecord {

    /**
     * id
     */
    private int id;

    /**
     * 操作时间
     */
    private Date operatingTime;

    /**
     * 操作类型
     */
    private String operatingType;

    /**
     * 操作者
     */
    private String operator;
}
