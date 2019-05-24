package com.hisun.BP;

public class BPB4800_AWA_4800 {
    public char IN_FLG = ' ';
    public short IN_FLG_NO = 0;
    public String SYS_MMO = " ";
    public short SYS_MMO_NO = 0;
    public String TX_CD = " ";
    public short TX_CD_NO = 0;
    public char STS = ' ';
    public short STS_NO = 0;
    public char TX_LVL = ' ';
    public short TX_LVL_NO = 0;
    public char ATH_LVL1 = ' ';
    public short ATH_LVL1_NO = 0;
    public char ATH_LVL2 = ' ';
    public short ATH_LVL2_NO = 0;
    public char SELF_FLG = ' ';
    public short SELF_FLG_NO = 0;
    public char COR_FLG = ' ';
    public short COR_FLG_NO = 0;
    public char BAT_FLG = ' ';
    public short BAT_FLG_NO = 0;
    public char SATH_FLG = ' ';
    public short SATH_FLG_NO = 0;
    public BPB4800_STSW_ARR[] STSW_ARR = new BPB4800_STSW_ARR[30];
    public char RUN_MODE = ' ';
    public short RUN_MODE_NO = 0;
    public String TAB_CD = " ";
    public short TAB_CD_NO = 0;
    public int UPD_DT = 0;
    public short UPD_DT_NO = 0;
    public String UPD_TLR = " ";
    public short UPD_TLR_NO = 0;
    public String EDESC = " ";
    public short EDESC_NO = 0;
    public String CDESC = " ";
    public short CDESC_NO = 0;
    public BPB4800_AWA_4800() {
        for (int i=0;i<30;i++) STSW_ARR[i] = new BPB4800_STSW_ARR();
    }
}
