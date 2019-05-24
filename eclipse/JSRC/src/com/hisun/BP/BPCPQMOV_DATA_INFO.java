package com.hisun.BP;

public class BPCPQMOV_DATA_INFO {
    public int MOV_DT = 0;
    public int CONF_NO = 0;
    public String CASH_TYP = " ";
    public String CCY = " ";
    public char MOV_TYP = ' ';
    public char MOV_STS = ' ';
    public int BR = 0;
    public char CS_KIND = ' ';
    public double AMT = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public String OUT_AC = " ";
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public String IN_AC = " ";
    public int BV_DT = 0;
    public String BV_CODE = " ";
    public String BV_NO = " ";
    public String TO_BANK = " ";
    public int PAR_CNT = 0;
    public BPCPQMOV_PAR_INFO[] PAR_INFO = new BPCPQMOV_PAR_INFO[20];
    public BPCPQMOV_DATA_INFO() {
        for (int i=0;i<20;i++) PAR_INFO[i] = new BPCPQMOV_PAR_INFO();
    }
}
