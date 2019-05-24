package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSUCBI {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCRDLT_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC110";
    String K_HIS_COPYBOOK = "DCRCDDAT";
    String WS_ERR_MSG = " ";
    int WS_CARD_NUM = 0;
    char WS_OLDCARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCO3110 DCCO3110 = new DCCO3110();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    int WS_TOT_SUB_CARD_CNT = 0;
    String WS_PRIM_CARD_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSUCBI DCCSUCBI;
    public void MP(SCCGWA SCCGWA, DCCSUCBI DCCSUCBI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSUCBI = DCCSUCBI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSUCBI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_UPDATE_CARD_INF_PROCESS();
        if (pgmRtn) return;
        B050_TRANS_DATA_OUTPUT_1();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.CARD_NO);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.ID_TYP);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.ID_NO);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.CI_NO);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.CI_NM);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.DRAW_OVER_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.HOLD_AC_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.PROD_LMT_FLG);
        if (DCCSUCBI.INP_DATA.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_CHECK_CARD_INF_PROC();
        if (pgmRtn) return;
        if (DCCSUCBI.INP_DATA.CI_NO.trim().length() == 0 
            && (DCCSUCBI.INP_DATA.ID_NO.trim().length() == 0 
            || DCCSUCBI.INP_DATA.ID_TYP.trim().length() == 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DCCSUCBI.INP_DATA.CI_NO.equalsIgnoreCase(DCRCDDAT.CARD_HLDR_CINO)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG != 'Y' 
                && DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG != 'C' 
                && DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG != 'N') {
                CEP.TRC(SCCGWA, "ͬ��ת�˿�ͨ��ʾ��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_SNAME_TRF_FLG_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG != 'Y' 
                && DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG != 'C' 
                && DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG != 'N') {
                CEP.TRC(SCCGWA, "��ͬ��ת�˿�ͨ��ʾ��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DNAME_TRF_FLG_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.DB_FREE_FLG == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.DB_FREE_FLG != 'Y' 
                && DCCSUCBI.INP_DATA.DB_FREE_FLG != 'C' 
                && DCCSUCBI.INP_DATA.DB_FREE_FLG != 'N') {
                CEP.TRC(SCCGWA, "С��˫���־��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DB_FREE_FLG_ERR);
            }
        }
        CEP.TRC(SCCGWA, DCCSUCBI.INP_DATA.DRAW_OVER_FLG);
        if (DCCSUCBI.INP_DATA.DRAW_OVER_FLG == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.DRAW_OVER_FLG != 'Y' 
                && DCCSUCBI.INP_DATA.DRAW_OVER_FLG != 'C' 
                && DCCSUCBI.INP_DATA.DRAW_OVER_FLG != 'N') {
                CEP.TRC(SCCGWA, "ATM����ȡ�ͨ��ʶ��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OUT_DRAW_FLG_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.TRAN_WITH_CARD == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.TRAN_WITH_CARD != 'Y' 
                && DCCSUCBI.INP_DATA.TRAN_WITH_CARD != 'C' 
                && DCCSUCBI.INP_DATA.TRAN_WITH_CARD != 'N') {
                CEP.TRC(SCCGWA, "�п����ѱ�־��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRAN_WITH_CARD_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.TRAN_NO_CARD == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.TRAN_NO_CARD != 'Y' 
                && DCCSUCBI.INP_DATA.TRAN_NO_CARD != 'C' 
                && DCCSUCBI.INP_DATA.TRAN_NO_CARD != 'N') {
                CEP.TRC(SCCGWA, "�޿����ױ�־��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRAN_NO_CARD_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.TRAN_CRS_BOR == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.TRAN_CRS_BOR != 'Y' 
                && DCCSUCBI.INP_DATA.TRAN_CRS_BOR != 'C' 
                && DCCSUCBI.INP_DATA.TRAN_CRS_BOR != 'N') {
                CEP.TRC(SCCGWA, "�羳���ױ�־��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRAN_CRS_BOR_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.TRAN_ATM_FLG == ' ') {
        } else {
            if (DCCSUCBI.INP_DATA.TRAN_ATM_FLG != 'Y' 
                && DCCSUCBI.INP_DATA.TRAN_ATM_FLG != 'C' 
                && DCCSUCBI.INP_DATA.TRAN_ATM_FLG != 'N') {
                CEP.TRC(SCCGWA, "ATM���ڽ��ױ�־��������");
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRAN_ATM_FLG_ERR);
            }
        }
        if (DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG == ' ' 
            && DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG == ' ' 
            && DCCSUCBI.INP_DATA.DB_FREE_FLG == ' ' 
            && DCCSUCBI.INP_DATA.DRAW_OVER_FLG == ' ' 
            && DCCSUCBI.INP_DATA.TRAN_WITH_CARD == ' ' 
            && DCCSUCBI.INP_DATA.TRAN_NO_CARD == ' ' 
            && DCCSUCBI.INP_DATA.TRAN_CRS_BOR == ' ' 
            && DCCSUCBI.INP_DATA.TRAN_ATM_FLG == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INP_CNT_ALL_EMP);
        }
    }
    public void B030_UPDATE_CARD_INF_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.SAME_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCRCDDAT.DIFF_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCRCDDAT.DRAW_OVER_FLG);
        CEP.TRC(SCCGWA, DCRCDDAT.DOUBLE_FREE_FLG);
        CEP.TRC(SCCGWA, DCRCDDAT.TRAN_WITH_CARD);
        CEP.TRC(SCCGWA, DCRCDDAT.TRAN_NO_CARD);
        CEP.TRC(SCCGWA, DCRCDDAT.TRAN_CRS_BOR);
        CEP.TRC(SCCGWA, DCRCDDAT.TRAN_ATM_FLG);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSUCBI.INP_DATA.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG != ' ' 
            && DCRCDDAT.SAME_NAME_TFR_FLG != DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG) {
            DCRCDDAT.SAME_NAME_TFR_FLG = DCCSUCBI.INP_DATA.SAME_NAME_TFR_FLG;
        }
        if (DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG != ' ' 
            && DCRCDDAT.DIFF_NAME_TFR_FLG != DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG) {
            DCRCDDAT.DIFF_NAME_TFR_FLG = DCCSUCBI.INP_DATA.DIFF_NAME_TFR_FLG;
        }
        if (DCCSUCBI.INP_DATA.DRAW_OVER_FLG != ' ' 
            && DCRCDDAT.DRAW_OVER_FLG != DCCSUCBI.INP_DATA.DRAW_OVER_FLG) {
            DCRCDDAT.DRAW_OVER_FLG = DCCSUCBI.INP_DATA.DRAW_OVER_FLG;
        }
        if (DCCSUCBI.INP_DATA.HOLD_AC_FLG != ' ' 
            && DCRCDDAT.HOLD_AC_FLG != DCCSUCBI.INP_DATA.HOLD_AC_FLG) {
            DCRCDDAT.HOLD_AC_FLG = DCCSUCBI.INP_DATA.HOLD_AC_FLG;
        }
        if (DCCSUCBI.INP_DATA.CARD_LNK_TYP != ' ' 
            && DCRCDDAT.CARD_LNK_TYP != DCCSUCBI.INP_DATA.CARD_LNK_TYP) {
            DCRCDDAT.CARD_LNK_TYP = DCCSUCBI.INP_DATA.CARD_LNK_TYP;
        }
        if (DCCSUCBI.INP_DATA.DB_FREE_FLG != ' ' 
            && DCRCDDAT.DOUBLE_FREE_FLG != DCCSUCBI.INP_DATA.DB_FREE_FLG) {
            DCRCDDAT.DOUBLE_FREE_FLG = DCCSUCBI.INP_DATA.DB_FREE_FLG;
        }
        if (DCCSUCBI.INP_DATA.TRAN_WITH_CARD != ' ' 
            && DCRCDDAT.TRAN_WITH_CARD != DCCSUCBI.INP_DATA.TRAN_WITH_CARD) {
            DCRCDDAT.TRAN_WITH_CARD = DCCSUCBI.INP_DATA.TRAN_WITH_CARD;
        }
        if (DCCSUCBI.INP_DATA.TRAN_NO_CARD != ' ' 
            && DCRCDDAT.TRAN_NO_CARD != DCCSUCBI.INP_DATA.TRAN_NO_CARD) {
            DCRCDDAT.TRAN_NO_CARD = DCCSUCBI.INP_DATA.TRAN_NO_CARD;
        }
        if (DCCSUCBI.INP_DATA.TRAN_CRS_BOR != ' ' 
            && DCRCDDAT.TRAN_CRS_BOR != DCCSUCBI.INP_DATA.TRAN_CRS_BOR) {
            DCRCDDAT.TRAN_CRS_BOR = DCCSUCBI.INP_DATA.TRAN_CRS_BOR;
        }
        if (DCCSUCBI.INP_DATA.TRAN_ATM_FLG != ' ' 
            && DCRCDDAT.TRAN_ATM_FLG != DCCSUCBI.INP_DATA.TRAN_ATM_FLG) {
            DCRCDDAT.TRAN_ATM_FLG = DCCSUCBI.INP_DATA.TRAN_ATM_FLG;
        }
        if (DCCSUCBI.INP_DATA.PROD_LMT_FLG != ' ' 
            && DCRCDDAT.PROD_LMT_FLG != DCCSUCBI.INP_DATA.PROD_LMT_FLG) {
            DCRCDDAT.PROD_LMT_FLG = DCCSUCBI.INP_DATA.PROD_LMT_FLG;
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSUCBI.OUT_DATA);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA);
        CEP.TRC(SCCGWA, "******************");
        DCCSUCBI.OUT_DATA.O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCCSUCBI.OUT_DATA.O_ID_TYP = DCCSUCBI.INP_DATA.ID_TYP;
        DCCSUCBI.OUT_DATA.O_ID_NO = DCCSUCBI.INP_DATA.ID_NO;
        DCCSUCBI.OUT_DATA.O_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        DCCSUCBI.OUT_DATA.O_CI_NM = DCCSUCBI.INP_DATA.CI_NM;
        DCCSUCBI.OUT_DATA.O_SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCSUCBI.OUT_DATA.O_DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCSUCBI.OUT_DATA.O_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCSUCBI.OUT_DATA.O_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        DCCSUCBI.OUT_DATA.O_CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        DCCSUCBI.OUT_DATA.O_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        DCCSUCBI.OUT_DATA.O_DB_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        DCCSUCBI.OUT_DATA.O_TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        DCCSUCBI.OUT_DATA.O_TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        DCCSUCBI.OUT_DATA.O_TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        DCCSUCBI.OUT_DATA.O_TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_DRAW_OVER_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_HOLD_AC_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_CARD_LNK_TYP);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_PROD_LMT_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_DB_FREE_FLG);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_TRAN_WITH_CARD);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_TRAN_NO_CARD);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, DCCSUCBI.OUT_DATA.O_ID_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCSUCBI.OUT_DATA;
        SCCFMT.DATA_LEN = 371;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_TRANS_DATA_OUTPUT_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCO3110);
        CEP.TRC(SCCGWA, DCCO3110);
        CEP.TRC(SCCGWA, "******************");
        DCCO3110.OUT_DATA.O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCCO3110.OUT_DATA.O_ID_TYP = DCCSUCBI.INP_DATA.ID_TYP;
        DCCO3110.OUT_DATA.O_ID_NO = DCCSUCBI.INP_DATA.ID_NO;
        DCCO3110.OUT_DATA.O_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        DCCO3110.OUT_DATA.O_CI_NM = DCCSUCBI.INP_DATA.CI_NM;
        DCCO3110.OUT_DATA.O_SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCO3110.OUT_DATA.O_DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCO3110.OUT_DATA.O_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCO3110.OUT_DATA.O_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        DCCO3110.OUT_DATA.O_CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        DCCO3110.OUT_DATA.O_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        DCCO3110.OUT_DATA.O_DB_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        DCCO3110.OUT_DATA.O_TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        DCCO3110.OUT_DATA.O_TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        DCCO3110.OUT_DATA.O_TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        DCCO3110.OUT_DATA.O_TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        CEP.TRC(SCCGWA, DCCO3110.OUT_DATA.O_CARD_NO);
        CEP.TRC(SCCGWA, DCCO3110.OUT_DATA.O_ID_TYP);
        CEP.TRC(SCCGWA, DCCO3110.OUT_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, DCCO3110.OUT_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, DCCO3110.OUT_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, DCCO3110);
        CEP.TRC(SCCGWA, DCCO3110);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCO3110;
        SCCFMT.DATA_LEN = 371;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCSUCBI.INP_DATA.CARD_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSUCBI.INP_DATA.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB04";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 489;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHECK_CARD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSUCBI.INP_DATA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_QURE_CARDPRD_INF();
        if (pgmRtn) return;
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P' 
            && DCCSUCBI.INP_DATA.HOLD_AC_FLG == ' ') {
            DCCSUCBI.INP_DATA.HOLD_AC_FLG = 'N';
        }
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P' 
            && DCCSUCBI.INP_DATA.HOLD_AC_FLG != DCRCDDAT.HOLD_AC_FLG 
            && (DCRCDDAT.HOLD_AC_FLG != ' ' 
            && DCCSUCBI.INP_DATA.HOLD_AC_FLG != 'N')) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HOLDFLG_ALTER_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_QURE_CARDPRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_GROUP_CDDAT_ID() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-TOT-SUB-CARD-CNT=COUNT(*)";
        DCTCDDAT_RD.where = "PRIM_CARD_NO = :WS_PRIM_CARD_NO "
            + "AND CARD_LNK_TYP < > '1'";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCRDLT_UPD() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.where = "CARD_NO = :DCRCRDLT.KEY.CARD_NO "
            + "AND TXN_TYPE = '05'";
        DCTCRDLT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.DELETE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
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
