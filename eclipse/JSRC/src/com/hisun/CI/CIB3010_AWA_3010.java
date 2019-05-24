package com.hisun.CI;

public class CIB3010_AWA_3010 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String TYPE = " ";
    public short TYPE_NO = 0;
    public String NAME = " ";
    public short NAME_NO = 0;
    public String OBJ_TYP = " ";
    public short OBJ_TYP_NO = 0;
    public char SPE_FLG = ' ';
    public short SPE_FLG_NO = 0;
    public char CT_FLG = ' ';
    public short CT_FLG_NO = 0;
    public String TX_TYPE = " ";
    public short TX_TYPE_NO = 0;
    public String CON_SPT = " ";
    public short CON_SPT_NO = 0;
    public String CON_MTH = " ";
    public short CON_MTH_NO = 0;
    public String CON_LWTP = " ";
    public short CON_LWTP_NO = 0;
    public CIB3010_VAL[] VAL = new CIB3010_VAL[7];
    public char HIS_FLG = ' ';
    public short HIS_FLG_NO = 0;
    public char ONE_FLG = ' ';
    public short ONE_FLG_NO = 0;
    public String MTX_TYP1 = " ";
    public short MTX_TYP1_NO = 0;
    public String MTX_TYP2 = " ";
    public short MTX_TYP2_NO = 0;
    public String MTX_TYP3 = " ";
    public short MTX_TYP3_NO = 0;
    public int STA_TM = 0;
    public short STA_TM_NO = 0;
    public int END_TM = 0;
    public short END_TM_NO = 0;
    public CIB3010_AWA_3010() {
        for (int i=0;i<7;i++) VAL[i] = new CIB3010_VAL();
    }
}
