package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTWND {
    DBParm BPTTWND_RD;
    brParm BPTTWND_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_THOL = "BPTTWND";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTWND BPRTWND = new BPRTWND();
    SCCGWA SCCGWA;
    BPCRTWND BPCRTWND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTWND BPRLTWND;
    public void MP(SCCGWA SCCGWA, BPCRTWND BPCRTWND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTWND = BPCRTWND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTWND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRLTWND = (BPRTWND) BPCRTWND.INFO.POINTER;
        IBS.init(SCCGWA, BPRTWND);
        IBS.CLONE(SCCGWA, BPRLTWND, BPRTWND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTWND.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'I') {
            B070_BROWSE_DATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.FUNC == 'T') {
            B080_BROWSE_TS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRTWND.INFO.DATA_LEN);
        IBS.CLONE(SCCGWA, BPRTWND, BPRLTWND);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTWND();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTWND_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTWND();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTWND();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTWND();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTWND.INFO.OPT == 'S') {
            if (BPCRTWND.INFO.BRW_IDX == '0') {
                T000_STARTBR_BPTTWND_INDEX0();
                if (pgmRtn) return;
            }
            if (BPCRTWND.INFO.BRW_IDX == '1') {
                T000_STARTBR_BPTTWND_INDEX1();
                if (pgmRtn) return;
            }
            if (BPCRTWND.INFO.OPT == 'S') {
                T000_STARTBR_PROCESS();
                if (pgmRtn) return;
            }
        } else if (BPCRTWND.INFO.OPT == 'N') {
            T000_READNEXT_BPTTWND();
            if (pgmRtn) return;
        } else if (BPCRTWND.INFO.OPT == 'E') {
            T000_ENDBR_BPTTWND();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_BROWSE_DATE_PROC() throws IOException,SQLException,Exception {
        T070_BROWSE_DATE_PROCESS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTWND() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        BPTTWND_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTWND, BPTTWND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTTWND() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        BPTTWND_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTWND, BPTTWND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTWND.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTWND_UPD() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        BPTTWND_RD.upd = true;
        BPTTWND_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTWND, BPTTWND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTTWND() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        IBS.REWRITE(SCCGWA, BPRTWND, BPTTWND_RD);
    }
    public void T000_DELETE_BPTTWND() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        IBS.DELETE(SCCGWA, BPRTWND, BPTTWND_RD);
    }
    public void T000_STARTBR_BPTTWND_INDEX0() throws IOException,SQLException,Exception {
        BPTTWND_BR.rp = new DBParm();
        BPTTWND_BR.rp.TableName = "BPTTWND";
        BPTTWND_BR.rp.order = "CAL_CD,TS";
        IBS.STARTBR(SCCGWA, BPRTWND, BPTTWND_BR);
    }
    public void T000_STARTBR_BPTTWND_INDEX1() throws IOException,SQLException,Exception {
        BPTTWND_BR.rp = new DBParm();
        BPTTWND_BR.rp.TableName = "BPTTWND";
        BPTTWND_BR.rp.order = "CAL_CD,TS";
        IBS.STARTBR(SCCGWA, BPRTWND, BPTTWND_BR);
    }
    public void T000_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        if (BPRTWND.KEY.CAL_CD.trim().length() > 0 
            && BPRTWND.KEY.EFF_DATE == 0) {
            BPTTWND_BR.rp = new DBParm();
            BPTTWND_BR.rp.TableName = "BPTTWND";
            BPTTWND_BR.rp.where = "CAL_CD = :BPRTWND.KEY.CAL_CD";
            BPTTWND_BR.rp.errhdl = true;
            BPTTWND_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTWND, this, BPTTWND_BR);
        }
        if (BPRTWND.KEY.CAL_CD.trim().length() > 0 
            && BPRTWND.KEY.EFF_DATE != 0) {
            BPTTWND_BR.rp = new DBParm();
            BPTTWND_BR.rp.TableName = "BPTTWND";
            BPTTWND_BR.rp.where = "CAL_CD = :BPRTWND.KEY.CAL_CD "
                + "AND EFF_DATE >= :BPRTWND.KEY.EFF_DATE";
            BPTTWND_BR.rp.errhdl = true;
            BPTTWND_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTWND, this, BPTTWND_BR);
        }
        if (BPRTWND.KEY.CAL_CD.trim().length() == 0 
            && BPRTWND.KEY.EFF_DATE == 0) {
            BPTTWND_BR.rp = new DBParm();
            BPTTWND_BR.rp.TableName = "BPTTWND";
            BPTTWND_BR.rp.errhdl = true;
            BPTTWND_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTWND, BPTTWND_BR);
        }
        if (BPRTWND.KEY.CAL_CD.trim().length() == 0 
            && BPRTWND.KEY.EFF_DATE != 0) {
            BPTTWND_BR.rp = new DBParm();
            BPTTWND_BR.rp.TableName = "BPTTWND";
            BPTTWND_BR.rp.where = "EFF_DATE >= :BPRTWND.KEY.EFF_DATE";
            BPTTWND_BR.rp.errhdl = true;
            BPTTWND_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTWND, this, BPTTWND_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_READNEXT_BPTTWND() throws IOException,SQLException,Exception {
        BPTTWND_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTWND, this, BPTTWND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTWND() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTWND_BR);
        CEP.TRC(SCCGWA, "=== ENDBR ====");
    }
    public void T070_BROWSE_DATE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTWND.KEY.CAL_CD);
        BPRTWND.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        BPTTWND_RD.where = "CAL_CD = :BPRTWND.KEY.CAL_CD "
            + "AND EFF_DATE <= :BPRTWND.KEY.EFF_DATE";
        BPTTWND_RD.fst = true;
        BPTTWND_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, BPRTWND, this, BPTTWND_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRTWND.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DB ERROR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B080_BROWSE_TS_PROC() throws IOException,SQLException,Exception {
        BPTTWND_RD = new DBParm();
        BPTTWND_RD.TableName = "BPTTWND";
        BPTTWND_RD.where = "CAL_CD = :BPRTWND.KEY.CAL_CD";
        BPTTWND_RD.fst = true;
        BPTTWND_RD.errhdl = true;
        BPTTWND_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BPRTWND, this, BPTTWND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTWND.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTWND.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRTWND.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DB ERROR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTWND.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTWND = ");
            CEP.TRC(SCCGWA, BPCRTWND);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
