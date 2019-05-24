package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT7550 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQHLD DDCSQHLD = new DDCSQHLD();
    SCCGWA SCCGWA;
    DDB7550_AWA_7550 DDB7550_AWA_7550;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT7550 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7550_AWA_7550>");
        DDB7550_AWA_7550 = (DDB7550_AWA_7550) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.AC);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.SEQ);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.CCY);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.BV_NO);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.HLD_NO);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.ORG_TYPE);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.HLD_FLG);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.HLD_TYP);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.LAST_FLG);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.FO_DATE);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.TO_DATE);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.PAGE_NUM);
        CEP.TRC(SCCGWA, DDB7550_AWA_7550.PAGE_ROW);
        if (DDB7550_AWA_7550.AC.trim().length() == 0 
            && DDB7550_AWA_7550.HLD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
        }
        if (DDB7550_AWA_7550.ORG_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ORG_TYPE_MUST_INPUT);
        }
        if (DDB7550_AWA_7550.HLD_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_TYP_MUST_INPUT);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQHLD);
        DDCSQHLD.AC = DDB7550_AWA_7550.AC;
        DDCSQHLD.SEQ = DDB7550_AWA_7550.SEQ;
        DDCSQHLD.CCY = DDB7550_AWA_7550.CCY;
        DDCSQHLD.CCY_TYPE = DDB7550_AWA_7550.CCY_TYPE;
        DDCSQHLD.BV_NO = DDB7550_AWA_7550.BV_NO;
        DDCSQHLD.HLD_NO = DDB7550_AWA_7550.HLD_NO;
        DDCSQHLD.ORG_TYPE = DDB7550_AWA_7550.ORG_TYPE;
        DDCSQHLD.HLD_FLG = DDB7550_AWA_7550.HLD_FLG;
        DDCSQHLD.HLD_TYP = DDB7550_AWA_7550.HLD_TYP;
        DDCSQHLD.LAST_FLG = DDB7550_AWA_7550.LAST_FLG;
        DDCSQHLD.FO_DATE = DDB7550_AWA_7550.FO_DATE;
        DDCSQHLD.TO_DATE = DDB7550_AWA_7550.TO_DATE;
        DDCSQHLD.PAGE_NUM = DDB7550_AWA_7550.PAGE_NUM;
        DDCSQHLD.PAGE_ROW = DDB7550_AWA_7550.PAGE_ROW;
        S000_CALL_DDZSQHLD();
    }
    public void S000_CALL_DDZSQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSQHLD", DDCSQHLD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
