package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRTD {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZPQRTD";
    String WS_ERR_MSG = " ";
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRRTID BPRRTID = new BPRRTID();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    SCCGWA SCCGWA;
    BPCOQRTD BPCOQRTD;
    public void MP(SCCGWA SCCGWA, BPCOQRTD BPCOQRTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOQRTD = BPCOQRTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_STOP_FLG = ' ';
        IBS.init(SCCGWA, BPRRTID);
        IBS.init(SCCGWA, BPCRMRTD);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCOQRTD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_RATE_ID();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.CCY);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.BASE_TYP);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.TENOR);
        if (BPCOQRTD.INQ_FLG == 'C' 
            && BPCOQRTD.DATA.TENOR.equalsIgnoreCase("0")) {
            BPCOQRTD.DATA.TENOR = "" + 301;
            JIBS_tmp_int = BPCOQRTD.DATA.TENOR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCOQRTD.DATA.TENOR = "0" + BPCOQRTD.DATA.TENOR;
        }
        if (BPCOQRTD.INQ_FLG == 'C') {
            if (BPCOQRTD.DATA.CCY.trim().length() == 0 
                || BPCOQRTD.DATA.BASE_TYP.trim().length() == 0 
                || BPCOQRTD.DATA.TENOR.trim().length() == 0) {
                CEP.TRC(SCCGWA, "11111");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQRTD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (BPCOQRTD.DATA.RATE_ID.trim().length() == 0) {
                CEP.TRC(SCCGWA, "22222");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQRTD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_GET_RATE_ID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQRTD.RC);
        IBS.init(SCCGWA, BPRRTID);
        IBS.init(SCCGWA, BPCRMRTD);
        BPCRMRTD.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
        CEP.TRC(SCCGWA, BPRRTID.KEY.ID);
        if (BPCOQRTD.INQ_FLG == 'C') {
            BPRRTID.CCY = BPCOQRTD.DATA.CCY;
            BPRRTID.BASE_TYP = BPCOQRTD.DATA.BASE_TYP;
            BPRRTID.TENOR = BPCOQRTD.DATA.TENOR;
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.CCY);
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.BASE_TYP);
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.TENOR);
            CEP.TRC(SCCGWA, BPRRTID.CCY);
            CEP.TRC(SCCGWA, BPRRTID.BASE_TYP);
            CEP.TRC(SCCGWA, BPRRTID.TENOR);
            BPCRMRTD.INFO.OPT = 'I';
        } else {
            BPRRTID.KEY.ID = BPCOQRTD.DATA.RATE_ID;
        }
        BPCRMRTD.INFO.POINTER = BPRRTID;
        BPCRMRTD.INFO.LEN = 44;
        S000_CALL_BPZRMRTD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRMRTD.RETURN_INFO);
        if (BPCRMRTD.RETURN_INFO == 'F') {
            BPCOQRTD.DATA.RATE_ID = BPRRTID.KEY.ID;
            BPCOQRTD.DATA.CCY = BPRRTID.CCY;
            BPCOQRTD.DATA.BASE_TYP = BPRRTID.BASE_TYP;
            BPCOQRTD.DATA.TENOR = BPRRTID.TENOR;
            BPCOQRTD.DATA.INPUT_DATE = BPRRTID.INPUT_DT;
            BPCOQRTD.DATA.LAST_DATE = BPRRTID.LAST_UPD_DT;
            BPCOQRTD.DATA.LAST_TLR = BPRRTID.LAST_TELLER;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCOQRTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTID-MAINT", BPCRMRTD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOQRTD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOQRTD = ");
            CEP.TRC(SCCGWA, BPCOQRTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
