package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSSTC {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI STCE INFO        ";
    String K_HIS_CPY = "CIRSTC";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI006";
    int K_MAX_ROW = 25;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    int WS_AC_DATE = 0;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    CIZSSTC_WS_STC_LIST WS_STC_LIST = new CIZSSTC_WS_STC_LIST();
    char WS_STC_FLG = ' ';
    char WS_SCS_FLG = ' ';
    char WS_SCA_FLG = ' ';
    char WS_BAS_FLG = ' ';
    char WS_ACR_FLG = ' ';
    char WS_ADR_FLG = ' ';
    char WS_CNT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRSTC CIRSTC = new CIRSTC();
    CIRSTC CIRSTCO = new CIRSTC();
    CIRSTC CIRSTCN = new CIRSTC();
    CIRSCS CIRSCS = new CIRSCS();
    CIRSCS CIRSCSO = new CIRSCS();
    CIRSCS CIRSCSN = new CIRSCS();
    CIRSCA CIRSCA = new CIRSCA();
    CIRSCA CIRSCAO = new CIRSCA();
    CIRSCA CIRSCAN = new CIRSCA();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CICMADR CICMADR = new CICMADR();
    CICOSTCL CICOSTCL = new CICOSTCL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSSTC CICSSTC;
    public void MP(SCCGWA SCCGWA, CICSSTC CICSSTC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSSTC = CICSSTC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSSTC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRSTC);
        IBS.init(SCCGWA, CIRSCS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSSTC.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICSSTC.FUNC == 'C') {
            B020_AC_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICSSTC.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICSSTC.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICSSTC.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICSSTC.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INF_INPUT_ERR, CICSSTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICSSTC.SCS_DATA.ADR_SRC.trim().length() > 0) {
            IBS.init(SCCGWA, CIRADR);
            CIRADR.KEY.CI_NO = CICSSTC.CI_NO;
            CIRADR.SRC_NO = CICSSTC.SCS_DATA.ADR_SRC;
            T00_SELECT_CITADR();
            if (pgmRtn) return;
            if (WS_ADR_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_NOTFND, CICSSTC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (CICSSTC.SCS_DATA.CNT_SRC.trim().length() > 0) {
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CI_NO = CICSSTC.CI_NO;
            CIRCNT.SRC_NO = CICSSTC.SCS_DATA.CNT_SRC;
            CEP.TRC(SCCGWA, CICSSTC.SCS_DATA.CNT_SRC);
            T00_SELECT_CITCNT_BY_SRC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "123");
            if (WS_CNT_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_NOTFND, CICSSTC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSSTC.CI_NO;
        T00_SELECT_CITBAS();
        if (pgmRtn) return;
