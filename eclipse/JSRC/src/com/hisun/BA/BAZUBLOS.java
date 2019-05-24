package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUBLOS {
    BAZUBLOS_WS_OUT_INFO WS_OUT_INFO;
    brParm BATMST1_BR = new brParm();
    brParm BATLOSS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short PAGE_ROW = 10;
    BAZUBLOS_WS_VARIABLES WS_VARIABLES = new BAZUBLOS_WS_VARIABLES();
    BAZUBLOS_WS_OUT_RECODE WS_OUT_RECODE = new BAZUBLOS_WS_OUT_RECODE();
    BAZUBLOS_WS_OCCURS1 WS_OCCURS1 = new BAZUBLOS_WS_OCCURS1();
    BAZUBLOS_WS_DB_VARS WS_DB_VARS = new BAZUBLOS_WS_DB_VARS();
    BAZUBLOS_WS_OCCURS2 WS_OCCURS2 = new BAZUBLOS_WS_OCCURS2();
    BAZUBLOS_WS_COND_FLG WS_COND_FLG = new BAZUBLOS_WS_COND_FLG();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BARDPAY BARDPAY = new BARDPAY();
    BARLOSS BARLOSS = new BARLOSS();
    BARCLCT BARCLCT = new BARCLCT();
    BACMSG_ERROR_MSG ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFDPAY BACFDPAY = new BACFDPAY();
    BACFLOSS BACFLOSS = new BACFLOSS();
    BACFCLCT BACFCLCT = new BACFCLCT();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BACUBLOS BACUBLOS;
    public void MP(SCCGWA SCCGWA, BACUBLOS BACUBLOS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUBLOS = BACUBLOS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUBLOS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, WS_OCCURS2);
        BACUBLOS.RC.RC_MMO = "BA";
        BACUBLOS.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BACUBLOS.DATA.TX_BR);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.TX_BR_LEV);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.BILL_NO);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.STP_STS);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.S_STP_DT);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.E_STP_DT);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.S_REM_DT);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.E_REM_DT);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.S_R_SEQ);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.E_R_SEQ);
        CEP.TRC(SCCGWA, BACUBLOS.DATA.RMK);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACUBLOS.DATA.FUN_COD == '0' 
            || BACUBLOS.DATA.FUN_COD == '3') {
            B000_CHECK_BANK_RIGHT();
            if (pgmRtn) return;
        }
        B000_DATE_DEAL_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK_BANK_RIGHT() throws IOException,SQLException,Exception {
        WS_VARIABLES.TR_BRAN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (WS_VARIABLES.TR_BRAN == 188000) {
            if ("100000".trim().length() == 0) WS_VARIABLES.TR_BRAN = 0;
            else WS_VARIABLES.TR_BRAN = Integer.parseInt("100000");
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.TR_BRAN);
        if (WS_VARIABLES.TR_BRAN != 100000) {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = WS_VARIABLES.TR_BRAN;
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
            BPCPQORG.BR = WS_VARIABLES.TR_BRAN;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            WS_VARIABLES.TR_BRAN = BPCPQORG.BRANCH_BR;
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
        BACUBLOS.DATA.TX_BR_LEV = BPCPQORG.LVL;
    }
    public void B000_DATE_DEAL_PROC() throws IOException,SQLException,Exception {
        if (BACUBLOS.DATA.E_STP_DT == 0) {
            BACUBLOS.DATA.E_STP_DT = 99991231;
        }
        if (BACUBLOS.DATA.E_REM_DT == 0) {
            BACUBLOS.DATA.E_REM_DT = 99991231;
        }
        if (BACUBLOS.DATA.PAG_ROW == 0) {
            BACUBLOS.DATA.PAG_ROW = 20;
        }
    }
    public void B000_GET_BANK() throws IOException,SQLException,Exception {
        WS_COND_FLG.OVER = 'N';
        while (WS_COND_FLG.OVER != 'Y' 
            && WS_VARIABLES.CNTB <= 1000) {
            for (WS_VARIABLES.INDEX = 1; WS_VARIABLES.INDEX <= BPCPORLO.SUB_NUM 
                && WS_VARIABLES.CNTB <= 1000; WS_VARIABLES.INDEX += 1) {
                CEP.TRC(SCCGWA, WS_VARIABLES.INDEX);
                WS_VARIABLES.CNTB = (short) (WS_VARIABLES.CNTA + WS_VARIABLES.INDEX);
                CEP.TRC(SCCGWA, WS_VARIABLES.CNTB);
                BPCPORLO.BR = BPCPORLO.SUB_BR_DATA[WS_VARIABLES.INDEX-1].SUB_BR;
                if (WS_VARIABLES.CNTB <= 1000) {
                    WS_OCCURS2.WS_BR[WS_VARIABLES.CNTB-1].TX_BR = BPCPORLO.SUB_BR_DATA[WS_VARIABLES.INDEX-1].SUB_BR;
                }
            }
            WS_VARIABLES.CNTA = WS_VARIABLES.CNTA + BPCPORLO.SUB_NUM;
            if (BPCPORLO.NEXT_CALL_FLG == 'Y' 
                && WS_VARIABLES.CNTB < 1000) {
                WS_VARIABLES.LAST_BR = 0;
                WS_VARIABLES.LAST_BR = BPCPORLO.LAST_BR;
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = WS_VARIABLES.TR_BRAN;
                BPCPORLO.LAST_BR = WS_VARIABLES.LAST_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            } else {
                WS_COND_FLG.OVER = 'Y';
            }
        }
        BPCPORLO.SUB_NUM = WS_VARIABLES.CNTB;
        CEP.TRC(SCCGWA, WS_VARIABLES.CNTB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (BACUBLOS.DATA.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = (short) BACUBLOS.DATA.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, BACUBLOS.DATA.PAGE_ROW);
        if (BACUBLOS.DATA.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new BAZUBLOS_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (BACUBLOS.DATA.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BA_ERR_PAGE_ROW, BACUBLOS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.PAGE_ROW = (short) BACUBLOS.DATA.PAGE_ROW;
                WS_OUT_INFO = new BAZUBLOS_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        if (BACUBLOS.DATA.FUN_COD == '0' 
            || BACUBLOS.DATA.FUN_COD == '3') {
            B110_STARTBR_MST1();
            if (pgmRtn) return;
            B120_READNEXT_MST1();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BACUBLOS.DATA.TX_BR_LEV);
            CEP.TRC(SCCGWA, BACUBLOS.DATA.TX_BR);
            if (BACUBLOS.DATA.TX_BR_LEV != '2' 
                && BACUBLOS.DATA.TX_BR != 100000 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BACUBLOS.DATA.TX_BR 
                && BACUBLOS.DATA.BILL_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, WS_VARIABLES.CNTA);
                CEP.TRC(SCCGWA, WS_VARIABLES.COUNT);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAA1");
                while (WS_VARIABLES.COUNT < BPCPORLO.SUB_NUM) {
                    B160_STARTBR_BRANCH();
                    if (pgmRtn) return;
                    B120_READNEXT_MST1();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "ADDFDGF");
                }
            }
            B130_ENDBR_MST1();
            if (pgmRtn) return;
        }
        if (BACUBLOS.DATA.FUN_COD == '1') {
            IBS.init(SCCGWA, BACFMST1);
            IBS.init(SCCGWA, BARMST1);
            BACFMST1.FUNC = 'L';
            BARMST1.BILL_NO = BACUBLOS.DATA.BILL_NO;
            WS_DB_VARS.BILL_NO2 = BACUBLOS.DATA.BILL_NO;
            CEP.TRC(SCCGWA, "00000010");
            S000_CALL_BAZFMST1();
            if (pgmRtn) return;
            if (BACFMST1.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_MST1_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B210_STARTBR_BATLOSS();
            if (pgmRtn) return;
            B220_READNEXT_BATLOSS();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                WS_VARIABLES.TOTAL_NUM += 1;
                if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                    && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                    WS_VARIABLES.IDX += 1;
                    IBS.init(SCCGWA, WS_OUT_INFO);
                    WS_OUT_INFO.ACPT_BR = BARMST1.ACPT_BR;
                    WS_OUT_INFO.BILL_NO = BARLOSS.BILL_NO;
                    WS_OUT_INFO.BILL_CUR = BARMST1.BILL_CUR;
                    WS_OUT_INFO.BILL_AMT = BARMST1.BILL_AMT;
                    WS_OUT_INFO.STP_STS = BARLOSS.LOSS_STS;
                    if (BARLOSS.LOSS_STS == '0') {
                        WS_OUT_INFO.TX_DT = BARLOSS.KEY.TX_DT;
                    } else {
                        WS_OUT_INFO.TX_DT = BARLOSS.UNSUP_DT;
                    }
                    WS_OUT_INFO.DRW_DT = BARMST1.ACPT_DT;
                    WS_OUT_INFO.MAT_DT = BARMST1.BILL_MAT_DT;
                    WS_OUT_INFO.STP_E_DT = BARLOSS.LOSS_ED_DT;
                    WS_OUT_INFO.SPPM_FLG = BARLOSS.SPPM_FLG;
                    WS_OUT_INFO.REC_KEY1 = BARLOSS.KEY.TX_DT;
                    WS_OUT_INFO.REC_KEY2 = BARLOSS.KEY.CRE_JRN;
                    WS_OUT_INFO.CRT_TLR = BARLOSS.CRT_TLR;
                    WS_OUT_INFO.RMK = BARLOSS.REMK;
                    WS_OUT_INFO.LOSS_FLG = 'Y';
                    B500_INQ_BATCLCT();
                    if (pgmRtn) return;
                    if (BACFCLCT.RC.RC_CODE == 0) {
                        WS_OUT_INFO.CLCT_FLG = 'Y';
                    } else {
                        WS_OUT_INFO.CLCT_FLG = 'N';
                    }
                }
                B220_READNEXT_BATLOSS();
                if (pgmRtn) return;
            }
            B230_ENDBR_BATLOSS();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.TOTAL_NUM != 0) {
            WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
            WS_VARIABLES.TOTAL_PAGE = (short) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
            if (WS_VARIABLES.BAL_CNT != 0) {
                WS_VARIABLES.TOTAL_PAGE += 1;
            }
            if (WS_VARIABLES.CURR_PAGE == WS_VARIABLES.TOTAL_PAGE) {
                WS_VARIABLES.LAST_PAGE = 'Y';
                if (WS_VARIABLES.BAL_CNT != 0) {
                    WS_VARIABLES.PAGE_ROW = (short) WS_VARIABLES.BAL_CNT;
                    WS_OUT_INFO = new BAZUBLOS_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.PAGE_ROW;
        } else {
            WS_VARIABLES.TOTAL_PAGE = 1;
            WS_VARIABLES.TOTAL_NUM = 0;
            WS_VARIABLES.LAST_PAGE = 'Y';
            WS_VARIABLES.PAGE_ROW = 0;
            WS_OUT_INFO = new BAZUBLOS_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_ROW);
        R010_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_MST1() throws IOException,SQLException,Exception {
        WS_DB_VARS.BILL_NO2 = BACUBLOS.DATA.BILL_NO;
        WS_DB_VARS.TX_BR2 = BACUBLOS.DATA.TX_BR;
        WS_VARIABLES.STP_STS1 = BACUBLOS.DATA.STP_STS;
        if (BACUBLOS.DATA.TX_BR == 100000) {
            B140_STARTBR_ALL();
            if (pgmRtn) return;
        } else {
            if (BACUBLOS.DATA.TX_BR_LEV == '2') {
                B150_STARTBR_SINGLE();
                if (pgmRtn) return;
            } else {
                B160_STARTBR_BRANCH();
                if (pgmRtn) return;
            }
        }
    }
    public void B140_STARTBR_ALL() throws IOException,SQLException,Exception {
        if (BACUBLOS.DATA.BILL_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "START");
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, BATMST1_BR);
            CEP.TRC(SCCGWA, "ALL");
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.where = "BILL_NO = :WS_DB_VARS.BILL_NO2";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B150_STARTBR_SINGLE() throws IOException,SQLException,Exception {
        if (BACUBLOS.DATA.BILL_NO.trim().length() == 0) {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.where = "ACPT_BR = :WS_DB_VARS.TX_BR2";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.where = "BILL_NO = :WS_DB_VARS.BILL_NO2";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B160_STARTBR_BRANCH() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BACUBLOS.DATA.TX_BR) {
            WS_DB_VARS.TX_BR2 = BACUBLOS.DATA.TX_BR;
        } else {
            WS_VARIABLES.COUNT += 1;
            if (WS_VARIABLES.COUNT <= BPCPORLO.SUB_NUM) {
                WS_DB_VARS.TX_BR2 = WS_OCCURS2.WS_BR[WS_VARIABLES.COUNT-1].TX_BR;
            }
        }
        CEP.TRC(SCCGWA, WS_DB_VARS.TX_BR2);
        if (BACUBLOS.DATA.BILL_NO.trim().length() == 0) {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.where = "ACPT_BR = :WS_DB_VARS.TX_BR2";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        } else {
            BATMST1_BR.rp = new DBParm();
            BATMST1_BR.rp.TableName = "BATMST1";
            BATMST1_BR.rp.col = "CNTR_NO,ACCT_CNT,BILL_NO,ACPT_DT, BILL_MAT_DT,BILL_CUR,BILL_AMT, DRWR_AC,ACPT_BR";
            BATMST1_BR.rp.where = "BILL_NO = :WS_DB_VARS.BILL_NO2";
            BATMST1_BR.rp.order = "CNTR_NO,ACCT_CNT";
            IBS.STARTBR(SCCGWA, BARMST1, this, BATMST1_BR);
        }
    }
    public void B120_READNEXT_MST1() throws IOException,SQLException,Exception {
        WS_COND_FLG.OVER = 'N';
        if (BACUBLOS.DATA.FUN_COD == '0') {
            while (WS_COND_FLG.OVER != 'Y' 
                && WS_VARIABLES.TOTAL_NUM != 500) {
                IBS.READNEXT(SCCGWA, BARMST1, this, BATMST1_BR);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_COND_FLG.OVER = 'Y';
                } else {
                    CEP.TRC(SCCGWA, BARMST1.BILL_STS);
                    B300_READ_LOSS_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            while (WS_COND_FLG.OVER != 'Y') {
                IBS.READNEXT(SCCGWA, BARMST1, this, BATMST1_BR);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_COND_FLG.OVER = 'Y';
                } else {
                    CEP.TRC(SCCGWA, BARMST1.BILL_STS);
                    B300_CHECK_NUM_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B130_ENDBR_MST1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BATMST1_BR);
    }
    public void B300_READ_LOSS_PROC() throws IOException,SQLException,Exception {
        B210_READ_LOSS_PROC();
        if (pgmRtn) return;
        if (BACFLOSS.RETURN_INFO == 'F') {
            if (BARLOSS.LOSS_STS == '0') {
                if (BACUBLOS.DATA.BILL_NO.trim().length() > 0 
                    && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                    || BACUBLOS.DATA.STP_STS == ' ')) {
                    WS_VARIABLES.TOTAL_NUM += 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                    CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                    CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                    if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                        && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                        WS_VARIABLES.IDX += 1;
                        WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                        B400_TRAN_DATA_OUTPUT();
                        if (pgmRtn) return;
                    }
                } else {
                    if (BACUBLOS.DATA.S_REM_DT != 0 
                        || BACUBLOS.DATA.E_REM_DT != 99991231) {
                    } else {
                        if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                            && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT 
                            && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                            || BACUBLOS.DATA.STP_STS == ' ')) {
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                            if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                                WS_VARIABLES.IDX += 1;
                                WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                                B400_TRAN_DATA_OUTPUT();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            } else {
                if (BACUBLOS.DATA.S_REM_DT <= BARLOSS.UNSUP_DT 
                    && BARLOSS.UNSUP_DT <= BACUBLOS.DATA.E_REM_DT 
                    && (((BARLOSS.LOSS_STS == '1' 
                    || BARLOSS.LOSS_STS == '2') 
                    && BACUBLOS.DATA.STP_STS == '1') 
                    || BACUBLOS.DATA.STP_STS == ' ')) {
                    if (BACUBLOS.DATA.S_STP_DT != 0 
                        || BACUBLOS.DATA.E_STP_DT != 99991231) {
                        WS_VARIABLES.CNTR_NO = BARLOSS.CNTR_NO;
                        WS_VARIABLES.ACCT_CNT = BARLOSS.ACCT_CNT;
                        WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                        WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                        WS_VARIABLES.SEQ = (short) (BARLOSS.SEQ - 1);
                        IBS.init(SCCGWA, BARLOSS);
                        IBS.init(SCCGWA, BACFLOSS);
                        BACFLOSS.FUNC = 'Z';
                        BARLOSS.SEQ = WS_VARIABLES.SEQ;
                        BARLOSS.CNTR_NO = WS_VARIABLES.CNTR_NO;
                        BARLOSS.ACCT_CNT = WS_VARIABLES.ACCT_CNT;
                        S000_CALL_BAZFLOSS();
                        if (pgmRtn) return;
                        if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                            && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT) {
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                            if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                                WS_VARIABLES.IDX += 1;
                                WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                                B300_TRAN_DATA_OUTPUT();
                                if (pgmRtn) return;
                            }
                        }
                    } else {
                        if (((BARLOSS.LOSS_STS == '1' 
                            || BARLOSS.LOSS_STS == '2') 
                            && BACUBLOS.DATA.STP_STS == '1') 
                            || BACUBLOS.DATA.STP_STS == ' ') {
                            WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                            WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                            if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                                WS_VARIABLES.IDX += 1;
                                WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                                B300_TRAN_DATA_OUTPUT();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
        }
    }
    public void B300_CHECK_NUM_PROC() throws IOException,SQLException,Exception {
        B210_READ_LOSS_PROC();
        if (pgmRtn) return;
        if (BACFLOSS.RETURN_INFO == 'F') {
            if (BARLOSS.LOSS_STS == '0') {
                if (BACUBLOS.DATA.BILL_NO.trim().length() > 0 
                    && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                    || BACUBLOS.DATA.STP_STS == ' ')) {
                    WS_VARIABLES.TOTAL_NUM += 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                    CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                } else {
                    if (BACUBLOS.DATA.S_REM_DT != 0 
                        || BACUBLOS.DATA.E_REM_DT != 99991231) {
                    } else {
                        if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                            && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT 
                            && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                            || BACUBLOS.DATA.STP_STS == ' ')) {
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                        }
                    }
                }
            } else {
                if (BACUBLOS.DATA.S_REM_DT <= BARLOSS.UNSUP_DT 
                    && BARLOSS.UNSUP_DT <= BACUBLOS.DATA.E_REM_DT 
                    && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                    || BACUBLOS.DATA.STP_STS == ' ')) {
                    if (BACUBLOS.DATA.S_STP_DT != 0 
                        || BACUBLOS.DATA.E_STP_DT != 99991231) {
                        WS_VARIABLES.CNTR_NO = BARLOSS.CNTR_NO;
                        WS_VARIABLES.ACCT_CNT = BARLOSS.ACCT_CNT;
                        WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                        WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                        WS_VARIABLES.SEQ = (short) (BARLOSS.SEQ - 1);
                        IBS.init(SCCGWA, BARLOSS);
                        IBS.init(SCCGWA, BACFLOSS);
                        BACFLOSS.FUNC = 'Z';
                        BARLOSS.SEQ = WS_VARIABLES.SEQ;
                        BARLOSS.CNTR_NO = WS_VARIABLES.CNTR_NO;
                        BARLOSS.ACCT_CNT = WS_VARIABLES.ACCT_CNT;
                        S000_CALL_BAZFLOSS();
                        if (pgmRtn) return;
                        if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                            && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT) {
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                        }
                    } else {
                        if (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                            || BACUBLOS.DATA.STP_STS == ' ') {
                            WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                            WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                        }
                    }
                }
            }
        }
    }
    public void B200_READ_LOSS_PROC() throws IOException,SQLException,Exception {
        B210_READ_LOSS_PROC();
        if (pgmRtn) return;
        if (BACFLOSS.RETURN_INFO == 'F') {
            if (BARLOSS.LOSS_STS == '0') {
                if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                    && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT 
                    && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                    || BACUBLOS.DATA.STP_STS == ' ')) {
                    WS_VARIABLES.TOTAL_NUM += 1;
                    WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                    CEP.TRC(SCCGWA, "DFFDGDD");
                    CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                    CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                    CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                    if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                        && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                        WS_VARIABLES.IDX += 1;
                        WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                        IBS.init(SCCGWA, WS_OUT_INFO);
                        WS_OUT_INFO.ACPT_BR = BARMST1.ACPT_BR;
                        WS_OUT_INFO.BILL_NO = BARLOSS.BILL_NO;
                        WS_OUT_INFO.BILL_CUR = BARMST1.BILL_CUR;
                        WS_OUT_INFO.BILL_AMT = BARMST1.BILL_AMT;
                        WS_OUT_INFO.STP_STS = '0';
                        WS_OUT_INFO.TX_DT = BARLOSS.KEY.TX_DT;
                        WS_OUT_INFO.DRW_DT = BARMST1.ACPT_DT;
                        WS_OUT_INFO.MAT_DT = BARMST1.BILL_MAT_DT;
                        WS_OUT_INFO.STP_E_DT = BARLOSS.LOSS_ED_DT;
                        WS_OUT_INFO.SPPM_FLG = BARLOSS.SPPM_FLG;
                        WS_OUT_INFO.REC_KEY1 = BARLOSS.KEY.TX_DT;
                        WS_OUT_INFO.REC_KEY2 = BARLOSS.KEY.CRE_JRN;
                        WS_OUT_INFO.CRT_TLR = BARLOSS.CRT_TLR;
                        WS_OUT_INFO.RMK = BARLOSS.REMK;
                    }
                }
            } else {
                if (BARLOSS.UNSUP_DT < BACUBLOS.DATA.S_REM_DT 
                    || BARLOSS.UNSUP_DT > BACUBLOS.DATA.E_REM_DT) {
                } else {
                    if (BACUBLOS.DATA.S_STP_DT != 0 
                        || BACUBLOS.DATA.E_STP_DT != 99991231) {
                        WS_VARIABLES.CNTR_NO = BARLOSS.CNTR_NO;
                        WS_VARIABLES.ACCT_CNT = BARLOSS.ACCT_CNT;
                        WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                        WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                        WS_VARIABLES.SEQ = (short) (BARLOSS.SEQ - 1);
                        IBS.init(SCCGWA, BARLOSS);
                        IBS.init(SCCGWA, BACFLOSS);
                        BACFLOSS.FUNC = 'Z';
                        BARLOSS.SEQ = WS_VARIABLES.SEQ;
                        BARLOSS.CNTR_NO = WS_VARIABLES.CNTR_NO;
                        BARLOSS.ACCT_CNT = WS_VARIABLES.ACCT_CNT;
                        S000_CALL_BAZFLOSS();
                        if (pgmRtn) return;
                        if (BARLOSS.LOSS_DT >= BACUBLOS.DATA.S_STP_DT 
                            && BARLOSS.LOSS_DT <= BACUBLOS.DATA.E_STP_DT 
                            && (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                            || BACUBLOS.DATA.STP_STS == ' ')) {
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                            if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                                WS_VARIABLES.IDX += 1;
                                WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                                B300_TRAN_DATA_OUTPUT();
                                if (pgmRtn) return;
                            }
                        }
                    } else {
                        if (BARLOSS.LOSS_STS == BACUBLOS.DATA.STP_STS 
                            || BACUBLOS.DATA.STP_STS == ' ') {
                            WS_VARIABLES.LOSS_STS = BARLOSS.LOSS_STS;
                            WS_VARIABLES.UNSUP_DT = BARLOSS.UNSUP_DT;
                            WS_VARIABLES.TOTAL_NUM += 1;
                            WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_TOT_AMT + BARMST1.BILL_AMT;
                            CEP.TRC(SCCGWA, "DFFDGDD");
                            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
                            CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
                            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
                            if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
                                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                                WS_VARIABLES.IDX += 1;
                                WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT = WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_AMT + BARMST1.BILL_AMT;
                                B300_TRAN_DATA_OUTPUT();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
        }
    }
    public void B210_READ_LOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOSS);
        BARLOSS.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARLOSS.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BACFLOSS.FUNC = 'L';
        CEP.TRC(SCCGWA, "AAAAAAA");
        S000_CALL_BAZFLOSS();
        if (pgmRtn) return;
    }
    public void B210_STARTBR_BATLOSS() throws IOException,SQLException,Exception {
        WS_DB_VARS.S_STP_DT = BACUBLOS.DATA.S_STP_DT;
        WS_DB_VARS.E_STP_DT = BACUBLOS.DATA.E_STP_DT;
        WS_DB_VARS.S_REM_DT = BACUBLOS.DATA.S_REM_DT;
        WS_DB_VARS.E_REM_DT = BACUBLOS.DATA.E_REM_DT;
        IBS.init(SCCGWA, BARLOSS);
        BARLOSS.LOSS_STS = BACUBLOS.DATA.STP_STS;
        if (BACUBLOS.DATA.STP_STS == ' ') {
            BATLOSS_BR.rp = new DBParm();
            BATLOSS_BR.rp.TableName = "BATLOSS";
            BATLOSS_BR.rp.where = "BILL_NO = :WS_DB_VARS.BILL_NO2 "
                + "AND LOSS_DT >= :WS_DB_VARS.S_STP_DT "
                + "AND LOSS_DT <= :WS_DB_VARS.E_STP_DT "
                + "AND UNSUP_DT >= :WS_DB_VARS.S_REM_DT "
                + "AND UNSUP_DT <= :WS_DB_VARS.E_REM_DT";
            BATLOSS_BR.rp.order = "CNTR_NO,ACCT_CNT,SEQ";
            IBS.STARTBR(SCCGWA, BARLOSS, this, BATLOSS_BR);
        } else {
            BATLOSS_BR.rp = new DBParm();
            BATLOSS_BR.rp.TableName = "BATLOSS";
            BATLOSS_BR.rp.where = "BILL_NO = :WS_DB_VARS.BILL_NO2 "
                + "AND LOSS_STS = :BARLOSS.LOSS_STS";
            BATLOSS_BR.rp.order = "CNTR_NO,ACCT_CNT,SEQ";
            IBS.STARTBR(SCCGWA, BARLOSS, this, BATLOSS_BR);
        }
    }
    public void B220_READNEXT_BATLOSS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BARLOSS, this, BATLOSS_BR);
    }
    public void B230_ENDBR_BATLOSS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BATLOSS_BR);
    }
    public void B500_INQ_BATCLCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        BARCLCT.CNTR_NO = BARLOSS.CNTR_NO;
        BARCLCT.ACCT_CNT = BARLOSS.ACCT_CNT;
        BARCLCT.KEY.TX_DT = BARLOSS.KEY.TX_DT;
        BACFCLCT.FUNC = 'M';
        S000_CALL_BAZFCLCT();
        if (pgmRtn) return;
    }
    public void B400_TRAN_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.ACPT_BR = BARMST1.ACPT_BR;
        WS_OUT_INFO.BILL_NO = BARLOSS.BILL_NO;
        WS_OUT_INFO.BILL_CUR = BARMST1.BILL_CUR;
        WS_OUT_INFO.BILL_AMT = BARMST1.BILL_AMT;
        WS_OUT_INFO.STP_STS = '0';
        WS_OUT_INFO.TX_DT = BARLOSS.KEY.TX_DT;
        WS_OUT_INFO.DRW_DT = BARMST1.ACPT_DT;
        WS_OUT_INFO.MAT_DT = BARMST1.BILL_MAT_DT;
        WS_OUT_INFO.STP_E_DT = BARLOSS.LOSS_ED_DT;
        WS_OUT_INFO.SPPM_FLG = BARLOSS.SPPM_FLG;
        WS_OUT_INFO.REC_KEY1 = BARLOSS.KEY.TX_DT;
        WS_OUT_INFO.REC_KEY2 = BARLOSS.KEY.CRE_JRN;
        WS_OUT_INFO.CRT_TLR = BARLOSS.CRT_TLR;
        WS_OUT_INFO.RMK = BARLOSS.REMK;
    }
    public void B300_TRAN_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.ACPT_BR = BARMST1.ACPT_BR;
        WS_OUT_INFO.BILL_NO = BARLOSS.BILL_NO;
        WS_OUT_INFO.BILL_CUR = BARMST1.BILL_CUR;
        WS_OUT_INFO.BILL_AMT = BARMST1.BILL_AMT;
        WS_OUT_INFO.STP_STS = WS_VARIABLES.LOSS_STS;
        WS_OUT_INFO.TX_DT = WS_VARIABLES.UNSUP_DT;
        WS_OUT_INFO.DRW_DT = BARMST1.ACPT_DT;
        WS_OUT_INFO.MAT_DT = BARMST1.BILL_MAT_DT;
        WS_OUT_INFO.STP_E_DT = BARLOSS.LOSS_ED_DT;
        WS_OUT_INFO.SPPM_FLG = BARLOSS.SPPM_FLG;
        WS_OUT_INFO.REC_KEY1 = BARLOSS.KEY.TX_DT;
        WS_OUT_INFO.REC_KEY2 = BARLOSS.KEY.CRE_JRN;
        WS_OUT_INFO.CRT_TLR = BARLOSS.CRT_TLR;
        WS_OUT_INFO.RMK = BARLOSS.REMK;
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
    }
    public void R010_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BAS01";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 6051;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 24140;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
    }
    public void S000_CALL_BAZFLOSS() throws IOException,SQLException,Exception {
        BACFLOSS.REC_PTR = BARLOSS;
        BACFLOSS.REC_LEN = 20878;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFLOSS", BACFLOSS);
    }
    public void S000_CALL_BAZFCLCT() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 21001;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
        CEP.TRC(SCCGWA, BACFCLCT.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
