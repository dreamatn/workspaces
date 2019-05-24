package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5413 {
    int JIBS_tmp_int;
    String CDD_U_AUTO_TD_PLAN = "DC-U-AUTO-TD-PLAN";
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    String WS_MSG_ID = " ";
    String WS_PROD_CDE = " ";
    double WS_TRM_AMT_T = 0;
    DCOT5413_REDEFINES7 REDEFINES7 = new DCOT5413_REDEFINES7();
    char WS_TRIG_MTH = ' ';
    char WS_PERM_KND = ' ';
    short WS_PERD_FREQ = 0;
    short WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSUATP DCCSUATP = new DCCSUATP();
    SCCGWA SCCGWA;
    DCB5413_AWA_5413 DCB5413_AWA_5413;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5413 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5413_AWA_5413>");
        DCB5413_AWA_5413 = (DCB5413_AWA_5413) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5413_AWA_5413.AGR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
        if (DCB5413_AWA_5413.OVR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_OVR_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5413_AWA_5413.PROD_CDE.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5413_AWA_5413.CCY.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5413_AWA_5413.CCY_TYP == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    if (DCB5413_AWA_5413.PROCS_DT == 0) {
        WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
        S000_ERR_MSG_PROC();
    }
    if (DCB5413_AWA_5413.PROCL_DT == 0) {
        WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
        S000_ERR_MSG_PROC();
    }
    if (DCB5413_AWA_5413.PROC_TYP == 'O') {
        if (DCB5413_AWA_5413.PERM_KND == ' ') {
            WS_PERM_KND = 'D';
        } else {
            WS_PERM_KND = DCB5413_AWA_5413.PERM_KND;
        }
        if ((WS_PERM_KND == 'D' 
            || WS_PERM_KND == 'W' 
            || WS_PERM_KND == 'M' 
            || WS_PERM_KND == 'Y')) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PER_KND_INV;
            S000_ERR_MSG_PROC();
        }
        if (DCB5413_AWA_5413.PERD == 0) {
            WS_PERD_FREQ = 0;
        } else {
            WS_PERD_FREQ = DCB5413_AWA_5413.PERD;
        }
        if (DCB5413_AWA_5413.TRIG_MTH == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRIG_MTH_M_INPUT;
        } else {
            WS_TRIG_MTH = DCB5413_AWA_5413.TRIG_MTH;
        }
        if ((WS_TRIG_MTH == '1' 
            || WS_TRIG_MTH == '2' 
            || WS_TRIG_MTH == '3')) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRIG_MTH_INV;
            S000_ERR_MSG_PROC();
        }
        if (WS_TRIG_MTH == '3') {
            if (DCB5413_AWA_5413.TRPCT_S == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PCT_S_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_TRIG_MTH == '2') {
            if (DCB5413_AWA_5413.MRM_AMT == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MRM_AMT_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_TRIG_MTH == '1') {
            if (DCB5413_AWA_5413.TRC_AMT == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRC_AMT_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCB5413_AWA_5413.TRIG_TMS == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TRIG_TMS_INPUT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    if (DCB5413_AWA_5413.PROC_TYP == 'I') {
        if (DCB5413_AWA_5413.DRAW_FLG == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DRAW_MTH_M_INPUT;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        if (DCB5413_AWA_5413.DRAW_USE.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DRAW_USE_M_INPUT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.OVR_NO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.CCY);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.CCY_TYP);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.PROCS_DT);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.PROCL_DT);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.SMR);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.FUNC_M);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.TRIG_MD);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.LNK_INFO[1-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.LNK_INFO[2-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.LNK_INFO[3-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.LNK_INFO[4-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.LNK_INFO[5-1].LNK_ACNO);
        CEP.TRC(SCCGWA, DCB5413_AWA_5413.TR_AGRNO);
        IBS.init(SCCGWA, DCCSUATP);
        DCCSUATP.IO_AREA.FUNC_MOD = DCB5413_AWA_5413.FUNC_M;
        DCCSUATP.IO_AREA.AGR_NO = DCB5413_AWA_5413.AGR_NO;
        DCCSUATP.IO_AREA.CI_NAME = DCB5413_AWA_5413.CI_NAME;
        DCCSUATP.IO_AREA.OVR_NO = DCB5413_AWA_5413.OVR_NO;
        DCCSUATP.IO_AREA.PROD_CDE = DCB5413_AWA_5413.PROD_CDE;
        DCCSUATP.IO_AREA.CCY = DCB5413_AWA_5413.CCY;
        DCCSUATP.IO_AREA.CCY_TYP = DCB5413_AWA_5413.CCY_TYP;
        DCCSUATP.IO_AREA.PROCS_DT = DCB5413_AWA_5413.PROCS_DT;
        DCCSUATP.IO_AREA.PROCL_DT = DCB5413_AWA_5413.PROCL_DT;
        DCCSUATP.IO_AREA.PROC_TYP = "" + DCB5413_AWA_5413.PROC_TYP;
        JIBS_tmp_int = DCCSUATP.IO_AREA.PROC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DCCSUATP.IO_AREA.PROC_TYP = "0" + DCCSUATP.IO_AREA.PROC_TYP;
        DCCSUATP.IO_AREA.SMR = DCB5413_AWA_5413.SMR;
        if (DCB5413_AWA_5413.PROC_TYP == 'O') {
            DCCSUATP.IO_AREA.PERM_KND = WS_PERM_KND;
            DCCSUATP.IO_AREA.TRIG_MD = DCB5413_AWA_5413.TRIG_MD;
            DCCSUATP.IO_AREA.PERD = WS_PERD_FREQ;
            if (DCB5413_AWA_5413.TRIG_TMS == ' ') DCCSUATP.IO_AREA.TRIG_TMS = 0;
            else DCCSUATP.IO_AREA.TRIG_TMS = Integer.parseInt(""+DCB5413_AWA_5413.TRIG_TMS);
            DCCSUATP.IO_AREA.TRM_AMT = DCB5413_AWA_5413.TRM_AMT;
            DCCSUATP.IO_AREA.TRIG_MTH = WS_TRIG_MTH;
            DCCSUATP.IO_AREA.INT_MTH = DCB5413_AWA_5413.INT_MTH;
            DCCSUATP.IO_AREA.TRPCT_S = DCB5413_AWA_5413.TRPCT_S;
            DCCSUATP.IO_AREA.MRM_AMT = DCB5413_AWA_5413.MRM_AMT;
            DCCSUATP.IO_AREA.TRC_AMT = DCB5413_AWA_5413.TRC_AMT;
            DCCSUATP.IO_AREA.TR_AGRNO = DCB5413_AWA_5413.TR_AGRNO;
            DCCSUATP.IO_AREA.DEP_TERM = DCB5413_AWA_5413.DEP_TERM;
        }
        if (DCB5413_AWA_5413.PROC_TYP == 'I') {
            DCCSUATP.IO_AREA.DRAW_FLG = DCB5413_AWA_5413.DRAW_FLG;
            DCCSUATP.IO_AREA.DRAW_USE = DCB5413_AWA_5413.DRAW_USE;
            DCCSUATP.IO_AREA.DRAW_MAX = DCB5413_AWA_5413.DRAW_MAX;
            DCCSUATP.IO_AREA.DRAW_MIN = DCB5413_AWA_5413.DRAW_MIN;
            DCCSUATP.IO_AREA.DRAW_AMT = DCB5413_AWA_5413.DRAW_AMT;
            DCCSUATP.IO_AREA.LIMT_AMT = DCB5413_AWA_5413.LIMT_AMT;
            for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                DCCSUATP.IO_AREA.LNK_INFO[WS_CNT-1].LNK_ACNO = DCB5413_AWA_5413.LNK_INFO[WS_CNT-1].LNK_ACNO;
            }
            if (DCB5413_AWA_5413.PROD_CDE.equalsIgnoreCase("9510000006") 
                && DCB5413_AWA_5413.LNK_INFO[1-1].LNK_ACNO.trim().length() == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_AC_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
        S000_CALL_DCZSUATP();
    }
    public void S000_CALL_DCZSUATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_AUTO_TD_PLAN, DCCSUATP);
        if (DCCSUATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCSUATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
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
