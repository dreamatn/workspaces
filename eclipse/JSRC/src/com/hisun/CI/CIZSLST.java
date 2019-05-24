package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZSLST {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CI610";
    int K_ARR_MAX_CNT = 20;
    int WS_I = 0;
    char WS_BAS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSSCH CICSSCH = new CICSSCH();
    CIRLS1 CIRLS1 = new CIRLS1();
    CIRLS2 CIRLS2 = new CIRLS2();
    CIRBAS CIRBAS = new CIRBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSLST CICSLST;
    public void MP(SCCGWA SCCGWA, CICSLST CICSLST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSLST = CICSLST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSLST return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRLS2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        WS_I = 1;
        if (CICSLST.FUNC == 'N' 
            && (CICSLST.DATA.CI_CNM.trim().length() > 0 
            || CICSLST.DATA.CI_ENM.trim().length() > 0)) {
            B030_BY_CINM();
            if (pgmRtn) return;
        } else {
            if (CICSLST.FUNC == 'B' 
                && (CICSLST.DATA.CI_CNM.trim().length() > 0 
                || CICSLST.DATA.CI_ENM.trim().length() > 0)) {
                B030_BY_CINM();
                if (pgmRtn) return;
            }
            if (CICSLST.FUNC == 'O') {
                B011_GET_CI_ID();
                if (pgmRtn) return;
            } else {
                B012_GET_CI_INFO();
                if (pgmRtn) return;
            }
            if (CICSLST.DATA.CI_NO.trim().length() > 0) {
                B020_BY_CINO();
                if (pgmRtn) return;
            }
            if (CICSLST.DATA.CI_NM.trim().length() > 0) {
                B023_BY_NM();
                if (pgmRtn) return;
            } else {
                if (CICSLST.DATA.ID_TYPE.trim().length() > 0 
                    && CICSLST.DATA.ID_NO.trim().length() > 0) {
                    B022_BY_ID();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICSLST.FUNC == 'U') {
            if (CICSLST.DATA.CI_NO.trim().length() > 0) {
                CICSLST.FUNC = 'O';
            } else if ((CICSLST.DATA.ID_TYPE.trim().length() > 0 
                    && CICSLST.DATA.ID_NO.trim().length() > 0 
                    && (CICSLST.DATA.CI_CNM.trim().length() > 0 
                    || CICSLST.DATA.CI_ENM.trim().length() > 0))) {
                CICSLST.FUNC = 'B';
            } else if (CICSLST.DATA.ID_TYPE.trim().length() > 0 
                    && CICSLST.DATA.ID_NO.trim().length() > 0) {
                CICSLST.FUNC = 'I';
            } else if ((CICSLST.DATA.CI_CNM.trim().length() > 0 
                    || CICSLST.DATA.CI_ENM.trim().length() > 0)) {
                CICSLST.FUNC = 'N';
            } else if (CICSLST.DATA.ENTY_TYP != ' ' 
                    && CICSLST.DATA.AGR_NO.trim().length() > 0) {
                CICSLST.FUNC = 'A';
            } else {
            }
        }
    }
    public void B011_GET_CI_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSLST.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'Y') {
            CICSLST.DATA.ID_TYPE = CIRBAS.ID_TYPE;
            CICSLST.DATA.ID_NO = CIRBAS.ID_NO;
