package com.jin.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hujin
 * @version 2022/1/23
 */
@Getter
@Setter
public class QueryVO<T> {

    /**
     * 当前页码
     */
    private Long pageNo;

    /**
     * 每页条目数
     */
    private Long pageSize;

    /**
     * 从第几条开始(pageNo-1)*pageSize
     */
    private Long offset;

    /**
     * 账户查询条件
     */
    private T condition;

    public Long getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
