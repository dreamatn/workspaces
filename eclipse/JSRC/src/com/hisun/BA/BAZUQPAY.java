package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUQPAY {
    BAZUQPAY_WS_OUT_INFO WS_OUT_INFO;
    brParm BATMST1_BR = new brParm();
    DBParm BATMST1_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BAZUQPAY_WS_VARIABLES WS_VARIABLES = new BAZUQPAY_WS_VARIABLES();
    BAZUQPAY_WS_OUT_RECODE WS_OUT_RECODE = new BAZUQPAY_WS_OUT_RECODE();
    BAZUQPAY_WS_COND_FLG WS_COND_FLG = new BAZUQPAY_WS_COND_FLG();
    BAZUQPAY_WS_DB_VARS WS_DB_VARS = new BAZUQPAY_WS_DB_VARS();
    BARMST1 BARMST1 = new BARMST1();
    BACMSG_ERROR_MSG ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BACFMST1 BACFMST1 = new BACFMST1();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BACUQPAY BACUQPAY;
    public void MP(SCCGWA SCCGWA, BACUQPAY BACUQPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUQPAY = BACUQPAY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUQPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DB_VARS);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_BANK_RIGHT();
        if (pgmRtn) return;
        B050_STARTBR_MST1();
        if (pgmRtn) return;
        B050_READNEXT_MST1();
        if (pgmRtn) return;
        if (BPCPQORG.LVL != '2' 
            && WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN != 100000) {
            while (WS_VARIABLES.WS_TEMP_VARIABLE.COUNT < BPCPORLO.SUB_NUM) {
                B200_STARTBR_BRANCH();
                if (pgmRtn) return;
                B050_READNEXT_MST1();
                if (pgmRtn) return;
            }
        }
        B100_ENDBR_BATMST1();
        if (pgmRtn) return;
        B200_GROUP_BANK();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.BAL_CNT = WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM % WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW;
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM - WS_VARIABLES.WS_TEMP_VARIABLE.BAL_CNT) / WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW);
            if (WS_VARIABLES.WS_TEMP_VARIABLE.BAL_CNT != 0) {
                WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_PAGE += 1;
            }
            if (WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE == WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_PAGE) {
                WS_VARIABLES.WS_TEMP_VARIABLE.LAST_PAGE = 'Y';
                if (WS_VARIABLES.WS_TEMP_VARIABLE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW = (short) WS_VARIABLES.WS_TEMP_VARIABLE.BAL_CNT;
                    WS_OUT_INFO = new BAZUQPAY_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                }
            }
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_TEMP_VARIABLE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW = 0;
            WS_OUT_INFO = new BAZUQPAY_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_TEMP_VARIABLE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        B100_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B000_CHECK_BANK_RIGHT() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN == 188000) {
            if ("100000".trim().length() == 0) WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN = 0;
            else WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN = Integer.parseInt("100000");
        }
        if (WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN != 100000) {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN;
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        }
        if (BPCPORLO.SUB_NUM != 0) {
            BPCPQORG.LVL = '6';
            B000_GET_BANK();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN = BPCPQORG.BRANCH_BR;
            if (BPCPQORG.LVL != '2' 
                && BPCPQORG.LVL != '9') {
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = BPCPQORG.BRANCH_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                if (BPCPORLO.SUB_NUM != 0) {
                    B000_GET_BANK();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B000_GET_BANK() throws IOException,SQLException,Exception {
        WS_COND_FLG.OVER = 'N';
        while (WS_COND_FLG.OVER != 'Y') {
            for (WS_VARIABLES.WS_TEMP_VARIABLE.INDEX = 1; WS_VARIABLES.WS_TEMP_VARIABLE.INDEX <= BPCPORLO.SUB_NUM; WS_VARIABLES.WS_TEMP_VARIABLE.INDEX += 1) {
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.INDEX);
                WS_VARIABLES.WS_TEMP_VARIABLE.CNTB = (short) (WS_VARIABLES.WS_TEMP_VARIABLE.CNTA + WS_VARIABLES.WS_TEMP_VARIABLE.INDEX);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.CNTB);
                BPCPORLO.BR = BPCPORLO.SUB_BR_DATA[WS_VARIABLES.WS_TEMP_VARIABLE.INDEX-1].SUB_BR;
                WS_DB_VARS.WS_BR[WS_VARIABLES.WS_TEMP_VARIABLE.CNTB-1].TX_BR = BPCPORLO.SUB_BR_DATA[WS_VARIABLES.WS_TEMP_VARIABLE.INDEX-1].SUB_BR;
                CEP.TRC(SCCGWA, WS_DB_VARS.WS_BR[WS_VARIABLES.WS_TEMP_VARIABLE.CNTB-1].TX_BR);
            }
            WS_VARIABLES.WS_TEMP_VARIABLE.CNTA = (short) (WS_VARIABLES.WS_TEMP_VARIABLE.CNTA + BPCPORLO.SUB_NUM);
            if (BPCPORLO.NEXT_CALL_FLG == 'Y') {
                WS_VARIABLES.WS_TEMP_VARIABLE.LAST_BR = 0;
                WS_VARIABLES.WS_TEMP_VARIABLE.LAST_BR = BPCPORLO.LAST_BR;
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN;
                BPCPORLO.LAST_BR = WS_VARIABLES.WS_TEMP_VARIABLE.LAST_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            } else {
                WS_COND_FLG.OVER = 'Y';
            }
        }
        BPCPORLO.SUB_NUM = WS_VARIABLES.WS_TEMP_VARIABLE.CNTB;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.CNTB);
    }
    public void B050_STARTBR_MST1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (BACUQPAY.DATA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE = (short) BACUQPAY.DATA.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE;
        WS_VARIABLES.WS_TEMP_VARIABLE.LAST_PAGE = 'N';
        WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW = (short) BACUQPAY.DATA.PAGE_ROW;
        WS_OUT_INFO = new BAZUQPAY_WS_OUT_INFO();
        WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.LAST_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM);
        WS_DB_VARS.AMT_STS = '3';
        WS_DB_VARS.BILL_STS = '5';
        WS_DB_VARS.ACPT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_DB_VARS.DRWR_AC = BACUQPAY.DATA.DRWR_AC;
        WS_DB_VARS.REM_AMT = 0;
        WS_DB_VARS.CNTR_NO = BACUQPAY.DATA.CNTR_NO;
        CEP.TRC(SCCGWA, WS_DB_VARS.ACPT_BR);
        if (WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN == 100000) {
            B200_STARTBR_ALL();
            if (pgmRtn) return;
        } else {
            if (BPCPQORG.LVL == '2') {
                B200_STARTBR_SINGLE();
                if (pgmRtn) return;
            } else {
                B200_STARTBR_BRANCH();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_STARTBR_ALL() throws IOException,SQLException,Exception {
        if (BACUQPAY.DATA.CNTR_NO.trim().length() == 0) {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
                + "AND CNTR_NO = :WS_DB_VARS.CNTR_NO";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B200_STARTBR_SINGLE() throws IOException,SQLException,Exception {
        if (BACUQPAY.DATA.CNTR_NO.trim().length() == 0) {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
                + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
                + "AND CNTR_NO = :WS_DB_VARS.CNTR_NO "
                + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B200_STARTBR_BRANCH() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.COUNT += 1;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.COUNT <= BPCPORLO.SUB_NUM) {
            WS_DB_VARS.ACPT_BR = WS_DB_VARS.WS_BR[WS_VARIABLES.WS_TEMP_VARIABLE.COUNT-1].TX_BR;
        }
        CEP.TRC(SCCGWA, WS_DB_VARS.ACPT_BR);
        if (BACUQPAY.DATA.CNTR_NO.trim().length() == 0) {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
                + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
                + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
                + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
                + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
                + "AND CNTR_NO = :WS_DB_VARS.CNTR_NO "
                + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B050_READNEXT_MST1() throws IOException,SQLException,Exception {
        B000_READNEXT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            while ((WS_VARIABLES.WS_TEMP_VARIABLE.IDX <= WS_VARIABLES.WS_TEMP_VARIABLE.PAGE_ROW) 
                && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                WS_VARIABLES.WS_TEMP_VARIABLE.IDX += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.IDX);
                IBS.init(SCCGWA, WS_OUT_INFO);
                WS_OUT_INFO.F_BILLNO = BARMST1.BILL_NO;
                WS_OUT_INFO.F_CNTRNO = BARMST1.KEY.CNTR_NO;
                WS_OUT_INFO.F_ACCCNT = BARMST1.KEY.ACCT_CNT;
                WS_OUT_INFO.F_REMAMT = BARMST1.REM_AMT;
                WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM;
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_TEMP_VARIABLE.IDX-1).F_BILLNO);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_TEMP_VARIABLE.IDX-1).F_CNTRNO);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_TEMP_VARIABLE.IDX-1).F_ACCCNT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_TEMP_VARIABLE.IDX-1).F_REMAMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.NEXT_START_NUM);
                CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
                B000_READNEXT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_GROUP_BANK() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.COUNT = 0;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN == 100000) {
            B200_GROUP_BANK_ALL();
            if (pgmRtn) return;
        } else {
            if (BPCPQORG.LVL == '2') {
                B200_GROUP_BANK_SINGLE();
                if (pgmRtn) return;
            } else {
                B200_GROUP_BANK_BRANCH();
                if (pgmRtn) return;
            }
        }
        if (BPCPQORG.LVL != '2' 
            && WS_VARIABLES.WS_TEMP_VARIABLE.TR_BRAN != 100000) {
            while (WS_VARIABLES.WS_TEMP_VARIABLE.COUNT < BPCPORLO.SUB_NUM) {
                B200_GROUP_BANK_BRANCH();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_GROUP_BANK_ALL() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.set = "WS-CNT=COUNT(*)";
        BATMST1_RD.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
            + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
            + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
            + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
            + "AND ( :WS_DB_VARS.CNTR_NO = ' ' "
            + "OR CNTR_NO = :WS_DB_VARS.CNTR_NO )";
        IBS.GROUP(SCCGWA, BARMST1, this, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM = WS_DB_VARS.CNT;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM);
        }
    }
    public void B200_GROUP_BANK_SINGLE() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.set = "WS-CNT=COUNT(*)";
        BATMST1_RD.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
            + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
            + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
            + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
            + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR "
            + "AND ( :WS_DB_VARS.CNTR_NO = ' ' "
            + "OR CNTR_NO = :WS_DB_VARS.CNTR_NO )";
        IBS.GROUP(SCCGWA, BARMST1, this, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM = WS_DB_VARS.CNT;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM);
        }
    }
    public void B200_GROUP_BANK_BRANCH() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.COUNT += 1;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.COUNT <= BPCPORLO.SUB_NUM) {
            WS_DB_VARS.ACPT_BR = WS_DB_VARS.WS_BR[WS_VARIABLES.WS_TEMP_VARIABLE.COUNT-1].TX_BR;
        }
        CEP.TRC(SCCGWA, WS_DB_VARS.ACPT_BR);
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.set = "WS-CNT=COUNT(*)";
        BATMST1_RD.where = "DRWR_AC = :WS_DB_VARS.DRWR_AC "
            + "AND AMT_STS = :WS_DB_VARS.AMT_STS "
            + "AND REM_AMT > :WS_DB_VARS.REM_AMT "
            + "AND BILL_STS < > :WS_DB_VARS.BILL_STS "
            + "AND ACPT_BR = :WS_DB_VARS.ACPT_BR "
            + "AND ( :WS_DB_VARS.CNTR_NO = ' ' "
            + "OR CNTR_NO = :WS_DB_VARS.CNTR_NO )";
        IBS.GROUP(SCCGWA, BARMST1, this, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM1 = WS_DB_VARS.CNT;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM1);
            WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM = WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM + WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM1;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TOTAL_NUM);
        }
    }
    public void B000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BARMST1, this, BATMST1_BR);
    }
    public void B100_ENDBR_BATMST1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BATMST1_BR);
    }
    public void B100_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BAS04";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 1744;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
