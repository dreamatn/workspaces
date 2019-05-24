package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSCHD {
    DBParm BPTFSCHD_RD;
    brParm BPTFSCHD_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRSCHD";
    String K_TBL_FARM = "BPTFSCHD";
    String WS_TEMP_RECORD = " ";
    short WS_SCHD_COUNT = 0;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    SCCGWA SCCGWA;
    BPCRSCHD BPCRSCHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFSCHD BPRFSCHL;
    public void MP(SCCGWA SCCGWA, BPCRSCHD BPCRSCHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSCHD = BPCRSCHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRSCHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFSCHL = (BPRFSCHD) BPCRSCHD.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFSCHD);
        BPCRSCHD.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRSCHD.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRFSCHL, BPRFSCHD);
        CEP.TRC(SCCGWA, BPRFSCHD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSCHD.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.FUNC == '1'
            || BPCRSCHD.INFO.FUNC == '2') {
            B090_GROUP_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFSCHD, BPRFSCHL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFSCHD();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFSCHD_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFSCHD();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFSCHD();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRSCHD.INFO.OPT);
        if (BPCRSCHD.INFO.OPT == '0') {
            T000_STARTBR_BPTFSCHD_0();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.OPT == '1') {
            T000_STARTBR_BPTFSCHD_1();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.OPT == '2') {
            T000_STARTBR_BPTFSCHD_2();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.OPT == '3') {
            T000_STARTBR_BPTFSCHD_3();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.OPT == 'A') {
            T000_STARTBR_BPTFSCHD_01();
            if (pgmRtn) return;
        } else if (BPCRSCHD.INFO.OPT == 'B') {
            T000_STARTBR_BPTFSCHD_02();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCHD.RC);
        }
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFSCHD();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFSCHD();
        if (pgmRtn) return;
    }
    public void B090_GROUP_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRSCHD.INFO.OPT);
        if (BPCRSCHD.INFO.FUNC == '1') {
            T000_GROUP_BPTFSCHD_01();
            if (pgmRtn) return;
            BPCRSCHD.INFO.SCHD_COUNT = WS_SCHD_COUNT;
        } else if (BPCRSCHD.INFO.FUNC == '2') {
            T000_GROUP_BPTFSCHD_02();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCHD.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        IBS.DELETE(SCCGWA, BPRFSCHD, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSCHD.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFSCHD() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        IBS.READ(SCCGWA, BPRFSCHD, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSCHD.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_0() throws IOException,SQLException,Exception {
        BPTFSCHD_BR.rp = new DBParm();
        BPTFSCHD_BR.rp.TableName = "BPTFSCHD";
        IBS.STARTBR(SCCGWA, BPRFSCHD, BPTFSCHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_1() throws IOException,SQLException,Exception {
        BPTFSCHD_BR.rp = new DBParm();
        BPTFSCHD_BR.rp.TableName = "BPTFSCHD";
        BPTFSCHD_BR.rp.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO "
            + "AND SETTLE_DATE > :BPRFSCHD.KEY.SETTLE_DATE";
        BPTFSCHD_BR.rp.order = "SETTLE_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_2() throws IOException,SQLException,Exception {
        BPTFSCHD_BR.rp = new DBParm();
        BPTFSCHD_BR.rp.TableName = "BPTFSCHD";
        BPTFSCHD_BR.rp.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO "
            + "AND STS = :BPRFSCHD.STS";
        BPTFSCHD_BR.rp.order = "SETTLE_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_3() throws IOException,SQLException,Exception {
        BPTFSCHD_BR.rp = new DBParm();
        BPTFSCHD_BR.rp.TableName = "BPTFSCHD";
        BPTFSCHD_BR.rp.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO";
        BPTFSCHD_BR.rp.order = "SETTLE_DATE";
        IBS.STARTBR(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_01() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO "
            + "AND SETTLE_DATE < :BPRFSCHD.KEY.SETTLE_DATE";
        BPTFSCHD_RD.fst = true;
        BPTFSCHD_RD.order = "CTRT_NO,SETTLE_DATE DESC";
        IBS.READ(SCCGWA, BPRFSCHD, this, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFSCHD_02() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO "
            + "AND SETTLE_DATE > :BPRFSCHD.KEY.SETTLE_DATE";
        BPTFSCHD_RD.fst = true;
        BPTFSCHD_RD.order = "CTRT_NO,SETTLE_DATE";
        IBS.READ(SCCGWA, BPRFSCHD, this, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFSCHD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFSCHD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFSCHD_BR);
    }
    public void T000_WRITE_BPTFSCHD() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFSCHD, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSCHD.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFSCHD_UPD() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.upd = true;
        IBS.READ(SCCGWA, BPRFSCHD, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFSCHD() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        IBS.REWRITE(SCCGWA, BPRFSCHD, BPTFSCHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSCHD.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_GROUP_BPTFSCHD_01() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.set = "WS-SCHD-COUNT=COUNT(*)";
        BPTFSCHD_RD.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO";
        IBS.GROUP(SCCGWA, BPRFSCHD, this, BPTFSCHD_RD);
    }
    public void T000_GROUP_BPTFSCHD_02() throws IOException,SQLException,Exception {
        BPTFSCHD_RD = new DBParm();
        BPTFSCHD_RD.TableName = "BPTFSCHD";
        BPTFSCHD_RD.set = "WS-SCHD-COUNT=COUNT(*)";
        BPTFSCHD_RD.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO";
        IBS.GROUP(SCCGWA, BPRFSCHD, this, BPTFSCHD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSCHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSCHD = ");
            CEP.TRC(SCCGWA, BPCRSCHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
