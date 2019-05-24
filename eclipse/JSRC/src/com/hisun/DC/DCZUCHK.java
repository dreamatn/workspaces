package com.hisun.DC;

import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCHK {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm TDTCMST_RD;
    DBParm DCTIAMST_RD;
    DBParm DDTFBID_RD;
    brParm DCTIAACR_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm DDTHLD_RD;
    DBParm DCTIAACR_RD;
    brParm DDTFBID_BR = new brParm();
    brParm DDTHLD_BR = new brParm();
    DBParm TDTBVT_RD;
    brParm DCTIACCY_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "HOLD OR BAN AUTO RELEASE BAN OR DEBIT";
    String K_STS_TABLE_APP1 = "DD";
    String K_STS_TABLE_APP2 = "TD";
    String K_STS_TABLE_APP3 = "DC";
    String K_CHK_STS_TBL1 = "7500";
    String K_CHK_STS_TBL2 = "5602";
    String K_CHK_STS_TBL3 = "7580";
    String WS_ERR_MSG = " ";
    String WS_DD_AC = " ";
    String WS_TD_AC = " ";
    String WS_REF_NO = " ";
    String WS_HLD_AC = " ";
    int WS_HLD_SEQ = 0;
    char WS_HLD_TYPE = ' ';
    char WS_HLD_AC_TYPE = ' ';
    char WS_AC_TYPE = ' ';
    char WS_AC_STS1 = ' ';
    char WS_AC_STS2 = ' ';
    char WS_AC_STS3 = ' ';
    char WS_AC_STS4 = ' ';
    char WS_AC_STS5 = ' ';
    char WS_AC_STS6 = ' ';
    char WS_IAACR_FLG = ' ';
    char WS_FBID_FLG = ' ';
    char WS_DDTHLD_REC = ' ';
    char WS_CHK_MST_FLG = ' ';
    char WS_TDTBVT_REC = ' ';
    char WS_IACCY_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRFBID DDRFBID = new DDRFBID();
    TDRCMST TDRCMST = new TDRCMST();
    TDRBVT TDRBVT = new TDRBVT();
    DDRHLD DDRHLD = new DDRHLD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCIACCT DCCIACCT = new DCCIACCT();
    CICQACAC CICQACAC = new CICQACAC();
    CICACCU CICACCU = new CICACCU();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCBINF SCCBINF = new SCCBINF();
    TDRSMST TDRSMST = new TDRSMST();
    int WS_HLD_CNT = 0;
    int WS_MST_CNT = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCUCHK DCCUCHK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCUCHK DCCUCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCHK = DCCUCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCHK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_ACAC_INFO();
        if (pgmRtn) return;
        B025_INQ_CI_INF_PROC();
        if (pgmRtn) return;
        if (!(DCCUCHK.DATA.OPT_TYP == '4' 
            || DCCUCHK.DATA.OPT_TYP == '5')) {
            B030_GET_AC_STS_PROC();
            if (pgmRtn) return;
        } else {
            B040_DUCT_CHK_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCHK.DATA.FUNC);
        CEP.TRC(SCCGWA, DCCUCHK.DATA.OPT_TYP);
        CEP.TRC(SCCGWA, DCCUCHK.DATA.SPR_TYP);
        CEP.TRC(SCCGWA, DCCUCHK.DATA.AC);
        CEP.TRC(SCCGWA, DCCUCHK.DATA.SEQ);
        CEP.TRC(SCCGWA, DCCUCHK.DATA.HLD_NO);
        if (DCCUCHK.DATA.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.OPT_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPT_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.FUNC != 'I' 
            && DCCUCHK.DATA.FUNC != 'P') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.OPT_TYP != '1' 
            && DCCUCHK.DATA.OPT_TYP != '2' 
            && DCCUCHK.DATA.OPT_TYP != '3' 
            && DCCUCHK.DATA.OPT_TYP != '4' 
            && DCCUCHK.DATA.OPT_TYP != '5') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPT_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.SPR_TYP != '1' 
            && DCCUCHK.DATA.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHK.DATA.OPT_TYP == '5' 
            && DCCUCHK.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_ACAC_INFO() throws IOException,SQLException,Exception {
        if (DCCUCHK.DATA.SEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = DCCUCHK.DATA.AC;
            CICQACAC.DATA.AGR_SEQ = DCCUCHK.DATA.SEQ;
            CICQACAC.FUNC = 'S';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = DCCUCHK.DATA.AC;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        }
    }
    public void B025_INQ_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DCCUCHK.DATA.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_TYP == '1' 
            && DCCUCHK.DATA.OPT_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PER_NOT_SET_CR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((CICACCU.DATA.CI_TYP != '1') 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            && (DCCUCHK.DATA.OPT_TYP == '1' 
            || DCCUCHK.DATA.OPT_TYP == '2' 
            || DCCUCHK.DATA.OPT_TYP == '3') 
            && DCCUCHK.DATA.SEQ == 0) {
            CEP.TRC(SCCGWA, "000");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_COMP_CARD_NOT_BAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((CICACCU.DATA.CI_TYP != '1') 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            && (DCCUCHK.DATA.OPT_TYP == '4' 
            || DCCUCHK.DATA.OPT_TYP == '5') 
            && DCCUCHK.DATA.SEQ == 0) {
            CEP.TRC(SCCGWA, "000");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_COMP_CARD_NOT_BAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_STS_PROC() throws IOException,SQLException,Exception {
        B030_20_AC_CHK();
        if (pgmRtn) return;
        B030_30_AC_CHK();
        if (pgmRtn) return;
    }
    public void B030_10_10_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DCRIAACR.FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCRIAACR.SUB_AC;
            CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(09 - 1, 09 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_HAS_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRIAACR.FRM_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = DCRIAACR.SUB_AC;
            CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
            CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW.substring(02 - 1, 02 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_HAS_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_20_AC_CHK() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCCUCHK.DATA.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            if (DDRMST.AC_PURP.trim().length() == 0) {
            } else {
                if (DCCUCHK.DATA.SPR_TYP == '1' 
                    && DCCUCHK.DATA.OPT_TYP == '3') {
                    R000_AC_PURP_CHECK();
                    if (pgmRtn) return;
                }
            }
            if (DCCUCHK.DATA.FUNC == 'I') {
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_MST_ALR_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_DD_AC = DDRMST.KEY.CUS_AC;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS2 = '1';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS3 = '1';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS1 = '1';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS6 = '1';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS4 = '1';
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = DCCUCHK.DATA.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && TDRBVT.STSW.substring(05 - 1, 05 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCCUCHK.DATA.SPR_TYP == '2') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_REP_HAS_IMP_BNK;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_REP_HAS_IMP_LAW;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DCCUCHK.DATA.FUNC == 'I') {
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
            }
            if (TDRCMST.STS == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_MST_ALR_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS6 = '1';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS4 = '1';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS2 = '1';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS3 = '1';
            }
            WS_TD_AC = TDRCMST.KEY.AC_NO;
        }
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_GET_HLD_CNT();
        if (pgmRtn) return;
        R000_AC_INT_CHK_PROC();
        if (pgmRtn) return;
    }
    public void B030_30_AC_CHK() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCCUCHK.DATA.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            WS_DD_AC = DDRMST.KEY.CUS_AC;
            if (DDRMST.AC_PURP.trim().length() == 0) {
            } else {
                if (DCCUCHK.DATA.SPR_TYP == '1' 
                    && DCCUCHK.DATA.OPT_TYP == '3') {
                    R000_AC_PURP_CHECK();
                    if (pgmRtn) return;
                }
            }
            if (DCCUCHK.DATA.FUNC == 'I') {
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_MST_ALR_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_STS4 = '1';
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1") 
                && (DCCUCHK.DATA.OPT_TYP == '1' 
                || DCCUCHK.DATA.OPT_TYP == '2')) {
                CEP.TRC(SCCGWA, "111");
                WS_ERR_MSG = DCCMSG_ERROR_MSG.ENT_AC_NOT_DR_BAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (CICACCU.DATA.CI_TYP != '1') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(60 - 1, 60 + 1 - 1));
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1));
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1));
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1));
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                if (!DDRMST.AC_STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_CHK_MST_FLG = 'N';
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_AC_STS2 = '1';
                    }
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_AC_STS3 = '1';
                    }
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_AC_STS1 = '1';
                    }
                }
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = DCCUCHK.DATA.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            if (DCCUCHK.DATA.FUNC == 'I') {
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
            }
            if (TDRCMST.STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_MST_ALR_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_GET_HLD_CNT();
        if (pgmRtn) return;
    }
    public void B040_DUCT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCUCHK.DATA.OPT_TYP == '4') {
            B040_01_STR_DUCT_CHK();
            if (pgmRtn) return;
        } else {
            B040_02_REL_DUCT_CHK();
            if (pgmRtn) return;
        }
    }
    public void B040_01_STR_DUCT_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRHLD.HLD_STS = 'N';
        DDRHLD.SPR_BR_TYP = '1';
        T000_STARTBR_DDTHLD();
        if (pgmRtn) return;
        T000_READNEXT_DDTHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DDTHLD_REC);
        while (WS_DDTHLD_REC != 'N') {
            CEP.TRC(SCCGWA, DDRHLD.HLD_TYP);
            if (DDRHLD.HLD_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "COMPUTED");
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTHLD();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCCUCHK.DATA.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_PURP.trim().length() == 0) {
            } else {
                R000_AC_PURP_CHECK();
                if (pgmRtn) return;
            }
            R000_CHECK_STS_TBL();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            CEP.TRC(SCCGWA, DDRMST);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = DCCUCHK.DATA.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = DCCUCHK.DATA.AC;
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            if (WS_TDTBVT_REC == 'Y') {
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BVT_NOT_DUCT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            R000_CHECK_STS_TBL();
            if (pgmRtn) return;
            if (TDRCMST.STS == 'C' 
                || TDRCMST.STS == 'R') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_02_REL_DUCT_CHK() throws IOException,SQLException,Exception {
        B040_02_GET_HLD_INF();
        if (pgmRtn) return;
        B040_02_REL_CHK_PROC();
        if (pgmRtn) return;
    }
    public void B040_02_GET_HLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCUCHK.DATA.HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        if (WS_DDTHLD_REC == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_HLD_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_HLD_SEQ = DDRHLD.HLD_SEQ;
        WS_HLD_TYPE = DDRHLD.HLD_TYP;
        if (CICACCU.DATA.CI_TYP != '1') {
            if (DDRHLD.SPR_BR_TYP != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_BR_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRHLD.HLD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_02_REL_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_HLD_AC;
        DDRHLD.HLD_STS = 'N';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_SEQ = WS_HLD_SEQ;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, WS_HLD_SEQ);
        T000_STARTBR_DDTHLD_BY_HLDSEQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTHLD();
        if (pgmRtn) return;
        while (WS_DDTHLD_REC != 'N') {
            if (DDRHLD.HLD_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTHLD();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCCUCHK.DATA.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_PURP.trim().length() == 0) {
            } else {
                R000_AC_PURP_CHECK();
                if (pgmRtn) return;
            }
            R000_CHECK_STS_TBL();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            if (WS_TDTBVT_REC == 'Y') {
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BVT_NOT_DUCT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            R000_CHECK_STS_TBL();
            if (pgmRtn) return;
            if (TDRCMST.STS == 'C' 
                || TDRCMST.STS == 'R') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_AC_INT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCUCHK.DATA.OPT_TYP == '1') {
            R000_DEBIT_CHECK();
            if (pgmRtn) return;
        } else if (DCCUCHK.DATA.OPT_TYP == '2' 
                && DCCUCHK.DATA.SPR_TYP == '1') {
            R000_LAW_BAN_CHECK();
            if (pgmRtn) return;
        } else if (DCCUCHK.DATA.OPT_TYP == '2' 
                && DCCUCHK.DATA.SPR_TYP == '2') {
            R000_BNK_BAN_CHECK();
            if (pgmRtn) return;
        } else if (DCCUCHK.DATA.OPT_TYP == '3') {
            R000_HOLD_CHECK();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPTION TYPE(" + DCCUCHK.DATA.OPT_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void R000_DEBIT_CHECK() throws IOException,SQLException,Exception {
        if (WS_AC_STS2 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEBIT_AC_HAS_BNK_BAN;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS3 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEBIT_AC_HAS_LAW_BAN;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS1 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEBIT_AC_HAS_DEBIT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS4 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEBIT_AC_HAS_HOLD;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS6 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEBIT_AC_HAS_LAW_HLD;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_CHECK_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void R000_LAW_BAN_CHECK() throws IOException,SQLException,Exception {
        if (WS_AC_STS2 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_BNK_BAN_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_HAS_BNK_BAN;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS3 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_LAW_BAN_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_HAS_LAW_BAN;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS1 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEBI_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEB_REL_DEB;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS4 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_HAS_HOLD_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_HAS_HOLD;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS6 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAN_AC_HAS_LAW_HOLD;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_CHECK_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        if (WS_AC_STS1 == '1' 
            && DCCUCHK.DATA.FUNC == 'P') {
            R000_UPD_AC_STS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_BNK_BAN_CHECK() throws IOException,SQLException,Exception {
        if (WS_AC_STS2 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_HAS_BANK_BAN;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS3 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_LAW_BAN_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_HAS_LAW_BAN;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS1 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEBI_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEB_REL_DEB;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS4 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_HAS_HOLD_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_HAS_HOLD;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS6 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_AC_HAS_LAW_HOLD;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_CHECK_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        if (WS_AC_STS1 == '1' 
            && DCCUCHK.DATA.FUNC == 'P') {
            R000_UPD_AC_STS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_HOLD_CHECK() throws IOException,SQLException,Exception {
        if (WS_AC_STS5 == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MST_AC_HAS_HOLD;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_AC_STS4 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_HOLD_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_HOLD;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS6 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_LAW_HOLD_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_LAW_HOLD;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS3 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_LAW_BAN_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_LAW_BAN;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS2 == '1') {
            CEP.TRC(SCCGWA, DCCUCHK.DATA.SPR_TYP);
            if (DCCUCHK.DATA.SPR_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_BANK_BAN_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_STOP_STS_E;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_AC_STS1 == '1') {
            if (DCCUCHK.DATA.FUNC == 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEBI_AUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HAS_DEB_REL_DEB;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_CHECK_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        if (DCCUCHK.DATA.FUNC == 'P' 
            && (WS_AC_STS2 == '1' 
            || WS_AC_STS1 == '1')) {
            R000_UPD_AC_STS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_UPD_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (DCCUCHK.DATA.OPT_TYP == '3' 
                && WS_AC_STS2 == '1') {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
            }
            if (WS_AC_STS1 == '1') {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 12 - 1) + "0" + DDRCCY.STS_WORD.substring(12 + 1 - 1);
            }
            T000_UPDATE_DDTCCY();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRFBID);
            DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_STARTBR_DDTFBID();
            if (pgmRtn) return;
            T000_READNEXT_DDTFBID();
            if (pgmRtn) return;
            while (WS_FBID_FLG != 'N') {
                T000_UPDATE_DDTFBID();
                if (pgmRtn) return;
                if (DDRFBID.TYPE == '1') {
                    DCCUCHK.DATA.REL_FB_FLG = 'Y';
                } else {
                    DCCUCHK.DATA.REL_DB_FLG = 'Y';
                }
                T000_READNEXT_DDTFBID();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTFBID();
            if (pgmRtn) return;
        } else {
            if (DCCUCHK.DATA.OPT_TYP == '3' 
                && WS_AC_STS2 == '1') {
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                TDRCMST.STSW = TDRCMST.STSW.substring(0, 7 - 1) + "0" + TDRCMST.STSW.substring(7 + 1 - 1);
            }
            TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_UPDATE_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRFBID);
            DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_STARTBR_DDTFBID();
            if (pgmRtn) return;
            T000_READNEXT_DDTFBID();
            if (pgmRtn) return;
            while (WS_FBID_FLG != 'N') {
                T000_UPDATE_DDTFBID();
                if (pgmRtn) return;
                T000_READNEXT_DDTFBID();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTFBID();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP1;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP2;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRCMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, TDRCMST.STSW);
        }
        if (DCCUCHK.DATA.OPT_TYP == '3') {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL1;
        } else {
            if (DCCUCHK.DATA.OPT_TYP == '1' 
                || DCCUCHK.DATA.OPT_TYP == '2') {
                BPCFCSTS.TBL_NO = K_CHK_STS_TBL2;
            }
        }
        if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
        JIBS_tmp_int = CICACCU.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.STSW.substring(0, 08) + BPCFCSTS.STATUS_WORD.substring(8);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 8));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(101 - 1, 101 + 99 - 1));
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void R000_AC_PURP_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIACCT);
        DCCIACCT.DATA.AC_PURP = DDRMST.AC_PURP;
        if (DCCUCHK.DATA.OPT_TYP == '3') {
            DCCIACCT.DATA.OPT = 'H';
        } else {
            DCCIACCT.DATA.OPT = 'D';
        }
        S000_CALL_DCZIACCT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_PURP);
    }
    public void R000_CHK_MASTER_CCY_STS() throws IOException,SQLException,Exception {
        if (WS_AC_TYPE == 'V') {
            CEP.TRC(SCCGWA, DCRIACCY.HLD_MTH);
            if (DCRIACCY.HLD_MTH == '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ENTY_AC_HAS_AMT_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, DCRIACCY.HLD_MTH);
            if (DCRIACCY.HLD_MTH == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_HAS_AMT_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS,AC_STS_WORD,AC_PURP,LAST_DATE,LAST_TLR, UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "STS,STS_WORD";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_UPDATE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_UPDATE_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.REWRITE(SCCGWA, DCRIAMST, DCTIAMST_RD);
    }
    public void T000_UPDATE_DDTFBID() throws IOException,SQLException,Exception {
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = K_HIS_REMARKS;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.REWRITE(SCCGWA, DDRFBID, DDTFBID_RD);
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :DCRIAACR.SUB_AC "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_BR.rp.upd = true;
        DCTIAACR_BR.rp.order = "VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "AC_STS,STS_WORD";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.col = "STS,STSW";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_HLD_CNT() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-HLD-CNT=COUNT(*)";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND SPR_BR_TYP = '1' "
            + "AND HLD_TYP = '1' "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, WS_HLD_CNT);
        if (WS_HLD_CNT > 0) {
            WS_AC_STS6 = '1';
        }
    }
    public void T000_GET_MST_CNT() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.set = "WS-MST-CNT=COUNT(*)";
        DCTIAACR_RD.where = "SUB_AC = :DCRIAACR.SUB_AC "
            + "AND ACCR_FLG = '1'";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
    }
    public void T000_STARTBR_DDTFBID() throws IOException,SQLException,Exception {
        if (DCCUCHK.DATA.OPT_TYP == '3') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND ( TYPE = '1' "
                + "OR TYPE = '2' ) "
                + "AND ORG_TYP = '1' "
                + "AND STS = '1'";
            DDTFBID_BR.rp.upd = true;
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '2' "
                + "AND ORG_TYP = '1' "
                + "AND STS = '1'";
            DDTFBID_BR.rp.upd = true;
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTFBID() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRFBID, this, DDTFBID_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FBID_FLG = 'Y';
        } else {
            WS_FBID_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_FBID_FLG);
    }
    public void T000_ENDBR_DDTFBID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTFBID_BR);
    }
    public void T000_STARTBR_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTHLD_BY_HLDSEQ() throws IOException,SQLException,Exception {
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_SEQ < :DDRHLD.HLD_SEQ";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTHLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.col = "STSW";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTBVT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTBVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_BR.rp = new DBParm();
        DCTIACCY_BR.rp.TableName = "DCTIACCY";
        DCTIACCY_BR.rp.where = "VIA_AC = :DCRIACCY.KEY.VIA_AC";
        DCTIACCY_BR.rp.upd = true;
        DCTIACCY_BR.rp.order = "VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACCY_FLG = 'Y';
        } else {
            WS_IACCY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_IACCY_FLG);
    }
    public void T000_ENDBR_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIACCY_BR);
    }
    public void T000_01_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :DCRIAACR.SUB_AC "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_BR.rp.upd = true;
        DCTIAACR_BR.rp.order = "VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_FLG = 'Y';
        } else {
            WS_IAACR_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_IAACR_FLG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-AC-PURP-CHK", DCCIACCT);
        if (DCCIACCT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIACCT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACCT.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
