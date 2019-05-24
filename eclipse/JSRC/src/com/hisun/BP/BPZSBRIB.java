package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBRIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSBRIB";
    String CPN_R_STARTBR_BRIB = "BP-R-STARTBR-BRIB";
    BPZSBRIB_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBRIB_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRBRIB BPCRBRIB = new BPCRBRIB();
    BPCOBRIO BPCOBRIO = new BPCOBRIO();
    BPRBRIS BPRBRIS = new BPRBRIS();
    SCCGWA SCCGWA;
    BPCSBRIB BPCSBRIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBRIB BPCSBRIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBRIB = BPCSBRIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBRIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPCRBRIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B00");
        CEP.TRC(SCCGWA, BPCSBRIB.BR);
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        B020_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && WS_TEMP_VARIABLE.WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
            CEP.TRC(SCCGWA, "11111");
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "22222");
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                    B040_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B040_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
        BPCSBRIB.CNT = WS_TEMP_VARIABLE.WS_CNT;
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIB.BR;
        if (BPCSBRIB.BR == 0) {
            BPRBRIS.KEY.BR = 0;
        } else {
            BPRBRIS.KEY.BR = BPCSBRIB.BR;
        }
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
        BPCRBRIB.INFO.FUNC = 'S';
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        BPCRBRIB.INFO.LEN = 134;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRBRIB.INFO.FUNC = 'N';
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        BPCRBRIB.INFO.LEN = 134;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
        if (BPCRBRIB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRBRIB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRBRIB.INFO.FUNC = 'E';
        BPCRBRIB.INFO.LEN = 134;
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
        if (BPCRBRIB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 79;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBRIO);
        BPCOBRIO.BR = BPRBRIS.KEY.BR;
        BPCOBRIO.INSR_CCY = BPRBRIS.INSR_CCY;
        BPCOBRIO.BXIR_AMT = BPRBRIS.BXIR_AMT;
        BPCOBRIO.PLIR_AMT = BPRBRIS.PLIR_AMT;
        BPCOBRIO.LMT_CCY = BPRBRIS.KEY.LMT_CCY;
        BPCOBRIO.LMT_U = BPRBRIS.LMT_U;
        BPCOBRIO.LMT_L = BPRBRIS.LMT_L;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBRIO);
        SCCMPAG.DATA_LEN = 79;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBRIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_BRIB, BPCRBRIB);
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
