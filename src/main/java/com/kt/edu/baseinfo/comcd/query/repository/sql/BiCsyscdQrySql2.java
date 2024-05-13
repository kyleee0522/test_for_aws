package com.kt.edu.baseinfo.comcd.query.repository.sql;

public class BiCsyscdQrySql2 {

    public static final String RETV_PPON_CONT_PAGE = """
			 SELECT("GRP_ID AS grpId");
			 			SELECT("CD AS cd");
			 			SELECT("NAME AS name");
			 			SELECT("VAL1 AS val1");
			 			SELECT("VAL2 AS val2");
			 			SELECT("VAL3 AS val3");
			 			SELECT("VAL4 AS val4");
			 			SELECT("VAL5 AS val5");
			 			SELECT("REG_DATE AS regDate");
			 			FROM("BI_CSYSCD");
			 			if(inDto.getGrpId() != null && !"".equals(inDto.getGrpId().trim())) {
			 				WHERE("GRP_ID = '"+inDto.getGrpId()+"'");
			 			}
			 			if(inDto.getCd() != null && !"".equals(inDto.getCd().trim())) {
			 				WHERE("CD = '"+inDto.getCd()+"'");
			 			} 
			""";


    public static final String RETV_CSYS_CD = """
				SELECT
					GRP_ID AS grpId,
					CD AS cd,
					NAME AS name,
					VAL1 AS val1,
					VAL2 AS val2,
					VAL3 AS val3,
					VAL4 AS val4,
					VAL5 AS val5,
					REG_DATE AS regDate
				FROM
					BI_CSYSCD
				WHERE GRP_ID=:inDto.getGrpId()
			""";

	//					svc_no like '%'||:srhwd||'%'
}