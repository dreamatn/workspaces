package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;

public class BPZPQBNK {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    BPZPQBNK_WS_TEMP_BANK WS_TEMP_BANK = new BPZPQBNK_WS_TEMP_BANK();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPBANK BPRPBANK = new BPRPBANK();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    BPCPQBNK BPCPQBNK;
    public void MP(SCCGWA SCCGWA, BPCPQBNK BPCPQBNK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQBNK = BPCPQBNK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQBNK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_BANK_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCPQBNK.DATA_INFO.EFF_DT == 0) {
            BPCPQBNK.DATA_INFO.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCPQBNK.DATA_INFO.BNK.trim().length() == 0) {
            BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B020_GET_BANK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBANK);
        IBS.init(SCCGWA, BPCPRMR);
        BPRPBANK.KEY.CD = BPCPQBNK.DATA_INFO.BNK;
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        BPCPRMR.EFF_DT = BPCPQBNK.DATA_INFO.EFF_DT;
        BPRPBANK.KEY.TYP = "BANK ";
        //BPRPBANK.DATA_LEN = 225;
        BPCPRMR.DAT_PTR = BPRPBANK;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPBANK);
        CEP.TRC(SCCGWA, BPRPBANK.DATA_TXT);
        CEP.TRC(SCCGWA, BPRPBANK.DATA_TXT.AC_CHK);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BANK_NOTFND, BPCPQBNK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPBANK.KEY.CD, WS_TEMP_BANK);
        BPCPQBNK.DATA_INFO.BNK = WS_TEMP_BANK.WS_BANK_CODE;
        BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQBNK.DATA_INFO.CHN_NM = BPRPBANK.DATA_TXT.CHN_NM;
        BPCPQBNK.DATA_INFO.ENG_NM = BPRPBANK.DATA_TXT.ENG_NM;
        BPCPQBNK.DATA_INFO.AC_CHK = BPRPBANK.DATA_TXT.AC_CHK;
        BPCPQBNK.DATA_INFO.CI_CHK = BPRPBANK.DATA_TXT.CI_CHK;
        BPCPQBNK.DATA_INFO.FX_RATE = BPRPBANK.DATA_TXT.FX_RATE;
        BPCPQBNK.DATA_INFO.MAX_LVL = BPRPBANK.DATA_TXT.MAX_LVL;
        BPCPQBNK.DATA_INFO.AUH_LVL = BPRPBANK.DATA_TXT.AUH_LVL;
        BPCPQBNK.DATA_INFO.HEAD_BR = BPRPBANK.DATA_TXT.HEAD_BR;
        BPCPQBNK.DATA_INFO.LOC_CCY1 = BPRPBANK.DATA_TXT.LOC_CCY1;
        BPCPQBNK.DATA_INFO.EVA_CCY = BPRPBANK.DATA_TXT.EVA_CCY;
        BPCPQBNK.DATA_INFO.TOT_CCY = BPRPBANK.DATA_TXT.TOT_CCY;
        BPCPQBNK.DATA_INFO.TAI_FEN = BPRPBANK.DATA_TXT.TAI_FEN;
        BPCPQBNK.DATA_INFO.TLR_COND = BPRPBANK.DATA_TXT.TLR_COND;
        BPCPQBNK.DATA_INFO.CALD_BUI = BPRPBANK.DATA_TXT.CALD_BUI;
        BPCPQBNK.DATA_INFO.CALD_SYS = BPRPBANK.DATA_TXT.CALD_SYS;
        BPCPQBNK.DATA_INFO.INT_TAX = BPRPBANK.DATA_TXT.INT_TAX;
        BPCPQBNK.DATA_INFO.COUN_CD = BPRPBANK.DATA_TXT.COUN_CD;
        BPCPQBNK.DATA_INFO.SG_IN_CNT = BPRPBANK.DATA_TXT.SG_IN_CNT;
        BPCPQBNK.DATA_INFO.CLS = BPRPBANK.DATA_TXT.CLS;
        BPCPQBNK.DATA_INFO.FEE_FLG = BPRPBANK.DATA_TXT.FEE_FLG;
        BPCPQBNK.DATA_INFO.OPN_TM = BPRPBANK.DATA_TXT.OPN_TM;
        BPCPQBNK.DATA_INFO.CLS_TM = BPRPBANK.DATA_TXT.CLS_TM;
        BPCPQBNK.DATA_INFO.HOPN_TM = BPRPBANK.DATA_TXT.HOPN_TM;
        BPCPQBNK.DATA_INFO.HCLS_TM = BPRPBANK.DATA_TXT.HCLS_TM;
        BPCPQBNK.DATA_INFO.CITY_CD = BPRPBANK.DATA_TXT.CITY_CD;
        BPCPQBNK.DATA_INFO.O_CHK_FL = BPRPBANK.DATA_TXT.O_CHK_FL;
        BPCPQBNK.DATA_INFO.V_CHK_FL = BPRPBANK.DATA_TXT.V_CHK_FL;
        BPCPQBNK.DATA_INFO.AUDIT_FL = BPRPBANK.DATA_TXT.AUDIT_FL;
        BPCPQBNK.DATA_INFO.H_E_TIME = BPRPBANK.DATA_TXT.H_E_TIME;
        BPCPQBNK.DATA_INFO.ERP_BRAN = BPRPBANK.DATA_TXT.ERP_BRAN;
        BPCPQBNK.DATA_INFO.EX_RA = BPRPBANK.DATA_TXT.EX_RA;
        BPCPQBNK.DATA_INFO.CALD_PUB = BPRPBANK.DATA_TXT.CALD_PUB;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQBNK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQBNK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQBNK = ");
            CEP.TRC(SCCGWA, BPCPQBNK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
