package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFCTR {
    DBParm BPTFCTR_RD;
    brParm BPTFCTR_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFCTR";
    String K_TBL_FARM = "BPTFCTR ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFCTR BPRFCTR = new BPRFCTR();
    SCCGWA SCCGWA;
    BPCRFCTR BPCRFCTR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFCTR BPRFCTR1;
    public void MP(SCCGWA SCCGWA, BPCRFCTR BPCRFCTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFCTR = BPCRFCTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFCTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFCTR1 = (BPRFCTR) BPCRFCTR.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFCTR);
        BPCRFCTR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFCTR.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRFCTR1, BPRFCTR);
        CEP.TRC(SCCGWA, BPRFCTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFCTR.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFCTR, BPRFCTR1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFCTR_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFCTR.INFO.OPT == '0') {
            T000_STARTBR_BPTFCTR_0();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '1') {
            T000_STARTBR_BPTFCTR_1();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '2') {
            T000_STARTBR_BPTFCTR_2();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '3') {
            T000_STARTBR_BPTFCTR_3();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '4') {
            T000_STARTBR_BPTFCTR_4();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '7') {
            T000_STARTBR_BPTFCTR_7();
            if (pgmRtn) return;
        } else if (BPCRFCTR.INFO.OPT == '8') {
            T000_STARTBR_BPTFCTR_8();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFCTR.RC);
        }
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFCTR();
        if (pgmRtn) return;
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.DELETE(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFCTR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFCTR.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_0() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.order = "CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFCTR, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_1() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "( FEE_STATUS = '3' "
            + "AND UPDTBL_DATE = :BPRFCTR.UPDTBL_DATE ) "
            + "OR ( FEE_STATUS = '2' "
            + "AND UPDTBL_DATE = :BPRFCTR.UPDTBL_DATE ) "
            + "OR FEE_STATUS = '1' "
            + "OR FEE_STATUS = '0'";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_2() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "START_DATE < :BPRFCTR.START_DATE "
            + "AND MATURITY_DATE > :BPRFCTR.MATURITY_DATE";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_3() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "FEE_STATUS = :BPRFCTR.FEE_STATUS";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_4() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "REL_CTRT_NO = :BPRFCTR.REL_CTRT_NO";
        BPTFCTR_BR.rp.order = "CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_7() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "START_DATE <= :BPRFCTR.START_DATE "
            + "AND MATURITY_DATE >= :BPRFCTR.MATURITY_DATE";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFCTR_8() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "FEE_STATUS = :BPRFCTR.FEE_STATUS "
            + "AND CTRT_NO > :BPRFCTR.KEY.CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
    }
    public void T000_WRITE_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFCTR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFCTR_UPD() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFCTR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.REWRITE(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFCTR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFCTR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFCTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFCTR = ");
            CEP.TRC(SCCGWA, BPCRFCTR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
