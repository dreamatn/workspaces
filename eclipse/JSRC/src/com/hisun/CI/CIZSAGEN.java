package com.hisun.CI;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSAGEN {
    DBParm CITAGENT_RD;
    brParm CITAGENT_BR = new brParm();
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIX01";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    int K_MAX_ROW = 25;
    int K_MAX_COL = 99;
    int K_COL_RSK = 5;
    String K_ID_CARD = "10100";
    String K_TX_CODE = "0115800";
    short K_MAX_CNT2 = 3;
    char K_SETT_FLG = 'T';
    CIZSAGEN_WS_ERR_MSG WS_ERR_MSG = new CIZSAGEN_WS_ERR_MSG();
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    char WS_DD_MST_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCKID CICCKID = new CICCKID();
    CICOAGEN CICOAGEN = new CICOAGEN();
    CICOAGE2 CICOAGE2 = new CICOAGE2();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CIRACR CIRACR = new CIRACR();
    CIRAGENT CIRAGENT = new CIRAGENT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICSAGEN CICSAGEN;
    public void MP(SCCGWA SCCGWA, CICSAGEN CICSAGEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSAGEN = CICSAGEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSAGEN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSAGEN.JRN_NO);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSAGEN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.FUNC == 'O') {
            B070_CHECK_RECORD_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.FUNC == 'S') {
            B090_INQUIRE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICSAGEN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSAGEN.ID_TYP);
        if (CICSAGEN.ID_TYP.equalsIgnoreCase(K_ID_CARD) 
            && CICSAGEN.FUNC == 'A') {
            IBS.init(SCCGWA, CICCKID);
            CICCKID.DATA.ID_NO = CICSAGEN.ID_NO;
            S000_LINK_CIZCKID();
            if (pgmRtn) return;
            if (CICCKID.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGENT_ID_CARD_ILLEGA);
            }
        }
        CEP.TRC(SCCGWA, CICSAGEN.AGENT_TP);
        if (CICSAGEN.AGENT_TP.trim().length() == 0 
            && CICSAGEN.FUNC == 'A') {
            CEP.TRC(SCCGWA, "AGENT-TP MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSAGEN.JRN_NO);
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.KEY.AC_DT = CICSAGEN.AC_DT;
        CIRAGENT.KEY.JRN_NO = CICSAGEN.JRN_NO;
        T000_READ_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGNET_REC_NOTFND);
        }
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSAGEN.AC_DT);
        CEP.TRC(SCCGWA, CICSAGEN.JRN_NO);
        CEP.TRC(SCCGWA, CICSAGEN.TX_CODE);
        CEP.TRC(SCCGWA, CICSAGEN.BR);
        CEP.TRC(SCCGWA, CICSAGEN.TLR_NO);
        CEP.TRC(SCCGWA, CICSAGEN.ID_TYP);
        CEP.TRC(SCCGWA, CICSAGEN.ID_NO);
        CEP.TRC(SCCGWA, CICSAGEN.CI_NAME);
        CEP.TRC(SCCGWA, CICSAGEN.PHONE);
        if (CICSAGEN.AC_DT == 0 
            || CICSAGEN.JRN_NO == 0 
            || CICSAGEN.TX_CODE.trim().length() == 0 
            || CICSAGEN.BR == 0 
            || CICSAGEN.TLR_NO.trim().length() == 0 
            || CICSAGEN.ID_TYP.trim().length() == 0 
            || CICSAGEN.ID_NO.trim().length() == 0 
            || CICSAGEN.CI_NAME.trim().length() == 0 
            || CICSAGEN.PHONE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CICSAGEN.CI_NO.trim().length() == 0 
            && CICSAGEN.OUT_AC.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO AND OUT-AC MUST INPUT ONE");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.KEY.AC_DT = CICSAGEN.AC_DT;
        CIRAGENT.KEY.JRN_NO = CICSAGEN.JRN_NO;
        T000_READ_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            R000_TRANS_DATA_TO_TBL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRAGENT.KEY.AC_DT);
            CEP.TRC(SCCGWA, CIRAGENT.KEY.JRN_NO);
            CEP.TRC(SCCGWA, CIRAGENT.TX_CODE);
            CEP.TRC(SCCGWA, CIRAGENT.BR);
            CEP.TRC(SCCGWA, CIRAGENT.TLR_NO);
            CEP.TRC(SCCGWA, CIRAGENT.CI_NO);
            CEP.TRC(SCCGWA, CIRAGENT.OUT_AC);
            CEP.TRC(SCCGWA, CIRAGENT.AGENT_TP);
            CEP.TRC(SCCGWA, CIRAGENT.CIREL_CD);
            CEP.TRC(SCCGWA, CIRAGENT.ID_TYP);
            CEP.TRC(SCCGWA, CIRAGENT.ID_NO);
            CEP.TRC(SCCGWA, CIRAGENT.CI_NM);
            CEP.TRC(SCCGWA, CIRAGENT.SEX);
            CEP.TRC(SCCGWA, CIRAGENT.PHONE);
            CEP.TRC(SCCGWA, CIRAGENT.CNTY);
            CEP.TRC(SCCGWA, CIRAGENT.BIRTH_DT);
            CEP.TRC(SCCGWA, CIRAGENT.FULL_ADDR);
            CEP.TRC(SCCGWA, CIRAGENT.PREM_ADDR);
            CEP.TRC(SCCGWA, CIRAGENT.ID_EXP_DT);
            CEP.TRC(SCCGWA, CIRAGENT.OCCP);
            CEP.TRC(SCCGWA, CIRAGENT.REMARK);
            CEP.TRC(SCCGWA, CIRAGENT);
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AGENT INFO EXIST");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (CICSAGEN.OPT == 'I') {
            B060_10_ID_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.OPT == 'A') {
            B060_20_AC_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.OPT == 'C') {
            B060_30_CI_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.OPT == 'J') {
            B060_40_JR_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSAGEN.OPT == 'B') {
            B060_50_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + CICSAGEN.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_ID_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.ID_TYP = CICSAGEN.ID_TYP;
        CIRAGENT.ID_NO = CICSAGEN.ID_NO;
        CIRAGENT.CI_NM = CICSAGEN.CI_NAME;
        T000_STARTBR_CITAGENT_ID();
        if (pgmRtn) return;
        B060_60_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_AC_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.OUT_AC = CICSAGEN.OUT_AC;
        T000_STARTBR_CITAGENT_AC();
        if (pgmRtn) return;
        B060_60_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_CI_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.CI_NO = CICSAGEN.CI_NO;
        T000_STARTBR_CITAGENT_CI();
        if (pgmRtn) return;
        B060_60_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_JR_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CEP.TRC(SCCGWA, CICSAGEN.AC_DT);
        CEP.TRC(SCCGWA, CICSAGEN.JRN_NO);
        CIRAGENT.KEY.AC_DT = CICSAGEN.AC_DT;
        CIRAGENT.KEY.JRN_NO = CICSAGEN.JRN_NO;
        T000_STARTBR_CITAGENT_JR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, CIRAGENT);
        B060_60_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        T000_STARTBR_CITAGENT_IDNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, CIRAGENT);
        B060_60_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_60_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_CITAGENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "2");
        CEP.TRC(SCCGWA, CIRAGENT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRAGENT.KEY.AC_DT);
        CEP.TRC(SCCGWA, CICSAGEN.START_DT);
        CEP.TRC(SCCGWA, CICSAGEN.LAST_DT);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITAGENT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGENT();
        if (pgmRtn) return;
    }
    public void B070_CHECK_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.ID_TYP = CICSAGEN.ID_TYP;
        CIRAGENT.ID_NO = CICSAGEN.ID_NO;
        CIRAGENT.CI_NM = CICSAGEN.CI_NAME;
        T000_STARTBR_CITAGENT_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITAGENT();
        if (pgmRtn) return;
        WS_CNT1 = 0;
        WS_CNT2 = 0;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRAGENT.TX_CODE);
            CEP.TRC(SCCGWA, CICSAGEN.TX_CODE);
            if (CIRAGENT.TX_CODE.equalsIgnoreCase(CICSAGEN.TX_CODE) 
                && CIRAGENT.OUT_AC.trim().length() > 0) {
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.FUNC = '1';
                DCCPACTY.INPUT.AC = CIRAGENT.OUT_AC;
                S000_CALL_DCZPACTY();
                if (pgmRtn) return;
                if (DCCPACTY.RC.RC_CODE == 0 
                    && DCCPACTY.OUTPUT.STD_AC.trim().length() > 0) {
                    IBS.init(SCCGWA, DDCIMMST);
                    DDCIMMST.TX_TYPE = 'I';
                    DDCIMMST.DATA.KEY.AC_NO = DCCPACTY.OUTPUT.STD_AC;
                    S000_CALL_DDZIMMST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
                    if (WS_DD_MST_FLG == 'F' 
                        && DDCIMMST.DATA.AC_TYPE == K_SETT_FLG) {
                        WS_CNT1 = (short) (WS_CNT1 + 1);
                        CEP.TRC(SCCGWA, WS_CNT1);
                        if (CIRAGENT.KEY.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
                            WS_CNT2 = (short) (WS_CNT2 + 1);
                        }
                    }
                }
            }
            T000_READNEXT_CITAGENT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGENT();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_RSK;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        R000_DATA_TRANS_TO_BRW();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOAGE2);
        SCCMPAG.DATA_LEN = 452;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B090_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.OUT_AC = CICSAGEN.OUT_AC;
        CIRAGENT.AGENT_TP = CICSAGEN.AGENT_TP;
        T000_READ_CITAGENT_BY_AC_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NO RECORD FOUND");
            CICSAGEN.FOUND_FLG = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            CICSAGEN.FOUND_FLG = 'Y';
        }
        CICSAGEN.AC_DT = CIRAGENT.KEY.AC_DT;
        CICSAGEN.JRN_NO = CIRAGENT.KEY.JRN_NO;
        CICSAGEN.TX_CODE = CIRAGENT.TX_CODE;
        CICSAGEN.BR = CIRAGENT.BR;
        CICSAGEN.TLR_NO = CIRAGENT.TLR_NO;
        CICSAGEN.CI_NO = CIRAGENT.CI_NO;
        CICSAGEN.AGE_CINO = CIRAGENT.AGENT_CI_NO;
        CICSAGEN.OUT_AC = CIRAGENT.OUT_AC;
        CICSAGEN.CIREL_CD = CIRAGENT.CIREL_CD;
        CICSAGEN.ID_NO = CIRAGENT.ID_NO;
        CICSAGEN.AGENT_TP = CIRAGENT.AGENT_TP;
        CICSAGEN.CI_NAME = CIRAGENT.CI_NM;
        CICSAGEN.SEX = CIRAGENT.SEX;
        CICSAGEN.PHONE = CIRAGENT.PHONE;
        CICSAGEN.BIRTH_DT = CIRAGENT.BIRTH_DT;
        CICSAGEN.REMARK = CIRAGENT.REMARK;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGENT);
        CIRAGENT.KEY.AC_DT = CICSAGEN.AC_DT;
        CIRAGENT.KEY.JRN_NO = CICSAGEN.JRN_NO;
        CIRAGENT.TX_CODE = CICSAGEN.TX_CODE;
        CIRAGENT.BR = CICSAGEN.BR;
        CIRAGENT.TLR_NO = CICSAGEN.TLR_NO;
        if (CICSAGEN.CI_NO.trim().length() > 0) {
            CIRAGENT.CI_NO = CICSAGEN.CI_NO;
        } else {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSAGEN.OUT_AC;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CIRAGENT.CI_NO = CIRACR.CI_NO;
        }
        CIRAGENT.AGENT_CI_NO = CICSAGEN.AGE_CINO;
        CIRAGENT.AGENT_TP = CICSAGEN.AGENT_TP;
        CIRAGENT.OUT_AC = CICSAGEN.OUT_AC;
        CIRAGENT.CIREL_CD = CICSAGEN.CIREL_CD;
        CIRAGENT.ID_TYP = CICSAGEN.ID_TYP;
        CIRAGENT.ID_NO = CICSAGEN.ID_NO;
        CIRAGENT.CI_NM = CICSAGEN.CI_NAME;
        CIRAGENT.SEX = CICSAGEN.SEX;
        CIRAGENT.PHONE = CICSAGEN.PHONE;
        CIRAGENT.CNTY = CICSAGEN.CNTY;
        CIRAGENT.BIRTH_DT = CICSAGEN.BIRTH_DT;
        CIRAGENT.REMARK = CICSAGEN.REMARK;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOAGEN);
        CICOAGEN.AC_DT = CIRAGENT.KEY.AC_DT;
        CICOAGEN.JRN_NO = CIRAGENT.KEY.JRN_NO;
        CICOAGEN.TX_CODE = CIRAGENT.TX_CODE;
        CICOAGEN.BR = CIRAGENT.BR;
        CICOAGEN.TLR_NO = CIRAGENT.TLR_NO;
        CICOAGEN.CI_NO = CIRAGENT.CI_NO;
        CICOAGEN.AGENT_CI_NO = CIRAGENT.AGENT_CI_NO;
        CICOAGEN.AGENT_TP = CIRAGENT.AGENT_TP;
        CICOAGEN.OUT_AC = CIRAGENT.OUT_AC;
        CICOAGEN.CIREL_CD = CIRAGENT.CIREL_CD;
        CICOAGEN.ID_TYP = CIRAGENT.ID_TYP;
        CICOAGEN.ID_NO = CIRAGENT.ID_NO;
        CICOAGEN.CI_NAME = CIRAGENT.CI_NM;
        CICOAGEN.SEX = CIRAGENT.SEX;
        CICOAGEN.PHONE = CIRAGENT.PHONE;
        CICOAGEN.CNTY = CIRAGENT.CNTY;
        CICOAGEN.BIRTH_DT = CIRAGENT.BIRTH_DT;
        CICOAGEN.REMARK = CIRAGENT.REMARK;
    }
    public void R000_DATA_TRANS_TO_BRW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOAGE2);
        CICOAGE2.AC_DT = CIRAGENT.KEY.AC_DT;
        CICOAGE2.JRN_NO = CIRAGENT.KEY.JRN_NO;
        CICOAGE2.TX_CODE = CIRAGENT.TX_CODE;
        CICOAGE2.BR = CIRAGENT.BR;
        CICOAGE2.TLR_NO = CIRAGENT.TLR_NO;
        CICOAGE2.CI_NO = CIRAGENT.CI_NO;
        CICOAGE2.AGENT_CI_NO = CIRAGENT.AGENT_CI_NO;
        CICOAGE2.AGENT_TP = CIRAGENT.AGENT_TP;
        CICOAGE2.OUT_AC = CIRAGENT.OUT_AC;
        CICOAGE2.CIREL_CD = CIRAGENT.CIREL_CD;
        CICOAGE2.ID_TYP = CIRAGENT.ID_TYP;
        CICOAGE2.ID_NO = CIRAGENT.ID_NO;
        CICOAGE2.CI_NAME = CIRAGENT.CI_NM;
        CICOAGE2.SEX = CIRAGENT.SEX;
        CICOAGE2.PHONE = CIRAGENT.PHONE;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICOAGEN;
        SCCFMT.DATA_LEN = 839;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        WS_DD_MST_FLG = ' ';
        if (DDCIMMST.RC.RC_CODE == 0) {
            WS_DD_MST_FLG = 'F';
        } else {
            if (DDCIMMST.RC.RC_CODE == DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND) {
                WS_DD_MST_FLG = 'N';
            } else {
                WS_ERR_MSG.WS_RC_MMO = DDCIMMST.RC.RC_MMO;
                WS_ERR_MSG.WS_RC_CODE = DDCIMMST.RC.RC_CODE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        CITAGENT_RD = new DBParm();
        CITAGENT_RD.TableName = "CITAGENT";
        IBS.READ(SCCGWA, CIRAGENT, CITAGENT_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        CITAGENT_RD = new DBParm();
        CITAGENT_RD.TableName = "CITAGENT";
        IBS.WRITE(SCCGWA, CIRAGENT, CITAGENT_RD);
    }
    public void T000_STARTBR_CITAGENT_ID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRAGENT.ID_NO);
        CEP.TRC(SCCGWA, CIRAGENT.ID_TYP);
        CEP.TRC(SCCGWA, CIRAGENT.CI_NM);
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.where = "ID_NO = :CIRAGENT.ID_NO "
            + "AND CI_NM = :CIRAGENT.CI_NM "
            + "AND ID_TYP = :CIRAGENT.ID_TYP";
        CITAGENT_BR.rp.order = "AC_DT";
        IBS.STARTBR(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_STARTBR_CITAGENT_AC() throws IOException,SQLException,Exception {
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.where = "OUT_AC = :CIRAGENT.OUT_AC";
        CITAGENT_BR.rp.order = "AC_DT";
        IBS.STARTBR(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_STARTBR_CITAGENT_CI() throws IOException,SQLException,Exception {
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.where = "CI_NO = :CIRAGENT.CI_NO";
        CITAGENT_BR.rp.order = "AC_DT";
        IBS.STARTBR(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_STARTBR_CITAGENT_JR() throws IOException,SQLException,Exception {
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.where = "JRN_NO = :CIRAGENT.KEY.JRN_NO "
            + "AND AC_DT = :CIRAGENT.KEY.AC_DT";
        CITAGENT_BR.rp.order = "AC_DT";
        IBS.STARTBR(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_STARTBR_CITAGENT_IDNO() throws IOException,SQLException,Exception {
        CITAGENT_BR.rp = new DBParm();
        CITAGENT_BR.rp.TableName = "CITAGENT";
        CITAGENT_BR.rp.where = "ID_NO = :CIRAGENT.ID_NO";
        CITAGENT_BR.rp.order = "AC_DT";
        IBS.STARTBR(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_READNEXT_CITAGENT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGENT, this, CITAGENT_BR);
    }
    public void T000_ENDBR_CITAGENT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGENT_BR);
    }
    public void T000_READ_CITAGENT_BY_AC_FIRST() throws IOException,SQLException,Exception {
        CITAGENT_RD = new DBParm();
        CITAGENT_RD.TableName = "CITAGENT";
        CITAGENT_RD.eqWhere = "OUT_AC , AGENT_TP";
        CITAGENT_RD.fst = true;
        IBS.READ(SCCGWA, CIRAGENT, CITAGENT_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void S000_LINK_CIZCKID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-ID-CHECK ", CICCKID);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICSAGEN=");
            CEP.TRC(SCCGWA, CICSAGEN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
