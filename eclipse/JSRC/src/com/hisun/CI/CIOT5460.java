package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5460 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSVER CICSVER = new CICSVER();
    SCCGWA SCCGWA;
    CIB5460_AWA_5460 CIB5460_AWA_5460;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5460 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5460_AWA_5460>");
        CIB5460_AWA_5460 = (CIB5460_AWA_5460) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CICSVER.CI_DATE.BR = CIB5460_AWA_5460.BR;
        CICSVER.CI_DATE.IDT_CHK = CIB5460_AWA_5460.IDT_CHK;
        CICSVER.CI_DATE.PAGE_ROW = CIB5460_AWA_5460.PAGE_ROW;
        CICSVER.CI_DATE.PAGE_NUM = CIB5460_AWA_5460.PAGE_NUM;
        CICSVER.CI_DATE.OP_DOWNL = 'N';
        CICSVER.FUNC = 'B';
        B020_CALL_CIZSVER();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CALL_CIZSVER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-VER-CIZSVER", CICSVER);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
