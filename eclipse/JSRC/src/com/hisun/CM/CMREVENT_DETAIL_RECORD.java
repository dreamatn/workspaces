package com.hisun.CM;

public class CMREVENT_DETAIL_RECORD {
    public String REQ_SYS_ID = " ";
    public int REQ_SYS_DATE = 0;
    public String REQ_SYS_JRN = " ";
    public int REC_SEQ = 0;
    public String AC_NO = " ";
    public String PRDMO_CD = " ";
    public String PRDCD_OLD = " ";
    public String PRDCD_NEW = " ";
    public String CATG_OLD = " ";
    public String CATG_NEW = " ";
    public String EVENT_CD = " ";
    public int VAL_DT = 0;
    public int CLR_DT = 0;
    public String CCY = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public int BR_3 = 0;
    public int BR_4 = 0;
    public int BR_5 = 0;
    public String CI_NO = " ";
    public char RED_FLG = ' ';
    public String RVS_NO = " ";
    public String DESC = " ";
    public String REMARK = " ";
    public CMREVENT_AMT_ARRAY[] AMT_ARRAY = new CMREVENT_AMT_ARRAY[25];
    public String FIELD1 = " ";
    public CMREVENT_BSP_INFO BSP_INFO = new CMREVENT_BSP_INFO();
    public String FIELD2 = " ";
    public String FIELD3 = " ";
    public String FIELD4 = " ";
    public String FIELD5 = " ";
    public char PROC_STS = ' ';
    public String RET_CODE = " ";
    public String RET_MSG = " ";
    public int HOST_DATE = 0;
    public long HOST_JRN = 0;
    public CMREVENT_DETAIL_RECORD() {
        for (int i=0;i<25;i++) AMT_ARRAY[i] = new CMREVENT_AMT_ARRAY();
    }
}
