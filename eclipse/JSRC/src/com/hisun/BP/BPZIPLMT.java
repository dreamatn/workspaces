package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIPLMT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRPLMT BPCRPLMT = new BPCRPLMT();
    BPRPLMT BPRPLMT = new BPRPLMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCIPLMT BPCIPLMT;
    public void MP(SCCGWA SCCGWA, BPCIPLMT BPCIPLMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIPLMT = BPCIPLMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIPLMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_PROC();
        if (pgmRtn) return;
        B002_QUERY_BPTPLMT_PROC();
        if (pgmRtn) return;
    }
    public void B001_CHECK_PROC() throws IOException,SQLException,Exception {
        if (BPCIPLMT.KEY.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_QUERY_BPTPLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRPLMT);
        IBS.init(SCCGWA, BPRPLMT);
        BPRPLMT.KEY.CCY = BPCIPLMT.KEY.CCY;
        BPRPLMT.KEY.BR = BPCIPLMT.KEY.BR;
        BPCRPLMT.FUNC = 'I';
        BPCRPLMT.REC_PTR = BPRPLMT;
        BPCRPLMT.REC_LEN = 116;
        S000_CALL_BPZRPLMT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRPLMT.RETURN_INFO == 'F') {
            BPCIPLMT.RETURN_INFO = 'F';
        } else if (BPCRPLMT.RETURN_INFO == 'N') {
            BPCIPLMT.RETURN_INFO = 'N';
        } else {
            BPCIPLMT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRPLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAIN-PLMT", BPCRPLMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIPLMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIPLMT = ");
            CEP.TRC(SCCGWA, BPCIPLMT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
