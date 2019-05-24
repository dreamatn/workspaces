package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7710 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSRHOL DCCSRHOL = new DCCSRHOL();
    SCCGWA SCCGWA;
    DDB7710_AWA_7710 DDB7710_AWA_7710;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7710 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7710_AWA_7710>");
        DDB7710_AWA_7710 = (DDB7710_AWA_7710) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB7710_AWA_7710.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            WS_FLD_NO = DDB7710_AWA_7710.HLD_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7710_AWA_7710.RHLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_MUST_INPUT;
            WS_FLD_NO = DDB7710_AWA_7710.RHLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7710_AWA_7710.RHLD_TYP != '1' 
            && DDB7710_AWA_7710.RHLD_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_INVALID;
            WS_FLD_NO = DDB7710_AWA_7710.RHLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB7710_AWA_7710.RHLD_TYP == '2' 
            && DDB7710_AWA_7710.RAMT == 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            WS_FLD_NO = DDB7710_AWA_7710.RAMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSRHOL);
        DCCSRHOL.DATA.HLD_NO = DDB7710_AWA_7710.HLD_NO;
        DCCSRHOL.DATA.RHLD_TYP = DDB7710_AWA_7710.RHLD_TYP;
        DCCSRHOL.DATA.RAMT = DDB7710_AWA_7710.RAMT;
        DCCSRHOL.DATA.CHG_NO = DDB7710_AWA_7710.CHG_NO;
        DCCSRHOL.DATA.SPR_NM = DDB7710_AWA_7710.SPR_NM;
        DCCSRHOL.DATA.RSN = DDB7710_AWA_7710.RSN;
        DCCSRHOL.DATA.RMK = DDB7710_AWA_7710.RMK;
        DCCSRHOL.DATA.CHG_BR = DDB7710_AWA_7710.CHG_BR;
        DCCSRHOL.DATA.LAW_NM1 = DDB7710_AWA_7710.LAW_NM1;
        DCCSRHOL.DATA.LAW_NO1 = DDB7710_AWA_7710.LAW_NO1;
        DCCSRHOL.DATA.LAW_NM2 = DDB7710_AWA_7710.LAW_NM2;
        DCCSRHOL.DATA.LAW_NO2 = DDB7710_AWA_7710.LAW_NO2;
        S000_CALL_DCZSRHOL();
    }
    public void S000_CALL_DCZSRHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-RHOL", DCCSRHOL);
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
