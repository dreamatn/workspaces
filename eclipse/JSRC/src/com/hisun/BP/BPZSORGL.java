package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSORGL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TBL_ORGL = "BPTLOSS ";
    String WS_TEMP_RECORD = " ";
    String WS_ACO_AC = " ";
    char WS_TBL_ORGL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    BPRORGL BPRORGL = new BPRORGL();
    SCCGWA SCCGWA;
    BPCSORGL BPCSORGL;
    public void MP(SCCGWA SCCGWA, BPCSORGL BPCSORGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSORGL = BPCSORGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSORGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSORGL.RC);
        IBS.init(SCCGWA, BPCRORGL);
        IBS.init(SCCGWA, BPRORGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSORGL.TX_DATE);
        CEP.TRC(SCCGWA, BPCSORGL.TX_JRN);
        CEP.TRC(SCCGWA, BPCSORGL.TX_SEQ);
        CEP.TRC(SCCGWA, BPCSORGL.AC_DT);
        CEP.TRC(SCCGWA, BPCSORGL.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPCSORGL.MN_JRN);
        CEP.TRC(SCCGWA, BPCSORGL.TX_TOOL);
        CEP.TRC(SCCGWA, BPCSORGL.CCY);
        CEP.TRC(SCCGWA, BPCSORGL.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSORGL.SEQ);
        CEP.TRC(SCCGWA, BPCSORGL.INCO_OLD_BR);
        CEP.TRC(SCCGWA, BPCSORGL.INCO_NEW_BR);
        CEP.TRC(SCCGWA, BPCSORGL.TX_FLG);
        CEP.TRC(SCCGWA, BPCSORGL.ERROR_CODE);
        CEP.TRC(SCCGWA, BPCSORGL.TX_TM);
        CEP.TRC(SCCGWA, BPCSORGL.AMO_DATE);
        CEP.TRC(SCCGWA, BPCSORGL.AMO_AMT_YET);
        CEP.TRC(SCCGWA, BPCSORGL.ADJ_AMT);
        CEP.TRC(SCCGWA, BPCSORGL.UPT_DT);
        CEP.TRC(SCCGWA, BPCSORGL.UPT_TLR);
        CEP.TRC(SCCGWA, BPCSORGL.CRT_DATE);
        CEP.TRC(SCCGWA, BPCSORGL.CRT_TLR);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSORGL.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSORGL.FUNC == 'U') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSORGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRORGL);
        BPCRORGL.INFO.FUNC = 'A';
        S000_CALL_BPZRORGL();
        if (pgmRtn) return;
        if (BPCRORGL.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
        }
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_DATE);
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_JRN);
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_SEQ);
        CEP.TRC(SCCGWA, BPRORGL.AC_DT);
        CEP.TRC(SCCGWA, BPRORGL.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPRORGL.MN_JRN);
        CEP.TRC(SCCGWA, BPRORGL.TX_TOOL);
        CEP.TRC(SCCGWA, BPRORGL.CCY);
        CEP.TRC(SCCGWA, BPRORGL.CCY_TYPE);
        CEP.TRC(SCCGWA, BPRORGL.SEQ);
        CEP.TRC(SCCGWA, BPRORGL.INCO_OLD_BR);
        CEP.TRC(SCCGWA, BPRORGL.INCO_NEW_BR);
        CEP.TRC(SCCGWA, BPRORGL.TX_FLG);
        CEP.TRC(SCCGWA, BPRORGL.ERROR_CODE);
        CEP.TRC(SCCGWA, BPRORGL.TX_TM);
        CEP.TRC(SCCGWA, BPRORGL.AMO_DATE);
        CEP.TRC(SCCGWA, BPRORGL.AMO_AMT_YET);
        CEP.TRC(SCCGWA, BPRORGL.ADJ_AMT);
        CEP.TRC(SCCGWA, BPRORGL.UPT_DT);
        CEP.TRC(SCCGWA, BPRORGL.UPT_TLR);
        CEP.TRC(SCCGWA, BPRORGL.CRT_DATE);
        CEP.TRC(SCCGWA, BPRORGL.CRT_TLR);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.KEY.TX_DATE = BPCSORGL.TX_DATE;
        BPRORGL.KEY.TX_JRN = BPCSORGL.TX_JRN;
        BPRORGL.KEY.TX_SEQ = BPCSORGL.TX_SEQ;
        BPRORGL.AC_DT = BPCSORGL.AC_DT;
        BPRORGL.AC_ORGI_TYP = BPCSORGL.AC_ORGI_TYP;
        BPRORGL.MN_JRN = BPCSORGL.MN_JRN;
        BPRORGL.TX_TOOL = BPCSORGL.TX_TOOL;
        BPRORGL.CCY = BPCSORGL.CCY;
        BPRORGL.CCY_TYPE = BPCSORGL.CCY_TYPE;
        BPRORGL.SEQ = BPCSORGL.SEQ;
        BPRORGL.INCO_OLD_BR = BPCSORGL.INCO_OLD_BR;
        BPRORGL.INCO_NEW_BR = BPCSORGL.INCO_NEW_BR;
        if (BPCSORGL.TX_FLG == ' ') {
            BPRORGL.TX_FLG = 'O';
        } else {
            BPRORGL.TX_FLG = BPCSORGL.TX_FLG;
        }
        BPRORGL.ERROR_CODE = BPCSORGL.ERROR_CODE;
        BPRORGL.TX_TM = BPCSORGL.TX_TM;
        BPRORGL.AMO_DATE = BPCSORGL.AMO_DATE;
        BPRORGL.AMO_AMT_YET = BPCSORGL.AMO_AMT_YET;
        BPRORGL.ADJ_AMT = BPCSORGL.ADJ_AMT;
        if (BPCSORGL.UPT_DT == 0) {
            BPRORGL.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPRORGL.UPT_DT = BPCSORGL.UPT_DT;
        }
        if (BPCSORGL.UPT_TLR.trim().length() == 0) {
            BPRORGL.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRORGL.UPT_TLR = BPCSORGL.UPT_TLR;
        }
        BPRORGL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRORGL.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BPRORGL.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRORGL.TS = "0" + BPRORGL.TS;
        if (BPCSORGL.AC_ORGI_TYP == '2') {
            B020_GET_ACO_NO();
            if (pgmRtn) return;
            BPRORGL.ACO_AC = WS_ACO_AC;
        }
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CEP.TRC(SCCGWA, BPCSORGL.TX_TOOL);
        CEP.TRC(SCCGWA, BPCSORGL.SEQ);
        CEP.TRC(SCCGWA, BPCSORGL.CCY);
        CEP.TRC(SCCGWA, BPCSORGL.CCY_TYPE);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = BPCSORGL.TX_TOOL;
        CICQACAC.DATA.AGR_SEQ = BPCSORGL.SEQ;
        CICQACAC.DATA.CCY_ACAC = BPCSORGL.CCY;
        CICQACAC.DATA.CR_FLG = BPCSORGL.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR2);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_OPN_BR_ACAC);
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRORGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSORGL.RC);
            CEP.ERR(SCCGWA, BPCRORGL.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSORGL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCSORGL = ");
            CEP.TRC(SCCGWA, BPCSORGL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
