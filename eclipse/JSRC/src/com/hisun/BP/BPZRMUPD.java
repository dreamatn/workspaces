package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMUPD {
    DBParm BPTEXUPF_RD;
    brParm BPTEXUPF_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTEXUPF = "BPTEXUPF";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRMUPD BPCRMUPD;
    BPREXUPF BPREXUPL;
    public void MP(SCCGWA SCCGWA, BPCRMUPD BPCRMUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMUPD = BPCRMUPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMUPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRMUPD.RC);
        BPREXUPL = (BPREXUPF) BPCRMUPD.INFO.POINTER;
        IBS.init(SCCGWA, BPREXUPF);
        IBS.CLONE(SCCGWA, BPREXUPL, BPREXUPF);
        CEP.TRC(SCCGWA, "BEGIN BPZRMUPD");
        CEP.TRC(SCCGWA, BPREXUPF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMUPD.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMUPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXUPF, BPREXUPL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTEXUPF();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXUPF_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTEXUPF();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXUPF();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEXUPF();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRMUPD.INFO.OPT == 'S') {
            if (BPCRMUPD.INFO.BRW_OPT == 'D') {
                T000_STARTBR_BPTEXUPF_BY_DT();
                if (pgmRtn) return;
            } else {
                if (BPCRMUPD.INFO.BRW_OPT == 'U') {
                    T000_STARTBR_BPTEXUPF_UPD();
                    if (pgmRtn) return;
                } else {
                    T000_STARTBR_BPTEXUPF();
                    if (pgmRtn) return;
                }
            }
        } else if (BPCRMUPD.INFO.OPT == 'N') {
            CEP.TRC(SCCGWA, "READNEXT");
            T000_READNEXT_BPTEXUPF();
            if (pgmRtn) return;
        } else if (BPCRMUPD.INFO.OPT == 'E') {
            T000_ENDBR_BPTEXUPF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMUPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_CREATE_BPTEXUPF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXUPF.KEY.BATCH_NO);
        CEP.TRC(SCCGWA, BPREXUPF.KEY.SEQ);
        CEP.TRC(SCCGWA, BPREXUPF.RECORD.trim().length());
        CEP.TRC(SCCGWA, BPREXUPF.RECORD);
        CEP.TRC(SCCGWA, BPREXUPF.CHK_FLAG);
        CEP.TRC(SCCGWA, BPREXUPF.CHK_MSG);
        BPTEXUPF_RD = new DBParm();
        BPTEXUPF_RD.TableName = "BPTEXUPF";
        IBS.WRITE(SCCGWA, BPREXUPF, BPTEXUPF_RD);
    }
    public void T000_READ_BPTEXUPF() throws IOException,SQLException,Exception {
        BPTEXUPF_RD = new DBParm();
        BPTEXUPF_RD.TableName = "BPTEXUPF";
        IBS.READ(SCCGWA, BPREXUPF, BPTEXUPF_RD);
    }
    public void T000_READ_BPTEXUPF_UPD() throws IOException,SQLException,Exception {
        BPTEXUPF_RD = new DBParm();
        BPTEXUPF_RD.TableName = "BPTEXUPF";
        BPTEXUPF_RD.upd = true;
        IBS.READ(SCCGWA, BPREXUPF, BPTEXUPF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMUPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMUPD.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRMUPD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXUPF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTEXUPF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXUPF.CHK_MSG);
        BPTEXUPF_RD = new DBParm();
        BPTEXUPF_RD.TableName = "BPTEXUPF";
        IBS.REWRITE(SCCGWA, BPREXUPF, BPTEXUPF_RD);
    }
    public void T000_DELETE_BPTEXUPF() throws IOException,SQLException,Exception {
        BPTEXUPF_RD = new DBParm();
        BPTEXUPF_RD.TableName = "BPTEXUPF";
        IBS.DELETE(SCCGWA, BPREXUPF, BPTEXUPF_RD);
    }
    public void T000_STARTBR_BPTEXUPF() throws IOException,SQLException,Exception {
        if (BPREXUPF.KEY.BATCH_NO.trim().length() == 0) {
            BPTEXUPF_BR.rp = new DBParm();
            BPTEXUPF_BR.rp.TableName = "BPTEXUPF";
            IBS.STARTBR(SCCGWA, BPREXUPF, BPTEXUPF_BR);
        } else {
            BPTEXUPF_BR.rp = new DBParm();
            BPTEXUPF_BR.rp.TableName = "BPTEXUPF";
            BPTEXUPF_BR.rp.where = "BATCH_NO = :BPREXUPF.KEY.BATCH_NO";
            IBS.STARTBR(SCCGWA, BPREXUPF, this, BPTEXUPF_BR);
        }
    }
    public void T000_STARTBR_BPTEXUPF_BY_DT() throws IOException,SQLException,Exception {
        BPTEXUPF_BR.rp = new DBParm();
        BPTEXUPF_BR.rp.TableName = "BPTEXUPF";
        BPTEXUPF_BR.rp.where = "APPV_DATE = :BPREXUPF.APPV_DATE "
            + "AND APPV_FLAG = :BPREXUPF.APPV_FLAG";
        BPTEXUPF_BR.rp.order = "BATCH_NO";
        IBS.STARTBR(SCCGWA, BPREXUPF, this, BPTEXUPF_BR);
    }
    public void T000_STARTBR_BPTEXUPF_UPD() throws IOException,SQLException,Exception {
        BPTEXUPF_BR.rp = new DBParm();
        BPTEXUPF_BR.rp.TableName = "BPTEXUPF";
        BPTEXUPF_BR.rp.where = "BATCH_NO = :BPREXUPF.KEY.BATCH_NO";
        BPTEXUPF_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPREXUPF, this, BPTEXUPF_BR);
    }
    public void T000_READNEXT_BPTEXUPF() throws IOException,SQLException,Exception {
        BPTEXUPF_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPREXUPF, this, BPTEXUPF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMUPD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMUPD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXUPF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTEXUPF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEXUPF_BR);
        CEP.TRC(SCCGWA, "END BR");
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMUPD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMUPD = ");
            CEP.TRC(SCCGWA, BPCRMUPD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
