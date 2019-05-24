package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZMGFX {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT_ENTRY = "CIX01";
    String K_OUTPUT_FMT_ADD = "CIX02";
    String K_OUTPUT_FMT_MODIFY = "CIX03";
    String K_OUTPUT_FMT_DELETE = "CIX04";
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    int K_ARR_MAX_CNT = 5;
    String K_GFX_AGT_PARM = "141290001017";
    double K_AMT_MINIMUN = 3000;
    int K_PERSON_MINIMUN = 20;
    String WS_REL_SEQ_L = " ";
    String WS_REL_SEQ_H = " ";
    int WS_I = 0;
    String WS_SIGN_AC = " ";
    char WS_EFF_FLG = ' ';
    char WS_EFF_FLG_CANCLE = ' ';
    char WS_CITCOMP_FLG = ' ';
    char WS_CITAGT_FLG = ' ';
    char WS_CITACR_FLG = ' ';
    char WS_CITCREL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCOMP CIRCOMP = new CIRCOMP();
    CIRCOMP CIRCOMP0 = new CIRCOMP();
    CIRCOMP CIRCOMPO = new CIRCOMP();
    CIRCREL CIRCREL = new CIRCREL();
    CIRBAS CIRBAS = new CIRBAS();
    CIRAGT CIRAGT = new CIRAGT();
    CIRACR CIRACR = new CIRACR();
    CICMAGT CICMAGT = new CICMAGT();
    CICOGFXE CICOGFXE = new CICOGFXE();
    CICOMGFX CICOMGFX = new CICOMGFX();
    CICMCRE CICMCRE = new CICMCRE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMGFX CICMGFX;
    public void MP(SCCGWA SCCGWA, CICMGFX CICMGFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMGFX = CICMGFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMGFX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMGFX.FUNC);
        if (CICMGFX.FUNC == 'I') {
            B020_INQUIRE_ENTRY();
            if (pgmRtn) return;
        } else if (CICMGFX.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMGFX.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMGFX.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICMGFX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQUIRE_ENTRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCOMP);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CICOGFXE);
        CIRCOMP.CI_NO = CICMGFX.CI_NO;
        T000_READ_CITCOMP_CI_NO();
        if (pgmRtn) return;
        if (WS_CITCOMP_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMGFX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRBAS.KEY.CI_NO = CICMGFX.CI_NO;
        T000_READ_CITBAS_CI_NO();
        if (pgmRtn) return;
        CICOGFXE.CI_NO = CIRBAS.KEY.CI_NO;
