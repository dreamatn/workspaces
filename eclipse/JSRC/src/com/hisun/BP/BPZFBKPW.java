package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBKPW {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_CHECK = "BP-PARM-READ        ";
    BPZFBKPW_WS_MSGID WS_MSGID = new BPZFBKPW_WS_MSGID();
    short WS_FLD_NO = 0;
    String WS_PASSWORD = " ";
    short WS_PWD_LEN = 0;
    String WS_BANK = " ";
    short WS_BANK_LEN = 0;
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPBKPW BPRPBKPW = new BPRPBKPW();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCGWA SCCGWA;
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFBKPW return!");
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
        IBS.init(SCCGWA, BPRPBKPW);
        IBS.CPY2CLS(SCCGWA, BPCRCKPM.VAL, BPRPBKPW.DATA_TXT);
        WS_PWD_LEN = 20;
        WS_BANK_LEN = 3;
        S000_CHECK_MAINFRAME_INPUT();
    }
    public void S000_CHECK_MAINFRAME_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        if (BPCRCKPM.CODE == null) BPCRCKPM.CODE = "";
        JIBS_tmp_int = BPCRCKPM.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCRCKPM.CODE += " ";
        BPCPQBNK.DATA_INFO.BNK = BPCRCKPM.CODE.substring(0, WS_BANK_LEN);
        CEP.TRC(SCCGWA, BPCRCKPM.CODE);
        S000_CALL_BPZPQBNK();
        if (BPRPBKPW.DATA_TXT.LEN_MAX > WS_PWD_LEN) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MAX_BIGGER_LENG, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.CAP_MAX > BPRPBKPW.DATA_TXT.LEN_MAX 
            || BPRPBKPW.DATA_TXT.LOW_MAX > BPRPBKPW.DATA_TXT.LEN_MAX 
            || BPRPBKPW.DATA_TXT.NUM_MAX > BPRPBKPW.DATA_TXT.LEN_MAX 
            || BPRPBKPW.DATA_TXT.CON_MAX_NUM > BPRPBKPW.DATA_TXT.LEN_MAX 
            || BPRPBKPW.DATA_TXT.USER_CON_MAX > BPRPBKPW.DATA_TXT.LEN_MAX 
            || BPRPBKPW.DATA_TXT.SPE_C_MAX > BPRPBKPW.DATA_TXT.LEN_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MAX_BIGGER_LENG, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.LEN_MAX < ( BPRPBKPW.DATA_TXT.CAP_MIN + BPRPBKPW.DATA_TXT.LOW_MIN + BPRPBKPW.DATA_TXT.NUM_MIN + BPRPBKPW.DATA_TXT.SPE_C_MIN )) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.TLR_RDAY > BPRPBKPW.DATA_TXT.TLR_PDAY) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_RDAY_ERROR, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.LEN_MIN > BPRPBKPW.DATA_TXT.LEN_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.CAP_MIN > BPRPBKPW.DATA_TXT.CAP_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.LOW_MIN > BPRPBKPW.DATA_TXT.LOW_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.NUM_MIN > BPRPBKPW.DATA_TXT.NUM_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.SPE_C_MIN > BPRPBKPW.DATA_TXT.SPE_C_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_MIN_BIGGER_MAX, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.CON_MAX_NUM <= 1) {
            if (BPRPBKPW.DATA_TXT.CON_MAX_NUM != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CONTINUE_NUM_ERR, WS_MSGID);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRPBKPW.DATA_TXT.USER_CON_MAX <= 1) {
            if (BPRPBKPW.DATA_TXT.USER_CON_MAX != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_USER_CON_NUM_ERR, WS_MSGID);
                S000_ERR_MSG_PROC();
            }
        }
        WS_I = 0;
        if (BPRPBKPW.DATA_TXT.CAP_MIN > 0) {
            WS_I += 1;
        }
        if (BPRPBKPW.DATA_TXT.LOW_MIN > 0) {
            WS_I += 1;
        }
        if (BPRPBKPW.DATA_TXT.NUM_MIN > 0) {
            WS_I += 1;
        }
        if (BPRPBKPW.DATA_TXT.SPE_C_MIN > 0) {
            WS_I += 1;
        }
        if (BPRPBKPW.DATA_TXT.ELEMENT_NUM > 4 
            || BPRPBKPW.DATA_TXT.ELEMENT_NUM < WS_I) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ELEMENT_NUM_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPRPBKPW.DATA_TXT.RE_FLG_TIMES > 10) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PSWD_RE_FLG_TIME_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
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
