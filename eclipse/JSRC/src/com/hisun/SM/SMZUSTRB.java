package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMZUSTRB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 5000;
    String CPN_DSTR_BROWSE = "SM-R_DSTR_BROWSE    ";
    String WS_ERR_MSG = " ";
    short WS_TOT_NUM = 0;
    short WS_LEN = 0;
    String WS_TS_REC = " ";
    short WS_TS_CNT = 0;
    char WS_EOF = ' ';
    SMZUSTRB_WS_DSTR_DATA WS_DSTR_DATA = new SMZUSTRB_WS_DSTR_DATA();
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCTSTRB SMCTSTRB = new SMCTSTRB();
    BPRDSTR BPRDSTR = new BPRDSTR();
    SCCGWA SCCGWA;
    SMCOSTRM SMCOSTRM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCOSTRM SMCOSTRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCOSTRM = SMCOSTRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUSTRB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCTSTRB);
        SMCTSTRB.INFO.POINTER = BPRDSTR;
        SMCTSTRB.INFO.LEN = 138;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_READY_TS_HEAD();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_READY_TS_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 170;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDSTR);
        BPRDSTR.KEY.NAME = SMCOSTRM.NAME;
        if (BPRDSTR.KEY.NAME.trim().length() == 0) {
            SMCTSTRB.INFO.FUNC = 'A';
        } else {
            SMCTSTRB.INFO.FUNC = 'S';
        }
        S000_CALL_SMZRSTRB();
        if (pgmRtn) return;
        SMCTSTRB.INFO.FUNC = 'R';
        S000_CALL_SMZRSTRB();
        if (pgmRtn) return;
        while (SMCTSTRB.INFO.FUNC != 'E') {
            R000_OUTPUT_REC();
            if (pgmRtn) return;
            S000_CALL_SMZRSTRB();
            if (pgmRtn) return;
        }
        S000_CALL_SMZRSTRB();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_REC() throws IOException,SQLException,Exception {
        WS_DSTR_DATA.WS_NAME = BPRDSTR.KEY.NAME;
        WS_DSTR_DATA.WS_ALIAS = BPRDSTR.ALIAS;
        WS_DSTR_DATA.WS_EN_NAME = BPRDSTR.EN_NAME;
        WS_DSTR_DATA.WS_CH_NAME = BPRDSTR.CH_NAME;
        WS_DSTR_DATA.WS_TYPE = BPRDSTR.TYPE;
        WS_TS_REC = " ";
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_DSTR_DATA);
        WS_LEN = 84;
        WS_TOT_NUM += 1;
        if (WS_TOT_NUM <= 1000) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TS_REC = " ";
            WS_TS_REC = "TO BE CONTINUED";
            WS_LEN = 84;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_EOF = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            WS_ERR_MSG = SMCMSG_ERROR_MSG.SM_REC_NUM_EXCEED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT += 1;
    }
    public void S000_CALL_SMZRSTRB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DSTR_BROWSE, SMCTSTRB);
        if (SMCTSTRB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCTSTRB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCOSTRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
