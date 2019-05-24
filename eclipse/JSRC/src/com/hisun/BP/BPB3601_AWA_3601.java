package com.hisun.BP;

public class BPB3601_AWA_3601 {
    public String TLR_CHG = " ";
    public short TLR_CHG_NO = 0;
    public int BR_CHG = 0;
    public short BR_CHG_NO = 0;
    public char DES_FLG = ' ';
    public short DES_FLG_NO = 0;
    public char VB_FLG = ' ';
    public short VB_FLG_NO = 0;
    public char MOV_FLG = ' ';
    public short MOV_FLG_NO = 0;
    public long CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public BPB3601_DATA[] DATA = new BPB3601_DATA[10];
    public char IN_FLG = ' ';
    public short IN_FLG_NO = 0;
    public BPB3601_AWA_3601() {
        for (int i=0;i<10;i++) DATA[i] = new BPB3601_DATA();
    }
}
