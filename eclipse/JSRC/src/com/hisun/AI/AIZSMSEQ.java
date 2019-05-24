package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSMSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CD_1 = "AI827";
    int WS_NUM = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    AICSMSEQ AICSMSEQ;
    public void MP(SCCGWA SCCGWA, AICSMSEQ AICSMSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSMSEQ = AICSMSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSMSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSMSEQ.TYPE);
        CEP.TRC(SCCGWA, AICSMSEQ.CODE);
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = AICSMSEQ.TYPE;
        BPCSGSEQ.CODE = AICSMSEQ.CODE;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        WS_NUM = (int) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, WS_NUM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_1;
        SCCFMT.DATA_PTR = WS_NUM;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
