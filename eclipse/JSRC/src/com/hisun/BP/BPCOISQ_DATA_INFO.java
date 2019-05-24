package com.hisun.BP;

public class BPCOISQ_DATA_INFO {
    public char FUNC = ' ';
    public String SQTP_TYPE = " ";
    public BPCOISQ_STA_QTP_INFO[] STA_QTP_INFO = new BPCOISQ_STA_QTP_INFO[30];
    public int EFF_BEG_DATE = 0;
    public int EFF_END_DATE = 0;
    public short CMPL_CNT = 0;
    public short OUT_REC_CNT = 0;
    public char CONT_FLAG = ' ';
    public char CMPL_FLAG = ' ';
    public BPCOISQ_DATA_INFO() {
        for (int i=0;i<30;i++) STA_QTP_INFO[i] = new BPCOISQ_STA_QTP_INFO();
    }
}
