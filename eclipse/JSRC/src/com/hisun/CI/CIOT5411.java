package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5411 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    CICMNAM CICMNAM = new CICMNAM();
    CICMSTS CICMSTS = new CICMSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5410_AWA_5410 CIB5410_AWA_5410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5411 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5410_AWA_5410>");
        CIB5410_AWA_5410 = (CIB5410_AWA_5410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        CICMNAM.DATA.CI_NO = CIB5410_AWA_5410.CI_NO;
        CICMNAM.DATA.SINGLE_DATA.S_NM_TYPE = CIB5410_AWA_5410.NMTYP;
        CICMNAM.DATA.SINGLE_DATA.S_CI_NM = CIB5410_AWA_5410.CI_NM;
        CICMNAM.DATA.SINGLE_DATA.S_OPEN = CIB5410_AWA_5410.OPEN;
        CICMNAM.FUNC = 'A';
        CICMNAM.CTLW_1 = 'O';
        S000_LINK_CIZMNAM();
        if (CIB5410_AWA_5410.OPEN == 'Y') {
            IBS.init(SCCGWA, CICMSTS);
            CICMSTS.FUNC = 'C';
            CICMSTS.DATA.CI_NO = CIB5410_AWA_5410.CI_NO;
            S000_LINK_CIZMSTS();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5410_AWA_5410.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5410_AWA_5410.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5410_AWA_5410.NMTYP.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5410_AWA_5410.NMTYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5410_AWA_5410.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5410_AWA_5410.CI_NM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS, true);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
    }
    public void S000_LINK_CIZMNAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-NAM", CICMNAM);
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
