package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT3003 {
    DBParm FXTDIRFX_RD;
    brParm FXTDIRFX_BR = new brParm();
    int WK_MAX_ROW = 30;
    String WK_PROD_CD = "1303020403";
    int WS_CNT = 0;
    int WS_I = 0;
    char WS_END_FLG = ' ';
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXCF003 FXCF003 = new FXCF003();
    int WS_TRN_DT = 0;
    String WS_FXREG_CD = " ";
    long WS_JRN_NO = 0;
    String WS_PROD_CD = " ";
    SCCGWA SCCGWA;
    SCCGAPV SCCGAPV;
    FXB3003_AWA_3003 FXB3003_AWA_3003;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT3003 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB3003_AWA_3003>");
        FXB3003_AWA_3003 = (FXB3003_AWA_3003) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, FXB3003_AWA_3003.TRN_DT);
        CEP.TRC(SCCGWA, FXB3003_AWA_3003.JRN_NO);
        CEP.TRC(SCCGWA, FXB3003_AWA_3003.FXREG_CD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (FXB3003_AWA_3003.TRN_DT == 0 
            || FXB3003_AWA_3003.TRN_DT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
        }
        if ((FXB3003_AWA_3003.JRN_NO != ' ' 
            && FXB3003_AWA_3003.JRN_NO != 0) 
            && (FXB3003_AWA_3003.FXREG_CD.trim().length() > 0 
            && !FXB3003_AWA_3003.FXREG_CD.equalsIgnoreCase("0"))) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_JRN_REG_ERR);
        }
    }
    public void B010_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        IBS.init(SCCGWA, FXCF003);
        WS_TRN_DT = FXB3003_AWA_3003.TRN_DT;
        if (FXB3003_AWA_3003.JRN_NO == ' ') {
            WS_JRN_NO = 0;
        } else {
            WS_JRN_NO = FXB3003_AWA_3003.JRN_NO;
        }
        WS_FXREG_CD = FXB3003_AWA_3003.FXREG_CD;
        WS_PROD_CD = WK_PROD_CD;
        if (FXB3003_AWA_3003.FXREG_CD.trim().length() > 0 
            && !FXB3003_AWA_3003.FXREG_CD.equalsIgnoreCase("0")) {
            B010_05_PROCESS_FXREG();
        } else {
            B010_10_PROCESS_BY_DT_JRN();
        }
        R000_FMT_OUTPUT();
    }
    public void B010_05_PROCESS_FXREG() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.where = "TRN_DT = :WS_TRN_DT "
            + "AND PROD_CD = :WS_PROD_CD "
            + "AND ES_REG_CD = :WS_FXREG_CD";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R010_FORMAT_F003();
            FXCF003.END_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            FXCF003.END_FLG = 'Y';
        }
    }
    public void B010_10_PROCESS_BY_DT_JRN() throws IOException,SQLException,Exception {
        if (WS_JRN_NO == 0) {
            B020_05_PROCESS_FX_ERR();
            if (WS_CNT < WK_MAX_ROW) {
                WS_JRN_NO = 0;
                B020_10_PROCESS_FX_OTH();
            }
        } else {
            FXTDIRFX_RD = new DBParm();
            FXTDIRFX_RD.TableName = "FXTDIRFX";
            FXTDIRFX_RD.where = "PROD_CD = :WS_PROD_CD "
                + "AND O_END_DT = :WS_TRN_DT "
                + "AND STATUS = 'E' "
                + "AND JRN_NO = :WS_JRN_NO";
            IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                B020_05_PROCESS_FX_ERR();
                if (WS_CNT < WK_MAX_ROW) {
                    WS_JRN_NO = 0;
                    B020_10_PROCESS_FX_OTH();
                }
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                B020_10_PROCESS_FX_OTH();
            }
        }
    }
    public void B020_05_PROCESS_FX_ERR() throws IOException,SQLException,Exception {
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "PROD_CD = :WS_PROD_CD "
            + "AND O_END_DT = :WS_TRN_DT "
            + "AND STATUS = 'E' "
            + "AND JRN_NO > :WS_JRN_NO";
        FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO";
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        B030_10_PROCESS_REC();
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void B020_10_PROCESS_FX_OTH() throws IOException,SQLException,Exception {
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "PROD_CD = :WS_PROD_CD "
            + "AND TRN_DT = :WS_TRN_DT "
            + "AND JRN_NO > :WS_JRN_NO";
        FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO";
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        B030_10_PROCESS_REC();
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
        if (WS_END_FLG == 'Y') {
            FXCF003.END_FLG = 'Y';
        }
    }
    public void B030_10_PROCESS_REC() throws IOException,SQLException,Exception {
        WS_END_FLG = 'N';
        while (WS_END_FLG != 'Y' 
            && WS_CNT != WK_MAX_ROW) {
            IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_END_FLG = 'Y';
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R010_FORMAT_F003();
                CEP.TRC(SCCGWA, WS_END_FLG);
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
                CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
            }
        }
    }
    public void R010_FORMAT_F003() throws IOException,SQLException,Exception {
        if ((FXRDIRFX.STATUS == 'U' 
            || FXRDIRFX.STATUS == 'C' 
            || FXRDIRFX.STATUS == 'M' 
            || FXRDIRFX.STATUS == 'R') 
            || (FXRDIRFX.O_END_DT == WS_TRN_DT 
            && FXRDIRFX.STATUS == 'E')) {
            WS_CNT += 1;
            FXCF003.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.KEY.JRN_NO;
            FXCF003.OUT_INFO[WS_CNT-1].FXREG_CD = FXRDIRFX.ES_REG_CD;
            FXCF003.OUT_INFO[WS_CNT-1].STATUS = FXRDIRFX.STATUS;
            if (FXRDIRFX.B_S_FLG == '1') {
                FXCF003.OUT_INFO[WS_CNT-1].FCYCCY = FXRDIRFX.BUY_CCY;
                FXCF003.OUT_INFO[WS_CNT-1].FCYAMT = FXRDIRFX.BUY_AMT;
            }
            if (FXRDIRFX.B_S_FLG == '2') {
                FXCF003.OUT_INFO[WS_CNT-1].FCYCCY = FXRDIRFX.SELL_CCY;
                FXCF003.OUT_INFO[WS_CNT-1].FCYAMT = FXRDIRFX.SELL_AMT;
            }
            if (FXRDIRFX.STATUS == 'R' 
                || FXRDIRFX.STATUS == 'E') {
                FXCF003.OUT_INFO[WS_CNT-1].AMT_FLG = '-';
            } else {
                FXCF003.OUT_INFO[WS_CNT-1].AMT_FLG = '+';
            }
        }
    }
    public void R000_FMT_OUTPUT() throws IOException,SQLException,Exception {
        FXCF003.CURR_MAX_ROW = WS_CNT;
        CEP.TRC(SCCGWA, FXCF003.CURR_MAX_ROW);
        CEP.TRC(SCCGWA, FXCF003.END_FLG);
        CEP.TRC(SCCGWA, FXCF003.OUT_INFO[1-1].JRN_NO);
        if (WS_CNT > 1) {
            CEP.TRC(SCCGWA, FXCF003.OUT_INFO[WS_CNT-1].JRN_NO);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX003";
        SCCFMT.DATA_PTR = FXCF003;
        SCCFMT.DATA_LEN = 1867;
        CEP.TRC(SCCGWA, FXCF003);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
