package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSQHLD {
    DBParm DDTHLD_RD;
    brParm DDTHLD_BR = new brParm();
    brParm DDTCCY_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm DDTHLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC753";
    String K_OUTPUT_FMT_A = "DC752";
    int K_SCR_ROW_NO = 8;
    String WS_ERR_MSG = " ";
    String WS_SUB_AC = " ";
    String WS_TR_AC = " ";
    String WS_CARD_VIA_AC = " ";
    double WS_DDBAL = 0;
    String WS_AC_CNM = " ";
    String WS_AC_ENM = " ";
    short WS_CNT = 0;
    short WS_TOTAL_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    String WS_CARD_NO = " ";
    char WS_HLD_FLG_1 = ' ';
    char WS_DDTCCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    DDRCCY DDRCCY = new DDRCCY();
    DCCOQHLD DCCOQHLD = new DCCOQHLD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDCF7520 DDCF7520 = new DDCF7520();
    TDCACE TDCACE = new TDCACE();
    DDRHLDR DDRHLDR = new DDRHLDR();
    CICQACRL CICQACRL = new CICQACRL();
    int WS_STR_DT = 0;
    int WS_END_DT2 = 0;
    char WS_TYPE1 = ' ';
    char WS_TYPE2 = ' ';
    char WS_STS1 = ' ';
    char WS_STS2 = ' ';
    char WS_TSK_STS1 = ' ';
    char WS_TSK_STS2 = ' ';
    int WS_COUNT = 0;
    int WS_RCD_SEQ = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCSQHLD DCCSQHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSQHLD DCCSQHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQHLD = DCCSQHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQHLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, DDCF7520);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DCCSQHLD.FUNC);
        if (DCCSQHLD.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSQHLD.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSQHLD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSQHLD.HLD_NO;
        T000_READ_DCTHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        B010_10_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_10_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOQHLD);
        DCCOQHLD.DATA.HLD_NO = DDRHLD.KEY.HLD_NO;
        DCCOQHLD.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DCCOQHLD.DATA.HLD_TYP = DDRHLD.HLD_TYP;
        DCCOQHLD.DATA.SPR_TYP = DDRHLD.SPR_BR_TYP;
        DCCOQHLD.DATA.HLD_STS = DDRHLD.HLD_STS;
        DCCOQHLD.DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DCCOQHLD.DATA.CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        DCCOQHLD.DATA.HLD_AMT = DDRHLD.HLD_AMT;
        DCCOQHLD.DATA.HLD_FLG = DDRHLD.HLD_FLG;
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.HLD_FLG);
        DCCOQHLD.DATA.SPR_NO = DDRHLD.HLD_WRIT_NO;
        DCCOQHLD.DATA.SPR_NM = DDRHLD.HLD_BR_NM;
        DCCOQHLD.DATA.HLD_SEQ = DDRHLD.HLD_SEQ;
        DCCOQHLD.DATA.EFF_DT = DDRHLD.EFF_DATE;
        DCCOQHLD.DATA.EXP_DT = DDRHLD.EXP_DATE;
        DCCOQHLD.DATA.RSN = DDRHLD.HLD_RSN;
        DCCOQHLD.DATA.RMK = DDRHLD.REMARK;
        DCCOQHLD.DATA.HLD_BR = DDRHLD.SPR_BR;
        DCCOQHLD.DATA.LAW_NM1 = DDRHLD.LAW_OFF_NM1;
        DCCOQHLD.DATA.LAW_NO1 = DDRHLD.LAW_ID_NO1;
        DCCOQHLD.DATA.LAW_NM2 = DDRHLD.LAW_OFF_NM2;
        DCCOQHLD.DATA.LAW_NO2 = DDRHLD.LAW_ID_NO2;
        DCCOQHLD.DATA.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DCCOQHLD.DATA.TR_TL = DDRHLD.UPDTBL_TLR;
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.TR_TL);
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        DDRHLDR.UPDTBL_TLR = DDRHLD.UPDTBL_TLR;
        DDRHLDR.UPDTBL_DATE = DDRHLD.UPDTBL_DATE;
        CEP.TRC(SCCGWA, DDRHLD.HLD_STS);
        if (DDRHLD.HLD_STS == 'C') {
            T000_READ_DDTHLDR1();
            if (pgmRtn) return;
        }
        if (DDRHLD.HLD_STS == 'N' 
            || DDRHLD.HLD_STS == 'W') {
            T000_READ_DDTHLDR2();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRHLDR.AUTH_TL);
        DCCOQHLD.DATA.AUTH_TL = DDRHLDR.AUTH_TL;
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        CEP.TRC(SCCGWA, DDRHLD.EXP_DATE);
        CEP.TRC(SCCGWA, "-----------");
        CEP.TRC(SCCGWA, DCCOQHLD);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            TDCACE.PAGE_INF.I_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].KY_BAL);
            DCCOQHLD.DATA.TDBAL = TDCACE.DATA[1-1].KY_BAL;
            CEP.TRC(SCCGWA, DCCOQHLD.DATA.TDBAL);
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDRHLD.AC;
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (WS_DDTCCY_FLG == 'Y') {
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
                WS_DDBAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
                CEP.TRC(SCCGWA, WS_DDBAL);
                DCCOQHLD.DATA.TDBAL = WS_DDBAL;
            }
        }
        CEP.TRC(SCCGWA, DCCOQHLD);
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.HLD_FLG);
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.TDBAL);
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.TR_TL);
        CEP.TRC(SCCGWA, DCCOQHLD.DATA.AUTH_TL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOQHLD;
        SCCFMT.DATA_LEN = 1351;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_40_STARTBR_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_40_STARTBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSQHLD.AC;
        WS_CARD_NO = DCCSQHLD.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CARD_NO);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_CARD_NO;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_CARD_NO = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && DCCSQHLD.SEQ != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_APP_DD_SEQ_M_SPACE);
        }
        CEP.TRC(SCCGWA, "1111111111");
        CEP.TRC(SCCGWA, DCCSQHLD.SEQ);
        CEP.TRC(SCCGWA, DCCSQHLD.BV_NO);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
            && DCCSQHLD.SEQ == 0) {
            CEP.TRC(SCCGWA, "== TD  SEQ ZERO ==");
        } else {
            CEP.TRC(SCCGWA, "==123456789==");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = WS_CARD_NO;
            CICQACAC.DATA.AGR_SEQ = DCCSQHLD.SEQ;
            CICQACAC.DATA.BV_NO = DCCSQHLD.BV_NO;
            CICQACAC.DATA.CCY_ACAC = DCCSQHLD.CCY;
            CICQACAC.DATA.CR_FLG = DCCSQHLD.CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_TR_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            WS_AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_AC_ENM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM;
        }
        R000_GET_TOTAL_NUM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "===NO RECORD===");
        if (WS_COUNT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
        }
        WS_TOTAL_NUM = (short) WS_COUNT;
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_ROW);
        WS_REMAINDER = (short) (WS_TOTAL_NUM % DCCSQHLD.PAGE_ROW);
        WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / DCCSQHLD.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_REMAINDER);
        T000_STARTBR_DCTHLD();
        if (pgmRtn) return;
        if (WS_REMAINDER != 0) {
            WS_TOTAL_PAGE += 1;
        }
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_ROW);
        if (DCCSQHLD.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( DCCSQHLD.PAGE_NUM - 1 ) * DCCSQHLD.PAGE_ROW + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        R000_BROWSE_DDTHLD();
        if (pgmRtn) return;
    }
    public void R000_BROWSE_DDTHLD() throws IOException,SQLException,Exception {
        T000_READNEXT_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_HLD_FLG_1 == 'N') {
            if (DCCSQHLD.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_ROW);
        for (WS_CNT = 1; WS_CNT <= DCCSQHLD.PAGE_ROW 
            && WS_HLD_FLG_1 != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "ABCDEFG");
            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
            B020_70_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B020_50_READNEXT_DCTHLD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_NO);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        DDCF7520.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF7520.TOT_NUM = WS_TOTAL_NUM;
        if (DCCSQHLD.PAGE_NUM == 0) {
            DDCF7520.CURR_PAGE = 1;
        } else {
            DDCF7520.CURR_PAGE = DCCSQHLD.PAGE_NUM;
        }
        if (WS_HLD_FLG_1 == 'N') {
            DDCF7520.LAST_IND = 'Y';
            DDCF7520.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF7520.CURR_ROW = DCCSQHLD.PAGE_ROW;
        }
        B020_60_ENDBR_DCTHLD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7520;
        SCCFMT.DATA_LEN = 9593;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_50_READNEXT_DCTHLD() throws IOException,SQLException,Exception {
        T000_READNEXT_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_60_ENDBR_DCTHLD() throws IOException,SQLException,Exception {
        T000_ENDBR_DCTHLD();
        if (pgmRtn) return;
    }
    public void B020_70_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRHLD.KEY.HLD_NO;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].AC = DCCSQHLD.AC;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_TYP = DDRHLD.HLD_TYP;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_TYP = DDRHLD.SPR_BR_TYP;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_STS = DDRHLD.HLD_STS;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_AMT = DDRHLD.HLD_AMT;
        CEP.TRC(SCCGWA, DDRHLD.HLD_FLG);
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_FLG = DDRHLD.HLD_FLG;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRHLD.HLD_WRIT_NO;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRHLD.HLD_BR_NM;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_SEQ = DDRHLD.HLD_SEQ;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRHLD.EFF_DATE;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRHLD.EXP_DATE;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].RSN = DDRHLD.HLD_RSN;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_BR = DDRHLD.SPR_BR;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRHLD.LAW_OFF_NM1;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRHLD.LAW_ID_NO1;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRHLD.LAW_OFF_NM2;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRHLD.LAW_ID_NO2;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        DDCF7520.OUTPUT_DATA[WS_CNT-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCF7520.OUTPUT_DATA[WS_CNT-1].CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_NO);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].AC);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_TYP);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_TYP);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_STS);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_AMT);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_FLG);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_NO);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].SPR_NM);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_SEQ);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].EFF_DT);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].EXP_DT);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].RSN);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].HLD_BR);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NM1);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NO1);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NM2);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].LAW_NO2);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].AC_SEQ);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].CCY);
        CEP.TRC(SCCGWA, DDCF7520.OUTPUT_DATA[WS_CNT-1].CCY_TYP);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE);
    }
    public void T000_READ_DCTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_TOTAL_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DCCSQHLD.AC;
        DDRHLD.HLD_STS = DCCSQHLD.HLD_STS;
        CEP.TRC(SCCGWA, DCCSQHLD.SPR_TYP);
        DDRHLD.SPR_BR_TYP = DCCSQHLD.SPR_TYP;
        if (DCCSQHLD.SPR_TYP == ' ') {
            if (DCCSQHLD.SEQ == 0) {
                DDTHLD_RD = new DBParm();
                DDTHLD_RD.TableName = "DDTHLD";
                DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
                DDTHLD_RD.where = "CARD_NO = :DDRHLD.CARD_NO "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
            } else {
                DDTHLD_RD = new DBParm();
                DDTHLD_RD.TableName = "DDTHLD";
                DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
                DDTHLD_RD.where = "AC = :DDRHLD.AC "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
            }
        } else {
            if (DCCSQHLD.SEQ == 0) {
                DDTHLD_RD = new DBParm();
                DDTHLD_RD.TableName = "DDTHLD";
                DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
                DDTHLD_RD.where = "CARD_NO = :DDRHLD.CARD_NO "
                    + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
            } else {
                DDTHLD_RD = new DBParm();
                DDTHLD_RD.TableName = "DDTHLD";
                DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
                DDTHLD_RD.where = "AC = :DDRHLD.AC "
                    + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
            }
        }
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void T000_STARTBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DCCSQHLD.AC;
        DDRHLD.HLD_STS = DCCSQHLD.HLD_STS;
        CEP.TRC(SCCGWA, DCCSQHLD.SPR_TYP);
        DDRHLD.SPR_BR_TYP = DCCSQHLD.SPR_TYP;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, DDRHLD.CARD_NO);
        CEP.TRC(SCCGWA, DDRHLD.HLD_STS);
        if (DCCSQHLD.SPR_TYP == ' ') {
            if (DCCSQHLD.SEQ == 0) {
                DDTHLD_BR.rp = new DBParm();
                DDTHLD_BR.rp.TableName = "DDTHLD";
                DDTHLD_BR.rp.where = "CARD_NO = :DDRHLD.CARD_NO "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
            } else {
                DDTHLD_BR.rp = new DBParm();
                DDTHLD_BR.rp.TableName = "DDTHLD";
                DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
            }
        } else {
            if (DCCSQHLD.SEQ == 0) {
                DDTHLD_BR.rp = new DBParm();
                DDTHLD_BR.rp.TableName = "DDTHLD";
                DDTHLD_BR.rp.where = "CARD_NO = :DDRHLD.CARD_NO "
                    + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
            } else {
                DDTHLD_BR.rp = new DBParm();
                DDTHLD_BR.rp.TableName = "DDTHLD";
                DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                    + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
                    + "AND ( HLD_STS = 'N' "
                    + "OR HLD_STS = 'W' )";
                IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTHLD_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLD_FLG_1 = 'Y';
        } else {
            WS_HLD_FLG_1 = 'N';
        }
    }
    public void T000_READNEXT_DCTHLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLD_FLG_1 = 'Y';
        } else {
            WS_HLD_FLG_1 = 'N';
        }
    }
    public void T000_ENDBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND AC_TYPE < > '6'";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void T000_READ_DDTHLDR1() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.where = "HLD_NO = :DDRHLDR.KEY.HLD_NO "
            + "AND UPDTBL_TLR = :DDRHLDR.UPDTBL_TLR "
            + "AND UPDTBL_DATE = :DDRHLDR.UPDTBL_DATE "
            + "AND ( HLD_TYP = '5' "
            + "OR HLD_TYP = 'B' )";
        DDTHLDR_RD.fst = true;
        DDTHLDR_RD.order = "TR_JRNNO";
        IBS.READ(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
    }
    public void T000_READ_DDTHLDR2() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.where = "HLD_NO = :DDRHLDR.KEY.HLD_NO "
            + "AND UPDTBL_TLR = :DDRHLDR.UPDTBL_TLR "
            + "AND UPDTBL_DATE = :DDRHLDR.UPDTBL_DATE "
            + "AND ( HLD_TYP = '1' "
            + "OR HLD_TYP = '2' "
            + "OR HLD_TYP = '4' "
            + "OR HLD_TYP = '7' "
            + "OR HLD_TYP = 'A' )";
        DDTHLDR_RD.fst = true;
        DDTHLDR_RD.order = "TR_JRNNO";
        IBS.READ(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQHLD.FUNC);
        CEP.TRC(SCCGWA, DCCSQHLD.AC);
        CEP.TRC(SCCGWA, DCCSQHLD.SEQ);
        CEP.TRC(SCCGWA, DCCSQHLD.HLD_STS);
        CEP.TRC(SCCGWA, DCCSQHLD.HLD_NO);
        if (DCCSQHLD.FUNC == 'B' 
            && DCCSQHLD.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSQHLD.FUNC == 'I' 
            && DCCSQHLD.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, K_SCR_ROW_NO);
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_ROW);
        if (DCCSQHLD.PAGE_ROW == 0) {
            DCCSQHLD.PAGE_ROW = (short) K_SCR_ROW_NO;
        } else {
            if (DCCSQHLD.PAGE_ROW >= K_SCR_ROW_NO) {
                DCCSQHLD.PAGE_ROW = (short) K_SCR_ROW_NO;
            }
        }
        CEP.TRC(SCCGWA, DCCSQHLD.PAGE_ROW);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPACTY.OUTPUT.AC_TYPE != 'K' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'D' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'V' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'I') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSQHLD.SEQ != 0) {
            WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    if (DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("DD")) {
                        WS_TR_AC = DCCPACTY.OUTPUT.STD_AC;
                    } else {
                        WS_TR_AC = DCCPACTY.OUTPUT.VIA_AC;
                    }
                } else {
                    WS_TR_AC = DCCSQHLD.AC;
                }
            }
        }
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        CEP.TRC(SCCGWA, WS_CARD_VIA_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_STD_FLG);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
