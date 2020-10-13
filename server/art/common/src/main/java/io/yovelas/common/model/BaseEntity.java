package io.yovelas.common.model;

import java.util.Date;
import java.util.List;

/**
 * BaseEntity
 * 参数：id,创建时间，操作记录
 */
public class BaseEntity {

    /**
     * id
     */
    private int id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作记录
     */
    private List<OperatingRecord> operatingRecords;


}
