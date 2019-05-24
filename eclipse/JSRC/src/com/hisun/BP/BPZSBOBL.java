package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBOBL {
    String JIBS_tmp_str[] = new String[10];
    BPZSBOBL_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBOBL_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUBOBL BPCUBOBL = new BPCUBOBL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSBOBL BPCSBOBL;
    public void MP(SCCGWA SCCGWA, BPCSBOBL BPCSBOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBOBL = BPCSBOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBOBL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B007_BRW_PTR_PROC();
    }
    public void B007_BRW_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBOBL);
        BPCUBOBL.SEQ_TYPE = BPCSBOBL.SEQ_TYPE;
        BPCUBOBL.SEQ_CODE = BPCSBOBL.SEQ_CODE;
        S000_CALL_BPZUBOBL();
    }
    public void S000_CALL_BPZUBOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BRW-OBL", BPCUBOBL);
        CEP.TRC(SCCGWA, BPCUBOBL.RC.RC_CODE);
        if (BPCUBOBL.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCUBOBL.RC.RC_MMO;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCUBOBL.RC.RC_CODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
