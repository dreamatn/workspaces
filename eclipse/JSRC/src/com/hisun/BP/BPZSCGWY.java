package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCGWY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPZ11";
    String K_R_DRW_CGWY = "BP-R-DRW-CGWY";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    BPZSCGWY_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSCGWY_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRCGWY BPCRCGWY = new BPCRCGWY();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPRCGWY BPRCGWY = new BPRCGWY();
    SCCGWA SCCGWA;
    BPCSCGWY BPCSCGWY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCGWY BPCSCGWY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCGWY = BPCSCGWY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZSCGWY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCGWY.RC);
        IBS.init(SCCGWA, BPRCGWY);
        IBS.init(SCCGWA, BPCRCGWY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 END");
        B200_KEY_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_KEY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCGWY.CHANG_WAY.trim().length() > 0) {
            BPCRCGWY.FUNC = 'Q';
            BPRCGWY.KEY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPRCGWY.KEY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
            CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
            CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
            S000_CALL_BPZRCGWY();
            if (pgmRtn) return;
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCSMPRD);
            BPCSMPRD.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPCSMPRD.INFO.FUNC = 'Q';
            S000_CALL_BPZSMPRD();
            if (pgmRtn) return;
            BPCSCGWY.PARM_CODE = BPCSMPRD.PARM_CODE;
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCGWY.KEY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPRCGWY.KEY.CHANG_WAY);
        CEP.TRC(SCCGWA, BPRCGWY.PARM_CODE);
        BPCSCGWY.PRDT_CODE = BPRCGWY.KEY.PRDT_CODE;
        BPCSCGWY.CHANG_WAY = BPRCGWY.KEY.CHANG_WAY;
        BPCSCGWY.PARM_CODE = BPRCGWY.PARM_CODE;
    }
    public void S000_CALL_BPZRCGWY() throws IOException,SQLException,Exception {
        BPCRCGWY.POINTER = BPRCGWY;
        BPCRCGWY.LEN = 50;
        IBS.CALLCPN(SCCGWA, K_R_DRW_CGWY, BPCRCGWY);
        if (BPCRCGWY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCGWY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCGWY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCGWY.RC);
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
