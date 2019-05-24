package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSISTF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP106";
    String K_BUSS_TYPE = "99";
    char K_LONGTOU_BOX_FLG = '5';
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM ";
    String CPN_U_CMOV_CONFIRM = "BP-U-CMOV-CONFIRM   ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_S_CASHAPP_MAINTAIN = "BP-S-CASHAPP-MAINTAIN";
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String WS_ERR_MSG = " ";
    int WS_INDEX = 0;
    int WS_CCY_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INFO_CNT = 0;
    double WS_TMP_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCMOV BPCUCMOV = new BPCUCMOV();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCOOSTF BPCOOSTF = new BPCOOSTF();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCRCWAT SCRCWA = new SCRCWAT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    BPCSISTF BPCSISTF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSISTF BPCSISTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSISTF = BPCSISTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSISTF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                CEP.TRC(SCCGWA, "WS-CCY-CNT1");
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                B020_IN_STORE_PROC_FOR_CN();
                B025_HISTORY_PROC();
                B030_ON_WAY_PROC();
                CEP.TRC(SCCGWA, "WS-CCY-CNT2");
                CEP.TRC(SCCGWA, WS_CCY_CNT);
            }
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        B050_OUTPUT_PROC();
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_IN_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B020-IN-STORE-PROC-FOR-CN");
        B020_UPDATE_CASH_INFO_FOR_CN();
        B030_GEN_CASH_VCH_FOR_CN();
    }
    public void B020_UPDATE_CASH_INFO_FOR_CN() throws IOException,SQLException,Exception {
        B02_01_UPDATE_CASH_BAL_FOR_CN();
    }
    public void B02_01_UPDATE_CASH_BAL_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B02-01-UPDATE-CASH-BAL-FOR-CN");
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBF);
        BPRCLIB.KEY.CCY = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPRCLIB.KEY.CASH_TYP = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSISTF.PLBOX_NO;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_TMP_AMT = BPRCLIB.BAL + BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW);
            }
        } else {
            WS_TMP_AMT = BPRCLIB.BAL - BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        }
        if (WS_TMP_AMT < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW);
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            }
        }
        if (BPRCLIB.BAL < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY);
        }
    }
    public void B030_GEN_CASH_VCH_FOR_CN() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT != 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "CAS";
            if (BPCSISTF.VB_BOX_IN == '0') {
                if (BPCSISTF.PLBOX_TP == '6') {
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMDT";
                } else {
                    BPCPOEWA.DATA.EVENT_CODE = "DR";
                }
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "CSSTDR";
            }
            BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            S000_CALL_BPZPOEWA();
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B025-HISTORY-PROC");
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCSISTF.PLBOX_NO;
        BPCPCHIS.CONF_SEQ = BPCSISTF.CONF_NO;
        BPCPCHIS.CASH_TYP = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        if (BPCSISTF.PLBOX_NO == null) BPCSISTF.PLBOX_NO = "";
        JIBS_tmp_int = BPCSISTF.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSISTF.PLBOX_NO += " ";
        if (BPCSISTF.PLBOX_NO == null) BPCSISTF.PLBOX_NO = "";
        JIBS_tmp_int = BPCSISTF.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSISTF.PLBOX_NO += " ";
        if (BPCSISTF.PLBOX_NO.substring(0, 1).equalsIgnoreCase("3") 
            || BPCSISTF.PLBOX_NO.substring(0, 1).equalsIgnoreCase("6")) {
            BPCPCHIS.VB_FLG = '0';
        } else {
            BPCPCHIS.VB_FLG = '1';
        }
        BPCPCHIS.IN_OUT = 'D';
        S000_CALL_BPZPCHIS();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-ON-WAY-PROC");
        IBS.init(SCCGWA, BPCUCMOV);
        BPCUCMOV.MOVE_DATE = BPCSISTF.MOV_DT;
        BPCUCMOV.CASH_TYP = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCMOV.CCY = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCMOV.TOTAL_AMT = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        BPCUCMOV.CONF_SEQ = BPCSISTF.CONF_NO;
        BPCUCMOV.OUT_BR = BPCSISTF.OUT_BR;
        BPCUCMOV.OUT_TLR = BPCSISTF.OUT_TLR;
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZUCMOV();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B050-OUTPUT-PROC");
        IBS.init(SCCGWA, BPCOOSTF);
        BPCOOSTF.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOOSTF.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOOSTF.IN_VB_FLG = BPCSISTF.VB_BOX_IN;
        BPCOOSTF.OUT_BR = BPCSISTF.OUT_BR;
        BPCOOSTF.OUT_TLR = BPCSISTF.OUT_TLR;
        BPCOOSTF.OUT_VB_FLG = BPCSISTF.VB_BOX_OT;
        BPCOOSTF.MOV_DT = BPCSISTF.MOV_DT;
        BPCOOSTF.CONF_NO = BPCSISTF.CONF_NO;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOOSTF.CCY_INF[WS_CCY_CNT-1].CCY = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCOOSTF.CCY_INF[WS_CCY_CNT-1].AMT = BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSISTF.FMT;
        SCCFMT.DATA_PTR = BPCOOSTF;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZUCMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_CONFIRM, BPCUCMOV);
        if (BPCUCMOV.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCMOV.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
