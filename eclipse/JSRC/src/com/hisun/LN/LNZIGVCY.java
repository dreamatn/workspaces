package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCACLDD;
import com.hisun.BP.BPCBWEVT;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCUGMC;
import com.hisun.BP.BPRVWA;
import com.hisun.BP.BPRWVET;
import com.hisun.CI.CICACCU;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.VT.VTCPQTAX;

public class LNZIGVCY {
    boolean pgmRtn = false;
    short K_MAX_AMT = 76;
    String K_DAILY_ACCRUAL = "LN00";
    String K_OVD_PROC = "LN00";
    String K_DAILY_REVERSAL = "LN00";
    String K_TONOACR_TNA = "LN00";
    String K_TONOACR_TAC = "LN00";
    String K_OVD_BACK = "LN00";
    String K_PN_BACK = "LN00";
    String K_AP_MMO = "LN";
    String K_CPN_RSC_LNZRCMMT = "LN-RSC-LNTCMMT";
    short K_RATE_DIF_INCOME = 9999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IND = 0;
    short WS_CNT = 0;
    double WS_BAL_NORMAL = 0;
    double WS_TMP_AMT = 0;
    double WS_TMP_BLOCK_AMT = 0;
    LNZIGVCY_WS_SUB_CTA_NO WS_SUB_CTA_NO = new LNZIGVCY_WS_SUB_CTA_NO();
    LNZIGVCY_WS_AMT_INFO[] WS_AMT_INFO = new LNZIGVCY_WS_AMT_INFO[80];
    double WS_OUT_AMT = 0;
    double WS_AMT_BAL = 0;
    double WS_ZZ_TAX = 0;
    double WS_ZDZZ_TAX = 0;
    double WS_IO_TAX_INT = 0;
    double WS_WAI_AMT = 0;
    double WS_TAX_BASE_AMT = 0;
    double WS_BANK_INT = 0;
    double WS_N_P_AMT = 0;
    double WS_O_P_AMT = 0;
    double WS_N_I_AMT = 0;
    double WS_O_I_AMT = 0;
    double WS_OLC_AMT = 0;
    double WS_LLC_AMT = 0;
    double WS_PAY_WFP_AMT = 0;
    double WS_PAY_WFI_AMT = 0;
    double WS_PAY_WFO_AMT = 0;
    double WS_PAY_WFL_AMT = 0;
    char WS_REC_FLAG = ' ';
    LNZIGVCY_WS_TAX_DATAS WS_TAX_DATAS = new LNZIGVCY_WS_TAX_DATAS();
    int WS_I = 0;
    double WS_BK_I_AMT = 0;
    double WS_BK_O_AMT = 0;
    double WS_OWE_P_AMT = 0;
    double WS_OWE_I_AMT = 0;
    double WS_OWE_O_AMT = 0;
    double WS_OWE_L_AMT = 0;
    double WS_OWE_F_AMT = 0;
    double WS_PRE_P_AMT = 0;
    double WS_PRE_I_AMT = 0;
    LNZIGVCY_WS_PART_DATA WS_PART_DATA = new LNZIGVCY_WS_PART_DATA();
    LNZIGVCY_WS_BEF_PART_DATA WS_BEF_PART_DATA = new LNZIGVCY_WS_BEF_PART_DATA();
    LNZIGVCY_WS_AFT_PART_DATA WS_AFT_PART_DATA = new LNZIGVCY_WS_AFT_PART_DATA();
    double WS_TOT_PAY_INT = 0;
    double WS_SYNL_AMT13 = 0;
    double WS_SYNL_AMT15 = 0;
    double WS_SYNL_AMT17 = 0;
    double WS_SYNL_AMT19 = 0;
    double WS_SYNL_AMT35 = 0;
    double WS_LOCAL_AMT7 = 0;
    double WS_LOCAL_AMT13 = 0;
    double WS_LOCAL_AMT15 = 0;
    double WS_LOCAL_AMT21 = 0;
    double WS_LOCAL_AMT41 = 0;
    double WS_LOCAL_AMT43 = 0;
    double WS_LOCAL_AMT51 = 0;
    char WS_INQ_TAX_FLG = ' ';
    int WS_PART_NO = 0;
    double WS_AMT_TAX_NEW = 0;
    int WS_PQTAX_SEQ = 0;
    String WS_VOUCHER_H = "LN";
    String WS_PART_ID = " ";
    int WS_VOUCHER_SEQ = 0;
    char WS_TRAN_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_RLN_FLG = ' ';
    char WS_CMMT_FLG = ' ';
    char WS_CMMT_AVAIL_FLG = ' ';
    char WS_VCH_FLG = ' ';
    char WS_AMT_UPD_FLG = ' ';
    char WS_AMT_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_FOUND_PART_FLG = ' ';
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNRWOFF LNRWOFF = new LNRWOFF();
    BPRWVET BPRWVET = new BPRWVET();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCUGMC BPCUGMC = new BPCUGMC();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCRTRAN LNCRTRAN = new LNCRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCIPART LNCIPART = new LNCIPART();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    CICACCU CICACCU = new CICACCU();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    SCCGWA SCCGWA;
    SCCBKPO SCCBKPO;
    SCCBPCT SCCBPCT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    SCCBATH SCCBATH;
    LNCIGVCY LNCIGVCY;
    public LNZIGVCY() {
        for (int i=0;i<80;i++) WS_AMT_INFO[i] = new LNZIGVCY_WS_AMT_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCIGVCY LNCIGVCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIGVCY = LNCIGVCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIGVCY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GEN_VCH();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CNTR_TYPE);
        LNCIGVCY.DATA.CNTR_TYPE = "CLDD";
        if (!LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM") 
            && !LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            && !LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLGU") 
            && !LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDL") 
            && !LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDP")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONTRACT_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.PROD_CODE_OLD);
        if (LNCIGVCY.DATA.PROD_CODE_OLD.trim().length() == 0 
            && !LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CTA_NO);
        if (LNCIGVCY.DATA.CTA_NO.equalsIgnoreCase("0")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        if (LNCIGVCY.DATA.EVENT_CODE.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_EVN_CODE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BR_OLD);
        if (LNCIGVCY.DATA.BR_OLD == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CCY_INFO[1-1].CCY);
        if (LNCIGVCY.DATA.CCY_INFO[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.VALUE_DATE);
        if (LNCIGVCY.DATA.VALUE_DATE == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_VALUE_DATE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_READ_LNTBALZ_PART();
        if (pgmRtn) return;
    }
    public void B020_GEN_VCH() throws IOException,SQLException,Exception {
        for (WS_IND = 1; WS_IND <= 80; WS_IND += 1) {
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[WS_IND-1].AMT);
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B020_01_GEN_BAT_VCH();
            if (pgmRtn) return;
        } else {
            B020_02_GEN_ONL_VCH();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GEN_BAT_VCH() throws IOException,SQLException,Exception {
        R010_GET_MAX_VCHNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNZIGVCY_WS2);
        IBS.init(SCCGWA, BPCBWEVT);
        BPCBWEVT.INFO.AP_MMO = " ";
        BPCBWEVT.INFO.TR_CODE = " ";
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        if (JIBS_sdf.format(JIBS_date).substring(0, 8).trim().length() == 0) BPCBWEVT.INFO.TR_DATE = 0;
        else BPCBWEVT.INFO.TR_DATE = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(0, 8));
        if (JIBS_sdf.format(JIBS_date).substring(8, 16).trim().length() == 0) BPCBWEVT.INFO.TR_TIME = 0;
        else BPCBWEVT.INFO.TR_TIME = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(8, 16));
        CEP.TRC(SCCGWA, "111");
        BPCBWEVT.INFO.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCBWEVT.INFO.TR_BR = LNCIGVCY.DATA.BR_OLD;
        BPCBWEVT.INFO.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCBWEVT.INFO.EVENT.CNTR_TYPE = LNCIGVCY.DATA.CNTR_TYPE;
        BPCBWEVT.INFO.EVENT.PROD_CODE = LNCIGVCY.DATA.PROD_CODE_OLD;
        BPCBWEVT.INFO.EVENT.AC_NO = LNCIGVCY.DATA.CTA_NO;
        BPCBWEVT.INFO.EVENT.EVENT_CODE = LNCIGVCY.DATA.EVENT_CODE;
        BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
        BPCBWEVT.INFO.EVENT.EVENT_CCY[1-1].CCY = LNCIGVCY.DATA.CCY_INFO[1-1].CCY;
        BPCBWEVT.INFO.EVENT.VAL_DATE = LNCIGVCY.DATA.VALUE_DATE;
        BPCBWEVT.INFO.AP_MMO = K_AP_MMO;
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("AM")) {
            BPCBWEVT.INFO.EVENT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCBWEVT.INFO.EVENT.CI_NO = LNCIGVCY.DATA.CI_NO;
        CEP.TRC(SCCGWA, "222");
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.SUB_CTA_NO);
        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIGVCY.DATA.SUB_CTA_NO;
        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
        CEP.TRC(SCCGWA, BPCBWEVT.INFO.EVENT.REF_NO);
        CEP.TRC(SCCGWA, "223");
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.PORT);
        BPCBWEVT.INFO.EVENT.PORTFO_CD = LNCIGVCY.DATA.PORT;
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EFF_DAYS);
        BPCBWEVT.INFO.EVENT.EFF_DAYS = LNCIGVCY.DATA.EFF_DAYS;
        CEP.TRC(SCCGWA, "224");
        BPCBWEVT.CLO_FILE_FLG = LNCIGVCY.DATA.CLO_FILE_FLG;
        CEP.TRC(SCCGWA, "333");
        BPCBWEVT.INFO.EVENT.AC_NO_REL = LNCIGVCY.DATA.CTA_NO;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNZIGVCY_WS2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBWEVT.INFO.SET_NO);
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCIGVCY.DATA.STATUS.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            B021_SYNLN_AMT_PROC();
            if (pgmRtn) return;
        } else {
            R000_TRANS_AMT_TO_AC_MODEL();
            if (pgmRtn) return;
            R010_GEN_BATVCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void R010_GET_MAX_VCHNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCBPCT.PART_ID);
        if (WS_VOUCHER_SEQ == 0) {
            IBS.init(SCCGWA, BPRWVET);
            if (SCCBPCT.PART_ID == null) SCCBPCT.PART_ID = "";
            JIBS_tmp_int = SCCBPCT.PART_ID.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) SCCBPCT.PART_ID += " ";
            WS_PART_ID = SCCBPCT.PART_ID.substring(3 - 1, 3 + 3 - 1);
            BPRWVET.KEY.SET_NO = IBS.CLS2CPY(SCCGWA, LNZIGVCY_WS2);
            if (BPRWVET.KEY.SET_NO == null) BPRWVET.KEY.SET_NO = "";
            JIBS_tmp_int = BPRWVET.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRWVET.KEY.SET_NO += " ";
            BPRWVET.KEY.SET_NO = BPRWVET.KEY.SET_NO.substring(0, 6 - 1) + "%%%%%%%" + BPRWVET.KEY.SET_NO.substring(6 + 7 - 1);
            S000_GET_MAX_SEQ();
            if (pgmRtn) return;
        } else {
            WS_VOUCHER_SEQ += 1;
        }
    }
    public void S000_GET_MAX_SEQ() throws IOException,SQLException,Exception {
        BPTWVET_RD = new DBParm();
        BPTWVET_RD.TableName = "BPTWVET";
        BPTWVET_RD.where = "SET_NO LIKE :BPRWVET.KEY.SET_NO";
        BPTWVET_RD.fst = true;
        BPTWVET_RD.errhdl = true;
        BPTWVET_RD.order = "SET_NO DESC";
        IBS.READ(SCCGWA, BPRWVET, this, BPTWVET_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPRWVET.KEY.SET_NO, LNZIGVCY_WS2);
            WS_VOUCHER_SEQ += 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VOUCHER_SEQ += 1;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTWVET";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B021_SYNLN_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNCIGVCY.DATA.CTA_NO;
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("ST")) {
            LNCIPAMT.P_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[19-1].AMT;
            LNCIPAMT.F_AMT = LNCIGVCY.DATA.AMT_INFO[72-1].AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                    } else {
                        BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                    }
                    if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        }
                    } else {
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                        }
                    }
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                        WS_AMT_INFO[1-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                        WS_AMT_INFO[19-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                        WS_SYNL_AMT19 += LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            WS_AMT_INFO[72-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_YHS_AMT;
                        } else {
                            if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                                WS_AMT_INFO[72-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_YHS_AMT;
                            }
                        }
                        WS_AMT_INFO[36-1].WS_AMT = 0;
                        WS_AMT_INFO[39-1].WS_AMT = 0;
                        WS_AMT_INFO[12-1].WS_AMT = 0;
                        if (LNCIGVCY.DATA.AMT_INFO[19-1].AMT > 0) {
                            WS_TAX_BASE_AMT = LNCIGVCY.DATA.AMT_INFO[19-1].AMT;
                            R010_COMP_ZZTAX();
                            if (pgmRtn) return;
                            LNCIGVCY.DATA.AMT_INFO[73-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                            WS_AMT_INFO[73-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[73-1].AMT;
                            R020_READUPD_LNTBALZ();
                            if (pgmRtn) return;
                            LNRBALZ.LOAN_BALL19 -= LNCIGVCY.DATA.AMT_INFO[73-1].AMT;
                            LNRBALZ.LOAN_BALL62 += LNCIGVCY.DATA.AMT_INFO[73-1].AMT;
                            R030_REWRITE_LNTBALZ();
                            if (pgmRtn) return;
                        }
                    } else {
                        WS_AMT_INFO[12-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                        WS_AMT_INFO[1-1].WS_AMT = 0;
                        WS_AMT_INFO[19-1].WS_AMT = 0;
                        WS_AMT_INFO[72-1].WS_AMT = 0;
                    }
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        R010_GEN_BATVCH_PROC();
                        if (pgmRtn) return;
                    } else {
                        R020_GEN_ONLVCH_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("DY")) {
            CEP.TRC(SCCGWA, "SYLNDY...");
            WS_PART_NO = 0;
            R000_READ_LNTBALZ_PART();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL15);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL42);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL52);
            IBS.init(SCCGWA, WS_BEF_PART_DATA);
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL42;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL52;
            LNCIPAMT.F_AMT = LNRBALZ.LOAN_BALL60;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT);
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT);
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT);
            }
            IBS.init(SCCGWA, WS_AFT_PART_DATA);
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 - LNCIGVCY.DATA.AMT_INFO[41-1].AMT;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL42 - LNCIGVCY.DATA.AMT_INFO[43-1].AMT;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL52 - LNCIGVCY.DATA.AMT_INFO[51-1].AMT;
            CEP.TRC(SCCGWA, LNCIPAMT.I_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.O_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.L_AMT);
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG > 'Y') {
                    WS_LOCAL_AMT41 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT + WS_LOCAL_AMT41;
                    WS_LOCAL_AMT43 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT + WS_LOCAL_AMT43;
                    WS_LOCAL_AMT51 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT + WS_LOCAL_AMT51;
                }
            }
            LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[41-1].AMT;
            LNCIPAMT.O_AMT = LNCIGVCY.DATA.AMT_INFO[43-1].AMT;
            LNCIPAMT.L_AMT = LNCIGVCY.DATA.AMT_INFO[51-1].AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_I-1].PART_NO);
                    WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                    R000_READ_LNTBALZ_PART();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                    } else {
                        BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                    }
                    if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        }
                    } else {
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                        }
                    }
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                        if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_AMT_INFO[45-1].WS_AMT = LNCIPAMT.I_AMT - WS_LOCAL_AMT41 + LNCIPAMT.O_AMT - WS_LOCAL_AMT43 + LNCIPAMT.L_AMT - WS_LOCAL_AMT51;
                            WS_AMT_INFO[41-1].WS_AMT = 0;
                            WS_AMT_INFO[43-1].WS_AMT = 0;
                            WS_AMT_INFO[51-1].WS_AMT = 0;
                        } else {
                            WS_AMT_INFO[41-1].WS_AMT = LNCIPAMT.I_AMT - WS_LOCAL_AMT41;
                            WS_AMT_INFO[43-1].WS_AMT = LNCIPAMT.O_AMT - WS_LOCAL_AMT43;
                            WS_AMT_INFO[51-1].WS_AMT = LNCIPAMT.L_AMT - WS_LOCAL_AMT51;
                        }
                        WS_AMT_INFO[37-1].WS_AMT = 0;
                        WS_AMT_INFO[39-1].WS_AMT = 0;
                        WS_AMT_INFO[40-1].WS_AMT = 0;
                        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                        if (LNCIGVCY.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_AMT_INFO[37-1].WS_AMT = LNCIPAMT.I_AMT - WS_LOCAL_AMT41 + LNCIPAMT.O_AMT - WS_LOCAL_AMT43 + LNCIPAMT.L_AMT - WS_LOCAL_AMT51;
                            WS_AMT_INFO[41-1].WS_AMT = 0;
                            WS_AMT_INFO[43-1].WS_AMT = 0;
                            WS_AMT_INFO[45-1].WS_AMT = 0;
                            WS_AMT_INFO[51-1].WS_AMT = 0;
                        }
                    } else {
                        WS_AMT_INFO[39-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT;
                        WS_AMT_INFO[41-1].WS_AMT = 0;
                        WS_AMT_INFO[42-1].WS_AMT = 0;
                        WS_AMT_INFO[43-1].WS_AMT = 0;
                        WS_AMT_INFO[45-1].WS_AMT = 0;
                        WS_AMT_INFO[51-1].WS_AMT = 0;
                        WS_AMT_INFO[37-1].WS_AMT = 0;
                    }
                    WS_AMT_INFO[73-1].WS_AMT = 0;
                    WS_AMT_INFO[74-1].WS_AMT = 0;
                    WS_AMT_INFO[75-1].WS_AMT = 0;
                    WS_AMT_INFO[67-1].WS_AMT = 0;
                    WS_AMT_INFO[68-1].WS_AMT = 0;
                    WS_PART_NO = 0;
                    R001_POST_INTAX_PROC();
                    if (pgmRtn) return;
                    WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                    R000_READUPD_LNTBALZ_PART();
                    if (pgmRtn) return;
                    LNRBALZ.LOAN_BALL15 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT;
                    LNRBALZ.LOAN_BALL42 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT;
                    LNRBALZ.LOAN_BALL52 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT;
                    if (WS_AMT_INFO[73-1].WS_AMT != 0 
                        || WS_AMT_INFO[74-1].WS_AMT != 0) {
                        if (LNRBALZ.LOAN_BALL60 == 0) {
                            if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                                LNRBALZ.LOAN_BALL60 = LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT + WS_AMT_INFO[73-1].WS_AMT + WS_AMT_INFO[74-1].WS_AMT;
                            } else {
                                LNRBALZ.LOAN_BALL60 = LNRBALZ.LOAN_BALL60 + WS_AMT_INFO[73-1].WS_AMT + WS_AMT_INFO[74-1].WS_AMT;
                            }
                        } else {
                            LNRBALZ.LOAN_BALL60 = LNRBALZ.LOAN_BALL60 + WS_AMT_INFO[73-1].WS_AMT + WS_AMT_INFO[74-1].WS_AMT;
                        }
                    }
                    if (WS_AMT_INFO[67-1].WS_AMT != 0 
                        || WS_AMT_INFO[68-1].WS_AMT != 0) {
                        LNRBALZ.LOAN_BALL60 = LNRBALZ.LOAN_BALL60 - WS_AMT_INFO[67-1].WS_AMT - WS_AMT_INFO[68-1].WS_AMT;
                    }
                    if (WS_FOUND_PART_FLG == 'Y') {
                        R000_REWRITE_LNTBALZ_PART();
                        if (pgmRtn) return;
                    } else {
                        R000_WRITE_LNTBALZ_PART();
                        if (pgmRtn) return;
                    }
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        R010_GEN_BATVCH_PROC();
                        if (pgmRtn) return;
                    } else {
                        R020_GEN_ONLVCH_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
            WS_PART_NO = 0;
            R000_READ_LNTBALZ_PART();
            if (pgmRtn) return;
            if (LNCIGVCY.DATA.AMT_INFO[7-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[14-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[15-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[17-1].AMT != 0) {
                LNCIGVCY.DATA.AMT_INFO[3-1].AMT = 0;
                LNCIGVCY.DATA.AMT_INFO[13-1].AMT = 0;
                LNCIGVCY.DATA.AMT_INFO[35-1].AMT = 0;
            }
            if (LNCIGVCY.DATA.AMT_INFO[7-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[14-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[15-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[17-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[21-1].AMT != 0) {
                WS_OWE_P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
                WS_OWE_I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
                WS_OWE_O_AMT = LNRBALZ.LOAN_BALL42;
                WS_OWE_L_AMT = LNRBALZ.LOAN_BALL52;
                WS_OWE_F_AMT = LNCIGVCY.DATA.AMT_INFO[17-1].AMT;
            } else {
                WS_PRE_P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
                WS_PRE_I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
            }
            if (LNCIGVCY.DATA.AMT_INFO[3-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[13-1].AMT != 0) {
                IBS.init(SCCGWA, WS_BEF_PART_DATA);
                LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06 + LNCIGVCY.DATA.AMT_INFO[3-1].AMT;
                LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 + LNCIGVCY.DATA.AMT_INFO[13-1].AMT;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                }
                IBS.init(SCCGWA, WS_AFT_PART_DATA);
                LNCIPAMT.P_AMT = WS_PRE_P_AMT;
                LNCIPAMT.I_AMT = WS_PRE_I_AMT;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                }
            }
            if (LNCIGVCY.DATA.AMT_INFO[7-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[14-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[15-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[21-1].AMT != 0) {
                IBS.init(SCCGWA, WS_BEF_PART_DATA);
                LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06 + LNCIGVCY.DATA.AMT_INFO[7-1].AMT;
                LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 + LNCIGVCY.DATA.AMT_INFO[14-1].AMT + LNCIGVCY.DATA.AMT_INFO[16-1].AMT;
                LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL42 + LNCIGVCY.DATA.AMT_INFO[15-1].AMT + LNCIGVCY.DATA.AMT_INFO[18-1].AMT;
                LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL52 + LNCIGVCY.DATA.AMT_INFO[21-1].AMT + LNCIGVCY.DATA.AMT_INFO[19-1].AMT;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                    WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                    CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT);
                    CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT);
                    CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT);
                    CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT);
                }
                IBS.init(SCCGWA, WS_AFT_PART_DATA);
                LNCIPAMT.P_AMT = WS_OWE_P_AMT;
                LNCIPAMT.I_AMT = WS_OWE_I_AMT;
                LNCIPAMT.O_AMT = WS_OWE_O_AMT;
                LNCIPAMT.L_AMT = WS_OWE_L_AMT;
                LNCIPAMT.F_AMT = WS_OWE_F_AMT;
                CEP.TRC(SCCGWA, LNCIPAMT.P_AMT);
                CEP.TRC(SCCGWA, LNCIPAMT.I_AMT);
                CEP.TRC(SCCGWA, LNCIPAMT.O_AMT);
                CEP.TRC(SCCGWA, LNCIPAMT.L_AMT);
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                    WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                    CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT);
                    CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT);
                    CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT);
                    CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT);
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG > 'Y') {
                        WS_LOCAL_AMT7 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT + WS_LOCAL_AMT7;
                        WS_LOCAL_AMT13 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT + WS_LOCAL_AMT13;
                        WS_LOCAL_AMT15 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT + WS_LOCAL_AMT15;
                        WS_LOCAL_AMT21 = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT + WS_LOCAL_AMT21;
                    }
                }
            }
            if (LNCIGVCY.DATA.AMT_INFO[7-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[14-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[15-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[17-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[21-1].AMT != 0) {
                LNCIPAMT.P_AMT = LNCIGVCY.DATA.AMT_INFO[7-1].AMT;
                LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[14-1].AMT + LNCIGVCY.DATA.AMT_INFO[16-1].AMT;
                LNCIPAMT.O_AMT = LNCIGVCY.DATA.AMT_INFO[15-1].AMT + LNCIGVCY.DATA.AMT_INFO[18-1].AMT;
                LNCIPAMT.L_AMT = LNCIGVCY.DATA.AMT_INFO[19-1].AMT + LNCIGVCY.DATA.AMT_INFO[21-1].AMT;
                LNCIGVCY.DATA.AMT_INFO[16-1].AMT = 0;
                LNCIGVCY.DATA.AMT_INFO[18-1].AMT = 0;
                LNCIGVCY.DATA.AMT_INFO[19-1].AMT = 0;
                LNCIPAMT.F_AMT = LNCIGVCY.DATA.AMT_INFO[17-1].AMT;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                        } else {
                            BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                        }
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            }
                        } else {
                            if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                                BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            }
                        }
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            WS_AMT_INFO[7-1].WS_AMT = LNCIPAMT.P_AMT - WS_LOCAL_AMT7;
                            WS_AMT_INFO[13-1].WS_AMT = LNCIPAMT.I_AMT - WS_LOCAL_AMT13;
                            WS_AMT_INFO[15-1].WS_AMT = LNCIPAMT.O_AMT - WS_LOCAL_AMT15;
                            WS_AMT_INFO[21-1].WS_AMT = LNCIPAMT.L_AMT - WS_LOCAL_AMT21;
                            WS_SYNL_AMT13 = WS_AMT_INFO[13-1].WS_AMT;
                            WS_SYNL_AMT15 = WS_AMT_INFO[15-1].WS_AMT;
                            WS_SYNL_AMT17 = WS_AMT_INFO[21-1].WS_AMT;
                            WS_AMT_INFO[17-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
                            CEP.TRC(SCCGWA, WS_AMT_INFO[7-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[13-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[15-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[21-1].WS_AMT);
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            R000_READUPD_LNTBALZ_PART();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                                LNRBALZ.LOAN_BALL42 = LNRBALZ.LOAN_BALL42 - LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                                LNRBALZ.LOAN_BALL52 = LNRBALZ.LOAN_BALL52 - LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            } else {
                                LNRBALZ.LOAN_BALL42 = LNRBALZ.LOAN_BALL42 + LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                                LNRBALZ.LOAN_BALL52 = LNRBALZ.LOAN_BALL52 + LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            }
                            R000_REWRITE_LNTBALZ_PART();
                            if (pgmRtn) return;
                            WS_AMT_INFO[36-1].WS_AMT = 0;
                            WS_AMT_INFO[39-1].WS_AMT = 0;
                            WS_AMT_INFO[12-1].WS_AMT = 0;
                            WS_ZDZZ_TAX = 0;
                            WS_IO_TAX_INT = 0;
                            WS_AMT_TAX_NEW = 0;
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            R010_BANK_INTAX_PROC();
                            if (pgmRtn) return;
                            WS_PART_NO = 0;
                            R020_READUPD_LNTBALZ();
                            if (pgmRtn) return;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                                || LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                LNRBALZ.LOAN_BALL66 -= WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL62 += WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL65 -= WS_IO_TAX_INT;
                            } else {
                                LNRBALZ.LOAN_BALL60 -= WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL62 += WS_ZDZZ_TAX;
                            }
                            LNRBALZ.LOAN_BALL62 += WS_AMT_TAX_NEW;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[57-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT;
                                WS_AMT_INFO[57-1].WS_AMT += WS_AMT_INFO[7-1].WS_AMT;
                                WS_AMT_INFO[3-1].WS_AMT = 0;
                                WS_AMT_INFO[7-1].WS_AMT = 0;
                                WS_TAX_BASE_AMT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[21-1].WS_AMT - LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
                                WS_AMT_INFO[13-1].WS_AMT = 0;
                                WS_AMT_INFO[15-1].WS_AMT = 0;
                                WS_AMT_INFO[21-1].WS_AMT = 0;
                                WS_INQ_TAX_FLG = 'Y';
                                R010_COMP_ZZTAX();
                                if (pgmRtn) return;
                                WS_AMT_INFO[70-1].WS_AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                                WS_AMT_INFO[29-1].WS_AMT = WS_TAX_BASE_AMT;
                                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                                    WS_TAX_BASE_AMT = LNCIGVCY.DATA.AMT_INFO[14-1].AMT + LNCIGVCY.DATA.AMT_INFO[15-1].AMT + LNCIGVCY.DATA.AMT_INFO[21-1].AMT;
                                    WS_INQ_TAX_FLG = 'Y';
                                    R010_COMP_ZZTAX();
                                    if (pgmRtn) return;
                                    LNRBALZ.LOAN_BALL65 = LNRBALZ.LOAN_BALL65 - WS_TAX_BASE_AMT + VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                                    LNRBALZ.LOAN_BALL66 = LNRBALZ.LOAN_BALL66 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                                }
                            }
                            R030_REWRITE_LNTBALZ();
                            if (pgmRtn) return;
                        } else {
                            WS_AMT_INFO[12-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT;
                            WS_AMT_INFO[39-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_O_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_O_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_L_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_L_AMT;
                            CEP.TRC(SCCGWA, WS_AMT_INFO[12-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[39-1].WS_AMT);
                            WS_AMT_INFO[3-1].WS_AMT = 0;
                            WS_AMT_INFO[7-1].WS_AMT = 0;
                            WS_AMT_INFO[13-1].WS_AMT = 0;
                            WS_AMT_INFO[15-1].WS_AMT = 0;
                            WS_AMT_INFO[17-1].WS_AMT = 0;
                            WS_AMT_INFO[21-1].WS_AMT = 0;
                            WS_AMT_INFO[57-1].WS_AMT = 0;
                            WS_AMT_INFO[29-1].WS_AMT = 0;
                            WS_AMT_INFO[70-1].WS_AMT = 0;
                            WS_AMT_INFO[75-1].WS_AMT = 0;
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            R000_READUPD_LNTBALZ_PART();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                                LNRBALZ.LOAN_BALL42 = LNRBALZ.LOAN_BALL42 - LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                                LNRBALZ.LOAN_BALL52 = LNRBALZ.LOAN_BALL52 - LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            } else {
                                LNRBALZ.LOAN_BALL42 = LNRBALZ.LOAN_BALL42 + LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                                LNRBALZ.LOAN_BALL52 = LNRBALZ.LOAN_BALL52 + LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            }
                            R000_REWRITE_LNTBALZ_PART();
                            if (pgmRtn) return;
                        }
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            R010_GEN_BATVCH_PROC();
                            if (pgmRtn) return;
                        } else {
                            R020_GEN_ONLVCH_PROC();
                            if (pgmRtn) return;
                        }
                        WS_AMT_INFO[12-1].WS_AMT = 0;
                        WS_AMT_INFO[39-1].WS_AMT = 0;
                    }
                    CEP.TRC(SCCGWA, WS_AMT_INFO[1-1].WS_AMT);
                }
            } else {
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                        } else {
                            BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                        }
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            }
                        } else {
                            if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                                BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            }
                        }
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            WS_AMT_INFO[3-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT;
                            WS_AMT_INFO[13-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT;
                            CEP.TRC(SCCGWA, WS_AMT_INFO[3-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[13-1].WS_AMT);
                            WS_AMT_INFO[15-1].WS_AMT = 0;
                            WS_AMT_INFO[17-1].WS_AMT = 0;
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            R000_READUPD_LNTBALZ_PART();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 - WS_AMT_INFO[13-1].WS_AMT;
                            } else {
                                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 + WS_AMT_INFO[13-1].WS_AMT;
                            }
                            R000_REWRITE_LNTBALZ_PART();
                            if (pgmRtn) return;
                            WS_AMT_INFO[36-1].WS_AMT = 0;
                            WS_AMT_INFO[39-1].WS_AMT = 0;
                            WS_AMT_INFO[12-1].WS_AMT = 0;
                            WS_ZDZZ_TAX = 0;
                            WS_IO_TAX_INT = 0;
                            WS_AMT_TAX_NEW = 0;
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            R010_BANK_INTAX_PROC();
                            if (pgmRtn) return;
                            WS_PART_NO = 0;
                            R020_READUPD_LNTBALZ();
                            if (pgmRtn) return;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                                || LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                LNRBALZ.LOAN_BALL66 -= WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL62 += WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL65 -= WS_IO_TAX_INT;
                            } else {
                                LNRBALZ.LOAN_BALL60 -= WS_ZDZZ_TAX;
                                LNRBALZ.LOAN_BALL62 += WS_ZDZZ_TAX;
                            }
                            LNRBALZ.LOAN_BALL62 += WS_AMT_TAX_NEW;
                            R030_REWRITE_LNTBALZ();
                            if (pgmRtn) return;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[57-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT;
                                WS_AMT_INFO[57-1].WS_AMT += WS_AMT_INFO[7-1].WS_AMT;
                                WS_AMT_INFO[3-1].WS_AMT = 0;
                                WS_AMT_INFO[7-1].WS_AMT = 0;
                            }
                        } else {
                            WS_AMT_INFO[12-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_P_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_P_AMT;
                            WS_AMT_INFO[39-1].WS_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_I-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_I-1].WS_AFT_PART_I_AMT;
                            WS_AMT_INFO[3-1].WS_AMT = 0;
                            WS_AMT_INFO[7-1].WS_AMT = 0;
                            WS_AMT_INFO[13-1].WS_AMT = 0;
                            WS_AMT_INFO[15-1].WS_AMT = 0;
                            WS_AMT_INFO[17-1].WS_AMT = 0;
                            WS_AMT_INFO[35-1].WS_AMT = 0;
                            WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            CEP.TRC(SCCGWA, WS_AMT_INFO[12-1].WS_AMT);
                            CEP.TRC(SCCGWA, WS_AMT_INFO[39-1].WS_AMT);
                            R000_READUPD_LNTBALZ_PART();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 - WS_AMT_INFO[39-1].WS_AMT;
                            } else {
                                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 + WS_AMT_INFO[39-1].WS_AMT;
                            }
                            R000_REWRITE_LNTBALZ_PART();
                            if (pgmRtn) return;
                        }
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            R010_GEN_BATVCH_PROC();
                            if (pgmRtn) return;
                        } else {
                            R020_GEN_ONLVCH_PROC();
                            if (pgmRtn) return;
                        }
                        WS_AMT_INFO[12-1].WS_AMT = 0;
                        WS_AMT_INFO[39-1].WS_AMT = 0;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_AMT_INFO[1-1].WS_AMT);
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("NO")) {
            LNCIPAMT.P_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[13-1].AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                    } else {
                        BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                    }
                    if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        }
                    } else {
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                        }
                    }
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                        WS_AMT_INFO[1-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                        WS_AMT_INFO[13-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                    }
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        R010_GEN_BATVCH_PROC();
                        if (pgmRtn) return;
                    } else {
                        R020_GEN_ONLVCH_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("PN")) {
            R020_READUPD_LNTBALZ();
            if (pgmRtn) return;
            WS_AMT_INFO[1-1].WS_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05;
            WS_AMT_INFO[5-1].WS_AMT = LNRBALZ.LOAN_BALL06 + 0;
            WS_AMT_INFO[13-1].WS_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
            WS_AMT_INFO[15-1].WS_AMT = LNRBALZ.LOAN_BALL42;
            WS_AMT_INFO[21-1].WS_AMT = LNRBALZ.LOAN_BALL52;
            WS_AMT_BAL = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + + WS_AMT_INFO[21-1].WS_AMT;
            WS_TAX_BASE_AMT = WS_AMT_BAL;
            WS_INQ_TAX_FLG = 'Y';
            R010_COMP_ZZTAX();
            if (pgmRtn) return;
            WS_AMT_INFO[70-1].WS_AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
            WS_AMT_INFO[42-1].WS_AMT = 0;
            WS_AMT_INFO[44-1].WS_AMT = 0;
            LNCIPAMT.P_AMT = WS_AMT_INFO[1-1].WS_AMT;
            LNCIPAMT.I_AMT = WS_AMT_INFO[13-1].WS_AMT;
            LNCIPAMT.O_AMT = WS_AMT_INFO[15-1].WS_AMT;
            LNCIPAMT.L_AMT = WS_AMT_INFO[21-1].WS_AMT;
            LNCIPAMT.F_AMT = WS_AMT_INFO[70-1].WS_AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                    } else {
                        BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                    }
                    if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        }
                    } else {
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                        }
                    }
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                        WS_AMT_INFO[1-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                        WS_AMT_INFO[21-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                        WS_AMT_INFO[13-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                        WS_AMT_INFO[15-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                        WS_BK_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                        WS_BK_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                        WS_BK_O_AMT += LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            WS_AMT_INFO[70-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
                        } else {
                            WS_BK_I_AMT = LNCIPAMT.I_AMT;
                            WS_BK_O_AMT = LNCIPAMT.O_AMT;
                            WS_BK_O_AMT += LNCIPAMT.L_AMT;
                            WS_AMT_INFO[70-1].WS_AMT = LNCIPAMT.F_AMT;
                        }
                    } else {
                        WS_AMT_INFO[1-1].WS_AMT = 0;
                        WS_AMT_INFO[5-1].WS_AMT = 0;
                        WS_AMT_INFO[41-1].WS_AMT = 0;
                        WS_AMT_INFO[43-1].WS_AMT = 0;
                        WS_AMT_INFO[70-1].WS_AMT = 0;
                        WS_AMT_INFO[13-1].WS_AMT = 0;
                        WS_AMT_INFO[15-1].WS_AMT = 0;
                        WS_AMT_INFO[21-1].WS_AMT = 0;
                    }
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        R010_GEN_BATVCH_PROC();
                        if (pgmRtn) return;
                    } else {
                        R020_GEN_ONLVCH_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            WS_AMT_INFO[70-1].WS_AMT = LNRBALZ.LOAN_BALL60;
            LNRBALZ.LOAN_BALL60 -= WS_AMT_INFO[70-1].WS_AMT;
            LNRBALZ.LOAN_BALL66 += WS_AMT_INFO[70-1].WS_AMT;
            WS_AMT_BAL = WS_BK_I_AMT + WS_BK_O_AMT - WS_AMT_INFO[70-1].WS_AMT;
            LNRBALZ.LOAN_BALL65 = WS_AMT_BAL;
            R030_REWRITE_LNTBALZ();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("NP")) {
            R020_READUPD_LNTBALZ();
            if (pgmRtn) return;
            LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL02;
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL24;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL48;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL58;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                    } else {
                        BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                    }
                    if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        }
                    } else {
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                        } else {
                            BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                        }
                    }
                    if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                        WS_AMT_INFO[55-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            WS_AMT_INFO[70-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            WS_AMT_INFO[32-1].WS_AMT = WS_AMT_INFO[70-1].WS_AMT + LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                            WS_AMT_INFO[30-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT - WS_AMT_INFO[32-1].WS_AMT;
                        } else {
                            WS_AMT_INFO[70-1].WS_AMT = LNRBALZ.LOAN_BALL60;
                            WS_AMT_INFO[32-1].WS_AMT = WS_AMT_INFO[70-1].WS_AMT + LNRBALZ.LOAN_BALL65;
                            WS_AMT_INFO[30-1].WS_AMT = LNRBALZ.LOAN_BALL15 - WS_AMT_INFO[32-1].WS_AMT;
                        }
                        if (WS_AMT_INFO[30-1].WS_AMT > 0) {
                            WS_TAX_BASE_AMT = WS_AMT_INFO[30-1].WS_AMT;
                            R010_COMP_ZZTAX();
                            if (pgmRtn) return;
                            WS_AMT_INFO[73-1].WS_AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                            LNRBALZ.LOAN_BALL60 += WS_AMT_INFO[73-1].WS_AMT;
                        }
                        WS_AMT_INFO[17-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT + LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT + LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                        WS_TAX_BASE_AMT = WS_AMT_INFO[17-1].WS_AMT;
                        R010_COMP_ZZTAX();
                        if (pgmRtn) return;
                        WS_AMT_INFO[75-1].WS_AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                    } else {
                        WS_AMT_INFO[55-1].WS_AMT = 0;
                        WS_AMT_INFO[70-1].WS_AMT = 0;
                        WS_AMT_INFO[32-1].WS_AMT = 0;
                        WS_AMT_INFO[30-1].WS_AMT = 0;
                    }
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                        R010_GEN_BATVCH_PROC();
                        if (pgmRtn) return;
                    } else {
                        R020_GEN_ONLVCH_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            WS_AMT_INFO[70-1].WS_AMT = LNRBALZ.LOAN_BALL66;
            LNRBALZ.LOAN_BALL66 -= WS_AMT_INFO[70-1].WS_AMT;
            LNRBALZ.LOAN_BALL60 += WS_AMT_INFO[70-1].WS_AMT;
            LNRBALZ.LOAN_BALL65 = 0;
            WS_AMT_INFO[55-1].WS_AMT = LNRBALZ.LOAN_BALL02;
            WS_AMT_INFO[17-1].WS_AMT = LNRBALZ.LOAN_BALL24 + LNRBALZ.LOAN_BALL48 + LNRBALZ.LOAN_BALL58;
            WS_TAX_BASE_AMT = WS_AMT_INFO[17-1].WS_AMT;
            R010_COMP_ZZTAX();
            if (pgmRtn) return;
            WS_AMT_INFO[75-1].WS_AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
            LNRBALZ.LOAN_BALL24 = 0;
            LNRBALZ.LOAN_BALL48 = 0;
            LNRBALZ.LOAN_BALL58 = 0;
            R030_REWRITE_LNTBALZ();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("WF")) {
            R020_READUPD_LNTBALZ();
            if (pgmRtn) return;
            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
            if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ZDZZ_TAX = LNRBALZ.LOAN_BALL66;
                WS_IO_TAX_INT = LNRBALZ.LOAN_BALL65;
            } else {
                WS_ZDZZ_TAX = LNRBALZ.LOAN_BALL60;
            }
            if (LNCIGVCY.DATA.AMT_INFO[1-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[13-1].AMT != 0 
                || LNRBALZ.LOAN_BALL66 != 0 
                || LNRBALZ.LOAN_BALL65 != 0 
                || LNRBALZ.LOAN_BALL60 != 0) {
                IBS.init(SCCGWA, WS_PART_DATA);
                LNCIPAMT.P_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
                LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[13-1].AMT;
                LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL66;
                LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL65;
                LNCIPAMT.F_AMT = LNRBALZ.LOAN_BALL60;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                    WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                    WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                    WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_F_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
                }
            } else {
                IBS.init(SCCGWA, WS_PART_DATA);
            }
            if (LNCIGVCY.DATA.AMT_INFO[5-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[14-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[15-1].AMT != 0 
                || LNCIGVCY.DATA.AMT_INFO[17-1].AMT != 0) {
                LNCIPAMT.P_AMT = LNCIGVCY.DATA.AMT_INFO[5-1].AMT;
                LNCIPAMT.I_AMT = LNCIGVCY.DATA.AMT_INFO[14-1].AMT;
                LNCIPAMT.O_AMT = LNCIGVCY.DATA.AMT_INFO[15-1].AMT;
                LNCIPAMT.L_AMT = LNCIGVCY.DATA.AMT_INFO[17-1].AMT;
                B321_CALL_LNZIPAMT();
                if (pgmRtn) return;
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                        } else {
                            BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                        }
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            }
                        } else {
                            if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                                BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
                            }
                        }
                        if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y') {
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[55-1].WS_AMT = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_P_AMT + LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                                WS_ZDZZ_TAX = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_O_AMT;
                                WS_IO_TAX_INT = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_L_AMT;
                                WS_AMT_INFO[1-1].WS_AMT = 0;
                                WS_AMT_INFO[5-1].WS_AMT = 0;
                            } else {
                                WS_AMT_INFO[1-1].WS_AMT = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_P_AMT;
                                WS_AMT_INFO[5-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                                WS_AMT_INFO[55-1].WS_AMT = 0;
                                WS_ZDZZ_TAX = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_F_AMT;
                                WS_IO_TAX_INT = 0;
                            }
                            WS_AMT_INFO[13-1].WS_AMT = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_I_AMT;
                            WS_AMT_INFO[14-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                            WS_AMT_INFO[15-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                            WS_AMT_INFO[17-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                                R041_FULLWF_INTPROC();
                                if (pgmRtn) return;
                            } else {
                            }
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[04-1].WS_AMT = WS_AMT_INFO[01-1].WS_AMT;
                                WS_AMT_INFO[08-1].WS_AMT = WS_AMT_INFO[05-1].WS_AMT;
                                WS_AMT_INFO[56-1].WS_AMT = WS_AMT_INFO[55-1].WS_AMT;
                                WS_AMT_INFO[18-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT;
                                WS_AMT_INFO[22-1].WS_AMT = WS_AMT_INFO[15-1].WS_AMT;
                                WS_AMT_INFO[48-1].WS_AMT = WS_AMT_INFO[17-1].WS_AMT;
                                WS_AMT_INFO[47-1].WS_AMT = WS_AMT_INFO[21-1].WS_AMT;
                                WS_AMT_INFO[01-1].WS_AMT = 0;
                                WS_AMT_INFO[05-1].WS_AMT = 0;
                                WS_AMT_INFO[55-1].WS_AMT = 0;
                                WS_AMT_INFO[13-1].WS_AMT = 0;
                                WS_AMT_INFO[15-1].WS_AMT = 0;
                                WS_AMT_INFO[17-1].WS_AMT = 0;
                                WS_AMT_INFO[21-1].WS_AMT = 0;
                            }
                        }
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            R010_GEN_BATVCH_PROC();
                            if (pgmRtn) return;
                        } else {
                            R020_GEN_ONLVCH_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            } else {
                for (WS_I = 1; WS_I <= 10 
                    && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                    if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            BPCBWEVT.INFO.EVENT.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCBWEVT.INFO.EVENT.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCBWEVT.INFO.EVENT.REF_NO = "0" + BPCBWEVT.INFO.EVENT.REF_NO;
                        } else {
                            BPCPOEWA.DATA.REF_NO = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                            JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
                            for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
                        }
                        if (LNCIPAMT.INNER_OUT_FLG == 'I') {
                            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                                BPCBWEVT.INFO.EVENT.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            } else {
                                BPCPOEWA.DATA.BR_OLD = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                            }
                        }
                        if (LNCIPAMT.INNER_OUT_FLG == 'I' 
                            || (LNCIPAMT.INNER_OUT_FLG == 'O' 
                            && LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG == 'Y')) {
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[55-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                                WS_ZDZZ_TAX = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_O_AMT;
                                WS_IO_TAX_INT = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_L_AMT;
                            } else {
                                WS_AMT_INFO[1-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                                WS_ZDZZ_TAX = WS_PART_DATA.WS_PART_DATAS[WS_I-1].WS_PART_F_AMT;
                                WS_IO_TAX_INT = 0;
                            }
                            WS_AMT_INFO[13-1].WS_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                            WS_AMT_INFO[15-1].WS_AMT = 0;
                            WS_AMT_INFO[17-1].WS_AMT = 0;
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                                R041_FULLWF_INTPROC();
                                if (pgmRtn) return;
                            } else {
                                R042_PARTWF_INTPROC();
                                if (pgmRtn) return;
                            }
                            if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                            JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                            if (LNCIGVCY.DATA.STATUS.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_AMT_INFO[04-1].WS_AMT = WS_AMT_INFO[01-1].WS_AMT;
                                WS_AMT_INFO[08-1].WS_AMT = WS_AMT_INFO[05-1].WS_AMT;
                                WS_AMT_INFO[56-1].WS_AMT = WS_AMT_INFO[55-1].WS_AMT;
                                WS_AMT_INFO[18-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT;
                                WS_AMT_INFO[22-1].WS_AMT = WS_AMT_INFO[15-1].WS_AMT;
                                WS_AMT_INFO[48-1].WS_AMT = WS_AMT_INFO[17-1].WS_AMT;
                                WS_AMT_INFO[47-1].WS_AMT = WS_AMT_INFO[21-1].WS_AMT;
                                WS_AMT_INFO[01-1].WS_AMT = 0;
                                WS_AMT_INFO[05-1].WS_AMT = 0;
                                WS_AMT_INFO[55-1].WS_AMT = 0;
                                WS_AMT_INFO[13-1].WS_AMT = 0;
                                WS_AMT_INFO[15-1].WS_AMT = 0;
                                WS_AMT_INFO[17-1].WS_AMT = 0;
                                WS_AMT_INFO[21-1].WS_AMT = 0;
                            }
                        }
                        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                            R010_GEN_BATVCH_PROC();
                            if (pgmRtn) return;
                        } else {
                            R020_GEN_ONLVCH_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            R030_REWRITE_LNTBALZ();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("IA")) {
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("AM")) {
        } else {
        }
    }
    public void R010_GEN_BATVCH_PROC() throws IOException,SQLException,Exception {
        WS_AMT_FLG = 'N';
        for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[WS_IND-1].AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
            if (BPCBWEVT.INFO.EVENT.EVENT_AMT[WS_IND-1].AMT > 0) {
                WS_AMT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, WS_AMT_INFO[WS_IND-1].WS_AMT);
        }
        if (WS_AMT_FLG == 'Y') {
            S000_CALL_BPZBWEVT();
            if (pgmRtn) return;
        }
    }
    public void B020_02_GEN_ONL_VCH() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            && (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RLN") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("TF") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("TFR") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("WF") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("IA") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RPI"))) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            } else {
                B110_READ_BEFTAX_BALL();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = LNCIGVCY.DATA.CNTR_TYPE;
        BPCPOEWA.DATA.PROD_CODE = LNCIGVCY.DATA.PROD_CODE_OLD;
        BPCPOEWA.DATA.AC_NO = LNCIGVCY.DATA.CTA_NO;
        BPCPOEWA.DATA.EVENT_CODE = LNCIGVCY.DATA.EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = LNCIGVCY.DATA.BR_OLD;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = LNCIGVCY.DATA.CCY_INFO[1-1].CCY;
        BPCPOEWA.DATA.VALUE_DATE = LNCIGVCY.DATA.VALUE_DATE;
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CI_NO);
        BPCPOEWA.DATA.CI_NO = LNCIGVCY.DATA.CI_NO;
        BPCPOEWA.DATA.REF_NO = "" + LNCIGVCY.DATA.SUB_CTA_NO;
        JIBS_tmp_int = BPCPOEWA.DATA.REF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCPOEWA.DATA.REF_NO = "0" + BPCPOEWA.DATA.REF_NO;
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        BPCPOEWA.DATA.PORT = LNCIGVCY.DATA.PORT;
        BPCPOEWA.DATA.AC_NO_REL = LNCIGVCY.DATA.CTA_NO;
        BPCPOEWA.DATA.EFF_DAYS = 0;
        BPCPOEWA.DATA.THEIR_AC = LNCIGVCY.DATA.DEP_AC;
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCIGVCY.DATA.STATUS.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            B021_SYNLN_AMT_PROC();
            if (pgmRtn) return;
        } else {
            R000_TRANS_AMT_TO_AC_MODEL();
            if (pgmRtn) return;
            R020_GEN_ONLVCH_PROC();
            if (pgmRtn) return;
        }
        R080_GEN_OPYINT_TAXVCH();
        if (pgmRtn) return;
        if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            && (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RLN") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("TF") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("TFR") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("WF") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("IA") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RPI"))) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B120_RESTORE_TAXDAT();
                if (pgmRtn) return;
            } else {
                B130_SAVE_BEFTAX_BALL();
                if (pgmRtn) return;
            }
        }
    }
    public void R020_GEN_ONLVCH_PROC() throws IOException,SQLException,Exception {
        WS_AMT_FLG = 'N';
        for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
            if (BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT > 0) {
                WS_AMT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT);
        }
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("GC") 
            || LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("BC")) {
            IBS.init(SCCGWA, BPCUGMC);
            BPCUGMC.INFO.CNTR_TYPE = LNCIGVCY.DATA.CNTR_TYPE;
            BPCUGMC.INFO.CCY_INFO[1-1].CCY = LNCIGVCY.DATA.CCY_INFO[1-1].CCY;
            BPCUGMC.INFO.CI_NO = LNCIGVCY.DATA.CI_NO;
            BPCUGMC.INFO.AC_NO = LNCIGVCY.DATA.CTA_NO;
            IBS.init(SCCGWA, BPCACLDD);
            BPCACLDD.PROD_CD = LNCIGVCY.DATA.PROD_CODE_OLD;
            BPCUGMC.INFO.OTH_PTR_LEN = 31;
            BPCUGMC.INFO.OTH_PTR_OLD = BPCACLDD;
            IBS.init(SCCGWA, BPCACLDD);
            BPCACLDD.PROD_CD = LNCIGVCY.DATA.PROD_CODE_OLD;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCACLDD;
            for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
                BPCUGMC.INFO.AMTS[WS_IND-1].AMT = BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT;
                CEP.TRC(SCCGWA, WS_AMT_INFO[WS_IND-1].WS_AMT);
            }
            S000_CALL_BPZUGMC();
            if (pgmRtn) return;
        } else {
            if (WS_AMT_FLG == 'Y') {
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
    }
    public void R080_GEN_OPYINT_TAXVCH() throws IOException,SQLException,Exception {
        WS_TOT_PAY_INT = 0;
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("0")) {
            if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("ST")) {
                if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                if (LNCIGVCY.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCIGVCY.DATA.STATUS.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TOT_PAY_INT = WS_AMT_INFO[19-1].WS_AMT;
                } else {
                    WS_TOT_PAY_INT = WS_SYNL_AMT19;
                }
            }
            if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
                if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
                if (LNCIGVCY.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || LNCIGVCY.DATA.STATUS.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TOT_PAY_INT = WS_SYNL_AMT13 + WS_SYNL_AMT15 + WS_SYNL_AMT17 + WS_SYNL_AMT35;
                } else {
                    WS_TOT_PAY_INT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[17-1].WS_AMT + WS_AMT_INFO[21-1].WS_AMT + WS_AMT_INFO[34-1].WS_AMT + WS_AMT_INFO[33-1].WS_AMT + WS_AMT_INFO[35-1].WS_AMT + WS_AMT_INFO[72-1].WS_AMT;
                }
            }
        }
        R081_WRITE_INTTAX_VCH();
        if (pgmRtn) return;
    }
    public void R081_WRITE_INTTAX_VCH() throws IOException,SQLException,Exception {
        if (WS_TOT_PAY_INT > 0) {
            IBS.init(SCCGWA, VTCPQTAX);
            VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            VTCPQTAX.INPUT_DATA.PROD_CD = LNCIGVCY.DATA.PROD_CODE_OLD;
            VTCPQTAX.INPUT_DATA.CI_NO = LNCIGVCY.DATA.CI_NO;
            VTCPQTAX.INPUT_DATA.CCY = LNCIGVCY.DATA.CCY_INFO[1-1].CCY;
            VTCPQTAX.INPUT_DATA.BR = LNCIGVCY.DATA.BR_OLD;
            VTCPQTAX.INPUT_DATA.CNTR_TYPE = LNCIGVCY.DATA.CNTR_TYPE;
            VTCPQTAX.INPUT_DATA.AC = LNCIGVCY.DATA.CTA_NO;
            VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'W';
            VTCPQTAX.INPUT_DATA.SH_AMT = WS_TOT_PAY_INT;
            S000_CALL_VTZPQTAX();
            if (pgmRtn) return;
        }
    }
    public void B110_READ_BEFTAX_BALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT60 = LNRBALZ.LOAN_BALL60;
        WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT62 = LNRBALZ.LOAN_BALL62;
        WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT65 = LNRBALZ.LOAN_BALL65;
        WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT66 = LNRBALZ.LOAN_BALL66;
    }
    public void B120_RESTORE_TAXDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTRAN);
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'X';
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNCRTRAN.FUNC = 'I';
        S000_CALL_LNZRTRAN1();
        if (pgmRtn) return;
        if (LNCRTRAN.RETURN_INFO == 'F' 
            || LNCRTRAN.RETURN_INFO == 'N') {
        } else {
            if (LNCRTRAN.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTRAN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCRTRAN.RETURN_INFO == 'F') {
            B121_REVS_TAX_BALL();
            if (pgmRtn) return;
            B122_UPDT_TRN_STUS();
            if (pgmRtn) return;
            B123_SYLN_REVS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B121_REVS_TAX_BALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, LNRTRAN.TR_DATA, WS_TAX_DATAS);
        CEP.TRC(SCCGWA, "REVS-TAXBAL...");
        CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT60);
        CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT62);
        CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT60);
        CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT62);
        CEP.TRC(SCCGWA, WS_TAX_DATAS);
        LNRBALZ.LOAN_BALL60 = WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT60;
        LNRBALZ.LOAN_BALL62 = WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT62;
        LNRBALZ.LOAN_BALL65 = WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT65;
        LNRBALZ.LOAN_BALL66 = WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT66;
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B122_UPDT_TRN_STUS() throws IOException,SQLException,Exception {
        LNCRTRAN.FUNC = 'R';
        S000_CALL_LNZRTRAN1();
        if (pgmRtn) return;
        LNRTRAN.TRAN_STS = 'R';
        LNRTRAN.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRTRAN.FUNC = 'U';
        S000_CALL_LNZRTRAN1();
        if (pgmRtn) return;
    }
    public void B123_SYLN_REVS_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS == null) LNCIGVCY.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCY.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCY.DATA.STATUS += " ";
        if (LNCIGVCY.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCIGVCY.DATA.STATUS.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT60);
            CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT62);
            CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT60);
            CEP.TRC(SCCGWA, WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT62);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL60);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL62);
            IBS.init(SCCGWA, LNCIPAMT);
            LNCIPAMT.CTA_NO = LNCIGVCY.DATA.CTA_NO;
            LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL60;
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL62;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL65;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL66;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10 
                && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
                if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                    IBS.init(SCCGWA, LNRBALZ);
                    LNRBALZ.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
                    LNRBALZ.KEY.SUB_CTA_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                    LNTBALZ_RD = new DBParm();
                    LNTBALZ_RD.TableName = "LNTBALZ";
                    LNTBALZ_RD.upd = true;
                    IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    LNRBALZ.LOAN_BALL60 = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                    LNRBALZ.LOAN_BALL62 = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                    LNRBALZ.LOAN_BALL65 = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                    LNRBALZ.LOAN_BALL66 = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                    LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_I-1].PART_NO);
                    CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL60);
                    CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL62);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        LNTBALZ_RD = new DBParm();
                        LNTBALZ_RD.TableName = "LNTBALZ";
                        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
                        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
    }
    public void B130_SAVE_BEFTAX_BALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT60 = LNRBALZ.LOAN_BALL60;
        WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT62 = LNRBALZ.LOAN_BALL62;
        WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT65 = LNRBALZ.LOAN_BALL65;
        WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT66 = LNRBALZ.LOAN_BALL66;
        WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT60 = WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT60 - WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT60;
        WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT62 = WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT62 - WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT62;
        WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT65 = WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT65 - WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT65;
        WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT66 = WS_TAX_DATAS.WS_AFTTAX_DATA.WS_AFTT_AMT66 - WS_TAX_DATAS.WS_BEFTAX_DATA.WS_BEFT_AMT66;
        if (WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT60 != 0 
            || WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT62 != 0 
            || WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT65 != 0 
            || WS_TAX_DATAS.WS_TRNTAX_DATA.WS_TRNT_AMT66 != 0) {
            IBS.init(SCCGWA, LNCRTRAN);
            IBS.init(SCCGWA, LNRTRAN);
            LNRTRAN.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
            LNRTRAN.KEY.SUB_CTA_NO = 0;
            LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            LNRTRAN.KEY.TXN_TYP = 'X';
            LNRTRAN.KEY.TRAN_FLG = 'C';
            LNRTRAN.TRAN_STS = 'N';
            LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            LNRTRAN.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_TAX_DATAS);
            LNCRTRAN.FUNC = 'A';
            S000_CALL_LNZRTRAN1();
            if (pgmRtn) return;
            if (LNCRTRAN.RETURN_INFO == 'F' 
                || LNCRTRAN.RETURN_INFO == 'D') {
            } else {
                if (LNCRTRAN.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTRAN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B030_CLCM_REVERSAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM") 
            && LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RP") 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNRCMMT);
            IBS.init(SCCGWA, LNCRCMMT);
            LNRCMMT.KEY.CONTRACT_NO = LNCIGVCY.DATA.CTA_NO;
            LNCRCMMT.FUNC = 'R';
            S000_CALL_LNZRCMMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CMMT_FLG);
            if (WS_CMMT_FLG == 'Y') {
                if (LNRCMMT.AVAIL_END_DATE > SCCGWA.COMM_AREA.AC_DATE 
                    || (LNRCMMT.AVAIL_END_DATE == SCCGWA.COMM_AREA.AC_DATE)) {
                    WS_CMMT_AVAIL_FLG = 'N';
                } else {
                    WS_CMMT_AVAIL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, WS_CMMT_AVAIL_FLG);
                CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
                CEP.TRC(SCCGWA, "AFT VWA-CNT");
                WS_VCH_FLG = 'N';
                for (WS_CNT = 1; WS_CNT <= BPRVWA.BASIC_AREA.CNT; WS_CNT += 1) {
                    if (BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AC_NO.equalsIgnoreCase(LNCIGVCY.DATA.CTA_NO) 
                        && BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.EVENT_CODE.equalsIgnoreCase("RP")) {
                        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AMT);
                        WS_TMP_BLOCK_AMT = BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AMT;
                        WS_VCH_FLG = 'Y';
                    }
                }
                CEP.TRC(SCCGWA, WS_VCH_FLG);
                CEP.TRC(SCCGWA, WS_CMMT_AVAIL_FLG);
                CEP.TRC(SCCGWA, LNRCMMT.REVOLVING_FLAG);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[1-1].AMT);
                CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
                WS_AMT_UPD_FLG = 'N';
                if ((WS_VCH_FLG == 'Y' 
                    && WS_CMMT_AVAIL_FLG == 'Y') 
                    || (WS_VCH_FLG == 'N' 
                    && WS_CMMT_AVAIL_FLG == 'N')) {
                    if (LNCIGVCY.DATA.AMT_INFO[1-1].AMT != 0 
                        && (LNRCMMT.REVOLVING_FLAG != 'N' 
                        || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 4019)) {
                        if (WS_VCH_FLG == 'Y') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                        if (WS_VCH_FLG == 'N') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                    }
                    if (LNCIGVCY.DATA.AMT_INFO[2-1].AMT != 0) {
                        CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
                        CEP.TRC(SCCGWA, WS_TMP_BLOCK_AMT);
                        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 2229) {
                            if (WS_TMP_BLOCK_AMT > 0) {
                                LNCIGVCY.DATA.AMT_INFO[2-1].AMT = WS_TMP_BLOCK_AMT;
                            }
                        }
                        CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
                        if (WS_VCH_FLG == 'Y') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                        if (WS_VCH_FLG == 'N') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                    }
                    CEP.TRC(SCCGWA, WS_AMT_UPD_FLG);
                    if (WS_AMT_UPD_FLG == 'Y') {
                        LNCRCMMT.FUNC = 'U';
                        S000_CALL_LNZRCMMT();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_TRANS_AMT_TO_AC_MODEL() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM")) {
            R000_TRANS_CLCM_AMT();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD")) {
            R000_TRANS_CLDD_AMT_HUS();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLGU")) {
            R000_TRANS_CLGU_AMT();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDL")) {
            R000_TRANS_CLDD_AMT();
            if (pgmRtn) return;
        } else if (LNCIGVCY.DATA.CNTR_TYPE.equalsIgnoreCase("CLDP")) {
            R000_TRANS_CLDP_AMT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONTRACT_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_CLCM_AMT() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RP")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[2-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[2-1].AMT;
        } else {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[2-1].AMT);
    }
    public void R000_TRANS_CLDP_AMT() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("ST")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[19-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[19-1].AMT;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("MA")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[57-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[57-1].AMT;
            WS_AMT_INFO[58-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[58-1].AMT;
            WS_AMT_INFO[59-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[59-1].AMT;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("AM")) {
            WS_AMT_INFO[53-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[53-1].AMT - LNCIGVCY.DATA.AMT_INFO[54-1].AMT;
            if (WS_AMT_INFO[53-1].WS_AMT < 0) {
                WS_AMT_INFO[53-1].WS_AMT = WS_AMT_INFO[53-1].WS_AMT * -1;
                WS_AMT_INFO[54-1].WS_AMT = WS_AMT_INFO[53-1].WS_AMT;
                WS_AMT_INFO[53-1].WS_AMT = 0;
                CEP.TRC(SCCGWA, WS_AMT_INFO[53-1].WS_AMT);
                CEP.TRC(SCCGWA, WS_AMT_INFO[54-1].WS_AMT);
            }
        } else {
        }
    }
    public void R000_TRANS_CLDD_AMT() throws IOException,SQLException,Exception {
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("ON") 
            && LNCIGVCY.DATA.AMT_INFO[5-1].AMT < 0) {
            LNCIGVCY.DATA.EVENT_CODE = "NO";
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[5-1].AMT * -1;
            LNCIGVCY.DATA.AMT_INFO[5-1].AMT = 0;
        }
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("ST")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[5-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[5-1].AMT;
            WS_AMT_INFO[2-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[2-1].AMT;
            WS_AMT_INFO[19-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[19-1].AMT;
        } else if (LNCIGVCY.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
            if (LNCIGVCY.DATA.AMT_INFO[4-1].AMT > 0) {
                WS_AMT_INFO[2-1].WS_AMT = LNCIGVCY.DATA.BAL_NORDUE;
                WS_AMT_INFO[4-1].WS_AMT = LNCIGVCY.DATA.BAL_NORDUE + LNCIGVCY.DATA.AMT_INFO[4-1].AMT;
            } else {
                WS_AMT_INFO[1-1].WS_AMT = LNCIGVCY.DATA.BAL_NORDUE;
                WS_AMT_INFO[3-1].WS_AMT = LNCIGVCY.DATA.BAL_NORDUE + LNCIGVCY.DATA.AMT_INFO[3-1].AMT;
                WS_AMT_INFO[5-1].WS_AMT = LNCIGVCY.DATA.BAL_OVERDUE_MANUAL + LNCIGVCY.DATA.BAL_OVERDUE;
                WS_AMT_INFO[7-1].WS_AMT = LNCIGVCY.DATA.BAL_OVERDUE_MANUAL + LNCIGVCY.DATA.BAL_OVERDUE + LNCIGVCY.DATA.AMT_INFO[7-1].AMT;
                WS_AMT_INFO[20-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[20-1].AMT;
            }
            WS_AMT_INFO[13-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[13-1].AMT;
            WS_AMT_INFO[15-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[15-1].AMT;
            WS_AMT_INFO[17-1].WS_AMT = LNCIGVCY.DATA.AMT_INFO[17-1].AMT;
