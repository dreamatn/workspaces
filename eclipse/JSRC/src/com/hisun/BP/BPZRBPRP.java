package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBPRP {
    brParm BPTPARP_BR = new brParm();
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPARP BPRPARP = new BPRPARP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCRBPRP BPCRBPRP;
    BPRPARP BPRPARP1;
    public void MP(SCCGWA SCCGWA, BPCRBPRP BPCRBPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBPRP = BPCRBPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRBPRP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARP);
        WS_LEN = 272;
        BPRPARP1 = (BPRPARP) BPCRBPRP.PTR;
        if (WS_LEN != BPCRBPRP.LEN) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPRPARP (" + BPCRBPRP.LEN + ") (" + WS_LEN + ") LENGTH NOT EQUAL";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARP1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBPRP.FUNC == 'S') {
            B010_START_BROWSE_PROC();
        } else if (BPCRBPRP.FUNC == 'R') {
            B020_READ_NEXT_PROC();
        } else if (BPCRBPRP.FUNC == 'E') {
            B030_END_BROWSE_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC (" + BPCRBPRP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRPARP, BPRPARP1);
    }
    public void B010_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T010_START_BROWSE_PROC();
    }
    public void B020_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T020_READ_NEXT_PROC();
    }
    public void B030_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T030_END_BROWSE_PROC();
    }
    public void T010_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPARP.KEY.TYPE);
        BPTPARP_BR.rp = new DBParm();
        BPTPARP_BR.rp.TableName = "BPTPARP";
        BPTPARP_BR.rp.where = "TYPE >= :BPRPARP.KEY.TYPE";
        BPTPARP_BR.rp.order = "TYPE";
        IBS.STARTBR(SCCGWA, BPRPARP, this, BPTPARP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPRP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRBPRP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPRP.RETURN_INFO = 'N';
        }
    }
    public void T020_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPARP, this, BPTPARP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPRP.RETURN_INFO = 'N';
        } else {
            BPCRBPRP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRBPRP.RC);
        }
    }
    public void T030_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPARP_BR);
        BPCRBPRP.RETURN_INFO = 'F';
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRBPRP.RC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBPRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBPRP = ");
            CEP.TRC(SCCGWA, BPCRBPRP);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
