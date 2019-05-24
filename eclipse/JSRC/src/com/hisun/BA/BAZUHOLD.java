package com.hisun.BA;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BAZUHOLD {
    boolean pgmRtn = false;
    String K_HIS_RMKS = "BILL FREEZE AND UNFREEZE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_UHOLD_FUN_COD = " ";
    short WS_ACCT_CNT = 0;
    long WS_SEQ_NO = 0;
    short WS_BIG_SEQ = 0;
    short WS_BIG_SEQ1 = 0;
    int WS_LOSS_DT = 0;
    String WS_RPT_LOSS_NM = " ";
    String WS_RPT_LOSS_ADDR = " ";
    String WS_RPT_LOSS_TEL = " ";
    String WS_STOP_ADV_NO = " ";
    String WS_PBCT_NO = " ";
    int WS_LOSS_ED_DT = 0;
    String WS_RPT_LOSS_RSN = " ";
    String WS_CNTR_NO = " ";
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BARDPAY BARDPAY = new BARDPAY();
    BARLOSS BARLOSS = new BARLOSS();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFDPAY BACFDPAY = new BACFDPAY();
    BACFLOSS BACFLOSS = new BACFLOSS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BACUHOLD BACUHOLD;
    public void MP(SCCGWA SCCGWA, BACUHOLD BACUHOLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUHOLD = BACUHOLD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUHOLD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BACUHOLD.RC.RC_MMO = "BA";
        BACUHOLD.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BACUHOLD.DATA.BILL_NO);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.FUN_COD);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GSZF_NM);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GSZF_ADD);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GSZF_TEL);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GSZF_NO);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GSZF_BN);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GS_EN_DT);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.GS_BRK);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.JG_NO);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.JG_RMK);
        CEP.TRC(SCCGWA, BACUHOLD.DATA.RMK);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_BILL_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_BILL_CHECK() throws IOException,SQLException,Exception {
        if (BACUHOLD.DATA.FUN_COD == '1') {
            if (BACUHOLD.DATA.GS_EN_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MAT_DTERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BARMST1.BILL_NO = BACUHOLD.DATA.BILL_NO;
        BACFMST1.FUNC = 'T';
        CEP.TRC(SCCGWA, "3333333333");
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BARMST1.ACPT_BR);
        if (BACUHOLD.DATA.FUN_COD == '1') {
            CEP.TRC(SCCGWA, "2222222");
            if (BARMST1.BILL_STS == '4' 
                || BARMST1.BILL_STS == '5') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_STS_UNNORMAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BACUHOLD.DATA.FUN_COD == '1' 
            && BARMST1.BLK_STS == '0') {
            CEP.TRC(SCCGWA, "3333333");
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BLK_STS_FROZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUHOLD.DATA.FUN_COD == '2' 
            && BARMST1.BLK_STS != '0') {
            CEP.TRC(SCCGWA, "4444444");
            CEP.TRC(SCCGWA, BARMST1.BLK_STS);
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_NOT_FREEZED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "99999");
        B050_UPDT_MST1_PROC();
        if (pgmRtn) return;
        B050_WRITE_TXDL_PROC();
        if (pgmRtn) return;
        B050_ADD_LOSS_PROC();
        if (pgmRtn) return;
    }
    public void B050_UPDT_MST1_PROC() throws IOException,SQLException,Exception {
        if (BACUHOLD.DATA.FUN_COD == '1') {
            BARMST1.BLK_STS = '0';
        }
        if (BACUHOLD.DATA.FUN_COD == '2') {
            BARMST1.BLK_STS = '1';
        }
        BARMST1.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARMST1.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B050_WRITE_TXDL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARTXDL);
        IBS.init(SCCGWA, BACFTXDL);
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.FUN_CD = BACUHOLD.DATA.FUN_COD;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BACUHOLD.DATA.BILL_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BACFTXDL.FUNC = 'A';
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void B050_ADD_LOSS_PROC() throws IOException,SQLException,Exception {
        B100_GET_BIGEST_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111");
        if (BACUHOLD.DATA.FUN_COD == '2') {
            CEP.TRC(SCCGWA, "222222");
            B100_GET_NEWEST_FREEZE_REC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "333333");
        B100_WRITE_LOSS_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_BIGEST_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOSS);
        IBS.init(SCCGWA, BACFLOSS);
        BARLOSS.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARLOSS.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        CEP.TRC(SCCGWA, BARLOSS.CNTR_NO);
        CEP.TRC(SCCGWA, BARLOSS.ACCT_CNT);
        BACFLOSS.FUNC = 'L';
        S000_CALL_BAZFLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BARLOSS.SEQ);
        CEP.TRC(SCCGWA, BARLOSS.KEY.TX_DT);
        CEP.TRC(SCCGWA, BARLOSS.KEY.CRE_JRN);
        CEP.TRC(SCCGWA, BARLOSS.ACPT_BR);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_BR);
        CEP.TRC(SCCGWA, BARLOSS.BILL_NO);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_STS);
        CEP.TRC(SCCGWA, BARLOSS.SPPM_FLG);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_DT);
        CEP.TRC(SCCGWA, BARLOSS.UNSUP_DT);
        CEP.TRC(SCCGWA, BARLOSS.RPT_LOSS_NM);
        CEP.TRC(SCCGWA, BARLOSS.TS);
        if (BACFLOSS.RETURN_INFO == 'N') {
            WS_BIG_SEQ = 1;
        } else {
            if (BACFLOSS.RETURN_INFO == 'F') {
                WS_BIG_SEQ = (short) (BARLOSS.SEQ + 1);
                WS_BIG_SEQ1 = (short) (BARLOSS.SEQ + 1);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFLOSS.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUHOLD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_GET_NEWEST_FREEZE_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOSS);
        IBS.init(SCCGWA, BACFLOSS);
        BARLOSS.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARLOSS.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARLOSS.LOSS_STS = '3';
        BACFLOSS.FUNC = 'M';
        S000_CALL_BAZFLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BARLOSS.SEQ);
        CEP.TRC(SCCGWA, BARLOSS.KEY.TX_DT);
        CEP.TRC(SCCGWA, BARLOSS.KEY.CRE_JRN);
        CEP.TRC(SCCGWA, BARLOSS.ACPT_BR);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_BR);
        CEP.TRC(SCCGWA, BARLOSS.BILL_NO);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_STS);
        CEP.TRC(SCCGWA, BARLOSS.SPPM_FLG);
        CEP.TRC(SCCGWA, BARLOSS.LOSS_DT);
        CEP.TRC(SCCGWA, BARLOSS.UNSUP_DT);
        CEP.TRC(SCCGWA, BARLOSS.RPT_LOSS_NM);
        CEP.TRC(SCCGWA, BARLOSS.TS);
        if (BACFLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFLOSS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUHOLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_LOSS_DT = BARLOSS.LOSS_DT;
        WS_RPT_LOSS_NM = BARLOSS.RPT_LOSS_NM;
