package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCACAAC;
import com.hisun.BP.BPCACLBA;
import com.hisun.BP.BPCACLCM;
import com.hisun.BP.BPCACLDD;
import com.hisun.BP.BPCACLGU;
import com.hisun.BP.BPCAMMDP;
import com.hisun.BP.BPCCNGL;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPQAMO;
import com.hisun.BP.BPCPQENT;
import com.hisun.BP.BPCPQGLM;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCRMBPM;
import com.hisun.BP.BPCUCNGM;
import com.hisun.BP.BPCUGMC;
import com.hisun.BP.BPRAPBL;
import com.hisun.BP.BPRMSG;
import com.hisun.BP.BPRPARM;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICMACR;
import com.hisun.CI.CICQACAC;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCSCINM;
import com.hisun.IB.IBCQINF;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;
import com.hisun.TD.TDCACE;

public class AIZUCPRD {
    boolean pgmRtn = false;
    short K_MAX_DD = 40;
    String WS_ERR_MSG = " ";
    AIZUCPRD_WS_ERR_RC WS_ERR_RC = new AIZUCPRD_WS_ERR_RC();
    int WS_QBKPM_CNT = 0;
    String WS_CCY = " ";
    char WS_CCY_TYPE = ' ';
    String WS_CCY1 = " ";
    char WS_CCY_TYPE1 = ' ';
    int WS_Z = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_COUNT = 0;
    int WS_CNT = 0;
    int WS_CNT0 = 0;
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    int WS_CNT3 = 0;
    String WS_ITM_NO = " ";
    int WS_OLD_BR = 0;
    String WS_OLD_PROD_TYPE = " ";
    String WS_NEW_CNTY_TYPE = " ";
    String WS_OLD_CNTY_TYPE = " ";
    String WS_OLD_MODEL = " ";
    String WS_NEW_MODEL = " ";
    String WS_FIN_TYP_OLD = " ";
    char WS_CI_TYP_OLD = ' ';
    char WS_AC_TYP_OLD = ' ';
    char WS_PROP_TYP_OLD = ' ';
    String WS_TERM_CD_OLD = " ";
    String WS_LOAN_TYPE_OLD = " ";
    char WS_WE_FLG_OLD = ' ';
    char WS_DUTY_FREE_OLD = ' ';
    AIZUCPRD_WS_OLD_GL_MST[] WS_OLD_GL_MST = new AIZUCPRD_WS_OLD_GL_MST[10];
    AIZUCPRD_WS_NEW_GL_MST[] WS_NEW_GL_MST = new AIZUCPRD_WS_NEW_GL_MST[10];
    AIZUCPRD_WS_GLM_INFO[] WS_GLM_INFO = new AIZUCPRD_WS_GLM_INFO[10];
    AIZUCPRD_WS_AMT_DATA[] WS_AMT_DATA = new AIZUCPRD_WS_AMT_DATA[76];
    AIZUCPRD_WS_TMP_CCY_DATA[] WS_TMP_CCY_DATA = new AIZUCPRD_WS_TMP_CCY_DATA[40];
    char WS_CHANGE_FLG = ' ';
    AIZUCPRD_WS_NEW_CUS_DATA WS_NEW_CUS_DATA = new AIZUCPRD_WS_NEW_CUS_DATA();
    AIZUCPRD_WS_OLD_CUS_DATA WS_OLD_CUS_DATA = new AIZUCPRD_WS_OLD_CUS_DATA();
    AIZUCPRD_WS_OUT_DATA WS_OUT_DATA = new AIZUCPRD_WS_OUT_DATA();
    List<AIZUCPRD_WS_BOOK_FLG_ALL> WS_BOOK_FLG_ALL = new ArrayList<AIZUCPRD_WS_BOOK_FLG_ALL>();
    char WS_CPRD_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPRAPBL BPRAPBL = new BPRAPBL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCPQENT BPCPQENT = new BPCPQENT();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCCNGL BPCICNGL = new BPCCNGL();
    AICPQITM AICPQITM = new AICPQITM();
    LNCSTRPD LNCSTRPD = new LNCSTRPD();
    BACSTRPD BACTRPD = new BACSTRPD();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCUGMC BPCUGMC = new BPCUGMC();
    BPCCNGL BPCNCNGL = new BPCCNGL();
    BPCCNGL BPCOCNGL = new BPCCNGL();
    CICSCGL CICSCGL = new CICSCGL();
    CICCUST CICCUST = new CICCUST();
    CICMACR CICMACR = new CICMACR();
    DDCSCHPD DDCSCHPD = new DDCSCHPD();
    DDCIQAPL DDCIQAPL = new DDCIQAPL();
    DDCSCINM DDCSCINM = new DDCSCINM();
    CICACCU CICACCU = new CICACCU();
    TDCIQAPT TDCIQAPT = new TDCIQAPT();
    TDCSUPPD TDCSUPPD = new TDCSUPPD();
    AIRCPRD AIRCPRD = new AIRCPRD();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    SCCIMSG SCCIMSG = new SCCIMSG();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCACLBA BPCACLBA = new BPCACLBA();
    BPCACLCM BPCACLCM = new BPCACLCM();
    BPCACLGU BPCACLGU = new BPCACLGU();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICQACAC CICQACAC = new CICQACAC();
    TDCACE TDCACE = new TDCACE();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFS IBCQINFS = new IBCQINFS();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    TDCACIU TDCACIU = new TDCACIU();
    IBCFATRU IBCFATRU = new IBCFATRU();
    BPRMSG BPRMSG = new BPRMSG();
    SCCPRMR SCCPRMR = new SCCPRMR();
    AIRBB07 AIRBB07 = new AIRBB07();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICUCPRD AICUCPRD;
    public AIZUCPRD() {
        for (int i=0;i<10;i++) WS_OLD_GL_MST[i] = new AIZUCPRD_WS_OLD_GL_MST();
        for (int i=0;i<10;i++) WS_NEW_GL_MST[i] = new AIZUCPRD_WS_NEW_GL_MST();
        for (int i=0;i<10;i++) WS_GLM_INFO[i] = new AIZUCPRD_WS_GLM_INFO();
        for (int i=0;i<76;i++) WS_AMT_DATA[i] = new AIZUCPRD_WS_AMT_DATA();
        for (int i=0;i<40;i++) WS_TMP_CCY_DATA[i] = new AIZUCPRD_WS_TMP_CCY_DATA();
    }
    public void MP(SCCGWA SCCGWA, AICUCPRD AICUCPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUCPRD = AICUCPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZUCPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_FOUND_GL_BOOK_FLG();
        if (pgmRtn) return;
        B020_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICUCPRD.AC.trim().length() > 0) {
            B040_UPDATE_NEW_PROD_TYP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_ERR_RC.WS_ERR_RC_CODE);
            if (WS_ERR_RC.WS_ERR_RC_CODE == 0) {
                B030_CHG_PROD_PROC();
                if (pgmRtn) return;
            }
        } else {
            B040_UPDATE_NEW_PROD_TYP();
            if (pgmRtn) return;
        }
        B050_UPDATE_CPRD();
        if (pgmRtn) return;
        if (WS_CHANGE_FLG == 'Y') {
            B060_WRITE_OUT_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_FOUND_GL_BOOK_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQBKPM.CNT);
        WS_QBKPM_CNT = BPCQBKPM.CNT;
        for (WS_CNT = 1; WS_CNT <= BPCQBKPM.CNT; WS_CNT += 1) {
            WS_BOOK_FLG_ALL = WS_BOOK_FLG_ALL.get(WS_CNT-1);
            WS_BOOK_FLG_ALL.WS_BOOK_FLG = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
            WS_BOOK_FLG_ALL.set(WS_CNT-1, WS_BOOK_FLG_ALL);
            WS_BOOK_FLG_ALL = WS_BOOK_FLG_ALL.get(WS_CNT-1);
            WS_BOOK_FLG_ALL.WS_COA_FLG = BPCQBKPM.DATA[WS_CNT-1].COA_FLG;
            WS_BOOK_FLG_ALL.set(WS_CNT-1, WS_BOOK_FLG_ALL);
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICUCPRD.AC.trim().length() > 0 
            && AICUCPRD.DEAL_FLG == '1') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CPRD_NO_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICUCPRD.AC.trim().length() > 0 
            && AICUCPRD.DEAL_FLG == '2') {
            B021_CHECK_AC_CI_RELAC();
            if (pgmRtn) return;
            B022_CHECK_CNTY_TYPE();
            if (pgmRtn) return;
            B023_GET_BAL_OLD_PROTYP_BR();
            if (pgmRtn) return;
            if (AICUCPRD.DEAL_FLG == '2' 
                || AICUCPRD.DEAL_FLG == '3') {
                B024_CHECK_PRD_TYPE();
                if (pgmRtn) return;
            }
        }
        B025_CHECK_PRO_GLM_PARM();
        if (pgmRtn) return;
    }
    public void B021_CHECK_AC_CI_RELAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUCPRD.AC);
        CEP.TRC(SCCGWA, AICUCPRD.CI_NO);
        CEP.TRC(SCCGWA, AICUCPRD.NEW_PROD_CD);
        if (!AICUCPRD.AP_MMO.equalsIgnoreCase("PL") 
            && !AICUCPRD.AP_MMO.equalsIgnoreCase("LN")) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = AICUCPRD.AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_OUT_DATA.WS_OUT_CI_NO = CICACCU.DATA.CI_NO;
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CI_NO);
            if (!AICUCPRD.TLR_ID.equalsIgnoreCase("BATCH")) {
                if (!WS_OUT_DATA.WS_OUT_CI_NO.equalsIgnoreCase(AICUCPRD.CI_NO)) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CI_NO_NOT_RELAC_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = AICUCPRD.AC;
            CICQACAC.DATA.AGR_SEQ = AICUCPRD.ACAC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        } else {
            CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO = AICUCPRD.AC;
            CICACCU.DATA.PROD_CD = AICUCPRD.OLD_PROD_CD;
        }
    }
    public void B022_CHECK_CNTY_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.FUNC = 'Q';
        BPCUCNGM.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
        BPCPQPRD.PRDT_CODE = CICACCU.DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
        WS_OLD_CNTY_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        WS_OLD_MODEL = BPCUCNGM.MOD_NO;
        for (WS_CNT1 = 1; WS_CNT1 <= 10; WS_CNT1 += 1) {
            WS_OLD_GL_MST[WS_CNT1-1].WS_OLD_MST_NO = BPCUCNGM.DATA[WS_CNT1-1].GLMST;
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_OLD_GL_MST[WS_CNT1-1].WS_OLD_MST_NO);
        }
        IBS.init(SCCGWA, BPCPQPRD);
        if (AICUCPRD.NEW_PROD_CD.trim().length() > 0) {
            BPCPQPRD.PRDT_CODE = AICUCPRD.NEW_PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_NEW_CNTY_TYPE = BPCPQPRD.PRDT_MODEL;
        } else {
            WS_NEW_CNTY_TYPE = WS_OLD_CNTY_TYPE;
        }
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        CEP.TRC(SCCGWA, WS_NEW_CNTY_TYPE);
        if ((!WS_NEW_CNTY_TYPE.equalsIgnoreCase(WS_OLD_CNTY_TYPE)) 
            && (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC") 
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP"))) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_OLD_NEW_CNTYP_NOSAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_GET_BAL_OLD_PROTYP_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        WS_OLD_PROD_TYPE = AICUCPRD.OLD_PROD_CD;
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            CEP.TRC(SCCGWA, "CAAC");
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = AICUCPRD.AC;
            DDCSCINM.INPUT_DATA.CCY = WS_CCY;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AC_TYP);
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AMT_TYPE);
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.OWNER_BRDP);
            WS_AC_TYP_OLD = DDCSCINM.OUTPUT_DATA.AC_TYP;
            WS_PROP_TYP_OLD = DDCSCINM.OUTPUT_DATA.AMT_TYPE;
            WS_OLD_BR = DDCSCINM.OUTPUT_DATA.OWNER_BRDP;
        }
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
            CEP.TRC(SCCGWA, "MMDP");
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = AICUCPRD.AC;
            TDCACE.PAGE_INF.I_AC_SEQ = AICUCPRD.ACAC_SEQ;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STS);
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_TYP);
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].MON_TYP);
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].CHE_BR);
            WS_AC_TYP_OLD = TDCACE.DATA[1-1].ACO_TYP;
            WS_PROP_TYP_OLD = TDCACE.DATA[1-1].MON_TYP;
            WS_OLD_BR = TDCACE.DATA[1-1].CHE_BR;
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].PROD_CD);
            WS_OLD_PROD_TYPE = TDCACE.DATA[1-1].PROD_CD;
            CEP.TRC(SCCGWA, WS_OLD_PROD_TYPE);
        }
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBDD")) {
            CEP.TRC(SCCGWA, "IBDD");
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = AICUCPRD.AC;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.FUND_ATTR);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.POST_ACT_CTR);
            WS_AC_TYP_OLD = ' ';
            WS_PROP_TYP_OLD = IBCQINF.OUTPUT_DATA.FUND_ATTR;
            WS_OLD_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        }
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBTD")) {
            CEP.TRC(SCCGWA, "IBTD");
            IBS.init(SCCGWA, IBCQINFS);
            IBCQINFS.AC_NO = AICUCPRD.AC;
            S000_CALL_IBZQINFS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINFS.FUND_ATTR);
            CEP.TRC(SCCGWA, IBCQINFS.POST_CTR);
            WS_AC_TYP_OLD = ' ';
            WS_PROP_TYP_OLD = IBCQINFS.FUND_ATTR;
            WS_OLD_BR = IBCQINFS.POST_CTR;
        }
        CEP.TRC(SCCGWA, WS_AC_TYP_OLD);
        CEP.TRC(SCCGWA, WS_PROP_TYP_OLD);
        CEP.TRC(SCCGWA, WS_OLD_BR);
        if (AICUCPRD.DEAL_FLG == '2') {
            CEP.TRC(SCCGWA, AICUCPRD.AC_DATE);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            CEP.TRC(SCCGWA, WS_CCY);
            IBS.init(SCCGWA, AIRBB07);
            AIRBB07.KEY.AC_DATE = AICUCPRD.AC_DATE;
            AIRBB07.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_AITBB07();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_AMT_DATA[1-1].WS_AMT = AIRBB07.BAL1;
                WS_AMT_DATA[2-1].WS_AMT = AIRBB07.BAL2;
                WS_AMT_DATA[3-1].WS_AMT = AIRBB07.BAL3;
                WS_AMT_DATA[4-1].WS_AMT = AIRBB07.BAL4;
                WS_AMT_DATA[5-1].WS_AMT = AIRBB07.BAL5;
                WS_AMT_DATA[6-1].WS_AMT = AIRBB07.BAL6;
                WS_AMT_DATA[7-1].WS_AMT = AIRBB07.BAL7;
                WS_AMT_DATA[8-1].WS_AMT = AIRBB07.BAL8;
                WS_AMT_DATA[9-1].WS_AMT = AIRBB07.BAL9;
                WS_AMT_DATA[10-1].WS_AMT = AIRBB07.BAL10;
                WS_AMT_DATA[11-1].WS_AMT = AIRBB07.BAL11;
                WS_AMT_DATA[12-1].WS_AMT = AIRBB07.BAL12;
                WS_AMT_DATA[13-1].WS_AMT = AIRBB07.BAL13;
                WS_AMT_DATA[14-1].WS_AMT = AIRBB07.BAL14;
                WS_AMT_DATA[15-1].WS_AMT = AIRBB07.BAL15;
                WS_AMT_DATA[16-1].WS_AMT = AIRBB07.BAL16;
                WS_AMT_DATA[17-1].WS_AMT = AIRBB07.BAL17;
                WS_AMT_DATA[18-1].WS_AMT = AIRBB07.BAL18;
                WS_AMT_DATA[19-1].WS_AMT = AIRBB07.BAL19;
                WS_AMT_DATA[20-1].WS_AMT = AIRBB07.BAL20;
                WS_AMT_DATA[21-1].WS_AMT = AIRBB07.BAL21;
                WS_AMT_DATA[22-1].WS_AMT = AIRBB07.BAL22;
                WS_AMT_DATA[23-1].WS_AMT = AIRBB07.BAL23;
                WS_AMT_DATA[24-1].WS_AMT = AIRBB07.BAL24;
                WS_AMT_DATA[25-1].WS_AMT = AIRBB07.BAL25;
                WS_AMT_DATA[26-1].WS_AMT = AIRBB07.BAL26;
                WS_AMT_DATA[27-1].WS_AMT = AIRBB07.BAL27;
                WS_AMT_DATA[28-1].WS_AMT = AIRBB07.BAL28;
                WS_AMT_DATA[29-1].WS_AMT = AIRBB07.BAL29;
                WS_AMT_DATA[30-1].WS_AMT = AIRBB07.BAL30;
                WS_AMT_DATA[31-1].WS_AMT = AIRBB07.BAL31;
                WS_AMT_DATA[32-1].WS_AMT = AIRBB07.BAL32;
                WS_AMT_DATA[33-1].WS_AMT = AIRBB07.BAL33;
                WS_AMT_DATA[34-1].WS_AMT = AIRBB07.BAL34;
                WS_AMT_DATA[35-1].WS_AMT = AIRBB07.BAL35;
                WS_AMT_DATA[36-1].WS_AMT = AIRBB07.BAL36;
                WS_AMT_DATA[37-1].WS_AMT = AIRBB07.BAL37;
                WS_AMT_DATA[38-1].WS_AMT = AIRBB07.BAL38;
                WS_AMT_DATA[39-1].WS_AMT = AIRBB07.BAL39;
                WS_AMT_DATA[40-1].WS_AMT = AIRBB07.BAL40;
                WS_AMT_DATA[41-1].WS_AMT = AIRBB07.BAL41;
                WS_AMT_DATA[42-1].WS_AMT = AIRBB07.BAL42;
                WS_AMT_DATA[43-1].WS_AMT = AIRBB07.BAL43;
                WS_AMT_DATA[44-1].WS_AMT = AIRBB07.BAL44;
                WS_AMT_DATA[45-1].WS_AMT = AIRBB07.BAL45;
                WS_AMT_DATA[46-1].WS_AMT = AIRBB07.BAL46;
                WS_AMT_DATA[47-1].WS_AMT = AIRBB07.BAL47;
                WS_AMT_DATA[48-1].WS_AMT = AIRBB07.BAL48;
                WS_AMT_DATA[49-1].WS_AMT = AIRBB07.BAL49;
                WS_AMT_DATA[50-1].WS_AMT = AIRBB07.BAL50;
                WS_AMT_DATA[51-1].WS_AMT = AIRBB07.BAL51;
                WS_AMT_DATA[52-1].WS_AMT = AIRBB07.BAL52;
                WS_AMT_DATA[53-1].WS_AMT = AIRBB07.BAL53;
                WS_AMT_DATA[54-1].WS_AMT = AIRBB07.BAL54;
                WS_AMT_DATA[55-1].WS_AMT = AIRBB07.BAL55;
                WS_AMT_DATA[56-1].WS_AMT = AIRBB07.BAL56;
                WS_AMT_DATA[57-1].WS_AMT = AIRBB07.BAL57;
                WS_AMT_DATA[58-1].WS_AMT = AIRBB07.BAL58;
                WS_AMT_DATA[59-1].WS_AMT = AIRBB07.BAL59;
                WS_AMT_DATA[60-1].WS_AMT = AIRBB07.BAL60;
                WS_AMT_DATA[61-1].WS_AMT = AIRBB07.BAL61;
                WS_AMT_DATA[62-1].WS_AMT = AIRBB07.BAL62;
                WS_AMT_DATA[63-1].WS_AMT = AIRBB07.BAL63;
                WS_AMT_DATA[64-1].WS_AMT = AIRBB07.BAL64;
                WS_AMT_DATA[65-1].WS_AMT = AIRBB07.BAL65;
                WS_AMT_DATA[66-1].WS_AMT = AIRBB07.BAL66;
                WS_AMT_DATA[67-1].WS_AMT = AIRBB07.BAL67;
                WS_AMT_DATA[68-1].WS_AMT = AIRBB07.BAL68;
                WS_AMT_DATA[69-1].WS_AMT = AIRBB07.BAL69;
                WS_AMT_DATA[70-1].WS_AMT = AIRBB07.BAL70;
                WS_AMT_DATA[71-1].WS_AMT = AIRBB07.BAL71;
                WS_AMT_DATA[72-1].WS_AMT = AIRBB07.BAL72;
                WS_AMT_DATA[73-1].WS_AMT = AIRBB07.BAL73;
                WS_AMT_DATA[74-1].WS_AMT = AIRBB07.BAL74;
                WS_AMT_DATA[75-1].WS_AMT = AIRBB07.BAL75;
                WS_AMT_DATA[76-1].WS_AMT = AIRBB07.BAL76;
                WS_OLD_BR = AIRBB07.KEY.BRH_OLD;
                WS_CCY = AIRBB07.KEY.CCY;
            }
        }
    }
    public void B024_CHECK_PRD_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = AICUCPRD.NEW_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUCPRD.NEW_PROD_CD);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
        if (BPCPQPRD.CUS_PER_FLG == '0') {
            WS_NEW_CUS_DATA.WS_NEW_CUS_PER = 'Y';
        }
        if (BPCPQPRD.CUS_COM_FLG == '0') {
            WS_NEW_CUS_DATA.WS_NEW_CUS_COM = 'Y';
        }
        if (BPCPQPRD.CUS_FIN_FLG == '0') {
            WS_NEW_CUS_DATA.WS_NEW_CUS_FIN = 'Y';
        }
        CEP.TRC(SCCGWA, WS_OLD_PROD_TYPE);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_OLD_PROD_TYPE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
        if (BPCPQPRD.CUS_PER_FLG == '0') {
            WS_OLD_CUS_DATA.WS_OLD_CUS_PER = 'Y';
        }
        if (BPCPQPRD.CUS_COM_FLG == '0') {
            WS_OLD_CUS_DATA.WS_OLD_CUS_COM = 'Y';
        }
        if (BPCPQPRD.CUS_FIN_FLG == '0') {
            WS_OLD_CUS_DATA.WS_OLD_CUS_FIN = 'Y';
        }
        CEP.TRC(SCCGWA, WS_NEW_CUS_DATA.WS_NEW_CUS_PER);
        CEP.TRC(SCCGWA, WS_NEW_CUS_DATA.WS_NEW_CUS_COM);
        CEP.TRC(SCCGWA, WS_NEW_CUS_DATA.WS_NEW_CUS_FIN);
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_PER);
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_COM);
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_FIN);
    }
    public void B025_CHECK_PRO_GLM_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = AICUCPRD.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_FIN_TYPE);
        WS_FIN_TYP_OLD = CICCUST.O_DATA.O_FIN_TYPE;
        WS_CI_TYP_OLD = CICCUST.O_DATA.O_CI_TYP;
        if (AICUCPRD.AC.trim().length() > 0 
            && AICUCPRD.DEAL_FLG == '2') {
            IBS.init(SCCGWA, BPCQCNGL);
            IBS.init(SCCGWA, BPCICNGL);
            BPCICNGL.FIN_TYP = AICUCPRD.NEW_FIN_TYP;
            BPCICNGL.CI_TYPE = AICUCPRD.NEW_CI_TYP;
            CEP.TRC(SCCGWA, BPCICNGL.FIN_TYP);
            CEP.TRC(SCCGWA, BPCICNGL.CI_TYPE);
            CEP.TRC(SCCGWA, WS_FIN_TYP_OLD);
            CEP.TRC(SCCGWA, WS_CI_TYP_OLD);
            BPCQCNGL.DAT.INPUT.CNTR_TYPE = WS_OLD_CNTY_TYPE;
            BPCICNGL.PROD_TYPE = AICUCPRD.NEW_PROD_CD;
            CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
            if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
                IBS.init(SCCGWA, BPCAMMDP);
                BPCAMMDP.PROD_TYPE = BPCICNGL.PROD_TYPE;
                BPCAMMDP.CI_TYPE = BPCICNGL.CI_TYPE;
                BPCAMMDP.FIN_TYP = BPCICNGL.FIN_TYP;
                BPCAMMDP.AC_TYP = AICUCPRD.NEW_AC_TYP;
                BPCAMMDP.PROP_TYP = AICUCPRD.NEW_PROP_TYP;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAMMDP;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
            } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
                IBS.init(SCCGWA, BPCACAAC);
                BPCACAAC.PROD_TYPE = BPCICNGL.PROD_TYPE;
                BPCACAAC.CI_TYPE = BPCICNGL.CI_TYPE;
                BPCACAAC.FIN_TYP = BPCICNGL.FIN_TYP;
                BPCACAAC.AC_TYP = AICUCPRD.NEW_AC_TYP;
                BPCACAAC.PROP_TYP = AICUCPRD.NEW_PROP_TYP;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
            } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLDD")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLDP")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLCO")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLQT")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLGU")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLCP")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLQT")) {
                IBS.init(SCCGWA, BPCACLDD);
                BPCACLDD.PROD_CD = AICUCPRD.NEW_PROD_CD;
                BPCACLDD.CI_TYPE = AICUCPRD.NEW_CI_TYP;
                BPCACLDD.FIN_TYP = AICUCPRD.NEW_FIN_TYP;
                BPCACLDD.AC_TYP = AICUCPRD.NEW_AC_TYP;
                BPCACLDD.PROP_TYP = AICUCPRD.NEW_PROP_TYP;
                BPCACLDD.TERM_CD = AICUCPRD.NEW_TERM_CD;
                BPCACLDD.LOAN_TYPE = AICUCPRD.NEW_LOAN_TYPE;
                BPCACLDD.WE_FLG = AICUCPRD.NEW_WE_FLG;
                BPCACLDD.DUTY_FREE = AICUCPRD.NEW_DUTY_FREE;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
            } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBDD")
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBTD")) {
                BPCICNGL.AC_TYP = AICUCPRD.NEW_AC_TYP;
                BPCICNGL.PROP_TYP = AICUCPRD.NEW_PROP_TYP;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCICNGL;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
            } else {
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCICNGL;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCICNGL);
            CEP.TRC(SCCGWA, BPCQCNGL);
            if (BPCQCNGL.RC.RC_CODE != 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PRO_GLM_PARM_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                for (WS_CNT2 = 1; WS_CNT2 <= 10; WS_CNT2 += 1) {
                    WS_NEW_GL_MST[WS_CNT2-1].WS_NEW_MST_NO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_CNT2-1].MSTNO;
                    CEP.TRC(SCCGWA, WS_CNT2);
                    CEP.TRC(SCCGWA, WS_NEW_GL_MST[WS_CNT2-1].WS_NEW_MST_NO);
                }
            }
        }
    }
    public void B026_GET_EVENT_INFO_CHK_ITM() throws IOException,SQLException,Exception {
        WS_OLD_MODEL = WS_OLD_CNTY_TYPE;
        for (WS_CNT0 = 1; WS_CNT0 <= BPCQBKPM.CNT; WS_CNT0 += 1) {
            IBS.init(SCCGWA, BPCPQENT);
            BPCPQENT.FUNC = 'Q';
            BPCPQENT.DATA_INFO.MODNO = WS_OLD_MODEL;
            BPCPQENT.DATA_INFO.EVENT_TYPE = "GO";
            BPCPQENT.DATA_INFO.GL_BOOK = WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG;
            CEP.TRC(SCCGWA, WS_CNT0);
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.MODNO);
            CEP.TRC(SCCGWA, WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG);
            S000_CALL_BPZPQENT();
            if (pgmRtn) return;
            WS_GLM_INFO[WS_CNT0-1].WS_GLM = WS_OLD_GL_MST[WS_CNT0-1].WS_OLD_MST_NO;
            R000_GET_ITM_DATA();
            if (pgmRtn) return;
        }
        for (WS_CNT0 = 1; WS_CNT0 <= BPCQBKPM.CNT; WS_CNT0 += 1) {
            IBS.init(SCCGWA, BPCPQENT);
            BPCPQENT.FUNC = 'Q';
            BPCPQENT.DATA_INFO.MODNO = WS_OLD_MODEL;
            BPCPQENT.DATA_INFO.EVENT_TYPE = "GI";
            BPCPQENT.DATA_INFO.GL_BOOK = WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG;
            CEP.TRC(SCCGWA, WS_CNT0);
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.MODNO);
            CEP.TRC(SCCGWA, WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG);
            S000_CALL_BPZPQENT();
            if (pgmRtn) return;
            WS_GLM_INFO[WS_CNT0-1].WS_GLM = WS_NEW_GL_MST[WS_CNT0-1].WS_NEW_MST_NO;
            R000_GET_ITM_DATA();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_ITM_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.CNT);
        for (WS_CNT1 = 1; WS_CNT1 <= BPCPQENT.DATA_INFO.CNT; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY);
            WS_ITM_NO = " ";
            if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY = "";
            JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY += " ";
            if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.substring(0, 1).equalsIgnoreCase("G")) {
                IBS.init(SCCGWA, BPCPQGLM);
                BPCPQGLM.DAT.INPUT.MSTNO = WS_GLM_INFO[WS_CNT0-1].WS_GLM;
                CEP.TRC(SCCGWA, WS_CNT0);
                CEP.TRC(SCCGWA, WS_GLM_INFO[WS_CNT0-1].WS_GLM);
                if (BPCPQGLM.DAT.INPUT.MSTNO != 0) {
                    S000_CALL_BPZPQGLM();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY);
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY = "";
                JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY += " ";
                CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.substring(2 - 1, 2 + 2 - 1));
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY = "";
                JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY += " ";
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.substring(2 - 1, 2 + 2 - 1).trim().length() == 0) WS_J = 0;
                else WS_J = Integer.parseInt(BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.substring(2 - 1, 2 + 2 - 1));
                WS_ITM_NO = BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO;
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO);
            } else {
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY == null) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY = "";
                JIBS_tmp_int = BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY += " ";
                if (BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].AC_KEY.substring(0, 1).equalsIgnoreCase("S")) {
                    WS_ITM_NO = BPCPQENT.DATA_INFO.EVENT_DATA[WS_CNT1-1].GL_CODE;
                }
            }
            CEP.TRC(SCCGWA, WS_ITM_NO);
            IBS.init(SCCGWA, AICPQITM);
            if (WS_ITM_NO.trim().length() > 0) {
                AICPQITM.INPUT_DATA.NO = WS_ITM_NO;
                AICPQITM.INPUT_DATA.BOOK_FLG = WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
                if (AICPQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO);
            CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_SEQ);
            CEP.TRC(SCCGWA, WS_OLD_BR);
            CEP.TRC(SCCGWA, WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG);
            if (BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO.trim().length() > 0 
                && BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_SEQ != 0) {
                AIRCMIB.KEY.GL_BOOK = WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG;
                AIRCMIB.KEY.BR = WS_OLD_BR;
                AIRCMIB.KEY.ITM = BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO;
                AIRCMIB.KEY.SEQ = BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                if (AIRCMIB.STS != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, WS_CCY);
            IBS.init(SCCGWA, AICPQMIB);
            if (BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO.trim().length() > 0 
                && BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_SEQ != 0 
                && WS_CCY.trim().length() > 0) {
                AICPQMIB.INPUT_DATA.GL_BOOK = WS_BOOK_FLG_ALL.get(WS_CNT0-1).WS_BOOK_FLG;
                AICPQMIB.INPUT_DATA.BR = WS_OLD_BR;
                AICPQMIB.INPUT_DATA.ITM_NO = BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_NO;
                AICPQMIB.INPUT_DATA.SEQ = BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_J-1].ITM_SEQ;
                AICPQMIB.INPUT_DATA.CCY = WS_CCY;
                S000_CALL_AIZPQMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.STS);
                if (AICPQMIB.OUTPUT_DATA.STS != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_NOT_NORMAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B030_CHG_PROD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        WS_CHANGE_FLG = 'Y';
        B026_GET_EVENT_INFO_CHK_ITM();
        if (pgmRtn) return;
        R000_CHG_BPZUGMC();
        if (pgmRtn) return;
    }
    public void R000_CHG_BPZUGMC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHANGE_FLG);
        IBS.init(SCCGWA, BPCUGMC);
        IBS.init(SCCGWA, BPCNCNGL);
        IBS.init(SCCGWA, BPCOCNGL);
        BPCUGMC.INFO.CNTR_TYPE = WS_OLD_CNTY_TYPE;
        BPCUGMC.INFO.CI_NO = AICUCPRD.CI_NO;
        BPCUGMC.INFO.CHANGE_FLG = WS_CHANGE_FLG;
        BPCUGMC.INFO.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCUGMC.INFO.BR_OLD = WS_OLD_BR;
        BPCUGMC.INFO.BR_NEW = WS_OLD_BR;
        BPCUGMC.INFO.CCY_INFO[1-1].CCY = WS_CCY;
        BPCUGMC.INFO.CHG_FLG = 'G';
        BPCOCNGL.PROD_TYPE = WS_OLD_PROD_TYPE;
        BPCNCNGL.PROD_TYPE = AICUCPRD.NEW_PROD_CD;
        CEP.TRC(SCCGWA, BPCOCNGL.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCNCNGL.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCICNGL);
        CEP.TRC(SCCGWA, BPCOCNGL);
        CEP.TRC(SCCGWA, BPCNCNGL);
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
            BPCUGMC.INFO.OTH_PTR_LEN = 21;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCAMMDP;
        } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            BPCUGMC.INFO.OTH_PTR_LEN = 21;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCACAAC;
        } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLDD")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLDP")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLCO")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLQT")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLGU")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLCP")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("CLQT")) {
            BPCUGMC.INFO.OTH_PTR_LEN = 31;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCACLDD;
        } else {
            BPCUGMC.INFO.OTH_PTR_LEN = 31;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCICNGL;
        }
        BPCUGMC.INFO.OTH_PTR_OLD = BPCOCNGL;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCUGMC.INFO.AMTS[WS_I-1].AMT = WS_AMT_DATA[WS_I-1].WS_AMT;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCUGMC.INFO.AMTS[WS_I-1].AMT);
        }
        S000_CALL_BPZUGMC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_NEW_PROD_TYP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        CEP.TRC(SCCGWA, AICUCPRD.NEW_PROD_CD);
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            IBS.init(SCCGWA, DDCSCHPD);
            DDCSCHPD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCSCHPD.PROD = AICUCPRD.NEW_PROD_CD;
            DDCSCHPD.AC_TYP = AICUCPRD.NEW_AC_TYP;
            DDCSCHPD.AMT_TYP = AICUCPRD.NEW_PROP_TYP;
            CEP.TRC(SCCGWA, DDCSCHPD.PROD);
            S000_CALL_DDZSCHPD();
            if (pgmRtn) return;
        } else {
            if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
                IBS.init(SCCGWA, TDCACIU);
                TDCACIU.OTHE_CHG = 'O';
                TDCACIU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                TDCACIU.PROD_CD = AICUCPRD.NEW_PROD_CD;
                TDCACIU.AC_TYP = AICUCPRD.NEW_AC_TYP;
                TDCACIU.MON_TYP = AICUCPRD.NEW_PROP_TYP;
                CEP.TRC(SCCGWA, TDCACIU.OTHE_CHG);
                CEP.TRC(SCCGWA, TDCACIU.ACO_AC);
                CEP.TRC(SCCGWA, TDCACIU.PROD_CD);
                CEP.TRC(SCCGWA, TDCACIU.AC_TYP);
                CEP.TRC(SCCGWA, TDCACIU.MON_TYP);
                S000_CALL_TDZACIU();
                if (pgmRtn) return;
            }
            if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBDD") 
                || WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBTD")) {
                IBS.init(SCCGWA, IBCFATRU);
                IBCFATRU.AC_NO = AICUCPRD.AC;
                IBCFATRU.SEQ_NO = AICUCPRD.ACAC_SEQ;
                IBCFATRU.PROD_CD = AICUCPRD.NEW_PROD_CD;
                IBCFATRU.FUND_ATTR = AICUCPRD.NEW_PROP_TYP;
                CEP.TRC(SCCGWA, IBCFATRU.FUNC);
                CEP.TRC(SCCGWA, IBCFATRU.AC_NO);
                CEP.TRC(SCCGWA, IBCFATRU.SEQ_NO);
                CEP.TRC(SCCGWA, IBCFATRU.PROD_CD);
                CEP.TRC(SCCGWA, IBCFATRU.FUND_ATTR);
                S000_CALL_IBZFATRU();
                if (pgmRtn) return;
            }
        }
        if (!AICUCPRD.AP_MMO.equalsIgnoreCase("PL") 
            && !AICUCPRD.AP_MMO.equalsIgnoreCase("LN")) {
            CEP.TRC(SCCGWA, WS_ERR_RC.WS_ERR_RC_CODE);
            if (AICUCPRD.AC.trim().length() > 0 
                && WS_ERR_RC.WS_ERR_RC_CODE == 0) {
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
                if (!CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
                    && (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC") 
                    || WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBDD"))) {
                    IBS.init(SCCGWA, CICSACR);
                    CICSACR.FUNC = 'M';
                    CICSACR.DATA.AGR_NO = AICUCPRD.AC;
                    CICSACR.DATA.PROD_CD = AICUCPRD.NEW_PROD_CD;
                    S000_CALL_CIZSACR();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, CICSACAC);
                CICSACAC.FUNC = 'M';
                CICSACAC.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                CICSACAC.DATA.PROD_CD = AICUCPRD.NEW_PROD_CD;
                CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
                CEP.TRC(SCCGWA, CICSACAC.DATA.PROD_CD);
                S000_CALL_CIZSACAC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_ERR_RC.WS_ERR_RC_CODE);
            if (AICUCPRD.NEW_CI_TYP != '1' 
                && (!WS_FIN_TYP_OLD.equalsIgnoreCase(AICUCPRD.NEW_FIN_TYP) 
                || WS_CI_TYP_OLD != AICUCPRD.NEW_CI_TYP) 
                && WS_ERR_RC.WS_ERR_RC_CODE == 0) {
                IBS.init(SCCGWA, CICSCGL);
                CEP.TRC(SCCGWA, AICUCPRD.CI_NO);
                CEP.TRC(SCCGWA, AICUCPRD.NEW_FIN_TYP);
                CEP.TRC(SCCGWA, AICUCPRD.NEW_CI_TYP);
                CICSCGL.DATA.CI_NO = AICUCPRD.CI_NO;
                CICSCGL.DATA.FIN_TYPE_NEW = AICUCPRD.NEW_FIN_TYP;
                CICSCGL.DATA.CI_TYP_NEW = AICUCPRD.NEW_CI_TYP;
                S000_CALL_CIZSCGL();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_UPDATE_CPRD() throws IOException,SQLException,Exception {
        AIRCPRD.KEY.CI_NO = AICUCPRD.CI_NO;
        AIRCPRD.KEY.DEAL_FLG = AICUCPRD.DEAL_FLG;
        AIRCPRD.KEY.AC_DATE = AICUCPRD.AC_DATE;
        if (AICUCPRD.DEAL_FLG == '1') {
            AIRCPRD.KEY.AC = " ";
        } else {
            AIRCPRD.KEY.AC = AICUCPRD.AC;
            AIRCPRD.OLD_PROD_CD = WS_OLD_PROD_TYPE;
        }
        CEP.TRC(SCCGWA, AIRCPRD.KEY.CI_NO);
        CEP.TRC(SCCGWA, AIRCPRD.KEY.DEAL_FLG);
        CEP.TRC(SCCGWA, AIRCPRD.KEY.AC_DATE);
        CEP.TRC(SCCGWA, AIRCPRD.KEY.AC);
        T000_READUPD_AITCPRD();
        if (pgmRtn) return;
        AIRCPRD.OLD_FIN_TYP = WS_FIN_TYP_OLD;
        AIRCPRD.OLD_CI_TYP = WS_CI_TYP_OLD;
        AIRCPRD.OLD_AC_TYP = AICUCPRD.OLD_AC_TYP;
        AIRCPRD.OLD_PROP_TYP = AICUCPRD.OLD_PROP_TYP;
        AIRCPRD.OLD_TERM_CD = AICUCPRD.OLD_TERM_CD;
        AIRCPRD.OLD_LOAN_TYPE = AICUCPRD.OLD_LOAN_TYPE;
        AIRCPRD.OLD_WE_FLG = AICUCPRD.OLD_WE_FLG;
        AIRCPRD.OLD_DUTY_FREE = AICUCPRD.OLD_DUTY_FREE;
        CEP.TRC(SCCGWA, WS_ERR_RC.WS_ERR_RC_CODE);
        if (WS_ERR_RC.WS_ERR_RC_CODE == 0) {
            AIRCPRD.MSG_ID = " ";
            AIRCPRD.DESC = " ";
            AIRCPRD.STS = 'E';
        } else {
            AIRCPRD.MSG_ID = IBS.CLS2CPY(SCCGWA, WS_ERR_RC);
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            AIRCPRD.DESC = BPRMSG.DATA_TXT.CMSG_TEXT;
            AIRCPRD.STS = 'F';
        }
        AIRCPRD.SET_NO = SCCGWA.COMM_AREA.VCH_NO;
        AIRCPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_AITCPRD();
        if (pgmRtn) return;
    }
    public void B060_WRITE_OUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_CNTR_TYPE = WS_OLD_CNTY_TYPE;
        WS_OUT_DATA.WS_OUT_NEW_PROD_CD = AICUCPRD.NEW_PROD_CD;
        WS_OUT_DATA.WS_OUT_OLD_PRODCD = WS_OLD_PROD_TYPE;
        WS_OUT_DATA.WS_OUT_CI_NO = AICUCPRD.CI_NO;
        WS_OUT_DATA.WS_OUT_AC = AICUCPRD.AC;
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            for (WS_Z = 1; WS_TMP_CCY_DATA[WS_Z-1].WS_TMP_CCY.trim().length() != 0 
                && WS_Z <= K_MAX_DD; WS_Z += 1) {
                WS_OUT_DATA.WS_OUT_CCY_DATA.WS_OUT_CCY[WS_Z-1] = WS_TMP_CCY_DATA[WS_Z-1].WS_TMP_CCY;
                CEP.TRC(SCCGWA, WS_Z);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CCY_DATA.WS_OUT_CCY[WS_Z-1]);
            }
        } else {
            WS_OUT_DATA.WS_OUT_CCY_DATA.WS_OUT_CCY[1-1] = WS_CCY;
        }
        WS_OUT_DATA.WS_OUT_FINNEW = AICUCPRD.NEW_FIN_TYP;
        WS_OUT_DATA.WS_OUT_CITYPNEW = AICUCPRD.NEW_CI_TYP;
        WS_OUT_DATA.WS_OUT_FINOLD = WS_FIN_TYP_OLD;
        WS_OUT_DATA.WS_OUT_CITYPOLD = WS_CI_TYP_OLD;
        WS_OUT_DATA.WS_OUT_ACTYPNEW = AICUCPRD.NEW_AC_TYP;
        WS_OUT_DATA.WS_OUT_ACTYPOLD = AICUCPRD.OLD_AC_TYP;
        WS_OUT_DATA.WS_OUT_PROPTNEW = AICUCPRD.NEW_PROP_TYP;
        WS_OUT_DATA.WS_OUT_PROPTOLD = AICUCPRD.OLD_PROP_TYP;
        WS_OUT_DATA.WS_OUT_TERMDNEW = AICUCPRD.NEW_TERM_CD;
        WS_OUT_DATA.WS_OUT_TERMDOLD = AICUCPRD.OLD_TERM_CD;
        WS_OUT_DATA.WS_OUT_LNTYPNEW = AICUCPRD.NEW_LOAN_TYPE;
        WS_OUT_DATA.WS_OUT_LNTYPOLD = AICUCPRD.OLD_LOAN_TYPE;
        WS_OUT_DATA.WS_OUT_WEFLGNEW = AICUCPRD.NEW_WE_FLG;
        WS_OUT_DATA.WS_OUT_WEFLGOLD = AICUCPRD.OLD_WE_FLG;
        WS_OUT_DATA.WS_OUT_DTFRENEW = AICUCPRD.NEW_DUTY_FREE;
        WS_OUT_DATA.WS_OUT_DTFREOLD = AICUCPRD.OLD_DUTY_FREE;
        WS_OUT_DATA.WS_OUT_GLM_OLD = WS_OLD_GL_MST[1-1].WS_OLD_MST_NO;
        WS_OUT_DATA.WS_OUT_GLM_NEW = WS_NEW_GL_MST[1-1].WS_NEW_MST_NO;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CNTR_TYPE);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_NEW_PROD_CD);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_OLD_PRODCD);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CCY_DATA.WS_OUT_CCY[1-1]);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CI_NO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_AC);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_FINNEW);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CITYPNEW);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_FINOLD);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_CITYPOLD);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_GLM_OLD);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_GLM_NEW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI588";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 9560;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READUPD_AITCPRD() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        AITCPRD_RD.upd = true;
        IBS.READ(SCCGWA, AIRCPRD, AITCPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CPRD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CPRD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITCPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_AITCPRD() throws IOException,SQLException,Exception {
        AITCPRD_RD = new DBParm();
        AITCPRD_RD.TableName = "AITCPRD";
        IBS.REWRITE(SCCGWA, AIRCPRD, AITCPRD_RD);
    }
    public void S000_CALL_LNZSTRPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVC-TR-PROD", LNCSTRPD);
        CEP.TRC(SCCGWA, LNCSTRPD.RC.RC_RTNCODE);
        if (LNCSTRPD.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSTRPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZSTRPD() throws IOException,SQLException,Exception {
