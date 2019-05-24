package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8230 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSCONS CICSCONS = new CICSCONS();
    SCCGWA SCCGWA;
    CIB8230_AWA_8230 CIB8230_AWA_8230;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8230_AWA_8230>");
        CIB8230_AWA_8230 = (CIB8230_AWA_8230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSCONS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB8230_AWA_8230.CI_NO.trim().length() > 0 
            && CIB8230_AWA_8230.ID_NO.trim().length() > 0 
            && CIB8230_AWA_8230.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "3.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8230_AWA_8230.ENTY_NO.trim().length() > 0 
            && CIB8230_AWA_8230.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8230_AWA_8230.CI_NO.trim().length() > 0 
            && CIB8230_AWA_8230.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8230_AWA_8230.CI_NO.trim().length() > 0 
            && CIB8230_AWA_8230.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8230_AWA_8230.ID_TYP.trim().length() > 0 
            && CIB8230_AWA_8230.ID_NO.trim().length() > 0 
            && CIB8230_AWA_8230.CI_NM.trim().length() > 0) {
            CICSCONS.FUNC = 'I';
        }
        if (CIB8230_AWA_8230.ID_NO.trim().length() > 0 
            && CIB8230_AWA_8230.CI_NM.trim().length() == 0) {
            CICSCONS.FUNC = 'D';
        }
        if (CIB8230_AWA_8230.ENTY_NO.trim().length() > 0) {
            CICSCONS.FUNC = 'A';
        }
        if (CIB8230_AWA_8230.CI_NO.trim().length() > 0) {
            CICSCONS.FUNC = 'C';
        }
        CEP.TRC(SCCGWA, CICSCONS.FUNC);
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CICSCONS.INPUT_DATA.ENTY_NO = CIB8230_AWA_8230.ENTY_NO;
        CICSCONS.INPUT_DATA.ID_TYPE = CIB8230_AWA_8230.ID_TYP;
        CICSCONS.INPUT_DATA.ID_NO = CIB8230_AWA_8230.ID_NO;
        CICSCONS.INPUT_DATA.CI_NM = CIB8230_AWA_8230.CI_NM;
        CICSCONS.INPUT_DATA.CI_NO = CIB8230_AWA_8230.CI_NO;
        CICSCONS.INPUT_DATA.CCY = CIB8230_AWA_8230.CCY;
        CICSCONS.INPUT_DATA.CCY_TYPE = CIB8230_AWA_8230.CCY_TYPE;
        CICSCONS.INPUT_DATA.PRD_CODE = CIB8230_AWA_8230.PRD_CODE;
        CICSCONS.INPUT_DATA.PAGE_ROW = CIB8230_AWA_8230.PAGE_ROW;
        CICSCONS.INPUT_DATA.PAGE_NUM = CIB8230_AWA_8230.PAGE_NUM;
        S000_CALL_CIZSCONS();
    }
    public void S000_CALL_CIZSCONS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-SCONS", CICSCONS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
