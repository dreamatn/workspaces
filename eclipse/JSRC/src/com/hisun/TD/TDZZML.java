package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZZML {
    int JIBS_tmp_int;
    brParm TDTOTHE_BR = new brParm();
    DBParm TDTOTHE_RD;
    brParm TDTLMT_BR = new brParm();
    DBParm TDTLMT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_INQ_FMT = "TD500";
    String K_BRW_FMT = "TD502";
    int K_HEAD_BR = 043999;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_MSGID = " ";
    int WS_I = 0;
    int WS_BAL_CNT = 0;
    double WS_LMT_TOT_BAL = 0;
    short WS_LM_CNT = 0;
    short WS_TMP_LVL = 0;
    String WS_TMP_POINT = " ";
    int WS_TMP_BR = 0;
    double WS_BAL_TI = 0;
    String WS_MSGID1 = " ";
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    char WS_OTHE_LMT_FLG = ' ';
    char WS_TML_FU = ' ';
    char WS_X_FLG = ' ';
    int WS_BP_BR1 = 0;
    int WS_BP_BR2 = 0;
    short WS_ST = 0;
    short WS_EN = 0;
    String WS_PROD_CD = " ";
    char WS_END_FLG = ' ';
    TDZZML_WS_CATY_CURRE[] WS_CATY_CURRE = new TDZZML_WS_CATY_CURRE[10];
    String WS_SMK = " ";
    char WS_C_FUNC = ' ';
    String WS_C_PROD_CD = " ";
    String WS_C_ACTI_NO = " ";
    int WS_C_BR = 0;
    char WS_C_SHAR_FLG = ' ';
    double WS_C_TOT_BAL = 0;
    char TDZZML_FILLER7 = 0X01;
    double WS_C_AGN_BAL = 0;
    char TDZZML_FILLER9 = 0X01;
    double WS_C_AUSE_BAL = 0;
    char TDZZML_FILLER11 = 0X01;
    double WS_C_ACUR_BAL = 0;
    char TDZZML_FILLER13 = 0X01;
    double WS_C_UAG_BAL = 0;
    char TDZZML_FILLER15 = 0X01;
    double WS_C_UUSE_BAL = 0;
    char TDZZML_FILLER17 = 0X01;
    double WS_C_UCUR_BAL = 0;
    char TDZZML_FILLER19 = 0X01;
    char WS_C_SUC_FLG = ' ';
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
    TDCOZML TDCOZML = new TDCOZML();
    SCCMPAG SCCMPAG = new SCCMPAG();
    int WS_HEADBR = 0;
    int WS_LMT_CNT = 0;
    int WS_STR_ROW = 0;
    SCCGWA SCCGWA;
    TDCZML TDCZML;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public TDZZML() {
        for (int i=0;i<10;i++) WS_CATY_CURRE[i] = new TDZZML_WS_CATY_CURRE();
    }
    public void MP(SCCGWA SCCGWA, TDCZML TDCZML) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCZML = TDCZML;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZZML return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDCOZML);
        WS_TIME = 0;
        WS_TIME2 = 0;
        WS_PROD_TYP = " ";
        WS_DT_IN_TI = " ";
        WS_SHAR_FLG_TI = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCZML.FUNC);
        CEP.TRC(SCCGWA, TDCZML.PROD_CD);
        CEP.TRC(SCCGWA, TDCZML.SMK);
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCZML.PROD_CD;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCZML.FUNC = 'Q';
        }
        if (TDCZML.FUNC == 'C') {
            R000_01_OUT_TITLE();
            if (pgmRtn) return;
            B020_I_FUNC();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'A') {
            B070_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'D') {
            B090_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'M') {
            R000_01_OUT_TITLE();
            if (pgmRtn) return;
            B110_FUNC_MODIFY();
            if (pgmRtn) return;
            R000_02_OUTPUT_DATA();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'B') {
            B400_BAL_MODIFY();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'Q') {
            B400_BAL_MODIFY();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'J') {
            B500_JUDGE_MODIFY();
            if (pgmRtn) return;
        } else if (TDCZML.FUNC == 'T') {
            B600_BAL_TMLT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_I_FUNC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        if (TDCZML.ACTI_NO.trim().length() > 0) {
            TDROTHE.KEY.ACTI_NO = TDCZML.ACTI_NO;
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRLMT);
            TDRLMT.KEY.PROD_CD = TDROTHE.PROD_CD;
            TDRLMT.KEY.ACTI_NO = TDROTHE.KEY.ACTI_NO;
            TDRLMT.KEY.BR = K_HEAD_BR;
            TDRLMT.KEY.CHNL_NO = "" + 0X00;
            JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO = "0" + TDRLMT.KEY.CHNL_NO;
            T000_START_TDTLMT();
            if (pgmRtn) return;
            T000_RENEXT_TDTLMT();
            if (pgmRtn) return;
            while (WS_LMT_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
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
                R000_02_OUTPUT_DATA();
                if (pgmRtn) return;
                T000_RENEXT_TDTLMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_LMT_FLG);
            }
            if (WS_TIME <= 0) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTLMT();
            if (pgmRtn) return;
        } else {
            TDROTHE.ACTI_FLG = TDCZML.ACTI_FLG;
            if (TDCZML.SDT != 0) {
                TDROTHE.STR_DATE = TDCZML.SDT;
            } else {
                TDROTHE.STR_DATE = 10101;
            }
            if (TDCZML.DDT != 0) {
                TDROTHE.END_DATE = TDCZML.DDT;
            } else {
                TDROTHE.END_DATE = 99991231;
            }
            TDROTHE.KEY.ACTI_NO = "" + 0X00;
            JIBS_tmp_int = TDROTHE.KEY.ACTI_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDROTHE.KEY.ACTI_NO = "0" + TDROTHE.KEY.ACTI_NO;
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            TDRLMT.KEY.BR = K_HEAD_BR;
            TDRLMT.KEY.CHNL_NO = "" + 0X00;
            JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO = "0" + TDRLMT.KEY.CHNL_NO;
            CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
            CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
            CEP.TRC(SCCGWA, TDROTHE.END_DATE);
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            WS_OTHE_LMT_FLG = 'C';
            T000_START_TDTOTHE();
            if (pgmRtn) return;
            T000_RENEXT_TDTOTHE();
            if (pgmRtn) return;
            WS_TIME = 0;
            CEP.TRC(SCCGWA, WS_OTHE_LMT_FLG);
            TDRLMT.KEY.PROD_CD = TDROTHE.PROD_CD;
            while (WS_OTHE_LMT_FLG != 'N') {
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
                    while (WS_LMT_FLG != 'N' 
                        && SCCMPAG.FUNC != 'E') {
                        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                        CEP.TRC(SCCGWA, WS_TIME);
                        S000_CALL_TDZPROD();
                        if (pgmRtn) return;
                        B030_I_OUT_MSG();
                        if (pgmRtn) return;
                        R000_02_OUTPUT_DATA();
                        if (pgmRtn) return;
                        T000_RENEXT_TDTLMT();
                        if (pgmRtn) return;
                    }
                    T000_RENEXT_TDTOTHE();
                    if (pgmRtn) return;
                    if (!TDROTHE.PROD_CD.equalsIgnoreCase(TDCZML.PROD_CD)) {
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
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTOTHE();
            if (pgmRtn) return;
        }
    }
    public void B030_I_OUT_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TIME);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.ACTI_DESC);
        if (SCCGWA.COMM_AREA.AC_DATE < TDROTHE.STR_DATE) {
            TDCOZML.XS_TYP = '1';
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDROTHE.STR_DATE 
            && SCCGWA.COMM_AREA.AC_DATE < TDROTHE.END_DATE) {
            TDCOZML.XS_TYP = '2';
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDROTHE.END_DATE) {
            TDCOZML.XS_TYP = '3';
        }
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        CEP.TRC(SCCGWA, TDROTHE.TERM);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.MIN_BAL);
        CEP.TRC(SCCGWA, TDROTHE.SUC_FLG);
        CEP.TRC(SCCGWA, TDRLMT.TOT_BAL);
        CEP.TRC(SCCGWA, TDRLMT.AGN_BAL);
        CEP.TRC(SCCGWA, TDRLMT.AGN_CURR_BAL);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT2);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT1);
        CEP.TRC(SCCGWA, TDRLMT.SALE_AMT3);
        if (TDROTHE.TRAN_TYP == '0') {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = "1" + TDCOZML.LZ_TYP.substring(1);
        } else {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = "0" + TDCOZML.LZ_TYP.substring(1);
        }
        if (TDROTHE.REDE_TYP == '0') {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 2 - 1) + "1" + TDCOZML.LZ_TYP.substring(2 + 1 - 1);
        } else {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 2 - 1) + "0" + TDCOZML.LZ_TYP.substring(2 + 1 - 1);
        }
        if (TDROTHE.PLED_TYP == '0') {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 4 - 1) + "1" + TDCOZML.LZ_TYP.substring(4 + 1 - 1);
        } else {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 4 - 1) + "0" + TDCOZML.LZ_TYP.substring(4 + 1 - 1);
        }
        if (TDCPIOD.EXP_PRM.OPTM_FLG == '0') {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 3 - 1) + "1" + TDCOZML.LZ_TYP.substring(3 + 1 - 1);
        } else {
            if (TDCOZML.LZ_TYP == null) TDCOZML.LZ_TYP = "";
            JIBS_tmp_int = TDCOZML.LZ_TYP.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCOZML.LZ_TYP += " ";
            TDCOZML.LZ_TYP = TDCOZML.LZ_TYP.substring(0, 3 - 1) + "0" + TDCOZML.LZ_TYP.substring(3 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.PART_NUM);
        CEP.TRC(SCCGWA, TDROTHE.INT_PERD);
        CEP.TRC(SCCGWA, TDROTHE.CHNL_NO);
        TDCOZML.PROD_CD = TDROTHE.PROD_CD;
        TDCOZML.ACTI_DESC = TDROTHE.ACTI_DESC;
        TDCOZML.FX_SDT = TDROTHE.STR_DATE;
        TDCOZML.FX_DDT = TDROTHE.END_DATE;
        TDCOZML.PROD_TERM = TDROTHE.TERM;
        TDCOZML.ACTI_NO = TDROTHE.KEY.ACTI_NO;
        TDCOZML.QC_BAL = TDROTHE.MIN_BAL;
        TDCOZML.GRPS_NO = TDROTHE.GRPS_NO;
        TDCOZML.BR = TDRLMT.KEY.BR;
        TDCOZML.C_CHNL_NO = TDRLMT.KEY.CHNL_NO;
        TDCOZML.TOT_BAL = TDRLMT.TOT_BAL;
        TDCOZML.YGM_BAL = TDRLMT.AGN_USE_BAL + TDRLMT.UNAGN_USE_BAL;
        TDCOZML.LMT_BAL = TDRLMT.AGN_CURR_BAL + TDRLMT.UNAGN_CURR_BAL;
        TDCOZML.AGN_BAL = TDRLMT.AGN_BAL;
        TDCOZML.AGN_USE_BAL = TDRLMT.AGN_USE_BAL;
        TDCOZML.AGN_CURR_BAL = TDRLMT.AGN_CURR_BAL;
        TDCOZML.UNAGN_BAL = TDRLMT.UNAGN_BAL;
        TDCOZML.UNAGN_USE_BAL = TDRLMT.UNAGN_USE_BAL;
        TDCOZML.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL;
        TDCOZML.GM_BAL = TDRLMT.SALE_AMT1;
        TDCOZML.WY_BAL = TDRLMT.SALE_AMT2;
        TDCOZML.SJ_BAL = TDRLMT.SALE_AMT3;
        TDCOZML.PART_NUM = TDCPIOD.TXN_PRM.PART_NUM;
        TDCOZML.FX_TYP = TDROTHE.INT_PERD;
        TDCOZML.CHNL_NO = TDROTHE.CHNL_NO;
        TDCOZML.SHAR_FLG = TDRLMT.SHAR_FLG;
        WS_C_PROD_CD = TDROTHE.PROD_CD;
        WS_C_FUNC = TDCZML.FUNC;
        WS_C_ACTI_NO = TDROTHE.KEY.ACTI_NO;
        WS_C_BR = TDRLMT.KEY.BR;
        WS_C_SHAR_FLG = TDRLMT.SHAR_FLG;
        WS_C_TOT_BAL = TDRLMT.TOT_BAL;
        WS_C_AGN_BAL = TDRLMT.AGN_BAL;
        WS_C_AUSE_BAL = TDRLMT.AGN_USE_BAL;
        WS_C_ACUR_BAL = TDRLMT.AGN_CURR_BAL;
        WS_C_UAG_BAL = TDRLMT.UNAGN_BAL;
        WS_C_UUSE_BAL = TDRLMT.UNAGN_USE_BAL;
        WS_C_UCUR_BAL = TDRLMT.UNAGN_CURR_BAL;
        WS_C_SUC_FLG = TDROTHE.SUC_FLG;
        CEP.TRC(SCCGWA, TDRLMT.SHAR_FLG);
        if (TDRLMT.SHAR_FLG == '1') {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = TDCOZML.BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRLMT);
            CEP.TRC(SCCGWA, TDCOZML.ACTI_NO);
            CEP.TRC(SCCGWA, TDCOZML.PROD_CD);
            TDRLMT.KEY.ACTI_NO = TDCOZML.ACTI_NO;
            TDRLMT.KEY.PROD_CD = TDCOZML.PROD_CD;
            CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
            CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
            T000_READ_TDTLMT_BYBR1();
            if (pgmRtn) return;
            TDCOZML.UNAGN_BAL = TDRLMT.UNAGN_BAL;
            TDCOZML.TOT_BAL = TDRLMT.UNAGN_BAL;
            WS_C_TOT_BAL = TDRLMT.UNAGN_BAL;
            WS_C_UAG_BAL = TDRLMT.UNAGN_BAL;
            TDCOZML.UNAGN_USE_BAL = TDRLMT.UNAGN_USE_BAL;
            WS_C_UUSE_BAL = TDRLMT.UNAGN_USE_BAL;
            TDCOZML.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL;
            TDCOZML.LMT_BAL = TDRLMT.UNAGN_CURR_BAL;
            WS_C_UCUR_BAL = TDRLMT.UNAGN_CURR_BAL;
        }
        CEP.TRC(SCCGWA, TDCOZML.AGN_BAL);
        CEP.TRC(SCCGWA, TDCOZML.AGN_USE_BAL);
        CEP.TRC(SCCGWA, TDCOZML.AGN_CURR_BAL);
        CEP.TRC(SCCGWA, TDCOZML.UNAGN_BAL);
        CEP.TRC(SCCGWA, TDCOZML.UNAGN_USE_BAL);
        CEP.TRC(SCCGWA, TDCOZML.UNAGN_CURR_BAL);
        CEP.TRC(SCCGWA, WS_C_TOT_BAL);
        CEP.TRC(SCCGWA, WS_C_UAG_BAL);
        CEP.TRC(SCCGWA, WS_C_UUSE_BAL);
        CEP.TRC(SCCGWA, WS_C_UCUR_BAL);
    }
    public void B070_FUNC_WRITE() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCZML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        if (TDCZML.LM_POINT == null) TDCZML.LM_POINT = "";
        JIBS_tmp_int = TDCZML.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCZML.LM_POINT += " ";
        if (TDROTHE.ACTI_FLG == '0' 
            && TDCZML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDROTHE.END_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ONLY_MOD_SHIX_DT);
        }
        WS_LM_CNT = 0;
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            if (TDCZML.LM_POINT == null) TDCZML.LM_POINT = "";
            JIBS_tmp_int = TDCZML.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCZML.LM_POINT += " ";
            if (TDCZML.LM_POINT.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                WS_LM_CNT = (short) (WS_I);
            }
        }
        if (TDCZML.LM_POINT == null) TDCZML.LM_POINT = "";
        JIBS_tmp_int = TDCZML.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCZML.LM_POINT += " ";
        if (TDCZML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_LM_CNT += 1;
            WS_CHNL_FLG = 'Y';
        } else {
            WS_CHNL_FLG = 'N';
        }
        CEP.TRC(SCCGWA, TDCZML.LM_POINT);
        CEP.TRC(SCCGWA, WS_LM_CNT);
        TDRLMT.LM_CNT = WS_LM_CNT;
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        T000_ACTI_READ_TDTLMT();
        if (pgmRtn) return;
        if (TDCZML.LM_POINT == null) TDCZML.LM_POINT = "";
        JIBS_tmp_int = TDCZML.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCZML.LM_POINT += " ";
        if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
        JIBS_tmp_int = TDRLMT.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
        if (WS_HEADBR_FLG == 'Y' 
            && !TDCZML.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1))) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LM_CNT_ERR, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_GET_HEADBR_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HEADBR_FLG);
        CEP.TRC(SCCGWA, TDCZML.SHAR_FLG);
        if (WS_HEADBR_FLG == 'Y') {
            if (TDCZML.BR == K_HEAD_BR 
                && WS_LM_CNT == 1) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_EXIST, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = TDCZML.BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                WS_TMP_LVL = (short) (WS_LM_CNT - 1);
                TDRLMT.LM_CNT = WS_TMP_LVL;
                T000_READ_TDTLMT_BYBR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRLMT.TOT_BAL);
                if (WS_CHNL_FLG == 'N') {
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL, TDCZML.RC);
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
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCZML.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.init(SCCGWA, TDRLMT);
                        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                        TDRLMT.KEY.BR = TDCZML.BR;
                        WS_TMP_LVL = (short) (WS_LM_CNT - 1);
                        TDRLMT.LM_CNT = WS_TMP_LVL;
                        T000_READ_TDTLMT_BYBR();
                        if (pgmRtn) return;
                        if (WS_HEADBR_FLG == 'N') {
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCZML.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        } else {
                            B073_UPDATE_SUPLVL_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (!TDCZML.CHNL_NO.equalsIgnoreCase(TDRLMT.KEY.CHNL_NO)) {
                            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                        }
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, TDCZML.SHAR_FLG);
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                TDRLMT.KEY.BR = TDCZML.BR;
                TDRLMT.KEY.CHNL_NO = TDCZML.CHNL_NO;
                TDRLMT.LM_CNT = WS_LM_CNT;
                TDRLMT.LM_POINT = TDCZML.LM_POINT;
                TDRLMT.SHAR_FLG = TDCZML.SHAR_FLG;
                if (TDCZML.SHAR_FLG == '0') {
                    TDRLMT.TOT_BAL = TDCZML.BAL;
                    TDRLMT.UNAGN_BAL = TDCZML.BAL;
                    TDRLMT.UNAGN_CURR_BAL = TDCZML.BAL;
                }
                B071_WRITE_LMT_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_LM_CNT != 1) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (TDROTHE.ACTI_FLG == '1') {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCZML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            if (TDCZML.BR == K_HEAD_BR) {
                if (TDROTHE.ACTI_FLG != '0') {
                    IBS.init(SCCGWA, TDCTLML);
                    TDCTLML.FUNC = 'O';
                    TDCTLML.CCY = TDROTHE.CCY;
                    TDCTLML.PROD_TYP = WS_PROD_TYP;
                    TDCTLML.FR = 1;
                    TDCTLML.AGN_BAL = TDCZML.BAL;
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
                TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                TDRLMT.KEY.BR = TDCZML.BR;
                TDRLMT.KEY.CHNL_NO = TDCZML.CHNL_NO;
                TDRLMT.LM_CNT = WS_LM_CNT;
                TDRLMT.LM_POINT = TDCZML.LM_POINT;
                TDRLMT.SHAR_FLG = '0';
                CEP.TRC(SCCGWA, TDCZML.BAL);
                TDRLMT.TOT_BAL = TDCZML.BAL;
                TDRLMT.UNAGN_BAL = TDCZML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDCZML.BAL;
                B071_WRITE_LMT_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_HEADBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.BR = K_HEAD_BR;
        TDRLMT.LM_CNT = 1;
        T000_READ_TDTLMT_BYBR();
        if (pgmRtn) return;
    }
    public void B071_WRITE_LMT_PROC() throws IOException,SQLException,Exception {
        if (TDCZML.FUNC == 'A') {
            T000_CHECK_TDTLMT();
            if (pgmRtn) return;
        }
        T000_WRITE_TDTLMT();
        if (pgmRtn) return;
    }
    public void B073_UPDATE_SUPLVL_PROC() throws IOException,SQLException,Exception {
        if (TDCZML.FUNC == 'A') {
            if (TDCZML.BAL > TDRLMT.UNAGN_CURR_BAL) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL - TDCZML.BAL;
            TDRLMT.UNAGN_BAL = TDRLMT.UNAGN_BAL - TDCZML.BAL;
            WS_BAL_TI = TDRLMT.AGN_BAL + TDCZML.BAL;
            if (WS_BAL_TI > TDRLMT.TOT_BAL) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (TDCZML.FUNC == 'D') {
                WS_BAL_TI = TDRLMT.AGN_BAL - TDCZML.BAL;
                TDRLMT.AGN_BAL = WS_BAL_TI;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_CURR_BAL - TDCZML.BAL;
                TDRLMT.UNAGN_BAL = TDRLMT.UNAGN_BAL + TDCZML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL + TDCZML.BAL;
            }
            if (TDCZML.FUNC == 'M') {
                WS_BAL_TI = TDRLMT.AGN_BAL + WS_BAL_TI;
                if (WS_BAL_TI > TDRLMT.TOT_BAL) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.M_BAL_ON_CURR);
                }
                TDRLMT.UNAGN_BAL = TDRLMT.TOT_BAL - WS_BAL_TI;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_BAL - TDRLMT.UNAGN_USE_BAL;
                TDRLMT.AGN_BAL = WS_BAL_TI;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_BAL - TDRLMT.AGN_USE_BAL;
            }
        }
        if (TDCZML.FUNC == 'B') {
            if (WS_SHAR_FLG_TI == '0') {
                TDRLMT.AGN_USE_BAL += TDCZML.BAL;
                TDRLMT.AGN_CURR_BAL = TDRLMT.AGN_CURR_BAL - TDCZML.BAL;
            } else {
                if (TDCZML.BAL > TDRLMT.UNAGN_CURR_BAL) {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.M_BAL_ON_CURR, TDCZML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                TDRLMT.UNAGN_USE_BAL += TDCZML.BAL;
                TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL - TDCZML.BAL;
            }
            WS_BAL_TI = TDRLMT.AGN_BAL;
        }
        if (TDCZML.FUNC == 'Q') {
            if (WS_SHAR_FLG_TI == '0') {
                TDRLMT.AGN_USE_BAL = TDRLMT.AGN_USE_BAL - TDCZML.BAL;
                TDRLMT.UNAGN_CURR_BAL += TDCZML.BAL;
            } else {
                TDRLMT.UNAGN_USE_BAL = TDRLMT.AGN_USE_BAL - TDCZML.BAL;
                TDRLMT.UNAGN_CURR_BAL += TDCZML.BAL;
            }
            WS_BAL_TI = TDRLMT.AGN_BAL;
        }
        CEP.TRC(SCCGWA, TDCZML.SHAR_FLG);
        if (TDCZML.SHAR_FLG == '0') {
            TDRLMT.AGN_BAL = WS_BAL_TI;
            if (TDCZML.FUNC == 'A') {
                TDRLMT.AGN_CURR_BAL = WS_BAL_TI;
            }
        }
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
        BPCPQPRD.PRDT_CODE = TDCZML.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
            WS_PROD_TYP = "01";
        } else {
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                WS_PROD_TYP = "02";
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (TDCZML.BR == K_HEAD_BR) {
            IBS.init(SCCGWA, TDCTLML);
            TDCTLML.FUNC = 'S';
            TDCTLML.CCY = TDROTHE.CCY;
            TDCTLML.PROD_TYP = WS_PROD_TYP;
            TDCTLML.FR = 1;
            TDCTLML.AGN_BAL = TDCZML.BAL;
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
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B090_FUNC_DELETE() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCZML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.BR = TDCZML.BR;
        TDRLMT.KEY.CHNL_NO = TDCZML.CHNL_NO;
        T000_READ_TDTLMT();
        if (pgmRtn) return;
        if (WS_LMT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDRLMT.AGN_BAL > 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_INUSE_FORBID, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        TDCZML.BAL = TDRLMT.TOT_BAL - TDRLMT.AGN_USE_BAL - TDRLMT.UNAGN_USE_BAL;
        TDCZML.LM_CNT = TDRLMT.LM_CNT;
        T000_READUPD_TDTLMT();
        if (pgmRtn) return;
        T000_DELETE_TDTLMT();
        if (pgmRtn) return;
        if (TDRLMT.LM_CNT > 1) {
            if (TDRLMT.SHAR_FLG == '0') {
                if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                JIBS_tmp_int = TDRLMT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                if (TDRLMT.LM_POINT.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_CHNL_FLG = 'Y';
                } else {
                    WS_CHNL_FLG = 'N';
                }
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = TDCZML.BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRLMT);
                TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                WS_TMP_LVL = (short) (TDCZML.LM_CNT - 1);
                TDRLMT.LM_CNT = WS_TMP_LVL;
                T000_READ_TDTLMT_BYBR();
                if (pgmRtn) return;
                if (WS_CHNL_FLG == 'N') {
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL, TDCZML.RC);
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
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCZML.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.init(SCCGWA, TDRLMT);
                        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                        TDRLMT.KEY.BR = TDCZML.BR;
                        WS_TMP_LVL = (short) (TDCZML.LM_CNT - 1);
                        TDRLMT.LM_CNT = WS_TMP_LVL;
                        T000_READ_TDTLMT_BYBR();
                        if (pgmRtn) return;
                        if (WS_HEADBR_FLG == 'N') {
                            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCZML.RC);
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
            }
        } else {
            if (TDROTHE.ACTI_FLG == '1') {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCZML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                if (TDCZML.BR == K_HEAD_BR) {
                    IBS.init(SCCGWA, TDCTLML);
                    TDCTLML.FUNC = 'S';
                    TDCTLML.CCY = TDROTHE.CCY;
                    TDCTLML.PROD_TYP = WS_PROD_TYP;
                    TDCTLML.FR = 1;
                    TDCTLML.AGN_BAL = TDCZML.BAL;
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
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCZML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B110_FUNC_MODIFY() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCZML.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.BR = TDCZML.BR;
        TDRLMT.KEY.CHNL_NO = TDCZML.CHNL_NO;
        T000_READ_TDTLMT();
        if (pgmRtn) return;
        if (TDRLMT.SHAR_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_LMT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCZML.BAL);
        if (TDCZML.BAL < TDRLMT.AGN_BAL) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READUPD_TDTLMT();
        if (pgmRtn) return;
        WS_BAL_TI = TDCZML.BAL - TDRLMT.TOT_BAL;
        CEP.TRC(SCCGWA, WS_BAL_TI);
        TDRLMT.TOT_BAL = TDCZML.BAL;
        TDRLMT.UNAGN_BAL = TDRLMT.TOT_BAL - TDRLMT.AGN_BAL;
        TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_BAL - TDRLMT.UNAGN_USE_BAL;
        TDCZML.LM_CNT = TDRLMT.LM_CNT;
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
            BPCPORUP.DATA_INFO.BR = TDCZML.BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRLMT);
            TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
            TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            TDRLMT.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
            WS_TMP_LVL = (short) (TDCZML.LM_CNT - 1);
            TDRLMT.LM_CNT = WS_TMP_LVL;
            T000_READ_TDTLMT_BYBR();
            if (pgmRtn) return;
            if (WS_CHNL_FLG == 'N') {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL, TDCZML.RC);
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
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (WS_HEADBR_FLG == 'N') {
                    IBS.init(SCCGWA, TDRLMT);
                    TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
                    TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
                    TDRLMT.KEY.BR = TDCZML.BR;
                    WS_TMP_LVL = (short) (TDCZML.LM_CNT - 1);
                    TDRLMT.LM_CNT = WS_TMP_LVL;
                    T000_READ_TDTLMT_BYBR();
                    if (pgmRtn) return;
                    if (WS_HEADBR_FLG == 'N') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_ON_INPUT, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        B073_UPDATE_SUPLVL_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (!TDCZML.CHNL_NO.equalsIgnoreCase(TDRLMT.KEY.CHNL_NO)) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_MUST_INPUT);
                    }
                    B073_UPDATE_SUPLVL_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (TDROTHE.ACTI_FLG == '1') {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = TDCZML.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
                    WS_PROD_TYP = "01";
                } else {
                    if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                        WS_PROD_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                if (TDCZML.BR == K_HEAD_BR) {
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
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST, TDCZML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B400_BAL_MODIFY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCZML.ACTI_NO);
        CEP.TRC(SCCGWA, TDCZML.PROD_CD);
        CEP.TRC(SCCGWA, TDCZML.BR);
        IBS.init(SCCGWA, TDRLMT);
        WS_X_FLG = ' ';
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = TDCZML.BR;
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        WS_BP_BR1 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        CEP.TRC(SCCGWA, WS_BP_BR1);
        if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_HEAD_BR) {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = WS_BP_BR1;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            WS_BP_BR2 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        } else {
            WS_BP_BR2 = K_HEAD_BR;
        }
        CEP.TRC(SCCGWA, WS_BP_BR2);
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        TDRLMT.KEY.BR = TDCZML.BR;
        T000_BP_STARTBR_TDTLMT();
        if (pgmRtn) return;
        T000_BP_RENEXT_TDTLMT();
        if (pgmRtn) return;
        while (WS_LMT_FLG != 'Y') {
            T000_BP_RENEXT_TDTLMT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "***************");
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDCZML.BAL);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
        if (WS_LMT_FLG == 'Y') {
            if (TDCZML.BAL > TDRLMT.UNAGN_CURR_BAL 
                && TDRLMT.SHAR_FLG != '1' 
                && TDCZML.FUNC == 'B') {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.M_BAL_ON_CURR, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (TDCZML.FUNC == 'B') {
                TDRLMT.UNAGN_USE_BAL = TDRLMT.UNAGN_USE_BAL + TDCZML.BAL;
                if (TDRLMT.SHAR_FLG == '0') {
                    TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL - TDCZML.BAL;
                }
            } else {
                TDRLMT.UNAGN_USE_BAL = TDRLMT.UNAGN_USE_BAL - TDCZML.BAL;
                if (TDRLMT.SHAR_FLG == '0') {
                    TDRLMT.UNAGN_CURR_BAL = TDRLMT.UNAGN_CURR_BAL + TDCZML.BAL;
                }
            }
            CEP.TRC(SCCGWA, "&&&&&&&&&&&");
            CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
            CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
            CEP.TRC(SCCGWA, TDRLMT.LM_CNT);
            WS_SHAR_FLG_TI = TDRLMT.SHAR_FLG;
            TDCZML.LM_CNT = TDRLMT.LM_CNT;
            T000_REWRITE_TDTLMT();
            if (pgmRtn) return;
            while (WS_X_FLG != 'N' 
                && TDRLMT.KEY.BR != K_HEAD_BR) {
                T000_BP_RENEXT_TDTLMT();
                if (pgmRtn) return;
                if (WS_X_FLG != 'N') {
                    B073_UPDATE_SUPLVL_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.TRC(SCCGWA, TDCZML.BAL);
                CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
                CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
                CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
            }
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
        T000_ENDBR_TDTLMT();
        if (pgmRtn) return;
    }
    public void B500_JUDGE_MODIFY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        WS_X_FLG = ' ';
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = TDCZML.BR;
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        WS_BP_BR1 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        CEP.TRC(SCCGWA, WS_BP_BR1);
        if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_HEAD_BR) {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = WS_BP_BR1;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            WS_BP_BR2 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        } else {
            WS_BP_BR2 = K_HEAD_BR;
        }
        CEP.TRC(SCCGWA, WS_BP_BR2);
        TDRLMT.KEY.ACTI_NO = TDCZML.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCZML.PROD_CD;
        TDRLMT.KEY.BR = TDCZML.BR;
        T000_BP_STARTBR_TDTLMT();
        if (pgmRtn) return;
        T000_BP_RENEXT_TDTLMT();
        if (pgmRtn) return;
        while (WS_LMT_FLG != 'Y') {
            T000_BP_RENEXT_TDTLMT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "***************");
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDCZML.BAL);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
        if (WS_LMT_FLG == 'Y') {
            if (TDCZML.BAL > TDRLMT.UNAGN_CURR_BAL 
                && TDRLMT.SHAR_FLG != '1' 
                && TDCZML.FUNC == 'B') {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.M_BAL_ON_CURR, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_SHAR_FLG_TI = TDRLMT.SHAR_FLG;
            TDCZML.LM_CNT = TDRLMT.LM_CNT;
            T000_REWRITE_TDTLMT();
            if (pgmRtn) return;
            while (WS_X_FLG != 'N' 
                && TDRLMT.KEY.BR != K_HEAD_BR) {
                T000_BP_RENEXT_TDTLMT();
                if (pgmRtn) return;
                if (WS_X_FLG != 'N') {
                    if (TDCZML.BAL > TDRLMT.UNAGN_CURR_BAL 
                        && WS_SHAR_FLG_TI != '0') {
                        IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.M_BAL_ON_CURR, TDCZML.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
                CEP.TRC(SCCGWA, TDCZML.BAL);
                CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
                CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
                CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
            }
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void R000_02_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        if (TDROTHE.ACTI_FLG == '1') {
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCOZML);
            SCCMPAG.DATA_LEN = 410;
        } else {
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDZZML_WS4);
            SCCMPAG.DATA_LEN = 172;
        }
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_START_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_BR.rp = new DBParm();
        TDTOTHE_BR.rp.TableName = "TDTOTHE";
        TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND ACTI_NO >= :TDROTHE.KEY.ACTI_NO "
            + "AND STR_DATE >= :TDROTHE.STR_DATE "
            + "AND END_DATE <= :TDROTHE.END_DATE "
            + "AND ACTI_FLG = :TDROTHE.ACTI_FLG";
        TDTOTHE_BR.rp.order = "ACTI_NO";
        IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_RENEXT_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, WS_OTHE_LMT_FLG);
            WS_OTHE_LMT_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (!TDROTHE.PROD_CD.equalsIgnoreCase(TDCZML.PROD_CD)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
            }
        }
    }
    public void T000_ENDBR_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDRLMT.LM_POINT);
        }
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTIVE_NOT_HAVE, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (!TDROTHE.PROD_CD.equalsIgnoreCase(TDCZML.PROD_CD)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
            }
        }
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_START_TDTLMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        TDTLMT_BR.rp = new DBParm();
        TDTLMT_BR.rp.TableName = "TDTLMT";
        TDTLMT_BR.rp.where = "PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND ACTI_NO = :TDRLMT.KEY.ACTI_NO";
        TDTLMT_BR.rp.order = "BR DESC ,CHNL_NO DESC";
        IBS.STARTBR(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (TDCZML.SDT != 0 
                || TDCZML.DDT != 0) {
                WS_OTHE_LMT_FLG = 'C';
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            WS_OTHE_LMT_FLG = 'Y';
        }
    }
    public void T000_RENEXT_TDTLMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_FLG = 'Y';
            WS_TIME += 1;
        } else {
            WS_LMT_FLG = 'N';
            CEP.TRC(SCCGWA, TDCZML.ACTI_NO);
        }
    }
    public void T000_ENDBR_TDTLMT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDRLMT.LM_POINT);
        }
    }
    public void T000_BP_STARTBR_TDTLMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        TDTLMT_BR.rp = new DBParm();
        TDTLMT_BR.rp.TableName = "TDTLMT";
        TDTLMT_BR.rp.where = "PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND ( BR = :TDRLMT.KEY.BR "
            + "OR BR = :WS_BP_BR1 "
            + "OR BR = :WS_BP_BR2 )";
        TDTLMT_BR.rp.upd = true;
        TDTLMT_BR.rp.order = "LM_CNT DESC";
        IBS.STARTBR(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (TDCZML.SDT != 0 
                || TDCZML.DDT != 0) {
                WS_OTHE_LMT_FLG = 'C';
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            WS_OTHE_LMT_FLG = 'Y';
        }
    }
    public void T000_BP_RENEXT_TDTLMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, WS_LMT_FLG);
            if (WS_LMT_FLG != 'Y') {
                if (TDCZML.CHNL_NO == null) TDCZML.CHNL_NO = "";
                JIBS_tmp_int = TDCZML.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDCZML.CHNL_NO += " ";
                CEP.TRC(SCCGWA, TDCZML.CHNL_NO.substring(0, 3));
                if (TDCZML.CHNL_NO == null) TDCZML.CHNL_NO = "";
                JIBS_tmp_int = TDCZML.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDCZML.CHNL_NO += " ";
                if (TDRLMT.KEY.CHNL_NO == null) TDRLMT.KEY.CHNL_NO = "";
                JIBS_tmp_int = TDRLMT.KEY.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRLMT.KEY.CHNL_NO += " ";
                if (TDCZML.CHNL_NO.substring(0, 3).equalsIgnoreCase(TDRLMT.KEY.CHNL_NO.substring(0, 3)) 
                    || TDRLMT.KEY.CHNL_NO.trim().length() == 0 
                    || TDRLMT.KEY.CHNL_NO.equalsIgnoreCase("99999")) {
                    WS_LMT_FLG = 'Y';
                }
            }
        } else {
            CEP.TRC(SCCGWA, "########");
            if (WS_LMT_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_X_FLG);
                WS_X_FLG = 'N';
            } else {
                CEP.TRC(SCCGWA, WS_LMT_FLG);
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_WRITE_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        IBS.WRITE(SCCGWA, TDRLMT, TDTLMT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTLMT_BYBR() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND BR = :TDRLMT.KEY.BR "
            + "AND LM_CNT = :TDRLMT.LM_CNT";
        TDTLMT_RD.upd = true;
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HEADBR_FLG = 'Y';
        } else {
            WS_HEADBR_FLG = 'N';
        }
    }
    public void T000_READ_TDTLMT_BYBR1() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND BR = :TDRLMT.KEY.BR";
        TDTLMT_RD.fst = true;
        TDTLMT_RD.order = "LM_CNT DESC";
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXXXXXXXX");
            WS_HEADBR_FLG = 'Y';
        } else {
            WS_HEADBR_FLG = 'N';
        }
    }
    public void T000_ACTI_READ_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND LM_CNT = :TDRLMT.LM_CNT";
        TDTLMT_RD.fst = true;
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HEADBR_FLG = 'Y';
        } else {
            WS_HEADBR_FLG = 'N';
        }
    }
    public void T000_CHECK_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND BR = :TDRLMT.KEY.BR "
            + "AND LM_CNT = :TDRLMT.LM_CNT";
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HAVE_SERVICE_RE, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.upd = true;
        IBS.READ(SCCGWA, TDRLMT, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_FLG = 'Y';
        } else {
            WS_LMT_FLG = 'N';
        }
    }
    public void T000_READUPD_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.upd = true;
        IBS.READ(SCCGWA, TDRLMT, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTLMT_Z() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDCZML.ACTI_NO);
        CEP.TRC(SCCGWA, TDCZML.PROD_CD);
        CEP.TRC(SCCGWA, TDCZML.CHNL_NO);
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.upd = true;
        IBS.READ(SCCGWA, TDRLMT, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_LMT_FLG = 'N';
                if (TDRLMT.KEY.BR == K_HEAD_BR) {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST, TDCZML.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST, TDCZML.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_REWRITE_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        IBS.REWRITE(SCCGWA, TDRLMT, TDTLMT_RD);
    }
    public void T000_DELETE_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        IBS.DELETE(SCCGWA, TDRLMT, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_DELETE_TB_ERR, TDCZML.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZTLML() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PRO-TLML", TDCTLML);
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
