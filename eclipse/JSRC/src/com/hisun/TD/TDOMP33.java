package com.hisun.TD;

import java.util.ArrayList;
import java.util.List;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.DP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOMP33 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTIREV_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCDI_RD;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTCMST_RD;
    DBParm DCTIAACR_RD;
    brParm TDTIREV_BR = new brParm();
    DBParm TDTBVT_RD;
    boolean pgmRtn = false;
    short WS_CNT = 0;
    short WS_OLD_SER = 0;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_FOUR_ZERO = "0000";
    String K_BR = "BR";
    char K_D_TABLE = 'D';
    short K_FLD_CNT = 150;
    int K_TR_BR = 999999;
    String K_HIS_RMKS1 = "INTEREST RATE EVENT MAINTENACE";
    String K_TD_MMO = "TD";
    char K_CALLTYPE_0 = '0';
    String WS_ERR_MSG = " ";
    double WS_CAL_RATE = 0;
    double WS_ACCR_RATE = 0;
    double WS_RDAMT_AMT = 0;
    short WS_CAL_BASIC_DAY = 0;
    int WS_J = 0;
    int WS_I = 0;
    short WS_TIME = 0;
    String WS_ACO_AC = " ";
    String WS_AC_NO = " ";
    double WS_CAL_TOT_AMT = 0;
    TDOMP33_WS_INT_RAT WS_INT_RAT = new TDOMP33_WS_INT_RAT();
    long WS_DIARY_BATNO = 0;
    String WS_TENOR = " ";
    String WS_CURR_EVENT_TYPE = " ";
    String WS_PREV_EVENT_TYPE = " ";
    double WS_TOT_PAID_AMT = 0;
    double WS_MA_TOT_AMT = 0;
    double WS_LAST_INT = 0;
    double WS_SETT_AMT = 0;
    double WS_TEMP_AMT = 0;
    double WS_PERD_INT = 0;
    int WS_ROLL_DATE = 0;
    int WS_NEW_DATE = 0;
    double WS_IS_AMT = 0;
    char WS_IREV_DOCU_FLG = ' ';
    String WS_IREV_DOCU_NO = " ";
    double WS_CON_RATE = 0;
    double WS_IREV_RATE = 0;
    double WS_DAILY_AMT = 0;
    TDOMP33_WS_WORK_AREA WS_WORK_AREA = new TDOMP33_WS_WORK_AREA();
    double WS_RATE_1 = 0;
    double WS_RATE_2 = 0;
    double WS_RATE_3 = 0;
    double WS_RATE_5 = 0;
    String WS_IRAT_CD = " ";
    TDOMP33_WS_TRT_CTL WS_TRT_CTL = new TDOMP33_WS_TRT_CTL();
    TDOMP33_WS_CTL_CTL WS_CTL_CTL = new TDOMP33_WS_CTL_CTL();
    TDOMP33_WS_STEP WS_STEP = new TDOMP33_WS_STEP();
    TDOMP33_WS_CTLT_KEY WS_CTLT_KEY = new TDOMP33_WS_CTLT_KEY();
    char WS_CTLD_CTL2 = ' ';
    List<TDOMP33_OCCURS1> OCCURS1 = new ArrayList<TDOMP33_OCCURS1>();
    char WS_OPTION = ' ';
    char WS_IREV_EOF_FLG = ' ';
    char WS_MST_EOF_FLG = ' ';
    char WS_ONLY_IREV_FLG = ' ';
    char WS_FOUND_IREV_FLG = ' ';
    char WS_FOUND_PSCH_FLG = ' ';
    char WS_ROLL_FOND_FLG = ' ';
    char WS_HVRL_PROC_FLG = ' ';
    char WS_SPECIAL_PROC_FLG = ' ';
    char WS_IREV_DUPKEY_FLG = ' ';
    char WS_RECORD_FLG = 'N';
    char WS_PSCH_FLG = 'N';
    char WS_CDCON_FLAG = 'Y';
    char WS_IRH_FLG = ' ';
    char WS_EXIT_CYL_FLG = ' ';
    char WS_MSREL_READ_FLG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    TDCO04 TDCO04 = new TDCO04();
    TDC005 TDC005 = new TDC005();
    TDCNHSRV TDCNHSRV = new TDCNHSRV();
    TDCNHSRV TDCNHSRO = new TDCNHSRV();
    TDCNHSRV TDCNHSRN = new TDCNHSRV();
    CICQACAC CICQACAC = new CICQACAC();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDCSRDE TDCSRDE = new TDCSRDE();
    TDCPIOD TDCPIOD = new TDCPIOD();
    TDCCEINT TDCCEINT = new TDCCEINT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCOGEDT TDCOGEDT = new TDCOGEDT();
    TDCOUHIT TDCOUHIT = new TDCOUHIT();
    DCCUAINQ DCCUAINQ = new DCCUAINQ();
    DPCPARMP DPCPARMP = new DPCPARMP();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPRCTL BPRCTLD = new BPRCTL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCEX BPCEX = new BPCEX();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    TDRBVT TDRBVT = new TDRBVT();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRSMST TDRSMSTO = new TDRSMST();
    TDRSMST TDRSMSTL = new TDRSMST();
    TDRIREV TDRIREV = new TDRIREV();
    TDRIREV TDRIREVF = new TDRIREV();
    TDRIREV TDRIREVO = new TDRIREV();
    TDRIREV TDRIREVL = new TDRIREV();
    TDRIREV TDRIREVP = new TDRIREV();
    TDRIREV TDRIREVC = new TDRIREV();
    TDRIRH TDRIRH = new TDRIRH();
    TDRCDI TDRCDI = new TDRCDI();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    BPRTRT BPRTRTT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCAWAC SCCAWAC;
    TDCMP33 TDCMP33;
    public void MP(SCCGWA SCCGWA, TDCMP33 TDCMP33) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCMP33 = TDCMP33;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCMP33);
        CEP.TRC(SCCGWA, "----------------");
        CEP.TRC(SCCGWA, TDCMP33.MAIN_AC);
        CEP.TRC(SCCGWA, TDCMP33.BV_NO);
        CEP.TRC(SCCGWA, TDCMP33.ERLY_TYP);
        CEP.TRC(SCCGWA, TDCMP33.DOCU_FLG);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOMP33 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) GWA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, SCCMSG);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_TRT_CTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMP33.MAIN_AC);
        CEP.TRC(SCCGWA, TDCMP33.BV_NO);
        CEP.TRC(SCCGWA, TDCMP33.ERLY_TYP);
        CEP.TRC(SCCGWA, TDCMP33.DOCU_FLG);
        CEP.TRC(SCCGWA, TDCMP33.UP_FLG);
        B010_GET_MST_INF();
        if (pgmRtn) return;
        B030_EVENT_MAINTENANCE();
        if (pgmRtn) return;
        if (WS_OPTION != 'I') {
            B070_FINAL_PROC();
            if (pgmRtn) return;
            B080_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_MST_INF() throws IOException,SQLException,Exception {
        B010_01_GET_AC_INFO();
        if (pgmRtn) return;
        B010_03_CHECK_AC_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, TDRSMST, TDRSMSTO);
        IBS.init(SCCGWA, CICACCU);
        if (TDCMP33.MAIN_AC.trim().length() > 0) {
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = TDCMP33.MAIN_AC;
        }
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B030_EVENT_MAINTENANCE() throws IOException,SQLException,Exception {
        WS_OPTION = TDCMP33.OPTION;
        if (WS_OPTION == 'A') {
            B041_ADD_EVENT();
            if (pgmRtn) return;
        } else if (WS_OPTION == 'C'
            || WS_OPTION == 'M'
            || WS_OPTION == 'H') {
            B050_MOD_EVENT();
            if (pgmRtn) return;
        } else if (WS_OPTION == 'D') {
            B060_DEL_EVENT();
            if (pgmRtn) return;
        } else if (WS_OPTION == 'I') {
            B090_INQ_EVENT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_OPTION != 'I') {
            if (TDRSMST.EXP_DATE != 0) {
                R000_UPDATE_CAL_TOT_AMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCCEINT.INT);
                TDRSMST.EXP_INT = TDCCEINT.INT;
            } else {
                WS_CAL_TOT_AMT = 0;
            }
        }
    }
    public void B041_ADD_EVENT() throws IOException,SQLException,Exception {
        if (!DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_SUPPORT_XY_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_CHECK_END_DATE();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(32 - 1, 32 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(32 - 1, 32 + 1 - 1).equalsIgnoreCase("2") 
            || TDRSMST.STSW.substring(32 - 1, 32 + 1 - 1).equalsIgnoreCase("3")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_DIFFERENT_FLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRIREVF);
        IBS.init(SCCGWA, TDRIREV);
        CEP.TRC(SCCGWA, "F-BUG88");
        TDRIREVF.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        T000_READ_TDTIREV_BY_AC_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRIREV.INT_SEL);
        CEP.TRC(SCCGWA, TDRIREV.KEY.IBS_HASH);
        if (TDRIREV.INT_SEL != '4') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MUST_XY_INT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, TDRIREV, TDRIREVF);
        R000_CHK_START_DATE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRIREVF.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDRIREVF.KEY.STR_DATE;
        T000_READ_TDTIREV_KEY_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        TDRIREV.END_DATE = TDCMP33.STR_DATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTIREV();
        if (pgmRtn) return;
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.DOCU_FLG = TDRIREVF.DOCU_FLG;
        TDRIREV.DOCU_NO = TDRIREVF.DOCU_NO;
        TDRIREV.KEY.STR_DATE = TDCMP33.STR_DATE;
        TDRIREV.END_DATE = TDCMP33.END_DATE;
        TDRIREV.CON_RATE = TDCMP33.CON_RATE;
        TDRIREV.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.NAR = TDCMP33.RMK;
        T000_WRITE_TDTIREV();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 32 - 1) + "4" + TDRSMST.STSW.substring(32 + 1 - 1);
    }
    public void R000_UPDATE_CAL_TOT_AMT() throws IOException,SQLException,Exception {
        WS_CAL_TOT_AMT = 0;
    }
    public void R000_ENQUAL_PROC() throws IOException,SQLException,Exception {
        WS_SPECIAL_PROC_FLG = 'Y';
        IBS.init(SCCGWA, TDRIREV);
        WS_IREV_EOF_FLG = 'N';
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_STARTBR_TDTIREV_CON();
        if (pgmRtn) return;
        T000_READNEXT_TDTIREV();
        if (pgmRtn) return;
        while (WS_IREV_EOF_FLG != 'Y') {
            T000_DELETE_TDTIREV();
            if (pgmRtn) return;
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTIREV();
        if (pgmRtn) return;
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.KEY.STR_DATE = TDCMP33.STR_DATE;
        TDRIREV.END_DATE = TDCMP33.END_DATE;
        TDRIREV.INT_SEL = '4';
        TDRIREV.CON_RATE = TDCMP33.CON_RATE;
        TDRIREV.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.NAR = TDCMP33.RMK;
        CEP.TRC(SCCGWA, TDCMP33.RATE_SEL);
        CEP.TRC(SCCGWA, TDCMP33.RUL_CD);
        CEP.TRC(SCCGWA, TDCMP33.TENOR);
        CEP.TRC(SCCGWA, TDCMP33.PRD_RATE);
        CEP.TRC(SCCGWA, TDCMP33.CON_RATE);
        CEP.TRC(SCCGWA, TDCMP33.SPRD_PNT);
        CEP.TRC(SCCGWA, TDCMP33.SPRD_PCT);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_BAL);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_TERM);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_SVR_LVL);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_GRPS_NO);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_BR);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_ASS_LVL);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_CHNL_NO);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_AGE);
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_GENDER);
        CEP.TRC(SCCGWA, TDCCEINT.CCY);
        if (TDCMP33.RATE_SEL != '4' 
            && TDCMP33.RATE_SEL != '1' 
            && TDCMP33.RATE_SEL != '2') {
            B050_CAL_EXP_INT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCCEINT.RAT);
            CEP.TRC(SCCGWA, TDCMP33.CON_RATE);
            if (TDCCEINT.RAT != TDCMP33.CON_RATE) {
                CEP.TRC(SCCGWA, "TD4477:M-TD-CONRAT-ERR");
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CONRAT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        TDRIREV.INT_SEL = TDCMP33.RATE_SEL;
        TDRIREV.INT_RUL_CD = TDCMP33.RUL_CD;
        TDRIREV.RATE_TYPE = TDCMP33.RATE_TYP;
        TDRIREV.TENOR = TDCMP33.TENOR;
        TDRIREV.PRD_RATE = TDCMP33.PRD_RATE;
        TDRIREV.CON_RATE = TDCMP33.CON_RATE;
        TDRIREV.SPRD_PNT = TDCMP33.SPRD_PNT;
        TDRIREV.SPRD_PCT = TDCMP33.SPRD_PCT;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.NAR = TDCMP33.RMK;
        TDRIREV.BAL = TDCCEINT.JU_RACD.RACD_BAL;
        TDRIREV.TERM = TDCCEINT.JU_RACD.RACD_TERM;
        if (TDCCEINT.JU_RACD.RACD_SVR_LVL.trim().length() == 0) TDRIREV.LVL = 0;
        else TDRIREV.LVL = Short.parseShort(TDCCEINT.JU_RACD.RACD_SVR_LVL);
        TDRIREV.GRPS_NO = TDCCEINT.JU_RACD.RACD_GRPS_NO;
        TDRIREV.BR = TDCCEINT.JU_RACD.RACD_BR;
        TDRIREV.ASS_LVL = TDCCEINT.JU_RACD.RACD_ASS_LVL;
        TDRIREV.CHNL_NO = TDCCEINT.JU_RACD.RACD_CHNL_NO;
        if (TDRIREV.OTH_FIL == null) TDRIREV.OTH_FIL = "";
        JIBS_tmp_int = TDRIREV.OTH_FIL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDRIREV.OTH_FIL += " ";
        JIBS_tmp_str[0] = "" + TDCCEINT.JU_RACD.RACD_AGE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRIREV.OTH_FIL = JIBS_tmp_str[0] + TDRIREV.OTH_FIL.substring(4);
        if (TDRIREV.OTH_FIL == null) TDRIREV.OTH_FIL = "";
        JIBS_tmp_int = TDRIREV.OTH_FIL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDRIREV.OTH_FIL += " ";
        JIBS_tmp_str[0] = "" + TDCCEINT.JU_RACD.RACD_GENDER;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRIREV.OTH_FIL = TDRIREV.OTH_FIL.substring(0, 5 - 1) + JIBS_tmp_str[0] + TDRIREV.OTH_FIL.substring(5 + 1 - 1);
        TDRIREV.CCY = TDCCEINT.CCY;
        T000_WRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_MORE_FIRST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREVF);
        IBS.CLONE(SCCGWA, TDRIREV, TDRIREVF);
        if (TDRIREVF.KEY.STR_DATE == TDCMP33.STR_DATE) {
            T000_DELETE_TDTIREV();
            if (pgmRtn) return;
        } else {
            WS_WORK_AREA.WS_PGDIN_DATE_1 = TDCMP33.STR_DATE;
            R000_GET_PREV_NATURE_DATE();
            if (pgmRtn) return;
            TDRIREVF.END_DATE = TDCOGEDT.OUTPUT_DATE;
            TDRIREVF.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRIREVF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CLONE(SCCGWA, TDRIREVF, TDRIREV);
            T000_REWRITE_TDTIREV();
            if (pgmRtn) return;
        }
    }
    public void R000_MORE_MIDDLE_REC() throws IOException,SQLException,Exception {
        T000_DELETE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_MORE_LAST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREVL);
        IBS.CLONE(SCCGWA, TDRIREV, TDRIREVL);
        T000_DELETE_TDTIREV();
        if (pgmRtn) return;
        WS_WORK_AREA.WS_PGDIN_DATE_1 = TDCMP33.END_DATE;
        R000_GET_NEXT_NATURE_DATE();
        if (pgmRtn) return;
        TDRIREVL.KEY.STR_DATE = TDCOGEDT.OUTPUT_DATE;
        CEP.TRC(SCCGWA, TDRIREVL.KEY.STR_DATE);
        if (TDRIREVL.KEY.STR_DATE > TDRSMST.EXP_DATE) {
            IBS.init(SCCGWA, TDRIREV);
            WS_IREV_EOF_FLG = 'N';
            TDRIREV.KEY.STR_DATE = TDRIREVL.KEY.STR_DATE;
            T000_STARTBR_TDTIREV_STR();
            if (pgmRtn) return;
            T000_READNEXT_TDTIREV_STR();
            if (pgmRtn) return;
            while (WS_IREV_EOF_FLG != 'Y') {
                T000_DELETE_TDTIREV_STR();
                if (pgmRtn) return;
                T000_READNEXT_TDTIREV_STR();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTIREV_STR();
            if (pgmRtn) return;
            TDRIREVL.END_DATE = 99991231;
            TDRIREVL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRIREVL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRIREVL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRIREVL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CLONE(SCCGWA, TDRIREVL, TDRIREV);
        } else {
            TDRIREVL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CLONE(SCCGWA, TDRIREVL, TDRIREV);
        }
        T000_WRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_ADD_MID_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.KEY.STR_DATE = TDCMP33.STR_DATE;
        TDRIREV.END_DATE = TDCMP33.END_DATE;
        TDRIREV.RATE_SEL = TDCMP33.RATE_SEL;
        TDRIREV.RATE_TYPE = TDCMP33.RATE_TYP;
        TDRIREV.TENOR = TDCMP33.TENOR;
        TDRIREV.PRD_RATE = TDCMP33.PRD_RATE;
        TDRIREV.CON_RATE = TDCMP33.CON_RATE;
        TDRIREV.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.NAR = TDCMP33.RMK;
        T000_WRITE_TDTIREV();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 32 - 1) + "1" + TDRSMST.STSW.substring(32 + 1 - 1);
    }
    public void B050_MOD_EVENT() throws IOException,SQLException,Exception {
        if (TDRSMST.EXP_DATE != 0) {
            if (TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                && WS_OPTION != 'H') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_EXP_ALRDY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_GROUP_TDTIREV();
        if (pgmRtn) return;
        if (WS_CNT > 1 
            && TDCMP33.RATE_SEL != '4') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CANOT_MODRAT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && TDRSMST.BAL_INT > 0 
            && !TDRSMST.STSW.substring(21 - 1, 21 + 3 - 1).equalsIgnoreCase("000")) {
            IBS.init(SCCGWA, TDRCDI);
            TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTCDI();
            if (pgmRtn) return;
            if (TDCMP33.STR_DATE < TDRCDI.LAST_DEAL_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INT_EVENT_MOD_N;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((TDCMP33.STR_DATE == TDRSMST.VAL_DATE 
            && TDRSMST.VAL_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && TDCMP33.END_DATE == TDRSMST.EXP_DATE) 
            || (TDCMP33.STR_DATE == TDRSMST.VAL_DATE 
            && TDCMP33.END_DATE == 99991231)) {
            R000_ENQUAL_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, TDCMP33.OPTION);
            CEP.TRC(SCCGWA, TDCMP33.RATE_SEL);
            if (TDCMP33.RATE_SEL != '4' 
                && TDCMP33.RATE_SEL != '1' 
                && TDCMP33.RATE_SEL != '2') {
                B050_CAL_EXP_INT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCCEINT.RAT);
                CEP.TRC(SCCGWA, TDCMP33.CON_RATE);
                if (TDCCEINT.RAT != TDCMP33.CON_RATE) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CONRAT_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            TDRIREV.KEY.ACO_AC = WS_ACO_AC;
            TDRIREV.KEY.STR_DATE = TDCMP33.STR_DATE;
            T000_READUP_TDTIREV();
            if (pgmRtn) return;
            R000_WRITE_MOD_HISTORY_OLD();
            if (pgmRtn) return;
            if (TDRSMST.EXP_DATE != 0) {
                if (TDCMP33.END_DATE == TDRSMST.EXP_DATE) {
                    TDRIREV.END_DATE = TDCMP33.END_DATE;
                }
            }
            CEP.TRC(SCCGWA, TDCMP33.RATE_SEL);
            CEP.TRC(SCCGWA, TDCMP33.RUL_CD);
            CEP.TRC(SCCGWA, TDCMP33.TENOR);
            CEP.TRC(SCCGWA, TDCMP33.PRD_RATE);
            CEP.TRC(SCCGWA, TDCMP33.CON_RATE);
            CEP.TRC(SCCGWA, TDCMP33.SPRD_PNT);
            CEP.TRC(SCCGWA, TDCMP33.SPRD_PCT);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_BAL);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_TERM);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_SVR_LVL);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_GRPS_NO);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_BR);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_ASS_LVL);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_CHNL_NO);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_AGE);
            CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.RACD_GENDER);
            CEP.TRC(SCCGWA, TDCCEINT.CCY);
            TDRIREV.INT_SEL = TDCMP33.RATE_SEL;
            TDRIREV.RATE_SEL = TDCCEINT.XZ_FLG;
            if (TDCCEINT.XZ_FLG == ' ') {
                TDRIREV.RATE_SEL = '0';
            }
            TDRIREV.INT_RUL_CD = TDCMP33.RUL_CD;
            if (TDCMP33.RATE_TYP.trim().length() > 0) {
                TDRIREV.RATE_TYPE = TDCMP33.RATE_TYP;
            }
            if (TDCMP33.TENOR.trim().length() > 0) {
                TDRIREV.TENOR = TDCMP33.TENOR;
            }
            if (TDCMP33.PRD_RATE != 0) {
                TDRIREV.PRD_RATE = TDCMP33.PRD_RATE;
            }
            TDRIREV.CON_RATE = TDCMP33.CON_RATE;
            TDRIREV.SPRD_PNT = TDCMP33.SPRD_PNT;
            TDRIREV.SPRD_PCT = TDCMP33.SPRD_PCT;
            if (TDCMP33.SPRD_PCT != 0) {
                TDRIREV.CON_RATE = TDRIREV.PRD_RATE * ( 1 + TDCMP33.SPRD_PCT );
            }
            if (TDCMP33.SPRD_PNT != 0) {
                TDRIREV.CON_RATE = TDRIREV.PRD_RATE + TDCMP33.SPRD_PNT;
            }
            TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRIREV.NAR = TDCMP33.RMK;
            CEP.TRC(SCCGWA, TDCMP33.ERLY_TYP);
            CEP.TRC(SCCGWA, TDCMP33.DOCU_FLG);
            TDRIREV.BAL = TDCCEINT.JU_RACD.RACD_BAL;
            TDRIREV.TERM = TDCCEINT.JU_RACD.RACD_TERM;
            if (TDCCEINT.JU_RACD.RACD_SVR_LVL.trim().length() == 0) TDRIREV.LVL = 0;
            else TDRIREV.LVL = Short.parseShort(TDCCEINT.JU_RACD.RACD_SVR_LVL);
            TDRIREV.GRPS_NO = TDCCEINT.JU_RACD.RACD_GRPS_NO;
            TDRIREV.BR = TDCCEINT.JU_RACD.RACD_BR;
            TDRIREV.ASS_LVL = TDCCEINT.JU_RACD.RACD_ASS_LVL;
            TDRIREV.CHNL_NO = TDCCEINT.JU_RACD.RACD_CHNL_NO;
            if (TDRIREV.OTH_FIL == null) TDRIREV.OTH_FIL = "";
            JIBS_tmp_int = TDRIREV.OTH_FIL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDRIREV.OTH_FIL += " ";
            JIBS_tmp_str[0] = "" + TDCCEINT.JU_RACD.RACD_AGE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRIREV.OTH_FIL = JIBS_tmp_str[0] + TDRIREV.OTH_FIL.substring(4);
            if (TDRIREV.OTH_FIL == null) TDRIREV.OTH_FIL = "";
            JIBS_tmp_int = TDRIREV.OTH_FIL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDRIREV.OTH_FIL += " ";
            JIBS_tmp_str[0] = "" + TDCCEINT.JU_RACD.RACD_GENDER;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRIREV.OTH_FIL = TDRIREV.OTH_FIL.substring(0, 5 - 1) + JIBS_tmp_str[0] + TDRIREV.OTH_FIL.substring(5 + 1 - 1);
            TDRIREV.CCY = TDCCEINT.CCY;
            R000_WRITE_MOD_HISTORY_NEW();
            if (pgmRtn) return;
            T000_REWRITE_TDTIREV();
            if (pgmRtn) return;
        }
    }
    public void B060_DEL_EVENT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACO_AC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_ACO_AC;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        if (TDRSMST.EXP_DATE != TDCMP33.END_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MST_DELAST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_SUPPORT_XY_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCMP33.STR_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || TDCMP33.STR_DATE == TDRSMST.VAL_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.REC_TAKE_EFFECT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        T000_GROUP_TDTIREV();
        if (pgmRtn) return;
        if (WS_CNT == 1) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ONLY_BANK_FIN_TRA;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_DEL_CURR_EVENT();
        if (pgmRtn) return;
        R000_KEEP_DEL_PREV_EVENT();
        if (pgmRtn) return;
        R000_MOD_PREV_EVENT();
        if (pgmRtn) return;
        R000_WRITE_DEL_HISTORY();
        if (pgmRtn) return;
    }
    public void B090_INQ_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        WS_IREV_EOF_FLG = 'N';
        CEP.TRC(SCCGWA, TDCMP33.MAIN_AC);
        CEP.TRC(SCCGWA, TDCMP33.BV_NO);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        B090_OUTPUT_MAIN_TITLE();
        if (pgmRtn) return;
        T000_STARTBR_TDTIREV_CON();
        if (pgmRtn) return;
        T000_READNEXT_TDTIREV();
        if (pgmRtn) return;
        while (WS_IREV_EOF_FLG != 'Y') {
            B093_SET_SUB_TRAN();
            if (pgmRtn) return;
            B091_TRANSFER_MAIN_DATA();
            if (pgmRtn) return;
            B092_OUTPUT_MAIN_DATA();
            if (pgmRtn) return;
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTIREV();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_MAIN_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 360;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B091_TRANSFER_MAIN_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMP33.AC_SEQ);
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDRIREV.END_DATE);
        CEP.TRC(SCCGWA, TDRIREV.RATE_SEL);
        CEP.TRC(SCCGWA, TDRIREV.INT_SEL);
        CEP.TRC(SCCGWA, TDRIREV.PRD_RATE);
        CEP.TRC(SCCGWA, TDRIREV.RATE_TYPE);
        CEP.TRC(SCCGWA, TDRIREV.TENOR);
        CEP.TRC(SCCGWA, TDRIREV.PRD_SPRD);
        CEP.TRC(SCCGWA, TDRIREV.DIS_SPRD);
        CEP.TRC(SCCGWA, TDRIREV.CON_SPRD);
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        CEP.TRC(SCCGWA, TDRIREV.MIN_INT_RATE);
        CEP.TRC(SCCGWA, TDRIREV.MAX_INT_RATE);
        CEP.TRC(SCCGWA, TDRIREV.NAR);
        CEP.TRC(SCCGWA, TDRIREV.CRT_TLR);
        CEP.TRC(SCCGWA, TDRIREV.CRT_DATE);
        CEP.TRC(SCCGWA, TDRIREV.UPD_TLT);
        CEP.TRC(SCCGWA, TDRIREV.UPD_DATE);
        CEP.TRC(SCCGWA, TDRIREV.SPRD_PCT);
        CEP.TRC(SCCGWA, TDRIREV.SPRD_PNT);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(33 - 1, 33 + 1 - 1));
        IBS.init(SCCGWA, TDC005);
        TDC005.AC_SEQ = TDCMP33.AC_SEQ;
        TDC005.STR_DATE = TDRIREV.KEY.STR_DATE;
        TDC005.END_DATE = TDRIREV.END_DATE;
        TDC005.RATE_SEL = TDRIREV.INT_SEL;
        TDC005.BASE_RATE = TDRIREV.PRD_RATE;
        TDC005.RATE_TYPE = TDRIREV.RATE_TYPE;
        TDC005.TENOR = TDRIREV.TENOR;
        TDC005.PRD_SPRD = TDRIREV.PRD_SPRD;
        TDC005.DIS_SPRD = TDRIREV.DIS_SPRD;
        TDC005.CON_SPRD = TDRIREV.CON_SPRD;
        TDC005.CON_RATE = TDRIREV.CON_RATE;
        TDC005.MIN_RATE = TDRIREV.MIN_INT_RATE;
        TDC005.MAX_RATE = TDRIREV.MAX_INT_RATE;
        TDC005.REMARK = TDRIREV.NAR;
        TDC005.CRT_TLR = TDRIREV.CRT_TLR;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDC005.FLOW = TDRSMST.STSW.substring(33 - 1, 33 + 1 - 1).charAt(0);
        TDC005.CRT_DATE = TDRIREV.CRT_DATE;
        TDC005.UPDTBL_TLR = TDRIREV.UPD_TLT;
        TDC005.UPDTBL_DATE = TDRIREV.UPD_DATE;
        TDC005.SINT = TDRIREV.SPRD_PNT;
        TDC005.SPT = TDRIREV.SPRD_PCT;
        CEP.TRC(SCCGWA, TDC005.ERLY_TYP);
        CEP.TRC(SCCGWA, TDC005.DOCU_FLG);
    }
    public void B092_OUTPUT_MAIN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDC005);
        SCCMPAG.DATA_LEN = 360;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B093_SET_SUB_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 12;
        SCCSUBS.TR_CODE = 5401;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void R000_WRITE_DEL_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCNHSRO);
        IBS.init(SCCGWA, TDCNHSRN);
        TDCNHSRO.OPTION = TDCMP33.OPTION;
        TDCNHSRO.STR_DATE = TDCMP33.STR_DATE;
        TDCNHSRO.END_DATE = TDCMP33.END_DATE;
        TDCNHSRO.RATE_SEL = TDCMP33.RATE_SEL;
        TDCNHSRO.BASE_TYPE = TDCMP33.RATE_TYP;
        TDCNHSRO.TENOR = TDCMP33.TENOR;
        TDCNHSRO.BASE_RATE = TDCMP33.PRD_RATE;
        TDCNHSRO.CON_RATE = TDCMP33.CON_RATE;
        TDCNHSRO.NAR = TDCMP33.RMK;
    }
    public void B070_FINAL_PROC() throws IOException,SQLException,Exception {
        R000_WRITE_HIS();
        if (pgmRtn) return;
        R000_UPD_TDTSMST();
        if (pgmRtn) return;
    }
    public void R000_UPD_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        T000_READ_TDTSMST_UPDATE();
        if (pgmRtn) return;
        TDRSMST.EXP_INT = WS_CAL_TOT_AMT;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        if (TDCMP33.OPTION == 'A') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 32 - 1) + "4" + TDRSMST.STSW.substring(32 + 1 - 1);
        }
        if (TDCMP33.UP_FLG != ' ') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            JIBS_tmp_str[0] = "" + TDCMP33.UP_FLG;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 33 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(33 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B080_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCO04);
        TDCO04.OPTION = TDCMP33.OPTION;
        TDCO04.ENG_NAME = CICACCU.DATA.CI_ENM;
        TDCO04.LCL_NAME = CICACCU.DATA.CI_CNM;
        TDCO04.STR_DATE = TDCMP33.STR_DATE;
        TDCO04.END_DATE = TDCMP33.END_DATE;
        TDCO04.RATE_SEL = TDCMP33.RATE_SEL;
        TDCO04.RATE_TYPE = TDCMP33.RATE_TYP;
        TDCO04.TENOR = TDCMP33.TENOR;
        TDCO04.CON_RATE = TDCMP33.CON_RATE;
        TDCO04.REMARK = TDCMP33.RMK;
        TDCO04.BAL = TDRSMST.BAL;
        TDCO04.BAL_INT_AMT = TDRSMST.BAL + TDRSMST.EXP_INT;
        TDCO04.DEP_AC_NO = " ";
        TDCO04.DEP_PER_AMT = 0;
        CEP.TRC(SCCGWA, TDCO04.REMARK);
        CEP.TRC(SCCGWA, TDCO04.PRD_CODE);
        CEP.TRC(SCCGWA, TDCO04.BAL);
        CEP.TRC(SCCGWA, TDCO04.BAL_INT_AMT);
        CEP.TRC(SCCGWA, TDCO04.DEP_AC_NO);
        CEP.TRC(SCCGWA, TDCO04.DEP_PER_AMT);
        CEP.TRC(SCCGWA, TDCO04.WDR_AC_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "12O04";
        SCCFMT.DATA_PTR = TDCO04;
        SCCFMT.DATA_LEN = 844;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMP33.MAIN_AC);
        CEP.TRC(SCCGWA, TDCMP33.BV_NO);
        CEP.TRC(SCCGWA, TDCMP33.AC_SEQ);
        if (TDCMP33.MAIN_AC.trim().length() > 0 
            && (TDCMP33.AC_SEQ != ' ' 
            || TDCMP33.BV_NO.trim().length() > 0)) {
            CEP.TRC(SCCGWA, "TD2");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCMP33.MAIN_AC;
            CICQACAC.DATA.AGR_SEQ = TDCMP33.AC_SEQ;
            CICQACAC.DATA.BV_NO = TDCMP33.BV_NO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (TDCMP33.AC_SEQ != 0 
                && TDCMP33.AC_SEQ != CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH);
            }
            TDCMP33.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CEP.TRC(SCCGWA, "F-BUG21");
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_ACO_AC;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        WS_OPTION = TDCMP33.OPTION;
        CEP.TRC(SCCGWA, WS_OPTION);
        if (WS_OPTION == 'A') {
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTIREV_BY_AC_FIRST();
            if (pgmRtn) return;
            TDCMP33.RATE_SEL = TDRIREV.INT_SEL;
        }
        CEP.TRC(SCCGWA, TDCMP33.RATE_SEL);
        if (TDRSMST.TERM.equalsIgnoreCase("S000") 
            && TDCMP33.RATE_SEL != '4' 
            && WS_OPTION != 'I' 
            && WS_OPTION != 'D') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_ONLY_XS);
        }
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, TDCPIOD);
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCPIOD.C_PROD_CD = TDRSMST.PROD_CD;
        TDCPIOD.GET_FLG = 'N';
        TDCPIOD.ACTI_NO = TDRSMST.ACTI_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (TDRSMST.ACTI_NO.trim().length() == 0) {
            TDCPIOD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDCPIOD.INTERM = TDRSMST.TERM;
            TDCPIOD.CCY = TDRSMST.CCY;
        }
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DPCPARMP);
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NOT_SUPPORT_TTZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
    }
    public void B010_03_CHECK_AC_PROC() throws IOException,SQLException,Exception {
        TDRCMST.KEY.AC_NO = WS_AC_NO;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        if (TDRCMST.STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CAL_EXP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCEINT);
        TDCCEINT.IRUL_PTR = TDCPIOD;
        TDCCEINT.OPT = '0';
        TDCCEINT.XZ_FLG = TDCMP33.RATE_SEL;
        CEP.TRC(SCCGWA, TDCMP33.RATE_SEL);
        CEP.TRC(SCCGWA, TDCCEINT.XZ_FLG);
        CEP.TRC(SCCGWA, TDCMP33.CON_RATE);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCMP33.STR_DATE);
        CEP.TRC(SCCGWA, TDCMP33.END_DATE);
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        TDCCEINT.RAT_1 = TDCMP33.CON_RATE;
        TDCCEINT.PROD_CD = TDRSMST.PROD_CD;
        TDCCEINT.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        TDCCEINT.CCY = TDRSMST.CCY;
        TDCCEINT.TXN_AMT = TDRSMST.BAL;
        TDCCEINT.VAL_DATE = TDCMP33.STR_DATE;
        TDCCEINT.EXP_DATE = TDCMP33.END_DATE;
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
        CEP.TRC(SCCGWA, TDCCEINT.IRAT_CD);
        TDCCEINT.AC_RUL_CD = TDCMP33.RUL_CD;
        TDCCEINT.SPRD_PNT = TDCMP33.PRD_RATE;
        TDCCEINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCCEINT.OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDCCEINT.PRDAC_CD = TDRSMST.PRDAC_CD;
        CEP.TRC(SCCGWA, TDCCEINT.PRDAC_CD);
        CEP.TRC(SCCGWA, TDCCEINT.PROD_CD);
        TDCCEINT.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        TDCCEINT.SRV_LVL = CICCUST.O_DATA.O_SVR_LVL;
        TDCCEINT.TERM = TDCMP33.TENOR;
        TDCCEINT.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        CEP.TRC(SCCGWA, TDCCEINT.CALD_FLG);
        if (TDCCEINT.IRAT_CD.trim().length() > 0) {
            S000_CALL_TDZCEINT();
            if (pgmRtn) return;
        }
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCCEINT.RAT = 0;
        }
    }
    public void R000_WRITE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = "TDCNHSRV";
        BPCPNHIS.INFO.AC = TDCMP33.MAIN_AC;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS1;
        BPCPNHIS.INFO.AC_SEQ = TDCMP33.AC_SEQ;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        if (TDCMP33.OPTION == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_TYP_CD = "P621";
        }
        if (TDCMP33.OPTION == 'C' 
            || TDCMP33.OPTION == 'M' 
            || WS_OPTION == 'H') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_TYP_CD = "P622";
        }
        if (TDCMP33.OPTION == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_TYP_CD = "P623";
        }
        BPCPNHIS.INFO.FMT_ID_LEN = 262;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = TDCNHSRO;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCNHSRN;
        S00_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_KEEP_DEL_PREV_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        IBS.init(SCCGWA, TDRIREVP);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.END_DATE = TDCMP33.STR_DATE;
        T000_READ_TDTIREV_BY_END();
        if (pgmRtn) return;
        if (WS_FOUND_IREV_FLG == 'N') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, TDRIREV, TDRIREVP);
    }
    public void R000_DEL_CURR_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        IBS.init(SCCGWA, TDRIREVC);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.KEY.STR_DATE = TDCMP33.STR_DATE;
        T000_READUP_TDTIREV();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, TDRIREV, TDRIREVC);
        T000_DELETE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_MOD_PREV_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREVP.END_DATE = TDRIREVC.END_DATE;
        TDRIREVP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREVP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CLONE(SCCGWA, TDRIREVP, TDRIREV);
        T000_REWRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_GET_NEXT_NATURE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOGEDT);
        TDCOGEDT.GET_DATE_FLG = '2';
        TDCOGEDT.INPUT_DATE = WS_WORK_AREA.WS_PGDIN_DATE_1;
        if (TDCOGEDT.INPUT_DATE != 99991231) {
            S000_CALL_TDZGEDT();
            if (pgmRtn) return;
        } else {
            TDCOGEDT.OUTPUT_DATE = 99991231;
        }
        CEP.TRC(SCCGWA, WS_WORK_AREA.WS_PGDIN_DATE_1);
        CEP.TRC(SCCGWA, TDCOGEDT.OUTPUT_DATE);
    }
    public void R000_GET_PREV_NATURE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOGEDT);
        TDCOGEDT.GET_DATE_FLG = '1';
        TDCOGEDT.INPUT_DATE = WS_WORK_AREA.WS_PGDIN_DATE_1;
        S000_CALL_TDZGEDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_WORK_AREA.WS_PGDIN_DATE_1);
        CEP.TRC(SCCGWA, TDCOGEDT.OUTPUT_DATE);
    }
    public void R000_CHECK_END_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMP33.END_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        if (TDRSMST.EXP_DATE == 0) {
            if (TDCMP33.END_DATE == 0) {
            }
        } else {
            if (TDCMP33.END_DATE == 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDCMP33.END_DATE != TDRSMST.EXP_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_END_DATE_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (TDRSMST.EXP_DATE != 0) {
            if (TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_EXP_ALRDY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHK_START_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMP33.STR_DATE);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        if (TDCMP33.STR_DATE == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRIREVF.KEY.STR_DATE);
        if (TDCMP33.STR_DATE < TDRSMST.VAL_DATE 
            || TDCMP33.STR_DATE == TDRSMST.VAL_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ERR_LT_STR_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCMP33.STR_DATE == TDRIREVF.KEY.STR_DATE 
            || TDCMP33.STR_DATE < TDRIREVF.KEY.STR_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_STR_DATE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && TDRSMST.BAL_INT > 0 
            && !TDRSMST.STSW.substring(21 - 1, 21 + 3 - 1).equalsIgnoreCase("000")) {
            IBS.init(SCCGWA, TDRCDI);
            TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTCDI();
            if (pgmRtn) return;
            if (TDCMP33.STR_DATE < TDRCDI.LAST_DEAL_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ERR_LT_LST_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_WRITE_MOD_HISTORY_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCNHSRO);
        TDCNHSRO.OPTION = TDCMP33.OPTION;
        TDCNHSRO.AC_NO = TDCMP33.MAIN_AC;
        TDCNHSRO.STR_DATE = TDCMP33.STR_DATE;
        TDCNHSRO.END_DATE = TDCMP33.END_DATE;
        TDCNHSRO.RATE_SEL = TDRIREV.INT_SEL;
        TDCNHSRO.BASE_TYPE = TDRIREV.RATE_TYPE;
        TDCNHSRO.TENOR = TDRIREV.TENOR;
        TDCNHSRO.BASE_RATE = TDRIREV.PRD_RATE;
        TDCNHSRO.PRD_SPRD = TDRIREV.PRD_SPRD;
        TDCNHSRO.DIS_SPRD = TDRIREV.DIS_SPRD;
        TDCNHSRO.CON_SPRD = TDRIREV.CON_SPRD;
        TDCNHSRO.CON_RATE = TDRIREV.CON_RATE;
        TDCNHSRO.MIN_INT_RATE = TDRIREV.MIN_INT_RATE;
        TDCNHSRO.MAX_INT_RATE = TDRIREV.MAX_INT_RATE;
        TDCNHSRO.NAR = TDRIREV.NAR;
        CEP.TRC(SCCGWA, TDCNHSRO.OPTION);
        CEP.TRC(SCCGWA, TDCNHSRN.NAR);
    }
    public void R000_WRITE_MOD_HISTORY_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCNHSRN);
        TDCNHSRN.OPTION = TDCMP33.OPTION;
        TDCNHSRN.STR_DATE = TDCMP33.STR_DATE;
        TDCNHSRN.END_DATE = TDCMP33.END_DATE;
        TDCNHSRN.RATE_SEL = TDCMP33.RATE_SEL;
        TDCNHSRN.BASE_TYPE = TDCMP33.RATE_TYP;
        TDCNHSRN.TENOR = TDCMP33.TENOR;
        TDCNHSRN.BASE_RATE = TDCMP33.PRD_RATE;
        TDCNHSRN.CON_RATE = TDCMP33.CON_RATE;
        TDCNHSRN.NAR = TDCMP33.RMK;
        CEP.TRC(SCCGWA, TDCNHSRN.OPTION);
        CEP.TRC(SCCGWA, TDCNHSRN.NAR);
    }
    public void R00_GET_CAL_ACCR() throws IOException,SQLException,Exception {
        R000_GET_CCY_CODE();
        if (pgmRtn) return;
        WS_J = 0;
        WS_ACCR_RATE = 0;
        WS_CAL_RATE = 0;
        CEP.TRC(SCCGWA, WS_I);
        for (WS_J = 1; WS_J <= WS_I; WS_J += 1) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DATE1 = OCCURS1.get(WS_J-1).WS_BEG_DATE;
            BPCOCLWD.DATE2 = OCCURS1.get(WS_J-1).WS_END_DATE;
            CEP.TRC(SCCGWA, OCCURS1.get(WS_J-1).WS_BEG_DATE);
            CEP.TRC(SCCGWA, OCCURS1.get(WS_J-1).WS_END_DATE);
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            WS_ACCR_RATE = ( BPCOCLWD.DAYS - 1 ) * OCCURS1.get(WS_J-1).WS_RATE;
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
            CEP.TRC(SCCGWA, OCCURS1.get(WS_J-1).WS_RATE);
            CEP.TRC(SCCGWA, WS_ACCR_RATE);
            CEP.TRC(SCCGWA, WS_CAL_RATE);
            WS_CAL_RATE = WS_CAL_RATE + WS_ACCR_RATE;
            CEP.TRC(SCCGWA, WS_CAL_RATE);
        }
    }
    public void R000_GET_CCY_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '1';
        BPCOQCAL.BK = BPCRBANK.BNK;
        BPCOQCAL.CCY = BPCRBANK.LOC_CCY1;
        CEP.TRC(SCCGWA, BPCOQCAL.CCY);
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
    }
    public void R00_CAL_INT() throws IOException,SQLException,Exception {
        WS_RDAMT_AMT = 0;
        WS_CAL_BASIC_DAY = 0;
        S000_GET_BASIC_DAY();
        if (pgmRtn) return;
        WS_RDAMT_AMT = TDRSMST.BAL * WS_CAL_RATE / WS_CAL_BASIC_DAY / 100;
        CEP.TRC(SCCGWA, WS_CAL_RATE);
        CEP.TRC(SCCGWA, WS_CAL_BASIC_DAY);
        CEP.TRC(SCCGWA, WS_RDAMT_AMT);
        if (WS_RDAMT_AMT > 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = TDRSMST.CCY;
            BPCRDAMT.AMT = WS_RDAMT_AMT;
            S000_CALL_BPZRDAMT();
            if (pgmRtn) return;
            WS_CAL_TOT_AMT = BPCRDAMT.RESULT_AMT;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZCEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-EXP-INT", TDCCEINT);
        if (TDCCEINT.RC_MSG.RC != 0) {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_BASIC_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = TDRSMST.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        WS_CAL_BASIC_DAY = BPCQCCY.DATA.STD_DAYS;
        CEP.TRC(SCCGWA, WS_CAL_BASIC_DAY);
        CEP.TRC(SCCGWA, WS_CAL_BASIC_DAY);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZGEDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZGEDT", TDCOGEDT);
        if (TDCOGEDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + TDCOGEDT.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCUHIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZCUHIT", TDCOUHIT);
        if (TDCOUHIT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCOUHIT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOACCU", CICACCU);
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_TDFPMT_D() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRCTLD;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[9] = "SCSSCLDT";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCCLDT.getClass()});
        m.invoke(obj, SCCGWA, SCCCLDT);
    }
    public void S000_CALL_PARM_READ_SEAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.DAT_PTR = TDCSRDE;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSOPARM ERROR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC.RC_CODE);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUAINQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-AC-INQ", DCCUAINQ);
        if (DCCUAINQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUAINQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void S00_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        while (WS_TIME < 12 
            && !TDRSMST.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].MIN_CCYC)) {
            CEP.TRC(SCCGWA, WS_TIME);
            WS_TIME += 1;
            if (TDRSMST.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].MIN_CCYC)) {
                CEP.TRC(SCCGWA, WS_TIME);
                WS_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].RAT_CD;
                CEP.TRC(SCCGWA, WS_IRAT_CD);
            }
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_GROUP_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.set = "WS-CNT=COUNT(*)";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.errhdl = true;
        IBS.GROUP(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST_UPDATE() throws IOException,SQLException,Exception {
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
    public void T000_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTSMST_2() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.eqWhere = "ACO_AC";
        TDTSMST_BR.rp.where = "SUBSTR ( STSW , 5 , 1 ) = '1'";
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR TDTSMST  ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MST_EOF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MST_EOF_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READNEXT TDTSMST  ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR TDTSMST  ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV_BY_AC_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREVF.KEY.ACO_AC);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.fst = true;
        TDTIREV_RD.order = "END_DATE DESC";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV_KEY_UPD() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.END_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV_BY_KEY() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV_BY_END() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREV.END_DATE);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "END_DATE = :TDRIREV.END_DATE";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_IREV_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_IREV_FLG = 'N';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_SUB_NOT_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "( END_DATE >= :TDRIREV.KEY.STR_DATE "
            + "AND STR_DATE <= :TDRIREV.END_DATE )";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_STARTBR_TDTIREV_CON() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "END_DATE > :TDRSMST.VAL_DATE";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_STARTBR_TDTIREV_CON_2() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "STR_DATE >= :TDRIREV.KEY.STR_DATE";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_STARTBR_TDTIREV_STR() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "STR_DATE >= :TDRIREV.KEY.STR_DATE";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.Reqid = 3;
        TDTIREV_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_STARTBR_TDTIREV_STR2() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "STR_DATE >= :TDRIREV.KEY.STR_DATE";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_READNEXT_TDTIREV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IREV_EOF_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTIREV_STR() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp.Reqid = 3;
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IREV_EOF_FLG = 'Y';
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void T000_ENDBR_TDTIREV_STR() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp.Reqid = 3;
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void T000_DELETE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.DELETE(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void T000_DELETE_TDTIREV_STR() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.Reqid = 3;
        IBS.DELETE(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void T000_READUP_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.REWRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIAACR_FIRST() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND VCH_NO = :DCRIAACR.VCH_NO "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_RD.fst = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
