package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3120 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUHDBR DCCUHDBR = new DCCUHDBR();
    SCCGWA SCCGWA;
    DCB3120_AWA_3120 DCB3120_AWA_3120;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3120 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3120_AWA_3120>");
        DCB3120_AWA_3120 = (DCB3120_AWA_3120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB3120_AWA_3120.QUERYTYP == '1') {
            if (DCB3120_AWA_3120.HLDRIDTP.trim().length() == 0 
                || DCB3120_AWA_3120.HLDRIDNO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB3120_AWA_3120.QUERYTYP == '2') {
            if (DCB3120_AWA_3120.HLDRCINO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB3120_AWA_3120.QUERYTYP == '3') {
            if (DCB3120_AWA_3120.OWNCINO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB3120_AWA_3120.QUERYTYP == '5') {
            if (DCB3120_AWA_3120.ECARD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_E_CARD);
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (DCB3120_AWA_3120.QUERYTYP != '5') {
            if (DCB3120_AWA_3120.LNKTYP == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_LNK_TYPE;
                S000_ERR_MSG_PROC();
            }
            if (DCB3120_AWA_3120.ADSCTYP == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_TYPE;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHDBR);
        DCCUHDBR.QUERY_TYP = DCB3120_AWA_3120.QUERYTYP;
        DCCUHDBR.CARD_HLDR_IDTYP = DCB3120_AWA_3120.HLDRIDTP;
        DCCUHDBR.CARD_HLDR_IDNO = DCB3120_AWA_3120.HLDRIDNO;
        DCCUHDBR.CARD_HLDR_CINM = DCB3120_AWA_3120.HLDRCINM;
        DCCUHDBR.CARD_HLDR_CINO = DCB3120_AWA_3120.HLDRCINO;
        DCCUHDBR.CARD_OWN_CINO = DCB3120_AWA_3120.OWNCINO;
        DCCUHDBR.CARD_LNK_TYP = DCB3120_AWA_3120.LNKTYP;
        DCCUHDBR.CARD_ADSC_TYP = DCB3120_AWA_3120.ADSCTYP;
        DCCUHDBR.E_CARD_NO = DCB3120_AWA_3120.ECARD_NO;
        DCCUHDBR.PAGE_ROW = DCB3120_AWA_3120.PAGE_ROW;
        DCCUHDBR.PAGE_NUM = DCB3120_AWA_3120.PAGE_NUM;
        S000_CALL_DCZUHDBR();
    }
    public void S000_CALL_DCZUHDBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUHDBR", DCCUHDBR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
