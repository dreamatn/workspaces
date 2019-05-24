package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7821 {
    DBParm BPTLOSS_RD;
    brParm BPTLOSS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BP836";
    int WS_TOT_CNT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    char WS_LOSS_FLG = ' ';
    char WS_OUT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCO7821 BPCO7821 = new BPCO7821();
    SCCGWA SCCGWA;
    BPB7821_AWA_7821 BPB7821_AWA_7821;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7821 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7821_AWA_7821>");
        BPB7821_AWA_7821 = (BPB7821_AWA_7821) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7821_AWA_7821.ID_TYP);
        CEP.TRC(SCCGWA, BPB7821_AWA_7821.ID_NO);
        CEP.TRC(SCCGWA, BPB7821_AWA_7821.AC);
        CEP.TRC(SCCGWA, BPB7821_AWA_7821.STR_POS);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B040_LOSS_INFO_BRO();
        if (pgmRtn) return;
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        BPCO7821.TOTAL_NUM = WS_TOT_CNT;
        if (WS_LOSS_FLG == 'N') {
            BPCO7821.END_FLG = 'Y';
        }
        SCCFMT.DATA_PTR = BPCO7821;
        SCCFMT.DATA_LEN = 9291;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        BPCO7821.TIMES[WS_I-1].LOS_NO = BPRLOSS.KEY.LOS_NO;
        BPCO7821.TIMES[WS_I-1].AC = BPRLOSS.AC;
        BPCO7821.TIMES[WS_I-1].AC_TYPE = BPRLOSS.AC_TYPE;
        BPCO7821.TIMES[WS_I-1].STS = BPRLOSS.STS;
        BPCO7821.TIMES[WS_I-1].G_AC_NO = BPRLOSS.GET_AC_NO;
        BPCO7821.TIMES[WS_I-1].GET_BR = BPRLOSS.GET_BR;
        BPCO7821.TIMES[WS_I-1].BV_NO = BPRLOSS.BV_NO;
        BPCO7821.TIMES[WS_I-1].N_BV_NO = BPRLOSS.NEW_BV_NO;
        BPCO7821.TIMES[WS_I-1].BV_TYP = BPRLOSS.BV_TYP;
        BPCO7821.TIMES[WS_I-1].BV_CODE = BPRLOSS.BV_CODE;
        BPCO7821.TIMES[WS_I-1].DEP_NO = BPRLOSS.DEP_NO;
        BPCO7821.TIMES[WS_I-1].OPEN_BR = BPRLOSS.OPEN_BR;
        BPCO7821.TIMES[WS_I-1].OPEN_AMT = BPRLOSS.OPEN_AMT;
        BPCO7821.TIMES[WS_I-1].BILL_TYP = BPRLOSS.BILL_TYP;
        BPCO7821.TIMES[WS_I-1].BILL_NO = BPRLOSS.BILL_NO;
        BPCO7821.TIMES[WS_I-1].SUP_B_NO = BPRLOSS.SUP_BILL_NO;
        BPCO7821.TIMES[WS_I-1].BILL_BR = BPRLOSS.BILL_BR;
        BPCO7821.TIMES[WS_I-1].LOS_RMK = BPRLOSS.LOS_RMK;
        BPCO7821.TIMES[WS_I-1].OTH_NM = BPRLOSS.OTH_NM;
        BPCO7821.TIMES[WS_I-1].O_ID_TYP = BPRLOSS.OTH_ID_TYP;
        BPCO7821.TIMES[WS_I-1].O_ID_NO = BPRLOSS.OTH_ID_NO;
        BPCO7821.TIMES[WS_I-1].LOS_NM = BPRLOSS.LOS_NM;
        BPCO7821.TIMES[WS_I-1].L_ID_TYP = BPRLOSS.LOS_ID_TYP;
        BPCO7821.TIMES[WS_I-1].L_ID_NO = BPRLOSS.LOS_ID_NO;
        BPCO7821.TIMES[WS_I-1].LOS_ORG = BPRLOSS.LOS_ORG;
        BPCO7821.TIMES[WS_I-1].LOS_TLR = BPRLOSS.LOS_TLR;
        BPCO7821.TIMES[WS_I-1].LOS_DT = BPRLOSS.LOS_DT;
        BPCO7821.TIMES[WS_I-1].LOS_TIME = BPRLOSS.LOS_TIME;
        BPCO7821.TIMES[WS_I-1].RLOS_ORG = BPRLOSS.RLOS_ORG;
        BPCO7821.TIMES[WS_I-1].RLOS_TLR = BPRLOSS.RLOS_TLR;
        BPCO7821.TIMES[WS_I-1].RLOS_DT = BPRLOSS.RLOS_DT;
        BPCO7821.TIMES[WS_I-1].RL_TIME = BPRLOSS.RLOS_TIME;
        BPCO7821.TIMES[WS_I-1].RL_DUE_T = BPRLOSS.RLOS_DUE_TIME;
        BPCO7821.TIMES[WS_I-1].HLD_FLG = BPRLOSS.HLD_FLG;
        BPCO7821.TIMES[WS_I-1].OPEN_DT = BPRLOSS.OPEN_DT;
        BPCO7821.TIMES[WS_I-1].GET_NM = BPRLOSS.GET_NM;
        BPCO7821.TIMES[WS_I-1].LOS_TELE = BPRLOSS.LOS_TELE;
        BPCO7821.TIMES[WS_I-1].LOS_ADDR = BPRLOSS.LOS_ADDR;
        BPCO7821.TOD_REC_NUM = WS_I;
        BPCO7821.END_POS = BPRLOSS.KEY.LOS_NO;
        BPCO7821.END_FLG = 'N';
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].AC);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].AC_TYPE);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].STS);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].G_AC_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].GET_BR);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BV_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].N_BV_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BV_TYP);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].DEP_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].OPEN_BR);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].OPEN_AMT);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BILL_TYP);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BILL_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].SUP_B_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].BILL_BR);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_RMK);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].OTH_NM);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].O_ID_TYP);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].O_ID_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_NM);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].L_ID_TYP);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].L_ID_NO);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_ORG);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_TLR);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_DT);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_TIME);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].RLOS_ORG);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].RLOS_TLR);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].RLOS_DT);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].RL_TIME);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].RL_DUE_T);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].HLD_FLG);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].OPEN_DT);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].GET_NM);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_TELE);
        CEP.TRC(SCCGWA, BPCO7821.TIMES[WS_I-1].LOS_ADDR);
        CEP.TRC(SCCGWA, BPCO7821.TOD_REC_NUM);
    }
    public void B040_LOSS_INFO_BRO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        IBS.init(SCCGWA, BPCO7821);
        if (BPB7821_AWA_7821.STR_POS.trim().length() == 0) {
            T000_SUMMARY_BPTLOSS();
            if (pgmRtn) return;
            T000_STARTBR_LOSS();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_LOSS_NO();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTLOSS();
        if (pgmRtn) return;
        while (WS_LOSS_FLG != 'N' 
            && WS_I <= 6) {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTLOSS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTLOSS();
        if (pgmRtn) return;
    }
    public void T000_SUMMARY_BPTLOSS() throws IOException,SQLException,Exception {
        BPRLOSS.ID_TYP = BPB7821_AWA_7821.ID_TYP;
        BPRLOSS.ID_NO = BPB7821_AWA_7821.ID_NO;
        BPRLOSS.AC = BPB7821_AWA_7821.AC;
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() == 0) {
            BPTLOSS_RD = new DBParm();
            BPTLOSS_RD.TableName = "BPTLOSS";
            BPTLOSS_RD.set = "WS-TOT-CNT=COUNT(*)";
            BPTLOSS_RD.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO";
            IBS.GROUP(SCCGWA, BPRLOSS, this, BPTLOSS_RD);
        }
        if (BPB7821_AWA_7821.AC.trim().length() > 0 
            && BPB7821_AWA_7821.ID_TYP.trim().length() == 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() == 0) {
            BPTLOSS_RD = new DBParm();
            BPTLOSS_RD.TableName = "BPTLOSS";
            BPTLOSS_RD.set = "WS-TOT-CNT=COUNT(*)";
            BPTLOSS_RD.where = "AC = :BPRLOSS.AC";
            IBS.GROUP(SCCGWA, BPRLOSS, this, BPTLOSS_RD);
        }
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() > 0) {
            BPTLOSS_RD = new DBParm();
            BPTLOSS_RD.TableName = "BPTLOSS";
            BPTLOSS_RD.set = "WS-TOT-CNT=COUNT(*)";
            BPTLOSS_RD.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO "
                + "AND AC = :BPRLOSS.AC";
            IBS.GROUP(SCCGWA, BPRLOSS, this, BPTLOSS_RD);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "SUMMARY BPTLOSS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LOSS() throws IOException,SQLException,Exception {
        BPRLOSS.ID_TYP = BPB7821_AWA_7821.ID_TYP;
        BPRLOSS.ID_NO = BPB7821_AWA_7821.ID_NO;
        BPRLOSS.AC = BPB7821_AWA_7821.AC;
        BPRLOSS.KEY.LOS_NO = BPB7821_AWA_7821.STR_POS;
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() == 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPB7821_AWA_7821.AC.trim().length() > 0 
            && BPB7821_AWA_7821.ID_TYP.trim().length() == 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() == 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() > 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO "
                + "AND AC = :BPRLOSS.AC";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LOSS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_LOSS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LOSS_NO() throws IOException,SQLException,Exception {
        BPRLOSS.ID_TYP = BPB7821_AWA_7821.ID_TYP;
        BPRLOSS.ID_NO = BPB7821_AWA_7821.ID_NO;
        BPRLOSS.AC = BPB7821_AWA_7821.AC;
        BPRLOSS.KEY.LOS_NO = BPB7821_AWA_7821.STR_POS;
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() == 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO "
                + "AND LOS_NO > :BPRLOSS.KEY.LOS_NO";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPB7821_AWA_7821.AC.trim().length() > 0 
            && BPB7821_AWA_7821.ID_TYP.trim().length() == 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() == 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
                + "AND LOS_NO > :BPRLOSS.KEY.LOS_NO";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPB7821_AWA_7821.ID_TYP.trim().length() > 0 
            && BPB7821_AWA_7821.ID_NO.trim().length() > 0 
            && BPB7821_AWA_7821.AC.trim().length() > 0) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO "
                + "AND AC = :BPRLOSS.AC "
                + "AND LOS_NO > :BPRLOSS.KEY.LOS_NO";
            BPTLOSS_BR.rp.order = "LOS_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LOSS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_LOSS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTLOSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LOSS_FLG = 'Y';
            WS_I += 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_LOSS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTLOSS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTLOSS_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
