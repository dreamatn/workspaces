package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRGD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_P_RGND_MAINTAIN = "BP-R-RGND-MAINTAIN ";
    char WS_EMP_RECORD = ' ';
    char WS_TBL_RGND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRRGDM BPCRRGDM = new BPCRRGDM();
    BPRRGND BPRRGND = new BPRRGND();
    SCCGWA SCCGWA;
    BPCPQRGD BPCPQRGD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQRGD BPCPQRGD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQRGD = BPCPQRGD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRGD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRGND);
        IBS.init(SCCGWA, BPCRRGDM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_RGND_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQRGD.RGN_TYPE.trim().length() == 0 
            || BPCPQRGD.RGN_SEQ == 0 
            || (BPCPQRGD.UNIT.trim().length() == 0 
            || BPCPQRGD.UNIT.charAt(0) == 0X00)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQRGD.BNK.equalsIgnoreCase("0")) {
            BPCPQRGD.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B020_GET_RGND_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = BPCPQRGD.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCPQRGD.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCPQRGD.RGN_SEQ;
        BPRRGND.KEY.RGN_UNIT = BPCPQRGD.UNIT;
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        BPCRRGDM.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPRRGND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_TYPE);
        CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_NO);
        CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_UNIT);
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
        if (BPCRRGDM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPQRGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.TR_BANK = BPRRGND.KEY.BNK;
        BPCPQRGD.RGN_TYPE = BPRRGND.KEY.RGN_TYPE;
        BPCPQRGD.RGN_SEQ = BPRRGND.KEY.RGN_NO;
        BPCPQRGD.UNIT = BPRRGND.KEY.RGN_UNIT;
        BPCPQRGD.FLG = BPRRGND.FLG;
        BPCPQRGD.RMK = BPRRGND.RMK;
        BPCPQRGD.EFF_DT = BPRRGND.EFF_DT;
        BPCPQRGD.EXP_DT = BPRRGND.EXP_DT;
        BPCPQRGD.UPT_DT = BPRRGND.UPT_DT;
        BPCPQRGD.UPT_TLR = BPRRGND.UPT_TLR;
    }
    public void S000_CALL_BPZTRGDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_P_RGND_MAINTAIN, BPCRRGDM);
        if (BPCRRGDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRGDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQRGD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQRGD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQRGD = ");
            CEP.TRC(SCCGWA, BPCPQRGD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
