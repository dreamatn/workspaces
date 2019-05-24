package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRMOG {
    DBParm BPTRMORG_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    BPZSRMOG_WS_OUTPUT WS_OUTPUT = new BPZSRMOG_WS_OUTPUT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRRMORG BPRRMORG = new BPRRMORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSRMOG BPCSRMOG;
    public void MP(SCCGWA SCCGWA, BPCSRMOG BPCSRMOG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRMOG = BPCSRMOG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSRMOG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_ADD_BPTRMORG();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCSRMOG.REMOVE_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRRMORG);
        BPRRMORG.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRRMORG.KEY.REMOVE_DATE = BPCSRMOG.REMOVE_DATE;
        BPRRMORG.KEY.OLD_BR = BPCSRMOG.OLD_BR;
        BPRRMORG.NEW_BR = BPCSRMOG.NEW_BR;
        BPTRMORG_RD = new DBParm();
        BPTRMORG_RD.TableName = "BPTRMORG";
        IBS.READ(SCCGWA, BPRRMORG, BPTRMORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_BPTRMORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRMORG);
        BPRRMORG.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRRMORG.KEY.REMOVE_DATE = BPCSRMOG.REMOVE_DATE;
        BPRRMORG.KEY.OLD_BR = BPCSRMOG.OLD_BR;
        BPRRMORG.NEW_BR = BPCSRMOG.NEW_BR;
        BPTRMORG_RD = new DBParm();
        BPTRMORG_RD.TableName = "BPTRMORG";
        IBS.WRITE(SCCGWA, BPRRMORG, BPTRMORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_REMOVE_DATE = BPCSRMOG.REMOVE_DATE;
        WS_OUTPUT.WS_NEW_BR = BPCSRMOG.NEW_BR;
        WS_OUTPUT.WS_OLD_BR = BPCSRMOG.OLD_BR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPXXX";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 20;
        IBS.FMT(SCCGWA, SCCFMT);
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
