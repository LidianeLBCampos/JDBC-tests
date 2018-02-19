package com.mysql.first.test;

import com.mysql.first.MySQLAccess;

public class Main {
	public static void main(String[] args) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
	}
}
