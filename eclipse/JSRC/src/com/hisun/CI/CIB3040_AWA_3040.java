package com.hisun.CI;

public class CIB3040_AWA_3040 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String TYPE = " ";
    public short TYPE_NO = 0;
    public String OBJ_TYP = " ";
    public short OBJ_TYP_NO = 0;
    public String LC_OBJ = " ";
    public short LC_OBJ_NO = 0;
    public String CUS_AC = " ";
    public short CUS_AC_NO = 0;
    public String TX_TYPE = " ";
    public short TX_TYPE_NO = 0;
    public CIB3040_VAL_INFO[] VAL_INFO = new CIB3040_VAL_INFO[3];
    public int LS_LC_NO = 0;
    public short LS_LC_NO_NO = 0;
    public int LS_SEQ = 0;
    public short LS_SEQ_NO = 0;
    public int LC_NO2 = 0;
    public short LC_NO2_NO = 0;
    public int LC_SEQ2 = 0;
    public short LC_SEQ2_NO = 0;
    public String LS_CNTYP = " ";
    public short LS_CNTYP_NO = 0;
    public int LS_SBSEQ = 0;
    public short LS_SBSEQ_NO = 0;
    public CIB3040_AWA_3040() {
        for (int i=0;i<3;i++) VAL_INFO[i] = new CIB3040_VAL_INFO();
    }
}
