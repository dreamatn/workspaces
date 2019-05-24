package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1850 {
    String CDD_C_VIRTUAL_AC = "DD-C-VIRTUAL-AC";
    String K_OUTPUT_FMT = "DD851";
    String WS_MSG_ID = " ";
    short WS_ERR_FLD_NO = 0;
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUCLVS DDCUCLVS = new DDCUCLVS();
    SCCGWA SCCGWA;
    DDB5860_AWA_5860 DDB5860_AWA_5860;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1850 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5860_AWA_5860>");
        DDB5860_AWA_5860 = (DDB5860_AWA_5860) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_CLOSE_VSAC_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.PARE_AC);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.P_AC_NM);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CI_NO);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_AC);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CCY);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CCY_TYP);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_AC_NM);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_NM);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_TL);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_AR);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.REMARK);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.OP_BR);
        if (DDB5860_AWA_5860.VS_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.VS_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5860_AWA_5860.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5860_AWA_5860.CCY_TYP == ' ') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.CCY_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5860_AWA_5860.OP_BR == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.OP_BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E' 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B200_CLOSE_VSAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCLVS);
        DDCUCLVS.VS_AC = DDB5860_AWA_5860.VS_AC;
        DDCUCLVS.CCY = DDB5860_AWA_5860.CCY;
        DDCUCLVS.CCY_TYP = DDB5860_AWA_5860.CCY_TYP;
        DDCUCLVS.REMARK = DDB5860_AWA_5860.REMARK;
        DDCUCLVS.OP_BR = DDB5860_AWA_5860.OP_BR;
        S000_CALL_DDZUCLVS();
    }
    public void S000_CALL_DDZUCLVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_C_VIRTUAL_AC, DDCUCLVS);
        if (DDCUCLVS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCUCLVS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSG_ID, WS_ERR_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
