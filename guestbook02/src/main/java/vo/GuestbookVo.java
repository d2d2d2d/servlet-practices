package com.douzone.guestbook.vo;

import java.sql.Timestamp;

public class GuestbookVo {
		private Long no;
		private String name;
		private String contents;
		private String legDate;
		private String password;
		
		@Override
		public String toString() {
			return "GuestbookVo [no=" + no + ", name=" + name + ", contents=" + contents + ", legDate=" + legDate
					+ ", password=" + password + "]";
		}
		public Long getNo() {
			return no;
		}
		public void setNo(Long no) {
			this.no = no;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}
		public String getLegDate() {
			return legDate;
		}
		public void setLegDate(String legDate) {
			this.legDate = legDate;
		}
		public String getPassword() {
			
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}
