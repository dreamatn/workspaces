package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5402 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMID CICMID = new CICMID();
    CICMRELN CICMRELN = new CICMRELN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5400_AWA_5400 CIB5400_AWA_5400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5402 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5400_AWA_5400>");
        CIB5400_AWA_5400 = (CIB5400_AWA_5400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            if (CIB5400_AWA_5400.ID_GS[WS_I-1].ID_NOS.trim().length() > 0 
                && CIB5400_AWA_5400.ID_GS[WS_I-1].ID_TYPS.trim().length() > 0) {
                if (CIB5400_AWA_5400.ID_GS[WS_I-1].REL_FLG == 'N') {
                    IBS.init(SCCGWA, CICMID);
                    CICMID.DATA.CI_NO = CIB5400_AWA_5400.CI_NO;
                    CICMID.DATA.SINGLE_DATA.S_ID_TYPE = CIB5400_AWA_5400.ID_GS[WS_I-1].ID_TYPS;
                    CICMID.FUNC = 'I';
                    S000_LINK_CIZMID();
                    if (!CIB5400_AWA_5400.ID_GS[WS_I-1].ID_NOS.equalsIgnoreCase(CICMID.DATA.SINGLE_DATA.S_ID_NO)) {
                        WS_MSGID = CICMSG_ERROR_MSG.CI_ID_NO_DIFFERENT;
                        WS_ERR_INFO = CIB5400_AWA_5400.ID_GS[WS_I-1].ID_NOS;
                        S000_ERR_MSG_PROC();
                    }
                    CICMID.DATA.SINGLE_DATA.S_EXP_DT = CIB5400_AWA_5400.ID_GS[WS_I-1].EXP_DTS;
                    CICMID.FUNC = 'M';
                    S000_LINK_CIZMID();
                } else {
                    IBS.init(SCCGWA, CICMRELN);
                    CICMRELN.DATA.CI_NO = CIB5400_AWA_5400.CI_NO;
                    CICMRELN.DATA.REL_CD = CIB5400_AWA_5400.ID_GS[WS_I-1].REL_CD;
                    CICMRELN.FUNC = 'I';
                    S000_LINK_CIZMRELN();
                    if (!CIB5400_AWA_5400.ID_GS[WS_I-1].ID_NOS.equalsIgnoreCase(CICMRELN.DATA.RCI_IDNO)) {
                        WS_MSGID = CICMSG_ERROR_MSG.CI_ID_NO_DIFFERENT;
                        WS_ERR_INFO = CIB5400_AWA_5400.ID_GS[WS_I-1].ID_NOS;
                        S000_ERR_MSG_PROC();
                    }
                    CICMRELN.DATA.RCI_ID_EXP = CIB5400_AWA_5400.ID_GS[WS_I-1].EXP_DTS;
                    CICMRELN.FUNC = 'M';
                    S000_LINK_CIZMRELN();
                }
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void S000_LINK_CIZMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ID", CICMID);
        if (CICMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMID.RC);
        }
    }
    public void S000_LINK_CIZMRELN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-RELN", CICMRELN);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
