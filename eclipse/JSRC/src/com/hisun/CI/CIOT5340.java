package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5340 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICOPCS CICOPCS = new CICOPCS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5340_AWA_5340 CIB5340_AWA_5340;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5340 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5340_AWA_5340>");
        CIB5340_AWA_5340 = (CIB5340_AWA_5340) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        CICOPCS.DATA.CI_NO = CIB5340_AWA_5340.CI_NO;
        CICOPCS.DATA.CI_TYP = CIB5340_AWA_5340.CI_TYP;
        CICOPCS.DATA.CI_ATTR = CIB5340_AWA_5340.CI_ATTR;
        CICOPCS.DATA.INT_TYP = CIB5340_AWA_5340.INT_TYP;
        if (CIB5340_AWA_5340.CI_NM1.trim().length() > 0) {
            CICOPCS.DATA.NM_TYPE1 = CIB5340_AWA_5340.NM_TYPE1;
            CICOPCS.DATA.CI_NM1 = CIB5340_AWA_5340.CI_NM1;
            CICOPCS.DATA.NM_TYPE2 = CIB5340_AWA_5340.NM_TYPE2;
            CICOPCS.DATA.CI_NM2 = CIB5340_AWA_5340.CI_NM2;
        } else {
            CICOPCS.DATA.NM_TYPE1 = CIB5340_AWA_5340.NM_TYPE2;
            CICOPCS.DATA.CI_NM1 = CIB5340_AWA_5340.CI_NM2;
        }
        CICOPCS.DATA.ID_TYPE = CIB5340_AWA_5340.ID_TYPE;
        CICOPCS.DATA.ID_NO = CIB5340_AWA_5340.ID_NO;
        CICOPCS.DATA.REMARK = CIB5340_AWA_5340.REMARK;
        CICOPCS.DATA.ID_RGN = CIB5340_AWA_5340.ID_RGN;
        CICOPCS.DATA.ID_EXPDT = CIB5340_AWA_5340.ID_EXPDT;
        CICOPCS.DATA.CORG = CIB5340_AWA_5340.CORG;
        CICOPCS.DATA.FIN_TYP = CIB5340_AWA_5340.FIN_TYP;
        CICOPCS.DATA.INDUS = CIB5340_AWA_5340.INDUS;
        S000_LINK_CIZOPCS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5340_AWA_5340.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5340_AWA_5340.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZOPCS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-SIMPLY", CICOPCS);
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
