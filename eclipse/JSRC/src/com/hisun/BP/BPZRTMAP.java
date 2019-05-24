package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTMAP {
    DBParm BPTTMAP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTMAP";
    String K_TBL_BANK = "BPTBANK ";
    String K_TBL_TLT = "BPTTLT  ";
    String K_TBL_TMAP = "BPTTMAP ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTSTS BPRTSTS = new BPRTSTS();
    BPRTMAP BPRTMAP = new BPRTMAP();
    SCCGWA SCCGWA;
    BPCRTMAP BPCRTMAP;
    BPRTLT BPRTLT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTMAP BPRTMSP;
    public void MP(SCCGWA SCCGWA, BPCRTMAP BPCRTMAP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTMAP = BPCRTMAP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTMAP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTMSP = (BPRTMUP) BPCRTMAP.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTMAP);
        IBS.CLONE(SCCGWA, BPRTMSP, BPRTMAP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMAP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAP.INFO.FUNC == 'M') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAP.INFO.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMAP.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTMAP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTMAP, BPRTMSP);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTMAP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTMAP_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTMAP();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTMAP();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTTMAP();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_RD = new DBParm();
        BPTTMAP_RD.TableName = "BPTTMAP";
        IBS.DELETE(SCCGWA, BPRTMAP, BPTTMAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTMAP.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_RD = new DBParm();
        BPTTMAP_RD.TableName = "BPTTMAP";
        BPTTMAP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTMAP, BPTTMAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTMAP.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCRTMAP.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTMAP_UPD() throws IOException,SQLException,Exception {
        BPTTMAP_RD = new DBParm();
        BPTTMAP_RD.TableName = "BPTTMAP";
        BPTTMAP_RD.upd = true;
        BPTTMAP_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTMAP, BPTTMAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTMAP.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_RD = new DBParm();
        BPTTMAP_RD.TableName = "BPTTMAP";
        BPTTMAP_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BPRTMAP, BPTTMAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTMAP.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_QUERY_BPTTMAP() throws IOException,SQLException,Exception {
        BPTTMAP_RD = new DBParm();
        BPTTMAP_RD.TableName = "BPTTMAP";
        BPTTMAP_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTMAP, BPTTMAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMAP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMAP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCRTMAP.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTMAP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTMAP = ");
            CEP.TRC(SCCGWA, BPCRTMAP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
