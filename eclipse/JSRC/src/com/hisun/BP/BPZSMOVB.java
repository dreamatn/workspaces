package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMOVB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 30;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSMOVB_WS_CMOV_HEAD WS_CMOV_HEAD = new BPZSMOVB_WS_CMOV_HEAD();
    BPZSMOVB_WS_CMOV_DETAIL WS_CMOV_DETAIL = new BPZSMOVB_WS_CMOV_DETAIL();
    int WS_TEST_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    SCCGWA SCCGWA;
    BPCSCMOV BPCSCMOV;
    public void MP(SCCGWA SCCGWA, BPCSCMOV BPCSCMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCMOV = BPCSCMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMOVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTMOVB);
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPCSCMOV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCMOV.FUNC);
        CEP.TRC(SCCGWA, BPCSCMOV.OUTPUT_FMT);
        CEP.TRC(SCCGWA, BPCSCMOV.BR);
        CEP.TRC(SCCGWA, BPCSCMOV.TLR);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_TYP);
        CEP.TRC(SCCGWA, BPCSCMOV.CCY);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_DT);
        if (BPCSCMOV.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCMOV.BR);
        BPCTMOVB.BR = BPCSCMOV.BR;
        BPCTMOVB.TLR = BPCSCMOV.TLR;
        BPRCMOV.MOV_TYP = BPCSCMOV.MOV_TYP;
        BPRCMOV.KEY.CASH_TYP = BPCSCMOV.CASH_TYP;
        BPRCMOV.KEY.CCY = BPCSCMOV.CCY;
        BPRCMOV.MOV_STS = BPCSCMOV.MOV_STS;
        BPRCMOV.KEY.MOV_DT = BPCSCMOV.MOV_DT;
        BPCTMOVB.FUNC = 'S';
        BPCTMOVB.POINTER = BPRCMOV;
        BPCTMOVB.DATA_LEN = 228;
        S000_CALL_BPZTMOVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSCMOV.BR);
        BPCTMOVB.FUNC = 'R';
        BPCTMOVB.POINTER = BPRCMOV;
        BPCTMOVB.DATA_LEN = 228;
        S000_CALL_BPZTMOVB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTMOVB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTMOVB.FUNC = 'R';
            S000_CALL_BPZTMOVB();
            if (pgmRtn) return;
        }
        BPCTMOVB.FUNC = 'E';
        BPCTMOVB.POINTER = BPRCMOV;
        BPCTMOVB.DATA_LEN = 228;
        S000_CALL_BPZTMOVB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 79;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_CMOV_DETAIL.WS_CMOV_CONF_NO = BPRCMOV.KEY.CONF_NO;
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CONF_NO);
        WS_CMOV_DETAIL.WS_CMOV_MOV_DT = BPRCMOV.KEY.MOV_DT;
        WS_CMOV_DETAIL.WS_CMOV_IN_BR = BPRCMOV.IN_BR;
        WS_CMOV_DETAIL.WS_CMOV_OUT_BR = BPRCMOV.OUT_BR;
        WS_CMOV_DETAIL.WS_CMOV_MOV_STS = BPRCMOV.MOV_STS;
        WS_CMOV_DETAIL.WS_CMOV_IN_TLR = BPRCMOV.IN_TLR;
        WS_CMOV_DETAIL.WS_CMOV_OUT_TLR = BPRCMOV.OUT_TLR;
        WS_CMOV_DETAIL.WS_CMOV_MOV_TYP = BPRCMOV.MOV_TYP;
        WS_CMOV_DETAIL.WS_CMOV_CASH_TYP = BPRCMOV.KEY.CASH_TYP;
        WS_CMOV_DETAIL.WS_CMOV_CCY = BPRCMOV.KEY.CCY;
        WS_CMOV_DETAIL.WS_CMOV_AMT = BPRCMOV.AMT;
        WS_CMOV_DETAIL.WS_ALLOT_TYPE = BPRCMOV.ALLOT_TYPE;
        WS_CMOV_DETAIL.WS_ONWAY_DT = BPRCMOV.ONWAY_DT;
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_CONF_NO);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_IN_BR);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_OUT_BR);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_MOV_STS);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_IN_TLR);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_OUT_TLR);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_MOV_TYP);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_CCY);
        CEP.TRC(SCCGWA, 5);
        CEP.TRC(SCCGWA, WS_CMOV_DETAIL.WS_CMOV_AMT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CMOV_DETAIL);
        SCCMPAG.DATA_LEN = 79;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
        if (BPCTMOVB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTMOVB.RC);
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
