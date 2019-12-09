package com.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFlow {
    private Integer recordId;

    private Date recordDate;

    private String recordHandletype;

    private BigDecimal recordHandlemoney;

    private Integer accountId;

    private BigDecimal recordSurplus;

    private String recordNotes;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordHandletype() {
        return recordHandletype;
    }

    public void setRecordHandletype(String recordHandletype) {
        this.recordHandletype = recordHandletype == null ? null : recordHandletype.trim();
    }

    public BigDecimal getRecordHandlemoney() {
        return recordHandlemoney;
    }

    public void setRecordHandlemoney(BigDecimal recordHandlemoney) {
        this.recordHandlemoney = recordHandlemoney;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getRecordSurplus() {
        return recordSurplus;
    }

    public void setRecordSurplus(BigDecimal recordSurplus) {
        this.recordSurplus = recordSurplus;
    }

    public String getRecordNotes() {
        return recordNotes;
    }

    public void setRecordNotes(String recordNotes) {
        this.recordNotes = recordNotes == null ? null : recordNotes.trim();
    }
}