package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT2100 {
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSDRAW DDCSDRAW = new DDCSDRAW();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDB2100_AWA_2100 DDB2100_AWA_2100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT2100 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB2100_AWA_2100>");
        DDB2100_AWA_2100 = (DDB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.AC);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.AC_SEQ);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.CCY);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.TX_AMT);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.TRF_FLG);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.TRF_AC);
        CEP.TRC(SCCGWA, DDB2100_AWA_2100.RMK);
        if (DDB2100_AWA_2100.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO);
        }
        if (DDB2100_AWA_2100.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT);
        }
        if (DDB2100_AWA_2100.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_MUST_INPUT);
        }
        if (DDB2100_AWA_2100.TX_AMT <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT);
        }
        if (DDB2100_AWA_2100.TRF_FLG == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRF_FLG_M_INPUT);
        }
        if (DDB2100_AWA_2100.TRF_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDRAW);
        DDCSDRAW.AC = DDB2100_AWA_2100.AC;
        DDCSDRAW.AC_SEQ = DDB2100_AWA_2100.AC_SEQ;
        DDCSDRAW.CCY = DDB2100_AWA_2100.CCY;
        DDCSDRAW.CCY_TYPE = DDB2100_AWA_2100.CCY_TYPE;
        DDCSDRAW.TX_AMT = DDB2100_AWA_2100.TX_AMT;
        DDCSDRAW.TRF_FLG = DDB2100_AWA_2100.TRF_FLG;
        DDCSDRAW.TRF_AC = DDB2100_AWA_2100.TRF_AC;
        DDCSDRAW.RMK = DDB2100_AWA_2100.RMK;
        S000_CALL_DDZSDRAW();
    }
    public void S000_CALL_DDZSDRAW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-SPEC-DRAW", DDCSDRAW);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
