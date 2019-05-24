package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5805 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMLS2 CICMLS2 = new CICMLS2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5800_AWA_5800 CIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5805 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5800_AWA_5800>");
        CIB5800_AWA_5800 = (CIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMLS2);
        CICMLS2.DATA.LST_CD = CIB5800_AWA_5800.LST_CD;
        CICMLS2.DATA.LST_SEQ = CIB5800_AWA_5800.LST_SEQ;
        CICMLS2.DATA.ID_TYPE = CIB5800_AWA_5800.ID_TYPE;
        CICMLS2.DATA.ID_NO = CIB5800_AWA_5800.ID_NO;
        CICMLS2.DATA.CI_CNM = CIB5800_AWA_5800.CI_CNM;
        CICMLS2.FUNC = 'D';
        S000_LINK_CIZMLS2();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.LST_CD);
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.LST_SEQ);
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.ID_NO);
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.ID_TYPE);
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.CI_CNM);
        CEP.TRC(SCCGWA, CIB5800_AWA_5800.CI_ENM);
        if (CIB5800_AWA_5800.LST_CD.trim().length() == 0 
            || CIB5800_AWA_5800.LST_SEQ == 0 
            || CIB5800_AWA_5800.ID_NO.trim().length() == 0 
            || CIB5800_AWA_5800.ID_TYPE.trim().length() == 0 
            || CIB5800_AWA_5800.CI_CNM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void S000_LINK_CIZMLS2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LST2", CICMLS2);
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
