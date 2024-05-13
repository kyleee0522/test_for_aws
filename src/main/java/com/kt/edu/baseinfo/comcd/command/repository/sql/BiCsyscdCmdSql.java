package com.kt.edu.baseinfo.comcd.command.repository.sql;

public class BiCsyscdCmdSql {
	public static final String INSERT_CSYSCD = """
			INSERT INTO BI_CSYSCD (GRP_ID, CD, NAME, VAL1, VAL2, VAL3, VAL4, VAL5) VALUES 
			(:grpId, :cd, :name, :val1, :val2, :val3, :val4, :val5)
			""";
}
