package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSREB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FECR = "BP-F-U-MAINTAIN-FECR";
    String WS_ERR_MSG = " ";
    BPZFSREB_WS_OUTPUT_LIST WS_OUTPUT_LIST = new BPZFSREB_WS_OUTPUT_LIST();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOREBO BPCOREBO = new BPCOREBO();
    SCCGWA SCCGWA;
    BPCOFECR BPCOFECR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFECR BPCOFECR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOFECR = BPCOFECR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSREB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOFECR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFECR.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOFECR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCOFECR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUREB();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUREB();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUREB();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOREBO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOFECR.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOREBO.KEY);
        CEP.TRC(SCCGWA, BPCOREBO.KEY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOFECR.VAL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOREBO.VAL);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[1-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[1-1].UP_CNT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[1-1].REB_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[1-1].REB_PER);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[2-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[2-1].UP_CNT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[2-1].REB_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[2-1].REB_PER);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[3-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[3-1].UP_CNT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[3-1].REB_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[3-1].REB_PER);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[4-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[4-1].UP_CNT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[4-1].REB_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[4-1].REB_PER);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[5-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[5-1].UP_CNT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[5-1].REB_AMT);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.REB_DATA[5-1].REB_PER);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOREBO.VAL.EXP_DATE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOFECR.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOREBO;
        SCCFMT.DATA_LEN = 442;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_OUT_RECORDE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_LIST);
        WS_OUTPUT_LIST.WS_REB_CODE = BPCOFECR.KEY.REB_CODE;
        CEP.TRC(SCCGWA, BPCOFECR.KEY.REB_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_REB_CODE);
        WS_OUTPUT_LIST.WS_REB_DESC = BPCOFECR.VAL.REB_DESC;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.REB_DESC);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_REB_DESC);
        WS_OUTPUT_LIST.WS_TX_MMO = BPCOFECR.VAL.TX_MMO;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.TX_MMO);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_TX_MMO);
        WS_OUTPUT_LIST.WS_EFF_DATE = BPCOFECR.VAL.EFF_DATE;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_EFF_DATE);
        WS_OUTPUT_LIST.WS_EXP_DATE = BPCOFECR.VAL.EXP_DATE;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.EXP_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_EXP_DATE);
        WS_OUTPUT_LIST.WS_REB_CYCLE = BPCOFECR.VAL.R_CYCLE;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.R_CYCLE);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_REB_CYCLE);
        WS_OUTPUT_LIST.WS_PERIDO_CNT = BPCOFECR.VAL.PER_CNT;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.PER_CNT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_PERIDO_CNT);
        WS_OUTPUT_LIST.WS_REB_STDT = BPCOFECR.VAL.REB_STDT;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.REB_STDT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_REB_STDT);
        WS_OUTPUT_LIST.WS_REB_DATE = BPCOFECR.VAL.REB_DATE;
        CEP.TRC(SCCGWA, BPCOFECR.VAL.REB_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_REB_DATE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_LIST);
        SCCMPAG.DATA_LEN = 160;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFUREB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FECR, BPCOFECR);
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
