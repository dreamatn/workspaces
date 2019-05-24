package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;
import com.hisun.BP.BPRTRT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZSABSP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSABSP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSABSP_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX01 SMCX01 = new SMCX01();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUQBSP SMCUQBSP = new SMCUQBSP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    SMCSQBSP SMCSQBSP;
    public void MP(SCCGWA SCCGWA, SMCSQBSP SMCSQBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSQBSP = SMCSQBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSABSP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_ADD_RECORD();
        if (pgmRtn) return;
    }
    public void B001_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCUQBSP);
        SMCUQBSP.SERV_CODE = SMCSQBSP.SERV_CODE;
        SMCUQBSP.REMARK = SMCSQBSP.REMARK;
        SMCUQBSP.TABLE_NAME = SMCSQBSP.TABLE_NAME;
        SMCUQBSP.UCMP_NAME = SMCSQBSP.UCMP_NAME;
        SMCUQBSP.BSP_TYPE = SMCSQBSP.BSP_TYPE;
        SMCUQBSP.OPER_STS = SMCSQBSP.OPER_STS;
        SMCUQBSP.WAIT_SERV_CODE = SMCSQBSP.WAIT_SERV_CODE;
        SMCUQBSP.REVERSE_FLG = SMCSQBSP.REVERSE_FLG;
        SMCUQBSP.ERROR_FLG = SMCSQBSP.ERROR_FLG;
        SMCUQBSP.RUN_TYPE = SMCSQBSP.RUN_TYPE;
        SMCUQBSP.TR_CODE = SMCSQBSP.TR_CODE;
        SMCUQBSP.CMP_NAME = SMCSQBSP.CMP_NAME;
        SMCUQBSP.RCP_NAME = SMCSQBSP.RCP_NAME;
        SMCUQBSP.GEN_PRO = SMCSQBSP.GEN_PRO;
        SMCUQBSP.BSP_PROC = SMCSQBSP.BSP_PROC;
        SMCUQBSP.BAT_PROC = SMCSQBSP.BAT_PROC;
        SMCUQBSP.CMT_SEQ = SMCSQBSP.CMT_SEQ;
        S000_CALL_SMZUABSP();
        if (pgmRtn) return;
    }
    public void S000_CALL_SMZUABSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-ADD-BSP-CTL", SMCUQBSP);
        if (SMCUQBSP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUQBSP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSQBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
