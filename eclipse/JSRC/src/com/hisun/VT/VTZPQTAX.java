package com.hisun.VT;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICMSG_ERROR_MSG;
import com.hisun.AI.AICPQITM;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCBWEVT;
import com.hisun.BP.BPCEX;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPCRDAMT;
import com.hisun.BP.BPREWA;
import com.hisun.BP.BPREWAD_EVENT;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class VTZPQTAX {
    boolean pgmRtn = false;
    int K_BR = 999999;
    String K_EX_MDR = "MDR";
    String K_PROD_CD = "TR000255";
    String K_CHNL_NO = "CORE1";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String CPN_EXG_CURRENCY = "BP-EX";
    String CPN_BR_QUERY = "BP-P-INQ-ORG";
    String CPN_OTAX_QUERY = "VT-R-OTAX-MAINTAIN";
    String CPN_TAX_QUERY = "VT-R-TAX-MAINTAIN";
    String CPN_PRDR_QUERY = "VT-R-PRDR-MAINTAIN";
    String WS_ERR_MSG = " ";
    int WS_BR = 0;
    String WS_CODE = " ";
    short WS_RT = 0;
    double WS_SH_OBAL = 0;
    String WS_MID_AMT = " ";
    String WS_SH_AMT = " ";
    double WS_VTCD_RT = 0;
    int WS_SET_SEQ = 0;
    int WS_SEQ = 0;
    int WS_BAT_SEQ = 0;
    int WS_EC_SET_SEQ = 0;
    String WS_EC_SET_NO = " ";
    int WS_EC_AC_DATE = 0;
    double WS_EX_AMT = 0;
    double WS_TAX_AMT = 0;
    double WS_TAX_AMT_OLD = 0;
    double WS_JM_IN_USE = 0;
    short WS_RAT = 0;
    double WS_OBAL = 0;
    double WS_CNY_AMT = 0;
    double WS_AMT = 0;
    String WS_JRN_NO = " ";
    String WS_TEMP_JRN_NO = " ";
    int WS_AC_DATE = 0;
    char WS_BILL_FLG = ' ';
    String WS_RETURN_CCY = " ";
    short WS_CNT = 0;
    String WS_PODR_OTH = " ";
    VTZPQTAX_REDEFINES30 REDEFINES30 = new VTZPQTAX_REDEFINES30();
    VTZPQTAX_WS_PRDR_NO WS_PRDR_NO = new VTZPQTAX_WS_PRDR_NO();
    VTZPQTAX_WS_ITMR_NO WS_ITMR_NO = new VTZPQTAX_WS_ITMR_NO();
    char WS_VAT_TYPE = ' ';
    char WS_QUERY_PRDR_CNT = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_PROD_FLG = ' ';
    char WS_RETURN_FLG = ' ';
    char WS_JMAC_FLG = ' ';
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    VTRPRDR VTRPRDR = new VTRPRDR();
    VTCRPRDR VTCRPRDR = new VTCRPRDR();
    VTCROTAX VTCROTAX = new VTCROTAX();
    VTROTAX VTROTAX = new VTROTAX();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    VTRACCT VTRACCT = new VTRACCT();
    VTCRACCT VTCRACCT = new VTCRACCT();
    BPCEX BPCEX = new BPCEX();
    AICUIANO AICUIANO = new AICUIANO();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA = new SCCGSCA_SC_AREA();
    VTRITMR VTRITMR = new VTRITMR();
    VTCRITMR VTCRITMR = new VTCRITMR();
    AICPQITM AICPQITM = new AICPQITM();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    VTRHTAX VTRHTAX = new VTRHTAX();
    VTCRHTAX VTCRHTAX = new VTCRHTAX();
    VTCRPODR VTCRPODR = new VTCRPODR();
    VTRPODR VTRPODR = new VTRPODR();
    VTRJMAC VTRJMAC = new VTRJMAC();
    VTRJMCD VTRJMCD = new VTRJMCD();
    VTRJMRE VTRJMRE = new VTRJMRE();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCBATH SCCBATH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPREWA BPREWA;
    BPREWAD_EVENT BPREWAD_EVENT;
    VTCPQTAX VTCPQTAX;
    public void MP(SCCGWA SCCGWA, VTCPQTAX VTCPQTAX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCPQTAX = VTCPQTAX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZPQTAX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPREWA = (BPREWA) GWA_BP_AREA.EWA_AREA.EWA_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }