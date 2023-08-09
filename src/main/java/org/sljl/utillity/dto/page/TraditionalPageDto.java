/**
 * 
 */
package org.sljl.utillity.dto.page;

import java.util.List;


/**
 * 传统分页工具类
 * 
 * @author L.Y.F
 *
 */
public class TraditionalPageDto<T> extends PageDto<T> {
	
	private static final long serialVersionUID = 4243831526571765059L;
	
	// 默认每页显示的数量
	private static final int DEFAULT_INITIAL_CAPACITY = 20;
	
	// 总条数
	private int totalCnt;
	
	/**
	 * 默认初始分页容量20
	 */
	public TraditionalPageDto() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	/**
	 * 显示的指定初始分页容量（推荐调用该方法，初始容量设置成符合当前需求的每页显示条数）
	 * @param initialCapacity
	 */
	public TraditionalPageDto(int initialCapacity) {
		super(initialCapacity);
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
	 * 当前页数
	 * 
	 * @return 
	 */
	public int getCurrentPage() {
		return super.getCurrentPage();
	}
	
	/**
	 * 当前页数
	 * 
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		super.setCurrentPage(currentPage);
	}
	
	/**
	 * 每页显示的数量
	 * 
	 * @return 
	 */
	public int getPageSize() {
		return super.getPageSize();
	}
	
	/**
	 * 总条数
	 * 
	 * @return
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * 总条数
	 * 
	 * @param totalCnt
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

}
