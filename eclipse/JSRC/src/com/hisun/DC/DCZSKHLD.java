package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.CI.CICQACAC;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDRCCY;
import com.hisun.DD.DDRMST;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCMSG_ERROR_MSG;
import com.hisun.TD.TDRSMST;

public class DCZSKHLD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC754";
    String K_HIS_REMARKS = "KEEP DEPOSIT HOLD";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    int WS_DAYS_OLD = 0;
    int WS_DAYS_NEW = 0;
    int WS_AC_BR = 0;
    String WS_AC_TYPE = " ";
    double WS_CURR_BAL = 0;
    double WS_AVL_AMT = 0;
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    String WS_CARD_NO = " ";
    String WS_MST_AC_NO = " ";
    double WS_HLD_AMT = 0;
    char WS_HLD_STS = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DCCOKHLD DCCOKHLD = new DCCOKHLD();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRCCY DDRCCY = new DDRCCY();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCRACLNK DCRACLNK = new DCRACLNK();
    CICQACAC CICQACAC = new CICQACAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    DCCSKHLD DCCSKHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSKHLD DCCSKHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSKHLD = DCCSKHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSKHLD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_HLD_INF_PROC();
        if (pgmRtn) return;
        B021_INQ_ACAC_PROC();
        if (pgmRtn) return;
        B030_UPDATE_DDTHLD();
        if (pgmRtn) return;
        B040_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B041_UPDATE_DDTCCY_PROC();
        if (pgmRtn) return;
        B041_UPDATE_TDTSMST_PROC();
        if (pgmRtn) return;
        B045_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B050_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSKHLD.DATA.HLD_NO);
        if (DCCSKHLD.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_HLD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSKHLD.DATA.HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        WS_EFF_DATE = DDRHLD.EFF_DATE;
        WS_EXP_DATE = DDRHLD.EXP_DATE;
        WS_HLD_AMT = DDRHLD.HLD_AMT;
        WS_HLD_STS = DDRHLD.HLD_STS;
        if (DDRHLD.HLD_CLS == '3') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CLS_THREE_NOT_HOLD);
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHLD.DATA.CHG_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHLD.DATA.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHLD.DATA.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHLD.DATA.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSKHLD.DATA.NEW_AMT);
        CEP.TRC(SCCGWA, DCCSKHLD.DATA.NEW_DT);
        if (DCCSKHLD.DATA.NEW_DT == 0 
            && DCCSKHLD.DATA.NEW_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_DT_MUST_INPUT);
        }
        if (DCCSKHLD.DATA.NEW_DT != 0 
            && DCCSKHLD.DATA.NEW_AMT != 0 
            && DCCSKHLD.DATA.NEW_DT == WS_EXP_DATE 
            && DCCSKHLD.DATA.NEW_AMT == WS_HLD_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_DT_NOT_SAME);
        }
        if (DCCSKHLD.DATA.NEW_DT != 0 
            && WS_EXP_DATE > DCCSKHLD.DATA.NEW_DT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEW_EXP_DT_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHLD.DATA.NEW_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCCSKHLD.DATA.NEW_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            CEP.TRC(SCCGWA, DCCSKHLD.DATA.NEW_DT);
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_EXP_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.AC_DATE > WS_EXP_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_NOT_LESS_EXPDATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHLD.DATA.NEW_DT != 0 
            && DDRHLD.SPR_BR_TYP == '1') {
            CEP.TRC(SCCGWA, WS_EFF_DATE);
            CEP.TRC(SCCGWA, WS_EXP_DATE);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_EXP_DATE;
            SCCCLDT.MTHS = 12;
            SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
            SCSSCLDT2.MP(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, DCCSKHLD.DATA.NEW_DT);
            if (DCCSKHLD.DATA.NEW_DT > SCCCLDT.DATE2) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TERM_OVER_ONE_YEAR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRHLD.HLD_TYP == '2' 
            && DCCSKHLD.DATA.NEW_AMT <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OLD_LINE_M_INPUT);
        }
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXX");
        if (DCCSKHLD.DATA.NEW_AMT != 0 
            && DCCSKHLD.DATA.NEW_AMT > WS_HLD_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NEW_AMT_INVALID);
        }
        if (DDRHLD.HLD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHLD.DATA.CHG_BR == 0) {
            DCCSKHLD.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (!DDRHLD.SPR_CHNL.equalsIgnoreCase("033100")) {
            if (DDRHLD.SPR_BR != 0 
                && DCCSKHLD.DATA.CHG_BR != DDRHLD.SPR_BR) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_KHLD_BR_M_HLD_BR);
            }
        }
        if (DDRHLD.HLD_TYP == '1' 
            && DCCSKHLD.DATA.NEW_AMT != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TOT_BAL_HLD);
        }
    }
    public void B021_INQ_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, "DD");
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "DC");
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = DDRHLD.AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDRHLD.AC;
            T000_READ_DDTCCY_UPDATE();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_DDTHLD() throws IOException,SQLException,Exception {
        DDRHLD.LST_HLD_DT = WS_EXP_DATE;
        if (DCCSKHLD.DATA.NEW_AMT != 0) {
            DDRHLD.HLD_AMT = DCCSKHLD.DATA.NEW_AMT;
        }
        if (DCCSKHLD.DATA.NEW_DT != 0) {
            DDRHLD.EXP_DATE = DCCSKHLD.DATA.NEW_DT;
        }
        DDRHLD.SPR_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLD.SPR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRHLD.SPR_CHNL);
        CEP.TRC(SCCGWA, DDRHLD.SPR_BR);
        if (DDRHLD.SPR_BR_TYP == '1') {
            DDRHLD.HLD_BR_NM = DCCSKHLD.DATA.SPR_NM;
            DDRHLD.HLD_WRIT_NO = DCCSKHLD.DATA.CHG_NO;
            DDRHLD.LAW_OFF_NM1 = DCCSKHLD.DATA.LAW_NM1;
            DDRHLD.LAW_ID_NO1 = DCCSKHLD.DATA.LAW_NO1;
            DDRHLD.LAW_OFF_NM2 = DCCSKHLD.DATA.LAW_NM2;
            DDRHLD.LAW_ID_NO2 = DCCSKHLD.DATA.LAW_NO2;
        }
        DDRHLD.HLD_RSN = DCCSKHLD.DATA.RSN;
        DDRHLD.REMARK = DCCSKHLD.DATA.RMK;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
    }
    public void B040_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCSKHLD.DATA.HLD_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.AC = DDRHLD.AC;
        DDRHLDR.HLD_TYP = '7';
        DDRHLDR.BEF_TR_AMT = DCCSKHLD.DATA.NEW_AMT;
        DDRHLDR.TR_AMT = DCCSKHLD.DATA.NEW_AMT;
        DDRHLDR.CHG_RSN = DCCSKHLD.DATA.RSN;
        DDRHLDR.SPR_BR_TYP = DDRHLD.SPR_BR_TYP;
        DDRHLDR.SPR_BR_NM = DCCSKHLD.DATA.SPR_NM;
        DDRHLDR.CHG_WRIT_NO = DCCSKHLD.DATA.CHG_NO;
        DDRHLDR.LAW_OFF_NM1 = DCCSKHLD.DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCSKHLD.DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCSKHLD.DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCSKHLD.DATA.LAW_NO2;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B041_UPDATE_TDTSMST_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            CEP.TRC(SCCGWA, WS_HLD_STS);
            if (WS_HLD_STS != 'W') {
                TDRSMST.HBAL = TDRSMST.HBAL - WS_HLD_AMT + DCCSKHLD.DATA.NEW_AMT;
                CEP.TRC(SCCGWA, TDRSMST.HBAL);
                T000_UPDATE_TDTSMST();
                if (pgmRtn) return;
            }
            WS_CURR_BAL = TDRSMST.BAL;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(02 - 1, 02 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(03 - 1, 03 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AVL_AMT = 0;
            } else {
                WS_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL;
            }
        }
    }
    public void B041_UPDATE_DDTCCY_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, WS_HLD_AMT);
            CEP.TRC(SCCGWA, DCCSKHLD.DATA.NEW_AMT);
            CEP.TRC(SCCGWA, WS_HLD_STS);
            if (WS_HLD_STS != 'W') {
                DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - WS_HLD_AMT + DCCSKHLD.DATA.NEW_AMT;
                CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
                T000_UPDATE_DDTCCY();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            WS_CURR_BAL = DDRCCY.CURR_BAL;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(08 - 1, 08 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(09 - 1, 09 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AVL_AMT = 0;
            } else {
