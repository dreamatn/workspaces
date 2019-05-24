package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRRNUM {
    DBParm BPTRNUM_RD;
    brParm BPTRNUM_BR = new brParm();
    boolean pgmRtn = false;
    int WS_COUNT = 0;
    char WS_TBL_RNUM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRNUM BPRRNUM = new BPRRNUM();
    SCCGWA SCCGWA;
    BPCRRNUM BPCRRNUM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRRNUM BPRRNUML;
    public void MP(SCCGWA SCCGWA, BPCRRNUM BPCRRNUM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRNUM = BPCRRNUM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRRNUM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRNUML = (BPRRNUM) BPCRRNUM.POINTER;
        CEP.TRC(SCCGWA, "00");
        CEP.TRC(SCCGWA, "11");
        IBS.CLONE(SCCGWA, BPRRNUML, BPRRNUM);
        CEP.TRC(SCCGWA, "22");
        BPCRRNUM.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "33");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRNUM.FUNC == 'A') {
            B010_RECORD_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'Q') {
            B020_RECORD_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'Y') {
            B020_RECORD_INQ_1_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'D') {
            B030_RECORD_DLE_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'M') {
            B040_READUPD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'U') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'S') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'R') {
            B070_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRRNUM.FUNC == 'E') {
            B080_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRRNUM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRRNUM, BPRRNUML);
    }
    public void B010_RECORD_ADD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_1_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_1_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B030_RECORD_DLE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (BPCRRNUM.OPT == '1') {
            T000_STARTBR_BPRRNUM_1();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '2') {
            T000_STARTBR_BPRRNUM_2();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '3') {
            T000_STARTBR_BPRRNUM_3();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '4') {
            T000_STARTBR_BPRRNUM_4();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '5') {
            T000_STARTBR_BPRRNUM_5();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '6') {
            T000_STARTBR_BPRRNUM_6();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '7') {
            T000_STARTBR_BPRRNUM_7();
            if (pgmRtn) return;
        } else if (BPCRRNUM.OPT == '8') {
            T000_STARTBR_BPRRNUM_8();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRRNUM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRRNUM();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRRNUM();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPRRNUM() throws IOException,SQLException,Exception {
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        IBS.WRITE(SCCGWA, BPRRNUM, BPTRNUM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRNUM.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_BPRRNUM() throws IOException,SQLException,Exception {
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        IBS.READ(SCCGWA, BPRRNUM, BPTRNUM_RD);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.BR);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.CCY);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRNUM.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_1_BPRRNUM() throws IOException,SQLException,Exception {
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        BPTRNUM_RD.where = "BR = :BPRRNUM.KEY.BR";
        IBS.READ(SCCGWA, BPRRNUM, this, BPTRNUM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUPD_BPRRNUM() throws IOException,SQLException,Exception {
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        BPTRNUM_RD.upd = true;
        IBS.READ(SCCGWA, BPRRNUM, BPTRNUM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPRRNUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRRNUM.KEY.BR);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.CCY);
        CEP.TRC(SCCGWA, BPRRNUM.CRNUM);
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        IBS.REWRITE(SCCGWA, BPRRNUM, BPTRNUM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPRRNUM() throws IOException,SQLException,Exception {
        BPTRNUM_RD = new DBParm();
        BPTRNUM_RD.TableName = "BPTRNUM";
        IBS.DELETE(SCCGWA, BPRRNUM, BPTRNUM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_1() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        IBS.STARTBR(SCCGWA, BPRRNUM, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_2() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "BR = :BPRRNUM.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_3() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "CCY = :BPRRNUM.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_4() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "CRNUM = :BPRRNUM.CRNUM";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_5() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "BR = :BPRRNUM.KEY.BR "
            + "AND CCY = :BPRRNUM.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_6() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "BR = :BPRRNUM.KEY.BR "
            + "AND CRNUM = :BPRRNUM.CRNUM";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_7() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "CCY = :BPRRNUM.KEY.CCY "
            + "AND CRNUM = :BPRRNUM.CRNUM";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRRNUM_8() throws IOException,SQLException,Exception {
        BPTRNUM_BR.rp = new DBParm();
        BPTRNUM_BR.rp.TableName = "BPTRNUM";
        BPTRNUM_BR.rp.where = "BR = :BPRRNUM.KEY.BR "
            + "AND CCY = :BPRRNUM.KEY.CCY "
            + "AND CRNUM = :BPRRNUM.CRNUM";
        IBS.STARTBR(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRRNUM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRNUM, this, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRRNUM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRNUM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRNUM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRNUM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRRNUM.RETURN_INFO);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRNUM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRRNUM = ");
            CEP.TRC(SCCGWA, BPCRRNUM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
