package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSCBB {
    DBParm BPTTLSC_RD;
    brParm BPTTLSC_BR = new brParm();
    boolean pgmRtn = false;
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCRSCBB BPCRSCBB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLSC BPRTLSCL;
    public void MP(SCCGWA SCCGWA, BPCRSCBB BPCRSCBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSCBB = BPCRSCBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRSCBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLSCL = (BPRTLSC) BPCRSCBB.POINTER;
        IBS.init(SCCGWA, BPRTLSC);
        IBS.CLONE(SCCGWA, BPRTLSCL, BPRTLSC);
        BPCRSCBB.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSCBB.FUNC == 'P') {
            B010_QUERY_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'A') {
            B020_QUERY_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'M') {
            B030_MODIF_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'U') {
            B040_READU_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'S') {
            B050_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'R') {
            B060_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'E') {
            B070_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'D') {
            B080_DELETE_A_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'L') {
            B090_DELETE_P_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCBB.FUNC == 'D') {
            B100_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCBB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLSC, BPRTLSCL);
    }
    public void B010_QUERY_STS_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_STS_P_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B020_QUERY_STS_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_STS_A_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B030_MODIF_STS_PROC() throws IOException,SQLException,Exception {
        T000_MODIF_STS_M_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B040_READU_STS_PROC() throws IOException,SQLException,Exception {
        T000_READUPDATE_STS_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B060_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B070_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B080_DELETE_A_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_A_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B090_DELETE_P_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_P_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRTLSC();
        if (pgmRtn) return;
    }
    public void T000_QUERY_STS_P_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "CODE_NO = :BPRTLSC.KEY.CODE_NO "
            + "AND SC_STS < > '0'";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_QUERY_STS_A_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND UPD_TLR = :BPRTLSC.UPD_TLR "
            + "AND SC_STS < > '0'";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READUPDATE_STS_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "UPD_TLR = :BPRTLSC.UPD_TLR";
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_A_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND UPD_TLR = :BPRTLSC.UPD_TLR "
            + "AND CODE_NO = :BPRTLSC.KEY.CODE_NO "
            + "AND SC_STS = '1'";
        BPTTLSC_RD.errhdl = true;
        IBS.DELETE(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_P_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "CODE_NO = :BPRTLSC.KEY.CODE_NO "
            + "AND SC_STS = '1'";
        BPTTLSC_RD.errhdl = true;
        IBS.DELETE(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.errhdl = true;
        IBS.DELETE(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCBB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCBB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_MODIF_STS_M_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.REWRITE(SCCGWA, BPRTLSC, BPTTLSC_RD);
    }
    public void T000_ENDBR_BPRTLSC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLSC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSCBB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSCBB = ");
            CEP.TRC(SCCGWA, BPCRSCBB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
