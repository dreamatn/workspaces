package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIHDCK {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    short WS_I = 0;
    String WS_TRACOMMT_NO = " ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_CHK_FLG = ' ';
    char WS_HLD_ALL = ' ';
    char WS_WRK_FLG = ' ';
    LNRCONT LNRCONT = new LNRCONT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCIHDCK LNCIHDCK;
    public void MP(SCCGWA SCCGWA, LNCIHDCK LNCIHDCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIHDCK = LNCIHDCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZIHDCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        LNCIHDCK.RC.RC_MMO = "LN";
        LNCIHDCK.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        B030_CHECK_HOLIDAY_PROC();
        B050_AUTHORIZATION_PROC();
        B070_RETURN_DATE_INF_PROC();
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.TRACOMMT_NO);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CNTY[1-1].CNTY_CD);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CNTY[2-1].CNTY_CD);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CNTY[3-1].CNTY_CD);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CNTY[4-1].CNTY_CD);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CNTY[5-1].CNTY_CD);
        if (LNCIHDCK.INPUT_INFO.WARN_AUTH != 'W' 
            && LNCIHDCK.INPUT_INFO.WARN_AUTH != 'A' 
            && LNCIHDCK.INPUT_INFO.WARN_AUTH != 'O') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_WRONG_WN_AT_FLG;
            S000_ERR_MSG_PROC();
        }
        if (LNCIHDCK.INPUT_INFO.INPUT_DATE == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT_DATE_M_IPT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.BR);
        if (LNCIHDCK.INPUT_INFO.BR == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_BOOK;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = LNCIHDCK.INPUT_INFO.BR;
            S000_CALL_BPZPQORG();
        }
    }
    public void B010_1_CHK_CNTY_CD() throws IOException,SQLException,Exception {
        WS_CHK_FLG = 'N';
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            if (LNCIHDCK.INPUT_INFO.CNTY[WS_I-1].CNTY_CD.trim().length() > 0) {
                WS_CHK_FLG = 'Y';
            }
        }
        if (WS_CHK_FLG == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTY_CD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNCIHDCK.INPUT_INFO.HOLIDAY_MTH == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_HLD_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNCIHDCK.INPUT_INFO.HOLIDAY_ORDE == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_HLD_ORD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_HOLIDAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = LNCIHDCK.INPUT_INFO.INPUT_DATE;
        CEP.TRC(SCCGWA, "INPUT DATE");
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.INPUT_DATE);
        if (LNCIHDCK.INPUT_INFO.RTN_WORKDAY == 'N') {
            BPCPCMWD.FUNC_FLAG = 'C';
        }
        for (WS_I = 1; WS_I <= 5 
            && LNCIHDCK.INPUT_INFO.CNTY[WS_I-1].CNTY_CD.trim().length() != 0; WS_I += 1) {
            BPCPCMWD.CAL_CODE[WS_I-1].CNTY_CD = LNCIHDCK.INPUT_INFO.CNTY[WS_I-1].CNTY_CD;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCIHDCK.INPUT_INFO.CONTRACT_CCY;
        S000_CALL_BPZQCCY();
        BPCPCMWD.CAL_CODE[WS_I-1].CNTY_CD = BPCQCCY.DATA.CNTY_CD;
        CEP.TRC(SCCGWA, "CONTRACT CCY");
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.CONTRACT_CCY);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CNTY_CD);
        WS_I = (short) (WS_I + 1);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCRBANK.LOC_CCY1;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        S000_CALL_BPZQCCY();
        BPCPCMWD.CAL_CODE[WS_I-1].CNTY_CD = BPCQCCY.DATA.CNTY_CD;
        CEP.TRC(SCCGWA, "LOCAL CCY");
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CNTY_CD);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.DATE1 = LNCIHDCK.INPUT_INFO.INPUT_DATE;
        BPCOCLWD.WDAYS = 1;
        CEP.TRC(SCCGWA, BPCRBANK.CALD_BUI);
        BPCOCLWD.CAL_CODE = BPCPQORG.CALD_CD;
        BPCOCLWD.DAYE_FLG = 'Y';
        CEP.TRC(SCCGWA, "1212121212121");
        CEP.TRC(SCCGWA, BPCRBANK.CALD_BUI);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        S000_CALL_BPZPCLWD();
        BPCPCMWD.NEXT_WORK_DAY_ALL = BPCOCLWD.DATE2;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.DATE1 = LNCIHDCK.INPUT_INFO.INPUT_DATE;
        BPCOCLWD.WDAYS = -1;
        BPCOCLWD.CAL_CODE = BPCPQORG.CALD_CD;
        BPCOCLWD.DAYE_FLG = 'Y';
        CEP.TRC(SCCGWA, "78787878");
        S000_CALL_BPZPCLWD();
        BPCPCMWD.LAST_WORK_DAY_ALL = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG);
        if (BPCOCLWD.DATE1_FLG == 'H') {
            LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG = 'Y';
        } else {
            LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG);
    }
    public void B050_AUTHORIZATION_PROC() throws IOException,SQLException,Exception {
        if (LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_HOL_ORD_EQL_N;
            S000_ERR_MSG_PROC();
        }
    }
    public void B070_RETURN_DATE_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY_ALL);
        CEP.TRC(SCCGWA, BPCPCMWD.LAST_WORK_DAY_ALL);
        CEP.TRC(SCCGWA, LNCIHDCK.INPUT_INFO.INPUT_DATE);
        if (LNCIHDCK.OUTPOUT_INFO.HOLIDAY_FLG == 'Y') {
            if (LNCIHDCK.INPUT_INFO.HOLIDAY_MTH == 'L') {
                LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = BPCPCMWD.NEXT_WORK_DAY_ALL;
            }
            if (LNCIHDCK.INPUT_INFO.HOLIDAY_MTH == 'F') {
                LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = BPCPCMWD.LAST_WORK_DAY_ALL;
            }
            if (LNCIHDCK.INPUT_INFO.HOLIDAY_MTH == 'M') {
                JIBS_tmp_str[0] = "" + LNCIHDCK.INPUT_INFO.INPUT_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCPCMWD.NEXT_WORK_DAY_ALL;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase(JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1))) {
                    LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = BPCPCMWD.NEXT_WORK_DAY_ALL;
                } else {
                    LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = BPCPCMWD.LAST_WORK_DAY_ALL;
                }
            }
            if (LNCIHDCK.INPUT_INFO.HOLIDAY_MTH == 'K') {
                LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = LNCIHDCK.INPUT_INFO.INPUT_DATE;
            }
        } else {
            LNCIHDCK.OUTPOUT_INFO.OUTPUT_DATE = LNCIHDCK.INPUT_INFO.INPUT_DATE;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIHDCK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCIHDCK=");
            CEP.TRC(SCCGWA, LNCIHDCK);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
