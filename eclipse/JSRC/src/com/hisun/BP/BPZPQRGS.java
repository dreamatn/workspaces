package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRGS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_P_QRGN_MAINTAIN = "BP-P-INQ-QRGN ";
    char WS_EMP_RECORD = ' ';
    int WS_I = 0;
    BPZPQRGS_WS_RGN_CODE WS_RGN_CODE = new BPZPQRGS_WS_RGN_CODE();
    char WS_TBL_RGND_FLAG = ' ';
    int WS_TEMP_BR1 = 0;
    int WS_TEMP_BR2 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPRRGND BPRRGND = new BPRRGND();
    BPCPQRGN BPCPQRGN = new BPCPQRGN();
    SCCGWA SCCGWA;
    BPCPQRGS BPCPQRGS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQRGS BPCPQRGS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQRGS = BPCPQRGS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRGS return!");
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
        B020_GET_QRGN_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQRGS.RGN_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
        if (BPCPQRGS.UNIT1.trim().length() == 0 
            || BPCPQRGS.UNIT1.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQRGS.UNIT2.trim().length() == 0 
            || BPCPQRGS.UNIT2.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQRGS.BNK.equalsIgnoreCase("0")) {
            BPCPQRGS.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B020_GET_QRGN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQRGN);
        BPCPQRGN.BNK = BPCPQRGS.BNK;
        BPCPQRGN.RGN_TYPE = BPCPQRGS.RGN_TYPE;
        BPCPQRGN.UNIT = BPCPQRGS.UNIT1;
        S000_CALL_BPZPQRGN();
        if (pgmRtn) return;
        if (BPCPQRGN.RGN_NO_INFO.RGN_CODE[1-1].trim().length() == 0) WS_TEMP_BR1 = 0;
        else WS_TEMP_BR1 = Integer.parseInt(BPCPQRGN.RGN_NO_INFO.RGN_CODE[1-1]);
        IBS.init(SCCGWA, BPCPQRGN);
        BPCPQRGN.BNK = BPCPQRGS.BNK;
        BPCPQRGN.RGN_TYPE = BPCPQRGS.RGN_TYPE;
        BPCPQRGN.UNIT = BPCPQRGS.UNIT2;
        S000_CALL_BPZPQRGN();
        if (pgmRtn) return;
        if (BPCPQRGN.RGN_NO_INFO.RGN_CODE[1-1].trim().length() == 0) WS_TEMP_BR2 = 0;
        else WS_TEMP_BR2 = Integer.parseInt(BPCPQRGN.RGN_NO_INFO.RGN_CODE[1-1]);
        if (WS_TEMP_BR1 == WS_TEMP_BR2) {
            BPCPQRGS.RGN_CODE = BPCPQRGN.RGN_NO_INFO.RGN_CODE[1-1];
        } else {
            BPCPQRGS.RGN_CODE = " ";
        }
    }
    public void S000_CALL_BPZPQRGN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_P_QRGN_MAINTAIN, BPCPQRGN);
        if (BPCTBRGN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTBRGN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQRGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQRGS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQRGS = ");
            CEP.TRC(SCCGWA, BPCPQRGS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
