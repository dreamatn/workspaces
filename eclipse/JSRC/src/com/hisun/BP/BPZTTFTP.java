package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTTFTP {
    DBParm BPTTRATE_RD;
    brParm BPTTRATE_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTTRATE = "BPTTRATE";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTRATE BPRTRATE = new BPRTRATE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCTTFTP BPCTTFTP;
    BPRTRATE BPRTRATF;
    public void MP(SCCGWA SCCGWA, BPCTTFTP BPCTTFTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTTFTP = BPCTTFTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTTFTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTTFTP.RC);
        BPRTRATF = (BPRTRATE) BPCTTFTP.INFO.POINTER;
        IBS.init(SCCGWA, BPRTRATE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTRATF);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTRATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTTFTP.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.FUNC == 'R') {
            B020_READUPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.FUNC == 'I') {
            B050_INQUIRE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUN_ERR, BPCTTFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTRATE, BPRTRATF);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T010_ADD_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B020_READUPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T020_READUPDATE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T030_UPDATE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T040_DELETE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B050_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        T050_INQUIRE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTTFTP.INFO.OPT == 'S') {
            T000_STARTBR_BPTTRATE();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.OPT == 'N') {
            T000_READNEXT_BPTTRATE();
            if (pgmRtn) return;
        } else if (BPCTTFTP.INFO.OPT == 'E') {
            T000_ENDBR_BPTTRATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BROWSE_FUN_ERR, BPCTTFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTTRATE_RD = new DBParm();
        BPTTRATE_RD.TableName = "BPTTRATE";
        BPTTRATE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTRATE, BPTTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTTFTP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ADD_DB_DATA_ERR, BPCTTFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T020_READUPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTTRATE_RD = new DBParm();
        BPTTRATE_RD.TableName = "BPTTRATE";
        BPTTRATE_RD.upd = true;
        IBS.READ(SCCGWA, BPRTRATE, BPTTRATE_RD);
    }
    public void T030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTTRATE_RD = new DBParm();
        BPTTRATE_RD.TableName = "BPTTRATE";
        IBS.REWRITE(SCCGWA, BPRTRATE, BPTTRATE_RD);
    }
    public void T040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTTRATE_RD = new DBParm();
        BPTTRATE_RD.TableName = "BPTTRATE";
        BPTTRATE_RD.where = "CURRENCY_CODE = :BPRTRATE.KEY.CURRENCY_CODE "
            + "AND TENOR_CD = :BPRTRATE.KEY.TENOR_CD";
        IBS.DELETE(SCCGWA, BPRTRATE, this, BPTTRATE_RD);
    }
    public void T050_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTTRATE_RD = new DBParm();
        BPTTRATE_RD.TableName = "BPTTRATE";
        BPTTRATE_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTRATE, BPTTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTTFTP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTTFTP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR, BPCTTFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTTRATE() throws IOException,SQLException,Exception {
        if (BPCTTFTP.INFO.INDEX_FLG == '1') {
            BPTTRATE_BR.rp = new DBParm();
            BPTTRATE_BR.rp.TableName = "BPTTRATE";
            IBS.STARTBR(SCCGWA, BPRTRATE, BPTTRATE_BR);
        }
        if (BPCTTFTP.INFO.INDEX_FLG == '2' 
            && BPRTRATE.KEY.CURRENCY_CODE.trim().length() > 0 
            && BPRTRATE.KEY.TENOR_CD.trim().length() > 0) {
            BPTTRATE_BR.rp = new DBParm();
            BPTTRATE_BR.rp.TableName = "BPTTRATE";
            BPTTRATE_BR.rp.where = "CURRENCY_CODE = :BPRTRATE.KEY.CURRENCY_CODE "
                + "AND TENOR_CD = :BPRTRATE.KEY.TENOR_CD";
            IBS.STARTBR(SCCGWA, BPRTRATE, this, BPTTRATE_BR);
        }
        if (BPCTTFTP.INFO.INDEX_FLG == '2' 
            && BPRTRATE.KEY.CURRENCY_CODE.trim().length() > 0 
            && BPRTRATE.KEY.TENOR_CD.trim().length() == 0) {
            BPTTRATE_BR.rp = new DBParm();
            BPTTRATE_BR.rp.TableName = "BPTTRATE";
            BPTTRATE_BR.rp.where = "CURRENCY_CODE = :BPRTRATE.KEY.CURRENCY_CODE";
            IBS.STARTBR(SCCGWA, BPRTRATE, this, BPTTRATE_BR);
        }
        if (BPCTTFTP.INFO.INDEX_FLG == '2' 
            && BPRTRATE.KEY.CURRENCY_CODE.trim().length() == 0 
            && BPRTRATE.KEY.TENOR_CD.trim().length() > 0) {
            BPTTRATE_BR.rp = new DBParm();
            BPTTRATE_BR.rp.TableName = "BPTTRATE";
            BPTTRATE_BR.rp.where = "TENOR_CD = :BPRTRATE.KEY.TENOR_CD";
            IBS.STARTBR(SCCGWA, BPRTRATE, this, BPTTRATE_BR);
        }
    }
    public void T000_READNEXT_BPTTRATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTTRATE_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTRATE, this, BPTTRATE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTTFTP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTTFTP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BROWSE_DATA_ERR, BPCTTFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTRATE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTRATE_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTTFTP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTTFTP = ");
            CEP.TRC(SCCGWA, BPCTTFTP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
