package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSRATE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD136";
    DDZSRATE_WS_LIST WS_LIST = new DDZSRATE_WS_LIST();
    double WS_PRATE_PRAT_RATE = 0;
    double WS_PRATE_CON_RATE = 0;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUINTI BPCUINTI = new BPCUINTI();
    SCCGWA SCCGWA;
    DDCSRATE DDCSRATE;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSRATE DDCSRATE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSRATE = DDCSRATE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSRATE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        S000_GET_RATE_NO();
        if (pgmRtn) return;
    }
    public void S000_GET_RATE_NO() throws IOException,SQLException,Exception {
        S000_CALL_RATE_MODULE();
        if (pgmRtn) return;
        WS_LIST.WS_LIST_RATE = WS_PRATE_CON_RATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_LIST;
        SCCFMT.DATA_LEN = 26;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_RATE_MODULE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUINTI);
        BPCUINTI.CCY = DDCSRATE.CCY;
        BPCUINTI.BASE_TYP = DDCSRATE.RATE_TYP;
        BPCUINTI.TENOR = DDCSRATE.RAT_TERM;
        BPCUINTI.DT = SCCGWA.COMM_AREA.AC_DATE;
        S00_CALL_BPZUINTI();
        if (pgmRtn) return;
        WS_PRATE_PRAT_RATE = BPCUINTI.OWN_RATE;
        WS_LIST.WS_LIST_BASE_RATE = BPCUINTI.OWN_RATE;
        DDCSRATE.BASE_RATE = WS_LIST.WS_LIST_BASE_RATE;
        S000_CAL_CON_RATE();
        if (pgmRtn) return;
    }
    public void S000_CAL_CON_RATE() throws IOException,SQLException,Exception {
        if (DDCSRATE.FLOAT_TP == '2') {
            WS_PRATE_CON_RATE = WS_PRATE_PRAT_RATE * ( 1 + DDCSRATE.F_PCNT / 100 );
        } else {
            WS_PRATE_CON_RATE = WS_PRATE_PRAT_RATE + DDCSRATE.F_SPRD;
        }
        DDCSRATE.CON_RATE = WS_PRATE_CON_RATE;
    }
    public void S00_CALL_BPZUINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INTR-INQ", BPCUINTI);
        CEP.TRC(SCCGWA, BPCUINTI.RC.RC_CODE);
        if (BPCUINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUINTI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
