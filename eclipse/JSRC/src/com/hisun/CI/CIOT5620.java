package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5620 {
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
        CEP.TRC(SCCGWA, "CIOT5620 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5620_AWA_5620>");
        CIB5620_AWA_5620 = (CIB5620_AWA_5620) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMALT);
        B010_CHECK_INPUT_DATA();
        CICMALT.DATA.ENTY_TYP = CIB5620_AWA_5620.ENTY_TYP;
        CICMALT.DATA.ENTY_NO = CIB5620_AWA_5620.ENTY_NO;
        CICMALT.DATA.ALT_TYP = CIB5620_AWA_5620.ALT_TYP;
        CICMALT.DATA.MSG_CHNL = CIB5620_AWA_5620.MSG_CHNL;
        CICMALT.DATA.EFF_DT = CIB5620_AWA_5620.EFF_DT;
        CICMALT.DATA.EXP_DT = CIB5620_AWA_5620.EXP_DT;
        CICMALT.FUNC = 'B';
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMALT.OPT);
        CEP.TRC(SCCGWA, CICMALT.DATA.MSG_CHNL);
        S000_LINK_CIZMALT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.ENTY_TYP);
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.ENTY_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.EFF_DT);
        CEP.TRC(SCCGWA, CIB5620_AWA_5620.EXP_DT);
        if ((CIB5620_AWA_5620.EXP_DT != 0) 
            && CIB5620_AWA_5620.EXP_DT < CIB5620_AWA_5620.EFF_DT) {
            CEP.TRC(SCCGWA, "ENTER AWA-EXP-DT < AWA-EFF-DT");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INVALID);
        }
        if (CIB5620_AWA_5620.ENTY_TYP == ' ') {
            CIB5620_AWA_5620.ENTY_TYP = '0';
        }
        if (CIB5620_AWA_5620.ENTY_NO.trim().length() == 0) {
            if (CIB5620_AWA_5620.ALT_TYP.trim().length() == 0) {
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
        } else {
            CICMALT.OPT = 'C';
        }
        if (CIB5620_AWA_5620.MSG_CHNL.trim().length() > 0 
            && CIB5620_AWA_5620.ENTY_NO.trim().length() > 0) {
            CICMALT.OPT = 'L';
        }
        if (CIB5620_AWA_5620.ALT_TYP.trim().length() > 0 
            && CIB5620_AWA_5620.ENTY_NO.trim().length() > 0) {
            CICMALT.OPT = 'T';
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
