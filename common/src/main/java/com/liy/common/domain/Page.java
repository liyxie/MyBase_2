package com.liy.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页对象模型
 * @Author liy
 *
 */

@ToString
public class Page<T> implements IPage<T> {

    /**
     * 查询数据列表
     */
    protected List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;

    /**
     * 排序字段信息
     */
    @Getter
    @Setter
    protected List<OrderItem> orders = new ArrayList<>();

    /**
     * 自动优化 COUNT SQL
     */
    protected boolean optimizeCountSql = true;
    /**
     * 是否进行 count 查询
     */
    protected boolean isSearchCount = true;
    /**
     * 是否命中count缓存
     */
    protected boolean hitCount = false;
    /**
     * countId
     */
    @Getter
    @Setter
    protected String countId;
    /**
     * countId
     */
    @Getter
    @Setter
    protected Long maxLimit;

    public Page() {
    }

    public Page(Page<?> page){
        this.current = page.getCurrent();
        this.isSearchCount = page.isSearchCount;
        this.total = page.getTotal();
        this.size = page.getSize();
        this.countId = page.getCountId();
        this.hitCount = page.hitCount;
        this.maxLimit = page.getMaxLimit();
        this.optimizeCountSql = page.optimizeCountSql;
        this.orders = page.getOrders();
        this.records = Collections.emptyList();
    }
    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public Page(long current, long size) {
        this(current, size, 0);
    }

    public Page(long current, long size, long total) {
        this(current, size, total, true);
    }

    public Page(long current, long size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    public Page(long current, long size, long total, boolean isSearchCount) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    /**
     * 是否存在上一页
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Page<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public Page<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String countId() {
        return getCountId();
    }
//    获取排序信息，排序的字段和正反序
//    返回值:
//    排序信息
    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public Long maxLimit() {
        return getMaxLimit();
    }



}
