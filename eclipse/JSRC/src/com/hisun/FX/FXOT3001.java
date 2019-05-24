package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT3001 {
    DBParm FXTDIRFX_RD;
    brParm FXTDIRFX_BR = new brParm();
    int WK_MAX_ROW = 10;
    int WS_CNT = 0;
    int WS_I = 0;
    short WS_SEQ = 0;
    short WS_SEQ_2 = 0;
    char WS_END_FLG = ' ';
    String WS_SELL_CCY_CNTYCD = " ";
    String WS_BUY_CCY_CNTYCD = " ";
    int WS_TEMP_DT = 0;
    int WS_SELT_DATE = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXCF001 FXCF001 = new FXCF001();
    int WS_TRN_DT = 0;
    long WS_JRN_NO = 0;
    String WS_FXREG_CD = " ";
    SCCGWA SCCGWA;
    FXB3001_AWA_3001 FXB3001_AWA_3001;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT3001 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB3001_AWA_3001>");
        FXB3001_AWA_3001 = (FXB3001_AWA_3001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, FXB3001_AWA_3001.TRN_DT);
        CEP.TRC(SCCGWA, FXB3001_AWA_3001.JRN_NO);
        CEP.TRC(SCCGWA, FXB3001_AWA_3001.FXREG_CD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (FXB3001_AWA_3001.TRN_DT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
        }
    }
    public void B010_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        WS_TRN_DT = FXB3001_AWA_3001.TRN_DT;
        WS_JRN_NO = FXB3001_AWA_3001.JRN_NO;
        WS_FXREG_CD = FXB3001_AWA_3001.FXREG_CD;
        if (WS_FXREG_CD.trim().length() > 0 
            && !WS_FXREG_CD.equalsIgnoreCase("0") 
            && !WS_FXREG_CD.equalsIgnoreCase("R")) {
            B010_05_PROCESS_FXREG();
        } else {
            if (FXB3001_AWA_3001.JRN_NO == ' ' 
                || FXB3001_AWA_3001.JRN_NO == 0) {
                B010_10_PROCESS_BY_DT();
            } else {
                B010_15_PROCESS_BY_DT_JRN();
            }
        }
        R000_FMT_OUTPUT();
    }
    public void B010_05_PROCESS_FXREG() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.where = "TRN_DT = :WS_TRN_DT "
            + "AND ES_REG_CD = :WS_FXREG_CD";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R010_FORMAT_F001();
            WS_END_FLG = 'Y';
            FXCF001.END_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
            FXCF001.END_FLG = 'Y';
        }
    }
    public void B010_10_PROCESS_BY_DT() throws IOException,SQLException,Exception {
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "TRN_DT = :WS_TRN_DT "
            + "AND ( STATUS = 'C' "
            + "OR STATUS = 'R' "
            + "OR STATUS = 'U' )";
        FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO";
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
            FXCF001.END_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CNT = 0;
            while (WS_END_FLG != 'Y' 
                && WS_CNT != WK_MAX_ROW) {
                B020_10_PROCESS_REC();
                T000_READNEXT_FXTDIRFX();
            }
        }
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void B010_15_PROCESS_BY_DT_JRN() throws IOException,SQLException,Exception {
        if (WS_FXREG_CD.equalsIgnoreCase("R")) {
            FXTDIRFX_RD = new DBParm();
            FXTDIRFX_RD.TableName = "FXTDIRFX";
            FXTDIRFX_RD.where = "TRN_DT = :WS_TRN_DT "
                + "AND OJRN_NO = :WS_JRN_NO";
            IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REV_JRN_NOFND);
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_JRN_NO = FXRDIRFX.KEY.JRN_NO;
                CEP.TRC(SCCGWA, WS_JRN_NO);
            }
        }
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "( TRN_DT = :WS_TRN_DT "
            + "AND JRN_NO > :WS_JRN_NO ) "
            + "AND ( STATUS = 'C' "
            + "OR STATUS = 'R' "
            + "OR STATUS = 'U' )";
        FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO";
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
            FXCF001.END_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CNT = 0;
            while (WS_END_FLG != 'Y' 
                && WS_CNT != WK_MAX_ROW) {
                B020_10_PROCESS_REC();
                T000_READNEXT_FXTDIRFX();
            }
        }
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void B020_10_PROCESS_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*************************");
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        R010_FORMAT_F001();
    }
    public void R010_FORMAT_F001() throws IOException,SQLException,Exception {
        if ((FXRDIRFX.STATUS == 'U' 
            && (FXRDIRFX.OJRN_NO == 0 
            || FXRDIRFX.OJRN_NO == ' ')) 
            || FXRDIRFX.STATUS == 'C' 
            || FXRDIRFX.STATUS == 'R') {
            WS_SEQ = 0;
            WS_SEQ_2 = 0;
            CEP.TRC(SCCGWA, FXRDIRFX.B_S_FLG);
            if (FXRDIRFX.B_S_FLG == '1'
                || FXRDIRFX.B_S_FLG == '2') {
                R010_10_JSH_PROCESS();
            } else if (FXRDIRFX.B_S_FLG == '3'
                || FXRDIRFX.B_S_FLG == '4') {
                R010_10_TJS_PROCESS();
            }
        }
    }
    public void R010_10_JSH_PROCESS() throws IOException,SQLException,Exception {
        if (FXRDIRFX.AMT_NS1 != 0) {
            CEP.TRC(SCCGWA, FXRDIRFX.AMT_NS1);
            CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
            if (FXRDIRFX.STATUS == 'R') {
                CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
                CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
                if (((FXB3001_AWA_3001.JRN_NO == ' ' 
                    || FXB3001_AWA_3001.JRN_NO == 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ) 
                    || ((FXB3001_AWA_3001.JRN_NO != ' ' 
                    && FXB3001_AWA_3001.JRN_NO != 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ 
                    && FXB3001_AWA_3001.JRN_NO < FXRDIRFX.OJRN_NO)) {
                    R020_FORMAT_REC();
                    FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                    FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD1;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS1;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
                    WS_SEQ_2 = WS_SEQ;
                }
            }
            R020_FORMAT_REC();
            if (WS_SEQ_2 > 0) {
                WS_SEQ = WS_SEQ_2;
                FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
            }
            FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD1;
            FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS1;
        }
        if (FXRDIRFX.AMT_NS2 != 0) {
            CEP.TRC(SCCGWA, FXRDIRFX.AMT_NS2);
            CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
            if (FXRDIRFX.STATUS == 'R') {
                CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
                CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
                if (((FXB3001_AWA_3001.JRN_NO == ' ' 
                    || FXB3001_AWA_3001.JRN_NO == 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ) 
                    || ((FXB3001_AWA_3001.JRN_NO != ' ' 
                    && FXB3001_AWA_3001.JRN_NO != 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ 
                    && FXB3001_AWA_3001.JRN_NO < FXRDIRFX.OJRN_NO)) {
                    R020_FORMAT_REC();
                    FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                    FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD2;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS2;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
                    WS_SEQ_2 = WS_SEQ;
                }
            }
            R020_FORMAT_REC();
            if (WS_SEQ_2 > 0) {
                WS_SEQ = WS_SEQ_2;
                FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
            }
            FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD2;
            FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS2;
        }
        if (FXRDIRFX.AMT_NS3 != 0) {
            CEP.TRC(SCCGWA, FXRDIRFX.AMT_NS3);
            CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
            if (FXRDIRFX.STATUS == 'R') {
                CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
                CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
                if (((FXB3001_AWA_3001.JRN_NO == ' ' 
                    || FXB3001_AWA_3001.JRN_NO == 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ) 
                    || ((FXB3001_AWA_3001.JRN_NO != ' ' 
                    && FXB3001_AWA_3001.JRN_NO != 0) 
                    && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ 
                    && FXB3001_AWA_3001.JRN_NO < FXRDIRFX.OJRN_NO)) {
                    R020_FORMAT_REC();
                    FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                    FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD3;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS3;
                    FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
                    WS_SEQ_2 = WS_SEQ;
                }
            }
            R020_FORMAT_REC();
            if (WS_SEQ_2 > 0) {
                WS_SEQ = WS_SEQ_2;
                FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
            }
            FXCF001.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD3;
            FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.AMT_NS3;
        }
    }
    public void R010_10_TJS_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        if (FXRDIRFX.STATUS == 'R') {
            CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
            CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
            if (((FXB3001_AWA_3001.JRN_NO == ' ' 
                || FXB3001_AWA_3001.JRN_NO == 0) 
                && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ) 
                || ((FXB3001_AWA_3001.JRN_NO != ' ' 
                && FXB3001_AWA_3001.JRN_NO != 0) 
                && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ 
                && FXB3001_AWA_3001.JRN_NO < FXRDIRFX.OJRN_NO)) {
                R020_FORMAT_REC();
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.BUY_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.BUY_AMT;
                FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
                WS_SEQ_2 = WS_SEQ;
            }
        }
        R020_FORMAT_REC();
        if (WS_SEQ_2 > 0) {
            WS_SEQ = WS_SEQ_2;
            FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
        }
        FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.BUY_CCY;
        FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.BUY_AMT;
        if (FXRDIRFX.STATUS == 'R') {
            CEP.TRC(SCCGWA, FXRDIRFX.OSEQ);
            CEP.TRC(SCCGWA, FXRDIRFX.OJRN_NO);
            if (((FXB3001_AWA_3001.JRN_NO == ' ' 
                || FXB3001_AWA_3001.JRN_NO == 0) 
                && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ) 
                || ((FXB3001_AWA_3001.JRN_NO != ' ' 
                && FXB3001_AWA_3001.JRN_NO != 0) 
                && FXRDIRFX.KEY.TRN_DT == FXRDIRFX.OSEQ 
                && FXB3001_AWA_3001.JRN_NO < FXRDIRFX.OJRN_NO)) {
                R020_FORMAT_REC();
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.SELL_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.SELL_AMT;
                FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
                WS_SEQ_2 = WS_SEQ;
            }
        }
        R020_FORMAT_REC();
        if (WS_SEQ_2 > 0) {
            WS_SEQ = WS_SEQ_2;
            FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
        }
        FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.SELL_CCY;
        FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.SELL_AMT;
    }
    public void R020_FORMAT_REC() throws IOException,SQLException,Exception {
        if (WS_CNT < WK_MAX_ROW) {
            WS_CNT += 1;
            WS_SEQ += 1;
            CEP.TRC(SCCGWA, WS_SEQ);
            FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.KEY.JRN_NO;
            FXCF001.OUT_INFO[WS_CNT-1].SEQ = WS_SEQ;
            FXCF001.OUT_INFO[WS_CNT-1].TRN_DT = FXRDIRFX.KEY.TRN_DT;
            FXCF001.OUT_INFO[WS_CNT-1].EX_RATE = FXRDIRFX.EX_RATE;
            FXCF001.OUT_INFO[WS_CNT-1].CI_NO = FXRDIRFX.CI_NO;
            FXCF001.OUT_INFO[WS_CNT-1].CI_CNM = FXRDIRFX.CI_CNM;
            FXCF001.OUT_INFO[WS_CNT-1].BR = FXRDIRFX.KEY.UPD_BR;
            FXCF001.OUT_INFO[WS_CNT-1].CUS_TYP = '1';
            if (FXRDIRFX.DD_AC1.trim().length() > 0) {
                FXCF001.OUT_INFO[WS_CNT-1].DD_AC1 = FXRDIRFX.DD_AC1;
            } else if (FXRDIRFX.BUY_SUPAC.trim().length() > 0) {
                FXCF001.OUT_INFO[WS_CNT-1].DD_AC1 = FXRDIRFX.BUY_SUPAC;
            }
            if (FXRDIRFX.DD_AC2.trim().length() > 0) {
                FXCF001.OUT_INFO[WS_CNT-1].DD_AC2 = FXRDIRFX.DD_AC2;
            } else if (FXRDIRFX.BUY_SUPAC.trim().length() > 0) {
                FXCF001.OUT_INFO[WS_CNT-1].DD_AC2 = FXRDIRFX.SELL_SUPAC;
            }
            FXCF001.OUT_INFO[WS_CNT-1].REF_NO = FXRDIRFX.REF_NO;
            FXCF001.OUT_INFO[WS_CNT-1].FXREG_CD = FXRDIRFX.ES_REG_CD;
            FXCF001.OUT_INFO[WS_CNT-1].REV_NO = FXRDIRFX.REV_NO;
            FXCF001.OUT_INFO[WS_CNT-1].ES_FLG = FXRDIRFX.B_S_FLG;
            if (FXRDIRFX.B_S_FLG == '1') {
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.BUY_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.BUY_AMT;
            } else if (FXRDIRFX.B_S_FLG == '2') {
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.SELL_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.SELL_AMT;
            } else if (FXRDIRFX.B_S_FLG == '3') {
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.BUY_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.BUY_AMT;
            } else if (FXRDIRFX.B_S_FLG == '4') {
                FXCF001.OUT_INFO[WS_CNT-1].CCY = FXRDIRFX.SELL_CCY;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_NS = FXRDIRFX.SELL_AMT;
            }
            if (FXRDIRFX.STATUS == 'R') {
                FXCF001.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.OJRN_NO;
                FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '-';
            } else {
                FXCF001.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
            }
            FXCF001.OUT_INFO[WS_CNT-1].ID_TYP = FXRDIRFX.ID_TYP;
            FXCF001.OUT_INFO[WS_CNT-1].ID_NO = FXRDIRFX.ID_NO;
            FXCF001.OUT_INFO[WS_CNT-1].AC_BR = FXRDIRFX.AC_BR;
            FXCF001.OUT_INFO[WS_CNT-1].CHNL = FXRDIRFX.ST_TRCHNL;
            CEP.TRC(SCCGWA, FXRDIRFX.ID_TYP);
            if (!FXRDIRFX.ID_TYP.equalsIgnoreCase("0") 
                && FXRDIRFX.ID_TYP.trim().length() > 0) {
                if (FXRDIRFX.ID_TYP.equalsIgnoreCase("10100")) {
                    FXCF001.OUT_INFO[WS_CNT-1].TRN_CUSTYP = '1';
                } else {
                    FXCF001.OUT_INFO[WS_CNT-1].TRN_CUSTYP = '2';
                }
            }
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].TRN_CUSTYP);
            FXCF001.OUT_INFO[WS_CNT-1].AC_BR = FXRDIRFX.AC_BR;
            FXCF001.OUT_INFO[WS_CNT-1].CHNL = FXRDIRFX.ST_TRCHNL;
            if (FXRDIRFX.VALUE_DT == FXRDIRFX.O_END_DT) {
                FXCF001.OUT_INFO[WS_CNT-1].TRN_TYP = '0';
            } else {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = FXRDIRFX.BUY_CCY;
                S000_CALL_BPZQCCY();
                WS_BUY_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
                CEP.TRC(SCCGWA, WS_BUY_CCY_CNTYCD);
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = FXRDIRFX.SELL_CCY;
                S000_CALL_BPZQCCY();
                WS_SELL_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
                CEP.TRC(SCCGWA, WS_SELL_CCY_CNTYCD);
                IBS.init(SCCGWA, BPCPCMWD);
                BPCPCMWD.DATE_TYPE = 'B';
                BPCPCMWD.CHECK_DATE = FXRDIRFX.VALUE_DT;
                BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
                BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
                BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
                BPCPCMWD.CALCD[1-1] = "CN  ";
                S000_CALL_BPZPCMWD();
                WS_TEMP_DT = BPCPCMWD.NEXT_WORK_DAY_ALL;
                CEP.TRC(SCCGWA, WS_TEMP_DT);
                IBS.init(SCCGWA, BPCPCMWD);
                BPCPCMWD.DATE_TYPE = 'B';
                BPCPCMWD.CHECK_DATE = WS_TEMP_DT;
                BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
                BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
                BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
                BPCPCMWD.CALCD[1-1] = "CN  ";
                S000_CALL_BPZPCMWD();
                WS_SELT_DATE = BPCPCMWD.NEXT_WORK_DAY_ALL;
                CEP.TRC(SCCGWA, WS_SELT_DATE);
                if (FXRDIRFX.O_END_DT == WS_TEMP_DT) {
                    FXCF001.OUT_INFO[WS_CNT-1].TRN_TYP = '1';
                }
                if (FXRDIRFX.O_END_DT == WS_SELT_DATE) {
                    FXCF001.OUT_INFO[WS_CNT-1].TRN_TYP = '2';
                }
            }
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].TRN_TYP);
        }
    }
    public void R000_FMT_OUTPUT() throws IOException,SQLException,Exception {
        FXCF001.CURR_MAX_ROW = WS_CNT;
        CEP.TRC(SCCGWA, FXCF001.CURR_MAX_ROW);
        CEP.TRC(SCCGWA, FXCF001.END_FLG);
        CEP.TRC(SCCGWA, FXCF001.OUT_INFO[1-1].JRN_NO);
        if (WS_CNT > 1) {
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].JRN_NO);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].SEQ);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].TRN_DT);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].EXST_CD);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].DD_AC1);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].DD_AC2);
            CEP.TRC(SCCGWA, FXCF001.OUT_INFO[WS_CNT-1].REF_NO);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX001";
        SCCFMT.DATA_PTR = FXCF001;
        SCCFMT.DATA_LEN = 5597;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        CEP.TRC(SCCGWA, FXCF001);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READNEXT_FXTDIRFX() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_END_FLG = 'Y';
            FXCF001.END_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_I = WS_CNT;
            if (FXRDIRFX.B_S_FLG == '1'
                || FXRDIRFX.B_S_FLG == '2') {
                if (FXRDIRFX.AMT_NS1 != 0) {
                    WS_I += 1;
                    if (FXRDIRFX.STATUS == 'R') {
                        WS_I += 1;
                    }
                }
                if (FXRDIRFX.AMT_NS2 != 0) {
                    WS_I += 1;
                    if (FXRDIRFX.STATUS == 'R') {
                        WS_I += 1;
                    }
                }
                if (FXRDIRFX.AMT_NS3 != 0) {
                    WS_I += 1;
                    if (FXRDIRFX.STATUS == 'R') {
                        WS_I += 1;
                    }
                }
            } else if (FXRDIRFX.B_S_FLG == '3'
                || FXRDIRFX.B_S_FLG == '4') {
                WS_I += 2;
                if (FXRDIRFX.STATUS == 'R') {
                    WS_I += 2;
                }
            }
            CEP.TRC(SCCGWA, WS_I);
            if (WS_I > WK_MAX_ROW) {
                WS_END_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
