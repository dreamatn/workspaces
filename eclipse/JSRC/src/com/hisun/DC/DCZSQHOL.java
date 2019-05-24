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

public class DCZSQHOL {
    DBParm DDTHLD_RD;
    DBParm DDTHLDR_RD;
    DBParm DDTCCY_RD;
    brParm DDTHLD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC773";
    String K_OUTPUT_FMT_A = "DC772";
    int K_SCR_ROW_NO = 8;
    String WS_ERR_MSG = " ";
    String WS_SUB_AC = " ";
    String WS_TR_AC = " ";
    String WS_CARD_VIA_AC = " ";
    double WS_DDBAL = 0;
    String WS_CARD_NO = " ";
    short WS_CNT = 0;
    short WS_TOTAL_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    char WS_HLDNO_FLG = ' ';
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    DDRCCY DDRCCY = new DDRCCY();
    DCCOQHOL DCCOQHOL = new DCCOQHOL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DDCF7720 DDCF7720 = new DDCF7720();
    TDCACE TDCACE = new TDCACE();
    DDRHLDR DDRHLDR = new DDRHLDR();
    int WS_COUNT = 0;
    int WS_RCD_SEQ = 0;
    char WS_HLD_FLG1 = ' ';
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCSQHOL DCCSQHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSQHOL DCCSQHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQHOL = DCCSQHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, DDCF7720);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQHOL.AC);
        CEP.TRC(SCCGWA, DCCSQHOL.SEQ);
        CEP.TRC(SCCGWA, DCCSQHOL.CCY);
        CEP.TRC(SCCGWA, DCCSQHOL.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCSQHOL.BV_NO);
        CEP.TRC(SCCGWA, DCCSQHOL.HLD_STS);
        CEP.TRC(SCCGWA, DCCSQHOL.HLD_NO);
        CEP.TRC(SCCGWA, DCCSQHOL.SPR_TYP);
        CEP.TRC(SCCGWA, DCCSQHOL.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQHOL.PAGE_ROW);
        CEP.TRC(SCCGWA, DCCSQHOL.HLD_FLG);
        if (DCCSQHOL.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSQHOL.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSQHOL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSQHOL.HLD_NO;
        WS_HLD_FLG1 = DCCSQHOL.HLD_FLG;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        B010_10_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_10_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOQHOL);
        DCCOQHOL.DATA.HLD_NO = DDRHLD.KEY.HLD_NO;
        DCCOQHOL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DCCOQHOL.DATA.HLD_TYP = DDRHLD.HLD_TYP;
        DCCOQHOL.DATA.SPR_TYP = DDRHLD.SPR_BR_TYP;
        DCCOQHOL.DATA.HLD_STS = DDRHLD.HLD_STS;
        DCCOQHOL.DATA.CCY = DDRHLD.CCY;
        DCCOQHOL.DATA.CCY_TYP = DDRHLD.CCY_TYPE;
        DCCOQHOL.DATA.HLD_AMT = DDRHLD.HLD_AMT;
        DCCOQHOL.DATA.SPR_NO = DDRHLD.HLD_WRIT_NO;
        DCCOQHOL.DATA.SPR_NM = DDRHLD.HLD_BR_NM;
        DCCOQHOL.DATA.HLD_SEQ = DDRHLD.HLD_SEQ;
        DCCOQHOL.DATA.EFF_DT = DDRHLD.EFF_DATE;
        DCCOQHOL.DATA.EXP_DT = DDRHLD.EXP_DATE;
        DCCOQHOL.DATA.RSN = DDRHLD.HLD_RSN;
        DCCOQHOL.DATA.RMK = DDRHLD.REMARK;
        DCCOQHOL.DATA.HLD_BR = DDRHLD.SPR_BR;
        DCCOQHOL.DATA.LAW_NM1 = DDRHLD.LAW_OFF_NM1;
        DCCOQHOL.DATA.LAW_NO1 = DDRHLD.LAW_ID_NO1;
        DCCOQHOL.DATA.LAW_NM2 = DDRHLD.LAW_OFF_NM2;
        DCCOQHOL.DATA.LAW_NO2 = DDRHLD.LAW_ID_NO2;
        DCCOQHOL.DATA.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DCCOQHOL.DATA.TR_TL = DDRHLD.UPDTBL_TLR;
        DCCOQHOL.DATA.HLD_FLG = DDRHLD.HLD_FLG;
        DCCOQHOL.DATA.DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        DCCOQHOL.DATA.UP_HLD_NO = DDRHLD.UP_HLD_NO;
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        DDRHLDR.UPDTBL_TLR = DDRHLD.UPDTBL_TLR;
        DDRHLDR.UPDTBL_DATE = DDRHLD.UPDTBL_DATE;
        if (DDRHLD.HLD_STS == 'C') {
            T000_READ_DDTHLDR_C();
            if (pgmRtn) return;
        }
        if (DDRHLD.HLD_STS == 'N' 
            || DDRHLD.HLD_STS == 'W') {
            T000_READ_DDTHLDR_NW();
            if (pgmRtn) return;
        }
        DCCOQHOL.DATA.AUTH_TL = DDRHLDR.AUTH_TL;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            TDCACE.PAGE_INF.I_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            DCCOQHOL.DATA.TDBAL = TDCACE.DATA[1-1].KY_BAL;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDRHLD.AC;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (WS_CCY_FLG == 'Y') {
                WS_DDBAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
                DCCOQHOL.DATA.TDBAL = WS_DDBAL;
            }
        }
        CEP.TRC(SCCGWA, DCCOQHOL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOQHOL;
        SCCFMT.DATA_LEN = 1391;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_10_STARTBR_DDTHLD();
        if (pgmRtn) return;
    }
    public void B020_10_STARTBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSQHOL.AC;
        WS_CARD_NO = DCCSQHOL.AC;
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
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && DCCSQHOL.SEQ != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_APP_DD_SEQ_M_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
            && DCCSQHOL.SEQ == 0) {
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = WS_CARD_NO;
            CICQACAC.DATA.AGR_SEQ = DCCSQHOL.SEQ;
            CICQACAC.DATA.BV_NO = DCCSQHOL.BV_NO;
            CICQACAC.DATA.CCY_ACAC = DCCSQHOL.CCY;
            CICQACAC.DATA.CR_FLG = DCCSQHOL.CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_TR_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        CEP.TRC(SCCGWA, WS_TR_AC);
        R000_GET_TOTAL_NUM();
        if (pgmRtn) return;
        if (WS_COUNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NO_RSLT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TOTAL_NUM = (short) WS_COUNT;
        WS_REMAINDER = (short) (WS_TOTAL_NUM % DCCSQHOL.PAGE_ROW);
        WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / DCCSQHOL.PAGE_ROW);
        if (WS_REMAINDER != 0) {
            WS_TOTAL_PAGE += 1;
        }
        T000_STARTBR_DDTHLD();
        if (pgmRtn) return;
        if (DCCSQHOL.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( DCCSQHOL.PAGE_NUM - 1 ) * DCCSQHOL.PAGE_ROW + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        R000_BROWSE_DDTHLD();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DCCSQHOL.FUNC == 'B' 
            && DCCSQHOL.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSQHOL.FUNC == 'I' 
            && DCCSQHOL.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSQHOL.PAGE_ROW == 0) {
            DCCSQHOL.PAGE_ROW = (short) K_SCR_ROW_NO;
        } else {
            if (DCCSQHOL.PAGE_ROW >= K_SCR_ROW_NO) {
                DCCSQHOL.PAGE_ROW = (short) K_SCR_ROW_NO;
            }
        }
    }
    public void R000_GET_TOTAL_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DCCSQHOL.AC;
        DDRHLD.SPR_BR_TYP = DCCSQHOL.SPR_TYP;
        DDRHLD.HLD_FLG = DCCSQHOL.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
        DDTHLD_RD.where = "CARD_NO = :DDRHLD.CARD_NO "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' ) "
            + "AND HLD_FLG = :DDRHLD.HLD_FLG "
            + "AND ( AC = :DDRHLD.AC "
            + "OR :DDRHLD.AC = ' ' ) "
            + "AND ( SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "OR :DDRHLD.SPR_BR_TYP = ' ' )";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void R000_BROWSE_DDTHLD() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_HLDNO_FLG == 'N') {
            if (DCCSQHOL.PAGE_NUM == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NO_RSLT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAST_PAGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_CNT = 1; WS_CNT <= DCCSQHOL.PAGE_ROW 
            && WS_HLDNO_FLG != 'N'; WS_CNT += 1) {
            R_OUTPUT_DETAIL();
            if (pgmRtn) return;
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        DDCF7720.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF7720.TOT_NUM = WS_TOTAL_NUM;
        if (DCCSQHOL.PAGE_NUM == 0) {
            DDCF7720.CURR_PAGE = 1;
        } else {
            DDCF7720.CURR_PAGE = DCCSQHOL.PAGE_NUM;
        }
        if (WS_HLDNO_FLG == 'N') {
            DDCF7720.LAST_IND = 'Y';
            DDCF7720.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF7720.CURR_ROW = DCCSQHOL.PAGE_ROW;
        }
        T000_ENDBR_DDTHLD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7720;
        SCCFMT.DATA_LEN = 9913;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRHLD.KEY.HLD_NO;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].AC = DCCSQHOL.AC;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_TYP = DDRHLD.HLD_TYP;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].SPR_TYP = DDRHLD.SPR_BR_TYP;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_STS = DDRHLD.HLD_STS;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].CCY = DDRHLD.CCY;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].CCY_TYP = DDRHLD.CCY_TYPE;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_AMT = DDRHLD.HLD_AMT;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRHLD.HLD_WRIT_NO;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRHLD.HLD_BR_NM;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_SEQ = DDRHLD.HLD_SEQ;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRHLD.EFF_DATE;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRHLD.EXP_DATE;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].RSN = DDRHLD.HLD_RSN;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_BR = DDRHLD.SPR_BR;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRHLD.LAW_OFF_NM1;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRHLD.LAW_ID_NO1;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRHLD.LAW_OFF_NM2;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRHLD.LAW_ID_NO2;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].HLD_FLG = DDRHLD.HLD_FLG;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].UP_HLD_NO = DDRHLD.UP_HLD_NO;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        DDCF7720.OUTPUT_DATA[WS_CNT-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        CEP.TRC(SCCGWA, WS_HLD_FLG1);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
            + "AND ( HLD_FLG = :WS_HLD_FLG1 "
            + "OR :WS_HLD_FLG1 = ' ' )";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLDR_C() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.where = "HLD_NO = :DDRHLDR.KEY.HLD_NO "
            + "AND UPDTBL_TLR = :DDRHLDR.UPDTBL_TLR "
            + "AND UPDTBL_DATE = :DDRHLDR.UPDTBL_DATE "
            + "AND HLD_TYP = '5'";
        DDTHLDR_RD.fst = true;
        DDTHLDR_RD.order = "TR_JRNNO";
        IBS.READ(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLDR_NW() throws IOException,SQLException,Exception {
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
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        } else {
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_STARTBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DCCSQHOL.AC;
        DDRHLD.HLD_STS = DCCSQHOL.HLD_STS;
        DDRHLD.SPR_BR_TYP = DCCSQHOL.SPR_TYP;
        DDRHLD.HLD_FLG = DCCSQHOL.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "CARD_NO = :DDRHLD.CARD_NO "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' ) "
            + "AND HLD_FLG = :DDRHLD.HLD_FLG "
            + "AND ( AC = :DDRHLD.AC "
            + "OR :DDRHLD.AC = ' ' ) "
            + "AND ( SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "OR :DDRHLD.SPR_BR_TYP = ' ' )";
        DDTHLD_BR.rp.order = "HLD_NO DESC";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
    }
    public void T000_READNEXT_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLDNO_FLG = 'Y';
        } else {
            WS_HLDNO_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTHLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLDNO_FLG = 'Y';
        } else {
            WS_HLDNO_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
