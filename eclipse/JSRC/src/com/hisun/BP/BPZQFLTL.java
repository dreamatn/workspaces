package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQFLTL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZQFLTL";
    String K_CALL_BPZQFLT = "BP-INQ-FLT-STS   ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZQFLTL_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZQFLTL_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQFLT BPCQFLT = new BPCQFLT();
    SCCGWA SCCGWA;
    BPCQFLTL BPCQFLTL;
    public void MP(SCCGWA SCCGWA, BPCQFLTL BPCQFLTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQFLTL = BPCQFLTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAINTAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQFLTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQFLT);
    }
    public void B000_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZQFLT();
        if (pgmRtn) return;
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= BPCQFLT.OUTPUT_DATA.FLT_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        }
    }
    public void S000_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 276;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.FLT_CNT);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD_DES);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM_DES);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].STS);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].TO_FLT);
        CEP.TRC(SCCGWA, BPCQFLT.CCY);
        WS_OUTPUT_DATA.WS_FLT_CD = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD;
        WS_OUTPUT_DATA.WS_FLT_CD_DES = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD_DES;
        WS_OUTPUT_DATA.WS_CCY = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CCY;
        WS_OUTPUT_DATA.WS_FLT_ITM = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM;
        WS_OUTPUT_DATA.WS_FLT_ITM_DES = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM_DES;
        WS_OUTPUT_DATA.WS_STS = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].STS;
        WS_OUTPUT_DATA.WS_TO_FLT = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].TO_FLT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 276;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZQFLT() throws IOException,SQLException,Exception {
        BPCQFLT.FLT_CODE = BPCQFLTL.FLT_CODE;
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQFLT, BPCQFLT);
        if (BPCQFLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQFLT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
