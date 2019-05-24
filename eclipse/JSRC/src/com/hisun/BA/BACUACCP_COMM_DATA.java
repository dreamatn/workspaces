package com.hisun.BA;

public class BACUACCP_COMM_DATA {
    public String CNTR_NO = " ";
    public short ACCT_CNT = 0;
    public String BILL_NO = " ";
    public char BILL_TYP = ' ';
    public String PROD_CD = " ";
    public String PRODMO = " ";
    public int DRW_DT = 0;
    public int ACPT_BK = 0;
    public String ACPT_BK_CD = " ";
    public int ACPT_DT = 0;
    public String CD_XY_NO = " ";
    public int BILL_MAT_DT = 0;
    public String BILL_CUR = " ";
    public double BILL_AMT = 0;
    public String DRWR_AC = " ";
    public String CPR_NM = " ";
    public String CI_NO = " ";
    public String PYE_AC = " ";
    public String PYE_NM = " ";
    public String PYE_BK_CD = " ";
    public String PYE_BK_NM = " ";
    public String LMT_SYS = " ";
    public int PRT_BR = 0;
    public String GDA_LOW_CUR = " ";
    public double GDA_LOW_AMT = 0;
    public String BZ_REF_NO = " ";
    public char DBT_SEQ = ' ';
    public short FEE_CNT = 0;
    public BACUACCP_FEE_ARRAY[] FEE_ARRAY = new BACUACCP_FEE_ARRAY[5];
    public char FEE_MODE = ' ';
    public String FEE_FRZ_NO = " ";
    public double OVD_RAT = 0;
    public char TRA_FLG = ' ';
    public String TRA_AC = " ";
    public String FEE_BSNO = " ";
    public BACUACCP_COMM_DATA() {
        for (int i=0;i<5;i++) FEE_ARRAY[i] = new BACUACCP_FEE_ARRAY();
    }
}
