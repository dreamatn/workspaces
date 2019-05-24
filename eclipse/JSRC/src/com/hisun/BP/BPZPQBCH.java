package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQBCH {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_MAINT_ORGS = "BP-R-ORGS-MAINTAIN  ";
    char K_STS_CLOSE = 'C';
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    String WS_ERR_MSG = " ";
    BPZPQBCH_WS_ORGM_KEY WS_ORGM_KEY = new BPZPQBCH_WS_ORGM_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    SCCGWA SCCGWA;
    BPCPQBCH BPCPQBCH;
    public void MP(SCCGWA SCCGWA, BPCPQBCH BPCPQBCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQBCH = BPCPQBCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQBCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBCH.BNK);
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        CEP.TRC(SCCGWA, BPCPQBCH.ACCT);
        IBS.init(SCCGWA, BPCPQBCH.RC);
        if (BPCPQBCH.BNK.trim().length() == 0) {
            BPCPQBCH.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
        if (BPCPQBCH.BCH.trim().length() == 0) {
            JIBS_tmp_str[0] = "" + BPCPQBCH.ACCT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCPQBCH.BCH = JIBS_tmp_str[0].substring(0, 3);
        }
        CEP.TRC(SCCGWA, BPCPQBCH.BNK);
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        CEP.TRC(SCCGWA, BPCPQBCH.ACCT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_ORG_INFO();
        if (pgmRtn) return;
        B300_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQBCH.BCH.equalsIgnoreCase("0") 
            && BPCPQBCH.ACCT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQBCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_ORG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPCPQBCH.BNK;
        WS_ORGM_KEY.WS_ORGM_BR = BPCPQBCH.ACCT;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        BPCRORGM.INFO.FUNC = 'R';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        if (BPCRORGM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRANCH_NOT_EXIST, BPCPQBCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_OUTPUT_INFO() throws IOException,SQLException,Exception {
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        BPCPQBCH.EFF_DT = BPRPORGM.EFF_DT;
        BPCPQBCH.EXP_DT = BPRPORGM.EXP_DT;
        BPCPQBCH.CHN_NM = BPRPORGM.CHN_NM;
        BPCPQBCH.CHN_ADDR = BPRPORGM.CHN_ADDR;
        BPCPQBCH.ENG_NM = BPRPORGM.ENG_NM;
        BPCPQBCH.ENG_ADDR = BPRPORGM.ENG_ADDR;
        BPCPQBCH.LINK_MAN = BPRPORGM.LINK_MAN;
        BPCPQBCH.LINK_TEL = BPRPORGM.LINK_TEL;
        BPCPQBCH.POST = BPRPORGM.POST;
        BPCPQBCH.TLR_MAX = BPRPORGM.TLR_MAX;
        BPCPQBCH.ATH_MAX = BPRPORGM.ATH_MAX;
        BPCPQBCH.OPN_DT = BPRPORGM.OPN_DT;
        BPCPQBCH.RUN_HDAY = BPRPORGM.RUN_HDAY;
        BPCPQBCH.CALD_CD = BPRPORGM.CALD_CD;
        BPCPQBCH.UPT_DT = BPRPORGM.UPT_DT;
        BPCPQBCH.UPT_TLR = BPRPORGM.UPT_TLR;
        BPCPQBCH.FAX = BPRPORGM.FAX;
        BPCPQBCH.TELEX = BPRPORGM.TELEX;
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
        if (BPCRORGM.RC.RC_CODE != 0 
            && BPCRORGM.RETURN_INFO != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQBCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQBCH = ");
            CEP.TRC(SCCGWA, BPCPQBCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
