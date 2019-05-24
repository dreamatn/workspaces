package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5623 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMALT CICMALT = new CICMALT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5620_AWA_5620 CIB5620_AWA_5620;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5623 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5620_AWA_5620>");
        CIB5620_AWA_5620 = (CIB5620_AWA_5620) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        CICMALT.DATA.ALT_NO = CIB5620_AWA_5620.ALT_NO;
        CICMALT.DATA.ENTY_TYP = CIB5620_AWA_5620.ENTY_TYP;
        CICMALT.DATA.ENTY_NO = CIB5620_AWA_5620.ENTY_NO;
        CICMALT.DATA.ALT_TYP = CIB5620_AWA_5620.ALT_TYP;
        CICMALT.DATA.MSG_CHNL = CIB5620_AWA_5620.MSG_CHNL;
        CICMALT.DATA.ALT_LVL = CIB5620_AWA_5620.ALT_LVL;
        CICMALT.DATA.MAX_CNT = CIB5620_AWA_5620.MAX_CNT;
        CICMALT.DATA.EFF_DT = CIB5620_AWA_5620.EFF_DT;
        CICMALT.DATA.EXP_DT = CIB5620_AWA_5620.EXP_DT;
        CICMALT.DATA.REMARK = CIB5620_AWA_5620.REMARK;
        CICMALT.FUNC = 'M';
        S000_LINK_CIZMALT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5620_AWA_5620.ALT_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_NO_INPUT);
        }
        if (CIB5620_AWA_5620.ALT_TYP.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_TYP_INPUT);
        }
        if (CIB5620_AWA_5620.ALT_TYP.equalsIgnoreCase("0020")) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_MOD_TAX_SURVER);
        }
        if (CIB5620_AWA_5620.ENTY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_ENTY_NO_INPUT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.EFF_DT);
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.EXP_DT);
        if ((CIB5620_AWA_5620.EXP_DT != 0) 
            && (CIB5620_AWA_5620.EXP_DT < CIB5620_AWA_5620.EFF_DT 
            || CIB5620_AWA_5620.EXP_DT < SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INVALID);
        }
        CEP.ERR(SCCGWA);
    }
    public void S000_LINK_CIZMALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ALT", CICMALT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
