package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQAMO {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN    ";
    BPZPQAMO_WS_TEMP_BANK WS_TEMP_BANK = new BPZPQAMO_WS_TEMP_BANK();
    short WS_I = 0;
    char WS_REDORD_FLAG = 'N';
    char WS_CNTR_TYPE_FLAG = 'N';
    char WS_PROD_TYPE_FLAG = 'N';
    char WS_BR_FLAG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRACM BPRACM = new BPRACM();
    SCCGWA SCCGWA;
    BPCPQAMO BPCPQAMO;
    public void MP(SCCGWA SCCGWA, BPCPQAMO BPCPQAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQAMO = BPCPQAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQAMO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPQAMO.RC.RC_MMO = "BP";
        BPCPQAMO.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQAMO.FUNC);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
        if (BPCPQAMO.FUNC == 'Q') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCPQAMO.FUNC == 'B') {
            B030_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCPQAMO.FUNC == 'N') {
            B040_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCPQAMO.FUNC == 'E') {
            B050_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQAMO.FUNC == 'Q' 
            || BPCPQAMO.FUNC == 'N') {
            B060_OUTPUT_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPQAMO.DATA_INFO.MOD_NO);
        if (BPCPQAMO.DATA_INFO.CNTR_TYPE.trim().length() == 0 
            && BPCPQAMO.DATA_INFO.MOD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQAMO.DATA_INFO.CNTR_TYPE.trim().length() > 0 
            && BPCPQAMO.DATA_INFO.MOD_NO.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROC() throws IOException,SQLException,Exception {
        if (BPCPQAMO.DATA_INFO.CNTR_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPRACM);
            IBS.init(SCCGWA, BPCPRMR);
            BPRACM.KEY.TYPE = "AMACM";
            BPRACM.KEY.REDEFINES6.CNTR_TYPE = BPCPQAMO.DATA_INFO.CNTR_TYPE;
            BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRACM.KEY.REDEFINES6);
            BPRACM.KEY.REDEFINES6.BR = 0;
            BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRACM.KEY.REDEFINES6);
            CEP.TRC(SCCGWA, BPRACM.KEY.CD);
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                CEP.TRC(SCCGWA, "AAA");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_9383, BPCPQAMO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCPQAMO.DATA_INFO.MOD_NO.trim().length() > 0) {
            B030_START_BROWSE_PROC();
            if (pgmRtn) return;
            WS_REDORD_FLAG = 'N';
            while (WS_REDORD_FLAG != 'Y') {
                IBS.init(SCCGWA, BPCPRMB.RC);
                BPCPRMB.FUNC = '1';
                S000_CALL_BPZPRMB();
                if (pgmRtn) return;
                if (BPRACM.DATA.MOD_NO.equalsIgnoreCase(BPCPQAMO.DATA_INFO.MOD_NO)) {
                    WS_REDORD_FLAG = 'Y';
                }
            }
            B050_END_BROWSE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMB);
        BPRACM.KEY.TYPE = "AMACM";
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void B040_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        WS_REDORD_FLAG = 'N';
        WS_CNTR_TYPE_FLAG = 'N';
        WS_PROD_TYPE_FLAG = 'N';
        WS_BR_FLAG = 'N';
        while (WS_REDORD_FLAG != 'Y') {
            IBS.init(SCCGWA, BPCPRMB.RC);
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            if (BPCPQAMO.DATA_INFO.CNTR_TYPE.trim().length() > 0) {
                if (BPRACM.KEY.REDEFINES6.CNTR_TYPE.equalsIgnoreCase(BPCPQAMO.DATA_INFO.CNTR_TYPE)) {
                    WS_CNTR_TYPE_FLAG = 'Y';
                }
            } else {
                WS_CNTR_TYPE_FLAG = 'Y';
            }
            if (BPCPQAMO.DATA_INFO.PROD_TYPE.trim().length() > 0) {
                if (BPRACM.KEY.REDEFINES6.PROD_TYPE.equalsIgnoreCase(BPCPQAMO.DATA_INFO.PROD_TYPE)) {
                    WS_PROD_TYPE_FLAG = 'Y';
                }
            } else {
                WS_PROD_TYPE_FLAG = 'Y';
            }
            if (BPCPQAMO.DATA_INFO.BR != 0) {
                if (BPRACM.KEY.REDEFINES6.BR == BPCPQAMO.DATA_INFO.BR) {
                    WS_BR_FLAG = 'Y';
                }
            } else {
                WS_BR_FLAG = 'Y';
            }
            if (WS_CNTR_TYPE_FLAG == 'Y' 
                && WS_PROD_TYPE_FLAG == 'Y' 
                && WS_BR_FLAG == 'Y') {
                WS_REDORD_FLAG = 'Y';
            } else {
                WS_CNTR_TYPE_FLAG = 'N';
                WS_PROD_TYPE_FLAG = 'N';
                WS_BR_FLAG = 'N';
            }
        }
    }
    public void B050_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_INFO() throws IOException,SQLException,Exception {
        BPCPQAMO.DATA_INFO.CNTR_TYPE = BPRACM.KEY.REDEFINES6.CNTR_TYPE;
        BPCPQAMO.DATA_INFO.PROD_TYPE = BPRACM.KEY.REDEFINES6.PROD_TYPE;
        BPCPQAMO.DATA_INFO.BR = BPRACM.KEY.REDEFINES6.BR;
        BPCPQAMO.DATA_INFO.MOD_NO = BPRACM.DATA.MOD_NO;
        BPCPQAMO.DATA_INFO.MOD_NAME = BPRACM.DATA.MOD_NAME;
        BPCPQAMO.DATA_INFO.UPD_TEL = BPRACM.DATA.UPD_TEL;
        BPCPQAMO.DATA_INFO.UPD_DATE = BPRACM.DATA.UPD_DATE;
        BPCPQAMO.DATA_INFO.UPD_TIME = BPRACM.DATA.UPD_TIME;
        BPCPQAMO.DATA_INFO.SUP_TEL1 = BPRACM.DATA.SUP_TEL1;
        BPCPQAMO.DATA_INFO.SUP_TEL2 = BPRACM.DATA.SUP_TEL2;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            BPCPQAMO.DATA_INFO.EVENT[WS_I-1].EVENT_TYPE = BPRACM.DATA.EVENT[WS_I-1].EVENT_TYPE;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRACM;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRACM.KEY.CD);
        BPCPRMB.DAT_PTR = BPRACM;
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE      ", BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            CEP.TRC(SCCGWA, "BBB");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_9383, BPCPQAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQAMO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQAMO = ");
            CEP.TRC(SCCGWA, BPCPQAMO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
