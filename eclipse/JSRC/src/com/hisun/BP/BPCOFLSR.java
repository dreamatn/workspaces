package com.hisun.BP;

public class BPCOFLSR {
    public int TRAN_DT = 0;
    public long JRNNO = 0;
    public int CAP_NO = 0;
    public char TRAN_TYP = ' ';
    public int BR = 0;
    public String TRAN_TLR = " ";
    public String REC_PBNO = " ";
    public String PAY_PBNO = " ";
    public String HLD_IDTP = " ";
    public String HLD_IDNO = " ";
    public char FILLER11 = 0X02;
    public String HLD_NM = " ";
    public char FILLER13 = 0X02;
    public String HLD_PHN = " ";
    public String HLD_ADD = " ";
    public char FILLER16 = 0X02;
    public String HLD_EML = " ";
    public int REC_NUM = 0;
    public BPCOFLSR_FLS_DATA FLS_DATA = new BPCOFLSR_FLS_DATA();
}
