package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCUST {
    int BAS_CI_NM_LEN;
    int NAM_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITCNT_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITACR_RD;
    brParm CITNAM_BR = new brParm();
    brParm CITID_BR = new brParm();
    DBParm CITLS1_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    short K_MAX_CASHE_COUNT = 2;
    CIZCUST_WS1 CIZCUST_WS1 = new CIZCUST_WS1();
    String WS_CI_NO = " ";
    short WS_I = 0;
    String WS_LST_STSW = " ";
    short WS_CASHE_COUNT = 0;
    short WS_CASHE_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRACR CIRACR = new CIRACR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRLS1 CIRLS1 = new CIRLS1();
    CICOCUB CICOCUB = new CICOCUB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCUST CICCUST;
    CICCASHE CICCASHE;
    public void MP(SCCGWA SCCGWA, CICCUST CICCUST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCUST = CICCUST;
        CEP.TRC(SCCGWA);
        IBS.CPY2CLS(SCCGWA, "001002003", CIZCUST_WS1);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCUST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CICCASHE = (CICCASHE) SCCGWA.COMM_AREA.PERFORMACE_PTR;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICCUST.FUNC == 'C') {
            B020_SEARCH_BY_CINO();
            if (pgmRtn) return;
        } else if (CICCUST.FUNC == 'B') {
            B030_SEARCH_BY_IDNM();
            if (pgmRtn) return;
        } else if (CICCUST.FUNC == 'A') {
            B050_SEARCH_BY_AC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        B070_WRITE_CICCASHE();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.FUNC);
        if (CICCUST.FUNC == 'U') {
            if (CICCUST.DATA.AGR_NO.trim().length() > 0) {
                CICCUST.FUNC = 'A';
            } else if (CICCUST.DATA.CI_NO.trim().length() > 0) {
                CICCUST.FUNC = 'C';
            } else if (CICCUST.DATA.ID_TYPE.trim().length() > 0 
                    && CICCUST.DATA.ID_NO.trim().length() > 0 
                    && CICCUST.DATA.CI_NM.trim().length() > 0) {
                CICCUST.FUNC = 'B';
            } else if (CICCUST.DATA.ID_TYPE.trim().length() > 0 
                    && CICCUST.DATA.ID_NO.trim().length() > 0) {
                CICCUST.FUNC = 'I';
            } else if (CICCUST.DATA.CI_NM.trim().length() > 0) {
                CICCUST.FUNC = 'N';
            } else {
                CEP.TRC(SCCGWA, "LACK OF INPUT INFORMATION");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICCUST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, CICCUST.FUNC);
        }
    }
    public void B020_SEARCH_BY_CINO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        if (CICCUST.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_CASHE_I < CICCASHE.COUNT) {
            WS_CASHE_I = (short) (WS_CASHE_I + 1);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].CI_NO);
            if (CICCUST.DATA.CI_NO.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].CI_NO)) {
                CEP.TRC(SCCGWA, "READ CASHE");
                R000_TRANS_CASHE_DATA_TO_OUTPUT();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCUST.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        R000_INQUIRE_CILST();
        if (pgmRtn) return;
    }
    public void B030_SEARCH_BY_IDNM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NM);
        if (CICCUST.DATA.ID_TYPE.trim().length() == 0 
            || CICCUST.DATA.ID_NO.trim().length() == 0 
            || CICCUST.DATA.CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_CASHE_I < CICCASHE.COUNT) {
            WS_CASHE_I = (short) (WS_CASHE_I + 1);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].ID_TYPE);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].ID_NO);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].CI_NM);
            if (CICCUST.DATA.ID_TYPE.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].ID_TYPE) 
                && CICCUST.DATA.ID_NO.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].ID_NO) 
                && CICCUST.DATA.CI_NM.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].CI_NM)) {
                CEP.TRC(SCCGWA, "READ CASHE");
                R000_TRANS_CASHE_DATA_TO_OUTPUT();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NM);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICCUST.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICCUST.DATA.ID_NO;
        CIRBAS.CI_NM = CICCUST.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        R000_INQUIRE_CILST();
        if (pgmRtn) return;
    }
    public void B040_SEARCH_BY_ID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_NO);
        if (CICCUST.DATA.ID_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRID);
        CIRID.ID_NO = CICCUST.DATA.ID_NO;
        T000_STARTBR_CITID_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
        CEP.TRC(SCCGWA, CICCUST.DATA.AGR_NO);
        if (CICCUST.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_CASHE_I < CICCASHE.COUNT) {
            WS_CASHE_I = (short) (WS_CASHE_I + 1);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].AGR_NO);
            if (CICCUST.DATA.AGR_NO.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "READ CASHE");
                R000_TRANS_CASHE_DATA_TO_OUTPUT();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCUST.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_CASHE_I < CICCASHE.COUNT) {
            WS_CASHE_I = (short) (WS_CASHE_I + 1);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_I-1].CI_NO);
            if (CIRACR.CI_NO.equalsIgnoreCase(CICCASHE.DATA[WS_CASHE_I-1].CI_NO)) {
                CEP.TRC(SCCGWA, "READ CASHE");
                R000_TRANS_CASHE_DATA_TO_OUTPUT();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        R000_INQUIRE_CILST();
        if (pgmRtn) return;
    }
    public void B060_SEARCH_BY_CINM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NM);
        if (CICCUST.DATA.CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.CI_NM = CICCUST.DATA.CI_NM;
        NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
        T000_STARTBR_CITNAM_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOTFOUND_BY_CI_NAM, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_CI_NO = CIRNAM.KEY.CI_NO;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITNAM();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITNAM();
        if (pgmRtn) return;
    }
    public void B070_WRITE_CICCASHE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCASHE.COUNT);
        if (CICCASHE.COUNT < K_MAX_CASHE_COUNT) {
            CICCASHE.COUNT = (short) (CICCASHE.COUNT + 1);
            CEP.TRC(SCCGWA, CICCASHE.COUNT);
            WS_CASHE_COUNT = CICCASHE.COUNT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CI_NO = CICCUST.O_DATA.O_CI_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].AGR_NO = CICCUST.DATA.AGR_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            CICCASHE.DATA[WS_CASHE_COUNT-1].ID_NO = CICCUST.O_DATA.O_ID_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CI_NM = CICCUST.O_DATA.O_CI_NM;
            CICCASHE.DATA[WS_CASHE_COUNT-1].EXP_DT = CICCUST.O_DATA.O_EXP_DT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CI_ENM = CICCUST.O_DATA.O_CI_ENM;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CI_TYP = CICCUST.O_DATA.O_CI_TYP;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CI_ATTR = CICCUST.O_DATA.O_CI_ATTR;
            CICCASHE.DATA[WS_CASHE_COUNT-1].STSW = CICCUST.O_DATA.O_STSW;
            CICCASHE.DATA[WS_CASHE_COUNT-1].OPEN_DT = CICCUST.O_DATA.O_OPEN_DT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].OWNER_BK = CICCUST.O_DATA.O_OWNER_BK;
            CICCASHE.DATA[WS_CASHE_COUNT-1].CLOSE_DT = CICCUST.O_DATA.O_CLOSE_DT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].OPEN_CHNL = CICCUST.O_DATA.O_OPEN_CHNL;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SUB_TYP = CICCUST.O_DATA.O_SUB_TYP;
            CICCASHE.DATA[WS_CASHE_COUNT-1].ORG_TYPE = CICCUST.O_DATA.O_ORG_TYPE;
            CICCASHE.DATA[WS_CASHE_COUNT-1].FIN_TYPE = CICCUST.O_DATA.O_FIN_TYPE;
            CICCASHE.DATA[WS_CASHE_COUNT-1].FTA_FLG = CICCUST.O_DATA.O_FTA_FLG;
            CICCASHE.DATA[WS_CASHE_COUNT-1].EP_FLG = CICCUST.O_DATA.O_EP_FLG;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SVR_LVL = CICCUST.O_DATA.O_SVR_LVL;
            CICCASHE.DATA[WS_CASHE_COUNT-1].TAX_BANK = CICCUST.O_DATA.O_TAX_BANK;
            CICCASHE.DATA[WS_CASHE_COUNT-1].TAX_AC_NO = CICCUST.O_DATA.O_TAX_AC_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].TAX_TYPE = CICCUST.O_DATA.O_TAX_TYPE;
            CICCASHE.DATA[WS_CASHE_COUNT-1].TAX_DIST_NO = CICCUST.O_DATA.O_TAX_DIST_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].RESIDENT = CICCUST.O_DATA.O_RESIDENT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SEX = CICCUST.O_DATA.O_SEX;
            CICCASHE.DATA[WS_CASHE_COUNT-1].BIRTH_DT = CICCUST.O_DATA.O_BIRTH_DT;
            CICCASHE.DATA[WS_CASHE_COUNT-1].NATION = CICCUST.O_DATA.O_NATION;
            CICCASHE.DATA[WS_CASHE_COUNT-1].REG_CNTY = CICCUST.O_DATA.O_REG_CNTY;
            CICCASHE.DATA[WS_CASHE_COUNT-1].INDUS1 = CICCUST.O_DATA.O_INDUS1;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SID_FLG = CICCUST.O_DATA.O_SID_FLG;
            CICCASHE.DATA[WS_CASHE_COUNT-1].TEL_NO = CICCUST.O_DATA.O_TEL_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].PB_AP_NO = CICCUST.O_DATA.O_PB_AP_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].OIC_NO = CICCUST.O_DATA.O_OIC_NO;
            CICCASHE.DATA[WS_CASHE_COUNT-1].RESP_CD = CICCUST.O_DATA.O_RESP_CD;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SUB_DP = CICCUST.O_DATA.O_SUB_DP;
            CICCASHE.DATA[WS_CASHE_COUNT-1].HOLD_LVL = CICCUST.O_DATA.O_HOLD_LVL;
            CICCASHE.DATA[WS_CASHE_COUNT-1].IDE_STSW = CICCUST.O_DATA.O_IDE_STSW;
            CICCASHE.DATA[WS_CASHE_COUNT-1].NRA_TAX_TYP = CICCUST.O_DATA.O_NRA_TAX_TYP;
            CICCASHE.DATA[WS_CASHE_COUNT-1].COL_IND_FLG = CICCUST.O_DATA.O_COL_IND_FLG;
            CICCASHE.DATA[WS_CASHE_COUNT-1].SIZE = CICCUST.O_DATA.O_SIZE;
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_COUNT-1].CI_NO);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_COUNT-1].AGR_NO);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_COUNT-1].ID_TYPE);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_COUNT-1].ID_NO);
            CEP.TRC(SCCGWA, CICCASHE.DATA[WS_CASHE_COUNT-1].CI_NM);
            CEP.TRC(SCCGWA, "WRITE CASH");
        }
    }
    public void R000_TRANS_CASHE_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT CASHE DATA");
        CICCUST.O_DATA.O_CI_NO = CICCASHE.DATA[WS_CASHE_I-1].CI_NO;
        CICCUST.O_DATA.O_ID_TYPE = CICCASHE.DATA[WS_CASHE_I-1].ID_TYPE;
        CICCUST.O_DATA.O_ID_NO = CICCASHE.DATA[WS_CASHE_I-1].ID_NO;
        CICCUST.O_DATA.O_CI_NM = CICCASHE.DATA[WS_CASHE_I-1].CI_NM;
        CICCUST.O_DATA.O_EXP_DT = CICCASHE.DATA[WS_CASHE_I-1].EXP_DT;
        CICCUST.O_DATA.O_CI_ENM = CICCASHE.DATA[WS_CASHE_I-1].CI_ENM;
        CICCUST.O_DATA.O_CI_TYP = CICCASHE.DATA[WS_CASHE_I-1].CI_TYP;
        CICCUST.O_DATA.O_CI_ATTR = CICCASHE.DATA[WS_CASHE_I-1].CI_ATTR;
        CICCUST.O_DATA.O_STSW = CICCASHE.DATA[WS_CASHE_I-1].STSW;
        CICCUST.O_DATA.O_OPEN_DT = CICCASHE.DATA[WS_CASHE_I-1].OPEN_DT;
        CICCUST.O_DATA.O_OWNER_BK = CICCASHE.DATA[WS_CASHE_I-1].OWNER_BK;
        CICCUST.O_DATA.O_CLOSE_DT = CICCASHE.DATA[WS_CASHE_I-1].CLOSE_DT;
        CICCUST.O_DATA.O_OPEN_CHNL = CICCASHE.DATA[WS_CASHE_I-1].OPEN_CHNL;
        CICCUST.O_DATA.O_SUB_TYP = CICCASHE.DATA[WS_CASHE_I-1].SUB_TYP;
        CICCUST.O_DATA.O_ORG_TYPE = CICCASHE.DATA[WS_CASHE_I-1].ORG_TYPE;
        CICCUST.O_DATA.O_FIN_TYPE = CICCASHE.DATA[WS_CASHE_I-1].FIN_TYPE;
        CICCUST.O_DATA.O_FTA_FLG = CICCASHE.DATA[WS_CASHE_I-1].FTA_FLG;
        CICCUST.O_DATA.O_EP_FLG = CICCASHE.DATA[WS_CASHE_I-1].EP_FLG;
        CICCUST.O_DATA.O_SVR_LVL = CICCASHE.DATA[WS_CASHE_I-1].SVR_LVL;
        CICCUST.O_DATA.O_TAX_BANK = CICCASHE.DATA[WS_CASHE_I-1].TAX_BANK;
        CICCUST.O_DATA.O_TAX_AC_NO = CICCASHE.DATA[WS_CASHE_I-1].TAX_AC_NO;
        CICCUST.O_DATA.O_TAX_TYPE = CICCASHE.DATA[WS_CASHE_I-1].TAX_TYPE;
        CICCUST.O_DATA.O_TAX_DIST_NO = CICCASHE.DATA[WS_CASHE_I-1].TAX_DIST_NO;
        CICCUST.O_DATA.O_RESIDENT = CICCASHE.DATA[WS_CASHE_I-1].RESIDENT;
        CICCUST.O_DATA.O_SEX = CICCASHE.DATA[WS_CASHE_I-1].SEX;
        CICCUST.O_DATA.O_BIRTH_DT = CICCASHE.DATA[WS_CASHE_I-1].BIRTH_DT;
        CICCUST.O_DATA.O_NATION = CICCASHE.DATA[WS_CASHE_I-1].NATION;
        CICCUST.O_DATA.O_REG_CNTY = CICCASHE.DATA[WS_CASHE_I-1].REG_CNTY;
        CICCUST.O_DATA.O_INDUS1 = CICCASHE.DATA[WS_CASHE_I-1].INDUS1;
        CICCUST.O_DATA.O_SID_FLG = CICCASHE.DATA[WS_CASHE_I-1].SID_FLG;
        CICCUST.O_DATA.O_TEL_NO = CICCASHE.DATA[WS_CASHE_I-1].TEL_NO;
        CICCUST.O_DATA.O_PB_AP_NO = CICCASHE.DATA[WS_CASHE_I-1].PB_AP_NO;
        CICCUST.O_DATA.O_OIC_NO = CICCASHE.DATA[WS_CASHE_I-1].OIC_NO;
        CICCUST.O_DATA.O_RESP_CD = CICCASHE.DATA[WS_CASHE_I-1].RESP_CD;
        CICCUST.O_DATA.O_SUB_DP = CICCASHE.DATA[WS_CASHE_I-1].SUB_DP;
        CICCUST.O_DATA.O_HOLD_LVL = CICCASHE.DATA[WS_CASHE_I-1].HOLD_LVL;
        CICCUST.O_DATA.O_IDE_STSW = CICCASHE.DATA[WS_CASHE_I-1].IDE_STSW;
        CICCUST.O_DATA.O_NRA_TAX_TYP = CICCASHE.DATA[WS_CASHE_I-1].NRA_TAX_TYP;
        CICCUST.O_DATA.O_COL_IND_FLG = CICCASHE.DATA[WS_CASHE_I-1].COL_IND_FLG;
        CICCUST.O_DATA.O_SIZE = CICCASHE.DATA[WS_CASHE_I-1].SIZE;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICCUST.O_DATA.O_CI_NO = CIRBAS.KEY.CI_NO;
        CICCUST.O_DATA.O_ID_TYPE = CIRBAS.ID_TYPE;
        CICCUST.O_DATA.O_ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICCUST.O_DATA.O_CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICCUST.O_DATA.O_CI_TYP = CIRBAS.CI_TYP;
        CICCUST.O_DATA.O_CI_ATTR = CIRBAS.CI_ATTR;
        CICCUST.O_DATA.O_STSW = CIRBAS.STSW;
        CICCUST.O_DATA.O_OPEN_DT = CIRBAS.OPEN_DT;
        CICCUST.O_DATA.O_OWNER_BK = CIRBAS.OWNER_BK;
        CICCUST.O_DATA.O_CLOSE_DT = CIRBAS.CLOSE_DT;
        CICCUST.O_DATA.O_OPEN_CHNL = CIRBAS.OPEN_CHNL;
        CICCUST.O_DATA.O_FTA_FLG = CIRBAS.FTA_FLG;
        CICCUST.O_DATA.O_SVR_LVL = CIRBAS.SVR_LVL;
        CICCUST.O_DATA.O_TAX_BANK = CIRBAS.TAX_BANK;
        CICCUST.O_DATA.O_TAX_AC_NO = CIRBAS.TAX_AC_NO;
        CICCUST.O_DATA.O_TAX_TYPE = CIRBAS.TAX_TYPE;
        CICCUST.O_DATA.O_OIC_NO = CIRBAS.OIC_NO;
        CICCUST.O_DATA.O_RESP_CD = CIRBAS.RESP_CD;
        CICCUST.O_DATA.O_SUB_DP = CIRBAS.SUB_DP;
        CICCUST.O_DATA.O_IDE_STSW = CIRBAS.IDE_STSW;
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        if (CIRBAS.IDE_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CICCUST.O_DATA.O_EP_FLG = 'Y';
        } else {
            CICCUST.O_DATA.O_EP_FLG = 'N';
        }
        CICCUST.O_DATA.O_NRA_TAX_TYP = CIRBAS.NRA_TAX_TYP;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_ATTR == '1') {
                R000_TRANS_PDM_DATA();
                if (pgmRtn) return;
                R000_TRANS_CNT_DATA();
                if (pgmRtn) return;
            }
        } else if (CIRBAS.CI_TYP == '2') {
            if (CIRBAS.CI_ATTR == '1') {
                R000_TRANS_CDM_DATA();
                if (pgmRtn) return;
            }
        } else if (CIRBAS.CI_TYP == '3') {
            if (CIRBAS.CI_ATTR == '1' 
                || CIRBAS.CI_ATTR == '6') {
                R000_TRANS_FDM_DATA();
                if (pgmRtn) return;
            }
        } else {
        }
        CEP.TRC(SCCGWA, CIRBAS.ORGIN_TP);
        CEP.TRC(SCCGWA, CIRBAS.ORIGIN);
        if (CIRBAS.ORGIN_TP == '1' 
            || CIRBAS.ORGIN_TP == '2' 
            || CIRBAS.ORGIN_TP == '3' 
            || CIRBAS.ORGIN_TP == '4') {
            CEP.TRC(SCCGWA, "COL-IND-FLG YES");
            CICCUST.O_DATA.O_COL_IND_FLG = 'Y';
        } else {
            CICCUST.O_DATA.O_COL_IND_FLG = 'N';
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRNAM.CI_NM == null) CIRNAM.CI_NM = "";
            JIBS_tmp_int = CIRNAM.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRNAM.CI_NM += " ";
            CICCUST.O_DATA.O_CI_ENM = CIRNAM.CI_NM.substring(0, NAM_CI_NM_LEN);
        }
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICCUST.O_DATA.O_EXP_DT = CIRID.EXP_DT;
    }
    public void R000_TRANS_PDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRPDM);
        CIRPDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITPDM();
        if (pgmRtn) return;
        CICCUST.O_DATA.O_SUB_TYP = CIRPDM.CI_SUB_TYP;
        CICCUST.O_DATA.O_RESIDENT = CIRPDM.RESIDENT;
        CICCUST.O_DATA.O_SEX = CIRPDM.SEX;
        CICCUST.O_DATA.O_BIRTH_DT = CIRPDM.BIRTH_DT;
        CICCUST.O_DATA.O_NATION = CIRPDM.NATION;
        CICCUST.O_DATA.O_REG_CNTY = CIRPDM.REG_CNTY;
        CICCUST.O_DATA.O_SID_FLG = CIRPDM.SID_FLG;
    }
    public void R000_TRANS_CDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITCDM();
        if (pgmRtn) return;
        CICCUST.O_DATA.O_SUB_TYP = CIRCDM.CI_SUB_TYP;
        CICCUST.O_DATA.O_ORG_TYPE = CIRCDM.ORG_TYPE;
        CICCUST.O_DATA.O_RESIDENT = CIRCDM.RESIDENT;
        CICCUST.O_DATA.O_INDUS1 = CIRCDM.INDUS1;
        CICCUST.O_DATA.O_SID_FLG = CIRCDM.SID_FLG;
        CICCUST.O_DATA.O_PB_AP_NO = CIRCDM.PB_AP_NO;
        CICCUST.O_DATA.O_SIZE = CIRCDM.COMP_SIZE;
    }
    public void R000_TRANS_FDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITFDM();
        if (pgmRtn) return;
        CICCUST.O_DATA.O_SUB_TYP = CIRFDM.CI_SUB_TYP;
        CICCUST.O_DATA.O_ORG_TYPE = CIRFDM.ORG_TYPE;
        CICCUST.O_DATA.O_FIN_TYPE = CIRFDM.FIN_TYPE;
        CICCUST.O_DATA.O_RESIDENT = CIRFDM.RESIDENT;
        CICCUST.O_DATA.O_INDUS1 = CIRFDM.INDUS1;
        CICCUST.O_DATA.O_SID_FLG = CIRFDM.SID_FLG;
        CICCUST.O_DATA.O_PB_AP_NO = CIRFDM.PB_AP_NO;
        CICCUST.O_DATA.O_SIZE = CIRFDM.COMP_SIZE;
    }
    public void R000_TRANS_CNT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCNT.KEY.CNT_TYPE = "21";
        T000_READ_CITCNT();
        if (pgmRtn) return;
        CICCUST.O_DATA.O_TEL_NO = CIRCNT.TEL_NO;
    }
    public void R000_INQUIRE_CILST() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            IBS.init(SCCGWA, CIRLS1);
            CIRLS1.KEY.ENTY_NO = CIRBAS.KEY.CI_NO;
            CIRLS1.KEY.ENTY_TYP = '0';
            CIRLS1.KEY.LST_CD = CIZCUST_WS1.K_LST_CD[WS_I-1];
            T000_READ_CITLS1();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_LST_STSW == null) WS_LST_STSW = "";
                JIBS_tmp_int = WS_LST_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) WS_LST_STSW += " ";
                WS_LST_STSW = WS_LST_STSW.substring(0, WS_I - 1) + "1" + WS_LST_STSW.substring(WS_I + 1 - 1);
            } else {
                if (WS_LST_STSW == null) WS_LST_STSW = "";
                JIBS_tmp_int = WS_LST_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) WS_LST_STSW += " ";
                WS_LST_STSW = WS_LST_STSW.substring(0, WS_I - 1) + "0" + WS_LST_STSW.substring(WS_I + 1 - 1);
            }
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (WS_LST_STSW == null) WS_LST_STSW = "";
        JIBS_tmp_int = WS_LST_STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_LST_STSW += " ";
        CICCUST.O_DATA.O_STSW = CICCUST.O_DATA.O_STSW.substring(0, 41 - 1) + WS_LST_STSW + CICCUST.O_DATA.O_STSW.substring(41 + 3 - 1);
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CI_NO);
        IBS.init(SCCGWA, CICOCUB);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOCUB.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOCUB.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOCUB.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICOCUB.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICOCUB.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOCUB.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOCUB.DATA.STSW = CIRBAS.STSW;
        CICOCUB.DATA.OPEN_DT = CIRBAS.OPEN_DT;
        CICOCUB.DATA.OWNER_BK = CIRBAS.OWNER_BK;
        CICOCUB.DATA.CLOSE_DT = CIRBAS.CLOSE_DT;
        CICOCUB.DATA.OPEN_CHNL = CIRBAS.OPEN_CHNL;
        CICOCUB.DATA.FTA_FLG = CIRBAS.FTA_FLG;
        CICOCUB.DATA.SVR_LVL = CIRBAS.SVR_LVL;
        CICOCUB.DATA.TAX_BANK = CIRBAS.TAX_BANK;
        CICOCUB.DATA.TAX_AC_NO = CIRBAS.TAX_AC_NO;
        CICOCUB.DATA.TAX_TYPE = CIRBAS.TAX_TYPE;
        CICOCUB.DATA.OIC_NO = CIRBAS.OIC_NO;
        CICOCUB.DATA.RESP_CD = CIRBAS.RESP_CD;
        CICOCUB.DATA.SUB_DP = CIRBAS.SUB_DP;
        if (CIRBAS.CI_TYP == '1') {
            R000_02_OUT_BRW_PDM_DATA();
            if (pgmRtn) return;
            R000_02_OUT_BRW_CNT_DATA();
            if (pgmRtn) return;
        } else if (CIRBAS.CI_TYP == '2') {
            R000_02_OUT_BRW_CDM_DATA();
            if (pgmRtn) return;
        } else if (CIRBAS.CI_TYP == '3') {
            R000_02_OUT_BRW_FDM_DATA();
            if (pgmRtn) return;
        } else {
        }
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRNAM.CI_NM == null) CIRNAM.CI_NM = "";
            JIBS_tmp_int = CIRNAM.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRNAM.CI_NM += " ";
            CICOCUB.DATA.CI_ENM = CIRNAM.CI_NM.substring(0, NAM_CI_NM_LEN);
        }
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICOCUB.DATA.EXP_DT = CIRID.EXP_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOCUB);
        SCCMPAG.DATA_LEN = 1078;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_PDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRPDM);
        CIRPDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITPDM();
        if (pgmRtn) return;
        CICOCUB.DATA.SUB_TYP = CIRPDM.CI_SUB_TYP;
        CICOCUB.DATA.RESIDENT = CIRPDM.RESIDENT;
        CICOCUB.DATA.SEX = CIRPDM.SEX;
        CICOCUB.DATA.BIRTH_DT = CIRPDM.BIRTH_DT;
        CICOCUB.DATA.NATION = CIRPDM.NATION;
        CICOCUB.DATA.REG_CNTY = CIRPDM.REG_CNTY;
        CICOCUB.DATA.SID_FLG = CIRPDM.SID_FLG;
    }
    public void R000_02_OUT_BRW_CDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITCDM();
        if (pgmRtn) return;
        CICOCUB.DATA.SUB_TYP = CIRCDM.CI_SUB_TYP;
        CICOCUB.DATA.ORG_TYPE = CIRCDM.ORG_TYPE;
        CICOCUB.DATA.RESIDENT = CIRCDM.RESIDENT;
        CICOCUB.DATA.INDUS1 = CIRCDM.INDUS1;
        CICOCUB.DATA.SID_FLG = CIRCDM.SID_FLG;
        CICOCUB.DATA.PB_AP_NO = CIRCDM.PB_AP_NO;
    }
    public void R000_02_OUT_BRW_FDM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITFDM();
        if (pgmRtn) return;
        CICOCUB.DATA.SUB_TYP = CIRFDM.CI_SUB_TYP;
        CICOCUB.DATA.ORG_TYPE = CIRFDM.ORG_TYPE;
        CICOCUB.DATA.FIN_TYPE = CIRFDM.FIN_TYPE;
        CICOCUB.DATA.RESIDENT = CIRFDM.RESIDENT;
        CICOCUB.DATA.INDUS1 = CIRFDM.INDUS1;
        CICOCUB.DATA.SID_FLG = CIRFDM.SID_FLG;
        CICOCUB.DATA.PB_AP_NO = CIRFDM.PB_AP_NO;
    }
    public void R000_02_OUT_BRW_CNT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCNT.KEY.CNT_TYPE = "15";
        T000_READ_CITCNT();
        if (pgmRtn) return;
        CICOCUB.DATA.TEL_NO = CIRCNT.CNT_INFO;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI BAS INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE, ID_NO, CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI PDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PDM_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITPDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI CDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CDM_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI FDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FDM_NOTFND, CICCUST.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITFDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CI_NO, CNT_TYPE";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICCUST.RC);
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
    }
    public void T000_STARTBR_CITNAM_NM() throws IOException,SQLException,Exception {
        CITNAM_BR.rp = new DBParm();
        CITNAM_BR.rp.TableName = "CITNAM";
        CITNAM_BR.rp.eqWhere = "CI_NM";
        IBS.STARTBR(SCCGWA, CIRNAM, CITNAM_BR);
    }
    public void T000_READNEXT_CITNAM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRNAM, this, CITNAM_BR);
    }
    public void T000_ENDBR_CITNAM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITNAM_BR);
    }
    public void T000_STARTBR_CITID_ID() throws IOException,SQLException,Exception {
        CITID_BR.rp = new DBParm();
        CITID_BR.rp.TableName = "CITID";
        CITID_BR.rp.eqWhere = "ID_NO";
        IBS.STARTBR(SCCGWA, CIRID, CITID_BR);
    }
    public void T000_READNEXT_CITID() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRID, this, CITID_BR);
    }
    public void T000_ENDBR_CITID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITID_BR);
    }
    public void T000_READ_CITLS1() throws IOException,SQLException,Exception {
        CITLS1_RD = new DBParm();
        CITLS1_RD.TableName = "CITLS1";
        IBS.READ(SCCGWA, CIRLS1, CITLS1_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
