package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRINVT {
    DBParm BPTINVT_RD;
    brParm BPTINVT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRINVT";
    String K_TBL_INVT = "BPTINVT";
    short WS_MAX_JRN_SEQ = 0;
    int WS_BG_DATE = 0;
    int WS_END_DATE = 0;
    char WS_STARTBR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRINVT BPRINVT = new BPRINVT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCTINVT BPCTINVT;
    BPRINVT BPRLNVT;
    public void MP(SCCGWA SCCGWA, BPCTINVT BPCTINVT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTINVT = BPCTINVT;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        CEP.TRC(SCCGWA, "BPZRINVT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLNVT = (BPRINVT) BPCTINVT.POINTER;
        CEP.TRC(SCCGWA, "INIT SET ADDRESS");
        IBS.init(SCCGWA, BPCTINVT.RC);
        IBS.CLONE(SCCGWA, BPRLNVT, BPRINVT);
        CEP.TRC(SCCGWA, "ILK-REC");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B000-QQQQQ");
        if (BPCTINVT.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '7') {
            B010_STARTBR1_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '8') {
            B010_STARTBR3_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '9') {
            B010_STARTBR_BV_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '2') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '3') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '4') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '5') {
            B050_CREAT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTINVT.INFO.FUNC == '6') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTINVT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRINVT, BPRLNVT);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_BG_DATE = 0;
        WS_END_DATE = 0;
        CEP.TRC(SCCGWA, WS_BG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (BPCTINVT.DATE == 0) {
            WS_BG_DATE = 0;
        } else {
            WS_BG_DATE = BPCTINVT.DATE;
        }
        if (BPCTINVT.DATE_END == 0) {
            WS_END_DATE = 999999999;
        } else {
            WS_END_DATE = BPCTINVT.DATE_END;
        }
        CEP.TRC(SCCGWA, WS_BG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        T000_STARTBR_BPTINVT();
        if (pgmRtn) return;
    }
    public void B010_STARTBR1_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR1_BPTINVT();
        if (pgmRtn) return;
    }
    public void B010_STARTBR3_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR3_BPTINVT();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_BV_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BV_BPTINVT();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTINVT();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTINVT();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.where = "'DATE' = :BPRINVT.KEY.DATE "
            + "AND JRNNO = :BPRINVT.KEY.JRNNO";
        BPTINVT_RD.upd = true;
        IBS.READ(SCCGWA, BPRINVT, this, BPTINVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        }
    }
    public void B050_CREAT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTINVT();
        if (pgmRtn) return;
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void T000_STARTBR_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND ( 'DATE' BETWEEN :WS_BG_DATE "
            + "AND :WS_END_DATE ) "
            + "AND CB_TYP = :BPRINVT.CB_TYP "
            + "AND INVNTYP = :BPRINVT.INVNTYP";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        BPCTINVT.RETURN_INFO = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR1_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND 'DATE' = :BPRINVT.KEY.DATE "
            + "AND TLR_D = :BPRINVT.TLR_D "
            + "AND CB_TYP = :BPRINVT.CB_TYP "
            + "AND INVNTYP = :BPRINVT.INVNTYP "
            + "AND CCY = :BPRINVT.CCY";
        BPTINVT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_INVT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR3_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "TX_TLR = :BPRINVT.TX_TLR "
            + "AND INVNTYP = :BPRINVT.PLBOX_TYPE";
        BPTINVT_BR.rp.order = "DATE DESC";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_INVT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BV_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND 'DATE' = :BPRINVT.KEY.DATE "
            + "AND TLR_D = :BPRINVT.TLR_D "
            + "AND CB_TYP = :BPRINVT.CB_TYP "
            + "AND INVNTYP = :BPRINVT.INVNTYP "
            + "AND CCY = :BPRINVT.CCY "
            + "AND VB_FLG = :BPRINVT.VB_FLG";
        BPTINVT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_INVT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTINVT_DT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND CB_TYP = :BPRINVT.CB_TYP "
            + "AND INVNTYP = :BPRINVT.INVNTYP";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        BPCTINVT.RETURN_INFO = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRINVT, this, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        BPCTINVT.RETURN_INFO = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTINVT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTINVT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        BPCTINVT.RETURN_INFO = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTINVT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRINVT, BPTINVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTINVT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTINVT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_INVT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTINVT.RC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTINVT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCTINVT = ");
            CEP.TRC(SCCGWA, BPCTINVT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
