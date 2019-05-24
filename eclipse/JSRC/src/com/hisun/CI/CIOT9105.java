package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9105 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMCNT CICSMCNT = new CICSMCNT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9105_AWA_9105 CIB9105_AWA_9105;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9105 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9105_AWA_9105>");
        CIB9105_AWA_9105 = (CIB9105_AWA_9105) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCNT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_CNT_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9105_AWA_9105.CI_NO);
        if (CIB9105_AWA_9105.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_CNT_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB9105_AWA_9105.ADR_AREA[WS_I-1].FUNC != ' ') {
                IBS.init(SCCGWA, CICSMCNT);
                CICSMCNT.DATA.CI_NO = CIB9105_AWA_9105.CI_NO;
                CICSMCNT.FUNC = CIB9105_AWA_9105.ADR_AREA[WS_I-1].FUNC;
                CICSMCNT.DATA.CNT_TYPE = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_TYPE;
                CICSMCNT.DATA.CNT_CNTY = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_CNTY;
                CICSMCNT.DATA.CNT_TECN = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_TECN;
                CICSMCNT.DATA.CNT_ZONE = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_ZONE;
                CICSMCNT.DATA.CNT_TEL = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_TEL;
                CICSMCNT.DATA.CNT_TEL2 = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_TEL2;
                CICSMCNT.DATA.CNT_INFO = CIB9105_AWA_9105.ADR_AREA[WS_I-1].CNT_INFO;
                S000_CALL_CIZSMCNT();
            }
        }
    }
    public void S000_CALL_CIZSMCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-CNT-INF", CICSMCNT);
        if (CICSMCNT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMCNT.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
