package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRXMSG {
    DBParm BPTEXMSG_RD;
    brParm BPTEXMSG_BR = new brParm();
    boolean pgmRtn = false;
    String WS_BATNO = " ";
    String TBL_BPTEXMSG = "BPTEXMSG";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPREXMSG BPREXMSG = new BPREXMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCREMSG BPCREMSG;
    BPREXMSG BPRRXMSG;
    public void MP(SCCGWA SCCGWA, BPCREMSG BPCREMSG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCREMSG = BPCREMSG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRXMSG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCREMSG.RC);
        BPRRXMSG = (BPREXMSG) BPCREMSG.REC_PT;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CLONE(SCCGWA, BPRRXMSG, BPREXMSG);
        CEP.TRC(SCCGWA, BPREXMSG);
        CEP.TRC(SCCGWA, BPCREMSG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCREMSG.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREMSG.FUNC == 'D') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            B010_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREMSG.FUNC == 'Q') {
            B040_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCREMSG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, "END");
        IBS.CLONE(SCCGWA, BPREXMSG, BPRRXMSG);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T00_WRITE_BPTEXMSG();
        if (pgmRtn) return;
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T00_READ_BPTEXMSG();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T00_READ_BPTEXMSG_UPD();
        if (pgmRtn) return;
    }
    public void B010_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DELETE-RECORD-FLG");
        T00_DELETE_BPTEXMSG();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCREMSG.OPT == 'S') {
            T00_STARTBR_BPTEXMSG();
            if (pgmRtn) return;
        } else if (BPCREMSG.OPT == 'A') {
            T00_STARTBR_BPTEXMSG_2();
            if (pgmRtn) return;
        } else if (BPCREMSG.OPT == 'B') {
            T00_STARTBR_BPTEXMSG_3();
            if (pgmRtn) return;
        } else if (BPCREMSG.OPT == 'R') {
            T00_READNEXT_BPTEXMSG();
            if (pgmRtn) return;
        } else if (BPCREMSG.OPT == 'E') {
            T00_ENDBR_BPTEXMSG();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCREMSG.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T00_READ_BPTEXMSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ BPTEXMSG");
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.SEQ);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.NO);
        BPTEXMSG_RD = new DBParm();
        BPTEXMSG_RD.TableName = "BPTEXMSG";
        BPTEXMSG_RD.errhdl = true;
        IBS.READ(SCCGWA, BPREXMSG, BPTEXMSG_RD);
        CEP.TRC(SCCGWA, BPCREMSG.RETURN_INFO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREMSG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREMSG.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXMSG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_BPTEXMSG() throws IOException,SQLException,Exception {
        BPTEXMSG_RD = new DBParm();
        BPTEXMSG_RD.TableName = "BPTEXMSG";
        BPTEXMSG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPREXMSG, BPTEXMSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREMSG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCREMSG.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXMSG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_BPTEXMSG_UPD() throws IOException,SQLException,Exception {
        BPTEXMSG_RD = new DBParm();
        BPTEXMSG_RD.TableName = "BPTEXMSG";
        BPTEXMSG_RD.upd = true;
        IBS.READ(SCCGWA, BPREXMSG, BPTEXMSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREMSG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREMSG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCREMSG.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXMSG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_DELETE_BPTEXMSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXMSG.KEY);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.SEQ);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.NO);
        BPTEXMSG_RD = new DBParm();
        BPTEXMSG_RD.TableName = "BPTEXMSG";
        IBS.DELETE(SCCGWA, BPREXMSG, BPTEXMSG_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_STARTBR_BPTEXMSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        BPTEXMSG_BR.rp = new DBParm();
        BPTEXMSG_BR.rp.TableName = "BPTEXMSG";
        BPTEXMSG_BR.rp.where = "BATNO = :BPREXMSG.KEY.BATNO "
            + "AND SEQ = :BPREXMSG.KEY.SEQ";
        BPTEXMSG_BR.rp.order = "NO";
        IBS.STARTBR(SCCGWA, BPREXMSG, this, BPTEXMSG_BR);
    }
    public void T00_STARTBR_BPTEXMSG_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        BPTEXMSG_BR.rp = new DBParm();
        BPTEXMSG_BR.rp.TableName = "BPTEXMSG";
        BPTEXMSG_BR.rp.where = "BATNO = :BPREXMSG.KEY.BATNO";
        BPTEXMSG_BR.rp.order = "SEQ,NO";
        IBS.STARTBR(SCCGWA, BPREXMSG, this, BPTEXMSG_BR);
        CEP.TRC(SCCGWA, BPCREMSG.RETURN_INFO);
    }
    public void T00_STARTBR_BPTEXMSG_3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        BPTEXMSG_BR.rp = new DBParm();
        BPTEXMSG_BR.rp.TableName = "BPTEXMSG";
        BPTEXMSG_BR.rp.where = "BATNO = :BPREXMSG.KEY.BATNO";
        BPTEXMSG_BR.rp.order = "SEQ,NO";
        IBS.STARTBR(SCCGWA, BPREXMSG, this, BPTEXMSG_BR);
        CEP.TRC(SCCGWA, BPCREMSG.RETURN_INFO);
    }
    public void T00_READNEXT_BPTEXMSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTEXMSG_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPREXMSG, this, BPTEXMSG_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.SEQ);
        CEP.TRC(SCCGWA, BPREXMSG.KEY.NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREMSG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREMSG.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXMSG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_BPTEXMSG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEXMSG_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCREMSG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCREMSG = ");
            CEP.TRC(SCCGWA, BPCREMSG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
