package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSSCH {
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_CI_NO = " ";
    String WS_ID_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRACR CIRACR = new CIRACR();
    CICLSCH CICLSCH = new CICLSCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSSCH CICSSCH;
    public void MP(SCCGWA SCCGWA, CICSSCH CICSSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSSCH = CICSSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSSCH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSSCH.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSSCH.FUNC == 'O') {
            B020_SEARCH_BY_CINO();
            if (pgmRtn) return;
        } else if (CICSSCH.FUNC == 'B') {
            B030_SEARCH_BY_IDNM();
            if (pgmRtn) return;
        } else if (CICSSCH.FUNC == 'I') {
            B040_SEARCH_BY_ID();
            if (pgmRtn) return;
        } else if (CICSSCH.FUNC == 'A') {
            B050_SEARCH_BY_AC();
            if (pgmRtn) return;
        } else if (CICSSCH.FUNC == 'N') {
            B060_SEARCH_BY_CINM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.FUNC);
        if (CICSSCH.FUNC == 'U') {
            if (CICSSCH.INPUT_DATA.I_AGR_NO.trim().length() > 0) {
                CICSSCH.FUNC = 'A';
            } else if (CICSSCH.INPUT_DATA.I_CI_NO.trim().length() > 0) {
                CICSSCH.FUNC = 'O';
            } else if (CICSSCH.INPUT_DATA.I_ID_TYPE.trim().length() > 0 
                    && CICSSCH.INPUT_DATA.I_ID_NO.trim().length() > 0 
                    && CICSSCH.INPUT_DATA.I_CI_NM.trim().length() > 0) {
                CICSSCH.FUNC = 'B';
            } else if (CICSSCH.INPUT_DATA.I_ID_TYPE.trim().length() > 0 
                    && CICSSCH.INPUT_DATA.I_ID_NO.trim().length() > 0) {
                CICSSCH.FUNC = 'I';
            } else if (CICSSCH.INPUT_DATA.I_CI_NM.trim().length() > 0) {
                CICSSCH.FUNC = 'N';
            } else {
                CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, CICSSCH.FUNC);
        }
    }
    public void B020_SEARCH_BY_CINO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_CI_NO);
        if (CICSSCH.INPUT_DATA.I_CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSSCH.INPUT_DATA.I_CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        if (CICSSCH.RTN_FLG1 == 'F') {
            R000_TRANS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        } else if (CICSSCH.RTN_FLG1 == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "RETURN FLG ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_SEARCH_BY_IDNM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_ID_TYPE);
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_ID_NO);
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_CI_NM);
        if (CICSSCH.INPUT_DATA.I_ID_TYPE.trim().length() == 0 
            || CICSSCH.INPUT_DATA.I_ID_NO.trim().length() == 0 
            || CICSSCH.INPUT_DATA.I_CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_ID_TYPE);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSSCH.INPUT_DATA.I_ID_TYPE;
        CIRBAS.ID_NO = CICSSCH.INPUT_DATA.I_ID_NO;
        CIRBAS.CI_NM = CICSSCH.INPUT_DATA.I_CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (CICSSCH.RTN_FLG1 == 'F') {
            R000_TRANS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_SEARCH_BY_ID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_ID_NO);
        if (CICSSCH.INPUT_DATA.I_ID_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
        }
        IBS.init(SCCGWA, CIRID);
        CIRID.ID_NO = CICSSCH.INPUT_DATA.I_ID_NO;
        T000_STARTBR_CITID_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_NOTFND, CICSSCH.RC);
            CICSSCH.RTN_FLG1 = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        CICSSCH.RTN_FLG1 = 'F';
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_CI_NO = CIRID.KEY.CI_NO;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
    }
    public void B050_SEARCH_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_AGR_NO);
        if (CICSSCH.INPUT_DATA.I_AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICSSCH.INPUT_DATA.I_AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        if (CICSSCH.RTN_FLG1 == 'F') {
            R000_TRANS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        } else if (CICSSCH.RTN_FLG1 == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "RETURN FLG ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_SEARCH_BY_CINM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSSCH.INPUT_DATA.I_CI_NM);
        if (CICSSCH.INPUT_DATA.I_CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSSCH.RC);
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.CI_NM = CICSSCH.INPUT_DATA.I_CI_NM;
        NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
