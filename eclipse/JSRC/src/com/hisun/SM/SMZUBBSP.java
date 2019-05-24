package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.SC.SCCWOUT;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMZUBBSP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 24;
    short K_SCR_COL_CNT = 11;
    short K_MAX_BUTT_CNT = 9;
    SMZUBBSP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZUBBSP_WS_TEMP_VARIABLE();
    char WS_WF_FLG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SMCC10 SMCC10 = new SMCC10();
    SCCRBSPC SCCRBSPC = new SCCRBSPC();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SMCUQBSP SMCUQBSP;
    public void MP(SCCGWA SCCGWA, SMCUQBSP SMCUQBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCUQBSP = SMCUQBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUBBSP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCRBSPC);
        SCCRBSPC.PTR = SCRBSPC;
        SCCRBSPC.LEN = 382;
        SMCUQBSP.RC.RC_MMO = "SM";
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B280_OUTPUT_QULIST_HEAD();
        if (pgmRtn) return;
        B001_BRW_BSP_REC();
        if (pgmRtn) return;
    }
    public void B001_BRW_BSP_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRBSPC);
        SCCRBSPC.FUN = 'S';
        S000_CALL_SCZRBSPC();
        if (pgmRtn) return;
        SCCRBSPC.FUN = 'R';
        S000_CALL_SCZRBSPC();
        if (pgmRtn) return;
        while (SCCRBSPC.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, SMCC10);
            WS_TEMP_VARIABLE.WS_TS_REC = " ";
            SMCC10.SERV_CODE = SCRBSPC.KEY.SERV_CODE;
            SMCC10.REMARK = SCRBSPC.REMARK;
            SMCC10.TABLE_NAME = SCRBSPC.TABLE_NAME;
            SMCC10.UCMP_NAME = SCRBSPC.UCMP_NAME;
            SMCC10.BSP_TYPE = SCRBSPC.BSP_TYPE;
            SMCC10.BSP_OPER_STS = SCRBSPC.BSP_OPER_STS;
            SMCC10.WAIT_SERV_CODE = SCRBSPC.WAIT_SERV_CODE;
            SMCC10.REVERSE_FLG = SCRBSPC.REVERSE_FLG;
            SMCC10.ERROR_FLG = SCRBSPC.ERROR_FLG;
            SMCC10.RUN_TYPE = SCRBSPC.RUN_TYPE;
            if (SCRBSPC.RUN_TYPE == 'T') {
                SMCC10.TR_CODE = SCRBSPC.RUN_CMP_NAME;
            } else {
                SMCC10.RUN_CMP_NAME = SCRBSPC.RUN_CMP_NAME;
            }
            SMCC10.RV_CMP_NAME = SCRBSPC.RV_CMP_NAME;
            SMCC10.GEN_PROC = SCRBSPC.GEN_PROC;
            SMCC10.BSP_PROC = SCRBSPC.BSP_PROC;
            SMCC10.BAT_PROC = SCRBSPC.BAT_PROC;
            SMCC10.CMT_SEQ = SCRBSPC.CMT_SEQ;
            WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, SMCC10);
            WS_TEMP_VARIABLE.WS_LEN = 364;
            S000_WRITE_TS();
            if (pgmRtn) return;
            S000_CALL_SCZRBSPC();
            if (pgmRtn) return;
        }
        SCCRBSPC.FUN = 'E';
        S000_CALL_SCZRBSPC();
        if (pgmRtn) return;
    }
    public void B280_OUTPUT_QULIST_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = K_SUBR_ROW_CNT;
        SCCMPAG.MAX_COL_NO = K_MAX_COL_NO;
        SCCMPAG.SCR_ROW_CNT = K_SCR_ROW_NO;
        SCCMPAG.SCR_COL_CNT = K_SCR_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZRBSPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-BRW-BSP-CTL", SCCRBSPC);
        if (SCCRBSPC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRBSPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCUQBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
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
