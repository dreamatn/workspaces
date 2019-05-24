package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZSDJDD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CMTPOSTD_RD;
    brParm CMTPOSTD_BR = new brParm();
    DBParm CMTPOSTC_RD;
    String WS_ERR_MSG = " ";
    CMZSDJDD_WS_MSG_INFO WS_MSG_INFO = new CMZSDJDD_WS_MSG_INFO();
    int WS_LEN = 0;
    int WS_CNT = 0;
    int WS_POST_SEQ = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    String WS_GD_NO = " ";
    CMZSDJDD_WS_POST_INF[] WS_POST_INF = new CMZSDJDD_WS_POST_INF[99];
    CMZSDJDD_WS_POST_INF1 WS_POST_INF1 = new CMZSDJDD_WS_POST_INF1();
    CMZSDJDD_WS_OUT_INFO WS_OUT_INFO = new CMZSDJDD_WS_OUT_INFO();
    char WS_POSTD_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CMCA1200 CMCA1200 = new CMCA1200();
    CMCB1200 CMCB1200 = new CMCB1200();
    CMRPOSTC CMRPOSTC = new CMRPOSTC();
    CMRPOSTD CMRPOSTD = new CMRPOSTD();
    CMCUPOST CMCUPOST = new CMCUPOST();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCSDJDD CMCSDJDD;
    public CMZSDJDD() {
        for (int i=0;i<99;i++) WS_POST_INF[i] = new CMZSDJDD_WS_POST_INF();
    }
    public void MP(SCCGWA SCCGWA, CMCSDJDD CMCSDJDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSDJDD = CMCSDJDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZSDJDD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCSDJDD.SYS_ID);
        CEP.TRC(SCCGWA, CMCSDJDD.TX_DATE);
        CEP.TRC(SCCGWA, CMCSDJDD.VCH_NO);
        T000_READUPD_CMTPOSTC();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CMRPOSTC.RECV_STS != 'N') {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_RECV_STS;
                S000_ERR_MSG_PROC();
            }
            if (CMRPOSTC.PROC_STS != 'P') {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_POST_PROC_STS;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSOTC_REC_NEXIST;
            S000_ERR_MSG_PROC();
        }
        B010_RES_TR_SEQ();
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.OTHSYS_KEY = CMCSDJDD.VCH_NO;
        S000_CALL_BPZPEBAS();
        CMCB1200.CNT = 0;
        WS_POSTD_FLG = 'N';
        T000_STARTBR_CMTPOSTD_POST();
        T000_READNEXT_CMTPOSTD();
        while (WS_POSTD_FLG != 'N') {
            S000_CALL_CMZUPOST();
            WS_LEN = 6237;
            if (CMCUPOST.OUT_DATA == null) CMCUPOST.OUT_DATA = "";
            JIBS_tmp_int = CMCUPOST.OUT_DATA.length();
            for (int i=0;i<1024-JIBS_tmp_int;i++) CMCUPOST.OUT_DATA += " ";
            IBS.CPY2CLS(SCCGWA, CMCUPOST.OUT_DATA.substring(0, WS_LEN), WS_OUT_INFO);
            if (WS_OUT_INFO.WS_GD_REL_REF_NO.trim().length() > 0) {
                WS_GD_NO = WS_OUT_INFO.WS_GD_REL_REF_NO;
                CEP.TRC(SCCGWA, WS_GD_NO);
            }
            if (WS_OUT_INFO.WS_CREV_NO.trim().length() > 0 
                || WS_OUT_INFO.WS_TD_SEQ != 0 
                || WS_OUT_INFO.WS_GD_REL_REF_NO.trim().length() > 0 
                || WS_OUT_INFO.WS_HOLD_NO.trim().length() > 0) {
                WS_LEN = 1024;
                CMRPOSTD.OUT_DATA = CMCUPOST.OUT_DATA;
                T000_REWRITE_CMTPOSTD();
                B060_PREP_OUT_DATA();
            }
            T000_READNEXT_CMTPOSTD();
        }
        T000_ENDBR_CMTPOSTD();
        CMRPOSTC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRPOSTC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CMRPOSTC.HOST_VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        CMRPOSTC.PROC_STS = 'N';
        T000_REWRITE_CMTPOSTC();
        CEP.TRC(SCCGWA, CMCB1200.CNT);
        B070_OUTPUT_DATA();
    }
    public void B010_RES_TR_SEQ() throws IOException,SQLException,Exception {
        WS_POSTD_FLG = 'N';
        T000_STARTBR_CMTPOSTD_REC();
        T000_READNEXT_CMTPOSTD();
        while (WS_POSTD_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, CMRPOSTD.BLOB_INP_DATA, CMCA1200.VCH_ARRAY[1-1]);
            CEP.TRC(SCCGWA, CMCA1200.VCH_ARRAY[1-1].PO_TYPE);
            CEP.TRC(SCCGWA, CMCA1200.VCH_ARRAY[1-1].REV_FLG);
            if ((CMCA1200.VCH_ARRAY[1-1].PO_TYPE == 'L' 
                || (CMCA1200.VCH_ARRAY[1-1].PO_TYPE == 'I' 
                && CMCA1200.VCH_ARRAY[1-1].P_I_FLG == 'I' 
                && CMCA1200.VCH_ARRAY[1-1].PRIN_AC_TYPE == 'L')) 
                && CMCA1200.VCH_ARRAY[1-1].REV_FLG == 'Y') {
                WS_CNT += 1;
                if (WS_CNT > 99) {
                    WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CNT_MAX_ERR;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, WS_CNT);
                WS_POST_INF[WS_CNT-1].WS_SEQ = CMRPOSTD.KEY.REC_SEQ;
                WS_POST_INF[WS_CNT-1].WS_RES_INF.WS_O_AC_DATE = CMCA1200.VCH_ARRAY[1-1].ORIG_AC_DATE;
                WS_POST_INF[WS_CNT-1].WS_RES_INF.WS_O_JRNNO = CMCA1200.VCH_ARRAY[1-1].ORIG_JRNNO;
                WS_POST_INF[WS_CNT-1].WS_RES_INF.WS_O_REC_SEQ = CMCA1200.VCH_ARRAY[1-1].ORIG_REC_SEQ;
            } else {
                WS_POST_SEQ += 1;
                CEP.TRC(SCCGWA, WS_POST_SEQ);
                CMRPOSTD.POST_SEQ = WS_POST_SEQ;
                T000_REWRITE_CMTPOSTD();
            }
            T000_READNEXT_CMTPOSTD();
        }
        T000_ENDBR_CMTPOSTD();
        if (WS_CNT > 1 
            && WS_CNT <= 99) {
            for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
                WS_K = WS_I + 1;
                for (WS_J = WS_K; WS_J <= WS_CNT; WS_J += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_INF[WS_I-1].WS_RES_INF);
                    if (JIBS_tmp_str[0].compareTo(WS_POST_INF[WS_J-1].WS_RES_INF) < 0) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_INF[WS_I-1]);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_POST_INF1);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_INF[WS_J-1]);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_POST_INF[WS_I-1]);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_INF1);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_POST_INF[WS_J-1]);
                    }
                }
            }
            for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
                WS_POST_SEQ += 1;
                T000_READUPD_CMTPOSTD();
                CMRPOSTD.POST_SEQ = WS_POST_SEQ;
                T000_REWRITE_CMTPOSTD();
            }
        }
    }
    public void B060_PREP_OUT_DATA() throws IOException,SQLException,Exception {
        CMCB1200.CNT += 1;
        CMCB1200.ARRAY[CMCB1200.CNT-1].REC_SEQ = CMRPOSTD.KEY.REC_SEQ;
        WS_LEN = 63;
        if (CMCUPOST.OUT_DATA == null) CMCUPOST.OUT_DATA = "";
        JIBS_tmp_int = CMCUPOST.OUT_DATA.length();
        for (int i=0;i<1024-JIBS_tmp_int;i++) CMCUPOST.OUT_DATA += " ";
        IBS.CPY2CLS(SCCGWA, CMCUPOST.OUT_DATA.substring(0, WS_LEN), CMCB1200.ARRAY[CMCB1200.CNT-1].OUT_INF);
    }
    public void B070_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35120";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCB1200;
        SCCFMT.DATA_LEN = 6737;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READUPD_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351200";
        CMRPOSTD.KEY.SYS_ID = CMCSDJDD.SYS_ID;
        CMRPOSTD.KEY.TX_DATE = CMCSDJDD.TX_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCSDJDD.VCH_NO;
        CMRPOSTD.KEY.REC_SEQ = WS_POST_INF[WS_I-1].WS_SEQ;
        CMTPOSTD_RD = new DBParm();
        CMTPOSTD_RD.TableName = "CMTPOSTD";
        CMTPOSTD_RD.upd = true;
        IBS.READ(SCCGWA, CMRPOSTD, CMTPOSTD_RD);
    }
    public void T000_STARTBR_CMTPOSTD_POST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351200";
        CMRPOSTD.KEY.SYS_ID = CMCSDJDD.SYS_ID;
        CMRPOSTD.KEY.TX_DATE = CMCSDJDD.TX_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCSDJDD.VCH_NO;
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_DATE);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.VCH_NO);
        CMTPOSTD_BR.rp = new DBParm();
        CMTPOSTD_BR.rp.TableName = "CMTPOSTD";
        CMTPOSTD_BR.rp.where = "TX_ID = :CMRPOSTD.KEY.TX_ID "
            + "AND SYS_ID = :CMRPOSTD.KEY.SYS_ID "
            + "AND TX_DATE = :CMRPOSTD.KEY.TX_DATE "
            + "AND VCH_NO = :CMRPOSTD.KEY.VCH_NO";
        CMTPOSTD_BR.rp.upd = true;
        CMTPOSTD_BR.rp.order = "POST_SEQ";
        IBS.STARTBR(SCCGWA, CMRPOSTD, this, CMTPOSTD_BR);
    }
    public void T000_STARTBR_CMTPOSTD_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTD);
        CMRPOSTD.KEY.TX_ID = "0351200";
        CMRPOSTD.KEY.SYS_ID = CMCSDJDD.SYS_ID;
        CMRPOSTD.KEY.TX_DATE = CMCSDJDD.TX_DATE;
        CMRPOSTD.KEY.VCH_NO = CMCSDJDD.VCH_NO;
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.SYS_ID);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.TX_DATE);
        CEP.TRC(SCCGWA, CMRPOSTD.KEY.VCH_NO);
        CMTPOSTD_BR.rp = new DBParm();
        CMTPOSTD_BR.rp.TableName = "CMTPOSTD";
        CMTPOSTD_BR.rp.where = "TX_ID = :CMRPOSTD.KEY.TX_ID "
            + "AND SYS_ID = :CMRPOSTD.KEY.SYS_ID "
            + "AND TX_DATE = :CMRPOSTD.KEY.TX_DATE "
            + "AND VCH_NO = :CMRPOSTD.KEY.VCH_NO";
        CMTPOSTD_BR.rp.upd = true;
        CMTPOSTD_BR.rp.order = "REC_SEQ";
        IBS.STARTBR(SCCGWA, CMRPOSTD, this, CMTPOSTD_BR);
    }
    public void T000_READNEXT_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRPOSTD, this, CMTPOSTD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_POSTD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_POSTD_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_CMTPOSTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CMTPOSTD_BR);
    }
    public void T000_READUPD_CMTPOSTC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRPOSTC);
        CMRPOSTC.KEY.TX_ID = "0351200";
        CMRPOSTC.KEY.SYS_ID = CMCSDJDD.SYS_ID;
        CMRPOSTC.KEY.TX_DATE = CMCSDJDD.TX_DATE;
        CMRPOSTC.KEY.VCH_NO = CMCSDJDD.VCH_NO;
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        CMTPOSTC_RD.upd = true;
        IBS.READ(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
    }
    public void T000_REWRITE_CMTPOSTC() throws IOException,SQLException,Exception {
        CMTPOSTC_RD = new DBParm();
        CMTPOSTC_RD.TableName = "CMTPOSTC";
        IBS.REWRITE(SCCGWA, CMRPOSTC, CMTPOSTC_RD);
    }
    public void T000_REWRITE_CMTPOSTD() throws IOException,SQLException,Exception {
        CMTPOSTD_RD = new DBParm();
        CMTPOSTD_RD.TableName = "CMTPOSTD";
        IBS.REWRITE(SCCGWA, CMRPOSTD, CMTPOSTD_RD);
    }
    public void S000_CALL_CMZUPOST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCUPOST);
        CMCUPOST.SYS_ID = CMRPOSTD.KEY.SYS_ID;
        CMCUPOST.TX_DATE = CMRPOSTD.KEY.TX_DATE;
        CMCUPOST.VCH_NO = CMRPOSTD.KEY.VCH_NO;
        CMCUPOST.REC_SEQ = CMRPOSTD.KEY.REC_SEQ;
        CMCUPOST.GD_NO = WS_GD_NO;
        CMCUPOST.INP_DATA = CMRPOSTD.BLOB_INP_DATA;
        IBS.CPY2CLS(SCCGWA, CMRPOSTD.BLOB_INP_DATA, CMCA1200.VCH_ARRAY[1-1]);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CM-U-ONE-REC-POST";
        SCCCALL.COMMAREA_PTR = CMCUPOST;
        SCCCALL.ERRHDL_FLG = 'Y';
        SCCCALL.NOFMT_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, CMCUPOST.RC.RC_CODE);
        if (CMCUPOST.RC.RC_CODE != 0 
            || (SCCGWA.COMM_AREA.ERR_MSG_ID.ERR_MSG_ID_CODE != 0 
            && SCCGWA.COMM_AREA.ERR_MSG_ID.ERR_MSG_ID_CODE != ' ')) {
            if (CMCUPOST.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CMCUPOST.RC);
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.ERR_MSG_ID);
            }
            WS_MSG_INFO.WS_REC_SEQ = CMRPOSTD.KEY.REC_SEQ;
            WS_MSG_INFO.WS_AC_NO = CMCA1200.VCH_ARRAY[1-1].AC;
            S000_ERR_MSG_PROC_AC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_AC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
