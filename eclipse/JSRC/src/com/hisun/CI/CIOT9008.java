package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9008 {
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    char WS_CITACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICUACR CICUACR = new CICUACR();
    SCCGWA SCCGWA;
    CIB9008_AWA_9008 CIB9008_AWA_9008;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9008 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9008_AWA_9008>");
        CIB9008_AWA_9008 = (CIB9008_AWA_9008) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9008_AWA_9008.AGR_NO);
        CEP.TRC(SCCGWA, CIB9008_AWA_9008.OPR_TYP);
        if (CIB9008_AWA_9008.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9008_AWA_9008.OPR_TYP == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CICUACR.DATA.OPR_TYP = CIB9008_AWA_9008.OPR_TYP;
        CICUACR.DATA.AGR_NO = CIB9008_AWA_9008.AGR_NO;
        S000_CALL_CIZUACR();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZUACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-UPD-ACR", CICUACR);
        CEP.TRC(SCCGWA, CICUACR.RC);
        if (CICUACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICUACR.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
