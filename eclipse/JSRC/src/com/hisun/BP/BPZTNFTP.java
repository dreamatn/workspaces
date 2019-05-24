package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTNFTP {
    DBParm BPTNTRAT_RD;
    brParm BPTNTRAT_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTNTRAT = "BPTNTRAT";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRNTRAT BPRNTRAT = new BPRNTRAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCTNFTP BPCTNFTP;
    BPRNTRAT BPRNTRAA;
    public void MP(SCCGWA SCCGWA, BPCTNFTP BPCTNFTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTNFTP = BPCTNFTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTNFTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTNFTP.RC);
        BPRNTRAA = (BPRNTRAT) BPCTNFTP.INFO.POINTER;
        IBS.init(SCCGWA, BPRNTRAT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRNTRAA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRNTRAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTNFTP.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.FUNC == 'R') {
            B020_READUPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.FUNC == 'I') {
            B050_INQUIRE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUN_ERR, BPCTNFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRNTRAT, BPRNTRAA);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTNTRAT_RD = new DBParm();
        BPTNTRAT_RD.TableName = "BPTNTRAT";
        BPTNTRAT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRNTRAT, BPTNTRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTNFTP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ADD_DB_DATA_ERR, BPCTNFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTNTRAT_RD = new DBParm();
        BPTNTRAT_RD.TableName = "BPTNTRAT";
        BPTNTRAT_RD.upd = true;
        IBS.READ(SCCGWA, BPRNTRAT, BPTNTRAT_RD);
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTNTRAT_RD = new DBParm();
        BPTNTRAT_RD.TableName = "BPTNTRAT";
        IBS.REWRITE(SCCGWA, BPRNTRAT, BPTNTRAT_RD);
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTNTRAT_RD = new DBParm();
        BPTNTRAT_RD.TableName = "BPTNTRAT";
        BPTNTRAT_RD.where = "PROD_TYP = :BPRNTRAT.KEY.PROD_TYP "
            + "AND ACC_CENTER = :BPRNTRAT.KEY.ACC_CENTER "
            + "AND CURRENCY_CODE = :BPRNTRAT.KEY.CURRENCY_CODE "
            + "AND B_O_CODE = :BPRNTRAT.KEY.B_O_CODE "
            + "AND FTP_PRD_TYPE = :BPRNTRAT.KEY.FTP_PRD_TYPE";
        IBS.DELETE(SCCGWA, BPRNTRAT, this, BPTNTRAT_RD);
    }
    public void B050_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTNTRAT_RD = new DBParm();
        BPTNTRAT_RD.TableName = "BPTNTRAT";
        BPTNTRAT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRNTRAT, BPTNTRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTNFTP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTNFTP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR, BPCTNFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTNFTP.INFO.OPT == 'S') {
            T000_STARTBR_BPTNTRAT();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.OPT == 'N') {
            T000_READNEXT_BPTNTRAT();
            if (pgmRtn) return;
        } else if (BPCTNFTP.INFO.OPT == 'E') {
            T000_ENDBR_BPTNTRAT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BROWSE_FUN_ERR, BPCTNFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTNTRAT() throws IOException,SQLException,Exception {
        BPTNTRAT_BR.rp = new DBParm();
        BPTNTRAT_BR.rp.TableName = "BPTNTRAT";
        BPTNTRAT_BR.rp.where = "( PROD_TYP = :BPRNTRAT.KEY.PROD_TYP "
            + "OR :BPRNTRAT.KEY.PROD_TYP = ' ' ) "
            + "AND ( ACC_CENTER = :BPRNTRAT.KEY.ACC_CENTER "
            + "OR :BPRNTRAT.KEY.ACC_CENTER = 0 ) "
            + "AND ( CURRENCY_CODE = :BPRNTRAT.KEY.CURRENCY_CODE "
            + "OR :BPRNTRAT.KEY.CURRENCY_CODE = ' ' ) "
            + "AND ( B_O_CODE = :BPRNTRAT.KEY.B_O_CODE "
            + "OR :BPRNTRAT.KEY.B_O_CODE = ' ' ) "
            + "AND ( FTP_PRD_TYPE = :BPRNTRAT.KEY.FTP_PRD_TYPE "
            + "OR :BPRNTRAT.KEY.FTP_PRD_TYPE = ' ' )";
        IBS.STARTBR(SCCGWA, BPRNTRAT, this, BPTNTRAT_BR);
    }
    public void T000_READNEXT_BPTNTRAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTNTRAT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRNTRAT, this, BPTNTRAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTNFTP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTNFTP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BROWSE_DATA_ERR, BPCTNFTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTNTRAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTNTRAT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTNFTP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTNFTP = ");
            CEP.TRC(SCCGWA, BPCTNFTP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
