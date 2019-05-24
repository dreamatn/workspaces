package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSMARQ {
    int JIBS_tmp_int;
    DBParm LNTAPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    LNZSMARQ_WS_VARIABLES WS_VARIABLES = new LNZSMARQ_WS_VARIABLES();
    LNZSMARQ_WS_PAGE_INFO WS_PAGE_INFO = new LNZSMARQ_WS_PAGE_INFO();
    LNZSMARQ_WS_OUT_RECODE WS_OUT_RECODE = new LNZSMARQ_WS_OUT_RECODE();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    LNRAPRD LNRAPRD = new LNRAPRD();
    SCCGWA SCCGWA;
    LNCSMARQ LNCSMARQ;
    public void MP(SCCGWA SCCGWA, LNCSMARQ LNCSMARQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSMARQ = LNCSMARQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSMARQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        LNCSMARQ.RC.RC_APP = "LN";
        LNCSMARQ.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_GET_PAGE_INFO();
        if (pgmRtn) return;
        B500_GET_MAR_INFO();
        if (pgmRtn) return;
        B700_GET_HEAD_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B300_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.OUT_AC);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        CEP.TRC(SCCGWA, WS_VARIABLES.OUT_AC);
        WS_PAGE_INFO.ROW_FLG = 'N';
        CEP.TRC(SCCGWA, WS_VARIABLES.OUT_AC);
        WS_PAGE_INFO.TOTAL_NUM = 0;
        CEP.TRC(SCCGWA, WS_VARIABLES.OUT_AC);
        WS_PAGE_INFO.PAGE_IDX = 0;
        WS_PAGE_INFO.LAST_PAGE = 'N';
        WS_PAGE_INFO.PAGE_ROW = LNCSMARQ.DATA.PAGE_ROW;
        WS_PAGE_INFO.CURR_PAGE = (short) LNCSMARQ.DATA.PAGE_NUM;
    }
    public void B500_GET_MAR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSMARQ.DATA.CONT_NO;
        CEP.TRC(SCCGWA, LNRAPRD.KEY.CONTRACT_NO);
        T000_READ_APRD();
        if (pgmRtn) return;
        if (LNRAPRD.GDA_APRE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN_GDA_APRE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, GDCIQLDR);
        GDCIQLDR.RSEQ = LNRAPRD.GDA_APRE;
        CEP.TRC(SCCGWA, GDCIQLDR.RSEQ);
        S000_CALL_GDZIQLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCIQLDR.TAMT);
        CEP.TRC(SCCGWA, GDCIQLDR.TCNT);
        CEP.TRC(SCCGWA, GDCIQLDR.RSEQ);
        CEP.TRC(SCCGWA, GDCIQLDR.IN_AC);
        CEP.TRC(SCCGWA, GDCIQLDR.IN_SEQ);
        for (WS_VARIABLES.TCNT = 1; WS_VARIABLES.TCNT <= GDCIQLDR.TCNT; WS_VARIABLES.TCNT += 1) {
            CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].OUT_AC);
            CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].OUT_SEQ);
            CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].AC_TYP);
            CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].CCY);
            CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].RAMT);
            CEP.TRC(SCCGWA, WS_VARIABLES.TCNT);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
            WS_PAGE_INFO.TOTAL_NUM += 1;
            if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                WS_PAGE_INFO.ROW_FLG = 'Y';
                WS_PAGE_INFO.PAGE_IDX += 1;
                R000_WRITE_QUEUE_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B700_GET_HEAD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.ROW_FLG);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE <= WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_IDX;
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
            WS_PAGE_INFO.CURR_PAGE = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 0;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        SCCFMT.FMTID = "LN802";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 1499;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_QUEUE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1]);
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].CONT_NO = LNCSMARQ.DATA.CONT_NO;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].M_NO = LNRAPRD.GDA_APRE;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].MG_AVL = GDCIQLDR.TAMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].M_AC = GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].OUT_AC;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].MG_HOLD = GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].RAMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].STS = GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].HOLD_STS;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].CCY = GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].CCY;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].SUB_AC = "" + GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].OUT_SEQ;
        JIBS_tmp_int = WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].SUB_AC.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].SUB_AC = "0" + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].SUB_AC;
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].CCY);
        CEP.TRC(SCCGWA, LNCSMARQ.DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNRAPRD.GDA_APRE);
        CEP.TRC(SCCGWA, GDCIQLDR.TAMT);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].OUT_AC);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].RAMT);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.TCNT-1].HOLD_STS);
    }
    public void T000_READ_APRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PAGE_INFO.READ_LNTAPRD_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PAGE_INFO.READ_LNTAPRD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        if (WS_PAGE_INFO.READ_LNTAPRD_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_APRD_NOTFND, LNCSMARQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSMARQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
