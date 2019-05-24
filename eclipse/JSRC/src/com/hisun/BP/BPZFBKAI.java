package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBKAI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_CHECK = "BP-PARM-READ        ";
    BPZFBKAI_WS_MSGID WS_MSGID = new BPZFBKAI_WS_MSGID();
    short WS_FLD_NO = 0;
    String WS_BANK = " ";
    short WS_BANK_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPBKAI BPRPBKAI = new BPRPBKAI();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCGWA SCCGWA;
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFBKAI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCRCKPM.VAL.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBKAI);
        IBS.CPY2CLS(SCCGWA, BPCRCKPM.VAL, BPRPBKAI.DATA_TXT);
        WS_BANK_LEN = 3;
        S000_CHECK_MAINFRAME_INPUT();
        CEP.TRC(SCCGWA, BPRPBKAI.DATA_TXT.BACK_DT);
        CEP.TRC(SCCGWA, BPRPBKAI.DATA_TXT.FORWARD_DT);
        CEP.TRC(SCCGWA, BPRPBKAI.DATA_TXT.CLOSE_DT);
        if (BPRPBKAI.DATA_TXT.CLOSE_DT > 31) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CHECK_MAINFRAME_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        if (BPCRCKPM.CODE == null) BPCRCKPM.CODE = "";
        JIBS_tmp_int = BPCRCKPM.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCRCKPM.CODE += " ";
        BPCPQBNK.DATA_INFO.BNK = BPCRCKPM.CODE.substring(0, WS_BANK_LEN);
        CEP.TRC(SCCGWA, BPCRCKPM.CODE);
        S000_CALL_BPZPQBNK();
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCKPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCKPM = ");
            CEP.TRC(SCCGWA, BPCRCKPM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
