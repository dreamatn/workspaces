package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSBAS {
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_OUTPUT_FMT = "BP051";
    short WK_MAX_OUTPUT = 500;
    String WS_ERR_MSG = " ";
    BPZFSBAS_WS_OUTPUT_LIST WS_OUTPUT_LIST = new BPZFSBAS_WS_OUTPUT_LIST();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    BPCOFAMO BPCOUAMO = new BPCOFAMO();
    BPCOFCOM BPCOUCOM = new BPCOFCOM();
    BPCOBASO BPCOBASO = new BPCOBASO();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    SCCGWA SCCGWA;
    BPCOFBAS BPCOSBAS;
    public void MP(SCCGWA SCCGWA, BPCOFBAS BPCOSBAS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSBAS = BPCOSBAS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSBAS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUAMO);
        IBS.init(SCCGWA, BPCOUCOM);
        IBS.init(SCCGWA, BPCOBASO);
        IBS.init(SCCGWA, BPCPQGLM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSBAS.FUNC);
        if (BPCOSBAS.FUNC == 'A' 
            || BPCOSBAS.FUNC == 'U') {
            B000_01_CHECK_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCOSBAS.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSBAS.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSBAS.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSBAS.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSBAS.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCOSBAS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (BPCOSBAS.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSBAS.KEY.FEE_CODE);
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
    }
    public void B000_01_CHECK_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSBAS.VAL.AMOT_CODE);
        if (BPCOSBAS.VAL.AMOT_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOUAMO);
            BPCOUAMO.KEY.AMORT_CODE = BPCOSBAS.VAL.AMOT_CODE;
            BPCOUAMO.FUNC = 'I';
            S000_CALL_BPZFUAMO();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B040_01_CHECK_FCOM();
        if (pgmRtn) return;
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
    }
    public void B040_01_CHECK_FCOM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUCOM);
        BPCOUCOM.KEY.FEE_CODE = BPCOSBAS.KEY.FEE_CODE;
        BPCOUCOM.FUNC = 'B';
        BPCOUCOM.OPT = 'S';
        S000_CALL_BPZFUCOM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOSBAS.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPCOUCOM.KEY.FEE_CODE);
        BPCOUCOM.OPT = 'N';
        S000_CALL_BPZFUCOM();
        if (pgmRtn) return;
        while (BPCOUCOM.FOUND_FLG != 'N') {
            BPCOUCOM.OPT = 'N';
            S000_CALL_BPZFUCOM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOUCOM.KEY.FEE_CODE);
            CEP.TRC(SCCGWA, BPCOSBAS.KEY.FEE_CODE);
            if (BPCOUCOM.KEY.FEE_CODE.equalsIgnoreCase(BPCOSBAS.KEY.FEE_CODE)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_USEDBYOTHER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPCOUCOM.OPT = 'E';
        S000_CALL_BPZFUCOM();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        BPCOSBAS.OPT = 'S';
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
        while (BPCOSBAS.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCOSBAS.OPT = 'N';
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOSBAS.FOUND_FLG);
            if (BPCOSBAS.FOUND_FLG != 'N') {
                R00_OUT_RECORDE();
                if (pgmRtn) return;
            }
        }
        BPCOSBAS.OPT = 'E';
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBASO);
