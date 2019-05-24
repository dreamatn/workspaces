package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPBRGN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_P_BRGN_MAINTAIN = "BP-R-INQ-BRGN ";
    char WS_EMP_RECORD = ' ';
    int WS_I = 0;
    char WS_TBL_RGND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPRRGND BPRRGND = new BPRRGND();
    SCCGWA SCCGWA;
    BPCOBRGN BPCOBRGN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOBRGN BPCOBRGN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOBRGN = BPCOBRGN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPBRGN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPRRGND);
        IBS.init(SCCGWA, BPCTBRGN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_BRGN_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOBRGN.RGN_TYP == ' ' 
            || BPCOBRGN.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCOBRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_BRGN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPRRGND.KEY.RGN_TYPE = "" + BPCOBRGN.RGN_TYP;
        JIBS_tmp_int = BPRRGND.KEY.RGN_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_TYPE = "0" + BPRRGND.KEY.RGN_TYPE;
        BPRRGND.KEY.RGN_UNIT = "" + BPCOBRGN.BR;
        JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT = "0" + BPRRGND.KEY.RGN_UNIT;
        BPCTBRGN.INFO.POINTER = BPRRGND;
        BPCTBRGN.FUNC = 'S';
        S000_CALL_BPZTRGND();
        if (pgmRtn) return;
        BPCTBRGN.FUNC = 'R';
        S000_CALL_BPZTRGND();
        if (pgmRtn) return;
        if (BPCTBRGN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BANK_NOTFND, BPCOBRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    for (WS_I = 1; WS_I <= 10 
        && BPCTBRGN.RETURN_INFO != 'N'; WS_I += 1) {
        BPCTBRGN.FUNC = 'R';
        S000_CALL_BPZTRGND();
        if (pgmRtn) return;
        BPCOBRGN.RGN_NO_INFO.RGN_NO[WS_I-1] = (short) BPRRGND.KEY.RGN_NO;
    }
    BPCTBRGN.FUNC = 'E';
    S000_CALL_BPZTRGND();
    if (pgmRtn) return;
    if (BPCTBRGN.RETURN_INFO == 'N') {
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BANK_NOTFND, BPCOBRGN.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.TR_BANK = BPRRGND.KEY.BNK;
        BPCOBRGN.RGN_TYP = BPRRGND.KEY.RGN_TYPE.charAt(0);
        BPCOBRGN.RGN_NO_INFO.RGN_NO[WS_I-1] = (short) BPRRGND.KEY.RGN_NO;
        if (BPRRGND.KEY.RGN_UNIT.trim().length() == 0) BPCOBRGN.BR = 0;
        else BPCOBRGN.BR = Integer.parseInt(BPRRGND.KEY.RGN_UNIT);
    }
    public void S000_CALL_BPZTRGND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_P_BRGN_MAINTAIN, BPCTBRGN);
        if (BPCTBRGN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTBRGN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOBRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOBRGN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOBRGN = ");
            CEP.TRC(SCCGWA, BPCOBRGN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
