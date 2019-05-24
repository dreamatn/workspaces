package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSLNQ {
    DBParm LNTAPRD_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPP43";
    LNZSLNQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSLNQ_WS_TEMP_VARIABLE();
    String WS_CI_ENAME = " ";
    String WS_CI_CNAME = " ";
    char WS_FILLER = 0X02;
    double WS_TOT_BAL = 0;
    String WS_EX_B_CCY = " ";
    double WS_EX_B_AMT = 0;
    String WS_EX_S_CCY = " ";
    double WS_EX_S_AMT = 0;
    char WS_STS = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    BPCEX BPCEX = new BPCEX();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNRAPRD LNRAPRD = new LNRAPRD();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSLNQ LNCSLNQ;
    public void MP(SCCGWA SCCGWA, LNCSLNQ LNCSLNQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLNQ = LNCSLNQ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLNQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROCESS() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_INQUIRE_PROC();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSLNQ.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_INQUIRE_PROC() throws IOException,SQLException,Exception {
        B210_GET_LOAN_INF();
        if (pgmRtn) return;
        B300_GET_CI_INF();
        if (pgmRtn) return;
        B220_GET_ICTL_INF();
        if (pgmRtn) return;
        B230_READ_LNTAPRD();
        if (pgmRtn) return;
    }
    public void B210_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSLNQ.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B220_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.SUF_NO);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSLNQ.COMM_DATA.LN_AC;
        if (LNCSLNQ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCSLNQ.COMM_DATA.SUF_NO);
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B230_READ_LNTAPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSLNQ.COMM_DATA.LN_AC;
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        LNCSLNQ.COMM_DATA.ENAME = WS_CI_ENAME;
        LNCSLNQ.COMM_DATA.CNAME = WS_CI_CNAME;
        LNCSLNQ.COMM_DATA.BR = LNCCONTM.REC_DATA.BOOK_BR;
        LNCSLNQ.COMM_DATA.PRD_CD = LNCCONTM.REC_DATA.PROD_CD;
        LNCSLNQ.COMM_DATA.PRIN = LNCCONTM.REC_DATA.ORIG_TOT_AMT;
        CEP.TRC(SCCGWA, "222");
        BPCRBANK.LOC_CCY1 = LNCCONTM.REC_DATA.CCY;
        if (!LNCCONTM.REC_DATA.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            IBS.init(SCCGWA, BPCEX);
            WS_EX_S_CCY = LNCCONTM.REC_DATA.CCY;
            WS_EX_B_CCY = BPCRBANK.LOC_CCY1;
            WS_EX_B_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT;
            BPCEX.SELL_CCY = WS_EX_S_CCY;
            BPCEX.BUY_CCY = WS_EX_B_CCY;
            BPCEX.BUY_AMT = WS_EX_B_AMT;
            S000_LINK_BPZSEX();
            if (pgmRtn) return;
            WS_EX_S_AMT = BPCEX.SELL_AMT;
        } else {
            WS_EX_S_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT;
        }
        CEP.TRC(SCCGWA, "333");
        LNCSLNQ.COMM_DATA.PRIN_EQU = WS_EX_S_AMT;
        CEP.TRC(SCCGWA, "4441");
        LNCSLNQ.COMM_DATA.VAL_DTE = LNCCONTM.REC_DATA.START_DATE;
        CEP.TRC(SCCGWA, "4442");
        LNCSLNQ.COMM_DATA.DUE_DTE = LNCCONTM.REC_DATA.MAT_DATE;
        CEP.TRC(SCCGWA, "4443");
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.INT_RATE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
        CEP.TRC(SCCGWA, "4444");
        LNCSLNQ.COMM_DATA.INT_RATE = LNCICTLM.REC_DATA.CUR_RAT;
        CEP.TRC(SCCGWA, "444");
        LNCSLNQ.COMM_DATA.OVD_RATE = LNCICTLM.REC_DATA.CUR_PO_RAT;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CRT_DATE);
        LNCSLNQ.COMM_DATA.OPEN_DT = LNCCONTM.REC_DATA.CRT_DATE;
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.OPEN_DT);
        CEP.TRC(SCCGWA, "555");
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            B310_INQ_NOR_BAL();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.N_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
            B310_INQ_OVR_BAL();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.O_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
        }
        CEP.TRC(SCCGWA, "666");
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.N_BAL);
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.N_BAL);
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.O_BAL);
        CEP.TRC(SCCGWA, WS_TOT_BAL);
        WS_TOT_BAL = LNCSLNQ.COMM_DATA.N_BAL + LNCSLNQ.COMM_DATA.O_BAL;
        CEP.TRC(SCCGWA, WS_TOT_BAL);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSLNQ.COMM_DATA.TOT_BAL = WS_TOT_BAL;
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                LNCSLNQ.COMM_DATA.TOT_BAL = 0;
            } else {
                LNCSLNQ.COMM_DATA.TOT_BAL = LNCCONTM.REC_DATA.LN_TOT_AMT;
            }
        }
        LNCSLNQ.COMM_DATA.PAY_AMT = LNCSLNQ.COMM_DATA.PRIN - LNCSLNQ.COMM_DATA.TOT_BAL;
        LNCSLNQ.COMM_DATA.AC_CCY = LNCCONTM.REC_DATA.CCY;
        LNCSLNQ.COMM_DATA.LN_STS = "00000000";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCSLNQ.COMM_DATA.LN_STS == null) LNCSLNQ.COMM_DATA.LN_STS = "";
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.LN_STS.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.LN_STS += " ";
        LNCSLNQ.COMM_DATA.LN_STS = LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1) + LNCSLNQ.COMM_DATA.LN_STS.substring(1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCSLNQ.COMM_DATA.LN_STS == null) LNCSLNQ.COMM_DATA.LN_STS = "";
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.LN_STS.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.LN_STS += " ";
        LNCSLNQ.COMM_DATA.LN_STS = LNCSLNQ.COMM_DATA.LN_STS.substring(0, 2 - 1) + LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1) + LNCSLNQ.COMM_DATA.LN_STS.substring(2 + 1 - 1);
        LNCSLNQ.COMM_DATA.START_TE = LNCICTLM.REC_DATA.P_CMP_TERM;
        LNCSLNQ.COMM_DATA.STRIN_TE = LNCICTLM.REC_DATA.IC_CMP_TERM;
        LNCSLNQ.COMM_DATA.IC_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNCSLNQ.COMM_DATA.IC_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        LNCSLNQ.COMM_DATA.IC_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            B330_INQ_LOAN_INT();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.TMPINT = LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
            B340_INQ_PAY_DUEINT();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.DUEINT = LNCCNEX.COMM_DATA.INQ_AMT;
            B350_INQ_PAY_RCVINT();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.RCVINT = LNCCNEX.COMM_DATA.INQ_AMT;
            B355_INQ_INT_ACCR();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.INT_ACCR = LNCCNEX.COMM_DATA.INQ_AMT;
            B340_INQ_PAY_DUELC();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.DUELC = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT + LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT;
            LNCSLNQ.COMM_DATA.DUELI = LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT + LNCCNEX.COMM_DATA.CTNR_DATA[4-1].CTNR_AMT;
            B350_INQ_PAY_RCVLC();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.RCVLC = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
            LNCSLNQ.COMM_DATA.RCVLI = LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT;
        }
        LNCSLNQ.COMM_DATA.ACCRUAL_TYPE = LNRAPRD.ACCRUAL_TYPE;
        LNCSLNQ.COMM_DATA.RB_SPRD01 = LNCICTLM.REC_DATA.CUR_RAT;
        LNCSLNQ.COMM_DATA.LC_RATE = LNCICTLM.REC_DATA.CUR_PO_RAT;
        LNCSLNQ.COMM_DATA.REP_OPT = 'T';
        LNCSLNQ.COMM_DATA.REP_PRD = "" + LNRAPRD.CAL_PERD;
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.REP_PRD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.REP_PRD = "0" + LNCSLNQ.COMM_DATA.REP_PRD;
        LNCSLNQ.COMM_DATA.REP_UNIT = LNRAPRD.CAL_PERD_UNIT;
        LNCSLNQ.COMM_DATA.INSAMT = 0;
        LNCSLNQ.COMM_DATA.EFFP_DTE = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        LNCSLNQ.COMM_DATA.MAT_DTE = LNCCONTM.REC_DATA.MAT_DATE;
        LNCSLNQ.COMM_DATA.FMAT_DTE = LNCCONTM.REC_DATA.MAT_DATE;
        LNCSLNQ.COMM_DATA.PAYI_PRD = "" + 1;
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.PAYI_PRD.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.PAYI_PRD = "0" + LNCSLNQ.COMM_DATA.PAYI_PRD;
        LNCSLNQ.COMM_DATA.PAYIUNIT = 'M';
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            B370_GEN_LOAN_STS();
            if (pgmRtn) return;
            LNCSLNQ.COMM_DATA.LOAN_STATUS = WS_STS;
            CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.LOAN_STATUS);
        }
    }
    public void B370_GEN_LOAN_STS() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS = 'M';
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if ((LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1"))) {
                WS_STS = 'A';
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(08 - 1, 08 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_STS = 'X';
                } else {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_STS = 'P';
                        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                        if (LNCICTLM.REC_DATA.CTL_STSW.substring(35 - 1, 35 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_STS = 'B';
                        }
                    }
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_STS = 'C';
                    }
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_STS = 'S';
                    }
                }
            } else {
                WS_STS = 'U';
            }
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS = 'D';
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(33 - 1, 33 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS = 'E';
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS = 'F';
        }
    }
    public void B300_GET_CI_INF() throws IOException,SQLException,Exception {
    }
    public void B330_INQ_LOAN_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQTMPI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCSLNQ.COMM_DATA.LN_AC;
        if (LNCSLNQ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCSLNQ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
    }
    public void B310_INQ_NOR_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQNORP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.SUF_NO);
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B310_INQ_OVR_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B320_INQ_PAY_PRIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPRIN";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B340_INQ_PAY_DUEINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDUEI";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B350_INQ_PAY_RCVINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQRCVI";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B355_INQ_INT_ACCR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQACCR";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B340_INQ_PAY_DUELC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDUEL";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B350_INQ_PAY_RCVLC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQRCVL";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B340_INQ_PAY_DUELI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPPEI";
        LNCCNEX.COMM_DATA.LN_AC = LNCSLNQ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCSLNQ.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCSLNQ.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            LNCSLNQ.RC.RC_APP = LNCICNQ.RC.RC_APP;
            LNCSLNQ.RC.RC_RTNCODE = LNCICNQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            LNCSLNQ.RC.RC_APP = BPCEX.RC.RC_MMO;
            LNCSLNQ.RC.RC_RTNCODE = BPCEX.RC.RC_CODE;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSLNQ=");
            CEP.TRC(SCCGWA, LNCSLNQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
