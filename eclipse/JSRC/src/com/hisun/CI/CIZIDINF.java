package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZIDINF {
    boolean pgmRtn = false;
    String K_ADR_HOME = "114";
    String K_ADR_COMPANY = "115";
    String K_ADR_OFTEN = "112";
    String K_CNT_COMPANY = "12";
    String K_CNT_HOME = "11";
    String K_CNT_MOPH = "13";
    int K_STS_1 = 1;
    String K_OUTPUT_FMT_X = "CIX01";
    int K_MAX_ROW = 25;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_S = 0;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    int WS_CX = 0;
    int WS_SEQ = 0;
    String WS_STSW_FLG = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CICMCNT CICMCNT = new CICMCNT();
    CICMADR CICMADR = new CICMADR();
    CICOIDIN CICOIDIN = new CICOIDIN();
    BPCPRMR BPCPRMR = new BPCPRMR();
    int WS_COUNT_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICIDINF CICIDINF;
    public void MP(SCCGWA SCCGWA, CICIDINF CICIDINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICIDINF = CICIDINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZIDINF return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICOIDIN.OUTPUT_TITLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICIDINF.FUNC);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.ID_NO);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.CI_NM);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.PAGE_NUM);
        if (CICIDINF.FUNC == 'D') {
            B020_BROWSE_CITBAS();
            if (pgmRtn) return;
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERROR;
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERR;
            WS_ERR_INFO = "CIOT8200 FUNC(" + CICIDINF.FUNC + ")";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_CITBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICIDINF.INPUT_DATA.ID_NO);
        CIRBAS.ID_TYPE = CICIDINF.INPUT_DATA.ID_TYPE;
        CIRBAS.ID_NO = CICIDINF.INPUT_DATA.ID_NO;
        CEP.TRC(SCCGWA, CIRBAS.ID_TYPE);
        CEP.TRC(SCCGWA, CIRBAS.ID_NO);
        T000_STARTBR_CITBAS_IDNO_TYPE();
        if (pgmRtn) return;
        if (CICIDINF.INPUT_DATA.PAGE_ROW > K_MAX_ROW 
            || CICIDINF.INPUT_DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICIDINF.INPUT_DATA.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        B020_01_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        if (CICIDINF.INPUT_DATA.PAGE_NUM > 0) {
            WS_CX = ( CICIDINF.INPUT_DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        } else {
            WS_CX = 1;
        }
        WS_SEQ = WS_CX;
        CEP.TRC(SCCGWA, WS_CX);
        CEP.TRC(SCCGWA, WS_SEQ);
        T000_READNEXT_CITBAS_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICOIDIN.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICOIDIN.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
        CEP.TRC(SCCGWA, WS_I);
        for (WS_I = 1; WS_I <= WS_PAGE_ROW 
            && CICOIDIN.OUTPUT_TITLE.LAST_PAGE != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            CEP.TRC(SCCGWA, WS_I);
            R000_DATA_TRANS_TO_BRW();
            if (pgmRtn) return;
            B050_INQUIRE_CITCNT();
            if (pgmRtn) return;
            B060_INQUIRE_CIADR();
            if (pgmRtn) return;
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CICOIDIN.OUTPUT_TITLE.LAST_PAGE = 'Y';
            } else {
                CICOIDIN.OUTPUT_TITLE.LAST_PAGE = 'N';
            }
            CICOIDIN.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_I;
            DATA = new CICOIDIN_DATA();
            CICOIDIN.DATA.add(DATA);
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
        if (CICIDINF.INPUT_DATA.PAGE_NUM > 0) {
            CICOIDIN.OUTPUT_TITLE.CURR_PAGE = (short) CICIDINF.INPUT_DATA.PAGE_NUM;
        } else {
            CICOIDIN.OUTPUT_TITLE.CURR_PAGE = 1;
        }
    }
    public void B020_01_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        if (CICIDINF.INPUT_DATA.PAGE_NUM == 0) {
            CEP.TRC(SCCGWA, "1");
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            T000_COUNT_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "2");
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            CICOIDIN.OUTPUT_TITLE.TOTAL_NUM = WS_COUNT_NO;
            CEP.TRC(SCCGWA, "3");
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            CEP.TRC(SCCGWA, CICOIDIN.OUTPUT_TITLE.TOTAL_NUM);
            CEP.TRC(SCCGWA, CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            WS_RECORD_NUM = (short) (CICOIDIN.OUTPUT_TITLE.TOTAL_NUM % WS_PAGE_ROW);
            CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE = (short) ((CICOIDIN.OUTPUT_TITLE.TOTAL_NUM - WS_RECORD_NUM) / WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            CEP.TRC(SCCGWA, CICOIDIN.OUTPUT_TITLE.TOTAL_NUM);
            CEP.TRC(SCCGWA, CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE);
            if (WS_RECORD_NUM > 0) {
                CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE = (short) (CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE + 1);
            }
        }
        CEP.TRC(SCCGWA, "4");
        CEP.TRC(SCCGWA, CICOIDIN.OUTPUT_TITLE.TOTAL_PAGE);
    }
    public void B050_INQUIRE_CITCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        IBS.init(SCCGWA, CICMCNT);
        CICMCNT.CI_NO = CIRBAS.KEY.CI_NO;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = K_CNT_HOME;
        CICMCNT.FUNC = 'T';
        S000_LINK_CIZMCNT();
        if (pgmRtn) return;
        DATA.CNT_HOME = CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO;
        IBS.init(SCCGWA, CICMCNT);
        CICMCNT.CI_NO = CIRBAS.KEY.CI_NO;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = K_CNT_COMPANY;
        CICMCNT.FUNC = 'T';
        S000_LINK_CIZMCNT();
        if (pgmRtn) return;
        DATA.CNT_COMPANY = CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO;
        IBS.init(SCCGWA, CICMCNT);
        CICMCNT.CI_NO = CIRBAS.KEY.CI_NO;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = K_CNT_MOPH;
        CICMCNT.FUNC = 'T';
        S000_LINK_CIZMCNT();
        if (pgmRtn) return;
        DATA.CNT_MOPH = CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO;
        CEP.TRC(SCCGWA, "HOME");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).CNT_HOME);
        CEP.TRC(SCCGWA, "COMPANY");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).CNT_COMPANY);
        CEP.TRC(SCCGWA, "MOBILE PHONE");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).CNT_MOPH);
    }
    public void B060_INQUIRE_CIADR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMADR);
        CICMADR.CI_NO = CIRBAS.KEY.CI_NO;
        CICMADR.ADR_TYPE = K_ADR_HOME;
        CICMADR.FUNC = 'T';
        S000_LINK_CIZMADR();
        if (pgmRtn) return;
        DATA.ADR_HOME = CICMADR.ADR_NM;
        IBS.init(SCCGWA, CICMADR);
        CICMADR.CI_NO = CIRBAS.KEY.CI_NO;
        CICMADR.ADR_TYPE = K_ADR_COMPANY;
        CICMADR.FUNC = 'T';
        S000_LINK_CIZMADR();
        if (pgmRtn) return;
        DATA.ADR_COMPANY = CICMADR.ADR_NM;
        IBS.init(SCCGWA, CICMADR);
        CICMADR.CI_NO = CIRBAS.KEY.CI_NO;
        CICMADR.ADR_TYPE = K_ADR_OFTEN;
        CICMADR.FUNC = 'T';
        S000_LINK_CIZMADR();
        if (pgmRtn) return;
        DATA.ADR_OFTEN = CICMADR.ADR_NM;
        CEP.TRC(SCCGWA, CICMADR.ADR_NM);
        CEP.TRC(SCCGWA, "HOME");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).ADR_HOME);
        CEP.TRC(SCCGWA, "COMPANY");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).ADR_COMPANY);
        CEP.TRC(SCCGWA, "OFTEN");
        CEP.TRC(SCCGWA, CICOIDIN.DATA.get(WS_I-1).ADR_OFTEN);
    }
    public void R000_DATA_TRANS_TO_BRW() throws IOException,SQLException,Exception {
        DATA.ID_TYPE = CIRBAS.ID_TYPE;
        DATA.ID_NO = CIRBAS.ID_NO;
