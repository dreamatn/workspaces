package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7228 {
    int JIBS_tmp_int;
    LNCXP13_LIST LIST;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAPRD_RD;
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "LNP13";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_ENTER = 'E';
    char K_CONFIRM = 'F';
    LNOT7228_WS_MSGID WS_MSGID = new LNOT7228_WS_MSGID();
    long WS_SEQ_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_PHS_NO = 0;
    short WS_PHS_NO1 = 0;
    int WS_I = 0;
    double WS_TOT_SCH_AMT = 0;
    short WS_TOT_SCH_TERM = 0;
    short WS_PHS_TOT_TERM = 0;
    short WS_TOT_TENORS = 0;
    int WS_SCH_END_DT = 0;
    char WS_CHG_FLG = ' ';
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
    LNCRTMPP LNCRTMPP = new LNCRTMPP();
    LNCSTMPP LNCSTMPP = new LNCSTMPP();
    LNCSPAIP LNCSPAIP = new LNCSPAIP();
    LNCTMPP1 LNCTMPP1 = new LNCTMPP1();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTMPPB LNCTMPPB = new LNCTMPPB();
    LNCXP13 LNCXP13 = new LNCXP13();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCCLOAN LNCCLOAN = new LNCCLOAN();
    LNCSCPAI LNCSCPAI = new LNCSCPAI();
    SCCGWA SCCGWA;
    LNB7220_AWA_7220 LNB7220_AWA_7220;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT7228 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7220_AWA_7220>");
        LNB7220_AWA_7220 = (LNB7220_AWA_7220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_UPD_PTR_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCPAI);
        LNCSCPAI.REC_DATA.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
        LNCSCPAI.REC_DATA.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        S000_CALL_LNZSCPAI();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
        if (LNB7220_AWA_7220.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_ERR_INFO = "" + LNB7220_AWA_7220.LN_AC_NO;
            JIBS_tmp_int = WS_ERR_INFO.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_INFO = "0" + WS_ERR_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLOAN);
        LNCCLOAN.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCCLOAN.COMM_DATA.CHECK_TYP = K_CONFIRM;
        S000_CALL_FUN_LNZCLOAN();
        if (pgmRtn) return;
        S000_READ_APRD_PROC();
        if (pgmRtn) return;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_TOT_TENORS = 1;
        WS_SCH_END_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
        while (WS_SCH_END_DT < LNRCONT.MAT_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            WS_TOT_TENORS += 1;
            if (LNRAPRD.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = LNRAPRD.CAL_PERD;
            }
            if (LNRAPRD.CAL_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNRAPRD.CAL_PERD;
            }
            SCCCLDT.DATE1 = WS_SCH_END_DT;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_SCH_END_DT = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, WS_SCH_END_DT);
        }
        S000_CALL_LNZTMPPB();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= LNCTMPPB.COMM_DATA.PHS_CNT; WS_I += 1) {
            if (LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].ACTION != 'C' 
                && LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].ACTION != 'D') {
                WS_TOT_SCH_AMT += LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_PRIN_AMT;
                WS_TOT_SCH_TERM += LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_TOT_TERM;
            }
        }
        CEP.TRC(SCCGWA, WS_TOT_SCH_AMT);
        CEP.TRC(SCCGWA, LNRCONT.LN_TOT_AMT);
        CEP.TRC(SCCGWA, WS_TOT_SCH_TERM);
        CEP.TRC(SCCGWA, WS_TOT_TENORS);
        if (WS_TOT_SCH_AMT != LNRCONT.LN_TOT_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_TOT_AMT_N_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTMPP);
        IBS.init(SCCGWA, LNRTMPP);
        IBS.init(SCCGWA, LNCSPAIP);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
        LNRTMPP.KEY.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
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
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRTMPP.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND)) {
            CEP.TRC(SCCGWA, WS_PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.ACTION);
            if (LNRTMPP.ACTION == 'A'
                || LNRTMPP.ACTION == 'M') {
                LNRTMPP.KEY.PHS_NO -= WS_PHS_NO;
                R000_MOVE_TMPP_TO_PAIP();
                if (pgmRtn) return;
                LNCSPAIP.FUNC = '0';
                S000_CALL_LNZSPAIP();
                if (pgmRtn) return;
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
            } else if (LNRTMPP.ACTION == 'I') {
                if (WS_PHS_NO != 0) {
                    R000_CHANGE_PHASE();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_PHS_NO);
            CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
            B320_MOVE_DETAIL();
            if (pgmRtn) return;
            LNCRTMPP.OPT = 'R';
            S000_CALL_LNZTMPPL();
            if (pgmRtn) return;
        }
        LNCRTMPP.OPT = 'E';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        if (WS_CHG_FLG == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTCHANGE;
            S000_ERR_MSG_PROC();
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
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.CI_NO);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.CI_CNAME);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.PROD_CD);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.PROD_NM);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.CCY);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.PRIN_AMT);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.OS_BAL);
        LNCXP13.AC = LNB7220_AWA_7220.LN_AC;
        LNCXP13.CI_NO = LNB7220_AWA_7220.CI_NO;
        LNCXP13.CI_CNAME = LNB7220_AWA_7220.CI_CNAME;
        LNCXP13.PROD_CD = LNB7220_AWA_7220.PROD_CD;
        LNCXP13.PROD_NAME = LNB7220_AWA_7220.PROD_NM;
        LNCXP13.CCY = LNB7220_AWA_7220.CCY;
        LNCXP13.PRIN_AMT = LNB7220_AWA_7220.PRIN_AMT;
        LNCXP13.OS_BAL = LNB7220_AWA_7220.OS_BAL;
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
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
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
        CEP.TRC(SCCGWA, LNCSPAIP);
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSTMPP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCSPAIP.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCSPAIP.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNRICTL.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.eqWhere = "CONTRACT_NO";
        LNTICTL_RD.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ICTL_NOTFND;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCPAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-COM-PAIP", LNCSCPAI);
        if (LNCSCPAI.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSCPAI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTMPPB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTMPPB);
        LNCTMPPB.COMM_DATA.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
        LNCTMPPB.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
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
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PAIPM-MAIN", LNCSPAIP);
        CEP.TRC(SCCGWA, LNCSPAIP.RC);
        if (LNCSPAIP.RC.RC_RTNCODE != 0) {
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
    public void S000_CALL_FUN_LNZCLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHECK-LNTLOAN", LNCCLOAN);
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
