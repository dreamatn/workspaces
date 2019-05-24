package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLTHL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPZ67";
    String K_T_MAINT_PRDT_MENU = "BP-BRW-RTHL-HIS";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSLTHL_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSLTHL_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFLTHL BPRFLTHL = new BPRFLTHL();
    BPCKLTHL BPCKLTHL = new BPCKLTHL();
    BPCTLTHL BPCTLTHL = new BPCTLTHL();
    SCCGWA SCCGWA;
    BPCSLTHL BPCSLTHL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSLTHL BPCSLTHL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLTHL = BPCSLTHL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLTHL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSLTHL.INFO.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSLTHL.INFO.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSLTHL.INFO.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSLTHL.INFO.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSLTHL.INFO.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSLTHL.INFO.FUNC != 'B') {
            B300_MOVE_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLTHL);
        if (BPCSLTHL.CLEAR_HOLD_TYPE.trim().length() == 0) {
            BPCTLTHL.CLEAR_HOLD_TYPE = "%%%%%%%%%%";
        } else {
            BPCTLTHL.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        }
        if (BPCSLTHL.CCY.trim().length() == 0) {
            BPCTLTHL.CCY = "%%%";
        } else {
            BPCTLTHL.CCY = BPCSLTHL.CCY;
        }
        CEP.TRC(SCCGWA, BPCTLTHL.CLEAR_HOLD_TYPE);
        CEP.TRC(SCCGWA, BPCTLTHL.CCY);
        BPCTLTHL.INFO.FUNC = 'B';
        BPCTLTHL.INFO.OPT = 'S';
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        BPCTLTHL.INFO.OPT = 'N';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTLTHL.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B050_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTLTHL.INFO.OPT = 'N';
            S000_CALL_BPZTLTHL();
            if (pgmRtn) return;
        }
        BPCTLTHL.INFO.OPT = 'E';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLTHL);
        IBS.init(SCCGWA, BPCTLTHL);
        BPRFLTHL.KEY.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        CEP.TRC(SCCGWA, BPRFLTHL.KEY.CLEAR_HOLD_TYPE);
        BPRFLTHL.KEY.CCY = BPCSLTHL.CCY;
        BPRFLTHL.CALD_CD1 = BPCSLTHL.CALD_CD1;
        BPRFLTHL.CALD_CD2 = BPCSLTHL.CALD_CD2;
        BPRFLTHL.CALD_CD3 = BPCSLTHL.CALD_CD3;
        BPRFLTHL.CALD_CD4 = BPCSLTHL.CALD_CD4;
        BPRFLTHL.CALD_CD5 = BPCSLTHL.CALD_CD5;
        BPRFLTHL.CALD_CD6 = BPCSLTHL.CALD_CD6;
        BPRFLTHL.CALD_CD7 = BPCSLTHL.CALD_CD7;
        BPRFLTHL.CALD_CD8 = BPCSLTHL.CALD_CD8;
        BPRFLTHL.CALD_CD9 = BPCSLTHL.CALD_CD9;
        BPRFLTHL.CALD_CD10 = BPCSLTHL.CALD_CD10;
        BPRFLTHL.CITY_CD1 = BPCSLTHL.CITY_CD1;
        BPRFLTHL.CITY_CD2 = BPCSLTHL.CITY_CD2;
        BPRFLTHL.CITY_CD3 = BPCSLTHL.CITY_CD3;
        BPRFLTHL.CITY_CD4 = BPCSLTHL.CITY_CD4;
        BPRFLTHL.CITY_CD5 = BPCSLTHL.CITY_CD5;
        BPRFLTHL.CITY_CD6 = BPCSLTHL.CITY_CD6;
        BPRFLTHL.CITY_CD7 = BPCSLTHL.CITY_CD7;
        BPRFLTHL.CITY_CD8 = BPCSLTHL.CITY_CD8;
        BPRFLTHL.CITY_CD9 = BPCSLTHL.CITY_CD9;
        BPRFLTHL.CITY_CD10 = BPCSLTHL.CITY_CD10;
        BPCTLTHL.INFO.FUNC = 'A';
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLTHL);
        BPRFLTHL.KEY.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        BPRFLTHL.KEY.CCY = BPCSLTHL.CCY;
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        BPCTLTHL.INFO.FUNC = 'R';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFLTHL);
        IBS.init(SCCGWA, BPCTLTHL);
        BPRFLTHL.KEY.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        BPRFLTHL.KEY.CCY = BPCSLTHL.CCY;
        BPRFLTHL.CALD_CD1 = BPCSLTHL.CALD_CD1;
        BPRFLTHL.CALD_CD2 = BPCSLTHL.CALD_CD2;
        BPRFLTHL.CALD_CD3 = BPCSLTHL.CALD_CD3;
        BPRFLTHL.CALD_CD4 = BPCSLTHL.CALD_CD4;
        BPRFLTHL.CALD_CD5 = BPCSLTHL.CALD_CD5;
        BPRFLTHL.CALD_CD6 = BPCSLTHL.CALD_CD6;
        BPRFLTHL.CALD_CD7 = BPCSLTHL.CALD_CD7;
        BPRFLTHL.CALD_CD8 = BPCSLTHL.CALD_CD8;
        BPRFLTHL.CALD_CD9 = BPCSLTHL.CALD_CD9;
        BPRFLTHL.CALD_CD10 = BPCSLTHL.CALD_CD10;
        BPRFLTHL.CITY_CD1 = BPCSLTHL.CITY_CD1;
        BPRFLTHL.CITY_CD2 = BPCSLTHL.CITY_CD2;
        BPRFLTHL.CITY_CD3 = BPCSLTHL.CITY_CD3;
        BPRFLTHL.CITY_CD4 = BPCSLTHL.CITY_CD4;
        BPRFLTHL.CITY_CD5 = BPCSLTHL.CITY_CD5;
        BPRFLTHL.CITY_CD6 = BPCSLTHL.CITY_CD6;
        BPRFLTHL.CITY_CD7 = BPCSLTHL.CITY_CD7;
        BPRFLTHL.CITY_CD8 = BPCSLTHL.CITY_CD8;
        BPRFLTHL.CITY_CD9 = BPCSLTHL.CITY_CD9;
        BPRFLTHL.CITY_CD10 = BPCSLTHL.CITY_CD10;
        BPCTLTHL.INFO.FUNC = 'U';
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLTHL);
        BPRFLTHL.KEY.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        BPRFLTHL.KEY.CCY = BPCSLTHL.CCY;
        BPCTLTHL.INFO.FUNC = 'I';
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        if (BPCTLTHL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOLD_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLTHL);
        BPRFLTHL.KEY.CLEAR_HOLD_TYPE = BPCSLTHL.CLEAR_HOLD_TYPE;
        BPRFLTHL.KEY.CCY = BPCSLTHL.CCY;
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        BPCTLTHL.INFO.FUNC = 'R';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        BPCTLTHL.INFO.FUNC = 'D';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 113;
        SCCMPAG.SCR_ROW_CNT = 99;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFLTHL.KEY.CLEAR_HOLD_TYPE);
        CEP.TRC(SCCGWA, BPRFLTHL.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFLTHL.CALD_CD1);
        CEP.TRC(SCCGWA, BPRFLTHL.CITY_CD1);
        WS_OUTPUT_DATA.WS_CLEAR_HOLD_TYPE = BPRFLTHL.KEY.CLEAR_HOLD_TYPE;
        WS_OUTPUT_DATA.WS_CCY = BPRFLTHL.KEY.CCY;
        WS_OUTPUT_DATA.WS_CALD_CD1 = BPRFLTHL.CALD_CD1;
        WS_OUTPUT_DATA.WS_CALD_CD2 = BPRFLTHL.CALD_CD2;
        WS_OUTPUT_DATA.WS_CALD_CD3 = BPRFLTHL.CALD_CD3;
        WS_OUTPUT_DATA.WS_CALD_CD4 = BPRFLTHL.CALD_CD4;
        WS_OUTPUT_DATA.WS_CALD_CD5 = BPRFLTHL.CALD_CD5;
        WS_OUTPUT_DATA.WS_CALD_CD6 = BPRFLTHL.CALD_CD6;
        WS_OUTPUT_DATA.WS_CALD_CD7 = BPRFLTHL.CALD_CD7;
        WS_OUTPUT_DATA.WS_CALD_CD8 = BPRFLTHL.CALD_CD8;
        WS_OUTPUT_DATA.WS_CALD_CD9 = BPRFLTHL.CALD_CD9;
        WS_OUTPUT_DATA.WS_CALD_CD10 = BPRFLTHL.CALD_CD10;
        WS_OUTPUT_DATA.WS_CITY_CD1 = BPRFLTHL.CITY_CD1;
        WS_OUTPUT_DATA.WS_CITY_CD2 = BPRFLTHL.CITY_CD2;
        WS_OUTPUT_DATA.WS_CITY_CD3 = BPRFLTHL.CITY_CD3;
        WS_OUTPUT_DATA.WS_CITY_CD4 = BPRFLTHL.CITY_CD4;
        WS_OUTPUT_DATA.WS_CITY_CD5 = BPRFLTHL.CITY_CD5;
        WS_OUTPUT_DATA.WS_CITY_CD6 = BPRFLTHL.CITY_CD6;
        WS_OUTPUT_DATA.WS_CITY_CD7 = BPRFLTHL.CITY_CD7;
        WS_OUTPUT_DATA.WS_CITY_CD8 = BPRFLTHL.CITY_CD8;
        WS_OUTPUT_DATA.WS_CITY_CD9 = BPRFLTHL.CITY_CD9;
        WS_OUTPUT_DATA.WS_CITY_CD10 = BPRFLTHL.CITY_CD10;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CLEAR_HOLD_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CALD_CD1);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CITY_CD1);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 113;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCKLTHL;
        SCCFMT.DATA_LEN = 113;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCKLTHL);
        BPCKLTHL.CLEAR_HOLD_TYPE = BPRFLTHL.KEY.CLEAR_HOLD_TYPE;
        BPCKLTHL.CCY = BPRFLTHL.KEY.CCY;
        BPCKLTHL.CALD_CD1 = BPRFLTHL.CALD_CD1;
        BPCKLTHL.CALD_CD2 = BPRFLTHL.CALD_CD2;
        BPCKLTHL.CALD_CD3 = BPRFLTHL.CALD_CD3;
        BPCKLTHL.CALD_CD4 = BPRFLTHL.CALD_CD4;
        BPCKLTHL.CALD_CD5 = BPRFLTHL.CALD_CD5;
        BPCKLTHL.CALD_CD6 = BPRFLTHL.CALD_CD6;
        BPCKLTHL.CALD_CD7 = BPRFLTHL.CALD_CD7;
        BPCKLTHL.CALD_CD8 = BPRFLTHL.CALD_CD8;
        BPCKLTHL.CALD_CD9 = BPRFLTHL.CALD_CD9;
        BPCKLTHL.CALD_CD10 = BPRFLTHL.CALD_CD10;
        BPCKLTHL.CITY_CD1 = BPRFLTHL.CITY_CD1;
        BPCKLTHL.CITY_CD2 = BPRFLTHL.CITY_CD2;
        BPCKLTHL.CITY_CD3 = BPRFLTHL.CITY_CD3;
        BPCKLTHL.CITY_CD4 = BPRFLTHL.CITY_CD4;
        BPCKLTHL.CITY_CD5 = BPRFLTHL.CITY_CD5;
        BPCKLTHL.CITY_CD6 = BPRFLTHL.CITY_CD6;
        BPCKLTHL.CITY_CD7 = BPRFLTHL.CITY_CD7;
        BPCKLTHL.CITY_CD8 = BPRFLTHL.CITY_CD8;
        BPCKLTHL.CITY_CD9 = BPRFLTHL.CITY_CD9;
        BPCKLTHL.CITY_CD10 = BPRFLTHL.CITY_CD10;
        CEP.TRC(SCCGWA, BPCKLTHL.CALD_CD1);
        CEP.TRC(SCCGWA, BPCKLTHL.CALD_CD2);
        CEP.TRC(SCCGWA, BPCKLTHL.CALD_CD3);
        CEP.TRC(SCCGWA, BPCKLTHL.CALD_CD4);
    }
    public void S000_CALL_BPZTLTHL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_T_MAINT_PRDT_MENU, BPCTLTHL);
        if (BPCTLTHL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLTHL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSLTHL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLTHL.RC);
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
