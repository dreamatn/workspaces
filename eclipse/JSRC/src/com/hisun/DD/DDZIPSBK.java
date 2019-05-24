package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIPSBK {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DDTVCH_BR = new brParm();
    DBParm DDTVCH_RD;
    DBParm DDTUPT_RD;
    DBParm DDTMST_RD;
    brParm DDTUPT_BR = new brParm();
    brParm DDTPBBL_BR = new brParm();
    DBParm DDTPBBL_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String K_PRT_COV_FMT = "DD013";
    String K_WRITE_MAGNET_FMT = "DD050";
    String K_CHA_PSBK_FMT = "DD051";
    String K_CHA_LINE_FMT = "DD052";
    String K_LST_PSBK_FMT = "DD053";
    String K_INQ_UPT_FMT = "DD055";
    String K_INQ_PSBK_FMT = "DD056";
    String K_PRT_UPT_FMT = "DD061";
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_SHEET_MAX_LINE = 120;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_PSB_LEFT_LINE = 0;
    short WS_PSB_AVA_PRT_LINE = 0;
    short WS_PRT_IDX = 0;
    String WS_PSBK_NO = " ";
    long WS_PSBK_NO_1 = 0;
    short WS_TOT_DAYS = 0;
    char WS_PB_STS_FLG = ' ';
    double WS_AMT = 0;
    double WS_PB_AMT = 0;
    double WS_LST_PB_BAL = 0;
    short WS_DAYS = 0;
    char WS_RCCY_END = ' ';
    DDZIPSBK_WS_LOST_NO WS_LOST_NO = new DDZIPSBK_WS_LOST_NO();
    short WS_PSBK_SEQ = 0;
    int WS_PRT_LINE = 0;
    short WS_PAGE = 0;
    short WS_LINE = 0;
    char WS_VCH_FLAG = ' ';
    char WS_PBBL_FLAG = ' ';
    char WS_UPT_FLAG = ' ';
    char WS_CCY_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDRVCH DDRVCH = new DDRVCH();
    DDRPBBL DDRPBBL = new DDRPBBL();
    DDRUPT DDRUPT = new DDRUPT();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    DDCOPHAD DDCOPHAD = new DDCOPHAD();
    DDCOPMAG DDCOPMAG = new DDCOPMAG();
    DDCOPCHP DDCOPCHP = new DDCOPCHP();
    DDCOPCHL DDCOPCHL = new DDCOPCHL();
    DDCOPLST DDCOPLST = new DDCOPLST();
    DDCOPINQ DDCOPINQ = new DDCOPINQ();
    DDCOPUPT DDCOPUPT = new DDCOPUPT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPBLST DDCPBLST = new DDCPBLST();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DDCPBKS DDCPBKS = new DDCPBKS();
    DDRMST DDRMST = new DDRMST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOUPT DDCOUPT = new DDCOUPT();
    DDRCCY DDRCCY = new DDRCCY();
    BPCEX BPCEX = new BPCEX();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDCBVCD DDCBVCD = new DDCBVCD();
    DCCIMSTU DCCIMSTU = new DCCIMSTU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRI CICQACRI = new CICQACRI();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICCUST CICCUST = new CICCUST();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    DDCIPSBK DDCIPSBK;
    public void MP(SCCGWA SCCGWA, DDCIPSBK DDCIPSBK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIPSBK = DDCIPSBK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIPSBK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CALD_CD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIPSBK.FUNC);
        if (DDCIPSBK.FUNC == 'A') {
            B010_CREATE_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'X') {
            B011_CREATE_CARDPB_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'Z') {
            B012_CANCEL_CARDPB_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'D') {
            B030_CLOSE_PSBK_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'C') {
            B050_CHANGE_PSBK_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'L') {
            B070_CHA_PRT_LINE_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'W'
            || DDCIPSBK.FUNC == 'U'
            || DDCIPSBK.FUNC == '1'
            || DDCIPSBK.FUNC == '2') {
            R000_GET_ACAC_NO();
            if (pgmRtn) return;
            B080_LOST_PSBK_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'R'
            || DDCIPSBK.FUNC == '5'
            || DDCIPSBK.FUNC == '6') {
            B090_RELOST_PSBK_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'T'
            || DDCIPSBK.FUNC == 'S') {
            B100_UPD_UPT_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'P') {
            B110_PRT_UPT_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'I') {
            B120_INQ_UPT_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'Q') {
            B130_INQ_PSBK_INFO_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'M') {
            B150_MAGNET_PSBK_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'B') {
            B160_PRT_PSBK_COVER_PROC();
            if (pgmRtn) return;
        } else if (DDCIPSBK.FUNC == 'F') {
            B170_CREATE_NOBV_VCH_REC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIPSBK.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void R000_GET_ACAC_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIPSBK.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCIPSBK.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B010_CREATE_PROC() throws IOException,SQLException,Exception {
        if (DDCIPSBK.SIGN_NO.trim().length() > 0) {
            if (DDCIPSBK.SIGN_TYP == '1') {
                R000_SIGN_NO_STS_TO_USE();
                if (pgmRtn) return;
                R000_SIGN_NO_IN();
                if (pgmRtn) return;
            }
            if (DDCIPSBK.SIGN_TYP == '2') {
                R000_SIGN_NO_CHECK();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIPSBK.AC;
        DDRVCH.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRVCH.VCH_TYPE = DDCIPSBK.VCH_TYPE;
        DDRVCH.PSBK_NO = DDCIPSBK.PSBK_NO;
        WS_PSBK_NO = DDCIPSBK.PSBK_NO;
        DDRVCH.PAY_TYPE = DDCIPSBK.PAY_MTH;
        CEP.TRC(SCCGWA, DDCIPSBK.PAY_MTH);
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCIPSBK.PSWD);
        DDRVCH.PAY_IDTYPE = DDCIPSBK.ID_TYPE;
        DDRVCH.PAY_IDNO = DDCIPSBK.ID_NO;
        DDRVCH.PAY_SIGN_NO = DDCIPSBK.SIGN_NO;
        DDRVCH.PSBK_STS = 'N';
        DDRVCH.PRT_LINE = 0;
        DDRVCH.PSBK_SEQ = 1;
        DDRVCH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.KEY.BV_TYPE = DDCIPSBK.BV_TYPE;
        T000_WRITE_DDTVCH();
        if (pgmRtn) return;
        R000_SET_PASSWORD();
        if (pgmRtn) return;
        WS_PSBK_SEQ = DDRVCH.PSBK_SEQ;
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.CARD_NO);
        if (DDCIPSBK.PSBK_NO.trim().length() > 0) {
            R000_PRT_HEAD_PAGE();
            if (pgmRtn) return;
            B020_WRITE_MAGNET();
            if (pgmRtn) return;
            R000_SET_PSB_STS_TO_USE();
            if (pgmRtn) return;
        }
    }
    public void B011_CREATE_CARDPB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIPSBK.AC;
        if (DDCIPSBK.VCH_TYPE == '3') {
            DDRVCH.VCH_TYPE = DDCIPSBK.VCH_TYPE;
        } else {
            DDRVCH.VCH_TYPE = '1';
        }
        DDRVCH.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRVCH.PSBK_NO = DDCIPSBK.PSBK_NO;
        WS_PSBK_NO = DDCIPSBK.PSBK_NO;
        DDRVCH.PAY_TYPE = DDCIPSBK.PAY_MTH;
        CEP.TRC(SCCGWA, DDCIPSBK.PAY_MTH);
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCIPSBK.PSWD);
        DDRVCH.PAY_SIGN_NO = DDCIPSBK.SIGN_NO;
        DDRVCH.PSBK_STS = 'N';
        DDRVCH.PRT_LINE = 0;
        DDRVCH.PSBK_SEQ = 1;
        DDRVCH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_PSBK_SEQ = DDRVCH.PSBK_SEQ;
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.CARD_NO);
        R000_PRT_HEAD_PAGE();
        if (pgmRtn) return;
        B020_WRITE_MAGNET();
        if (pgmRtn) return;
        R000_PRT_FIRST_BAL_CARDPB();
        if (pgmRtn) return;
        if (DDCIPSBK.VCH_TYPE != '3') {
            R000_SET_PSB_STS_TO_USE();
            if (pgmRtn) return;
        }
        T000_WRITE_DDTVCH();
        if (pgmRtn) return;
        R000_SET_PASSWORD();
        if (pgmRtn) return;
        R000_SET_USING_PB_FLG();
        if (pgmRtn) return;
    }
    public void B012_CANCEL_CARDPB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCIPSBK.CARD_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIPSBK.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        if (DDRVCH.PSBK_STS != 'C') {
            DDRVCH.PSBK_STS = 'C';
            DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void B170_CREATE_NOBV_VCH_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIPSBK.AC;
        DDRVCH.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRVCH.VCH_TYPE = DDCIPSBK.VCH_TYPE;
        DDRVCH.PAY_TYPE = DDCIPSBK.PAY_MTH;
        DDRVCH.PAY_IDTYPE = DDCIPSBK.ID_TYPE;
        DDRVCH.PAY_IDNO = DDCIPSBK.ID_NO;
        DDRVCH.PAY_SIGN_NO = DDCIPSBK.SIGN_NO;
        DDRVCH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.KEY.BV_TYPE = DDCIPSBK.BV_TYPE;
        T000_WRITE_DDTVCH();
        if (pgmRtn) return;
        R000_SET_PASSWORD();
        if (pgmRtn) return;
    }
    public void B030_CLOSE_PSBK_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        if (DDRVCH.PSBK_NO.trim().length() > 0 
            && DDRVCH.PSBK_STS != 'C') {
            CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
            CEP.TRC(SCCGWA, DDCIPSBK.LOST_NO);
            CEP.TRC(SCCGWA, DDRVCH.LOST_NO);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("111804")) {
                if (DDRVCH.PSBK_STS == 'W' 
                    && !DDCIPSBK.LOST_NO.equalsIgnoreCase(DDRVCH.LOST_NO)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LOSTNO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDRVCH.PSBK_STS != 'W' 
                    && !DDRVCH.PSBK_NO.equalsIgnoreCase(DDCIPSBK.PSBK_NO)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBKNO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DDRVCH.PSBK_STS == 'W') {
                R000_PRT_LOST_ADVICE();
                if (pgmRtn) return;
            }
            DDRVCH.PSBK_STS = 'C';
            DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
        }
    }
    public void B050_CHANGE_PSBK_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIPSBK.CHA_REASON);
        CEP.TRC(SCCGWA, DDRVCH.LOST_EXP_DATE);
        R000_GET_PSBK_LOST_PARM();
        if (pgmRtn) return;
        if (DDCIPSBK.FUNC == 'C' 
            && DDCIPSBK.CHA_REASON == 'L') {
            R000_CHK_ORG_PROC();
            if (pgmRtn) return;
            if (DDRVCH.PSBK_STS != 'W') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_NOT_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_GET_CI_INF_PROC();
            if (pgmRtn) return;
            if (DDRVCH.PSBK_STS == 'W') {
                if (!DDCIPSBK.LOST_NO.equalsIgnoreCase(DDRVCH.LOST_NO)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LOSTNO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                B050_2_UPD_DDTLOSS_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCIPSBK.CHA_REASON != 'I') {
            if (DDRVCH.PSBK_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NOISUE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
            CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
            if (DDRVCH.PSBK_STS != 'W' 
                && DDCIPSBK.CHA_REASON == 'L') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_NOT_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DDRVCH.PSBK_NO.trim().length() > 0 
                && DDRVCH.PSBK_STS != 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_ISUED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIPSBK.PSBK_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBNO_NOT_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCIPSBK.CHA_REASON == 'C') {
            if (DDCIPSBK.PSBK_NO_NEW.trim().length() > 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NPSBNO_NOT_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCIPSBK.CHA_REASON == 'C') {
            DDRVCH.PSBK_STS = 'C';
        } else {
            if (DDCIPSBK.CHA_REASON != 'D') {
                CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
                if (DDRVCH.VCH_TYPE == '1') {
                    WS_PSBK_NO = DDCIPSBK.PSBK_NO_NEW;
                    R000_SET_PSB_STS_TO_USE();
                    if (pgmRtn) return;
                    DDRVCH.PSBK_NO = DDCIPSBK.PSBK_NO_NEW;
                } else {
                    CEP.TRC(SCCGWA, "DZB PROCESS");
                    DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
                    if (DDRVCH.PSBK_NO.trim().length() == 0) WS_PSBK_NO_1 = 0;
                    else WS_PSBK_NO_1 = Long.parseLong(DDRVCH.PSBK_NO);
                    WS_PSBK_NO_1 = WS_PSBK_NO_1 + 1;
                    DDRVCH.PSBK_NO = "" + WS_PSBK_NO_1;
                    JIBS_tmp_int = DDRVCH.PSBK_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRVCH.PSBK_NO = "0" + DDRVCH.PSBK_NO;
                    DDCIPSBK.PSBK_NO_NEW = "" + WS_PSBK_NO_1;
                    JIBS_tmp_int = DDCIPSBK.PSBK_NO_NEW.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDCIPSBK.PSBK_NO_NEW = "0" + DDCIPSBK.PSBK_NO_NEW;
                    WS_PSBK_NO = "" + WS_PSBK_NO_1;
                    JIBS_tmp_int = WS_PSBK_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) WS_PSBK_NO = "0" + WS_PSBK_NO;
                }
            }
            DDRVCH.PRT_LINE = 0;
            DDRVCH.PSBK_STS = 'N';
            DDRVCH.W_LOST_DATE = 0;
            DDRVCH.LOST_EXP_DATE = 0;
            DDRVCH.LOST_NO = " ";
            DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDCIPSBK.PAY_MTH = DDRVCH.PAY_TYPE;
            R000_PRT_HEAD_PAGE();
            if (pgmRtn) return;
            R000_PRT_CHA_PSBK_ADVICE();
            if (pgmRtn) return;
            WS_PSBK_SEQ = DDRVCH.PSBK_SEQ;
            CEP.TRC(SCCGWA, WS_PSBK_SEQ);
            B020_WRITE_MAGNET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRVCH.PRT_LINE == 0 
            && DDCIPSBK.CHA_REASON != 'C' 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0354800")) {
            R000_PRT_FIRST_BAL();
            if (pgmRtn) return;
        }
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
        if (DDCIPSBK.CHA_REASON == 'C' 
            || DDCIPSBK.CHA_REASON == 'I') {
            R000_SET_USING_PB_FLG();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y' 
            && DDCIPSBK.FUNC == 'C' 
            && DDCIPSBK.CHA_REASON == 'L') {
            R000_ADD_AGEN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_1_CHK_LOST_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
        BPCOCLWD.DAYE_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCOCLWD.DATE1 = DDRVCH.W_LOST_DATE;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        BPCOCLWD.DAYS = DDCPBLST.DATA_TXT.WLOST_LOST_DAYS;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AC_DATE <= BPCOCLWD.DATE2) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHG_PB_DT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_2_UPD_DDTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.LOS_NO = DDRVCH.LOST_NO;
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NO);
        if (DDCIPSBK.FUNC == '1') {
            BPCSLOSS.STS = '4';
        } else if (DDCIPSBK.FUNC == '2') {
            BPCSLOSS.STS = '3';
        } else if (DDCIPSBK.FUNC == 'W') {
            BPCSLOSS.STS = '4';
        } else if (DDCIPSBK.FUNC == 'R'
            || DDCIPSBK.FUNC == '5'
            || DDCIPSBK.FUNC == '6') {
            if (DDRVCH.PSBK_STS == 'U') {
                BPCSLOSS.STS = '4';
            } else {
                BPCSLOSS.STS = '3';
            }
        } else if (DDCIPSBK.FUNC == 'C') {
            BPCSLOSS.STS = '5';
        } else {
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSLOSS.RLOS_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSLOSS.RLOS_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            BPCSLOSS.RLOS_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSLOSS.RLOS_TELE = CICGAGA_AGENT_AREA.PHONE;
            R000_ADD_AGEN_PROC();
            if (pgmRtn) return;
        } else {
            BPCSLOSS.RLOS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            BPCSLOSS.RLOS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            BPCSLOSS.RLOS_NM = CICCUST.O_DATA.O_CI_NM;
            BPCSLOSS.RLOS_TELE = DDCIPSBK.R_TEL_NO;
        }
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_ID_TYP);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_ID_NO);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_NM);
        CEP.TRC(SCCGWA, BPCSLOSS.RLOS_TELE);
        BPCSLOSS.NEW_BV_NO = DDCIPSBK.PSBK_NO_NEW;
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B070_CHA_PRT_LINE_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        WS_PRT_LINE = ( DDCIPSBK.PSBK_PAGE - 1 ) * DDCPBKS.DATA_TXT.PAGE_LINE + DDCIPSBK.PSBK_LINE;
        DDRVCH.PRT_LINE = (short) WS_PRT_LINE;
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_PAGE);
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_LINE);
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
        R000_PRT_CHA_LINE_ADVICE();
        if (pgmRtn) return;
    }
    public void B080_LOST_PSBK_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        if (DDRVCH.PSBK_STS == 'U' 
            && DDCIPSBK.FUNC == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ULIST_PSBK);
        }
        if (DDRVCH.PSBK_STS == 'U' 
            && DDCIPSBK.FUNC == 'U') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ULIST_PSBK);
        }
        if (DDRVCH.PSBK_STS == 'W' 
            && DDCIPSBK.FUNC == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_WLST_PSBK);
        }
        if (DDRVCH.PSBK_STS == 'W' 
            && DDCIPSBK.FUNC == 'W') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_WLST_PSBK);
        }
        if (DDCIPSBK.FUNC == 'U' 
            || DDCIPSBK.FUNC == '1') {
            if (DDRVCH.PSBK_STS == 'W') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRVCH.PSBK_STS == 'N' 
            && (DDCIPSBK.FUNC == '1' 
            || DDCIPSBK.FUNC == '2')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PSBK_NO_NML);
        }
        R000_GET_CI_INF_PROC();
        if (pgmRtn) return;
        if (DDCIPSBK.FUNC == '1' 
            || DDCIPSBK.FUNC == '2') {
            B050_2_UPD_DDTLOSS_PROC();
            if (pgmRtn) return;
        }
        if (DDCIPSBK.FUNC == '2') {
            R000_CHK_ORG_PROC();
            if (pgmRtn) return;
        }
        R000_GEN_LOST_NO();
        if (pgmRtn) return;
        if (DDCIPSBK.FUNC == 'U' 
            || DDCIPSBK.FUNC == '1') {
            DDRVCH.PSBK_STS = 'U';
            R000_GET_PSBK_LOST_PARM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.DAYS = DDCPBLST.DATA_TXT.ULOST_EXP_DAYS;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            DDRVCH.LOST_EXP_DATE = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            DDRVCH.W_LOST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRVCH.LOST_NO = IBS.CLS2CPY(SCCGWA, WS_LOST_NO);
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
        } else {
            if (DDRVCH.PSBK_STS == 'U') {
                B050_2_UPD_DDTLOSS_PROC();
                if (pgmRtn) return;
            }
            DDRVCH.PSBK_STS = 'W';
            DDRVCH.W_LOST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.LOST_EXP_DATE = 0;
            DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRVCH.LOST_NO = IBS.CLS2CPY(SCCGWA, WS_LOST_NO);
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
        }
        B080_1_GEN_DDTLOSS_REC();
        if (pgmRtn) return;
        R000_PRT_LOST_ADVICE();
        if (pgmRtn) return;
        DDCIPSBK.LOST_NO = DDRVCH.LOST_NO;
    }
    public void B080_1_GEN_DDTLOSS_REC() throws IOException,SQLException,Exception {
        BPCSLOSS.BV_NO = DDRVCH.PSBK_NO;
        IBS.init(SCCGWA, BPCSLOSS);
        IBS.init(SCCGWA, BPRLOSS);
        BPCSLOSS.FUNC = 'C';
        CEP.TRC(SCCGWA, WS_LOST_NO);
        BPCSLOSS.LOS_NO = IBS.CLS2CPY(SCCGWA, WS_LOST_NO);
        BPCSLOSS.AC = DDCIPSBK.AC;
        BPCSLOSS.PER_FLG = '1';
        BPCSLOSS.AC_TYPE = "01";
        if (DDCIPSBK.FUNC == 'W' 
            || DDCIPSBK.FUNC == '2') {
            BPCSLOSS.STS = '1';
        } else {
            BPCSLOSS.STS = '2';
        }
        BPCSLOSS.BV_TYP = '4';
        BPCSLOSS.BV_CODE = "001";
        BPCSLOSS.BV_NO = DDRVCH.PSBK_NO;
        T000_READ_DDTMST1();
        if (pgmRtn) return;
        BPCSLOSS.OPEN_BR = DDRMST.OPEN_DP;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        BPCSLOSS.OPEN_AMT = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSLOSS.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSLOSS.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            BPCSLOSS.OTH_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSLOSS.OTH_TELE = CICGAGA_AGENT_AREA.PHONE;
            R000_ADD_AGEN_PROC();
            if (pgmRtn) return;
        }
        BPCSLOSS.LOS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSLOSS.LOS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSLOSS.LOS_NM = CICCUST.O_DATA.O_CI_NM;
        BPCSLOSS.LOS_RMK = DDCIPSBK.UPT_RMK;
        BPCSLOSS.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSLOSS.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSLOSS.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSLOSS.LOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.LOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.LOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.LOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (DDCIPSBK.HLD_FLG == 'Y') {
            BPCSLOSS.HLD_FLG = '0';
        } else {
            BPCSLOSS.HLD_FLG = '1';
        }
        if (DDCIPSBK.FUNC == 'U' 
            || DDCIPSBK.FUNC == '1') {
            BPCSLOSS.RLOS_DUE_TIME = DDRVCH.LOST_EXP_DATE;
        }
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void R000_GEN_LOST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "LOSEQ";
        BPCSGSEQ.CODE = "01";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1).trim().length() == 0) WS_LOST_NO.WS_LOST_YYMM = 0;
        else WS_LOST_NO.WS_LOST_YYMM = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1));
        WS_LOST_NO.WS_LOST_TYPE = "01";
        WS_LOST_NO.WS_LOST_SEQ = (int) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, WS_LOST_NO);
    }
    public void R000_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDCIPSBK.AC;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B090_RELOST_PSBK_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        R000_GET_CI_INF_PROC();
        if (pgmRtn) return;
        WS_PB_STS_FLG = DDRVCH.PSBK_STS;
        R000_PRT_LOST_ADVICE();
        if (pgmRtn) return;
        B050_2_UPD_DDTLOSS_PROC();
        if (pgmRtn) return;
        DDRVCH.PSBK_STS = 'N';
        DDRVCH.W_LOST_DATE = 0;
        DDRVCH.LOST_EXP_DATE = 0;
        DDRVCH.LOST_NO = " ";
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B100_UPD_UPT_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        if (DDCIPSBK.FUNC == 'T') {
            R000_CHK_PSBK_STS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        if (DDRVCH.PSBK_STS != 'C' 
            && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && DDCIPSBK.PSBK_NO.equalsIgnoreCase("999999")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_CHANGE_PSB;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DDCIPSBK.PSBK_NO.equalsIgnoreCase("999999")) {
                if (!DDRVCH.PSBK_NO.equalsIgnoreCase(DDCIPSBK.PSBK_NO) 
                    && DDCIPSBK.PSBK_NO.trim().length() > 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBKNO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, DDRUPT);
            CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
            DDRUPT.KEY.CUS_AC = DDRVCH.KEY.CUS_AC;
            DDRVCH.UPT_LAST_NO = DDRVCH.UPT_LAST_NO + 1;
            DDRUPT.KEY.UPT_NO = DDRVCH.UPT_LAST_NO;
            DDRUPT.CCY = DDCIPSBK.UPT_CCY;
            DDRUPT.CCY_TYPE = DDCIPSBK.UPT_CCY_TYPE;
            DDRUPT.TXN_AMT = DDCIPSBK.UPT_AMT;
            DDRUPT.TXN_TYPE = DDCIPSBK.UPT_TXN_TYPE;
            DDRUPT.TXN_MMO = DDCIPSBK.UPT_MMO;
            DDRUPT.REMARK = DDCIPSBK.UPT_RMK;
            DDRUPT.TXN_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            DDRUPT.ZIP_FLAG = '0';
            DDRUPT.PSBK_SEQ = DDRVCH.PSBK_SEQ;
            DDRUPT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRUPT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRUPT.TELLER = SCCGWA.COMM_AREA.TL_ID;
            DDRUPT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRUPT.DP = SCCGWA.COMM_AREA.BR_DP.TR_DEP;
            DDRUPT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRUPT.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDRUPT.PRT_FLAG = '0';
            DDRVCH.UPT_CNT += 1;
            DDRUPT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRUPT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRUPT.KEY.UPT_NO);
            T000_WRITE_DDTUPT();
            if (pgmRtn) return;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
        }
        DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
    }
    public void B110_PRT_UPT_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        if (DDRVCH.UPT_CNT != 0) {
            R000_PRT_UPT_PROC();
            if (pgmRtn) return;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
        } else {
            R000_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B120_INQ_UPT_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
    }
    public void B130_INQ_PSBK_INFO_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        R000_INQ_PSBK_INFO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B150_MAGNET_PSBK_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTMST();
        if (pgmRtn) return;
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
    }
    public void B160_PRT_PSBK_COVER_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        R000_CHK_PSBK_STS();
        if (pgmRtn) return;
        if (DDRVCH.PSBK_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NOISUE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
        DDCIPSBK.PAY_MTH = DDRVCH.PAY_TYPE;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_SEQ);
        R000_PRT_HEAD_PAGE();
        if (pgmRtn) return;
    }
    public void R000_PRT_FIRST_BAL() throws IOException,SQLException,Exception {
        if (DDCIPSBK.CHA_REASON == 'I') {
            IBS.init(SCCGWA, DDRCCY);
            T000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            while (WS_RCCY_END != 'E') {
                IBS.init(SCCGWA, DDRPBBL);
                DDRPBBL.KEY.CUS_AC = DDCIPSBK.AC;
                DDRPBBL.KEY.CCY = DDRCCY.CCY;
                DDRPBBL.KEY.CCY_TYPE = DDRCCY.CCY_TYPE;
                T000_READ_UPD_DDTPBBL();
                if (pgmRtn) return;
                DDRPBBL.PSBK_BAL = DDRCCY.CURR_BAL;
                if (WS_PBBL_FLAG == 'N') {
                    DDRPBBL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRPBBL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DDTPBBL();
                    if (pgmRtn) return;
                } else {
                    DDRPBBL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRPBBL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTPBBL();
                    if (pgmRtn) return;
                }
                T000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTCCY();
            if (pgmRtn) return;
        }
        DDCOPUPT.CNT = 0;
        DDCOPUPT.PSBK_LINE = 1;
        IBS.init(SCCGWA, DDRPBBL);
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_DDTPBBL();
        if (pgmRtn) return;
        T000_READNEXT_DDTPBBL();
        if (pgmRtn) return;
        while (WS_PBBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            DDCOPUPT.CNT += 1;
            if (DDRVCH.VCH_TYPE == '1' 
                || DDRVCH.VCH_TYPE == '2') {
                DDRVCH.PRT_LINE += 1;
            }
            DDCOPUPT.PSBK_LINE = 1;
            IBS.init(SCCGWA, DDCOUPT);
            DDCOUPT.DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCOUPT.CCY = DDRPBBL.KEY.CCY;
            DDCOUPT.CCY_TYPE = DDRPBBL.KEY.CCY_TYPE;
            DDCOUPT.NUM = DDRVCH.PRT_LINE;
            DDCOUPT.AMT = 0;
            DDCOUPT.DRCR_FLG = 'C';
            DDCOUPT.AC_ATTR = DDCIPSBK.AC_ATTR;
            DDCOUPT.BALANCE = DDRPBBL.PSBK_BAL;
            DDCOUPT.REF = DDCIPSBK.UPT_MMO;
            DDCOUPT.TL_ID = SCCGWA.COMM_AREA.TL_ID;
            R000_MPAGE_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_DDTPBBL();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRT_UPT_FMT;
        SCCFMT.DATA_PTR = DDCOPUPT;
        SCCFMT.DATA_LEN = 42;
        IBS.FMT(SCCGWA, SCCFMT);
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        CEP.TRC(SCCGWA, WS_SHEET_MAX_LINE);
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        if (DDRVCH.VCH_TYPE == '1' 
            || DDRVCH.VCH_TYPE == '2') {
            WS_PSB_LEFT_LINE = (short) (DDCPBKS.DATA_TXT.MAX_LINE - DDRVCH.PRT_LINE);
        }
        CEP.TRC(SCCGWA, WS_PSB_LEFT_LINE);
        if (WS_PSB_LEFT_LINE <= 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NET_PRT_ITM_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.UPT_CNT >= WS_PSB_LEFT_LINE) {
            WS_PSB_AVA_PRT_LINE = WS_PSB_LEFT_LINE;
        } else {
            WS_PSB_AVA_PRT_LINE = (short) DDRVCH.UPT_CNT;
        }
        CEP.TRC(SCCGWA, WS_PSB_AVA_PRT_LINE);
        CEP.TRC(SCCGWA, DDCIPSBK.CHA_REASON);
        T000_STARTBR_DDTUPT();
        if (pgmRtn) return;
        T000_READNEXT_DDTUPT();
        if (pgmRtn) return;
        while (WS_UPT_FLAG != 'N' 
            && WS_PRT_IDX < WS_PSB_AVA_PRT_LINE 
            && DDRVCH.UPT_CNT != 0 
            && SCCMPAG.FUNC != 'E') {
            if (DDCIPSBK.CHA_REASON == 'I') {
                DDRUPT.PRT_FLAG = '1';
                DDRUPT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRUPT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTUPT();
                if (pgmRtn) return;
                T000_READNEXT_DDTUPT();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDRPBBL);
                DDRPBBL.KEY.CUS_AC = DDRUPT.KEY.CUS_AC;
                DDRPBBL.KEY.CCY = DDRUPT.CCY;
                DDRPBBL.KEY.CCY_TYPE = DDRUPT.CCY_TYPE;
                T000_READ_UPD_DDTPBBL();
                if (pgmRtn) return;
                if (WS_PBBL_FLAG == 'N') {
                    if (DDRVCH.LAST_PB_BAL != 0) {
                        CEP.TRC(SCCGWA, DDRVCH.LAST_PB_BAL);
                        CEP.TRC(SCCGWA, DDRUPT.TXN_AMT);
                        if (DDRUPT.CCY.equalsIgnoreCase(DDRVCH.LAST_PB_CCY)) {
                            if (DDRUPT.TXN_TYPE == 'D') {
                                DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL - DDRUPT.TXN_AMT;
                            } else {
                                DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL + DDRUPT.TXN_AMT;
                            }
                            CEP.TRC(SCCGWA, "SAME CCY");
                        } else {
                            CEP.TRC(SCCGWA, "NOT SAME CCY");
                            if (DDRUPT.TXN_TYPE == 'D') {
                                DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                            } else {
                                DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                            }
                        }
                    } else {
                        CEP.TRC(SCCGWA, DDRVCH.LAST_PB_BAL);
                        CEP.TRC(SCCGWA, DDRUPT.TXN_AMT);
                        if (DDRUPT.TXN_TYPE == 'D') {
                            DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                        } else {
                            DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                        }
                    }
                    T000_WRITE_DDTPBBL();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "PBBL FOUND");
                    if (DDRUPT.TXN_TYPE == 'D') {
                        DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                    } else {
                        DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                    }
                    T000_REWRITE_DDTPBBL();
                    if (pgmRtn) return;
                }
                DDRVCH.UPT_CNT -= 1;
                DDRVCH.PRT_LINE += 1;
                DDCOPUPT.CNT += 1;
                CEP.TRC(SCCGWA, DDCOPUPT.CNT);
                CEP.TRC(SCCGWA, DDCOPUPT.PSBK_LINE);
                CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
                IBS.init(SCCGWA, DDCOUPT);
                WS_PRT_IDX += 1;
                DDCOUPT.DATE = DDRUPT.TXN_DATE;
                DDCOUPT.CCY = DDRUPT.CCY;
                DDCOUPT.CCY_TYPE = DDRUPT.CCY_TYPE;
                DDCOUPT.NUM = DDRVCH.PRT_LINE;
                if (DDRUPT.TXN_TYPE == 'D') {
                    DDCOUPT.AMT = DDRUPT.TXN_AMT;
                    DDCOUPT.DRCR_FLG = 'D';
                } else {
                    DDCOUPT.AMT = DDRUPT.TXN_AMT;
                    DDCOUPT.DRCR_FLG = 'C';
                }
                DDCOUPT.AC_ATTR = DDCIPSBK.AC_ATTR;
                DDCOUPT.BALANCE = DDRPBBL.PSBK_BAL;
                DDCOUPT.REF = DDRUPT.TXN_MMO;
                DDCOUPT.TL_ID = DDRUPT.TELLER;
                DDRUPT.PSBK_SEQ = DDRVCH.PSBK_SEQ;
                DDRUPT.PRT_FLAG = '1';
                DDRUPT.PRT_LINE = DDRVCH.PRT_LINE;
                DDRUPT.PB_BAL = DDRPBBL.PSBK_BAL;
                DDRUPT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRUPT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTUPT();
                if (pgmRtn) return;
                R000_MPAGE_OUTPUT();
                if (pgmRtn) return;
                T000_READNEXT_DDTUPT();
                if (pgmRtn) return;
            }
        }
        if (DDCIPSBK.CHA_REASON != 'I') {
            DDCOPUPT.ACNO.ACNO_1 = 'T';
            DDCOPUPT.ACNO.ACNO_21 = DDRVCH.KEY.CUS_AC;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_PRT_UPT_FMT;
            SCCFMT.DATA_PTR = DDCOPUPT;
            SCCFMT.DATA_LEN = 42;
            IBS.FMT(SCCGWA, SCCFMT);
            if (WS_PRT_IDX > 0) {
                DDRVCH.LAST_PB_CCY = DDRUPT.CCY;
                DDRVCH.LAST_PB_BAL = DDRPBBL.PSBK_BAL;
            }
        }
        T000_ENDBR_DDTUPT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        if (DDCIPSBK.CHA_REASON == 'I') {
            DDRVCH.UPT_CNT = 0;
        }
        if (DDCIPSBK.CHA_REASON != 'I') {
            if (DDRVCH.UPT_CNT > 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PB_FULL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_PRT_UPT_PROC() throws IOException,SQLException,Exception {
        DDCOPUPT.CNT = 0;
        DDCOPUPT.PSBK_LINE = DDRVCH.PRT_LINE;
        DDCOPUPT.PSBK_LINE += 1;
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
        WS_PSB_LEFT_LINE = (short) (DDCPBKS.DATA_TXT.MAX_LINE - DDRVCH.PRT_LINE);
        DDCOUPT.LEFT_LINE = WS_PSB_LEFT_LINE;
        CEP.TRC(SCCGWA, DDCOUPT.LEFT_LINE);
        CEP.TRC(SCCGWA, WS_PSB_LEFT_LINE);
        if (WS_PSB_LEFT_LINE <= 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NET_PRT_ITM_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.UPT_CNT >= WS_PSB_LEFT_LINE) {
            WS_PSB_AVA_PRT_LINE = WS_PSB_LEFT_LINE;
        } else {
            WS_PSB_AVA_PRT_LINE = (short) DDRVCH.UPT_CNT;
        }
        CEP.TRC(SCCGWA, WS_PSB_AVA_PRT_LINE);
        CEP.TRC(SCCGWA, WS_PRT_IDX);
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_DDTUPT();
        if (pgmRtn) return;
        T000_READNEXT_DDTUPT();
        if (pgmRtn) return;
        while (WS_UPT_FLAG != 'N' 
            && WS_PRT_IDX < WS_PSB_AVA_PRT_LINE 
            && DDRVCH.UPT_CNT != 0 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, DDRPBBL);
            DDRPBBL.KEY.CUS_AC = DDRUPT.KEY.CUS_AC;
            DDRPBBL.KEY.CCY = DDRUPT.CCY;
            DDRPBBL.KEY.CCY_TYPE = DDRUPT.CCY_TYPE;
            T000_READ_UPD_DDTPBBL();
            if (pgmRtn) return;
            if (WS_PBBL_FLAG == 'N') {
                if (DDRVCH.LAST_PB_BAL != 0) {
                    CEP.TRC(SCCGWA, DDRVCH.LAST_PB_BAL);
                    CEP.TRC(SCCGWA, DDRUPT.TXN_AMT);
                    if (DDRUPT.CCY.equalsIgnoreCase(DDRVCH.LAST_PB_CCY)) {
                        if (DDRUPT.TXN_TYPE == 'D') {
                            DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL - DDRUPT.TXN_AMT;
                        } else {
                            DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL + DDRUPT.TXN_AMT;
                        }
                        CEP.TRC(SCCGWA, "SAME CCY");
                    } else {
                        CEP.TRC(SCCGWA, "NOT SAME CCY");
                        if (DDRUPT.TXN_TYPE == 'D') {
                            DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                        } else {
                            DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                        }
                    }
                } else {
                    CEP.TRC(SCCGWA, DDRVCH.LAST_PB_BAL);
                    CEP.TRC(SCCGWA, DDRUPT.TXN_AMT);
                    if (DDRUPT.TXN_TYPE == 'D') {
                        DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                    } else {
                        DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                    }
                }
                T000_WRITE_DDTPBBL();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "PBBL FOUND");
                if (DDRUPT.TXN_TYPE == 'D') {
                    DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                } else {
                    DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                }
                T000_REWRITE_DDTPBBL();
                if (pgmRtn) return;
            }
            DDRVCH.UPT_CNT -= 1;
            DDCOUPT.LEFT_LINE -= 1;
            DDRVCH.PRT_LINE += 1;
            DDCOPUPT.CNT += 1;
            CEP.TRC(SCCGWA, DDCOUPT.LEFT_LINE);
            CEP.TRC(SCCGWA, DDCOPUPT.CNT);
            CEP.TRC(SCCGWA, DDCOPUPT.PSBK_LINE);
            CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
            IBS.init(SCCGWA, DDCOUPT);
            WS_PRT_IDX += 1;
            DDCOUPT.DATE = DDRUPT.TXN_DATE;
            DDCOUPT.CCY = DDRUPT.CCY;
            DDCOUPT.CCY_TYPE = DDRUPT.CCY_TYPE;
            DDCOUPT.NUM = DDRVCH.PRT_LINE;
            if (DDRUPT.TXN_TYPE == 'D') {
                DDCOUPT.AMT = DDRUPT.TXN_AMT;
                DDCOUPT.DRCR_FLG = 'D';
            } else {
                DDCOUPT.AMT = DDRUPT.TXN_AMT;
                DDCOUPT.DRCR_FLG = 'C';
            }
            DDCOUPT.AC_ATTR = DDCIPSBK.AC_ATTR;
            DDCOUPT.BALANCE = DDRPBBL.PSBK_BAL;
            DDCOUPT.REF = DDRUPT.TXN_MMO;
            DDCOUPT.TL_ID = DDRUPT.TELLER;
            DDRUPT.PSBK_SEQ = DDRVCH.PSBK_SEQ;
            DDRUPT.PRT_FLAG = '1';
            DDRUPT.PRT_LINE = DDRVCH.PRT_LINE;
            CEP.TRC(SCCGWA, DDRUPT.PRT_LINE);
            DDRUPT.PB_BAL = DDRPBBL.PSBK_BAL;
            DDRUPT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRUPT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTUPT();
            if (pgmRtn) return;
            if (WS_PRT_IDX > 0) {
                DDCOPUPT.ACNO.ACNO_1 = 'T';
                DDCOPUPT.ACNO.ACNO_21 = DDRVCH.KEY.CUS_AC;
                IBS.init(SCCGWA, SCCFMT);
                SCCFMT.FMTID = K_PRT_UPT_FMT;
                SCCFMT.DATA_PTR = DDCOPUPT;
                SCCFMT.DATA_LEN = 42;
                IBS.FMT(SCCGWA, SCCFMT);
                R000_MPAGE_OUTPUT();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTUPT();
            if (pgmRtn) return;
        }
        if (WS_PRT_IDX > 0) {
            DDRVCH.LAST_PB_CCY = DDRUPT.CCY;
            DDRVCH.LAST_PB_BAL = DDRPBBL.PSBK_BAL;
        }
        T000_ENDBR_DDTUPT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        if (DDRVCH.UPT_CNT > 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PB_FULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_ORG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLOSS);
        BPCPLOSS.DATA_INFO.LOS_NO = DDRVCH.LOST_NO;
        BPCPLOSS.INFO.FUNC = 'I';
        BPCPLOSS.INFO.INDEX_FLG = "1";
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        if (BPCPLOSS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
        R000_GET_BBR_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_BR_NOT_MATCH);
        }
    }
    public void R000_GET_BBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void R000_CHK_PSBK_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        CEP.TRC(SCCGWA, DDCIPSBK.FUNC);
        if (DDRVCH.PSBK_NO.trim().length() > 0 
            && DDRVCH.PSBK_STS != 'C') {
            if (DDCIPSBK.FUNC == 'D' 
                || DDCIPSBK.FUNC == 'M' 
                || DDCIPSBK.FUNC == 'B' 
                || DDCIPSBK.FUNC == 'P') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (!JIBS_tmp_str[0].equalsIgnoreCase("111804")) {
                    if (DDRVCH.PSBK_STS == 'U') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_UNWRITE_LOST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDRVCH.PSBK_STS == 'W') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (DDCIPSBK.UPT_TXN_TYPE == 'D') {
                if (DDRVCH.PSBK_STS == 'U' 
                    && DDCIPSBK.FUNC != 'W' 
                    && DDCIPSBK.FUNC != 'R' 
                    && DDCIPSBK.FUNC != 'T') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_UNWRITE_LOST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDRVCH.PSBK_STS == 'W' 
                    && DDCIPSBK.FUNC != 'R' 
                    && DDCIPSBK.FUNC != 'C' 
                    && DDCIPSBK.FUNC != 'D' 
                    && DDCIPSBK.FUNC != 'T') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DDRVCH.PSBK_STS == 'N' 
                && DDCIPSBK.FUNC == 'R') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_NOT_LOST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRVCH.PSBK_STS == 'N' 
            && DDCIPSBK.FUNC == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PSBK_NOT_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_STS == 'C') {
            if (DDCIPSBK.FUNC == 'L' 
                || DDCIPSBK.FUNC == 'U' 
                || DDCIPSBK.FUNC == 'W' 
                || DDCIPSBK.FUNC == 'R' 
                || DDCIPSBK.FUNC == 'I' 
                || DDCIPSBK.FUNC == '1' 
                || DDCIPSBK.FUNC == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NOISUE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_PRT_HEAD_PAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPHAD);
        DDCOPHAD.AC = DDCIPSBK.AC;
        DDCOPHAD.ENAME = DDCIPSBK.AC_ENAME;
        DDCOPHAD.CNAME = DDCIPSBK.AC_CNAME;
        T000_READ_DDTMST1();
        if (pgmRtn) return;
        DDCOPHAD.BR = DDRMST.OPEN_DP;
        DDCOPHAD.PSBK_NO = DDCIPSBK.PSBK_NO;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_SEQ);
        DDCOPHAD.PSBK_SEQ = DDRVCH.PSBK_SEQ;
        DDCOPHAD.PAY_MTH = DDCIPSBK.PAY_MTH;
        DDCOPHAD.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOPHAD.TL_ID = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDCIPSBK.PAY_MTH);
        CEP.TRC(SCCGWA, DDCOPHAD.PAY_MTH);
        CEP.TRC(SCCGWA, DDCOPHAD);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRT_COV_FMT;
        SCCFMT.DATA_PTR = DDCOPHAD;
        SCCFMT.DATA_LEN = 581;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_WRITE_MAGNET() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPMAG);
        DDCOPMAG.AC = DDCIPSBK.AC;
        DDCOPMAG.PSBK_NO = WS_PSBK_NO;
        DDCOPMAG.PSBK_SEQ = WS_PSBK_SEQ;
        CEP.TRC(SCCGWA, DDCOPMAG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_WRITE_MAGNET_FMT;
        SCCFMT.DATA_PTR = DDCOPMAG;
        SCCFMT.DATA_LEN = 52;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_PRT_FIRST_BAL_CARDPB() throws IOException,SQLException,Exception {
        DDCOPUPT.CNT = 0;
        IBS.init(SCCGWA, DDRCCY);
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_RCCY_END != 'E' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, DDRPBBL);
            DDRPBBL.KEY.CUS_AC = DDCIPSBK.AC;
            DDRPBBL.KEY.CCY = DDRCCY.CCY;
            DDRPBBL.KEY.CCY_TYPE = DDRCCY.CCY_TYPE;
            T000_READ_UPD_DDTPBBL();
            if (pgmRtn) return;
            DDRPBBL.PSBK_BAL = DDRCCY.CURR_BAL;
            DDRPBBL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPBBL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            if (WS_PBBL_FLAG == 'N') {
                T000_WRITE_DDTPBBL();
                if (pgmRtn) return;
            }
            DDCOPUPT.CNT += 1;
            if (DDCIPSBK.VCH_TYPE != '3') {
                DDRVCH.PRT_LINE += 1;
            }
            IBS.init(SCCGWA, DDCOUPT);
            DDCOUPT.DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCOUPT.CCY = DDRCCY.CCY;
            DDCOUPT.CCY_TYPE = DDRCCY.CCY_TYPE;
            DDCOUPT.NUM = DDRVCH.PRT_LINE;
            DDCOUPT.AMT = 0;
            DDCOUPT.DRCR_FLG = 'C';
            DDCOUPT.AC_ATTR = DDCIPSBK.AC_ATTR;
            DDCOUPT.BALANCE = DDRCCY.CURR_BAL;
            DDCOUPT.REF = DDCIPSBK.UPT_MMO;
            DDCOUPT.TL_ID = SCCGWA.COMM_AREA.TL_ID;
            R000_MPAGE_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void R000_SET_USING_PB_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIPSBK.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void R000_RESET_VA_HLD_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIPSBK.CARD_NO);
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCIPSBK.CARD_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        IBS.init(SCCGWA, DCCIMSTU);
        DCCIMSTU.VIA_AC = DCCPACTY.OUTPUT.VIA_AC;
        if (DCCIMSTU.STS_WORD == null) DCCIMSTU.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTU.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTU.STS_WORD += " ";
        JIBS_tmp_str[0] = "" + 0;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCIMSTU.STS_WORD = DCCIMSTU.STS_WORD.substring(0, 7 - 1) + JIBS_tmp_str[0] + DCCIMSTU.STS_WORD.substring(7 + 1 - 1);
        S000_CALL_DCZIMSTU();
        if (pgmRtn) return;
    }
    public void R000_PRT_LOST_ADVICE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPLST);
        DDCOPLST.LOST_FUNC = DDRVCH.PSBK_STS;
        DDCOPLST.AC = DDCIPSBK.AC;
        DDCOPLST.ENAME = DDCIPSBK.AC_ENAME;
        DDCOPLST.CNAME = DDCIPSBK.AC_CNAME;
        DDCOPLST.PSBK_NO = DDRVCH.PSBK_NO;
        DDCOPLST.LOST_NO = DDRVCH.LOST_NO;
        DDCOPLST.HLD_FLG = DDCIPSBK.HLD_FLG;
        DDCOPLST.R_TEL_NO = DDCIPSBK.R_TEL_NO;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        CEP.TRC(SCCGWA, DDCOPLST.PSBK_NO);
        CEP.TRC(SCCGWA, DDRVCH.LOST_NO);
        CEP.TRC(SCCGWA, DDCOPLST.LOST_NO);
        CEP.TRC(SCCGWA, DDCOPLST.HLD_FLG);
        if (DDRVCH.PSBK_STS == 'U') {
            DDCOPLST.DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            DDCOPLST.DATE = DDRVCH.W_LOST_DATE;
        }
        DDCOPLST.EXP_DATE = DDRVCH.LOST_EXP_DATE;
        if (DDCIPSBK.FUNC == 'W' 
            || DDCIPSBK.FUNC == 'U') {
            DDCOPLST.PSBK_FUNC = 'N';
        } else {
            if (DDCIPSBK.FUNC == 'R') {
                DDCOPLST.PSBK_FUNC = 'Y';
            }
        }
        CEP.TRC(SCCGWA, DDCOPLST);
        CEP.TRC(SCCGWA, DDCOPLST);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_LST_PSBK_FMT;
        SCCFMT.DATA_PTR = DDCOPLST;
        SCCFMT.DATA_LEN = 611;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_PRT_CHA_PSBK_ADVICE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPCHP);
        DDCOPCHP.AC = DDCIPSBK.AC;
        DDCOPCHP.CHA_REASON = DDCIPSBK.CHA_REASON;
        DDCOPCHP.ENAME = DDCIPSBK.AC_ENAME;
        DDCOPCHP.CNAME = DDCIPSBK.AC_CNAME;
        DDCOPCHP.PSBK_NO_OLD = DDCIPSBK.PSBK_NO;
        DDCOPCHP.PSBK_NO_NEW = DDCIPSBK.PSBK_NO_NEW;
        DDCOPCHP.LOST_NO = DDCIPSBK.LOST_NO;
        CEP.TRC(SCCGWA, DDCOPCHP);
        CEP.TRC(SCCGWA, DDCOPCHP.LOST_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_CHA_PSBK_FMT;
        SCCFMT.DATA_PTR = DDCOPCHP;
        SCCFMT.DATA_LEN = 591;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_PRT_CHA_LINE_ADVICE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPCHL);
        DDCOPCHL.AC = DDCIPSBK.AC;
        DDCOPCHL.CNAME = DDCIPSBK.AC_CNAME;
        DDCOPCHL.ENAME = DDCIPSBK.AC_ENAME;
        DDCOPCHL.PSBK_NO = DDCIPSBK.PSBK_NO;
        DDCOPCHL.PSBK_PAGE = DDCIPSBK.PSBK_PAGE;
        DDCOPCHL.PSBK_LINE = DDCIPSBK.PSBK_LINE;
        CEP.TRC(SCCGWA, DDCOPCHL.AC);
        CEP.TRC(SCCGWA, DDCOPCHL.CNAME);
        CEP.TRC(SCCGWA, DDCOPCHL.ENAME);
        CEP.TRC(SCCGWA, DDCOPCHL.PSBK_NO);
        CEP.TRC(SCCGWA, DDCOPCHL.PSBK_PAGE);
        CEP.TRC(SCCGWA, DDCOPCHL.PSBK_LINE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_CHA_LINE_FMT;
        SCCFMT.DATA_PTR = DDCOPCHL;
        SCCFMT.DATA_LEN = 562;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_INQ_PSBK_INFO_OUTPUT() throws IOException,SQLException,Exception {
        DDCIPSBK.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCIPSBK.SIGN_NO = DDRVCH.PAY_SIGN_NO;
        DDCIPSBK.PSBK_NO = DDRVCH.PSBK_NO;
        DDCIPSBK.ID_TYPE = DDRVCH.PAY_IDTYPE;
        DDCIPSBK.ID_NO = DDRVCH.PAY_IDNO;
        DDCIPSBK.UPT_CNT = DDRVCH.UPT_CNT;
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCOPINQ);
        DDCOPINQ.AC = DDRVCH.KEY.CUS_AC;
        DDCOPINQ.CNAME = DDCIPSBK.AC_CNAME;
        DDCOPINQ.ENAME = DDCIPSBK.AC_ENAME;
        DDCOPINQ.PSBK_STS = DDRVCH.PSBK_STS;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOPINQ.PWD_STS = '2';
            DDCOPINQ.PWD_STS2 = '1';
        } else {
            DDCOPINQ.PWD_STS2 = '0';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (!DDRMST.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            && !DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOPINQ.PWD_STS = '3';
        }
        CEP.TRC(SCCGWA, DDCOPINQ.PWD_STS);
        CEP.TRC(SCCGWA, DDCOPINQ.PWD_STS1);
        CEP.TRC(SCCGWA, DDCOPINQ.PWD_STS2);
        DDCOPINQ.PSBK_NO = DDRVCH.PSBK_NO;
        DDCOPINQ.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOPINQ.PAY_IDTYPE = DDRVCH.PAY_IDTYPE;
        DDCOPINQ.PAY_IDNO = DDRVCH.PAY_IDNO;
        DDCOPINQ.PAY_SIGN_NO = DDRVCH.PAY_SIGN_NO;
        DDCOPINQ.PRT_LINE = DDRVCH.PRT_LINE;
        WS_LINE = (short) (DDRVCH.PRT_LINE % DDCPBKS.DATA_TXT.PAGE_LINE);
        WS_PAGE = (short) ((DDRVCH.PRT_LINE - WS_LINE) / DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, WS_PAGE);
        CEP.TRC(SCCGWA, WS_LINE);
        if (WS_LINE == 0) {
            if (WS_PAGE > 0) {
                DDCOPINQ.P_LINE = DDCPBKS.DATA_TXT.PAGE_LINE;
            } else {
                DDCOPINQ.P_LINE = WS_LINE;
            }
            DDCOPINQ.P_PAGE = WS_PAGE;
        } else {
            WS_PAGE = (short) (WS_PAGE + 1);
            DDCOPINQ.P_LINE = WS_LINE;
            DDCOPINQ.P_PAGE = WS_PAGE;
        }
        CEP.TRC(SCCGWA, DDCOPINQ.P_LINE);
        CEP.TRC(SCCGWA, DDCOPINQ.P_PAGE);
        DDCOPINQ.LAST_PB_CCY = DDRVCH.LAST_PB_CCY;
        DDCOPINQ.LAST_PB_BAL = DDRVCH.LAST_PB_BAL;
        DDCOPINQ.W_LOST_DATE = DDRVCH.W_LOST_DATE;
        DDCOPINQ.LOST_NO = DDRVCH.LOST_NO;
        DDCOPINQ.PSW_LOST_NO = DDRVCH.PWD_LOST_NO;
        DDCOPINQ.PSBK_FLG = DDRVCH.VCH_TYPE;
        DDCOPINQ.UPT_CNT = DDRVCH.UPT_CNT;
        CEP.TRC(SCCGWA, DDRVCH.LOST_NO);
        CEP.TRC(SCCGWA, DDCOPINQ.LOST_NO);
        CEP.TRC(SCCGWA, DDCOPINQ.PSBK_FLG);
        CEP.TRC(SCCGWA, DDCOPCHP);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_PSBK_FMT;
        SCCFMT.DATA_PTR = DDCOPINQ;
        SCCFMT.DATA_LEN = 747;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_SET_PASSWORD() throws IOException,SQLException,Exception {
        if (DDCIPSBK.PAY_MTH == '1' 
            || DDCIPSBK.PAY_MTH == '4') {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.FUNC = 'A';
            DDCIMPAY.PAY_MTH = '1';
            CEP.TRC(SCCGWA, DDCIPSBK.AC);
            DDCIMPAY.AC = DDCIPSBK.AC;
            DDCIMPAY.PSWD_NEW = DDCIPSBK.PSWD;
            DDCIMPAY.ID_TYPE = DDCIPSBK.ID_TYPE;
            DDCIMPAY.ID_NO = DDCIPSBK.ID_NO;
            DDCIMPAY.AC_CNAME = DDCIPSBK.AC_CNAME;
            DDCIMPAY.NEW_CARD_NO = "123456789012";
            S000_CALL_DDZIMPAY();
            if (pgmRtn) return;
        }
    }
    public void R000_ADD_AGEN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDCIPSBK.FUNC == 'U' 
            || DDCIPSBK.FUNC == 'W' 
            || DDCIPSBK.FUNC == '1' 
            || DDCIPSBK.FUNC == '2') {
            CICSAGEN.TX_CODE = "0115540";
        }
        if (DDCIPSBK.FUNC == '5' 
            || DDCIPSBK.FUNC == '6') {
            CICSAGEN.TX_CODE = "0115550";
        }
        if (DDCIPSBK.FUNC == 'C' 
            && DDCIPSBK.CHA_REASON == 'L') {
            CICSAGEN.TX_CODE = "0115530";
        }
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        CICSAGEN.START_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.LAST_DT = 20991231;
        CICSAGEN.AGENT_TP = "04";
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void T000_READ_CHECK_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.PAY_SIGN_NO = DDCIPSBK.SIGN_NO;
        DDTVCH_BR.rp = new DBParm();
        DDTVCH_BR.rp.TableName = "DDTVCH";
        DDTVCH_BR.rp.where = "PAY_SIGN_NO = :DDRVCH.PAY_SIGN_NO";
        IBS.STARTBR(SCCGWA, DDRVCH, this, DDTVCH_BR);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIPSBK.AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.col = "CUS_AC,PAY_TYPE,PAY_IDTYPE,PAY_IDNO, PAY_SIGN_NO,PSBK_NO,PSBK_STS,PSBK_SEQ,PRT_LINE, UPT_CNT,UPT_LAST_NO,LAST_PB_CCY,LAST_PB_BAL, W_LOST_DATE,LOST_EXP_DATE, LOST_NO,PWD_LOST_NO";
        DDTVCH_RD.fst = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTVCH() throws IOException,SQLException,Exception {
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIPSBK.AC;
        CEP.TRC(SCCGWA, DDCIPSBK.AC);
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (DDCIPSBK.PSBK_NO.trim().length() > 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (DDCIPSBK.FUNC != 'S') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH1_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    T000_DZB_AC_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void T000_DZB_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCIPSBK.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        CEP.TRC(SCCGWA, CICQACRL.DATA.REL_AC_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.AC);
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            IBS.init(SCCGWA, DDRVCH);
            DDRVCH.KEY.CUS_AC = CICQACRL.O_DATA.O_AC_NO;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
            DDTVCH_RD = new DBParm();
            DDTVCH_RD.TableName = "DDTVCH";
            DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
            DDTVCH_RD.upd = true;
            IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            }
        } else {
        }
    }
    public void T000_READ_BPTLOSS_UPDATE() throws IOException,SQLException,Exception {
    }
    public void T000_REWRITE_DDTVCH() throws IOException,SQLException,Exception {
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.REWRITE(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void T000_WRITE_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRUPT, DDTUPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_UPT_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIPSBK.AC;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS,AC_STS_WORD,CROS_CR_FLG,OPEN_BK";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIPSBK.AC;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS,OPEN_DP";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS_WORD, UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 69;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCOUPT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOUPT);
        SCCMPAG.DATA_LEN = 69;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_PSBK_LOST_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBLST);
        DDCPBLST.KEY.TYP = "PDD02";
        DDCPBLST.KEY.CD = "LOST";
        BPCPRMR.DAT_PTR = DDCPBLST;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBLST.DATA_TXT.ULOST_EXP_DAYS);
        CEP.TRC(SCCGWA, DDCPBLST.DATA_TXT.WLOST_LOST_DAYS);
        CEP.TRC(SCCGWA, DDCPBLST.DATA_TXT.WLOST_LOST_BALANCE);
    }
    public void R000_GET_PASSBOOK_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBKS);
        DDCPBKS.KEY.TYP = "PDD01";
        DDCPBKS.KEY.CD = "PASSBOOK";
        BPCPRMR.DAT_PTR = DDCPBKS;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_PAGE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
    }
    public void T000_REWRITE_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.REWRITE(SCCGWA, DDRUPT, DDTUPT_RD);
    }
    public void T000_STARTBR_DDTUPT() throws IOException,SQLException,Exception {
        DDRUPT.KEY.CUS_AC = DDCIPSBK.AC;
        DDRUPT.PRT_FLAG = '0';
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PRT_FLAG = :DDRUPT.PRT_FLAG";
        DDTUPT_BR.rp.upd = true;
        DDTUPT_BR.rp.order = "UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTUPT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRUPT, this, DDTUPT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_UPT_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_UPT_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DDTUPT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTUPT_BR);
    }
    public void T000_DELETE_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND UPT_NO = :DDRUPT.KEY.UPT_NO";
        IBS.DELETE(SCCGWA, DDRUPT, this, DDTUPT_RD);
    }
    public void T000_STARTBR_DDTPBBL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIPSBK.AC);
        DDRPBBL.KEY.CUS_AC = DDCIPSBK.AC;
        DDTPBBL_BR.rp = new DBParm();
        DDTPBBL_BR.rp.TableName = "DDTPBBL";
        DDTPBBL_BR.rp.where = "CUS_AC = :DDRPBBL.KEY.CUS_AC";
        IBS.STARTBR(SCCGWA, DDRPBBL, this, DDTPBBL_BR);
    }
    public void T000_READNEXT_DDTPBBL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRPBBL, this, DDTPBBL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PBBL_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PBBL_FLAG = 'N';
        }
    }
    public void T000_READ_UPD_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.col = "PSBK_BAL";
        DDTPBBL_RD.upd = true;
        IBS.READ(SCCGWA, DDRPBBL, DDTPBBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PBBL_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PBBL_FLAG = 'N';
        }
    }
    public void T000_WRITE_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRPBBL, DDTPBBL_RD);
    }
    public void T000_REWRITE_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        IBS.REWRITE(SCCGWA, DDRPBBL, DDTPBBL_RD);
    }
    public void R000_SET_PSB_STS_TO_USE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = "A00015";
        BPCUBUSE.BEG_NO = WS_PSBK_NO;
        BPCUBUSE.END_NO = WS_PSBK_NO;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void R000_GET_PB_AMT() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_RCCY_END != 'E') {
            if (DDRCCY.CURR_BAL != 0) {
                if (!DDRCCY.CCY.equalsIgnoreCase("156")) {
                    IBS.init(SCCGWA, BPCEX);
                    CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
                    BPCEX.BUY_CCY = DDRCCY.CCY;
                    BPCEX.BUY_AMT = DDRCCY.CURR_BAL;
                    BPCEX.SELL_CCY = "156";
                    S000_CALL_BPZSEX();
                    if (pgmRtn) return;
                    WS_AMT = BPCEX.SELL_AMT;
                } else {
                    WS_AMT = DDRCCY.CURR_BAL;
                }
            }
            WS_PB_AMT += WS_AMT;
            CEP.TRC(SCCGWA, WS_PB_AMT);
            WS_AMT = 0;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PB_AMT);
    }
    public void R000_SIGN_NO_CHECK() throws IOException,SQLException,Exception {
        T000_READ_CHECK_DDTVCH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            R000_SIGN_NO_IN();
            if (pgmRtn) return;
        }
    }
    public void R000_SIGN_NO_STS_TO_USE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = "BPBV03";
        BPCUBUSE.BEG_NO = DDCIPSBK.SIGN_NO;
        BPCUBUSE.END_NO = DDCIPSBK.SIGN_NO;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void R000_SIGN_NO_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVIN);
        BPCUBVIN.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVIN.TYPE = '0';
        BPCUBVIN.BV_CODE = "BPBV04";
        BPCUBVIN.BEG_NO = DDCIPSBK.SIGN_NO;
        BPCUBVIN.END_NO = DDCIPSBK.SIGN_NO;
        BPCUBVIN.NUM = 1;
        BPCUBVIN.VB_FLG = '0';
        BPCUBVIN.BV_STS = '0';
        BPCUBVIN.AC_TYP = '0';
        S000_CALL_BPZUBVIN();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUBVIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-IN      ", BPCUBVIN);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCIPSBK.AC;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READ_DDTCCY_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDRUPT.KEY.CUS_AC;
        DDRCCY.CCY = DDRUPT.CCY;
        DDRCCY.CCY_TYPE = DDRUPT.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        if (DDRVCH.VCH_TYPE == '2') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = DDRUPT.KEY.CUS_AC;
            CICQACRL.DATA.AC_REL = "12";
            CICQACRL.FUNC = '3';
            CICQACRL.FUNC = 'I';
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.RC.RC_CODE == 0) {
                DDRCCY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
            }
        }
        DDRCCY.CCY = DDRUPT.CCY;
        DDRCCY.CCY_TYPE = DDRUPT.CCY_TYPE;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        WS_CCY_FLAG = 'Y';
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLAG = 'N';
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RCCY_END = 'E';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY  ", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-IAMST", DCCIMSTU);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_INF() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIPSBK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIPSBK=");
            CEP.TRC(SCCGWA, DDCIPSBK);
        }
    } //FROM #ENDIF
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
