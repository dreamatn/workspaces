package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1501 {
    short K_CHECK_TIMES = 60;
    int WS_DATE = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPRTRT BPRTRTT;
    BPCPQBNK_DATA_INFO BPCRBANK;
    FXB1501_AWA_1501 FXB1501_AWA_1501;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1501 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1501_AWA_1501>");
        FXB1501_AWA_1501 = (FXB1501_AWA_1501) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCMWD);
        if (FXB1501_AWA_1501.TYPE == '0') {
            WS_DATE = FXB1501_AWA_1501.DT;
        }
        if (FXB1501_AWA_1501.TYPE == '1') {
            BPCPCMWD.CHECK_DATE = FXB1501_AWA_1501.DT;
            B010_GET_NEXT_DAY();
            WS_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
        }
        if (FXB1501_AWA_1501.TYPE == '2') {
            BPCPCMWD.CHECK_DATE = FXB1501_AWA_1501.DT;
            B010_GET_NEXT_DAY();
            BPCPCMWD.CHECK_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
            B010_GET_NEXT_DAY();
            WS_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
        }
        if (FXB1501_AWA_1501.TYPE == '3') {
            BPCPCMWD.CHECK_DATE = FXB1501_AWA_1501.DT;
            B010_GET_NEXT_DAY();
            BPCPCMWD.CHECK_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
            B010_GET_NEXT_DAY();
            BPCPCMWD.CHECK_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
            B010_GET_NEXT_DAY();
            WS_DATE = BPCPCMWD.NEXT_WORK_DAY[1-1];
        }
        B020_OUTPUT_DATA();
    }
    public void B010_GET_NEXT_DAY() throws IOException,SQLException,Exception {
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = BPCRBANK.COUN_CD;
        CEP.TRC(SCCGWA, BPCRBANK.COUN_CD);
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
        CEP.TRC(SCCGWA, BPCPCMWD.HOLIDAY_FLG_ALL);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[1-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.LAST_WORK_DAY[1-1]);
    }
    public void B020_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX501";
        SCCFMT.DATA_PTR = FXOT1501_WS1;
        SCCFMT.DATA_LEN = 8;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
