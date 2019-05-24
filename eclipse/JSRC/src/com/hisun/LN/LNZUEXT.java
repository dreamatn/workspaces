package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUEXT {
    int JIBS_tmp_int;
    brParm LNTINTA_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATH_RD;
    DBParm LNTRATN_RD;
    DBParm LNTRATL_RD;
    DBParm LNTEXTN_RD;
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTRCVD_RD;
    brParm LNTTRAN_BR = new brParm();
    brParm LNTEXTN_BR = new brParm();
    DBParm LNTPAIP_RD;
    DBParm LNTBALZ_RD;
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_HIS_RMKS = "CONTRACT EXTENSION";
    String K_HIS_REMARKS = "CONTRACT OVD CHANGE TO NOR AN CHANGE MUT DT";
    char K_YEAR = 'Y';
    String WS_ERR_MSG = " ";
    char WS_GMST_END = ' ';
    char WS_CODE = ' ';
    char WS_PAY_MTH = ' ';
    short WS_CNT = 0;
    String WS_CTA_NO = " ";
    char WS_CNT_CMMT = ' ';
    char WS_CNT_CONT = ' ';
    char WS_CNT_ICTL = ' ';
    String WS_PROD_CD = " ";
    String WS_CI_NO = " ";
    String WS_CTA_NO1 = " ";
    int WS_DUE_DT = 0;
    int WS_START_DT = 0;
    int WS_EXTEND_EXP_DATE = 0;
    String WS_AUTH_LVL = " ";
    LNZUEXT_REDEFINES17 REDEFINES17 = new LNZUEXT_REDEFINES17();
    char WS_FND_FLG = ' ';
    char WS_FND_FLG2 = ' ';
    char WS_CHG_PAY_SCH = ' ';
    char WS_AUTO_PLAN = ' ';
    char WS_TRAN_FOUND_FLG = ' ';
    char WS_INTA_FOUND_FLG = ' ';
    int WS_SUB_CTA_NO = 0;
    short WS_P_TERM = 0;
    int WS_RCVD_VAL_DT = 0;
    int WS_RCVD_DUE_DT = 0;
    double WS_P_NOR_AMT1 = 0;
    double WS_P_OVD_AMT1 = 0;
    double WS_DUE_PRI_AMT = 0;
    int WS_BRAT_EFF_DT = 0;
    int WS_ORAT_EFF_DT = 0;
    int WS_LRAT_EFF_DT = 0;
    int WS_PRAT_EFF_DT = 0;
    short WS_FLT_DAY = 0;
    int WS_OLD_DATE = 0;
    int WS_VAL_DATE = 0;
    short WS_CAL_TERM = 0;
    short WS_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    short WS_IC_CAL_TERM = 0;
    double WS_P_AMT = 0;
    double WS_L_AMT = 0;
    double WS_ROVD_P_AMT = 0;
    double WS_NOP_AMT = 0;
    double WS_OVP_AMT = 0;
    double WS_MOVE_AMT = 0;
    double WS_ALL_P_AMT = 0;
    int WS_LAST_F_VAL_DATE = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_OE_DATE = 0;
    char WS_EXT_FLG = ' ';
    char WS_RCVD_FLG = ' ';
    char WS_ERR_FLG = ' ';
    char WS_REXTN_FLG = ' ';
    char WS_OVD_FLG = ' ';
    char WS_SPECIAL_FLG = ' ';
    char WS_REAT_O = ' ';
    char WS_REAT_L = ' ';
    char WS_REAT_P = ' ';
    double WS_PHS_INST_AMT = 0;
    double WS_PHS_PRIN_AMT = 0;
    short WS_PHS_TOT_TERM = 0;
    double WS_PHS_REM_PRIN_AMT = 0;
    short WS_PHS_CAL_TERM = 0;
    short WS_PHS_CMP_TERM = 0;
    double WS_CUR_INST_AMT = 0;
    double WS_CUR_INST_IRAT = 0;
    int WS_CNEV_DT = 0;
    String WS_CTL_STSW = " ";
    double WS_TMP_AMT = 0;
    double WS_P_PAY_AMT = 0;
    double WS_ADJ_LC_AMT = 0;
    double WS_ADJ_O_AMT = 0;
    double WS_ADJ_L_AMT = 0;
    int WS_NEXT_LC_CAL_DAT = 0;
    int WS_NEXT_LC_CAL_DAT2 = 0;
    char WS_RATH_FLG = ' ';
    int WS_RATE_DT = 0;
    char WS_UPEXTN_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCIGVCH LNCIGVCH = new LNCIGVCH();
    LNCHUEXT LNCHUEXO = new LNCHUEXT();
    LNCHUEXT LNCHUEXN = new LNCHUEXT();
    LNCICRL LNCICRL = new LNCICRL();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNCURTN LNCSRTN = new LNCURTN();
    LNCURTL LNCSRTL = new LNCURTL();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNREXTN LNREXTN = new LNREXTN();
    LNCREXTN LNCREXTN = new LNCREXTN();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRINTA LNRINTA = new LNRINTA();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCBKRAT LNCBKRAT = new LNCBKRAT();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCICAL LNCICAL = new LNCICAL();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNRBALZ LNRBALZ = new LNRBALZ();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCSRATE LNCSRATE = new LNCSRATE();
    LNRRATH LNRRATH = new LNRRATH();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCUEXT LNCUEXT;
    public void MP(SCCGWA SCCGWA, LNCUEXT LNCUEXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUEXT = LNCUEXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUEXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        LNCUEXT.RC.RC_APP = "LN";
        LNCUEXT.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCUEXT.OE_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUEXT.CTA_NO);
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_INTEREST, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CHG_PAY_SCH = 'Y';
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCUEXT.CTA_TYP == 'D') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B030_MAIN_PROC();
                if (pgmRtn) return;
                B030_UPDATE_CONT_PROC();
                if (pgmRtn) return;
                B040_UPDATE_ICTL_PROC();
                if (pgmRtn) return;
                B050_RCAL_PROC();
                if (pgmRtn) return;
                B050_PRIN_SPECIAL_PROC();
                if (pgmRtn) return;
                B060_RATE_PROCESS();
                if (pgmRtn) return;
                B070_PLAN_ADJ_PROCESS();
                if (pgmRtn) return;
                B051_ICAL_PROC();
                if (pgmRtn) return;
                B080_UPDATE_PAIP_PROC();
                if (pgmRtn) return;
            } else {
                B030_UPDATE_CONT_PROC();
                if (pgmRtn) return;
                B040_UPDATE_ICTL_PROC();
                if (pgmRtn) return;
                B050_PRIN_SPECIAL_PROC();
                if (pgmRtn) return;
                B060_RATE_PROCESS();
                if (pgmRtn) return;
                B070_PLAN_ADJ_PROCESS();
                if (pgmRtn) return;
                B090_WRITE_TRAN_PROC();
                if (pgmRtn) return;
            }
        } else if (LNCUEXT.CTA_TYP == 'C') {
            WS_CTA_NO = LNCUEXT.CTA_NO;
            IBS.init(SCCGWA, LNCHUEXO);
            if (LNCUEXT.OE_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                && LNCUEXT.B_AMT > 0) {
                C000_GEN_VCH_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCUEXT.CTA_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B061_BAK_UP_RATE() throws IOException,SQLException,Exception {
        if (LNCUEXT.EXT_FLG == '2') {
            WS_RATE_DT = LNCUEXT.VAL_DATE;
        } else {
            if (LNCUEXT.VAL_DATE < LNCUEXT.OE_DATE) {
                WS_RATE_DT = LNCUEXT.OE_DATE;
            } else {
                WS_RATE_DT = LNCUEXT.VAL_DATE;
            }
        }
        IBS.init(SCCGWA, LNCBKRAT);
        LNCBKRAT.VAL_DT = WS_RATE_DT;
        CEP.TRC(SCCGWA, "B061 11111111");
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.IRAT_CD);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.IRATCLS);
        CEP.TRC(SCCGWA, LNCSRATE.FUNC_TYP);
        if ((LNCUEXT.RAT_INFO.IRAT_CD.trim().length() > 0 
            && LNCUEXT.RAT_INFO.IRATCLS.trim().length() > 0) 
            || (LNCUEXT.RAT_INFO.IRAT_CD.trim().length() == 0 
            && LNCUEXT.RAT_INFO.IRATCLS.trim().length() == 0)) {
            IBS.init(SCCGWA, LNRRATH);
            IBS.init(SCCGWA, LNCSRATE);
            LNRRATH.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNRRATH.KEY.SUB_CTA_NO = 0;
            LNRRATH.KEY.RATE_TYP = 'N';
            LNRRATH.KEY.RAT_CHG_DT = WS_RATE_DT;
            T00_READ_LNTRATH();
            if (pgmRtn) return;
            if (WS_RATH_FLG == 'Y') {
                LNCSRATE.FUNC_TYP = 'M';
                LNCBKRAT.OLD_FLG_N = 'M';
                LNCBKRAT.OLDRAT_INFO.OLD_RATT_N = LNRRATH.RATE_KIND;
                LNCBKRAT.OLDRAT_INFO.OLD_IRA_N = LNRRATH.INT_RAT;
            } else {
                LNCSRATE.FUNC_TYP = 'A';
                LNCBKRAT.OLD_FLG_N = 'A';
            }
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = WS_RATE_DT;
            LNCSRATE.DATA.RATE_KIND_N = K_YEAR;
            LNCSRATE.DATA.FLT_MTH_N = LNCUEXT.RAT_INFO.FLT_MTH;
            CEP.TRC(SCCGWA, LNCSRATE.DATA.FLT_MTH_N);
            LNCSRATE.DATA.RATE_TYP_N = LNCUEXT.RAT_INFO.IRAT_CD;
            LNCSRATE.DATA.RATEPERD_N = LNCUEXT.RAT_INFO.IRATCLS;
            LNCSRATE.DATA.RATE_VAR_N = LNCUEXT.RAT_INFO.INT_RTV;
            LNCSRATE.DATA.RATE_PCT_N = LNCUEXT.RAT_INFO.RAT_PCT;
            LNCSRATE.DATA.RATE_INT_N = LNCUEXT.RAT_INFO.ALL_RAT;
            CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.INT_RAT);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_INT_N);
            LNCSRATE.DATA.INT_RAT_N = LNCUEXT.RAT_INFO.ALL_RAT;
            CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.ALL_RAT);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
            LNCSRATE.DATA.MTH = LNCUEXT.RAT_INFO.RAT_FLG;
        }
        CEP.TRC(SCCGWA, "B061 22222222");
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.PEN_RATT);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.PEN_RATC);
        CEP.TRC(SCCGWA, LNCSRATE.FUNC_TYP);
        if ((LNCUEXT.RAT_INFO.PEN_RATT.trim().length() > 0 
            && LNCUEXT.RAT_INFO.PEN_RATC.trim().length() > 0) 
            || LNCUEXT.RAT_INFO.PEN_RRAT == 'F') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNRRATH.KEY.SUB_CTA_NO = 0;
            LNRRATH.KEY.RATE_TYP = 'O';
            LNRRATH.KEY.RAT_CHG_DT = WS_RATE_DT;
            T00_READ_LNTRATH();
            if (pgmRtn) return;
            if (WS_RATH_FLG == 'Y') {
                LNCSRATE.FUNC_TYP = 'M';
                LNCBKRAT.OLD_FLG_O = 'M';
                LNCBKRAT.OLDRAT_INFO.OLD_RATT_O = LNRRATH.RATE_KIND;
                LNCBKRAT.OLDRAT_INFO.OLD_IRA_O = LNRRATH.INT_RAT;
            } else {
                LNCSRATE.FUNC_TYP = 'A';
                LNCBKRAT.OLD_FLG_O = 'A';
            }
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = WS_RATE_DT;
            LNCSRATE.DATA.RATE_KIND_O = K_YEAR;
            LNCSRATE.DATA.PEN_RRAT_O = LNCUEXT.RAT_INFO.PEN_RRAT;
            LNCSRATE.DATA.PEN_TYP_O = LNCUEXT.RAT_INFO.PEN_TYP;
            LNCSRATE.DATA.PEN_RATT_O = LNCUEXT.RAT_INFO.PEN_RATT;
            LNCSRATE.DATA.PEN_RATC_O = LNCUEXT.RAT_INFO.PEN_RATC;
            LNCSRATE.DATA.PEN_SPR_O = LNCUEXT.RAT_INFO.PEN_SPR;
            LNCSRATE.DATA.PEN_PCT_O = LNCUEXT.RAT_INFO.PEN_PCT;
            LNCSRATE.DATA.INT_RAT_O = LNCUEXT.RAT_INFO.PEN_IRAT;
            CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.PEN_IRAT);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_O);
        }
        CEP.TRC(SCCGWA, "B061 33333333");
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.CPND_USE);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.CPNDRATT);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.CPNDRATC);
        CEP.TRC(SCCGWA, LNCSRATE.FUNC_TYP);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_O);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_P);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_L);
        if (LNCUEXT.RAT_INFO.CPND_USE == 'Y' 
            || (LNCUEXT.RAT_INFO.CPND_USE == 'N' 
            && LNCUEXT.RAT_INFO.CPNDRATT.trim().length() > 0 
            && LNCUEXT.RAT_INFO.CPNDRATC.trim().length() > 0)) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNRRATH.KEY.SUB_CTA_NO = 0;
            LNRRATH.KEY.RATE_TYP = 'L';
            LNRRATH.KEY.RAT_CHG_DT = WS_RATE_DT;
            T00_READ_LNTRATH();
            if (pgmRtn) return;
            if (WS_RATH_FLG == 'Y') {
                LNCSRATE.FUNC_TYP = 'M';
                LNCBKRAT.OLD_FLG_L = 'M';
                LNCBKRAT.OLDRAT_INFO.OLD_RATT_L = LNRRATH.RATE_KIND;
                LNCBKRAT.OLDRAT_INFO.OLD_IRA_L = LNRRATH.INT_RAT;
            } else {
                LNCSRATE.FUNC_TYP = 'A';
                LNCBKRAT.OLD_FLG_L = 'A';
            }
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = WS_RATE_DT;
            LNCSRATE.DATA.INT_MTH = LNCUEXT.RAT_INFO.INT_MTH;
            LNCSRATE.DATA.CPND_USE = LNCUEXT.RAT_INFO.CPND_USE;
            LNCSRATE.DATA.RATE_KIND_L = K_YEAR;
            LNCSRATE.DATA.CPND_RTY_L = LNCUEXT.RAT_INFO.CPND_RRAT;
            LNCSRATE.DATA.CPND_TYP_L = LNCUEXT.RAT_INFO.CPND_TYP;
            LNCSRATE.DATA.CPNDRATT_L = LNCUEXT.RAT_INFO.CPNDRATT;
            LNCSRATE.DATA.CPNDRATC_L = LNCUEXT.RAT_INFO.CPNDRATC;
            LNCSRATE.DATA.CPND_SPR_L = LNCUEXT.RAT_INFO.CPND_SPR;
            LNCSRATE.DATA.CPND_PCT_L = LNCUEXT.RAT_INFO.CPND_PCT;
            LNCSRATE.DATA.INT_RAT_L = LNCUEXT.RAT_INFO.CPND_IRAT;
            if (LNCUEXT.RAT_INFO.CPND_USE == 'Y') {
                LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
                LNCSRATE.DATA.SUB_CTA_NO = 0;
                LNCSRATE.DATA.RAT_CHG_DT = WS_RATE_DT;
                LNCSRATE.DATA.INT_MTH = LNCUEXT.RAT_INFO.INT_MTH;
                LNCSRATE.DATA.CPND_USE = LNCUEXT.RAT_INFO.CPND_USE;
                LNCSRATE.DATA.RATE_KIND_L = K_YEAR;
                LNCSRATE.DATA.CPND_RTY_L = LNCUEXT.RAT_INFO.PEN_RRAT;
                LNCSRATE.DATA.CPND_TYP_L = LNCUEXT.RAT_INFO.PEN_TYP;
                LNCSRATE.DATA.CPNDRATT_L = LNCUEXT.RAT_INFO.PEN_RATT;
                LNCSRATE.DATA.CPNDRATC_L = LNCUEXT.RAT_INFO.PEN_RATC;
                LNCSRATE.DATA.CPND_SPR_L = LNCUEXT.RAT_INFO.PEN_SPR;
                LNCSRATE.DATA.CPND_PCT_L = LNCUEXT.RAT_INFO.PEN_PCT;
                LNCSRATE.DATA.INT_RAT_L = LNCUEXT.RAT_INFO.PEN_IRAT;
            }
        }
        S000_CALL_LNZSRATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B061 44444444");
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.ABUSRATT);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.ABUSRATC);
        CEP.TRC(SCCGWA, LNCSRATE.FUNC_TYP);
    }
    public void B030_UPDATE_CONT_PROC() throws IOException,SQLException,Exception {
        S000_READUPDATE_CONT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && LNRCONT.LAST_TX_SEQ != LNRTRAN.TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        S000_REWRITE_CONT();
        if (pgmRtn) return;
        WS_PROD_CD = LNRCONT.PROD_CD;
        WS_CTA_NO = LNRCONT.KEY.CONTRACT_NO;
        WS_START_DT = LNRCONT.START_DATE;
        WS_DUE_DT = LNRCONT.MAT_DATE;
    }
    public void B040_UPDATE_ICTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        WS_NEXT_LC_CAL_DAT = LNRICTL.NEXT_LC_CAL_DAT;
        WS_SUB_CTA_NO = LNRICTL.KEY.SUB_CTA_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_CTL_STSW = LNRICTL.CTL_STSW;
        }
        if (WS_PAY_MTH == '4') {
            WS_CAL_TERM = LNRICTL.IC_CAL_TERM;
            WS_CUR_TERM = LNRICTL.IC_CUR_TERM;
        } else {
            WS_CAL_TERM = LNRICTL.P_CAL_TERM;
            WS_CUR_TERM = LNRICTL.P_CUR_TERM;
            WS_IC_CAL_TERM = LNRICTL.IC_CAL_TERM;
        }
        LNCRICTL.FUNC = 'U';
        if (LNCUEXT.FLG == ' ') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 33 - 1) + "1" + LNRICTL.CTL_STSW.substring(33 + 1 - 1);
            } else {
                if (WS_CTL_STSW == null) WS_CTL_STSW = "";
                JIBS_tmp_int = WS_CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 33 - 1) + WS_CTL_STSW.substring(33 - 1, 33 + 1 - 1) + LNRICTL.CTL_STSW.substring(33 + 1 - 1);
                LNRICTL.NEXT_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT2;
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 35 - 1) + "1" + LNRICTL.CTL_STSW.substring(35 + 1 - 1);
            } else {
                if (WS_CTL_STSW == null) WS_CTL_STSW = "";
                JIBS_tmp_int = WS_CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 35 - 1) + WS_CTL_STSW.substring(35 - 1, 35 + 1 - 1) + LNRICTL.CTL_STSW.substring(35 + 1 - 1);
                LNRICTL.NEXT_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT2;
            }
        }
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void B050_PRIN_SPECIAL_PROC() throws IOException,SQLException,Exception {
        B054_MAIN_MOVE_AMT();
        if (pgmRtn) return;
    }
    public void B054_MAIN_MOVE_AMT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            T000_STARTBR_LNTRCVD();
            if (pgmRtn) return;
            T000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            while (WS_RCVD_FLG != 'Y') {
                B092_WRITE_LNTTRAN();
                if (pgmRtn) return;
                if (LNRRCVD.KEY.AMT_TYP != 'I') {
                    B154_MAIN_MOVE_AMT();
                    if (pgmRtn) return;
                }
                T000_READNEXT_LNTRCVD();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTRCVD();
            if (pgmRtn) return;
        } else {
            R000_CANCEL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B154_MAIN_MOVE_AMT() throws IOException,SQLException,Exception {
        WS_MOVE_AMT = 0;
        IBS.init(SCCGWA, LNCRCVDM);
        if (LNRRCVD.TERM_STS == '0') {
            WS_MOVE_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
            WS_NOP_AMT = WS_NOP_AMT + LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
        } else {
            WS_MOVE_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
            WS_OVP_AMT = WS_OVP_AMT + LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
        }
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        CEP.TRC(SCCGWA, WS_NOP_AMT);
        CEP.TRC(SCCGWA, WS_OVP_AMT);
        WS_ALL_P_AMT = WS_NOP_AMT + WS_OVP_AMT;
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.TERM = LNRRCVD.KEY.TERM;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        WS_RCVD_VAL_DT = LNCRCVDM.REC_DATA.VAL_DT;
        WS_RCVD_DUE_DT = LNCRCVDM.REC_DATA.DUE_DT;
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNCUEXT.CTA_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TYPE = LNRRCVD.KEY.AMT_TYP;
        LNCLCCM.COMM_DATA.TERM = LNRRCVD.KEY.TERM;
        LNCLCCM.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
        if (LNRRCVD.P_PAY_AMT != 0) {
            LNCRCVDM.REC_DATA.P_REC_AMT = LNRRCVD.P_PAY_AMT;
        } else {
            LNCRCVDM.REC_DATA.P_REC_AMT = 0;
        }
        R000_GET_ADJ_AMT();
        if (pgmRtn) return;
        LNCRCVDM.REC_DATA.O_REC_AMT = LNCLCCM.COMM_DATA.O_AMT + LNRRCVD.O_PAY_AMT + LNRRCVD.O_WAV_AMT - WS_ADJ_O_AMT;
        LNCRCVDM.REC_DATA.L_REC_AMT = LNCLCCM.COMM_DATA.L_AMT + LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT - WS_ADJ_L_AMT;
        WS_TMP_AMT = 0;
        WS_TMP_AMT = LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT + LNCRCVDM.REC_DATA.I_REC_AMT - LNCRCVDM.REC_DATA.I_PAY_AMT - LNCRCVDM.REC_DATA.I_WAV_AMT + LNCRCVDM.REC_DATA.O_REC_AMT - LNCRCVDM.REC_DATA.O_PAY_AMT - LNCRCVDM.REC_DATA.O_WAV_AMT + LNCRCVDM.REC_DATA.L_REC_AMT - LNCRCVDM.REC_DATA.L_PAY_AMT - LNCRCVDM.REC_DATA.L_WAV_AMT + LNCRCVDM.REC_DATA.F_REC_AMT - LNCRCVDM.REC_DATA.F_PAY_AMT - LNCRCVDM.REC_DATA.F_WAV_AMT + WS_ADJ_O_AMT + WS_ADJ_L_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        if (WS_TMP_AMT == 0) {
            LNCRCVDM.REC_DATA.REPY_STS = '2';
        }
        LNCRCVDM.REC_DATA.PI_STOP_DT = LNCUEXT.VAL_DATE;
        LNCRCVDM.FUNC = '2';
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        if (WS_MOVE_AMT != 0) {
            CEP.TRC(SCCGWA, "REWRITE PLPI");
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '4';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
            LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNRRCVD.KEY.AMT_TYP;
            LNCPLPIM.REC_DATA.KEY.TERM = LNRRCVD.KEY.TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
            CEP.TRC(SCCGWA, WS_MOVE_AMT);
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT - WS_MOVE_AMT;
            if (LNCPLPIM.REC_DATA.PRIN_AMT >= WS_MOVE_AMT) {
                LNCPLPIM.REC_DATA.PRIN_AMT = LNCPLPIM.REC_DATA.PRIN_AMT - WS_MOVE_AMT;
            }
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
            CEP.TRC(SCCGWA, WS_OE_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (WS_OE_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                LNRBALZ.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
                LNRBALZ.KEY.SUB_CTA_NO = 0;
                T00_READUPDATE_LNTBALZ();
                if (pgmRtn) return;
                LNRBALZ.LOAN_BALL02 = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
                LNRBALZ.LOAN_BALL05 = 0;
                LNRBALZ.LOAN_BALL06 = 0;
                T00_REWRITE_LNTBALZ();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_ADJ_AMT() throws IOException,SQLException,Exception {
        if (LNRRCVD.KEY.AMT_TYP == 'P') {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'O';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
        } else if (LNRRCVD.KEY.AMT_TYP == 'I') {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'L';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
        } else {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'O';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'L';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
        }
    }
    public void B051_MAIN_SPECIAL_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B152_REMEMBER_RCVD_PROC();
            if (pgmRtn) return;
            B151_UPD_PLPI_PROC();
            if (pgmRtn) return;
            if (WS_PAY_MTH == '1') {
                B052_RCAL_PROC();
                if (pgmRtn) return;
                B053_UPD_PLPI_PROC();
                if (pgmRtn) return;
            }
        } else {
            R000_CANCEL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B151_UPD_PLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = 1;
        LNCPLPIM.REC_DATA.KEY.TERM = (short) (WS_CAL_TERM - 1);
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.REC_STS = 'R';
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
        LNCPLPIM.REC_DATA.DUE_REPY_AMT -= WS_P_PAY_AMT;
        LNCPLPIM.REC_DATA.PRIN_AMT -= WS_P_PAY_AMT;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
    }
    public void B152_REMEMBER_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'I';
        LNCRCVDM.REC_DATA.KEY.TERM = (short) (WS_CAL_TERM - 1);
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.P_PAY_AMT);
        if (LNCRCVDM.REC_DATA.P_PAY_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_ERROR, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'P';
        LNCRCVDM.REC_DATA.KEY.TERM = (short) (WS_CAL_TERM - 1);
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.P_PAY_AMT);
        if (LNCRCVDM.REC_DATA.P_PAY_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_ERROR, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_P_PAY_AMT = LNCRCVDM.REC_DATA.P_PAY_AMT;
        CEP.TRC(SCCGWA, WS_P_PAY_AMT);
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.TERM_STS);
        if (LNCRCVDM.REC_DATA.TERM_STS == '0') {
            WS_MOVE_AMT = LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT;
            WS_NOP_AMT = WS_NOP_AMT + LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT;
        } else {
            WS_MOVE_AMT = LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT;
            WS_OVP_AMT = WS_OVP_AMT + LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT;
        }
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        CEP.TRC(SCCGWA, WS_NOP_AMT);
        CEP.TRC(SCCGWA, WS_OVP_AMT);
        WS_ALL_P_AMT = WS_NOP_AMT + WS_OVP_AMT;
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        WS_CNEV_DT = LNCRCVDM.REC_DATA.DUE_DT;
        WS_RCVD_VAL_DT = LNCRCVDM.REC_DATA.VAL_DT;
        WS_RCVD_DUE_DT = LNCRCVDM.REC_DATA.DUE_DT;
        CEP.TRC(SCCGWA, WS_CNEV_DT);
        B091_WRITE_LNTTRAN();
        if (pgmRtn) return;
        LNCRCVDM.FUNC = '1';
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        LNCRICTL.FUNC = 'U';
        LNRICTL.P_CAL_TERM = (short) (LNRICTL.P_CAL_TERM - 1);
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void B052_RCAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "RCAL -----------------");
        IBS.init(SCCGWA, LNCRCAL);
        LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
        LNCRCAL.COMM_DATA.LN_AC = LNCUEXT.CTA_NO;
        LNCRCAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
        LNCRCAL.COMM_DATA.FUNC_TYPE = 'I';
        LNCRCAL.COMM_DATA.VAL_DATE = WS_RCVD_VAL_DT;
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.VAL_DATE);
        S000_CALL_LNZRCAL();
        if (pgmRtn) return;
    }
    public void B053_UPD_PLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'I';
        LNCPLPIM.REC_DATA.KEY.TERM = (short) (WS_IC_CAL_TERM - 1);
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.REC_STS = 'R';
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.REC_STS);
    }
    public void R000_CANCEL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CANCEL----------");
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNRTRAN.KEY.REC_FLAG = 'I';
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.KEY.TRAN_FLG = 'D';
        T000_STARTBR_LNTTRAN_2();
        if (pgmRtn) return;
        WS_ERR_FLG = 'N';
        T000_READNEXT_LNTTRAN();
        if (pgmRtn) return;
        while (WS_TRAN_FOUND_FLG != 'N') {
            B042_UPDATE_DATA();
            if (pgmRtn) return;
            T000_READNEXT_LNTTRAN();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTTRAN();
        if (pgmRtn) return;
    }
    public void B080_UPDATE_PAIP_PROC() throws IOException,SQLException,Exception {
        if (WS_PAY_MTH == '4' 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B302_UPDATE_PAIP_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_WRITE_TRAN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B091_WRITE_TOT_TRAN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_RATE_PROCESS() throws IOException,SQLException,Exception {
        WS_UPEXTN_FLG = 'N';
        if ((LNCUEXT.RAT_INFO.ADJ_FLG != 'N' 
            || WS_PAY_MTH == '4') 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B032_REUPDATE_EXTN();
            if (pgmRtn) return;
            WS_UPEXTN_FLG = 'Y';
        }
        if (LNCUEXT.RAT_INFO.ADJ_FLG != 'N' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B061_BAK_UP_RATE();
            if (pgmRtn) return;
        }
        if (WS_PAY_MTH == '4' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B025_REMEMBER_PAIP_PROC();
            if (pgmRtn) return;
        }
        if (WS_UPEXTN_FLG == 'Y') {
            B034_UPDATE_EXTN();
            if (pgmRtn) return;
        }
        if (LNCUEXT.RAT_INFO.ADJ_FLG != 'N' 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B061_CANCEL_RATE();
            if (pgmRtn) return;
        }
    }
    public void B061_CANCEL_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_P);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_L);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_O);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_N);
        if (LNCUEXT.RAT_INFO.ABUSRATT.trim().length() > 0 
            && LNCUEXT.RAT_INFO.ABUSIRAT != 0) {
            IBS.init(SCCGWA, LNCSRATE);
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = LNCBKRAT.VAL_DT;
            if (LNCBKRAT.OLD_FLG_P == 'A') {
                LNCSRATE.FUNC_TYP = 'D';
                LNCSRATE.OPE_RATE = 'P';
            } else {
                LNCSRATE.FUNC_TYP = 'M';
            }
            LNCSRATE.DATA.RATE_KIND_P = LNCBKRAT.OLDRAT_INFO.OLD_RATT_P;
            LNCUEXT.RAT_INFO.ABUSRATT = "" + LNCBKRAT.OLDRAT_INFO.OLD_RATT_P;
            JIBS_tmp_int = LNCUEXT.RAT_INFO.ABUSRATT.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.ABUSRATT = "0" + LNCUEXT.RAT_INFO.ABUSRATT;
            LNCSRATE.DATA.INT_RAT_P = LNCBKRAT.OLDRAT_INFO.OLD_IRA_P;
            LNCUEXT.RAT_INFO.ABUSIRAT = LNCBKRAT.OLDRAT_INFO.OLD_IRA_P;
            S000_CALL_LNZSRATE();
            if (pgmRtn) return;
        }
        if (LNCUEXT.RAT_INFO.CPND_USE == 'Y' 
            || (LNCUEXT.RAT_INFO.CPND_USE == 'N' 
            && LNCUEXT.RAT_INFO.CPNDRATT.trim().length() > 0 
            && LNCUEXT.RAT_INFO.CPND_IRAT != 0)) {
            IBS.init(SCCGWA, LNCSRATE);
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = LNCBKRAT.VAL_DT;
            if (LNCBKRAT.OLD_FLG_L == 'A') {
                LNCSRATE.FUNC_TYP = 'D';
                LNCSRATE.OPE_RATE = 'L';
            } else {
                LNCSRATE.FUNC_TYP = 'M';
            }
            LNCSRATE.DATA.RATE_KIND_L = LNCBKRAT.OLDRAT_INFO.OLD_RATT_L;
            LNCUEXT.RAT_INFO.CPNDRATT = "" + LNCBKRAT.OLDRAT_INFO.OLD_RATT_L;
            JIBS_tmp_int = LNCUEXT.RAT_INFO.CPNDRATT.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.CPNDRATT = "0" + LNCUEXT.RAT_INFO.CPNDRATT;
            LNCSRATE.DATA.INT_RAT_L = LNCBKRAT.OLDRAT_INFO.OLD_IRA_L;
            LNCUEXT.RAT_INFO.CPND_IRAT = LNCBKRAT.OLDRAT_INFO.OLD_IRA_L;
            LNCSRATE.DATA.CPND_USE = LNCUEXT.RAT_INFO.CPND_USE;
            S000_CALL_LNZSRATE();
            if (pgmRtn) return;
        }
        if (LNCUEXT.RAT_INFO.PEN_RATT.trim().length() > 0 
            && LNCUEXT.RAT_INFO.PEN_IRAT != 0) {
            IBS.init(SCCGWA, LNCSRATE);
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = LNCBKRAT.VAL_DT;
            if (LNCBKRAT.OLD_FLG_O == 'A') {
                LNCSRATE.FUNC_TYP = 'D';
                LNCSRATE.OPE_RATE = 'O';
            } else {
                LNCSRATE.FUNC_TYP = 'M';
            }
            LNCSRATE.DATA.RATE_KIND_O = LNCBKRAT.OLDRAT_INFO.OLD_RATT_O;
            LNCUEXT.RAT_INFO.PEN_RATT = "" + LNCBKRAT.OLDRAT_INFO.OLD_RATT_O;
            JIBS_tmp_int = LNCUEXT.RAT_INFO.PEN_RATT.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.PEN_RATT = "0" + LNCUEXT.RAT_INFO.PEN_RATT;
            LNCSRATE.DATA.INT_RAT_O = LNCBKRAT.OLDRAT_INFO.OLD_IRA_O;
            LNCUEXT.RAT_INFO.PEN_IRAT = LNCBKRAT.OLDRAT_INFO.OLD_IRA_O;
            S000_CALL_LNZSRATE();
            if (pgmRtn) return;
        }
        if (LNCUEXT.RAT_INFO.IRAT_CD.trim().length() > 0 
            && LNCUEXT.RAT_INFO.INT_RAT != 0) {
            IBS.init(SCCGWA, LNCSRATE);
            LNCSRATE.DATA.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = LNCBKRAT.VAL_DT;
            if (LNCBKRAT.OLD_FLG_N == 'A') {
                LNCSRATE.FUNC_TYP = 'D';
                LNCSRATE.OPE_RATE = 'N';
            } else {
                LNCSRATE.FUNC_TYP = 'M';
            }
            LNCSRATE.DATA.RATE_KIND_N = LNCBKRAT.OLDRAT_INFO.OLD_RATT_N;
            LNCUEXT.RAT_INFO.IRAT_CD = "" + LNCBKRAT.OLDRAT_INFO.OLD_RATT_N;
            JIBS_tmp_int = LNCUEXT.RAT_INFO.IRAT_CD.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.IRAT_CD = "0" + LNCUEXT.RAT_INFO.IRAT_CD;
            LNCSRATE.DATA.INT_RAT_N = LNCBKRAT.OLDRAT_INFO.OLD_IRA_N;
            LNCUEXT.RAT_INFO.INT_RAT = LNCBKRAT.OLDRAT_INFO.OLD_IRA_N;
            S000_CALL_LNZSRATE();
            if (pgmRtn) return;
        }
    }
    public void B025_REMEMBER_PAIP_PROC() throws IOException,SQLException,Exception {
        T000_READ_LNTPAIP_FIRST();
        if (pgmRtn) return;
        LNCBKRAT.PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        LNCBKRAT.PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNCBKRAT.PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        LNCBKRAT.PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        LNCBKRAT.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        LNCBKRAT.PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        LNCBKRAT.CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        LNCBKRAT.CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
    }
    public void B070_PLAN_ADJ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLAJ);
        LNCPLAJ.COMM_DATA.ADJ_IND = 'X';
        LNCPLAJ.COMM_DATA.LN_AC = LNCUEXT.CTA_NO;
        LNCPLAJ.COMM_DATA.TR_VAL_DATE = LNCUEXT.VAL_DATE;
        LNCPLAJ.COMM_DATA.OLD_DUE_DT = LNCUEXT.OE_DATE;
        if (LNCUEXT.FLG == '1') {
            LNCPLAJ.COMM_DATA.P_VAL_DT = LNCUEXT.DLAY_DT;
        } else {
            LNCPLAJ.COMM_DATA.P_VAL_DT = LNCUEXT.VAL_DATE;
        }
        LNCPLAJ.COMM_DATA.PAY_P_AMT = WS_ALL_P_AMT;
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.P_VAL_DT);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        S000_CALL_LNZPLAJ();
        if (pgmRtn) return;
        if (LNCPLAJ.COMM_DATA.AUTO_PLAN_CHG_FLG == 'Y') {
            CEP.TRC(SCCGWA, "111111");
            WS_AUTO_PLAN = 'Y';
            CEP.TRC(SCCGWA, WS_AUTO_PLAN);
        }
    }
    public void B042_UPDATE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTRAN.P_AMT);
        CEP.TRC(SCCGWA, WS_NOP_AMT);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNRTRAN.KEY.TXN_TYP;
        LNCRCVDM.REC_DATA.KEY.TERM = LNRTRAN.KEY.TXN_TERM;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        if (LNRTRAN.KEY.TXN_TYP != 'I') {
            if (LNCRCVDM.REC_DATA.TERM_STS == '0') {
                WS_MOVE_AMT = LNRTRAN.P_AMT;
                WS_NOP_AMT = WS_NOP_AMT + LNRTRAN.P_AMT;
            } else {
                WS_MOVE_AMT = LNRTRAN.P_AMT;
                WS_OVP_AMT = WS_OVP_AMT + LNRTRAN.P_AMT;
            }
            WS_ALL_P_AMT = WS_NOP_AMT + WS_OVP_AMT;
            CEP.TRC(SCCGWA, WS_NOP_AMT);
            CEP.TRC(SCCGWA, WS_OVP_AMT);
            CEP.TRC(SCCGWA, WS_ALL_P_AMT);
            LNCRCVDM.REC_DATA.P_REC_AMT = LNCRCVDM.REC_DATA.P_PAY_AMT + LNRTRAN.P_AMT;
        }
        LNCRCVDM.REC_DATA.O_REC_AMT = LNRTRAN.O_REC_AMT;
        LNCRCVDM.REC_DATA.L_REC_AMT = LNRTRAN.L_REC_AMT;
        LNCRCVDM.REC_DATA.PI_STOP_DT = LNRTRAN.PI_STOP_DT;
        LNCRCVDM.REC_DATA.O_LST_CAL_AMT = LNRTRAN.O_LST_CAL_AMT;
        LNCRCVDM.REC_DATA.O_LST_PST_AMT = LNRTRAN.O_LST_PST_AMT;
        LNCRCVDM.REC_DATA.L_LST_CAL_AMT = LNRTRAN.L_LST_CAL_AMT;
        LNCRCVDM.REC_DATA.L_LST_PST_AMT = LNRTRAN.L_LST_PST_AMT;
        CEP.TRC(SCCGWA, "333333333333");
        CEP.TRC(SCCGWA, LNRTRAN.TRAN_STS);
        WS_TMP_AMT = 0;
        WS_TMP_AMT = LNCRCVDM.REC_DATA.I_PAY_AMT + LNCRCVDM.REC_DATA.O_PAY_AMT + LNCRCVDM.REC_DATA.L_PAY_AMT + LNCRCVDM.REC_DATA.F_PAY_AMT;
        if (WS_TMP_AMT > 0) {
            LNCRCVDM.REC_DATA.REPY_STS = '1';
        } else {
            LNCRCVDM.REC_DATA.REPY_STS = '0';
        }
        LNCRCVDM.FUNC = '2';
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NOP_AMT);
        if (WS_MOVE_AMT != 0 
            && LNRTRAN.KEY.TXN_TYP != 'I') {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '4';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNRTRAN.KEY.TXN_TYP;
            LNCPLPIM.REC_DATA.KEY.TERM = LNRTRAN.KEY.TXN_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
            CEP.TRC(SCCGWA, WS_MOVE_AMT);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT - WS_MOVE_AMT;
                if (LNCPLPIM.REC_DATA.PRIN_AMT >= WS_MOVE_AMT) {
                    LNCPLPIM.REC_DATA.PRIN_AMT = LNCPLPIM.REC_DATA.PRIN_AMT - WS_MOVE_AMT;
                }
            } else {
                LNCPLPIM.REC_DATA.PRIN_AMT = LNCPLPIM.REC_DATA.PRIN_AMT + WS_MOVE_AMT;
                LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT + WS_MOVE_AMT;
            }
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_REPY_AMT);
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.PRIN_AMT);
        }
        B060_REWRITE_LNTTRAN();
        if (pgmRtn) return;
    }
    public void B092_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'I';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRRCVD.KEY.AMT_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRRCVD.KEY.TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.DUE_DT = WS_RCVD_DUE_DT;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.P_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
        LNCTRANM.REC_DATA.I_REC_AMT = LNRRCVD.I_REC_AMT;
        LNCTRANM.REC_DATA.O_REC_AMT = LNRRCVD.O_REC_AMT;
        LNCTRANM.REC_DATA.L_REC_AMT = LNRRCVD.L_REC_AMT;
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
        LNCTRANM.REC_DATA.PI_STOP_DT = LNRRCVD.PI_STOP_DT;
        LNCTRANM.REC_DATA.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT;
        LNCTRANM.REC_DATA.O_LST_PST_AMT = LNRRCVD.O_LST_PST_AMT;
        LNCTRANM.REC_DATA.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT;
        LNCTRANM.REC_DATA.L_LST_PST_AMT = LNRRCVD.L_LST_PST_AMT;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TXN_CCY = LNCUEXT.CCY;
        if (LNCUEXT.FLG == '1') {
            LNCTRANM.REC_DATA.TR_MMO = "12010006";
        } else {
            if (LNCUEXT.EXT_FLG == '1') {
                LNCTRANM.REC_DATA.TR_MMO = "12010004";
            } else {
                LNCTRANM.REC_DATA.TR_MMO = "12010005";
            }
        }
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B091_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCRCVDM.REC_DATA.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'I';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNCRCVDM.REC_DATA.KEY.AMT_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNCRCVDM.REC_DATA.KEY.TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.DUE_DT = WS_RCVD_DUE_DT;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.P_AMT = LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT;
        LNCTRANM.REC_DATA.O_REC_AMT = LNCRCVDM.REC_DATA.O_REC_AMT;
        LNCTRANM.REC_DATA.L_REC_AMT = LNCRCVDM.REC_DATA.L_REC_AMT;
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
        LNCTRANM.REC_DATA.PI_STOP_DT = LNCRCVDM.REC_DATA.PI_STOP_DT;
        LNCTRANM.REC_DATA.O_LST_CAL_AMT = LNCRCVDM.REC_DATA.O_LST_CAL_AMT;
        LNCTRANM.REC_DATA.O_LST_PST_AMT = LNCRCVDM.REC_DATA.O_LST_PST_AMT;
        LNCTRANM.REC_DATA.L_LST_CAL_AMT = LNCRCVDM.REC_DATA.L_LST_CAL_AMT;
        LNCTRANM.REC_DATA.L_LST_PST_AMT = LNCRCVDM.REC_DATA.L_LST_PST_AMT;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TXN_CCY = LNCUEXT.CCY;
        if (LNCUEXT.FLG == '1') {
            LNCTRANM.REC_DATA.TR_MMO = "X8F";
        } else {
            LNCTRANM.REC_DATA.TR_MMO = "043";
        }
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B091_WRITE_TOT_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            LNCTRANM.REC_DATA.KEY.REC_FLAG = 'O';
        }
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
        if (WS_ALL_P_AMT != 0) {
            LNCTRANM.REC_DATA.P_AMT = WS_ALL_P_AMT;
        } else {
            LNCTRANM.REC_DATA.P_AMT = LNCUEXT.B_AMT;
        }
        LNCTRANM.REC_DATA.OS_BAL = LNCUEXT.B_AMT;
        LNCTRANM.REC_DATA.TXN_CCY = LNCUEXT.CCY;
        if (LNCUEXT.FLG == '1') {
            LNCTRANM.REC_DATA.TR_MMO = "12010006";
        } else {
            if (LNCUEXT.EXT_FLG == '1') {
                LNCTRANM.REC_DATA.TR_MMO = "12010004";
            } else {
                LNCTRANM.REC_DATA.TR_MMO = "12010005";
            }
        }
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.DUE_DT = WS_OE_DATE;
        LNCTRANM.REC_DATA.ACTL_DATE = LNCUEXT.VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DATE;
        CEP.TRC(SCCGWA, WS_LAST_F_VAL_DATE);
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B060_REWRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNRTRAN.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = LNRTRAN.KEY.REC_FLAG;
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNRTRAN.KEY.TR_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRTRAN.KEY.TXN_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRTRAN.KEY.TXN_TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = LNRTRAN.KEY.TRAN_FLG;
        LNCTRANM.FUNC = '4';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '2';
        LNCTRANM.REC_DATA.TRAN_STS = 'R';
        LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B028_WRITE_PFHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCUEXT.CTA_NO;
        BPCPFHIS.DATA.AC = LNRAGRE.PAPER_NO;
        BPCPFHIS.DATA.ACO_AC = LNCUEXT.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCUEXT.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = LNCUEXT.CCY;
        if (LNCUEXT.FLG == '1') {
            BPCPFHIS.DATA.TX_MMO = "X8F";
        } else {
            BPCPFHIS.DATA.TX_MMO = "043";
        }
        CEP.TRC(SCCGWA, WS_ALL_P_AMT);
        CEP.TRC(SCCGWA, LNCUEXT.B_AMT);
        BPCPFHIS.DATA.TX_AMT = LNCUEXT.B_AMT;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        BPCPFHIS.DATA.PROD_CD = LNCUEXT.PROD_CD;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARKS;
        BPCPFHIS.DATA.FMT_CODE = "LNE07";
        BPCPFHIS.DATA.FMT_LEN = 475;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCUEXT);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CANCEL----------");
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'I';
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.KEY.TRAN_FLG = 'D';
        T000_STARTBR_LNTTRAN();
        if (pgmRtn) return;
        T000_READNEXT_LNTTRAN();
        if (pgmRtn) return;
        WS_ERR_FLG = 'Y';
        WS_OLD_DATE = LNRTRAN.DUE_DT;
        WS_VAL_DATE = LNRTRAN.ACTL_DATE;
        if (LNRTRAN.DUE_DT < LNRTRAN.ACTL_DATE) {
            WS_OVD_FLG = 'Y';
        }
        B300_CHANGE_STS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNRTRAN.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = LNRTRAN.KEY.REC_FLAG;
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNRTRAN.KEY.TR_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRTRAN.KEY.TXN_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRTRAN.KEY.TXN_TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = LNRTRAN.KEY.TRAN_FLG;
        LNCTRANM.FUNC = '4';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '2';
        LNCTRANM.REC_DATA.TRAN_STS = 'R';
        LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        T000_ENDBR_LNTTRAN();
        if (pgmRtn) return;
        LNCUEXT.OE_DATE = LNCUEXT.EE_DATE;
        LNCUEXT.EE_DATE = LNRTRAN.DUE_DT;
        WS_CTL_STSW = LNRTRAN.LOAN_STSW;
        WS_NEXT_LC_CAL_DAT2 = LNRTRAN.LAST_LC_CAL_DAT;
        CEP.TRC(SCCGWA, LNCUEXT.OE_DATE);
        CEP.TRC(SCCGWA, LNCUEXT.EE_DATE);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_N);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_O);
        CEP.TRC(SCCGWA, LNCBKRAT.OLD_FLG_L);
    }
    public void B300_CHANGE_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNCUEXT.OE_DATE = LNRTRAN.DUE_DT;
        LNREXTN.KEY.VAL_DT = LNRTRAN.ACTL_DATE;
        LNREXTN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNREXTN.KEY.STATUS = '4';
        if (LNCUEXT.FLG == '1') {
            LNREXTN.KEY.EXT_FLG = '3';
        } else {
            CEP.TRC(SCCGWA, LNCUEXT.OE_DATE);
            CEP.TRC(SCCGWA, LNCUEXT.EE_DATE);
            if (LNCUEXT.EE_DATE > LNCUEXT.OE_DATE) {
                LNREXTN.KEY.EXT_FLG = '1';
            } else {
                LNREXTN.KEY.EXT_FLG = '2';
            }
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        T00_REUPDATE_LNTEXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1113334");
        if (WS_REXTN_FLG == 'N') {
            T00_DELETE_LNTEXTN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "22223334");
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNCUEXT.OE_DATE = LNRTRAN.DUE_DT;
        LNREXTN.KEY.VAL_DT = LNRTRAN.ACTL_DATE;
        LNREXTN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNREXTN.KEY.STATUS = '2';
        if (LNCUEXT.FLG == '1') {
            LNREXTN.KEY.EXT_FLG = '3';
        } else {
            CEP.TRC(SCCGWA, LNCUEXT.OE_DATE);
            CEP.TRC(SCCGWA, LNCUEXT.EE_DATE);
            if (LNCUEXT.EE_DATE > LNCUEXT.OE_DATE) {
                LNREXTN.KEY.EXT_FLG = '1';
            } else {
                LNREXTN.KEY.EXT_FLG = '2';
            }
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        T00_REUPDATE_LNTEXTN();
        if (pgmRtn) return;
        if (WS_REXTN_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWDH_NOTFND, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "0000000");
        IBS.init(SCCGWA, LNCBKRAT);
        IBS.CPY2CLS(SCCGWA, LNREXTN.RAT_DATA, LNCBKRAT);
        CEP.TRC(SCCGWA, "1111111");
        if (WS_PAY_MTH == '4') {
            WS_PHS_INST_AMT = LNCBKRAT.PHS_INST_AMT;
            WS_PHS_PRIN_AMT = LNCBKRAT.PHS_PRIN_AMT;
            WS_PHS_TOT_TERM = LNCBKRAT.PHS_TOT_TERM;
            WS_PHS_REM_PRIN_AMT = LNCBKRAT.PHS_REM_PRIN_AMT;
            WS_PHS_CAL_TERM = LNCBKRAT.PHS_CAL_TERM;
            WS_PHS_CMP_TERM = LNCBKRAT.PHS_CMP_TERM;
            WS_CUR_INST_AMT = LNCBKRAT.CUR_INST_AMT;
            WS_CUR_INST_IRAT = LNCBKRAT.CUR_INST_IRAT;
        }
        T00_DELETE_LNTEXTN();
        if (pgmRtn) return;
        LNREXTN.KEY.STATUS = '4';
        T00_WRITE_LNTEXTN();
        if (pgmRtn) return;
    }
    public void B032_REUPDATE_EXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUEXT.EXT_FLG);
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNREXTN.ALL_IN_RATE = LNCUEXT.RAT_INFO.ALL_RAT;
        if (LNCUEXT.FLG == '1') {
            LNREXTN.KEY.EXT_FLG = '3';
        } else {
            LNREXTN.KEY.EXT_FLG = LNCUEXT.EXT_FLG;
        }
        LNREXTN.KEY.VAL_DT = LNCUEXT.VAL_DATE;
        LNREXTN.KEY.STATUS = '2';
        LNCREXTN.FUNC = 'R';
        S000_CALL_LNZREXTN();
        if (pgmRtn) return;
    }
    public void B034_UPDATE_EXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNREXTN.RAT_DATA);
        LNCREXTN.FUNC = 'U';
        LNREXTN.RAT_DATA = IBS.CLS2CPY(SCCGWA, LNCBKRAT);
        S000_CALL_LNZREXTN();
        if (pgmRtn) return;
    }
    public void B302_UPDATE_PAIP_PROC() throws IOException,SQLException,Exception {
        T000_READ_LNTPAIP_FIRST();
        if (pgmRtn) return;
        T000_UPREAD_LNTPAIP();
        if (pgmRtn) return;
        LNRPAIP.PHS_INST_AMT = WS_PHS_INST_AMT;
        LNRPAIP.PHS_PRIN_AMT = WS_PHS_PRIN_AMT;
        LNRPAIP.PHS_TOT_TERM = WS_PHS_TOT_TERM;
        LNRPAIP.PHS_REM_PRIN_AMT = WS_PHS_REM_PRIN_AMT;
        LNRPAIP.PHS_CAL_TERM = WS_PHS_CAL_TERM;
        LNRPAIP.PHS_CMP_TERM = WS_PHS_CAL_TERM;
        LNRPAIP.CUR_INST_AMT = WS_CUR_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = WS_CUR_INST_IRAT;
        T000_REWRITE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B050_RCAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL LNZRCAL---------------------");
        IBS.init(SCCGWA, LNCRCAL);
        LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
        LNCRCAL.COMM_DATA.LN_AC = LNCUEXT.CTA_NO;
        LNCRCAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
        LNCRCAL.COMM_DATA.VAL_DATE = WS_VAL_DATE;
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.VAL_DATE);
        S000_CALL_LNZRCAL();
        if (pgmRtn) return;
    }
    public void B051_ICAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNCUEXT.CTA_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void B410_ADJ_LC_PROCESS() throws IOException,SQLException,Exception {
        WS_ADJ_LC_AMT = 0;
        WS_INTA_FOUND_FLG = 'N';
        T00_STARTBR_LNTINTA();
        if (pgmRtn) return;
        T00_READNEXT_LNTINTA();
        if (pgmRtn) return;
        while (WS_INTA_FOUND_FLG != 'N') {
            WS_ADJ_LC_AMT += LNRINTA.ADJ_AMT;
            T00_READNEXT_LNTINTA();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTINTA();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_LNTINTA() throws IOException,SQLException,Exception {
        LNRINTA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRINTA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRINTA.KEY.REPY_TERM = LNRRCVD.KEY.TERM;
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_BR.rp = new DBParm();
        LNTINTA_BR.rp.TableName = "LNTINTA";
        LNTINTA_BR.rp.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ > :LNRINTA.KEY.ADJ_SEQ";
        LNTINTA_BR.rp.order = "ADJ_SEQ";
        IBS.STARTBR(SCCGWA, LNRINTA, this, LNTINTA_BR);
    }
    public void T00_READNEXT_LNTINTA() throws IOException,SQLException,Exception {
        WS_INTA_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRINTA, this, LNTINTA_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        CEP.TRC(SCCGWA, LNCCNBU.RC);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCUEXT.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZREXTN() throws IOException,SQLException,Exception {
        LNCREXTN.REC_PTR = LNREXTN;
        LNCREXTN.REC_LEN = 595;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTEXTN", LNCREXTN);
        if (LNCREXTN.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCREXTN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C000_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGVCH);
        LNCIGVCH.DATA.CNTR_TYPE = "CLCM";
        LNCIGVCH.DATA.PROD_CODE_OLD = LNCUEXT.PROD_CD;
        LNCIGVCH.DATA.EVENT_CODE = "ST";
        LNCIGVCH.DATA.BR_OLD = LNCUEXT.BR;
        LNCIGVCH.DATA.CCY_INFO[1-1].CCY = LNCUEXT.CCY;
        LNCIGVCH.DATA.VALUE_DATE = LNCUEXT.EE_DATE;
        LNCIGVCH.DATA.BAL_NORMAL = LNCUEXT.B_AMT;
        LNCIGVCH.DATA.AMT_INFO[1-1].AMT = LNCUEXT.B_AMT;
        LNCIGVCH.DATA.CI_NO = LNCUEXT.CI_NO;
        LNCIGVCH.DATA.CTA_NO = LNCUEXT.CTA_NO;
        LNCIGVCH.DATA.SUB_CTA_NO = 0;
        LNCIGVCH.DATA.STATUS = LNRCMMT.STATUS;
        S000_CALL_LNZIGVCH();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        CEP.TRC(SCCGWA, LNCRCVDM.RC.RC_RTNCODE);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCUEXT.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            CEP.TRC(SCCGWA, LNCUEXT.RC.RC_RTNCODE);
            CEP.TRC(SCCGWA, LNCUEXT.RC.RC_RTNCODE);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCUEXT.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SEV-RATE-MAINT", LNCSRATE);
        if (LNCSRATE.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_APP = LNCSRATE.RC.RC_APP;
            LNCUEXT.RC.RC_RTNCODE = LNCSRATE.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_STARTBR_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'B';
        LNCRICTL.OPT = 'B';
        LNRICTL.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_READUPDATE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'R';
        LNRCONT.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "123");
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        WS_OE_DATE = LNRCONT.MAT_DATE;
        WS_LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        CEP.TRC(SCCGWA, WS_OE_DATE);
        CEP.TRC(SCCGWA, WS_LAST_F_VAL_DATE);
    }
    public void S000_INQUIRE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_INQUIRE_LOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRLOAN);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = WS_CTA_NO;
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void S000_INQUIRE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = WS_CTA_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void S000_READNEXT_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'B';
        LNCRICTL.OPT = 'R';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_REWRITE_CONT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "YE000");
        CEP.TRC(SCCGWA, LNCUEXT.EE_DATE);
        LNCRCONT.FUNC = 'U';
        LNRCONT.MAT_DATE = LNCUEXT.EE_DATE;
        LNRCONT.LAST_F_VAL_DATE = LNCUEXT.VAL_DATE;
        LNRCONT.LAST_TX_DT = LNCUEXT.VAL_DATE;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNRCONT.LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ - 1;
            CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.LAST_F_VAL_DATE);
            LNRCONT.LAST_F_VAL_DATE = LNCTRANM.REC_DATA.LAST_F_VAL_DATE;
        } else {
            LNRCONT.LAST_TX_SEQ += 1;
        }
        WS_LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        CEP.TRC(SCCGWA, LNRCONT.LAST_F_VAL_DATE);
    }
    public void S000_ENDBR_ICTL() throws IOException,SQLException,Exception {
        LNCRICTL.FUNC = 'B';
        LNCRICTL.OPT = 'E';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            if (LNCRICTL.RETURN_INFO == 'E' 
                || LNCRICTL.RETURN_INFO == 'N') {
            } else {
                LNCUEXT.RC.RC_APP = LNCRICTL.RC.RC_MMO;
                LNCUEXT.RC.RC_RTNCODE = LNCRICTL.RC.RC_CODE;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        WS_CNT_CONT = ' ';
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            if (LNCRCONT.RETURN_INFO == 'E' 
                || LNCRCONT.RETURN_INFO == 'N') {
                WS_CNT_CONT = 'Y';
            } else {
                LNCUEXT.RC.RC_APP = LNCRCONT.RC.RC_MMO;
                LNCUEXT.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RETURN_INFO != 'F') {
            LNCUEXT.RC.RC_APP = LNCRLOAN.RC.RC_MMO;
            LNCUEXT.RC.RC_RTNCODE = LNCRLOAN.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGVCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCH", LNCIGVCH);
        if (LNCIGVCH.RC.RC_CODE != 0) {
            LNCUEXT.RC.RC_APP = LNCIGVCH.RC.RC_MMO;
            LNCUEXT.RC.RC_RTNCODE = LNCIGVCH.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CRY-RISK-LMT", LNCICRL);
        if (LNCICRL.RC.RC_CODE != 0) {
            LNCUEXT.RC.RC_APP = LNCICRL.RC.RC_MMO;
            LNCUEXT.RC.RC_RTNCODE = LNCICRL.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-RATN-MAINT", LNCSRTN);
        CEP.TRC(SCCGWA, LNCSRTN.RC.RC_RTNCODE);
        if (LNCSRTN.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCSRTN.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-RATL-MAINT", LNCSRTL);
        if (LNCSRTL.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCSRTL.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCTRANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PLPI-AUTADJ", LNCPLAJ);
        CEP.TRC(SCCGWA, LNCPLAJ.RC.RC_RTNCODE);
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_APP = LNCPLAJ.RC.RC_APP;
            LNCUEXT.RC.RC_RTNCODE = LNCPLAJ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            LNCUEXT.RC.RC_APP = BPCPFHIS.RC.RC_MMO;
            LNCUEXT.RC.RC_RTNCODE = BPCPFHIS.RC.RC_CODE;
        }
    }
    public void T00_READ_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_RATH_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATH_FLG = 'Y';
        } else {
            WS_RATH_FLG = 'N';
        }
    }
    public void T000_READ_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT = :LNRRATN.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        }
    }
    public void T000_READ_LNTRATL() throws IOException,SQLException,Exception {
        WS_FND_FLG2 = ' ';
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT = :LNRRATL.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG2 = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG2 = 'Y';
        }
        CEP.TRC(SCCGWA, LNRRATL.RATE_TYPE);
        CEP.TRC(SCCGWA, LNRRATL.RATE_CLAS);
        CEP.TRC(SCCGWA, LNRRATL.DIF_IRAT_PER);
        CEP.TRC(SCCGWA, LNRRATL.DIF_IRAT_PNT);
    }
    public void T00_WRITE_LNTEXTN() throws IOException,SQLException,Exception {
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.WRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_DUPKEY, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNRRCVD.REPY_STS = '2';
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS";
        LNTRCVD_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RCVD_VAL_DT = LNRRCVD.VAL_DT;
            WS_RCVD_DUE_DT = LNRRCVD.DUE_DT;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_RCVD_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
                CEP.TRC(SCCGWA, LNCUEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READUP_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T000_REWRITE_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            CEP.TRC(SCCGWA, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "111122334");
    }
    public void T000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_STARTBR_LNTTRAN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.REC_FLAG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = :LNRTRAN.KEY.REC_FLAG "
            + "AND TRAN_FLG = :LNRTRAN.KEY.TRAN_FLG "
            + "AND TR_DATE = :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP";
        LNTTRAN_BR.rp.order = "TXN_TERM";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTTRAN_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.REC_FLAG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = :LNRTRAN.KEY.REC_FLAG "
            + "AND TRAN_FLG = :LNRTRAN.KEY.TRAN_FLG "
            + "AND TR_DATE = :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP < > :LNRTRAN.KEY.TXN_TYP";
        LNTTRAN_BR.rp.order = "TXN_TERM";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_TRAN_FOUND_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0' 
            && WS_ERR_FLG == 'Y') {
            WS_TRAN_FOUND_FLG = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_NOTFND, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTEXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        LNTEXTN_BR.rp = new DBParm();
        LNTEXTN_BR.rp.TableName = "LNTEXTN";
        LNTEXTN_BR.rp.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND VAL_DT = :LNREXTN.KEY.VAL_DT "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        IBS.STARTBR(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNREXTN, this, LNTEXTN_BR);
    }
    public void T000_ENDBR_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTEXTN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_REUPDATE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.upd = true;
        IBS.READ(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REXTN_FLG = 'N';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_REXTN_FLG = 'Y';
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
                CEP.TRC(SCCGWA, LNCUEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T00_DELETE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.DELETE(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            CEP.TRC(SCCGWA, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTPAIP_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPAIP_RD.fst = true;
        LNTPAIP_RD.order = "PHS_NO DESC";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            CEP.TRC(SCCGWA, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPREAD_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.upd = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            CEP.TRC(SCCGWA, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FIRST_RATN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BRAT_EFF_DT);
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
        LNRRATN.KEY.ACTV_DT = WS_BRAT_EFF_DT;
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT = :LNRRATN.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.REWRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCUEXT.RC);
            CEP.TRC(SCCGWA, LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCAPRDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLCCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LC-CMP", LNCLCCM);
        if (LNCLCCM.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCLCCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCUEXT.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL-BASE", LNCICAL);
        CEP.TRC(SCCGWA, LNCICAL.RC.RC_RTNCODE);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READUPDATE_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void T00_REWRITE_LNTBALZ() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_W() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
        if (LNCUEXT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCUEXT=");
            CEP.TRC(SCCGWA, LNCUEXT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
