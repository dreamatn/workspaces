package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP14 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_AI_P_INQ_ITM = "AI-P-INQ-ITM        ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    String CPN_BP_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_BP_P_QUERY_BKAI = "BP-P-QUERY-BKAI     ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String ITM_NO_01 = "16309000";
    String ITM_NO_02 = "16309099";
    String ITM_NO_03 = "23509000";
    String ITM_NO_04 = "23509099";
    String WS_ERR_MSG = " ";
    short WS_CUR_POS = 0;
    short WS_I = 0;
    AIOIP14_WS_T_CTL WS_T_CTL = new AIOIP14_WS_T_CTL();
    int WS_1_DATE = 0;
    AIOIP14_REDEFINES9 REDEFINES9 = new AIOIP14_REDEFINES9();
    int WS_2_DATE = 0;
    AIOIP14_REDEFINES13 REDEFINES13 = new AIOIP14_REDEFINES13();
    String WS_PROD_CD = " ";
    String WS_PROD_NM = " ";
    String WS_LOAN_TYPE = " ";
    String WS_ITM = " ";
    AIOIP14_WS_DD_RC WS_DD_RC = new AIOIP14_WS_DD_RC();
    String WS_VIL_TYP = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCEX BPCEX = new BPCEX();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCICKCD DDCICKCD = new DDCICKCD();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUPRVS AICUPRVS = new AICUPRVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    AIB1780_AWA_1780 AIB1780_AWA_1780;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP14 return!");
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
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB1780_AWA_1780>");
        AIB1780_AWA_1780 = (AIB1780_AWA_1780) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.TEMPNO);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.TMPNM);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.ENTR);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.GLBOOK);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.VALDAT);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.ENTRTYPE);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.BR);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.BR_NO);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.CUR);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.ITM);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.AC_SEQ1);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.AC);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.CIF);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.ACTNCODE);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.AMT);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.RB_FLG);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.REV_NO);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.NARRCD);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.TRNDESC);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.REF);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.PORTCD);
        CEP.TRC(SCCGWA, AIB1780_AWA_1780.TMP_FLG);
        if (AIB1780_AWA_1780.BR_NO != 0) {
            if (AIB1780_AWA_1780.BR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = AIB1780_AWA_1780.BR;
                WS_CUR_POS = AIB1780_AWA_1780.BR_NO;
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
            } else {
                if (AIB1780_AWA_1780.ENTRTYPE == 'G') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT, AIB1780_AWA_1780.BR_NO);
                }
            }
        }
        if (AIB1780_AWA_1780.ENTRTYPE_NO != 0) {
            if (AIB1780_AWA_1780.ENTRTYPE == 'D') {
                if (AIB1780_AWA_1780.AC.compareTo(0) > 0) {
                    IBS.init(SCCGWA, DDCICKCD);
                    if (AIB1780_AWA_1780.ACTNCODE == 'C') {
                        DDCICKCD.INPUT_DATA.FUNC = '1';
                    } else {
                        DDCICKCD.INPUT_DATA.FUNC = '2';
                    }
                    DDCICKCD.INPUT_DATA.AC_NO = AIB1780_AWA_1780.AC;
                    DDCICKCD.INPUT_DATA.CCY = AIB1780_AWA_1780.CUR;
                    DDCICKCD.INPUT_DATA.TX_AMT = AIB1780_AWA_1780.AMT;
                    S00_CALL_DDZICKCD();
                }
            }
            if (AIB1780_AWA_1780.ENTRTYPE == 'N') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.NOSTRO_CD = AIB1780_AWA_1780.AC;
                IBCQINF.INPUT_DATA.CCY = AIB1780_AWA_1780.CUR;
                S00_CALL_IBZQINF();
            }
            if (AIB1780_AWA_1780.ENTRTYPE == 'I') {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.GL_BOOK = AIB1780_AWA_1780.GLBOOK;
                AICPQMIB.INPUT_DATA.AC = AIB1780_AWA_1780.AC;
                AICPQMIB.INPUT_DATA.CCY = AIB1780_AWA_1780.CUR;
                AICPQMIB.INPUT_DATA.TX_FLG = 'O';
                CEP.TRC(SCCGWA, "TEST11111");
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.BR);
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.CUR);
                S00_CALL_AIZPQMIB();
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.AMT_DIR);
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.ACTNCODE);
                if (AICPQMIB.OUTPUT_DATA.AMT_DIR != 'B' 
                    && AICPQMIB.OUTPUT_DATA.AMT_DIR != AIB1780_AWA_1780.ACTNCODE) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DIR_NOT_ALLOW);
                }
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_TYP);
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.RVS_FLG);
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.RVS_FLG);
                if (AIB1780_AWA_1780.RVS_FLG == '1') {
                    CEP.TRC(SCCGWA, "IS 111111");
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP != AIB1780_AWA_1780.ACTNCODE 
                        && AIB1780_AWA_1780.RB_FLG == 'B') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP);
                    }
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP == AIB1780_AWA_1780.ACTNCODE 
                        && AIB1780_AWA_1780.RB_FLG == 'R') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP2_FOR_RED);
                    }
                }
                if (AIB1780_AWA_1780.RVS_FLG == '2') {
                    CEP.TRC(SCCGWA, "IS 111111");
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP == AIB1780_AWA_1780.ACTNCODE 
                        && AIB1780_AWA_1780.RB_FLG == 'B') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_EQ_RVS_TYP);
                    }
                    if (AICPQMIB.OUTPUT_DATA.RVS_TYP != AIB1780_AWA_1780.ACTNCODE 
                        && AIB1780_AWA_1780.RB_FLG == 'R') {
                        CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP1_FOR_RED);
                    }
                }
                if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG == 'N') {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_CANT_USE_ODE);
                }
                AIB1780_AWA_1780.BR = AICPQMIB.INPUT_DATA.BR;
                AIB1780_AWA_1780.ITM = AICPQMIB.INPUT_DATA.ITM_NO;
                IBS.init(SCCGWA, AICUPRVS);
                AICUPRVS.DATA.FUNC = 'C';
                AICUPRVS.DATA.GL_BOOK = AIB1780_AWA_1780.GLBOOK;
                AICUPRVS.DATA.AC = AIB1780_AWA_1780.AC;
                AICUPRVS.DATA.CCY = AIB1780_AWA_1780.CUR;
                AICUPRVS.DATA.BR = AIB1780_AWA_1780.BR;
                AICUPRVS.DATA.SIGN = AIB1780_AWA_1780.ACTNCODE;
                AICUPRVS.DATA.AMT = AIB1780_AWA_1780.AMT;
                AICUPRVS.DATA.RED_FLG = AIB1780_AWA_1780.RB_FLG;
                AICUPRVS.DATA.RVS_NO = AIB1780_AWA_1780.REV_NO;
                S00_CALL_AIZUPRVS();
            }
            if (AIB1780_AWA_1780.ENTRTYPE == 'G') {
                if (AIB1780_AWA_1780.ITM.trim().length() == 0) {
                    CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_MUST_INPUT);
                }
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.NO = AIB1780_AWA_1780.ITM;
                AICPQITM.INPUT_DATA.BOOK_FLG = AIB1780_AWA_1780.GLBOOK;
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
            }
        }
        if (AIB1780_AWA_1780.RB_FLG == ' ') {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT, AIB1780_AWA_1780.RB_FLG_NO);
        }
        if (AIB1780_AWA_1780.RB_FLG == 'R' 
            && AIB1780_AWA_1780.AMT > 0) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.RED_FLG_AMT_NEG, AIB1780_AWA_1780.AMT_NO);
        }
        if (AIB1780_AWA_1780.RB_FLG != 'R' 
            && AIB1780_AWA_1780.AMT < 0) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.N_RED_FLG_AMT_POSTIVE, AIB1780_AWA_1780.AMT_NO);
        }
        if (AIB1780_AWA_1780.NARRCD_NO != 0) {
            if (AIB1780_AWA_1780.NARRCD.trim().length() > 0) {
                CEP.TRC(SCCGWA, AIB1780_AWA_1780.NARRCD);
                IBS.init(SCCGWA, BPCOQPCD);
                BPCOQPCD.INPUT_DATA.TYPE = "MMO";
                BPCOQPCD.INPUT_DATA.CODE = AIB1780_AWA_1780.NARRCD;
                WS_CUR_POS = AIB1780_AWA_1780.NARRCD_NO;
                S000_CALL_BPZPQPCD();
                WS_PROD_NM = BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_NAME;
            }
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE == 0) {
            BPCOQPCD.INPUT_DATA.TYPE = "PROD";
            BPCOQPCD.INPUT_DATA.CODE = WS_PROD_CD;
            IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
            if (BPCOQPCD.RC.RC_CODE != 0) {
                CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.TYPE_NOT_EXIST, WS_PROD_CD);
            }
        }
    }
    public void R000_CHECK_NARR_CD() throws IOException,SQLException,Exception {
        WS_CUR_POS = AIB1780_AWA_1780.NARRCD_NO;
        if ((WS_ITM.compareTo(ITM_NO_01) >= 0 
            && WS_ITM.compareTo(ITM_NO_02) <= 0) 
            || (WS_ITM.compareTo(ITM_NO_03) >= 0 
            && WS_ITM.compareTo(ITM_NO_04) <= 0)) {
            if (AIB1780_AWA_1780.NARRCD.trim().length() == 0) {
                CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.CD_MUST_INPUT, WS_CUR_POS);
            }
        } else {
            if (AIB1780_AWA_1780.NARRCD.trim().length() > 0) {
                CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.CD_CAN_NOT_INPUT, WS_CUR_POS);
            }
        }
    }
    public void S00_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], AIB1780_AWA_1780.AC_NO);
        }
    }
    public void S00_CALL_DDZICKCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CR-DR-CHK", DDCICKCD);
        for (WS_I = 1; WS_I <= DDCICKCD.OUTPUT_DATA.RTN_CNT; WS_I += 1) {
            if (DDCICKCD.OUTPUT_DATA.RC[WS_I-1].RC_CODE != 0) {
                if (DDCICKCD.OUTPUT_DATA.RC[WS_I-1].RC_CODE != 1452) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCICKCD.OUTPUT_DATA.RC[WS_I-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DD_RC);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DD_RC);
                    CEP.ERRC(SCCGWA, JIBS_tmp_str[0], AIB1780_AWA_1780.AC_NO);
                }
            }
        }
    }
    public void S00_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
        if (AICUPRVS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUPRVS.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
