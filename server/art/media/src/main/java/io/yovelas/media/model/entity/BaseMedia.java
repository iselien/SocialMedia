package io.yovelas.media.model.entity;

import io.yovelas.common.model.BaseEntity;

import java.util.Date;

/**
 * 媒体文件抽象类
 * 一对多参数：专辑，标签(收藏是一种模拟的标签)，创作者，下载记录
 * 一对一参数：文件大小，文件创建日期，MD5，上传者
 */

public class BaseMedia extends BaseEntity {
    /**
     * 文件大小
     */
    private double FileSize;

    /**
     * 文件创建时间
     */
    private Date FileCreateTime;

    /**
     * 文件最后修改时间
     */
    private Date LastModifyTime;

    /**
     * MD5值
     */
    private String FileMD5Value;

    /**
     * 上传者
     */
    private String FileUploader;
}
