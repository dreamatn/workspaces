package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCEX;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class LNZSDDBR {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String K_CLDD = "CLDD";
    String K_PROD_CLS_ADV = "P015";
    char K_VARIABLE_YES = 'Y';
    char K_VARIABLE_NO = 'N';
    String K_PRCS_SYSTEM = "PRCS";
    String K_XXD_SYSTEM = "NCMS";
    String K_JXD_SYSTEM = "CMSF";
    String K_PPMQ_PROD_CLS_ADV = "P004";
    char K_CKPD_INQ = '0';
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    double WS_EQU_AMT = 0;
    LNZSDDBR_WS_OUT_SYS_DATA WS_OUT_SYS_DATA = new LNZSDDBR_WS_OUT_SYS_DATA();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNZSDDBR_WS_MSGID WS_MSGID = new LNZSDDBR_WS_MSGID();
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSWLAD LNCSLDRW = new LNCSWLAD();
    LNCUADVI LNCUADVI = new LNCUADVI();
    LNCICONT LNCICONT = new LNCICONT();
    LNRADVC LNRADVC = new LNRADVC();
    LNRICTL LNRICTL = new LNRICTL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRCONT LNRCONT = new LNRCONT();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCRCMEQ LNCRCMEQ = new LNCRCMEQ();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCICAL LNCICAL = new LNCICAL();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNCIOVD LNCIOVD = new LNCIOVD();
    LNCICRCM LNCICRCM = new LNCICRCM();
    BPCEX BPCEX = new BPCEX();
    SCCTPCL SCCTPCL = new SCCTPCL();
    SCCGWA SCCGWA;
    LNCSWLAD LNCSDDBR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSDDBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSDDBR = LNCSDDBR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSDDBR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSDDBR.RC.RC_APP = "LN";
        LNCSDDBR.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSDDBR.COMM_DATA.CTA_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B000_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B000_CHECK();
            if (pgmRtn) return;
            B000_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCSDDBR.COMM_DATA.CTA_NO.trim().length() == 0) {
