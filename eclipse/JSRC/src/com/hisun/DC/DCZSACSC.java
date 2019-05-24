package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSACSC {
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC993";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    short WS_TOT_NUM = 0;
    short WS_OUTPUT_NUM = 0;
    short WS_IDX = 0;
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCF993 DCCF993 = new DCCF993();
    CICCUST CICCUST = new CICCUST();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSACSC DCCSACSC;
    public void MP(SCCGWA SCCGWA, DCCSACSC DCCSACSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSACSC = DCCSACSC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSACSC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF993);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_READ_PROCESS();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSACSC.CI_NO);
        CEP.TRC(SCCGWA, DCCSACSC.ID_TYP);
        CEP.TRC(SCCGWA, DCCSACSC.ID_NO);
        CEP.TRC(SCCGWA, DCCSACSC.CI_NAME);
        CEP.TRC(SCCGWA, DCCSACSC.AC_TYP);
        CEP.TRC(SCCGWA, DCCSACSC.OPEN_CHNL);
        if (DCCSACSC.CI_NO.trim().length() == 0 
            && (DCCSACSC.ID_TYP.trim().length() == 0 
            || DCCSACSC.ID_NO.trim().length() == 0 
            || DCCSACSC.CI_NAME.trim().length() == 0)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ID_CINO_INPUT_ONE);
        }
        if (DCCSACSC.ID_TYP.trim().length() > 0 
            && DCCSACSC.ID_NO.trim().length() > 0 
            && DCCSACSC.CI_NAME.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'B';
            CICCUST.DATA.ID_TYPE = DCCSACSC.ID_TYP;
            CICCUST.DATA.ID_NO = DCCSACSC.ID_NO;
            CICCUST.DATA.CI_NM = DCCSACSC.CI_NAME;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
            if (DCCSACSC.CI_NO.trim().length() > 0 
                && !DCCSACSC.CI_NO.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NO)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ID_INF_ERR);
            }
        }
        if (DCCSACSC.AC_TYP != ' ' 
            && DCCSACSC.AC_TYP != '1' 
            && DCCSACSC.AC_TYP != '2' 
            && DCCSACSC.AC_TYP != '3') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYPE_INVALID);
        }
    }
    public void B020_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '7';
        if (DCCSACSC.CI_NO.trim().length() > 0) {
            CICQCIAC.DATA.CI_NO = DCCSACSC.CI_NO;
        } else {
            CICQCIAC.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        }
        WS_IDX = 1;
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        WS_CNT = 1;
        while (WS_IDX <= 100 
            && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO.trim().length() != 0) {
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
            DDCIQBAL.DATA.CCY = "156";
            DDCIQBAL.DATA.CCY_TYPE = '1';
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC_TYPE);
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP);
            if (DCCSACSC.AC_TYP == ' ' 
                && DCCSACSC.OPEN_CHNL.trim().length() == 0 
                && WS_OUTPUT_NUM <= 12) {
                CEP.TRC(SCCGWA, "00");
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '1' 
                    && DDCIQBAL.DATA.AC_TYPE == 'A') {
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = '1';
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'C';
                    CICQACAC.DATA.AGR_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    CICQACAC.DATA.CCY_ACAC = "156";
                    CICQACAC.DATA.CR_FLG = '1';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.DATA.ACAC_NO;
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
                    DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                    WS_OUTPUT_NUM += 1;
                } else {
                    if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '2' 
                        || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '4' 
                        || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '5') {
                        IBS.init(SCCGWA, DCRCDDAT);
                        DCRCDDAT.KEY.CARD_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        T000_READ_DCTCDDAT();
                        if (pgmRtn) return;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = DCRCDDAT.ACNO_TYPE;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = DCRCDDAT.ISSU_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
            }
            if (DCCSACSC.AC_TYP != ' ' 
                && DCCSACSC.OPEN_CHNL.trim().length() == 0) {
                CEP.TRC(SCCGWA, "10");
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '1' 
                    && DCCSACSC.AC_TYP == '1' 
                    && WS_OUTPUT_NUM <= 12 
                    && DDCIQBAL.DATA.AC_TYPE == 'A') {
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = '1';
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'C';
                    CICQACAC.DATA.AGR_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    CICQACAC.DATA.CCY_ACAC = "156";
                    CICQACAC.DATA.CR_FLG = '1';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.DATA.ACAC_NO;
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
                    DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                    WS_OUTPUT_NUM += 1;
                }
                if ((CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '2' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '4' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '5') 
                    && WS_OUTPUT_NUM <= 12) {
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    T000_READ_DCTCDDAT();
                    if (pgmRtn) return;
                    if (DCRCDDAT.ACNO_TYPE == DCCSACSC.AC_TYP) {
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = DCRCDDAT.ACNO_TYPE;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = DCRCDDAT.ISSU_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
            }
            if (DCCSACSC.AC_TYP == ' ' 
                && DCCSACSC.OPEN_CHNL.trim().length() > 0) {
                CEP.TRC(SCCGWA, "01");
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '1' 
                    && WS_OUTPUT_NUM <= 12 
                    && DDCIQBAL.DATA.AC_TYPE == 'A') {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'C';
                    CICQACAC.DATA.AGR_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    CICQACAC.DATA.CCY_ACAC = "156";
                    CICQACAC.DATA.CR_FLG = '1';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.DATA.ACAC_NO;
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    if (BPCPOCAC.DATA_INFO.CHNL_NO.equalsIgnoreCase(DCCSACSC.OPEN_CHNL)) {
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = '1';
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
                if ((CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '2' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '4' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '5') 
                    && WS_OUTPUT_NUM <= 12) {
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    T000_READ_DCTCDDAT();
                    if (pgmRtn) return;
                    if (DCRCDDAT.OPEN_CHNL.equalsIgnoreCase(DCCSACSC.OPEN_CHNL)) {
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = DCRCDDAT.ACNO_TYPE;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = DCRCDDAT.ISSU_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
            }
            if (DCCSACSC.AC_TYP != ' ' 
                && DCCSACSC.OPEN_CHNL.trim().length() > 0) {
                CEP.TRC(SCCGWA, "11");
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '1' 
                    && DCCSACSC.AC_TYP == '1' 
                    && WS_OUTPUT_NUM <= 12 
                    && DDCIQBAL.DATA.AC_TYPE == 'A') {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'C';
                    CICQACAC.DATA.AGR_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    CICQACAC.DATA.CCY_ACAC = "156";
                    CICQACAC.DATA.CR_FLG = '1';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.DATA.ACAC_NO;
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    if (BPCPOCAC.DATA_INFO.CHNL_NO.equalsIgnoreCase(DCCSACSC.OPEN_CHNL)) {
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = '1';
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
                if ((CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '2' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '4' 
                    || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_TYP == '5') 
                    && WS_OUTPUT_NUM <= 12) {
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                    T000_READ_DCTCDDAT();
                    if (pgmRtn) return;
                    if (DCRCDDAT.OPEN_CHNL.equalsIgnoreCase(DCCSACSC.OPEN_CHNL) 
                        && DCRCDDAT.ACNO_TYPE == DCCSACSC.AC_TYP) {
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_IDX-1].ENTY_NO;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_TYP = DCRCDDAT.ACNO_TYPE;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_DATE = DCRCDDAT.ISSU_DT;
                        DCCF993.OUT_INFO[WS_CNT-1].O_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
                        DCCF993.OUT_INFO[WS_CNT-1].O_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                        WS_OUTPUT_NUM += 1;
                    }
                }
            }
            WS_IDX += 1;
            WS_TOT_NUM += 1;
        }
        CEP.TRC(SCCGWA, WS_IDX);
        CEP.TRC(SCCGWA, WS_TOT_NUM);
        CEP.TRC(SCCGWA, WS_OUTPUT_NUM);
        CEP.TRC(SCCGWA, WS_CNT);
        DCCF993.O_AC_TOT_NUM = WS_OUTPUT_NUM;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF993;
        SCCFMT.DATA_LEN = 760;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
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
