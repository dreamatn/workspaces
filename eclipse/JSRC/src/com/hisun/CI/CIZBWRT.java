package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBWRT {
    brParm CITRELT_BR = new brParm();
    brParm CITRREL_BR = new brParm();
    DBParm CITRELT_RD;
    DBParm CITRREL_RD;
    boolean pgmRtn = false;
    int K_MAX_ROW = 5;
    String K_OUTPUT_FMT = "CIB40";
    int WS_PAGE_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_RECORD_NUM1 = 0;
    int WS_ALL_NUM = 0;
    int WS_I = 0;
    int WS_II = 0;
    char WS_INQ_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICFB40 CICFB40 = new CICFB40();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBWRT CICBWRT;
    public void MP(SCCGWA SCCGWA, CICBWRT CICBWRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBWRT = CICBWRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBWRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_BROWSE_PROC();
        if (pgmRtn) return;
        B030_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B040_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICFB40);
        if (CICBWRT.DATA.PAGE_ROW > K_MAX_ROW 
            || CICBWRT.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICBWRT.DATA.PAGE_ROW;
        }
        WS_ALL_NUM = WS_PAGE_ROW * ( CICBWRT.DATA.PAGE_NUM - 1 );
        if (CICBWRT.DATA.RELT_CNM.trim().length() > 0 
            || CICBWRT.DATA.RELT_ENM.trim().length() > 0 
            || CICBWRT.DATA.RELT_IDNO.trim().length() > 0) {
            WS_INQ_FLG = 'Y';
            B021_BROWSE_BY_RELT();
            if (pgmRtn) return;
        } else {
            WS_INQ_FLG = 'N';
            B022_BROWSE_BY_RREL();
            if (pgmRtn) return;
        }
    }
    public void B021_BROWSE_BY_RELT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_CNM = CICBWRT.DATA.RELT_CNM;
        CIRRELT.RELT_ENM = CICBWRT.DATA.RELT_ENM;
        CIRRELT.RELT_ID_TYPE = CICBWRT.DATA.RELT_IDTYP;
        CIRRELT.RELT_ID_NO = CICBWRT.DATA.RELT_IDNO;
        T000_STARTBR_CITRELT_BY_NMID();
        if (pgmRtn) return;
        T000_READNEXT_CITRELT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_RECORD_NUM = WS_RECORD_NUM + 1;
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            CEP.TRC(SCCGWA, WS_ALL_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            if (WS_RECORD_NUM > WS_ALL_NUM 
                && WS_I < WS_PAGE_ROW) {
                WS_I = WS_I + 1;
                B023_DATA_TRANS_TO_FMT_01();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITRELT();
            if (pgmRtn) return;
        }
    }
    public void B022_BROWSE_BY_RREL() throws IOException,SQLException,Exception {
        if (CICBWRT.DATA.RREL_CNM.trim().length() > 0 
            || CICBWRT.DATA.RREL_ENM.trim().length() > 0 
            || CICBWRT.DATA.RREL_IDNO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRRREL);
            CIRRREL.REL_CNM = CICBWRT.DATA.RREL_CNM;
            CIRRREL.REL_ENM = CICBWRT.DATA.RREL_ENM;
            CIRRREL.REL_ID_TYPE = CICBWRT.DATA.RREL_IDTYP;
            CIRRREL.REL_ID_NO = CICBWRT.DATA.RREL_IDNO;
            T000_STARTBR_CITRREL_BY_IDNM();
            if (pgmRtn) return;
            T000_READNEXT_CITRREL();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.init(SCCGWA, CIRRELT);
                CIRRELT.KEY.RELT_NO = CIRRREL.RELT_NO;
                T000_READ_CITRELT();
                if (pgmRtn) return;
                WS_RECORD_NUM = WS_RECORD_NUM + 1;
                if (WS_RECORD_NUM > WS_ALL_NUM 
                    && WS_I <= WS_PAGE_ROW) {
                    WS_I = WS_I + 1;
                    B023_DATA_TRANS_TO_FMT_02();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITRREL();
                if (pgmRtn) return;
            }
        }
    }
    public void B023_DATA_TRANS_TO_FMT_01() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRRELT.KEY.RELT_NO);
        CEP.TRC(SCCGWA, CIRRELT.RELT_CNM);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ENM);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_TYPE);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_NO);
        CICFB40.DATA[WS_I-1].RELT_NO = CIRRELT.KEY.RELT_NO;
        CICFB40.DATA[WS_I-1].RELT_CNM = CIRRELT.RELT_CNM;
        CICFB40.DATA[WS_I-1].RELT_ENM = CIRRELT.RELT_ENM;
        CICFB40.DATA[WS_I-1].RELT_IDTYP = CIRRELT.RELT_ID_TYPE;
        CICFB40.DATA[WS_I-1].RELT_IDNO = CIRRELT.RELT_ID_NO;
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_NO);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_CNM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_ENM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_IDTYP);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_IDNO);
    }
    public void B023_DATA_TRANS_TO_FMT_02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRRELT.KEY.RELT_NO);
        CEP.TRC(SCCGWA, CIRRELT.RELT_CNM);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ENM);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_TYPE);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_NO);
        CEP.TRC(SCCGWA, CIRRREL.REL_CNM);
        CEP.TRC(SCCGWA, CIRRREL.REL_ENM);
        CEP.TRC(SCCGWA, CIRRREL.REL_ID_TYPE);
        CEP.TRC(SCCGWA, CIRRREL.REL_ID_NO);
        CICFB40.DATA[WS_I-1].RELT_NO = CIRRELT.KEY.RELT_NO;
        CICFB40.DATA[WS_I-1].RELT_CNM = CIRRELT.RELT_CNM;
        CICFB40.DATA[WS_I-1].RELT_ENM = CIRRELT.RELT_ENM;
        CICFB40.DATA[WS_I-1].RELT_IDTYP = CIRRELT.RELT_ID_TYPE;
        CICFB40.DATA[WS_I-1].RELT_IDNO = CIRRELT.RELT_ID_NO;
        CICFB40.DATA[WS_I-1].RREL_CNM = CIRRREL.REL_CNM;
        CICFB40.DATA[WS_I-1].RREL_ENM = CIRRREL.REL_ENM;
        CICFB40.DATA[WS_I-1].RREL_IDTYP = CIRRREL.REL_ID_TYPE;
        CICFB40.DATA[WS_I-1].RREL_IDNO = CIRRREL.REL_ID_NO;
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_NO);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_CNM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_ENM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_IDTYP);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RELT_IDNO);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RREL_CNM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RREL_ENM);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RREL_IDTYP);
        CEP.TRC(SCCGWA, CICFB40.DATA[WS_I-1].RREL_IDNO);
    }
    public void B030_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        WS_RECORD_NUM1 = WS_RECORD_NUM % WS_PAGE_ROW;
        CICFB40.OUTPUT_TITLE.TOTAL_PAGE = (int) ((WS_RECORD_NUM - WS_RECORD_NUM1) / WS_PAGE_ROW);
        if (WS_RECORD_NUM1 > 0) {
            CICFB40.OUTPUT_TITLE.TOTAL_PAGE = CICFB40.OUTPUT_TITLE.TOTAL_PAGE + 1;
        }
        CICFB40.OUTPUT_TITLE.TOTAL_NUM = WS_RECORD_NUM;
        CICFB40.OUTPUT_TITLE.CURR_PAGE = CICBWRT.DATA.PAGE_NUM;
        CICFB40.OUTPUT_TITLE.PAGE_ROW = WS_I;
        WS_RECORD_NUM1 = WS_ALL_NUM + WS_I;
        if (WS_RECORD_NUM == WS_RECORD_NUM1) {
            CICFB40.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICFB40.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B040_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFB40);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFB40;
        SCCFMT.DATA_LEN = 5920;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_CITRELT_BY_NMID() throws IOException,SQLException,Exception {
        CITRELT_BR.rp = new DBParm();
        CITRELT_BR.rp.TableName = "CITRELT";
        CITRELT_BR.rp.where = "( RELT_CNM = :CIRRELT.RELT_CNM "
            + "OR :CIRRELT.RELT_CNM = ' ' ) "
            + "AND ( RELT_ENM = :CIRRELT.RELT_ENM "
            + "OR :CIRRELT.RELT_ENM = ' ' ) "
            + "AND ( RELT_ID_TYPE = :CIRRELT.RELT_ID_TYPE "
            + "OR :CIRRELT.RELT_ID_TYPE = ' ' ) "
            + "AND ( RELT_ID_NO = :CIRRELT.RELT_ID_NO "
            + "OR :CIRRELT.RELT_ID_NO = ' ' )";
        IBS.STARTBR(SCCGWA, CIRRELT, this, CITRELT_BR);
    }
    public void T000_READNEXT_CITRELT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRELT, this, CITRELT_BR);
    }
    public void T000_ENDBR_CITRELT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRELT_BR);
    }
    public void T000_STARTBR_CITRREL_BY_IDNM() throws IOException,SQLException,Exception {
        CITRREL_BR.rp = new DBParm();
        CITRREL_BR.rp.TableName = "CITRREL";
        CITRREL_BR.rp.where = "( REL_CNM = :CIRRREL.REL_CNM "
            + "OR :CIRRREL.REL_CNM = ' ' ) "
            + "AND ( REL_ENM = :CIRRREL.REL_ENM "
            + "OR :CIRRREL.REL_ENM = ' ' ) "
            + "AND ( REL_ID_TYPE = :CIRRREL.REL_ID_TYPE "
            + "OR :CIRRREL.REL_ID_TYPE = ' ' ) "
            + "AND ( REL_ID_NO = :CIRRREL.REL_ID_NO "
            + "OR :CIRRREL.REL_ID_NO = ' ' )";
        IBS.STARTBR(SCCGWA, CIRRREL, this, CITRREL_BR);
    }
    public void T000_STARTBR_CITRREL_BY_RELTNO() throws IOException,SQLException,Exception {
        CITRREL_BR.rp = new DBParm();
        CITRREL_BR.rp.TableName = "CITRREL";
        CITRREL_BR.rp.where = "RELT_NO = :CIRRREL.RELT_NO";
        CITRREL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRRREL, this, CITRREL_BR);
    }
    public void T000_READNEXT_CITRREL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRREL, this, CITRREL_BR);
    }
    public void T000_ENDBR_CITRREL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRREL_BR);
    }
    public void T000_READ_CITRELT() throws IOException,SQLException,Exception {
        CITRELT_RD = new DBParm();
        CITRELT_RD.TableName = "CITRELT";
        IBS.READ(SCCGWA, CIRRELT, CITRELT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELT_NOT_FND, CICBWRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITRREL() throws IOException,SQLException,Exception {
        CITRREL_RD = new DBParm();
        CITRREL_RD.TableName = "CITRREL";
        IBS.READ(SCCGWA, CIRRREL, CITRREL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RREL_NOT_FND, CICBWRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICBWRT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICBWRT=");
            CEP.TRC(SCCGWA, CICBWRT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
