package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCNEV {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZCNEV_FILLER1 = ' ';
    int WS_I = 0;
    double WS_AMT = 0;
    short WS_NO = 0;
    double WS_NOR_P_AMT = 0;
    double WS_OVD_P_AMT = 0;
    double WS_NOR_I_AMT = 0;
    double WS_OVD_I_AMT = 0;
    double WS_BAL_OVERINT = 0;
    char WS_AS_IND = ' ';
    LNZCNEV_WS_CNTR_KEY WS_CNTR_KEY = new LNZCNEV_WS_CNTR_KEY();
    LNZCNEV_WS_CNTL_ID WS_CNTL_ID = new LNZCNEV_WS_CNTL_ID();
    String WS_AC_MODEL = " ";
    String WS_EVENT_CODE = "        ";
    char WS_EWA_IND = ' ';
    short WS_P_NO = 0;
    short WS_I_NO = 0;
    short WS_O_NO = 0;
    short WS_L_NO = 0;
    short WS_F_NO = 0;
    LNZCNEV_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZCNEV_WS_LOAN_CONT_AREA();
    String WS_CONTRACT_TYPE = " ";
    String WS_CI_NO = " ";
    double WS_TOT_AMT = 0;
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_OI_AMT = 0;
    double WS_OO_AMT = 0;
    double WS_OL_AMT = 0;
    double WS_TTT_AMT = 0;
    double WS_TTTR_AMT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_CLDD_FOUND = ' ';
    char WS_OLD_CLEAR_FLG = ' ';
    LNRBALD LNRBALD = new LNRBALD();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCCNTRB LNCCNTRB = new LNCCNTRB();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCUSTS LNCUSTS = new LNCUSTS();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    LNCRBALD LNCRBALD = new LNCRBALD();
    CICMACR CICMACR = new CICMACR();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    CIRBAS CIRBAS = new CIRBAS();
    CICACCU CICACCU = new CICACCU();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCGWA SCCGWA;
    LNCCNEV LNCCNEV;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCCNEV LNCCNEV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCNEV = LNCCNEV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCNEV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCCNEV.RC.RC_APP = "LN";
        LNCCNEV.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.ACM_EVENT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.P_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.I_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[4-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[5-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[4-1].FEE_AMT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        if (WS_EWA_IND == 'Y') {
            if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                B100_SET_EWA_BASIC_INF();
                if (pgmRtn) return;
            }
            B300_CGB_GENVCY_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCCNEV.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_GET_LOAN_INF();
        if (pgmRtn) return;
        B020_GET_ICTL_INF();
        if (pgmRtn) return;
        B030_GET_APRD_INF();
        if (pgmRtn) return;
    }
    public void B010_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        CEP.TRC(SCCGWA, "666666666666");
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.LN_AC);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        WS_CONTRACT_TYPE = LNCCONTM.REC_DATA.CONTRACT_TYPE;
        CEP.TRC(SCCGWA, WS_CONTRACT_TYPE);
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        if (LNCCNEV.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNEV.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        WS_OLD_CLEAR_FLG = LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
    }
    public void B030_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B100_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[1-1] = WS_CONTRACT_TYPE;
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[2-1] = "CLCM";
        CEP.TRC(SCCGWA, GWA_BP_AREA.EWA_AREA.EWA_MAX_CNT);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_GET_LOANS_BAL();
        if (pgmRtn) return;
        B210_GET_CNTR_INF();
        if (pgmRtn) return;
        B215_EVENT_AMT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].FEE_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[4-1].FEE_AMT);
        B220_LN_EVENT_PROC();
        if (pgmRtn) return;
        B230_LOAN_STS_PROC();
        if (pgmRtn) return;
    }
    public void B210_GET_CNTR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTRB);
        LNCCNTRB.PTYP = "CNTR";
        WS_CNTR_KEY.WS_CNTR_CODE = LNCCNEV.COMM_DATA.EVENT_CODE;
        WS_CNTR_KEY.WS_CNTR_SEQ = 0;
        LNCCNTRB.CODE = IBS.CLS2CPY(SCCGWA, WS_CNTR_KEY);
        S000_CALL_LNZCNTRB();
        if (pgmRtn) return;
        WS_EWA_IND = LNCCNTRB.REC_DATAS.REC_TXT[1-1].DATA_TXT.EWA_IND;
        WS_EVENT_CODE = LNCCNEV.COMM_DATA.EVENT_CODE;
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[1-1].DATA_TXT.INCD_FUNC);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[1-1].DATA_TXT.FROM_ID);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[1-1].DATA_TXT.TO_ID);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[2-1].DATA_TXT.INCD_FUNC);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[2-1].DATA_TXT.FROM_ID);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[2-1].DATA_TXT.TO_ID);
    }
    public void B220_LN_EVENT_PROC() throws IOException,SQLException,Exception {
        WS_P_NO = 1;
        WS_I_NO = 1;
        WS_O_NO = 1;
        WS_L_NO = 1;
        WS_F_NO = 1;
        for (WS_I = 1; WS_I <= LNCCNTRB.REC_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID);
            CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID);
            CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT);
            if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.INCD_FUNC != 'N') {
                B210_1_EVENT_PROC();
                if (pgmRtn) return;
            }
            if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.INCD_FUNC == 'N') {
                if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'P' 
                    || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'P') {
                    WS_P_NO += 1;
                }
                if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'I' 
                    || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'I') {
                    WS_I_NO += 1;
                }
                if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'O' 
                    || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'O') {
                    WS_O_NO += 1;
                }
                if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'L' 
                    || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'L') {
                    WS_L_NO += 1;
                }
                if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'F' 
                    || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'F') {
                    WS_F_NO += 1;
                }
            }
        }
    }
    public void B210_1_EVENT_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CNTL_ID);
        if (WS_CNTL_ID.WS_CNTL_TYP != ' ') {
            WS_AS_IND = 'S';
            BS01_AMT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_AMT);
            if (WS_AMT != 0) {
                B210_CNTL_BALUPD();
                if (pgmRtn) return;
            }
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CNTL_ID);
        CEP.TRC(SCCGWA, WS_CNTL_ID);
        if (WS_CNTL_ID.WS_CNTL_TYP != ' ') {
            WS_AS_IND = 'A';
            BS01_AMT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_AMT);
            if (WS_AMT != 0) {
                B210_CNTL_BALUPD();
                if (pgmRtn) return;
            }
        }
        if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'P' 
            || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'P') {
            WS_P_NO += 1;
        }
        if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'I' 
            || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'I') {
            WS_I_NO += 1;
        }
        if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'O' 
            || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'O') {
            WS_O_NO += 1;
        }
        if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'L' 
            || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'L') {
            WS_L_NO += 1;
        }
        if (LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID.FROM_TYP == 'F' 
            || LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID.TO_TYP == 'F') {
            WS_F_NO += 1;
        }
    }
    public void BS01_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNTL_ID.WS_CNTL_TYP);
        if (WS_CNTL_ID.WS_CNTL_TYP == 'P') {
            WS_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[WS_P_NO-1].PRI_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'I') {
            WS_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[WS_I_NO-1].INT_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'O') {
            WS_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[WS_O_NO-1].OLC_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'L') {
            WS_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[WS_L_NO-1].LLC_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'F') {
            WS_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[WS_F_NO-1].FEE_AMT;
        }
    }
    public void B210_CNTL_BALUPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNBU);
        LNCCNBU.COMM_DATA.LN_AC = LNCCNEV.COMM_DATA.LN_AC;
        LNCCNBU.COMM_DATA.SUF_NO = LNCCNEV.COMM_DATA.SUF_NO;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CNTL_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
        LNCCNBU.COMM_DATA.VAL_DATE = LNCCNEV.COMM_DATA.VAL_DATE;
        LNCCNBU.COMM_DATA.AMT = WS_AMT;
        LNCCNBU.COMM_DATA.AS_IND = WS_AS_IND;
        LNCCNBU.COMM_DATA.RVS_IND = LNCCNEV.COMM_DATA.RVS_IND;
        if (LNCCNEV.COMM_DATA.RVS_IND == 'Y') {
            LNCCNBU.COMM_DATA.TXN_FLG = 'R';
        } else {
            LNCCNBU.COMM_DATA.TXN_FLG = 'N';
        }
        LNCCNBU.COMM_DATA.ACM_EVCD = LNCCNEV.COMM_DATA.ACM_EVENT;
        S000_CALL_LNZCNBU();
        if (pgmRtn) return;
    }
    public void B230_LOAN_STS_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP") 
            || JIBS_tmp_str[1].equalsIgnoreCase("0134000")) {
            IBS.init(SCCGWA, LNCUSTS);
            LNCUSTS.COMM_DATA.LN_AC = LNCCNEV.COMM_DATA.LN_AC;
            LNCUSTS.COMM_DATA.SUF_NO = LNCCNEV.COMM_DATA.SUF_NO;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0136020")) {
                LNCUSTS.COMM_DATA.RINT_TYP = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].RINT_TYP;
            }
            S000_CALL_LNZUSTS();
            if (pgmRtn) return;
        }
        B020_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B300_CGB_GENVCY_PROC() throws IOException,SQLException,Exception {
        LNCIGVCY.DATA.CNTR_TYPE = WS_CONTRACT_TYPE;
        LNCIGVCY.DATA.PROD_CODE_OLD = LNCCONTM.REC_DATA.PROD_CD;
        LNCIGVCY.DATA.CTA_NO = LNCCNEV.COMM_DATA.LN_AC;
        if (LNCCNEV.COMM_DATA.SUF_NO.trim().length() == 0) LNCIGVCY.DATA.SUB_CTA_NO = 0;
        else LNCIGVCY.DATA.SUB_CTA_NO = Integer.parseInt(LNCCNEV.COMM_DATA.SUF_NO);
        LNCIGVCY.DATA.EVENT_CODE = LNCCNEV.COMM_DATA.ACM_EVENT;
        LNCIGVCY.DATA.BR_OLD = LNCCONTM.REC_DATA.BOOK_BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCCONTM.REC_DATA.CCY;
        LNCIGVCY.DATA.VALUE_DATE = LNCCNEV.COMM_DATA.VAL_DATE;
        LNCIGVCY.DATA.CI_NO = WS_CI_NO;
        LNCIGVCY.DATA.STATUS = LNCICTLM.REC_DATA.CTL_STSW;
        LNCIGVCY.DATA.DEP_AC = LNCCNEV.DEP_AC;
        CEP.TRC(SCCGWA, "CGB-GENVCH-PROC");
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.PROD_CODE_OLD);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BR_OLD);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.STATUS);
        LNCIGVCY.DATA.EFF_DAYS = 0;
        B310_GVCY_AMT_PROC();
        if (pgmRtn) return;
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void B310_GVCY_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW);
        if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("DRAWDWN")) {
            if (LNCLOANM.REC_DATA.CTL_TYPE != ' ') {
                LNCIGVCY.DATA.AMT_INFO[5-1].AMT = LNCCNEV.COMM_DATA.P_AMT;
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[2-1].AMT = LNCCNEV.COMM_DATA.P_AMT;
                } else {
                    LNCIGVCY.DATA.AMT_INFO[1-1].AMT = LNCCNEV.COMM_DATA.P_AMT;
                    LNCIGVCY.DATA.AMT_INFO[19-1].AMT = LNCCNEV.COMM_DATA.I_AMT;
                }
            }
            LNCIGVCY.DATA.AMT_INFO[72-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT;
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYPR")) {
            LNCIGVCY.DATA.AMT_INFO[3-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
            LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].FEE_AMT);
            CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[1-1].AMT);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[3-1].AMT = 0;
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT = 0;
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[61-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[3-1].AMT = 0;
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[17-1].AMT = LNCCNEV.COMM_DATA.TX_LLC_AMT;
                LNCIGVCY.DATA.AMT_INFO[17-1].AMT += LNCCNEV.COMM_DATA.TX_I_AMT;
                LNCIGVCY.DATA.AMT_INFO[17-1].AMT += LNCCNEV.COMM_DATA.TX_OLC_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("SOLDBDL")
            || LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("SOLDBDD")) {
            LNCIGVCY.DATA.AMT_INFO[3-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
            LNCIGVCY.DATA.AMT_INFO[20-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
            LNCIGVCY.DATA.AMT_INFO[60-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
            LNCIGVCY.DATA.AMT_INFO[61-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[4-1].INT_AMT;
            LNCIGVCY.DATA.AMT_INFO[62-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[5-1].INT_AMT;
            LNCIGVCY.DATA.AMT_INFO[70-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT;
            LNCIGVCY.DATA.AMT_INFO[71-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].FEE_AMT;
            LNCIGVCY.DATA.AMT_INFO[72-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[3-1].FEE_AMT;
            LNCIGVCY.DATA.AMT_INFO[63-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[4-1].FEE_AMT;
            LNCIGVCY.DATA.AMT_INFO[64-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[5-1].FEE_AMT;
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("RVPYINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT = WS_NOR_P_AMT;
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT += WS_OVD_P_AMT;
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT = WS_NOR_I_AMT;
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[23-1].AMT);
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += WS_OVD_I_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[23-1].AMT);
            } else {
                LNCIGVCY.DATA.AMT_INFO[3-1].AMT = WS_NOR_P_AMT;
                LNCIGVCY.DATA.AMT_INFO[7-1].AMT = WS_OVD_P_AMT;
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[7-1].AMT = WS_NOR_P_AMT + WS_OVD_P_AMT;
                    LNCIGVCY.DATA.AMT_INFO[3-1].AMT = 0;
                }
                CEP.TRC(SCCGWA, WS_NOR_I_AMT);
                CEP.TRC(SCCGWA, WS_OVD_I_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.NX_I_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.NX_OLC_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.NX_LLC_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.TX_LLC_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.TX_I_AMT);
                CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.TX_OLC_AMT);
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
                    LNCIGVCY.DATA.AMT_INFO[13-1].AMT = WS_NOR_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[13-1].AMT += WS_OVD_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("0")) {
                        LNCIGVCY.DATA.AMT_INFO[21-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                    } else {
                        LNCIGVCY.DATA.AMT_INFO[14-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                    }
                } else {
                    LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCCNEV.COMM_DATA.NX_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCCNEV.COMM_DATA.NX_OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[21-1].AMT = LNCCNEV.COMM_DATA.NX_LLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[17-1].AMT = LNCCNEV.COMM_DATA.TX_LLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[17-1].AMT += LNCCNEV.COMM_DATA.TX_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[17-1].AMT += LNCCNEV.COMM_DATA.TX_OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[16-1].AMT = LNCCNEV.COMM_DATA.TX_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[18-1].AMT = LNCCNEV.COMM_DATA.TX_OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[19-1].AMT = LNCCNEV.COMM_DATA.TX_LLC_AMT;
                }
                CEP.TRC(SCCGWA, WS_OI_AMT);
                CEP.TRC(SCCGWA, WS_OO_AMT);
                CEP.TRC(SCCGWA, WS_OL_AMT);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[17-1].AMT);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[13-1].AMT);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[15-1].AMT);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[21-1].AMT);
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[61-1].AMT = LNCIGVCY.DATA.AMT_INFO[3-1].AMT;
                    LNCIGVCY.DATA.AMT_INFO[61-1].AMT += LNCIGVCY.DATA.AMT_INFO[7-1].AMT;
                    LNCIGVCY.DATA.AMT_INFO[3-1].AMT = 0;
                    LNCIGVCY.DATA.AMT_INFO[7-1].AMT = 0;
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[14-1].AMT = WS_OVD_I_AMT + WS_NOR_I_AMT - LNCCNEV.COMM_DATA.TX_I_AMT;
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT - LNCCNEV.COMM_DATA.TX_OLC_AMT;
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("WAIVINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT = LNCCNEV.COMM_DATA.I_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.O_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.L_AMT;
            } else {
                LNCIGVCY.DATA.AMT_INFO[25-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[26-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[28-1].AMT = LNCCNEV.COMM_DATA.O_AMT;
                LNCIGVCY.DATA.AMT_INFO[32-1].AMT = LNCCNEV.COMM_DATA.L_AMT;
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT = LNCCNEV.COMM_DATA.NX_I_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.NX_OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.NX_LLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[17-1].AMT = LNCCNEV.COMM_DATA.TX_LLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[26-1].AMT = 0;
                    LNCIGVCY.DATA.AMT_INFO[28-1].AMT = 0;
                    LNCIGVCY.DATA.AMT_INFO[32-1].AMT = 0;
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("KOPPRIN")) {
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[2-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
            } else {
            }
            CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[4-1].AMT);
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("CANCINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                } else {
                    LNCIGVCY.DATA.AMT_INFO[26-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[26-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[26-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[28-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("PATCINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                } else {
                    LNCIGVCY.DATA.AMT_INFO[25-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[25-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[25-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[27-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("WRITOFF")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[1-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[5-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[14-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                LNCIGVCY.DATA.AMT_INFO[17-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("POSTINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[65-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[65-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[65-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCICTLM.REC_DATA.CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[23-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[23-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                }
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[41-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[43-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[51-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                } else {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                        LNCIGVCY.DATA.AMT_INFO[45-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                        LNCIGVCY.DATA.AMT_INFO[45-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                        LNCIGVCY.DATA.AMT_INFO[45-1].AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                        IBS.init(SCCGWA, LNCBALLM);
                        LNCBALLM.FUNC = '4';
                        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
                        if (LNCCNEV.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
                        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNEV.COMM_DATA.SUF_NO);
                        S000_CALL_LNZBALLM();
                        if (pgmRtn) return;
                        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[24-1].LOAN_BAL += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[48-1].LOAN_BAL += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[58-1].LOAN_BAL += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                        LNCBALLM.FUNC = '2';
                        S000_CALL_LNZBALLM();
                        if (pgmRtn) return;
                    } else {
                        LNCIGVCY.DATA.AMT_INFO[41-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                        LNCIGVCY.DATA.AMT_INFO[43-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                        LNCIGVCY.DATA.AMT_INFO[51-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                    }
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                    LNCIGVCY.DATA.AMT_INFO[41-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    LNCIGVCY.DATA.AMT_INFO[43-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT;
                    LNCIGVCY.DATA.AMT_INFO[51-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT;
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("AMOTINT")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[53-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                LNCIGVCY.DATA.AMT_INFO[54-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("OVDNORP")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[5-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
                LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("DUEOVDP")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                || (LNCLOANM.REC_DATA.CTL_TYPE != ' ' 
                && LNCCONTM.REC_DATA.TRAN_DTE == SCCGWA.COMM_AREA.AC_DATE 
                && LNCCONTM.REC_DATA.TRAN_JRN == SCCGWA.COMM_AREA.JRN_NO) 
                || (LNCLOANM.REC_DATA.CTL_TYPE != ' ' 
                && JIBS_tmp_str[1].equalsIgnoreCase("0351200"))) {
            } else {
                if (LNCLOANM.REC_DATA.CTL_TYPE == ' ') {
                    if (LNCCNEV.COMM_DATA.ACM_EVENT.equalsIgnoreCase("NO")) {
                        LNCIGVCY.DATA.AMT_INFO[1-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                        LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    } else {
                        LNCIGVCY.DATA.AMT_INFO[5-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
                    }
                }
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("TONOACR")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[42-1].AMT = LNCCNEV.COMM_DATA.I_AMT;
                LNCIGVCY.DATA.AMT_INFO[44-1].AMT = LNCCNEV.COMM_DATA.O_AMT;
                LNCIGVCY.DATA.AMT_INFO[52-1].AMT = LNCCNEV.COMM_DATA.L_AMT;
            }
        } else if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("INTCALR")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            } else {
                LNCIGVCY.DATA.AMT_INFO[5-1].AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
            }
        } else {
        }
    }
    public void B201_GET_LOANS_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        if (LNCCNEV.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNEV.COMM_DATA.SUF_NO);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        IBS.init(SCCGWA, LNCIGVCY);
        LNCIGVCY.DATA.BAL_NORMAL = WS_LOAN_CONT_AREA.REDEFINES29.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
        LNCIGVCY.DATA.BAL_NORDUE = WS_LOAN_CONT_AREA.REDEFINES29.WS_LOAN_CONT[5-1].WS_LOAN_BAL;
        LNCIGVCY.DATA.BAL_OVERDUE_MANUAL = WS_LOAN_CONT_AREA.REDEFINES29.WS_LOAN_CONT[3-1].WS_LOAN_BAL;
        LNCIGVCY.DATA.BAL_OVERDUE = WS_LOAN_CONT_AREA.REDEFINES29.WS_LOAN_CONT[6-1].WS_LOAN_BAL;
        WS_BAL_OVERINT = WS_LOAN_CONT_AREA.REDEFINES29.WS_LOAN_CONT[22-1].WS_LOAN_BAL;
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BAL_NORDUE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BAL_OVERDUE_MANUAL);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BAL_OVERDUE);
        if (!LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("POSTINT")) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = LNCCNEV.COMM_DATA.LN_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            WS_CI_NO = CICACCU.DATA.CI_NO;
        }
    }
    public void B215_EVENT_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYPR") 
            && LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT != 0) {
            LNCCNEV.COMM_DATA.IETM_AMTS[4-1].INT_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
        }
        if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM") 
            && LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT != 0) {
            LNCCNEV.COMM_DATA.IETM_AMTS[4-1].INT_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT;
        }
        if ((LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("SOLDBDL") 
            || LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("SOLDBDD")) 
            && LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT != 0) {
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
        }
        if (LNCCNEV.COMM_DATA.RVS_IND == 'N') {
            if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM")) {
                B215_REPAYNM_AMT_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM") 
                || LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("DRAWDWN")) {
                if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM")) {
                    LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = 0;
                    LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = 0;
                    LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = 0;
                    LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = 0;
                }
                B215_GET_REV_AMT_PROC();
                if (pgmRtn) return;
                if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("DRAWDWN")) {
                    LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                }
                if (LNCCNEV.COMM_DATA.EVENT_CODE.equalsIgnoreCase("REPAYNM")) {
                    WS_NOR_P_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
                    WS_OVD_P_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
                    WS_NOR_I_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
                    WS_OVD_I_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                }
            }
        }
    }
    public void B215_REPAYNM_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCCNEV.COMM_DATA.P_AMT < LNCIGVCY.DATA.BAL_OVERDUE) {
            WS_NOR_P_AMT = 0;
            WS_OVD_P_AMT = LNCCNEV.COMM_DATA.P_AMT;
        } else {
            if (LNCIGVCY.DATA.BAL_OVERDUE > 0) {
                WS_NOR_P_AMT = LNCCNEV.COMM_DATA.P_AMT - LNCIGVCY.DATA.BAL_OVERDUE;
                WS_OVD_P_AMT = LNCIGVCY.DATA.BAL_OVERDUE;
            } else {
                WS_NOR_P_AMT = LNCCNEV.COMM_DATA.P_AMT;
                WS_OVD_P_AMT = 0;
            }
        }
        if (LNCCNEV.COMM_DATA.I_AMT < WS_BAL_OVERINT) {
            WS_NOR_I_AMT = 0;
            WS_OVD_I_AMT = LNCCNEV.COMM_DATA.I_AMT;
        } else {
            if (WS_BAL_OVERINT > 0) {
                WS_NOR_I_AMT = LNCCNEV.COMM_DATA.I_AMT - WS_BAL_OVERINT;
                WS_OVD_I_AMT = WS_BAL_OVERINT;
            } else {
                WS_NOR_I_AMT = LNCCNEV.COMM_DATA.I_AMT;
                WS_OVD_I_AMT = 0;
            }
        }
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_NOR_P_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_OVD_P_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_NOR_I_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_OVD_I_AMT;
    }
    public void B215_GET_REV_AMT_PROC() throws IOException,SQLException,Exception {
        WS_P_NO = 1;
        WS_I_NO = 1;
        WS_O_NO = 1;
        WS_L_NO = 1;
        WS_F_NO = 1;
        for (WS_I = 1; WS_I <= LNCCNTRB.REC_CNT; WS_I += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.FROM_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CNTL_ID);
            if (WS_CNTL_ID.WS_CNTL_TYP != ' ') {
                BS04_GET_CNTL_INF();
                if (pgmRtn) return;
                if (LNCCNTLM.DATA_TXT.HIST_FLG == 'Y') {
                    BS02_GET_REVAMT_PROC();
                    if (pgmRtn) return;
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WS_I-1].DATA_TXT.TO_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CNTL_ID);
            if (WS_CNTL_ID.WS_CNTL_TYP != ' ') {
                BS04_GET_CNTL_INF();
                if (pgmRtn) return;
                if (LNCCNTLM.DATA_TXT.HIST_FLG == 'Y') {
                    BS02_GET_REVAMT_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CNTL_ID.WS_CNTL_TYP == 'P') {
                WS_P_NO += 1;
            }
            if (WS_CNTL_ID.WS_CNTL_TYP == 'I') {
                WS_I_NO += 1;
            }
            if (WS_CNTL_ID.WS_CNTL_TYP == 'O') {
                WS_O_NO += 1;
            }
            if (WS_CNTL_ID.WS_CNTL_TYP == 'L') {
                WS_L_NO += 1;
            }
            if (WS_CNTL_ID.WS_CNTL_TYP == 'F') {
                WS_F_NO += 1;
            }
        }
    }
    public void BS02_GET_REVAMT_PROC() throws IOException,SQLException,Exception {
        BS03_GET_CNTL_AMT();
        if (pgmRtn) return;
        if (WS_AMT != 0) {
            BS03_REP_CNEV_AMT();
            if (pgmRtn) return;
        }
    }
    public void BS03_REP_CNEV_AMT() throws IOException,SQLException,Exception {
        if (WS_CNTL_ID.WS_CNTL_TYP == 'P') {
            LNCCNEV.COMM_DATA.IETM_AMTS[WS_P_NO-1].PRI_AMT = WS_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'I') {
            LNCCNEV.COMM_DATA.IETM_AMTS[WS_I_NO-1].INT_AMT = WS_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'O') {
            LNCCNEV.COMM_DATA.IETM_AMTS[WS_O_NO-1].OLC_AMT = WS_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'L') {
            LNCCNEV.COMM_DATA.IETM_AMTS[WS_L_NO-1].LLC_AMT = WS_AMT;
        } else if (WS_CNTL_ID.WS_CNTL_TYP == 'F') {
            LNCCNEV.COMM_DATA.IETM_AMTS[WS_F_NO-1].FEE_AMT = WS_AMT;
        }
    }
    public void BS03_GET_CNTL_AMT() throws IOException,SQLException,Exception {
        BS05_READ_LNTBALD();
        if (pgmRtn) return;
    }
    public void BS04_GET_CNTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTLM);
        LNCCNTLM.FUNC = 'I';
        LNCCNTLM.KEY.TYP = "CNTL";
        LNCCNTLM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CNTL_ID);
        S000_CALL_LNZCNTLM();
        if (pgmRtn) return;
        WS_NO = LNCCNTLM.DATA_TXT.NO;
    }
    public void BS05_READ_LNTBALD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALD);
        IBS.init(SCCGWA, LNCRBALD);
        LNCRBALD.FUNC = 'R';
        LNRBALD.KEY.CONTRACT_NO = LNCCNEV.COMM_DATA.LN_AC;
        if (LNCCNEV.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALD.KEY.SUB_CTA_NO = 0;
        else LNRBALD.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNEV.COMM_DATA.SUF_NO);
        LNRBALD.KEY.CTNR_NO = WS_NO;
        LNRBALD.KEY.TXN_AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRBALD.KEY.JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRBALD.KEY.TXN_VAL_DT = LNCCNEV.COMM_DATA.VAL_DATE;
        LNCRBALD.REC_PTR = LNRBALD;
        LNCRBALD.REC_LEN = 175;
        S000_CALL_LNZRBALD();
        if (pgmRtn) return;
        if (LNCRBALD.RETURN_INFO == 'F') {
            WS_AMT = LNRBALD.AMT;
            LNCRBALD.FUNC = 'U';
            LNRBALD.TXN_FLG = 'Y';
            LNRBALD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRBALD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNCRBALD.REC_PTR = LNRBALD;
            LNCRBALD.REC_LEN = 175;
            S000_CALL_LNZRBALD();
            if (pgmRtn) return;
        } else {
            WS_AMT = 0;
        }
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTL-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNTRB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTR-BRWQ", LNCCNTRB);
        if (LNCCNTRB.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTRB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCCNEV.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCCNEV.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            LNCCNEV.RC.RC_APP = BPCPEBAS.RC.RC_MMO;
            LNCCNEV.RC.RC_RTNCODE = BPCPEBAS.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        LNCIGVCY.DATA.ACCEPT_FLG = 'Y';
        LNCIGVCY.DATA.ACCEPT_DATA.APRD_DATA.APRD_ACCRUAL_TYPE = LNCAPRDM.REC_DATA.ACCRUAL_TYPE;
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRBALD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTBALD", LNCRBALD);
        CEP.TRC(SCCGWA, LNCRBALD.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRBALD.RC);
    }
    public void S000_CALL_LNZUSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-OVDSTS-UPD", LNCUSTS);
        CEP.TRC(SCCGWA, LNCUSTS.RC.RC_RTNCODE);
        if (LNCUSTS.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUSTS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCCNEV=");
            CEP.TRC(SCCGWA, LNCCNEV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
