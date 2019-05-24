package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMPRP {
    DBParm BPTPARP_RD;
    String JIBS_tmp_str[] = new String[10];
    brParm BPTPARP_BR = new brParm();
    int WS_LEN = 0;
    BPRPARP BPRPARP = new BPRPARP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCRMPRP BPCRMPRP;
    BPRPARP BPRPARPL;
    public void MP(SCCGWA SCCGWA, BPCRMPRP BPCRMPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMPRP = BPCRMPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRMPRP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPARPL = (BPRPARP) BPCRMPRP.PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMPRP.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRMPRP.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
        } else if (BPCRMPRP.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
        } else if (BPCRMPRP.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
        } else if (BPCRMPRP.FUNC == 'Q') {
            B050_INQURE_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC (" + BPCRMPRP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPARP();
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTPARP();
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPARP();
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPARP();
    }
    public void B050_INQURE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPARP();
    }
    public void B060_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_START_BROWSE_PROC();
    }
    public void B070_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READ_NEXT_PROC();
    }
    public void B080_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_END_BROWSE_PROC();
    }
    public void T000_WRITE_BPTPARP() throws IOException,SQLException,Exception {
        WS_LEN = 272;
        IBS.CLONE(SCCGWA, BPRPARPL, BPRPARP);
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.WRITE(SCCGWA, BPRPARP, BPTPARP_RD);
        BPCRMPRP.RETURN_INFO = 'F';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 7;
        }
    }
    public void T000_READUPD_BPTPARP() throws IOException,SQLException,Exception {
        WS_LEN = 5;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARPL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARP.KEY);
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        BPTPARP_RD.upd = true;
        IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRP.RETURN_INFO = 'N';
        } else {
            BPCRMPRP.RETURN_INFO = 'F';
            WS_LEN = 272;
            IBS.CLONE(SCCGWA, BPRPARP, BPRPARPL);
        }
    }
    public void T000_REWRITE_BPTPARP() throws IOException,SQLException,Exception {
        WS_LEN = 272;
        IBS.CLONE(SCCGWA, BPRPARPL, BPRPARP);
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.REWRITE(SCCGWA, BPRPARP, BPTPARP_RD);
        BPCRMPRP.RETURN_INFO = 'F';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 7;
        }
    }
    public void T000_DELETE_BPTPARP() throws IOException,SQLException,Exception {
        WS_LEN = 5;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARPL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARP.KEY);
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.DELETE(SCCGWA, BPRPARP, BPTPARP_RD);
        BPCRMPRP.RETURN_INFO = 'F';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 7;
        }
    }
    public void T000_READ_BPTPARP() throws IOException,SQLException,Exception {
        WS_LEN = 5;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARPL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARP.KEY);
        CEP.TRC(SCCGWA, BPRPARP.KEY);
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRP.RETURN_INFO = 'N';
        } else {
            BPCRMPRP.RETURN_INFO = 'F';
            WS_LEN = 272;
            IBS.CLONE(SCCGWA, BPRPARP, BPRPARPL);
        }
    }
    public void T000_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        BPTPARP_BR.rp = new DBParm();
        BPTPARP_BR.rp.TableName = "BPTPARP";
        BPTPARP_BR.rp.where = "TYPE >= :BPRPARP.KEY.TYPE";
        BPTPARP_BR.rp.order = "TYPE";
        IBS.STARTBR(SCCGWA, BPRPARP, this, BPTPARP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPRP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRMPRP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRP.RETURN_INFO = 'N';
        }
    }
    public void T000_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPARP, this, BPTPARP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRP.RETURN_INFO = 'N';
        } else {
            BPCRMPRP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRMPRP.RC);
            WS_LEN = 272;
            IBS.CLONE(SCCGWA, BPRPARP, BPRPARPL);
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPARP_BR);
        BPCRMPRP.RETURN_INFO = 'F';
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRMPRP.RC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMPRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMPRP = ");
            CEP.TRC(SCCGWA, BPCRMPRP);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
