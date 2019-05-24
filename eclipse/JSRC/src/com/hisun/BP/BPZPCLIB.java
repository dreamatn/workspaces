package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCLIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_BP_CLIB_GROUP = "BP-R-CLIB-GROUP ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCTCIBB BPCTCIBB = new BPCTCIBB();
    SCCGWA SCCGWA;
    BPCPCLIB BPCPCLIB;
    public void MP(SCCGWA SCCGWA, BPCPCLIB BPCPCLIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCLIB = BPCPCLIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCLIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHK_INPUT();
        if (pgmRtn) return;
        if (BPCPCLIB.VB_FLG == '3' 
            || BPCPCLIB.VB_FLG == '4') {
            B100_CHK_CLIB_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCPCLIB.VB_FLG == '1' 
            || BPCPCLIB.VB_FLG == '2') {
            B200_CHK_CLIB_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B001_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCLIB.VB_FLG);
        if (BPCPCLIB.VB_FLG != '1' 
            && BPCPCLIB.VB_FLG != '2' 
            && BPCPCLIB.VB_FLG != '3' 
            && BPCPCLIB.VB_FLG != '4') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VBFLG_ERR, BPCPCLIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCLIB.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VBTLR_INPUT, BPCPCLIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_CLIB_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCIBB);
        BPCTCIBB.INFO.FUNC = 'G';
        BPCTCIBB.INFO.TLR = BPCPCLIB.TLR;
        BPCTCIBB.INFO.VB_FLG = BPCPCLIB.VB_FLG;
        S000_CALL_BPZTCIBB();
        if (pgmRtn) return;
        if (BPCTCIBB.RETURN_CNT > 0) {
            BPCPCLIB.FLG = 'Y';
        }
        if (BPCTCIBB.RETURN_CNT == 0) {
            BPCPCLIB.FLG = 'N';
        }
    }
    public void B200_CHK_CLIB_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCIBB);
        BPCTCIBB.INFO.FUNC = 'G';
        BPCTCIBB.INFO.MGR_TLR = BPCPCLIB.TLR;
        BPCTCIBB.INFO.VB_FLG = BPCPCLIB.VB_FLG;
        S000_CALL_BPZTCIBB();
        if (pgmRtn) return;
        if (BPCTCIBB.RETURN_CNT > 0) {
            BPCPCLIB.FLG = 'Y';
        }
        if (BPCTCIBB.RETURN_CNT == 0) {
            BPCPCLIB.FLG = 'N';
        }
    }
    public void S000_CALL_BPZTCIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_CLIB_GROUP, BPCTCIBB);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCLIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCLIB = ");
            CEP.TRC(SCCGWA, BPCPCLIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
