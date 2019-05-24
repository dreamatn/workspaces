package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0220 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int K_MAX_JOIN_NUM = 10;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_NUM = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSRBAD DCCSRBAD = new DCCSRBAD();
    SCCGWA SCCGWA;
    DCB0220_AWA_0220 DCB0220_AWA_0220;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT0220 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0220_AWA_0220>");
        DCB0220_AWA_0220 = (DCB0220_AWA_0220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB0220_AWA_0220.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; (WS_I <= K_MAX_JOIN_NUM) 
            && (DCB0220_AWA_0220.E_DATA[WS_I-1].E_CARD.trim().length() != 0); WS_I += 1) {
            B011_CHECK_E_CARD_DATA();
        }
        CEP.TRC(SCCGWA, WS_I);
        WS_NUM = K_MAX_JOIN_NUM + 1 - WS_I;
        CEP.TRC(SCCGWA, WS_NUM);
        for (WS_J = 1; (WS_J <= K_MAX_JOIN_NUM) 
            && (DCB0220_AWA_0220.A_DATA[WS_J-1].A_CARD.trim().length() != 0); WS_J += 1) {
            B012_CHECK_A_CARD_DATA();
        }
        CEP.TRC(SCCGWA, WS_J);
        WS_J = WS_J - 1;
        CEP.TRC(SCCGWA, WS_J);
        if (WS_J > WS_NUM) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXCEED_MAX_JOIN_LMT;
            S000_ERR_MSG_PROC();
        }
        if (WS_J > 4) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ADD_ROBIN_CARD_OVER4;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_CHECK_E_CARD_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        if (WS_I < 3 
            && DCB0220_AWA_0220.E_DATA[WS_I-1].E_CARD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B012_CHECK_A_CARD_DATA() throws IOException,SQLException,Exception {
        if (WS_J < 2 
            && DCB0220_AWA_0220.A_DATA[WS_J-1].A_CARD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB0220_AWA_0220.A_DATA[WS_J-1].A_CARD.trim().length() > 0) {
            if (DCB0220_AWA_0220.A_DATA[WS_J-1].A_IDTYPE.trim().length() == 0 
                || DCB0220_AWA_0220.A_DATA[WS_J-1].A_IDNO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
                S000_ERR_MSG_PROC();
            }
            if (DCB0220_AWA_0220.A_DATA[WS_J-1].A_CINO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (DCB0220_AWA_0220.A_DATA[WS_J-1].A_FEE == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ANU_FREE_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSRBAD);
        DCCSRBAD.CARD_PROD = DCB0220_AWA_0220.PROD_CD;
        DCCSRBAD.PROD_NAME = DCB0220_AWA_0220.PROD_NM;
        for (WS_I = 1; (WS_I <= K_MAX_JOIN_NUM) 
            && (DCB0220_AWA_0220.E_DATA[WS_I-1].E_CARD.trim().length() != 0); WS_I += 1) {
            DCCSRBAD.E_DATA[WS_I-1].E_CARD_NO = DCB0220_AWA_0220.E_DATA[WS_I-1].E_CARD;
            DCCSRBAD.E_DATA[WS_I-1].E_ID_TYPE = DCB0220_AWA_0220.E_DATA[WS_I-1].E_IDTYPE;
            DCCSRBAD.E_DATA[WS_I-1].E_ID_NO = DCB0220_AWA_0220.E_DATA[WS_I-1].E_IDNO;
            DCCSRBAD.E_DATA[WS_I-1].E_CI_NO = DCB0220_AWA_0220.E_DATA[WS_I-1].E_CINO;
            DCCSRBAD.E_DATA[WS_I-1].E_CI_NM = DCB0220_AWA_0220.E_DATA[WS_I-1].E_CINM;
        }
        for (WS_J = 1; (WS_J <= K_MAX_JOIN_NUM) 
            && (DCB0220_AWA_0220.A_DATA[WS_J-1].A_CARD.trim().length() != 0); WS_J += 1) {
            DCCSRBAD.A_DATA[WS_J-1].A_CARD_NO = DCB0220_AWA_0220.A_DATA[WS_J-1].A_CARD;
            DCCSRBAD.A_DATA[WS_J-1].A_ID_TYPE = DCB0220_AWA_0220.A_DATA[WS_J-1].A_IDTYPE;
            DCCSRBAD.A_DATA[WS_J-1].A_ID_NO = DCB0220_AWA_0220.A_DATA[WS_J-1].A_IDNO;
            DCCSRBAD.A_DATA[WS_J-1].A_CI_NO = DCB0220_AWA_0220.A_DATA[WS_J-1].A_CINO;
            DCCSRBAD.A_DATA[WS_J-1].A_CI_NM = DCB0220_AWA_0220.A_DATA[WS_J-1].A_CINM;
            DCCSRBAD.A_DATA[WS_J-1].A_ANU_FEE_FREE = DCB0220_AWA_0220.A_DATA[WS_J-1].A_FEE;
        }
        S000_CALL_DCZSRBAD();
    }
    public void S000_CALL_DCZSRBAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSRBAD", DCCSRBAD);
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
