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

public class SMZUCHKB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 5000;
    String CPN_DCHK_BROWSE = "SM-R_DCHK_BROWSE    ";
    String WS_ERR_MSG = " ";
    short WS_TOT_NUM = 0;
    short WS_LEN = 0;
    String WS_TS_REC = " ";
    short WS_TS_CNT = 0;
    char WS_EOF = ' ';
    SMZUCHKB_WS_DCHK_DATA WS_DCHK_DATA = new SMZUCHKB_WS_DCHK_DATA();
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCTCHKB SMCTCHKB = new SMCTCHKB();
    BPRDCHK BPRDCHK = new BPRDCHK();
    SCCGWA SCCGWA;
    SMCOCHKM SMCOCHKM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCOCHKM SMCOCHKM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCOCHKM = SMCOCHKM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUCHKB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCTCHKB);
        SMCTCHKB.INFO.POINTER = BPRDCHK;
        SMCTCHKB.INFO.LEN = 122;
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
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDCHK);
        BPRDCHK.KEY.NAME = SMCOCHKM.NAME;
        BPRDCHK.KEY.FMT = SMCOCHKM.FMTNO;
        if (BPRDCHK.KEY.NAME.trim().length() == 0 
            && BPRDCHK.KEY.FMT.trim().length() == 0) {
            SMCTCHKB.INFO.FUNC = 'A';
        } else {
            if (BPRDCHK.KEY.NAME.trim().length() == 0) {
                BPRDCHK.KEY.NAME = "%%%%%%%%";
            }
            if (BPRDCHK.KEY.FMT.trim().length() == 0) {
                BPRDCHK.KEY.FMT = "%%%";
            }
            SMCTCHKB.INFO.FUNC = 'S';
        }
        S000_CALL_SMZRCHKB();
        if (pgmRtn) return;
        SMCTCHKB.INFO.FUNC = 'R';
        S000_CALL_SMZRCHKB();
        if (pgmRtn) return;
        while (SMCTCHKB.INFO.FUNC != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_OUTPUT_REC();
            if (pgmRtn) return;
            S000_CALL_SMZRCHKB();
            if (pgmRtn) return;
        }
        S000_CALL_SMZRCHKB();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_REC() throws IOException,SQLException,Exception {
        WS_DCHK_DATA.WS_NAME = BPRDCHK.KEY.NAME;
        WS_DCHK_DATA.WS_FMTNO = BPRDCHK.KEY.FMT;
        WS_DCHK_DATA.WS_OPT = BPRDCHK.OPT;
        WS_DCHK_DATA.WS_REMARK = BPRDCHK.RMK;
        WS_TS_REC = " ";
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_DCHK_DATA);
        WS_LEN = 43;
        WS_TOT_NUM += 1;
        if (WS_TOT_NUM <= 1000) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TS_REC = " ";
            WS_TS_REC = "TO BE CONTINUED";
            WS_LEN = 43;
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
    public void S000_CALL_SMZRCHKB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCHK_BROWSE, SMCTCHKB);
        if (SMCTCHKB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCTCHKB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCOCHKM.RC);
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
