package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPCDCD {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_DT_CHAR4 = " ";
    String WS_PIN_BLOCK = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCGWA SCCGWA;
    DCCPCDCD DCCPCDCD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCPCDCD DCCPCDCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPCDCD = DCCPCDCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZPCDCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCPCDCD.RC);
        IBS.init(SCCGWA, DCCPCDCD.OUTP_DATA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_INPUT_CHECK();
        if (pgmRtn) return;
        B100_GEN_SECRET_KEY();
        if (pgmRtn) return;
        B200_GEN_RANDOM_COD();
        if (pgmRtn) return;
        B300_LMK_CONVER_ZPK();
        if (pgmRtn) return;
        B400_ZPK_CONVER_PVK();
        if (pgmRtn) return;
    }
    public void B001_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPCDCD.INP_DATA.CARD_BRH);
        CEP.TRC(SCCGWA, DCCPCDCD.INP_DATA.CARD_TYPE);
        CEP.TRC(SCCGWA, DCCPCDCD.INP_DATA.CARD_NO);
        if (DCCPCDCD.INP_DATA.CARD_BRH == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPCDCD.INP_DATA.CARD_TYPE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPCDCD.INP_DATA.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_GEN_SECRET_KEY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        CEP.TRC(SCCGWA, "KEY VALUE");
        SCCHMPW.INP_DATA.SERV_ID = "E111";
        SCCHMPW.INP_DATA.TR_BR = DCCPCDCD.INP_DATA.CARD_BRH;
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_E111.OUT_KEYVALUE);
        DCCPCDCD.OUTP_DATA.SECRET_KEY = SCCHMPW.OUT_INFO.OUT_E111.OUT_KEYVALUE;
    }
    public void B200_GEN_RANDOM_COD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        CEP.TRC(SCCGWA, "PASSWORD");
        SCCHMPW.INP_DATA.SERV_ID = "O130";
        if (SCCHMPW.INP_DATA.NEW_AC == null) SCCHMPW.INP_DATA.NEW_AC = "";
        JIBS_tmp_int = SCCHMPW.INP_DATA.NEW_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) SCCHMPW.INP_DATA.NEW_AC += " ";
        if (DCCPCDCD.INP_DATA.CARD_NO == null) DCCPCDCD.INP_DATA.CARD_NO = "";
        JIBS_tmp_int = DCCPCDCD.INP_DATA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCD.INP_DATA.CARD_NO += " ";
        SCCHMPW.INP_DATA.NEW_AC = DCCPCDCD.INP_DATA.CARD_NO + SCCHMPW.INP_DATA.NEW_AC.substring(19);
        SCCHMPW.INP_DATA.TR_BR = DCCPCDCD.INP_DATA.CARD_BRH;
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.SERV_ID);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GENE PASSWORD OK");
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_E130.OUT_PINB);
        WS_PIN_BLOCK = SCCHMPW.OUT_INFO.OUT_E130.OUT_PINB;
    }
    public void B300_LMK_CONVER_ZPK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E142";
        SCCHMPW.INP_DATA.SERV_ID = "0001";
        SCCHMPW.INP_DATA.TR_BR = DCCPCDCD.INP_DATA.CARD_BRH;
        SCCHMPW.INP_DATA.PIN_DA = WS_PIN_BLOCK;
        SCCHMPW.INP_DATA.OLD_AC = DCCPCDCD.INP_DATA.CARD_NO;
        SCCHMPW.INP_DATA.NEW_AC = DCCPCDCD.INP_DATA.CARD_NO;
        CEP.TRC(SCCGWA, "PRINT PRINT PASSWROD");
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.SERV_ID);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        DCCPCDCD.OUTP_DATA.PRINT_PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
    }
    public void B400_ZPK_CONVER_PVK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E142";
        SCCHMPW.INP_DATA.SERV_ID = "0002";
        SCCHMPW.INP_DATA.TR_BR = DCCPCDCD.INP_DATA.CARD_BRH;
        SCCHMPW.INP_DATA.PIN_DA = DCCPCDCD.OUTP_DATA.PRINT_PSW;
        SCCHMPW.INP_DATA.OLD_AC = DCCPCDCD.INP_DATA.CARD_NO;
        SCCHMPW.INP_DATA.NEW_AC = DCCPCDCD.INP_DATA.CARD_NO;
        if (DCCPCDCD.INP_DATA.CARD_TYPE.equalsIgnoreCase("P")) {
            SCCHMPW.INP_DATA.AC_FLG = '0';
        } else if (DCCPCDCD.INP_DATA.CARD_TYPE.equalsIgnoreCase("C")) {
            SCCHMPW.INP_DATA.AC_FLG = '1';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCPCDCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "PRINT DEL PASSWROD");
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.SERV_ID);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, DCCPCDCD.INP_DATA.CARD_TYPE);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.AC_FLG);
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        DCCPCDCD.OUTP_DATA.DEL_PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
        IBS.CPY2CLS(SCCGWA, "SC0000", DCCPCDCD.RC);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD      ", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.SERV_ID);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if ((SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("009040") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001024") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001014"))) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_SIX_LEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001020")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDCVN_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
                    IBS.CPY2CLS(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE, DCCPCDCD.RC);
                    WS_ERR_MSG = SCCHMPW.OUT_INFO.ERR_CODE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
