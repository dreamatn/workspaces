package com.hisun.BP;

public class BPB2600_AWA_2600 {
    public String APT_CINO = " ";
    public short APT_CINO_NO = 0;
    public int APT_NO = 0;
    public short APT_NO_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public int APT_DT = 0;
    public short APT_DT_NO = 0;
    public int APT_TM = 0;
    public short APT_TM_NO = 0;
    public double APT_AMT = 0;
    public short APT_AMT_NO = 0;
    public int APT_WDDT = 0;
    public short APT_WDDT_NO = 0;
    public String APT_CHNL = " ";
    public short APT_CHNL_NO = 0;
    public int APT_BR = 0;
    public short APT_BR_NO = 0;
    public String CONTACT = " ";
    public short CONTACT_NO = 0;
    public String CONT_PN = " ";
    public short CONT_PN_NO = 0;
    public String CASH_TYP = " ";
    public short CASH_TYP_NO = 0;
    public String APT_CCY = " ";
    public short APT_CCY_NO = 0;
    public BPB2600_CCY_INFO[] CCY_INFO = new BPB2600_CCY_INFO[20];
    public BPB2600_AWA_2600() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPB2600_CCY_INFO();
    }
}
