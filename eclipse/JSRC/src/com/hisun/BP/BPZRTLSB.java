package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTLSB {
    brParm BPTTLSC_BR = new brParm();
    DBParm BPTTLSC_RD;
    boolean pgmRtn = false;
    String TBL_LOG = "BPTTLSC";
    String K_PGM_NAME = "BPZRTLSC";
    String K_TBL_TLT = "BPRTLSC";
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCRTLSB BPCRTLSB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLSC BPRTLSL;
    public void MP(SCCGWA SCCGWA, BPCRTLSB BPCRTLSB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTLSB = BPCRTLSB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTLSB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLSL = (BPRTLSC) BPCRTLSB.POINTER;
        IBS.init(SCCGWA, BPRTLSC);
        IBS.CLONE(SCCGWA, BPRTLSL, BPRTLSC);
        BPCRTLSB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTLSB.FUNC == 'F') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'C') {
            B020_STARTBR_CARD_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'S') {
            B030_STARTBR_TLR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'L') {
            B040_STARTBR_ALL_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'U') {
            B050_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'B') {
            B080_STARTBR_BOW_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'T') {
            B090_STARTBR_UPDATE_STS();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'R') {
            B060_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLSB.FUNC == 'E') {
            B070_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTLSB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLSC, BPRTLSL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B020_STARTBR_CARD_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CARD();
        if (pgmRtn) return;
    }
    public void B030_STARTBR_TLR_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TLR();
        if (pgmRtn) return;
    }
    public void B060_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPDATE();
        if (pgmRtn) return;
    }
    public void B080_STARTBR_BOW_PROC() throws IOException,SQLException,Exception {
        if (BPRTLSC.KEY.BR == 0) {
            T000_STARTBR_BOW();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BOW_BR();
            if (pgmRtn) return;
        }
    }
    public void B070_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_ALL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_ALL();
        if (pgmRtn) return;
    }
    public void B090_STARTBR_UPDATE_STS() throws IOException,SQLException,Exception {
        T000_STARTBR_UPDATE_STS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND SC_STS = :BPRTLSC.SC_STS";
        BPTTLSC_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_UPDATE_STS() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND SC_STS = :BPRTLSC.SC_STS";
        BPTTLSC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CARD() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "CODE_NO = :BPRTLSC.KEY.CODE_NO";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TLR() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "UPD_TLR = :BPRTLSC.UPD_TLR";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_UPDATE() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO";
        BPTTLSC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BOW() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "PL_BOX_NO LIKE :BPRTLSC.KEY.PL_BOX_NO "
            + "AND CODE_NO LIKE :BPRTLSC.KEY.CODE_NO "
            + "AND SC_TYPE LIKE :BPRTLSC.SC_TYPE "
            + "AND SC_STS LIKE :BPRTLSC.SC_STS";
        BPTTLSC_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BOW_BR() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "BR = :BPRTLSC.KEY.BR "
            + "AND PL_BOX_NO LIKE :BPRTLSC.KEY.PL_BOX_NO "
            + "AND CODE_NO LIKE :BPRTLSC.KEY.CODE_NO "
            + "AND SC_TYPE LIKE :BPRTLSC.SC_TYPE "
            + "AND SC_STS LIKE :BPRTLSC.SC_STS";
        BPTTLSC_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTLSC_BR);
    }
    public void T000_STARTBR_ALL() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO";
        BPTTLSC_BR.rp.errhdl = true;
        BPTTLSC_BR.rp.order = "UPD_TLR";
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLSB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLSB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_LOG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTLSB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTLSB = ");
            CEP.TRC(SCCGWA, BPCRTLSB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
