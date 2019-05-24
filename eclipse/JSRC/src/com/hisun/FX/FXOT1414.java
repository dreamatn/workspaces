package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1414 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSLLMT FXCSLLMT = new FXCSLLMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    FXB1410_AWA_1410 FXB1410_AWA_1410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1414 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1410_AWA_1410>");
        FXB1410_AWA_1410 = (FXB1410_AWA_1410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (FXB1410_AWA_1410.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_MISSED);
        }
        if (FXB1410_AWA_1410.BLMT_AMT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
        if (FXB1410_AWA_1410.SLMT_AMT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
    }
    public void B020_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSLLMT);
        FXCSLLMT.KEY.CCY = FXB1410_AWA_1410.CCY;
        FXCSLLMT.KEY.TRCHNL = FXB1410_AWA_1410.TRCHNL;
        FXCSLLMT.B_AMT = FXB1410_AWA_1410.BLMT_AMT;
        FXCSLLMT.S_AMT = FXB1410_AWA_1410.SLMT_AMT;
        FXCSLLMT.FUNC = 'D';
        S000_CALL_FXZSLLMT();
    }
    public void S000_CALL_FXZSLLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-LLMT", FXCSLLMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
