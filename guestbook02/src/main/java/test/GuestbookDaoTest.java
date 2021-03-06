package com.douzone.gusetbook.dao.test;

import java.util.List;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

public class GuestbookDaoTest {

	public static void main(String[] args) {
		insertTest();
		selectTest();
//		GuestbookVo vo = new GuestbookVo();
//		vo.setNo(22L);
//		vo.setPassword("a");
//		new GuestbookDao().delete(vo);
//		selectTest();
	}

	public static void selectTest() {
		List<GuestbookVo> list = new GuestbookDao().findAll();
		for(GuestbookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void insertTest() {
		GuestbookVo vo = new GuestbookVo();
		vo.setName("또치님");
		vo.setContents("또치야");
		vo.setPassword("a");
		new GuestbookDao().insert(vo);
	}

}
