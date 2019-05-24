package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZUGRNO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    AIZUGRNO_WS_RVS_NO WS_RVS_NO = new AIZUGRNO_WS_RVS_NO();
    char WS_PROC_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICUGRNO AICUGRNO;
    public void MP(SCCGWA SCCGWA, AICUGRNO AICUGRNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUGRNO = AICUGRNO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZUGRNO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUGRNO.DATA.BR);
        CEP.TRC(SCCGWA, AICUGRNO.DATA.DATE);
        WS_RVS_NO.WS_BR = AICUGRNO.DATA.BR;
        JIBS_tmp_str[0] = "" + AICUGRNO.DATA.DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1).trim().length() == 0) WS_RVS_NO.WS_DATE = 0;
        else WS_RVS_NO.WS_DATE = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1));
        CEP.TRC(SCCGWA, WS_RVS_NO);
        R000_GET_LAST_NO();
    }
    public void R000_GET_LAST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'B';
        AICRGINF.INFO.OPT = 'L';
        AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        S000_CALL_AIZRGINF();
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
        if (AICRGINF.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
            IBS.CPY2CLS(SCCGWA, AIRGINF.KEY.RVS_NO, WS_RVS_NO);
            CEP.TRC(SCCGWA, WS_RVS_NO.WS_SEQ);
            WS_RVS_NO.WS_SEQ += 1;
            CEP.TRC(SCCGWA, WS_RVS_NO.WS_SEQ);
            AICUGRNO.DATA.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        } else {
            CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
            WS_RVS_NO.WS_SEQ = 1;
            CEP.TRC(SCCGWA, WS_RVS_NO.WS_SEQ);
            AICUGRNO.DATA.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
        }
        CEP.TRC(SCCGWA, AICUGRNO.DATA.RVS_NO);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        CEP.TRC(SCCGWA, AICRGINF.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICUGRNO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICUGRNO = ");
            CEP.TRC(SCCGWA, AICUGRNO);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
