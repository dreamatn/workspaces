package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQMIB;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCSCINM;
import com.hisun.IB.IBCQINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.VT.VTCPQTAX;

public class BPZSFECT {
    boolean pgmRtn = false;
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_U_T_CHRG_FEE = "BP-U-T-CHRG-FEE     ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE CONTRACT INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "BPCHFBAS";
    String K_OUTPUT_FMT = "BPF60";
    double K_TOT_PCT = 1;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_CPN_BPZPACTY = "BP-P-QUERY-AC-TYPE";
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL = "0016";
    String K_CUS_CR_STS_TBL = "0016";
    String K_STS_TABLE_DC = "DC";
    String K_DC_DR_STS_TBL = "0002";
    String K_DC_CR_STS_TBL = "0001";
    String WS_ERR_MSG = " ";
    short WS_FEE_NO = 0;
    short WS_ITM = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_CNT = 0;
    int WS_LAST_LOC1 = 0;
    int WS_LAST_LOC2 = 0;
    int WS_IDX = 0;
    double WS_TOT_PCT = 0;
    int WS_OLD_ST_DT = 0;
    int WS_OLD_MA_DT = 0;
    int WS_OLD_CREATE_DT = 0;
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    double WS_CHG_AMT = 0;
    double WS_EX_RATE = 0;
    int WS_OLD_CRT_DT = 0;
    String WS_OLD_CRT_TL = " ";
    long WS_OLD_JNL_NO = 0;
    char WS_CUS_TYPE = ' ';
    int WS_GLMST1 = 0;
    int WS_GLMST2 = 0;
    String WS_FEE_CCY_JUDGE = " ";
    String WS_CHG_CCY_JUDGE = " ";
    double WS_REL_AMT = 0;
    char WS_CTR_FLAG = ' ';
    char WS_CPH_FLAG = ' ';
    char WS_SPACE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPRFCPH BPRFCPH = new BPRFCPH();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCHFCTR BPCHFCTR = new BPCHFCTR();
    BPCHFCTR BPCNFCTR = new BPCHFCTR();
    BPCHFCTR BPCOFCTR = new BPCHFCTR();
    BPCHFCPH BPCHFCPH = new BPCHFCPH();
    BPCHFCPH BPCNFCPH = new BPCHFCPH();
    BPCHFCPH BPCOFCPH = new BPCHFCPH();
    BPCUFEEC BPCUFEEC = new BPCUFEEC();
    BPCUMENT BPCUMENT = new BPCUMENT();
    BPRPACCR BPRPACCR = new BPRPACCR();
    BPCRACCR BPCRACCR = new BPCRACCR();
    BPRFAMO BPRFAMO = new BPRFAMO();
    BPCRFAMO BPCRFAMO = new BPCRFAMO();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUFCOM BPCUFCOM = new BPCUFCOM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCCGCT BPCCGCT = new BPCCGCT();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCOUFEC BPCOUFEC = new BPCOUFEC();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    DDCSBCHQ DDCSBCHQ = new DDCSBCHQ();
    IBCQINF IBCQINF = new IBCQINF();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCUMPRT BPCUMPRT = new BPCUMPRT();
    BPCUGLM BPCUGLM = new BPCUGLM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCSQCHQ DDCSQCHQ = new DDCSQCHQ();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DDCSQPB DDCSQPB = new DDCSQPB();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    SCCGWA SCCGWA;
    BPCSFECT BPCSFECT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSFECT BPCSFECT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFECT = BPCSFECT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFECT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        if (BPCSFECT.CHG_FLG == ' ') {
            BPCSFECT.CHG_FLG = 'Y';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFECT.FUNC_CODE);
        if (BPCSFECT.FUNC_CODE == 'I') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B200_QUERY_PROCESS_CN();
                if (pgmRtn) return;
            } else {
            }
        } else if (BPCSFECT.FUNC_CODE == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B300_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSFECT.FUNC_CODE == 'U') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B400_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSFECT.FUNC_CODE == 'D') {
            B200_CHECK_INPUT();
            if (pgmRtn) return;
            B500_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSFECT.FUNC_CODE == 'R') {
            B600_REVERSAL_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.FUNC_CODE == 'A' 
            || BPCSFECT.FUNC_CODE == 'U' 
            || BPCSFECT.FUNC_CODE == 'D') {
            B900_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCSFECT.START_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANOT_DELETE_RECORD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IIIIIIIII");
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        CEP.TRC(SCCGWA, BPCSFECT.FEE_DESC);
        CEP.TRC(SCCGWA, BPCSFECT.CINO);
        CEP.TRC(SCCGWA, BPCSFECT.CI_ABBR_NM);
        CEP.TRC(SCCGWA, BPCSFECT.CT_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.FEE_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.BOOK_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.PRDT_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, BPCSFECT.MATURITY_DT);
        CEP.TRC(SCCGWA, BPCSFECT.HOL_OVR);
        CEP.TRC(SCCGWA, BPCSFECT.CAL_CD1);
        CEP.TRC(SCCGWA, BPCSFECT.HOL_METH);
        CEP.TRC(SCCGWA, BPCSFECT.CAL_CD2);
        CEP.TRC(SCCGWA, BPCSFECT.PAY_IND);
        CEP.TRC(SCCGWA, BPCSFECT.CSH_FLW_IND);
        CEP.TRC(SCCGWA, BPCSFECT.BK_PFLO);
        CEP.TRC(SCCGWA, BPCSFECT.PAY_METH);
        CEP.TRC(SCCGWA, BPCSFECT.ACU_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.FIX_PRC_METH);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_CCY);
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_CTNO);
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_CT_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.AMT_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_PRDT_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.INT_BASIC);
        CEP.TRC(SCCGWA, BPCSFECT.AGGR_TYP);
        CEP.TRC(SCCGWA, BPCSFECT.REF_CCY);
        CEP.TRC(SCCGWA, BPCSFECT.REF_METH);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_CCY);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_METH);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_ACNO);
        CEP.TRC(SCCGWA, BPCSFECT.NOSTRO_CD);
        CEP.TRC(SCCGWA, BPCSFECT.CHQ_NO);
        CEP.TRC(SCCGWA, BPCSFECT.GL_MST_BR);
        CEP.TRC(SCCGWA, BPCSFECT.GL_MST_HO);
        CEP.TRC(SCCGWA, BPCSFECT.GL_MST_IAS39);
        CEP.TRC(SCCGWA, BPCSFECT.GL_MST_UNUS);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.CT_STS);
        CEP.TRC(SCCGWA, BPCSFECT.REMARK);
        CEP.TRC(SCCGWA, BPCSFECT.CP_NO);
        CEP.TRC(SCCGWA, BPCSFECT.SALE_DT);
        CEP.TRC(SCCGWA, BPCSFECT.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSFECT.RELT_FLG);
        if (BPCSFECT.CHG_METH == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.FUNC_CODE == 'U') {
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
            T000_READ_BPTFCTR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSFECT.CHG_METH);
        CEP.TRC(SCCGWA, BPCSFECT.CINO);
        if (BPCSFECT.CINO.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSFECT.CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPRFBAS.KEY.FEE_CODE = BPCSFECT.FEE_TYP;
        BPCTFBAS.INFO.FUNC = 'Q';
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFBAS.REB_FLG);
        if (BPRFBAS.REB_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANOT_REBATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSFECT.BOOK_ACCT);
        if (BPCSFECT.BOOK_ACCT != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCSFECT.BOOK_ACCT;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (BPCSFECT.FUNC_CODE == 'A' 
            && BPCSFECT.PAY_METH == 'A') {
            if (BPCSFECT.MATURITY_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSFECT.CHG_AMT);
        if (BPCSFECT.FUNC_CODE == 'A' 
            && BPCSFECT.PAY_METH == 'P') {
            if (BPCSFECT.CHG_AMT == 0 
                && BPCSFECT.CHG_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AMT_CANT_BE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSFECT.MATURITY_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.MATURITY_DT < BPCSFECT.START_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DATE_LESS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.MATURITY_DT > 20991231) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "11111");
        if (BPCSFECT.HOL_OVR == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOLI_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "22222");
        CEP.TRC(SCCGWA, BPCSFECT.CAL_CD1);
        if (BPCSFECT.CAL_CD1.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHKCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "33333");
        if (BPCSFECT.START_DT > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSFECT.HOL_METH == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOLI_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "44444");
        if (BPCSFECT.START_DT > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSFECT.CAL_CD2.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HCHKCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "55555");
        if (BPCSFECT.CAL_CD1.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCSFECT.CAL_CD1);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPCSFECT.CAL_CD1;
            S000_CALL_BPZPCCAL();
            if (pgmRtn) return;
        }
        if (BPCSFECT.HOL_OVR == 'N') {
            if (BPCSFECT.FUNC_CODE == 'A') {
                IBS.init(SCCGWA, BPCOCLWD);
                BPCOCLWD.DATE1 = BPCSFECT.START_DT;
                BPCOCLWD.CAL_CODE = BPCSFECT.CAL_CD1;
                BPCOCLWD.DAYE_FLG = 'Y';
                BPCOCLWD.WDAYS = 2;
                S000_CALL_BPZPCLWD();
                if (pgmRtn) return;
                if (BPCOCLWD.DATE1_FLG == 'H') {
                    CEP.TRC(SCCGWA, BPCSFECT.START_DT);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.DATE1 = BPCSFECT.MATURITY_DT;
            BPCOCLWD.CAL_CODE = BPCSFECT.CAL_CD1;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 2;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            if (BPCOCLWD.DATE1_FLG == 'H') {
                CEP.TRC(SCCGWA, BPCSFECT.MATURITY_DT);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSFECT.CAL_CD2.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCSFECT.CAL_CD2);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPCSFECT.CAL_CD2;
            S000_CALL_BPZPCCAL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_CTNO);
        if (BPCSFECT.RLTD_CTNO.trim().length() > 0 
            && (BPCSFECT.AMT_TYP == 01 
            || BPCSFECT.AMT_TYP == 02)) {
            IBS.init(SCCGWA, LNCSIQIF);
            LNCSIQIF.CONTRACT_NO = BPCSFECT.RLTD_CTNO;
            S000_CALL_LNZSIQIF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSIQIF.START_DT);
            CEP.TRC(SCCGWA, LNCSIQIF.MATU_DT);
            CEP.TRC(SCCGWA, BPCSFECT.START_DT);
            CEP.TRC(SCCGWA, BPCSFECT.MATURITY_DT);
            if (BPCSFECT.CHG_AMT == 0 
                && BPCSFECT.AMT_TYP != 0) {
                if (LNCSIQIF.START_DT != 0 
                    && BPCSFECT.START_DT < LNCSIQIF.START_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCH_STA_DATE_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCSIQIF.MATU_DT != 0 
                    && BPCSFECT.MATURITY_DT > LNCSIQIF.MATU_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCH_MAT_DATE_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSFECT.REF_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCSFECT.REF_CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        } else {
            BPCSFECT.REF_CCY = BPCSFECT.CHG_CCY;
        }
        CEP.TRC(SCCGWA, BPCSFECT.REF_CCY);
        if (BPCSFECT.CHG_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCSFECT.CHG_CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
        } else {
            CEP.TRC(SCCGWA, "CHECK START");
            if (BPCSFECT.CHG_CCY_REAL.trim().length() > 0 
                && BPCSFECT.CHG_CCY.trim().length() > 0 
                && !BPCSFECT.CHG_CCY_REAL.equalsIgnoreCase(BPCSFECT.CHG_CCY)) {
                CEP.TRC(SCCGWA, "CHECK IN   ");
                CEP.TRC(SCCGWA, BPCSFECT.CHG_CCY_REAL);
                CEP.TRC(SCCGWA, BPCSFECT.CHG_CCY);
                WS_FEE_CCY_JUDGE = BPCSFECT.CHG_CCY;
                WS_CHG_CCY_JUDGE = BPCSFECT.CHG_CCY_REAL;
                if ((!WS_FEE_CCY_JUDGE.equalsIgnoreCase("344") 
                    && !WS_FEE_CCY_JUDGE.equalsIgnoreCase("446")) 
                    || (!WS_CHG_CCY_JUDGE.equalsIgnoreCase("344") 
                    && !WS_CHG_CCY_JUDGE.equalsIgnoreCase("446"))) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXCHANGE_CCY_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "CHECK END  ");
        if (BPCSFECT.PAY_IND == 'P' 
            && BPCSFECT.CHG_METH == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_NOT_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.PAY_METH == 'A' 
            && BPCSFECT.START_DT == BPCSFECT.MATURITY_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STADT_CANT_EQ_MAT_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSFECT.REF_METH == '1' 
            && BPCSFECT.CHG_AMT == 0) {
            for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                if (BPCSFECT.REF_FEE_DATA[WS_CNT-1].REF_PCT != 0) {
                    WS_LAST_LOC2 = WS_CNT;
                }
            }
            CEP.TRC(SCCGWA, WS_LAST_LOC2);
            if (BPCSFECT.REF_FEE_DATA[1-1].REF_PCT != 100) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_PCT_MUST_100;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSFECT.CHG_ACNO);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_METH);
        if (BPCSFECT.CHG_METH == '0') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCSFECT.CHG_ACNO;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
        }
        if (BPCSFECT.CHG_METH == '2') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.NOSTRO_CD = BPCSFECT.CHG_ACNO;
            IBCQINF.INPUT_DATA.CCY = BPCSFECT.CHG_CCY_REAL;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
        }
        if (BPCSFECT.CHG_METH == '4' 
            || BPCSFECT.CHG_METH == '5') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCSFECT.CHG_ACNO;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
        }
        if (BPCSFECT.CHG_METH == '5') {
            CEP.TRC(SCCGWA, "CHECK PSBK STATUS");
            CEP.TRC(SCCGWA, BPCSFECT.CHG_ACNO);
            IBS.init(SCCGWA, DDCSQPB);
            DDCSQPB.AC = BPCSFECT.CHG_ACNO;
            CEP.TRC(SCCGWA, DDCSQPB.AC);
            S000_CALL_DDZSQPB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSFECT.CHQ_NO);
        if (BPCSFECT.CHQ_NO.trim().length() > 0) {
            if (BPCSFECT.CHG_METH == '6' 
                || BPCSFECT.CHG_METH == '7') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCSFECT.CHG_ACNO;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCSBCHQ);
            DDCSBCHQ.AC_NO = BPCSFECT.CHG_ACNO;
            DDCSBCHQ.CHQ_NO = BPCSFECT.CHQ_NO;
            S000_CALL_DDZSBCHQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
            IBS.init(SCCGWA, DDCSQCHQ);
            DDCSQCHQ.AC_NO = BPCSFECT.CHG_ACNO;
            DDCSQCHQ.STR_CHQ_NO = BPCSFECT.CHQ_NO;
            DDCSQCHQ.END_CHQ_NO = BPCSFECT.CHQ_NO;
            S000_CALL_DDZSQCHQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
            CEP.TRC(SCCGWA, BPRFCTR.FEE_STATUS);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if ((DDCSQCHQ.CHQ_STS != '1' 
                && DDCSQCHQ.CHQ_STS != '6') 
                && (JIBS_tmp_str[0].equalsIgnoreCase("9999122") 
                || (JIBS_tmp_str[1].equalsIgnoreCase("9999123") 
                && BPRFCTR.FEE_STATUS == '0'))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_STS_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((BPCSFECT.CHG_METH == '6' 
                && DDCSQCHQ.CHQ_TYP != '2') 
                || (BPCSFECT.CHG_METH == '7' 
                && DDCSQCHQ.CHQ_TYP != '3')) {
                CEP.TRC(SCCGWA, "CHQ TYPE NOT MATCH");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSFECT.CHG_METH == '0' 
            || BPCSFECT.CHG_METH == '2' 
            || BPCSFECT.CHG_METH == '4' 
            || BPCSFECT.CHG_METH == '5' 
            || BPCSFECT.CHG_METH == '6' 
            || BPCSFECT.CHG_METH == '7') {
            CEP.TRC(SCCGWA, BPCSFECT.CHG_METH);
            CEP.TRC(SCCGWA, BPCSFECT.CHG_ACNO);
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = BPCSFECT.CHG_ACNO;
            if (BPCSFECT.CHG_METH == '2') {
                CICACCU.DATA.AGR_NO = IBCQINF.INPUT_DATA.AC_NO;
            }
            if (BPCSFECT.CHG_METH == '4' 
                || BPCSFECT.CHG_METH == '5') {
                CICACCU.DATA.AGR_NO = BPCSFECT.CHG_ACNO;
            }
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            CEP.TRC(SCCGWA, BPCSFECT.CINO);
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(BPCSFECT.CINO)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSFECT.CHG_METH == '3') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = BPCSFECT.CHG_ACNO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
        }
        if (BPCSFECT.CHG_METH == '0' 
            || BPCSFECT.CHG_METH == '4' 
            || BPCSFECT.CHG_METH == '5' 
            || BPCSFECT.CHG_METH == '6' 
            || BPCSFECT.CHG_METH == '7') {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = BPCSFECT.CHG_ACNO;
            if ((BPCSFECT.CHG_METH == '0' 
                || BPCSFECT.CHG_METH == '4' 
                || BPCSFECT.CHG_METH == '5' 
                || BPCSFECT.CHG_METH == '6' 
                || BPCSFECT.CHG_METH == '7')) {
                DDCIMCCY.DATA[1-1].AC = CICACCU.DATA.AGR_NO;
            }
            DDCIMCCY.DATA[1-1].CCY = BPCSFECT.CHG_CCY_REAL;
            CEP.TRC(SCCGWA, BPCSFECT.CCY_TYPE);
            DDCIMCCY.DATA[1-1].CCY_TYPE = BPCSFECT.CCY_TYPE;
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT);
        if (BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT != 0 
            || BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT != 0 
            || BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT != 0 
            || BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT != 0 
            || BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT != 0) {
            WS_TOT_PCT = BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT + BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT + BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT + BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT;
            CEP.TRC(SCCGWA, WS_TOT_PCT);
            CEP.TRC(SCCGWA, K_TOT_PCT);
            if (WS_TOT_PCT != K_TOT_PCT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_OVER_100;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSFECT.FUNC_CODE == 'U') {
            if (BPRFCTR.FEE_STATUS == '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_DEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        BPCSFECT.CTNO = BPRFCTR.KEY.CTRT_NO;
        BPCSFECT.FEE_DESC = BPRFCTR.CTRT_DESC;
        BPCSFECT.CINO = BPRFCTR.CI_NO;
        BPCSFECT.CT_TYP = BPRFCTR.CTRT_TYPE;
        BPCSFECT.FEE_TYP = BPRFCTR.FEE_TYPE;
        BPCSFECT.BOOK_ACCT = BPRFCTR.BOOK_CENTRE;
        BPCSFECT.PRDT_TYP = BPRFCTR.PRD_TYPE;
        BPCSFECT.START_DT = BPRFCTR.START_DATE;
        BPCSFECT.MATURITY_DT = BPRFCTR.MATURITY_DATE;
        BPCSFECT.HOL_OVR = BPRFCTR.HOLI_OVER;
        BPCSFECT.CAL_CD1 = BPRFCTR.CAL_CODE1;
        BPCSFECT.HOL_METH = BPRFCTR.HOLI_METHOD;
        BPCSFECT.CAL_CD2 = BPRFCTR.CAL_CODE2;
        BPCSFECT.PAY_IND = BPRFCTR.PAY_IND;
        BPCSFECT.CSH_FLW_IND = BPRFCTR.CASHFLOW_IND;
        BPCSFECT.BK_PFLO = BPRFCTR.BANK_PORTF;
        BPCSFECT.PAY_METH = BPRFCTR.PAYMENT_METHOD;
        BPCSFECT.ACU_TYP = BPRFCTR.ACCRUAL_TYPE;
        BPCSFECT.FIX_PRC_METH = BPRFCTR.PRICE_METHOD;
        BPCSFECT.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCSFECT.TXN_AMT = BPRFCTR.PRIN_AMT;
        BPCSFECT.RLTD_CTNO = BPRFCTR.REL_CTRT_NO;
        BPCSFECT.AMT_TYP = BPRFCTR.AMT_TYPE;
        BPCSFECT.AGGR_TYP = BPRFCTR.AGGR_TYPE;
        BPCSFECT.REF_CCY = BPRFCTR.REF_CCY;
        BPCSFECT.REF_METH = BPRFCTR.REF_METHOD;
        BPCSFECT.CHG_CCY = BPRFCTR.CHARGE_CCY;
        BPCSFECT.CHG_AMT = BPRFCTR.CHARGE_AMT;
        BPCSFECT.CHG_METH = BPRFCTR.CHARGE_METHOD;
        BPCSFECT.CHG_ACNO = BPRFCTR.CHARGE_AC;
        BPCSFECT.NOSTRO_CD = BPRFCTR.NOSTRO_AC;
        BPCSFECT.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCSFECT.GL_MST_BR = BPRFCTR.GL_MASTER1;
        BPCSFECT.GL_MST_HO = BPRFCTR.GL_MASTER2;
        BPCSFECT.GL_MST_IAS39 = BPRFCTR.GL_MASTER3;
        BPCSFECT.GL_MST_UNUS = BPRFCTR.GL_MASTER4;
        BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT = BPRFCTR.OIC_NO1;
        BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC = BPRFCTR.OIC_CENTRE1;
        BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT = BPRFCTR.OIC_PCT1;
        BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT = BPRFCTR.OIC_NO2;
        BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC = BPRFCTR.OIC_CENTRE2;
        BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT = BPRFCTR.OIC_PCT2;
        BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT = BPRFCTR.OIC_NO3;
        BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC = BPRFCTR.OIC_CENTRE3;
        BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT = BPRFCTR.OIC_PCT3;
        BPCSFECT.TXN_OIC_DATA[4-1].OIC_ACCT = BPRFCTR.OIC_NO4;
        BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC = BPRFCTR.OIC_CENTRE4;
        BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT = BPRFCTR.OIC_PCT4;
        BPCSFECT.TXN_OIC_DATA[5-1].OIC_ACCT = BPRFCTR.OIC_NO5;
        BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC = BPRFCTR.OIC_CENTRE5;
        BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT = BPRFCTR.OIC_PCT5;
        BPCSFECT.CT_STS = BPRFCTR.FEE_STATUS;
        BPCSFECT.REMARK = BPRFCTR.REMARK;
        BPCSFECT.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCSFECT.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCSFECT.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCSFECT.TICKET = BPRFCTR.TICKET;
        BPCSFECT.RATE = BPRFCTR.RATE;
        BPCSFECT.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCSFECT.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCSFECT.MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        BPCSFECT.JNL_NO = BPRFCTR.JNL_NO;
        BPCSFECT.REAL_AC_DATE = BPRFCTR.REAL_AC_DATE;
        BPCSFECT.VOUCHER_NO = BPRFCTR.VOUCHER_NO;
        BPCSFECT.CREATE_DATE = BPRFCTR.CREATE_DATE;
        BPCSFECT.CREATE_TELL = BPRFCTR.CREATE_TELL;
        BPCSFECT.LAST_TELL = BPRFCTR.LAST_TELL;
        BPCSFECT.SUP_TEL1 = BPRFCTR.SUP_TEL1;
        BPCSFECT.SUP_TEL2 = BPRFCTR.SUP_TEL2;
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, BPRFCTR.START_DATE);
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPRFCPH.KEY.VALUE_DATE = BPRFCTR.START_DATE;
        T000_READ_BPTFCPH();
        if (pgmRtn) return;
        BPCSFECT.AMT_PCT = BPRFCPH.MULTI;
        BPCSFECT.INT_BASIC = BPRFCPH.INT_BAS;
        BPCSFECT.REF_FEE_DATA[1-1].REF_AMT = BPRFCPH.UP_AMT_1;
        BPCSFECT.REF_FEE_DATA[1-1].REF_PCT = BPRFCPH.UP_PCT_1;
        BPCSFECT.REF_FEE_DATA[1-1].FEE_AMT = BPRFCPH.FEE_AMT_1;
        BPCSFECT.REF_FEE_DATA[1-1].FEE_RATE = BPRFCPH.FEE_RATE_1;
        BPCSFECT.REF_FEE_DATA[2-1].REF_AMT = BPRFCPH.UP_AMT_2;
        BPCSFECT.REF_FEE_DATA[2-1].REF_PCT = BPRFCPH.UP_PCT_2;
        BPCSFECT.REF_FEE_DATA[2-1].FEE_AMT = BPRFCPH.FEE_AMT_2;
        BPCSFECT.REF_FEE_DATA[2-1].FEE_RATE = BPRFCPH.FEE_RATE_2;
        BPCSFECT.REF_FEE_DATA[3-1].REF_AMT = BPRFCPH.UP_AMT_3;
        BPCSFECT.REF_FEE_DATA[3-1].REF_PCT = BPRFCPH.UP_PCT_3;
        BPCSFECT.REF_FEE_DATA[3-1].FEE_AMT = BPRFCPH.FEE_AMT_3;
        BPCSFECT.REF_FEE_DATA[3-1].FEE_RATE = BPRFCPH.FEE_RATE_3;
        BPCSFECT.REF_FEE_DATA[4-1].REF_AMT = BPRFCPH.UP_AMT_4;
        BPCSFECT.REF_FEE_DATA[4-1].REF_PCT = BPRFCPH.UP_PCT_4;
        BPCSFECT.REF_FEE_DATA[4-1].FEE_AMT = BPRFCPH.FEE_AMT_4;
        BPCSFECT.REF_FEE_DATA[4-1].FEE_RATE = BPRFCPH.FEE_RATE_4;
        BPCSFECT.REF_FEE_DATA[5-1].REF_AMT = BPRFCPH.UP_AMT_5;
        BPCSFECT.REF_FEE_DATA[5-1].REF_PCT = BPRFCPH.UP_PCT_5;
        BPCSFECT.REF_FEE_DATA[5-1].FEE_AMT = BPRFCPH.FEE_AMT_5;
        BPCSFECT.REF_FEE_DATA[5-1].FEE_RATE = BPRFCPH.FEE_RATE_5;
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_CTNO);
        if (BPCSFECT.CINO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "XXXXXXXXX");
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSFECT.CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_ENM.trim().length() > 0) {
                BPCSFECT.CI_ABBR_NM = CICCUST.O_DATA.O_CI_ENM;
            }
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                BPCSFECT.CI_ABBR_NM = CICCUST.O_DATA.O_CI_NM;
            }
        }
        CEP.TRC(SCCGWA, "INQURE OUT DATA:");
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.CI_ABBR_NM);
    }
    public void B200_QUERY_PROCESS_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        BPCSFECT.CTNO = BPRFCTR.KEY.CTRT_NO;
        BPCSFECT.FEE_DESC = BPRFCTR.CTRT_DESC;
        BPCSFECT.CINO = BPRFCTR.CI_NO;
        BPCSFECT.CT_TYP = BPRFCTR.CTRT_TYPE;
        BPCSFECT.FEE_TYP = BPRFCTR.FEE_TYPE;
        BPCSFECT.BOOK_ACCT = BPRFCTR.BOOK_CENTRE;
        BPCSFECT.PRDT_TYP = BPRFCTR.PRD_TYPE;
        BPCSFECT.START_DT = BPRFCTR.START_DATE;
        BPCSFECT.MATURITY_DT = BPRFCTR.MATURITY_DATE;
        BPCSFECT.HOL_OVR = BPRFCTR.HOLI_OVER;
        BPCSFECT.CAL_CD1 = BPRFCTR.CAL_CODE1;
        BPCSFECT.HOL_METH = BPRFCTR.HOLI_METHOD;
        BPCSFECT.CAL_CD2 = BPRFCTR.CAL_CODE2;
        BPCSFECT.PAY_IND = BPRFCTR.PAY_IND;
        BPCSFECT.CSH_FLW_IND = BPRFCTR.CASHFLOW_IND;
        BPCSFECT.BK_PFLO = BPRFCTR.BANK_PORTF;
        BPCSFECT.PAY_METH = BPRFCTR.PAYMENT_METHOD;
        BPCSFECT.ACU_TYP = BPRFCTR.ACCRUAL_TYPE;
        BPCSFECT.FIX_PRC_METH = BPRFCTR.PRICE_METHOD;
        BPCSFECT.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCSFECT.TXN_AMT = BPRFCTR.PRIN_AMT;
        BPCSFECT.RLTD_CTNO = BPRFCTR.REL_CTRT_NO;
        BPCSFECT.AMT_TYP = BPRFCTR.AMT_TYPE;
        BPCSFECT.AGGR_TYP = BPRFCTR.AGGR_TYPE;
        BPCSFECT.REF_CCY = BPRFCTR.REF_CCY;
        BPCSFECT.REF_METH = BPRFCTR.REF_METHOD;
        BPCSFECT.CHG_CCY = BPRFCTR.CHARGE_CCY;
        BPCSFECT.CHG_AMT = BPRFCTR.CHARGE_AMT;
        BPCSFECT.CHG_METH = BPRFCTR.CHARGE_METHOD;
        BPCSFECT.CHG_ACNO = BPRFCTR.CHARGE_AC;
        BPCSFECT.NOSTRO_CD = BPRFCTR.NOSTRO_AC;
        BPCSFECT.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCSFECT.GL_MST_BR = BPRFCTR.GL_MASTER1;
        BPCSFECT.GL_MST_HO = BPRFCTR.GL_MASTER2;
        BPCSFECT.GL_MST_IAS39 = BPRFCTR.GL_MASTER3;
        BPCSFECT.GL_MST_UNUS = BPRFCTR.GL_MASTER4;
        CEP.TRC(SCCGWA, BPRFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT = BPRFCTR.OIC_NO1;
        BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC = BPRFCTR.OIC_CENTRE1;
        BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT = BPRFCTR.OIC_PCT1;
        CEP.TRC(SCCGWA, BPRFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPRFCTR.OIC_CENTRE1);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT);
        BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT = BPRFCTR.OIC_NO2;
        BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC = BPRFCTR.OIC_CENTRE2;
        BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT = BPRFCTR.OIC_PCT2;
        CEP.TRC(SCCGWA, BPRFCTR.OIC_NO2);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPRFCTR.OIC_CENTRE2);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT);
        BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT = BPRFCTR.OIC_NO3;
        BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC = BPRFCTR.OIC_CENTRE3;
        BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT = BPRFCTR.OIC_PCT3;
        CEP.TRC(SCCGWA, BPRFCTR.OIC_NO3);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPRFCTR.OIC_CENTRE3);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT);
        BPCSFECT.TXN_OIC_DATA[4-1].OIC_ACCT = BPRFCTR.OIC_NO4;
        BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC = BPRFCTR.OIC_CENTRE4;
        BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT = BPRFCTR.OIC_PCT4;
        BPCSFECT.TXN_OIC_DATA[5-1].OIC_ACCT = BPRFCTR.OIC_NO5;
        BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC = BPRFCTR.OIC_CENTRE5;
        BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT = BPRFCTR.OIC_PCT5;
        BPCSFECT.CT_STS = BPRFCTR.FEE_STATUS;
        BPCSFECT.REMARK = BPRFCTR.REMARK;
        BPCSFECT.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCSFECT.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCSFECT.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCSFECT.TICKET = BPRFCTR.TICKET;
        BPCSFECT.RATE = BPRFCTR.RATE;
        BPCSFECT.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCSFECT.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCSFECT.MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        BPCSFECT.JNL_NO = BPRFCTR.JNL_NO;
        BPCSFECT.REAL_AC_DATE = BPRFCTR.REAL_AC_DATE;
        BPCSFECT.VOUCHER_NO = BPRFCTR.VOUCHER_NO;
        BPCSFECT.CREATE_DATE = BPRFCTR.CREATE_DATE;
        BPCSFECT.CREATE_TELL = BPRFCTR.CREATE_TELL;
        BPCSFECT.LAST_TELL = BPRFCTR.LAST_TELL;
        BPCSFECT.SUP_TEL1 = BPRFCTR.SUP_TEL1;
        BPCSFECT.SUP_TEL2 = BPRFCTR.SUP_TEL2;
        BPCSFECT.CP_NO = BPRFCTR.CARD_PSBK_NO;
        BPCSFECT.SALE_DT = BPRFCTR.SALE_DATE;
        CEP.TRC(SCCGWA, BPRFCTR.CCY_TYPE);
        BPCSFECT.CCY_TYPE = BPRFCTR.CCY_TYPE;
        CEP.TRC(SCCGWA, BPRFCTR.CCY_TYPE);
        BPCSFECT.CREV_NO = BPRFCTR.CREV_NO;
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, BPRFCTR.START_DATE);
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPRFCPH.KEY.VALUE_DATE = BPRFCTR.START_DATE;
        T000_READ_BPTFCPH();
        if (pgmRtn) return;
        BPCSFECT.AMT_PCT = BPRFCPH.MULTI;
        BPCSFECT.INT_BASIC = BPRFCPH.INT_BAS;
        BPCSFECT.REF_FEE_DATA[1-1].REF_AMT = BPRFCPH.UP_AMT_1;
        BPCSFECT.REF_FEE_DATA[1-1].REF_PCT = BPRFCPH.UP_PCT_1;
        BPCSFECT.REF_FEE_DATA[1-1].AGG_MTH = BPRFCPH.AGG_MTH_1;
        BPCSFECT.REF_FEE_DATA[1-1].FEE_AMT = BPRFCPH.FEE_AMT_1;
        BPCSFECT.REF_FEE_DATA[1-1].FEE_RATE = BPRFCPH.FEE_RATE_1;
        BPCSFECT.REF_FEE_DATA[2-1].REF_AMT = BPRFCPH.UP_AMT_2;
        BPCSFECT.REF_FEE_DATA[2-1].REF_PCT = BPRFCPH.UP_PCT_2;
        BPCSFECT.REF_FEE_DATA[2-1].AGG_MTH = BPRFCPH.AGG_MTH_2;
        BPCSFECT.REF_FEE_DATA[2-1].FEE_AMT = BPRFCPH.FEE_AMT_2;
        BPCSFECT.REF_FEE_DATA[2-1].FEE_RATE = BPRFCPH.FEE_RATE_2;
        BPCSFECT.REF_FEE_DATA[3-1].REF_AMT = BPRFCPH.UP_AMT_3;
        BPCSFECT.REF_FEE_DATA[3-1].REF_PCT = BPRFCPH.UP_PCT_3;
        BPCSFECT.REF_FEE_DATA[3-1].AGG_MTH = BPRFCPH.AGG_MTH_3;
        BPCSFECT.REF_FEE_DATA[3-1].FEE_AMT = BPRFCPH.FEE_AMT_3;
        BPCSFECT.REF_FEE_DATA[3-1].FEE_RATE = BPRFCPH.FEE_RATE_3;
        BPCSFECT.REF_FEE_DATA[4-1].REF_AMT = BPRFCPH.UP_AMT_4;
        BPCSFECT.REF_FEE_DATA[4-1].REF_PCT = BPRFCPH.UP_PCT_4;
        BPCSFECT.REF_FEE_DATA[4-1].AGG_MTH = BPRFCPH.AGG_MTH_4;
        BPCSFECT.REF_FEE_DATA[4-1].FEE_AMT = BPRFCPH.FEE_AMT_4;
        BPCSFECT.REF_FEE_DATA[4-1].FEE_RATE = BPRFCPH.FEE_RATE_4;
        BPCSFECT.REF_FEE_DATA[5-1].REF_AMT = BPRFCPH.UP_AMT_5;
        BPCSFECT.REF_FEE_DATA[5-1].REF_PCT = BPRFCPH.UP_PCT_5;
        BPCSFECT.REF_FEE_DATA[5-1].AGG_MTH = BPRFCPH.AGG_MTH_5;
        BPCSFECT.REF_FEE_DATA[5-1].FEE_AMT = BPRFCPH.FEE_AMT_5;
        BPCSFECT.REF_FEE_DATA[5-1].FEE_RATE = BPRFCPH.FEE_RATE_5;
        CEP.TRC(SCCGWA, BPCSFECT.RLTD_CTNO);
        if (BPCSFECT.CINO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "XXXXXXXXX");
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSFECT.CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_ENM.trim().length() > 0) {
                BPCSFECT.CI_ABBR_NM = CICCUST.O_DATA.O_CI_ENM;
            }
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                BPCSFECT.CI_ABBR_NM = CICCUST.O_DATA.O_CI_NM;
            }
        }
        CEP.TRC(SCCGWA, "INQURE OUT DATA:");
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT);
        CEP.TRC(SCCGWA, BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT);
        CEP.TRC(SCCGWA, BPCSFECT.CI_ABBR_NM);
    }
    public void B300_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B300_10_CREATE_BPTFCTR();
        if (pgmRtn) return;
        B300_20_CREATE_BPTFCPH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSFECT.PAY_METH);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_FLG);
        if (BPCSFECT.CHG_FLG == 'Y') {
            CEP.TRC(SCCGWA, "AAA");
            if (BPCSFECT.RELT_FLG == '0' 
                && BPCSFECT.PAY_METH == 'P') {
                CEP.TRC(SCCGWA, "BBB");
                R000_SET_FEE_INFO();
                if (pgmRtn) return;
                R000_CHG_FEE_PROC();
                if (pgmRtn) return;
                B300_CREATE_BPTFAMO();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            if (BPCSFECT.CHG_FLG == 'N') {
                CEP.TRC(SCCGWA, "DDD");
                CEP.TRC(SCCGWA, "WRITE BPTFAMO IF CHARGE FLAG IS N");
                B300_CREATE_BPTFAMO();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B300_40_HISTORY_RECORD_CN();
            if (pgmRtn) return;
        } else {
            B300_40_HISTORY_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B300_10_CREATE_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBCH);
        IBS.init(SCCGWA, BPRFCTR);
        IBS.init(SCCGWA, BPCCGCT);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        if (pgmRtn) return;
        BPCCGCT.BR = "737";
        BPCCGCT.PRDT_CODE = " ";
        if (BPCCGCT.SPEC_PREFIX == null) BPCCGCT.SPEC_PREFIX = "";
        JIBS_tmp_int = BPCCGCT.SPEC_PREFIX.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.SPEC_PREFIX += " ";
        BPCCGCT.SPEC_PREFIX = "51" + BPCCGCT.SPEC_PREFIX.substring(2);
        CEP.TRC(SCCGWA, BPCCGCT.BR);
        CEP.TRC(SCCGWA, BPCCGCT.PRDT_CODE);
        S000_CALL_BPZGCTNO();
        if (pgmRtn) return;
        BPRFCTR.KEY.CTRT_NO = BPCCGCT.CTNO;
        BPCSFECT.CTNO = BPCCGCT.CTNO;
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        CEP.TRC(SCCGWA, BPRFCTR.KEY.CTRT_NO);
        R000_MOVE_TO_BPRFCTR();
        if (pgmRtn) return;
        T000_WRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B300_20_CREATE_BPTFCPH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPRFCPH.KEY.VALUE_DATE = BPCSFECT.START_DT;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_MOVE_TO_BPRFCPH_CN();
            if (pgmRtn) return;
        } else {
            R000_MOVE_TO_BPRFCPH();
            if (pgmRtn) return;
        }
        T000_WRITE_BPTFCPH();
        if (pgmRtn) return;
    }
    public void R000_GET_GLMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFBAS.GL_MASTER1);
        CEP.TRC(SCCGWA, BPRFBAS.GL_MASTER2);
        WS_GLMST1 = BPRFBAS.GL_MASTER1;
        if (BPRFBAS.GL_MASTER2 != 0 
            && (WS_CUS_TYPE == 'C' 
            || WS_CUS_TYPE == 'F')) {
            WS_GLMST1 = BPRFBAS.GL_MASTER2;
        }
        CEP.TRC(SCCGWA, WS_GLMST1);
    }
    public void B300_40_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCOFCTR);
        IBS.init(SCCGWA, BPCNFCTR);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = BPCSFECT.CINO;
        BPCPNHIS.INFO.REF_NO = BPRFCTR.KEY.CTRT_NO;
        BPCPNHIS.INFO.TX_RMK = "FEE CONTRACT - ADD";
        BPCPNHIS.INFO.FMT_ID = "BPCHFCTR";
        BPCNFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCNFCTR.CTRT_DESC = BPCSFECT.FEE_DESC;
        BPCNFCTR.CI_NO = BPCSFECT.CINO;
        BPCNFCTR.CTRT_TYPE = BPCSFECT.CT_TYP;
        BPCNFCTR.FEE_TYPE = BPCSFECT.FEE_TYP;
        BPCNFCTR.BOOK_CENTRE = BPCSFECT.BOOK_ACCT;
        BPCNFCTR.PRD_TYPE = BPCSFECT.PRDT_TYP;
        BPCNFCTR.START_DATE = BPCSFECT.START_DT;
        BPCNFCTR.MATURITY_DATE = BPCSFECT.MATURITY_DT;
        BPCNFCTR.HOLI_OVER = BPCSFECT.HOL_OVR;
        BPCNFCTR.CAL_CODE1 = BPCSFECT.CAL_CD1;
        BPCNFCTR.HOLI_METHOD = BPCSFECT.HOL_METH;
        BPCNFCTR.CAL_CODE2 = BPCSFECT.CAL_CD2;
        BPCNFCTR.PAY_IND = BPCSFECT.PAY_IND;
        BPCNFCTR.CASHFLOW_IND = BPCSFECT.CSH_FLW_IND;
        BPCNFCTR.BANK_PORTF = BPCSFECT.BK_PFLO;
        BPCNFCTR.PAYMENT_METHOD = BPCSFECT.PAY_METH;
        BPCNFCTR.ACCRUAL_TYPE = BPCSFECT.ACU_TYP;
        BPCNFCTR.PRICE_METHOD = BPCSFECT.FIX_PRC_METH;
        BPCNFCTR.TXN_CCY = BPCSFECT.TXN_CCY;
        BPCNFCTR.PRIN_AMT = BPCSFECT.TXN_AMT;
        BPCNFCTR.REL_CTRT_NO = BPCSFECT.RLTD_CTNO;
        BPCNFCTR.RLTD_CT_TYP = BPCSFECT.RLTD_CT_TYP;
        BPCNFCTR.AMT_TYPE = BPCSFECT.AMT_TYP;
        BPCNFCTR.RLTD_PRDT_TYP = BPCSFECT.RLTD_PRDT_TYP;
        BPCNFCTR.MULTI = BPCSFECT.AMT_PCT;
        BPCNFCTR.INT_BAS = BPCSFECT.INT_BASIC;
        BPCNFCTR.AGGR_TYPE = BPCSFECT.AGGR_TYP;
        BPCNFCTR.REF_CCY = BPCSFECT.REF_CCY;
        BPCNFCTR.REF_METHOD = BPCSFECT.REF_METH;
        BPCNFCTR.REF_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_AMT;
        BPCNFCTR.REF_PCT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_PCT;
        BPCNFCTR.FEE_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_AMT;
        BPCNFCTR.FEE_RATE1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_RATE;
        BPCNFCTR.REF_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_AMT;
        BPCNFCTR.REF_PCT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_PCT;
        BPCNFCTR.FEE_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_AMT;
        BPCNFCTR.FEE_RATE2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_RATE;
        BPCNFCTR.REF_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_AMT;
        BPCNFCTR.REF_PCT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_PCT;
        BPCNFCTR.FEE_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_AMT;
        BPCNFCTR.FEE_RATE3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_RATE;
        BPCNFCTR.REF_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_AMT;
        BPCNFCTR.REF_PCT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_PCT;
        BPCNFCTR.FEE_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_AMT;
        BPCNFCTR.FEE_RATE4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_RATE;
        BPCNFCTR.REF_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_AMT;
        BPCNFCTR.REF_PCT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_PCT;
        BPCNFCTR.FEE_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_AMT;
        BPCNFCTR.FEE_RATE5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_RATE;
        BPCNFCTR.CHARGE_CCY = BPCSFECT.CHG_CCY;
        BPCNFCTR.CHARGE_AMT = BPCSFECT.CHG_AMT;
        BPCNFCTR.CHARGE_METHOD = BPCSFECT.CHG_METH;
        BPCNFCTR.CHARGE_AC = BPCSFECT.CHG_ACNO;
        BPCNFCTR.NOSTRO_AC = BPCSFECT.NOSTRO_CD;
        BPCNFCTR.CHQ_NO = BPCSFECT.CHQ_NO;
        BPCNFCTR.GL_MASTER1 = WS_GLMST1;
        BPCNFCTR.GL_MASTER2 = BPCSFECT.GL_MST_HO;
        BPCNFCTR.GL_MASTER3 = BPCSFECT.GL_MST_IAS39;
        BPCNFCTR.GL_MASTER4 = BPCSFECT.GL_MST_UNUS;
        if (BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE1 = 0;
        else BPCNFCTR.OIC_CENTRE1 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        BPCNFCTR.OIC_NO1 = "" + BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO1 = "0" + BPCNFCTR.OIC_NO1;
        BPCNFCTR.OIC_PCT1 = BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE2 = 0;
        else BPCNFCTR.OIC_CENTRE2 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        BPCNFCTR.OIC_NO2 = "" + BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO2 = "0" + BPCNFCTR.OIC_NO2;
        BPCNFCTR.OIC_PCT2 = BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE3 = 0;
        else BPCNFCTR.OIC_CENTRE3 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        BPCNFCTR.OIC_NO3 = "" + BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO3 = "0" + BPCNFCTR.OIC_NO3;
        BPCNFCTR.OIC_PCT3 = BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE4 = 0;
        else BPCNFCTR.OIC_CENTRE4 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC);
        BPCNFCTR.OIC_NO4 = "" + BPCSFECT.TXN_OIC_DATA[4-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO4.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO4 = "0" + BPCNFCTR.OIC_NO4;
        BPCNFCTR.OIC_PCT4 = BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE5 = 0;
        else BPCNFCTR.OIC_CENTRE5 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC);
        BPCNFCTR.OIC_NO5 = "" + BPCSFECT.TXN_OIC_DATA[5-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO5.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO5 = "0" + BPCNFCTR.OIC_NO5;
        BPCNFCTR.OIC_PCT5 = BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT;
        BPCNFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCNFCTR.REMARK = BPCSFECT.REMARK;
        BPCNFCTR.FEE_BAS_AMT = BPCSFECT.FEE_BAS_AMT;
        BPCNFCTR.CHG_CCY_REAL = BPCSFECT.CHG_CCY_REAL;
        BPCNFCTR.CHG_AMT_REAL = BPCSFECT.CHG_AMT_REAL;
        BPCNFCTR.TICKET = BPCSFECT.TICKET;
        BPCNFCTR.RATE = BPCSFECT.RATE;
        BPCNFCTR.EXG_DATE = BPCSFECT.EXG_DATE;
        BPCNFCTR.EXG_TIME = BPCSFECT.EXG_TIME;
        BPCNFCTR.FCHG_MIN_AMT = BPCSFECT.MIN_AMT;
        CEP.TRC(SCCGWA, "FEE CONTRACT - ADD");
        CEP.TRC(SCCGWA, BPCNFCTR.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_DESC);
        CEP.TRC(SCCGWA, BPCNFCTR.CI_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.BOOK_CENTRE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.START_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_OVER);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE1);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE2);
        CEP.TRC(SCCGWA, BPCNFCTR.PAY_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.CASHFLOW_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.BANK_PORTF);
        CEP.TRC(SCCGWA, BPCNFCTR.PAYMENT_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRICE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.TXN_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.PRIN_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_CT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.AMT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_PRDT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.MULTI);
        CEP.TRC(SCCGWA, BPCNFCTR.INT_BAS);
        CEP.TRC(SCCGWA, BPCNFCTR.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE5);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.NOSTRO_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.CHQ_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER1);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER2);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER3);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER4);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_STATUS);
        CEP.TRC(SCCGWA, BPCNFCTR.REMARK);
        BPCPNHIS.INFO.FMT_ID_LEN = 1031;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNFCTR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B300_40_HISTORY_RECORD_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCOFCTR);
        IBS.init(SCCGWA, BPCNFCTR);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = BPCSFECT.CINO;
        BPCPNHIS.INFO.REF_NO = BPRFCTR.KEY.CTRT_NO;
        BPCPNHIS.INFO.TX_RMK = "FEE CONTRACT - ADD";
        BPCPNHIS.INFO.FMT_ID = "BPCHFCTR";
        BPCNFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCNFCTR.CTRT_DESC = BPCSFECT.FEE_DESC;
        BPCNFCTR.CI_NO = BPCSFECT.CINO;
        BPCNFCTR.CTRT_TYPE = BPCSFECT.CT_TYP;
        BPCNFCTR.FEE_TYPE = BPCSFECT.FEE_TYP;
        BPCNFCTR.BOOK_CENTRE = BPCSFECT.BOOK_ACCT;
        BPCNFCTR.PRD_TYPE = BPCSFECT.PRDT_TYP;
        BPCNFCTR.START_DATE = BPCSFECT.START_DT;
        BPCNFCTR.MATURITY_DATE = BPCSFECT.MATURITY_DT;
        BPCNFCTR.HOLI_OVER = BPCSFECT.HOL_OVR;
        BPCNFCTR.CAL_CODE1 = BPCSFECT.CAL_CD1;
        BPCNFCTR.HOLI_METHOD = BPCSFECT.HOL_METH;
        BPCNFCTR.CAL_CODE2 = BPCSFECT.CAL_CD2;
        BPCNFCTR.PAY_IND = BPCSFECT.PAY_IND;
        BPCNFCTR.CASHFLOW_IND = BPCSFECT.CSH_FLW_IND;
        BPCNFCTR.BANK_PORTF = BPCSFECT.BK_PFLO;
        BPCNFCTR.PAYMENT_METHOD = BPCSFECT.PAY_METH;
        BPCNFCTR.ACCRUAL_TYPE = BPCSFECT.ACU_TYP;
        BPCNFCTR.PRICE_METHOD = BPCSFECT.FIX_PRC_METH;
        BPCNFCTR.TXN_CCY = BPCSFECT.TXN_CCY;
        BPCNFCTR.PRIN_AMT = BPCSFECT.TXN_AMT;
        BPCNFCTR.REL_CTRT_NO = BPCSFECT.RLTD_CTNO;
        BPCNFCTR.RLTD_CT_TYP = BPCSFECT.RLTD_CT_TYP;
        BPCNFCTR.AMT_TYPE = BPCSFECT.AMT_TYP;
        BPCNFCTR.RLTD_PRDT_TYP = BPCSFECT.RLTD_PRDT_TYP;
        BPCNFCTR.MULTI = BPCSFECT.AMT_PCT;
        BPCNFCTR.INT_BAS = BPCSFECT.INT_BASIC;
        BPCNFCTR.AGGR_TYPE = BPCSFECT.AGGR_TYP;
        BPCNFCTR.REF_CCY = BPCSFECT.REF_CCY;
        BPCNFCTR.REF_METHOD = BPCSFECT.REF_METH;
        BPCNFCTR.REF_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_AMT;
        BPCNFCTR.REF_PCT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_PCT;
        BPCNFCTR.AGG_MTH1 = BPCSFECT.REF_FEE_DATA[1-1].AGG_MTH;
        BPCNFCTR.FEE_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_AMT;
        BPCNFCTR.FEE_RATE1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_RATE;
        BPCNFCTR.REF_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_AMT;
        BPCNFCTR.REF_PCT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_PCT;
        BPCNFCTR.AGG_MTH2 = BPCSFECT.REF_FEE_DATA[2-1].AGG_MTH;
        BPCNFCTR.FEE_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_AMT;
        BPCNFCTR.FEE_RATE2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_RATE;
        BPCNFCTR.REF_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_AMT;
        BPCNFCTR.REF_PCT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_PCT;
        BPCNFCTR.AGG_MTH3 = BPCSFECT.REF_FEE_DATA[3-1].AGG_MTH;
        BPCNFCTR.FEE_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_AMT;
        BPCNFCTR.FEE_RATE3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_RATE;
        BPCNFCTR.REF_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_AMT;
        BPCNFCTR.REF_PCT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_PCT;
        BPCNFCTR.AGG_MTH4 = BPCSFECT.REF_FEE_DATA[4-1].AGG_MTH;
        BPCNFCTR.FEE_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_AMT;
        BPCNFCTR.FEE_RATE4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_RATE;
        BPCNFCTR.REF_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_AMT;
        BPCNFCTR.REF_PCT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_PCT;
        BPCNFCTR.AGG_MTH5 = BPCSFECT.REF_FEE_DATA[5-1].AGG_MTH;
        BPCNFCTR.FEE_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_AMT;
        BPCNFCTR.FEE_RATE5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_RATE;
        BPCNFCTR.CHARGE_CCY = BPCSFECT.CHG_CCY;
        BPCNFCTR.CHARGE_AMT = BPCSFECT.CHG_AMT;
        BPCNFCTR.CHARGE_METHOD = BPCSFECT.CHG_METH;
        BPCNFCTR.CHARGE_AC = BPCSFECT.CHG_ACNO;
        BPCNFCTR.NOSTRO_AC = BPCSFECT.NOSTRO_CD;
        BPCNFCTR.CHQ_NO = BPCSFECT.CHQ_NO;
        BPCNFCTR.GL_MASTER1 = WS_GLMST1;
        BPCNFCTR.GL_MASTER2 = BPCSFECT.GL_MST_HO;
        BPCNFCTR.GL_MASTER3 = BPCSFECT.GL_MST_IAS39;
        BPCNFCTR.GL_MASTER4 = BPCSFECT.GL_MST_UNUS;
        if (BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE1 = 0;
        else BPCNFCTR.OIC_CENTRE1 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        BPCNFCTR.OIC_NO1 = "" + BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO1 = "0" + BPCNFCTR.OIC_NO1;
        BPCNFCTR.OIC_PCT1 = BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE2 = 0;
        else BPCNFCTR.OIC_CENTRE2 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        BPCNFCTR.OIC_NO2 = "" + BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO2 = "0" + BPCNFCTR.OIC_NO2;
        BPCNFCTR.OIC_PCT2 = BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE3 = 0;
        else BPCNFCTR.OIC_CENTRE3 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        BPCNFCTR.OIC_NO3 = "" + BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO3 = "0" + BPCNFCTR.OIC_NO3;
        BPCNFCTR.OIC_PCT3 = BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE4 = 0;
        else BPCNFCTR.OIC_CENTRE4 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC);
        BPCNFCTR.OIC_NO4 = "" + BPCSFECT.TXN_OIC_DATA[4-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO4.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO4 = "0" + BPCNFCTR.OIC_NO4;
        BPCNFCTR.OIC_PCT4 = BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE5 = 0;
        else BPCNFCTR.OIC_CENTRE5 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC);
        BPCNFCTR.OIC_NO5 = "" + BPCSFECT.TXN_OIC_DATA[5-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO5.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO5 = "0" + BPCNFCTR.OIC_NO5;
        BPCNFCTR.OIC_PCT5 = BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT;
        BPCNFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCNFCTR.REMARK = BPCSFECT.REMARK;
        BPCNFCTR.FEE_BAS_AMT = BPCSFECT.FEE_BAS_AMT;
        BPCNFCTR.CHG_CCY_REAL = BPCSFECT.CHG_CCY_REAL;
        BPCNFCTR.CHG_AMT_REAL = BPCSFECT.CHG_AMT_REAL;
        BPCNFCTR.TICKET = BPCSFECT.TICKET;
        BPCNFCTR.RATE = BPCSFECT.RATE;
        BPCNFCTR.EXG_DATE = BPCSFECT.EXG_DATE;
        BPCNFCTR.EXG_TIME = BPCSFECT.EXG_TIME;
        BPCNFCTR.FCHG_MIN_AMT = BPCSFECT.MIN_AMT;
        BPCNFCTR.CP_NO = BPCSFECT.CP_NO;
        BPCNFCTR.SALE_DT = BPCSFECT.SALE_DT;
        BPCNFCTR.CCY_TYPE = BPCSFECT.CCY_TYPE;
        BPCNFCTR.CREV_NO = BPCSFECT.CREV_NO;
        CEP.TRC(SCCGWA, "FEE CONTRACT - ADD");
        CEP.TRC(SCCGWA, BPCNFCTR.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_DESC);
        CEP.TRC(SCCGWA, BPCNFCTR.CI_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.BOOK_CENTRE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.START_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_OVER);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE1);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE2);
        CEP.TRC(SCCGWA, BPCNFCTR.PAY_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.CASHFLOW_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.BANK_PORTF);
        CEP.TRC(SCCGWA, BPCNFCTR.PAYMENT_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRICE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.TXN_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.PRIN_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_CT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.AMT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_PRDT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.MULTI);
        CEP.TRC(SCCGWA, BPCNFCTR.INT_BAS);
        CEP.TRC(SCCGWA, BPCNFCTR.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE5);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.NOSTRO_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.CHQ_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER1);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER2);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER3);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER4);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_STATUS);
        CEP.TRC(SCCGWA, BPCNFCTR.REMARK);
        BPCPNHIS.INFO.FMT_ID_LEN = 1031;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNFCTR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B400_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_MOVE_OLD_DATA_HIS_CN();
            if (pgmRtn) return;
        } else {
            R000_MOVE_OLD_DATA_HIS();
            if (pgmRtn) return;
        }
        B400_10_UPDATE_BPTFCTR();
        if (pgmRtn) return;
        B400_20_UPDATE_BPTFCPH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, WS_OLD_ST_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_OLD_JNL_NO);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B400_40_HISTORY_RECORD_CN();
            if (pgmRtn) return;
        } else {
            B400_40_HISTORY_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B400_10_UPDATE_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        T000_READ_UPD_BPTFCTR();
        if (pgmRtn) return;
        WS_OLD_ST_DT = BPRFCTR.START_DATE;
        WS_OLD_MA_DT = BPRFCTR.MATURITY_DATE;
        WS_OLD_CREATE_DT = BPRFCTR.CREATE_DATE;
        WS_OLD_JNL_NO = BPRFCTR.JNL_NO;
        CEP.TRC(SCCGWA, BPRFCTR.START_DATE);
        CEP.TRC(SCCGWA, BPRFCTR.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPRFCTR.CREATE_DATE);
        R000_MOVE_TO_BPRFCTR();
        if (pgmRtn) return;
        T000_REWRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B400_20_UPDATE_BPTFCPH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCPH);
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_OLD_ST_DT);
        CEP.TRC(SCCGWA, BPCSFECT.START_DT);
        if (BPCSFECT.START_DT != WS_OLD_ST_DT) {
            BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
            BPRFCPH.KEY.VALUE_DATE = WS_OLD_ST_DT;
            T000_READ_UPD_BPTFCPH();
            if (pgmRtn) return;
            WS_OLD_CRT_DT = BPRFCPH.CREATE_DATE;
            WS_OLD_CRT_TL = BPRFCPH.CREATE_TELL;
            T000_DELETE_BPTFCPH();
            if (pgmRtn) return;
            BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
            BPRFCPH.KEY.VALUE_DATE = BPCSFECT.START_DT;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                R000_MOVE_TO_BPRFCPH_CN();
                if (pgmRtn) return;
            } else {
                R000_MOVE_TO_BPRFCPH();
                if (pgmRtn) return;
            }
            T000_WRITE_BPTFCPH();
            if (pgmRtn) return;
        } else {
            BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
            BPRFCPH.KEY.VALUE_DATE = BPCSFECT.START_DT;
            T000_READ_UPD_BPTFCPH();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                R000_MOVE_TO_BPRFCPH_CN();
                if (pgmRtn) return;
            } else {
                R000_MOVE_TO_BPRFCPH();
                if (pgmRtn) return;
            }
            T000_REWRITE_BPTFCPH();
            if (pgmRtn) return;
        }
    }
    public void R000_MOVE_OLD_DATA_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPRFCPH.KEY.VALUE_DATE = BPRFCTR.START_DATE;
        T000_READ_BPTFCPH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOFCTR);
        BPCOFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCOFCTR.CTRT_DESC = BPRFCTR.CTRT_DESC;
        BPCOFCTR.CI_NO = BPRFCTR.CI_NO;
        BPCOFCTR.CTRT_TYPE = BPRFCTR.CTRT_TYPE;
        BPCOFCTR.FEE_TYPE = BPRFCTR.FEE_TYPE;
        BPCOFCTR.BOOK_CENTRE = BPRFCTR.BOOK_CENTRE;
        BPCOFCTR.PRD_TYPE = BPRFCTR.PRD_TYPE;
        BPCOFCTR.START_DATE = BPRFCTR.START_DATE;
        BPCOFCTR.MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        BPCOFCTR.HOLI_OVER = BPRFCTR.HOLI_OVER;
        BPCOFCTR.CAL_CODE1 = BPRFCTR.CAL_CODE1;
        BPCOFCTR.HOLI_METHOD = BPRFCTR.HOLI_METHOD;
        BPCOFCTR.CAL_CODE2 = BPRFCTR.CAL_CODE2;
        BPCOFCTR.PAY_IND = BPRFCTR.PAY_IND;
        BPCOFCTR.CASHFLOW_IND = BPRFCTR.CASHFLOW_IND;
        BPCOFCTR.BANK_PORTF = BPRFCTR.BANK_PORTF;
        BPCOFCTR.PAYMENT_METHOD = BPRFCTR.PAYMENT_METHOD;
        BPCOFCTR.ACCRUAL_TYPE = BPRFCTR.ACCRUAL_TYPE;
        BPCOFCTR.PRICE_METHOD = BPRFCTR.PRICE_METHOD;
        BPCOFCTR.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCOFCTR.PRIN_AMT = BPRFCTR.PRIN_AMT;
        BPCOFCTR.REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
        BPCOFCTR.AMT_TYPE = BPRFCTR.AMT_TYPE;
        BPCOFCTR.AGGR_TYPE = BPRFCTR.AGGR_TYPE;
        BPCOFCTR.REF_CCY = BPRFCTR.REF_CCY;
        BPCOFCTR.REF_METHOD = BPRFCTR.REF_METHOD;
        BPCOFCTR.MULTI = BPRFCPH.MULTI;
        BPCOFCTR.INT_BAS = BPRFCPH.INT_BAS;
        BPCOFCTR.REF_AMT1 = BPRFCPH.UP_AMT_1;
        BPCOFCTR.REF_PCT1 = BPRFCPH.UP_PCT_1;
        BPCOFCTR.FEE_AMT1 = BPRFCPH.FEE_AMT_1;
        BPCOFCTR.FEE_RATE1 = BPRFCPH.FEE_RATE_1;
        BPCOFCTR.REF_AMT2 = BPRFCPH.UP_AMT_2;
        BPCOFCTR.REF_PCT2 = BPRFCPH.UP_PCT_2;
        BPCOFCTR.FEE_AMT2 = BPRFCPH.FEE_AMT_2;
        BPCOFCTR.FEE_RATE2 = BPRFCPH.FEE_RATE_2;
        BPCOFCTR.REF_AMT3 = BPRFCPH.UP_AMT_3;
        BPCOFCTR.REF_PCT3 = BPRFCPH.UP_PCT_3;
        BPCOFCTR.FEE_AMT3 = BPRFCPH.FEE_AMT_3;
        BPCOFCTR.FEE_RATE3 = BPRFCPH.FEE_RATE_3;
        BPCOFCTR.REF_AMT4 = BPRFCPH.UP_AMT_4;
        BPCOFCTR.REF_PCT4 = BPRFCPH.UP_PCT_4;
        BPCOFCTR.FEE_AMT4 = BPRFCPH.FEE_AMT_4;
        BPCOFCTR.FEE_RATE4 = BPRFCPH.FEE_RATE_4;
        BPCOFCTR.REF_AMT5 = BPRFCPH.UP_AMT_5;
        BPCOFCTR.REF_PCT5 = BPRFCPH.UP_PCT_5;
        BPCOFCTR.FEE_AMT5 = BPRFCPH.FEE_AMT_5;
        BPCOFCTR.FEE_RATE5 = BPRFCPH.FEE_RATE_5;
        BPCOFCTR.CHARGE_CCY = BPRFCTR.CHARGE_CCY;
        BPCOFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT;
        BPCOFCTR.CHARGE_METHOD = BPRFCTR.CHARGE_METHOD;
        BPCOFCTR.CHARGE_AC = BPRFCTR.CHARGE_AC;
        BPCOFCTR.NOSTRO_AC = BPRFCTR.NOSTRO_AC;
        BPCOFCTR.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCOFCTR.GL_MASTER1 = BPRFCTR.GL_MASTER1;
        BPCOFCTR.GL_MASTER2 = BPRFCTR.GL_MASTER2;
        BPCOFCTR.GL_MASTER3 = BPRFCTR.GL_MASTER3;
        BPCOFCTR.GL_MASTER4 = BPRFCTR.GL_MASTER4;
        BPCOFCTR.OIC_NO1 = "" + BPRFCTR.OIC_NO1;
        JIBS_tmp_int = BPCOFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO1 = "0" + BPCOFCTR.OIC_NO1;
        if (BPRFCTR.OIC_CENTRE1.trim().length() == 0) BPCOFCTR.OIC_CENTRE1 = 0;
        else BPCOFCTR.OIC_CENTRE1 = Integer.parseInt(BPRFCTR.OIC_CENTRE1);
        BPCOFCTR.OIC_PCT1 = BPRFCTR.OIC_PCT1;
        BPCOFCTR.OIC_NO2 = "" + BPRFCTR.OIC_NO2;
        JIBS_tmp_int = BPCOFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO2 = "0" + BPCOFCTR.OIC_NO2;
        if (BPRFCTR.OIC_CENTRE2.trim().length() == 0) BPCOFCTR.OIC_CENTRE2 = 0;
        else BPCOFCTR.OIC_CENTRE2 = Integer.parseInt(BPRFCTR.OIC_CENTRE2);
        BPCOFCTR.OIC_PCT2 = BPRFCTR.OIC_PCT2;
        BPCOFCTR.OIC_NO3 = "" + BPRFCTR.OIC_NO3;
        JIBS_tmp_int = BPCOFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO3 = "0" + BPCOFCTR.OIC_NO3;
        if (BPRFCTR.OIC_CENTRE3.trim().length() == 0) BPCOFCTR.OIC_CENTRE3 = 0;
        else BPCOFCTR.OIC_CENTRE3 = Integer.parseInt(BPRFCTR.OIC_CENTRE3);
        BPCOFCTR.OIC_PCT3 = BPRFCTR.OIC_PCT3;
        BPCOFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCOFCTR.REMARK = BPRFCTR.REMARK;
        BPCOFCTR.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCOFCTR.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCOFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCOFCTR.TICKET = BPRFCTR.TICKET;
        BPCOFCTR.RATE = BPRFCTR.RATE;
        BPCOFCTR.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCOFCTR.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCOFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
    }
    public void R000_MOVE_OLD_DATA_HIS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPRFCPH.KEY.VALUE_DATE = BPRFCTR.START_DATE;
        T000_READ_BPTFCPH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOFCTR);
        BPCOFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCOFCTR.CTRT_DESC = BPRFCTR.CTRT_DESC;
        BPCOFCTR.CI_NO = BPRFCTR.CI_NO;
        BPCOFCTR.CTRT_TYPE = BPRFCTR.CTRT_TYPE;
        BPCOFCTR.FEE_TYPE = BPRFCTR.FEE_TYPE;
        BPCOFCTR.BOOK_CENTRE = BPRFCTR.BOOK_CENTRE;
        BPCOFCTR.PRD_TYPE = BPRFCTR.PRD_TYPE;
        BPCOFCTR.START_DATE = BPRFCTR.START_DATE;
        BPCOFCTR.MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        BPCOFCTR.HOLI_OVER = BPRFCTR.HOLI_OVER;
        BPCOFCTR.CAL_CODE1 = BPRFCTR.CAL_CODE1;
        BPCOFCTR.HOLI_METHOD = BPRFCTR.HOLI_METHOD;
        BPCOFCTR.CAL_CODE2 = BPRFCTR.CAL_CODE2;
        BPCOFCTR.PAY_IND = BPRFCTR.PAY_IND;
        BPCOFCTR.CASHFLOW_IND = BPRFCTR.CASHFLOW_IND;
        BPCOFCTR.BANK_PORTF = BPRFCTR.BANK_PORTF;
        BPCOFCTR.PAYMENT_METHOD = BPRFCTR.PAYMENT_METHOD;
        BPCOFCTR.ACCRUAL_TYPE = BPRFCTR.ACCRUAL_TYPE;
        BPCOFCTR.PRICE_METHOD = BPRFCTR.PRICE_METHOD;
        BPCOFCTR.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCOFCTR.PRIN_AMT = BPRFCTR.PRIN_AMT;
        BPCOFCTR.REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
        BPCOFCTR.AMT_TYPE = BPRFCTR.AMT_TYPE;
        BPCOFCTR.AGGR_TYPE = BPRFCTR.AGGR_TYPE;
        BPCOFCTR.REF_CCY = BPRFCTR.REF_CCY;
        BPCOFCTR.REF_METHOD = BPRFCTR.REF_METHOD;
        BPCOFCTR.MULTI = BPRFCPH.MULTI;
        BPCOFCTR.INT_BAS = BPRFCPH.INT_BAS;
        BPCOFCTR.REF_AMT1 = BPRFCPH.UP_AMT_1;
        BPCOFCTR.REF_PCT1 = BPRFCPH.UP_PCT_1;
        BPCOFCTR.AGG_MTH1 = BPRFCPH.AGG_MTH_1;
        BPCOFCTR.FEE_AMT1 = BPRFCPH.FEE_AMT_1;
        BPCOFCTR.FEE_RATE1 = BPRFCPH.FEE_RATE_1;
        BPCOFCTR.REF_AMT2 = BPRFCPH.UP_AMT_2;
        BPCOFCTR.REF_PCT2 = BPRFCPH.UP_PCT_2;
        BPCOFCTR.AGG_MTH2 = BPRFCPH.AGG_MTH_2;
        BPCOFCTR.FEE_AMT2 = BPRFCPH.FEE_AMT_2;
        BPCOFCTR.FEE_RATE2 = BPRFCPH.FEE_RATE_2;
        BPCOFCTR.REF_AMT3 = BPRFCPH.UP_AMT_3;
        BPCOFCTR.REF_PCT3 = BPRFCPH.UP_PCT_3;
        BPCOFCTR.AGG_MTH3 = BPRFCPH.AGG_MTH_3;
        BPCOFCTR.FEE_AMT3 = BPRFCPH.FEE_AMT_3;
        BPCOFCTR.FEE_RATE3 = BPRFCPH.FEE_RATE_3;
        BPCOFCTR.REF_AMT4 = BPRFCPH.UP_AMT_4;
        BPCOFCTR.REF_PCT4 = BPRFCPH.UP_PCT_4;
        BPCOFCTR.AGG_MTH4 = BPRFCPH.AGG_MTH_4;
        BPCOFCTR.FEE_AMT4 = BPRFCPH.FEE_AMT_4;
        BPCOFCTR.FEE_RATE4 = BPRFCPH.FEE_RATE_4;
        BPCOFCTR.REF_AMT5 = BPRFCPH.UP_AMT_5;
        BPCOFCTR.REF_PCT5 = BPRFCPH.UP_PCT_5;
        BPCOFCTR.AGG_MTH5 = BPRFCPH.AGG_MTH_5;
        BPCOFCTR.FEE_AMT5 = BPRFCPH.FEE_AMT_5;
        BPCOFCTR.FEE_RATE5 = BPRFCPH.FEE_RATE_5;
        BPCOFCTR.CHARGE_CCY = BPRFCTR.CHARGE_CCY;
        BPCOFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT;
        BPCOFCTR.CHARGE_METHOD = BPRFCTR.CHARGE_METHOD;
        BPCOFCTR.CHARGE_AC = BPRFCTR.CHARGE_AC;
        BPCOFCTR.NOSTRO_AC = BPRFCTR.NOSTRO_AC;
        BPCOFCTR.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCOFCTR.GL_MASTER1 = BPRFCTR.GL_MASTER1;
        BPCOFCTR.GL_MASTER2 = BPRFCTR.GL_MASTER2;
        BPCOFCTR.GL_MASTER3 = BPRFCTR.GL_MASTER3;
        BPCOFCTR.GL_MASTER4 = BPRFCTR.GL_MASTER4;
        BPCOFCTR.OIC_NO1 = "" + BPRFCTR.OIC_NO1;
        JIBS_tmp_int = BPCOFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO1 = "0" + BPCOFCTR.OIC_NO1;
        if (BPRFCTR.OIC_CENTRE1.trim().length() == 0) BPCOFCTR.OIC_CENTRE1 = 0;
        else BPCOFCTR.OIC_CENTRE1 = Integer.parseInt(BPRFCTR.OIC_CENTRE1);
        BPCOFCTR.OIC_PCT1 = BPRFCTR.OIC_PCT1;
        BPCOFCTR.OIC_NO2 = "" + BPRFCTR.OIC_NO2;
        JIBS_tmp_int = BPCOFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO2 = "0" + BPCOFCTR.OIC_NO2;
        if (BPRFCTR.OIC_CENTRE2.trim().length() == 0) BPCOFCTR.OIC_CENTRE2 = 0;
        else BPCOFCTR.OIC_CENTRE2 = Integer.parseInt(BPRFCTR.OIC_CENTRE2);
        BPCOFCTR.OIC_PCT2 = BPRFCTR.OIC_PCT2;
        BPCOFCTR.OIC_NO3 = "" + BPRFCTR.OIC_NO3;
        JIBS_tmp_int = BPCOFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOFCTR.OIC_NO3 = "0" + BPCOFCTR.OIC_NO3;
        if (BPRFCTR.OIC_CENTRE3.trim().length() == 0) BPCOFCTR.OIC_CENTRE3 = 0;
        else BPCOFCTR.OIC_CENTRE3 = Integer.parseInt(BPRFCTR.OIC_CENTRE3);
        BPCOFCTR.OIC_PCT3 = BPRFCTR.OIC_PCT3;
        BPCOFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCOFCTR.REMARK = BPRFCTR.REMARK;
        BPCOFCTR.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCOFCTR.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCOFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCOFCTR.TICKET = BPRFCTR.TICKET;
        BPCOFCTR.RATE = BPRFCTR.RATE;
        BPCOFCTR.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCOFCTR.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCOFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        BPCOFCTR.CP_NO = BPRFCTR.CARD_PSBK_NO;
        BPCOFCTR.SALE_DT = BPRFCTR.SALE_DATE;
        BPCOFCTR.CCY_TYPE = BPRFCTR.CCY_TYPE;
        BPCOFCTR.CREV_NO = BPRFCTR.CREV_NO;
    }
    public void B400_40_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCNFCTR);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = BPRFCTR.CI_NO;
        BPCPNHIS.INFO.REF_NO = BPRFCTR.KEY.CTRT_NO;
        BPCPNHIS.INFO.TX_RMK = "FEE CONTRACT - UPD";
        BPCPNHIS.INFO.FMT_ID = "BPCHFCTR";
        BPCNFCTR.KEY.CTRT_NO = BPCSFECT.CTNO;
        BPCNFCTR.CTRT_DESC = BPCSFECT.FEE_DESC;
        BPCNFCTR.CI_NO = BPCSFECT.CINO;
        BPCNFCTR.CTRT_TYPE = BPCSFECT.CT_TYP;
        BPCNFCTR.FEE_TYPE = BPCSFECT.FEE_TYP;
        BPCNFCTR.BOOK_CENTRE = BPCSFECT.BOOK_ACCT;
        BPCNFCTR.PRD_TYPE = BPCSFECT.PRDT_TYP;
        BPCNFCTR.START_DATE = BPCSFECT.START_DT;
        BPCNFCTR.MATURITY_DATE = BPCSFECT.MATURITY_DT;
        BPCNFCTR.HOLI_OVER = BPCSFECT.HOL_OVR;
        BPCNFCTR.CAL_CODE1 = BPCSFECT.CAL_CD1;
        BPCNFCTR.HOLI_METHOD = BPCSFECT.HOL_METH;
        BPCNFCTR.CAL_CODE2 = BPCSFECT.CAL_CD2;
        BPCNFCTR.PAY_IND = BPCSFECT.PAY_IND;
        BPCNFCTR.CASHFLOW_IND = BPCSFECT.CSH_FLW_IND;
        BPCNFCTR.BANK_PORTF = BPCSFECT.BK_PFLO;
        BPCNFCTR.PAYMENT_METHOD = BPCSFECT.PAY_METH;
        BPCNFCTR.ACCRUAL_TYPE = BPCSFECT.ACU_TYP;
        BPCNFCTR.PRICE_METHOD = BPCSFECT.FIX_PRC_METH;
        BPCNFCTR.TXN_CCY = BPCSFECT.TXN_CCY;
        BPCNFCTR.PRIN_AMT = BPCSFECT.TXN_AMT;
        BPCNFCTR.REL_CTRT_NO = BPCSFECT.RLTD_CTNO;
        BPCNFCTR.RLTD_CT_TYP = BPCSFECT.RLTD_CT_TYP;
        BPCNFCTR.AMT_TYPE = BPCSFECT.AMT_TYP;
        BPCNFCTR.RLTD_PRDT_TYP = BPCSFECT.RLTD_PRDT_TYP;
        BPCNFCTR.AMT_TYPE = BPCSFECT.AMT_TYP;
        BPCNFCTR.MULTI = BPCSFECT.AMT_PCT;
        BPCNFCTR.INT_BAS = BPCSFECT.INT_BASIC;
        BPCNFCTR.AGGR_TYPE = BPCSFECT.AGGR_TYP;
        BPCNFCTR.REF_CCY = BPCSFECT.REF_CCY;
        BPCNFCTR.REF_METHOD = BPCSFECT.REF_METH;
        BPCNFCTR.REF_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_AMT;
        BPCNFCTR.REF_PCT1 = BPCSFECT.REF_FEE_DATA[1-1].REF_PCT;
        BPCNFCTR.FEE_AMT1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_AMT;
        BPCNFCTR.FEE_RATE1 = BPCSFECT.REF_FEE_DATA[1-1].FEE_RATE;
        BPCNFCTR.REF_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_AMT;
        BPCNFCTR.REF_PCT2 = BPCSFECT.REF_FEE_DATA[2-1].REF_PCT;
        BPCNFCTR.FEE_AMT2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_AMT;
        BPCNFCTR.FEE_RATE2 = BPCSFECT.REF_FEE_DATA[2-1].FEE_RATE;
        BPCNFCTR.REF_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_AMT;
        BPCNFCTR.REF_PCT3 = BPCSFECT.REF_FEE_DATA[3-1].REF_PCT;
        BPCNFCTR.FEE_AMT3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_AMT;
        BPCNFCTR.FEE_RATE3 = BPCSFECT.REF_FEE_DATA[3-1].FEE_RATE;
        BPCNFCTR.REF_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_AMT;
        BPCNFCTR.REF_PCT4 = BPCSFECT.REF_FEE_DATA[4-1].REF_PCT;
        BPCNFCTR.FEE_AMT4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_AMT;
        BPCNFCTR.FEE_RATE4 = BPCSFECT.REF_FEE_DATA[4-1].FEE_RATE;
        BPCNFCTR.REF_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_AMT;
        BPCNFCTR.REF_PCT5 = BPCSFECT.REF_FEE_DATA[5-1].REF_PCT;
        BPCNFCTR.FEE_AMT5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_AMT;
        BPCNFCTR.FEE_RATE5 = BPCSFECT.REF_FEE_DATA[5-1].FEE_RATE;
        BPCNFCTR.CHARGE_CCY = BPCSFECT.CHG_CCY;
        BPCNFCTR.CHARGE_AMT = BPCSFECT.CHG_AMT;
        BPCNFCTR.CHARGE_METHOD = BPCSFECT.CHG_METH;
        BPCNFCTR.CHARGE_AC = BPCSFECT.CHG_ACNO;
        BPCNFCTR.NOSTRO_AC = BPCSFECT.NOSTRO_CD;
        BPCNFCTR.CHQ_NO = BPCSFECT.CHQ_NO;
        BPCNFCTR.GL_MASTER1 = WS_GLMST1;
        BPCNFCTR.GL_MASTER2 = BPCSFECT.GL_MST_HO;
        BPCNFCTR.GL_MASTER3 = BPCSFECT.GL_MST_IAS39;
        BPCNFCTR.GL_MASTER4 = BPCSFECT.GL_MST_UNUS;
        if (BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE1 = 0;
        else BPCNFCTR.OIC_CENTRE1 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC);
        BPCNFCTR.OIC_NO1 = "" + BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO1 = "0" + BPCNFCTR.OIC_NO1;
        BPCNFCTR.OIC_PCT1 = BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE2 = 0;
        else BPCNFCTR.OIC_CENTRE2 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC);
        BPCNFCTR.OIC_NO2 = "" + BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO2 = "0" + BPCNFCTR.OIC_NO2;
        BPCNFCTR.OIC_PCT2 = BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE3 = 0;
        else BPCNFCTR.OIC_CENTRE3 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC);
        BPCNFCTR.OIC_NO3 = "" + BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO3 = "0" + BPCNFCTR.OIC_NO3;
        BPCNFCTR.OIC_PCT3 = BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE4 = 0;
        else BPCNFCTR.OIC_CENTRE4 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[4-1].TXN_OIC);
        BPCNFCTR.OIC_NO4 = "" + BPCSFECT.TXN_OIC_DATA[4-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO4.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO4 = "0" + BPCNFCTR.OIC_NO4;
        BPCNFCTR.OIC_PCT4 = BPCSFECT.TXN_OIC_DATA[4-1].PRFT_PCT;
        if (BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC.trim().length() == 0) BPCNFCTR.OIC_CENTRE5 = 0;
        else BPCNFCTR.OIC_CENTRE5 = Integer.parseInt(BPCSFECT.TXN_OIC_DATA[5-1].TXN_OIC);
        BPCNFCTR.OIC_NO5 = "" + BPCSFECT.TXN_OIC_DATA[5-1].OIC_ACCT;
        JIBS_tmp_int = BPCNFCTR.OIC_NO5.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO5 = "0" + BPCNFCTR.OIC_NO5;
        BPCNFCTR.OIC_PCT5 = BPCSFECT.TXN_OIC_DATA[5-1].PRFT_PCT;
        BPCNFCTR.FEE_STATUS = BPCSFECT.CT_STS;
        BPCNFCTR.REMARK = BPCSFECT.REMARK;
        BPCNFCTR.FEE_BAS_AMT = BPCSFECT.FEE_BAS_AMT;
        BPCNFCTR.CHG_CCY_REAL = BPCSFECT.CHG_CCY_REAL;
        BPCNFCTR.CHG_AMT_REAL = BPCSFECT.CHG_AMT_REAL;
        BPCNFCTR.TICKET = BPCSFECT.TICKET;
        BPCNFCTR.RATE = BPCSFECT.RATE;
        BPCNFCTR.EXG_DATE = BPCSFECT.EXG_DATE;
        BPCNFCTR.EXG_TIME = BPCSFECT.EXG_TIME;
        BPCNFCTR.FCHG_MIN_AMT = BPCSFECT.MIN_AMT;
        CEP.TRC(SCCGWA, "FEE CONTRACT - UPD(NEW)");
        CEP.TRC(SCCGWA, BPCNFCTR.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_DESC);
        CEP.TRC(SCCGWA, BPCNFCTR.CI_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.CTRT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.BOOK_CENTRE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.START_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_OVER);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE1);
        CEP.TRC(SCCGWA, BPCNFCTR.HOLI_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CAL_CODE2);
        CEP.TRC(SCCGWA, BPCNFCTR.PAY_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.CASHFLOW_IND);
        CEP.TRC(SCCGWA, BPCNFCTR.BANK_PORTF);
        CEP.TRC(SCCGWA, BPCNFCTR.PAYMENT_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.PRICE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.TXN_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.PRIN_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_CT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.AMT_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.RLTD_PRDT_TYP);
        CEP.TRC(SCCGWA, BPCNFCTR.MULTI);
        CEP.TRC(SCCGWA, BPCNFCTR.INT_BAS);
        CEP.TRC(SCCGWA, BPCNFCTR.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT1);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE1);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT2);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE2);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE3);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT4);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE4);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.REF_PCT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_AMT5);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_RATE5);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_METHOD);
        CEP.TRC(SCCGWA, BPCNFCTR.CHARGE_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.NOSTRO_AC);
        CEP.TRC(SCCGWA, BPCNFCTR.CHQ_NO);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER1);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER2);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER3);
        CEP.TRC(SCCGWA, BPCNFCTR.GL_MASTER4);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT1);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT2);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_NO3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_CENTRE3);
        CEP.TRC(SCCGWA, BPCNFCTR.OIC_PCT3);
        CEP.TRC(SCCGWA, BPCNFCTR.FEE_STATUS);
        CEP.TRC(SCCGWA, BPCNFCTR.REMARK);
        CEP.TRC(SCCGWA, "FEE CONTRACT - UPD(OLD)");
        CEP.TRC(SCCGWA, BPCOFCTR.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCOFCTR.CTRT_DESC);
        CEP.TRC(SCCGWA, BPCOFCTR.CI_NO);
        CEP.TRC(SCCGWA, BPCOFCTR.CTRT_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.BOOK_CENTRE);
        CEP.TRC(SCCGWA, BPCOFCTR.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.START_DATE);
        CEP.TRC(SCCGWA, BPCOFCTR.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPCOFCTR.HOLI_OVER);
        CEP.TRC(SCCGWA, BPCOFCTR.CAL_CODE1);
        CEP.TRC(SCCGWA, BPCOFCTR.HOLI_METHOD);
        CEP.TRC(SCCGWA, BPCOFCTR.CAL_CODE2);
        CEP.TRC(SCCGWA, BPCOFCTR.PAY_IND);
        CEP.TRC(SCCGWA, BPCOFCTR.CASHFLOW_IND);
        CEP.TRC(SCCGWA, BPCOFCTR.BANK_PORTF);
        CEP.TRC(SCCGWA, BPCOFCTR.PAYMENT_METHOD);
        CEP.TRC(SCCGWA, BPCOFCTR.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.PRICE_METHOD);
        CEP.TRC(SCCGWA, BPCOFCTR.TXN_CCY);
        CEP.TRC(SCCGWA, BPCOFCTR.PRIN_AMT);
        CEP.TRC(SCCGWA, BPCOFCTR.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCOFCTR.RLTD_CT_TYP);
        CEP.TRC(SCCGWA, BPCOFCTR.AMT_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.RLTD_PRDT_TYP);
        CEP.TRC(SCCGWA, BPCOFCTR.MULTI);
        CEP.TRC(SCCGWA, BPCOFCTR.INT_BAS);
        CEP.TRC(SCCGWA, BPCOFCTR.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_CCY);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_METHOD);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_AMT1);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_PCT1);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_AMT1);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_RATE1);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_AMT2);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_PCT2);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_AMT2);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_RATE2);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_AMT3);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_PCT3);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_AMT3);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_RATE3);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_AMT4);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_PCT4);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_AMT4);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_RATE4);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_AMT5);
        CEP.TRC(SCCGWA, BPCOFCTR.REF_PCT5);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_AMT5);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_RATE5);
        CEP.TRC(SCCGWA, BPCOFCTR.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCOFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCOFCTR.CHARGE_METHOD);
        CEP.TRC(SCCGWA, BPCOFCTR.CHARGE_AC);
        CEP.TRC(SCCGWA, BPCOFCTR.NOSTRO_AC);
        CEP.TRC(SCCGWA, BPCOFCTR.CHQ_NO);
        CEP.TRC(SCCGWA, BPCOFCTR.GL_MASTER1);
        CEP.TRC(SCCGWA, BPCOFCTR.GL_MASTER2);
        CEP.TRC(SCCGWA, BPCOFCTR.GL_MASTER3);
        CEP.TRC(SCCGWA, BPCOFCTR.GL_MASTER4);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_NO1);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_CENTRE1);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_PCT1);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_NO2);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_CENTRE2);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_PCT2);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_NO3);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_CENTRE3);
        CEP.TRC(SCCGWA, BPCOFCTR.OIC_PCT3);
        CEP.TRC(SCCGWA, BPCOFCTR.FEE_STATUS);
        CEP.TRC(SCCGWA, BPCOFCTR.REMARK);
