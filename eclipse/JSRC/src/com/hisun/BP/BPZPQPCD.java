package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.*;

public class BPZPQPCD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE_TYPE = "PARMT";
    String K_PARM_CODE_TYPE = "PARMC";
    short WS_I = 0;
    BPZPQPCD_WS_PARMT_KEY WS_PARMT_KEY = new BPZPQPCD_WS_PARMT_KEY();
    BPZPQPCD_WS_PARMT_VAL WS_PARMT_VAL = new BPZPQPCD_WS_PARMT_VAL();
    BPZPQPCD_WS_PARMC_KEY WS_PARMC_KEY = new BPZPQPCD_WS_PARMC_KEY();
    BPZPQPCD_WS_PARMC_VAL WS_PARMC_VAL = new BPZPQPCD_WS_PARMC_VAL();
    short WS_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPCOQPCD BPCOQPCD;
    public void MP(SCCGWA SCCGWA, BPCOQPCD BPCOQPCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOQPCD = BPCOQPCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQPCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCOQPCD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQPCD);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        if (BPCOQPCD.INPUT_DATA.TYPE.trim().length() == 0 
            || BPCOQPCD.INPUT_DATA.TYPE.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_INPUT_TYPE_CODE, BPCOQPCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = K_PARM_TYPE_TYPE;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        BPRPRMT.KEY.CD = BPCOQPCD.INPUT_DATA.TYPE;
        CEP.TRC(SCCGWA, K_PARM_TYPE_TYPE);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_PARMT_VAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0]);
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_NAME = WS_PARMT_VAL.WS_PARMT_CHG_NAME;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_ENG_NAME = WS_PARMT_VAL.WS_PARMT_NAME;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_LVL = WS_PARMT_VAL.WS_PARMT_LVL;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_UP_TYPE = WS_PARMT_VAL.WS_PARMT_UP_TYPE;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_REMARKS = WS_PARMT_VAL.WS_PARMT_REMARKS;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_CODE_MAX_LEN = WS_PARMT_VAL.WS_PARMT_CODE_MAX_LEN;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCE_MAX_LEN = WS_PARMT_VAL.WS_PARMT_DESCE_MAX_LEN;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCC_MAX_LEN = WS_PARMT_VAL.WS_PARMT_DESCC_MAX_LEN;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR1 = WS_PARMT_VAL.WS_PARMT_MAINT_BR1;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR2 = WS_PARMT_VAL.WS_PARMT_MAINT_BR2;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR3 = WS_PARMT_VAL.WS_PARMT_MAINT_BR3;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR4 = WS_PARMT_VAL.WS_PARMT_MAINT_BR4;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR5 = WS_PARMT_VAL.WS_PARMT_MAINT_BR5;
        BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_REP_FLG = WS_PARMT_VAL.WS_PARMT_REP_FLG;
        if (BPCOQPCD.INPUT_DATA.CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRPRMT);
            BPCPRMR.FUNC = ' ';
            BPRPRMT.KEY.TYP = K_PARM_CODE_TYPE;
            WS_PARMC_KEY.WS_PARMC_TYPE = BPCOQPCD.INPUT_DATA.TYPE;
            WS_PARMC_KEY.WS_PARMC_CODE = BPCOQPCD.INPUT_DATA.CODE;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PARMC_KEY);
            CEP.TRC(SCCGWA, WS_PARMC_KEY);
            BPCPRMR.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AAAAA");
            WS_NUM = 668;
            //CEP.TRC(SCCGWA, BPRPRMT.DAT_LEN);
            CEP.TRC(SCCGWA, WS_NUM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_PARMC_VAL);
            BPCOQPCD.OUTPUT_DATA.CODE_INFO.REMARKS = WS_PARMC_VAL.WS_PARMC_REMARKS;
            BPCOQPCD.OUTPUT_DATA.CODE_INFO.RBASE_TYP = WS_PARMC_VAL.WS_PARMC_RBASE_TYP;
            BPCOQPCD.OUTPUT_DATA.CODE_INFO.RBASE_TYP4 = WS_PARMC_VAL.WS_PARMC_RBASE_TYP4;
            BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME = WS_PARMC_VAL.WS_PARMC_CODE_NAME;
            BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME = WS_PARMC_VAL.WS_PARMC_CODE_NAME_S;
            CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
            CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME);
        }
        BPCOQPCD.OUTPUT_DATA.CODE_INFO.EFF_DATE = BPCPRMR.EFF_DT;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, "CALL-BACK");
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                if (BPRPRMT.KEY.TYP.equalsIgnoreCase(K_PARM_CODE_TYPE)) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND, BPCOQPCD.RC);
                } else {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, BPCOQPCD.RC);
                }
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOQPCD.RC);
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOQPCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOQPCD = ");
            CEP.TRC(SCCGWA, BPCOQPCD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
