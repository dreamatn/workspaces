package com.hisun.CM;

import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT8010 {
    CMCF801_ACNO_ARRAY ACNO_ARRAY;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_COUNT = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCEX BPCEX = new BPCEX();
    CMCF801 CMCF801 = new CMCF801();
    CMCUINQB CMCUINQB = new CMCUINQB();
    BPRMSG BPRMSG = new BPRMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    CMB8010_AWA_8010 CMB8010_AWA_8010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT8010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB8010_AWA_8010>");
        CMB8010_AWA_8010 = (CMB8010_AWA_8010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B000-MAIN-PROC-BEGIN");
        B010_CHK_INPUT_PROC();
        B030_INQUIRE_AC_PROC();
        B070_OUTPUT_PROC();
    }
    public void B010_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-CHK-INPUT-PROC-BEGIN");
        CEP.TRC(SCCGWA, CMB8010_AWA_8010.AC_CNT);
        if (CMB8010_AWA_8010.AC_CNT > 0 
            && CMB8010_AWA_8010.AC_CNT <= 60) {
        } else {
            CEP.TRC(SCCGWA, "M-CM-AC-CNT-ERRO");
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_NUM_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_INQUIRE_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-INQUIRE-AC-PROC-BEGIN");
        for (WS_I = 1; WS_I <= CMB8010_AWA_8010.AC_CNT; WS_I += 1) {
            IBS.init(SCCGWA, CMCUINQB.RC);
            IBS.init(SCCGWA, CMCUINQB.INP_INFO);
            IBS.init(SCCGWA, CMCUINQB.OUT_INFO);
            CMCUINQB.INP_INFO.AC = CMB8010_AWA_8010.AC_ARY[WS_I-1].AC_NO;
            CMCUINQB.INP_INFO.CCY = CMB8010_AWA_8010.AC_ARY[WS_I-1].CCY;
            CMCUINQB.INP_INFO.CCY_TYP = CMB8010_AWA_8010.AC_ARY[WS_I-1].CCY_TYPE;
            S000_CALL_CMZUINQB();
            B050_MOVE_DATA_PROC();
        }
    }
    public void B050_MOVE_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B050-MOVE-DATA-PROC-BEGIN");
        IBS.init(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.set(WS_I-1, ));
        WS_COUNT += 1;
        CEP.TRC(SCCGWA, WS_COUNT);
        CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).AC_NO);
        CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).CCY);
        CMCF801.FIX_DATA.AC_CNT = CMB8010_AWA_8010.AC_CNT;
        ACNO_ARRAY = new CMCF801_ACNO_ARRAY();
        CMCF801.ACNO_AREA.ACNO_ARRAY.add(ACNO_ARRAY);
        ACNO_ARRAY.AC_NO = CMB8010_AWA_8010.AC_ARY[WS_I-1].AC_NO;
        ACNO_ARRAY.CCY = CMCUINQB.OUT_INFO.CCY_O;
        ACNO_ARRAY.CCY_TYPE = CMCUINQB.OUT_INFO.CCY_TYP_O;
        if (CMCUINQB.RC.RC_CODE != 0 
            && CMCUINQB.RC.RC_CODE != ' ') {
            ACNO_ARRAY.RESULT = 'F';
            ACNO_ARRAY.RT_CD = IBS.CLS2CPY(SCCGWA, CMCUINQB.RC);
        } else {
            ACNO_ARRAY.RESULT = 'N';
            ACNO_ARRAY.RT_CD = " ";
            ACNO_ARRAY.LEDGER_BAL = CMCUINQB.OUT_INFO.LEDGER_BAL_O;
            ACNO_ARRAY.AVAIL_BAL = CMCUINQB.OUT_INFO.AVAIL_BAL_O;
            ACNO_ARRAY.HOLD_BAL = CMCUINQB.OUT_INFO.HOLD_BAL_O;
            ACNO_ARRAY.LAST_DATE = CMCUINQB.OUT_INFO.LAST_DATE_O;
            ACNO_ARRAY.AC_STS = CMCUINQB.OUT_INFO.AC_STS_O;
            ACNO_ARRAY.AC_STSW = CMCUINQB.OUT_INFO.AC_STSW_O;
            CEP.TRC(SCCGWA, CMCF801.FIX_DATA.AC_CNT);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).AC_NO);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).CCY);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).CCY_TYPE);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).RESULT);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).RT_CD);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).LEDGER_BAL);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).AVAIL_BAL);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).HOLD_BAL);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).LAST_DATE);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).AC_STS);
            CEP.TRC(SCCGWA, CMCF801.ACNO_AREA.ACNO_ARRAY.get(WS_I-1).AC_STSW);
        }
    }
    public void B070_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B070-OUTPUT-PROC-BEGIN");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35801";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCF801;
        SCCFMT.DATA_LEN = 9182;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CMZUINQB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-CMZUINQB-BEGIN");
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CM-B-INQ-AC-INFO";
        SCCCALL.COMMAREA_PTR = CMCUINQB;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, "99999999999999");
        CEP.TRC(SCCGWA, CMCUINQB.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
