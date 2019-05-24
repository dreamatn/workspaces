package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5412 {
    int JIBS_tmp_int;
    char K_CARD_MTH = '1';
    char K_TRANS_OUT = '0';
    char K_TRANS_IN = '1';
    String CDD_M_AUTO_TD_PLAN = "DC-M-AUTO-TD-PLAN";
    String WS_MSG_ID = " ";
    String WS_PROD_CDE = " ";
    double WS_TRM_AMT_T = 0;
    DCOT5412_REDEFINES9 REDEFINES9 = new DCOT5412_REDEFINES9();
    char WS_TRIG_MTH = ' ';
    char WS_PERM_KND = ' ';
    short WS_PERD_FREQ = 0;
    short WS_CNT = 0;
    char K_ERROR = 'E';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUMATP DCCUMATP = new DCCUMATP();
    SCCGWA SCCGWA;
    DCB5412_AWA_5412 DCB5412_AWA_5412;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5412 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5412_AWA_5412>");
        DCB5412_AWA_5412 = (DCB5412_AWA_5412) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ADD_CON_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.FUNC_M);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.AGR_NO);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.CI_NAME);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROD_LVL);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.CCY);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.CCY);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.CCY_TYP);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.CCY_TYP);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROCS_DT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROCL_DT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PROC_TYP);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PERM_KND);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRIG_MD);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.PERD);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRIG_TMS);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRM_AMT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRIG_MTH);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.INT_MTH);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.MRM_AMT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRC_AMT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TRPCT_S);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.TR_AGRNO);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DEP_TERM);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DRAW_FLG);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DRAW_USE);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DRAW_MAX);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DRAW_MIN);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.DRAW_AMT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.LIMT_AMT);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.LNK_INFO[1-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.LNK_INFO[2-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.LNK_INFO[3-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5412_AWA_5412.LNK_INFO[5-1].LNK_ACNO);
        if (DCB5412_AWA_5412.AGR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        if (DCB5412_AWA_5412.PROD_CDE.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_PROD_CDE = DCB5412_AWA_5412.PROD_CDE;
        CEP.TRC(SCCGWA, WS_PROD_CDE);
        if ((WS_PROD_CDE.equalsIgnoreCase("8821010000") 
            || WS_PROD_CDE.equalsIgnoreCase("8821020000"))) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROD_TYP_INVALID;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5412_AWA_5412.CCY.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5412_AWA_5412.CCY_TYP == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5412_AWA_5412.PROCS_DT == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5412_AWA_5412.PROCL_DT == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5412_AWA_5412.PROC_TYP == 'O') {
            if (DCB5412_AWA_5412.TRIG_MTH == ' ') {
                WS_TRIG_MTH = '1';
            } else {
                WS_TRIG_MTH = DCB5412_AWA_5412.TRIG_MTH;
            }
            if ((WS_TRIG_MTH == '1' 
                || WS_TRIG_MTH == '2' 
                || WS_TRIG_MTH == '3')) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRIG_MTH_INV;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (WS_TRIG_MTH == '3') {
                if (DCB5412_AWA_5412.TRPCT_S == 0) {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PCT_S_M_INPUT;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (WS_TRIG_MTH == '1') {
                if (DCB5412_AWA_5412.TRC_AMT == 0) {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_FIX_ERR;
                    CEP.ERRC(SCCGWA, WS_MSG_ID);
                }
            }
            if (WS_TRIG_MTH == '2') {
                if (DCB5412_AWA_5412.MRM_AMT == 0) {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_SUB_ERR;
                    CEP.ERRC(SCCGWA, WS_MSG_ID);
                }
            }
            if (DCB5412_AWA_5412.TRIG_TMS == ' ') {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRIG_TMS_INPUT;
                CEP.ERRC(SCCGWA, WS_MSG_ID);
            }
            if (DCB5412_AWA_5412.PERM_KND == ' ') {
                WS_PERM_KND = 'D';
            } else {
                WS_PERM_KND = DCB5412_AWA_5412.PERM_KND;
            }
            if ((WS_PERM_KND == 'D' 
                || WS_PERM_KND == 'M' 
                || WS_PERM_KND == 'Y' 
                || WS_PERM_KND == 'W')) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PER_KND_INV;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DCB5412_AWA_5412.PERD == 0) {
                WS_PERD_FREQ = 1;
            } else {
                WS_PERD_FREQ = DCB5412_AWA_5412.PERD;
            }
        }
        if (DCB5412_AWA_5412.PROC_TYP == 'I') {
            if (DCB5412_AWA_5412.DRAW_FLG == ' ') {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DRAW_FLG_M_INPUT;
                CEP.ERRC(SCCGWA, WS_MSG_ID);
            }
            if (DCB5412_AWA_5412.DRAW_USE.trim().length() == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DRAW_USE_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
            if (DCB5412_AWA_5412.PROD_CDE.equalsIgnoreCase("9510000006") 
                && DCB5412_AWA_5412.LNK_INFO[1-1].LNK_ACNO.trim().length() == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_AC_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_ADD_CON_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.FUNC_M = '4';
        DCCUMATP.IO_AREA.AGR_NO = DCB5412_AWA_5412.AGR_NO;
        DCCUMATP.IO_AREA.CI_NAME = DCB5412_AWA_5412.CI_NAME;
        DCCUMATP.IO_AREA.PROD_CDE = DCB5412_AWA_5412.PROD_CDE;
        DCCUMATP.IO_AREA.PROD_LVL = DCB5412_AWA_5412.PROD_LVL;
        DCCUMATP.IO_AREA.CCY = DCB5412_AWA_5412.CCY;
        DCCUMATP.IO_AREA.CCY_TYP = DCB5412_AWA_5412.CCY_TYP;
        DCCUMATP.IO_AREA.PROCS_DT = DCB5412_AWA_5412.PROCS_DT;
        DCCUMATP.IO_AREA.PROCL_DT = DCB5412_AWA_5412.PROCL_DT;
        DCCUMATP.IO_AREA.PROC_TYP = "" + DCB5412_AWA_5412.PROC_TYP;
        JIBS_tmp_int = DCCUMATP.IO_AREA.PROC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DCCUMATP.IO_AREA.PROC_TYP = "0" + DCCUMATP.IO_AREA.PROC_TYP;
        if (DCB5412_AWA_5412.PROC_TYP == 'O') {
            DCCUMATP.IO_AREA.PERM_KND = WS_PERM_KND;
            DCCUMATP.IO_AREA.TRIG_MD = DCB5412_AWA_5412.TRIG_MD;
            DCCUMATP.IO_AREA.PERD = DCB5412_AWA_5412.PERD;
            if (DCB5412_AWA_5412.TRIG_TMS == ' ') DCCUMATP.IO_AREA.TRIG_TMS = 0;
            else DCCUMATP.IO_AREA.TRIG_TMS = Integer.parseInt(""+DCB5412_AWA_5412.TRIG_TMS);
            DCCUMATP.IO_AREA.TRM_AMT = DCB5412_AWA_5412.TRM_AMT;
            DCCUMATP.IO_AREA.TRIG_MTH = WS_TRIG_MTH;
            DCCUMATP.IO_AREA.INT_MTH = DCB5412_AWA_5412.INT_MTH;
            DCCUMATP.IO_AREA.TRPCT_S = DCB5412_AWA_5412.TRPCT_S;
            DCCUMATP.IO_AREA.MRM_AMT = DCB5412_AWA_5412.MRM_AMT;
            DCCUMATP.IO_AREA.TRC_AMT = DCB5412_AWA_5412.TRC_AMT;
            if (!DCB5412_AWA_5412.PROD_CDE.equalsIgnoreCase("9510000004") 
                && DCB5412_AWA_5412.TR_AGRNO.equalsIgnoreCase(DCB5412_AWA_5412.AGR_NO)) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_AC_EQ_SIGN_AC;
                S000_ERR_MSG_PROC();
            }
            DCCUMATP.IO_AREA.TR_AGRNO = DCB5412_AWA_5412.TR_AGRNO;
            DCCUMATP.IO_AREA.TD_TERM = DCB5412_AWA_5412.DEP_TERM;
        }
        if (DCB5412_AWA_5412.PROC_TYP == 'I') {
            DCCUMATP.IO_AREA.DRAW_FLG = DCB5412_AWA_5412.DRAW_FLG;
            DCCUMATP.IO_AREA.DRAW_USE = DCB5412_AWA_5412.DRAW_USE;
            DCCUMATP.IO_AREA.DRAW_MAX = DCB5412_AWA_5412.DRAW_MAX;
            DCCUMATP.IO_AREA.DRAW_MIN = DCB5412_AWA_5412.DRAW_MIN;
            DCCUMATP.IO_AREA.DRAW_AMT = DCB5412_AWA_5412.DRAW_AMT;
            DCCUMATP.IO_AREA.LIMT_AMT = DCB5412_AWA_5412.LIMT_AMT;
            for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                DCCUMATP.IO_AREA.LNK_INFO[WS_CNT-1].LNK_ACNO = DCB5412_AWA_5412.LNK_INFO[WS_CNT-1].LNK_ACNO;
            }
            if (DCB5412_AWA_5412.PROD_CDE.equalsIgnoreCase("9510000006") 
                && DCB5412_AWA_5412.LNK_INFO[1-1].LNK_ACNO.trim().length() == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_AC_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
        S000_CALL_DCZUMATP();
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_M_AUTO_TD_PLAN, DCCUMATP);
        if (DCCUMATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCUMATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSG_ID);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
