package com.hisun.BP;

public class BPB2500_AWA_2500 {
    public String TLR = " ";
    public short TLR_NO = 0;
    public String TLR_PSW = " ";
    public short TLR_PSW_NO = 0;
    public String ATM = " ";
    public short ATM_NO = 0;
    public String ATM_PSW = " ";
    public short ATM_PSW_NO = 0;
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int MOVE_DT = 0;
    public short MOVE_DT_NO = 0;
    public BPB2500_CCY_INFO[] CCY_INFO = new BPB2500_CCY_INFO[5];
    public BPB2500_PAR_INFO[] PAR_INFO = new BPB2500_PAR_INFO[60];
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public BPB2500_AWA_2500() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2500_CCY_INFO();
        for (int i=0;i<60;i++) PAR_INFO[i] = new BPB2500_PAR_INFO();
    }
}
