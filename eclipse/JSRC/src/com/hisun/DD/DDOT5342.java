package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5342 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSFGAM DDCSFGAM = new DDCSFGAM();
    SCCGWA SCCGWA;
    DDB5342_AWA_5342 DDB5342_AWA_5342;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5342 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5342_AWA_5342>");
        DDB5342_AWA_5342 = (DDB5342_AWA_5342) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_CALL_FGAM_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5342_AWA_5342.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5342_AWA_5342.BNK.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_BK_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CALL_FGAM_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.FUNC);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.BNK);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.CCY);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.EXR_TYP);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.FLG);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.FRG_LAMT);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.TEMP_AMT);
        CEP.TRC(SCCGWA, DDB5342_AWA_5342.REMK);
        IBS.init(SCCGWA, DDCSFGAM);
        DDCSFGAM.FUNC = DDB5342_AWA_5342.FUNC;
        DDCSFGAM.BNK = DDB5342_AWA_5342.BNK;
        DDCSFGAM.CCY = DDB5342_AWA_5342.CCY;
        DDCSFGAM.EXR_TYP = DDB5342_AWA_5342.EXR_TYP;
        DDCSFGAM.FLG = DDB5342_AWA_5342.FLG;
        DDCSFGAM.FRG_LAMT = DDB5342_AWA_5342.FRG_LAMT;
        DDCSFGAM.TEMP_AMT = DDB5342_AWA_5342.TEMP_AMT;
        DDCSFGAM.REMK = DDB5342_AWA_5342.REMK;
        S000_CALL_DDZSFGAM();
    }
    public void S000_CALL_DDZSFGAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSFGAM", DDCSFGAM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
