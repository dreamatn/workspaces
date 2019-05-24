package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQACAC {
    int JIBS_tmp_int;
    int ACR_AC_CNM_LEN;
    int ACR_AC_ENM_LEN;
    int BAS_CI_NM_LEN;
    int BAS_TAX_BANK_LEN;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITACAC_RD;
    DBParm CITACRL_RD;
    boolean pgmRtn = false;
    short K_MAX_BASC_COUNT = 2;
    short K_MAX_ACRC_COUNT = 2;
    String WS_AGR_NO = " ";
    short WS_BASC_COUNT = 0;
    short WS_BASC_I = 0;
    char WS_BASC_FLG = ' ';
    short WS_ACRC_COUNT = 0;
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    char WS_ACAC_FLG = ' ';
    char WS_ACRL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CIRBAS CIRBAS = new CIRBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQACAC CICQACAC;
    CICBASC CICBASC;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICQACAC CICQACAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQACAC = CICQACAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQACAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICBASC = (CICBASC) SCCGWA.COMM_AREA.CITBAS_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICQACAC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQACAC.FUNC == 'R') {
            B020_INQ_BY_ACR();
            if (pgmRtn) return;
        } else if (CICQACAC.FUNC == 'C') {
            B030_INQ_BY_CCY();
            if (pgmRtn) return;
        } else if (CICQACAC.FUNC == 'B') {
            B040_INQ_BY_BV_NO();
            if (pgmRtn) return;
        } else if (CICQACAC.FUNC == 'S') {
            B050_INQ_BY_SEQ();
            if (pgmRtn) return;
        } else if (CICQACAC.FUNC == 'A') {
            B060_INQ_BY_ACAC();
            if (pgmRtn) return;
        } else if (CICQACAC.FUNC == 'I') {
            B070_INQ_BY_AID();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_BY_ACR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.DATA.BV_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AID);
        if (CICQACAC.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CICQACAC.O_DATA.O_OLD_ACR_DATA.O_AGR_NO_OLD = CIRACR.KEY.AGR_NO;
        CICQACAC.O_DATA.O_OLD_ACR_DATA.O_STS_OLD = CIRACR.STS;
        CICQACAC.O_DATA.O_OLD_ACR_DATA.O_FRM_APP_OLD = CIRACR.FRM_APP;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = CICQACAC.DATA.AGR_NO;
            CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = WS_AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
        } else {
            WS_AGR_NO = CICQACAC.DATA.AGR_NO;
        }
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CICQACAC.DATA.AGR_SEQ != 0 
                && CIRACR.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = WS_AGR_NO;
            CIRACAC.AGR_SEQ = CICQACAC.DATA.AGR_SEQ;
            T000_READ_CITACAC_BY_SEQ();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        } else if (((CIRACR.FRM_APP.equalsIgnoreCase("DD") 
                || CIRACR.FRM_APP.equalsIgnoreCase("DC")) 
                && CICQACAC.DATA.CCY_ACAC.trim().length() > 0 
                && CICQACAC.DATA.CR_FLG != ' ' 
                && CIRACR.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1"))) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = WS_AGR_NO;
            CIRACAC.CCY = CICQACAC.DATA.CCY_ACAC;
            CIRACAC.CR_FLG = CICQACAC.DATA.CR_FLG;
            T000_READ_CITACAC_BY_CCY();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        } else if (((CIRACR.FRM_APP.equalsIgnoreCase("TD") 
                || CIRACR.FRM_APP.equalsIgnoreCase("LN")) 
                && CICQACAC.DATA.BV_NO.trim().length() > 0 
                && CIRACR.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = WS_AGR_NO;
            CIRACAC.BV_NO = CICQACAC.DATA.BV_NO;
            T000_READ_CITACAC_BY_BV_NO();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        } else if (CICQACAC.DATA.AID.trim().length() > 0 
                && CIRACR.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = WS_AGR_NO;
            CIRACAC.AID = CICQACAC.DATA.AID;
            T000_READ_CITACAC_BY_AID();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        } else if (CIRACR.STSW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = WS_AGR_NO;
            T000_READ_CITACAC_BY_ACR();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ACAC FOUND ERROR");
            WS_ACAC_FLG = 'E';
        }
        R000_READ_ACRL_INF();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        if (WS_ACAC_FLG == 'S' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_SEQ, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (WS_ACAC_FLG == 'C' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_CCY, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (WS_ACAC_FLG == 'B' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_BV, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (WS_ACAC_FLG == 'A' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_AID, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (WS_ACAC_FLG == 'D' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DEFAULT_ACAC_NOT_FND, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (WS_ACAC_FLG == 'E' 
                && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC FOUND ERROR AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_FOUND_ERROR, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_INQ_BY_CCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        if (CICQACAC.DATA.AGR_NO.trim().length() == 0 
            || CICQACAC.DATA.CCY_ACAC.trim().length() == 0 
            || CICQACAC.DATA.CR_FLG == ' ') {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = CICQACAC.DATA.AGR_NO;
            CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = WS_AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
        } else {
            WS_AGR_NO = CICQACAC.DATA.AGR_NO;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        CIRACAC.CCY = CICQACAC.DATA.CCY_ACAC;
        CIRACAC.CR_FLG = CICQACAC.DATA.CR_FLG;
        T000_READ_CITACAC_BY_CCY();
        if (pgmRtn) return;
        R000_READ_ACRL_INF();
        if (pgmRtn) return;
        if (WS_ACAC_FLG == 'C' 
            && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_CCY, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_INQ_BY_BV_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.BV_NO);
        if (CICQACAC.DATA.AGR_NO.trim().length() == 0 
            || CICQACAC.DATA.BV_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = CICQACAC.DATA.AGR_NO;
            CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = WS_AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
        } else {
            WS_AGR_NO = CICQACAC.DATA.AGR_NO;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        CIRACAC.BV_NO = CICQACAC.DATA.BV_NO;
        T000_READ_CITACAC_BY_BV_NO();
        if (pgmRtn) return;
        R000_READ_ACRL_INF();
        if (pgmRtn) return;
        if (WS_ACAC_FLG == 'B' 
            && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_BV, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_INQ_BY_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
        if (CICQACAC.DATA.AGR_NO.trim().length() == 0 
            || CICQACAC.DATA.AGR_SEQ == 0) {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = CICQACAC.DATA.AGR_NO;
            CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = WS_AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
        } else {
            WS_AGR_NO = CICQACAC.DATA.AGR_NO;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        CIRACAC.AGR_SEQ = CICQACAC.DATA.AGR_SEQ;
        T000_READ_CITACAC_BY_SEQ();
        if (pgmRtn) return;
        R000_READ_ACRL_INF();
        if (pgmRtn) return;
        if (WS_ACAC_FLG == 'S' 
            && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_SEQ, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B060_INQ_BY_ACAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
        if (CICQACAC.DATA.ACAC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.KEY.ACAC_NO = CICQACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_BY_ACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIRACAC.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        WS_AGR_NO = CIRACR.KEY.AGR_NO;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B070_INQ_BY_AID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AID);
        if (CICQACAC.DATA.AGR_NO.trim().length() == 0 
            || CICQACAC.DATA.AID.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = CICQACAC.DATA.AGR_NO;
            CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = WS_AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
        } else {
            WS_AGR_NO = CICQACAC.DATA.AGR_NO;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        CIRACAC.AID = CICQACAC.DATA.AID;
        T000_READ_CITACAC_BY_AID();
        if (pgmRtn) return;
        R000_READ_ACRL_INF();
        if (pgmRtn) return;
        if (WS_ACAC_FLG == 'A' 
            && WS_ACRL_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACAC AND ACRL NOTFND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_FND_BY_AID, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO = WS_AGR_NO;
        CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
        CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO = CIRACR.CI_NO;
        CICQACAC.O_DATA.O_ACR_DATA.O_STS = CIRACR.STS;
        CICQACAC.O_DATA.O_ACR_DATA.O_STSW = CIRACR.STSW;
        CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR = CIRACR.PROD_CD;
        CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR = CIRACR.FRM_APP;
        CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR = CIRACR.CCY;
        CICQACAC.O_DATA.O_ACR_DATA.O_SHOW_FLG = CIRACR.SHOW_FLG;
        CICQACAC.O_DATA.O_ACR_DATA.O_SMS_FLG = CIRACR.SMS_FLG;
        CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR = CIRACR.OPN_BR;
        CICQACAC.O_DATA.O_ACR_DATA.O_OPEN_DT_ACR = CIRACR.OPEN_DT;
        CICQACAC.O_DATA.O_ACR_DATA.O_CLOSE_DT_ACR = CIRACR.CLOSE_DT;
        CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR = CIRACR.OWNER_BK;
        if (WS_ACAC_FLG == 'F') {
            CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO = CIRACAC.KEY.ACAC_NO;
            CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ = CIRACAC.AGR_SEQ;
            CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO = CIRACAC.BV_NO;
            CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC = CIRACAC.CCY;
            CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG = CIRACAC.CR_FLG;
            CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC = CIRACAC.PROD_CD;
            CICQACAC.O_DATA.O_ACAC_DATA.O_AID = CIRACAC.AID;
            CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS = CIRACAC.ACAC_STS;
            CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL = CIRACAC.ACAC_CTL;
            CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC = CIRACAC.FRM_APP;
            CICQACAC.O_DATA.O_ACAC_DATA.O_OPN_BR_ACAC = CIRACAC.OPN_BR;
            CICQACAC.O_DATA.O_ACAC_DATA.O_OWNER_BK_ACAC = CIRACAC.OWNER_BK;
            CICQACAC.O_DATA.O_ACAC_DATA.O_OPEN_DT_ACAC = CIRACAC.OPEN_DT;
            CICQACAC.O_DATA.O_ACAC_DATA.O_CLOSE_DT_ACAC = CIRACAC.CLOSE_DT;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '1') {
            CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM = CIRBAS.CI_NM;
        } else if (CIRBAS.CI_TYP == '2' 
                && CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_AC_R();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM = CIRACR.AC_CNM;
                CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM = CIRACR.AC_ENM;
            }
        } else {
            CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM = CIRACR.AC_CNM;
            CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM = CIRACR.AC_ENM;
        }
    }
    public void R000_READ_ACRL_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AGR_NO);
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = WS_AGR_NO;
        T000_READ_CITACRL_BY_AC();
        if (pgmRtn) return;
        if (WS_ACRL_FLG == 'F') {
            CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL = CIRACRL.KEY.AC_REL;
            CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
        }
    }
    public void R000_TRANS_ACRC_TO_ACR() throws IOException,SQLException,Exception {
        CIRACR.KEY.AGR_NO = CICACRC.DATA[WS_ACRC_I-1].AGR_NO;
        CIRACR.ENTY_TYP = CICACRC.DATA[WS_ACRC_I-1].ENTY_TYP;
        CIRACR.CI_NO = CICACRC.DATA[WS_ACRC_I-1].CI_NO;
        CIRACR.STS = CICACRC.DATA[WS_ACRC_I-1].STS;
        CIRACR.STSW = CICACRC.DATA[WS_ACRC_I-1].STSW;
        CIRACR.PROD_CD = CICACRC.DATA[WS_ACRC_I-1].PROD_CD;
        CIRACR.CNTRCT_TYP = CICACRC.DATA[WS_ACRC_I-1].CNTRCT_TYP;
        CIRACR.FRM_APP = CICACRC.DATA[WS_ACRC_I-1].FRM_APP;
        CIRACR.CCY = CICACRC.DATA[WS_ACRC_I-1].CCY;
        CIRACR.SHOW_FLG = CICACRC.DATA[WS_ACRC_I-1].SHOW_FLG;
        CIRACR.SMS_FLG = CICACRC.DATA[WS_ACRC_I-1].SMS_FLG;
        CIRACR.AC_CNM = CICACRC.DATA[WS_ACRC_I-1].AC_CNM;
        CIRACR.AC_ENM = CICACRC.DATA[WS_ACRC_I-1].AC_ENM;
        ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        if (ACR_AC_CNM_LEN == 0) {
            ACR_AC_CNM_LEN += 1;
        }
        ACR_AC_ENM_LEN = CIRACR.AC_ENM.length();
        if (ACR_AC_ENM_LEN == 0) {
            ACR_AC_ENM_LEN += 1;
        }
        CIRACR.AC_SEQ = CICACRC.DATA[WS_ACRC_I-1].AC_SEQ;
        CIRACR.OPN_BR = CICACRC.DATA[WS_ACRC_I-1].OPN_BR;
        CIRACR.OPEN_DT = CICACRC.DATA[WS_ACRC_I-1].OPEN_DT;
        CIRACR.CLOSE_DT = CICACRC.DATA[WS_ACRC_I-1].CLOSE_DT;
        CIRACR.OWNER_BK = CICACRC.DATA[WS_ACRC_I-1].OWNER_BK;
        CIRACR.CRT_TLR = CICACRC.DATA[WS_ACRC_I-1].CRT_TLR;
        CIRACR.CRT_BR = CICACRC.DATA[WS_ACRC_I-1].CRT_BR;
        CIRACR.CRT_DT = CICACRC.DATA[WS_ACRC_I-1].CRT_DT;
        CIRACR.UPD_TLR = CICACRC.DATA[WS_ACRC_I-1].UPD_TLR;
        CIRACR.UPD_BR = CICACRC.DATA[WS_ACRC_I-1].UPD_BR;
        CIRACR.UPD_DT = CICACRC.DATA[WS_ACRC_I-1].UPD_DT;
        CIRACR.TS = CICACRC.DATA[WS_ACRC_I-1].TS;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, WS_ACRC_I);
    }
    public void R000_WRITE_ACRC() throws IOException,SQLException,Exception {
        CICACRC.COUNT = (short) (CICACRC.COUNT + 1);
        CICACRC.DATA[CICACRC.COUNT-1].AGR_NO = CIRACR.KEY.AGR_NO;
        CICACRC.DATA[CICACRC.COUNT-1].ENTY_TYP = CIRACR.ENTY_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].CI_NO = CIRACR.CI_NO;
        CICACRC.DATA[CICACRC.COUNT-1].STS = CIRACR.STS;
        CICACRC.DATA[CICACRC.COUNT-1].STSW = CIRACR.STSW;
        CICACRC.DATA[CICACRC.COUNT-1].PROD_CD = CIRACR.PROD_CD;
        CICACRC.DATA[CICACRC.COUNT-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].FRM_APP = CIRACR.FRM_APP;
        CICACRC.DATA[CICACRC.COUNT-1].CCY = CIRACR.CCY;
        CICACRC.DATA[CICACRC.COUNT-1].SHOW_FLG = CIRACR.SHOW_FLG;
        CICACRC.DATA[CICACRC.COUNT-1].SMS_FLG = CIRACR.SMS_FLG;
        if (CIRACR.AC_CNM == null) CIRACR.AC_CNM = "";
        JIBS_tmp_int = CIRACR.AC_CNM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRACR.AC_CNM += " ";
        CICACRC.DATA[CICACRC.COUNT-1].AC_CNM = CIRACR.AC_CNM.substring(0, ACR_AC_CNM_LEN);
        if (CIRACR.AC_ENM == null) CIRACR.AC_ENM = "";
        JIBS_tmp_int = CIRACR.AC_ENM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRACR.AC_ENM += " ";
        CICACRC.DATA[CICACRC.COUNT-1].AC_ENM = CIRACR.AC_ENM.substring(0, ACR_AC_ENM_LEN);
        CICACRC.DATA[CICACRC.COUNT-1].AC_SEQ = CIRACR.AC_SEQ;
        CICACRC.DATA[CICACRC.COUNT-1].OPN_BR = CIRACR.OPN_BR;
        CICACRC.DATA[CICACRC.COUNT-1].OPEN_DT = CIRACR.OPEN_DT;
        CICACRC.DATA[CICACRC.COUNT-1].CLOSE_DT = CIRACR.CLOSE_DT;
        CICACRC.DATA[CICACRC.COUNT-1].OWNER_BK = CIRACR.OWNER_BK;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_TLR = CIRACR.CRT_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_BR = CIRACR.CRT_BR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_DT = CIRACR.CRT_DT;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_TLR = CIRACR.UPD_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_BR = CIRACR.UPD_BR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_DT = CIRACR.UPD_DT;
        CICACRC.DATA[CICACRC.COUNT-1].TS = CIRACR.TS;
        CEP.TRC(SCCGWA, CICACRC.COUNT);
        CEP.TRC(SCCGWA, CICACRC.DATA[CICACRC.COUNT-1].AGR_NO);
    }
    public void R000_TRANS_BASC_TO_BAS() throws IOException,SQLException,Exception {
        CIRBAS.KEY.CI_NO = CICBASC.DATA[WS_BASC_I-1].CI_NO;
        CIRBAS.CI_TYP = CICBASC.DATA[WS_BASC_I-1].CI_TYP;
        CIRBAS.CI_ATTR = CICBASC.DATA[WS_BASC_I-1].CI_ATTR;
        CIRBAS.SVR_LVL = CICBASC.DATA[WS_BASC_I-1].SVR_LVL;
        CIRBAS.SVR_LVL1 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL1;
        CIRBAS.SVR_LVL2 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL2;
        CIRBAS.SVR_LVL3 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL3;
        CIRBAS.STSW = CICBASC.DATA[WS_BASC_I-1].STSW;
        CIRBAS.VER_STSW = CICBASC.DATA[WS_BASC_I-1].VER_STSW;
        CIRBAS.IDE_STSW = CICBASC.DATA[WS_BASC_I-1].IDE_STSW;
        CIRBAS.OPN_BR = CICBASC.DATA[WS_BASC_I-1].OPN_BR;
        CIRBAS.OPEN_DT = CICBASC.DATA[WS_BASC_I-1].OPEN_DT;
        CIRBAS.CLOSE_DT = CICBASC.DATA[WS_BASC_I-1].CLOSE_DT;
        CIRBAS.ORGIN_TP = CICBASC.DATA[WS_BASC_I-1].ORGIN_TP;
        CIRBAS.ORIGIN = CICBASC.DATA[WS_BASC_I-1].ORIGIN;
        CIRBAS.ORIGIN2 = CICBASC.DATA[WS_BASC_I-1].ORIGIN2;
        CIRBAS.CI_NM = CICBASC.DATA[WS_BASC_I-1].CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICBASC.DATA[WS_BASC_I-1].ID_TYPE;
        CIRBAS.ID_NO = CICBASC.DATA[WS_BASC_I-1].ID_NO;
        CIRBAS.ID_RGN = CICBASC.DATA[WS_BASC_I-1].ID_RGN;
        CIRBAS.TAX_BANK = CICBASC.DATA[WS_BASC_I-1].TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICBASC.DATA[WS_BASC_I-1].TAX_AC_NO;
        CIRBAS.TAX_TYPE = CICBASC.DATA[WS_BASC_I-1].TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICBASC.DATA[WS_BASC_I-1].TAX_DIST_NO;
        CIRBAS.FTA_FLG = CICBASC.DATA[WS_BASC_I-1].FTA_FLG;
        CIRBAS.OWNER_BK = CICBASC.DATA[WS_BASC_I-1].OWNER_BK;
        CIRBAS.OWNER_BR = CICBASC.DATA[WS_BASC_I-1].OWNER_BR;
        CIRBAS.OIC_NO = CICBASC.DATA[WS_BASC_I-1].OIC_NO;
        CIRBAS.RESP_CD = CICBASC.DATA[WS_BASC_I-1].RESP_CD;
        CIRBAS.SUB_DP = CICBASC.DATA[WS_BASC_I-1].SUB_DP;
        CIRBAS.OPEN_CHNL = CICBASC.DATA[WS_BASC_I-1].OPEN_CHNL;
        CIRBAS.NRA_TAX_TYP = CICBASC.DATA[WS_BASC_I-1].NRA_TAX_TYP;
        CIRBAS.CRT_TLR = CICBASC.DATA[WS_BASC_I-1].CRT_TLR;
        CIRBAS.CRT_DT = CICBASC.DATA[WS_BASC_I-1].CRT_DT;
        CIRBAS.CRT_BR = CICBASC.DATA[WS_BASC_I-1].CRT_BR;
        CIRBAS.UPD_TLR = CICBASC.DATA[WS_BASC_I-1].UPD_TLR;
        CIRBAS.UPD_BR = CICBASC.DATA[WS_BASC_I-1].UPD_BR;
        CIRBAS.UPD_DT = CICBASC.DATA[WS_BASC_I-1].UPD_DT;
        CIRBAS.TS = CICBASC.DATA[WS_BASC_I-1].TS;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        CEP.TRC(SCCGWA, WS_BASC_I);
    }
    public void R000_WRITE_BASC() throws IOException,SQLException,Exception {
        CICBASC.COUNT = (short) (CICBASC.COUNT + 1);
        CICBASC.DATA[CICBASC.COUNT-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICBASC.DATA[CICBASC.COUNT-1].CI_TYP = CIRBAS.CI_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CI_ATTR = CIRBAS.CI_ATTR;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL = CIRBAS.SVR_LVL;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL1 = CIRBAS.SVR_LVL1;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL2 = CIRBAS.SVR_LVL2;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL3 = CIRBAS.SVR_LVL3;
        CICBASC.DATA[CICBASC.COUNT-1].STSW = CIRBAS.STSW;
        CICBASC.DATA[CICBASC.COUNT-1].VER_STSW = CIRBAS.VER_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].IDE_STSW = CIRBAS.IDE_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].OPN_BR = CIRBAS.OPN_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_DT = CIRBAS.OPEN_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CLOSE_DT = CIRBAS.CLOSE_DT;
        CICBASC.DATA[CICBASC.COUNT-1].ORGIN_TP = CIRBAS.ORGIN_TP;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN = CIRBAS.ORIGIN;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN2 = CIRBAS.ORIGIN2;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICBASC.DATA[CICBASC.COUNT-1].CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].ID_NO = CIRBAS.ID_NO;
        CICBASC.DATA[CICBASC.COUNT-1].ID_RGN = CIRBAS.ID_RGN;
        if (CIRBAS.TAX_BANK == null) CIRBAS.TAX_BANK = "";
        JIBS_tmp_int = CIRBAS.TAX_BANK.length();
        for (int i=0;i<140-JIBS_tmp_int;i++) CIRBAS.TAX_BANK += " ";
        CICBASC.DATA[CICBASC.COUNT-1].TAX_BANK = CIRBAS.TAX_BANK.substring(0, BAS_TAX_BANK_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].TAX_AC_NO = CIRBAS.TAX_AC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_TYPE = CIRBAS.TAX_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_DIST_NO = CIRBAS.TAX_DIST_NO;
        CICBASC.DATA[CICBASC.COUNT-1].FTA_FLG = CIRBAS.FTA_FLG;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BK = CIRBAS.OWNER_BK;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BR = CIRBAS.OWNER_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OIC_NO = CIRBAS.OIC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].RESP_CD = CIRBAS.RESP_CD;
        CICBASC.DATA[CICBASC.COUNT-1].SUB_DP = CIRBAS.SUB_DP;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_CHNL = CIRBAS.OPEN_CHNL;
        CICBASC.DATA[CICBASC.COUNT-1].NRA_TAX_TYP = CIRBAS.NRA_TAX_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_TLR = CIRBAS.CRT_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_DT = CIRBAS.CRT_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_BR = CIRBAS.CRT_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_TLR = CIRBAS.UPD_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_BR = CIRBAS.UPD_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_DT = CIRBAS.UPD_DT;
        CICBASC.DATA[CICBASC.COUNT-1].TS = CIRBAS.TS;
        CEP.TRC(SCCGWA, CICBASC.COUNT);
        CEP.TRC(SCCGWA, CICBASC.DATA[CICBASC.COUNT-1].CI_NO);
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        WS_BASC_I = 0;
        WS_BASC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_BASC_I < CICBASC.COUNT 
            && WS_BASC_FLG != 'Y') {
            WS_BASC_I = (short) (WS_BASC_I + 1);
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            if (CIRBAS.KEY.CI_NO.equalsIgnoreCase(CICBASC.DATA[WS_BASC_I-1].CI_NO)) {
                CEP.TRC(SCCGWA, "READ BAS CAHSE");
                R000_TRANS_BASC_TO_BAS();
                if (pgmRtn) return;
                WS_BASC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
        if (WS_BASC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ BAS TABLE");
            CITBAS_RD = new DBParm();
            CITBAS_RD.TableName = "CITBAS";
            IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICBASC.COUNT);
                if (CICBASC.COUNT < K_MAX_BASC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE BAS CASHE");
                    R000_WRITE_BASC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        WS_ACRC_I = 0;
        WS_ACRC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_ACRC_I < CICACRC.COUNT 
            && WS_ACRC_FLG != 'Y') {
            WS_ACRC_I = (short) (WS_ACRC_I + 1);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            if (CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICACRC.DATA[WS_ACRC_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "READ ACR CASHE");
                R000_TRANS_ACRC_TO_ACR();
                if (pgmRtn) return;
                WS_ACRC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
        if (WS_ACRC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ ACR TABLE");
            CITACR_RD = new DBParm();
            CITACR_RD.TableName = "CITACR";
            IBS.READ(SCCGWA, CIRACR, CITACR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_NOT_EXIST, CICQACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                B_DB_EXCP();
                if (pgmRtn) return;
            }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICACRC.COUNT);
                if (CICACRC.COUNT < K_MAX_ACRC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE ACR CASHE");
                    R000_WRITE_ACRC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_READ_CITACAC_BY_ACR() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            WS_ACAC_FLG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_BY_CCY() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO , CCY , CR_FLG";
        CITACAC_RD.where = "FRM_APP = 'DD' "
            + "AND AID = ' '";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            WS_ACAC_FLG = 'C';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_BY_BV_NO() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO , BV_NO";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            WS_ACAC_FLG = 'B';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_BY_SEQ() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO , AGR_SEQ";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            WS_ACAC_FLG = 'S';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_BY_ACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_BY_AID() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO , AID";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            WS_ACAC_FLG = 'A';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1' "
            + "AND AC_REL IN ( '01' , '02' , '03' , '04' , '09' , '13' )";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACRL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACRL INF NOT FOUND");
            WS_ACRL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC_R() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACRL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACRL INF NOT FOUND");
            WS_ACRL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
