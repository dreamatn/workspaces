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
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQENT;
import com.hisun.BP.BPCPQGLM;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCUCNGM;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCSCINM;
import com.hisun.IB.IBCQINF;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCACE;

public class AIOT5905 {
    boolean pgmRtn = false;
    String TBL_AITCPRD = "AITCPRD";
    short K_MAX_DD = 40;
    String WS_ERR_MSG = " ";
    String WS_CI_NO = " ";
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
    String WS_APP_MO = " ";
    AIOT5905_WS_OLD_GL_MST[] WS_OLD_GL_MST = new AIOT5905_WS_OLD_GL_MST[10];
    AIOT5905_WS_NEW_GL_MST[] WS_NEW_GL_MST = new AIOT5905_WS_NEW_GL_MST[10];
    AIOT5905_WS_GLM_INFO[] WS_GLM_INFO = new AIOT5905_WS_GLM_INFO[10];
    AIOT5905_WS_AMT_DATA[] WS_AMT_DATA = new AIOT5905_WS_AMT_DATA[76];
    AIOT5905_WS_TMP_CCY_DATA[] WS_TMP_CCY_DATA = new AIOT5905_WS_TMP_CCY_DATA[40];
    char WS_CHANGE_FLG = ' ';
    AIOT5905_WS_NEW_CUS_DATA WS_NEW_CUS_DATA = new AIOT5905_WS_NEW_CUS_DATA();
    AIOT5905_WS_OLD_CUS_DATA WS_OLD_CUS_DATA = new AIOT5905_WS_OLD_CUS_DATA();
    AIOT5905_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIOT5905_WS_BRW_OUTPUT();
    AIOT5905_WS_OUTPUT WS_OUTPUT = new AIOT5905_WS_OUTPUT();
    List<AIOT5905_WS_BOOK_FLG_ALL> WS_BOOK_FLG_ALL = new ArrayList<AIOT5905_WS_BOOK_FLG_ALL>();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICRCPRD AICRCPRD = new AICRCPRD();
    AIRCPRD AIRCPRD = new AIRCPRD();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQENT BPCPQENT = new BPCPQENT();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCIQAPL DDCIQAPL = new DDCIQAPL();
    DDCSCHPD DDCSCHPD = new DDCSCHPD();
    LNCSTRPD LNCSTRPD = new LNCSTRPD();
    TDCIQAPT TDCIQAPT = new TDCIQAPT();
    TDCSUPPD TDCSUPPD = new TDCSUPPD();
    BACSTRPD BACTRPD = new BACSTRPD();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCCNGL BPCOCNGL = new BPCCNGL();
    AICPQITM AICPQITM = new AICPQITM();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCACLBA BPCACLBA = new BPCACLBA();
    BPCACLCM BPCACLCM = new BPCACLCM();
    BPCACLGU BPCACLGU = new BPCACLGU();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICQACAC CICQACAC = new CICQACAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    TDCACE TDCACE = new TDCACE();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFS IBCQINFS = new IBCQINFS();
    SCCGWA SCCGWA;
    SCCAWAC SCCAWAC;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5900_AWA_5900 AIB5900_AWA_5900;
    public AIOT5905() {
        for (int i=0;i<10;i++) WS_OLD_GL_MST[i] = new AIOT5905_WS_OLD_GL_MST();
        for (int i=0;i<10;i++) WS_NEW_GL_MST[i] = new AIOT5905_WS_NEW_GL_MST();
        for (int i=0;i<10;i++) WS_GLM_INFO[i] = new AIOT5905_WS_GLM_INFO();
        for (int i=0;i<76;i++) WS_AMT_DATA[i] = new AIOT5905_WS_AMT_DATA();
        for (int i=0;i<40;i++) WS_TMP_CCY_DATA[i] = new AIOT5905_WS_TMP_CCY_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5905 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5900_AWA_5900>");
        AIB5900_AWA_5900 = (AIB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_FOUND_GL_BOOK_FLG();
        if (pgmRtn) return;
        B020_CHECK_INPUT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT();
        if (pgmRtn) return;
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
        if (AIB5900_AWA_5900.DEAL_FLG == '2' 
            || AIB5900_AWA_5900.DEAL_FLG == '3') {
            B011_CHECK_AC_CI_RELAC();
            if (pgmRtn) return;
            B012_CHECK_CNTY_TYPE();
            if (pgmRtn) return;
            B013_GET_BAL_OLD_PROTYP_BR();
            if (pgmRtn) return;
            B014_CHECK_PRD_TYPE();
            if (pgmRtn) return;
            B015_CHECK_PRO_GLM_PARM();
            if (pgmRtn) return;
            B016_GET_EVENT_INFO_CHK_ITM();
            if (pgmRtn) return;
        } else {
            B017_CHK_CI_ATTRIBUTE();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_AC_CI_RELAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = AIB5900_AWA_5900.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        WS_APP_MO = CICACCU.DATA.FRM_APP;
        CEP.TRC(SCCGWA, WS_APP_MO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = AIB5900_AWA_5900.AC_NO;
        CICQACAC.DATA.AGR_SEQ = AIB5900_AWA_5900.ACAC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC);
        WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
    }
    public void B012_CHECK_CNTY_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.FUNC = 'Q';
        BPCUCNGM.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
        BPCPQPRD.PRDT_CODE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
        if (BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLCM") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLDL") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLGU") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLDP") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CLBA") 
            || BPCUCNGM.KEY.CNTR_TYPE.equalsIgnoreCase("CARD")) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_LN_BA_NOT_CAGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OLD_CNTY_TYPE = BPCUCNGM.KEY.CNTR_TYPE;
        WS_OLD_MODEL = BPCUCNGM.MOD_NO;
        for (WS_CNT1 = 1; WS_CNT1 <= 10; WS_CNT1 += 1) {
            WS_OLD_GL_MST[WS_CNT1-1].WS_OLD_MST_NO = BPCUCNGM.DATA[WS_CNT1-1].GLMST;
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_OLD_GL_MST[WS_CNT1-1].WS_OLD_MST_NO);
        }
    }
    public void B013_GET_BAL_OLD_PROTYP_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
        WS_OLD_PROD_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            CEP.TRC(SCCGWA, "CAAC");
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = AIB5900_AWA_5900.AC_NO;
            DDCSCINM.INPUT_DATA.CCY = WS_CCY;
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AC_TYP);
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AMT_TYPE);
            CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.OWNER_BRDP);
            WS_AC_TYP_OLD = DDCSCINM.OUTPUT_DATA.AC_TYP;
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
                WS_AC_TYP_OLD = 'A';
            }
            WS_PROP_TYP_OLD = DDCSCINM.OUTPUT_DATA.AMT_TYPE;
            WS_OLD_BR = DDCSCINM.OUTPUT_DATA.OWNER_BRDP;
        }
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
            CEP.TRC(SCCGWA, "MMDP");
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = AIB5900_AWA_5900.AC_NO;
            TDCACE.PAGE_INF.I_AC_SEQ = AIB5900_AWA_5900.ACAC_SEQ;
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
            IBCQINF.INPUT_DATA.AC_NO = AIB5900_AWA_5900.AC_NO;
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
            IBCQINFS.AC_NO = AIB5900_AWA_5900.AC_NO;
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
    }
    public void B014_CHECK_PRD_TYPE() throws IOException,SQLException,Exception {
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
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_PER);
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_COM);
        CEP.TRC(SCCGWA, WS_OLD_CUS_DATA.WS_OLD_CUS_FIN);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = WS_CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_FIN_TYPE);
        WS_FIN_TYP_OLD = CICCUST.O_DATA.O_FIN_TYPE;
        WS_CI_TYP_OLD = CICCUST.O_DATA.O_CI_TYP;
    }
    public void B015_CHECK_PRO_GLM_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCICNGL);
        BPCICNGL.FIN_TYP = WS_FIN_TYP_OLD;
        BPCICNGL.CI_TYPE = WS_CI_TYP_OLD;
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = WS_OLD_CNTY_TYPE;
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("MMDP")) {
            IBS.init(SCCGWA, BPCAMMDP);
            BPCAMMDP.PROD_TYPE = WS_OLD_PROD_TYPE;
            BPCAMMDP.CI_TYPE = WS_CI_TYP_OLD;
            BPCAMMDP.FIN_TYP = WS_FIN_TYP_OLD;
            BPCAMMDP.AC_TYP = WS_AC_TYP_OLD;
            BPCAMMDP.PROP_TYP = WS_PROP_TYP_OLD;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAMMDP;
            S000_CALL_BPZQCNGL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCOCNGL);
            IBS.CPY2CLS(SCCGWA, BPCQCNGL.DAT.OUTPUT.OTH, BPCOCNGL);
        } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("CAAC")) {
            IBS.init(SCCGWA, BPCACAAC);
            BPCACAAC.PROD_TYPE = WS_OLD_PROD_TYPE;
            BPCACAAC.CI_TYPE = WS_CI_TYP_OLD;
            BPCACAAC.FIN_TYP = WS_FIN_TYP_OLD;
            BPCACAAC.AC_TYP = WS_AC_TYP_OLD;
            BPCACAAC.PROP_TYP = WS_PROP_TYP_OLD;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
            S000_CALL_BPZQCNGL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCOCNGL);
            IBS.CPY2CLS(SCCGWA, BPCQCNGL.DAT.OUTPUT.OTH, BPCOCNGL);
        } else if (WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBDD")
            || WS_OLD_CNTY_TYPE.equalsIgnoreCase("IBTD")) {
            BPCICNGL.PROD_TYPE = WS_OLD_PROD_TYPE;
            BPCICNGL.AC_TYP = WS_AC_TYP_OLD;
            BPCICNGL.PROP_TYP = WS_PROP_TYP_OLD;
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
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PRO_GLM_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            for (WS_CNT2 = 1; WS_CNT2 <= 10; WS_CNT2 += 1) {
                WS_OLD_GL_MST[WS_CNT2-1].WS_OLD_MST_NO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_CNT2-1].MSTNO;
                CEP.TRC(SCCGWA, WS_CNT2);
                CEP.TRC(SCCGWA, WS_OLD_GL_MST[WS_CNT2-1].WS_OLD_MST_NO);
            }
        }
    }
    public void B016_GET_EVENT_INFO_CHK_ITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OLD_MODEL);
        CEP.TRC(SCCGWA, WS_OLD_CNTY_TYPE);
        CEP.TRC(SCCGWA, WS_OLD_PROD_TYPE);
        if (WS_OLD_MODEL.trim().length() == 0) {
            IBS.init(SCCGWA, BPCPQAMO);
            BPCPQAMO.FUNC = 'Q';
            BPCPQAMO.DATA_INFO.CNTR_TYPE = WS_OLD_CNTY_TYPE;
            BPCPQAMO.DATA_INFO.PROD_TYPE = WS_OLD_PROD_TYPE;
            S000_CALL_BPZPQAMO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
            WS_OLD_MODEL = BPCPQAMO.DATA_INFO.MOD_NO;
        }
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
    public void B017_CHK_CI_ATTRIBUTE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = AIB5900_AWA_5900.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_FIN_TYPE);
        WS_FIN_TYP_OLD = CICCUST.O_DATA.O_FIN_TYPE;
        WS_CI_TYP_OLD = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_FIN_TYP_OLD);
        CEP.TRC(SCCGWA, WS_CI_TYP_OLD);
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD", BPCPQAMO);
        CEP.TRC(SCCGWA, BPCPQAMO.RC);
        if (BPCPQAMO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZSUPPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CG-TDZSUPPD", TDCSUPPD);
        CEP.TRC(SCCGWA, TDCSUPPD.RC.RC_CODE);
        if (TDCSUPPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCSUPPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
        CEP.TRC(SCCGWA, BPCPQGLM.RC.RC_CODE);
        if (BPCPQGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PROD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZSTRPD() throws IOException,SQLException,Exception {
