package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8150 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICCKEV CICCKEV = new CICCKEV();
    SCCGWA SCCGWA;
    CIB8150_AWA_8150 CIB8150_AWA_8150;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8150_AWA_8150>");
        CIB8150_AWA_8150 = (CIB8150_AWA_8150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCKEV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB8150_AWA_8150.CI_NO.trim().length() > 0 
            && CIB8150_AWA_8150.ID_NO.trim().length() > 0 
            && CIB8150_AWA_8150.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "ALL INPUT ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            WS_ERR_INFO = "INPUT INF ONLY ONE";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8150_AWA_8150.ID_NO.trim().length() == 0 
            && CIB8150_AWA_8150.CI_NO.trim().length() == 0 
            && CIB8150_AWA_8150.ENTY_NO.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8150_AWA_8150.ENTY_NO.trim().length() > 0 
            && CIB8150_AWA_8150.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "ENTY-NO*ID-NO INPUT ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            WS_ERR_INFO = "AC.INF/ID.INF ONLY ONE";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8150_AWA_8150.CI_NO.trim().length() > 0 
            && CIB8150_AWA_8150.ID_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CI-NO&ID-NO INPUT ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            WS_ERR_INFO = "CI.INF/ID.INF ONLY ONE";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8150_AWA_8150.CI_NO.trim().length() > 0 
            && CIB8150_AWA_8150.ENTY_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CI-NO&ENTY-NO INPUT ERROR");
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            WS_ERR_INFO = "CI.INF/AC.INF ONLY ONE";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8150_AWA_8150.ID_TYP.trim().length() > 0 
            && CIB8150_AWA_8150.ID_NO.trim().length() > 0 
            && CIB8150_AWA_8150.CI_NM.trim().length() > 0) {
            CICCKEV.FUNC = 'I';
        }
        if (CIB8150_AWA_8150.ID_NO.trim().length() > 0 
            && CIB8150_AWA_8150.CI_NM.trim().length() == 0) {
            CICCKEV.FUNC = 'D';
        }
        if (CIB8150_AWA_8150.ENTY_NO.trim().length() > 0) {
            CICCKEV.FUNC = 'A';
        }
        if (CIB8150_AWA_8150.CI_NO.trim().length() > 0) {
            CICCKEV.FUNC = 'C';
        }
        CEP.TRC(SCCGWA, CICCKEV.FUNC);
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CICCKEV.DATA.ENTY_NO = CIB8150_AWA_8150.ENTY_NO;
        CICCKEV.DATA.ID_TYPE = CIB8150_AWA_8150.ID_TYP;
        CICCKEV.DATA.ID_NO = CIB8150_AWA_8150.ID_NO;
        CICCKEV.DATA.CI_NM = CIB8150_AWA_8150.CI_NM;
        CICCKEV.DATA.CI_NO = CIB8150_AWA_8150.CI_NO;
        S000_CALL_CIZCKEV();
    }
    public void S000_CALL_CIZCKEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CKEV", CICCKEV);
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
