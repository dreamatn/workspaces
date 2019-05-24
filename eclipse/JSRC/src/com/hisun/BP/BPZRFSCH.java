package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFSCH {
    DBParm BPTFSCH_RD;
    brParm BPTFSCH_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFSCH";
    String K_TBL_FARM = "BPTFSCH ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFSCH BPRFSCH = new BPRFSCH();
    SCCGWA SCCGWA;
    BPCRFSCH BPCRFSCH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFSCH BPRFSCH1;
    public void MP(SCCGWA SCCGWA, BPCRFSCH BPCRFSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFSCH = BPCRFSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFSCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFSCH1 = (BPRFSCH) BPCRFSCH.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFSCH);
        BPCRFSCH.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFSCH.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRFSCH1, BPRFSCH);
        CEP.TRC(SCCGWA, BPRFSCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFSCH.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFSCH, BPRFSCH1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFSCH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFSCH_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFSCH();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFSCH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFSCH.INFO.OPT == '0') {
            T000_STARTBR_BPTFSCH_0();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '1') {
            T000_STARTBR_BPTFSCH_1();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '2') {
            T000_STARTBR_BPTFSCH_2();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '3') {
            T000_STARTBR_BPTFSCH_3();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '4') {
            T000_STARTBR_BPTFSCH_4();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '5') {
            T000_STARTBR_BPTFSCH_5();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '6') {
            T000_STARTBR_BPTFSCH_6();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '7') {
            T000_STARTBR_BPTFSCH_7();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == '8') {
            T000_STARTBR_BPTFSCH_8();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == 'M') {
            T000_STARTBR_BPTFSCH_LMT();
            if (pgmRtn) return;
        } else if (BPCRFSCH.INFO.OPT == 'O') {
            T000_STARTBR_BPTFSCH_COL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFSCH.RC);
        }
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFSCH();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFSCH();
        if (pgmRtn) return;
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        IBS.DELETE(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFSCH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFSCH() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        IBS.READ(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFSCH.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_0() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.order = "CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFSCH, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_1() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "( FEE_STATUS = '3' "
            + "AND UPDTBL_DATE = :BPRFSCH.UPDTBL_DATE ) "
            + "OR ( FEE_STATUS = '2' "
            + "AND UPDTBL_DATE = :BPRFSCH.UPDTBL_DATE ) "
            + "OR FEE_STATUS = '1' "
            + "OR FEE_STATUS = '0'";
        BPTFSCH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_2() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "START_DATE < :BPRFSCH.START_DATE "
            + "AND MATURITY_DATE > :BPRFSCH.MATURITY_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_3() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "FEE_STATUS = :BPRFSCH.FEE_STATUS";
        BPTFSCH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_4() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "REL_CTRT_NO = :BPRFSCH.REL_CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_5() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "CI_NO LIKE :BPRFSCH.CI_NO "
            + "AND FEE_TYPE LIKE :BPRFSCH.FEE_TYPE "
            + "AND FEE_STATUS LIKE :BPRFSCH.FEE_STATUS";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_6() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "START_DATE >= :BPRFSCH.START_DATE "
            + "AND MATURITY_DATE <= :BPRFSCH.MATURITY_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_7() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "START_DATE <= :BPRFSCH.START_DATE "
            + "AND MATURITY_DATE >= :BPRFSCH.MATURITY_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_8() throws IOException,SQLException,Exception {
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "FEE_STATUS = :BPRFSCH.FEE_STATUS "
            + "AND CTRT_NO > :BPRFSCH.KEY.CTRT_NO";
        BPTFSCH_BR.rp.order = "CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_LMT() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.where = "( FEE_STATUS = '1' "
            + "OR FEE_STATUS = '2' ) "
            + "AND REL_LMT_NO = :BPRFSCH.REL_LMT_NO";
        BPTFSCH_RD.order = "CTRT_NO";
        IBS.READ(SCCGWA, BPRFSCH, this, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCH_COL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCH.REL_COL_NO);
        null.fst = true;
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.where = "( FEE_STATUS = '1' "
            + "OR FEE_STATUS = '2' ) "
            + "AND REL_COL_NO = :BPRFSCH.REL_COL_NO";
        BPTFSCH_RD.order = "CTRT_NO";
        IBS.READ(SCCGWA, BPRFSCH, this, BPTFSCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFSCH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFSCH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFSCH_BR);
    }
    public void T000_WRITE_BPTFSCH() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFSCH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFSCH_UPD() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFSCH() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        IBS.REWRITE(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFSCH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFSCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFSCH = ");
            CEP.TRC(SCCGWA, BPCRFSCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
