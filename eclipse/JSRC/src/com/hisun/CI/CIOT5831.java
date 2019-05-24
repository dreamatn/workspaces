package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5831 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMLS1 CICMLS1 = new CICMLS1();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5830_AWA_5830 CIB5830_AWA_5830;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5831 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5830_AWA_5830>");
        CIB5830_AWA_5830 = (CIB5830_AWA_5830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMLS1);
        CICMLS1.DATA.INP_OUT_DATA.LST_CD = CIB5830_AWA_5830.LST_CD;
        CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP = CIB5830_AWA_5830.ENTY_TYP;
        CICMLS1.DATA.INP_OUT_DATA.ENTY_NO = CIB5830_AWA_5830.ENTY_NO;
        CICMLS1.DATA.INP_OUT_DATA.REL_CI = CIB5830_AWA_5830.REL_CI;
        CICMLS1.DATA.INP_OUT_DATA.REL_NM = CIB5830_AWA_5830.CI_NM;
        CICMLS1.DATA.INP_OUT_DATA.RSN = CIB5830_AWA_5830.RSN;
        CICMLS1.DATA.INP_OUT_DATA.DESC = CIB5830_AWA_5830.DESC;
        CEP.TRC(SCCGWA, CICMLS1.DATA.INP_OUT_DATA.LST_CD);
        CEP.TRC(SCCGWA, CICMLS1.DATA.INP_OUT_DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMLS1.DATA.INP_OUT_DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICMLS1.DATA.INP_OUT_DATA.REL_CI);
        CICMLS1.FUNC = 'A';
        S000_LINK_CIZMLS1();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5830_AWA_5830.LST_CD);
        CEP.TRC(SCCGWA, CIB5830_AWA_5830.ENTY_NO);
        CEP.TRC(SCCGWA, CIB5830_AWA_5830.ENTY_TYP);
        if (CIB5830_AWA_5830.LST_CD.trim().length() == 0 
            || CIB5830_AWA_5830.ENTY_NO.trim().length() == 0 
            || CIB5830_AWA_5830.ENTY_TYP == ' ') {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void S000_LINK_CIZMLS1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LS1", CICMLS1);
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
