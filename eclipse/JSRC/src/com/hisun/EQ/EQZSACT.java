package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.DC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQZSACT {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm EQTFRZ_RD;
    DBParm EQTPLG_RD;
    DBParm EQTACT_RD;
    DBParm EQTBVT_RD;
    DBParm EQTACTD_RD;
    DBParm EQTFRZD_RD;
    DBParm EQTWDZ_RD;
    boolean pgmRtn = false;
    String K_CCY_CNY = "156";
    String K_BSZ_BANKID = "01";
    String K_OUTPUT_FMT_1 = "EQ101";
    String K_OUTPUT_FMT_3 = "EQ103";
    String K_BUSS_ENTITY = "2207010101";
    String K_EXT_NAT_PER = "1204010101";
    String K_STAFF = "1204020101";
    String K_OTHER = "2207020101";
    String K_ADD_REMARK = "ADD EQAC";
    String K_UPDATE_REMARK = "ENLARGE EQAC";
    String K_INHERIT_REMARK = "INHERIT EQAC";
    String K_QUIT_REMARK = "QUIT EQAC";
    String K_UPT_RSN = "Compulsory Deduction";
    String WS_MSGID = " ";
    char WS_ACT_FLG = ' ';
    char WS_ACTD_FLG = ' ';
    char WS_FRZ_FLG = ' ';
    char WS_FRZD_FLG = ' ';
    char WS_PLG_FLG = ' ';
    char WS_BVT_FLG = ' ';
    char WS_PAY_VCH = ' ';
    String WS_CI_NO = " ";
    String WS_SACT_CINO = " ";
    double WS_AVA_QTY = 0;
    double WS_ACT_QTY = 0;
    double WS_FRZ_QTY = 0;
    double WS_FRZD_QTY = 0;
    String WS_EQ_AC = " ";
    String WS_ACO_AC = " ";
    String WS_ACO_AC_OLD = " ";
    String WS_ACO_AC_CLOSE = " ";
    String WS_PSBK_NO = " ";
    char WS_EQ_TYP = ' ';
    char WS_EQ_TYP_OLD = ' ';
    char WS_EQ_TYP_NEW = ' ';
    String WS_TXN_EQAC = " ";
    String WS_TXN_CINO = " ";
    String WS_TXN_PRODCD = " ";
    char WS_PRT_FLG = ' ';
    char WS_PROC_FLG = ' ';
    char WS_DEDUCT_FLG = ' ';
    char WS_TXN_TYP = ' ';
    double WS_TXN_QTY = 0;
    double WS_TXN_PRI = 0;
    double WS_TXN_AMT = 0;
    String WS_TXN_REMARK = " ";
    String WS_TXN_EQ_CNM = " ";
    double WS_TMP_QTY = 0;
    double WS_TMP_PRI = 0;
    double WS_TMP_AMT = 0;
    double WS_BAL_QTY = 0;
    double WS_BAL_QTY1 = 0;
    double WS_BAL_QTY2 = 0;
    String WS_ACTD_AC = " ";
    String WS_ACTD_OPPAC = " ";
    int WS_UPT_NO = 0;
    String WS_TX_MMO = " ";
    char WS_BVT_STS = ' ';
    char WS_TX_DRCR_FLG = ' ';
    char WS_TX_TYP = ' ';
    String WS_HIS_AC = " ";
    int WS_VCH_BR = 0;
    EQZSACT_WS_OUT_DATA WS_OUT_DATA = new EQZSACT_WS_OUT_DATA();
    char WS_NORMAL_STS = 'N';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRACTD EQRACTD = new EQRACTD();
    EQRBVT EQRBVT = new EQRBVT();
    EQRWDZ EQRWDZ = new EQRWDZ();
    EQRFRZ EQRFRZ = new EQRFRZ();
    EQRFRZD EQRFRZD = new EQRFRZD();
    EQRPLG EQRPLG = new EQRPLG();
    EQCRACT EQCRACT = new EQCRACT();
    EQCRFRZ EQCRFRZ = new EQCRFRZ();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCCGAC BPCCGAC = new BPCCGAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACAC CICSACAC = new CICSACAC();
    CICSACR CICSACR = new CICSACR();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACAAC BPCACAAC = new BPCACAAC();
    CICCUST CICCUST = new CICCUST();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCSACT EQCSACT;
    public void MP(SCCGWA SCCGWA, EQCSACT EQCSACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCSACT = EQCSACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZSACT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        EQCSACT.RC.RC_MMO = "EQ";
        EQCSACT.RC.RC_CODE = 0;
        WS_TX_TYP = 'T';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_CHECK_INPUT_DATA();
            if (pgmRtn) return;
        }
        if (EQCSACT.FUNC == 'A') {
            B100_ADD_ACT_PROC();
            if (pgmRtn) return;
        } else if (EQCSACT.FUNC == 'E') {
            B200_ENLARGE_ACT_PROC();
            if (pgmRtn) return;
        } else if (EQCSACT.FUNC == 'I') {
            B300_INHERIT_ACT_PROC();
            if (pgmRtn) return;
        } else if (EQCSACT.FUNC == 'Q') {
            B400_QUIT_ACT_PROC();
            if (pgmRtn) return;
        } else if (EQCSACT.FUNC == 'C') {
            B500_UPDATE_CINO();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + EQCSACT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_ADD_ACT_PROC() throws IOException,SQLException,Exception {
        WS_TXN_TYP = '1';
        WS_TXN_CINO = EQCSACT.DATA.CI_NO;
        WS_TXN_PRODCD = EQCSACT.DATA.PROD_CD;
        WS_TXN_QTY = EQCSACT.DATA.SUB_QTY;
        WS_BAL_QTY = EQCSACT.DATA.SUB_QTY;
        WS_TXN_PRI = EQCSACT.DATA.SUB_PRI;
        WS_TXN_AMT = EQCSACT.DATA.SUB_AMT;
        WS_TXN_REMARK = K_ADD_REMARK;
        WS_SACT_CINO = EQCSACT.DATA.CI_NO;
        WS_ACTD_OPPAC = " ";
        WS_PSBK_NO = EQCSACT.DATA.PSBK_NO;
        WS_TX_MMO = "T005";
        WS_TX_DRCR_FLG = 'C';
        WS_VCH_BR = EQCSACT.DATA.ADD_BR;
        if (EQCSACT.DATA.FD_SRC == '5') {
            WS_TX_TYP = 'C';
            WS_HIS_AC = " ";
        } else {
            if (EQCSACT.DATA.FD_SRC == '3') {
                WS_HIS_AC = EQCSACT.DATA.BR_AC;
            } else {
                WS_HIS_AC = EQCSACT.DATA.PAY_AC;
            }
        }
        R000_GET_PROD_INF();
        if (pgmRtn) return;
        R000_GET_CIINF();
        if (pgmRtn) return;
        R000_CHECK_ADD();
        if (pgmRtn) return;
        R000_CHECK_DIV_AC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_GEN_EQ_AC();
            if (pgmRtn) return;
            EQCSACT.DATA.EQ_AC = WS_EQ_AC;
            WS_TXN_EQAC = WS_EQ_AC;
            WS_ACTD_AC = WS_EQ_AC;
            EQCSACT.GEQ_AC = WS_EQ_AC;
            R000_RELA_EQAC();
            if (pgmRtn) return;
            R000_GEN_ACO_AC();
            if (pgmRtn) return;
            EQCSACT.DATA.ACO_AC = WS_ACO_AC;
            EQCSACT.GACO_AC = WS_ACO_AC;
            R000_RELA_ACOAC();
            if (pgmRtn) return;
            R000_INQ_GL_MASTER();
            if (pgmRtn) return;
            R000_WRT_GL_MASTER();
            if (pgmRtn) return;
            B110_WRITE_EQTACT();
            if (pgmRtn) return;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                B120_WRITE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY;
                WS_TMP_AMT = WS_TXN_AMT;
                WS_PRT_FLG = 'Y';
                R000_WRITE_EQTWDZ1();
                if (pgmRtn) return;
            }
        } else {
            WS_TXN_TYP = '6';
            WS_BAL_QTY = 0;
            EQCSACT.DATA.EQ_AC = EQCSACT.GEQ_AC;
            WS_TXN_EQAC = EQCSACT.GEQ_AC;
            WS_ACTD_AC = EQCSACT.GEQ_AC;
            WS_EQ_AC = EQCSACT.GEQ_AC;
            WS_ACO_AC = EQCSACT.GACO_AC;
            R000_RELA_ACOAC();
            if (pgmRtn) return;
            R000_RELA_EQAC();
            if (pgmRtn) return;
            B140_UPDATE_EQTACT();
            if (pgmRtn) return;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                WS_BVT_STS = 'R';
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY * -1;
                WS_TMP_AMT = WS_TXN_AMT * -1;
                R000_WRITE_EQTWDZ2();
                if (pgmRtn) return;
            }
        }
        R000_WRITE_BPTOCAC();
        if (pgmRtn) return;
        R000_FINANCE_HIS();
        if (pgmRtn) return;
        R000_DR_PROC();
        if (pgmRtn) return;
        R000_GEN_BSVCH_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B130_FMT_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_ENLARGE_ACT_PROC() throws IOException,SQLException,Exception {
        WS_TXN_TYP = '2';
        WS_TXN_EQAC = EQCSACT.DATA.EQ_AC;
        WS_ACTD_AC = EQCSACT.DATA.EQ_AC;
        WS_TXN_CINO = EQCSACT.DATA.CI_NO;
        WS_TXN_PRODCD = EQCSACT.DATA.PROD_CD;
        WS_TXN_QTY = EQCSACT.DATA.EXP_QTY;
        WS_TXN_PRI = EQCSACT.DATA.EXP_PRI;
        WS_TXN_AMT = EQCSACT.DATA.EXP_AMT;
        WS_TXN_REMARK = K_UPDATE_REMARK;
        WS_ACTD_OPPAC = " ";
        WS_PSBK_NO = EQCSACT.DATA.PSBK_NO;
        WS_TX_MMO = "T006";
        WS_TX_DRCR_FLG = 'C';
        if (EQCSACT.DATA.FD_SRC == '5') {
            WS_TX_TYP = 'C';
            WS_HIS_AC = " ";
        } else {
            if (EQCSACT.DATA.FD_SRC == '3') {
                WS_HIS_AC = EQCSACT.DATA.BR_AC;
            } else {
                WS_HIS_AC = EQCSACT.DATA.PAY_AC;
            }
        }
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        WS_ACO_AC = EQRACT.ACO_AC;
        WS_EQ_TYP = EQRACT.EQ_TYP;
        WS_VCH_BR = EQRACT.OWNER_BK;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ACT_QTY = EQRACT.EQ_QTY + EQCSACT.DATA.EXP_QTY;
        } else {
            WS_ACT_QTY = EQRACT.EQ_QTY - EQCSACT.DATA.EXP_QTY;
            WS_TXN_TYP = '7';
        }
        WS_BAL_QTY = WS_ACT_QTY;
        CEP.TRC(SCCGWA, WS_ACT_QTY);
        EQRACT.AC_STS_WORD = "02";
        EQRACT.EQ_QTY = WS_ACT_QTY;
        EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        R000_WRITE_EQTACTD();
        if (pgmRtn) return;
        if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
            WS_BVT_STS = 'N';
            R000_UPDATE_EQTBVT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_TMP_QTY = WS_TXN_QTY;
                WS_TMP_AMT = WS_TXN_AMT;
                WS_PRT_FLG = 'N';
                R000_WRITE_EQTWDZ1();
                if (pgmRtn) return;
            } else {
                WS_TMP_QTY = WS_TXN_QTY * -1;
                WS_TMP_AMT = WS_TXN_AMT * -1;
                R000_WRITE_EQTWDZ2();
                if (pgmRtn) return;
            }
        }
        R000_GET_CIINF();
        if (pgmRtn) return;
        R000_FINANCE_HIS();
        if (pgmRtn) return;
        R000_DR_PROC();
        if (pgmRtn) return;
        R000_GET_PROD_INF();
        if (pgmRtn) return;
        R000_GEN_BSVCH_PROC();
        if (pgmRtn) return;
    }
    public void B300_INHERIT_ACT_PROC() throws IOException,SQLException,Exception {
        WS_TXN_TYP = '4';
        WS_TXN_EQAC = EQCSACT.DATA.EQ_AC;
        WS_TXN_CINO = EQCSACT.DATA.CI_NO;
        WS_TXN_PRODCD = EQCSACT.DATA.PROD_CD;
        WS_TXN_REMARK = K_INHERIT_REMARK;
        WS_SACT_CINO = EQCSACT.DATA.OCI_NO;
        WS_ACTD_OPPAC = " ";
        WS_PSBK_NO = EQCSACT.DATA.PSBK_NO;
        WS_TX_DRCR_FLG = 'C';
        R000_CHECK_DIV_AC();
        if (pgmRtn) return;
        R000_GET_PRICE();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (EQCSACT.DATA.FRZ_NO.trim().length() > 0) {
                R000_CHECK_FRZ_INF();
                if (pgmRtn) return;
            } else {
                R000_CHECK_QTY();
                if (pgmRtn) return;
            }
            if (EQCSACT.DATA.TRAN_FLG == '1') {
                WS_BAL_QTY = 0;
                WS_BAL_QTY1 = 0;
                WS_BVT_STS = 'C';
                WS_TXN_QTY = EQCSACT.DATA.EQ_QTY;
                R000_CHECK_PLG();
                if (pgmRtn) return;
                if (EQCSACT.DATA.FRZ_NO.trim().length() == 0) {
                    R000_CHECK_FRZ();
                    if (pgmRtn) return;
                } else {
                    EQRFRZ.REL_QTY = EQRFRZ.REL_QTY + EQRFRZ.FRZ_QTY;
                    EQRFRZ.FRZ_QTY = 0;
                    EQRFRZ.FRZ_STS = 'C';
                    EQRFRZ.REL_RSN = K_UPT_RSN;
                    EQRFRZ.ACT_REL_DT = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.RELEASE_DT = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EQCRFRZ.FUNC = 'U';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    WS_PROC_FLG = '4';
                    WS_DEDUCT_FLG = '2';
                    R000_WRITE_EQTFRZD();
                    if (pgmRtn) return;
                }
                R000_CLOSE_AC();
                if (pgmRtn) return;
                WS_EQ_TYP_OLD = EQRACT.EQ_TYP;
                WS_ACO_AC_OLD = EQRACT.ACO_AC;
                R000_UPDATE_BPTOCAC();
                if (pgmRtn) return;
            } else {
                WS_TXN_QTY = EQCSACT.DATA.TRAN_QTY;
                WS_BVT_STS = 'N';
                IBS.init(SCCGWA, EQRACT);
                IBS.init(SCCGWA, EQCRACT);
                EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                EQRACT.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
                EQCRACT.FUNC = 'R';
                S000_CALL_EQZRACT();
                if (pgmRtn) return;
                WS_ACO_AC = EQRACT.ACO_AC;
                WS_ACO_AC_OLD = EQRACT.ACO_AC;
                WS_EQ_TYP_OLD = EQRACT.EQ_TYP;
                if (EQCSACT.DATA.FRZ_NO.trim().length() > 0) {
                    WS_FRZ_QTY = EQRFRZ.REL_QTY + EQCSACT.DATA.TRAN_QTY;
                    EQRFRZ.REL_QTY = WS_FRZ_QTY;
                    WS_FRZ_QTY = EQCSACT.DATA.FRZ_QTY - EQCSACT.DATA.TRAN_QTY;
                    EQRACT.FRZ_QTY = WS_FRZ_QTY;
                    CEP.TRC(SCCGWA, WS_FRZ_QTY);
                    WS_FRZ_QTY = EQRFRZ.FRZ_QTY - EQCSACT.DATA.TRAN_QTY;
                    EQRFRZ.FRZ_QTY = WS_FRZ_QTY;
                    if (WS_FRZ_QTY == 0) {
                        EQRFRZ.FRZ_STS = 'C';
                        WS_DEDUCT_FLG = '2';
                    } else {
                        WS_DEDUCT_FLG = '1';
                    }
                    EQRFRZ.REL_RSN = K_UPT_RSN;
                    EQRFRZ.ACT_REL_DT = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.RELEASE_DT = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EQCRFRZ.FUNC = 'U';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    WS_PROC_FLG = '4';
                    R000_WRITE_EQTFRZD();
                    if (pgmRtn) return;
                }
                WS_ACT_QTY = EQCSACT.DATA.EQ_QTY - EQCSACT.DATA.TRAN_QTY;
                CEP.TRC(SCCGWA, WS_ACT_QTY);
                EQRACT.EQ_QTY = WS_ACT_QTY;
                WS_BAL_QTY = WS_ACT_QTY;
                WS_BAL_QTY1 = WS_ACT_QTY;
                EQRACT.AC_STS_WORD = "03";
                EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
                EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                EQCRACT.FUNC = 'U';
                S000_CALL_EQZRACT();
                if (pgmRtn) return;
            }
            WS_TXN_PRI = WS_TMP_PRI;
            WS_TXN_AMT = WS_TXN_QTY * WS_TXN_PRI;
            bigD = new BigDecimal(WS_TXN_AMT);
            WS_TXN_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_TXN_AMT);
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY * -1;
                WS_TMP_AMT = WS_TXN_AMT * -1;
                WS_PRT_FLG = 'N';
                R000_WRITE_EQTWDZ1();
                if (pgmRtn) return;
            }
        } else {
            if (EQCSACT.DATA.TRAN_FLG == '1') {
                IBS.init(SCCGWA, EQRACT);
                WS_ACT_FLG = ' ';
                EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                EQRACT.CI_NO = EQCSACT.DATA.CI_NO;
                T000_READ_EQTACT();
                if (pgmRtn) return;
                if (WS_ACT_FLG == 'Y') {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_ROLL_NALLOWE1;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_TXN_QTY = EQCSACT.DATA.EQ_QTY;
                WS_BAL_QTY = EQCSACT.DATA.EQ_QTY;
                if (EQCSACT.DATA.FRZ_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, EQRFRZ);
                    IBS.init(SCCGWA, EQCRFRZ);
                    EQRFRZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                    EQRFRZ.FRZ_NO = EQCSACT.DATA.FRZ_NO;
                    EQCRFRZ.FUNC = 'R';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    EQRFRZ.REL_QTY = EQRFRZ.REL_QTY - EQCSACT.DATA.FRZ_QTY;
                    EQRFRZ.FRZ_QTY = EQCSACT.DATA.FRZ_QTY;
                    EQRFRZ.FRZ_STS = 'N';
                    EQRFRZ.REL_RSN = " ";
                    EQRFRZ.ACT_REL_DT = 0;
                    EQRFRZ.RELEASE_DT = 0;
                    EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EQCRFRZ.FUNC = 'U';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    R000_UPDATE_EQTFRZD();
                    if (pgmRtn) return;
                    WS_PROC_FLG = '5';
                    WS_DEDUCT_FLG = '2';
                    R000_WRITE_EQTFRZD();
                    if (pgmRtn) return;
                }
                R000_CLOSE_AC();
                if (pgmRtn) return;
                WS_EQ_TYP_OLD = EQRACT.EQ_TYP;
                WS_ACO_AC_OLD = EQRACT.ACO_AC;
                R000_UPDATE_BPTOCAC();
                if (pgmRtn) return;
            } else {
                WS_TXN_QTY = EQCSACT.DATA.TRAN_QTY;
                IBS.init(SCCGWA, EQRACT);
                IBS.init(SCCGWA, EQCRACT);
                EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                EQRACT.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
                EQCRACT.FUNC = 'R';
                S000_CALL_EQZRACT();
                if (pgmRtn) return;
                WS_ACO_AC = EQRACT.ACO_AC;
                WS_ACO_AC_OLD = EQRACT.ACO_AC;
                WS_EQ_TYP_OLD = EQRACT.EQ_TYP;
                if (EQCSACT.DATA.FRZ_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, EQRFRZ);
                    IBS.init(SCCGWA, EQCRFRZ);
                    EQRFRZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                    EQRFRZ.FRZ_NO = EQCSACT.DATA.FRZ_NO;
                    EQCRFRZ.FUNC = 'R';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    WS_FRZ_QTY = EQRFRZ.REL_QTY - EQCSACT.DATA.TRAN_QTY;
                    EQRFRZ.REL_QTY = WS_FRZ_QTY;
                    EQRACT.FRZ_QTY = EQCSACT.DATA.FRZ_QTY;
                    WS_FRZ_QTY = EQRFRZ.FRZ_QTY + EQCSACT.DATA.TRAN_QTY;
                    EQRFRZ.FRZ_QTY = WS_FRZ_QTY;
                    if (EQRFRZ.FRZ_STS == 'C') {
                        EQRFRZ.FRZ_STS = 'N';
                        WS_DEDUCT_FLG = '2';
                    } else {
                        WS_DEDUCT_FLG = '1';
                    }
                    EQRFRZ.REL_RSN = " ";
                    EQRFRZ.ACT_REL_DT = 0;
                    EQRFRZ.RELEASE_DT = 0;
                    EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    EQCRFRZ.FUNC = 'U';
                    S000_CALL_EQZRFRZ();
                    if (pgmRtn) return;
                    R000_UPDATE_EQTFRZD();
                    if (pgmRtn) return;
                    WS_PROC_FLG = '5';
                    R000_WRITE_EQTFRZD();
                    if (pgmRtn) return;
                }
                EQRACT.EQ_QTY = EQCSACT.DATA.EQ_QTY;
                WS_BAL_QTY = EQCSACT.DATA.EQ_QTY;
                EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
                EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                EQCRACT.FUNC = 'U';
                S000_CALL_EQZRACT();
                if (pgmRtn) return;
            }
            WS_TXN_PRI = WS_TMP_PRI;
            WS_TXN_AMT = WS_TXN_QTY * WS_TXN_PRI;
            bigD = new BigDecimal(WS_TXN_AMT);
            WS_TXN_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_TXN_AMT);
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                WS_BVT_STS = 'N';
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY;
                WS_TMP_AMT = WS_TXN_AMT;
                R000_WRITE_EQTWDZ2();
                if (pgmRtn) return;
            }
        }
        R000_GET_PROD_INF();
        if (pgmRtn) return;
        B310_VCH_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TXN_QTY);
        WS_TXN_EQAC = EQCSACT.DATA.OEQ_AC;
        WS_TXN_CINO = EQCSACT.DATA.OCI_NO;
        WS_TXN_PRODCD = EQCSACT.DATA.OPROD_CD;
        WS_PSBK_NO = EQCSACT.DATA.OPSBK_NO;
        WS_TX_MMO = "T102";
        WS_TXN_TYP = '5';
        if (EQCSACT.DATA.OEQ_AC.trim().length() > 0) {
            WS_BVT_STS = 'N';
            IBS.init(SCCGWA, EQRACT);
            IBS.init(SCCGWA, EQCRACT);
            WS_ACT_QTY = 0;
            EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
            EQRACT.KEY.EQ_AC = EQCSACT.DATA.OEQ_AC;
            EQCRACT.FUNC = 'R';
            S000_CALL_EQZRACT();
            if (pgmRtn) return;
            WS_EQ_TYP_NEW = EQRACT.EQ_TYP;
            WS_VCH_BR = EQRACT.OWNER_BK;
            if (EQRACT.AC_STS == 'C') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!EQRACT.PROD_CD.equalsIgnoreCase(EQCSACT.DATA.OPROD_CD)) {
                WS_MSGID = EQCMSG_ERROR_MSG.PROD_CD_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQRACT.ADD_BR != EQCSACT.DATA.ADD_BR) {
                WS_MSGID = EQCMSG_ERROR_MSG.ADD_BR_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ACO_AC = EQRACT.ACO_AC;
            CEP.TRC(SCCGWA, WS_ACO_AC);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_ACT_QTY = EQRACT.EQ_QTY + WS_TXN_QTY;
            } else {
                WS_ACT_QTY = EQRACT.EQ_QTY - WS_TXN_QTY;
            }
            CEP.TRC(SCCGWA, WS_ACT_QTY);
            EQRACT.EQ_QTY = WS_ACT_QTY;
            WS_BAL_QTY = WS_ACT_QTY;
            WS_BAL_QTY2 = WS_ACT_QTY;
            EQRACT.AC_STS_WORD = "03";
            EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
            EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            EQCRACT.FUNC = 'U';
            S000_CALL_EQZRACT();
            if (pgmRtn) return;
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    WS_TMP_QTY = WS_TXN_QTY;
                    WS_TMP_AMT = WS_TXN_AMT;
                    WS_PRT_FLG = 'N';
                    R000_WRITE_EQTWDZ1();
                    if (pgmRtn) return;
                } else {
                    WS_TMP_QTY = WS_TXN_QTY * -1;
                    WS_TMP_AMT = WS_TXN_AMT * -1;
                    R000_WRITE_EQTWDZ2();
                    if (pgmRtn) return;
                }
            }
            WS_HIS_AC = EQCSACT.DATA.EQ_AC;
            R000_FINANCE_HIS();
            if (pgmRtn) return;
        } else {
            WS_BAL_QTY = WS_TXN_QTY;
            WS_BAL_QTY2 = WS_TXN_QTY;
            WS_VCH_BR = EQCSACT.DATA.ADD_BR;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                IBS.init(SCCGWA, EQRACT);
                WS_ACT_FLG = ' ';
                EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                EQRACT.CI_NO = EQCSACT.DATA.OCI_NO;
                T000_READ_EQTACT();
                if (pgmRtn) return;
                if (WS_ACT_FLG == 'Y') {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_AC_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                    IBS.init(SCCGWA, EQRBVT);
                    WS_BVT_FLG = ' ';
                    EQRBVT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                    EQRBVT.KEY.PSBK_NO = EQCSACT.DATA.OPSBK_NO;
                    T000_READ_EQTBVT();
                    if (pgmRtn) return;
                    if (WS_BVT_FLG == 'Y') {
                        WS_MSGID = EQCMSG_ERROR_MSG.EQ_BVT_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                R000_GET_PROD_INF();
                if (pgmRtn) return;
                R000_GET_CIINF();
                if (pgmRtn) return;
                WS_EQ_TYP_NEW = WS_EQ_TYP;
                R000_CHECK_ADD();
                if (pgmRtn) return;
                R000_GEN_EQ_AC();
                if (pgmRtn) return;
                EQCSACT.DATA.OEQ_AC = WS_EQ_AC;
                WS_TXN_EQAC = WS_EQ_AC;
                WS_ACTD_AC = WS_EQ_AC;
                EQCSACT.GEQ_AC = WS_EQ_AC;
                R000_RELA_EQAC();
                if (pgmRtn) return;
                R000_GEN_ACO_AC();
                if (pgmRtn) return;
                EQCSACT.DATA.OACO_AC = WS_ACO_AC;
                EQCSACT.GACO_AC = WS_ACO_AC;
                R000_RELA_ACOAC();
                if (pgmRtn) return;
                R000_INQ_GL_MASTER();
                if (pgmRtn) return;
                R000_WRT_GL_MASTER();
                if (pgmRtn) return;
                B110_WRITE_EQTACT();
                if (pgmRtn) return;
                if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                    B120_WRITE_EQTBVT();
                    if (pgmRtn) return;
                    WS_TMP_QTY = WS_TXN_QTY;
                    WS_TMP_AMT = WS_TXN_AMT;
                    WS_PRT_FLG = 'N';
                    R000_WRITE_EQTWDZ1();
                    if (pgmRtn) return;
                }
            } else {
                WS_BAL_QTY = 0;
                WS_BAL_QTY2 = 0;
                WS_TXN_EQAC = EQCSACT.GEQ_AC;
                WS_EQ_AC = EQCSACT.GEQ_AC;
                WS_ACO_AC = EQCSACT.GACO_AC;
                R000_RELA_ACOAC();
                if (pgmRtn) return;
                R000_RELA_EQAC();
                if (pgmRtn) return;
                B140_UPDATE_EQTACT();
                if (pgmRtn) return;
                if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                    WS_BVT_STS = 'R';
                    R000_UPDATE_EQTBVT();
                    if (pgmRtn) return;
                    WS_TMP_QTY = WS_TXN_QTY * -1;
                    WS_TMP_AMT = WS_TXN_AMT * -1;
                    R000_WRITE_EQTWDZ2();
                    if (pgmRtn) return;
                }
            }
            R000_GET_CIINF();
            if (pgmRtn) return;
            R000_WRITE_BPTOCAC();
            if (pgmRtn) return;
            WS_HIS_AC = EQCSACT.DATA.EQ_AC;
            R000_FINANCE_HIS();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B150_FMT_OUTPUT_PROC();
            if (pgmRtn) return;
        }
        R000_GET_PROD_INF();
        if (pgmRtn) return;
        B320_VCH_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ACTD_AC = WS_TXN_EQAC;
            WS_ACTD_OPPAC = EQCSACT.DATA.EQ_AC;
            WS_TXN_TYP = '5';
            WS_EQ_TYP = WS_EQ_TYP_NEW;
            WS_BAL_QTY = WS_BAL_QTY2;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            WS_ACTD_AC = EQCSACT.DATA.EQ_AC;
            WS_ACTD_OPPAC = WS_TXN_EQAC;
            WS_TXN_TYP = '4';
            WS_EQ_TYP = WS_EQ_TYP_OLD;
            WS_BAL_QTY = WS_BAL_QTY1;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
        } else {
            WS_ACTD_AC = WS_TXN_EQAC;
            WS_ACTD_OPPAC = EQCSACT.DATA.EQ_AC;
            WS_TXN_TYP = '0';
            WS_EQ_TYP = WS_EQ_TYP_NEW;
            WS_BAL_QTY = WS_BAL_QTY2;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            WS_ACTD_AC = EQCSACT.DATA.EQ_AC;
            WS_ACTD_OPPAC = WS_TXN_EQAC;
            WS_TXN_TYP = '9';
            WS_EQ_TYP = WS_EQ_TYP_OLD;
            WS_BAL_QTY = EQCSACT.DATA.EQ_QTY;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
        }
        WS_HIS_AC = WS_TXN_EQAC;
        WS_TXN_EQAC = EQCSACT.DATA.EQ_AC;
        WS_ACO_AC = WS_ACO_AC_OLD;
        WS_TXN_PRODCD = EQCSACT.DATA.PROD_CD;
        WS_TXN_CINO = EQCSACT.DATA.CI_NO;
        WS_TX_MMO = "T101";
        WS_TX_DRCR_FLG = 'D';
        R000_FINANCE_HIS();
        if (pgmRtn) return;
    }
    public void B400_QUIT_ACT_PROC() throws IOException,SQLException,Exception {
        WS_TXN_TYP = '3';
        WS_TXN_EQAC = EQCSACT.DATA.EQ_AC;
        WS_ACTD_AC = EQCSACT.DATA.EQ_AC;
        WS_TXN_CINO = EQCSACT.DATA.CI_NO;
        WS_TXN_PRODCD = EQCSACT.DATA.PROD_CD;
        WS_TXN_QTY = EQCSACT.DATA.WDAL_QTY;
        WS_TXN_PRI = EQCSACT.DATA.WDAL_PRI;
        WS_TXN_AMT = EQCSACT.DATA.WDAL_AMT;
        WS_TXN_REMARK = K_QUIT_REMARK;
        WS_SACT_CINO = EQCSACT.DATA.CI_NO;
        WS_ACTD_OPPAC = " ";
        WS_PSBK_NO = EQCSACT.DATA.PSBK_NO;
        WS_BAL_QTY = 0;
        WS_TX_MMO = "T007";
        WS_TX_DRCR_FLG = 'D';
        R000_CHECK_PAY_AC();
        if (pgmRtn) return;
        R000_CHECK_FRZ();
        if (pgmRtn) return;
        R000_CHECK_PLG();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, EQRACT);
            WS_ACT_FLG = ' ';
            EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
            EQRACT.CI_NO = EQCSACT.DATA.CI_NO;
            T000_READ_EQTACT();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'Y') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_ROLL_NALLOWE2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_CLOSE_AC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                WS_BVT_STS = 'C';
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY * -1;
                WS_TMP_AMT = WS_TXN_AMT * -1;
                WS_PRT_FLG = 'N';
                R000_WRITE_EQTWDZ1();
                if (pgmRtn) return;
            }
        } else {
            WS_TXN_TYP = '8';
            WS_BAL_QTY = EQCSACT.DATA.EQ_QTY;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                WS_BVT_STS = 'N';
                R000_UPDATE_EQTBVT();
                if (pgmRtn) return;
                WS_TMP_QTY = WS_TXN_QTY;
                WS_TMP_AMT = WS_TXN_AMT;
                R000_WRITE_EQTWDZ2();
                if (pgmRtn) return;
            }
        }
        R000_UPDATE_BPTOCAC();
        if (pgmRtn) return;
        WS_HIS_AC = EQCSACT.DATA.DIV_AC;
        R000_FINANCE_HIS();
        if (pgmRtn) return;
        R000_CR_PROC();
        if (pgmRtn) return;
        R000_GET_PROD_INF();
        if (pgmRtn) return;
        R000_GEN_WIVCH_PROC();
        if (pgmRtn) return;
    }
    public void B500_UPDATE_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = EQCSACT.DATA.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = EQCSACT.DATA.OCI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACT.CI_NO = EQCSACT.DATA.CI_NO;
        T000_READ_EQTACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            WS_EQ_AC = EQRACT.KEY.EQ_AC;
            IBS.init(SCCGWA, EQRACT);
            IBS.init(SCCGWA, EQCRACT);
            EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
            EQRACT.KEY.EQ_AC = WS_EQ_AC;
            EQCRACT.FUNC = 'R';
            S000_CALL_EQZRACT();
            if (pgmRtn) return;
            EQRACT.CI_NO = EQCSACT.DATA.OCI_NO;
            EQCRACT.FUNC = 'U';
            S000_CALL_EQZRACT();
            if (pgmRtn) return;
        }
    }
    public void B110_WRITE_EQTACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = WS_EQ_AC;
        EQRACT.ACO_AC = WS_ACO_AC;
        EQRACT.PROD_CD = WS_TXN_PRODCD;
        EQRACT.CI_NO = WS_TXN_CINO;
        EQRACT.EQ_TYP = WS_EQ_TYP;
        EQRACT.TEL_NO = EQCSACT.DATA.TEL_NO;
        EQRACT.EQ_ADDR = EQCSACT.DATA.EQ_ADDR;
        EQRACT.CCY = K_CCY_CNY;
        EQRACT.EQ_QTY = WS_TXN_QTY;
        EQRACT.FRZ_QTY = 0;
        EQRACT.PLG_QTY = 0;
        EQRACT.AC_STS = 'N';
        EQRACT.AC_STS_WORD = "01";
        EQRACT.DIV_AC = EQCSACT.DATA.DIV_AC;
        EQRACT.LS_DIVIDEND_DT = 0;
        EQRACT.LS_DIV_PROC_DT = 0;
        EQRACT.LS_DIV_AMT = 0;
        EQRACT.LS_COUPON_DT = 0;
        EQRACT.LS_CPN_PROC_DT = 0;
        EQRACT.LS_CPN_QTY = 0;
        EQRACT.ADD_BR = EQCSACT.DATA.ADD_BR;
        EQRACT.ADD_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRACT.ADD_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.WDAL_BR = 0;
        EQRACT.WDAL_TLR = "" + 0;
        JIBS_tmp_int = EQRACT.WDAL_TLR.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) EQRACT.WDAL_TLR = "0" + EQRACT.WDAL_TLR;
        EQRACT.WDAL_DT = 0;
        EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.REMARK = " ";
        EQRACT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (EQCSACT.BAT_FLG == 'Y') {
            EQRACT.CRT_TLR = "BAT001";
        } else {
            EQRACT.CRT_TLR = EQCSACT.ADD_TLR;
        }
        EQRACT.OWNER_BK = EQCSACT.DATA.ADD_BR;
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (EQCSACT.BAT_FLG == 'Y') {
            EQRACT.UPDTBL_TLR = "BAT001";
        } else {
            EQRACT.UPDTBL_TLR = EQCSACT.ADD_TLR;
        }
        EQRACT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRACT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRACT.TS = "0" + EQRACT.TS;
        EQCRACT.FUNC = 'A';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
    }
    public void B120_WRITE_EQTBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        EQRBVT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRBVT.KEY.EQ_AC = WS_TXN_EQAC;
        EQRBVT.KEY.PSBK_NO = WS_PSBK_NO;
        EQRBVT.PSBK_STS = 'N';
        if (WS_EQ_TYP == '2') {
            EQRBVT.PSBK_TYP = "001";
        } else {
            EQRBVT.PSBK_TYP = "002";
        }
        EQRBVT.PSBK_SEQ = 0;
        EQRBVT.PRT_LINE = 0;
        EQRBVT.UPT_CNT = 0;
        WS_UPT_NO = 1;
        EQRBVT.UPT_TYP = ' ';
        EQRBVT.PT_INCNT = 0;
        EQRBVT.PT_CHCNT = 1;
        EQRBVT.PT_BOCNT = 0;
        EQRBVT.UT_INCNT = 0;
        EQRBVT.UT_CHCNT = 0;
        EQRBVT.UT_BOCNT = 0;
        EQRBVT.UPT_STR_NO = 0;
        EQRBVT.UPT_LAST_NO = 0;
        EQRBVT.LAST_PB_CCY = "" + 0;
        JIBS_tmp_int = EQRBVT.LAST_PB_CCY.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) EQRBVT.LAST_PB_CCY = "0" + EQRBVT.LAST_PB_CCY;
        EQRBVT.PAY_TYPE = ' ';
        EQRBVT.CI_PSWD_FLG = ' ';
        EQRBVT.PAY_PSWD = " ";
        EQRBVT.PSWD_ERR_NUM = 0;
        EQRBVT.PSWD_ERR_DATE = 0;
        EQRBVT.PSBK_RANGE = ' ';
        EQRBVT.CHG_RSN = " ";
        EQRBVT.REMARK = " ";
        EQRBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRBVT.OWNER_BK = EQCSACT.DATA.ADD_BR;
        EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRBVT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRBVT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRBVT.TS = "0" + EQRBVT.TS;
        T000_WRITE_EQTBVT();
        if (pgmRtn) return;
    }
    public void B130_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_1;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B140_UPDATE_EQTACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = WS_TXN_EQAC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        if (EQRACT.FRZ_QTY != 0 
            || EQRACT.PLG_QTY != 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_CANNOT_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_EQ_TYP = EQRACT.EQ_TYP;
        EQRACT.EQ_QTY = 0;
        EQRACT.AC_STS = 'R';
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
    }
    public void B150_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_3;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_UPDATE_EQTBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        WS_BVT_FLG = ' ';
        EQRBVT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRBVT.KEY.EQ_AC = WS_TXN_EQAC;
        EQRBVT.KEY.PSBK_NO = WS_PSBK_NO;
        T000_READUP_EQTBVT();
        if (pgmRtn) return;
        WS_UPT_NO = EQRBVT.PT_CHCNT + EQRBVT.UT_CHCNT + 1;
        EQRBVT.UPT_CNT = EQRBVT.UPT_CNT + 1;
        EQRBVT.UT_CHCNT = EQRBVT.UT_CHCNT + 1;
        EQRBVT.PSBK_STS = WS_BVT_STS;
        T000_REWRITE_EQTBVT();
        if (pgmRtn) return;
    }
    public void B310_VCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TXN_QTY);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = WS_TXN_PRODCD;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.DESC = "088";
        BPCPOEWA.DATA.BR_OLD = EQRACT.OWNER_BK;
        BPCPOEWA.DATA.BR_NEW = EQRACT.OWNER_BK;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = K_CCY_CNY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TXN_QTY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = WS_ACO_AC;
        BPCPOEWA.DATA.CI_NO = WS_TXN_CINO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B320_VCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TXN_QTY);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = WS_TXN_PRODCD;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.DESC = "088";
        BPCPOEWA.DATA.BR_OLD = WS_VCH_BR;
        BPCPOEWA.DATA.BR_NEW = WS_VCH_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = K_CCY_CNY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TXN_QTY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = WS_ACO_AC;
        BPCPOEWA.DATA.CI_NO = WS_TXN_CINO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.CI_NO.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_CI_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.FUNC == 'C' 
            && EQCSACT.DATA.OCI_NO.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_CI_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.FUNC == 'A' 
            || EQCSACT.FUNC == 'E') {
            if (EQCSACT.DATA.FD_SRC == '1') {
                R000_CHECK_DCAC();
                if (pgmRtn) return;
            } else if (EQCSACT.DATA.FD_SRC == '2') {
                R000_CHECK_PAYAC();
                if (pgmRtn) return;
            } else if (EQCSACT.DATA.FD_SRC == '3') {
                R000_CHECK_BRAC();
                if (pgmRtn) return;
            } else if (EQCSACT.DATA.FD_SRC == '4') {
                R000_CHECK_DDAC();
                if (pgmRtn) return;
            } else if (EQCSACT.DATA.FD_SRC == '5') {
            } else {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_FD_SRC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (EQCSACT.FUNC == 'A') {
            IBS.init(SCCGWA, EQRACT);
            WS_ACT_FLG = ' ';
            EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
            EQRACT.CI_NO = EQCSACT.DATA.CI_NO;
            T000_READ_EQTACT();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'Y') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_CI_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                if (EQCSACT.BAT_FLG == 'Y') {
                    IBS.init(SCCGWA, BPCFQFBV);
                    BPCFQFBV.TLR = EQCSACT.ADD_TLR;
                    BPCFQFBV.TYPE = '0';
                    if (EQCSACT.DATA.PROD_CD.equalsIgnoreCase(K_BUSS_ENTITY)) {
                        BPCFQFBV.BV_CODE = "085";
                    } else {
                        BPCFQFBV.BV_CODE = "086";
                    }
                    S000_CALL_BPZFQFBV();
                    if (pgmRtn) return;
                    EQCSACT.DATA.PSBK_NO = BPCFQFBV.BEG_NO;
                }
                CEP.TRC(SCCGWA, EQCSACT.DATA.PSBK_NO);
                IBS.init(SCCGWA, EQRBVT);
                WS_BVT_FLG = ' ';
                EQRBVT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
                EQRBVT.KEY.PSBK_NO = EQCSACT.DATA.PSBK_NO;
                T000_READ_EQTBVT();
                if (pgmRtn) return;
                if (WS_BVT_FLG == 'Y') {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_BVT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (EQCSACT.FUNC == 'E' 
            || EQCSACT.FUNC == 'I' 
            || EQCSACT.FUNC == 'Q') {
            IBS.init(SCCGWA, EQRACT);
            IBS.init(SCCGWA, EQCRACT);
            EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
            EQRACT.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
            EQCRACT.FUNC = 'Q';
            S000_CALL_EQZRACT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
            CEP.TRC(SCCGWA, EQRACT.EQ_ACT);
            CEP.TRC(SCCGWA, EQRACT.EQ_CINO);
            CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
            CEP.TRC(SCCGWA, EQRACT.FRZ_QTY);
            CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
            if (!EQRACT.KEY.EQ_AC.equalsIgnoreCase(EQCSACT.DATA.EQ_AC) 
                || !EQRACT.EQ_ACT.equalsIgnoreCase(EQCSACT.DATA.EQ_ACT) 
                || !EQRACT.EQ_CINO.equalsIgnoreCase(EQCSACT.DATA.EQ_CINO) 
                || EQRACT.EQ_QTY != EQCSACT.DATA.EQ_QTY 
                || EQRACT.FRZ_QTY != EQCSACT.DATA.FRZ_QTY 
                || EQRACT.PLG_QTY != EQCSACT.DATA.PLG_QTY) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQRACT.AC_STS == 'C') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQRACT.AC_STS == 'R') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_AC_EROOR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_DCAC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.PAY_AC.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = EQCSACT.DATA.PAY_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = EQCSACT.DATA.PAY_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STS == '0' 
            || DCCUCINF.CARD_STS == '1' 
            || DCCUCINF.CARD_STS == 'C') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EQCSACT.DATA.PAY_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        if (!WS_CI_NO.equalsIgnoreCase(EQCSACT.DATA.CI_NO)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PAYAC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.PAY_AC.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = EQCSACT.DATA.PAY_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = EQCSACT.DATA.PAY_AC;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (DDCIMMST.DATA.AC_STS == 'C') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
        if (DDCIMMST.DATA.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIMMST.DATA.AC_TYPE == 'B') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_B;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EQCSACT.DATA.PAY_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        if (!WS_CI_NO.equalsIgnoreCase(EQCSACT.DATA.CI_NO)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BRAC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.BR_AC.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_BR_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = EQCSACT.DATA.BR_AC;
        AICPQMIB.INPUT_DATA.CCY = K_CCY_CNY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DDAC() throws IOException,SQLException,Exception {
        WS_PAY_VCH = EQCSACT.DATA.PAY_VCH;
        if ((WS_PAY_VCH != '1' 
            && WS_PAY_VCH != '2')) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_PAY_VCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (EQCSACT.DATA.PAY_VCH == '2') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYVCH_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_CHECK_PAYAC();
        if (pgmRtn) return;
        if (EQCSACT.DATA.PAY_VCH == '1') {
            if (EQCSACT.DATA.BLL_KND.trim().length() == 0) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_BLL_KND_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQCSACT.DATA.BLL_NO.trim().length() == 0) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_BLL_NO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (EQCSACT.DATA.SIGN_DT == 0) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_SIGN_DT_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_DIV_AC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.DIV_AC.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_DIV_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EQCSACT.DATA.DIV_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_SACT_CINO);
        if (!WS_CI_NO.equalsIgnoreCase(WS_SACT_CINO)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_DIV_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = EQCSACT.DATA.DIV_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = EQCSACT.DATA.DIV_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            if (DDCIMMST.DATA.AC_STS == 'C') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_TYPE == 'B') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_DDAC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = EQCSACT.DATA.DIV_AC;
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                if (DCCUCINF.CARD_STS == '0' 
                    || DCCUCINF.CARD_STS == '1' 
                    || DCCUCINF.CARD_STS == 'C') {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_DDAC_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_DIVAC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_PAY_AC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.DIV_AC.trim().length() == 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = EQCSACT.DATA.DIV_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_SACT_CINO);
        if (!WS_CI_NO.equalsIgnoreCase(WS_SACT_CINO)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAY_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = EQCSACT.DATA.DIV_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = EQCSACT.DATA.DIV_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            if (DDCIMMST.DATA.AC_STS == 'C') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.DATA.AC_TYPE == 'B') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_B;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = EQCSACT.DATA.DIV_AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == '0' 
                || DCCUCINF.CARD_STS == '1' 
                || DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PAYAC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_FRZ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRFRZ.FRZ_NO = EQCSACT.DATA.FRZ_NO;
        EQCRFRZ.FUNC = 'R';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        WS_FRZD_QTY = EQRFRZ.FRZ_QTY;
        if (!EQRFRZ.KEY.EQ_AC.equalsIgnoreCase(EQCSACT.DATA.EQ_AC)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_FRZ_EQAC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRFRZ.FRZ_STS == 'C') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_FRZ_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.DATA.TRAN_QTY > EQRFRZ.FRZ_QTY) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_FRZ_QTY_LESS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.DATA.TRAN_QTY > EQCSACT.DATA.EQ_QTY) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_QTY_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!EQRFRZ.SPR_NM.equalsIgnoreCase(EQCSACT.DATA.FRC_NAME)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_SPRNM_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_FRZ() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.FRZ_QTY > 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_STS_FRZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EQRFRZ);
        WS_FRZ_FLG = ' ';
        EQRFRZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRFRZ.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQRFRZ.FRZ_STS = 'N';
        T000_READ_EQTFRZ_EQAC();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'Y') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_STS_FRZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PLG() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.PLG_QTY > 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_STS_PLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EQRPLG);
        WS_PLG_FLG = ' ';
        EQRPLG.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRPLG.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQRPLG.PLG_STS = 'N';
        T000_READ_EQTPLG();
        if (pgmRtn) return;
        if (WS_PLG_FLG == 'Y') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_STS_PLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_QTY() throws IOException,SQLException,Exception {
        WS_AVA_QTY = EQCSACT.DATA.EQ_QTY - EQCSACT.DATA.FRZ_QTY - EQCSACT.DATA.PLG_QTY;
        CEP.TRC(SCCGWA, WS_AVA_QTY);
        if (WS_AVA_QTY < EQCSACT.DATA.TRAN_QTY) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_QTY_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_ADD() throws IOException,SQLException,Exception {
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            if (WS_EQ_TYP != '1' 
                && WS_EQ_TYP != '3') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_CIINF_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_EQ_TYP != '2') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_CIINF_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICCUST.O_DATA.O_EP_FLG != 'Y' 
            && WS_EQ_TYP == '3') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_YZ_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_EQ_TYP == '3') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CICCUST.O_DATA.O_OWNER_BK;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(EQCSACT.DATA.EQ_BKID)) {
            } else {
                if (BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
                    && EQCSACT.DATA.EQ_BKID.equalsIgnoreCase("01")) {
                } else {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_BK_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (!EQCSACT.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
            IBS.init(SCCGWA, BPCUBUSE);
            BPCUBUSE.TLR = EQCSACT.ADD_TLR;
            BPCUBUSE.TYPE = '0';
            if (WS_EQ_TYP == '2') {
                BPCUBUSE.BV_CODE = "085";
            } else {
                BPCUBUSE.BV_CODE = "086";
            }
            BPCUBUSE.BEG_NO = WS_PSBK_NO;
            BPCUBUSE.END_NO = WS_PSBK_NO;
            BPCUBUSE.NUM = 1;
            S000_CALL_BPZUBUSE();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        EQRFRZD.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = EQCSACT.DATA.FRZ_NO;
        EQRFRZD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRFRZD.PREV_FRZ_QTY = WS_FRZD_QTY;
        EQRFRZD.TXN_QTY = WS_TXN_QTY;
        EQRFRZD.PROC_FLG = WS_PROC_FLG;
        EQRFRZD.HLD_SEQ = 1;
        EQRFRZD.UPT_NM = EQCSACT.DATA.FRC_NAME;
        EQRFRZD.UPT_NO = EQCSACT.DATA.FRC_WRIT;
        EQRFRZD.UPT_RSN = K_UPT_RSN;
        EQRFRZD.DEDUCT_FLG = WS_DEDUCT_FLG;
        EQRFRZD.RVS_FLG = ' ';
        EQRFRZD.LAW_NM1 = EQCSACT.DATA.LAW_NM1;
        EQRFRZD.LAW_BNO1 = EQCSACT.DATA.LAW_BNO1;
        EQRFRZD.LAW_ENO1 = EQCSACT.DATA.LAW_ENO1;
        EQRFRZD.LAW_NM2 = EQCSACT.DATA.LAW_NM2;
        EQRFRZD.LAW_BNO2 = EQCSACT.DATA.LAW_BNO2;
        EQRFRZD.LAW_ENO2 = EQCSACT.DATA.LAW_ENO2;
        EQRFRZD.CHNL_NO = " ";
        EQRFRZD.REMARK = " ";
        EQRFRZD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRFRZD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRFRZD.TS = "0" + EQRFRZD.TS;
        T000_WRITE_EQTFRZD();
        if (pgmRtn) return;
    }
    public void R000_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACAAC);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        BPCACAAC.PROD_TYPE = WS_TXN_PRODCD;
        BPCACAAC.CI_TYPE = CICCUST.O_DATA.O_ID_TYPE.charAt(0);
        BPCACAAC.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        BPCACAAC.AC_TYP = 'A';
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void R000_WRT_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = WS_ACO_AC;
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCUCNGM.PROD_TYPE = WS_TXN_PRODCD;
        BPCUCNGM.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void R000_DR_PROC() throws IOException,SQLException,Exception {
        if (EQCSACT.DATA.FD_SRC == '1' 
            || EQCSACT.DATA.FD_SRC == '2' 
            || EQCSACT.DATA.FD_SRC == '4') {
            R000_DR_DDAC_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.DATA.FD_SRC == '3') {
            R000_DR_INRAC_PROC();
            if (pgmRtn) return;
        }
        if (EQCSACT.DATA.FD_SRC == '5') {
            R000_DR_CASH_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_DR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW = 'N';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.AC = EQCSACT.DATA.PAY_AC;
        DDCUDRAC.CCY = K_CCY_CNY;
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = WS_TXN_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.CHQ_TYPE = EQCSACT.DATA.BLL_KND.charAt(0);
        DDCUDRAC.CHQ_NO = EQCSACT.DATA.BLL_NO;
        DDCUDRAC.CHQ_ISS_DATE = EQCSACT.DATA.SIGN_DT;
        DDCUDRAC.PAY_PSWD = EQCSACT.DATA.BLL_PSW;
        DDCUDRAC.OTHER_AC = WS_TXN_EQAC;
        DDCUDRAC.OTHER_CCY = K_CCY_CNY;
        DDCUDRAC.OTHER_AMT = WS_TXN_AMT;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.TX_MMO = WS_TX_MMO;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void R000_DR_INRAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = EQCSACT.DATA.BR_AC;
        AICUUPIA.DATA.CCY = K_CCY_CNY;
        AICUUPIA.DATA.AMT = WS_TXN_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = EQCSACT.DATA.CREV_NO;
        AICUUPIA.DATA.THEIR_AC = EQCSACT.DATA.EQ_AC;
        AICUUPIA.DATA.PAY_MAN = WS_TXN_EQ_CNM;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void R000_DR_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = K_CCY_CNY;
        BPCUABOX.AMT = WS_TXN_AMT;
        BPCUABOX.OPP_AC = WS_TXN_EQAC;
        S000_CALL_BPZUABOX();
        if (pgmRtn) return;
    }
    public void R000_GEN_BSVCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = WS_TXN_PRODCD;
        BPCPOEWA.DATA.EVENT_CODE = "BS";
        BPCPOEWA.DATA.DESC = "088";
        BPCPOEWA.DATA.BR_OLD = WS_VCH_BR;
        BPCPOEWA.DATA.BR_NEW = WS_VCH_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = K_CCY_CNY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TXN_AMT;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = WS_TXN_QTY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = WS_ACO_AC;
        BPCPOEWA.DATA.CI_NO = WS_TXN_CINO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = EQCSACT.DATA.DIV_AC;
        DDCUCRAC.CCY = K_CCY_CNY;
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = WS_TXN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.TX_MMO = WS_TX_MMO;
        DDCUCRAC.BANK_CR_FLG = 'N';
        DDCUCRAC.OTHER_AC = WS_TXN_EQAC;
        DDCUCRAC.OTHER_CCY = K_CCY_CNY;
        DDCUCRAC.OTHER_AMT = WS_TXN_AMT;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void R000_GEN_WIVCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACO_AC_CLOSE);
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = WS_TXN_PRODCD;
        BPCPOEWA.DATA.EVENT_CODE = "WI";
        BPCPOEWA.DATA.DESC = "088";
        BPCPOEWA.DATA.BR_OLD = EQRACT.OWNER_BK;
        BPCPOEWA.DATA.BR_NEW = EQRACT.OWNER_BK;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = K_CCY_CNY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TXN_AMT;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = WS_TXN_QTY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = WS_ACO_AC_CLOSE;
        BPCPOEWA.DATA.CI_NO = WS_TXN_CINO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_GET_PROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_TXN_PRODCD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PROD_CD_NOTFND);
        }
        if (WS_TXN_PRODCD.equalsIgnoreCase(K_EXT_NAT_PER)) {
            WS_EQ_TYP = '1';
        } else if (WS_TXN_PRODCD.equalsIgnoreCase(K_BUSS_ENTITY)) {
            WS_EQ_TYP = '2';
        } else if (WS_TXN_PRODCD.equalsIgnoreCase(K_STAFF)) {
            WS_EQ_TYP = '3';
        } else if (WS_TXN_PRODCD.equalsIgnoreCase(K_OTHER)) {
            WS_EQ_TYP = '4';
        } else {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PRODCD_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_PRICE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACTD);
        WS_ACTD_FLG = ' ';
        WS_TMP_PRI = 0;
        EQRACTD.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACTD.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQRACTD.TXN_TYP = '1';
        T000_READ_EQTACTD();
        if (pgmRtn) return;
        if (WS_ACTD_FLG == 'Y') {
            WS_TMP_PRI = EQRACTD.TXN_PRICE;
        } else {
            WS_TMP_PRI = 1;
        }
        CEP.TRC(SCCGWA, WS_TMP_PRI);
    }
    public void R000_GET_CIINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = WS_TXN_CINO;
        CEP.TRC(SCCGWA, WS_TXN_CINO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        WS_TXN_EQ_CNM = CICCUST.O_DATA.O_CI_NM;
    }
    public void R000_WRITE_EQTACTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACTD);
        EQRACTD.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACTD.KEY.EQ_AC = WS_ACTD_AC;
        EQRACTD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACTD.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRACTD.EQ_TYP = WS_EQ_TYP;
        EQRACTD.TXN_TYP = WS_TXN_TYP;
        EQRACTD.TXN_QTY = WS_TXN_QTY;
        EQRACTD.TXN_PRICE = WS_TXN_PRI;
        EQRACTD.TXN_AMT = WS_TXN_AMT;
        EQRACTD.BAL_QTY = WS_BAL_QTY;
        EQRACTD.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRACTD.OPP_AC = WS_ACTD_OPPAC;
        EQRACTD.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRACTD.REMARK = " ";
        EQRACTD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACTD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRACTD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRACTD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACTD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRACTD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRACTD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRACTD.TS = "0" + EQRACTD.TS;
        T000_WRITE_EQTACTD();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTWDZ1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = WS_TXN_EQAC;
        EQRWDZ.KEY.PSBK_NO = WS_PSBK_NO;
        EQRWDZ.KEY.UPT_TYP = '2';
        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.TXN_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = EQRWDZ.TXN_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) EQRWDZ.TXN_CODE = "0" + EQRWDZ.TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = WS_TXN_TYP;
        EQRWDZ.TXN_QTY = WS_TMP_QTY;
        EQRWDZ.TXN_PRICE = WS_TXN_PRI;
        EQRWDZ.TXN_AMT = WS_TMP_AMT;
        EQRWDZ.BAL_QTY = WS_BAL_QTY;
        EQRWDZ.ADD_DTL = " ";
        EQRWDZ.TEL_DTL = " ";
        EQRWDZ.AC_DTL = " ";
        EQRWDZ.PRT_FLG = WS_PRT_FLG;
        EQRWDZ.REMARK = " ";
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void R000_WRITE_EQTWDZ2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = WS_TXN_EQAC;
        EQRWDZ.KEY.PSBK_NO = WS_PSBK_NO;
        EQRWDZ.KEY.UPT_TYP = '2';
        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.TXN_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = EQRWDZ.TXN_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) EQRWDZ.TXN_CODE = "0" + EQRWDZ.TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = '8';
        EQRWDZ.TXN_QTY = WS_TMP_QTY;
        EQRWDZ.TXN_PRICE = WS_TXN_PRI;
        EQRWDZ.TXN_AMT = WS_TMP_AMT;
        EQRWDZ.BAL_QTY = WS_BAL_QTY;
        EQRWDZ.ADD_DTL = " ";
        EQRWDZ.TEL_DTL = " ";
        EQRWDZ.AC_DTL = " ";
        EQRWDZ.PRT_FLG = 'N';
        EQRWDZ.REMARK = " ";
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void R000_WRITE_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.ACO_AC = WS_ACO_AC;
        BPCSOCAC.AC = WS_EQ_AC;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "04";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.BV_TYP = '1';
        BPCSOCAC.ID_TYP = "" + CICCUST.O_DATA.O_CI_TYP;
        JIBS_tmp_int = BPCSOCAC.ID_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCSOCAC.ID_TYP = "0" + BPCSOCAC.ID_TYP;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.CCY = K_CCY_CNY;
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = WS_TXN_PRODCD;
        BPCSOCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSOCAC.OTH_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.OTH_ID_NO = CICCUST.O_DATA.O_ID_NO;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        CEP.TRC(SCCGWA, WS_ACO_AC_CLOSE);
        BPCSOCAC.AC = WS_TXN_EQAC;
        BPCSOCAC.ACO_AC = WS_ACO_AC_CLOSE;
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CLOSE_AC_STS = 'D';
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        WS_FRZD_FLG = ' ';
        EQRFRZD.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = EQCSACT.DATA.FRZ_NO;
        EQRFRZD.KEY.TXN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        EQRFRZD.JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        T000_READUP_EQTFRZD();
        if (pgmRtn) return;
        EQRFRZD.RVS_FLG = 'R';
        T000_REWRITE_EQTFRZD();
        if (pgmRtn) return;
    }
    public void R000_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = WS_TXN_EQAC;
        BPCPFHIS.DATA.TX_REV_DT = 0;
        BPCPFHIS.DATA.TX_DRCR_FLG = WS_TX_DRCR_FLG;
        BPCPFHIS.DATA.SUMUP_FLG = 'N';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        BPCPFHIS.DATA.TX_CCY = K_CCY_CNY;
        BPCPFHIS.DATA.VAL_BAL_CCY = K_CCY_CNY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = WS_TX_TYP;
        BPCPFHIS.DATA.RLT_AC = WS_HIS_AC;
        BPCPFHIS.DATA.OTH_AC = WS_HIS_AC;
        BPCPFHIS.DATA.TX_AMT = WS_TXN_QTY;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_MMO = WS_TX_MMO;
        BPCPFHIS.DATA.PROD_CD = WS_TXN_PRODCD;
        BPCPFHIS.DATA.CI_NO = WS_TXN_CINO;
        BPCPFHIS.DATA.AC = WS_TXN_EQAC;
        BPCPFHIS.DATA.ACO_AC = WS_ACO_AC;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.REMARK = WS_TXN_REMARK;
        WS_OUT_DATA.WS_OUT_EQAC = WS_TXN_EQAC;
        WS_OUT_DATA.WS_OUT_PRODCD = WS_TXN_PRODCD;
        WS_OUT_DATA.WS_OUT_CINO = WS_TXN_CINO;
        WS_OUT_DATA.WS_OUT_QTY = WS_TXN_QTY;
        WS_OUT_DATA.WS_OUT_PRI = WS_TXN_PRI;
        BPCPFHIS.DATA.FMT_CODE = "EQ101";
        BPCPFHIS.DATA.FMT_LEN = 86;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void R000_GEN_EQ_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        WS_EQ_AC = " ";
        BPCCGAC.DATA.BR = EQCSACT.DATA.ADD_BR;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.BR);
        CEP.TRC(SCCGWA, EQCSACT.DATA.ADD_BR);
        BPCCGAC.DATA.EQ_CCY = K_CCY_CNY;
        BPCCGAC.DATA.EQ_BIZ_TYPE = "701";
        BPCCGAC.DATA.AC_KIND = '3';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        WS_EQ_AC = BPCCGAC.DATA.EQ_AC;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.EQ_AC);
    }
    public void R000_RELA_EQAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = WS_TXN_EQAC;
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.CI_NO = WS_TXN_CINO;
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
        CICSACR.DATA.PROD_CD = WS_TXN_PRODCD;
        CICSACR.DATA.FRM_APP = "EQ";
        CICSACR.DATA.CCY = K_CCY_CNY;
        CICSACR.DATA.SHOW_FLG = 'N';
        CICSACR.DATA.AC_CNM = WS_TXN_EQ_CNM;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void R000_GEN_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        WS_ACO_AC = " ";
        BPCCGAC.DATA.PRD_CODE = WS_TXN_PRODCD;
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_MMO = "88";
        BPCCGAC.DATA.ACO_AC_DEF = 88;
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        WS_ACO_AC = BPCCGAC.DATA.ACO_AC;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC);
    }
    public void R000_RELA_ACOAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.ACAC_NO = WS_ACO_AC;
        CICSACAC.DATA.AGR_NO = WS_EQ_AC;
        CICSACAC.DATA.PROD_CD = WS_TXN_PRODCD;
        CICSACAC.DATA.AGR_SEQ = 0;
        CICSACAC.DATA.BV_NO = "" + 0;
        JIBS_tmp_int = CICSACAC.DATA.BV_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) CICSACAC.DATA.BV_NO = "0" + CICSACAC.DATA.BV_NO;
        CICSACAC.DATA.CCY = K_CCY_CNY;
        CICSACAC.DATA.CR_FLG = '0';
        CICSACAC.DATA.ACAC_CTL = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        CICSACAC.DATA.ACAC_CTL = "1" + CICSACAC.DATA.ACAC_CTL.substring(1);
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 2 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(2 + 1 - 1);
        CICSACAC.DATA.FRM_APP = "EQ";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void R000_CLOSE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSACT.DATA.EQ_AC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        WS_ACO_AC_CLOSE = EQRACT.ACO_AC;
        WS_ACO_AC = EQRACT.ACO_AC;
        WS_EQ_TYP = EQRACT.EQ_TYP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            EQRACT.EQ_QTY = 0;
            EQRACT.FRZ_QTY = 0;
            EQRACT.AC_STS = 'C';
            if (EQCSACT.FUNC == 'I') {
                EQRACT.AC_STS_WORD = "22";
            }
            if (EQCSACT.FUNC == 'Q') {
                EQRACT.AC_STS_WORD = "21";
            }
            EQRACT.WDAL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            EQRACT.WDAL_TLR = SCCGWA.COMM_AREA.TL_ID;
            EQRACT.WDAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            EQRACT.EQ_QTY = EQCSACT.DATA.EQ_QTY;
            EQRACT.FRZ_QTY = EQCSACT.DATA.FRZ_QTY;
            EQRACT.AC_STS = 'N';
            EQRACT.WDAL_BR = 0;
            EQRACT.WDAL_TLR = " ";
            EQRACT.WDAL_DT = 0;
        }
        EQRACT.LSTX_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
    }
    public void S000_CALL_EQZRACT() throws IOException,SQLException,Exception {
        EQCRACT.REC_PTR = EQRACT;
        EQCRACT.REC_LEN = 724;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTACT", EQCRACT);
        if (EQCRACT.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, EQCRACT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EQZRFRZ() throws IOException,SQLException,Exception {
        EQCRFRZ.REC_PTR = EQRFRZ;
        EQCRFRZ.REC_LEN = 1990;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTFRZ", EQCRFRZ);
        if (EQCRFRZ.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, EQCRFRZ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_MSGID = EQCMSG_ERROR_MSG.BR_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void S000_CALL_BPZFQFBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-INQ-FST-BVNO", BPCFQFBV);
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC.RC_CODE);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_MSGID = "" + CICSACR.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA, true);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX, true);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX, true);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T000_READ_EQTFRZ_EQAC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZ.KEY.EQ_AC "
            + "AND FRZ_STS = :EQRFRZ.FRZ_STS";
        EQTFRZ_RD.fst = true;
        IBS.READ(SCCGWA, EQRFRZ, this, EQTFRZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTPLG() throws IOException,SQLException,Exception {
        EQTPLG_RD = new DBParm();
        EQTPLG_RD.TableName = "EQTPLG";
        EQTPLG_RD.where = "EQ_BKID = :EQRPLG.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRPLG.KEY.EQ_AC "
            + "AND PLG_STS = :EQRPLG.PLG_STS";
        IBS.READ(SCCGWA, EQRPLG, this, EQTPLG_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLG_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLG_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND CI_NO = :EQRACT.CI_NO "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND PSBK_NO = :EQRBVT.KEY.PSBK_NO";
        EQTBVT_RD.fst = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACTD() throws IOException,SQLException,Exception {
        EQTACTD_RD = new DBParm();
        EQTACTD_RD.TableName = "EQTACTD";
        EQTACTD_RD.where = "EQ_BKID = :EQRACTD.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACTD.KEY.EQ_AC "
            + "AND TXN_TYP = :EQRACTD.TXN_TYP";
        EQTACTD_RD.fst = true;
        EQTACTD_RD.order = "EQ_BKID,EQ_AC,TXN_DATE";
        IBS.READ(SCCGWA, EQRACTD, this, EQTACTD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACTD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACTD_FLG = 'N';
        } else {
        }
    }
    public void T000_READUP_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_NO = :EQRBVT.KEY.PSBK_NO";
        EQTBVT_RD.upd = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READUP_EQTFRZD() throws IOException,SQLException,Exception {
        EQTFRZD_RD = new DBParm();
        EQTFRZD_RD.TableName = "EQTFRZD";
        EQTFRZD_RD.where = "EQ_BKID = :EQRFRZD.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZD.KEY.EQ_AC "
            + "AND FRZ_NO = :EQRFRZD.KEY.FRZ_NO "
            + "AND TXN_DATE = :EQRFRZD.KEY.TXN_DATE "
            + "AND JRN_NO = :EQRFRZD.JRN_NO";
        EQTFRZD_RD.upd = true;
        IBS.READ(SCCGWA, EQRFRZD, this, EQTFRZD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZD_FLG = 'N';
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_FRZD_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_WRITE_EQTACTD() throws IOException,SQLException,Exception {
        EQTACTD_RD = new DBParm();
        EQTACTD_RD.TableName = "EQTACTD";
        IBS.WRITE(SCCGWA, EQRACTD, EQTACTD_RD);
    }
    public void T000_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        EQTFRZD_RD = new DBParm();
        EQTFRZD_RD.TableName = "EQTFRZD";
        IBS.WRITE(SCCGWA, EQRFRZD, EQTFRZD_RD);
    }
    public void T000_WRITE_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        IBS.WRITE(SCCGWA, EQRBVT, EQTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_BVT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_EQTWDZ() throws IOException,SQLException,Exception {
        EQTWDZ_RD = new DBParm();
        EQTWDZ_RD.TableName = "EQTWDZ";
        IBS.WRITE(SCCGWA, EQRWDZ, EQTWDZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_WDZ_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_EQTBVT() throws IOException,SQLException,Exception {
        EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        IBS.REWRITE(SCCGWA, EQRBVT, EQTBVT_RD);
    }
    public void T000_REWRITE_EQTFRZD() throws IOException,SQLException,Exception {
        EQRFRZD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTFRZD_RD = new DBParm();
        EQTFRZD_RD.TableName = "EQTFRZD";
        IBS.REWRITE(SCCGWA, EQRFRZD, EQTFRZD_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
