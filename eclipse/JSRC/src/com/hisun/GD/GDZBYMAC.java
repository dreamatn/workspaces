package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
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
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZBYMAC {
    int JIBS_tmp_int;
    brParm CITACAC_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTMSTR_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    brParm TDTIREV_BR = new brParm();
    DBParm TDTIREV_RD;
    DBParm DDTPRAT_RD;
    DBParm GDTPLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD830";
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_AC_BK = "043";
    String WS_ERR_MSG = " ";
    String WS_INQ_AC = " ";
    char WS_IREV_END = ' ';
    double WS_GUAR_BAL = 0;
    double WS_RL_BAL = 0;
    String WS_AC = " ";
    double WS_AC_CCAL_BAL = 0;
    int WS_NUM_9 = 0;
    GDZBYMAC_WS_OUTPUT_DATA WS_OUTPUT_DATA = new GDZBYMAC_WS_OUTPUT_DATA();
    char WS_RHIS_FLG = ' ';
    char WS_DDTPRAT_FLG = ' ';
    char WS_TDTSMST_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCRMST DDCRMST = new DDCRMST();
    long WS_TT_CNT = 0;
    String WS_MAIN_AC_NO = " ";
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRPRAT DDRPRAT = new DDRPRAT();
    TDRIREV TDRIREV = new TDRIREV();
    CIRACAC CIRACAC = new CIRACAC();
    DDRMSTR DDRMSTR = new DDRMSTR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCCINTI BPCCINTI = new BPCCINTI();
    CICQACRI CICQACRI = new CICQACRI();
    GDRPLDR GDRPLDR = new GDRPLDR();
    CICQACAC CICQACAC = new CICQACAC();
    GDCOQINF GDCOQINF = new GDCOQINF();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    GDRSTAC GDRSTAC = new GDRSTAC();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    GDCSQINF GDCSQINF;
    GDCBYMAC GDCBYMAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, GDCBYMAC GDCBYMAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCBYMAC = GDCBYMAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZBYMAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCBYMAC.INF.MAIN_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_ENTY_TYP == '3') {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = GDCBYMAC.INF.MAIN_AC;
            CIRACAC.AGR_SEQ = GDCBYMAC.INF.AC_SEQ;
            WS_RHIS_FLG = 'N';
            if (GDCBYMAC.INF.AC_SEQ != 0) {
                T000_STARTBR_CITACAC_SEQ();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_CITACAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
            while (WS_RHIS_FLG != 'Y') {
                GDCBYMAC.INF.AC_SEQ = CIRACAC.AGR_SEQ;
                B020_CALL_ACTY_PROC();
                if (pgmRtn) return;
                B030_GET_OUTPUT_DATA();
                if (pgmRtn) return;
                if (!(CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
                    && WS_TDTSMST_FLG == 'N')) {
                    B040_INQ_DATA_OUTPUT();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITACAC();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITACAC();
            if (pgmRtn) return;
        } else {
            B020_CALL_ACTY_PROC();
            if (pgmRtn) return;
            B030_GET_OUTPUT_DATA();
            if (pgmRtn) return;
            if (!(CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
                && WS_TDTSMST_FLG == 'N')) {
                B040_INQ_DATA_OUTPUT();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCBYMAC.INF.MAIN_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_ENTY_TYP == '3') {
            CEP.TRC(SCCGWA, "MAIN AC");
            WS_AC = CICQACRI.O_DATA.O_AGR_NO;
            WS_OUTPUT_DATA.WS_AC1 = GDCBYMAC.INF.MAIN_AC;
        } else {
            if (GDCBYMAC.INF.AC_SEQ != 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SINGLE_AC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_OUTPUT_DATA.WS_AC1 = GDCBYMAC.INF.MAIN_AC;
            WS_AC = CICQACRI.O_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, GDCBYMAC.INF.MAIN_AC);
        }
        CEP.TRC(SCCGWA, GDCBYMAC.INF.MAIN_AC);
        CEP.TRC(SCCGWA, GDCBYMAC.INF.AC_SEQ);
        WS_OUTPUT_DATA.WS_AC_SEQ = GDCBYMAC.INF.AC_SEQ;
    }
    public void B030_GET_OUTPUT_DATA() throws IOException,SQLException,Exception {
        B031_INQ_CI_INF();
        if (pgmRtn) return;
        B037_CALL_GDZRSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B032_READ_DDTMST();
            if (pgmRtn) return;
            B036_CALL_BPZPQPRD();
            if (pgmRtn) return;
            B033_READ_DDTCCY();
            if (pgmRtn) return;
        } else {
            B034_READ_TDTSMST();
            if (pgmRtn) return;
            B035_GET_INT_RAT();
            if (pgmRtn) return;
            B036_CALL_BPZPQPRD();
            if (pgmRtn) return;
        }
    }
    public void B031_INQ_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            WS_OUTPUT_DATA.WS_CI_CNM = CICACCU.DATA.CI_CNM;
        } else {
            WS_OUTPUT_DATA.WS_CI_CNM = CICACCU.DATA.AC_CNM;
        }
        WS_OUTPUT_DATA.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_OUTPUT_DATA.WS_OPEN_DT = CICACCU.DATA.OPEN_DT;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_CNM);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_ORG_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OPEN_DT);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_AC;
        CICQACAC.DATA.AGR_SEQ = GDCBYMAC.INF.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B032_READ_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = WS_AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_TYPE != 'N') {
            CEP.TRC(SCCGWA, "NOT GD AC");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        WS_OUTPUT_DATA.WS_AC_TYP = "" + DDRMST.AC_TYPE;
        JIBS_tmp_int = WS_OUTPUT_DATA.WS_AC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_OUTPUT_DATA.WS_AC_TYP = "0" + WS_OUTPUT_DATA.WS_AC_TYP;
        WS_OUTPUT_DATA.WS_AC_BR = DDRMST.OPEN_DP;
        WS_OUTPUT_DATA.WS_DOM_BR = DDRMST.OWNER_BR;
        WS_OUTPUT_DATA.WS_BOOK_BR = DDRMST.OWNER_BR;
        WS_OUTPUT_DATA.WS_PROD_CD = DDRMST.PROD_CODE;
        WS_OUTPUT_DATA.WS_AC_STS = DDRMST.AC_STS;
        WS_OUTPUT_DATA.WS_TYP = DDRMST.AC_TYPE;
        WS_OUTPUT_DATA.WS_BAL_TYP = DDRMST.SPC_KIND.charAt(0);
        WS_OUTPUT_DATA.WS_BUS_KNB = DDRMST.YW_TYP;
        WS_OUTPUT_DATA.WS_DD_FLG = DDRMST.CROS_DR_FLG;
        WS_OUTPUT_DATA.WS_CUTD_FLG = DDRMST.CROS_CR_FLG;
        if (DDRMST.FRG_CODE.trim().length() == 0) WS_OUTPUT_DATA.WS_FC_CD = 0;
        else WS_OUTPUT_DATA.WS_FC_CD = Short.parseShort(DDRMST.FRG_CODE);
        WS_OUTPUT_DATA.WS_OIC_NO = DDRMST.AC_OIC_NO;
        WS_OUTPUT_DATA.WS_RES_CD = DDRMST.AC_OIC_CODE;
        WS_OUTPUT_DATA.WS_SUB_DP = DDRMST.SUB_DP;
        WS_OUTPUT_DATA.WS_UPD_DATE = DDRMST.UPDTBL_DATE;
    }
    public void B033_READ_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = GDCBYMAC.INF.MAIN_AC;
        GDRPLDR.RELAT_STS = 'N';
        T000_GROUP_GDTPLDR();
        if (pgmRtn) return;
        WS_NUM_9 = (int) WS_TT_CNT;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_CCY = DDRCCY.CCY;
        WS_OUTPUT_DATA.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_OUTPUT_DATA.WS_AC_BAL = DDRCCY.CURR_BAL;
        WS_OUTPUT_DATA.WS_BAL_S = DDRCCY.HOLD_BAL;
        WS_RL_BAL = DDRCCY.MARGIN_BAL;
        WS_AC_CCAL_BAL = DDRCCY.CCAL_TOT_BAL;
        WS_OUTPUT_DATA.WS_AMT = DDRCCY.MARGIN_BAL;
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        WS_OUTPUT_DATA.WS_AVLRL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL + DDRCCY.CCAL_TOT_BAL;
        WS_OUTPUT_DATA.WS_AC_BAL = WS_OUTPUT_DATA.WS_AC_BAL + WS_AC_CCAL_BAL;
        if (WS_OUTPUT_DATA.WS_AVL_BAL < 0) {
            WS_OUTPUT_DATA.WS_AVL_BAL = 0;
        }
        if (WS_OUTPUT_DATA.WS_AVLRL_BAL < 0) {
            WS_OUTPUT_DATA.WS_AVLRL_BAL = 0;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUTPUT_DATA.WS_HD_TYPE = '1';
        }
        if (DDRCCY.HOLD_BAL > 0) {
            WS_OUTPUT_DATA.WS_HD_TYPE = '2';
        }
        WS_OUTPUT_DATA.WS_TL_TYP = '1';
        WS_OUTPUT_DATA.WS_OPN_TLR = DDRCCY.CRT_TLR;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_OUTPUT_DATA.WS_INT_SEL = '0';
        }
        if (DDRMSTR.TIR_SPR11 != 0) {
            WS_OUTPUT_DATA.WS_SPRD_PNT = DDRMSTR.TIR_SPR11;
            WS_OUTPUT_DATA.WS_INT_SEL = '1';
        }
        if (DDRMSTR.TIR_SPR_PCT11 != 0) {
            WS_OUTPUT_DATA.WS_SPRD_PCT = DDRMSTR.TIR_SPR_PCT11;
            WS_OUTPUT_DATA.WS_INT_SEL = '2';
        }
        if (DDRMSTR.TIR_FIX_RATE11 != 0) {
            WS_OUTPUT_DATA.WS_CON_RATE = DDRMSTR.TIR_FIX_RATE11;
            WS_OUTPUT_DATA.WS_INT_SEL = '4';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRMSTR.TIR_FIX_RATE11 != 0) {
                CEP.TRC(SCCGWA, DDRMSTR.TIR_FIX_RATE11);
                WS_OUTPUT_DATA.WS_INT_RAT = DDRMSTR.TIR_FIX_RATE11;
            } else {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = DDRMSTR.TIR_RBAS11;
                BPCCINTI.BASE_INFO.TENOR = DDRMSTR.TIR_RCD11;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.CCY = DDRCCY.CCY;
                BPCCINTI.BASE_INFO.BR = DDRCCY.OWNER_BR;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                if (DDRMSTR.TIR_SPR_PCT11 != 0) {
                    WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE * ( 1 + DDRMSTR.TIR_SPR_PCT11 );
                } else {
                    if (DDRMSTR.TIR_SPR11 != 0) {
                        WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE + DDRMSTR.TIR_SPR11;
                    } else {
                        WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE;
                    }
                }
            }
        } else {
            IBS.init(SCCGWA, DDRPRAT);
            DDRPRAT.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
            DDRPRAT.KEY.IBS_AC_BK = K_AC_BK;
            DDRPRAT.KEY.CCY = DDRCCY.CCY;
            DDRPRAT.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPRAT.KEY.PRDMO_CD = "CAAC";
            T000_READ_DDTPRAT();
            if (pgmRtn) return;
            if (WS_DDTPRAT_FLG == 'Y') {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = DDRPRAT.INT_RBAS11;
                BPCCINTI.BASE_INFO.TENOR = DDRPRAT.INT_RCD11;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.CCY = DDRCCY.CCY;
                BPCCINTI.BASE_INFO.BR = DDRCCY.OWNER_BR;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                if (DDRPRAT.TIER_SPR_PCT11 != 0) {
                    WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE * ( 1 + DDRPRAT.TIER_SPR_PCT11 );
                } else {
                    if (DDRPRAT.TIER_SPR11 != 0) {
                        WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE + DDRPRAT.TIER_SPR11;
                    } else {
                        WS_OUTPUT_DATA.WS_INT_RAT = BPCCINTI.BASE_INFO.OWN_RATE;
                    }
                }
            }
        }
    }
    public void B034_READ_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, GDCBYMAC.INF.AC_SEQ);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_AC;
        CICQACAC.DATA.AGR_SEQ = GDCBYMAC.INF.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_TDTSMST_FLG = 'N';
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = WS_AC;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = GDCBYMAC.INF.MAIN_AC;
        GDRPLDR.KEY.AC_SEQ = GDCBYMAC.INF.AC_SEQ;
        GDRPLDR.RELAT_STS = 'N';
        T000_GROUP_GDTPLDR();
        if (pgmRtn) return;
        WS_NUM_9 = (int) WS_TT_CNT;
        WS_OUTPUT_DATA.WS_AC_TYP = "" + TDRSMST.AC_TYP;
        JIBS_tmp_int = WS_OUTPUT_DATA.WS_AC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_OUTPUT_DATA.WS_AC_TYP = "0" + WS_OUTPUT_DATA.WS_AC_TYP;
        WS_OUTPUT_DATA.WS_AC_BR = TDRSMST.OWNER_BR;
        WS_OUTPUT_DATA.WS_DOM_BR = TDRSMST.OWNER_BR;
        WS_OUTPUT_DATA.WS_BOOK_BR = TDRSMST.OWNER_BR;
        WS_OUTPUT_DATA.WS_PROD_CD = TDRSMST.PROD_CD;
        WS_OUTPUT_DATA.WS_AC_STS = TDRSMST.ACO_STS;
        WS_OUTPUT_DATA.WS_CCY = TDRSMST.CCY;
        WS_OUTPUT_DATA.WS_CCY_TYPE = TDRSMST.CCY_TYP;
        WS_OUTPUT_DATA.WS_AC_BAL = TDRSMST.BAL;
        WS_OUTPUT_DATA.WS_BAL_S = TDRSMST.HBAL;
        WS_OUTPUT_DATA.WS_TYP = 'M';
        WS_OUTPUT_DATA.WS_TERM = TDRSMST.TERM;
        WS_OUTPUT_DATA.WS_BUS_KNB = TDRCMST.REF_TYP;
        WS_OUTPUT_DATA.WS_DD_FLG = TDRCMST.CROS_DR_FLG;
        WS_OUTPUT_DATA.WS_CUTD_FLG = TDRCMST.CROS_CR_FLG;
        WS_OUTPUT_DATA.WS_AMT = TDRSMST.GUAR_BAL;
        WS_OUTPUT_DATA.WS_FC_CD = TDRSMST.FC_CD;
        WS_OUTPUT_DATA.WS_UPD_DATE = TDRSMST.UPD_DATE;
        WS_OUTPUT_DATA.WS_OPN_TLR = TDRSMST.CRT_TLR;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BUS_KNB);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_INT_AC);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_TYP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_TERM);
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        WS_GUAR_BAL = TDRSMST.GUAR_BAL;
        WS_OUTPUT_DATA.WS_AVL_BAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        WS_OUTPUT_DATA.WS_AVLRL_BAL = TDRSMST.BAL - TDRSMST.HBAL - WS_GUAR_BAL;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AVLRL_BAL);
        if (WS_OUTPUT_DATA.WS_AVL_BAL < 0) {
            WS_OUTPUT_DATA.WS_AVL_BAL = 0;
        }
        if (WS_OUTPUT_DATA.WS_AVLRL_BAL < 0) {
            WS_OUTPUT_DATA.WS_AVLRL_BAL = 0;
        }
        WS_OUTPUT_DATA.WS_INSTR_MTH = TDRSMST.INSTR_MTH;
        WS_OUTPUT_DATA.WS_ST_DT = TDRSMST.VAL_DATE;
        WS_OUTPUT_DATA.WS_ET_DT = TDRSMST.EXP_DATE;
        WS_OUTPUT_DATA.WS_OIC_NO = TDRSMST.OIC_NO;
        WS_OUTPUT_DATA.WS_RES_CD = TDRSMST.RES_CD;
        WS_OUTPUT_DATA.WS_SUB_DP = TDRSMST.SUB_DP;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if ((TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
            || (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_OUTPUT_DATA.WS_HD_TYPE = '1';
        }
        if (TDRSMST.HBAL > 0) {
            WS_OUTPUT_DATA.WS_HD_TYPE = '2';
        }
        WS_OUTPUT_DATA.WS_TL_TYP = '1';
    }
    public void B035_GET_INT_RAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.STR_DATE = TDRSMST.EXP_DATE - 1;
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        T000_READ_TDTIREV();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_INT_RAT = TDRIREV.CON_RATE;
        WS_OUTPUT_DATA.WS_INT_SEL = TDRIREV.INT_SEL;
        WS_OUTPUT_DATA.WS_SPRD_PNT = TDRIREV.SPRD_PNT;
        WS_OUTPUT_DATA.WS_SPRD_PCT = TDRIREV.SPRD_PCT;
        WS_OUTPUT_DATA.WS_CON_RATE = TDRIREV.CON_RATE;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_INT_RAT);
    }
    public void B036_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_CD);
        BPCPQPRD.PRDT_CODE = WS_OUTPUT_DATA.WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.DESC);
        WS_OUTPUT_DATA.WS_PRDC_ENM = BPCPQPRD.DESC;
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_NAME);
        WS_OUTPUT_DATA.WS_PRDC_ENM = BPCPQPRD.PRDT_NAME;
    }
    public void B037_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'I';
        GDRSTAC.KEY.AC = GDCBYMAC.INF.MAIN_AC;
        GDRSTAC.KEY.AC_SEQ = GDCBYMAC.INF.AC_SEQ;
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        WS_OUTPUT_DATA.WS_INT_AC = GDRSTAC.INT_AC;
        WS_OUTPUT_DATA.WS_ST_AC = GDRSTAC.ST_AC;
    }
    public void B040_INQ_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_CNM);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_SEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_DOM_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BOOK_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_CD);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PRDC_ENM);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_TYP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_STS);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_TYP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CCY_TYPE);
        CEP.TRC(SCCGWA, WS_AC_CCAL_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AC_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BAL_S);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_AVL_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BAL_TYP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_HD_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_TERM);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_INSTR_MTH);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_ST_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_ET_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_TL_TYP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_INT_RAT);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_ORG_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BUS_KNB);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 860;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.where = "AGR_NO = :CIRACAC.AGR_NO";
        CITACAC_BR.rp.order = "AGR_SEQ";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC_SEQ() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.where = "AGR_NO = :CIRACAC.AGR_NO "
            + "AND AGR_SEQ = :CIRACAC.AGR_SEQ";
        CITACAC_BR.rp.order = "AGR_SEQ";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_RHIS_FLG = 'Y';
        }
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_COMMIT() throws IOException,SQLException,Exception {
        IBS.COMMIT(SCCGWA);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
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
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "AC = :DDRCCY.KEY.AC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC "
            + "AND PRDAC_CD = '033' "
            + "AND ACO_STS = '0'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_FLG = 'Y';
        } else {
            WS_TDTSMST_FLG = 'N';
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRIREV, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
        }
    }
    public void T000_READNEXT_TDTIREV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IREV_END = 'E';
        }
    }
    public void T000_ENDBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE > :TDRIREV.KEY.STR_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
    }
    public void T000_READ_DDTPRAT() throws IOException,SQLException,Exception {
        DDTPRAT_RD = new DBParm();
        DDTPRAT_RD.TableName = "DDTPRAT";
        DDTPRAT_RD.where = "IBS_AC_BK = :DDRPRAT.KEY.IBS_AC_BK "
            + "AND PRDMO_CD = :DDRPRAT.KEY.PRDMO_CD "
            + "AND PARM_CODE = :DDRPRAT.KEY.PARM_CODE "
            + "AND CCY = :DDRPRAT.KEY.CCY "
            + "AND EFF_DATE <= :DDRPRAT.KEY.EFF_DATE "
            + "AND EXP_DATE >= :DDRPRAT.KEY.EFF_DATE";
        IBS.READ(SCCGWA, DDRPRAT, this, DDTPRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTPRAT_FLG = 'Y';
        } else {
            WS_DDTPRAT_FLG = 'N';
        }
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.set = "WS-TT-CNT=COUNT(RSEQ)";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TT_CNT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
