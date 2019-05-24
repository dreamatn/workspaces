package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRAPPD {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTAPPD_BR = new brParm();
    DBParm BPTAPPD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRAPPD";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TLT = "BPTAPPD ";
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRAPPD BPRAPPD = new BPRAPPD();
    SCCGWA SCCGWA;
    BPCRAPPD BPCRAPPD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRAPPD BPRAPPL;
    public void MP(SCCGWA SCCGWA, BPCRAPPD BPCRAPPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRAPPD = BPCRAPPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRAPPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRAPPL = (BPRAPPD) BPCRAPPD.INFO.POINTER;
        IBS.init(SCCGWA, BPRAPPD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPPL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRAPPL, BPRAPPD);
        BPCRAPPD.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRAPPD.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '1') {
            B015_STARTBR_CINO_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '2') {
            B040_STARTBR_APT_DT_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '5') {
            B060_STARTBR_CINO_WDDT_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '8') {
            B070_STARTBR_WDDT_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '4') {
            B080_STARTBR_DT_WDDT_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '3') {
            B090_STARTBR_CINO_DT_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '6') {
            B100_STARTBR_BR_ALL_PROC();
            if (pgmRtn) return;
        } else if (BPCRAPPD.INFO.FUNC == '7') {
            B110_STARTBR_CINO_W_DT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRAPPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPPD);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRAPPD, BPRAPPL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_CINO_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTAPPD_CINO();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTAPPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_APT_DT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTAPPD_APT_DT();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_CINO_WDDT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CINO_WDDT();
        if (pgmRtn) return;
    }
    public void B070_STARTBR_WDDT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_WDDT();
        if (pgmRtn) return;
    }
    public void B080_STARTBR_DT_WDDT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_WDDT();
        if (pgmRtn) return;
    }
    public void B090_STARTBR_CINO_DT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CINO_DT();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_BR_ALL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BR_ALL();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_CINO_W_DT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CINO_W_DT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_WDDT = :BPRAPPD.APT_WDDT "
            + "AND APT_CCY = :BPRAPPD.KEY.APT_CCY "
            + "AND STS = :BPRAPPD.STS";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTAPPD_CINO() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_CINO = :BPRAPPD.APT_CINO "
            + "AND APT_BR = :BPRAPPD.APT_BR";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_CINO_WDDT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_CINO = :BPRAPPD.APT_CINO "
            + "AND APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_WDDT = :BPRAPPD.APT_WDDT";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_CINO_DT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_CINO = :BPRAPPD.APT_CINO "
            + "AND APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_DT >= :BPRAPPD.APT_DT";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_CINO_W_DT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_CINO = :BPRAPPD.APT_CINO "
            + "AND APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_WDDT = :BPRAPPD.APT_WDDT "
            + "AND APT_DT >= :BPRAPPD.APT_DT";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTAPPD_APT_DT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.upd = true;
        BPTAPPD_BR.rp.where = "APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_DT >= :BPRAPPD.APT_DT";
        BPTAPPD_BR.rp.errhdl = true;
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_WDDT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_WDDT = :BPRAPPD.APT_WDDT";
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_DT_WDDT() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_DT >= :BPRAPPD.APT_DT "
            + "AND APT_WDDT = :BPRAPPD.APT_WDDT";
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BR_ALL() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp = new DBParm();
        BPTAPPD_BR.rp.TableName = "BPTAPPD";
        BPTAPPD_BR.rp.where = "APT_BR = :BPRAPPD.APT_BR "
            + "AND APT_DT >= :BPRAPPD.APT_DT";
        BPTAPPD_BR.rp.order = "APT_DT";
        IBS.STARTBR(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRAPPD, this, BPTAPPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRAPPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRAPPD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTAPPD_BR);
    }
    public void T000_REWRITE_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        IBS.REWRITE(SCCGWA, BPRAPPD, BPTAPPD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRAPPD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRAPPD = ");
            CEP.TRC(SCCGWA, BPCRAPPD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
