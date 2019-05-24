package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDCLS {
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTCLDD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD640";
    String K_HIS_REMARKS = "DORMANT ACCOUNT CLOSE";
    String K_HIS_MMO = "C004";
    String K_CHK_STS_TBL = "0005";
    String K_APP_MMO = "DD";
    String K_ITM_NO = " ";
    String WS_ERR_MSG = " ";
    char WS_MST_AC_STS = ' ';
    double WS_DREG_BAL = 0;
    char WS_DREG_STS = ' ';
    char WS_DREG_RCD_STS = ' ';
    String WS_AC_AC = " ";
    char WS_READ_STS_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDCRMST DDCRMST = new DDCRMST();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDCODCLS DDCODCLS = new DDCODCLS();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    SCCBINF SCCBINF = new SCCBINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    AICPQIA AICPQIA = new AICPQIA();
    CICACCU CICACCU = new CICACCU();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DDRCLDD DDRCLDD = new DDRCLDD();
    CICMACR CICMACR = new CICMACR();
    DDRVCH DDRVCH = new DDRVCH();
    DDCSIACC DDCSIACC = new DDCSIACC();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACR CICSACR = new CICSACR();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPROCAC BPROCAC = new BPROCAC();
    CICSACAC CICSACAC = new CICSACAC();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICPUITM AICPUITM = new AICPUITM();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    DDCUFEES DDCUFEES = new DDCUFEES();
    SCCGWA SCCGWA;
    DDCSDCLS DDCSDCLS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSDCLS DDCSDCLS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDCLS = DDCSDCLS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDCLS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B011_POST_AC_CHK();
        if (pgmRtn) return;
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B025_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B025_CONTRACT_CHECK_PROC();
        if (pgmRtn) return;
        B026_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        B030_CLS_DORM_PROC();
        if (pgmRtn) return;
        B072_UPD_CI_INFO();
        if (pgmRtn) return;
        if (WS_DREG_BAL != 0) {
            B041_GEN_VCH_PROC();
            if (pgmRtn) return;
            B050_AMT_TRF_PROC();
            if (pgmRtn) return;
        }
        B032_DSD_UNUSE_CHQB_PROC();
        if (pgmRtn) return;
        B070_UPD_AC_STS_PROC();
        if (pgmRtn) return;
        B075_CRT_BP_OCAC_PROC();
        if (pgmRtn) return;
        B076_WAVE_ANNUAL_FEES();
        if (pgmRtn) return;
        B080_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B090_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.AC);
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.CCY);
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.DRAW_MTH);
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.PAY_TYP);
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.TO_AC);
        if (DDCSDCLS.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDCLS.DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSDCLS.DATA.CCY.equalsIgnoreCase("156") 
            && DDCSDCLS.DATA.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSDCLS.DATA.CCY.equalsIgnoreCase("156") 
            && (DDCSDCLS.DATA.CCY_TYPE != '1' 
            && DDCSDCLS.DATA.CCY_TYPE != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDCLS.DATA.PAY_TYP != '1' 
            && DDCSDCLS.DATA.PAY_TYP != '2' 
            && DDCSDCLS.DATA.PAY_TYP != '3' 
            && DDCSDCLS.DATA.PAY_TYP != '4') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDCLS.DATA.PAY_TYP == '1' 
            && DDCSDCLS.DATA.TO_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TOAC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDCLS.DATA.PAY_TYP == '1' 
            && DDCSDCLS.DATA.TO_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DDCSDCLS.DATA.TO_AC;
            S000_CALL_CISOACCU();
            if (pgmRtn) return;
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STS_CLOSE_E);
            }
        }
    }
    public void B011_POST_AC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.POST_AC = DDCSDCLS.DATA.AC;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_READ_STS_FLAG != 'Y') {
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B025_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSDCLS.DATA.AC;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B025_CONTRACT_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSDCLS.DATA.AC;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        if (BPCPFPDT.OUTPUT.PCHG_FLG == 'Y' 
            || BPCPFPDT.OUTPUT.UNCHG_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ARREARGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B026_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSDCLS.DATA.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSDCLS.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCSDCLS.DATA.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_AC_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = DDCSDCLS.DATA.AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDCRMST.FUNC = 'R';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
        WS_MST_AC_STS = DDRMST.AC_STS;
        if (DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH);
        }
        if (WS_MST_AC_STS != 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_DORMANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.CASH_FLG == '2' 
            && DDCSDCLS.DATA.PAY_TYP == '4') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_DRW);
        }
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSDCLS.DATA.AC;
        DDRVCH.VCH_TYPE = '2';
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.col = "CUS_AC,PAY_TYPE";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void B030_CLS_DORM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDCLS.DATA.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDCLS.DATA.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        DDCIDREG.OPT = 'R';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        DDCIDREG.DATA.KEY.AC = DDCSDCLS.DATA.AC;
        DDCIDREG.DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_DREG_STS = DDCIDREG.DATA.STS;
        WS_DREG_BAL = DDCIDREG.DATA.BAL;
        WS_DREG_RCD_STS = DDCIDREG.DATA.RCD_STS;
        DDCIDREG.DATA.N_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.N_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.N_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (WS_DREG_STS != '2' 
            && WS_DREG_STS != '3' 
            && WS_DREG_STS != '4') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_STS_ACT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_DREG_RCD_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DREG_STS_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDCIDREG.DATA.OTH_AC = DDCSDCLS.DATA.OTH_AC;
        DDCIDREG.DATA.OTH_ACNM = DDCSDCLS.DATA.OTH_ACNM;
        DDCIDREG.DATA.OTH_BR = DDCSDCLS.DATA.OTH_BR;
        DDCIDREG.DATA.OTH_BRNM = DDCSDCLS.DATA.OTH_BRNM;
        DDCIDREG.DATA.STS = '8';
        DDCIDREG.DATA.ITM_NO = K_ITM_NO;
        DDCIDREG.OPT = 'S';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
    }
    public void B032_DSD_UNUSE_CHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIACC);
        DDCSIACC.AC = DDCSDCLS.DATA.AC;
        DDCSIACC.FUNC = 'D';
        S000_CALL_DDZSIACC();
        if (pgmRtn) return;
    }
    public void B035_WRITE_DDTCLDD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCLDD);
        DDRCLDD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRCLDD.KEY.AC = DDCSDCLS.DATA.AC;
        DDRCLDD.KEY.CCY = DDCSDCLS.DATA.CCY;
        DDRCLDD.KEY.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        DDRCLDD.STS = DDCIDREG.DATA.STS;
        DDRCLDD.BAL = DDCIDREG.DATA.BAL;
        DDRCLDD.FLG = 'O';
        DDRCLDD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCLDD.DRCR_FLG = 'D';
        DDRCLDD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCLDD.ITM_NO = K_ITM_NO;
        T000_WRITE_DDTCLDD();
        if (pgmRtn) return;
    }
    public void B041_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        if (WS_DREG_STS == '2') {
            BPCPOEWA.DATA.EVENT_CODE = "HXH";
        }
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BR;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDRCCY.CCY;
        BPCPOEWA.DATA.AMT_INFO[20-1].AMT = WS_DREG_BAL;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B050_AMT_TRF_PROC() throws IOException,SQLException,Exception {
        if (DDCSDCLS.DATA.PAY_TYP == '1') {
            B050_10_CI_TRF_PROC();
            if (pgmRtn) return;
        } else if (DDCSDCLS.DATA.PAY_TYP == '3') {
            B050_50_INTER_PROC();
            if (pgmRtn) return;
        } else if (DDCSDCLS.DATA.PAY_TYP == '4') {
            B050_10_CASH_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSDCLS.DATA.PAY_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B050_10_CASH_PROC() throws IOException,SQLException,Exception {
        if (WS_DREG_BAL != 0) {
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = DDCSDCLS.DATA.CCY;
            BPCUSBOX.CCY_TYP = DDCSDCLS.DATA.CCY_TYPE;
            BPCUSBOX.AMT = WS_DREG_BAL;
            CEP.TRC(SCCGWA, BPCUSBOX.CCY);
            CEP.TRC(SCCGWA, BPCUSBOX.AMT);
            BPCUSBOX.OPP_AC = DDCSDCLS.DATA.AC;
            S000_CALL_BPZUSBOX();
            if (pgmRtn) return;
        }
    }
    public void B050_10_CI_TRF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = DDCSDCLS.DATA.TO_AC;
        DDCUCRAC.CCY = DDCSDCLS.DATA.CCY;
        DDCUCRAC.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        DDCUCRAC.TX_AMT = WS_DREG_BAL;
        DDCUCRAC.TX_MMO = K_HIS_MMO;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B050_30_BR_OUTER_PROC() throws IOException,SQLException,Exception {
        S000_QRY_AIPQIA();
        if (pgmRtn) return;
        S000_DR_AIZUUPIA();
        if (pgmRtn) return;
        S000_QRY2_AIPQIA();
        if (pgmRtn) return;
        S000_CR_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B050_50_INTER_PROC() throws IOException,SQLException,Exception {
        S000_CR_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void S000_QRY_AIPQIA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "3";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = "BK001";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        if (WS_DREG_STS != '4') {
            AIRPAI7.KEY.REDEFINES6.BUSI_KND = "DDEG3";
            AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        } else {
            AIRPAI7.KEY.REDEFINES6.BUSI_KND = "DDEG1";
            AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        }
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C);
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C != 0) {
            IBS.init(SCCGWA, AICPQIA);
            if (WS_DREG_STS != '4') {
                AICPQIA.CD.BUSI_KND = "DDEG3";
            } else {
                AICPQIA.CD.BUSI_KND = "DDEG1";
            }
            CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.BR = DDRCCY.OWNER_BRDP;
            AICPQIA.CCY = DDCIDREG.DATA.KEY.CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            if (AICPQIA.AC == null) AICPQIA.AC = "";
            JIBS_tmp_int = AICPQIA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
            K_ITM_NO = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1);
            CEP.TRC(SCCGWA, K_ITM_NO);
        }
    }
    public void S000_QRY2_AIPQIA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.BUSI_KND = "DDEG3";
        CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
        AICPQIA.CD.AC_TYP = "3";
        AICPQIA.BR = DDRCCY.OWNER_BRDP;
        AICPQIA.CCY = DDCIDREG.DATA.KEY.CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        DDCSDCLS.DATA.TO_AC = AICPQIA.AC;
        CEP.TRC(SCCGWA, DDCSDCLS.DATA.TO_AC);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void S000_DR_AIZUUPIA() throws IOException,SQLException,Exception {
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C != 0) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = DDCIDREG.DATA.KEY.CCY;
            AICUUPIA.DATA.AMT = DDCIDREG.DATA.BAL;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.RVS_NO = DDCSDCLS.DATA.CREV_NO;
            AICUUPIA.DATA.EVENT_CODE = "DR";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        } else {
            IBS.init(SCCGWA, AICPUITM);
            AICPUITM.DATA.EVENT_CODE = "DR";
            AICPUITM.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
            AICPUITM.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
            AICPUITM.DATA.CCY = DDCIDREG.DATA.KEY.CCY;
            AICPUITM.DATA.ITM_NO = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
            AICPUITM.DATA.AMT = DDCIDREG.DATA.BAL;
            AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZPUITM();
            if (pgmRtn) return;
        }
    }
    public void S000_CR_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCSDCLS.DATA.TO_AC;
        AICUUPIA.DATA.CCY = DDCIDREG.DATA.KEY.CCY;
        AICUUPIA.DATA.AMT = DDCIDREG.DATA.BAL;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = DDCSDCLS.DATA.CREV_NO;
        AICUUPIA.DATA.PAY_MAN = CICACCU.DATA.AC_CNM;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZUUPIA1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.CCY = DDCIDREG.DATA.KEY.CCY;
        AICUUPIA.DATA.AMT = DDCIDREG.DATA.BAL;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
    }
    public void B070_UPD_AC_STS_PROC() throws IOException,SQLException,Exception {
        DDRMST.AC_STS = 'C';
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 3 - 1) + "0" + DDRMST.AC_STS_WORD.substring(3 + 1 - 1);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 11 - 1) + "1" + DDRMST.AC_STS_WORD.substring(11 + 1 - 1);
        DDCRMST.FUNC = 'U';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
    }
    public void B072_UPD_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSDCLS.DATA.AC;
        DDRCCY.CCY = DDCSDCLS.DATA.CCY;
        DDRCCY.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.AGR_NO = DDCSDCLS.DATA.AC;
        CICSACAC.DATA.ACAC_NO = DDRCCY.KEY.AC;
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = DDCSDCLS.DATA.AC;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B075_CRT_BP_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        IBS.init(SCCGWA, BPROCAC);
        BPCSOCAC.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCSOCAC.AC = DDCSDCLS.DATA.AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CLOSE_AC_STS = 'D';
        BPCSOCAC.FUNC = 'U';
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B076_WAVE_ANNUAL_FEES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUFEES);
        DDCUFEES.FUNC = '3';
        DDCUFEES.AC_NO = DDCSDCLS.DATA.AC;
        DDCUFEES.CI_NO = CICACCU.DATA.CI_NO;
        S000_CALL_DDZUFEES();
        if (pgmRtn) return;
    }
    public void B080_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DDCSDCLS.DATA.AC;
        BPCPNHIS.INFO.CCY = DDCSDCLS.DATA.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        BPCPNHIS.INFO.TX_TOOL = CICQACAC.DATA.ACAC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = K_HIS_MMO;
        BPCPNHIS.INFO.TX_TYP = 'D';
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCODCLS);
        DDCODCLS.AC = DDCSDCLS.DATA.AC;
        DDCODCLS.CCY = DDCSDCLS.DATA.CCY;
        DDCODCLS.CCY_TYPE = DDCSDCLS.DATA.CCY_TYPE;
        DDCODCLS.DRAW_MTH = DDCSDCLS.DATA.DRAW_MTH;
        DDCODCLS.PAY_TYP = DDCSDCLS.DATA.PAY_TYP;
        DDCODCLS.TO_AC = DDCSDCLS.DATA.TO_AC;
        DDCODCLS.CREV_NO = AICUUPIA.DATA.RVS_NO;
        DDCODCLS.TX_MMO = DDCSDCLS.DATA.TX_MMO;
        DDCODCLS.REMARKS = "GAA";
        DDCODCLS.OTH_AC = DDCSDCLS.DATA.OTH_AC;
        DDCODCLS.OTH_ACNM = DDCSDCLS.DATA.OTH_ACNM;
        DDCODCLS.OTH_BR = DDCSDCLS.DATA.OTH_BR;
        DDCODCLS.OTH_BRNM = DDCSDCLS.DATA.OTH_BRNM;
        DDCODCLS.L_BAL = DDCIDREG.DATA.BAL;
        CEP.TRC(SCCGWA, DDCODCLS.AC);
        CEP.TRC(SCCGWA, DDCODCLS.CCY);
        CEP.TRC(SCCGWA, DDCODCLS.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCODCLS.DRAW_MTH);
        CEP.TRC(SCCGWA, DDCODCLS.PAY_TYP);
        CEP.TRC(SCCGWA, DDCODCLS.TX_MMO);
        CEP.TRC(SCCGWA, DDCODCLS.REMARKS);
        CEP.TRC(SCCGWA, DDCODCLS.OTH_AC);
        CEP.TRC(SCCGWA, DDCODCLS.OTH_ACNM);
        CEP.TRC(SCCGWA, DDCODCLS.OTH_BR);
        CEP.TRC(SCCGWA, DDCODCLS.OTH_BRNM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCODCLS;
        SCCFMT.DATA_LEN = 786;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B180_CALL_MSGS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        S000_CALL_SCCWRMSG();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.STS = 'C';
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
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
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_POST_AC);
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT, true);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSIACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSIACC", DDCSIACC);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACR.RC);
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        CEP.TRC(SCCGWA, DDCIDREG.RC.RC_CODE);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUFEES() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-WAVE-FEES", DDCUFEES);
        CEP.TRC(SCCGWA, DDCUFEES.RC);
        if (DDCUFEES.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUFEES.RC);
        }
    }
    public void T000_WRITE_DDTCLDD() throws IOException,SQLException,Exception {
        DDTCLDD_RD = new DBParm();
        DDTCLDD_RD.TableName = "DDTCLDD";
        DDTCLDD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCLDD, DDTCLDD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLDD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
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
