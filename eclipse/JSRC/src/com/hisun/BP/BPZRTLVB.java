package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTLVB {
    brParm BPTTLVB_BR = new brParm();
    DBParm BPTTLVB_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTLVB";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TLT = "BPTTLVB ";
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLVB BPRTLVB = new BPRTLVB();
    SCCGWA SCCGWA;
    BPCRTLVB BPCRTLVB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLVB BPRTLVL;
    public void MP(SCCGWA SCCGWA, BPCRTLVB BPCRTLVB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTLVB = BPCRTLVB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTLVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLVL = (BPRTLVB) BPCRTLVB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTLVB);
        IBS.CLONE(SCCGWA, BPRTLVL, BPRTLVB);
        BPCRTLVB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTLVB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'B') {
            B015_STARTBR_BR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'U') {
            B040_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'Q') {
            B060_STARTBR_INQ_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "STARTBR");
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        } else if (BPCRTLVB.INFO.FUNC == 'T') {
            B070_STARTBR_TLR_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'O') {
            B080_STARTBR_BRO_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'P') {
            B090_STARTBR_PLBOX_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'R') {
            B100_STARTBR_BR_TLR_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLVB.INFO.FUNC == 'L') {
            B110_STARTBR_ALL_TLR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTLVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLVB, BPRTLVL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_BR_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLVB_BR();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLVB_UPD();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B060");
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        T000_STARTBR_INQ();
        if (pgmRtn) return;
    }
    public void B070_STARTBR_TLR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B070");
        T000_BPTTLVB_TLR();
        if (pgmRtn) return;
    }
    public void B080_STARTBR_BRO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B080");
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        T000_STARTBR_BRO();
        if (pgmRtn) return;
    }
    public void B090_STARTBR_PLBOX_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PLBOX();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_BR_TLR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        T000_STARTBR_BR_TLR();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_ALL_TLR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL_TLR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO >= :BPRTLVB.KEY.PLBOX_NO";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLVB_BR() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO = :BPRTLVB.KEY.PLBOX_NO "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLVB_UPD() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.upd = true;
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO >= :BPRTLVB.KEY.PLBOX_NO "
            + "AND PLBOX_TP >= :BPRTLVB.PLBOX_TP";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_INQ() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO LIKE :BPRTLVB.KEY.PLBOX_NO";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_BPTTLVB_TLR() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP LIKE :BPRTLVB.PLBOX_TP "
            + "AND ( CRNT_TLR LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 LIKE :BPRTLVB.CRNT_TLR )";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BRO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        CEP.TRC(SCCGWA, BPRTLVB.BIND_TYPE);
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO LIKE :BPRTLVB.KEY.PLBOX_NO "
            + "AND PLBOX_TP LIKE :BPRTLVB.PLBOX_TP "
            + "AND ( CRNT_TLR LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 LIKE :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 LIKE :BPRTLVB.CRNT_TLR ) "
            + "AND BIND_TYPE LIKE :BPRTLVB.BIND_TYPE";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTLVB_BR);
    }
    public void T000_REWRITE_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        IBS.REWRITE(SCCGWA, BPRTLVB, BPTTLVB_RD);
    }
    public void T000_STARTBR_PLBOX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BR_TLR() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "( BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR ) "
            + "OR ( BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR1 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR2 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR3 = :BPRTLVB.CRNT_TLR ) "
            + "OR ( BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_TP = :BPRTLVB.PLBOX_TP "
            + "AND CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_ALL_TLR() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR";
        BPTTLVB_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTLVB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTLVB = ");
            CEP.TRC(SCCGWA, BPCRTLVB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
