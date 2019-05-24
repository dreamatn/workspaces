package com.hisun.LN;

public class LNCXP08_DATA_TXT {
    public String PRODMO = " ";
    public char PROD_MOD = ' ';
    public String PROD_CLS = " ";
    public LNCXP08_CCY_INF[] CCY_INF = new LNCXP08_CCY_INF[2];
    public short MIN_DD = 0;
    public short MAX_DD = 0;
    public char ACCRUAL_TYPE = ' ';
    public char CM_TYP = ' ';
    public LNCXP08_BSRT_TYP[] BSRT_TYP = new LNCXP08_BSRT_TYP[2];
    public char RT_TYP = ' ';
    public LNCXP08_RT_TERM[] RT_TERM = new LNCXP08_RT_TERM[2];
    public LNCXP08_RAT_MTH[] RAT_MTH = new LNCXP08_RAT_MTH[2];
    public char CFT_FLG = ' ';
    public LNCXP08_RT_P_U[] RT_P_U = new LNCXP08_RT_P_U[2];
    public short RT_PERD = 0;
    public LNCXP08_RT_MTH[] RT_MTH = new LNCXP08_RT_MTH[2];
    public LNCXP08_REPY_MTH[] REPY_MTH = new LNCXP08_REPY_MTH[2];
    public char REPY_CO1 = ' ';
    public String REPY_CT1 = " ";
    public char REPY_CO2 = ' ';
    public String REPY_CT2 = " ";
    public char REPY_CO3 = ' ';
    public String REPY_CT3 = " ";
    public short PAY_LEVL = 0;
    public char BLN_FLG = ' ';
    public double BLN_PP = 0;
    public char VCT_FLG = ' ';
    public int GRA_D = 0;
    public char PGRAMT = ' ';
    public char CGRAMT = ' ';
    public char MAR_FLG = ' ';
    public char MAR_MTH = ' ';
    public LNCXP08_DATA_TXT() {
        for (int i=0;i<2;i++) CCY_INF[i] = new LNCXP08_CCY_INF();
        for (int i=0;i<2;i++) BSRT_TYP[i] = new LNCXP08_BSRT_TYP();
        for (int i=0;i<2;i++) RT_TERM[i] = new LNCXP08_RT_TERM();
        for (int i=0;i<2;i++) RAT_MTH[i] = new LNCXP08_RAT_MTH();
        for (int i=0;i<2;i++) RT_P_U[i] = new LNCXP08_RT_P_U();
        for (int i=0;i<2;i++) RT_MTH[i] = new LNCXP08_RT_MTH();
        for (int i=0;i<2;i++) REPY_MTH[i] = new LNCXP08_REPY_MTH();
    }
}
