package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5860 {
    String CDD_O_VIRTUAL_AC = "DD-O-VIRTUAL-AC";
    String K_OUTPUT_FMT = "DD860";
    String WS_MSG_ID = " ";
    short WS_ERR_FLD_NO = 0;
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUOPVS DDCUOPVS = new DDCUOPVS();
    DDCOOPVS DDCOOPVS = new DDCOOPVS();
    SCCGWA SCCGWA;
    DDB5860_AWA_5860 DDB5860_AWA_5860;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5860 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5860_AWA_5860>");
        DDB5860_AWA_5860 = (DDB5860_AWA_5860) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_OPEN_VSAC_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.PARE_AC);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CCY);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CCY_TYP);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_AC_NM);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_NM);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_TL);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_CN_AR);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_IDTYP);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_IDNO);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.REMARK);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.OP_BR);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_DRFLG);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.CHNLNO);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_INTAC);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_MMO);
        CEP.TRC(SCCGWA, DDB5860_AWA_5860.VS_SYSNO);
        if (DDB5860_AWA_5860.PARE_AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.PARE_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5860_AWA_5860.OP_BR == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            WS_ERR_FLD_NO = DDB5860_AWA_5860.OP_BR_NO;
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
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E' 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B200_OPEN_VSAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOPVS);
        DDCUOPVS.PARENT_AC = DDB5860_AWA_5860.PARE_AC;
        DDCUOPVS.CCY = DDB5860_AWA_5860.CCY;
        DDCUOPVS.CCY_TYP = DDB5860_AWA_5860.CCY_TYP;
        DDCUOPVS.VS_AC_NAME = DDB5860_AWA_5860.VS_AC_NM;
        DDCUOPVS.VS_CON_NAME = DDB5860_AWA_5860.VS_CN_NM;
        DDCUOPVS.VS_CON_TEL = DDB5860_AWA_5860.VS_CN_TL;
        DDCUOPVS.VS_CON_ADDR = DDB5860_AWA_5860.VS_CN_AR;
        DDCUOPVS.VS_IDTYP = DDB5860_AWA_5860.VS_IDTYP;
        DDCUOPVS.VS_IDNO = DDB5860_AWA_5860.VS_IDNO;
        DDCUOPVS.REMARK = DDB5860_AWA_5860.REMARK;
        DDCUOPVS.OP_BR = DDB5860_AWA_5860.OP_BR;
        DDCUOPVS.VS_DRFLG = DDB5860_AWA_5860.VS_DRFLG;
        DDCUOPVS.CHNLNO = DDB5860_AWA_5860.CHNLNO;
        DDCUOPVS.VS_INTAC = DDB5860_AWA_5860.VS_INTAC;
        DDCUOPVS.VS_MMO = DDB5860_AWA_5860.VS_MMO;
        DDCUOPVS.VS_SYSNO = DDB5860_AWA_5860.VS_SYSNO;
        S000_CALL_DDZUOPVS();
    }
    public void S000_CALL_DDZUOPVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_O_VIRTUAL_AC, DDCUOPVS);
        if (DDCUOPVS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCUOPVS.RC);
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
