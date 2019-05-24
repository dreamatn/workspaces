package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSCDT {
    DBParm BPTTLSC_RD;
    brParm BPTTLSC_BR = new brParm();
    boolean pgmRtn = false;
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCRSCDT BPCRSCDT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLSC BPRTLSL;
    public void MP(SCCGWA SCCGWA, BPCRSCDT BPCRSCDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSCDT = BPCRSCDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRSCDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLSL = (BPRTLSC) BPCRSCDT.POINTER;
        IBS.init(SCCGWA, BPRTLSC);
        IBS.CLONE(SCCGWA, BPRTLSL, BPRTLSC);
        BPCRSCDT.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSCDT.FUNC == 'P') {
            B010_QUERY_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'A') {
            B020_QUERY_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'U') {
            B030_UPDATE_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'M') {
            B040_MODIFY_STS_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'S') {
            B050_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'R') {
            B060_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'E') {
            B070_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'I') {
            B080_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCDT.FUNC == 'D') {
            B090_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLSC, BPRTLSL);
    }
    public void B010_QUERY_STS_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_STS_P_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B020_QUERY_STS_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_STS_A_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_STS_PROC() throws IOException,SQLException,Exception {
        T000_UPDATE_STS_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_STS_PROC() throws IOException,SQLException,Exception {
        T000_MODIFY_STS_BPRTLSC();
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
    public void B080_ADD_PROC() throws IOException,SQLException,Exception {
        T000_ADD_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B090_DELETE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRTLSC();
        if (pgmRtn) return;
    }
    public void T000_QUERY_STS_P_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "CODE_NO = :BPRTLSC.KEY.CODE_NO "
            + "AND SC_STS = '1'";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_QUERY_STS_A_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.where = "PL_BOX_NO = :BPRTLSC.KEY.PL_BOX_NO "
            + "AND UPD_TLR = :BPRTLSC.UPD_TLR "
            + "AND SC_STS = '1'";
        BPTTLSC_RD.fst = true;
        BPTTLSC_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLSC, this, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_UPDATE_STS_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        BPTTLSC_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_MODIFY_STS_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.REWRITE(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "UPD_TLR = :BPRTLSC.UPD_TLR";
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRTLSC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLSC_BR);
    }
    public void T000_ADD_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.WRITE(SCCGWA, BPRTLSC, BPTTLSC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_RD = new DBParm();
        BPTTLSC_RD.TableName = "BPTTLSC";
        IBS.DELETE(SCCGWA, BPRTLSC, BPTTLSC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSCDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSCDT = ");
            CEP.TRC(SCCGWA, BPCRSCDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
