package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DCZSHLD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC750";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCOHLD DCCOHLD = new DCCOHLD();
    SCCBINF SCCBINF = new SCCBINF();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    SCCGWA SCCGWA;
    DCCSHLD DCCSHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSHLD DCCSHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSHLD = DCCSHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSHLD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_AC_HOLD_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSHLD.DATA.AC);
        CEP.TRC(SCCGWA, DCCSHLD.DATA.CHQ_NO);
        CEP.TRC(SCCGWA, DCCSHLD.DATA.HLD_TYP);
        CEP.TRC(SCCGWA, DCCSHLD.DATA.SPR_TYP);
        if (DCCSHLD.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.HLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.HLD_TYP != '1' 
            && DCCSHLD.DATA.HLD_TYP != '2' 
            && DCCSHLD.DATA.HLD_TYP != '3' 
            && DCCSHLD.DATA.HLD_TYP != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP != '1' 
            && DCCSHLD.DATA.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSHLD.DATA.HLD_TYP == '2' 
            || DCCSHLD.DATA.HLD_TYP == 'A' 
            || DCCSHLD.DATA.HLD_TYP == '3') 
            && DCCSHLD.DATA.AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP == '1' 
            && DCCSHLD.DATA.SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP == '1' 
            && DCCSHLD.DATA.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP == '1' 
            && DCCSHLD.DATA.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.SPR_TYP == '1' 
            && DCCSHLD.DATA.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.HLD_TYP != '3' 
            && DCCSHLD.DATA.CHQ_NO.trim().length() > 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_LOAD_CHQ_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHLD.DATA.HLD_TYP == 'A' 
            && (DCCSHLD.DATA.REF_OLD.trim().length() == 0 
            || !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID"))) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT);
        }
    }
    public void B020_AC_HOLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        if (DCCSHLD.DATA.HLD_TYP != 'A') {
            DCCUHLD.DATA.AC = DCCSHLD.DATA.AC;
            DCCUHLD.DATA.SEQ = DCCSHLD.DATA.SEQ;
            DCCUHLD.DATA.CHQ_NO = DCCSHLD.DATA.CHQ_NO;
            DCCUHLD.DATA.HLD_TYP = DCCSHLD.DATA.HLD_TYP;
            DCCUHLD.DATA.SPR_TYP = DCCSHLD.DATA.SPR_TYP;
            DCCUHLD.DATA.CCY = DCCSHLD.DATA.CCY;
            DCCUHLD.DATA.CCY_TYP = DCCSHLD.DATA.CCY_TYP;
            DCCUHLD.DATA.AMT = DCCSHLD.DATA.AMT;
            DCCUHLD.DATA.SPR_NO = DCCSHLD.DATA.SPR_NO;
            DCCUHLD.DATA.SPR_NM = DCCSHLD.DATA.SPR_NM;
            DCCUHLD.DATA.RSN = DCCSHLD.DATA.RSN;
            DCCUHLD.DATA.EFF_DT = DCCSHLD.DATA.EFF_DT;
            DCCUHLD.DATA.EXP_DT = DCCSHLD.DATA.EXP_DT;
            DCCUHLD.DATA.RMK = DCCSHLD.DATA.RMK;
            DCCUHLD.DATA.HLD_BR = DCCSHLD.DATA.HLD_BR;
            DCCUHLD.DATA.LAW_NM1 = DCCSHLD.DATA.LAW_NM1;
            DCCUHLD.DATA.LAW_NO1 = DCCSHLD.DATA.LAW_NO1;
            DCCUHLD.DATA.LAW_NM2 = DCCSHLD.DATA.LAW_NM2;
            DCCUHLD.DATA.LAW_NO2 = DCCSHLD.DATA.LAW_NO2;
            DCCUHLD.DATA.HLD_NO = DCCSHLD.DATA.REF_OLD;
            DCCUHLD.DATA.HLD_CLS = DCCSHLD.DATA.HLD_CLS;
            DCCUHLD.DATA.CHK_OPT = DCCSHLD.DATA.CHK_OPT;
            DCCUHLD.DATA.PSWD = DCCSHLD.DATA.PSWD;
            DCCUHLD.DATA.TRK_DAT2 = DCCSHLD.DATA.TRK_DAT2;
            DCCUHLD.DATA.TRK_DAT3 = DCCSHLD.DATA.TRK_DAT3;
