package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9107 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMREL CICSMREL = new CICSMREL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9107_AWA_9107 CIB9107_AWA_9107;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9107 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9107_AWA_9107>");
        CIB9107_AWA_9107 = (CIB9107_AWA_9107) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMREL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MOD_REL_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9107_AWA_9107.CI_NO);
        if (CIB9107_AWA_9107.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_MOD_REL_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (CIB9107_AWA_9107.ADR_AREA[WS_I-1].FUNC != ' ') {
                IBS.init(SCCGWA, CICSMREL);
                CICSMREL.DATA.CI_NO = CIB9107_AWA_9107.CI_NO;
                CICSMREL.FUNC = CIB9107_AWA_9107.ADR_AREA[WS_I-1].FUNC;
                CICSMREL.DATA.REL_RECD = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_RECD;
                CICSMREL.DATA.REL_NAME = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_NAME;
                CICSMREL.DATA.REL_IDTP = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_IDTP;
                CICSMREL.DATA.REL_IDNO = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_IDNO;
                CICSMREL.DATA.REL_IDEX = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_IDEX;
                CICSMREL.DATA.REL_CNTY = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_CNTY;
                CICSMREL.DATA.REL_MOB = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_MOB;
                CICSMREL.DATA.REL_TEL = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_TEL;
                CICSMREL.DATA.REL_SEX = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_SEX;
                CICSMREL.DATA.REL_ADCN = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_ADCN;
                CICSMREL.DATA.REL_ADDR = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_ADDR;
                CICSMREL.DATA.REL_OCNM = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_OCNM;
                CICSMREL.DATA.REL_BIRT = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_BIRT;
                CICSMREL.DATA.REL_OCCU = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_OCCU;
                CICSMREL.DATA.REL_HOLD = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_HOLD;
                CICSMREL.DATA.REL_SHDT = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_SHDT;
                CICSMREL.DATA.REL_NM1 = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_NM1;
                CICSMREL.DATA.REL_NM2 = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_NM2;
                CICSMREL.DATA.REL_RSDT = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_RSDT;
                CICSMREL.DATA.REL_BRCT = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_BRCT;
                CICSMREL.DATA.REL_FMCT = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_FMCT;
                CICSMREL.DATA.REL_FMAD = CIB9107_AWA_9107.ADR_AREA[WS_I-1].REL_FMAD;
                S000_CALL_CIZSMREL();
            }
        }
    }
    public void S000_CALL_CIZSMREL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-REL-INF", CICSMREL);
        if (CICSMREL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMREL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
