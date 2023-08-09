package org.sljl.utillity.dto.page;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 专门针对只需要知道是否有下一页，不需要总数这种特定场景使用的分页工具类
 * 
 * @author L.Y.F
 *
 * @param <T>
 */
public class LackOfCntPageDto<T> extends PageDto<T> {
	
	private static final long serialVersionUID = 8026210272730871770L;
	
	// 默认每页显示的数量
	private static final int DEFAULT_INITIAL_CAPACITY = 20;
	// 是否有下一页
	private boolean haveNext = false;
	
	/**
	 * 默认初始分页容量20
	 */
	public LackOfCntPageDto() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	/**
	 * 显示的指定初始分页容量（推荐调用该方法，初始容量设置成符合当前需求的每页显示条数）
	 * @param initialCapacity
	 */
	public LackOfCntPageDto(int initialCapacity) {
		super(initialCapacity);
	}
	
	/**
     * 当平台不返回总条数时，调用此方法 此方法会做两步操作 （此方法会把传入的datas集合按照pageSize数量进行截取）
     * 1. 截取正确的分页数量条记录 
     * 2. 计算出是否有下一页
     * 
     * @param data ： 查询的列表结果
     * @param pageSize : 真实的每页数量
     */
    public void setData(List<T> data, int pageSize) {
        List<T> list = new ArrayList<T>(data);
        setHaveNext(list.size() <= pageSize ? false : true);
        if (list.size() > pageSize) {
            list = list.subList(0, pageSize);
        }
        super.setData(list);
    }
    
    /**
	 * 设置是否有下一页
	 * 
	 * @param haveNext
	 */
    public void setHaveNext(boolean haveNext) {
		this.haveNext = haveNext;
	}
    
    /**
	 * 获取是否有下一页
	 * @return
	 */
    public boolean isHaveNext() {
		return haveNext;
	}
    
    /**
	 * 获取分页数据
	 * @return
	 */
    public List<T> getData() {
		return super.getData();
	}
    
    /**
	 * 填充分页数据
	 * @param data
	 */
    public void setData(List<T> data) {
		super.setData(data);
	}
    
    /**
     * 内存分页方法（多取一条方式）
     * @param dataList：待分页的数据集
     * @param currentPage：当前页数
     * @param pageSize：每页显示条数
     * @return
     */
    public List<T> queryLackPage(List<T> dataList, int currentPage, int pageSize) {
    	if (CollectionUtils.isEmpty(dataList)) {
    		return dataList;
    	}
        int fromIndex = (currentPage - 1) * pageSize;
        if (fromIndex >= dataList.size()) {
            return Collections.emptyList();
        }

        int toIndex = currentPage * pageSize+1;
        if (toIndex >= dataList.size()) {
            toIndex = dataList.size();
        }
        return dataList.subList(fromIndex, toIndex);
    }

}
