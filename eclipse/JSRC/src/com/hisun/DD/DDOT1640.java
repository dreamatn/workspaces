package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1640 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDCLS DDCSDCLS = new DDCSDCLS();
    SCCGWA SCCGWA;
    DDB1640_AWA_1640 DDB1640_AWA_1640;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1640 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1640_AWA_1640>");
        DDB1640_AWA_1640 = (DDB1640_AWA_1640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.AC);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.CCY);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.DRAW_MTH);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.PAY_TYP);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.TO_AC);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.CREV_NO);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.TX_MMO);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640.REMARKS);
        CEP.TRC(SCCGWA, DDB1640_AWA_1640);
        if (DDB1640_AWA_1640.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1640_AWA_1640.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1640_AWA_1640.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB1640_AWA_1640.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1640_AWA_1640.CCY_TYPE != '1' 
            && DDB1640_AWA_1640.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            WS_FLD_NO = DDB1640_AWA_1640.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDCLS);
        DDCSDCLS.DATA.AC = DDB1640_AWA_1640.AC;
        DDCSDCLS.DATA.CCY = DDB1640_AWA_1640.CCY;
        DDCSDCLS.DATA.CCY_TYPE = DDB1640_AWA_1640.CCY_TYPE;
        DDCSDCLS.DATA.DRAW_MTH = DDB1640_AWA_1640.DRAW_MTH;
        DDCSDCLS.DATA.PAY_TYP = DDB1640_AWA_1640.PAY_TYP;
        DDCSDCLS.DATA.TO_AC = DDB1640_AWA_1640.TO_AC;
        DDCSDCLS.DATA.CREV_NO = DDB1640_AWA_1640.CREV_NO;
        DDCSDCLS.DATA.TX_MMO = DDB1640_AWA_1640.TX_MMO;
        DDCSDCLS.DATA.REMARKS = DDB1640_AWA_1640.REMARKS;
        DDCSDCLS.DATA.OTH_AC = DDB1640_AWA_1640.OTH_AC;
        DDCSDCLS.DATA.OTH_ACNM = DDB1640_AWA_1640.AC_CNM;
        DDCSDCLS.DATA.OTH_BR = DDB1640_AWA_1640.OTH_BR;
        DDCSDCLS.DATA.OTH_BRNM = DDB1640_AWA_1640.BR_CNM;
        S000_CALL_DDZSDCLS();
    }
    public void S000_CALL_DDZSDCLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSDCLS", DDCSDCLS);
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
