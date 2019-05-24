package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT8500 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSCHK DCCSCHK = new DCCSCHK();
    SCCGWA SCCGWA;
    DCB8500_AWA_8500 DCB8500_AWA_8500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT8500 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB8500_AWA_8500>");
        DCB8500_AWA_8500 = (DCB8500_AWA_8500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB8500_AWA_8500.OPT_TYP);
        CEP.TRC(SCCGWA, DCB8500_AWA_8500.SPR_TYP);
        CEP.TRC(SCCGWA, DCB8500_AWA_8500.AC);
        CEP.TRC(SCCGWA, DCB8500_AWA_8500.SEQ);
        CEP.TRC(SCCGWA, DCB8500_AWA_8500.HLD_NO);
        if (DCB8500_AWA_8500.OPT_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPT_TYP_MUST_INPUT;
            WS_FLD_NO = DCB8500_AWA_8500.OPT_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB8500_AWA_8500.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            WS_FLD_NO = DCB8500_AWA_8500.SPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB8500_AWA_8500.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            WS_FLD_NO = DCB8500_AWA_8500.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB8500_AWA_8500.OPT_TYP == '5' 
            && DCB8500_AWA_8500.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            WS_FLD_NO = DCB8500_AWA_8500.HLD_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSCHK);
        DCCSCHK.DATA.OPT_TYP = DCB8500_AWA_8500.OPT_TYP;
        DCCSCHK.DATA.SPR_TYP = DCB8500_AWA_8500.SPR_TYP;
        DCCSCHK.DATA.AC = DCB8500_AWA_8500.AC;
        DCCSCHK.DATA.SEQ = DCB8500_AWA_8500.SEQ;
        DCCSCHK.DATA.HLD_NO = DCB8500_AWA_8500.HLD_NO;
        S000_CALL_DCZSCHK();
    }
    public void S000_CALL_DCZSCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-CHK", DCCSCHK);
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
