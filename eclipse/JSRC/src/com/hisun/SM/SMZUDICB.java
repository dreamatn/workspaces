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

public class SMZUDICB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 5000;
    String CPN_DDIC_BROWSE = "SM-R_DDIC_BROWSE    ";
    String WS_ERR_MSG = " ";
    short WS_TOT_NUM = 0;
    short WS_LEN = 0;
    String WS_TS_REC = " ";
    short WS_TS_CNT = 0;
    char WS_EOF = ' ';
    SMZUDICB_WS_DDIC_DATA WS_DDIC_DATA = new SMZUDICB_WS_DDIC_DATA();
    SMZUDICB_WS_TIT1 WS_TIT1 = new SMZUDICB_WS_TIT1();
    SMZUDICB_WS_TIT2 WS_TIT2 = new SMZUDICB_WS_TIT2();
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCTDICB SMCTDICB = new SMCTDICB();
    BPRDDIC BPRDDIC = new BPRDDIC();
    SCCGWA SCCGWA;
    SMCODICM SMCODICM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCODICM SMCODICM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCODICM = SMCODICM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUDICB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCTDICB);
        SMCTDICB.INFO.POINTER = BPRDDIC;
        SMCTDICB.INFO.LEN = 418;
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
        SCCMPAG.MAX_COL_NO = 400;
        SCCMPAG.SCR_ROW_CNT = 40;
        SCCMPAG.SCR_COL_CNT = 11;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDDIC);
        BPRDDIC.KEY.NAME = SMCODICM.NAME;
        BPRDDIC.CLASSIFY_CODE = SMCODICM.CL_CODE;
        if (BPRDDIC.KEY.NAME.trim().length() == 0 
            && BPRDDIC.CLASSIFY_CODE.trim().length() == 0) {
            SMCTDICB.INFO.FUNC = 'A';
        } else {
            SMCTDICB.INFO.FUNC = 'S';
        }
        S000_CALL_SMZRDICB();
        if (pgmRtn) return;
        SMCTDICB.INFO.FUNC = 'R';
        S000_CALL_SMZRDICB();
        if (pgmRtn) return;
        while (SMCTDICB.INFO.FUNC != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_OUTPUT_REC();
            if (pgmRtn) return;
            S000_CALL_SMZRDICB();
            if (pgmRtn) return;
        }
        S000_CALL_SMZRDICB();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_REC() throws IOException,SQLException,Exception {
        WS_DDIC_DATA.WS_NAME = BPRDDIC.KEY.NAME;
        WS_DDIC_DATA.WS_CL_CODE = BPRDDIC.CLASSIFY_CODE;
        WS_DDIC_DATA.WS_ALIAS = BPRDDIC.ALIAS;
        WS_DDIC_DATA.WS_EN_NAME = BPRDDIC.EN_NAME;
        WS_DDIC_DATA.WS_CH_NAME = BPRDDIC.CH_NAME;
        WS_DDIC_DATA.WS_TYPE = BPRDDIC.TYPE;
        WS_DDIC_DATA.WS_LENGTH = BPRDDIC.LEN;
        WS_DDIC_DATA.WS_DECIMAL = BPRDDIC.DEC_PNT;
        if (BPRDDIC.SIGN == 'S') {
            WS_DDIC_DATA.WS_SIGN = 'Y';
        } else {
            WS_DDIC_DATA.WS_SIGN = 'N';
        }
        WS_DDIC_DATA.WS_AP_NAME = BPRDDIC.APPL_NAME;
        WS_DDIC_DATA.WS_BSN_DESC = BPRDDIC.BSN_DESC;
        WS_TS_REC = " ";
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_DDIC_DATA);
        WS_LEN = 335;
        WS_TOT_NUM += 1;
        if (WS_TOT_NUM <= 1000) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TS_REC = " ";
            WS_TS_REC = "TO BE CONTINUED";
            WS_LEN = 335;
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
    public void S000_CALL_SMZRDICB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_BROWSE, SMCTDICB);
        if (SMCTDICB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCTDICB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCODICM.RC);
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
