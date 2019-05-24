package com.hisun.TD;

public class TDCPIOD_OTH_PRM {
    public char PRD_TYP = ' ';
    public short MAX_NUM = 0;
    public int PAY_GRCE = 0;
    public String PAY_PERD = " ";
    public char PLAN_FLG = ' ';
    public String MIN_TERM = " ";
    public String MAX_TERM = " ";
    public char INT_PRD1 = ' ';
    public char INT_PRD2 = ' ';
    public char INT_PRD3 = ' ';
    public char COMP_FLG = ' ';
    public char INTP_FLG = ' ';
    public char PENA_FLG = ' ';
    public int UNIT_DAY = 0;
    public String INT_PERD = " ";
    public int MAX_GRCE = 0;
    public char CCY_TYP = ' ';
    public char DOCU_FLG = ' ';
    public String REF_CCY = " ";
    public TDCPIOD_AVA_CCY_ARRY[] AVA_CCY_ARRY = new TDCPIOD_AVA_CCY_ARRY[16];
    public TDCPIOD_CCY_INF[] CCY_INF = new TDCPIOD_CCY_INF[25];
    public char NO_NOTIFY_FLG = ' ';
    public char BAL_FLG = ' ';
    public char MID_FLG = ' ';
    public char DELA_FLG = ' ';
    public char CPRA_TYP = ' ';
    public char ACTI_FLG1 = ' ';
    public char FRZ_FLG = ' ';
    public TDCPIOD_OTH_PRM() {
        for (int i=0;i<16;i++) AVA_CCY_ARRY[i] = new TDCPIOD_AVA_CCY_ARRY();
        for (int i=0;i<25;i++) CCY_INF[i] = new TDCPIOD_CCY_INF();
    }
}
