package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPBL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_FND_FLG = ' ';
    BPZPQPBL_WS_KEY WS_KEY = new BPZPQPBL_WS_KEY();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPBL BPRPBL = new BPRPBL();
    SCCGWA SCCGWA;
    BPCPQPBL BPCPQPBL;
    public void MP(SCCGWA SCCGWA, BPCPQPBL BPCPQPBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPBL = BPCPQPBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQPBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPBL);
        WS_FND_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_BPRPBL_PROC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_BPRPBL_PROC() throws IOException,SQLException,Exception {
        BPCPRMR.FUNC = ' ';
        BPRPBL.KEY.TYPE = "AMPBL";
        CEP.TRC(SCCGWA, BPCPQPBL.KEY_TYPE);
        BPCPRMR.TYP = BPRPBL.KEY.TYPE;
        WS_KEY.MOD_NO = BPCPQPBL.KEY.REDEFINES7.MOD_NO;
        WS_KEY.BOOK_FLG = BPCPQPBL.KEY.REDEFINES7.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMR.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        CEP.TRC(SCCGWA, BPCPRMR.CD);
        BPCPRMR.DAT_PTR = BPRPBL;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPBL.TEXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQPBL.TEXT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_PBL_CODE_NOT_EXIST, BPCPQPBL.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQPBL.RC);
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPBL = ");
            CEP.TRC(SCCGWA, BPCPQPBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
