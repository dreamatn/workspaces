package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACFIS {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMAIN_RD;
    DBParm IBTTMST_RD;
    DBParm IBTINSH_RD;
    DBParm IBTMST_RD;
    DBParm IBTBALF_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    char K_OPEN = 'O';
    char K_CLOSE = 'C';
    char K_SETT = 'S';
    String K_IB_PROD_MODEL = "IBTD";
    String K_OUTPUT_FMT1 = "IBE20   ";
    String K_OUTPUT_FMT2 = "IBE30   ";
    String K_OUTPUT_FMT3 = "IBE40   ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_STSW = "00000000000000000000";
    String K_STATUS_NORMAL = "10000000000000000000000000000000";
    String K_OUTPUT_FMT_A = "IBT05";
    String K_OUTPUT_FMT_B = "IBT06";
    String K_OUTPUT_FMT_C = "IBT07";
    IBZACFIS_WS_AC_STATUS WS_AC_STATUS = new IBZACFIS_WS_AC_STATUS();
    String WS_STS_FORMAT = " ";
    String WS_PROD_MODEL = " ";
    String WS_AC_NO = " ";
    double WS_REV_AMT = 0;
    double WS_REV_AMT1 = 0;
    double WS_AMT = 0;
    short WS_INT_DAYS = 0;
    double WS_ADV_BUD_INT = 0;
    double WS_ADV_DRW_INT = 0;
    double WS_ROUND_AMT = 0;
    double WS_ADV_INT = 0;
    double WS_ASET_AMT = 0;
    double WS_DEL_AMT = 0;
    double WS_SELL_AMT = 0;
    int WS_SEQ_NO = 0;
    String WS_OTH_AC = " ";
    short WS_I = 0;
    double WS_CNT_AMT = 0;
    double WS_EXP_INT = 0;
    double WS_BUD_AMT = 0;
    double WS_BUD_LAST = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFT IBCQINFT = new IBCQINFT();
    IBCQINFS IBCQINFS = new IBCQINFS();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCOCFIS IBCOCFIS = new IBCOCFIS();
    IBCDPFHI IBCDPFHI = new IBCDPFHI();
    IBCDWFHI IBCDWFHI = new IBCDWFHI();
    IBCSETCK IBCSETCK = new IBCSETCK();
    AICOCKOP AICOCKOP = new AICOCKOP();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    IBCCNGL IBCCNGL = new IBCCNGL();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCFX BPCFX = new BPCFX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICSACAC CICSACAC = new CICSACAC();
    CICCUST CICCUST = new CICCUST();
    IBCUBAL IBCUBAL = new IBCUBAL();
    CICACCU CICACCU = new CICACCU();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBCQEINT IBCQEINT = new IBCQEINT();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    IBRTMST IBRTMST = new IBRTMST();
    IBRMST IBRMST = new IBRMST();
    IBRBALF IBRBALF = new IBRBALF();
    IBRINSH IBRINSH = new IBRINSH();
    IBRINRH IBRINRH = new IBRINRH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    IBCACFIS IBCACFIS;
    public void MP(SCCGWA SCCGWA, IBCACFIS IBCACFIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACFIS = IBCACFIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACFIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (IBCACFIS.FUNC == K_OPEN) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B040_OPEN_AC_PROC();
            if (pgmRtn) return;
        } else if (IBCACFIS.FUNC == K_CLOSE) {
            B020_CHECK_INPUT();
            if (pgmRtn) return;
            B050_CLOSE_AC_PROC();
            if (pgmRtn) return;
        } else if (IBCACFIS.FUNC == K_SETT) {
            B030_CHECK_INPUT();
            if (pgmRtn) return;
            B060_SETT_AC_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC (" + IBCACFIS.FUNC + ")";
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACFIS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACFIS.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCACFIS.CCY);
        if (IBCACFIS.PRIM_AC_NO.trim().length() == 0) {
            if (IBCACFIS.NOSTR_CD.trim().length() == 0 
                || IBCACFIS.CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
            }
        }
        CEP.TRC(SCCGWA, IBCACFIS.PROD_CD);
        if (IBCACFIS.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PROD_CD_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.FUND_ATTR);
        if (IBCACFIS.FUND_ATTR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FUND_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.OPEN_BAL);
        if (IBCACFIS.OPEN_BAL <= 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OPEN_BAL_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.VAL_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (IBCACFIS.VAL_DATE == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VAL_DATE_M);
            } else {
                if (IBCACFIS.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BACK_VAL);
                }
            }
        }
        CEP.TRC(SCCGWA, IBCACFIS.EXP_DATE);
        if (IBCACFIS.EXP_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EXP_DATE_M);
        }
        if (IBCACFIS.EXP_DATE <= IBCACFIS.VAL_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EXP_LE_VAL);
        }
        CEP.TRC(SCCGWA, IBCACFIS.RATE_MTH);
        if (IBCACFIS.RATE_MTH == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_MTH_M);
        } else {
            CEP.TRC(SCCGWA, IBCACFIS.INTS_CYC);
            if (IBCACFIS.RATE_MTH == '1' 
                && IBCACFIS.INTS_CYC == ' ') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INTS_CYC_M);
            }
        }
        CEP.TRC(SCCGWA, IBCACFIS.RATE);
        CEP.TRC(SCCGWA, IBCACFIS.CALR_STD);
        if (IBCACFIS.RATE_MTH != '0') {
            if (IBCACFIS.CALR_STD == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M);
            }
        }
        CEP.TRC(SCCGWA, IBCACFIS.OTH_AC_ATTR);
        if (IBCACFIS.OTH_AC_ATTR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTHAC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.OTH_AC_NO);
        if (IBCACFIS.OTH_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_ACNO_M);
        } else {
            WS_OTH_AC = IBCACFIS.OTH_AC_NO;
        }
        CEP.TRC(SCCGWA, IBCACFIS.AC_NO1);
        if (SCCGWA.COMM_AREA.CANCEL_IND != ' ') {
            if (IBCACFIS.AC_NO1.trim().length() == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INPUT);
            }
            IBS.init(SCCGWA, IBRTMST);
            IBRTMST.KEY.AC_NO = IBCACFIS.AC_NO1;
            T000_READ_IBTTMST1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
            }
            if (IBRTMST.STS != 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
            }
            if (IBRTMST.LSET_DATE != 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_INT_SETTL);
            }
            if (IBRTMST.CURR_BAL < IBRTMST.OPEN_BAL) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_PART_DRAW);
            }
        }
        B010_01_CHECK_BR();
        if (pgmRtn) return;
        B010_02_CHECK_CCY();
        if (pgmRtn) return;
        B010_03_CHECK_PROD_CD();
        if (pgmRtn) return;
        B010_04_CHECK_OTH_AC();
        if (pgmRtn) return;
        B010_05_CHECK_VAL_DATE();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCACFIS.BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR == '3') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_OPN_BR);
        }
    }
    public void B010_02_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        if (IBCACFIS.PRIM_AC_NO.trim().length() > 0) {
            IBRTMAIN.KEY.AC_NO = IBCACFIS.PRIM_AC_NO;
            CEP.TRC(SCCGWA, IBRTMAIN.KEY.AC_NO);
            T000_READ_IBTMAIN1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
            }
            if (IBCACFIS.CCY.trim().length() == 0 
                || !IBRTMAIN.CCY.equalsIgnoreCase(IBCACFIS.CCY)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
            }
        } else {
            IBRTMAIN.NOSTRO_CODE = IBCACFIS.NOSTR_CD;
            IBRTMAIN.CCY = IBCACFIS.CCY;
            CEP.TRC(SCCGWA, IBRTMAIN.NOSTRO_CODE);
            CEP.TRC(SCCGWA, IBRTMAIN.CCY);
            T000_READ_IBTMAIN2();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
            }
        }
        if (IBRTMAIN.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (IBCACFIS.OTH_AC_ATTR == 'I') {
            if (IBCACFIS.OTH_AC_NO == null) IBCACFIS.OTH_AC_NO = "";
            JIBS_tmp_int = IBCACFIS.OTH_AC_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) IBCACFIS.OTH_AC_NO += " ";
            if (!IBCACFIS.CCY.equalsIgnoreCase(IBCACFIS.OTH_AC_NO.substring(7 - 1, 7 + 3 - 1))) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
            }
        }
    }
    public void B010_03_CHECK_PROD_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        if (IBCACFIS.FUNC == 'O') {
            BPCPQPRD.PRDT_CODE = IBCACFIS.PROD_CD;
        }
        if (IBCACFIS.FUNC == 'C' 
            || IBCACFIS.FUNC == 'S') {
            BPCPQPRD.PRDT_CODE = IBRTMST.PROD_CD;
        }
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(K_IB_PROD_MODEL)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_PROD_TYP);
        } else {
            WS_PROD_MODEL = BPCPQPRD.PRDT_MODEL;
        }
    }
    public void B010_04_CHECK_OTH_AC() throws IOException,SQLException,Exception {
        if (IBCACFIS.OTH_AC_ATTR == 'N') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBCACFIS.OTH_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_STS);
            if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
            }
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.GL_BOOK = "BK001";
            AICPQMIB.INPUT_DATA.AC = IBCACFIS.OTH_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
        }
    }
    public void B010_05_CHECK_VAL_DATE() throws IOException,SQLException,Exception {
        if (IBCACFIS.VAL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, AICOCKOP);
            AICOCKOP.VAL_DATE = IBCACFIS.VAL_DATE;
            S000_CALL_AIZCKOP();
            if (pgmRtn) return;
        }
    }
    public void B010_06_CHECK_EXP_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '1';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.DATE_1 = IBCACFIS.EXP_DATE;
        BPCPGDIN.INPUT_DATA.CCY = IBCACFIS.CCY;
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IS_HOLIDAY1);
            }
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACFIS.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCACFIS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACFIS.CCY);
        if (IBCACFIS.PRIM_AC_NO.trim().length() == 0 
            && (IBCACFIS.NOSTR_CD.trim().length() == 0 
            || IBCACFIS.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        B020_01_GET_AC_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCACFIS.DRW_AMT);
        if (IBCACFIS.DRW_AMT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRW_AMT_M);
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                if (IBCACFIS.DRW_AMT > IBCQINFS.CURR_BAL) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INVALID_DRW_AMT);
                }
            }
        }
        CEP.TRC(SCCGWA, IBCACFIS.DRW_DATE);
        if (IBCACFIS.DRW_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRW_DATE_M);
        } else {
            if (IBCACFIS.DRW_DATE < IBCQINFS.VAL_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INVALID_DRW_DT);
            }
            if (IBCACFIS.DRW_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INVALID_DRW_DT);
            }
            if (IBCACFIS.DRW_DATE < IBCQINFS.LSET_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRW_BEFORE_LSET);
            }
        }
        CEP.TRC(SCCGWA, IBCQINFS.RATE_MTH);
        CEP.TRC(SCCGWA, IBCACFIS.ASET_AMT);
        CEP.TRC(SCCGWA, IBCACFIS.OTH_AC_ATTR);
        if (IBCACFIS.OTH_AC_ATTR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTHAC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.OTH_AC_NO);
        if (IBCACFIS.OTH_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_ACNO_M);
        } else {
            WS_OTH_AC = IBCACFIS.OTH_AC_NO;
            B010_04_CHECK_OTH_AC();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        if (IBCACFIS.PRIM_AC_NO.trim().length() > 0) {
            IBCQINFS.PRIM_AC_NO = IBCACFIS.PRIM_AC_NO;
        } else {
            IBCQINFT.NOSTR_CD = IBCACFIS.NOSTR_CD;
            IBCQINFT.CCY = IBCACFIS.CCY;
            S000_CALL_IBZQINFT();
            if (pgmRtn) return;
            IBCQINFS.PRIM_AC_NO = IBCQINFT.AC_NO;
        }
        IBCQINFS.SEQ_NO = IBCACFIS.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (IBCQINFS.STS != 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
            }
        } else {
            if (IBCQINFS.CURR_BAL == 0) {
                if (IBCQINFS.STS != 'C') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SUBAC_NO_CLOSE);
                }
            } else {
                if (IBCQINFS.STS != 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
                }
            }
        }
    }
    public void B020_02_CHECK_TXNBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.POST_CTR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINFS.POST_CTR) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG != 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
        }
    }
    public void B030_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACFIS.AC_NO);
        CEP.TRC(SCCGWA, IBCACFIS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACFIS.CCY);
        if (IBCACFIS.AC_NO.trim().length() == 0 
            && (IBCACFIS.NOSTR_CD.trim().length() == 0 
            || IBCACFIS.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCACFIS.ASET_AMT);
        if (IBCACFIS.ASET_AMT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ASET_AMT_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.INTS_DATE);
        if (IBCACFIS.INTS_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SETT_DATE_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.INTS_AC_ATTR);
        if (IBCACFIS.INTS_AC_ATTR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTHAC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCACFIS.INTS_AC_NO);
        if (IBCACFIS.INTS_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_ACNO_M);
        } else {
            WS_OTH_AC = IBCACFIS.INTS_AC_NO;
            B030_04_CHECK_INTS_AC();
            if (pgmRtn) return;
        }
        B020_01_GET_AC_INFO();
        if (pgmRtn) return;
        B030_01_CHECK_RATE_FLAG();
        if (pgmRtn) return;
        B030_02_CHECK_SETT_DT();
        if (pgmRtn) return;
    }
    public void B030_01_CHECK_RATE_FLAG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.RATE_MTH);
        if (IBCQINFS.RATE_MTH == '0') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOT_INT);
        }
    }
    public void B030_02_CHECK_SETT_DT() throws IOException,SQLException,Exception {
        if (IBCACFIS.INTS_DATE < IBCQINFS.VAL_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_TH_EFF_DATE);
        }
        if (IBCACFIS.INTS_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SETTDT_NOT_GE_ACDT);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (IBCACFIS.INTS_DATE > IBCQINFS.EXP_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INTS_GRT_EXP);
            }
            if (IBCACFIS.INTS_DATE <= IBCQINFS.LSET_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INP_DT_GE_LASTDT);
            }
            if (IBCACFIS.INTS_DATE1 == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SETT_DATE1_M);
            }
        } else {
            if (IBCACFIS.INTS_DATE != IBCQINFS.LSET_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IS_NOT_LAS_DT);
            }
        }
    }
    public void B030_03_SETT_AMT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.BUD_INT);
        CEP.TRC(SCCGWA, IBCACFIS.ASET_AMT);
        if (IBCQINFS.BUD_INT != IBCACFIS.ASET_AMT) {
            WS_DEL_AMT = IBCACFIS.ASET_AMT - IBCQINFS.BUD_INT;
            bigD = new BigDecimal(WS_DEL_AMT);
            WS_DEL_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, WS_DEL_AMT);
        if (WS_DEL_AMT != 0) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCSETCK);
            IBCSETCK.KEY.TYP = "PIB01";
            IBCSETCK.KEY.CD = "DIFCK";
            BPCPRMR.DAT_PTR = IBCSETCK;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            WS_CNT_AMT = IBCSETCK.DATA_TXT.CNT_AMT;
            if (IBCACFIS.CCY.equalsIgnoreCase("156")) {
                WS_SELL_AMT = WS_DEL_AMT;
            } else {
                CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BUY_CCY = IBCACFIS.CCY;
                BPCFX.BUY_AMT = WS_DEL_AMT;
                BPCFX.SELL_CCY = BPCRBANK.LOC_CCY1;
                S000_CALL_BPZSFX();
                if (pgmRtn) return;
                WS_SELL_AMT = BPCFX.SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_SELL_AMT);
            CEP.TRC(SCCGWA, WS_CNT_AMT);
            if (WS_SELL_AMT > WS_CNT_AMT) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ASAMT_THAN_ESAMT_MOR);
            }
        }
    }
    public void B030_04_CHECK_INTS_AC() throws IOException,SQLException,Exception {
        if (IBCACFIS.INTS_AC_ATTR == 'N') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBCACFIS.INTS_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
            }
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.GL_BOOK = "BK001";
            AICPQMIB.INPUT_DATA.AC = IBCACFIS.INTS_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
        }
    }
    public void B040_OPEN_AC_PROC() throws IOException,SQLException,Exception {
        B040_01_GEN_ACNO();
        if (pgmRtn) return;
        B040_02_GEN_AC_SEQ();
        if (pgmRtn) return;
        B040_03_WRITE_BALF();
        if (pgmRtn) return;
        B040_04_WRITE_HIST();
        if (pgmRtn) return;
        B040_05_PROC_VCH();
        if (pgmRtn) return;
        B040_06_INQ_GL();
        if (pgmRtn) return;
        B040_07_WRITE_GL();
        if (pgmRtn) return;
        B040_08_WRITE_TMST();
        if (pgmRtn) return;
        B040_09_WRITE_BPTOCAC_PROC();
        if (pgmRtn) return;
        B040_10_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B040_01_GEN_ACNO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBS.init(SCCGWA, BPCCGAC);
            BPCCGAC.DATA.AC_KIND = '2';
            BPCCGAC.DATA.ACO_AC_FLG = '8';
            BPCCGAC.DATA.ACO_AC_MMO = "16";
            BPCCGAC.DATA.ACO_AC_DEF = 0;
            S000_CALL_BPZGACNO();
            if (pgmRtn) return;
            WS_AC_NO = BPCCGAC.DATA.ACO_AC;
            CEP.TRC(SCCGWA, WS_AC_NO);
            IBS.init(SCCGWA, IBRTMST);
            IBRTMST.KEY.AC_NO = WS_AC_NO;
            T000_READ_IBTTMST1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
            } else {
                IBCACFIS.AC_NO = WS_AC_NO;
                IBCACFIS.AC_NO1 = WS_AC_NO;
            }
        }
        IBS.init(SCCGWA, IBCQINFT);
        if (IBCACFIS.PRIM_AC_NO.trim().length() > 0) {
            IBCQINFT.AC_NO = IBCACFIS.PRIM_AC_NO;
        } else {
            IBCQINFT.NOSTR_CD = IBCACFIS.NOSTR_CD;
            IBCQINFT.CCY = IBCACFIS.CCY;
        }
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = IBCQINFT.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B040_02_GEN_AC_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            CICSACAC.DATA.ACAC_NO = IBCACFIS.AC_NO;
        } else {
            CICSACAC.DATA.ACAC_NO = IBCACFIS.AC_NO1;
        }
        CICSACAC.DATA.AGR_NO = IBRTMAIN.KEY.AC_NO;
        CICSACAC.DATA.CCY = IBCACFIS.CCY;
        CICSACAC.DATA.CR_FLG = '1';
        CICSACAC.DATA.ACAC_CTL = K_STSW;
        CICSACAC.DATA.PROD_CD = IBCACFIS.PROD_CD;
        CICSACAC.DATA.FRM_APP = "IB";
        CICSACAC.DATA.OPN_BR = IBCACFIS.BRANCH;
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) CICSACAC.DATA.OWNER_BK = 0;
        else CICSACAC.DATA.OWNER_BK = Integer.parseInt(SCCGWA.COMM_AREA.TR_BANK);
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
        WS_SEQ_NO = CICSACAC.DATA.AGR_SEQ;
    }
    public void B040_03_WRITE_BALF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRBALF);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRBALF.KEY.AC_NO = IBCACFIS.AC_NO;
            IBRBALF.KEY.BAL_DATE = IBCACFIS.VAL_DATE;
            IBRBALF.BAL = IBCACFIS.OPEN_BAL;
            IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_IBTBALF();
            if (pgmRtn) return;
        } else {
            IBRBALF.KEY.AC_NO = IBCACFIS.AC_NO1;
            IBRBALF.KEY.BAL_DATE = IBCACFIS.VAL_DATE;
            T000_READ_IBTBALF_UPD();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBRBALF.KEY.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_WRITE_IBTBALF();
                if (pgmRtn) return;
            } else {
                IBRBALF.BAL = 0;
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_REWRITE_IBTBALF();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_04_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBCACFIS.PRIM_AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCDPFHI);
        IBCDPFHI.NOSTRO_CODE = IBCACFIS.NOSTR_CD;
        IBCDPFHI.PRIM_AC_NO = IBCACFIS.PRIM_AC_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBCDPFHI.AC_NO = IBCACFIS.AC_NO;
        } else {
            IBCDPFHI.AC_NO = IBCACFIS.AC_NO1;
        }
        IBCDPFHI.SEQ_NO = IBCACFIS.SEQ_NO;
        IBCDPFHI.CUSTNME = IBCACFIS.CUSTNME;
        IBCDPFHI.PROD_CD = IBCACFIS.PROD_CD;
        IBCDPFHI.FUND_ATTR = IBCACFIS.FUND_ATTR;
        IBCDPFHI.CCY = IBCACFIS.CCY;
        IBCDPFHI.OPEN_BAL = IBCACFIS.OPEN_BAL;
        IBCDPFHI.VAL_DATE = IBCACFIS.VAL_DATE;
        IBCDPFHI.EXP_DATE = IBCACFIS.EXP_DATE;
        IBCDPFHI.INT_DAYS = (short) IBCACFIS.INT_DAYS;
        IBCDPFHI.RATE_MTH = IBCACFIS.RATE_MTH;
        IBCDPFHI.INTS_CYC = IBCACFIS.INTS_CYC;
        IBCDPFHI.PVAL_DATE = IBCACFIS.PVAL_DATE;
        IBCDPFHI.RATE = IBCACFIS.RATE;
        IBCDPFHI.ADV_RATE = IBCACFIS.ADV_RATE;
        IBCDPFHI.OVERDUE_RATE = IBCACFIS.OVD_RATE;
        IBCDPFHI.EXP_INT = IBCACFIS.EXP_INT;
        IBCDPFHI.CALR_STD = IBCACFIS.CALR_STD;
        IBCDPFHI.OTH_AC_ATTR = IBCACFIS.OTH_AC_ATTR;
        IBCDPFHI.OTH_AC_NO = IBCACFIS.OTH_AC_NO;
        IBCDPFHI.INTS_AC_ATTR = IBCACFIS.INTS_AC_ATTR;
        IBCDPFHI.INTS_AC_NO = IBCACFIS.INTS_AC_NO;
        IBCDPFHI.CORR_AC_NO = IBCACFIS.CORR_AC_NO;
        IBCDPFHI.CORR_DEPR_NO = IBCACFIS.CORR_DEPR_NO;
        IBCDPFHI.OPEN_DATE = IBCACFIS.OPEN_DATE;
        IBCDPFHI.BRANCH = IBCACFIS.BRANCH;
        IBCDPFHI.OIC_NO = IBCACFIS.OIC_NO;
        IBCDPFHI.SUB_DP = IBCACFIS.SUB_DP;
        IBCDPFHI.RESP_CD = IBCACFIS.RESP_CD;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.SUMUP_FLG = 'N';
        BPCPFHIS.DATA.AC = IBCACFIS.PRIM_AC_NO;
        CEP.TRC(SCCGWA, IBCACFIS.AC_NO);
        CEP.TRC(SCCGWA, IBCACFIS.AC_NO1);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            BPCPFHIS.DATA.ACO_AC = IBCACFIS.AC_NO;
        } else {
            BPCPFHIS.DATA.ACO_AC = IBCACFIS.AC_NO1;
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        BPCPFHIS.DATA.TX_CCY = IBCACFIS.CCY;
        if (IBCACFIS.CCY.equalsIgnoreCase("156")) {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        } else {
            BPCPFHIS.DATA.TX_CCY_TYP = '2';
        }
        BPCPFHIS.DATA.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TYPE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY_TYP);
        BPCPFHIS.DATA.TX_AMT = IBCACFIS.OPEN_BAL;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.PROD_CD = IBCACFIS.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = WS_PROD_MODEL;
        B050_04_01_GET_OTH_INFO();
        if (pgmRtn) return;
        BPCPFHIS.DATA.OTH_AC = IBCACFIS.OTH_AC_NO;
        BPCPFHIS.DATA.TX_MMO = "A802";
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.RLT_AC = IBCACFIS.OTH_AC_NO;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        BPCPFHIS.DATA.SMS_FLG = 'N';
        BPCPFHIS.DATA.VAL_BAL = IBCACFIS.OPEN_BAL;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.VAL_BAL);
        BPCPFHIS.DATA.VAL_BAL_CCY = IBCACFIS.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.FMT_ID = "IBCDPFHI";
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT_A;
        BPCPFHIS.DATA.FMT_LEN = 612;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBCDPFHI);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B040_05_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = WS_PROD_MODEL;
        BPCPOEWA.DATA.PROD_CODE = IBCACFIS.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.BR_OLD = IBCACFIS.BRANCH;
        BPCPOEWA.DATA.BR_NEW = IBCACFIS.BRANCH;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            BPCPOEWA.DATA.AC_NO = IBCACFIS.AC_NO;
        } else {
            BPCPOEWA.DATA.AC_NO = IBCACFIS.AC_NO1;
        }
        BPCPOEWA.DATA.AC_NO = IBCACFIS.AC_NO;
        BPCPOEWA.DATA.CCY_INFO[01-1].CCY = IBCACFIS.CCY;
        BPCPOEWA.DATA.AMT_INFO[01-1].AMT = IBCACFIS.OPEN_BAL;
        BPCPOEWA.DATA.CI_NO = IBCQINFS.CI_NO;
        BPCPOEWA.DATA.THEIR_AC = IBCACFIS.OTH_AC_NO;
        BPCPOEWA.DATA.VALUE_DATE = IBCACFIS.VAL_DATE;
        BPCPOEWA.DATA.POST_NARR = "IBTD DEPOSIT";
        BPCPOEWA.DATA.DESC = "IBTD DEPOSIT";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        if (IBCACFIS.OTH_AC_ATTR == 'N') {
            B040_05_01_PROC_VCH();
            if (pgmRtn) return;
        } else {
            B040_05_02_PROC_VCH();
            if (pgmRtn) return;
        }
    }
    public void B040_05_01_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = IBCACFIS.OTH_AC_NO;
        IBCPOSTA.AMT = IBCACFIS.OPEN_BAL;
        IBCPOSTA.CCY = IBCACFIS.CCY;
        IBCPOSTA.VAL_DATE = IBCACFIS.OPEN_DATE;
        IBCPOSTA.OTH_AC_NO = IBCACFIS.AC_NO;
        B040_05_03_GET_INFO();
        if (pgmRtn) return;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B040_05_02_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.AC_NO = IBCACFIS.OTH_AC_NO;
        AICUUPIA.DATA.CCY = IBCACFIS.CCY;
        AICUUPIA.DATA.AMT = IBCACFIS.OPEN_BAL;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.PROD_CODE = IBCACFIS.PROD_CD;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            AICUUPIA.DATA.THEIR_AC = IBCACFIS.AC_NO;
        } else {
            AICUUPIA.DATA.THEIR_AC = IBCACFIS.AC_NO1;
        }
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = IBCACFIS.CCY;
        AICUUPIA.DATA.THEIR_AMT = IBCACFIS.OPEN_BAL;
        AICUUPIA.DATA.BR_OLD = IBCACFIS.BRANCH;
        AICUUPIA.DATA.BR_NEW = IBCACFIS.BRANCH;
        AICUUPIA.DATA.POST_NARR = "IBTD AC DEPOSIT";
        AICUUPIA.DATA.DESC = "A001";
        AICUUPIA.DATA.PAY_MAN = IBCQINFT.CUSTNME;
        AICUUPIA.DATA.PAY_BR = IBCACFIS.BRANCH;
        AICUUPIA.DATA.PAY_BKNM = "SUZHOU BANK";
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B040_05_03_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        IBCQINFT.AC_NO = IBCACFIS.PRIM_AC_NO;
        S000_CALL_IBZQINFT();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = IBCQINFT.CUSTNME;
        IBCPOSTA.OTH_BR = IBCQINFT.OPEN_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINFT.OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_CNM);
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_BR);
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_BR_CNM);
    }
    public void B040_06_INQ_GL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, IBCCNGL);
        if (BPCPQPRD.STOP_SOLD_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRO_STOP_SOLD);
        }
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        IBCCNGL.PROD_TYPE = IBCACFIS.PROD_CD;
        IBCCNGL.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        IBCCNGL.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        IBCCNGL.PROP_TYP = IBCACFIS.FUND_ATTR;
        BPCQCNGL.DAT.INPUT.OTH_PTR = IBCCNGL;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B040_07_WRITE_GL() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.KEY.AC = IBCACFIS.AC_NO;
            BPCUCNGM.PROD_TYPE = IBCACFIS.PROD_CD;
            BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
            BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
            BPCUCNGM.FUNC = 'A';
            S000_CALL_BPZUCNGM();
            if (pgmRtn) return;
        }
    }
    public void B040_08_WRITE_TMST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBS.init(SCCGWA, IBRTMST);
            CEP.TRC(SCCGWA, IBRTMAIN.KEY.AC_NO);
            IBRTMST.KEY.AC_NO = IBCACFIS.AC_NO;
            IBRTMST.SEQ_NO = WS_SEQ_NO;
            IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
            IBRTMST.PROD_CD = IBCACFIS.PROD_CD;
            IBRTMST.STS = 'N';
            IBRTMST.STSW = K_STATUS_NORMAL;
            IBRTMST.FUND_ATTR = IBCACFIS.FUND_ATTR;
            IBRTMST.AC_NATR = IBCACFIS.AC_NATR;
            IBRTMST.CCY = IBRTMAIN.CCY;
            IBRTMST.OPEN_BAL = IBCACFIS.OPEN_BAL;
            IBRTMST.CURR_BAL = IBCACFIS.OPEN_BAL;
            IBRTMST.VAL_DATE = IBCACFIS.VAL_DATE;
            IBRTMST.EXP_INT = IBCACFIS.EXP_INT;
            IBRTMST.EXP_DATE = IBCACFIS.EXP_DATE;
            IBRTMST.PVAL_DATE = IBCACFIS.PVAL_DATE;
            IBRTMST.RATE_MTH = IBCACFIS.RATE_MTH;
            IBRTMST.INTS_CYC = IBCACFIS.INTS_CYC;
            IBRTMST.RATE = IBCACFIS.RATE;
            IBRTMST.ADV_RATE = IBCACFIS.ADV_RATE;
            IBRTMST.OVD_RATE = IBCACFIS.OVD_RATE;
            IBRTMST.CALR_STD = IBCACFIS.CALR_STD;
            IBRTMST.INTS_AC_TYPE = IBCACFIS.INTS_AC_ATTR;
            IBRTMST.INTS_AC_NO = IBCACFIS.INTS_AC_NO;
            IBRTMST.CORR_BK_CD = IBCACFIS.CORR_BK_CD;
            IBRTMST.CORR_AC_NO = IBCACFIS.CORR_AC_NO;
            IBRTMST.CORR_DEPR_NO = IBCACFIS.CORR_DEPR_NO;
            IBRTMST.POST_CTR = IBCACFIS.BRANCH;
            IBRTMST.OIC_NO = IBCACFIS.OIC_NO;
            IBRTMST.SUB_DP = IBCACFIS.SUB_DP;
            IBRTMST.RESP_CD = IBCACFIS.RESP_CD;
            IBRTMST.OWNER_BK = SCCGWA.COMM_AREA.TR_BANK;
            IBRTMST.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBRTMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRTMST.CLOS_DATE = 99991231;
            IBRTMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
            IBRTMST.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, IBRTMST.AUTH_TLR);
            IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_IBTTMST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, IBRTMST);
            IBRTMST.KEY.AC_NO = IBCACFIS.AC_NO1;
            CEP.TRC(SCCGWA, IBRTMST.KEY.AC_NO);
            T000_READ_IBTTMST_UPD();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
            }
            IBRTMST.STS = 'R';
            IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTTMST();
            if (pgmRtn) return;
        }
    }
    public void B040_09_WRITE_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = IBRTMST.PRIM_AC_NO;
        BPCSOCAC.ACO_AC = IBRTMST.KEY.AC_NO;
        BPCSOCAC.SEQ = IBRTMST.SEQ_NO;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "25";
        BPCSOCAC.CI_TYPE = '3';
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.CCY = IBRTMST.CCY;
        if (IBRTMST.CCY.equalsIgnoreCase("156")) {
            BPCSOCAC.CCY_TYPE = '1';
        } else {
            BPCSOCAC.CCY_TYPE = '2';
        }
        BPCSOCAC.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, BPCSOCAC.AUT_TLR);
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.PROD_CD = IBRTMST.PROD_CD;
        BPCSOCAC.AC_CNM = IBCQINFT.CUSTNME;
        BPCSOCAC.OPEN_RAT = IBRTMST.RATE;
        BPCSOCAC.OPEN_AMT = IBRTMST.OPEN_BAL;
        BPCSOCAC.BR = IBRTMST.POST_CTR;
        CEP.TRC(SCCGWA, BPCSOCAC.BR);
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B040_10_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCFIS);
        IBCOCFIS.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCOCFIS.PRIM_AC_NO = IBCQINFS.PRIM_AC_NO;
        IBCOCFIS.SEQ_NO = IBRTMST.SEQ_NO;
        IBCOCFIS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCFIS.PROD_CD = IBRTMST.PROD_CD;
        IBCOCFIS.FUND_ATTR = IBRTMST.FUND_ATTR;
        IBCOCFIS.CCY = IBRTMST.CCY;
        IBCOCFIS.OPEN_BAL = IBRTMST.OPEN_BAL;
        IBCOCFIS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCOCFIS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCOCFIS.INT_DAYS = IBCACFIS.INT_DAYS;
        IBCOCFIS.RATE_MTH = IBRTMST.RATE_MTH;
        IBCOCFIS.INTS_CYC = IBRTMST.INTS_CYC;
        IBCOCFIS.PVAL_DATE = IBRTMST.PVAL_DATE;
        IBCOCFIS.RATE = IBRTMST.RATE;
        IBCOCFIS.ADV_RATE = IBRTMST.ADV_RATE;
        IBCOCFIS.OVD_RATE = IBRTMST.OVD_RATE;
        IBCOCFIS.EXP_INT = IBRTMST.EXP_INT * 1;
        bigD = new BigDecimal(IBCOCFIS.EXP_INT);
        IBCOCFIS.EXP_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        if (IBRTMST.CALR_STD == 360) {
            IBCOCFIS.CALR_STD = "01";
        } else if (IBRTMST.CALR_STD == 365) {
            IBCOCFIS.CALR_STD = "02";
        } else if (IBRTMST.CALR_STD == 366) {
            IBCOCFIS.CALR_STD = "03";
        }
        IBCOCFIS.OTH_AC_ATTR = IBCACFIS.OTH_AC_ATTR;
        IBCOCFIS.OTH_AC_NO = IBCACFIS.OTH_AC_NO;
        IBCOCFIS.INTS_AC_ATTR = IBCACFIS.INTS_AC_ATTR;
        IBCOCFIS.INTS_AC_NO = IBCACFIS.INTS_AC_NO;
        IBCOCFIS.CORR_BK_CD = IBRTMST.CORR_BK_CD;
        IBCOCFIS.CORR_AC_NO = IBRTMST.CORR_AC_NO;
        IBCOCFIS.CORR_DEPR_NO = IBRTMST.CORR_DEPR_NO;
        IBCOCFIS.OPEN_DATE = IBRTMST.OPEN_DATE;
        IBCOCFIS.OPEN_BR = IBRTMST.OPEN_BR;
        IBCOCFIS.OIC_NO = IBRTMST.OIC_NO;
        IBCOCFIS.RESP_CD = IBRTMST.RESP_CD;
        IBCOCFIS.SUB_DP = IBRTMST.SUB_DP;
        IBCOCFIS.AC_NATR = IBRTMST.AC_NATR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = IBCOCFIS;
        SCCFMT.DATA_LEN = 764;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_CLOSE_AC_PROC() throws IOException,SQLException,Exception {
        B050_01_GET_AC_INFO();
        if (pgmRtn) return;
        B050_02_UPD_BAL();
        if (pgmRtn) return;
        B050_03_WRITE_BALF();
        if (pgmRtn) return;
        B050_04_WRITE_HIST();
        if (pgmRtn) return;
        B050_05_PROC_VCH();
        if (pgmRtn) return;
        B050_06_WRITE_INSH();
        if (pgmRtn) return;
        B050_07_REWRITE_TMST();
        if (pgmRtn) return;
        B050_08_SEND_CI();
        if (pgmRtn) return;
        B050_09_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B050_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.KEY.AC_NO = IBCQINFS.AC_NO;
        T000_READ_IBTTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBCACFIS.RATE1 = IBRTMST.RATE;
            IBCACFIS.ADV_RAT1 = IBRTMST.ADV_RATE;
            IBCACFIS.OVD_RAT1 = IBRTMST.OVD_RATE;
        }
    }
    public void B050_02_UPD_BAL() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            CEP.TRC(SCCGWA, IBRTMST.CURR_BAL);
            CEP.TRC(SCCGWA, IBCACFIS.DRW_INT);
            CEP.TRC(SCCGWA, IBCACFIS.BUD_INT);
            WS_ADV_BUD_INT = IBCACFIS.BUD_INT * IBCACFIS.DRW_AMT / IBRTMST.CURR_BAL;
            bigD = new BigDecimal(WS_ADV_BUD_INT);
            WS_ADV_BUD_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_ADV_BUD_INT);
            WS_ROUND_AMT = WS_ADV_BUD_INT;
            B050_02_01_ROUND();
            if (pgmRtn) return;
            WS_ADV_BUD_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, WS_ADV_BUD_INT);
            WS_ADV_DRW_INT = IBCACFIS.DRW_INT * IBCACFIS.DRW_AMT / IBRTMST.CURR_BAL;
            bigD = new BigDecimal(WS_ADV_DRW_INT);
            WS_ADV_DRW_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_ADV_DRW_INT);
            WS_ROUND_AMT = WS_ADV_DRW_INT;
            B050_02_01_ROUND();
            if (pgmRtn) return;
            WS_ADV_DRW_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, WS_ADV_DRW_INT);
            IBRTMST.BUD_INT = IBRTMST.BUD_INT - WS_ADV_BUD_INT;
            IBRTMST.DRW_INT = IBRTMST.DRW_INT - WS_ADV_DRW_INT;
            CEP.TRC(SCCGWA, IBRTMST.BUD_INT);
            CEP.TRC(SCCGWA, IBRTMST.DRW_INT);
            IBRTMST.CURR_BAL = IBRTMST.CURR_BAL - IBCACFIS.DRW_AMT;
            CEP.TRC(SCCGWA, IBRTMST.CURR_BAL);
        } else {
            CEP.TRC(SCCGWA, IBCACFIS.CURR_BAL);
            CEP.TRC(SCCGWA, IBCACFIS.BUD_INT);
            IBRTMST.CURR_BAL = IBRTMST.CURR_BAL + IBCACFIS.DRW_AMT;
            WS_ADV_BUD_INT = IBCACFIS.BUD_INT * IBCACFIS.DRW_AMT / IBCACFIS.CURR_BAL;
            bigD = new BigDecimal(WS_ADV_BUD_INT);
            WS_ADV_BUD_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_ADV_BUD_INT);
            WS_ROUND_AMT = WS_ADV_BUD_INT;
            B050_02_01_ROUND();
            if (pgmRtn) return;
            WS_ADV_BUD_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, WS_ADV_BUD_INT);
            WS_ADV_DRW_INT = IBCACFIS.DRW_INT * IBCACFIS.DRW_AMT / IBCACFIS.CURR_BAL;
            bigD = new BigDecimal(WS_ADV_DRW_INT);
            WS_ADV_DRW_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_ADV_DRW_INT);
            WS_ROUND_AMT = WS_ADV_DRW_INT;
            B050_02_01_ROUND();
            if (pgmRtn) return;
            WS_ADV_DRW_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, WS_ADV_DRW_INT);
            IBRTMST.BUD_INT = IBRTMST.BUD_INT + WS_ADV_BUD_INT;
            IBRTMST.DRW_INT = IBRTMST.DRW_INT + WS_ADV_DRW_INT;
            CEP.TRC(SCCGWA, IBRTMST.BUD_INT);
            CEP.TRC(SCCGWA, IBRTMST.DRW_INT);
        }
    }
    public void B050_02_01_ROUND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.AMT = WS_ROUND_AMT;
        BPCRDAMT.CCY = IBRTMST.CCY;
        CEP.TRC(SCCGWA, BPCRDAMT.AMT);
        CEP.TRC(SCCGWA, BPCRDAMT.CCY);
        S000_CALL_BPZRDAMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
    }
    public void B050_03_WRITE_BALF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCUBAL);
        IBCUBAL.SIGN = 'C';
        IBCUBAL.AC_NO = IBRTMST.KEY.AC_NO;
        IBCUBAL.VALUE_DATE = IBCACFIS.DRW_DATE;
        CEP.TRC(SCCGWA, IBCACFIS.DRW_DATE);
        IBCUBAL.AMT = IBCACFIS.DRW_AMT;
        S000_CALL_IBZUBAL();
        if (pgmRtn) return;
    }
    public void B050_04_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBRTMST.PRIM_AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCDWFHI);
        IBCDWFHI.NOSTR_CD = IBCACFIS.NOSTR_CD;
        IBCDWFHI.PRIM_AC_NO = IBCACFIS.PRIM_AC_NO;
        IBCDWFHI.SEQ_NO = IBCACFIS.SEQ_NO;
        IBCDWFHI.CCY = IBRTMST.CCY;
        IBCDWFHI.OPEN_BAL = IBRTMST.OPEN_BAL;
        IBCDWFHI.REMAIN_BAL = IBRTMST.CURR_BAL;
        IBCDWFHI.DRW_AMT = IBCACFIS.DRW_AMT;
        IBCDWFHI.DRW_DATE = IBCACFIS.DRW_DATE;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBCDWFHI.RATE = IBCACFIS.RATE;
            IBCDWFHI.ADV_RATE = IBCACFIS.ADV_RATE;
            IBCDWFHI.OVD_RATE = IBCACFIS.OVD_RATE;
        } else {
            IBCDWFHI.RATE = IBCACFIS.RATE1;
            IBCDWFHI.ADV_RATE = IBCACFIS.ADV_RAT1;
            IBCDWFHI.OVD_RATE = IBCACFIS.OVD_RAT1;
        }
        IBCDWFHI.ESET_AMT = IBCACFIS.ESET_AMT;
        IBCDWFHI.ASET_AMT = IBCACFIS.ASET_AMT;
        IBCDWFHI.OTH_AC_ATTR = IBCACFIS.OTH_AC_ATTR;
        IBCDWFHI.OTH_AC_NO = IBCACFIS.OTH_AC_NO;
        if (IBCACFIS.DRW_AMT == IBCQINFS.CURR_BAL) {
            IBCDWFHI.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        IBCDWFHI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCDWFHI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.AC = IBCACFIS.PRIM_AC_NO;
        BPCPFHIS.DATA.ACO_AC = IBCQINFS.AC_NO;
        BPCPFHIS.DATA.TX_CCY = IBRTMST.CCY;
        if (IBRTMST.CCY.equalsIgnoreCase("156")) {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        } else {
            BPCPFHIS.DATA.TX_CCY_TYP = '2';
        }
        BPCPFHIS.DATA.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY_TYP);
        BPCPFHIS.DATA.TX_AMT = IBCACFIS.DRW_AMT;
        BPCPFHIS.DATA.TX_VAL_DT = IBCACFIS.DRW_DATE;
        BPCPFHIS.DATA.PROD_CD = IBRTMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.PROD_CD);
        B010_03_CHECK_PROD_CD();
        if (pgmRtn) return;
        BPCPFHIS.DATA.PRDMO_CD = WS_PROD_MODEL;
        BPCPFHIS.DATA.TX_MMO = "A803";
        B050_04_01_GET_OTH_INFO();
        if (pgmRtn) return;
        BPCPFHIS.DATA.OTH_AC = IBCACFIS.OTH_AC_NO;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.RLT_AC = IBCACFIS.OTH_AC_NO;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        BPCPFHIS.DATA.SMS_FLG = 'N';
        BPCPFHIS.DATA.VAL_BAL = IBRTMST.CURR_BAL;
        BPCPFHIS.DATA.VAL_BAL_CCY = IBRTMST.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.FMT_ID = "IBCDWFHI";
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT_B;
        BPCPFHIS.DATA.FMT_LEN = 752;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBCDWFHI);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B050_04_01_GET_OTH_INFO() throws IOException,SQLException,Exception {
        if (IBCACFIS.OTH_AC_ATTR == 'N') {
            CEP.TRC(SCCGWA, "111");
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBCACFIS.OTH_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            BPCPFHIS.DATA.RLT_BANK = "" + IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            BPCPFHIS.DATA.RLT_AC_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (IBCACFIS.OTH_AC_ATTR == 'I') {
            CEP.TRC(SCCGWA, "222");
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = IBCACFIS.OTH_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            BPCPFHIS.DATA.RLT_BANK = AICPQMIB.INPUT_DATA.AC.substring(0, 6);
            BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
            IBS.init(SCCGWA, BPCPQORG);
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if (AICPQMIB.INPUT_DATA.AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(AICPQMIB.INPUT_DATA.AC.substring(0, 6));
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void B050_05_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = WS_PROD_MODEL;
        BPCPOEWA.DATA.PROD_CODE = IBRTMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = IBRTMST.POST_CTR;
        BPCPOEWA.DATA.BR_NEW = IBRTMST.POST_CTR;
        CEP.TRC(SCCGWA, IBRTMST.POST_CTR);
        BPCPOEWA.DATA.AC_NO = IBRTMST.KEY.AC_NO;
        BPCPOEWA.DATA.CCY_INFO[01-1].CCY = IBCACFIS.CCY;
        BPCPOEWA.DATA.AMT_INFO[01-1].AMT = IBCACFIS.DRW_AMT;
        BPCPOEWA.DATA.AMT_INFO[02-1].AMT = WS_ADV_BUD_INT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[02-1].AMT);
        if (IBCACFIS.ASET_AMT >= 0) {
            BPCPOEWA.DATA.AMT_INFO[04-1].AMT = IBCACFIS.ASET_AMT;
        } else {
            WS_ASET_AMT = IBCACFIS.ASET_AMT * -1;
            BPCPOEWA.DATA.AMT_INFO[07-1].AMT = WS_ASET_AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        BPCPOEWA.DATA.CI_NO = IBCQINFS.CI_NO;
        BPCPOEWA.DATA.THEIR_AC = IBCACFIS.OTH_AC_NO;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.POST_NARR = "IBTD DRAW";
        BPCPOEWA.DATA.DESC = "IBTD DRAW";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        WS_AMT = IBCACFIS.DRW_AMT + IBCACFIS.ASET_AMT;
        if (IBCACFIS.OTH_AC_ATTR == 'N') {
            B050_05_01_PROC_VCH();
            if (pgmRtn) return;
        } else {
            B050_05_02_PROC_VCH();
            if (pgmRtn) return;
        }
    }
    public void B050_05_01_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = IBCACFIS.OTH_AC_NO;
        IBCPOSTA.AMT = WS_AMT;
        IBCPOSTA.CCY = IBCACFIS.CCY;
        B040_05_03_GET_INFO();
        if (pgmRtn) return;
        IBCPOSTA.VAL_DATE = IBCACFIS.DRW_DATE;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B050_05_02_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.THEIR_AC = IBCQINFS.AC_NO;
        AICUUPIA.DATA.CCY = IBCACFIS.CCY;
        AICUUPIA.DATA.AMT = WS_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = IBCACFIS.SUSP_SEQ;
        AICUUPIA.DATA.PROD_CODE = IBCQINFS.PROD_CD;
        AICUUPIA.DATA.AC_NO = IBCACFIS.OTH_AC_NO;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = IBCQINFS.CCY;
        AICUUPIA.DATA.THEIR_AMT = WS_AMT;
        AICUUPIA.DATA.BR_OLD = IBCQINFS.POST_CTR;
        AICUUPIA.DATA.BR_NEW = IBCQINFS.POST_CTR;
        AICUUPIA.DATA.DESC = "A019";
        AICUUPIA.DATA.PAY_MAN = IBCQINFT.CUSTNME;
        AICUUPIA.DATA.PAY_BR = IBCACFIS.BRANCH;
        AICUUPIA.DATA.PAY_BKNM = "SUZHOU BANK";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B050_05_03_REV_AMT() throws IOException,SQLException,Exception {
        if (IBCQINFS.RATE_MTH == '1' 
            && IBCACFIS.DRW_DATE < IBCQINFS.EXP_DATE 
            && IBCQINFS.LSET_DATE != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCQINFS.LSET_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_INT_DAYS = (short) SCCCLDT.DAYS;
            CEP.TRC(SCCGWA, WS_INT_DAYS);
            WS_ADV_INT = IBCACFIS.DRW_AMT * IBCACFIS.ADV_RATE * WS_INT_DAYS / 100 / IBRTMST.CALR_STD;
            bigD = new BigDecimal(WS_ADV_INT);
            WS_ADV_INT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            WS_ROUND_AMT = WS_ADV_INT;
            B050_02_01_ROUND();
            if (pgmRtn) return;
            WS_ADV_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, WS_ADV_INT);
            CEP.TRC(SCCGWA, WS_ADV_DRW_INT);
            WS_REV_AMT = WS_ADV_INT - WS_ADV_DRW_INT;
            bigD = new BigDecimal(WS_REV_AMT);
            WS_REV_AMT = bigD.setScale(4, RoundingMode.HALF_UP).doubleValue();
            WS_REV_AMT1 = WS_REV_AMT;
            CEP.TRC(SCCGWA, WS_REV_AMT);
        }
    }
    public void B050_06_WRITE_INSH() throws IOException,SQLException,Exception {
        if (IBCQINFS.RATE_MTH != '0') {
            IBS.init(SCCGWA, IBRINSH);
            IBRINSH.KEY.AC_NO = IBCQINFS.AC_NO;
            CEP.TRC(SCCGWA, IBRINSH.KEY.AC_NO);
            if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                WS_SEQ_NO = 0;
                IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_READ_IBTINSH_FIRST();
                if (pgmRtn) return;
                IBRINSH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
                IBRINSH.KEY.SEQ = WS_SEQ_NO;
                IBRINSH.INTS_DATE = IBCACFIS.DRW_DATE;
                if (IBCQINFS.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    if (IBCQINFS.RATE_MTH == '1') {
                        IBRINSH.ESET_AMT = WS_REV_AMT;
                    }
                    if (IBCQINFS.RATE_MTH == '2') {
                        IBRINSH.ESET_AMT = WS_ADV_BUD_INT;
                    }
                } else {
                    IBRINSH.ESET_AMT = IBCACFIS.BUD_INT;
                }
                IBRINSH.ASET_AMT = IBCACFIS.ASET_AMT;
                if (IBCACFIS.DRW_AMT < IBCQINFS.CURR_BAL) {
                    IBRINSH.SETT_TYPE = '4';
                } else {
                    IBRINSH.SETT_TYPE = '5';
                }
                IBRINSH.SETT_AC_NO = IBCACFIS.OTH_AC_NO;
                IBRINSH.SETT_AC_TYPE = IBCACFIS.OTH_AC_ATTR;
                IBRINSH.REV_FLG = 'N';
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
                IBRINSH.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
                CEP.TRC(SCCGWA, IBRINSH.AUTH_TLR);
                IBRINSH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRINSH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_WRITE_IBTINSH();
                if (pgmRtn) return;
            } else {
                T000_READ_INSH_LAST_SETDAT();
                if (pgmRtn) return;
                if (WS_TABLE_REC == 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INSH);
                }
                T000_READUPD_IBTINSH();
                if (pgmRtn) return;
                if (WS_TABLE_REC == 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INSH);
                }
                IBRINSH.REV_FLG = 'R';
                IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_REWRITE_IBTINSH();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_07_REWRITE_TMST() throws IOException,SQLException,Exception {
        if (IBRTMST.RATE_MTH != '0') {
            B050_07_01_COMP_EXP_INT();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            CEP.TRC(SCCGWA, IBCACFIS.DRW_AMT);
            CEP.TRC(SCCGWA, IBCQINFS.CURR_BAL);
            IBRTMST.RATE = IBCACFIS.RATE;
            IBRTMST.OVD_RATE = IBCACFIS.OVD_RATE;
            IBRTMST.ADV_RATE = IBCACFIS.ADV_RATE;
            if (IBCACFIS.DRW_AMT == IBCQINFS.CURR_BAL) {
                IBRTMST.STS = 'C';
                WS_AC_STATUS.WS_STS_NORMA = '0';
                WS_AC_STATUS.WS_STS_BLOCK = '0';
                WS_AC_STATUS.WS_STS_LHOLD = '0';
                WS_AC_STATUS.WS_STS_CLOSD = '1';
                IBRTMST.STSW = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
                IBRTMST.CLOS_DATE = SCCGWA.COMM_AREA.AC_DATE;
                B050_07_02_UPD_BPTOCAC_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBRTMST.STS = 'N';
            WS_AC_STATUS.WS_STS_NORMA = '1';
            WS_AC_STATUS.WS_STS_BLOCK = '0';
            WS_AC_STATUS.WS_STS_LHOLD = '0';
            WS_AC_STATUS.WS_STS_CLOSD = '0';
            IBRTMST.STSW = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
            IBRTMST.CLOS_DATE = 99991231;
            CEP.TRC(SCCGWA, IBCACFIS.RATE1);
            CEP.TRC(SCCGWA, IBCACFIS.OVD_RAT1);
            CEP.TRC(SCCGWA, IBCACFIS.ADV_RAT1);
            IBRTMST.RATE = IBCACFIS.RATE1;
            IBRTMST.OVD_RATE = IBCACFIS.OVD_RAT1;
            IBRTMST.ADV_RATE = IBCACFIS.ADV_RAT1;
        }
        IBRTMST.EXP_INT = WS_EXP_INT;
        IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRTMST.LAST_FI_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_IBTTMST();
        if (pgmRtn) return;
    }
    public void B050_07_01_COMP_EXP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = IBRTMST.VAL_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        SCCCLDT.DATE2 = IBRTMST.EXP_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, IBRTMST.CALR_STD);
        WS_EXP_INT = IBRTMST.CURR_BAL * IBCACFIS.RATE * SCCCLDT.DAYS / IBRTMST.CALR_STD / 100;
    }
    public void B050_07_02_UPD_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = IBCQINFS.PRIM_AC_NO;
        BPCSOCAC.ACO_AC = IBCQINFS.AC_NO;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.WORK_TYP = "25";
        BPCSOCAC.CI_TYPE = '3';
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.CCY = IBCQINFS.CCY;
        if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
            BPCSOCAC.CCY_TYPE = '1';
        } else {
            BPCSOCAC.CCY_TYPE = '2';
        }
        BPCSOCAC.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.LOSS_INT = IBCACFIS.ASET_AMT;
        BPCSOCAC.PROD_CD = IBCQINFS.PROD_CD;
        CEP.TRC(SCCGWA, IBCACFIS.DRW_AMT);
        CEP.TRC(SCCGWA, IBCACFIS.ASET_AMT);
        BPCSOCAC.LOSS_AMT = IBCACFIS.DRW_AMT + IBCACFIS.ASET_AMT;
        if (IBCACFIS.DRW_DATE < IBCQINFS.EXP_DATE) {
            BPCSOCAC.LOSS_RAT = IBCACFIS.ADV_RATE;
        } else {
            BPCSOCAC.LOSS_RAT = IBCACFIS.RATE;
        }
        BPCSOCAC.LOSS_INT = IBCACFIS.ASET_AMT;
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_AMT);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_INT);
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B050_08_SEND_CI() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (IBCACFIS.DRW_AMT == IBCQINFS.CURR_BAL) {
                IBS.init(SCCGWA, CICSACAC);
                CICSACAC.FUNC = 'D';
                CICSACAC.DATA.ACAC_NO = IBRTMST.KEY.AC_NO;
                S000_CALL_CIZSACAC();
                if (pgmRtn) return;
            }
        } else {
            if (IBCQINFS.STS == 'C') {
                IBS.init(SCCGWA, CICSACAC);
                CICSACAC.FUNC = 'D';
                CICSACAC.DATA.ACAC_NO = IBRTMST.KEY.AC_NO;
                S000_CALL_CIZSACAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_09_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCFIS);
        IBCOCFIS.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCOCFIS.PRIM_AC_NO = IBCQINFS.PRIM_AC_NO;
        IBCOCFIS.SEQ_NO = IBRTMST.SEQ_NO;
        IBCOCFIS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCFIS.PROD_CD = IBRTMST.PROD_CD;
        IBCOCFIS.CCY = IBRTMST.CCY;
        IBCOCFIS.OPEN_BAL = IBRTMST.OPEN_BAL;
        IBCOCFIS.CURR_BAL = IBRTMST.CURR_BAL;
        IBCOCFIS.RATE_MTH = IBRTMST.RATE_MTH;
        IBCOCFIS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCOCFIS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCOCFIS.DRW_AMT = IBCACFIS.DRW_AMT;
        IBCOCFIS.DRW_DATE = IBCACFIS.DRW_DATE;
        IBCOCFIS.RATE = IBRTMST.RATE;
        IBCOCFIS.ADV_RATE = IBRTMST.ADV_RATE;
        IBCOCFIS.OVD_RATE = IBRTMST.OVD_RATE;
        IBCOCFIS.BUD_INT = IBRTMST.BUD_INT * 1;
        bigD = new BigDecimal(IBCOCFIS.BUD_INT);
        IBCOCFIS.BUD_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.DRW_INT = IBRTMST.DRW_INT * 1;
        bigD = new BigDecimal(IBCOCFIS.DRW_INT);
        IBCOCFIS.DRW_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.ESET_AMT = IBCACFIS.ESET_AMT * 1;
        bigD = new BigDecimal(IBCOCFIS.ESET_AMT);
        IBCOCFIS.ESET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.ASET_AMT = IBCACFIS.ASET_AMT * 1;
        bigD = new BigDecimal(IBCOCFIS.ASET_AMT);
        IBCOCFIS.ASET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.OTH_AC_ATTR = IBCACFIS.OTH_AC_ATTR;
        IBCOCFIS.OTH_AC_NO = IBCACFIS.OTH_AC_NO;
        IBCOCFIS.SUSP_SEQ = IBCACFIS.SUSP_SEQ;
        IBCOCFIS.OIC_NO = IBRTMST.OIC_NO;
        IBCOCFIS.RESP_CD = IBRTMST.RESP_CD;
        IBCOCFIS.SUB_DP = IBRTMST.SUB_DP;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT2;
        SCCFMT.DATA_PTR = IBCOCFIS;
        SCCFMT.DATA_LEN = 764;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_SETT_AC_PROC() throws IOException,SQLException,Exception {
        B050_01_GET_AC_INFO();
        if (pgmRtn) return;
        B060_01_UPD_BUD_INT();
        if (pgmRtn) return;
        B060_02_PROC_VCH();
        if (pgmRtn) return;
        B060_03_WRITE_INSH();
        if (pgmRtn) return;
        B060_04_REWRITE_TMST();
        if (pgmRtn) return;
        B060_05_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B060_01_UPD_BUD_INT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRTMST.BUD_INT = IBRTMST.BUD_INT - IBCACFIS.ESET_AMT;
        } else {
            IBRTMST.BUD_INT = IBRTMST.BUD_INT + IBCACFIS.ESET_AMT;
        }
    }
    public void B060_02_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        B010_03_CHECK_PROD_CD();
        if (pgmRtn) return;
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = IBRTMST.POST_CTR;
        BPCPOEWA.DATA.BR_NEW = IBRTMST.POST_CTR;
        BPCPOEWA.DATA.CI_NO = IBCQINFS.CI_NO;
        BPCPOEWA.DATA.AC_NO = IBCQINFS.AC_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = IBCACFIS.CCY;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = IBCACFIS.ESET_AMT * 1;
        bigD = new BigDecimal(BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = IBCACFIS.ASET_AMT;
        BPCPOEWA.DATA.THEIR_AC = IBCACFIS.INTS_AC_NO;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PROD_CODE = IBRTMST.PROD_CD;
        BPCPOEWA.DATA.POST_NARR = "IBTD INT SETTL";
        BPCPOEWA.DATA.DESC = "IBTD INT SETTL";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        if (IBCACFIS.INTS_AC_ATTR == 'N') {
            B060_02_01_PROC_VCH();
            if (pgmRtn) return;
        } else {
            B060_02_02_PROC_VCH();
            if (pgmRtn) return;
        }
    }
    public void B060_02_01_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = IBCACFIS.INTS_AC_NO;
        IBCPOSTA.AMT = IBCACFIS.ASET_AMT;
        IBCPOSTA.CCY = IBCACFIS.CCY;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_NO = IBCQINFS.AC_NO;
        IBCPOSTA.VAL_DATE = IBCACFIS.INTS_DATE;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.TX_MMO = "S107";
        IBCPOSTA.NARR = IBCQINFS.PRIM_AC_NO + "+" + IBCQINFS.SEQ_NO;
        B040_05_03_GET_INFO();
        if (pgmRtn) return;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B060_02_02_PROC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = IBCACFIS.INTS_AC_NO;
        AICUUPIA.DATA.CCY = IBCACFIS.CCY;
        AICUUPIA.DATA.AMT = IBCACFIS.ASET_AMT;
        AICUUPIA.DATA.VALUE_DATE = IBCACFIS.INTS_DATE;
        AICUUPIA.DATA.SETTLE_DT = IBCACFIS.INTS_DATE;
        AICUUPIA.DATA.RVS_NO = IBCACFIS.SUSP_SEQ;
        AICUUPIA.DATA.PROD_CODE = IBCQINFS.PROD_CD;
        CEP.TRC(SCCGWA, IBCQINFS.AC_NO);
        AICUUPIA.DATA.THEIR_AC = IBCQINFS.PRIM_AC_NO;
        AICUUPIA.DATA.BR_OLD = IBCQINFS.POST_CTR;
        AICUUPIA.DATA.BR_NEW = IBCQINFS.POST_CTR;
        AICUUPIA.DATA.DESC = IBCQINFS.PRIM_AC_NO + IBCQINFS.SEQ_NO;
        AICUUPIA.DATA.PAY_MAN = IBCQINFS.CUSTNME;
        CEP.TRC(SCCGWA, IBCQINFS.CUSTNME);
        AICUUPIA.DATA.PAY_BR = IBCQINFS.POST_CTR;
        B060_02_02_01_GET_OTH_INFO();
        if (pgmRtn) return;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B060_02_02_01_GET_OTH_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINFS.POST_CTR;
        CEP.TRC(SCCGWA, IBCQINFS.POST_CTR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        AICUUPIA.DATA.PAY_BKNM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_BKNM);
    }
    public void B060_03_WRITE_INSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBCQINFS.AC_NO;
        CEP.TRC(SCCGWA, IBRINSH.KEY.AC_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            WS_SEQ_NO = 0;
            IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IBTINSH_FIRST();
            if (pgmRtn) return;
            IBRINSH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            IBRINSH.KEY.SEQ = WS_SEQ_NO;
            IBRINSH.INTS_DATE = IBCACFIS.INTS_DATE;
            IBRINSH.ESET_AMT = IBCACFIS.ESET_AMT;
            IBRINSH.ASET_AMT = IBCACFIS.ASET_AMT;
            IBRINSH.REV_FLG = 'N';
            IBRINSH.SETT_AC_NO = IBCACFIS.INTS_AC_NO;
            IBRINSH.SETT_AC_TYPE = IBCACFIS.INTS_AC_ATTR;
            IBRINSH.SETT_TYPE = '3';
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
            IBRINSH.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, IBRINSH.AUTH_TLR);
            IBRINSH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_IBTINSH();
            if (pgmRtn) return;
        } else {
            T000_READ_INSH_LAST_SETDAT();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INSH);
            }
            T000_READUPD_IBTINSH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INSH);
            }
            IBRINSH.REV_FLG = 'R';
            IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_IBTINSH();
            if (pgmRtn) return;
        }
    }
    public void B060_04_REWRITE_TMST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRTMST.DRW_INT = IBRTMST.DRW_INT + IBCACFIS.ASET_AMT;
            IBRTMST.LSET_DATE = IBCACFIS.INTS_DATE;
        } else {
            IBRTMST.DRW_INT = IBRTMST.DRW_INT - IBRINSH.ASET_AMT;
            T000_READ_INSH_LAST2_SETDAT();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                IBRTMST.LSET_DATE = IBRINSH.INTS_DATE;
            } else {
                IBRTMST.LSET_DATE = 0;
            }
        }
        IBRTMST.LAST_FI_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTTMST();
        if (pgmRtn) return;
    }
    public void B060_05_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCFIS);
        IBCOCFIS.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCOCFIS.PRIM_AC_NO = IBCQINFS.PRIM_AC_NO;
        IBCOCFIS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCFIS.SEQ_NO = IBRTMST.SEQ_NO;
        IBCOCFIS.CCY = IBRTMST.CCY;
        IBCOCFIS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCOCFIS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCOCFIS.RATE = IBRTMST.RATE;
        IBCOCFIS.LSET_DATE = IBRTMST.LSET_DATE;
        IBCOCFIS.BUD_INT = IBRTMST.BUD_INT * 1;
        bigD = new BigDecimal(IBCOCFIS.BUD_INT);
        IBCOCFIS.BUD_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.ESET_AMT = IBCACFIS.ESET_AMT * 1;
        bigD = new BigDecimal(IBCOCFIS.ESET_AMT);
        IBCOCFIS.ESET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.ASET_AMT = IBCACFIS.ASET_AMT * 1;
        bigD = new BigDecimal(IBCOCFIS.ASET_AMT);
        IBCOCFIS.ASET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCFIS.INTS_DATE = IBCACFIS.INTS_DATE;
        IBCOCFIS.INTS_AC_ATTR = IBCACFIS.INTS_AC_ATTR;
        IBCOCFIS.INTS_AC_NO = IBCACFIS.INTS_AC_NO;
        IBCOCFIS.SUSP_SEQ = IBCACFIS.SUSP_SEQ;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT3;
        SCCFMT.DATA_PTR = IBCOCFIS;
        SCCFMT.DATA_LEN = 764;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFT", IBCQINFT);
        if (IBCQINFT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFX.RC);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0 
            && CICCUST.RC.RC_CODE != 3011) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZUBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-UPD-BAL", IBCUBAL);
        if (IBCUBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCUBAL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ESET-AMT-INQ", IBCQEINT);
        if (IBCQEINT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQEINT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTMAIN1() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMAIN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMAIN2() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "CCY = :IBRTMAIN.CCY "
            + "AND NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMAIN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST1() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBS.READ(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST2() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = TMST_SEQ_NO";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST_FIRST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO";
        IBTTMST_RD.fst = true;
        IBTTMST_RD.order = "SEQ_NO DESC";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST_UPD() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "REV_FLG = 'N'";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.upd = true;
        IBS.READ(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBS.WRITE(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBS.REWRITE(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_INSH_LAST2_SETDAT() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND INTS_DATE < :IBCACFIS.INTS_DATE "
            + "AND REV_FLG = 'N'";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "INTS_DATE DESC, SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_INSH_LAST_SETDAT() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND REV_FLG = 'N'";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "DEAL_DATE DESC, SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINSH_FIRST() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND DEAL_DATE = :IBRINSH.KEY.DEAL_DATE";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SEQ_NO = IBRINSH.KEY.SEQ + 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SEQ_NO = WS_SEQ_NO + 1;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBS.WRITE(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBS.REWRITE(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINSH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTBALF_FIRST() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBTBALF_RD.where = "AC_NO = :IBRBALF.KEY.AC_NO";
        IBTBALF_RD.fst = true;
        IBTBALF_RD.order = "BAL_DATE DESC";
        IBS.READ(SCCGWA, IBRBALF, this, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTBALF_UPD() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBTBALF_RD.upd = true;
        IBS.READ(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.WRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.REWRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
