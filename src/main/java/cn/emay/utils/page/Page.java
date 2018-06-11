package cn.emay.utils.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页组件
 * 
 * @author Frank
 *
 * @param <T>
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DATA_LIST = "list";// 数据列表Key

	public static final String TOTAL_COUNT = "totalCount";// 数据总数Key

	public static final String START = "start";// 数据起始位置Key

	public static final String LIMIT = "limit";// 每页数据数量Key

	public static final String CURRENT_PAGE = "currentPage";// 当前页Key

	public static final String TOTAL_PAGE = "totalPage";// 总页数Key

	/**
	 * 数据列表
	 */
	private List<T> list = null;

	/**
	 * 总数
	 */
	private int totalCount = 0;

	/**
	 * 从第几条开始
	 */
	private int start = 0;

	/**
	 * 每页多少条
	 */
	private int limit = 0;

	/**
	 * 当前页数
	 */
	private int currentPageNum = 0;

	/**
	 * 总页数
	 */
	private int totalPage = 0;

	/**
	 * 按照当前页数创建
	 * 
	 * @param currentPageNum
	 *            当前页数
	 * @param limit
	 *            每页条数
	 * @param totalCount
	 *            总条数
	 * @param list
	 *            当页数据集合
	 * @return
	 */
	public static <T> Page<T> createByPageNumAndLimit(int currentPageNum, int limit, int totalCount, List<T> list) {
		Page<T> page = new Page<T>();
		page.setNumByCurrentPageAndLimit(currentPageNum, limit, totalCount);
		page.setList(list);
		return page;
	}

	/**
	 * 按照当前页数创建
	 * 
	 * @param currentPageNum
	 *            当前页数
	 * @param limit
	 *            每页条数
	 * @param totalCount
	 *            总条数
	 * @return
	 */
	public static <T> Page<T> createByPageNumAndLimit(int currentPageNum, int limit, int totalCount) {
		Page<T> page = new Page<T>();
		page.setNumByCurrentPageAndLimit(currentPageNum, limit, totalCount);
		return page;
	}

	/**
	 * 按照起始条数创建
	 * 
	 * @param start
	 *            起始条数
	 * @param limit
	 *            每页条数
	 * @param totalCount
	 *            总条数
	 * @param list
	 *            当页数据集合
	 * @return
	 */
	public static <T> Page<T> createByStartAndLimit(int start, int limit, int totalCount, List<T> list) {
		Page<T> page = new Page<T>();
		page.setNumByStartAndLimit(start, limit, totalCount);
		page.setList(list);
		return page;
	}

	/**
	 * 按照起始条数创建
	 * 
	 * @param start
	 *            起始条数
	 * @param limit
	 *            每页条数
	 * @param totalCount
	 *            总条数
	 * @return
	 */
	public static <T> Page<T> createByStartAndLimit(int start, int limit, int totalCount) {
		Page<T> page = new Page<T>();
		page.setNumByStartAndLimit(start, limit, totalCount);
		return page;
	}

	/**
	 * 转化为Map，Key值：<br/>
	 * 
	 * cn.emay.basic.Page.DATA_LIST = "list";// 数据列表Key<br/>
	 * cn.emay.basic.Page.TOTAL_COUNT = "totalCount";// 数据总数Key<br/>
	 * cn.emay.basic.Page.START = "start";// 数据起始位置Key<br/>
	 * cn.emay.basic.Page.LIMIT = "limit";// 每页数据数量Key<br/>
	 * cn.emay.basic.Page.CURRENT_PAGE = "currentPage";// 当前页数Key<br/>
	 * cn.emay.basic.Page.TOTAL_PAGE = "totalPage";// 总页数Key<br/>
	 * 
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(LIMIT, this.getLimit());
		map.put(START, this.getStart());
		map.put(TOTAL_COUNT, this.getTotalCount());
		map.put(CURRENT_PAGE, this.getCurrentPageNum());
		map.put(TOTAL_PAGE, this.getTotalPage());
		map.put(DATA_LIST, this.getList());
		return map;
	}

	/**
	 * 按照起始位置与每页条数构建Page
	 * 
	 * @param start
	 * @param limit
	 * @param totalCount
	 */
	public void setNumByStartAndLimit(int start, int limit, int totalCount) {
		this.start = start;
		this.limit = limit;
		this.totalCount = totalCount;
		this.currentPageNum = start / limit + 1;
		this.totalPage = totalCount / limit;
		if (totalCount % limit != 0) {
			this.totalPage++;
		}
	}

	/**
	 * 按照当前页数与每页条数构建Page
	 * 
	 * @param currentPageNum
	 * @param limit
	 * @param totalCount
	 */
	public void setNumByCurrentPageAndLimit(int currentPageNum, int limit, int totalCount) {
		this.currentPageNum = currentPageNum;
		this.limit = limit;
		this.totalCount = totalCount;
		int starttemp = (currentPageNum - 1) * limit;
		this.start = starttemp <= 0 ? 0 : starttemp;
		this.totalPage = totalCount / limit;
		if (totalCount % limit != 0) {
			this.totalPage++;
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
