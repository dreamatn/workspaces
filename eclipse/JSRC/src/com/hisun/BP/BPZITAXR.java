package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZITAXR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRTAXR BPCRTAXR = new BPCRTAXR();
    BPRTAXR BPRTAXR = new BPRTAXR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCITAXR BPCITAXR;
    public void MP(SCCGWA SCCGWA, BPCITAXR BPCITAXR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCITAXR = BPCITAXR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZITAXR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B002_QUERY_BPTTAXR_PROC();
        if (pgmRtn) return;
    }
    public void B002_QUERY_BPTTAXR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTAXR);
        IBS.init(SCCGWA, BPRTAXR);
        BPRTAXR.KEY.CCY = BPCITAXR.KEY.CCY;
        BPRTAXR.KEY.BR = BPCITAXR.KEY.BR;
        BPRTAXR.KEY.EFF_DT = BPCITAXR.KEY.EFF_DT;
        BPRTAXR.KEY.TAX_TYP = BPCITAXR.KEY.TAX_TYP;
        BPRTAXR.KEY.VAL_TYP = BPCITAXR.KEY.VAL_TYP;
        BPRTAXR.KEY.RESIDENT = BPCITAXR.KEY.RESIDENT;
        BPCRTAXR.FUNC = 'I';
        BPCRTAXR.REC_PTR = BPRTAXR;
        BPCRTAXR.REC_LEN = 181;
        S000_CALL_BPZRTAXR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRTAXR.RETURN_INFO == 'F') {
            BPCITAXR.RETURN_INFO = 'F';
            BPCITAXR.TAX_NAME = BPRTAXR.TAX_NAME;
            BPCITAXR.TAX_VAL = BPRTAXR.TAX_VAL;
            BPCITAXR.EXP_DT = BPRTAXR.EXP_DT;
        } else if (BPCRTAXR.RETURN_INFO == 'N') {
            BPCITAXR.RETURN_INFO = 'N';
        } else {
            BPCITAXR.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTAXR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAIN-TAXR", BPCRTAXR);
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
        if (BPCITAXR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCITAXR = ");
            CEP.TRC(SCCGWA, BPCITAXR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
