package com.hisun.BA;

public class BAB2030_AWA_2030 {
    public char XZK_FLG = ' ';
    public short XZK_FLG_NO = 0;
    public String ID_NO_O = " ";
    public short ID_NO_O_NO = 0;
    public String ID_NO_N = " ";
    public short ID_NO_N_NO = 0;
    public String HP_BRK = " ";
    public short HP_BRK_NO = 0;
    public char ENCR_FLG = ' ';
    public short ENCR_FLG_NO = 0;
    public String ENCR = " ";
    public short ENCR_NO = 0;
    public int LP_BR = 0;
    public short LP_BR_NO = 0;
    public String LP_GY = " ";
    public short LP_GY_NO = 0;
    public String ID_TYP = " ";
    public short ID_TYP_NO = 0;
    public char FEE_FLG = ' ';
    public short FEE_FLG_NO = 0;
    public short FEE_CNT = 0;
    public short FEE_CNT_NO = 0;
    public BAB2030_FEE_DATA[] FEE_DATA = new BAB2030_FEE_DATA[5];
    public BAB2030_AWA_2030() {
        for (int i=0;i<5;i++) FEE_DATA[i] = new BAB2030_FEE_DATA();
    }
}
