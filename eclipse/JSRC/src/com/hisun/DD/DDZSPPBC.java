package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSPPBC {
    int JIBS_tmp_int;
    String CPN_DD_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String CPN_DD_SVR_CI_NAME = "DD-SVR-CI-NAME";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    DDCSPPBC DDCSPPBC;
    public void MP(SCCGWA SCCGWA, DDCSPPBC DDCSPPBC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSPPBC = DDCSPPBC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSPPBC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_AC_INF_PROC();
        B030_PRT_PSBK_COVER_PROC();
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSPPBC.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSPPBC.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDCSPPBC.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSPPBC.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCSPPBC.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDCSPPBC.AC = CICQACRL.O_DATA.O_AC_NO;
            B010_CHK_CARD_NO_PROC();
        } else {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = DDCSPPBC.AC;
            DDCSCINM.INPUT_DATA.FUNC = '1';
            S000_CALL_DDZSCINM();
        }
    }
    public void B010_CHK_CARD_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CICQACRL.DATA.REL_AC_NO;
        S000_CALL_DCZUCINF();
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR);
        }
    }
    public void B030_PRT_PSBK_COVER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.AC = DDCSPPBC.AC;
        DDCIPSBK.AC_ENAME = DDCSPPBC.AC_ENAME;
        DDCIPSBK.AC_CNAME = DDCSPPBC.AC_CNAME;
        DDCIPSBK.FUNC = 'B';
        S000_CALL_DDZIPSBK();
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_SVR_CI_NAME, DDCSCINM);
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
