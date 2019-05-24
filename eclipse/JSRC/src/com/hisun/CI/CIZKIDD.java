package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
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

public class CIZKIDD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_X = "CI530";
    int K_NUM_1 = 1;
    int K_STS_1 = 1;
    String K_ID_TYP = "10100";
    String K_TEMP_TYP = "10200";
    String K_REDB_TYP = "10300";
    String K_OTHER_TYP = "99999";
    int K_MAX_ROW = 25;
    String K_ID_HKID = "10701";
    String K_ID_TWID = "10703";
    String K_ID_CARD = "10100";
    String K_ID_ICARD = "10200";
    String K_ID_TYPE_ORG = "20600";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_S = 0;
    int WS_I = 0;
    int WS_O = 0;
    int WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    int WS_CX = 0;
    int WS_SEQ = 0;
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    String WS_CI_NO = " ";
    String WS_TPID_TYPE = " ";
    String WS_CHECK_OTHER_ID = " ";
    String WS_STSW_FLG = " ";
    char WS_MPAG_ONE = ' ';
    char WS_CDM_FLG = ' ';
    char WS_FDM_FLG = ' ';
    char WS_BAS_FLG = ' ';
    char WS_ACR_FLAG = ' ';
    char WS_NAM_FLAG = ' ';
    char WS_ID_FLAG = ' ';
    char WS_COUNT_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    CIRBAS CIRBAS = new CIRBAS();
    CICUHIS CICUHIS = new CICUHIS();
    CIRACR CIRACR = new CIRACR();
    CIRID CIRID = new CIRID();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRNAM CIRNAM = new CIRNAM();
    CICBALT CICBALT = new CICBALT();
    CICOKIDD CICOKIDD = new CICOKIDD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    int WS_CNT = 0;
    String WS_DB_ID_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICKIDD CICKIDD;
    public void MP(SCCGWA SCCGWA, CICKIDD CICKIDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICKIDD = CICKIDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZKIDD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRID);
        IBS.init(SCCGWA, CICOKIDD.OUTPUT_TITLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICKIDD.FUNC);
        if (CICKIDD.FUNC == 'I') {
            B020_INQ_BY_ID_PROC();
            if (pgmRtn) return;
        } else if (CICKIDD.FUNC == 'C') {
            B030_INQ_BY_CI_PROC();
            if (pgmRtn) return;
        } else if (CICKIDD.FUNC == 'A') {
            B040_INQ_BY_AC_PROC();
            if (pgmRtn) return;
        } else if (CICKIDD.FUNC == 'D') {
            B050_INQ_BY_IDNO_PROC();
            if (pgmRtn) return;
        } else if (CICKIDD.FUNC == 'E') {
            B060_CHECK_INFO_CI_PROC();
            if (pgmRtn) return;
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERR;
            WS_ERR_INFO = "CIOT5300 FUNC(" + CICKIDD.FUNC + ")";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_BY_ID_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICKIDD.DATA.CI_NM);
        WS_ID_TYPE = CICKIDD.DATA.ID_TYPE;
        WS_ID_NO = CICKIDD.DATA.ID_NO;
        WS_CI_NM = CICKIDD.DATA.CI_NM;
        B020_01_STARTBR_ID_PROC();
        if (pgmRtn) return;
        B020_01_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        T000_READNEXT_CITBAS_FIRST();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CICUHIS);
            CICUHIS.PRI_CI = CIRBAS.KEY.CI_NO;
            CICUHIS.FUNC = 'G';
            S000_CALL_CIZUHIS();
            if (pgmRtn) return;
            CIRBAS.KEY.CI_NO = CICUHIS.KEY.CI_NO;
            CEP.TRC(SCCGWA, CICUHIS.KEY.CI_NO);
            T000_READ_CITBAS_CI_NO();
            if (pgmRtn) return;
        }
        if (WS_BAS_FLG == 'N') {
            CICOKIDD.OUTPUT_TITLE.TOTAL_PAGE = 1;
            CICOKIDD.OUTPUT_TITLE.TOTAL_NUM = 1;
            CICOKIDD.OUTPUT_TITLE.CURR_PAGE = 1;
            CICOKIDD.OUTPUT_TITLE.LAST_PAGE = 'Y';
            CICOKIDD.OUTPUT_TITLE.CI_FLG = '5';
        } else {
            WS_TPID_TYPE = CIRBAS.ID_TYPE;
        }
        CEP.TRC(SCCGWA, WS_BAS_FLG);
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        for (WS_I = 1; WS_BAS_FLG != 'N' 
            && WS_I <= WS_PAGE_ROW; WS_I += 1) {
            B050_01_OUTPUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
        CICOKIDD.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_O;
        DATA = new CICOKIDD_DATA();
        CICOKIDD.DATA.add(DATA);
    }
    public void B030_INQ_BY_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICKIDD.DATA.CI_NO;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (WS_BAS_FLG == 'Y' 
            && CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CICUHIS);
            CICUHIS.PRI_CI = CIRBAS.KEY.CI_NO;
            CICUHIS.FUNC = 'G';
            S000_CALL_CIZUHIS();
            if (pgmRtn) return;
            CIRBAS.KEY.CI_NO = CICUHIS.KEY.CI_NO;
            CEP.TRC(SCCGWA, CICUHIS.KEY.CI_NO);
            T000_READ_CITBAS();
            if (pgmRtn) return;
        }
        if (WS_BAS_FLG == 'Y') {
            CICOKIDD.OUTPUT_TITLE.LAST_PAGE = 'Y';
            CICOKIDD.OUTPUT_TITLE.CI_FLG = '1';
            CICOKIDD.OUTPUT_TITLE.TOTAL_PAGE = 1;
            CICOKIDD.OUTPUT_TITLE.TOTAL_NUM = 1;
            CICOKIDD.OUTPUT_TITLE.CURR_PAGE = 1;
            R000_DATA_TRANS_TO_BRW();
            if (pgmRtn) return;
        }
    }
    public void B040_INQ_BY_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CEP.TRC(SCCGWA, CICKIDD.DATA.AC_NO);
        CIRACR.KEY.AGR_NO = CICKIDD.DATA.AC_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (WS_ACR_FLAG == 'Y') {
            CICOKIDD.OUTPUT_TITLE.LAST_PAGE = 'Y';
            CICKIDD.DATA.CI_NO = CIRACR.CI_NO;
            B030_INQ_BY_CI_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_INQ_BY_IDNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        WS_ID_TYPE = " ";
        WS_ID_NO = " ";
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_NO);
        WS_ID_TYPE = CICKIDD.DATA.ID_TYPE;
        WS_ID_NO = CICKIDD.DATA.ID_NO;
        B050_01_STARTBR_IDNO_PROC();
        if (pgmRtn) return;
        B020_01_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        T000_READNEXT_CITID_FIRST();
        if (pgmRtn) return;
        if (WS_ID_FLAG == 'N') {
            CICOKIDD.OUTPUT_TITLE.TOTAL_PAGE = 1;
            CICOKIDD.OUTPUT_TITLE.TOTAL_NUM = 1;
            CICOKIDD.OUTPUT_TITLE.CURR_PAGE = 1;
            CICOKIDD.OUTPUT_TITLE.LAST_PAGE = 'Y';
            CICOKIDD.OUTPUT_TITLE.CI_FLG = '5';
        } else {
            WS_TPID_TYPE = CIRID.KEY.ID_TYPE;
        }
        for (WS_I = 1; WS_I <= WS_PAGE_ROW 
            && WS_ID_FLAG != 'N'; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            CEP.TRC(SCCGWA, CIRID.KEY.CI_NO);
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRID.KEY.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (WS_BAS_FLG == 'Y' 
                && CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICUHIS);
                CICUHIS.PRI_CI = CIRBAS.KEY.CI_NO;
                CICUHIS.FUNC = 'G';
                S000_CALL_CIZUHIS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICUHIS.KEY.CI_NO);
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CICUHIS.KEY.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            CEP.TRC(SCCGWA, WS_CI_NO);
            if (WS_BAS_FLG == 'Y' 
                && !CIRBAS.KEY.CI_NO.equalsIgnoreCase(WS_CI_NO)) {
                B050_01_OUTPUT_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
        CICOKIDD.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_O;
        DATA = new CICOKIDD_DATA();
        CICOKIDD.DATA.add(DATA);
    }
    public void B060_CHECK_INFO_CI_PROC() throws IOException,SQLException,Exception {
        B060_CHECK_INFO_CI_READY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICKIDD.DATA.CI_NO;
        T000_READ_CITBAS_CI_NO();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'N') {
            WS_MSGID = CICMSG_ERROR_MSG.CI_BAS_INF_NOTEXIST;
            WS_ERR_INFO = CICKIDD.DATA.CI_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRBAS.STSW);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CICUHIS);
            CICUHIS.PRI_CI = CIRBAS.KEY.CI_NO;
            CICUHIS.FUNC = 'G';
            S000_CALL_CIZUHIS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICUHIS.KEY.CI_NO;
            CEP.TRC(SCCGWA, CICUHIS.KEY.CI_NO);
            T000_READ_CITBAS_CI_NO();
            if (pgmRtn) return;
        }
        if (CICKIDD.CTLWD.CHECK_NAME_FLG == 'Y' 
            && !CICKIDD.DATA.CI_NM.equalsIgnoreCase(CIRBAS.CI_NM)) {
            CEP.TRC(SCCGWA, "CHECK CI NAME");
            CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            CEP.TRC(SCCGWA, CICKIDD.CTLWD.CHECK_RETURN_FLG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CIRBAS.ID_TYPE);
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_NO);
        CEP.TRC(SCCGWA, CIRBAS.ID_NO);
        if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(K_ID_CARD)
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(K_ID_ICARD)) {
            if ((CIRBAS.ID_TYPE.equalsIgnoreCase(K_ID_CARD) 
                || CIRBAS.ID_TYPE.equalsIgnoreCase(K_ID_ICARD))) {
                CEP.TRC(SCCGWA, CICKIDD.DATA.ID_NO);
                CEP.TRC(SCCGWA, CIRBAS.ID_NO);
                if (CIRBAS.ID_NO.equalsIgnoreCase(CICKIDD.DATA.ID_NO)) {
                    CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                    CEP.TRC(SCCGWA, CICKIDD.CTLWD.CHECK_RETURN_FLG);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if (CICKIDD.DATA.ID_NO.substring(15 - 1, 15 + 1 - 1).trim().length() > 0 
                    && CICKIDD.DATA.ID_NO.substring(16 - 1, 16 + 3 - 1).trim().length() == 0) {
                    if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                    JIBS_tmp_int = CIRBAS.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                    if (CIRBAS.ID_NO.substring(16 - 1, 16 + 3 - 1).trim().length() > 0) {
                        if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                        JIBS_tmp_int = CIRBAS.ID_NO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                        if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                        JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                        WS_CHECK_OTHER_ID = CIRBAS.ID_NO.substring(0, 6) + WS_CHECK_OTHER_ID.substring(6);
                        if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                        JIBS_tmp_int = CIRBAS.ID_NO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                        if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                        JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                        WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 7 - 1) + CIRBAS.ID_NO.substring(9 - 1, 9 + 9 - 1) + WS_CHECK_OTHER_ID.substring(7 + 9 - 1);
                    } else {
                        WS_CHECK_OTHER_ID = CIRBAS.ID_NO;
                    }
                    if (CICKIDD.DATA.ID_NO.equalsIgnoreCase(WS_CHECK_OTHER_ID)) {
                        CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                    } else {
                        CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
                    }
                } else {
                    if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                    JIBS_tmp_int = CIRBAS.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                    if (CIRBAS.ID_NO.substring(16 - 1, 16 + 3 - 1).trim().length() > 0) {
                        WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO;
                    } else {
                        if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                        JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                        if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                        JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                        WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO.substring(0, 6) + WS_CHECK_OTHER_ID.substring(6);
                        if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                        JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                        if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                        JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                        for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                        WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 7 - 1) + CICKIDD.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1) + WS_CHECK_OTHER_ID.substring(7 + 9 - 1);
                    }
                    if (CIRBAS.ID_NO.equalsIgnoreCase(WS_CHECK_OTHER_ID)) {
                        CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                    } else {
                        CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
                    }
                }
            } else {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            }
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(K_ID_HKID)) {
            if (CIRBAS.ID_TYPE.equalsIgnoreCase(K_ID_HKID)) {
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if ((CICKIDD.DATA.ID_NO.substring(0, 3).equalsIgnoreCase("HKG") 
                    || CICKIDD.DATA.ID_NO.substring(0, 3).equalsIgnoreCase("MAC"))) {
                    if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                    JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                    if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                    JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                    WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO.substring(0, 1) + WS_CHECK_OTHER_ID.substring(1);
                    if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                    JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                    if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                    JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                    WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 2 - 1) + CICKIDD.DATA.ID_NO.substring(5 - 1, 5 + 8 - 1) + WS_CHECK_OTHER_ID.substring(2 + 8 - 1);
                    CEP.TRC(SCCGWA, WS_CHECK_OTHER_ID);
                } else {
                    WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO;
                }
                if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                JIBS_tmp_int = CIRBAS.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                if (WS_CHECK_OTHER_ID.substring(0, 9).equalsIgnoreCase(CIRBAS.ID_NO.substring(0, 9))) {
                    CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                } else {
                    CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
                }
            } else {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            }
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(K_ID_TWID)) {
            if (CIRBAS.ID_TYPE.equalsIgnoreCase(K_ID_TWID)) {
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if (CICKIDD.DATA.ID_NO.substring(0, 3).equalsIgnoreCase("TWN")) {
                    if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                    JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                    if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                    JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                    WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO.substring(0, 1) + WS_CHECK_OTHER_ID.substring(1);
                    if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                    JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                    if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                    JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                    WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 2 - 1) + CICKIDD.DATA.ID_NO.substring(5 - 1, 5 + 7 - 1) + WS_CHECK_OTHER_ID.substring(2 + 7 - 1);
                    CEP.TRC(SCCGWA, WS_CHECK_OTHER_ID);
                } else {
                    WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO;
                }
                if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                if (CIRBAS.ID_NO == null) CIRBAS.ID_NO = "";
                JIBS_tmp_int = CIRBAS.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CIRBAS.ID_NO += " ";
                if (WS_CHECK_OTHER_ID.substring(0, 8).equalsIgnoreCase(CIRBAS.ID_NO.substring(0, 8))) {
                    CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                } else {
                    CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
                }
            } else {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            }
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(K_ID_TYPE_ORG)) {
            if (!CIRBAS.ID_TYPE.equalsIgnoreCase(K_ID_TYPE_ORG)) {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
                CEP.TRC(SCCGWA, CICKIDD.CTLWD.CHECK_RETURN_FLG);
                Z_RET();
                if (pgmRtn) return;
            }
            if (CIRBAS.ID_NO.equalsIgnoreCase(CICKIDD.DATA.ID_NO)) {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
                CEP.TRC(SCCGWA, CICKIDD.CTLWD.CHECK_RETURN_FLG);
                Z_RET();
                if (pgmRtn) return;
            }
            if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
            JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
            if (!CICKIDD.DATA.ID_NO.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("-")) {
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                WS_CHECK_OTHER_ID = CICKIDD.DATA.ID_NO.substring(0, 8) + WS_CHECK_OTHER_ID.substring(8);
                if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 9 - 1) + "-" + WS_CHECK_OTHER_ID.substring(9 + 1 - 1);
                if (CICKIDD.DATA.ID_NO == null) CICKIDD.DATA.ID_NO = "";
                JIBS_tmp_int = CICKIDD.DATA.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) CICKIDD.DATA.ID_NO += " ";
                if (WS_CHECK_OTHER_ID == null) WS_CHECK_OTHER_ID = "";
                JIBS_tmp_int = WS_CHECK_OTHER_ID.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_OTHER_ID += " ";
                WS_CHECK_OTHER_ID = WS_CHECK_OTHER_ID.substring(0, 10 - 1) + CICKIDD.DATA.ID_NO.substring(9 - 1, 9 + 1 - 1) + WS_CHECK_OTHER_ID.substring(10 + 1 - 1);
            }
            CEP.TRC(SCCGWA, WS_CHECK_OTHER_ID);
            CEP.TRC(SCCGWA, CIRBAS.ID_NO);
            if (CIRBAS.ID_NO.equalsIgnoreCase(WS_CHECK_OTHER_ID)) {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
            } else {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            }
        } else {
            if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(CIRBAS.ID_TYPE) 
                && CICKIDD.DATA.ID_NO.equalsIgnoreCase(CIRBAS.ID_NO)) {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'Y';
            } else {
                CICKIDD.CTLWD.CHECK_RETURN_FLG = 'N';
            }
        }
        CEP.TRC(SCCGWA, CICKIDD.CTLWD.CHECK_RETURN_FLG);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B060_CHECK_INFO_CI_READY() throws IOException,SQLException,Exception {
        if (CICKIDD.DATA.CI_NO.trim().length() == 0) {
            if (CICKIDD.DATA.AC_NO.trim().length() == 0) {
                WS_MSGID = CICMSG_ERROR_MSG.CI_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, CIRACR);
                CEP.TRC(SCCGWA, CICKIDD.DATA.AC_NO);
                CIRACR.KEY.AGR_NO = CICKIDD.DATA.AC_NO;
                T000_READ_CITBAS_CI_NO();
                if (pgmRtn) return;
                if (WS_ACR_FLAG == 'N') {
                    WS_MSGID = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                    WS_ERR_INFO = CICKIDD.DATA.AC_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, CICKIDD.DATA.CI_NO);
    }
    public void B050_01_STARTBR_IDNO_PROC() throws IOException,SQLException,Exception {
        if (CICKIDD.DATA.ID_TYPE.trim().length() == 0
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10100")
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10200")
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10300")) {
            CEP.TRC(SCCGWA, "1ID");
            WS_COUNT_FLAG = 'N';
            CIRID.ID_NO = WS_ID_NO;
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
            JIBS_tmp_int = WS_DB_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
            WS_DB_ID_NO = WS_ID_NO.substring(0, 6) + WS_DB_ID_NO.substring(6);
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
            JIBS_tmp_int = WS_DB_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
            WS_DB_ID_NO = WS_DB_ID_NO.substring(0, 7 - 1) + WS_ID_NO.substring(9 - 1, 9 + 9 - 1) + WS_DB_ID_NO.substring(7 + 9 - 1);
            CEP.TRC(SCCGWA, WS_DB_ID_NO);
            T000_STARTBR_CITID_IDNO();
            if (pgmRtn) return;
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10703")) {
            WS_COUNT_FLAG = 'T';
            CIRID.KEY.ID_TYPE = WS_ID_TYPE;
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            CIRID.ID_NO = WS_ID_NO.substring(0, 8);
            T000_STARTBR_CITID_IDNO_TYPE();
            if (pgmRtn) return;
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10701")) {
            WS_COUNT_FLAG = 'T';
            CIRID.KEY.ID_TYPE = WS_ID_TYPE;
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            CIRID.ID_NO = WS_ID_NO.substring(0, 9);
            T000_STARTBR_CITID_IDNO_TYPE();
            if (pgmRtn) return;
        } else if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("20600")) {
            CEP.TRC(SCCGWA, "ORG");
            WS_COUNT_FLAG = 'N';
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_ID_NO.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("-")) {
                CIRID.ID_NO = WS_ID_NO;
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
                JIBS_tmp_int = WS_DB_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
                WS_DB_ID_NO = WS_ID_NO.substring(0, 8) + WS_DB_ID_NO.substring(8);
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
                JIBS_tmp_int = WS_DB_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
                WS_DB_ID_NO = WS_DB_ID_NO.substring(0, 9 - 1) + WS_ID_NO.substring(10 - 1, 10 + 1 - 1) + WS_DB_ID_NO.substring(9 + 1 - 1);
            } else {
                CIRID.ID_NO = WS_ID_NO;
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
                JIBS_tmp_int = WS_DB_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
                WS_DB_ID_NO = WS_ID_NO.substring(0, 8) + WS_DB_ID_NO.substring(8);
                if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
                JIBS_tmp_int = WS_DB_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
                WS_DB_ID_NO = WS_DB_ID_NO.substring(0, 9 - 1) + "-" + WS_DB_ID_NO.substring(9 + 1 - 1);
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_DB_ID_NO == null) WS_DB_ID_NO = "";
                JIBS_tmp_int = WS_DB_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_DB_ID_NO += " ";
                WS_DB_ID_NO = WS_DB_ID_NO.substring(0, 10 - 1) + WS_ID_NO.substring(9 - 1, 9 + 1 - 1) + WS_DB_ID_NO.substring(10 + 1 - 1);
            }
            CEP.TRC(SCCGWA, WS_DB_ID_NO);
            T000_STARTBR_CITID_IDNO();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "2ID");
            WS_COUNT_FLAG = 'T';
            CIRID.KEY.ID_TYPE = WS_ID_TYPE;
            CIRID.ID_NO = WS_ID_NO;
            T000_STARTBR_CITID_IDNO_TYPE();
            if (pgmRtn) return;
        }
    }
    public void B050_01_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICKIDD.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CIRID.KEY.ID_TYPE);
        CEP.TRC(SCCGWA, CIRBAS.ID_TYPE);
        if (CICKIDD.DATA.ID_TYPE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "A");
            R000_DATA_TRANS_TO_BRW();
            if (pgmRtn) return;
        } else {
            if (WS_TPID_TYPE.equalsIgnoreCase(K_ID_TYP) 
                || WS_TPID_TYPE.equalsIgnoreCase(K_TEMP_TYP) 
                || WS_TPID_TYPE.equalsIgnoreCase(K_REDB_TYP) 
                || WS_TPID_TYPE.equalsIgnoreCase(K_OTHER_TYP)) {
                CEP.TRC(SCCGWA, "B");
                R000_DATA_TRANS_TO_BRW();
                if (pgmRtn) return;
                CICOKIDD.OUTPUT_TITLE.TOTAL_NUM = WS_O;
            } else {
                if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase(WS_TPID_TYPE)) {
                    CEP.TRC(SCCGWA, "C");
                    R000_DATA_TRANS_TO_BRW();
                    if (pgmRtn) return;
                    CICOKIDD.OUTPUT_TITLE.TOTAL_NUM = WS_O;
                }
            }
        }
    }
    public void B020_01_STARTBR_ID_PROC() throws IOException,SQLException,Exception {
        if (CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10100")
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10200")
            || CICKIDD.DATA.ID_TYPE.equalsIgnoreCase("10300")) {
            CEP.TRC(SCCGWA, "1BAS");
