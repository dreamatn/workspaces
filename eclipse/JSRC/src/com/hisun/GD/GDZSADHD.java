package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSADHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD560";
    String WS_ERR_MSG = " ";
    short WS_PLDR_CNT = 0;
    short WS_IDX = 0;
    int WS_TMP_DATE = 0;
    char WS_TMP_FLG = ' ';
    String WS_PLDR_NO = " ";
    double WS_RL_AMT = 0;
    double WS_AVL_RELAT_AMT = 0;
    double WS_AVL_RELAT_AMT2 = 0;
    double WS_RELAT_AMT2 = 0;
    double WS_BAL = 0;
    GDZSADHD_WS_TXCTA_NO WS_TXCTA_NO = new GDZSADHD_WS_TXCTA_NO();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    CICACCU CICACCU = new CICACCU();
    GDCOADHD GDCOADHD = new GDCOADHD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDCSADHD GDCSADHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSADHD GDCSADHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSADHD = GDCSADHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSADHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        if (GDCSADHD.TXFUNC == '1') {
            B020_ADVANCE_HOLD_PROC();
            if (pgmRtn) return;
        } else if (GDCSADHD.TXFUNC == '2') {
            B020_RELEASE_HOLD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCSADHD.TXFUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSADHD.TXDD_AC);
        CEP.TRC(SCCGWA, GDCSADHD.TXSEQ);
        if (GDCSADHD.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_ADVANCE_HOLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.AC = GDCSADHD.TXDD_AC;
        DCCUHLD.DATA.SEQ = GDCSADHD.TXSEQ;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.HLD_CLS = '5';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = GDCSADHD.TXCCY;
        if (GDCSADHD.TXCCY.equalsIgnoreCase("156")) {
            DCCUHLD.DATA.CCY_TYP = '1';
        } else {
            DCCUHLD.DATA.CCY_TYP = '2';
        }
        DCCUHLD.DATA.AMT = GDCSADHD.TXHAMT;
        DCCUHLD.DATA.SPR_NO = GDCSADHD.CNTR_NO;
        DCCUHLD.DATA.RMK = GDCSADHD.REMARK;
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.AAMT);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.UAMT);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.AAMT);
    }
    public void B020_RELEASE_HOLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSADHD.TXHLD_NO);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = GDCSADHD.TXHLD_NO;
        DCCURHLD.DATA.AC = GDCSADHD.TXDD_AC;
        DCCURHLD.DATA.RHLD_TYP = '2';
        DCCURHLD.DATA.CCY = GDCSADHD.TXCCY;
        if (GDCSADHD.TXCCY.equalsIgnoreCase("156")) {
            DCCURHLD.DATA.CCY_TYP = '1';
        } else {
            DCCURHLD.DATA.CCY_TYP = '2';
        }
        DCCURHLD.DATA.RAMT = GDCSADHD.TXHAMT;
        DCCURHLD.DATA.CHG_NO = GDCSADHD.CNTR_NO;
        DCCURHLD.DATA.HLD_TYP = '2';
        DCCURHLD.DATA.SPR_TYP = '2';
        DCCURHLD.DATA.RMK = GDCSADHD.REMARK;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCURHLD.DATA.RAMT);
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSADHD.TXDD_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.HLD_NO);
        IBS.init(SCCGWA, GDCOADHD);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOADHD.TXDD_AC = GDCSADHD.TXDD_AC;
        if (GDCSADHD.TXCI_NM.trim().length() == 0) {
            GDCOADHD.TXCI_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOADHD.TXCI_NM = GDCSADHD.TXCI_NM;
        }
        GDCOADHD.TXSEQ = GDCSADHD.TXSEQ;
        GDCOADHD.TXCCY = GDCSADHD.TXCCY;
        GDCOADHD.CCY_TYP = GDCSADHD.CCY_TYP;
        GDCOADHD.TXHLD_NO = DCCUHLD.DATA.HLD_NO;
        GDCOADHD.REMARK = GDCSADHD.REMARK;
        SCCFMT.DATA_PTR = GDCOADHD;
        SCCFMT.DATA_LEN = 454;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
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
