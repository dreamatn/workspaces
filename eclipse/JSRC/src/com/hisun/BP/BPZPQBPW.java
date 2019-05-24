package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQBPW {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_TEMP_BANK = " ";
    int WS_I = 0;
    int WS_J = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPBKPW BPRPBKPW = new BPRPBKPW();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    BPCPQBPW BPCPQBPW;
    public void MP(SCCGWA SCCGWA, BPCPQBPW BPCPQBPW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQBPW = BPCPQBPW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQBPW return!");
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
        if (BPCPQBPW.DATA_INFO.BNK.equalsIgnoreCase("0")) {
            BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
        if (BPCPQBPW.DATA_INFO.EFF_DT == 0) {
            BPCPQBPW.DATA_INFO.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.PSW_TYP);
        if (BPCPQBPW.DATA_INFO.PSW_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQBPW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 3;
        WS_J = 1;
        BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
    }
    public void B020_GET_BANK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBKPW);
        IBS.init(SCCGWA, BPCPRMR);
        BPRPBKPW.KEY.TYP = "BKPW ";
        if (BPRPBKPW.KEY.CD == null) BPRPBKPW.KEY.CD = "";
        JIBS_tmp_int = BPRPBKPW.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPBKPW.KEY.CD += " ";
        if (BPCPQBPW.DATA_INFO.BNK == null) BPCPQBPW.DATA_INFO.BNK = "";
        JIBS_tmp_int = BPCPQBPW.DATA_INFO.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQBPW.DATA_INFO.BNK += " ";
        BPRPBKPW.KEY.CD = BPCPQBPW.DATA_INFO.BNK + BPRPBKPW.KEY.CD.substring(WS_I);
        WS_I += 1;
        if (BPRPBKPW.KEY.CD == null) BPRPBKPW.KEY.CD = "";
        JIBS_tmp_int = BPRPBKPW.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPBKPW.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCPQBPW.DATA_INFO.PSW_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRPBKPW.KEY.CD = BPRPBKPW.KEY.CD.substring(0, WS_I - 1) + JIBS_tmp_str[0] + BPRPBKPW.KEY.CD.substring(WS_I + WS_J - 1);
        BPCPRMR.EFF_DT = BPCPQBPW.DATA_INFO.EFF_DT;
        CEP.TRC(SCCGWA, "BEFOR-CALL-BPZPRMR");
        CEP.TRC(SCCGWA, BPRPBKPW.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMR.EFF_DT);
        BPRPBKPW.DATA_LEN = 72;
        BPCPRMR.DAT_PTR = BPRPBKPW;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER-CALL-BPZPRMR");
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.TLR_PMAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.TLR_PDAY);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.TLR_RDAY);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.LEN_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.LEN_MIN);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.CAP_MIN);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.CAP_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.LOW_MIN);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.LOW_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.NUM_MIN);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.NUM_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.SPE_C_MIN);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.SPE_C_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.ELEMENT_NUM);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.CON_MAX_NUM);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.USER_CON_MAX);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.RE_FLG_TIMES);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.RAND_PSW_FLG);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.INIT_PSW);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.REL_PSW_LVL);
        CEP.TRC(SCCGWA, BPRPBKPW.DATA_TXT.USE_PSW_MOD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BANK_NOTFND, BPCPQBPW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        WS_I += -1;
        if (BPRPBKPW.KEY.CD == null) BPRPBKPW.KEY.CD = "";
        JIBS_tmp_int = BPRPBKPW.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPBKPW.KEY.CD += " ";
        WS_TEMP_BANK = BPRPBKPW.KEY.CD.substring(0, WS_I);
        BPCPQBPW.DATA_INFO.BNK = WS_TEMP_BANK;
        BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        WS_I += 1;
        if (BPRPBKPW.KEY.CD == null) BPRPBKPW.KEY.CD = "";
        JIBS_tmp_int = BPRPBKPW.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPBKPW.KEY.CD += " ";
        BPCPQBPW.DATA_INFO.PSW_TYP = BPRPBKPW.KEY.CD.substring(WS_I - 1, WS_I + WS_J - 1).charAt(0);
        BPCPQBPW.DATA_INFO.TLR_PMAX = BPRPBKPW.DATA_TXT.TLR_PMAX;
        BPCPQBPW.DATA_INFO.TLR_PDAY = BPRPBKPW.DATA_TXT.TLR_PDAY;
        BPCPQBPW.DATA_INFO.TLR_RDAY = BPRPBKPW.DATA_TXT.TLR_RDAY;
        BPCPQBPW.DATA_INFO.LEN_MAX = BPRPBKPW.DATA_TXT.LEN_MAX;
        BPCPQBPW.DATA_INFO.LEN_MIN = BPRPBKPW.DATA_TXT.LEN_MIN;
        BPCPQBPW.DATA_INFO.CAP_MIN = BPRPBKPW.DATA_TXT.CAP_MIN;
        BPCPQBPW.DATA_INFO.CAP_MAX = BPRPBKPW.DATA_TXT.CAP_MAX;
        BPCPQBPW.DATA_INFO.LOW_MIN = BPRPBKPW.DATA_TXT.LOW_MIN;
        BPCPQBPW.DATA_INFO.LOW_MAX = BPRPBKPW.DATA_TXT.LOW_MAX;
        BPCPQBPW.DATA_INFO.NUM_MIN = BPRPBKPW.DATA_TXT.NUM_MIN;
        BPCPQBPW.DATA_INFO.NUM_MAX = BPRPBKPW.DATA_TXT.NUM_MAX;
        BPCPQBPW.DATA_INFO.SPE_C_MIN = BPRPBKPW.DATA_TXT.SPE_C_MIN;
        BPCPQBPW.DATA_INFO.SPE_C_MAX = BPRPBKPW.DATA_TXT.SPE_C_MAX;
        BPCPQBPW.DATA_INFO.ELEMENT_NUM = BPRPBKPW.DATA_TXT.ELEMENT_NUM;
        BPCPQBPW.DATA_INFO.CON_MAX_NUM = BPRPBKPW.DATA_TXT.CON_MAX_NUM;
        BPCPQBPW.DATA_INFO.USER_CON_MAX = BPRPBKPW.DATA_TXT.USER_CON_MAX;
        BPCPQBPW.DATA_INFO.RE_FLG_TIMES = BPRPBKPW.DATA_TXT.RE_FLG_TIMES;
        BPCPQBPW.DATA_INFO.RAND_PSW_FLG = BPRPBKPW.DATA_TXT.RAND_PSW_FLG;
        BPCPQBPW.DATA_INFO.INIT_PSW = BPRPBKPW.DATA_TXT.INIT_PSW;
        BPCPQBPW.DATA_INFO.REL_PSW_LVL = BPRPBKPW.DATA_TXT.REL_PSW_LVL;
        BPCPQBPW.DATA_INFO.USE_PSW_MOD = BPRPBKPW.DATA_TXT.USE_PSW_MOD;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQBPW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQBPW.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQBPW = ");
            CEP.TRC(SCCGWA, BPCPQBPW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
