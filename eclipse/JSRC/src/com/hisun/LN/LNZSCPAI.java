package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCPAI {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    LNCXP13_LIST LIST;
    brParm LNTPAIP_BR = new brParm();
    DBParm LNTPAIP_RD;
    DBParm LNTPLPI_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "LNP13";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_ENTER = 'E';
    char K_CONFIRM = 'F';
    LNZSCPAI_WS_MSGID WS_MSGID = new LNZSCPAI_WS_MSGID();
    long WS_SEQ_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_PHS_NO = 0;
    short WS_PHS_NO1 = 0;
    int WS_I = 0;
    int WS_L = 0;
    double WS_TOT_SCH_AMT = 0;
    short WS_TOT_SCH_TERM = 0;
    short WS_PHS_TOT_TERM = 0;
    short WS_TOT_TENORS = 0;
    int WS_SCH_END_DT = 0;
    char WS_CHG_FLG = ' ';
    short WS_IDX = 0;
    int WS_SUB_CTA_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRTMPP LNCRTMPP = new LNCRTMPP();
    LNCSTMPP LNCSTMPP = new LNCSTMPP();
    LNCSPAIP LNCSPAIP = new LNCSPAIP();
    LNCTMPP1 LNCTMPP1 = new LNCTMPP1();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTMPPB LNCTMPPB = new LNCTMPPB();
    LNCXP13 LNCXP13 = new LNCXP13();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCCLOAN LNCCLOAN = new LNCCLOAN();
    LNCSCALT LNCSCALT = new LNCSCALT();
    LNCCNEX LNCCNEX = new LNCCNEX();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSCPAI LNCSCPAI;
    public void MP(SCCGWA SCCGWA, LNCSCPAI LNCSCPAI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCPAI = LNCSCPAI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCPAI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, LNRPAIP);
        LNCSCPAI.RC.RC_APP = "LN";
        LNCSCPAI.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_UPD_PTR_PROCESS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0137228")) {
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCPAI.REC_DATA.CONTRACT_NO);
        if (LNCSCPAI.REC_DATA.CONTRACT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_ERR_INFO = LNCSCPAI.REC_DATA.CONTRACT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCPAI.REC_DATA.TRAN_SEQ == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_ERR_INFO = "" + LNCSCPAI.REC_DATA.TRAN_SEQ;
            JIBS_tmp_int = WS_ERR_INFO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) WS_ERR_INFO = "0" + WS_ERR_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLOAN);
        LNCCLOAN.COMM_DATA.LN_AC = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCCLOAN.COMM_DATA.CHECK_TYP = K_CONFIRM;
        S000_CALL_FUN_LNZCLOAN();
        if (pgmRtn) return;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_TOT_TENORS = 1;
        WS_SCH_END_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
        S000_CALL_LNZTMPPB();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= LNCTMPPB.COMM_DATA.PHS_CNT; WS_I += 1) {
            if (LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].ACTION != 'C' 
                && LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].ACTION != 'D') {
                WS_TOT_SCH_AMT += LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_PRIN_AMT;
                WS_TOT_SCH_TERM += LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_TOT_TERM;
            }
        }
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
        CEP.TRC(SCCGWA, WS_TOT_SCH_AMT);
        CEP.TRC(SCCGWA, LNRCONT.LN_TOT_AMT);
        CEP.TRC(SCCGWA, WS_TOT_SCH_TERM);
        CEP.TRC(SCCGWA, WS_TOT_TENORS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0133300")) {
            if (WS_TOT_SCH_AMT != LNCCNEX.COMM_DATA.INQ_AMT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_TOT_AMT_N_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCICTLM);
        IBS.init(SCCGWA, LNRICTL);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        B10_GET_TOT_TERM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
        CEP.TRC(SCCGWA, WS_TOT_SCH_TERM);
    }
    public void B10_GET_TOT_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCALT);
        LNCSCALT.INPUT.VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        LNCSCALT.INPUT.DUE_DT = LNRCONT.MAT_DATE;
        LNCSCALT.INPUT.CAL_FST_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        LNCSCALT.INPUT.PHS_FLG = 'Y';
        LNCSCALT.INPUT.PHS_NUM = (short) LNCTMPPB.COMM_DATA.PHS_CNT;
        CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.PHS_DATA[1-1].PERD);
        CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.PHS_DATA[1-1].PERD_UNIT);
        LNCSCALT.INPUT.CAL_PERD = LNCTMPPB.COMM_DATA.PHS_DATA[1-1].PERD;
        LNCSCALT.INPUT.CAL_PERD_UNIT = LNCTMPPB.COMM_DATA.PHS_DATA[1-1].PERD_UNIT;
        LNCSCALT.INPUT.CAL_FST_FLG = LNCAPRDM.REC_DATA.FST_PAY_FLG;
        for (WS_IDX = 1; WS_IDX <= LNCTMPPB.COMM_DATA.PHS_CNT; WS_IDX += 1) {
            WS_L += 1;
            LNCSCALT.INPUT.PHS_DATA[WS_L-1].PHS_TOT_TERM = LNCTMPPB.COMM_DATA.PHS_DATA[WS_IDX-1].PHS_TOT_TERM;
            LNCSCALT.INPUT.PHS_DATA[WS_L-1].PHS_PERU = LNCTMPPB.COMM_DATA.PHS_DATA[WS_IDX-1].PERD_UNIT;
            LNCSCALT.INPUT.PHS_DATA[WS_L-1].PHS_PERD = LNCTMPPB.COMM_DATA.PHS_DATA[WS_IDX-1].PERD;
            if (WS_L >= 5) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.TMPP_TERM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        S000_CALL_LNZSCALT();
        if (pgmRtn) return;
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTMPP);
        IBS.init(SCCGWA, LNRTMPP);
        IBS.init(SCCGWA, LNCSPAIP);
        CEP.TRC(SCCGWA, LNCSCPAI.REC_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCSCPAI.REC_DATA.CONTRACT_NO);
        LNRTMPP.KEY.TRAN_SEQ = LNCSCPAI.REC_DATA.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCRTMPP.FUNC = 'B';
        LNCRTMPP.OPT = 'S';
        LNCRTMPP.REC_LEN = 208;
        LNCRTMPP.REC_PTR = LNRTMPP;
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        if (LNCRTMPP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTMPP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCRTMPP.OPT = 'R';
        WS_CHG_FLG = 'N';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        WS_PHS_NO = 0;
        LNCXP13.CNT = 0;
        LIST = new LNCXP13_LIST();
        LNCXP13.LIST.add(LIST);
        LNRPAIP.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        D000_DELETE_PAIP_RECORD();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRTMPP.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND)) {
            CEP.TRC(SCCGWA, WS_PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.ACTION);
            if (LNRTMPP.ACTION == 'A'
                || LNRTMPP.ACTION == 'M') {
                LNRTMPP.KEY.PHS_NO -= WS_PHS_NO;
                if (LNRTMPP.KEY.PHS_NO > 5) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_PHS_NUM_C_NOT_EXCD_5;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                    if (JIBS_tmp_str[0].equalsIgnoreCase("0133000")) {
                        R000_MOVE_TMPP_TO_PAIP();
                        if (pgmRtn) return;
                        LNCSPAIP.FUNC = '1';
                        S000_CALL_LNZSPAIP();
                        if (pgmRtn) return;
                    }
                    R000_MOVE_TMPP_TO_PAIP();
                    if (pgmRtn) return;
                    LNCSPAIP.FUNC = '0';
                    S000_CALL_LNZSPAIP();
                    if (pgmRtn) return;
                }
            } else if (LNRTMPP.ACTION == 'U') {
                if (WS_PHS_NO != 0) {
                    R000_CHANGE_PHASE();
                    if (pgmRtn) return;
                } else {
                    R000_MOVE_TMPP_TO_PAIP();
                    if (pgmRtn) return;
                    LNCSPAIP.FUNC = '2';
                    S000_CALL_LNZSPAIP();
                    if (pgmRtn) return;
                }
            } else if (LNRTMPP.ACTION == 'D') {
                R000_MOVE_TMPP_TO_PAIP();
                if (pgmRtn) return;
                LNCSPAIP.FUNC = '1';
                S000_CALL_LNZSPAIP();
                if (pgmRtn) return;
                WS_PHS_NO += 1;
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0137228")) {
                B320_MOVE_DETAIL();
                if (pgmRtn) return;
            }
            LNCRTMPP.OPT = 'R';
            S000_CALL_LNZTMPPL();
            if (pgmRtn) return;
        }
        LNCRTMPP.OPT = 'E';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        if (WS_CHG_FLG == 'N') {
        } else {
            IBS.init(SCCGWA, LNCICTLM);
            IBS.init(SCCGWA, LNRICTL);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            S000_DEL_PLPI_PROC();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.IC_CMP_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
            LNCICTLM.REC_DATA.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            LNCICTLM.FUNC = '2';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B310_MOVE_HEAD();
        if (pgmRtn) return;
        B330_OUTPUT();
        if (pgmRtn) return;
    }
    public void B310_MOVE_HEAD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCPAI.REC_DATA.CONTRACT_NO);
        LNCXP13.AC = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCXP13.TRAN_SEQ = (short) LNCSCPAI.REC_DATA.TRAN_SEQ;
    }
    public void B320_MOVE_DETAIL() throws IOException,SQLException,Exception {
        LNCXP13.CNT += 1;
        LIST = new LNCXP13_LIST();
        LNCXP13.LIST.add(LIST);
        LIST.LIST_DATA.PERD = LNRTMPP.PERD;
        LIST.LIST_DATA.PERD_UNIT = LNRTMPP.PERD_UNIT;
        LIST.LIST_DATA.SEQ = LNRTMPP.KEY.PHS_NO;
        LIST.LIST_DATA.PRINCIPAL = LNRTMPP.PHS_PRIN_AMT;
        LIST.LIST_DATA.REM_PRIN = LNRTMPP.PHS_REM_PRIN_AMT;
        LIST.LIST_DATA.TOT_TERM = LNRTMPP.PHS_TOT_TERM;
        LIST.LIST_DATA.MTH = LNRTMPP.INST_MTH;
        LIST.LIST_DATA.INST_AMT = LNRTMPP.CUR_INST_AMT;
        if (LNRTMPP.ACTION == 'A' 
            || LNRTMPP.ACTION == 'M') {
            LIST.LIST_DATA.ACTION = "ADD";
        }
        if (LNRTMPP.ACTION == 'U') {
            LIST.LIST_DATA.ACTION = "UPDATE";
        }
        if (LNRTMPP.ACTION == 'D') {
            LIST.LIST_DATA.ACTION = "DELETE";
        }
        if (LNRTMPP.ACTION == 'I') {
            LIST.LIST_DATA.ACTION = " ";
        }
        CEP.TRC(SCCGWA, LNCXP13.CNT);
        if (LNCXP13.CNT > 180) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5743;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            LNCXP13.CNT = 180;
            LIST = new LNCXP13_LIST();
            LNCXP13.LIST.add(LIST);
        }
    }
    public void B330_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, LNCXP13.CNT);
        SCCFMT.DATA_PTR = LNCXP13;
        SCCFMT.DATA_LEN = 12470;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void D000_DELETE_PAIP_RECORD() throws IOException,SQLException,Exception {
        B2100_STARTBR_LNTPAIP();
        if (pgmRtn) return;
        B2200_READNEXT_LNTPAIP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B2700_01_READUP_LNTPLPI();
            if (pgmRtn) return;
            B2730_01_DELETE_LNTPAIP();
            if (pgmRtn) return;
            B2200_READNEXT_LNTPAIP();
            if (pgmRtn) return;
        }
        B2300_ENDBR_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B2100_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.eqWhere = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPAIP, LNTPAIP_BR);
    }
    public void B2200_READNEXT_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
    }
    public void B2700_01_READUP_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.upd = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void B2300_ENDBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void B2730_01_DELETE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.DELETE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void R000_CHANGE_PHASE() throws IOException,SQLException,Exception {
        R000_MOVE_TMPP_TO_PAIP();
        if (pgmRtn) return;
        LNCSPAIP.FUNC = '1';
        S000_CALL_LNZSPAIP();
        if (pgmRtn) return;
        LNRTMPP.KEY.PHS_NO -= WS_PHS_NO;
        R000_MOVE_TMPP_TO_PAIP();
        if (pgmRtn) return;
        LNCSPAIP.FUNC = '0';
        S000_CALL_LNZSPAIP();
        if (pgmRtn) return;
    }
    public void R000_MOVE_TMPP_TO_PAIP() throws IOException,SQLException,Exception {
        WS_CHG_FLG = 'Y';
        CEP.TRC(SCCGWA, WS_PHS_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNRTMPP.INST_MTH);
        CEP.TRC(SCCGWA, LNRTMPP.PERD);
        CEP.TRC(SCCGWA, LNRTMPP.PERD_UNIT);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_INST_AMT);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, LNRTMPP.PHS_CMP_TERM);
        CEP.TRC(SCCGWA, LNRTMPP.CUR_INST_AMT);
        CEP.TRC(SCCGWA, LNRTMPP.CUR_INST_IRAT);
        IBS.init(SCCGWA, LNCSPAIP);
        LNCSPAIP.REC_DATA.KEY.LN_AC = LNRTMPP.KEY.CONTRACT_NO;
        LNCSPAIP.REC_DATA.KEY.SUF_NO = LNRTMPP.KEY.SUB_CTA_NO;
        LNCSPAIP.REC_DATA.KEY.PHS_NO = LNRTMPP.KEY.PHS_NO;
        LNCSPAIP.REC_DATA.INST_MTH = LNRTMPP.INST_MTH;
        LNCSPAIP.REC_DATA.PERD = LNRTMPP.PERD;
        LNCSPAIP.REC_DATA.PERD_UNIT = LNRTMPP.PERD_UNIT;
        LNCSPAIP.REC_DATA.PHS_INST_AMT = LNRTMPP.PHS_INST_AMT;
        LNCSPAIP.REC_DATA.PHS_PRIN_AMT = LNRTMPP.PHS_PRIN_AMT;
        LNCSPAIP.REC_DATA.PHS_TOT_TERM = LNRTMPP.PHS_TOT_TERM;
        LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT = LNRTMPP.PHS_REM_PRIN_AMT;
        LNCSPAIP.REC_DATA.PHS_CAL_TERM = LNRTMPP.PHS_CAL_TERM;
        LNCSPAIP.REC_DATA.PHS_CMP_TERM = LNRTMPP.PHS_CMP_TERM;
        LNCSPAIP.REC_DATA.CUR_INST_AMT = LNRTMPP.CUR_INST_AMT;
        LNCSPAIP.REC_DATA.CUR_INST_IRAT = LNRTMPP.CUR_INST_IRAT;
        LNCSPAIP.REC_DATA.CRT_DATE = LNRTMPP.CRT_DATE;
        LNCSPAIP.REC_DATA.CRT_TLR = LNRTMPP.CRT_TLR;
        LNCSPAIP.REC_DATA.UPDTBL_DATE = LNRTMPP.UPDTBL_DATE;
        LNCSPAIP.REC_DATA.UPDTBL_TLR = LNRTMPP.UPDTBL_TLR;
        LNCSPAIP.REC_DATA.TS = LNRTMPP.TS;
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.LN_AC);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.SUF_NO);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.INST_MTH);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PERD);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PERD_UNIT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_INST_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_CMP_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.CUR_INST_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.CUR_INST_IRAT);
    }
    public void S000_CALL_LNZSCALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CAL-TERM", LNCSCALT);
        if (LNCSCALT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSCALT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_UPD_PLPI_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCPAI.REC_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'C';
        LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_RD.where = "TERM >= :LNRPLPI.KEY.TERM";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_PLPI_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_DEL_PLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'C';
        LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_RD.where = "TERM >= :LNRPLPI.KEY.TERM";
        IBS.DELETE(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void S000_CALL_LNZTMPPB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTMPPB);
        LNCTMPPB.COMM_DATA.TRAN_SEQ = LNCSCPAI.REC_DATA.TRAN_SEQ;
        LNCTMPPB.COMM_DATA.LN_AC = LNCSCPAI.REC_DATA.CONTRACT_NO;
        LNCTMPPB.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCTMPPB.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCTMPPB.COMM_DATA.SUF_NO = "0" + LNCTMPPB.COMM_DATA.SUF_NO;
        IBS.CALLCPN(SCCGWA, "LN-FUN-TMPP-BRWQ", LNCTMPPB);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (SCCCLDT.RC != 0) {
            WS_MSGID.WS_MSG_AP = "SC";
            WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PAIPM-MAIN", LNCSPAIP);
        CEP.TRC(SCCGWA, LNCSPAIP.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (LNCSPAIP.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0135410") 
            && LNCSPAIP.FUNC != '1') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPAIP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTMPPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-TMPP-MAIN", LNCRTMPP);
        CEP.TRC(SCCGWA, LNCRTMPP.RC);
    }
    public void S000_CALL_LNZSTMPP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-TMPP-MAIN", LNCSTMPP);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUN_LNZCLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHECK-LNTLOAN", LNCCLOAN);
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, LNCSCPAI.RC);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
        if (LNCSCPAI.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSCPAI=");
            CEP.TRC(SCCGWA, LNCSCPAI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
