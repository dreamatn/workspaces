package com.hisun.BA;

public class BAB2040_AWA_2040 {
    public String ID_NO = " ";
    public short ID_NO_NO = 0;
    public String ENCR = " ";
    public short ENCR_NO = 0;
    public String LMT_SYS = " ";
    public short LMT_SYS_NO = 0;
    public String RSN = " ";
    public short RSN_NO = 0;
    public short FEE_CNT = 0;
    public short FEE_CNT_NO = 0;
    public BAB2040_FEE_DATA[] FEE_DATA = new BAB2040_FEE_DATA[5];
    public BAB2040_AWA_2040() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BAB2040_FEE_DATA();
    }
}
