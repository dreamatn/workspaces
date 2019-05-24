package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7590 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSERHD DCCSERHD = new DCCSERHD();
    SCCGWA SCCGWA;
    DCB7590_AWA_7590 DCB7590_AWA_7590;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7590 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7590_AWA_7590>");
        DCB7590_AWA_7590 = (DCB7590_AWA_7590) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.DD_AC);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.CCY);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.CCY_TYP);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.AMT);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.RSN);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.RMK);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.LCNTR_NO);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.LNTX_SEQ);
        CEP.TRC(SCCGWA, DCB7590_AWA_7590.LMTX_SEQ);
        if (DCB7590_AWA_7590.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DCB7590_AWA_7590.DD_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7590_AWA_7590.LCNTR_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CNTR_NO_M_INPUT;
            WS_FLD_NO = DCB7590_AWA_7590.LCNTR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7590_AWA_7590.LNTX_SEQ == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LNTX_SEQ_M_INPUT;
            WS_FLD_NO = DCB7590_AWA_7590.LNTX_SEQ_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7590_AWA_7590.LMTX_SEQ == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LMTX_SEQ_INPUT;
            WS_FLD_NO = DCB7590_AWA_7590.LMTX_SEQ_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSERHD);
        DCCSERHD.DATA.DD_AC = DCB7590_AWA_7590.DD_AC;
        DCCSERHD.DATA.CCY = DCB7590_AWA_7590.CCY;
        DCCSERHD.DATA.CCY_TYP = DCB7590_AWA_7590.CCY_TYP;
        DCCSERHD.DATA.AMT = DCB7590_AWA_7590.AMT;
        DCCSERHD.DATA.RSN = DCB7590_AWA_7590.RSN;
        DCCSERHD.DATA.RMK = DCB7590_AWA_7590.RMK;
        DCCSERHD.DATA.LNCNTR_NO = DCB7590_AWA_7590.LCNTR_NO;
        DCCSERHD.DATA.LNTX_SEQ = DCB7590_AWA_7590.LNTX_SEQ;
        DCCSERHD.DATA.LMTX_SEQ = DCB7590_AWA_7590.LMTX_SEQ;
        S000_CALL_DCZSERHD();
    }
    public void S000_CALL_DCZSERHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-ERHD", DCCSERHD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
