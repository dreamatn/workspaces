package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9181 {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BPX01";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    BPOT9181_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9181_WS_TEMP_VARIABLE();
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    int WS_CYCLE_DATE = 0;
    int WS_FIRST_START_DATE = 0;
    short WS_MTHS = 0;
    int WS_DAYS = 0;
    int WS_DATE1 = 0;
    BPOT9181_REDEFINES13 REDEFINES13 = new BPOT9181_REDEFINES13();
    double WS_AMT_TERM = 0;
    double WS_AMT_TOTAL = 0;
    short WS_TOT_CNT = 0;
    short WS_FREQ_CNT = 0;
    int WS_TOTAL_DAYS = 0;
    short WS_TOTAL_MTHS = 0;
    short WS_LONG = 0;
    short WS_REMAINDER = 0;
    char WS_MONTH_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCSFECT BPCSFECT = new BPCSFECT();
    BPCOFCTR BPCOFCTR = new BPCOFCTR();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB9181_AWA_9181 BPB9181_AWA_9181;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9181 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9181_AWA_9181>");
        BPB9181_AWA_9181 = (BPB9181_AWA_9181) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPCOFCTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_ADD_FEE_CNTR_PROC();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.FEE_CODE);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.FEE_DESC);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CI_NO);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CHG_BR);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.STA_DATE);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.END_DATE);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CHG_FREQ);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.FREQ_CNT);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.REL_SRC);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.REL_CTRT);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.AC_TYP);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CHG_MEDI);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CHG_CCY);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.AMT_TERM);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.AMT_ALL);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.SALE_DT);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.CREV_NO);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.FEE_RMK);
        B110_GET_FEE_BASIC_INFO();
        CEP.TRC(SCCGWA, BPRFBAS.AMO_FLG);
        if (BPRFBAS.AMO_FLG == '0') {
            CEP.TRC(SCCGWA, "FEE CODE SHOULE NOT AMO");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOT_AMO, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB9181_AWA_9181.STA_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_START_DATE_LESS, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB9181_AWA_9181.END_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB9181_AWA_9181.END_DATE < BPB9181_AWA_9181.STA_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DATE_LESS, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB9181_AWA_9181.END_DATE > 20991231) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B110_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPB9181_AWA_9181.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPRFBAS.KEY);
        CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
        S000_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, BPCTFBAS.RETURN_INFO);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "ENTER NOTFND ");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ADD_FEE_CNTR_PROC() throws IOException,SQLException,Exception {
        WS_FIRST_START_DATE = BPB9181_AWA_9181.STA_DATE;
        WS_START_DATE = BPB9181_AWA_9181.STA_DATE;
        WS_MONTH_END_FLG = 'N';
        if (BPB9181_AWA_9181.CHG_FREQ == '0' 
            || BPB9181_AWA_9181.CHG_FREQ == '1' 
            || BPB9181_AWA_9181.CHG_FREQ == '2') {
            WS_DATE1 = BPB9181_AWA_9181.STA_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
            R000_GET_MONTH_END();
            CEP.TRC(SCCGWA, WS_DATE1);
            if (WS_DATE1 == BPB9181_AWA_9181.STA_DATE) {
                WS_MONTH_END_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.AMT_TERM);
        CEP.TRC(SCCGWA, BPB9181_AWA_9181.AMT_ALL);
        if (BPB9181_AWA_9181.AMT_TERM == 0 
            && BPB9181_AWA_9181.AMT_ALL != 0) {
            B210_CAL_TOTAL_FREQS();
            CEP.TRC(SCCGWA, WS_FREQ_CNT);
            WS_AMT_TERM = BPB9181_AWA_9181.AMT_ALL / WS_FREQ_CNT;
            bigD = new BigDecimal(WS_AMT_TERM);
            WS_AMT_TERM = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        }
        WS_CYCLE_DATE = 0;
        WS_DATE1 = 0;
        WS_MTHS = 0;
        WS_DAYS = 0;
        while (WS_END_DATE < BPB9181_AWA_9181.END_DATE) {
            B220_CAL_PERIOD();
            WS_END_DATE = WS_CYCLE_DATE;
            CEP.TRC(SCCGWA, WS_AMT_TERM);
            CEP.TRC(SCCGWA, WS_AMT_TOTAL);
            if (WS_END_DATE == BPB9181_AWA_9181.END_DATE) {
                WS_AMT_TERM = BPB9181_AWA_9181.AMT_ALL - WS_AMT_TOTAL;
            } else {
                WS_AMT_TOTAL += WS_AMT_TERM;
            }
            CEP.TRC(SCCGWA, WS_AMT_TERM);
            CEP.TRC(SCCGWA, WS_START_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            IBS.init(SCCGWA, BPCSFECT);
            BPCSFECT.FUNC_CODE = 'A';
            R000_MOVE_DATA();
            S000_CALL_BPZSFECT();
            WS_TOT_CNT += 1;
            if (WS_TOT_CNT <= 30) {
                BPCOFCTR.CTRT_INFO[WS_TOT_CNT-1].CTRT_NO = BPCSFECT.CTNO;
                BPCOFCTR.CTRT_INFO[WS_TOT_CNT-1].CTRT_STS = BPCSFECT.CT_STS;
            }
            CEP.TRC(SCCGWA, WS_TOT_CNT);
            CEP.TRC(SCCGWA, BPCOFCTR.CTRT_INFO[WS_TOT_CNT-1].CTRT_NO);
            CEP.TRC(SCCGWA, BPCOFCTR.CTRT_INFO[WS_TOT_CNT-1].CTRT_STS);
            if (WS_END_DATE < BPB9181_AWA_9181.END_DATE) {
                CEP.TRC(SCCGWA, "GET NEXT START DATE");
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_END_DATE;
                SCCCLDT.DAYS = 1;
                S000_CALL_SCSSCLDT();
                WS_START_DATE = SCCCLDT.DATE2;
            }
            CEP.TRC(SCCGWA, WS_START_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            CEP.TRC(SCCGWA, BPB9181_AWA_9181.END_DATE);
        }
    }
    public void B210_CAL_TOTAL_FREQS() throws IOException,SQLException,Exception {
        while (WS_CYCLE_DATE < BPB9181_AWA_9181.END_DATE) {
            B220_CAL_PERIOD();
            CEP.TRC(SCCGWA, WS_FREQ_CNT);
            WS_FREQ_CNT += 1;
            CEP.TRC(SCCGWA, WS_FREQ_CNT);
            CEP.TRC(SCCGWA, WS_CYCLE_DATE);
            CEP.TRC(SCCGWA, BPB9181_AWA_9181.END_DATE);
        }
    }
    public void B220_CAL_PERIOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_FIRST_START_DATE;
        if (BPB9181_AWA_9181.CHG_FREQ == '0') {
            WS_MTHS = (short) (WS_MTHS + BPB9181_AWA_9181.FREQ_CNT * 12);
            SCCCLDT.MTHS = WS_MTHS;
        } else if (BPB9181_AWA_9181.CHG_FREQ == '1') {
            WS_MTHS = (short) (WS_MTHS + BPB9181_AWA_9181.FREQ_CNT * 3);
            SCCCLDT.MTHS = WS_MTHS;
        } else if (BPB9181_AWA_9181.CHG_FREQ == '2') {
            WS_MTHS = (short) (WS_MTHS + BPB9181_AWA_9181.FREQ_CNT);
            SCCCLDT.MTHS = WS_MTHS;
        } else if (BPB9181_AWA_9181.CHG_FREQ == '3') {
            WS_DAYS = WS_DAYS + BPB9181_AWA_9181.FREQ_CNT;
            SCCCLDT.DAYS = WS_DAYS;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID CHARGE-FREQ(" + BPB9181_AWA_9181.CHG_FREQ + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        S000_CALL_SCSSCLDT();
        if (WS_MONTH_END_FLG == 'Y') {
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
            R000_GET_MONTH_END();
            WS_CYCLE_DATE = WS_DATE1;
        } else {
            WS_CYCLE_DATE = SCCCLDT.DATE2;
        }
        if (WS_CYCLE_DATE > BPB9181_AWA_9181.END_DATE) {
            WS_CYCLE_DATE = BPB9181_AWA_9181.END_DATE;
        }
    }
    public void R000_GET_MONTH_END() throws IOException,SQLException,Exception {
        if (REDEFINES13.WS_DATE1_MM == 12) {
            REDEFINES13.WS_DATE1_MM = 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES13);
            WS_DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
            REDEFINES13.WS_DATE1_YYYY += 1;
        } else {
            REDEFINES13.WS_DATE1_MM += 1;
        }
        REDEFINES13.WS_DATE1_DD = 1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES13);
        WS_DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DAYS = -1;
        S000_CALL_SCSSCLDT();
        WS_DATE1 = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
    }
    public void R000_MOVE_DATA() throws IOException,SQLException,Exception {
        BPCSFECT.FEE_TYP = BPB9181_AWA_9181.FEE_CODE;
        BPCSFECT.FEE_DESC = BPB9181_AWA_9181.FEE_DESC;
        BPCSFECT.CINO = BPB9181_AWA_9181.CI_NO;
        BPCSFECT.BOOK_ACCT = BPB9181_AWA_9181.CHG_BR;
        BPCSFECT.CT_TYP = "FEES";
        BPCSFECT.HOL_OVR = 'Y';
        BPCSFECT.HOL_METH = 'N';
        BPCSFECT.CAL_CD1 = "CN";
        BPCSFECT.CAL_CD2 = "CN";
        BPCSFECT.CHG_FLG = 'Y';
        BPCSFECT.PAY_METH = 'P';
        BPCSFECT.PAY_IND = 'R';
        BPCSFECT.ACU_TYP = 'F';
        BPCSFECT.START_DT = WS_START_DATE;
        BPCSFECT.MATURITY_DT = WS_END_DATE;
        if (BPCSFECT.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            BPCSFECT.CT_STS = '0';
        } else {
            if (BPCSFECT.MATURITY_DT != BPCSFECT.START_DT) {
                BPCSFECT.CT_STS = '1';
            } else {
                BPCSFECT.CT_STS = '2';
            }
        }
        BPCSFECT.RLTD_CT_TYP = "" + BPB9181_AWA_9181.REL_SRC;
        JIBS_tmp_int = BPCSFECT.RLTD_CT_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCSFECT.RLTD_CT_TYP = "0" + BPCSFECT.RLTD_CT_TYP;
        BPCSFECT.RLTD_CTNO = BPB9181_AWA_9181.REL_CTRT;
        BPCSFECT.CHG_METH = BPB9181_AWA_9181.AC_TYP;
        if (BPB9181_AWA_9181.AC_TYP == '4' 
            || BPB9181_AWA_9181.AC_TYP == '5') {
            BPCSFECT.CP_NO = BPB9181_AWA_9181.CHG_MEDI;
        } else {
            BPCSFECT.CHG_ACNO = BPB9181_AWA_9181.CHG_MEDI;
        }
        BPCSFECT.CHG_CCY_REAL = BPB9181_AWA_9181.CHG_CCY;
        BPCSFECT.CHG_CCY = BPB9181_AWA_9181.CHG_CCY;
        BPCSFECT.CCY_TYPE = BPB9181_AWA_9181.CCY_TYPE;
        if (BPB9181_AWA_9181.AMT_TERM != 0) {
            BPCSFECT.CHG_AMT = BPB9181_AWA_9181.AMT_TERM;
            BPCSFECT.CHG_AMT_REAL = BPB9181_AWA_9181.AMT_TERM;
            BPCSFECT.FEE_BAS_AMT = BPB9181_AWA_9181.AMT_TERM;
        } else {
            BPCSFECT.CHG_AMT = WS_AMT_TERM;
            BPCSFECT.CHG_AMT_REAL = WS_AMT_TERM;
            BPCSFECT.FEE_BAS_AMT = WS_AMT_TERM;
        }
        CEP.TRC(SCCGWA, BPCSFECT.CHG_AMT);
        BPCSFECT.SALE_DT = BPB9181_AWA_9181.SALE_DT;
        BPCSFECT.CREV_NO = BPB9181_AWA_9181.CREV_NO;
        BPCSFECT.REMARK = BPB9181_AWA_9181.FEE_RMK;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        BPCOFCTR.FEE_CODE = BPB9181_AWA_9181.FEE_CODE;
        BPCOFCTR.FEE_DESC = BPB9181_AWA_9181.FEE_DESC;
        BPCOFCTR.CI_NO = BPB9181_AWA_9181.CI_NO;
        BPCOFCTR.REL_SRC = BPB9181_AWA_9181.REL_SRC;
        BPCOFCTR.REL_CTRT = BPB9181_AWA_9181.REL_CTRT;
        BPCOFCTR.AC_TYP = BPB9181_AWA_9181.AC_TYP;
        BPCOFCTR.CHG_MEDIUM = BPB9181_AWA_9181.CHG_MEDI;
        BPCOFCTR.CHG_CCY = BPB9181_AWA_9181.CHG_CCY;
        BPCOFCTR.CCY_TYPE = BPB9181_AWA_9181.CCY_TYPE;
        if (BPB9181_AWA_9181.AMT_TERM != 0) {
            BPCOFCTR.CHG_AMT = BPB9181_AWA_9181.AMT_TERM;
        } else {
            BPCOFCTR.CHG_AMT = WS_AMT_TERM;
        }
        BPCOFCTR.CTRT_CNT_TOT = WS_TOT_CNT;
        CEP.TRC(SCCGWA, BPCOFCTR);
        CEP.TRC(SCCGWA, BPCOFCTR.CHG_AMT);
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFCTR;
        SCCFMT.DATA_LEN = 1213;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT, true);
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC.RC_CODE);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL SCSSCLDT FAIL, RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
