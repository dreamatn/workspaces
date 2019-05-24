package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQIFA {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTINF_RD;
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_AC_INF = "DD814";
    String WS_ERR_MSG = " ";
    String WS_CCY = " ";
    String WS_AC_ENM = " ";
    String WS_AC_CNM = " ";
    char WS_CCY_FLG = ' ';
    char WS_MSTR_FLG = ' ';
    char WS_CHQ_FLG = ' ';
    DDCOINFA DDCOINFA = new DDCOINFA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRVCH DDRVCH = new DDRVCH();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRVSABI DDRVSABI = new DDRVSABI();
    SCCGWA SCCGWA;
    DDCSQIFA DDCSQIFA;
    public void MP(SCCGWA SCCGWA, DDCSQIFA DDCSQIFA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQIFA = DDCSQIFA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQIFA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_AC_INF();
        if (pgmRtn) return;
        B030_GET_CI_INF();
        if (pgmRtn) return;
        B040_GET_CCY_INF();
        if (pgmRtn) return;
        B050_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQIFA.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, DDCSQIFA.INPUT_DATA.CCY);
        CEP.TRC(SCCGWA, DDCSQIFA.INPUT_DATA.CCY_TYPE);
        if (DDCSQIFA.INPUT_DATA.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INF() throws IOException,SQLException,Exception {
        if (DDCSQIFA.INPUT_DATA.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        DDCSQIFA.OUTPUT_DATA.OPEN_DT = DDRMST.OPEN_DATE;
        DDCSQIFA.OUTPUT_DATA.EXP_DT = DDRMST.EXP_DATE;
        DDCSQIFA.OUTPUT_DATA.OPEN_DP = DDRMST.OPEN_DP;
        DDCSQIFA.OUTPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCSQIFA.OUTPUT_DATA.AC_TYP = DDRMST.AC_TYPE;
        DDCSQIFA.OUTPUT_DATA.AC_PURP = DDRMST.AC_PURP;
        DDCSQIFA.OUTPUT_DATA.SPC_KIND = DDRMST.SPC_KIND.charAt(0);
        DDCSQIFA.OUTPUT_DATA.FRG_TYPE = DDRMST.FRG_TYPE;
        DDCSQIFA.OUTPUT_DATA.CLOSE_DT = DDRMST.CLOSE_DATE;
        DDCSQIFA.CHCK_IND = DDRMST.CHCK_IND;
        if (DDRMST.CLOSE_DATE != 0) {
            DDCSQIFA.CLS_TLR = DDRMST.UPDTBL_TLR;
        }
        DDCSQIFA.OUTPUT_DATA.FRG_IND = DDRMST.FRG_IND;
        DDCSQIFA.OUTPUT_DATA.AC_STS = DDRMST.AC_STS;
        DDCSQIFA.OUTPUT_DATA.STS_WORD = DDRMST.AC_STS_WORD;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSQIFA.OUTPUT_DATA.HOLD_FLG = 'F';
        } else {
            DDCSQIFA.OUTPUT_DATA.HOLD_FLG = 'N';
        }
        DDCSQIFA.OUTPUT_DATA.BLOCK_FLG = 'N';
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSQIFA.OUTPUT_DATA.BLOCK_FLG = 'D';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS == 'C') {
            DDCSQIFA.OUTPUT_DATA.BLOCK_FLG = 'F';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS == 'O' 
            || DDRMST.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSQIFA.OUTPUT_DATA.BLOCK_FLG = 'C';
        }
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
        DDRVCH.VCH_TYPE = '1';
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, DDRVCH);
            DDRVCH.KEY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
            DDRVCH.VCH_TYPE = '2';
            T000_READ_DDTVCH();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, DDRVCH);
                DDRVCH.KEY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
                DDRVCH.VCH_TYPE = '3';
                T000_READ_DDTVCH();
                if (pgmRtn) return;
                DDCSQIFA.OUTPUT_DATA.PAY_TYP = DDRVCH.PAY_TYPE;
            } else {
                DDCSQIFA.OUTPUT_DATA.PAY_TYP = DDRVCH.PAY_TYPE;
            }
        } else {
            DDCSQIFA.OUTPUT_DATA.PSB_STS = DDRVCH.PSBK_STS;
            DDCSQIFA.OUTPUT_DATA.PAY_TYP = DDRVCH.PAY_TYPE;
        }
        CEP.TRC(SCCGWA, DDCSQIFA.OUTPUT_DATA.PAY_TYP);
        DDCSQIFA.OUTPUT_DATA.CR_FLG = DDRMST.CROS_CR_FLG;
        DDCSQIFA.OUTPUT_DATA.DR_FLG = DDRMST.CROS_DR_FLG;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("0")) {
            DDCSQIFA.OUTPUT_DATA.YCHK_FLG = 'Y';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSQIFA.OUTPUT_DATA.YCHK_FLG = 'N';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("0")) {
            DDCSQIFA.OUTPUT_DATA.YCHK_FLG = 'W';
        }
        DDCSQIFA.OUTPUT_DATA.APP_DT = DDRMST.PBC_APPR_DATE;
        DDCSQIFA.OUTPUT_DATA.CLOSE_DT = DDRMST.CLOSE_DATE;
        DDCSQIFA.OUTPUT_DATA.LST_FN_DT = DDRMST.LAST_FN_DATE;
        DDCSQIFA.CI_TYP = DDRMST.CI_TYP;
        if (DDRMST.CI_TYP != '1') {
            IBS.init(SCCGWA, DDRINF);
            DDRINF.KEY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
            T000_READ_DDTINF();
            if (pgmRtn) return;
            DDCSQIFA.OUTPUT_DATA.FIN_STFNAME1 = DDRINF.FIN_STFNAME1;
            DDCSQIFA.OUTPUT_DATA.FIN_STFTEL1 = DDRINF.FIN_STFTEL1;
            DDCSQIFA.OUTPUT_DATA.FIN_STFCELL1 = DDRINF.FIN_STFCELL1;
            DDCSQIFA.OUTPUT_DATA.FIN_STFNAME2 = DDRINF.FIN_STFNAME2;
            DDCSQIFA.OUTPUT_DATA.FIN_STFTEL2 = DDRINF.FIN_STFTEL2;
            DDCSQIFA.OUTPUT_DATA.FIN_STFCELL2 = DDRINF.FIN_STFCELL2;
            DDCSQIFA.OUTPUT_DATA.FIN_STFNAME3 = DDRINF.FIN_STFNAME3;
            DDCSQIFA.OUTPUT_DATA.FIN_STFTEL3 = DDRINF.FIN_STFTEL3;
            DDCSQIFA.OUTPUT_DATA.FIN_STFCELL3 = DDRINF.FIN_STFCELL3;
        }
    }
    public void B030_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSQIFA.INPUT_DATA.AC_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        DDCSQIFA.OUTPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
        DDCSQIFA.OUTPUT_DATA.CI_NM = CICACCU.DATA.CI_CNM;
        DDCSQIFA.OUTPUT_DATA.ID_TYPE = CICACCU.DATA.ID_TYPE;
        if (DDRMST.CI_TYP == '1') {
            if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
            JIBS_tmp_int = CICACCU.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
            if (CICACCU.DATA.STSW.substring(0, 1).equalsIgnoreCase("3")) {
                DDCSQIFA.OUTPUT_DATA.AC_ENM = CICACCU.DATA.CI_ENM;
                DDCSQIFA.OUTPUT_DATA.AC_NAME = CICACCU.DATA.CI_CNM;
            } else {
                if (CICACCU.DATA.AC_ENM.trim().length() == 0) {
                    DDCSQIFA.OUTPUT_DATA.AC_ENM = CICACCU.DATA.CI_ENM;
                } else {
                    DDCSQIFA.OUTPUT_DATA.AC_ENM = CICACCU.DATA.AC_ENM;
                }
                if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                    DDCSQIFA.OUTPUT_DATA.AC_NAME = CICACCU.DATA.CI_CNM;
                } else {
                    DDCSQIFA.OUTPUT_DATA.AC_NAME = CICACCU.DATA.AC_CNM;
                }
            }
        } else {
            DDCSQIFA.OUTPUT_DATA.AC_ENM = CICACCU.DATA.AC_ENM;
            DDCSQIFA.OUTPUT_DATA.AC_NAME = CICACCU.DATA.AC_CNM;
        }
    }
    public void B040_GET_CCY_INF() throws IOException,SQLException,Exception {
        if (DDRMST.CI_TYP == '2' 
            || DDRMST.CI_TYP == '3') {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
            DDRCCY.CCY = DDCSQIFA.INPUT_DATA.CCY;
            DDRCCY.CCY_TYPE = DDCSQIFA.INPUT_DATA.CCY_TYPE;
            if (DDCSQIFA.INPUT_DATA.CCY.trim().length() > 0 
                && DDCSQIFA.INPUT_DATA.CCY_TYPE != ' ') {
                T000_READ_DDTCCY();
                if (pgmRtn) return;
            } else {
                T000_READ_DDTCCY_FIRST();
                if (pgmRtn) return;
            }
            DDCSQIFA.INPUT_DATA.CCY_TYPE = DDRCCY.CCY_TYPE;
        }
        IBS.init(SCCGWA, DDRCCY);
        if (DDCSQIFA.INPUT_DATA.CCY.trim().length() > 0 
            && DDCSQIFA.INPUT_DATA.CCY_TYPE != ' ') {
            DDRCCY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
            DDRCCY.CCY = DDCSQIFA.INPUT_DATA.CCY;
            DDRCCY.CCY_TYPE = DDCSQIFA.INPUT_DATA.CCY_TYPE;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
        } else {
            DDRCCY.CUS_AC = DDCSQIFA.INPUT_DATA.AC_NO;
            T000_READ_DDTCCY_FIRST();
            if (pgmRtn) return;
        }
        DDCSQIFA.OUTPUT_DATA.CURR_BAL = DDRCCY.CURR_BAL;
        DDCSQIFA.OUTPUT_DATA.CURR_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        DDCSQIFA.OUTPUT_DATA.AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, DDCSQIFA.OUTPUT_DATA.AVL_BAL);
        if (DDCSQIFA.OUTPUT_DATA.AVL_BAL < 0) {
            DDCSQIFA.OUTPUT_DATA.AVL_BAL = 0;
        }
        DDCSQIFA.OUTPUT_DATA.HLD_AMT = DDRCCY.HOLD_BAL;
        DDCSQIFA.OUTPUT_DATA.CCY_STS = DDRCCY.STS;
        DDCSQIFA.OUTPUT_DATA.VAL_DT = DDRCCY.OPEN_DATE;
    }
    public void B050_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFA);
        DDCOINFA.AC_NO = DDCSQIFA.INPUT_DATA.AC_OLD;
        DDCOINFA.CCY = DDRCCY.CCY;
        DDCOINFA.CCY_TYP = DDRCCY.CCY_TYPE;
        DDCOINFA.CI_NO = DDCSQIFA.OUTPUT_DATA.CI_NO;
        DDCOINFA.CI_NM = DDCSQIFA.OUTPUT_DATA.CI_NM;
        CEP.TRC(SCCGWA, DDCOINFA.AC_NO);
        CEP.TRC(SCCGWA, DDCOINFA.CCY);
        CEP.TRC(SCCGWA, DDCOINFA.CCY_TYP);
        CEP.TRC(SCCGWA, DDCOINFA.CI_NO);
        CEP.TRC(SCCGWA, DDCOINFA.CI_NM);
        DDCOINFA.ID_TYPE = DDCSQIFA.OUTPUT_DATA.ID_TYPE;
        DDCOINFA.ID_NO = DDCSQIFA.OUTPUT_DATA.ID_NO;
        DDCOINFA.AC_NAME = DDCSQIFA.OUTPUT_DATA.AC_NAME;
        DDCOINFA.AC_ENM = DDCSQIFA.OUTPUT_DATA.AC_ENM;
        CEP.TRC(SCCGWA, DDCOINFA.AC_NAME);
        CEP.TRC(SCCGWA, DDCOINFA.AC_ENM);
        DDCOINFA.OPEN_DT = DDCSQIFA.OUTPUT_DATA.OPEN_DT;
        DDCOINFA.EXP_DT = DDCSQIFA.OUTPUT_DATA.EXP_DT;
        DDCOINFA.OPEN_BR = DDCSQIFA.OUTPUT_DATA.OPEN_BR;
        DDCOINFA.OPEN_DP = DDCSQIFA.OUTPUT_DATA.OPEN_DP;
        CEP.TRC(SCCGWA, DDCOINFA.OPEN_BR);
        CEP.TRC(SCCGWA, DDCOINFA.OPEN_DP);
        DDCOINFA.PROD_CODE = DDCSQIFA.OUTPUT_DATA.PROD_CODE;
        DDCOINFA.AC_TYP = DDCSQIFA.OUTPUT_DATA.AC_TYP;
        DDCOINFA.AC_PURP = DDCSQIFA.OUTPUT_DATA.AC_PURP;
        DDCOINFA.SPC_KIND = DDCSQIFA.OUTPUT_DATA.SPC_KIND;
        DDCOINFA.FRG_TYPE = DDCSQIFA.OUTPUT_DATA.FRG_TYPE;
        DDCOINFA.FRG_IND = DDCSQIFA.OUTPUT_DATA.FRG_IND;
        DDCOINFA.AC_STS = DDCSQIFA.OUTPUT_DATA.AC_STS;
        DDCOINFA.STS_WORD = DDCSQIFA.OUTPUT_DATA.STS_WORD;
        DDCOINFA.HOLD_FLG = DDCSQIFA.OUTPUT_DATA.HOLD_FLG;
        DDCOINFA.BLOCK_FLG = DDCSQIFA.OUTPUT_DATA.BLOCK_FLG;
        DDCOINFA.PSB_USE_IND = DDCSQIFA.OUTPUT_DATA.PSB_USE_IND;
        DDCOINFA.PSB_STS = DDCSQIFA.OUTPUT_DATA.PSB_STS;
        DDCOINFA.PAY_TYP = DDCSQIFA.OUTPUT_DATA.PAY_TYP;
        DDCOINFA.CURR_BAL = DDCSQIFA.OUTPUT_DATA.CURR_BAL;
        DDCOINFA.AVL_BAL = DDCSQIFA.OUTPUT_DATA.AVL_BAL;
        CEP.TRC(SCCGWA, DDCOINFA.AVL_BAL);
        DDCOINFA.HLD_AMT = DDCSQIFA.OUTPUT_DATA.HLD_AMT;
        DDCOINFA.COM_INT_FLG = DDCSQIFA.OUTPUT_DATA.COM_INT_FLG;
        DDCOINFA.CR_FLG = DDCSQIFA.OUTPUT_DATA.CR_FLG;
        DDCOINFA.DR_FLG = DDCSQIFA.OUTPUT_DATA.DR_FLG;
        DDCOINFA.YCHK_FLG = DDCSQIFA.OUTPUT_DATA.YCHK_FLG;
        DDCOINFA.APP_DT = DDCSQIFA.OUTPUT_DATA.APP_DT;
        DDCOINFA.OPEN_NO = DDCSQIFA.OUTPUT_DATA.OPEN_NO;
        DDCOINFA.CLOSE_DT = DDCSQIFA.OUTPUT_DATA.CLOSE_DT;
        DDCOINFA.LST_FN_DT = DDCSQIFA.OUTPUT_DATA.LST_FN_DT;
        DDCOINFA.CI_TYP = DDCSQIFA.CI_TYP;
        DDCOINFA.FIN_STFNAME1 = DDCSQIFA.OUTPUT_DATA.FIN_STFNAME1;
        DDCOINFA.FIN_STFTEL1 = DDCSQIFA.OUTPUT_DATA.FIN_STFTEL1;
        DDCOINFA.FIN_STFCELL1 = DDCSQIFA.OUTPUT_DATA.FIN_STFCELL1;
        DDCOINFA.FIN_STFNAME2 = DDCSQIFA.OUTPUT_DATA.FIN_STFNAME2;
        DDCOINFA.FIN_STFTEL2 = DDCSQIFA.OUTPUT_DATA.FIN_STFTEL2;
        DDCOINFA.FIN_STFCELL2 = DDCSQIFA.OUTPUT_DATA.FIN_STFCELL2;
        DDCOINFA.FIN_STFNAME3 = DDCSQIFA.OUTPUT_DATA.FIN_STFNAME3;
        DDCOINFA.FIN_STFTEL3 = DDCSQIFA.OUTPUT_DATA.FIN_STFTEL3;
        DDCOINFA.FIN_STFCELL3 = DDCSQIFA.OUTPUT_DATA.FIN_STFCELL3;
        DDCOINFA.LAMT_VER_NAME1 = DDRINF.LAMT_VER_NAME1;
        DDCOINFA.LAMT_VER_TEL1 = DDRINF.LAMT_VER_TEL1;
        DDCOINFA.LAMT_VER_CELL1 = DDRINF.LAMT_VER_CELL1;
        DDCOINFA.LAMT_VER_NAME2 = DDRINF.LAMT_VER_NAME2;
        DDCOINFA.LAMT_VER_TEL2 = DDRINF.LAMT_VER_TEL2;
        DDCOINFA.LAMT_VER_CELL2 = DDRINF.LAMT_VER_CELL2;
        DDCOINFA.LAMT_VER_NAME3 = DDRINF.LAMT_VER_NAME3;
        DDCOINFA.LAMT_VER_TEL3 = DDRINF.LAMT_VER_TEL3;
        DDCOINFA.LAMT_VER_CELL3 = DDRINF.LAMT_VER_CELL3;
        DDCOINFA.AUTH_TYP1 = DDRINF.AUTH_TYP1;
        DDCOINFA.AUTH_NO1 = DDRINF.AUTH_NO1;
        DDCOINFA.AUTH_CNM1 = DDRINF.AUTH_CNM1;
        DDCOINFA.AUTH_TEL_NO1 = DDRINF.AUTH_TEL_NO1;
        DDCOINFA.AUTH_TYP2 = DDRINF.AUTH_TYP2;
        DDCOINFA.AUTH_NO2 = DDRINF.AUTH_NO2;
        DDCOINFA.AUTH_CNM2 = DDRINF.AUTH_CNM2;
        DDCOINFA.AUTH_TEL_NO2 = DDRINF.AUTH_TEL_NO2;
        DDCOINFA.AUTH_TYP3 = DDRINF.AUTH_TYP3;
        DDCOINFA.AUTH_NO3 = DDRINF.AUTH_NO3;
        DDCOINFA.AUTH_CNM3 = DDRINF.AUTH_CNM3;
        DDCOINFA.AUTH_TEL_NO3 = DDRINF.AUTH_TEL_NO3;
        DDCOINFA.CASH_FLG = DDRMST.CASH_FLG;
        DDCOINFA.FRG_CODE = DDRMST.FRG_CODE;
        DDCOINFA.FRG_OPEN_NO = DDRMST.FRG_OPEN_NO;
        DDCOINFA.GEN_RSN = DDRMST.GEN_RSN;
        CEP.TRC(SCCGWA, DDCOINFA.AC_NO);
        CEP.TRC(SCCGWA, DDCOINFA.CI_NO);
        CEP.TRC(SCCGWA, DDCOINFA.CI_NM);
        CEP.TRC(SCCGWA, DDCOINFA.ID_TYPE);
        CEP.TRC(SCCGWA, DDCOINFA.ID_NO);
        CEP.TRC(SCCGWA, DDCOINFA.AC_NAME);
        CEP.TRC(SCCGWA, DDCOINFA.AC_ENM);
        CEP.TRC(SCCGWA, DDCOINFA.OPEN_DT);
        CEP.TRC(SCCGWA, DDCOINFA.EXP_DT);
        CEP.TRC(SCCGWA, DDCOINFA.OPEN_BR);
        CEP.TRC(SCCGWA, DDCOINFA.OPEN_DP);
        CEP.TRC(SCCGWA, DDCOINFA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCOINFA.AC_TYP);
        CEP.TRC(SCCGWA, DDCOINFA.AC_PURP);
        CEP.TRC(SCCGWA, DDCOINFA.SPC_KIND);
        CEP.TRC(SCCGWA, DDCOINFA.FRG_TYPE);
        CEP.TRC(SCCGWA, DDCOINFA.FRG_IND);
        CEP.TRC(SCCGWA, DDCOINFA.AC_STS);
        CEP.TRC(SCCGWA, DDCOINFA.STS_WORD);
        CEP.TRC(SCCGWA, DDCOINFA.HOLD_FLG);
        CEP.TRC(SCCGWA, DDCOINFA.BLOCK_FLG);
        CEP.TRC(SCCGWA, DDCOINFA.PSB_USE_IND);
        CEP.TRC(SCCGWA, DDCOINFA.PSB_STS);
        CEP.TRC(SCCGWA, DDCOINFA.PAY_TYP);
        DDCOINFA.CCY_STS = DDCSQIFA.OUTPUT_DATA.CCY_STS;
        DDCOINFA.CHKQ_USE_IND = DDCSQIFA.CHKQ_USE_IND;
        DDCOINFA.CLOSE_DT = DDCSQIFA.OUTPUT_DATA.CLOSE_DT;
        DDCOINFA.CHCK_IND = DDCSQIFA.CHCK_IND;
        DDCOINFA.CLS_TLR = DDCSQIFA.CLS_TLR;
        SCCFMT.FMTID = K_FMT_AC_INF;
        SCCFMT.DATA_PTR = DDCOINFA;
        SCCFMT.DATA_LEN = 3985;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINF() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.READ(SCCGWA, DDRINF, DDTINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
