package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBLOW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_BMOV = "BP-R-BRW-BMOV ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSBLOW_WS_BLOW_HEAD WS_BLOW_HEAD = new BPZSBLOW_WS_BLOW_HEAD();
    BPZSBLOW_WS_BLOW_DETAIL WS_BLOW_DETAIL = new BPZSBLOW_WS_BLOW_DETAIL();
    int WS_TEST_BR = 0;
    char WS_TBL_BLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPCRBMOB BPCRBMOB = new BPCRBMOB();
    SCCGWA SCCGWA;
    BPCSBLOW BPCSBLOW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBLOW BPCSBLOW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBLOW = BPCSBLOW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBLOW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRBMOB);
        IBS.init(SCCGWA, BPRBMOV);
        IBS.init(SCCGWA, BPCSBLOW.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBLOW);
        if (BPCSBLOW.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBLOW.BR);
        BPRBMOV.IN_BR = BPCSBLOW.BR;
        BPRBMOV.IN_TLR = BPCSBLOW.TLR;
        BPRBMOV.CODE1 = BPCSBLOW.CODE;
        BPRBMOV.MOV_STS = BPCSBLOW.MOV_STS;
        if (BPCSBLOW.CODE.trim().length() == 0 
                && BPCSBLOW.MOV_STS == ' ') {
            BPCRBMOB.INFO.FUNC = '4';
        } else if (BPCSBLOW.CODE.trim().length() == 0 
                && BPCSBLOW.MOV_STS != ' ') {
            BPCRBMOB.INFO.FUNC = '5';
        } else if (BPCSBLOW.CODE.trim().length() > 0 
                && BPCSBLOW.MOV_STS == ' ') {
            BPCRBMOB.INFO.FUNC = '6';
        } else if (BPCSBLOW.CODE.trim().length() > 0 
                && BPCSBLOW.MOV_STS != ' ') {
            BPCRBMOB.INFO.FUNC = '7';
        } else {
        }
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSBLOW.BR);
        CEP.TRC(SCCGWA, BPCSBLOW.TLR);
        BPCRBMOB.INFO.FUNC = 'N';
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBMOV.OUT_BR);
        CEP.TRC(SCCGWA, BPRBMOV.OUT_TLR);
        CEP.TRC(SCCGWA, BPRBMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPRBMOV.IN_BR);
        CEP.TRC(SCCGWA, BPRBMOV.IN_TLR);
        CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRBMOB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRBMOB.INFO.FUNC = 'N';
            S000_CALL_BPZRBMOB();
            if (pgmRtn) return;
        }
        BPCRBMOB.INFO.FUNC = 'E';
        BPCRBMOB.INFO.POINTER = BPCRBMOB;
        BPCRBMOB.INFO.LEN = 33;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 130;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 13;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_BLOW_DETAIL.WS_BLOW_OUT_BR = BPRBMOV.OUT_BR;
        CEP.TRC(SCCGWA, BPRBMOV.OUT_BR);
        WS_BLOW_DETAIL.WS_BLOW_OUT_TLR = BPRBMOV.OUT_TLR;
        WS_BLOW_DETAIL.WS_BLOW_MOV_STS = BPRBMOV.MOV_STS;
        WS_BLOW_DETAIL.WS_BLOW_IN_BR = BPRBMOV.IN_BR;
        WS_BLOW_DETAIL.WS_BLOW_IN_TLR = BPRBMOV.IN_TLR;
        WS_BLOW_DETAIL.WS_BLOW_MOV_DT = BPRBMOV.KEY.MOV_DT;
        CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
        WS_BLOW_DETAIL.WS_BLOW_CONF_NO = BPRBMOV.KEY.CONF_NO;
        WS_BLOW_DETAIL.WS_BLOW_CODE = BPRBMOV.CODE1;
        WS_BLOW_DETAIL.WS_BLOW_VALUE = BPRBMOV.VALUE;
        WS_BLOW_DETAIL.WS_BLOW_HEAD_NO = BPRBMOV.HEAD_NO1;
        WS_BLOW_DETAIL.WS_BLOW_BEG_NO = BPRBMOV.BEG_NO1;
        WS_BLOW_DETAIL.WS_BLOW_END_NO = BPRBMOV.END_NO1;
        WS_BLOW_DETAIL.WS_BLOW_NUM = BPRBMOV.NUM1;
        CEP.TRC(SCCGWA, WS_BLOW_DETAIL.WS_BLOW_HEAD_NO);
        CEP.TRC(SCCGWA, WS_BLOW_DETAIL.WS_BLOW_BEG_NO);
        CEP.TRC(SCCGWA, WS_BLOW_DETAIL.WS_BLOW_END_NO);
        CEP.TRC(SCCGWA, WS_BLOW_DETAIL.WS_BLOW_NUM);
        CEP.TRC(SCCGWA, WS_BLOW_DETAIL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BLOW_DETAIL);
        SCCMPAG.DATA_LEN = 130;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRBMOB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BMOV, BPCRBMOB);
        CEP.TRC(SCCGWA, BPCRBMOB.RC);
        CEP.TRC(SCCGWA, BPCRBMOB.RETURN_INFO);
        if (BPCRBMOB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBMOB.RC);
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
