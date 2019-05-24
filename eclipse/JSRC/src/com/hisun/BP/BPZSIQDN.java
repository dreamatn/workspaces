package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIQDN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "DAYE ";
    String K_CMP_MAIN_BPTDAYE = "BP-R-MAINT-DAYE";
    BPZSIQDN_WS_RC WS_RC = new BPZSIQDN_WS_RC();
    short WS_I = 0;
    int WS_END_DATE = 0;
    BPZSIQDN_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSIQDN_WS_OUTPUT_DATA();
    char WS_STOP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPRDAYE BPRDAYE = new BPRDAYE();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSIQDN BPCSIQDN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSIQDN BPCSIQDN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIQDN = BPCSIQDN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIQDN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_RC);
        WS_RC.WS_RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSIQDN.OUTPUT_FLG == 'Y' 
            || BPCSIQDN.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCSIQDN.TYPE != 'A' 
            && BPCSIQDN.TYPE != 'B' 
            && BPCSIQDN.TYPE != 'I')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSIQDN.TYPE == 'B' 
                && BPCSIQDN.BR == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSIQDN.TYPE == 'A' 
                && BPCSIQDN.REG_CD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSIQDN.STR_DATE == 0 
            || BPCSIQDN.END_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSIQDN.FUNC == 'B') {
            B210_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDAYE);
        IBS.init(SCCGWA, BPCTDAYE);
        BPRDAYE.KEY.TYPE = BPCSIQDN.TYPE;
        BPRDAYE.KEY.RGN = BPCSIQDN.REG_CD;
        BPRDAYE.KEY.BCH = "" + BPCSIQDN.BR;
        JIBS_tmp_int = BPRDAYE.KEY.BCH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRDAYE.KEY.BCH = "0" + BPRDAYE.KEY.BCH;
        BPRDAYE.KEY.DATE = BPCSIQDN.STR_DATE;
        WS_END_DATE = BPCSIQDN.END_DATE;
        BPCTDAYE.INFO.FUNC = 'B';
        BPCTDAYE.INFO.OPT = 'S';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCSIQDN.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B211_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCTDAYE);
            BPCTDAYE.INFO.FUNC = 'B';
            BPCTDAYE.INFO.OPT = 'N';
            S000_CALL_BPZTDAYE();
            if (pgmRtn) return;
            if (BPCTDAYE.RETURN_INFO == 'N' 
                || BPRDAYE.KEY.DATE > WS_END_DATE) {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPCSIQDN.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B211_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTDAYE);
        BPCTDAYE.INFO.FUNC = 'B';
        BPCTDAYE.INFO.OPT = 'E';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
    }
    public void B211_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 39;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_TYPE = BPRDAYE.KEY.TYPE;
            WS_OUTPUT_DATA.WS_OT_RGN = BPRDAYE.KEY.RGN;
            if (BPRDAYE.KEY.BCH.trim().length() == 0) WS_OUTPUT_DATA.WS_OT_BR = 0;
            else WS_OUTPUT_DATA.WS_OT_BR = Integer.parseInt(BPRDAYE.KEY.BCH);
            WS_OUTPUT_DATA.WS_OT_DATE = BPRDAYE.KEY.DATE;
            WS_OUTPUT_DATA.WS_OT_DATE_CHA = BPRDAYE.CHARACTER;
            WS_OUTPUT_DATA.WS_OT_LST_DATE = BPRDAYE.LAST_DATE;
            WS_OUTPUT_DATA.WS_OT_LST_TLT = BPRDAYE.LAST_TELLER;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 39;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTDAYE, BPCTDAYE);
        if (BPCTDAYE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTDAYE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
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
