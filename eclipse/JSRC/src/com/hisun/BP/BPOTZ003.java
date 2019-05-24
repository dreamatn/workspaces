package com.hisun.BP;

import com.hisun.SM.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOTZ003 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT = "TZ003";
    BPOTZ003_WS_VARIABLES WS_VARIABLES = new BPOTZ003_WS_VARIABLES();
    BPOTZ003_WS_OUT_AREA WS_OUT_AREA = new BPOTZ003_WS_OUT_AREA();
    BPOTZ003_WS_STAR_COMM WS_STAR_COMM = new BPOTZ003_WS_STAR_COMM();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGWA_WS_COMM_AREA WS_COMM_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOTZ003 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_COMM_AREA = SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COMM_AREA.PAY_AMT);
        CEP.TRC(SCCGWA, WS_COMM_AREA.RECV_AMT);
        WS_OUT_AREA.O_EX_RATE = 0;
        WS_OUT_AREA.O_EX_RATE2 = 0;
        if (WS_COMM_AREA.RECV_AMT != 0) {
            WS_OUT_AREA.O_EX_RATE = WS_COMM_AREA.PAY_AMT / WS_COMM_AREA.RECV_AMT;
            WS_OUT_AREA.O_EX_RATE2 = WS_COMM_AREA.RECV_AMT / WS_COMM_AREA.PAY_AMT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_AREA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
