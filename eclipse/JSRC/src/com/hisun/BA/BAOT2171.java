package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2171 {
    String K_OUTPUT_FMT = "BAZ01";
    BAOT2171_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT2171_WS_TEMP_VARIABLE();
    BAOT2171_WS_OUT_DATA WS_OUT_DATA = new BAOT2171_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BACUGD01 BACUGD01 = new BACUGD01();
    BACUDD01 BACUDD01 = new BACUDD01();
    BACUAI02 BACUAI02 = new BACUAI02();
    SCCGWA SCCGWA;
    BAB2170_AWA_2170 BAB2170_AWA_2170;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BAB2170_AWA_2170 BAB2170_AWA_2170) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BAB2170_AWA_2170 = BAB2170_AWA_2170;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BAOT2171 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2170_AWA_2170>");
        BAB2170_AWA_2170 = (BAB2170_AWA_2170) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2170_AWA_2170);
        CEP.TRC(SCCGWA, BAB2170_AWA_2170.BILL_NO);
        CEP.TRC(SCCGWA, BAB2170_AWA_2170.FUN_CD);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
        B200_OUTPUT_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUGD01);
        IBS.init(SCCGWA, BACUDD01);
        IBS.init(SCCGWA, BACUAI02);
        if (BAB2170_AWA_2170.FUN_CD == 'G') {
            BACUGD01.DATA.BILL_NO = BAB2170_AWA_2170.BILL_NO;
            BACUGD01.DATA.FUN_CD = BAB2170_AWA_2170.FUN_CD;
            S000_CALL_BAZUGD01();
        } else if (BAB2170_AWA_2170.FUN_CD == 'D') {
            BACUDD01.DATA.BILL_NO = BAB2170_AWA_2170.BILL_NO;
            BACUDD01.DATA.FUN_CD = BAB2170_AWA_2170.FUN_CD;
            S000_CALL_BAZUDD01();
        } else if (BAB2170_AWA_2170.FUN_CD == 'A') {
            BACUAI02.DATA.BILL_NO = BAB2170_AWA_2170.BILL_NO;
            BACUAI02.DATA.FUN_CD = BAB2170_AWA_2170.FUN_CD;
            S000_CALL_BAZUAI02();
        } else {
        }
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B210_TRANS_DATA_TO_OUT_FMT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        if (BAB2170_AWA_2170.FUN_CD == 'G') {
            WS_OUT_DATA.WS_OUT_REAL_AMT = BACUGD01.DATA.TRXAMT;
        } else if (BAB2170_AWA_2170.FUN_CD == 'D') {
            WS_OUT_DATA.WS_OUT_REAL_AMT = BACUDD01.DATA.TRXAMT;
        } else if (BAB2170_AWA_2170.FUN_CD == 'A') {
            WS_OUT_DATA.WS_OUT_REAL_AMT = BACUAI02.DATA.TRXAMT;
        } else {
        }
    }
    public void S000_CALL_BAZUDD01() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-BAMT-DD01", BACUDD01);
        CEP.TRC(SCCGWA, BACUDD01.RC);
        if (BACUDD01.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUDD01.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZUAI02() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-BAMT-AI02", BACUAI02);
        if (BACUAI02.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUAI02.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZUGD01() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-BAMT-GD01", BACUGD01);
        if (BACUGD01.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUGD01.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
