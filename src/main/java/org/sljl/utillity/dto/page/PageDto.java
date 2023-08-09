package org.sljl.utillity.dto.page;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类基础类
 * 
 * @author L.Y.F
 * 
 */
public class PageDto<T> implements Serializable {

	private static final long serialVersionUID = 7481564023567174953L;

	// 存放数据的字段，默认实现是ArrayList
	private List<T> data;
	// 当前页数
	private int currentPage;
	// 每页显示的数量
	private int pageSize;
	
	protected PageDto(){}
	
	protected PageDto(int initialCapacity) {
		this.pageSize = initialCapacity;
		this.data = Lists.<T>newArrayListWithCapacity(initialCapacity);
	}
	
	/**
	 * 获取分页数据
	 * @return
	 */
	protected List<T> getData() {
		return data;
	}

	/**
	 * 填充分页数据
	 * @param data
	 */
	protected void setData(List<T> data) {
		this.data = data;
	}
	
	/**
	 * 直接为datas列表增加data，注意，这个方法不是线程安全的
	 * @param data
	 */
	protected void addData(T data) {
		this.data.add(data);
	}
	
	/**
	 * 当前页数
	 * 
	 * @return 
	 */
	protected int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * 当前页数
	 * 
	 * @param currentPage
	 */
	protected void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 每页显示的数量
	 * 
	 * @return 
	 */
	protected int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 每页显示的数量
	 * 
	 * @param pageSize 
	 */
	protected void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
