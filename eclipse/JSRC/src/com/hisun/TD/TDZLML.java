package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPORUP;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZLML {
    boolean pgmRtn = false;
    String K_INQ_FMT = "TD500";
    String K_BRW_FMT = "TD502";
    int K_MAX_ROW = 20;
    int K_HEAD_BR = 043999;
    String WS_MSGID = " ";
    int WS_I = 0;
    int WS_BAL_CNT = 0;
    double WS_LMT_TOT_BAL = 0;
    short WS_LM_CNT = 0;
    short WS_TMP_LVL = 0;
    String WS_TMP_POINT = " ";
    int WS_TMP_BR = 0;
    double WS_BAL_TI = 0;
    char WS_SHAR_FLG = ' ';
    short WS_LM_POINT_C = 0;
    short WS_BP_T = 0;
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    char WS_OTHE_LMT_FLG = ' ';
    char WS_TML_FU = ' ';
    char WS_X_FLG = ' ';
    char WS_ZHIHANG_FLG = ' ';
    char WS_TML_F_FLG = ' ';
    int WS_BP_BR1 = 0;
    int WS_BP_BR2 = 0;
    int WS_BP_BR3 = 0;
    int WS_BP_BR4 = 0;
    int WS_BP_BR5 = 0;
    TDZLML_WS_BP_BR_N[] WS_BP_BR_N = new TDZLML_WS_BP_BR_N[5];
    short WS_ST = 0;
    short WS_EN = 0;
    String WS_PROD_CD = " ";
    char WS_END_FLG = ' ';
    TDZLML_WS_CATY_CURRE[] WS_CATY_CURRE = new TDZLML_WS_CATY_CURRE[10];
    String WS_SMK = " ";
    char WS_C_FUNC = ' ';
    String WS_C_PROD_CD = " ";
    TDZLML_WS_C_CATY_CURRE[] WS_C_CATY_CURRE = new TDZLML_WS_C_CATY_CURRE[21];
    String WS_C_SMK = " ";
    char WS_C_END_FLG = ' ';
    short WS_TIME = 0;
    short WS_TIME2 = 0;
    String K_OUTPUT_FMT1 = "TD576";
    String K_OUTPUT_FMT2 = "TD578";
    String WS_PROD_TYP = " ";
    String WS_DT_IN_TI = " ";
    char WS_SHAR_FLG_TI = ' ';
    char WS_LMT_FLG = ' ';
    char WS_HEADBR_FLG = ' ';
    char WS_SAMELVL_FLG = ' ';
    char WS_SAME_FLG = ' ';
    char WS_CHNL_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDRLMT TDRLMT = new TDRLMT();
    TDROTHE TDROTHE = new TDROTHE();
    TDCPIOD TDCPIOD = new TDCPIOD();
    BPCPORUP BPCPORUP = new BPCPORUP();
    TDCTLML TDCTLML = new TDCTLML();
    TDCPROD TDCPROD = new TDCPROD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCOLML TDCOLML = new TDCOLML();
    TDCLMBP TDCLMBP = new TDCLMBP();
    int WS_HEADBR = 0;
    int WS_LMT_CNT = 0;
    int WS_STR_ROW = 0;
    SCCGWA SCCGWA;
    TDCLML TDCLML;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public TDZLML() {
        for (int i=0;i<5;i++) WS_BP_BR_N[i] = new TDZLML_WS_BP_BR_N();
        for (int i=0;i<10;i++) WS_CATY_CURRE[i] = new TDZLML_WS_CATY_CURRE();
        for (int i=0;i<21;i++) WS_C_CATY_CURRE[i] = new TDZLML_WS_C_CATY_CURRE();
    }
    public void MP(SCCGWA SCCGWA, TDCLML TDCLML) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCLML = TDCLML;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZLML return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "JFSTART");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDCOLML);
        WS_TIME = 0;
        WS_TIME2 = 0;
        WS_PROD_TYP = " ";
        WS_DT_IN_TI = " ";
        WS_SHAR_FLG_TI = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCLML.FUNC);
        CEP.TRC(SCCGWA, TDCLML.PROD_CD);
        CEP.TRC(SCCGWA, TDCLML.SMK);
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCLML.PROD_CD;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && TDCLML.FUNC == 'B') {
            TDCLML.FUNC = 'Q';
        }
        if (TDCLML.FUNC == 'I') {
            B020_I_FUNC();
            if (pgmRtn) return;
            B200_OUTPUT_PROC();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'A') {
            B070_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'D') {
            B090_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'M') {
            B110_FUNC_MODIFY();
            if (pgmRtn) return;
            B200_OUTPUT_PROC();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'B') {
            B400_BAL_MODIFY();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'Q') {
            B400_BAL_MODIFY();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'J') {
            B500_JUDGE_MODIFY();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'T') {
            B600_BAL_TMLT();
            if (pgmRtn) return;
        } else if (TDCLML.FUNC == 'E') {
            B700_I_BRANK();
            if (pgmRtn) return;
            B200_OUTPUT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_I_FUNC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        if (TDCLML.ACTI_NO.trim().length() > 0) {
            TDROTHE.KEY.ACTI_NO = TDCLML.ACTI_NO;
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRLMT);
            TDRLMT.KEY.PROD_CD = TDROTHE.PROD_CD;
            TDRLMT.KEY.ACTI_NO = TDROTHE.KEY.ACTI_NO;
            if (TDCLML.SMK.trim().length() > 0) {
                if (TDCLML.SMK == null) TDCLML.SMK = "";
                JIBS_tmp_int = TDCLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCLML.SMK += " ";
                if (TDCLML.SMK.substring(21 - 1, 21 + 9 - 1).trim().length() == 0) TDRLMT.KEY.BR = 0;
                else TDRLMT.KEY.BR = Integer.parseInt(TDCLML.SMK.substring(21 - 1, 21 + 9 - 1));
            } else {
                TDRLMT.KEY.BR = K_HEAD_BR;
            }
            if (TDCLML.SMK.trim().length() > 0) {
                if (TDCLML.SMK == null) TDCLML.SMK = "";
                JIBS_tmp_int = TDCLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCLML.SMK += " ";
                TDRLMT.KEY.CHNL_NO = TDCLML.SMK.substring(30 - 1, 30 + 5 - 1);
            } else {
                TDRLMT.KEY.CHNL_NO = "" + 0X00;
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO = "0" + TDRLMT.KEY.CHNL_NO;
            }
            T000_START_TDTLMT();
            if (pgmRtn) return;
            T000_RENEXT_TDTLMT();
            if (pgmRtn) return;
            while (WS_TIME <= 10 
                && WS_LMT_FLG != 'N') {
                IBS.init(SCCGWA, BPCPQPRD);
                CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
                BPCPQPRD.PRDT_CODE = TDROTHE.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                IBS.init(SCCGWA, TDCPIOD);
                TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
                CEP.TRC(SCCGWA, WS_TIME);
                CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                TDCPIOD.ACTI_NO = TDROTHE.KEY.ACTI_NO;
                S000_CALL_TDZPROD();
                if (pgmRtn) return;
                B030_I_OUT_MSG();
                if (pgmRtn) return;
                T000_RENEXT_TDTLMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_LMT_FLG);
            }
            if (WS_TIME <= 0) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTLMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_TIME);
            if (WS_TIME == 11) {
                TDCOLML.END_FLG = 'N';
                WS_C_END_FLG = 'N';
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                if (TDRLMT.KEY.ACTI_NO == null) TDRLMT.KEY.ACTI_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.ACTI_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRLMT.KEY.ACTI_NO += " ";
                TDCOLML.SMK = TDRLMT.KEY.ACTI_NO + TDCOLML.SMK.substring(20);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(0, 20));
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                JIBS_tmp_str[0] = "" + TDRLMT.KEY.BR;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDCOLML.SMK = TDCOLML.SMK.substring(0, 21 - 1) + JIBS_tmp_str[0] + TDCOLML.SMK.substring(21 + 9 - 1);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(21 - 1, 21 + 9 - 1));
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                TDCOLML.SMK = TDCOLML.SMK.substring(0, 30 - 1) + TDRLMT.KEY.CHNL_NO + TDCOLML.SMK.substring(30 + 5 - 1);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(30 - 1, 30 + 5 - 1));
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                if (TDRLMT.KEY.ACTI_NO == null) TDRLMT.KEY.ACTI_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.ACTI_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRLMT.KEY.ACTI_NO += " ";
                WS_C_SMK = TDRLMT.KEY.ACTI_NO + WS_C_SMK.substring(20);
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                JIBS_tmp_str[0] = "" + TDRLMT.KEY.BR;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_C_SMK = WS_C_SMK.substring(0, 21 - 1) + JIBS_tmp_str[0] + WS_C_SMK.substring(21 + 9 - 1);
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                WS_C_SMK = WS_C_SMK.substring(0, 30 - 1) + TDRLMT.KEY.CHNL_NO + WS_C_SMK.substring(30 + 5 - 1);
            }
            if (WS_TIME < 11) {
                TDCOLML.END_FLG = 'Y';
                WS_C_END_FLG = 'Y';
            }
        } else {
            CEP.TRC(SCCGWA, TDCLML.ACTI_FLG);
            TDROTHE.ACTI_FLG = TDCLML.ACTI_FLG;
            if (TDCLML.SDT != 0) {
                TDROTHE.STR_DATE = TDCLML.SDT;
            } else {
                TDROTHE.STR_DATE = 10101;
            }
            if (TDCLML.DDT != 0) {
                TDROTHE.END_DATE = TDCLML.DDT;
            } else {
                TDROTHE.END_DATE = 99991231;
            }
            if (TDCLML.SMK.trim().length() > 0) {
                if (TDCLML.SMK == null) TDCLML.SMK = "";
                JIBS_tmp_int = TDCLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCLML.SMK += " ";
                TDROTHE.KEY.ACTI_NO = TDCLML.SMK.substring(0, 20);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(0, 20));
                if (TDCLML.SMK == null) TDCLML.SMK = "";
                JIBS_tmp_int = TDCLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCLML.SMK += " ";
                if (TDCLML.SMK.substring(21 - 1, 21 + 9 - 1).trim().length() == 0) TDRLMT.KEY.BR = 0;
                else TDRLMT.KEY.BR = Integer.parseInt(TDCLML.SMK.substring(21 - 1, 21 + 9 - 1));
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                if (TDCLML.SMK == null) TDCLML.SMK = "";
                JIBS_tmp_int = TDCLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCLML.SMK += " ";
                TDRLMT.KEY.CHNL_NO = TDCLML.SMK.substring(30 - 1, 30 + 5 - 1);
            } else {
                TDROTHE.KEY.ACTI_NO = "" + 0X00;
                JIBS_tmp_int = TDROTHE.KEY.ACTI_NO.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) TDROTHE.KEY.ACTI_NO = "0" + TDROTHE.KEY.ACTI_NO;
                CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                TDRLMT.KEY.BR = K_HEAD_BR;
                TDRLMT.KEY.CHNL_NO = "" + 0X00;
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO = "0" + TDRLMT.KEY.CHNL_NO;
            }
            CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
            CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
            CEP.TRC(SCCGWA, TDROTHE.END_DATE);
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            CEP.TRC(SCCGWA, TDROTHE.ACTI_FLG);
            WS_OTHE_LMT_FLG = 'C';
            T000_START_TDTOTHE();
            if (pgmRtn) return;
            T000_RENEXT_TDTOTHE();
            if (pgmRtn) return;
            WS_TIME = 0;
            CEP.TRC(SCCGWA, WS_OTHE_LMT_FLG);
            TDRLMT.KEY.PROD_CD = TDROTHE.PROD_CD;
            while (WS_TIME <= 10 
                && WS_OTHE_LMT_FLG != 'N' 
                && WS_TIME2 <= 20) {
                TDRLMT.KEY.ACTI_NO = TDROTHE.KEY.ACTI_NO;
                if (WS_TIME > 0) {
                    CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                    TDRLMT.KEY.BR = K_HEAD_BR;
                    TDRLMT.KEY.CHNL_NO = "" + 0X00;
                    JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO = "0" + TDRLMT.KEY.CHNL_NO;
                }
                T000_START_TDTLMT();
                if (pgmRtn) return;
                if (WS_OTHE_LMT_FLG == 'Y') {
                    T000_RENEXT_TDTLMT();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCPQPRD);
                    CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
                    BPCPQPRD.PRDT_CODE = TDROTHE.PROD_CD;
                    S000_CALL_BPZPQPRD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                    IBS.init(SCCGWA, TDCPIOD);
                    TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
                    TDCPIOD.C_PROD_CD = TDROTHE.PROD_CD;
                    CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                    TDCPIOD.ACTI_NO = TDROTHE.KEY.ACTI_NO;
                    while (WS_TIME <= 10 
                        && WS_LMT_FLG != 'N') {
                        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                        CEP.TRC(SCCGWA, WS_TIME);
                        S000_CALL_TDZPROD();
                        if (pgmRtn) return;
                        B030_I_OUT_MSG();
                        if (pgmRtn) return;
                        T000_RENEXT_TDTLMT();
                        if (pgmRtn) return;
                    }
                    T000_RENEXT_TDTOTHE();
                    if (pgmRtn) return;
                    if (!TDROTHE.PROD_CD.equalsIgnoreCase(TDCLML.PROD_CD)) {
                        WS_TIME = (short) (WS_TIME - 1);
                    }
                }
                if (WS_OTHE_LMT_FLG == 'C') {
                    T000_RENEXT_TDTOTHE();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                WS_TIME2 += 1;
            }
            CEP.TRC(SCCGWA, WS_TIME);
            if (WS_TIME > 0) {
                T000_ENDBR_TDTLMT();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTOTHE();
            if (pgmRtn) return;
            if (WS_TIME == 11) {
                TDCOLML.END_FLG = 'N';
                WS_C_END_FLG = 'N';
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                if (TDRLMT.KEY.ACTI_NO == null) TDRLMT.KEY.ACTI_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.ACTI_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRLMT.KEY.ACTI_NO += " ";
                TDCOLML.SMK = TDRLMT.KEY.ACTI_NO + TDCOLML.SMK.substring(20);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(0, 20));
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                JIBS_tmp_str[0] = "" + TDRLMT.KEY.BR;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDCOLML.SMK = TDCOLML.SMK.substring(0, 21 - 1) + JIBS_tmp_str[0] + TDCOLML.SMK.substring(21 + 9 - 1);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(21 - 1, 21 + 9 - 1));
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                TDCOLML.SMK = TDCOLML.SMK.substring(0, 30 - 1) + TDRLMT.KEY.CHNL_NO + TDCOLML.SMK.substring(30 + 5 - 1);
                if (TDCOLML.SMK == null) TDCOLML.SMK = "";
                JIBS_tmp_int = TDCOLML.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) TDCOLML.SMK += " ";
                CEP.TRC(SCCGWA, TDCOLML.SMK.substring(30 - 1, 30 + 5 - 1));
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                if (TDRLMT.KEY.ACTI_NO == null) TDRLMT.KEY.ACTI_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.ACTI_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRLMT.KEY.ACTI_NO += " ";
                WS_C_SMK = TDRLMT.KEY.ACTI_NO + WS_C_SMK.substring(20);
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                JIBS_tmp_str[0] = "" + TDRLMT.KEY.BR;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_C_SMK = WS_C_SMK.substring(0, 21 - 1) + JIBS_tmp_str[0] + WS_C_SMK.substring(21 + 9 - 1);
                if (WS_C_SMK == null) WS_C_SMK = "";
                JIBS_tmp_int = WS_C_SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_C_SMK += " ";
                if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                WS_C_SMK = WS_C_SMK.substring(0, 30 - 1) + TDRLMT.KEY.CHNL_NO + WS_C_SMK.substring(30 + 5 - 1);
            }
            if (WS_TIME < 11) {
                TDCOLML.END_FLG = 'Y';
                WS_C_END_FLG = 'Y';
            }
        }
    }
    public void B030_I_OUT_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TIME);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.ACTI_DESC);
        if (SCCGWA.COMM_AREA.AC_DATE < TDROTHE.STR_DATE) {
            TDCOLML.CATY_CURRE[WS_TIME-1].XS_TYP = '1';
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDROTHE.STR_DATE 
            && SCCGWA.COMM_AREA.AC_DATE < TDROTHE.END_DATE) {
            TDCOLML.CATY_CURRE[WS_TIME-1].XS_TYP = '2';
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDROTHE.END_DATE) {
            TDCOLML.CATY_CURRE[WS_TIME-1].XS_TYP = '3';
        }
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        CEP.TRC(SCCGWA, TDROTHE.TERM);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.MIN_BAL);
        CEP.TRC(SCCGWA, TDRLMT.TOT_BAL);
        CEP.TRC(SCCGWA, TDRLMT.AGN_BAL);
        CEP.TRC(SCCGWA, TDRLMT.AGN_CURR_BAL);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT2);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT1);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT3);
        if (TDROTHE.TRAN_TYP == '0') {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "1";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "1" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(1);
        } else {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "0";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "0" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(1);
        }
        if (TDROTHE.REDE_TYP == '0') {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "1";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 2 - 1) + "1" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(2 + 1 - 1);
        } else {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "0";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 2 - 1) + "0" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(2 + 1 - 1);
        }
        if (TDROTHE.PLED_TYP == '0') {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "1";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 4 - 1) + "1" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(4 + 1 - 1);
        } else {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "0";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 4 - 1) + "0" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(4 + 1 - 1);
        }
        if (TDCPIOD.EXP_PRM.OPTM_FLG == '0') {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "1";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 3 - 1) + "1" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(3 + 1 - 1);
        } else {
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "0";
            if (TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP == null) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = "";
            JIBS_tmp_int = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP += " ";
            TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP = TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(0, 3 - 1) + "0" + TDCOLML.CATY_CURRE[WS_TIME-1].LZ_TYP.substring(3 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.PART_NUM);
        CEP.TRC(SCCGWA, TDROTHE.INT_PERD);
        CEP.TRC(SCCGWA, TDROTHE.CHNL_NO);
        TDCOLML.PROD_CD = TDROTHE.KEY.ACTI_NO;
        TDCOLML.CATY_CURRE[WS_TIME-1].ACTI_DESC = TDROTHE.ACTI_DESC;
        TDCOLML.CATY_CURRE[WS_TIME-1].FX_SDT = TDROTHE.STR_DATE;
        TDCOLML.CATY_CURRE[WS_TIME-1].FX_DDT = TDROTHE.END_DATE;
        TDCOLML.CATY_CURRE[WS_TIME-1].PROD_TERM = TDROTHE.TERM;
        TDCOLML.CATY_CURRE[WS_TIME-1].ACTI_NO = TDROTHE.KEY.ACTI_NO;
        TDCOLML.CATY_CURRE[WS_TIME-1].QC_BAL = TDROTHE.MIN_BAL;
        TDCOLML.CATY_CURRE[WS_TIME-1].BR = TDRLMT.KEY.BR;
        TDCOLML.CATY_CURRE[WS_TIME-1].C_CHNL_NO = TDRLMT.KEY.CHNL_NO;
        TDCOLML.CATY_CURRE[WS_TIME-1].TOT_BAL = TDRLMT.TOT_BAL;
        TDCOLML.CATY_CURRE[WS_TIME-1].YGM_BAL = TDRLMT.AGN_USE_BAL + TDRLMT.UNAGN_USE_BAL;
        TDCOLML.CATY_CURRE[WS_TIME-1].LMT_BAL = TDRLMT.AGN_CURR_BAL + TDRLMT.UNAGN_CURR_BAL;
        if (TDCLML.FUNC == 'E') {
            TDCOLML.CATY_CURRE[WS_TIME-1].YGM_BAL = TDRLMT.UNAGN_USE_BAL;
            TDCOLML.CATY_CURRE[WS_TIME-1].LMT_BAL = TDRLMT.UNAGN_CURR_BAL;
            if (TDRLMT.SHAR_FLG == '1') {
                T000_BP_RENEXT_TDTLMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
                CEP.TRC(SCCGWA, WS_LMT_FLG);
                while (WS_LMT_FLG != 'Y' 
                    && WS_X_FLG != 'N' 
                    && (TDRLMT.KEY.BR != K_HEAD_BR 
                    || TDRLMT.LM_CNT != 1)) {
                    T000_BP_RENEXT_TDTLMT();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
                CEP.TRC(SCCGWA, WS_LMT_FLG);
                if (WS_X_FLG != 'N') {
                    TDCOLML.CATY_CURRE[WS_TIME-1].YGM_BAL = TDRLMT.UNAGN_USE_BAL;
                    TDCOLML.CATY_CURRE[WS_TIME-1].LMT_BAL = TDRLMT.UNAGN_CURR_BAL;
                } else {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, TDCOLML.CATY_CURRE[WS_TIME-1].YGM_BAL);
        CEP.TRC(SCCGWA, TDCOLML.CATY_CURRE[WS_TIME-1].LMT_BAL);
        TDCOLML.CATY_CURRE[WS_TIME-1].GM_BAL = TDRLMT.SALE_AMT2;
        TDCOLML.CATY_CURRE[WS_TIME-1].WY_BAL = TDRLMT.SALE_AMT1;
        TDCOLML.CATY_CURRE[WS_TIME-1].SJ_BAL = TDRLMT.SALE_AMT3;
        TDCOLML.CATY_CURRE[WS_TIME-1].PART_NUM = TDCPIOD.TXN_PRM.PART_NUM;
        TDCOLML.CATY_CURRE[WS_TIME-1].FX_TYP = TDROTHE.INT_PERD;
        TDCOLML.CATY_CURRE[WS_TIME-1].CHNL_NO = TDROTHE.CHNL_NO;
        WS_C_PROD_CD = TDROTHE.PROD_CD;
        WS_C_FUNC = TDCLML.FUNC;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_ACTI_NO = TDROTHE.KEY.ACTI_NO;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_BR = TDRLMT.KEY.BR;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_SHAR_FLG = TDRLMT.SHAR_FLG;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_TOT_BAL = TDRLMT.TOT_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_AGN_BAL = TDRLMT.AGN_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_AUSE_BAL = TDRLMT.AGN_USE_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_ACUR_BAL = TDRLMT.AGN_CURR_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_UAG_BAL = TDRLMT.UNAGN_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_UUSE_BAL = TDRLMT.UNAGN_USE_BAL;
        WS_C_CATY_CURRE[WS_TIME-1].WS_C_UCUR_BAL = TDRLMT.UNAGN_CURR_BAL;
    }
    public void B070_FUNC_WRITE() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCLML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        B030_01_GET_TYP_PROC();
        if (pgmRtn) return;
        if ((TDCPIOD.EXP_PRM.LMT_FLG == '0' 
            || TDCPIOD.EXP_PRM.LMT_FLG == 'N') 
            && TDROTHE.ACTI_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LMT_FLG_LIVE_ERR);
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037") 
            && TDROTHE.SDT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.M_PROD_MOD_SDT_ERR);
        }
        if (TDROTHE.END_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRD_STOP_SOLD);
        }
        WS_LM_CNT = 0;
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            if (TDCLML.LM_POINT == null) TDCLML.LM_POINT = "";
            JIBS_tmp_int = TDCLML.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCLML.LM_POINT += " ";
            if (TDCLML.LM_POINT.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                WS_LM_CNT = (short) (WS_I);
            }
        }
        if (TDCLML.LM_POINT == null) TDCLML.LM_POINT = "";
        JIBS_tmp_int = TDCLML.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCLML.LM_POINT += " ";
        if (TDCLML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_LM_CNT += 1;
            WS_CHNL_FLG = 'Y';
        } else {
            WS_CHNL_FLG = 'N';
        }
        CEP.TRC(SCCGWA, TDCLML.LM_POINT);
        CEP.TRC(SCCGWA, WS_LM_CNT);
        TDRLMT.LM_CNT = WS_LM_CNT;
        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
        T000_ACTI_READ_TDTLMT();
        if (pgmRtn) return;
        if (TDCLML.LM_POINT == null) TDCLML.LM_POINT = "";
        JIBS_tmp_int = TDCLML.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCLML.LM_POINT += " ";
        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
        JIBS_tmp_int = TDRLMT.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
        if (WS_HEADBR_FLG == 'Y' 
            && !TDCLML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1))) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_CNT_ERR, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_FENHANG_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "JFTEST11030");
        R000_GET_HEADBR_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HEADBR_FLG);
        CEP.TRC(SCCGWA, TDCLML.SHAR_FLG);
        if (WS_HEADBR_FLG == 'Y') {
            CEP.TRC(SCCGWA, "JFTEST11031");
            if (TDCLML.BR != K_HEAD_BR 
                && WS_LM_CNT == 1) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_EXIST, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "JFTEST11032");
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.BR = K_HEAD_BR;
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.LM_CNT = 2;
                T000_READ_TDTLMT_BYBR2();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "JFTEST11033");
                if (TDCLML.LM_POINT == null) TDCLML.LM_POINT = "";
                JIBS_tmp_int = TDCLML.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDCLML.LM_POINT += " ";
                if (WS_HEADBR_FLG == 'Y' 
                    && (!TDCLML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || TDCLML.CHNL_NO.trim().length() == 0)) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_CNT_ERR);
                }
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.BR = K_HEAD_BR;
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.LM_CNT = 2;
                T000_READ_TDTLMT_BYBR3();
                if (pgmRtn) return;
                if (TDCLML.LM_POINT == null) TDCLML.LM_POINT = "";
                JIBS_tmp_int = TDCLML.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDCLML.LM_POINT += " ";
                if (WS_HEADBR_FLG == 'Y' 
                    && (TDCLML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || TDCLML.CHNL_NO.trim().length() > 0)) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_CNT_ERR);
                }
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = TDCLML.BR;
                CEP.TRC(SCCGWA, "JFTEST11034");
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                WS_TMP_LVL = (short) (WS_LM_CNT - 1);
                TDRLMT.LM_CNT = WS_TMP_LVL;
                if (TDRLMT.KEY.BR == K_HEAD_BR 
                    && TDRLMT.LM_CNT == 2) {
                    if (TDCLML.CHNL_NO == null) TDCLML.CHNL_NO = "";
                    JIBS_tmp_int = TDCLML.CHNL_NO.length();
                    for (int i=0;i<5-JIBS_tmp_int;i++) TDCLML.CHNL_NO += " ";
                    if (!TDCLML.CHNL_NO.substring(0, 3).equalsIgnoreCase("101")) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                    }
                    TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
                    T000_READ_TDTLMT_BYBR1();
                    if (pgmRtn) return;
                } else {
                    T000_READ_TDTLMT_BYBR();
                    if (pgmRtn) return;
                    if (WS_HEADBR_FLG == 'N' 
                        && WS_ZHIHANG_FLG == 'Y') {
                        IBS.init(SCCGWA, BPCPORUP);
                        BPCPORUP.DATA_INFO.BR = TDRLMT.KEY.BR;
                        S000_CALL_BPZPORUP();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, TDRLMT);
                        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                        TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                        WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
                        TDRLMT.LM_CNT = WS_TMP_LVL;
                        if (TDRLMT.KEY.BR == K_HEAD_BR 
                            && TDRLMT.LM_CNT == 2) {
                            if (TDCLML.CHNL_NO == null) TDCLML.CHNL_NO = "";
                            JIBS_tmp_int = TDCLML.CHNL_NO.length();
                            for (int i=0;i<5-JIBS_tmp_int;i++) TDCLML.CHNL_NO += " ";
                            if (!TDCLML.CHNL_NO.substring(0, 3).equalsIgnoreCase("101")) {
                                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                            }
                            TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
                            T000_READ_TDTLMT_BYBR1();
                            if (pgmRtn) return;
                        } else {
                            T000_READ_TDTLMT_BYBR();
                            if (pgmRtn) return;
                        }
                    }
                }
                CEP.TRC(SCCGWA, TDRLMT.TOT_BAL);
                CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
                CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.TRC(SCCGWA, TDRLMT.LM_CNT);
                CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
                if (WS_CHNL_FLG == 'N') {
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                        JIBS_tmp_int = TDRLMT.LM_POINT.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                        if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0") 
                            && TDRLMT.SHAR_FLG == '0') {
                            B073_UPDATE_SUPLVL_PROC();
                            if (pgmRtn) return;
                        } else {
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (WS_HEADBR_FLG == 'N') {
                        if (TDCLML.BR != K_HEAD_BR) {
                            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                        }
                        IBS.init(SCCGWA, TDRLMT);
                        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                        TDRLMT.KEY.BR = TDCLML.BR;
                        WS_TMP_LVL = (short) (WS_LM_CNT - 1);
                        TDRLMT.LM_CNT = WS_TMP_LVL;
                        T000_READ_TDTLMT_BYBR();
                        if (pgmRtn) return;
                        if (WS_HEADBR_FLG == 'N') {
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCLML.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        } else {
                            B073_UPDATE_SUPLVL_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                        JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                        for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                        if (TDRLMT.KEY.CHNL_NO.substring(0, 3).equalsIgnoreCase("101")) {
                            if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                            JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                            for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                            if (TDRLMT.KEY.CHNL_NO.substring(4 - 1, 4 + 2 - 1).trim().length() > 0 
                                && !TDRLMT.KEY.CHNL_NO.equalsIgnoreCase(TDCLML.CHNL_NO)) {
                                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                            } else {
                                if (TDCLML.CHNL_NO == null) TDCLML.CHNL_NO = "";
                                JIBS_tmp_int = TDCLML.CHNL_NO.length();
                                for (int i=0;i<5-JIBS_tmp_int;i++) TDCLML.CHNL_NO += " ";
                                if (!TDCLML.CHNL_NO.substring(0, 3).equalsIgnoreCase("101")) {
                                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                                }
                            }
                        } else {
                            if (!TDRLMT.KEY.CHNL_NO.equalsIgnoreCase(TDCLML.CHNL_NO)) {
                                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                            }
                        }
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, TDCLML.SHAR_FLG);
                CEP.TRC(SCCGWA, TDCLML.CHNL_NO);
                CEP.TRC(SCCGWA, TDCLML.BR);
                CEP.TRC(SCCGWA, TDCLML.PROD_CD);
                CEP.TRC(SCCGWA, TDCLML.ACTI_NO);
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.KEY.BR = TDCLML.BR;
                TDRLMT.LM_CNT = WS_LM_CNT;
                if (TDCLML.FUNC == 'A') {
                    T000_CHECK_TDTLMT();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.KEY.BR = TDCLML.BR;
                TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
                TDRLMT.LM_CNT = WS_LM_CNT;
                TDRLMT.LM_POINT = TDCLML.LM_POINT;
                TDRLMT.SHAR_FLG = TDCLML.SHAR_FLG;
                if (TDCLML.SHAR_FLG == '0') {
                    TDRLMT.TOT_BAL = TDCLML.BAL;
                    TDRLMT.UNAGN_BAL = TDCLML.BAL;
                    TDRLMT.UNAGN_CURR_BAL = TDCLML.BAL;
                }
                B071_WRITE_LMT_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "JFTEST11035");
            if (WS_LM_CNT != 1) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (TDROTHE.ACTI_FLG == '1') {
                CEP.TRC(SCCGWA, "JFTEST11036");
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCLML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            if (TDCLML.BR == K_HEAD_BR) {
                if (TDROTHE.ACTI_FLG != '0') {
                    IBS.init(SCCGWA, TDCTLML);
                    TDCTLML.FUNC = 'O';
                    TDCTLML.CCY = TDROTHE.CCY;
                    TDCTLML.PROD_TYP = WS_PROD_TYP;
                    TDCTLML.FR = 1;
                    TDCTLML.AGN_BAL = TDCLML.BAL;
                    WS_DT_IN_TI = "" + SCCGWA.COMM_AREA.AC_DATE;
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI = "0" + WS_DT_IN_TI;
                    if (WS_DT_IN_TI == null) WS_DT_IN_TI = "";
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI += " ";
                    if (WS_DT_IN_TI.substring(0, 4).trim().length() == 0) TDCTLML.DT = 0;
                    else TDCTLML.DT = Short.parseShort(WS_DT_IN_TI.substring(0, 4));
                    S000_CALL_TDZTLML();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.KEY.BR = TDCLML.BR;
                TDRLMT.LM_CNT = WS_LM_CNT;
                if (TDCLML.FUNC == 'A') {
                    T000_CHECK_TDTLMT();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                TDRLMT.KEY.BR = TDCLML.BR;
                TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
                TDRLMT.LM_CNT = WS_LM_CNT;
                TDRLMT.LM_POINT = TDCLML.LM_POINT;
                TDRLMT.SHAR_FLG = '0';
                CEP.TRC(SCCGWA, TDCLML.BAL);
                TDRLMT.TOT_BAL = TDCLML.BAL;
                TDRLMT.UNAGN_BAL = TDCLML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDCLML.BAL;
                B071_WRITE_LMT_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (TDROTHE.ACTI_FLG == '0' 
            && TDROTHE.SUC_FLG != '1') {
            IBS.init(SCCGWA, TDCLMBP);
            TDCLMBP.ACTI_NO = TDCLML.ACTI_NO;
            TDCLMBP.PROD_CD = TDCLML.PROD_CD;
            S000_CALL_TDZLMBP();
            if (pgmRtn) return;
        }
    }
    public void R000_FENHANG_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = TDCLML.BR;
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.TYP);
        if (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("02") 
            || BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("03") 
            || (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("11") 
            && BPCPORUP.DATA_INFO.ATTR == '0')) {
            R000_ZHIHANG_CHACK();
            if (pgmRtn) return;
        }
        if (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("12") 
            || BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("13") 
            || (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("11") 
            && BPCPORUP.DATA_INFO.ATTR != '0')) {
            WS_ZHIHANG_FLG = 'Y';
        }
    }
    public void R000_ZHIHANG_CHACK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
        T000_F_STARTBR_TDTLMT();
        if (pgmRtn) return;
        T000_F_RENEXT_TDTLMT();
        if (pgmRtn) return;
        while (WS_TML_F_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = TDRLMT.KEY.BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR == TDCLML.BR) {
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ZONG_ZHI_ERR);
            }
            T000_F_RENEXT_TDTLMT();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTLMT();
        if (pgmRtn) return;
    }
    public void R000_GET_HEADBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
        TDRLMT.KEY.BR = K_HEAD_BR;
        TDRLMT.LM_CNT = 1;
        T000_READ_TDTLMT_BYBR();
        if (pgmRtn) return;
    }
    public void B071_WRITE_LMT_PROC() throws IOException,SQLException,Exception {
        TDRLMT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRLMT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTLMT();
        if (pgmRtn) return;
    }
    public void B073_UPDATE_SUPLVL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SHAR_FLG_TI);
        if (TDCLML.FUNC == 'A' 
            && TDCLML.SHAR_FLG == '0') {
            if (TDCLML.BAL > TDRLMT.UNAGN_CURR_BAL) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_HAVE_BEEN_OVER, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL - TDCLML.BAL;
            TDRLMT.UNAGN_BAL = TDRLMT.UNAGN_BAL - TDCLML.BAL;
            WS_BAL_TI = TDRLMT.AGN_BAL + TDCLML.BAL;
            if (WS_BAL_TI > TDRLMT.TOT_BAL) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_HAVE_BEEN_OVER, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (TDCLML.FUNC == 'A' 
                && TDCLML.SHAR_FLG == '1') {
                if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                JIBS_tmp_int = TDRLMT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_LM_POINT_C = 0;
                    else WS_LM_POINT_C = Short.parseShort(TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1));
                } else {
                    WS_LM_POINT_C = 0;
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    JIBS_tmp_str[0] = "" + 0;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    TDRLMT.LM_POINT = TDRLMT.LM_POINT.substring(0, 7 - 1) + JIBS_tmp_str[0] + TDRLMT.LM_POINT.substring(7 + 2 - 1);
                }
                WS_LM_POINT_C += 1;
                if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                JIBS_tmp_int = TDRLMT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                JIBS_tmp_str[0] = "" + WS_LM_POINT_C;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDRLMT.LM_POINT = TDRLMT.LM_POINT.substring(0, 7 - 1) + JIBS_tmp_str[0] + TDRLMT.LM_POINT.substring(7 + 2 - 1);
            }
            if (TDCLML.FUNC == 'D') {
                WS_BAL_TI = TDRLMT.AGN_BAL - TDCLML.BAL;
                TDRLMT.AGN_BAL = WS_BAL_TI;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_CURR_BAL - TDCLML.BAL;
                TDRLMT.UNAGN_BAL = TDRLMT.UNAGN_BAL + TDCLML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL + TDCLML.BAL;
                CEP.TRC(SCCGWA, WS_SHAR_FLG);
                CEP.TRC(SCCGWA, TDRLMT.LM_POINT);
                if (WS_SHAR_FLG == '1') {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                        JIBS_tmp_int = TDRLMT.LM_POINT.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                        if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_LM_POINT_C = 0;
                        else WS_LM_POINT_C = Short.parseShort(TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1));
                    } else {
                        WS_LM_POINT_C = 0;
                        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                        JIBS_tmp_int = TDRLMT.LM_POINT.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                        JIBS_tmp_str[0] = "" + 0;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        TDRLMT.LM_POINT = TDRLMT.LM_POINT.substring(0, 7 - 1) + JIBS_tmp_str[0] + TDRLMT.LM_POINT.substring(7 + 2 - 1);
                    }
                    if (WS_LM_POINT_C > 0) {
                        WS_LM_POINT_C = (short) (WS_LM_POINT_C - 1);
                        CEP.TRC(SCCGWA, WS_LM_POINT_C);
                    }
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    JIBS_tmp_str[0] = "" + WS_LM_POINT_C;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    TDRLMT.LM_POINT = TDRLMT.LM_POINT.substring(0, 7 - 1) + JIBS_tmp_str[0] + TDRLMT.LM_POINT.substring(7 + 2 - 1);
                }
            }
            if (TDCLML.FUNC == 'M') {
                if (WS_BAL_TI > TDRLMT.UNAGN_CURR_BAL) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_HAVE_BEEN_OVER);
                }
                WS_BAL_TI = TDRLMT.AGN_BAL + WS_BAL_TI;
                if (WS_BAL_TI > TDRLMT.TOT_BAL) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_HAVE_BEEN_OVER);
                }
                TDRLMT.UNAGN_BAL = TDRLMT.TOT_BAL - WS_BAL_TI;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_BAL - TDRLMT.UNAGN_USE_BAL;
                TDRLMT.AGN_BAL = WS_BAL_TI;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_BAL - TDRLMT.AGN_USE_BAL;
            }
        }
        if (TDCLML.FUNC == 'B') {
            if (WS_SHAR_FLG_TI == '0') {
                TDRLMT.AGN_USE_BAL += TDCLML.BAL;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_CURR_BAL - TDCLML.BAL;
            } else {
                if (TDCLML.BAL > TDRLMT.UNAGN_CURR_BAL) {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_HAVE_BEEN_OVER, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                TDRLMT.UNAGN_USE_BAL += TDCLML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL - TDCLML.BAL;
            }
            if (TDRLMT.LM_CNT == 1) {
                if (TDCLML.CHNL_NO.equalsIgnoreCase("101")) {
                    TDRLMT.SALE_AMT1 = TDRLMT.SALE_AMT1 + TDCLML.BAL;
                }
                if (TDCLML.CHNL_NO.equalsIgnoreCase("10202")) {
                    TDRLMT.SALE_AMT2 = TDRLMT.SALE_AMT2 + TDCLML.BAL;
                }
                if (TDCLML.CHNL_NO.equalsIgnoreCase("10203")) {
                    TDRLMT.SALE_AMT3 = TDRLMT.SALE_AMT3 + TDCLML.BAL;
                }
            }
            WS_BAL_TI = TDRLMT.AGN_BAL;
        }
        if (TDCLML.FUNC == 'Q') {
            CEP.TRC(SCCGWA, WS_SHAR_FLG_TI);
            if (WS_SHAR_FLG_TI == '0') {
                TDRLMT.AGN_USE_BAL = TDRLMT.AGN_USE_BAL - TDCLML.BAL;
                TDRLMT.AGN_CURR_BAL += TDCLML.BAL;
            } else {
                TDRLMT.UNAGN_USE_BAL = TDRLMT.UNAGN_USE_BAL - TDCLML.BAL;
                TDRLMT.UNAGN_CURR_BAL += TDCLML.BAL;
            }
            if (TDRLMT.LM_CNT == 1) {
                if (TDCLML.CHNL_NO.equalsIgnoreCase("101") 
                    && TDRLMT.SALE_AMT1 != 0) {
                    TDRLMT.SALE_AMT1 = TDRLMT.SALE_AMT1 - TDCLML.BAL;
                }
                if (TDCLML.CHNL_NO.equalsIgnoreCase("10202") 
                    && TDRLMT.SALE_AMT2 != 0) {
                    TDRLMT.SALE_AMT2 = TDRLMT.SALE_AMT2 - TDCLML.BAL;
                }
                if (TDCLML.CHNL_NO.equalsIgnoreCase("10203") 
                    && TDRLMT.SALE_AMT3 != 0) {
                    TDRLMT.SALE_AMT3 = TDRLMT.SALE_AMT3 - TDCLML.BAL;
                }
            }
            WS_BAL_TI = TDRLMT.AGN_BAL;
        }
        CEP.TRC(SCCGWA, TDCLML.SHAR_FLG);
        if (TDCLML.SHAR_FLG == '0') {
            TDRLMT.AGN_BAL = WS_BAL_TI;
            if (TDCLML.FUNC == 'A') {
                TDRLMT.AGN_CURR_BAL = WS_BAL_TI;
            }
        }
        TDRLMT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRLMT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTLMT();
        if (pgmRtn) return;
    }
    public void R000_GET_SUPBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = WS_TMP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
    }
    public void B600_BAL_TMLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCLML.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
            WS_PROD_TYP = "01";
        } else {
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                WS_PROD_TYP = "02";
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (TDCLML.BR == K_HEAD_BR) {
            IBS.init(SCCGWA, TDCTLML);
            TDCTLML.FUNC = 'S';
            TDCTLML.CCY = TDROTHE.CCY;
            TDCTLML.PROD_TYP = WS_PROD_TYP;
            TDCTLML.FR = 1;
            TDCTLML.AGN_BAL = TDCLML.BAL;
            WS_DT_IN_TI = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = WS_DT_IN_TI.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI = "0" + WS_DT_IN_TI;
            if (WS_DT_IN_TI == null) WS_DT_IN_TI = "";
            JIBS_tmp_int = WS_DT_IN_TI.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI += " ";
            if (WS_DT_IN_TI.substring(0, 4).trim().length() == 0) TDCTLML.DT = 0;
            else TDCTLML.DT = Short.parseShort(WS_DT_IN_TI.substring(0, 4));
            S000_CALL_TDZTLML();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B090_FUNC_DELETE() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCLML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
        TDRLMT.KEY.BR = TDCLML.BR;
        TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        T000_READ_TDTLMT();
        if (pgmRtn) return;
        if (WS_LMT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRLMT.AGN_BAL);
        if (TDRLMT.AGN_BAL > 0 
            || TDRLMT.UNAGN_USE_BAL > 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_INUSE_FORBID, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
        JIBS_tmp_int = TDRLMT.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
        if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
            if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
            JIBS_tmp_int = TDRLMT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
            if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_LM_POINT_C = 0;
            else WS_LM_POINT_C = Short.parseShort(TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1));
        } else {
            WS_LM_POINT_C = 0;
            if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
            JIBS_tmp_int = TDRLMT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
            JIBS_tmp_str[0] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRLMT.LM_POINT = TDRLMT.LM_POINT.substring(0, 7 - 1) + JIBS_tmp_str[0] + TDRLMT.LM_POINT.substring(7 + 2 - 1);
        }
        CEP.TRC(SCCGWA, TDRLMT.SHAR_FLG);
        CEP.TRC(SCCGWA, WS_LM_POINT_C);
        if (TDRLMT.SHAR_FLG == '0' 
            && WS_LM_POINT_C > 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BANK_TML_NOT_ADD, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_SHAR_FLG = TDRLMT.SHAR_FLG;
        TDCLML.BAL = TDRLMT.TOT_BAL - TDRLMT.AGN_USE_BAL - TDRLMT.UNAGN_USE_BAL;
        TDCLML.LM_CNT = TDRLMT.LM_CNT;
        T000_READUPD_TDTLMT();
        if (pgmRtn) return;
        T000_DELETE_TDTLMT();
        if (pgmRtn) return;
        if (TDRLMT.LM_CNT > 1) {
            if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
            JIBS_tmp_int = TDRLMT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
            if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_CHNL_FLG = 'Y';
            } else {
                WS_CHNL_FLG = 'N';
            }
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = TDCLML.BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            if (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("12") 
                || BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("13") 
                || (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("11") 
                && BPCPORUP.DATA_INFO.ATTR != '0')) {
                WS_ZHIHANG_FLG = 'Y';
            }
            IBS.init(SCCGWA, TDRLMT);
            TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
            TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
            TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
            WS_TMP_LVL = (short) (TDCLML.LM_CNT - 1);
            TDRLMT.LM_CNT = WS_TMP_LVL;
            T000_READ_TDTLMT_BYBR();
            if (pgmRtn) return;
            if (WS_HEADBR_FLG == 'N' 
                && WS_ZHIHANG_FLG == 'Y') {
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = TDRLMT.KEY.BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
                TDRLMT.LM_CNT = WS_TMP_LVL;
                T000_READ_TDTLMT_BYBR();
                if (pgmRtn) return;
            }
            if (WS_CHNL_FLG == 'N') {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.init(SCCGWA, TDRLMT);
                    TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                    TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                    TDRLMT.KEY.BR = TDCLML.BR;
                    WS_TMP_LVL = (short) (TDCLML.LM_CNT - 1);
                    TDRLMT.LM_CNT = WS_TMP_LVL;
                    T000_READ_TDTLMT_BYBR();
                    if (pgmRtn) return;
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    B073_UPDATE_SUPLVL_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (TDROTHE.ACTI_FLG == '1') {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCLML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                if (TDCLML.BR == K_HEAD_BR) {
                    IBS.init(SCCGWA, TDCTLML);
                    TDCTLML.FUNC = 'S';
                    TDCTLML.CCY = TDROTHE.CCY;
                    TDCTLML.PROD_TYP = WS_PROD_TYP;
                    TDCTLML.FR = 1;
                    TDCTLML.AGN_BAL = TDCLML.BAL;
                    WS_DT_IN_TI = "" + SCCGWA.COMM_AREA.AC_DATE;
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI = "0" + WS_DT_IN_TI;
                    if (WS_DT_IN_TI == null) WS_DT_IN_TI = "";
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI += " ";
                    if (WS_DT_IN_TI.substring(0, 4).trim().length() == 0) TDCTLML.DT = 0;
                    else TDCTLML.DT = Short.parseShort(WS_DT_IN_TI.substring(0, 4));
                    S000_CALL_TDZTLML();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDROTHE.ACTI_FLG == '0' 
            && TDROTHE.SUC_FLG != '1') {
            IBS.init(SCCGWA, TDCLMBP);
            TDCLMBP.ACTI_NO = TDCLML.ACTI_NO;
            TDCLMBP.PROD_CD = TDCLML.PROD_CD;
            S000_CALL_TDZLMBP();
            if (pgmRtn) return;
        }
    }
    public void B110_FUNC_MODIFY() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCLML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCLML.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037") 
            && TDROTHE.SDT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.M_PROD_MOD_SDT_ERR);
        }
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
        TDRLMT.KEY.BR = TDCLML.BR;
        TDRLMT.KEY.CHNL_NO = TDCLML.CHNL_NO;
        T000_READ_TDTLMT();
        if (pgmRtn) return;
        if (TDRLMT.SHAR_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_LMT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCLML.BAL);
        if (TDCLML.BAL < TDRLMT.AGN_BAL 
            || TDCLML.BAL < TDRLMT.UNAGN_USE_BAL) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_USER_LMT_ERR, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READUPD_TDTLMT();
        if (pgmRtn) return;
        WS_BAL_TI = TDCLML.BAL - TDRLMT.TOT_BAL;
        CEP.TRC(SCCGWA, WS_BAL_TI);
        TDRLMT.TOT_BAL = TDCLML.BAL;
        TDRLMT.UNAGN_BAL = TDRLMT.TOT_BAL - TDRLMT.AGN_BAL;
        TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_BAL - TDRLMT.UNAGN_USE_BAL;
        TDCLML.LM_CNT = TDRLMT.LM_CNT;
        TDRLMT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRLMT.UPD_TLT = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = TDRLMT.UPD_TLT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.UPD_TLT = "0" + TDRLMT.UPD_TLT;
        T000_REWRITE_TDTLMT();
        if (pgmRtn) return;
        if (TDRLMT.LM_CNT > 1) {
            if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
            JIBS_tmp_int = TDRLMT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
            if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_CHNL_FLG = 'Y';
            } else {
                WS_CHNL_FLG = 'N';
            }
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = TDCLML.BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            if (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("12") 
                || BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("13") 
                || (BPCPORUP.DATA_INFO.TYP.equalsIgnoreCase("11") 
                && BPCPORUP.DATA_INFO.ATTR != '0')) {
                WS_ZHIHANG_FLG = 'Y';
            }
            IBS.init(SCCGWA, TDRLMT);
            TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
            TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
            WS_TMP_LVL = (short) (TDCLML.LM_CNT - 1);
            TDRLMT.LM_CNT = WS_TMP_LVL;
            T000_READ_TDTLMT_BYBR();
            if (pgmRtn) return;
            if (WS_HEADBR_FLG == 'N' 
                && WS_ZHIHANG_FLG == 'Y') {
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = TDRLMT.KEY.BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
                TDRLMT.LM_CNT = WS_TMP_LVL;
                T000_READ_TDTLMT_BYBR();
                if (pgmRtn) return;
            }
            if (WS_CHNL_FLG == 'N') {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.init(SCCGWA, TDRLMT);
                    TDRLMT.KEY.ACTI_NO = TDCLML.ACTI_NO;
                    TDRLMT.KEY.PROD_CD = TDCLML.PROD_CD;
                    TDRLMT.KEY.BR = TDCLML.BR;
                    WS_TMP_LVL = (short) (TDCLML.LM_CNT - 1);
                    TDRLMT.LM_CNT = WS_TMP_LVL;
                    T000_READ_TDTLMT_BYBR();
                    if (pgmRtn) return;
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (!TDCLML.CHNL_NO.equalsIgnoreCase(TDRLMT.KEY.CHNL_NO)) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                    }
                    B073_UPDATE_SUPLVL_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (TDROTHE.ACTI_FLG == '1') {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCLML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCLML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, "JFTEST11039");
                if (TDCLML.BR == K_HEAD_BR) {
                    IBS.init(SCCGWA, TDCTLML);
                    TDCTLML.FUNC = 'O';
                    TDCTLML.CCY = TDROTHE.CCY;
                    TDCTLML.PROD_TYP = WS_PROD_TYP;
                    TDCTLML.FR = 1;
                    TDCTLML.AGN_BAL = WS_BAL_TI;
                    WS_DT_IN_TI = "" + SCCGWA.COMM_AREA.AC_DATE;
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI = "0" + WS_DT_IN_TI;
                    if (WS_DT_IN_TI == null) WS_DT_IN_TI = "";
                    JIBS_tmp_int = WS_DT_IN_TI.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) WS_DT_IN_TI += " ";
                    if (WS_DT_IN_TI.substring(0, 4).trim().length() == 0) TDCTLML.DT = 0;
                    else TDCTLML.DT = Short.parseShort(WS_DT_IN_TI.substring(0, 4));
                    S000_CALL_TDZTLML();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCLML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDROTHE.ACTI_FLG == '0' 
            && TDROTHE.SUC_FLG != '1') {
            IBS.init(SCCGWA, TDCLMBP);
            TDCLMBP.ACTI_NO = TDCLML.ACTI_NO;
            TDCLMBP.PROD_CD = TDCLML.PROD_CD;
            S000_CALL_TDZLMBP();
            if (pgmRtn) return;
        }
    }
    public void B400_BAL_MODIFY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCLML.ACTI_NO);
        CEP.TRC(SCCGWA, TDCLML.PROD_CD);
        CEP.TRC(SCCGWA, TDCLML.BR);
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDCLML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCLML.CHNL_NO);
        CEP.TRC(SCCGWA, TDROTHE.CHNL_NO);
        if (TDROTHE.ACTI_FLG == '0') {
            TDCLML.ACTI_FLG = '2';
        }
        if (TDCLML.CHNL_NO == null) TDCLML.CHNL_NO = "";
        JIBS_tmp_int = TDCLML.CHNL_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCLML.CHNL_NO += " ";
        if (!TDCLML.CHNL_NO.substring(0, 3).equalsIgnoreCase("101") 
            && (!TDCLML.CHNL_NO.equalsIgnoreCase("10202") 
            && !TDCLML.CHNL_NO.equalsIgnoreCase("10203"))) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NO_NCAN_SHOP, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDROTHE.CHNL_NO == null) TDROTHE.CHNL_NO = "";
        JIBS_tmp_int = TDROTHE.CHNL_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDROTHE.CHNL_NO += " ";
        if (TDCLML.CHNL_NO == null) TDCLML.CHNL_NO = "";
        JIBS_tmp_int = TDCLML.CHNL_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCLML.CHNL_NO += " ";
        if (TDROTHE.CHNL_NO.substring(0, 1).equalsIgnoreCase("0") 
            && TDCLML.CHNL_NO.substring(0, 3).equalsIgnoreCase("101")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NO_NCAN_SHOP, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDROTHE.CHNL_NO == null) TDROTHE.CHNL_NO = "";
        JIBS_tmp_int = TDROTHE.CHNL_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDROTHE.CHNL_NO += " ";
        if (TDROTHE.CHNL_NO.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && TDCLML.CHNL_NO.equalsIgnoreCase("10202")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NO_NCAN_SHOP, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDROTHE.CHNL_NO == null) TDROTHE.CHNL_NO = "";
        JIBS_tmp_int = TDROTHE.CHNL_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDROTHE.CHNL_NO += " ";
        if (TDROTHE.CHNL_NO.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            && TDCLML.CHNL_NO.equalsIgnoreCase("10203")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NO_NCAN_SHOP, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDROTHE.SUC_FLG == '1' 
            && TDROTHE.ACTI_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NO_NCAN_SHOP, TDCLML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRLMT);
        WS_X_FLG = ' ';
        IBS.init(SCCGWA, BPCPORUP);
        WS_BP_T = 1;
        CEP.TRC(SCCGWA, TDCLML.BR);
        while (WS_BP_T < 5 
            && BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_HEAD_BR 
            && TDCLML.BR != K_HEAD_BR) {
            IBS.init(SCCGWA, BPCPORUP);
            if (WS_BP_T == 1) {
                CEP.TRC(SCCGWA, "LUO1");
                CEP.TRC(SCCGWA, TDCLML.BR);
                BPCPORUP.DATA_INFO.BR = TDCLML.BR;
            } else {
