package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8220 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSTOTA CICSTOTA = new CICSTOTA();
    SCCGWA SCCGWA;
    CIB8220_AWA_8220 CIB8220_AWA_8220;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8220 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8220_AWA_8220>");
        CIB8220_AWA_8220 = (CIB8220_AWA_8220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSTOTA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB8220_AWA_8220.CI_NO.trim().length() > 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() > 0 
            && CIB8220_AWA_8220.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "3.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.ENTY_NO.trim().length() > 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.CI_NO.trim().length() > 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.CI_NO.trim().length() > 0 
            && CIB8220_AWA_8220.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2.NO ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.ID_TYP.trim().length() == 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() > 0 
            && CIB8220_AWA_8220.CI_NM.trim().length() > 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.CI_NO.trim().length() == 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() == 0 
            && CIB8220_AWA_8220.ENTY_NO.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8220_AWA_8220.ID_TYP.trim().length() > 0 
            && CIB8220_AWA_8220.ID_NO.trim().length() > 0 
            && CIB8220_AWA_8220.CI_NM.trim().length() > 0) {
            CICSTOTA.FUNC = 'I';
        }
        if (CIB8220_AWA_8220.ID_NO.trim().length() > 0 
            && CIB8220_AWA_8220.CI_NM.trim().length() == 0) {
            CICSTOTA.FUNC = 'D';
        }
        if (CIB8220_AWA_8220.ENTY_NO.trim().length() > 0) {
            CICSTOTA.FUNC = 'A';
        }
        if (CIB8220_AWA_8220.CI_NO.trim().length() > 0) {
            CICSTOTA.FUNC = 'C';
        }
        CEP.TRC(SCCGWA, CICSTOTA.FUNC);
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CICSTOTA.INPUT_DATA.ENTY_NO = CIB8220_AWA_8220.ENTY_NO;
        CICSTOTA.INPUT_DATA.ID_TYPE = CIB8220_AWA_8220.ID_TYP;
        CICSTOTA.INPUT_DATA.ID_NO = CIB8220_AWA_8220.ID_NO;
        CICSTOTA.INPUT_DATA.CI_NM = CIB8220_AWA_8220.CI_NM;
        CICSTOTA.INPUT_DATA.CI_NO = CIB8220_AWA_8220.CI_NO;
        CICSTOTA.INPUT_DATA.CCY = CIB8220_AWA_8220.CCY;
        CICSTOTA.INPUT_DATA.CCY_TYPE = CIB8220_AWA_8220.CCY_TYPE;
        CICSTOTA.INPUT_DATA.PRD_CODE = CIB8220_AWA_8220.PRD_CODE;
        CICSTOTA.INPUT_DATA.PAGE_ROW = CIB8220_AWA_8220.PAGE_ROW;
        CICSTOTA.INPUT_DATA.PAGE_NUM = CIB8220_AWA_8220.PAGE_NUM;
        S000_CALL_CIZSTOTA();
    }
    public void S000_CALL_CIZSTOTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-STOTA", CICSTOTA);
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
