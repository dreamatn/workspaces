package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class CIZCKEV {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_X = "CI815";
    int K_STS_1 = 1;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    int WS_I = 0;
    String WS_ENTY_NO = " ";
    CIZCKEV_WS_DATA_OUT WS_DATA_OUT = new CIZCKEV_WS_DATA_OUT();
    String WS_STSW_FLG = " ";
    char WS_ACR_FLG = ' ';
    char WS_ALT_FLG = ' ';
    char WS_LS1_FLG = ' ';
    char WS_IDE_FLG = ' ';
    char WS_TAE_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRLS1 CIRLS1 = new CIRLS1();
    CIRALT CIRALT = new CIRALT();
    CICCKID CICCKID = new CICCKID();
    CICOCKEV CICOCKEV = new CICOCKEV();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCKEV CICCKEV;
    public void MP(SCCGWA SCCGWA, CICCKEV CICCKEV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCKEV = CICCKEV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCKEV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCKEV.FUNC);
        if (CICCKEV.FUNC == 'C') {
            B030_INQ_BY_CI_PROC();
            if (pgmRtn) return;
        } else if (CICCKEV.FUNC == 'A') {
            B040_INQ_BY_AC_PROC();
            if (pgmRtn) return;
            B030_INQ_BY_CI_PROC();
            if (pgmRtn) return;
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERR;
            WS_ERR_INFO = "CIOT8260 FUNC(" + CICCKEV.FUNC + ")";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_INQ_BY_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRLS1);
        IBS.init(SCCGWA, CIRALT);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CICOCKEV);
        CIRACR.CI_NO = CICCKEV.DATA.CI_NO;
        CIRLS1.KEY.ENTY_NO = CICCKEV.DATA.CI_NO;
        CIRBAS.KEY.CI_NO = CICCKEV.DATA.CI_NO;
        CIRALT.ENTY_NO = CICCKEV.DATA.CI_NO;
        CICOCKEV.CI_EVENT.CI_NO = CICCKEV.DATA.CI_NO;
        CEP.TRC(SCCGWA, CIRLS1.KEY.ENTY_NO);
        T000_READ_CITLS1_FIRST();
        if (pgmRtn) return;
        CICOCKEV.CI_EVENT.BLACK_FLG = WS_LS1_FLG;
        CEP.TRC(SCCGWA, CIRALT.ENTY_NO);
        T000_READ_CITALT_FIRST();
        if (pgmRtn) return;
        CICOCKEV.CI_EVENT.ALT_FLG = WS_ALT_FLG;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOCKEV.CI_EVENT.STS_CODE = WS_STSW_FLG;
        CICOCKEV.CI_EVENT.IDEXP_FLG = WS_IDE_FLG;
        CIRACR.FRM_APP = "DD";
        T000_STARTBR_CITACR_DD();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (WS_ACR_FLG != 'N' 
            && WS_TAE_FLG != 'Y') {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.KEY.AC_NO);
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            if (DDCIMMST.DATA.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && DDCIMMST.DATA.AC_TYPE == 'H') {
                WS_TAE_FLG = 'Y';
            } else {
                WS_TAE_FLG = 'N';
            }
            CICOCKEV.CI_EVENT.TMPAC_FLG = WS_TAE_FLG;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B040_INQ_BY_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCKEV.DATA.ENTY_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (WS_ACR_FLG == 'N') {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = CICCKEV.DATA.ENTY_NO;
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            WS_ENTY_NO = DCCPACTY.OUTPUT.STD_AC;
            CEP.TRC(SCCGWA, WS_ENTY_NO);
            if (!WS_ENTY_NO.equalsIgnoreCase("0")) {
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = WS_ENTY_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                if (WS_ACR_FLG == 'N') {
                    WS_MSGID = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                    WS_ERR_INFO = "(" + CIRACR.KEY.AGR_NO + ")ACNOTFND!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CICCKEV.DATA.CI_NO = CIRACR.CI_NO;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = CICOCKEV;
        SCCFMT.DATA_LEN = 22;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(0, 1).trim().length() == 0) WS_I = 0;
        else WS_I = Integer.parseInt(CIRBAS.STSW.substring(0, 1));
        if (WS_I == K_STS_1) {
            WS_STSW_FLG = "00";
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1).trim().length() == 0) WS_I = 0;
        else WS_I = Integer.parseInt(CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1));
        if (WS_I == K_STS_1) {
            WS_STSW_FLG = "01";
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).trim().length() == 0) WS_I = 0;
        else WS_I = Integer.parseInt(CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1));
        if (WS_I == K_STS_1) {
            WS_STSW_FLG = "02";
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(22 - 1, 22 + 1 - 1).trim().length() == 0) WS_I = 0;
        else WS_I = Integer.parseInt(CIRBAS.STSW.substring(22 - 1, 22 + 1 - 1));
        if (WS_I == K_STS_1) {
            WS_IDE_FLG = 'Y';
        } else {
            WS_IDE_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_STSW_FLG);
    }
    public void T000_STARTBR_CITBAS_ID() throws IOException,SQLException,Exception {
