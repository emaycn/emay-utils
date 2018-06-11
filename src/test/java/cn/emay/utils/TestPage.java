package cn.emay.utils;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.emay.utils.page.Page;

public class TestPage {

	private int currentPageNum = 5;

	private int limit = 20;

	private int totalCount = 1001;

	private int start = 80;

	private List<?> list;

	private int listSize = 100;

	private int totalPage = 0;

	@Before
	public void before() {
		list = Mockito.mock(List.class);
		Mockito.when(list.size()).thenReturn(listSize);
		totalPage = totalCount / limit + (totalCount % limit == 0 ? 0 : 1);
	}

	@After
	public void after() {
		list = null;
	}

	@Test()
	public void testCreateByPageNumAndLimitWithData() {
		Page<?> page = Page.createByPageNumAndLimit(currentPageNum, limit, totalCount, list);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNotNull(page.getList());
		Assert.assertEquals(page.getList().size(), listSize);
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

	@Test()
	public void testCreateByPageNumAndLimitWithoutData() {
		Page<?> page = Page.createByPageNumAndLimit(currentPageNum, limit, totalCount);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNull(page.getList());
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

	@Test()
	public void testCreateByStartAndLimitWithData() {
		Page<?> page = Page.createByStartAndLimit(start, limit, totalCount, list);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNotNull(page.getList());
		Assert.assertEquals(page.getList().size(), listSize);
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

	@Test()
	public void testCreateByStartAndLimitWithoutData() {
		Page<?> page = Page.createByStartAndLimit(start, limit, totalCount);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNull(page.getList());
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

	@Test()
	public void testToMap() {
		Page<?> page = Page.createByStartAndLimit(start, limit, totalCount, list);
		Map<String, Object> map = page.toMap();
		Assert.assertEquals(map.get(Page.CURRENT_PAGE), currentPageNum);
		Assert.assertEquals(map.get(Page.LIMIT), limit);
		Assert.assertNotNull(map.get(Page.DATA_LIST));
		Assert.assertEquals(((List<?>) map.get(Page.DATA_LIST)).size(), listSize);
		Assert.assertEquals(map.get(Page.START), start);
		Assert.assertEquals(map.get(Page.TOTAL_COUNT), totalCount);
		Assert.assertEquals(map.get(Page.TOTAL_PAGE), totalPage);
	}

	@Test()
	public void testSetNumByCurrentPageAndLimit() {
		Page<?> page = new Page<>();
		page.setNumByCurrentPageAndLimit(currentPageNum, limit, totalCount);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNull(page.getList());
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

	@Test()
	public void TestSetNumByStartAndLimit() {
		Page<?> page = new Page<>();
		page.setNumByStartAndLimit(start, limit, totalCount);
		Assert.assertEquals(page.getCurrentPageNum(), currentPageNum);
		Assert.assertEquals(page.getLimit(), limit);
		Assert.assertNull(page.getList());
		Assert.assertEquals(page.getStart(), start);
		Assert.assertEquals(page.getTotalCount(), totalCount);
		Assert.assertEquals(page.getTotalPage(), totalPage);
	}

}
