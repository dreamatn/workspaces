package com.hisun.IB;

public class IBB0005_AWA_0005 {
    public char FCN = ' ';
    public short FCN_NO = 0;
    public String FCN_C = " ";
    public short FCN_C_NO = 0;
    public String CIFNO = " ";
    public short CIFNO_NO = 0;
    public String ACNO = " ";
    public short ACNO_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String LOCCCY = " ";
    public short LOCCCY_NO = 0;
    public String CUSTNAME = " ";
    public short CUSTNAME_NO = 0;
    public String SCMCODE = " ";
    public short SCMCODE_NO = 0;
    public String SCMDESC = " ";
    public short SCMDESC_NO = 0;
    public String FEETYPES = " ";
    public short FEETYPES_NO = 0;
    public String FEEDESCS = " ";
    public short FEEDESCS_NO = 0;
    public IBB0005_FEEINFO[] FEEINFO = new IBB0005_FEEINFO[30];
    public String APCODES = " ";
    public short APCODES_NO = 0;
    public char INQTYPE = ' ';
    public short INQTYPE_NO = 0;
    public int FROMDATE = 0;
    public short FROMDATE_NO = 0;
    public int TODATE = 0;
    public short TODATE_NO = 0;
    public char INQUIRY = ' ';
    public short INQUIRY_NO = 0;
    public char SORTBY = ' ';
    public short SORTBY_NO = 0;
    public String INTCODE = " ";
    public short INTCODE_NO = 0;
    public String INTCOIR = " ";
    public short INTCOIR_NO = 0;
    public double SPREAD = 0;
    public short SPREAD_NO = 0;
    public double RECVINT = 0;
    public short RECVINT_NO = 0;
    public double NRCVINT = 0;
    public short NRCVINT_NO = 0;
    public double PAYINT = 0;
    public short PAYINT_NO = 0;
    public double RADJAMT = 0;
    public short RADJAMT_NO = 0;
    public double NRADJAMT = 0;
    public short NRADJAMT_NO = 0;
    public double PADJAMT = 0;
    public short PADJAMT_NO = 0;
    public int VALDATEP = 0;
    public short VALDATEP_NO = 0;
    public int VALDATER = 0;
    public short VALDATER_NO = 0;
    public int VALDATNR = 0;
    public short VALDATNR_NO = 0;
    public String NARTIVE = " ";
    public short NARTIVE_NO = 0;
    public String SHDWAC = " ";
    public short SHDWAC_NO = 0;
    public String BANKNME1 = " ";
    public short BANKNME1_NO = 0;
    public String BANKNME2 = " ";
    public short BANKNME2_NO = 0;
    public int MONTH = 0;
    public short MONTH_NO = 0;
    public char CALEXP = ' ';
    public short CALEXP_NO = 0;
    public IBB0005_TRXINFO[] TRXINFO = new IBB0005_TRXINFO[3];
    public IBB0005_TOTINFO[] TOTINFO = new IBB0005_TOTINFO[3];
    public double AVAILBAL = 0;
    public short AVAILBAL_NO = 0;
    public double LEDGBAL = 0;
    public short LEDGBAL_NO = 0;
    public double ODLMT = 0;
    public short ODLMT_NO = 0;
    public double INTRALMT = 0;
    public short INTRALMT_NO = 0;
    public double HOLDBAL = 0;
    public short HOLDBAL_NO = 0;
    public int LASTDATE = 0;
    public short LASTDATE_NO = 0;
    public double MABALDR = 0;
    public short MABALDR_NO = 0;
    public double MABALCR = 0;
    public short MABALCR_NO = 0;
    public double MHBALDR = 0;
    public short MHBALDR_NO = 0;
    public double MHBALCR = 0;
    public short MHBALCR_NO = 0;
    public double YABALDR = 0;
    public short YABALDR_NO = 0;
    public double YABALCR = 0;
    public short YABALCR_NO = 0;
    public double YHBALDR = 0;
    public short YHBALDR_NO = 0;
    public double YHBALCR = 0;
    public short YHBALCR_NO = 0;
    public double OPENBAL = 0;
    public short OPENBAL_NO = 0;
    public double CLOSBAL = 0;
    public short CLOSBAL_NO = 0;
    public double GROSSDR = 0;
    public short GROSSDR_NO = 0;
    public double GROSSCR = 0;
    public short GROSSCR_NO = 0;
    public short RECCNT = 0;
    public short RECCNT_NO = 0;
    public String BANKNME3 = " ";
    public short BANKNME3_NO = 0;
    public String BANKNME4 = " ";
    public short BANKNME4_NO = 0;
    public String PRDCODE = " ";
    public short PRDCODE_NO = 0;
    public int FINPDATE = 0;
    public short FINPDATE_NO = 0;
    public int TINPDATE = 0;
    public short TINPDATE_NO = 0;
    public String NCODE = " ";
    public short NCODE_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public IBB0005_AWA_0005() {
        for (int i=0;i<30;i++) FEEINFO[i] = new IBB0005_FEEINFO();
        for (int i=0;i<3;i++) TRXINFO[i] = new IBB0005_TRXINFO();
        for (int i=0;i<3;i++) TOTINFO[i] = new IBB0005_TOTINFO();
    }
}