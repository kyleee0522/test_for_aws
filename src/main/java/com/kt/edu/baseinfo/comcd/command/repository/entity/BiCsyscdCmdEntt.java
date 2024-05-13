package com.kt.edu.baseinfo.comcd.command.repository.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "BI_CSYSCD")
public class BiCsyscdCmdEntt implements Persistable<String> {
	@Column("GRP_ID")
	private String grpId;

	@Column("CD")
	private String cd;

	@Column("NAME")
	private String name;

	@Column("VAL1")
	private String val1;

	@Column("VAL2")
	private String val2;

	@Column("VAL3")
	private String val3;

	@Column("VAL4")
	private String val4;

	@Column("VAL5")
	private String val5;

	@Column("REG_DATE")
	private Timestamp regDate;
	
	@Transient
	public boolean isNew = false;

	@Override
	public String getId() {
		return null;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
