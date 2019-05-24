package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PNZSMBCC {
    boolean pgmRtn = false;
    double WS_AMT1 = 0;
    double WS_AMT2 = 0;
    int WS_ISSDT1 = 0;
    int WS_ISSDT2 = 0;
    int WS_RCD_SEQ = 0;
    int WS_CNT = 0;
    double WS_AMT = 0;
    String K_OUTPUT_FMT1 = "PNX01";
    String K_OUTPUT_FMT_9 = "PN910";
    int K_SCR_ROW_NO = 10;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    PNZSMBCC_WS_FMT WS_FMT = new PNZSMBCC_WS_FMT();
    PNZSMBCC_WS_TEMP WS_TEMP = new PNZSMBCC_WS_TEMP();
    char WS_TBL_FLAG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PNRBCC PNRBCCN = new PNRBCC();
    PNRBCC PNRBCCO = new PNRBCC();
    PNRBCC PNROPUT = new PNRBCC();
    PNCOMBCC PNCOMBCC = new PNCOMBCC();
    PNRBCC PNRBCC = new PNRBCC();
    PNRGBCC PNRGBCC = new PNRGBCC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNCSMBCC PNCSMBCC;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, PNCSMBCC PNCSMBCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCSMBCC = PNCSMBCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZSMBCC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, PNROPUT);
        IBS.init(SCCGWA, PNRBCC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCSMBCC.FUNC);
        if (PNCSMBCC.FUNC == 'B') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (PNCSMBCC.FUNC == 'I') {
            B050_QUERY_PROCESS();
            if (pgmRtn) return;
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_FUNC_IPT_ERR, PNCSMBCC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_KND = PNCSMBCC.DATA.KEY.KND;
        PNRBCC.KEY.BILL_NO = PNCSMBCC.DATA.KEY.CC_NO;
        PNRBCC.STS = PNCSMBCC.DATA.STS;
        WS_ISSDT1 = PNCSMBCC.DATA.ISS_FD;
        WS_ISSDT2 = PNCSMBCC.DATA.ISS_TD;
        WS_AMT1 = PNCSMBCC.DATA.AMT_FR;
        WS_AMT2 = PNCSMBCC.DATA.AMT_TO;
        PNRBCC.APP_AC = PNCSMBCC.DATA.APP_AC;
        PNRBCC.APP_ACNM = PNCSMBCC.DATA.APP_ACNM;
        PNRBCC.ODUE_FLG = PNCSMBCC.DATA.ODUE_FLG;
        PNRBCC.ISS_BR = PNCSMBCC.DATA.ISS_BR;
        CEP.TRC(SCCGWA, "******************");
        CEP.TRC(SCCGWA, PNRBCC.KEY.BILL_KND);
        CEP.TRC(SCCGWA, PNRBCC.KEY.BILL_NO);
        CEP.TRC(SCCGWA, PNRBCC.STS);
        CEP.TRC(SCCGWA, WS_ISSDT1);
        CEP.TRC(SCCGWA, WS_ISSDT2);
        CEP.TRC(SCCGWA, WS_AMT1);
        CEP.TRC(SCCGWA, WS_AMT2);
        CEP.TRC(SCCGWA, PNRBCC.APP_AC);
        CEP.TRC(SCCGWA, PNRBCC.APP_ACNM);
        CEP.TRC(SCCGWA, PNRBCC.ODUE_FLG);
        CEP.TRC(SCCGWA, PNRBCC.ISS_BR);
        if (PNCSMBCC.DATA.KEY.CC_NO.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'B';
        } else if (PNCSMBCC.DATA.APP_AC.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'A';
        } else if (PNCSMBCC.DATA.APP_ACNM.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'N';
        } else if (PNCSMBCC.DATA.ISS_BR != ' ') {
            WS_TEMP.WS_BROWSE_FLG = 'R';
        } else {
            CEP.TRC(SCCGWA, "----OTHER-----");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNCSMBCC.RC);
            WS_ERR_INFO = "WS-BROWSE-FLG" + WS_TEMP.WS_BROWSE_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_FLG);
        T000_COUNT_PNTBCC();
        if (pgmRtn) return;
        T000_STARTBR_PNTBCC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PNCSMBCC.DATA.PAGE_NUM);
        if (PNCSMBCC.DATA.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( PNCSMBCC.DATA.PAGE_NUM - 1 ) * K_SCR_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_PNTBCC_FIRST();
        if (pgmRtn) return;
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_NUM < K_SCR_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            CEP.TRC(SCCGWA, PNRBCC.KEY.BILL_NO);
            WS_NUM = (short) (WS_NUM + 1);
            CEP.TRC(SCCGWA, "**********************");
            CEP.TRC(SCCGWA, WS_NUM);
            WS_FMT.WS_DATA[WS_NUM-1].WS_KND1 = PNRBCC.KEY.BILL_KND;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CC_NO1 = PNRBCC.KEY.BILL_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STS1 = PNRBCC.STS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AMT3 = PNRBCC.AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_APP_AC1 = PNRBCC.APP_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ISS_BR1 = PNRBCC.ISS_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ISS_DT3 = PNRBCC.ISS_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODUE_FLG = PNRBCC.ODUE_FLG;
            T000_READNEXT_PNTBCC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PNTBCC();
        if (pgmRtn) return;
        if (PNCSMBCC.DATA.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = (short) PNCSMBCC.DATA.PAGE_NUM;
        }
        WS_FMT.WS_LAST_PAGE = 'N';
        if (WS_NUM <= K_SCR_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            WS_FMT.WS_LAST_PAGE = 'Y';
        }
        WS_FMT.WS_PAGE_ROW = WS_NUM;
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        PNCOMBCC.DATA.KND = PNRBCC.KEY.BILL_KND;
        PNCOMBCC.DATA.CC_NO = PNRBCC.KEY.BILL_NO;
        PNCOMBCC.DATA.OLD_CCNO = PNRBCC.OLD_CCNO;
        PNCOMBCC.DATA.PAY_TYPE = PNRBCC.PAY_TYPE;
        PNCOMBCC.DATA.ISS_DT = PNRBCC.ISS_DT;
        PNCOMBCC.DATA.DUE_DATE = PNRBCC.DUE_DATE;
        PNCOMBCC.DATA.CLR_DATE = PNRBCC.CLR_DATE;
        PNCOMBCC.DATA.CHG_DATE = PNRBCC.CHG_DATE;
        PNCOMBCC.DATA.CCY = PNRBCC.CCY;
        PNCOMBCC.DATA.AMT = PNRBCC.AMT;
        PNCOMBCC.DATA.STS = PNRBCC.STS;
        PNCOMBCC.DATA.PAY_BK = PNRBCC.PAY_BK;
        PNCOMBCC.DATA.APP_ACNM = PNRBCC.APP_ACNM;
        PNCOMBCC.DATA.PAYEE_ACNM = PNRBCC.PAYEE_ACNM;
        PNCOMBCC.DATA.LHD_ACNM = PNRBCC.LHD_ACNM;
        PNCOMBCC.DATA.TRN_FLG = PNRBCC.TRN_FLG;
        PNCOMBCC.DATA.C_T_FLG = PNRBCC.C_T_FLG;
        PNCOMBCC.DATA.ODUE_FLG = PNRBCC.ODUE_FLG;
        PNCOMBCC.DATA.APP_AC = PNRBCC.APP_AC;
        PNCOMBCC.DATA.PAYEE_AC = PNRBCC.PAYEE_AC;
        PNCOMBCC.DATA.LHD_AC = PNRBCC.LHD_AC;
        PNCOMBCC.DATA.ISS_FEEFLG = PNRBCC.FEE_FLG;
        if (PNRBCC.APB_TYPE == '1') {
            PNCOMBCC.DATA.APB_TYPE = '0';
        } else {
            PNCOMBCC.DATA.APB_TYPE = '1';
        }
        PNCOMBCC.DATA.APB_NO = PNRBCC.APB_NO;
        CEP.TRC(SCCGWA, PNCOMBCC.DATA.APB_NO);
        PNCOMBCC.DATA.APB_VALUE_DATE = PNRBCC.APB_VALUE_DATE;
        PNCOMBCC.DATA.ISS_BR = PNRBCC.ISS_BR;
        PNCOMBCC.DATA.ISS_TLR = PNRBCC.ISS_TLR;
        PNCOMBCC.DATA.CLR_BR = PNRBCC.CLR_BR;
        PNCOMBCC.DATA.CLR_TLR = PNRBCC.CLR_TLR;
        PNCOMBCC.DATA.USG_RMK = PNRBCC.USG_RMK;
        PNCOMBCC.DATA.STL_OPT = PNRBCC.STL_OPT;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = PNCOMBCC;
        SCCFMT.DATA_LEN = 2897;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "AFTER-OUTPUT");
        CEP.TRC(SCCGWA, PNROPUT);
        CEP.TRC(SCCGWA, PNCOMBCC);
