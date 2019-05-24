package com.hisun.EA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class EAZSWINQ {
    DBParm EATWLST_RD;
    brParm EATWLST_BR = new brParm();
    String WS_SQL_AC_NO = "                                ";
    String WS_SQL_REQ_SYS = "      ";
    String WS_SQL_AC_NAME = "                                                                                                                                                                                                                                                            ";
    int WS_CNT = 0;
    String K_AP_MMO = "EA";
    String K_SYS_ERR = "SC6001";
    String K_OUTPUT_FMT = "EA870";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    char WS_WLST_FLG = ' ';
    String WS_IRAT_CD = " ";
    String WS_DISPLAY = " ";
    short WS_SEQ = 0;
    short WS_DEC = 0;
    short WS_LEN = 0;
    short WS_MTHS = 0;
    short WS_I = 0;
    short WS_J = 0;
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    EACO870 EACO870 = new EACO870();
    EARWLST EARWLST = new EARWLST();
    SCCGWA SCCGWA;
    EACSWINQ EACSWINQ;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, EACSWINQ EACSWINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACSWINQ = EACSWINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, "EAZSWINQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, EACO870);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSWINQ.BEG_SEQ);
        CEP.TRC(SCCGWA, EACSWINQ.REQ_SYS);
        CEP.TRC(SCCGWA, EACSWINQ.LST_AC);
        CEP.TRC(SCCGWA, EACSWINQ.AC_NAME);
        CEP.TRC(SCCGWA, EACSWINQ.FUNC);
        if (EACSWINQ.REQ_SYS.trim().length() == 0 
            && EACSWINQ.FUNC == 'A') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_INPUT_ERROR);
        }
        if (EACSWINQ.FUNC == 'A') {
            EARWLST.KEY.REQ_SYS = EACSWINQ.REQ_SYS;
            EACO870.REQ_SYS = EACSWINQ.REQ_SYS;
            T000_READ_WLST_COUNT();
            EACO870.TOTAL_CNT = WS_CNT;
            T000_STARTBR_WLST();
            T000_READNEXT_WLST();
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_WLST_FLG != 'Y') {
                WS_SEQ += 1;
                if (WS_SEQ > EACSWINQ.BEG_SEQ) {
                    WS_I += 1;
                    EACO870.AC_ARRAY[WS_I-1].AC_FLG = EARWLST.KEY.AC_FLG;
                    EACO870.AC_ARRAY[WS_I-1].DC_FLG = EARWLST.KEY.DC_FLG;
                    EACO870.AC_ARRAY[WS_I-1].LST_FLG = EARWLST.KEY.LST_FLG;
                    EACO870.AC_ARRAY[WS_I-1].LST_AC = EARWLST.KEY.LST_AC;
                    EACO870.AC_ARRAY[WS_I-1].AC_NME = EARWLST.KEY.AC_NAME;
                    EACO870.AC_ARRAY[WS_I-1].IO_FLG = EARWLST.IO_FLG;
                    EACO870.AC_ARRAY[WS_I-1].CON_BNK = EARWLST.CON_BNK;
                    EACO870.AC_ARRAY[WS_I-1].CON_NME = EARWLST.CON_NME;
                    EACO870.AC_ARRAY[WS_I-1].CRE_DT = EARWLST.CRT_DATE;
                    EACO870.AC_ARRAY[WS_I-1].UPD_DT = EARWLST.UPDTBL_DATE;
                    EACO870.AC_ARRAY[WS_I-1].UPD_TLR = EARWLST.UPDTBL_TLR;
                }
                if (WS_I >= 10) {
                    WS_WLST_FLG = 'Y';
                } else {
                    T000_READNEXT_WLST();
                }
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                EACO870.END_FLG = 'Y';
            } else {
                EACO870.END_FLG = 'N';
            }
            EACO870.BEG_SEQ = WS_SEQ;
            T000_ENDBR_WLST();
        } else {
            B100_INQ_WLST_BY_AC();
        }
        R_OUTPUT_PROC();
    }
    public void B100_INQ_WLST_BY_AC() throws IOException,SQLException,Exception {
        EARWLST.KEY.REQ_SYS = EACSWINQ.REQ_SYS;
        WS_SQL_REQ_SYS = EACSWINQ.REQ_SYS;
        EACO870.REQ_SYS = EACSWINQ.REQ_SYS;
        EARWLST.KEY.LST_AC = EACSWINQ.LST_AC;
        WS_SQL_AC_NO = EACSWINQ.LST_AC;
        WS_SQL_AC_NAME = EACSWINQ.AC_NAME;
        T000_READ_WLST_AC_COUNT();
        EACO870.TOTAL_CNT = WS_CNT;
        T000_STARTBR_WLST_AC();
        T000_READNEXT_WLST();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_WLST_FLG != 'Y') {
            WS_SEQ += 1;
            if (WS_SEQ > EACSWINQ.BEG_SEQ) {
                WS_I += 1;
                EACO870.AC_ARRAY[WS_I-1].AC_FLG = EARWLST.KEY.AC_FLG;
                EACSWINQ.AC_ARRAY[WS_I-1].AC_FLG = EARWLST.KEY.AC_FLG;
                EACO870.AC_ARRAY[WS_I-1].DC_FLG = EARWLST.KEY.DC_FLG;
                EACSWINQ.AC_ARRAY[WS_I-1].DC_FLG = EARWLST.KEY.DC_FLG;
                EACO870.AC_ARRAY[WS_I-1].LST_FLG = EARWLST.KEY.LST_FLG;
                EACSWINQ.AC_ARRAY[WS_I-1].LST_FLG = EARWLST.KEY.LST_FLG;
                EACO870.AC_ARRAY[WS_I-1].LST_AC = EARWLST.KEY.LST_AC;
                EACSWINQ.AC_ARRAY[WS_I-1].LAST_AC = EARWLST.KEY.LST_AC;
                EACO870.AC_ARRAY[WS_I-1].AC_NME = EARWLST.KEY.AC_NAME;
                EACSWINQ.AC_ARRAY[WS_I-1].AC_NME = EARWLST.KEY.AC_NAME;
                EACO870.AC_ARRAY[WS_I-1].IO_FLG = EARWLST.IO_FLG;
                EACSWINQ.AC_ARRAY[WS_I-1].IO_FLG = EARWLST.IO_FLG;
                EACO870.AC_ARRAY[WS_I-1].CON_BNK = EARWLST.CON_BNK;
                EACO870.AC_ARRAY[WS_I-1].CON_NME = EARWLST.CON_NME;
                EACO870.AC_ARRAY[WS_I-1].CRE_DT = EARWLST.CRT_DATE;
                EACO870.AC_ARRAY[WS_I-1].UPD_DT = EARWLST.UPDTBL_DATE;
                EACO870.AC_ARRAY[WS_I-1].UPD_TLR = EARWLST.UPDTBL_TLR;
            }
            if (WS_I >= 10) {
                WS_WLST_FLG = 'Y';
            } else {
                T000_READNEXT_WLST();
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            EACO870.END_FLG = 'Y';
        } else {
            EACO870.END_FLG = 'N';
        }
        EACO870.BEG_SEQ = WS_SEQ;
        T000_ENDBR_WLST();
    }
    public void R_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 4697;
        CEP.TRC(SCCGWA, EACO870);
        SCCFMT.DATA_PTR = EACO870;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_WLST_COUNT() throws IOException,SQLException,Exception {
        EATWLST_RD = new DBParm();
        EATWLST_RD.TableName = "EATWLST";
        EATWLST_RD.set = "WS-CNT=COUNT(*)";
        EATWLST_RD.eqWhere = "REQ_SYS";
        IBS.GROUP(SCCGWA, EARWLST, this, EATWLST_RD);
        CEP.TRC(SCCGWA, WS_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATWLST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_WLST_AC_COUNT() throws IOException,SQLException,Exception {
        EATWLST_RD = new DBParm();
        EATWLST_RD.TableName = "EATWLST";
        EATWLST_RD.set = "WS-CNT=COUNT(*)";
        EATWLST_RD.where = "( REQ_SYS = :WS_SQL_REQ_SYS "
            + "OR :WS_SQL_REQ_SYS = ' ' ) "
            + "AND ( LST_AC = :WS_SQL_AC_NO "
            + "OR :WS_SQL_AC_NO = ' ' ) "
            + "AND ( AC_NAME = :WS_SQL_AC_NAME "
            + "OR :WS_SQL_AC_NAME = ' ' )";
        IBS.GROUP(SCCGWA, EARWLST, this, EATWLST_RD);
        CEP.TRC(SCCGWA, WS_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_RECORD_NOTFND);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "EATWLST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_WLST_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SQL_REQ_SYS);
        CEP.TRC(SCCGWA, WS_SQL_AC_NO);
        CEP.TRC(SCCGWA, WS_SQL_AC_NAME);
        EATWLST_BR.rp = new DBParm();
        EATWLST_BR.rp.TableName = "EATWLST";
        EATWLST_BR.rp.where = "( REQ_SYS = :WS_SQL_REQ_SYS "
            + "OR :WS_SQL_REQ_SYS = ' ' ) "
            + "AND ( LST_AC = :WS_SQL_AC_NO "
            + "OR :WS_SQL_AC_NO = ' ' ) "
            + "AND ( AC_NAME = :WS_SQL_AC_NAME "
            + "OR :WS_SQL_AC_NAME = ' ' )";
        IBS.STARTBR(SCCGWA, EARWLST, this, EATWLST_BR);
    }
    public void T000_STARTBR_WLST() throws IOException,SQLException,Exception {
        EATWLST_BR.rp = new DBParm();
        EATWLST_BR.rp.TableName = "EATWLST";
        EATWLST_BR.rp.eqWhere = "REQ_SYS";
        IBS.STARTBR(SCCGWA, EARWLST, EATWLST_BR);
    }
    public void T000_READNEXT_WLST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EARWLST, this, EATWLST_BR);
    }
    public void T000_ENDBR_WLST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EATWLST_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
