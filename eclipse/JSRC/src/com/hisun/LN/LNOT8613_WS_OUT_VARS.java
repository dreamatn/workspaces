package com.hisun.LN;

public class LNOT8613_WS_OUT_VARS {
    String WS_CTA_NO = " ";
    int WS_TR_VALDT = 0;
    double WS_TOT_AMT = 0;
    double WS_TOT_PRIN = 0;
    double WS_TOT_INT = 0;
    double WS_TOT_PLC = 0;
    double WS_WAV_PLC = 0;
    double WS_TOT_ILC = 0;
    double WS_WAV_ILC = 0;
    String WS_APT_REF = " ";
    double WS_RDI_AMT = 0;
    double WS_HRG_AMT = 0;
    double WS_TAX_AMT = 0;
    char WS_AC_FLG = ' ';
    String WS_SETL_MTH = " ";
    String WS_REC_AC = " ";
    LNOT8613_WS_PARS_INFO[] WS_PARS_INFO = new LNOT8613_WS_PARS_INFO[10];
    public LNOT8613_WS_OUT_VARS() {
        for (int i=0;i<10;i++) WS_PARS_INFO[i] = new LNOT8613_WS_PARS_INFO();
    }
}
