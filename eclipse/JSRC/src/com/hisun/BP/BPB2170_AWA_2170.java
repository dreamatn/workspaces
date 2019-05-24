package com.hisun.BP;

public class BPB2170_AWA_2170 {
    public char MOVE_TYP = ' ';
    public short MOVE_TYP_NO = 0;
    public String IN_TLR = " ";
    public short IN_TLR_NO = 0;
    public int IN_BR = 0;
    public short IN_BR_NO = 0;
    public int OUT_BR = 0;
    public short OUT_BR_NO = 0;
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int MOVE_DT = 0;
    public short MOVE_DT_NO = 0;
    public char CS_KIND = ' ';
    public short CS_KIND_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public BPB2170_CCY_INFO[] CCY_INFO = new BPB2170_CCY_INFO[5];
    public BPB2170_P_INFO[] P_INFO = new BPB2170_P_INFO[60];
    public BPB2170_AWA_2170() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2170_CCY_INFO();
        for (int i=0;i<60;i++) P_INFO[i] = new BPB2170_P_INFO();
    }
}
