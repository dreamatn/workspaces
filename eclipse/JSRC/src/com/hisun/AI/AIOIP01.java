package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP01 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_AI_P_INQ_ITM = "AI-P-INQ-ITM        ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    String CPN_BP_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_BP_P_QUERY_BKAI = "BP-P-QUERY-BKAI     ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_DESC_LEN_LMT = 120;
    short WS_CUR_POS = 0;
    short WS_I = 0;
    short WS_LOC = 0;
    AIOIP01_WS_T_CTL WS_T_CTL = new AIOIP01_WS_T_CTL();
    int WS_1_DATE = 0;
    AIOIP01_REDEFINES9 REDEFINES9 = new AIOIP01_REDEFINES9();
    int WS_2_DATE = 0;
    AIOIP01_REDEFINES13 REDEFINES13 = new AIOIP01_REDEFINES13();
    String WS_PROD_CD = " ";
    String WS_LOAN_TYPE = " ";
    String WS_ITM = " ";
    AIOIP01_WS_DD_RC WS_DD_RC = new AIOIP01_WS_DD_RC();
    char WS_CHN_FLG = ' ';
    char WS_END_FLG = ' ';
    String WS_PROD_AP = " ";
    double WS_AMT = 0;
    String WS_VIL_TYP = " ";
    int WS_RVS_EXP_DT = 0;
    double WS_AFT_AMT = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICOCKOP AICOCKOP = new AICOCKOP();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCEX BPCEX = new BPCEX();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCICKCD DDCICKCD = new DDCICKCD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCQINF IBCQINF = new IBCQINF();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUPRVS AICUPRVS = new AICUPRVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    AIB0001_AWA_0001 AIB0001_AWA_0001;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP01 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0001_AWA_0001>");
        AIB0001_AWA_0001 = (AIB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.TEMPFLG);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.TEMPNO);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.TMPNM);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ISSUDAT);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.GRP);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ENTR);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.GLBOOK);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.VALDAT);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ENTRTYPE);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.BR);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.CUR);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ITM);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.AC);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.CIF);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ACTNCODE);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.AMT);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.NARRCD);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.TRNDESC);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.RVS_FLG);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.REF);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.PORTCD);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.ENTRSTS);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.TRNTY);
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.REV_NO);
        CEP.TRC(SCCGWA, "****************************");
        if (AIB0001_AWA_0001.TRNDESC_NO != 0) {
            if (AIB0001_AWA_0001.TRNDESC.trim().length() > 0) {
                WS_CHN_FLG = 'N';
                WS_LOC = 1;
                for (WS_I = 1; WS_I <= WS_DESC_LEN_LMT; WS_I += 1) {
                    if (AIB0001_AWA_0001.TRNDESC == null) AIB0001_AWA_0001.TRNDESC = "";
                    JIBS_tmp_int = AIB0001_AWA_0001.TRNDESC.length();
                    for (int i=0;i<120-JIBS_tmp_int;i++) AIB0001_AWA_0001.TRNDESC += " ";
                    if (AIB0001_AWA_0001.TRNDESC.charAt(WS_I - 1) == 0X0E) {
                        if (AIB0001_AWA_0001.ENTRTYPE == 'N') {
                            WS_CUR_POS = AIB0001_AWA_0001.TRNDESC_NO;
                            WS_CUR_POS -= 1;
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_IB_NINPUT_CHN, WS_CUR_POS);
                        }
                        if (WS_CHN_FLG == 'Y') {
                            WS_CUR_POS = AIB0001_AWA_0001.TRNDESC_NO;
                            WS_CUR_POS -= 1;
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, WS_CUR_POS);
                        } else {
                            WS_CHN_FLG = 'Y';
                            WS_LOC = WS_I;
                        }
                    }
                    if (AIB0001_AWA_0001.TRNDESC == null) AIB0001_AWA_0001.TRNDESC = "";
                    JIBS_tmp_int = AIB0001_AWA_0001.TRNDESC.length();
                    for (int i=0;i<120-JIBS_tmp_int;i++) AIB0001_AWA_0001.TRNDESC += " ";
                    if (AIB0001_AWA_0001.TRNDESC.charAt(WS_I - 1) == 0X0F) {
                        if (WS_CHN_FLG == 'N') {
                            WS_CUR_POS = AIB0001_AWA_0001.TRNDESC_NO;
                            WS_CUR_POS -= 1;
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, WS_CUR_POS);
                        } else {
                            WS_CHN_FLG = 'N';
                        }
                    }
                }
                if (WS_CHN_FLG == 'Y') {
                    WS_CUR_POS = AIB0001_AWA_0001.TRNDESC_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DESC_EX_LIMIT, WS_CUR_POS);
                }
                if (AIB0001_AWA_0001.ENTRTYPE == 'D') {
                    WS_END_FLG = 'N';
                    for (WS_I = 37; WS_I <= 60 
                        && WS_END_FLG != 'Y'; WS_I += 1) {
                        if (AIB0001_AWA_0001.TRNDESC == null) AIB0001_AWA_0001.TRNDESC = "";
                        JIBS_tmp_int = AIB0001_AWA_0001.TRNDESC.length();
                        for (int i=0;i<120-JIBS_tmp_int;i++) AIB0001_AWA_0001.TRNDESC += " ";
                        if (AIB0001_AWA_0001.TRNDESC.substring(WS_I - 1, WS_I + 1 - 1).trim().length() > 0) {
                            WS_CUR_POS = AIB0001_AWA_0001.TRNDESC_NO;
                            WS_CUR_POS -= 1;
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DESC_EX_DD_LIMIT, WS_CUR_POS);
                            WS_END_FLG = 'Y';
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.BR_NO);
        if (AIB0001_AWA_0001.BR_NO != 0) {
            CEP.TRC(SCCGWA, AIB0001_AWA_0001.BR);
            if (AIB0001_AWA_0001.BR != 0) {
                if (AIB0001_AWA_0001.ENTRTYPE == 'N' 
                    || AIB0001_AWA_0001.ENTRTYPE == 'D') {
                    WS_CUR_POS = AIB0001_AWA_0001.BR_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_ACCT_NOT_INPUT, WS_CUR_POS);
                } else {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = AIB0001_AWA_0001.BR;
                    WS_CUR_POS = AIB0001_AWA_0001.BR_NO;
                    S00_CALL_BPZPQORG();
                    if (BPCPQORG.ATTR != '2') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                    }
                    WS_VIL_TYP = BPCPQORG.TRA_TYP;
                    CEP.TRC(SCCGWA, WS_VIL_TYP);
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    S00_CALL_BPZPQORG();
                    CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
                    if (!BPCPQORG.TRA_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DIFF_BOOK_BR_VIL_TYP);
                    }
                }
            } else {
                if (AIB0001_AWA_0001.ENTRTYPE == 'G') {
                    WS_CUR_POS = AIB0001_AWA_0001.BR_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT, WS_CUR_POS);
                }
            }
        }
        if (AIB0001_AWA_0001.CUR_NO != 0 
            && AIB0001_AWA_0001.CUR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AIB0001_AWA_0001.CUR;
            WS_CUR_POS = AIB0001_AWA_0001.CUR_NO;
            S00_CALL_BPZQCCY();
        }
        if (AIB0001_AWA_0001.ENTRTYPE_NO != 0) {
            if (AIB0001_AWA_0001.ENTRTYPE == 'G') {
                if (AIB0001_AWA_0001.ITM.trim().length() == 0) {
                    WS_CUR_POS = AIB0001_AWA_0001.ITM_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_MUST_INPUT, WS_CUR_POS);
                }
            } else {
                if (AIB0001_AWA_0001.AC.trim().length() == 0) {
                    WS_CUR_POS = AIB0001_AWA_0001.AC_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_AC_MUST_INPUT, WS_CUR_POS);
                }
                if (AIB0001_AWA_0001.ITM.trim().length() == 0) {
                    WS_CUR_POS = AIB0001_AWA_0001.ITM_NO;
                    WS_CUR_POS -= 1;
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_COA_INPUT_ERR, WS_CUR_POS);
                }
                if (AIB0001_AWA_0001.ENTRTYPE == 'D' 
                    || AIB0001_AWA_0001.ENTRTYPE == 'N') {
                    IBS.init(SCCGWA, BPCPRMR);
                    IBS.init(SCCGWA, AIRPAI1);
                    AIRPAI1.KEY.TYP = "PAI01";
                    AIRPAI1.KEY.REDEFINES6.CODE = "FMD";
                    AIRPAI1.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI1.KEY.REDEFINES6);
                    BPCPRMR.DAT_PTR = AIRPAI1;
                    S000_CALL_BPZPRMR();
                    CEP.TRC(SCCGWA, AIRPAI1.DATA_TXT.DATA_INF.BR);
                    CEP.TRC(SCCGWA, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == AIRPAI1.DATA_TXT.DATA_INF.BR) {
                        WS_CUR_POS = AIB0001_AWA_0001.ENTRTYPE_NO;
                        WS_CUR_POS -= 1;
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_FMD_NINPUT_DDIB, WS_CUR_POS);
                    }
                }
            }
            if (AIB0001_AWA_0001.ENTRTYPE == 'D') {
                if (AIB0001_AWA_0001.AC.trim().length() > 0) {
                    CEP.TRC(SCCGWA, AIB0001_AWA_0001.RED_FLG);
                    IBS.init(SCCGWA, DDCICKCD);
                    if ((AIB0001_AWA_0001.ACTNCODE == 'C' 
                        && AIB0001_AWA_0001.RED_FLG == 'B') 
                        || (AIB0001_AWA_0001.ACTNCODE == 'D' 
                        && AIB0001_AWA_0001.RED_FLG == 'R')) {
                        DDCICKCD.INPUT_DATA.FUNC = '1';
                    } else {
                        DDCICKCD.INPUT_DATA.FUNC = '2';
                    }
                    DDCICKCD.INPUT_DATA.AC_NO = AIB0001_AWA_0001.AC;
                    DDCICKCD.INPUT_DATA.CCY = AIB0001_AWA_0001.CUR;
                    CEP.TRC(SCCGWA, AIB0001_AWA_0001.RED_FLG);
                    if (AIB0001_AWA_0001.RED_FLG == 'R') {
                        WS_AMT = AIB0001_AWA_0001.AMT * ( -1 );
                        DDCICKCD.INPUT_DATA.TX_AMT = WS_AMT;
                    } else {
                        DDCICKCD.INPUT_DATA.TX_AMT = AIB0001_AWA_0001.AMT;
                    }
                    DDCICKCD.INPUT_DATA.HOLD_M_FLG = 'N';
                    S00_CALL_DDZICKCD();
                }
            }
            if (AIB0001_AWA_0001.ENTRTYPE == 'N') {
                if (AIB0001_AWA_0001.REF_NO != 0) {
                    if (AIB0001_AWA_0001.REF.trim().length() == 0) {
                        WS_CUR_POS = AIB0001_AWA_0001.REF_NO;
                        WS_CUR_POS -= 1;
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_REF_M_INPUT, WS_CUR_POS);
                    }
                }
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.NOSTRO_CD = AIB0001_AWA_0001.AC;
                IBCQINF.INPUT_DATA.CCY = AIB0001_AWA_0001.CUR;
                S00_CALL_IBZQINF();
            }
            if (AIB0001_AWA_0001.ENTRTYPE == 'I') {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.GL_BOOK = AIB0001_AWA_0001.GLBOOK;
                AICPQMIB.INPUT_DATA.AC = AIB0001_AWA_0001.AC;
                AICPQMIB.INPUT_DATA.CCY = AIB0001_AWA_0001.CUR;
                AICPQMIB.INPUT_DATA.TX_FLG = 'O';
                S00_CALL_AIZPQMIB();
                if (AICPQMIB.OUTPUT_DATA.STS != 'N' 
                    && AICPQMIB.OUTPUT_DATA.STS != 'S') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_NOT_NORMAL);
                }
                if (AICPQMIB.OUTPUT_DATA.STS == 'S') {
                    if (AIB0001_AWA_0001.ACTNCODE == 'D') {
                        WS_AFT_AMT = AICPQMIB.OUTPUT_DATA.CBAL - AIB0001_AWA_0001.AMT;
                    } else {
                        WS_AFT_AMT = AICPQMIB.OUTPUT_DATA.CBAL + AIB0001_AWA_0001.AMT;
                    }
                    if (AICPQMIB.OUTPUT_DATA.CBAL < 0) {
                        if (WS_AFT_AMT > 0 
                            || WS_AFT_AMT < AICPQMIB.OUTPUT_DATA.CBAL) {
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_IN_CLN_STS);
                        }
                    }
                    if (AICPQMIB.OUTPUT_DATA.CBAL > 0) {
                        if (WS_AFT_AMT < 0 
                            || WS_AFT_AMT > AICPQMIB.OUTPUT_DATA.CBAL) {
                            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_IN_CLN_STS);
                        }
                    }
                    if (AICPQMIB.OUTPUT_DATA.CBAL == 0) {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_IN_CLN_STS);
                    }
                }
                if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG == 'N') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_AC_NOT_MANUL_POST);
                }
                CEP.TRC(SCCGWA, AIB0001_AWA_0001.ACTNCODE);
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.AMT_DIR);
                if (AICPQMIB.OUTPUT_DATA.AMT_DIR != 'B' 
                    && AICPQMIB.OUTPUT_DATA.AMT_DIR != AIB0001_AWA_0001.ACTNCODE) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DIR_NOT_ALLOW);
                }
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_TYP);
                CEP.TRC(SCCGWA, AIB0001_AWA_0001.RVS_FLG);
                CEP.TRC(SCCGWA, AIB0001_AWA_0001.RVS_FLG);
                if (AIB0001_AWA_0001.RVS_FLG == '1') {
                    CEP.TRC(SCCGWA, "IS 111111");
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP != AIB0001_AWA_0001.ACTNCODE 
                        && AIB0001_AWA_0001.RED_FLG == 'B') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP);
                    }
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP == AIB0001_AWA_0001.ACTNCODE 
                        && AIB0001_AWA_0001.RED_FLG == 'R') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP2_FOR_RED);
                    }
                }
                if (AIB0001_AWA_0001.RVS_FLG == '2') {
                    CEP.TRC(SCCGWA, "IS 111111");
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP == AIB0001_AWA_0001.ACTNCODE 
                        && AIB0001_AWA_0001.RED_FLG == 'B') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_EQ_RVS_TYP);
                    }
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP != AIB0001_AWA_0001.ACTNCODE 
                        && AIB0001_AWA_0001.RED_FLG == 'R') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP1_FOR_RED);
                    }
                }
                if (AICPQMIB.OUTPUT_DATA.RVS_TYP != AIB0001_AWA_0001.RVS_TYP) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP_NOT_EQ_AWA);
                }
                if (AIB0001_AWA_0001.RVS_EXP != 0) {
                    R000_CAL_EXP_DT();
                    if (AIB0001_AWA_0001.RVS_EXP > WS_RVS_EXP_DT) {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_EXP_ERROR);
                    }
                }
                if (AICPQMIB.INPUT_DATA.BR != AIB0001_AWA_0001.BR 
                    || !AICPQMIB.INPUT_DATA.ITM_NO.equalsIgnoreCase(AIB0001_AWA_0001.ITM) 
                    || !AICPQMIB.INPUT_DATA.CCY.equalsIgnoreCase(AIB0001_AWA_0001.CUR) 
                    || AICPQMIB.INPUT_DATA.SEQ != AIB0001_AWA_0001.AC_SEQ) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_NOT_SYNC_MIB);
                }
                AIB0001_AWA_0001.BR = AICPQMIB.INPUT_DATA.BR;
                AIB0001_AWA_0001.ITM = AICPQMIB.INPUT_DATA.ITM_NO;
                IBS.init(SCCGWA, AICUPRVS);
                AICUPRVS.DATA.FUNC = 'C';
                AICUPRVS.DATA.GL_BOOK = AIB0001_AWA_0001.GLBOOK;
                AICUPRVS.DATA.AC = AIB0001_AWA_0001.AC;
                AICUPRVS.DATA.CCY = AIB0001_AWA_0001.CUR;
                AICUPRVS.DATA.BR = AIB0001_AWA_0001.BR;
                AICUPRVS.DATA.SIGN = AIB0001_AWA_0001.ACTNCODE;
                AICUPRVS.DATA.AMT = AIB0001_AWA_0001.AMT;
                AICUPRVS.DATA.RED_FLG = AIB0001_AWA_0001.RED_FLG;
                AICUPRVS.DATA.RVS_NO = AIB0001_AWA_0001.REV_NO;
                S00_CALL_AIZUPRVS();
            }
            if (AIB0001_AWA_0001.ENTRTYPE == 'G') {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.NO = AIB0001_AWA_0001.ITM;
                AICPQITM.INPUT_DATA.BOOK_FLG = AIB0001_AWA_0001.GLBOOK;
                S00_CALL_AIZPQITM();
                if (AICPQITM.RC.RTNCODE != 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
                    CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
                }
                if (AICPQITM.OUTPUT_DATA.MIB_FLG != 'G') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_ISNOT_G);
                }
                if (AICPQITM.OUTPUT_DATA.STS != 'A') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE);
                }
                if (AICPQITM.OUTPUT_DATA.ODE_FLG != 'Y') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_AC_NOT_MANUL_POST);
                }
                if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    && AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'C' 
                    && AIB0001_AWA_0001.ACTNCODE == 'D') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DIR_NOT_ALLOW);
                }
            }
        }
        if (AIB0001_AWA_0001.RED_FLG == 'R' 
            && AIB0001_AWA_0001.AMT > 0) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.RED_FLG_AMT_NEG, AIB0001_AWA_0001.AMT_NO);
        }
        if (AIB0001_AWA_0001.RED_FLG != 'R' 
            && AIB0001_AWA_0001.AMT < 0) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.N_RED_FLG_AMT_POSTIVE, AIB0001_AWA_0001.AMT_NO);
        }
        CEP.TRC(SCCGWA, AIB0001_AWA_0001.NARRCD);
        if (AIB0001_AWA_0001.NARRCD_NO != 0) {
            if (AIB0001_AWA_0001.NARRCD.trim().length() > 0) {
                CEP.TRC(SCCGWA, AIB0001_AWA_0001.NARRCD);
                IBS.init(SCCGWA, BPCOQPCD);
                BPCOQPCD.INPUT_DATA.TYPE = "MMO";
                BPCOQPCD.INPUT_DATA.CODE = AIB0001_AWA_0001.NARRCD;
                WS_CUR_POS = AIB0001_AWA_0001.NARRCD_NO;
                S000_CALL_BPZPQPCD();
            }
        }
        if (WS_PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = WS_PROD_CD;
            S000_CALL_BPZPQPRD();
        }
        if (AIB0001_AWA_0001.VALDAT_NO != 0) {
            if (AIB0001_AWA_0001.VALDAT != 0) {
                if (AIB0001_AWA_0001.VALDAT != SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.init(SCCGWA, AICOCKOP);
                    AICOCKOP.VAL_DATE = AIB0001_AWA_0001.VALDAT;
                    S000_CALL_AIZPCKOP();
                }
            } else {
                AIB0001_AWA_0001.VALDAT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        CEP.ERR(SCCGWA);
    }
    public void R000_CAL_EXP_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_UNIT);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_EXP);
        if (AICPQMIB.OUTPUT_DATA.RVS_UNIT != ' ' 
            && AICPQMIB.OUTPUT_DATA.RVS_EXP != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            if (AICPQMIB.OUTPUT_DATA.RVS_UNIT == 'D') {
                SCCCLDT.DAYS = AICPQMIB.OUTPUT_DATA.RVS_EXP;
                CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            } else {
                SCCCLDT.MTHS = AICPQMIB.OUTPUT_DATA.RVS_EXP;
                CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            }
            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC == 0) {
                WS_RVS_EXP_DT = SCCCLDT.DATE2;
            }
        } else {
            if (AICPQMIB.OUTPUT_DATA.RVS_UNIT != ' ' 
                && AICPQMIB.OUTPUT_DATA.RVS_EXP == 0) {
                WS_RVS_EXP_DT = 99991231;
            } else {
                WS_RVS_EXP_DT = 0;
            }
        }
        CEP.TRC(SCCGWA, WS_RVS_EXP_DT);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.AC_EXP);
        if (AICPQMIB.OUTPUT_DATA.AC_EXP < WS_RVS_EXP_DT 
            && AICPQMIB.OUTPUT_DATA.AC_EXP != 0) {
            WS_RVS_EXP_DT = AICPQMIB.OUTPUT_DATA.AC_EXP;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE == 0) {
            WS_PROD_AP = BPCPQPRD.BUSI_TYPE;
        } else {
            BPCOQPCD.INPUT_DATA.TYPE = "PROD";
            BPCOQPCD.INPUT_DATA.CODE = WS_PROD_CD;
            IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
            if (BPCOQPCD.RC.RC_CODE != 0) {
                CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_NOT_EXIST, WS_CUR_POS);
            } else {
                WS_PROD_AP = "IB";
            }
        }
    }
    public void R000_CHECK_LOAN_TYPE() throws IOException,SQLException,Exception {
        if (AIB0001_AWA_0001.ENTRTYPE == 'G') {
            if (WS_PROD_AP.equalsIgnoreCase("DD") 
                || (WS_PROD_AP.equalsIgnoreCase("IB") 
                || WS_PROD_AP.equalsIgnoreCase("IL") 
                || WS_PROD_AP.equalsIgnoreCase("SW")) 
                || WS_PROD_AP.equalsIgnoreCase("MM")) {
                if (WS_LOAN_TYPE.trim().length() > 0) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_CAN_NOT_INPUT);
                }
            }
            if (WS_PROD_AP.equalsIgnoreCase("CL")) {
                if (WS_LOAN_TYPE.trim().length() == 0) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_MUST_INPUT);
                } else {
                    BPCOQPCD.INPUT_DATA.TYPE = "PORT";
                    BPCOQPCD.INPUT_DATA.CODE = WS_LOAN_TYPE;
                    IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
                    if (BPCOQPCD.RC.RC_CODE != 0) {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_INVALID);
                    }
                }
            }
            if (WS_PROD_AP.equalsIgnoreCase("FE")) {
                if (WS_LOAN_TYPE.trim().length() > 0) {
                    BPCOQPCD.INPUT_DATA.TYPE = "PORT";
                    BPCOQPCD.INPUT_DATA.CODE = WS_LOAN_TYPE;
                    IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
                    if (BPCOQPCD.RC.RC_CODE != 0) {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_INVALID);
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], AIB0001_AWA_0001.ENTRTYPE_NO);
        }
    }
    public void S00_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_CUR_POS = AIB0001_AWA_0001.AC_NO;
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_DDZICKCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CR-DR-CHK", DDCICKCD);
        WS_CUR_POS = AIB0001_AWA_0001.AC_NO;
        WS_CUR_POS -= 1;
        for (WS_I = 1; WS_I <= DDCICKCD.OUTPUT_DATA.RTN_CNT; WS_I += 1) {
            if (DDCICKCD.OUTPUT_DATA.RC[WS_I-1].RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCICKCD.OUTPUT_DATA.RC[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DD_RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DD_RC);
                CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
            }
        }
        for (WS_I = 1; WS_I <= 30; WS_I += 1) {
            if (SCCGMSG.MSG_AREA.MSG_CODE[WS_I-1].MSG_TYPE == 'A') {
                SCCGMSG.MSG_AREA.MSG_CODE[WS_I-1].MSG_TYPE = 'W';
            }
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'A') {
            SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE = 'N';
        }
    }
    public void S00_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
    }
    public void S00_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
        if (AICUPRVS.RC.RC_CODE != 0) {
            WS_CUR_POS -= 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUPRVS.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
