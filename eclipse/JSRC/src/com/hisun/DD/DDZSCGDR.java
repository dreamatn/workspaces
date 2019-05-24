package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.BP.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCGDR {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    DBParm DDTCLDD_RD;
    brParm DDTCCY_BR = new brParm();
    String K_OTHER_PAYABLES_ITM = "24508";
    String K_NONBUSINESS_INCOM_ITM = "51604";
    String K_OTHER_PAY_CODE = "DDEG3";
    String K_NOBUSINE_INCOM_CODE = "DDEG1";
    String K_ITM_NO = " ";
    String WS_ERR_MSG = " ";
    double WS_DEP_INT = 0;
    double WS_INT_TAX = 0;
    double WS_DREG_BAL = 0;
    char WS_TRAN_ITEM_FLG = ' ';
    char WS_READ_STS_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIDREG DDCIDREG = new DDCIDREG();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    CICQACAC CICQACAC = new CICQACAC();
    TDCUCACK TDCUCACK = new TDCUCACK();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    GDCUSTPL GDCUSTPL = new GDCUSTPL();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRCLDD DDRCLDD = new DDRCLDD();
    SCCGWA SCCGWA;
    DDCSCGDR DDCSCGDR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSCGDR DDCSCGDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCGDR = DDCSCGDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCGDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B011_POST_AC_CHK();
        B110_POST_INT_PROC();
        B120_GET_CURR_BAL_PROC();
        B125_TD_CONTRACT();
        B135_CHK_GD_RLTSHIP();
        B141_GEN_VCH_PROC();
        B150_UPD_DDTMST_PROC();
        B160_UPD_DDTDREG_PROC();
        if (WS_DREG_BAL > 0) {
            B180_FIN_HISTORY_PROC();
        }
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCGDR.AC);
        CEP.TRC(SCCGWA, DDCSCGDR.CCY);
        CEP.TRC(SCCGWA, DDCSCGDR.CCY_TYPE);
        if (DDCSCGDR.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCGDR.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_POST_AC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.POST_AC = DDCSCGDR.AC;
        T000_STARTBR_DDTCCY();
        T000_READNEXT_DDTCCY();
        while (WS_READ_STS_FLAG != 'Y') {
            T000_READNEXT_DDTCCY();
        }
        T000_ENDBR_DDTCCY();
    }
    public void B110_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSCGDR.AC;
        DDCUPINT.CCY = DDCSCGDR.CCY;
        DDCUPINT.CCY_TYPE = DDCSCGDR.CCY_TYPE;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.UOD_INT);
        WS_DEP_INT = DDCUPINT.DEP_INT;
        WS_INT_TAX = DDCUPINT.INT_TAX;
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSCGDR.AC;
        DDCUPINT.CCY = DDCSCGDR.CCY;
        DDCUPINT.CCY_TYPE = DDCSCGDR.CCY_TYPE;
        DDCUPINT.TX_MMO = "P401";
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
    }
    public void B120_GET_CURR_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSCGDR.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSCGDR.CCY;
        CICQACAC.DATA.CR_FLG = DDCSCGDR.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY_PROC();
        WS_DREG_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_DREG_BAL);
        if (WS_DREG_BAL < 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT1_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FORBID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B125_TD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUCACK);
        TDCUCACK.AC_NO = DDCSCGDR.AC;
        S000_CALL_TDZUCACK();
        CEP.TRC(SCCGWA, TDCUCACK.PI_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.GK_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.ZC_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FB_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.PI_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FX_ACCT);
        }
        if (TDCUCACK.GK_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_GK_ACCT);
        }
        if (TDCUCACK.ZC_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_ZC_ACCT);
        }
        if (TDCUCACK.FB_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FB_ACCT);
        }
        if (TDCUCACK.FOUND_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_TD_CONT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B130_UNPAY_FEE_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSCGDR.AC;
        S000_CALL_BPZPFPDT();
        if (BPCPFPDT.OUTPUT.PCHG_FLG == 'Y' 
            || BPCPFPDT.OUTPUT.UNCHG_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ARREARGE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B135_CHK_GD_RLTSHIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUSTPL);
        GDCUSTPL.INPUT.AC = DDCSCGDR.AC;
        S000_CALL_GDZUSTPL();
        CEP.TRC(SCCGWA, GDCUSTPL.OUTPUT.RLT_STS);
        if (GDCUSTPL.OUTPUT.RLT_STS == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_GDAC_HAS_RLT);
        }
    }
    public void B130_SET_ITEM_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DREG_BAL);
        CEP.TRC(SCCGWA, DDCSCGDR.LIMIT_BAL);
        if (DDCSCGDR.CCY.equalsIgnoreCase("156") 
            && WS_DREG_BAL <= DDCSCGDR.LIMIT_BAL) {
            WS_TRAN_ITEM_FLG = 'N';
        } else {
            WS_TRAN_ITEM_FLG = 'O';
        }
    }
    public void B140_DR_DD_TO_AI_PROC() throws IOException,SQLException,Exception {
        B140_01_GET_AI_AC_PROC();
        if (WS_DREG_BAL != 0) {
            B140_03_WITHDRAW_PROC();
            B140_05_CR_AI_PROC();
        }
    }
    public void B140_01_GET_AI_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "3";
        AICPQIA.CD.BUSI_KND = K_OTHER_PAY_CODE;
        AICPQIA.BR = DDCSCGDR.BR;
        AICPQIA.CCY = DDCSCGDR.CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        CEP.TRC(SCCGWA, AICPQIA.AC);
    }
    public void B140_03_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DDCSCGDR.AC;
        DDCUDRAC.CCY = DDCSCGDR.CCY;
        DDCUDRAC.CCY_TYPE = DDCSCGDR.CCY_TYPE;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.TX_AMT = WS_DREG_BAL;
        DDCUDRAC.OTHER_AC = AICPQIA.AC;
        DDCUDRAC.TX_MMO = "P401";
        S000_CALL_DDZUDRAC();
    }
    public void B140_05_CR_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.CCY = DDCSCGDR.CCY;
        AICUUPIA.DATA.AMT = WS_DREG_BAL;
        AICUUPIA.DATA.BR_OLD = DDCSCGDR.BR;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B141_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        if (WS_DREG_BAL > 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
            BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
            BPCPOEWA.DATA.EVENT_CODE = "DH";
            BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCSCGDR.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_DREG_BAL;
            BPCPOEWA.DATA.AMT_INFO[20-1].AMT = WS_DREG_BAL;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            BPCPOEWA.DATA.REF_NO = " ";
            S000_CALL_BPZPOEWA();
        }
    }
    public void B150_UPD_DDTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCGDR.AC;
        T000_READUP_DDTMST();
        if (DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'O') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REFUSE_FAIL_AC);
        }
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        DDRMST.AC_STS = 'D';
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 03 - 1) + "1" + DDRMST.AC_STS_WORD.substring(03 + 1 - 1);
        T000_REWRITE_DDTMST();
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUP_DDTCCY_PROC();
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 03 - 1) + "1" + DDRCCY.STS_WORD.substring(03 + 1 - 1);
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                DDRCCY.LAST_BAL -= WS_DREG_BAL;
            }
        }
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
            DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
            DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
            DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
            DDRCCY.LASDAY_TOT_DR_AMT = DDRCCY.DAY_TOT_DR_AMT;
            DDRCCY.LASDAY_TOT_CR_AMT = DDRCCY.DAY_TOT_CR_AMT;
            DDRCCY.DRW_CAMT = 0;
            DDRCCY.DEP_CAMT = 0;
            DDRCCY.DRW_TAMT = 0;
            DDRCCY.DEP_TAMT = 0;
            DDRCCY.DAY_TOT_DR_AMT = 0;
            DDRCCY.DAY_TOT_CR_AMT = 0;
        }
        DDRCCY.CURR_BAL = 0;
        DDRCCY.DRW_TAMT += WS_DREG_BAL;
        DDRCCY.DAY_TOT_DR_AMT += WS_DREG_BAL;
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
    }
    public void B160_UPD_DDTDREG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.AC == null) AICPQIA.AC = "";
        JIBS_tmp_int = AICPQIA.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
        if (K_ITM_NO == null) K_ITM_NO = "";
        JIBS_tmp_int = K_ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) K_ITM_NO += " ";
        K_ITM_NO = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1) + K_ITM_NO.substring(8);
        CEP.TRC(SCCGWA, K_ITM_NO);
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSCGDR.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSCGDR.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSCGDR.CCY_TYPE;
        DDCIDREG.DATA.STS = '2';
        DDCIDREG.DATA.FLG = 'B';
        DDCIDREG.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDCIDREG.DATA.BAL = WS_DREG_BAL;
        DDCIDREG.DATA.INT = WS_DEP_INT;
        DDCIDREG.DATA.INT_BAL = WS_INT_TAX;
        if (DDCIDREG.DATA.STS == '2') {
            DDCIDREG.DATA.SEQ = 1;
        } else {
            DDCIDREG.DATA.SEQ = 2;
        }
        DDCIDREG.DATA.ITM_NO = K_ITM_NO;
        DDCIDREG.DATA.D_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.D_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.D_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCIDREG.OPT = 'U';
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY_TYPE);
        S000_CALL_DDZIDREG();
    }
    public void B170_WRITE_DDTCLDD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCLDD);
        DDRCLDD.KEY.AC = DDCSCGDR.AC;
        DDRCLDD.KEY.CCY = DDCSCGDR.CCY;
        DDRCLDD.KEY.CCY_TYPE = DDCSCGDR.CCY_TYPE;
        DDRCLDD.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRCLDD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.STS = DDCIDREG.DATA.STS;
        DDRCLDD.BAL = WS_DREG_BAL;
        DDRCLDD.FLG = 'B';
        DDRCLDD.BR = DDCSCGDR.BR;
        DDRCLDD.DRCR_FLG = 'C';
        DDRCLDD.ITM_NO = DDCIDREG.DATA.ITM_NO;
        DDRCLDD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTCLDD();
    }
    public void B180_FIN_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.AC = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        BPCPFHIS.DATA.TX_CCY = DDRCCY.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDRCCY.CCY_TYPE;
        BPCPFHIS.DATA.TX_AMT = WS_DREG_BAL;
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        IBS.init(SCCGWA, DDRFHIS);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFHIS.AC_NO = DDRCCY.CUS_AC;
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.TX_AMT = WS_DREG_BAL;
        DDRFHIS.DOMBR = DDRCCY.OWNER_BRDP;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDRCCY.CCY;
        DDRFHIS.CRDR_FLG = "DR".charAt(0);
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = 0;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPFHIS);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZUCACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CLOSE-AC-CHK", TDCUCACK);
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT, true);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZUSTPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRV-GDZUSTPL", GDCUSTPL);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void T000_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READUP_DDTCCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_WRITE_DDTCLDD() throws IOException,SQLException,Exception {
        DDTCLDD_RD = new DBParm();
        DDTCLDD_RD.TableName = "DDTCLDD";
        DDTCLDD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCLDD, DDTCLDD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLDD_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "POST_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_STS_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRCCY.POST_AC);
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CINT_FLG);
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && (!DDRCCY.POST_AC.equalsIgnoreCase(DDRCCY.CUS_AC)) 
            && DDRCCY.CINT_FLG == 'Y' 
            && DDRCCY.STS != 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.POST_AC_CAN_C);
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
